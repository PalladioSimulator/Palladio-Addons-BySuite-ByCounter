package de.uka.ipd.sdq.ByCounter.execution;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.instrumentation.AdditionalOpcodeInformation;
import de.uka.ipd.sdq.ByCounter.reporting.ICountingResultWriter;
import de.uka.ipd.sdq.ByCounter.utils.FullOpcodeMapper;

/**
 * Class used to collect statistics about an instrumented method.
 * TODO implement an "adaptation-oriented inlining", where after a certain (threshold) number of invocations, a method is inlined (callees independently, too)
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class CountingResultCollector {
	
	private static final char INTERNAL_CLASS_DEFINITION_WILDCARD_CHAR = '*';

	/**
	 * Used by ByCounter to identify results from code instrumented to use 
	 * basic blocks for counting.
	 */
	public static final String BASIC_BLOCK_MAGIC_PREFIX = "__BB__";
	
	private static final int DEFAULT_THRESHOLD_PER_REPORTING_METHOD = 100;

	private static final int DEFAULT_TOTAL_THRESHOLD = 10000;

	/**
	 * Think about the singleton pattern here :-)
	 */
	private static CountingResultCollector instance = null;
	
	/**
	 * Used by ByCounter to identify results from code instrumented with range 
	 * block counters.
	 */
	public static final String RANGE_BLOCK_MAGIC_PREFIX = "__RB__";

	/**
	 * The bytecode parameter descriptor for
	 * {@link #protocolCountInt(ProtocolCountStructure)} and for
	 * {@link #protocolCountLong(ProtocolCountStructure)}.
	 */
	public static final String SIGNATURE_protocolCount = "(Lde/uka/ipd/sdq/ByCounter/execution/ProtocolCountStructure;)V";

	/**
	 * When true, do not calculate opcode counts from the basic block execution
	 * counts instantly, but only when the results are requested. This is 
	 * only relevant to inlining.
	 */
	private static final boolean useDeferredBBcalculations = true;
	
	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * Public singleton accessor. Use this to get a reference
	 * to the singleton instance.
	 * @return The singleton instance of {@link CountingResultCollector}.
	 */
	public synchronized static CountingResultCollector getInstance() {
		if (instance == null) {
			instance = new CountingResultCollector();
		}
		return instance;
	}

	/**
	 * retrieve the full counting artefact information by the beginning time
	 */
	private HashMap<Long,CountingArtefactInformation> countingInformationsByBeginning; //later, use SortedSet (after defining a comparator...)

	/**
	 * Retrieve all invocations of a method by its signature
	 */
	private HashMap<String,List<CountingArtefactInformation>> countingInformationsByMethodname; //later, use SortedSet (after defining a comparator...)

	/**
	 *	a simple ArrayList that holds a list of results
	 */
	private ArrayList<CountingResult> countingResults;//TODO make this a sorted list... --> runtime overhead?

	/**
	 * Database replacement ;-)
	 * TODO does not consider forced inlining
	 */
	private HashMap<CountingArtefactInformation, CountingResult> countingResultsByArtefactInformation;

	/**
	 * Currently evaluated; see forceInline method for controlling flags
	 * @deprecated because untested and not evaluated
	 */
	private List<long[]> forcedInlining_CollectedMethodCallCounts;

	/**
	 * Currently evaluated; see forceInline method for controlling flags
	 * @deprecated because untested and not evaluated
	 */
	private List<String[]> forcedInlining_CollectedMethodCallSignatures;
	
	/**
	 * Should be re-appended carefully to the lists returned by *get methods
	 */
	private CountingResult forcedInlining_CountingResult;

	/**
	 * TODO should this really be a separate structure? Yes, because forced inlining can be switched on and off.
	 * TODO ensure that this structure is properly read.
	 */
	private SortedSet<CountingResult> forcedInlining_CountingResultsSortedSet;

