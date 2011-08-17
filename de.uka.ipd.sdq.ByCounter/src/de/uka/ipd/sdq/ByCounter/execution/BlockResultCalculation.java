package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.parsing.InstructionBlockDescriptor;
import de.uka.ipd.sdq.ByCounter.parsing.RangeBlockDescriptor;
import de.uka.ipd.sdq.ByCounter.parsing.RangeBlockDescriptor.BasicBlockOffset;

/**
 * Class class provides functions to calculate counting results for 
 * instrumentation results based on range block or basic block definitions.
 * @author Martin Krogmann
 *
 */
public class BlockResultCalculation {
	

	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private Logger log;
	private BlockDefinitionContext blockContext;
	/** The basic blocks defined for the current method */
	private InstructionBlockDescriptor[] currentBasicBlocks;
	/** The range blocks defined for the current method */
	private InstructionBlockDescriptor[] currentRangeBlocks;
	
	/**
	 * @param blockContext Definition context of the range/basic blocks.
	 */
	public BlockResultCalculation(BlockDefinitionContext blockContext) {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.blockContext = blockContext;
	}
	
	/**
	 * This method uses the execution frequencies of basic blocks and calculates 
	 * from these the actual opcode counts and method call counts.
	 * @param qualifyingMethodName The descriptor of the method. This is the 
	 * <code>canonicalClassName + "." + methodName + methodSignatureDescriptor
	 * </code>.
	 * @param basicBlockExecutionCounts The basic block execution frequencies.
	 * @param prevOpcodeCounts This array of opcode counters will be 
	 * incremented for opcodes in executed basic blocks if 
	 * calculateIndividualResults is false. 
	 * @param prevMethodCallCounts This map of method counters will be 
	 * incremented for method executions in executed basic blocks if 
	 * calculateIndividualResults is false.
	 */
	public synchronized CalculatedCounts calculateCountsFromBBCounts(
			final String qualifyingMethodName,
			final long[] basicBlockExecutionCounts,
			final long[] prevOpcodeCounts,
			final SortedMap<String, Long> prevMethodCallCounts) {
		// the string operations are expensive, so guard them by the log level.
		Level logLevel = log.getLevel();
		if(logLevel != null && logLevel.intValue() <= Level.FINE.intValue()) {
			log.fine("opcodeCounts for calculateCountsFromBBCounts: "+Arrays.toString(basicBlockExecutionCounts));
		}
		
		this.loadUpdatedBlockDefinitions(qualifyingMethodName, true, false);

		CalculatedCounts counts = null;
		counts = new CalculatedCounts();
		counts.opcodeCounts = prevOpcodeCounts;
		counts.methodCounts = prevMethodCallCounts;

		// go through all basic blocks
		for(InstructionBlockDescriptor blockDesc : this.currentBasicBlocks) {
			int bbIndex = blockDesc.getBlockIndex();
			if(bbIndex >= basicBlockExecutionCounts.length) {
				// this can happen if the basic block is never executed?
				// no actually this should not happen at all because the array
				// is of the full size no matter what is executed.
				continue;
			}

			long bbCount = basicBlockExecutionCounts[bbIndex];
			if(bbCount == 0) {
				continue;
			}
			// add opcode counts
			counts.addOpcodeCounts(blockDesc.getOpcodeCounts(), bbCount);
			// add method call counts
			counts.addMethodCallCounts(blockDesc.getMethodCallCounts(), bbCount);
		}
		return counts;
	}


	/**
	 * This method uses the execution frequencies of basic blocks and calculates 
	 * from these the actual opcode counts and method call counts for the 
	 * specified range blocks.
	 * @param qualifyingMethodName The descriptor of the method. This is the 
	 * <code>canonicalClassName + "." + methodName + methodSignatureDescriptor
	 * </code>.
	 * @param basicBlockExecutionCounts The basic block execution frequencies.
	 * @param prevOpcodeCounts This array of opcode counters will be 
	 * incremented for opcodes in executed basic blocks if 
	 * calculateIndividualResults is false. 
	 * @param prevMethodCallCounts This map of method counters will be 
	 * incremented for method executions in executed basic blocks if 
	 * calculateIndividualResults is false.
	 */
	public synchronized CalculatedCounts[] calculateCountsFromRBCounts(
			final String qualifyingMethodName,
			final long[] basicBlockExecutionCounts,
			final long[] prevOpcodeCounts,
			final SortedMap<String, Long> prevMethodCallCounts) {
		// the string operations are expensive, so guard them by the log level.
		Level logLevel = log.getLevel();
		if(logLevel != null && logLevel.intValue() <= Level.FINE.intValue()) {
			log.fine("opcodeCounts for calculateCountsFromRBCounts: "+Arrays.toString(basicBlockExecutionCounts));
		}

		this.loadUpdatedBlockDefinitions(qualifyingMethodName, true, true);
				
		ArrayList<CalculatedCounts> resultList = new ArrayList<CalculatedCounts>();
		for(InstructionBlockDescriptor currentRB  : this.currentRangeBlocks) {
			CalculatedCounts rbCounts = 
				getCountsForRangeBlock((RangeBlockDescriptor) currentRB, basicBlockExecutionCounts);
			if(rbCounts != null) {
				resultList.add(rbCounts);
			}
			
		}
		return resultList.toArray(new CalculatedCounts[resultList.size()]);
	}

