package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

import de.uka.ipd.sdq.ByCounter.instrumentation.AdditionalOpcodeInformation;
import de.uka.ipd.sdq.ByCounter.instrumentation.BlockCountingMode;

/**
 * This class is used in {@link CountingResultCollector} in 
 * order to process results during result collection when no other strategy 
 * is specified. 
 * @author Martin Krogmann
 *
 */
public class CollectionStrategyDefault extends AbstractCollectionStrategy {

	
	/**
	 * Simple data structure to hold converted information about new array creation.
	 * @see CountingResultCollector#analyzeArrayParams(long[], String[], int[])
	 * No initialisation happens on construction.
	 */
	private static class NewArrayTypeAndDimension {
		String[] newArrayType;
		int[] newArrayDim;
	}
	

	/**
	 *	A {@link SortedSet} that holds the results.
	 */
	private SortedSet<CountingResult> countingResults;
	
	/** {@link BlockResultCalculation} helper. */
	private BlockResultCalculation blockCalculation;

	/** Indexing infrastructure for counting results. */
	private CountingResultIndexing countingResultIndexing;

	/** Indexing infrastructure for section update results. */
	private CountingResultUpdateIndexing countingResultUpdateIndexing;
	
	/** For each method: Last length of execution sequence. For updates. */
	private Map<UUID, Integer> blockExecutionSequenceLengthByMethod;
	
	/**
	 * Construct the strategy object.
	 * @param parent {@link CountingResultCollector} using this strategy.
	 */
	public CollectionStrategyDefault(CountingResultCollector parent) {
		super(parent);
		this.countingResults = new TreeSet<CountingResult>();
		this.blockCalculation = new BlockResultCalculation(parentResultCollector.blockContext);
		this.countingResultIndexing = new CountingResultIndexing();
		this.countingResultUpdateIndexing = new CountingResultUpdateIndexing();
		this.blockExecutionSequenceLengthByMethod = new HashMap<UUID, Integer>();
	}

	/** {@inheritDoc} */
	@Override
	public void clearResults() {
		this.log.fine("Used to have "+this.countingResults.size()+" results before clearing");
		this.countingResults.clear();
		this.countingResultIndexing.clearResults();
		this.countingResultUpdateIndexing.clearResults();
		this.blockExecutionSequenceLengthByMethod.clear();
	}

	
	/**
	 * Decode the information saved for array parameters.
	 * @param newArrayCounts Array initialisation counts reported from ByCounter
	 * @param newArrayDescr Array descriptors reported from ByCounter
	 * @param newArrayTypeOrDim Array types/dimensions reported from ByCounter
	 */
	public static synchronized NewArrayTypeAndDimension analyzeArrayParams(long[] newArrayCounts, String[] newArrayDescr,
			int[] newArrayTypeOrDim) {
		NewArrayTypeAndDimension result = new NewArrayTypeAndDimension();
		// process information for the *newarray counts
		result.newArrayType = new String[newArrayCounts.length];
		result.newArrayDim = new int[newArrayCounts.length];

		for(int i = 0; i < newArrayCounts.length; i++) {
			// check whether the type is coded into the integer
			if(newArrayDescr[i] == AdditionalOpcodeInformation.NO_INFORMATION_STRING) {
				String str = null;
				// convert the type to a readable string
				switch(newArrayTypeOrDim[i]) {
				case 4:
					str = "boolean";
					break;
				case 5:
					str = "char";
					break;
				case 6:
					str = "float";
					break;
				case 7:
					str = "double";
					break;
				case 8:
					str = "byte";
					break;
				case 9:
					str = "short";
					break;
				case 10:
					str = "int";
					break;
				case 11:
					str = "long";
					break;
				default:
					throw new IllegalArgumentException("Unknown object type id: " + newArrayTypeOrDim[i]);
				}
				result.newArrayType[i] = str;
				// no dimension information here
				result.newArrayDim[i] = AdditionalOpcodeInformation.NO_INFORMATION_INT;
			} else {
				// Type is there as descriptor - just copy it
				result.newArrayType[i] = newArrayDescr[i];
				// copy the dimension information
				result.newArrayDim[i] = newArrayTypeOrDim[i];
			}
		}
		return result;

	}

	/** 
	 * @return Indexing infrastructure for counting results.
	 */
	public CountingResultIndexing getCountingResultIndexing() {
		return countingResultIndexing;
	}