//	/**
//	 * TODO should this really be a separate structure?
//	 */
//	private SortedSet<SortedMap<String, Integer>> forcedInlining_InlinedMethodMappings;

	private SortedMap<String, Long> forcedInlining_InlinedMethodCounts;

	/**
	 * TODO make sure those are printed when counting results are logged...
	 * TODO work as a pointer to the field of forcedInlining_CountingResult instead?
	 */
	private long[] forcedInlining_InlinedOpcodeCounts;

	/**
	 * Complements forcedInlining_Opcode counts.
	 * TODO there should be a SortedSet of such elements, similarily to forcedInlining_CountingResultSet 
	 * since method counts should also reflect the possibility to switch forced inlining on and off. 
	 * TODO work as a pointer to the field of forcedInlining_CountingResult instead?
	 */
	private SortedMap<String, Integer> forcedInlining_OccurenceCountsReportingMethods;

	private Integer forcedInlining_thresholdPerReportingMethod;

	
	private Integer forcedInlining_thresholdTotalMaximum;

	private Integer forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining;//don't forget to reset!

	/**
	 * Unlike forced inlining, this field is for method-level specification of inlining (e.g. for invariant methods)
	 */
	private CountingResult inlined_countingResult;
	
	/**
	 * Describes how often a certain method has been inlined (by method's request, not by forced inlining)...
	 */
	private TreeMap<String,Integer> inlined_methodsMap;
	
	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private Logger log;
	
	/**
	 * The main flag which switches forced inlining on...
	 */
	private CountingResultCollectorModeEnum mode;

	/**
	 * For usage related to the class CountingResultCollectorMonitor...
	 * TODO refactor this.
	 */
	private boolean monitorShouldStop;

	/**
	 * When a {@link CountingResult} is logged, all known writers will be
	 * asked to log (write) it as well. This mechanism is introduced to
	 * decouple {@link CountingResultCollector} from specific mechanisms and
	 * frameworks, such as CSV writing, JFreeChart creation etc.
	 */
	List<ICountingResultWriter> resultWriters;

	/**
	 * Maps a method to the execution counts of the basic blocks 
	 * in that method.
	 */
	private long[][] uncalculatedBBCounts;
	
	/**
	 * Basic block and range block definitions.
	 */
	public BlockDefinitionContext blockContext;
	
	/**
	 * {@link BlockResultCalculation} helper.
	 */
	private BlockResultCalculation blockCalculation;

	/**
	 * Maps the method signature to the index in {@link #uncalculatedBBCounts}.
	 * This indirection is used to avoid the costs of boxing/unboxing 
	 * values in a Long[][] array by using the long[][] array.
	 */
	private Map<String, Integer> uncalculatedBBCounts_Index;

	boolean verbose = false;

	/**
	 * Method execution details on how BytecodeCounters execute method was 
	 * last called.
	 */
	private MethodExecutionRecord lastMethodExecutionDetails;

	/**
	 * Classes defined as internal when using recursive result retrieval.
	 * 
	 * @see #setInternalClassesDefinition(Set)
	 */
	private Set<String> internalClassesDefinition;
	
	/**
	 * Private constructor that is invoked to create the singleton instance
	 */
	private CountingResultCollector() {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.countingInformationsByBeginning = new HashMap<Long, CountingArtefactInformation>();
		this.countingInformationsByMethodname = new HashMap<String, List<CountingArtefactInformation>>();//TODO consider removing this...
		this.countingResults = new ArrayList<CountingResult>();
		this.countingResultsByArtefactInformation = new HashMap<CountingArtefactInformation, CountingResult>();
		this.forcedInlining_CollectedMethodCallCounts = new ArrayList<long[]>();
		this.forcedInlining_CollectedMethodCallSignatures = new ArrayList<String[]>();
		this.forcedInlining_CountingResult = createNewForcedInlinedCountingResult();
		this.forcedInlining_CountingResultsSortedSet = new TreeSet<CountingResult>();
		this.forcedInlining_InlinedMethodCounts = new TreeMap<String, Long>();
		this.forcedInlining_InlinedOpcodeCounts = new long[CountingResult.MAX_OPCODE];
		this.forcedInlining_OccurenceCountsReportingMethods = new TreeMap<String, Integer>();
		this.forcedInlining_thresholdPerReportingMethod = DEFAULT_THRESHOLD_PER_REPORTING_METHOD;
		this.forcedInlining_thresholdTotalMaximum = DEFAULT_TOTAL_THRESHOLD;
		this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining = 0;//even augment
		this.inlined_countingResult = new CountingResult(
				UUID.randomUUID(),//TODO use constants for this!
				UUID.randomUUID(),
				UUID.randomUUID(),
				"inlined",
				"______inlined______",
				0, //filetype
				0L, //input characterisation
				0L, //output characterisation
				0L, //invocation beginning
				0L, //reporting time
				new long[CountingResult.MAX_OPCODE],
				new TreeMap<String,Long>(),
				new long[]{},
				new int[]{},
				new String[]{},
				new TreeMap<BytecodeSectionDescription,SortedMap<Integer,Long>>(),
				new TreeMap<BytecodeSectionDescription,SortedMap<String,Long>>()
				);
		this.inlined_methodsMap = new TreeMap<String, Integer>();
		this.mode = CountingResultCollectorModeEnum.UseReportingMethodChoiceByInstrumentedMethods;
		this.resultWriters = new ArrayList<ICountingResultWriter>();
		this.uncalculatedBBCounts_Index = new HashMap<String, Integer>();
		
		this.blockContext = new BlockDefinitionContext();
		this.blockContext.tryToLoadBasicBlockSerialisation();
		this.blockCalculation = new BlockResultCalculation(blockContext);
	}

	private synchronized void addToCountingResults(
			ProtocolCountStructure result,
			long reportingStart) {
		//TODO assert equal length of newarray inputs
		
		long[] filteredCounts = new long[CountingResult.MAX_OPCODE];
		SortedMap<String, Long> methodCounts = new TreeMap<String, Long>();
		int numResults = 1;	// the number of results created from the values
		
		boolean convertCountsFromBasicBlockCounts = false;
		boolean convertCountsFromRangeBlockCounts = false;
		if(result.qualifyingMethodName.startsWith(BASIC_BLOCK_MAGIC_PREFIX)) {
			convertCountsFromBasicBlockCounts = true;
			result.qualifyingMethodName =
				parseBBQualifyingMethodName(false, result.qualifyingMethodName);
		} else if(result.qualifyingMethodName.startsWith(RANGE_BLOCK_MAGIC_PREFIX)) {
			convertCountsFromRangeBlockCounts = true;
			result.qualifyingMethodName = 
				parseBBQualifyingMethodName(true, result.qualifyingMethodName);			
		}
		CalculatedCounts[] ccounts;
		if(convertCountsFromBasicBlockCounts) {
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
		} else if (convertCountsFromRangeBlockCounts) {//Ranges!
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
				analyzeArrayParams(newArrayType, 
						newArrayDim, 
						result.newArrayCounts,
						result.newArrayDescr, 
						result.newArrayTypeOrDim);
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
				newArrayType,
				null, //sectionInstCounts,
				null  //sectionMethCounts
				);
			if(convertCountsFromRangeBlockCounts) {
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
	
			insertCountingResultIntoIndexingInfrastructures(
					uniqueExecutionStart, 
					result.qualifyingMethodName, reportingStart, res);
		}
	}

	private void insertCountingResultIntoIndexingInfrastructures(
			long executionStart, String qualifyingMethodName,
			long reportingStart, CountingResult res) {
		CountingArtefactInformation artefact;//does THIS create too much overhead? it requires ALL elements to be in memory?
		artefact = new CountingArtefactInformation(
				qualifyingMethodName,
				executionStart,
				null,//input parameters
				reportingStart,
				null //output parameters
				);

		this.countingInformationsByBeginning.put(executionStart, artefact);
		Set<String> keys = this.countingInformationsByMethodname.keySet();
		if(keys.contains(qualifyingMethodName)){
			this.countingInformationsByMethodname.get(qualifyingMethodName).add(artefact);
		}else{
			List<CountingArtefactInformation> list = new ArrayList<CountingArtefactInformation>();
			list.add(artefact);
			this.countingInformationsByMethodname.put(qualifyingMethodName, list);
		}
		this.countingResultsByArtefactInformation.put(artefact, res);
	}
	
	private void addToWishedInliningResult(
			ProtocolCountStructure result) {
		long[] opcodes = this.inlined_countingResult.getOpcodeCounts();
		Long currOpcodeCount;
		//can this be done without copying to save time in GC as well as memory?
		for(int opcode = 0; opcode < result.opcodeCounts.length; opcode++) {
			//check opcodeCounts[opcode] for being zero and skip the following actions?
			currOpcodeCount = opcodes[opcode];
			opcodes[opcode] = result.opcodeCounts[opcode] + currOpcodeCount;
		}
		this.inlined_countingResult.overwriteOpcodeCounts(opcodes);

		Long currMethodCount;
		SortedMap<String,Long> methods = this.inlined_countingResult.getMethodCallCounts();
		assert result.methodCallCounts.length == result.calledMethods.length;
		// create a HashMap for the method signatures and their counts
		for(int i = 0; i < result.methodCallCounts.length; i++) {
			currMethodCount = methods.get(result.calledMethods[i]);
			if(currMethodCount == null){
				methods.put(result.calledMethods[i], result.methodCallCounts[i]);
			}else{
				methods.put(result.calledMethods[i], currMethodCount+result.methodCallCounts[i]);//superfluous additions of repeated zeros?
			}
		}
		this.inlined_countingResult.overwriteMethodCallCounts(methods);
		
		//increasing the count of how often this method was inlined //TODO make sure this is printed by SPECCompressCountingStarter etc.
		Integer count = this.inlined_methodsMap.get(result.qualifyingMethodName);
		if(count==null || count.equals(new Integer(0))){
			this.inlined_methodsMap.put(result.qualifyingMethodName, 1);
			log.fine("First time that counts inlined for "+result.qualifyingMethodName);
			log.warning("addToWishedInliningResult and its callers ignore array details");
		}else{
			this.inlined_methodsMap.put(result.qualifyingMethodName, count+1);
//			log.fine((count+1)+". time that counts inlined for "+qualifyingMethodName);
		}
	}
	
	/**
	 * TODO test this method out-refactoring
	 * @param newArrayType
	 * @param newArrayDim
	 * @param newArrayCounts
	 * @param newArrayDescr
	 * @param newArrayTypeOrDim
	 */
	public synchronized void analyzeArrayParams(String[] newArrayType, int[] newArrayDim, long[] newArrayCounts, String[] newArrayDescr,
			int[] newArrayTypeOrDim){
		// process information for the *newarray counts
		newArrayType = new String[newArrayCounts.length];
		newArrayDim = new int[newArrayCounts.length];

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
					log.severe("Unknown object type id: " + newArrayTypeOrDim[i]);
					break;
				}
				newArrayType[i] = str;
				// no dimension information here
				newArrayDim[i] = AdditionalOpcodeInformation.NO_INFORMATION_INT;
			} else {
				// Type is there as descriptor - just copy it
				newArrayType[i] = newArrayDescr[i];
				// copy the dimension information
				newArrayDim[i] = newArrayTypeOrDim[i];
			}
		}

	}

	/**
	 * Clear all results in the internal list.
	 */
	public synchronized void clearResults() {
		this.log.fine("Used to have "+this.countingResults.size()+" results before clearing");
		this.countingInformationsByBeginning.clear();
		this.countingInformationsByMethodname.clear();
		this.countingResults.clear();
		this.countingResultsByArtefactInformation.clear();
		this.forcedInlining_CollectedMethodCallCounts = new ArrayList<long[]>();
		this.forcedInlining_CollectedMethodCallSignatures = new ArrayList<String[]>();
		this.forcedInlining_CountingResult = createNewForcedInlinedCountingResult();
		this.forcedInlining_CountingResultsSortedSet = new TreeSet<CountingResult>();
		this.forcedInlining_InlinedMethodCounts = new TreeMap<String, Long>();
		this.forcedInlining_InlinedOpcodeCounts = new long[CountingResult.MAX_OPCODE];
		this.forcedInlining_OccurenceCountsReportingMethods = new TreeMap<String, Integer>();//TODO or clear?
		this.inlined_countingResult.resetMethodAndInstructionCounts();//TODO refactor for efficiency
		this.inlined_methodsMap = new TreeMap<String, Integer>();
		this.uncalculatedBBCounts = null;	// is initialised on demand
	}

	private synchronized CountingResult createNewForcedInlinedCountingResult() {
		return new CountingResult(
				UUID.randomUUID(),//requestID
				UUID.randomUUID(),//ownID
				UUID.randomUUID(),//callerID
				"forcedInlined",//id
				"______forcedInlined______",//qualifying method name
				0, //filetype
				0L, //input characterisation
				0L, //output characterisation
				System.nanoTime/*currentTimeMillis*/(), //invocation beginning
				0L, //reporting time TODO use a Date-like class for this...
				new long[CountingResult.MAX_OPCODE],//opcode counts
				new TreeMap<String,Long>(),//method call counts
				new long[]{},//array creation counts
				new int[]{},//array creation dimensions
				new String[]{},//array creation type info
				new TreeMap<BytecodeSectionDescription,SortedMap<Integer,Long>>(),//section instruction counts
				new TreeMap<BytecodeSectionDescription,SortedMap<String,Long>>()//section method counts
				);
	}

	/**
	 * TODO for forced inlining, efficiency can be significantly increased by counting only those methods which have not been instrumented. This has to be a modification of the instrumenting mechanism...
	 */
	private synchronized void forceInline(
			long reportingTime, 
			ProtocolCountStructure result,
			boolean countReportsPerSignature) {
		
		// look for basic block counting
		boolean convertCountsFromOpcodeCounts = false;
		if(result.qualifyingMethodName.startsWith(BASIC_BLOCK_MAGIC_PREFIX)) {
			convertCountsFromOpcodeCounts = true;
			result.qualifyingMethodName =
				parseBBQualifyingMethodName(false, result.qualifyingMethodName);
		} else if(result.qualifyingMethodName.startsWith(RANGE_BLOCK_MAGIC_PREFIX)) {
			// separate range block results make no sense for inlining because 
			// the results are all added up anyways, so use basic block routines
			convertCountsFromOpcodeCounts = true;
			result.qualifyingMethodName =
				parseBBQualifyingMethodName(true, result.qualifyingMethodName);
		}
		if(convertCountsFromOpcodeCounts) {
			if(useDeferredBBcalculations) {
				// save the values
				saveUncalculatedBBCounts(result.qualifyingMethodName, result.opcodeCounts);
			} else {
				// calculate immediatly
				CalculatedCounts ccounts = blockCalculation.calculateCountsFromBBCounts(
						result.qualifyingMethodName,
						result.opcodeCounts,
						forcedInlining_InlinedOpcodeCounts, 
						forcedInlining_InlinedMethodCounts);
				// the forcedInlining_ values are included in the new counts
				// so apply the results
				forcedInlining_InlinedOpcodeCounts = ccounts.opcodeCounts;
				forcedInlining_InlinedMethodCounts = ccounts.methodCounts;
			}
		} else {
			
			for(int opcode = 0; opcode < result.opcodeCounts.length; opcode++) {
				forcedInlining_InlinedOpcodeCounts[opcode] += result.opcodeCounts[opcode];
				//check opcodeCounts[opcode] for being zero and skip the following actions? the following code is copy-and-paste legacy
	//			currOpcodeCount = opcodes[opcode];
	//			opcodes[opcode] = currOpcodeCount + opcodeCounts[opcode]; //superfluous additions of repeated zeros?
			}
	//		if(forcedInlining_PostponeEvaluationOfMethodCounts){
	//			forcedInlining_CollectedMethodCallCounts.add(methodCallCounts);//TODO re-activate
	//			forcedInlining_CollectedMethodCallSignatures.add(calledMethods);
	//		}else{
	//			TreeMap<String,Long> methods = this.forcedInlining_CountingResult.getMethodCallCounts();
				Long currMethodCount;
				assert result.methodCallCounts.length == result.calledMethods.length;//escalate warning - RuntimeException?
				// create a HashMap for the method signatures and their counts
				for(int i = 0; i < result.methodCallCounts.length; i++) {
					currMethodCount = forcedInlining_InlinedMethodCounts.get(result.calledMethods[i]);
					if(currMethodCount == null){
						forcedInlining_InlinedMethodCounts.put(result.calledMethods[i], result.methodCallCounts[i]);
					}else{
						forcedInlining_InlinedMethodCounts.put(result.calledMethods[i], currMethodCount+result.methodCallCounts[i]);//superfluous additions of repeated zeros?
					}
				}
				//TODO check if needed! this.forcedInlined_CountingResult.overwriteMethodCallCounts(methods);//TODO is this really efficient?
		
				//increasing the count of how often this method was inlined //TODO make sure this is printed by SPECCompressCountingStarter etc.
	//			if(forcedInlining_TrackSignaturesOfForceInlinedInstrumentedMethods ){
	//				Integer count = this.forcedInlining_InlinedMethodMapping.get(qualifyingMethodName);
	//				if(count==null || count.equals(new Integer(0))){//TODO pre-initialising this structures with zeros is more efficient!
	//					this.forcedInlining_InlinedMethodMapping.put(qualifyingMethodName, 1);
	//					log.fine("First time that counts force-inlined for "+qualifyingMethodName);
	//					log.fine("WARNING: forceInline ignores array details and tracing/request IDs");
	//				}else{
	//					this.forcedInlining_InlinedMethodMapping.put(qualifyingMethodName, count+1);
	//		//			log.fine((count+1)+". time that counts inlined for "+qualifyingMethodName);
	//				}
	//			}
	//		}
		}
		
		if(result.executionStart<forcedInlining_CountingResult.forcedInlining_earliestStartOfInlinedMethod){
			forcedInlining_CountingResult.forcedInlining_earliestStartOfInlinedMethod = result.executionStart;
		}
//		TreeMap<Integer,Long> opcodes = this.forcedInlined_CountingResult.getOpcodeCounts();
//		Long currOpcodeCount;
//		this.forcedInlined_CountingResult.overwriteOpcodeCounts(opcodes);
		
	}

	/**
	 * Gets the mapping of {@link CountingArtefactInformation} to {@link CountingResult}s.
	 * @return The mapping as {@link HashMap}.
	 */
	public HashMap<CountingArtefactInformation, CountingResult> getAllCountingResultsByArtefacts() {
		log.warning("getAllCountingResultsByArtefacts disregards inlined and force-inlined methods, " +
				"use retrieveAllCountingResults instead");
		return this.countingResultsByArtefactInformation;
	}

	/**
	 * Gets all result writers registered to the collector.
	 * @return A list of {@link ICountingResultWriter}s.
	 */
	public List<ICountingResultWriter> getAllResultWriters() {
		return this.resultWriters;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?!
	 * @return A {@link HashMap}. The long value is the time as
	 * returned by System.nanoTime().
	 */
	public HashMap<Long, CountingArtefactInformation> getCountingArtefactsByBeginning() {
		log.warning("getCountingArtefactsByBeginning disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by method name.
	 * TODO does not consider forced inlining?
	 * @return A {@link HashMap}. The {@link String} is the method name.
	 */
	public HashMap<String, List<CountingArtefactInformation>> getCountingArtefactsByMethodname() {
		log.warning("getCountingArtefactsByMethodname disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByMethodname;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by method name.
	 * TODO does not consider forced inlining?
	 * @param name The method name used to select the
	 * {@link CountingArtefactInformation} that is returned.
	 * @return The specified list of {@link CountingArtefactInformation}.
	 */
	public List<CountingArtefactInformation> getCountingArtefactsByName(String name){
		log.warning("getCountingArtefactsByName disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByMethodname.get(name);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param time A time as returned by System.nanoTime().
	 * @return The specified {@link CountingArtefactInformation}.
	 */
	public CountingArtefactInformation getCountingArtefactsByTime(long time){
		log.warning("getCountingArtefactsByTime disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning.get(time);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param timestamp A time as {@link Timestamp}.
	 * @return The specified {@link CountingArtefactInformation}.
	 */
	public CountingArtefactInformation getCountingArtefactsByTimestamp(Timestamp timestamp){
		log.warning("getCountingArtefactsByTimestamp disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning.get(timestamp.getTime());
	}

	/**
	 * Gets the {@link CountingResult} that corresponds to the given
	 * {@link CountingArtefactInformation}.
	 * TODO does not consider forced inlining?
	 * @param cai A {@link CountingArtefactInformation}.
	 * @return The {@link CountingResult} described by cai.
	 */
	public CountingResult getCountingResultByArtefact(CountingArtefactInformation cai) {
		log.warning("getCountingResultByArtefact disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingResultsByArtefactInformation.get(cai);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * Timestamp should be unique.
	 * TODO does not consider forced inlining?
	 * @param timestamp A time as {@link Timestamp}.
	 * @return The specified {@link CountingResult}.
	 */
	public CountingResult getCountingResultByMethodStartTimestamp(Timestamp timestamp){
		log.warning("getCountingResultByMethodStartTimestamp disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingResultsByArtefactInformation.get(this.countingInformationsByBeginning.get(timestamp.getTime()));
	}

	public SortedMap<String, Integer> getForcedInlining_OccurenceCountsReportingMethods() {
		return this.forcedInlining_OccurenceCountsReportingMethods;
	}

	public CountingResult getInlined_countingResult() {
		return this.inlined_countingResult;
	}

	public CountingResultCollectorModeEnum getMode() {
		return this.mode;
	}

	/**
	 * Return an appropriate number of tabs to follow the given string.
	 * This is for log formatting purposes only.
	 * @param str String after which the tabs shall follow
	 * @param maxNumTabs maximum number of tabs to return.
	 * @return A string containing a fitting number of tabs.
	 */
	private static synchronized String getTabs(String str, int maxNumTabs) {
		StringBuilder tabs = new StringBuilder();
		for(int i = maxNumTabs; i > 0; i--) {
			if(str.length() < 8*i) {//TODO encode tab width variably?
				tabs.append("\t");
			} else {
				break;
			}
		}
		return tabs.toString();
	}

	public boolean isForceInliningIgnoringMethodWishes() {
		if(mode.equals(CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts)
				|| mode.equals(CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts_ButCountReportsPerSignature)
				|| mode.equals(CountingResultCollectorModeEnum.UseThresholdPerReportingMethod_UntilTotalThresholdReachedThenForceInline)
				|| mode.equals(CountingResultCollectorModeEnum.UseTotalThreshold_RegardlessOfIndividualMethodCountsThenForceInline)){
			return true;
		}else{
			return false;
		}
	}

//	/**
//	 * TODO how to make the parameter unmodifiable?
//	 * @return
//	 */
//	public CountingResult getForcedInlined_CountingResult() {
//		return this.forcedInlining_CountingResult;
//	}

	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 */
	public boolean isMonitorShouldStop() {
		return this.monitorShouldStop;
	}

//	/**
//	 * Print a log message that reports the result, listing all counts and
//	 * data that was collected.
//	 * @param cr Result to report.
//	 * @param printZeros
//	 * @param vertically
//	 */
//	public synchronized /*DefaultCategoryDataset*/ void logResult(
//			CountingResult cr,
//			boolean printZeros, //eigentlich 3 Abstufungen: gar nicht; wie gespeichert; alle opcodes (auch wenn nicht gespeichert)
//			boolean vertically //TODO currently ignored
//			) {
//		StringBuffer sb = new StringBuffer();
//		sb.append("\n==START========= Logging CountingResult ================\n");
//		if(cr==null) {
//			log.severe("CountingResult to log is null, EXITING");
//			sb.append("== END ========= Logging CountingResult ================\n");
//			log.info(sb.toString());
//			return /*null*/;
//		}
//		String qualifyingMethodName = cr.getQualifyingMethodName();
//		if(qualifyingMethodName==null || qualifyingMethodName.equals("")) {
//			log.severe("Qualifying method name is null or empty, EXITING");
//			sb.append("== END ========= Logging CountingResult ================\n");;
//			log.info(sb.toString());
//			return /*null*/;
//		}
//		sb.append("qualifyingMethodName: " + qualifyingMethodName+"\n");
//		sb.append("requestID: " + cr.getRequestID() + ", ownID: " + cr.getOwnID()
//				+ ", callerID: " + cr.getCallerID()+"\n");
////		if(cr==null){
////			log.severe("The CountingResult to log is null - nothing to do, returning immediately.");
////			log.info("== END ========= Logging CountingResult ================");
////			return;
////		}
//
//		long[] opcodeCounts 		= cr.getOpcodeCounts();
//		if(opcodeCounts == null) {
//			log.severe("Opcode counts is null... EXITING");
//			sb.append("== END ========= Logging CountingResult ================\n");
//			log.info(sb.toString());
//			return /*null*/;
//		}
//
//		SortedMap<String, Long> methodCallCounts 		= cr.getMethodCallCounts();
//		if(methodCallCounts == null) {
//			log.severe("Method counts hashmap is null... EXITING");
//			sb.append("== END ========= Logging CountingResult ================\n");
//			log.info(sb.toString());
//			return /*null*/;
//		}
//
//		long time = cr.getMethodInvocationBeginning();
//		if(time<0) {
//			log.severe("Wrong time: "+time);//TODO which kind of time is this?
//			sb.append("== END ========= Logging CountingResult ================\n");
//			log.info(sb.toString());
//			return /*null*/;
//		}
//
//		if(resultWriters.size()>0){
//			log.fine("Logging CountinResult using "+resultWriters.size()+
//					" registered result writers");
//			for(ICountingResultWriter rw : resultWriters){
//				rw.writeResult(cr, false, -1);//TODO make this better/parameterised
//			}
//		}
//
//		long[] newArrayCounts 						= cr.getNewArrayCounts();
//		int[] newArrayDims 							= cr.getNewArrayDim();
//		String[] newArrayTypes 						= cr.getNewArrayTypes();
//
//		// No checks here (but below!) for array results, because null is also
//		// returned when array parameter recording is disabled.
//
//		// make sure DisplayOpcodes does not interfere with the output...?
//		ASMOpcodesMapper dop = ASMOpcodesMapper.getInstance();
//		long totalCountOfAllOpcodes = 0; //you need longs for that...
//		long totalCountOfAllMethods = 0; //you need longs for that...
//
//		String 	tabs;					// tabulators (for logging)
//		String 	currentOpcodeString;	// opcode as string
//		long 	currentOpcodeCount;		// opcode count
//		long 	currentMethodCount = 0;	// method count
//
//
//		int numberOfOpcodesWithNonzeroFrequencies=0;
//		for(int i = 0; i < CountingResult.MAX_OPCODE; i++) {
//			currentOpcodeString = dop.getOpcodeString(i);
//			currentOpcodeCount 	= opcodeCounts[i];
//			tabs 				= getTabs(currentOpcodeString + ":", 2);
////			dataset.addValue(currentOpcodeCount, qualifyingMethodName+": instructions", currentOpcodeString);
//			if(currentOpcodeCount!=0 || printZeros){
//				sb.append(currentOpcodeString + ": "+tabs+currentOpcodeCount+"\n");//TODO make a cheaper call... append to a StringBuffer
//			}
//			if((totalCountOfAllOpcodes+currentOpcodeCount)<totalCountOfAllOpcodes){
//				log.severe("OVERFLOW while adding opcode counts... use BigInteger instead");
//			}else{
//				totalCountOfAllOpcodes += currentOpcodeCount;
//				if(currentOpcodeCount>0){
//					numberOfOpcodesWithNonzeroFrequencies++;
//				}
//			}
//		}
//
//		Iterator<String> methodSigs = methodCallCounts.keySet().iterator();
//		String currentSig;
//		while(methodSigs.hasNext()) {
//			currentSig = methodSigs.next();
//			currentMethodCount = methodCallCounts.get(currentSig);
//			tabs = getTabs(currentSig + ":", 9);
////			dataset.addValue(currentMethodCount, qualifyingMethodName+": methods", currentMethodSignature);
//			sb.append(currentSig + ": " + tabs + currentMethodCount+"\n");
//			if(totalCountOfAllMethods + currentMethodCount<totalCountOfAllMethods){
//				log.severe("OVERFLOW while adding method counts");
//			}else{
//				totalCountOfAllMethods += currentMethodCount;
//			}
////			instrnames_texSB.append(currentMethodSignature+" & ");
////			instrcounts_texSB.append(currentMethodCount+" & ");
//		}
////		instrnames_texSB.append("total \\\\"); //TODO use File.separator here
////		instrcounts_texSB.append(totalCountOfAllMethods+" \\\\"); //TODO use File.separator here
//
//		// because null is a valid value for the array*Something* arrays,
//		// we need to be carefull here.
//		if(newArrayCounts != null
//				&& newArrayDims != null
//				&& newArrayTypes != null) {
//			for(int i = 0; i < newArrayCounts.length; i++) {
//				sb.append("new array of type '" + newArrayTypes[i] + "'"
//						+ (newArrayDims[i] > 0 ? ", dim " + newArrayDims[i] : "")
//						+ ": " + newArrayCounts[i]+"\n");
//			}
//		}
//		sb.append("====================================================\n");
//		sb.append(totalCountOfAllOpcodes + " instruc. executions of "+
//				numberOfOpcodesWithNonzeroFrequencies + " different opcodes were counted.\n");
//		sb.append(totalCountOfAllMethods + " methods invocations of "+
//				methodCallCounts.size() + " different signatures were counted.\n");
//		sb.append("== END ========= Logging CountingResult ================\n");
//		log.info(sb.toString());
//	}

	public synchronized String logResult(CountingResult cr,
			boolean printZeros, //eigentlich 3 Abstufungen: gar nicht; wie gespeichert; alle opcodes (auch wenn nicht gespeichert)
			boolean vertically //TODO currently ignored
			){
		return logResult(cr,printZeros,vertically,Level.INFO);
	}

	/**
	 * Print a log message that reports the result, listing all counts and
	 * data that was collected.
	 * @param cr Result to report.
	 * @param printZeros
	 * @param vertically
	 */
	public synchronized String logResult(
			CountingResult cr,
			boolean printZeros, //eigentlich 3 Abstufungen: gar nicht; wie gespeichert; alle opcodes (auch wenn nicht gespeichert)
			boolean vertically, //TODO currently ignored
			Level loggingLevel
			) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n==START========= Logging CountingResult ================");
		sb.append(NEWLINE);
		if(cr==null) {
			log.severe("CountingResult to log is null, EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}
		String qualifyingMethodName = cr.getQualifyingMethodName();
		if(qualifyingMethodName==null || qualifyingMethodName.equals("")) {
			log.severe("Qualifying method name is null or empty, EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}
		sb.append("qualifyingMethodName: ");
		sb.append(qualifyingMethodName);
		sb.append(NEWLINE);
		sb.append("requestID: ");
		sb.append(cr.getRequestID());
		sb.append(", ownID: ");
		sb.append(cr.getOwnID());
		sb.append(", callerID: ");
		sb.append(cr.getCallerID());
		sb.append(NEWLINE);
		if (cr.getIndexOfRangeBlock() == -1) {
			sb.append("The whole method was measured (cr.getIndexOfRangeBlock() == -1 in CountingResultCollector.logResult)");
			sb.append(NEWLINE);
		} else {
			sb.append("Section number ");
			sb.append(cr.getIndexOfRangeBlock());
			sb.append(" was measured.");
			sb.append(NEWLINE);
		}
//		if(cr==null){
//			log.severe("The CountingResult to log is null - nothing to do, returning immediately.");
//			log.info("== END ========= Logging CountingResult ================");
//			return;
//		}

		long[] opcodeCounts 		= cr.getOpcodeCounts();
		if(opcodeCounts == null) {
			log.severe("Opcode counts is null... EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		SortedMap<String, Long> methodCallCounts 		= cr.getMethodCallCounts();
		if(methodCallCounts == null) {
			log.severe("Method counts hashmap is null... EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		long time = cr.getMethodInvocationBeginning();
		if(time<0) {
			log.severe("Wrong time: "+time);//TODO which kind of time is this?
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		if(resultWriters.size()>0){
			log.fine("Logging CountinResult using "+resultWriters.size()+
					" registered result writers");
			for(ICountingResultWriter rw : resultWriters){
				rw.writeResultToFile(cr, false, -1);//TODO make this better/parameterised
			}
		}

		long[] newArrayCounts 						= cr.getNewArrayCounts();
		int[] newArrayDims 							= cr.getNewArrayDim();
		String[] newArrayTypes 						= cr.getNewArrayTypes();

		// No checks here (but below!) for array results, because null is also
		// returned when array parameter recording is disabled.

		// make sure DisplayOpcodes does not interfere with the output...?
		long totalCountOfAllOpcodes = 0; //you need longs for that...
		long totalCountOfAllMethods = 0; //you need longs for that...

		String 	tabs;					// tabulators (for logging)
		String 	currentOpcodeString;	// opcode as string
		long 	currentOpcodeCount;		// opcode count
		long 	currentMethodCount = 0;	// method count


		int numberOfOpcodesWithNonzeroFrequencies=0;
		for(int i = 0; i < CountingResult.MAX_OPCODE; i++) {
			currentOpcodeString = FullOpcodeMapper.getMnemonicOfOpcode(i);
			currentOpcodeCount 	= opcodeCounts[i];
			tabs 				= getTabs(currentOpcodeString + ":", 2);
//			dataset.addValue(currentOpcodeCount, qualifyingMethodName+": instructions", currentOpcodeString);
			if(currentOpcodeCount!=0 || printZeros){
				sb.append(currentOpcodeString);
				sb.append(":");
				sb.append(tabs);
				sb.append(currentOpcodeCount);
				sb.append(NEWLINE);
			}
			if((totalCountOfAllOpcodes+currentOpcodeCount)<totalCountOfAllOpcodes){
				log.severe("OVERFLOW while adding opcode counts... use BigInteger instead");
			}else{
				totalCountOfAllOpcodes += currentOpcodeCount;
				if(currentOpcodeCount>0){
					numberOfOpcodesWithNonzeroFrequencies++;
				}
			}
		}

		Iterator<String> methodSigs = methodCallCounts.keySet().iterator();
		SortedSet<String> classesContainingMethodSigs = new TreeSet<String>();
		String currentSig, className;
		while(methodSigs.hasNext()) {
			currentSig = methodSigs.next();
			className = currentSig.split("\\.")[0];
			classesContainingMethodSigs.add(className);
			currentMethodCount = methodCallCounts.get(currentSig);
			tabs = getTabs(currentSig + ":", 9);
//			dataset.addValue(currentMethodCount, qualifyingMethodName+": methods", currentMethodSignature);
			sb.append(currentSig);
			sb.append(":");
			sb.append(tabs);
			sb.append(currentMethodCount);
//			sb.append(" (class: "+className+")");
			sb.append(NEWLINE);
			if(totalCountOfAllMethods + currentMethodCount<totalCountOfAllMethods){
				log.severe("OVERFLOW while adding method counts");
			}else{
				totalCountOfAllMethods += currentMethodCount;
			}
//			instrnames_texSB.append(currentMethodSignature+" & ");
//			instrcounts_texSB.append(currentMethodCount+" & ");
		}
//		instrnames_texSB.append("total \\\\");
//		instrcounts_texSB.append(totalCountOfAllMethods+" \\\\");

		// because null is a valid value for the array*Something* arrays,
		// we need to be carefull here.
		if(newArrayCounts != null
				&& newArrayDims != null
				&& newArrayTypes != null) {
			for(int i = 0; i < newArrayCounts.length; i++) {
				sb.append("new array of type '");
				sb.append(newArrayTypes[i]);
				sb.append("'");
				sb.append((newArrayDims[i] > 0 ? ", dim " + newArrayDims[i] : ""));
				sb.append(": ");
				sb.append(newArrayCounts[i]);
				sb.append(NEWLINE);
			}
		}
		sb.append("====================================================");
		sb.append(NEWLINE);
		sb.append(totalCountOfAllOpcodes);
		sb.append(" instruc. executions of ");
		sb.append(numberOfOpcodesWithNonzeroFrequencies);
		sb.append(" different opcodes were counted.");
		sb.append(NEWLINE);
		sb.append(totalCountOfAllMethods);
		sb.append(" methods invocations of ");
		sb.append(methodCallCounts.size());
		sb.append(" different signatures were counted, from "+classesContainingMethodSigs.size()+" classes.");
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		int i=1;
		int approxNrOfJavaPlatformClasses = 0;
		StringBuffer sb2 = new StringBuffer();
		sb.append("API / platform classes: \n");
		for(String classs : classesContainingMethodSigs){
			if(classs.startsWith("java/") || classs.startsWith("javax/") || classs.startsWith("sun/")){
				approxNrOfJavaPlatformClasses++;
				sb.append("class "+i+": "+classs+"\n");
			}else{
				sb2.append("class "+i+": "+classs+"\n");
			}
			i++;
		}
		sb.append((classesContainingMethodSigs.size()-approxNrOfJavaPlatformClasses)+
				" are 'business' classes outside of the Java platform:\n");
		sb.append(sb2.toString());
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		sb.append("== END ========= Logging CountingResult ================");
		sb.append(NEWLINE);
		String ret = sb.toString();
		if(loggingLevel.equals(Level.CONFIG)){
			log.config(sb.toString());
		}else if(loggingLevel.equals(Level.FINE)){
			log.fine(sb.toString());
		}else if(loggingLevel.equals(Level.FINER)){
			log.finer(sb.toString());
		}else if(loggingLevel.equals(Level.FINEST)){
			log.finest(sb.toString());
		}else if(loggingLevel.equals(Level.INFO)){
			log.info(sb.toString());
		}else if(loggingLevel.equals(Level.SEVERE)){
			log.severe(sb.toString());
		}else if(loggingLevel.equals(Level.WARNING)){
			log.warning(sb.toString());
		}else if(loggingLevel.equals(Level.ALL)){
			log.info(sb.toString());
		}else if(loggingLevel.equals(Level.OFF)){
			//log.info(sb.toString());
		}else{
			log.info(sb.toString());
		} 
		return ret;
	}

	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 */
	public void monitorShouldStop() {
		this.setMonitorShouldStop(true);
	}

	/**
	 * Modifies the method name by cutting of the encoded meta information about
	 * the basic blocks or code ranges.
	 * @param qualifyingMethodName Qualifying method name as passed to the 
	 * protocolCount methods.
	 * @return the modified method name.
	 */
	private synchronized String parseBBQualifyingMethodName(boolean isRangeBlock, String qualifyingMethodName) {
		// we have basic blocks; cut of the prefix
		if(!isRangeBlock) {
			qualifyingMethodName = 
				qualifyingMethodName.substring(BASIC_BLOCK_MAGIC_PREFIX.length());
		} else {
			qualifyingMethodName = 
				qualifyingMethodName.substring(RANGE_BLOCK_MAGIC_PREFIX.length());
		}
		return qualifyingMethodName;
	}

	private synchronized void protocol_forceInliningAlwaysAndCountReportingsPerSig(
			ProtocolCountStructure result, 
			long reportingStart) {
		forceInline(reportingStart, result, true);
		Integer count;
		if(result.qualifyingMethodName==null){
			log.severe("Qualifying method name is null");
		}else{
			count = this.forcedInlining_OccurenceCountsReportingMethods.get(result.qualifyingMethodName);
			if(count==null){
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, 1);
			}else{
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, count+1);
			}
		}
	}

	private synchronized void protocol_forceInliningWhenReachingThresholdPerReportingMethodOrTotalThreshold(
			ProtocolCountStructure result, long reportingStart) {
		//TODO ignores newArray* as well as *IDs
		Integer count=null;
		if(result.qualifyingMethodName==null){
			log.severe("Qualifying method name is null");
		}else{
			count = this.forcedInlining_OccurenceCountsReportingMethods.get(result.qualifyingMethodName);
			if(count==null){
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, 1);
			}else{
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, count+1);
			}
		}
		if(count!=null && count>=forcedInlining_thresholdPerReportingMethod){
			if(verbose) log.info("Inlining counting result because "+result.qualifyingMethodName+
					" already has "+forcedInlining_thresholdPerReportingMethod+" counting results");
			forceInline(reportingStart, result, false);
		}else if(this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining>=this.forcedInlining_thresholdTotalMaximum){
			if (verbose) log.info("Inlining counting result because total number of stored counting results " +
					" already reached "+forcedInlining_thresholdTotalMaximum);
			forceInline(reportingStart, result, false);
		}else{
			this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining++;
			addToCountingResults(result,
					reportingStart);
		}
	}

	private synchronized void protocol_forceInliningWhenReachingTotalThreshold(
			ProtocolCountStructure result,
			long reportingStart) {
		Integer count=null;
		if(result.qualifyingMethodName==null){
			log.severe("Qualifying method name is null");
		}else{
			count = this.forcedInlining_OccurenceCountsReportingMethods.get(result.qualifyingMethodName);
			if(count==null){
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, 1);
			}else{
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, count+1);
			}
		}
		if(this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining>=this.forcedInlining_thresholdTotalMaximum){
			forceInline(reportingStart, result, false);
		}else{
			this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining++;
			addToCountingResults(result, reportingStart);
		}
	}
	
	private synchronized void protocol_normalInliningWished_forceInliningWhenReachingThresholdPerMethodOrTotalThreshold(
			ProtocolCountStructure result, 
			long reportingStart) {
		Integer count=null;
		if(result.qualifyingMethodName==null){
			log.severe("Qualifying method name is null");
		}else{
			count = this.forcedInlining_OccurenceCountsReportingMethods.get(result.qualifyingMethodName);
			if(count==null){
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, 1);
			}else{
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, count+1);
			}
		}
		if(count!=null && count>=forcedInlining_thresholdPerReportingMethod){
			if(verbose) log.info("Inlining counting result because "+result.qualifyingMethodName+
					" already has "+forcedInlining_thresholdPerReportingMethod+" counting results");
			forceInline(reportingStart, result, false);
		}else if(this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining>=this.forcedInlining_thresholdTotalMaximum){
			if(verbose) log.info("Inlining counting result because total number of stored counting results " +
					" already reached "+forcedInlining_thresholdTotalMaximum);
			forceInline(reportingStart, result, false);
		}else{
			this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining++;
			addToWishedInliningResult(result);
		}
	}

	private synchronized void protocol_normalInliningWished_forceInliningWhenTotalThresholdReached(
			ProtocolCountStructure result, long reportingStart) {
		Integer count=null;
		if(result.qualifyingMethodName==null){
			log.severe("Qualifying method name is null");
		}else{
			count = this.forcedInlining_OccurenceCountsReportingMethods.get(result.qualifyingMethodName);
			if(count==null){
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, 1);
			}else{
				this.forcedInlining_OccurenceCountsReportingMethods.put(result.qualifyingMethodName, count+1);
			}
		}
		if(this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining>=this.forcedInlining_thresholdTotalMaximum){
			if(verbose) log.info("Inlining counting result because total number of stored counting results " +
					" already reached "+forcedInlining_thresholdTotalMaximum);
			forceInline(reportingStart, result, false);
		}else{
			this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining++;
			addToWishedInliningResult(result);
		}
	}

	/**
	 * Changing the signature of the protocolCount* methods
	 * ====================================================
	 *
	 *  Changing the signature of the protocol methods requires a number of
	 * additional changes in ByCounter.
	 * 1) Make sure to extend _all_ of the protocol* methods in the same way if
	 * appropriate.
	 * 2) Adapt the signature in bytecode notation in the SIGNATURE_* constants
	 * (i.e. as shown in the asmifier plugin) to match the new signature.
	 * 3) In insertResultCollectorCall, make sure that the stack is filled
	 * correctly before the visitMethodInsn calls to match the changed signature.
	 * 4) The directResultWriting method that is inserted into instrumented
	 * classes if chosen needs to be adapted as it mostly shares the parameter
	 * list of the protocol method. For this, adapt the methods template in
	 * MethodCountClassAdapter and follow the instructions in the javadoc of the
	 * template that instruct you to change the asm code that is generated from
	 * the template.
	 *
	 * An instrumented class calls this method to report the instruction and method call counts.
	 * This version gathers integer counts
	 * (the simpler "IINC" instrumentation has some performance advantages),
	 * but the data is saved as "longs" in CountingResultCollector anyway.
	 * @param result Information reported by an instrumented method.
	 */
	public synchronized void protocolCountInt(ProtocolCountStructure result) {
		result.convertIntToLong();//TODO how expensive is this?
		this.protocolCountLong(result);
	}

	/**
	 * @param result The result as reported by an instrumented method.
	 */
	public synchronized void protocolCountInt_inline(ProtocolCountStructure result) {
//		if(newArrayCounts!=null && newArrayTypeOrDim!=null && newArrayDescr!=null){
//			assert(newArrayCounts.length== newArrayTypeOrDim.length 
//					&& newArrayTypeOrDim.length== newArrayDescr.length);
//		}
		if(result.newArrayCounts!=null && result.newArrayCounts.length>0){
			log.severe("Ignoring array counts reported for "+result.qualifyingMethodName+" at "+result.executionStart);
		}else if(result.newArrayTypeOrDim!=null && result.newArrayTypeOrDim.length>0){
			log.severe("Ignoring array counts reported for "+result.qualifyingMethodName+" at "+result.executionStart);
		}else if(result.newArrayDescr!=null && result.newArrayDescr.length>0){
			log.severe("Ignoring array counts reported for "+result.qualifyingMethodName+" at "+result.executionStart);
		}
		result.convertIntToLong();
		this.protocolCountLong_inline(result);
	}

	/**
	 * An instrumented class calls this method to report the instruction and method call counts.
	 * This version gathers "long"-typed counts, instead of faster but limited "int"-typed counts.
	 * @param result The result reported by an instrumented method.
	 */
	@SuppressWarnings("boxing")
	public synchronized void protocolCountLong(ProtocolCountStructure result) {
		long reportingStart = System.nanoTime();//TODO make this configurable and clear, move to an interface/class that is accessed
		if(this.mode==CountingResultCollectorModeEnum.DiscardAllIncomingCountingResults){
			log.fine("Discarding counting result of method "+result.qualifyingMethodName+", which started execution " +
					"at "+result.executionStart);
		}else if(this.mode==CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts){
			//TODO ignores newArray* as well as *IDs
			forceInline(reportingStart, result, false);
		}else if(this.mode==CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts_ButCountReportsPerSignature){
			//TODO ignores newArray* as well as *IDs
			protocol_forceInliningAlwaysAndCountReportingsPerSig(result, reportingStart);
		}else if(this.mode==CountingResultCollectorModeEnum.UseThresholdPerReportingMethod_UntilTotalThresholdReachedThenForceInline){
			protocol_forceInliningWhenReachingThresholdPerReportingMethodOrTotalThreshold(
					result, reportingStart);
		}else if(this.mode==CountingResultCollectorModeEnum.UseTotalThreshold_RegardlessOfIndividualMethodCountsThenForceInline){
			protocol_forceInliningWhenReachingTotalThreshold(result, reportingStart);
		}else{
			addToCountingResults(result, reportingStart);
		}
	}

//	private void resetInlinedCountingResult() {
//		inlined_countingResult.resetMethodInstructionCountsOnly();
//	}


	/**
	 * TODO test me...
	 * how far is "synchronized" problematic in multi-threading?
	 * @param result The result reported by the instrumented method
	 */
	public synchronized void protocolCountLong_inline(
			ProtocolCountStructure result) {
//		log.severe("Protocolling_long_inline: "+qualifyingMethodName);
		long reportingStart = System.nanoTime();//TODO??? can be ignored
		if(this.mode==CountingResultCollectorModeEnum.DiscardAllIncomingCountingResults){
			if(verbose) log.fine("Discarding counting result of method "+result.qualifyingMethodName+", which started execution " +
					"at "+result.executionStart);
		}else if(this.mode==CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts){
			forceInline(reportingStart, result, false);
		}else if(this.mode==CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts_ButCountReportsPerSignature){
			protocol_forceInliningAlwaysAndCountReportingsPerSig(result, reportingStart);
		}else if(this.mode==CountingResultCollectorModeEnum.UseThresholdPerReportingMethod_UntilTotalThresholdReachedThenForceInline){
			protocol_normalInliningWished_forceInliningWhenReachingThresholdPerMethodOrTotalThreshold(
					result, reportingStart);
		}else if(this.mode==CountingResultCollectorModeEnum.UseTotalThreshold_RegardlessOfIndividualMethodCountsThenForceInline){
			protocol_normalInliningWished_forceInliningWhenTotalThresholdReached(
					result, reportingStart);
		}else{
			addToWishedInliningResult(result);
		}
	}

	public synchronized void registerWriter(ICountingResultWriter resultWriter){
		if(resultWriter==null){
			log.severe("Passed resultWriter is null, adding nonetheless");
		}
		this.resultWriters.add(resultWriter);
	}

	/**
	 *
	 * Get all results the {@link CountingResultCollector} holds.
	 * This does not clear the {@link CountingResultCollector} list.
	 * You have to explicitly
	 * call <code>clearResults()</code> if that is your intention.
	 * @param recursive When true, the counts for a result are the sum of
	 * the counts of the respective method and recursively the counts of all
	 * methods called by the method, i.e. of the entire calling tree.
	 * When false, only the counts for operation done in the method itself
	 * - not those by called methods - are returned.
	 * Does NOT consider the inlined result!
	 * @return A <code>Result</code> list.
	 */
	private synchronized List<CountingResult> retrieveAllCountingResults(boolean recursive) {//TODO the returned result should be a sorted set
		
		if(useDeferredBBcalculations) {
			processDeferredResults();
		}
		
		//TODO this shouldn't be a list, but a sorted set...
		int initialSize = this.countingResults.size();
		List<CountingResult> ret = new ArrayList<CountingResult>(initialSize);
		if(!recursive) {
//			with small initial sizes, the ArrayList constructor is undependable: 
//			try{
//				Collections.copy(ret, this.countingResults);//this fails for me 
//			}catch (IndexOutOfBoundsException e){
//				log.severe("Failed to copy from countingResults field (length "+this.countingResults.size()+
//						") into a new List of length "+ret.size()+", despite passing an initial size "+
//						initialSize);
//				e.printStackTrace();
//			}
			Iterator<CountingResult> iter = this.countingResults.iterator();
			while(iter.hasNext()){
				ret.add(iter.next());
			}
		}else{
			// calculate the sums for all results
			long callerStartTime;
			for(int i = 0; i < this.countingResults.size(); i++) {
				callerStartTime = this.countingResults.get(i).getMethodInvocationBeginning();
				CountingResult cr = this.retrieveCountingResultByStartTime_evaluateCallingTree(callerStartTime, true);
				if(isInternalClass(cr.getQualifyingMethodName())) {
					ret.add(cr);
				}
			}
		}
		
		//1.
		ret.add(inlined_countingResult);

		//2. there is no recursion for forcedInlined
		for(CountingResult result:forcedInlining_CountingResultsSortedSet){
			ret.add(result);
		}
		//3. the last one is dangling TODO consider operating on the ALREADY ADDED one
		forcedInlining_CountingResult.overwriteOpcodeCounts(forcedInlining_InlinedOpcodeCounts); //TODO check
		forcedInlining_CountingResult.overwriteMethodCallCounts(forcedInlining_InlinedMethodCounts); //TODO check
		ret.add(forcedInlining_CountingResult);

		return ret;
	}

	/**
	 * Uses {@link #getInternalClassesDefinition()} to decide whether the given 
	 * name is considered an internal class.
	 * @param qualifyingMethodName Name of the class to check.
	 * @return True when the class is internal.
	 */
	public boolean isInternalClass(String qualifyingMethodName) {
		if(this.internalClassesDefinition == null) {
			return true;
		}
		// find public parent class in case of internal class
		int i = qualifyingMethodName.indexOf('$');
		String className = qualifyingMethodName;
		if(i >= 0) {
			className = qualifyingMethodName.substring(0, i);
		}
		
		for(String s : this.internalClassesDefinition) {
			if(s.charAt(s.length() - 1) == INTERNAL_CLASS_DEFINITION_WILDCARD_CHAR) {
				final String prefix = s.substring(0, s.length() - 1);
				// prefix matching
				if(qualifyingMethodName.startsWith(prefix)) {
					return true;
				}
			} else {
				// exact matching 
				if(className.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * If {@link #useDeferredBBcalculations} is enabled, this method processes 
	 * the uncalculated results.
	 */
	private void processDeferredResults() {
		for(Entry<String, Integer> e : this.uncalculatedBBCounts_Index.entrySet()) {
			int index = e.getValue();
			String canonicalMethodName = e.getKey();
			
			// then calculate the opcode counts
			CalculatedCounts ccounts = blockCalculation.calculateCountsFromBBCounts(
					canonicalMethodName,
					this.uncalculatedBBCounts[index],
					forcedInlining_InlinedOpcodeCounts, 
					forcedInlining_InlinedMethodCounts);

			// add up the counts
			// the forcedInlining_ values are included in the new counts
			// so apply the results
			forcedInlining_InlinedOpcodeCounts = ccounts.opcodeCounts;
			forcedInlining_InlinedMethodCounts = ccounts.methodCounts;
		}
		this.uncalculatedBBCounts = null;
	}

	/**
	 * Get all results the {@link CountingResultCollector} holds.
	 * This does not clear the {@link CountingResultCollector} list. You have to explicitly
	 * call <code>clearResults()</code> if that is your intention.
	 * This method is the same as <code>getResults(false)</code>.
	 * @return A <code>Result</code> list.
	 */
	public synchronized List<CountingResult> retrieveAllCountingResults_nonRecursively() {
		return retrieveAllCountingResults(false);
	}

	/**
	 * Get all results the {@link CountingResultCollector} holds, incl. methods called from a methods.
	 * This does not clear the {@link CountingResultCollector} list. You have to explicitly
	 * call <code>clearResults()</code> if that is your intention.
	 * This method is the same as <code>getResults(true)</code>.
	 * @return A <code>Result</code> list.
	 */
	public synchronized List<CountingResult> retrieveAllCountingResults_recursively() {
		return retrieveAllCountingResults(true);
	}

	/**
	 * Get all results the {@link CountingResultCollector} holds.
	 * This does not clear the {@link CountingResultCollector} list. You have to explicitly
	 * call <code>clearResults()</code> if that is your intention.
	 * @param recursive When true, the counts for a result are the sum of
	 * the counts of the respective method and recursively the counts of all
	 * methods called by the method, i.e. of the entire calling tree.
	 * When false, only the counts for operation done in the method itself
	 * - not those by called methods - are returned.
	 * @return A Result array.
	 */
	public synchronized CountingResult[] retrieveAllCountingResultsAsArray(boolean recursive) {
		return retrieveAllCountingResults(recursive).toArray(new CountingResult[0/*this.countingResults.size()*/]);
	}

	/**
	 * Temporary fix. TODO: stop the original method from returning no inlining 
	 * results.
	 * @param recursive
	 * @return CountingResults without the artificial results from inlining.
	 */
	public synchronized CountingResult[] retrieveAllCountingResultsAsArray_noInlining(boolean recursive) {
		
		List<CountingResult> allCountingResults = retrieveAllCountingResults(recursive);
		List<CountingResult> countingResults = new ArrayList<CountingResult>();;
		for(CountingResult cr : allCountingResults) {
			if(!cr.getQualifyingMethodName().startsWith("____")) {
				countingResults.add(cr);
			}
		}
		return countingResults.toArray(new CountingResult[0]);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param time A time as returned by System.nanoTime().
	 * @return The specified {@link CountingResult}.
	 */
	public synchronized CountingResult retrieveCountingResultByMethodStartTime(long time){
		CountingArtefactInformation cai = this.countingInformationsByBeginning.get(time);
		if(cai==null){
			this.log.severe("No counting artefact information for starting time "+time);
			return null;
		}
		return this.countingResultsByArtefactInformation.get(cai);

	}

/**TODO
	 * Gets a {@link CountingResult} that is the accumulation of all
	 *
	 * @param callerStartTime
	 * @param suppressDebugMessages
	 * @return The calculated {@link CountingResult}.
	 */
	@SuppressWarnings("deprecation")
	public synchronized CountingResult retrieveCountingResultByStartTime_evaluateCallingTree(
			Long callerStartTime,
			boolean suppressDebugMessages){
		this.log.info("Evaluating calling tree for method start time "+callerStartTime);
		CountingResult candidateCountingResult;		// The currently considered counting result
		CountingArtefactInformation canditateCAI;	// corresponding CAI
		Long candidateStartTime;					// the start time of the considered result
		Long candidateReportingTime;				// the reporting time of the considered result

		Long callerReportingTime
			= this.countingInformationsByBeginning
				.get(callerStartTime)
					.getResultsReceivedByCollectorTime();
		this.log.fine("Corresponding caller reporting time: "+callerReportingTime);

		Set<Long> allKeys = this.countingInformationsByBeginning.keySet();
		// create a list of results sorted by start time
		ArrayList<Long> keysCopy = new ArrayList<Long>(allKeys);
		Collections.sort(keysCopy);
		// skip all result of methods executed before callerStartTime
		Iterator<Long> iter = keysCopy.iterator();
		candidateStartTime = iter.next();
		CountingResult totalCountingResult = this.retrieveCountingResultByMethodStartTime(callerStartTime);
		this.log.fine("Counting result before Type2 addition: "+totalCountingResult);

		while(candidateStartTime<callerStartTime){//ECHT kleiner!
			if(!suppressDebugMessages) this.log.fine("Ignoring time "+candidateStartTime+" because <"+callerStartTime);
			candidateStartTime=iter.next();
		}

		boolean firstIt = true;
		// now add the results of the methods executed before the current
		// result was reported.
		do {
			if(!firstIt) {
				candidateStartTime = iter.next();
			}
			firstIt = false;
			canditateCAI = this.countingInformationsByBeginning.get(candidateStartTime);
			candidateReportingTime = canditateCAI.getResultsReceivedByCollectorTime();
			if(!suppressDebugMessages) this.log.fine("Considering for addition: "+canditateCAI+"");
			if(!suppressDebugMessages){
				this.log.fine("Just for the record: trying to add " +
					"["+candidateStartTime+","+candidateReportingTime+"] to " +
					"["+callerStartTime+","+callerReportingTime+"].");
			}
			// candidate results were reported before the caller was
			// assume that the caller has called the candidate and add it
			if(candidateReportingTime.longValue()<callerReportingTime.longValue()){
				if(!suppressDebugMessages){
					this.log.fine("Adding callee counts of time "+candidateStartTime+
						" because its start >"+callerStartTime+
						" and because its reporting time " +
						"("+canditateCAI.getResultsReceivedByCollectorTime()+")< " +
						"caller reporting time ("+callerReportingTime+").");
				}
				candidateCountingResult = canditateCAI.getCountingResult();
				if(!suppressDebugMessages) this.log.fine("Added counting result: "+candidateCountingResult);
				totalCountingResult.add(candidateCountingResult);
				if(!suppressDebugMessages) this.log.fine("Intermediate total counting result: "+totalCountingResult);
			}else if(candidateReportingTime.longValue()>callerReportingTime.longValue()){
				if(!suppressDebugMessages) this.log.fine("Skipping callee counts of time "+candidateStartTime+
						" because, while its start time >"+callerStartTime+
						", its reporting time " +
						"("+canditateCAI.getResultsReceivedByCollectorTime()+")> " +
						"caller reporting time ("+callerReportingTime+").");
			}else if(candidateReportingTime.longValue()==callerReportingTime.longValue()){
				if(candidateStartTime.longValue()==callerStartTime.longValue()){
					if(!suppressDebugMessages) this.log.fine("Potential callee is the caller herself -> skipping");
				}else{
					if(!suppressDebugMessages) this.log.fine("A real callee that ends at the same instant " +
							"that the caller --> SKIPPING");
				}
			}else{
				this.log.severe("This should not happen :-)");//TODO document better...
			}
			//do something with the total counting result
//			candidateStartTime = iter.next();
		} while (candidateStartTime<callerReportingTime && iter.hasNext());
		if(!suppressDebugMessages) this.log.fine("Finished the active part");

		if(!suppressDebugMessages) {
			while(iter.hasNext()) {
				this.log.fine("Skipping callers with time "+iter.next());
			}
		}
		return totalCountingResult;
	}
	
	/**
	 * Gets the {@link CountingResult}s that exist for the given method name.
	 * @param name The name of a method measured by ByCounter.
	 * @return A {@link List} of {@link CountingResult}s for the given name.
	 */
	public synchronized List<CountingResult> retrieveCountingResultsByMethodName(String name){
		log.warning("retrieveCountingResultsByMethodName disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		List<CountingResult> counts = new ArrayList<CountingResult>();
		Iterator<CountingArtefactInformation> iter = this.countingInformationsByMethodname.get(name).iterator();
		CountingArtefactInformation cai = null;
		CountingResult cr = null;
		for(;iter.hasNext();){
			cai = iter.next();
			cr = this.countingResultsByArtefactInformation.get(cai);
			if(cr!=null){
				counts.add(cr);
			}
		}
		return counts;
//		return this.countingResults.get(artefact);
	}
	
	//	/**
//	 * Assumption: when forced inlining is not "on", a ready-to-use CountingResult is maintained nonetheless
//	 * TODO this logic can be simiplified: remove redundancies
//	 * @param doInliningIgnoringMethodWished
//	 */
//	public synchronized final void setForceInliningIgnoringMethodWishes(boolean doInliningIgnoringMethodWished) {
//		if      (this.forcedInlining_IgnoreMethodWishes==true && doInliningIgnoringMethodWished==true){
//			this.saveCurrentForcedInliningResultAndRecreateNewOne();
//		}else if(this.forcedInlining_IgnoreMethodWishes==true && doInliningIgnoringMethodWished==false){
//			this.saveCurrentForcedInliningResultAndRecreateNewOne();
//			this.forcedInlining_IgnoreMethodWishes = doInliningIgnoringMethodWished;
//		}else if(this.forcedInlining_IgnoreMethodWishes==false && doInliningIgnoringMethodWished==true){
//			this.forcedInlining_IgnoreMethodWishes = doInliningIgnoringMethodWished;
//		}else if(this.forcedInlining_IgnoreMethodWishes==false && doInliningIgnoringMethodWished==false){
//			this.forcedInlining_IgnoreMethodWishes = doInliningIgnoringMethodWished;
//		}
//	}
	/**
	 * TODO use the forcedInlining_* boolean flags...
	 * Used to be called when changing modes
	 */
	private synchronized final void saveCurrentForcedInliningResultAndRecreateNewOne() {
		this.forcedInlining_CountingResult.setMethodReportingTime(System.nanoTime());//TODO parameterise
		this.forcedInlining_CountingResult.overwriteOpcodeCounts(forcedInlining_InlinedOpcodeCounts);
		this.forcedInlining_CountingResult.overwriteMethodCallCounts(forcedInlining_InlinedMethodCounts);
		//TODO set its opcode counts to forcedInlining_InlinedOpcodeCounts
		//TODO set its method counts to forcedInlining_InlinedMethodMapping
		
		this.forcedInlining_CountingResultsSortedSet.add(this.forcedInlining_CountingResult);
		this.forcedInlining_CountingResult = createNewForcedInlinedCountingResult();
//		this.forcedInlining_InlinedMethodMappings.add(this.forcedInlining_InlinedMethodMapping);//TODO I think this structure should be added to the forcedInlining_CountingResult
		this.forcedInlining_InlinedMethodCounts = new TreeMap<String, Long>();
		this.forcedInlining_InlinedOpcodeCounts = new long[256];
	}
	
	/**
	 * Add up the basic block execution counts for later opcode calculations.
	 * @param qualifyingMethodName Method from which the results come.
	 * @param bbCounts Basic block execution counts.
	 */
	private void saveUncalculatedBBCounts(String qualifyingMethodName,
			long[] bbCounts) {
		Integer index = uncalculatedBBCounts_Index.get(qualifyingMethodName);
		// initialise the array if necessary
		if(index == null) {
			index = uncalculatedBBCounts_Index.size();
			uncalculatedBBCounts_Index.put(qualifyingMethodName, index);
			if(this.uncalculatedBBCounts == null) {
				this.uncalculatedBBCounts = new long[blockContext.bbSerialisation.getBasicBlocksByMethod().size()][];
			}
			uncalculatedBBCounts[index] = new long[bbCounts.length];
		}

		long[] basicBlockCounts = uncalculatedBBCounts[index];
		// add up the execution counts
		for(int i = 0; i < bbCounts.length; i++) {
			basicBlockCounts[i] += bbCounts[i];
		}
	}
	
	public void setForceInliningIgnoringMethodWishes(boolean b) {
		if(b){
			setMode(CountingResultCollectorModeEnum.ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts_ButCountReportsPerSignature);
		}else{
			setMode(CountingResultCollectorModeEnum.UseReportingMethodChoiceByInstrumentedMethods);
		}
	}

	public void setMode(CountingResultCollectorModeEnum mode) {
		//TODO add checks and reactions (esp. w.r.t. inlining), cf. the setForceInliningIgnoringMethodWishes(...) method
		this.mode = mode;
	}

	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 * @param monitorShouldStop
	 */
	public void setMonitorShouldStop(boolean monitorShouldStop) {
		this.monitorShouldStop = monitorShouldStop;
	}
	
	public CountingResult getForcedInlining_CountingResult() {
		return this.forcedInlining_CountingResult;
	}
	
	public List<long[]> getForcedInlining_CollectedMethodCallCounts() {
		return this.forcedInlining_CollectedMethodCallCounts;
	}
	
	public List<String[]> getForcedInlining_CollectedMethodCallSignatures() {
		return this.forcedInlining_CollectedMethodCallSignatures;
	}
	
	public SortedSet<CountingResult> getForcedInlining_CountingResultsSortedSet() {
		return this.forcedInlining_CountingResultsSortedSet;
	}

	public SortedMap<String, Long> getForcedInlining_InlinedMethodCounts() {
		return this.forcedInlining_InlinedMethodCounts;
	}

	public long[] getForcedInlining_InlinedOpcodeCounts() {
		return this.forcedInlining_InlinedOpcodeCounts;
	}

	public Integer getForcedInlining_thresholdPerReportingMethod() {
		return this.forcedInlining_thresholdPerReportingMethod;
	}

	public Integer getForcedInlining_thresholdTotalMaximum() {
		return this.forcedInlining_thresholdTotalMaximum;
	}

	public Integer getForcedInlining_totalOfUninlinedMethodsDespiteForcedInlining() {
		return this.forcedInlining_totalOfUninlinedMethodsDespiteForcedInlining;
	}



	/**
	 * This is called by {@link BytecodeCounter} when an execute method is 
	 * executed to provide the details of the execution to 
	 * {@link CountingResultCollector}
	 * @param lastMethodExecutionDetails Method execution details.
	 */
	public void setLastMethodExecutionDetails(MethodExecutionRecord lastMethodExecutionDetails) {
		this.lastMethodExecutionDetails = lastMethodExecutionDetails;
	}

	/**
	 * @return the lastMethodExecutionDetails
	 */
	public MethodExecutionRecord getLastMethodExecutionDetails() {
		return lastMethodExecutionDetails;
	}

	/**
	 * @param internalClassesDefinition The definition of interal classes.
	 * When adding up results when retrieving results recursivly, this 
	 * definition allows for adding up only results for classes defined as 
	 * internal.
	 * <p>
	 * For each string, specifying a '*' at the end enabled prefix matching, 
	 * i.e. all classes with the prefix are matched. If a string specifies a 
	 * class name, non-public/internal classes are also considered internal.
	 * </p>
	 * <p>
	 * Examples:
	 * <code>
	 * <list>
	 * <li>de.uka* matches de.ukap.Test, de.uka.ipd, ...</li>
	 * <li>de.uka.Test matches de.uka.Test, de.uka.Test$XXX, de.uka.Test$XXX$YYY, ...</li>
	 * <li>de.uka.Test matches de.uka.Test, but not de.uka.Test.{ENUM Y}</li>
	 * </list>
	 * </code>
	 * </p>
	 */
	public void setInternalClassesDefinition(
			Set<String> internalClassesDefinition) {
		this.internalClassesDefinition = internalClassesDefinition;
	}

	/**
	 * @return The definition of internal classes.
	 * @see #setInternalClassesDefinition(Set)
	 */
	public Set<String> getInternalClassesDefinition() {
		return internalClassesDefinition;
	}

}
