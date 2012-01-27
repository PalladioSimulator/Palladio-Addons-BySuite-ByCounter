package de.uka.ipd.sdq.ByCounter.execution;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

/**
 * This class can be used in addition to {@link CountingResultCollector} in 
 * order to achieve result inlining for specific methods during result 
 * collection. Inlining means 
 * that instead of holding every individual result, results are added up into 
 * shared counters.
 * <p>
 * The goal of this strategy is to provide a collection strategy with a strong 
 * limit in used memory.
 * </p>
 * @author Martin Krogmann
 * @author Michael Kuperberg
 *
 */
public class CollectionStrategyWishedInlining extends
		AbstractCollectionStrategy {

	/**
	 * Unlike forced inlining, this field is for method-level specification of inlining (e.g. for invariant methods)
	 */
	private CountingResult inlined_countingResult;
	
	/**
	 * Describes how often a certain method has been inlined (by method's request, not by forced inlining)...
	 */
	private TreeMap<String,Integer> inlined_methodsMap;

	/** When true, at least one result has been inlined since the last 
	 * {@link #clearResults()}*/
	private boolean hasInliningResult;
	
	public CollectionStrategyWishedInlining(CountingResultCollector parent) {
		super(parent);
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
				new String[]{}
				);
		this.inlined_methodsMap = new TreeMap<String, Integer>();
	}
	
	@Override
	public void clearResults() {
		this.inlined_countingResult.resetMethodAndInstructionCounts();//TODO refactor for efficiency
		this.inlined_methodsMap = new TreeMap<String, Integer>();
		this.hasInliningResult = false;
	}

	/**
	 * Add to wished inlining result.
	 * @param result The result to add.
	 * @param reportingStart This is ignored for this strategy.
	 */
	@Override
	public boolean protocolCount(ProtocolCountStructure result, long reportingStart) {
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
		return true;
	}

	@Override
	public SortedSet<CountingResult> retrieveAllCountingResults() {
		SortedSet<CountingResult> result = new TreeSet<CountingResult>();
		if(this.hasInliningResult) {
			result.add(this.inlined_countingResult);
		}
		return result;
	}

}