	/** Add to counting results. */
	@Override
	public boolean protocolCount(ProtocolCountStructure result,
			long reportingStart) {
		if(result.newArrayCounts != null) {
			// assert equal length of newarray inputs
			if(result.newArrayCounts.length != result.newArrayDescr.length
					|| result.newArrayDescr.length != result.newArrayTypeOrDim.length) {
				throw new IllegalArgumentException("Reported new array count structures must match in length.");
			}
		}
		// Is this an update?
		if(!(result instanceof ProtocolCountUpdateStructure)) {
			// This is not an update so all updates are done.
			this.countingResultUpdateIndexing.setMethodDone(result.ownID);
		} else if(result.blockExecutionSequence != null) {
			// This is an update. Replace the block execution sequence with 
			// the part of the sequence that is new since the last update.
			Integer lastExSeqLength = blockExecutionSequenceLengthByMethod.get(result.ownID);
			Integer newExSeqLength = result.blockExecutionSequence.size();
			if(lastExSeqLength != null && lastExSeqLength != newExSeqLength) {
				ArrayList<Integer> newSequence = new ArrayList<Integer>();
				for(int i = lastExSeqLength; i < newExSeqLength; i++) {
					newSequence.add(result.blockExecutionSequence.get(i));
				}
				result.blockExecutionSequence = newSequence;
				// consider only the last entered range block?
				int rangeBlockIndex = result.rangeBlockExecutionSequence.get(result.rangeBlockExecutionSequence.size()-1);
				result.rangeBlockExecutionSequence = new ArrayList<Integer>();
				result.rangeBlockExecutionSequence.add(rangeBlockIndex);
			}
			// update length of execution sequence
			blockExecutionSequenceLengthByMethod.put(result.ownID, newExSeqLength);
		}
		
		CalculatedCounts[] ccounts = calculateResultCounts(result);

		for(int i = 0; i < ccounts.length; i++) {
			int[] newArrayDim = null;
			String[] newArrayType = null;
			if(result.newArrayCounts != null && 
					result.newArrayCounts.length != 0 && 
					result.newArrayTypeOrDim != null && 
					result.newArrayTypeOrDim.length != 0 && 
					result.newArrayDescr != null && 
					result.newArrayDescr.length != 0) {
				NewArrayTypeAndDimension r = analyzeArrayParams(
						result.newArrayCounts,
						result.newArrayDescr, 
						result.newArrayTypeOrDim);
				newArrayType = r.newArrayType; 
				newArrayDim = r.newArrayDim;
			}
	
			CountingResult res = new CountingResult(
					result.requestID,
					result.ownID,
					result.callerID,
					/*executionStart,
					qualifyingMethodName,
					filteredCounts,
					methodCounts,
					newArrayCounts,
					newArrayDim,
					newArrayType*/
					result.qualifyingMethodName, //TODO fix it --> Martin; vgl. javadocs zu CountingResult
					result.qualifyingMethodName,
				0, //filetype TODO document
				0L, //input characterisation TODO document
				0L, //output characterisation TODO document
				result.executionStart,
				reportingStart,
				ccounts[i].opcodeCounts, 
				ccounts[i].methodCounts,
				result.newArrayCounts,
				newArrayDim,
				newArrayType
				);
			if(result.blockCountingMode == BlockCountingMode.RangeBlocks) {
				// set the index of the range block, i.e. the number of the section as
				// defined by the user in the instrumentation settings. This 
				// enables the user to find the counts for specific sections.
				res.setIndexOfRangeBlock(ccounts[i].indexOfRangeBlock);
			}
			// When this result is not an update, add it to the permanent results
			if(!(result instanceof ProtocolCountUpdateStructure)) {
				this.countingResults.add(res);
				
				int nrOfCountingResults=this.countingResults.size();
				if(nrOfCountingResults%10000==0){
					log.warning(nrOfCountingResults+" results in ByCounter");
				}
		
				this.countingResultIndexing.add(res, reportingStart);
			} else {
				// result is an instance of ProtocolCountUpdateStructure
				this.countingResultUpdateIndexing.add(res);
			}
		}
		return true;
	}