	/**
	 * Load the block definitions from the {@link #blockContext}
	 * to set 
	 * {@link #currentBasicBlocks} and {@link #currentRangeBlocks}.
	 * @param qualifyingMethodName Current method.
	 * @param loadBasicBlocks False skips basic blocks.
	 * @param loadRangeBlocks False skips range blocks.
	 */
	private void loadUpdatedBlockDefinitions(
			final String qualifyingMethodName,
			final boolean loadBasicBlocks, 
			final boolean loadRangeBlocks) {
		if(loadBasicBlocks) {
			blockContext.updateBasicBlocks();
			currentBasicBlocks = blockContext.bbSerialisation.getBasicBlocksByMethod().get(qualifyingMethodName);
			if(currentBasicBlocks == null) {
				throw new IllegalStateException("Could not find the basic block definition for the method '" 
						+ qualifyingMethodName + "'");
			}
		}
		if(loadRangeBlocks) {
			blockContext.updateRangeBlocks();
			currentRangeBlocks = blockContext.rbSerialisation.getBasicBlocksByMethod().get(qualifyingMethodName);
			if(currentBasicBlocks == null) {
				throw new IllegalStateException("Could not find the range block definition for the method '" 
						+ qualifyingMethodName + "'");
			}
		}
	}

	/**
	 * Uses the results blockExecutionSequence to calculate counting results.
	 * @param result Result as reported by an instrumented method.
	 * @return {@link CalculatedCounts}.
	 */
	public CalculatedCounts[] calculateCountsFromBlockExecutionSequence(
			final ProtocolCountStructure result,
			final boolean calculateRangeBlocks) {
		
		this.loadUpdatedBlockDefinitions(result.qualifyingMethodName, true, true);
		

		// possibly more than one counting result per item in the sequence
		ArrayList<CalculatedCounts> resultCounts = new ArrayList<CalculatedCounts>();
		
		if(!calculateRangeBlocks) {
			// just add the complete basic block
			for(Integer blockIndex : result.blockExecutionSequence) {
				CalculatedCounts c = new CalculatedCounts();
				c.init();
				c.addMethodCallCounts(this.currentBasicBlocks[blockIndex].getMethodCallCounts(), 1);
				c.addOpcodeCounts(this.currentBasicBlocks[blockIndex].getOpcodeCounts(), 1);
				resultCounts.add(c);
			}
		} else {
			// range blocks
			Map<Integer, List<RangeBlockDescriptor>> rangeBlocksByBasicBlock = 
				getRangeBlocksByBasicBlock();
			
			List<RangeBlocksBBExecutionCounts> currentRBs = new LinkedList<RangeBlocksBBExecutionCounts>();
			
			for(Integer blockIndex : result.blockExecutionSequence) {
				List<RangeBlockDescriptor> rangeBlocksContainingBBblockIndex = 
					rangeBlocksByBasicBlock.get(blockIndex);	// can be null!
				List<RangeBlocksBBExecutionCounts> toRemoveFromCurrentRBs = new LinkedList<BlockResultCalculation.RangeBlocksBBExecutionCounts>();
				// Find active range blocks that do not contain the new basic block
				for(RangeBlocksBBExecutionCounts rb : currentRBs) {
					if(rangeBlocksContainingBBblockIndex == null
							|| !rangeBlocksContainingBBblockIndex.contains(rb.rb)) {
						// range block ends
						resultCounts.add(getCountsForRangeBlock(rb.rb, rb.basicBlockExecutionCounts));
						// mark for removal
						toRemoveFromCurrentRBs.add(rb);
					}
				}
				// actually remove the rbs
				currentRBs.removeAll(toRemoveFromCurrentRBs);
				
				// no new range block can start here if no range block contains the bb
				if(rangeBlocksContainingBBblockIndex == null) {
					continue;
				}
				
				// find inactive range blocks that contain the new basic block
				for(RangeBlockDescriptor rb : rangeBlocksContainingBBblockIndex) {
					if(!currentRBs.contains(rb)) {
						// a new range block started
						RangeBlocksBBExecutionCounts newRB = new RangeBlocksBBExecutionCounts();
						newRB.rb = rb;
						newRB.basicBlockExecutionCounts = new long[this.currentBasicBlocks.length];
						currentRBs.add(newRB);
					}
				}
				// add the executed basic block to the active range blocks
				for(RangeBlocksBBExecutionCounts rb : currentRBs) {
					rb.basicBlockExecutionCounts[blockIndex] += 1;
				}
			}
		}
		return resultCounts.toArray(new CalculatedCounts[resultCounts.size()]);
	}

