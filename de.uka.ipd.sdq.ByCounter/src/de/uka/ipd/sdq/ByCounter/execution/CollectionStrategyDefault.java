package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
	 *	A simple {@link List} that holds the results.
	 */
	private List<CountingResult> countingResults;
	
	/** {@link BlockResultCalculation} helper. */
	private BlockResultCalculation blockCalculation;

	/**
	 * Construct the strategy object.
	 * @param parent {@link CountingResultCollector} using this strategy.
	 */
	public CollectionStrategyDefault(CountingResultCollector parent) {
		super(parent);
		this.countingResults = new ArrayList<CountingResult>();
		this.blockCalculation = new BlockResultCalculation(parentResultCollector.blockContext);
	}

	@Override
	public void clearResults() {
		this.log.fine("Used to have "+this.countingResults.size()+" results before clearing");
		this.countingResults.clear();
	}

	
	/**
	 * Decode the information saved for array parameters.
	 * @param newArrayCounts
	 * @param newArrayDescr
	 * @param newArrayTypeOrDim
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

	/** Add to counting results. */
	@Override
	public boolean protocolCount(ProtocolCountStructure result,
			long reportingStart) {
		//TODO assert equal length of newarray inputs
		
		long[] filteredCounts = new long[CountingResult.MAX_OPCODE];
		SortedMap<String, Long> methodCounts = new TreeMap<String, Long>();
		int numResults = 1;	// the number of results created from the values
		
		CalculatedCounts[] ccounts;
		if(result.blockCountingMode == BlockCountingMode.BasicBlocks) {
			if(result.blockExecutionSequence != null) {
				ccounts = blockCalculation.calculateCountsFromBlockExecutionSequence(
						result, false);
				numResults = ccounts.length;
			} else {
				ccounts = new CalculatedCounts[] {
						blockCalculation.calculateCountsFromBBCounts(
						result.qualifyingMethodName, 
						result.opcodeCounts,
						filteredCounts,
						methodCounts)
				};
			}
		} else if (result.blockCountingMode == BlockCountingMode.RangeBlocks) {//Ranges!
			if(result.blockExecutionSequence != null) {
				ccounts = blockCalculation.calculateCountsFromBlockExecutionSequence(
						result, true);
				numResults = ccounts.length;
			} else {
				ccounts = blockCalculation.calculateCountsFromRBCounts(
						result.qualifyingMethodName, 
						result.opcodeCounts,
						filteredCounts,
						methodCounts);
				numResults = ccounts.length;
			}
		} else {
			//TODO check proper length
			// remove all 0 counts
			for(int opcode = 0; opcode < result.opcodeCounts.length; opcode++) {
				if(result.opcodeCounts[opcode] != 0) {
					filteredCounts[opcode] = result.opcodeCounts[opcode];//TODO why filtering???
				}
			}

			assert result.methodCallCounts.length == result.calledMethods.length;
			// create a HashMap for the method signatures and their counts
			for(int i = 0; i < result.methodCallCounts.length; i++) {
				methodCounts.put(result.calledMethods[i], result.methodCallCounts[i]);//TODO too much effort...
			}
			ccounts = new CalculatedCounts[1];//again, too many conversions...
			ccounts[0] = new CalculatedCounts();
			ccounts[0].opcodeCounts = filteredCounts;
			ccounts[0].methodCounts = methodCounts;
//			numResults = ccounts.length;//not needed, because already set to 1 above
		}

		for(int i = 0; i < numResults; i++) {
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

			/* executionStart + i is a used to make sure that two results always have a different starting time.
			 * This is needed for the existing indexing infrastructure only allows one result per time point, yet 
			 * there are more results in the case of line number ranges that we want to distinguish.
			 * This workaround assumes that two methods always start > nrOfCountingResults*1ns apart from each other.
			 * TODO: fix indexing infrastructure instead 
			 */
			long uniqueExecutionStart = result.executionStart + i;
	
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
				uniqueExecutionStart,
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
			this.countingResults.add(res);
			
			int nrOfCountingResults=this.countingResults.size();
			if(nrOfCountingResults%10000==0){
				log.warning(nrOfCountingResults+" results in ByCounter");
			}
	
			parentResultCollector.getCountingResultIndexing().add(
					res, reportingStart);
		}
		return true;
	}

	@Override
	public SortedSet<CountingResult> retrieveAllCountingResults() {
		SortedSet<CountingResult> ret = new TreeSet<CountingResult>();
		if(!parentResultCollector.getLastMethodExecutionDetails().executionSettings.getAddUpResultsRecursively()) {
			Iterator<CountingResult> iter = this.countingResults.iterator();
			while(iter.hasNext()){
				ret.add(iter.next());
			}
		}else{
			// calculate the sums for all results
			long callerStartTime;
			for(int i = 0; i < this.countingResults.size(); i++) {
				callerStartTime = this.countingResults.get(i).getMethodInvocationBeginning();
				CountingResult cr = parentResultCollector.getCountingResultIndexing().retrieveCountingResultByStartTime_evaluateCallingTree(callerStartTime, true);
				if(parentResultCollector.getLastMethodExecutionDetails().executionSettings.isInternalClass(
						cr.getQualifyingMethodName())) {
					ret.add(cr);
				}
			}
		}
		return ret;
	}

}