	/**
	 * Based on the kind of information available in the 
	 * {@link ProtocolCountStructure}, calculate result counts.
	 * @param result The recorded data.´
	 * @return The result counts.
	 * @see CalculatedCounts
	 */
	private CalculatedCounts[] calculateResultCounts(
			ProtocolCountStructure result) {
		CalculatedCounts[] ccounts;
		SortedMap<String, Long> methodCounts = new TreeMap<String, Long>();
		if(result.blockCountingMode == BlockCountingMode.BasicBlocks) {
			if(result.blockExecutionSequence != null) {
				ccounts = blockCalculation.calculateCountsFromBlockExecutionSequence(
						result);
			} else {
				ccounts = new CalculatedCounts[] {
						blockCalculation.calculateCountsFromBBCounts(
						result.qualifyingMethodName, 
						result.opcodeCounts,
						new long[CountingResult.MAX_OPCODE], // opcode counts
						methodCounts)
				};
			}
		} else if (result.blockCountingMode == BlockCountingMode.RangeBlocks) {//Ranges!
			if(result.blockExecutionSequence != null) {
				result.rangeBlockExecutionSequence = removeDuplicateSequencesFromList(result.rangeBlockExecutionSequence);
				ccounts = blockCalculation.calculateCountsFromBlockExecutionSequence(
						result);
			} else {
				ccounts = blockCalculation.calculateCountsFromRBCounts(
						result.qualifyingMethodName, 
						result.opcodeCounts,
						new long[CountingResult.MAX_OPCODE], // opcode counts
						methodCounts);
			}
		} else {
			// check proper length
			if(result.methodCallCounts.length != result.calledMethods.length) {
				throw new IllegalArgumentException("Reported method call count structures must match in length.");
			}
			// create a HashMap for the method signatures and their counts
			for(int i = 0; i < result.methodCallCounts.length; i++) {
				methodCounts.put(result.calledMethods[i], result.methodCallCounts[i]);//TODO too much effort...
			}
			ccounts = new CalculatedCounts[1];//again, too many conversions...
			ccounts[0] = new CalculatedCounts();
			ccounts[0].opcodeCounts = result.opcodeCounts;
			ccounts[0].methodCounts = methodCounts;
			// numResults is already set to 1 above
		}
		return ccounts;
	}

	/**
	 * Remove duplicate sequences from the list and return the filtered result.
	 * Duplicate sequences are identical values that appear multiple times in 
	 * the list with no other values in between. I.e. in the list [1,2,3,3,3,4],
	 * [3,3,3] are duplicate sequences and will be replaced by [3].
	 * @param list List to remove the duplicate sequences from.
	 * @return Filtered list.
	 */
	private <T> ArrayList<T> removeDuplicateSequencesFromList(
			ArrayList<T> list) {
		ArrayList<T> result = new ArrayList<T>();
		T lastEntry = null;
		for(T entry : list) {
			if(lastEntry == entry) {
				continue;
			}
			result.add(entry);
			lastEntry = entry;
		}
		return result;
	}

	@Override
	public SortedSet<CountingResult> retrieveAllCountingResults() {
		SortedSet<CountingResult> ret = new TreeSet<CountingResult>();
		MethodExecutionRecord lastMethodExecutionDetails = parentResultCollector.getLastMethodExecutionDetails();
		if(lastMethodExecutionDetails == null) {
			log.warning("No method execution details are available. Please make certain that instrumented code has been executed.");
		}
		
		if(lastMethodExecutionDetails != null && !lastMethodExecutionDetails.executionSettings.getAddUpResultsRecursively()) {
			// no more calculation is necessary; return results
			Iterator<CountingResult> iter = this.countingResults.iterator();
			while(iter.hasNext()){
				ret.add(iter.next());
			}
		} else {
			// calculate the sums for all results
			long callerStartTime;
			long callerReportTime;
			long prevCallerReportTime = Long.MIN_VALUE;
			for(CountingResult cr : this.countingResults) {
				cr.logResult(false, true);
			}
			for(CountingResult cr : this.countingResults) {
				// countingResults are ordered by callerStartTime!
				callerStartTime = cr.getMethodInvocationBeginning();
				callerReportTime = cr.getMethodReportingTime();
				if(prevCallerReportTime > callerReportTime) {
					// do not return results that have been added up into a previous result already
					continue;
				}
				CountingResult crSum = this.countingResultIndexing.retrieveCountingResultByStartTime_evaluateCallingTree(callerStartTime, true);
				if(lastMethodExecutionDetails == null
						|| lastMethodExecutionDetails.executionSettings.isInternalClass(
						crSum.getQualifyingMethodName())) {
					ret.add(crSum);
					prevCallerReportTime = callerReportTime;
				}
			}
		}
		return ret;
	}

}