	/**
	 * Gets the opcode counts and method call counts, i.e. CalculatedCounts 
	 * for the specified range block.
	 * <p>Assumes {@link #loadUpdatedBlockDefinitions(String, boolean, boolean)}
	 * was called appropriately.</p>
	 * @param rb Range block to get the counts for.
	 * @param basicBlockExecutionCounts The execution counts for the defined 
	 * basic blocks.
	 * @return An instance of {@link CalculatedCounts} if the range block was 
	 * actually executed. Null otherwise.
	 */
	private CalculatedCounts getCountsForRangeBlock(
			final RangeBlockDescriptor rb, 
			final long[] basicBlockExecutionCounts) {
		CalculatedCounts result = new CalculatedCounts();
		result.init();
		result.indexOfRangeBlock = rb.getBlockIndex();
		
		boolean rangeBlockCounted = false;

		// go through all basic blocks and add the counts if the basic block is part of the range block
		for(InstructionBlockDescriptor blockDesc : this.currentBasicBlocks) {
			int bbIndex = blockDesc.getBlockIndex();
			if(bbIndex >= basicBlockExecutionCounts.length) {
				// this can happen if the basic block is never executed?
				// no actually this should not happen at all because the array
				// is of the full size no matter what is executed.
				continue;
			}
			
			// count of the currently handled BB in the currently handled RB
			int currentRBBBCount = rb.getBasicBlockCounts()[bbIndex];
			if(currentRBBBCount == 0) {
				// basic block is not part of rb
				continue;
			}
			
			// number of times the basic block was executed * number of times the basic block is part of the range block
			long bbCount = basicBlockExecutionCounts[bbIndex] * currentRBBBCount;
			if(bbCount == 0) {
				continue;
			}
			// add opcode counts
			result.addOpcodeCounts(blockDesc.getOpcodeCounts(), bbCount);
			// add method call counts
			result.addMethodCallCounts(blockDesc.getMethodCallCounts(), bbCount);
			
			if(!rangeBlockCounted) {
				rangeBlockCounted = true;
			}
		}
		
		if(rangeBlockCounted) {
			// now subtract nrOfBBExecutions*offsets for first and last bb
			// opcode counts
			for(BasicBlockOffset bbOffset : rb.getBasicBlockOffsets()) {
				if(bbOffset == null || bbOffset.offset == null) {
					continue;
				}
				if(bbOffset.basicBlockIndex >= basicBlockExecutionCounts.length) {
					// basicBlockExecutionCounts contains no values for the basic block
					continue;
				}
				final long bbCount = basicBlockExecutionCounts[bbOffset.basicBlockIndex]*
					rb.getBasicBlockCounts()[bbOffset.basicBlockIndex];
				result.addOpcodeCounts(bbOffset.offset.getOpcodeCounts(), bbCount);
				result.addMethodCallCounts(bbOffset.offset.getMethodCallCounts(), bbCount);
			}
		} else {
			return null;
		}
		
		return result;
	}

	/**
	 * @return A map that holds the range blocks sorted by the basic blocks of 
	 * which they consist.
	 */
	private Map<Integer, List<RangeBlockDescriptor>> getRangeBlocksByBasicBlock() {
		HashMap<Integer, List<RangeBlockDescriptor>> resultMap = new HashMap<Integer, List<RangeBlockDescriptor>>();

		for(InstructionBlockDescriptor rangeBlockI : this.currentRangeBlocks) {
			RangeBlockDescriptor rangeBlock = (RangeBlockDescriptor)rangeBlockI;
			
			int basicBlockIndex = 0;
			for(Integer basicBlockCount : rangeBlock.getBasicBlockCounts()) {
				if(basicBlockCount == 0) {
					// basic block is 0 times in range block -> skip
					basicBlockIndex++;
					continue;
				}
				// create a new list for the basic block
				if(!resultMap.containsKey(basicBlockIndex)) {
					List<RangeBlockDescriptor> rbList = new LinkedList<RangeBlockDescriptor>();
					resultMap.put(basicBlockIndex, rbList);
				}
				// add the range block to the list
				resultMap.get(basicBlockIndex).add(rangeBlock);
				// next basic block
				basicBlockIndex++;
			}
		}
		
		
		return resultMap;
	}
	
	private class RangeBlocksBBExecutionCounts {
		public RangeBlockDescriptor rb;
		long[] basicBlockExecutionCounts;
	}
}
