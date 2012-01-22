package de.uka.ipd.sdq.ByCounter.parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

import de.uka.ipd.sdq.ByCounter.instrumentation.IInstructionAnalyser;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationState;
import de.uka.ipd.sdq.ByCounter.parsing.RangeBlockDescriptor.BasicBlockOffset;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * An implementation of {@link IInstructionAnalyser} that analyses instructions 
 * in order to find the basicBlocks that comprise range blocks.
 * Therefore this class depends on {@link BasicBlockAnalyser}. 
 * The results of this {@link IInstructionAnalyser} are serialised to the file
 * specified by {@link BasicBlockSerialisation#FILE_RANGE_BLOCK_SERIALISATION}.
 *
 */
public final class RangeBlockAnalyser implements IInstructionAnalyser {

	private Logger log;
	
	/**
	 * The ranges as specified by the user.
	 */
	private LineNumberRange[] ranges;
	
	/**
	 * This is a list of linenumbers used in the specification of line number 
	 * ranges but not yet found in the visited method.
	 * Used for error finding.
	 */
	private Set<Integer> lineNumbersNotYetFound;
	
	/**
	 * The list of all label blocks, i.e. instruction blocks that contain the 
	 * instructions for each label.
	 */
	private List<InstructionBlockLocation> labelBlocks;

	/**
	 * Map to quickly find an {@link InstructionBlockLocation} 
	 * in {@link #labelBlocks} by label.
	 */
	private Map<Label, InstructionBlockLocation> findLabelBlockByLabel;
	
	/**
	 * Map to quickly find an {@link InstructionBlockLocation} 
	 * in {@link #labelBlocks} by line number.
	 */
	private Map<Integer, List<InstructionBlockLocation>> findLabelBlockByLine;
		
	/**
	 * The method descriptor string for the analysed method as returned by 
	 * {@link MethodDescriptor#getCanonicalMethodName()}.
	 */
	private String methodDescriptorString;
	
	/**
	 * The instrumentation state that define how to analyse the method.
	 */
	private InstrumentationState instrumentationState;
	
	/**
	 * Labels for the basic blocks found by the 
	 * {@link BasicBlockAnalyser}. The index is used to refer to the basic block.
	 */
	private Label[] basicBlockLabels = null;
	

	/** The smallest value for linenumber in the analysed method.
	 */
	private int minLineNumber = -1;
	/**
	 * The largest value for linenumber in the analysed method.
	 */
	private int maxLineNumber = Integer.MAX_VALUE;
	
	public RangeBlockAnalyser(
			MethodDescriptor currentMethod, 
			InstrumentationState instrumentationState) {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.methodDescriptorString = currentMethod.getCanonicalMethodName();
		this.ranges = currentMethod.getCodeAreasToInstrument();
		this.instrumentationState = instrumentationState;
		
		this.labelBlocks = new ArrayList<InstructionBlockLocation>();
		this.findLabelBlockByLabel = new HashMap<Label, InstructionBlockLocation>();
		this.findLabelBlockByLine = new HashMap<Integer, List<InstructionBlockLocation>>();
		
		this.lineNumbersNotYetFound = new HashSet<Integer>();
		// construct the set of all specified line numbers for error checking
		for(LineNumberRange r : ranges) {
			lineNumbersNotYetFound.add(r.firstLine);
			lineNumbersNotYetFound.add(r.lastLine);
		}
	}
	

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.instrumentation.IInstructionAnalyser#analyseInstruction(org.objectweb.asm.tree.AbstractInsnNode)
	 */
	public void analyseInstruction(AbstractInsnNode insn) {
		if(insn instanceof LineNumberNode) {
			LineNumberNode lnNode = (LineNumberNode) insn;
			addLabelForInstructionBlock(lnNode.line, lnNode.start.getLabel());
		} else if(insn instanceof LabelNode) {
			addLabelForInstructionBlock(-1, ((LabelNode) insn).getLabel());
		}
	}
	
	/**
	 * Add the label to the list of labels. If the label has already been added,
	 * and the given line number is >= 0, the line number for the label is 
	 * updated.
	 * @param lineNumber line number for the label. Set to -1 if there is no 
	 * line number for the label.
	 * @param label Label to add.
	 */
	private void addLabelForInstructionBlock(int lineNumber, Label label) {
		InstructionBlockLocation loc = findLabelBlockByLabel.get(label);
		if(loc == null) {
			// this is the first time we see this label, so initialise the 
			// InstructionBlockLocation
			loc = new InstructionBlockLocation();
			loc.label = label;
			loc.labelBlock = new InstructionBlockDescriptor();
			this.findLabelBlockByLabel.put(label, loc);
			this.labelBlocks.add(loc);
		}
		if(lineNumber >= 0) {
			updateMinMaxLineNumber(lineNumber);
			loc.lineNumber = lineNumber;
			
			// add the block to the line number search structure
			List<InstructionBlockLocation> blocksForLine 
				= this.findLabelBlockByLine.get(lineNumber);
			if(blocksForLine == null) {
				blocksForLine = new LinkedList<InstructionBlockLocation>();
				this.findLabelBlockByLine.put(lineNumber, blocksForLine);
			}
			blocksForLine.add(loc);
			
			// the line lineNumber was found; remove it from the not found set
			this.lineNumbersNotYetFound.remove(lineNumber);
		}
	}


	/** Updates {@link #minLineNumber} and {@link #maxLineNumber} if linenumber
	 * qualifies for either one.
	 * @param lineNumber An analysed instruction.
	 */
	private void updateMinMaxLineNumber(int lineNumber) {
		if(lineNumber < minLineNumber) {
			minLineNumber = lineNumber;
		}
		if(lineNumber > maxLineNumber) {
			maxLineNumber = lineNumber;
		}
	}
	
	/**
	 * 
	 * @param tryCatchNode The {@link TryCatchBlockNode} to analyse.
	 */
	public void analyseTryCatchBlock(final TryCatchBlockNode tryCatchNode) {
		// nothing to do here
	}


	/**
	 * Walk through the instructions and use the {@link #instructionBlockLabels} to
	 * construct range blocks.
	 * @param instructions The list of instructions in the analysed method.
	 */
	private void constructRangeBlocks() {
		RangeBlockDescriptor[] rangeBlocks = new RangeBlockDescriptor[this.ranges.length];
		boolean[] rangeWasEntered = new boolean[rangeBlocks.length];
		for(int i = 0; i < rangeBlocks.length; i++) {
			rangeWasEntered[i] = false;
		}
		
		// go through all labels by line number and find the basic blocks that 
		// need to be included in a linenumber range
		// basic blocks that are not fully included in a linenumberrange are 
		// still added to the range block but in addition to that, the 
		// instructions that are not in the specified linenumberrange are added 
		// to an offset counter to be able to subtract these from the results.
		if(this.labelBlocks.isEmpty()) {
			// empty method?
			return;
		}
		
		int currentBasicBlockIndex = 0;
		
		// the current basic block from its start to the currentLine
		InstructionBlockDescriptor partialBB = new InstructionBlockDescriptor();
		
		// initialise range blocks
		for(int i = 0; i < this.ranges.length; i++) {
			rangeBlocks[i] = new RangeBlockDescriptor(basicBlockLabels.length);
			rangeBlocks[i].setBlockIndex(i);
		}
				
		// we need the basic blocks to calculate partial blocks
		final InstructionBlockDescriptor[] basicBlocks = 
			this.instrumentationState.getBasicBlockSerialisation()
					.getBasicBlocksByMethod().get(this.methodDescriptorString);
		
		for(InstructionBlockLocation labelBlock : this.labelBlocks) {
			Label currentLabel =  labelBlock.label;
			// ranges where firstLine <= currentLine <= lastLine
			// this needs to be rebuild for every new line because the order
			// of the linenumbers is not the same as the order of the labels
			List<Integer> currentRanges = new ArrayList<Integer>();
			
			final int currentLine = labelBlock.lineNumber;
			

			// look for a basic block start labeltype filter textype filter textt
			int bbFind = findLabelIndex(this.basicBlockLabels, currentLabel);
			if(bbFind >= 0) {
				// a new basic block starts
				currentBasicBlockIndex = bbFind;
				// since a new basic block starts, the old basic block is complete
				// start a new partialBB
				partialBB = new InstructionBlockDescriptor();
			}
			
			// TODO: at this point, currentLine may be -1, so we need to find 
			// to which line the label belongs so we can answer whether the 
			// instructions belong to a range or not.

			// calculate currentRanges
			for(int r  = 0; r < ranges.length; r++) {
				if(currentLine >= this.ranges[r].firstLine
						&& currentLine <= this.ranges[r].lastLine) {
					// move range to currentRanges
					currentRanges.add(r);

					// add the basic block to all current ranges
					RangeBlockDescriptor.setUsesBasicBlock(
							rangeBlocks[r], currentBasicBlockIndex);
					
					if(!rangeWasEntered[r]) {
						// this is the first time we are in this range
						rangeWasEntered[r] = true;
						BasicBlockOffset bbOffset = rangeBlocks[r].new BasicBlockOffset();
						bbOffset.offset = 
							InstructionBlockDescriptor.subtract(	// offset=-partialBB
									new InstructionBlockDescriptor(), partialBB);
						bbOffset.basicBlockIndex = currentBasicBlockIndex;
						rangeBlocks[r].getBasicBlockOffsets().add(bbOffset);
					}
				} else if(currentLine == this.ranges[r].lastLine + 1
						&& rangeBlocks[r].getBasicBlockCounts()[currentBasicBlockIndex] != 0) {
					// Assume this is the label directly after the range.
					// Handle the partial basic block instructions by					
					// subtracting the instructions that are in the basic block, 
					// but not yet in the partialBB from the range.
					BasicBlockOffset bbOffset = rangeBlocks[r].new BasicBlockOffset();
					// the offset 
					bbOffset.offset = 
						InstructionBlockDescriptor.subtract(
								partialBB, basicBlocks[currentBasicBlockIndex]);
					bbOffset.basicBlockIndex = currentBasicBlockIndex;
					rangeBlocks[r].getBasicBlockOffsets().add(bbOffset);
				}
			}
			
			// add the instructions for this label to the partialBB
			partialBB.add(labelBlock.labelBlock);
		}
				
		// serialise the detected blocks
		instrumentationState.getRangeBlockSerialisation().addBasicBlocksForMethod(
				methodDescriptorString, 
				rangeBlocks);
	}

	/**
	 * Creates instruction blocks for each label in the code.
	 * These are saved in {@link #labelBlocks}.
	 * @param instructions
	 */
	private void createInstructionBlocks(InsnList instructions) {
		Label currentLabel = null;
		// build a map that saves from where labels are jumped to
		// so that jumpSourceMap(toLabel) == fromLabel
		Map<Label, List<Label>> jumpSourceMap = new HashMap<Label, List<Label>>();
		
		// go through all instructions
		for (	@SuppressWarnings("unchecked")
				Iterator<AbstractInsnNode> iterator = instructions.iterator(); 
				iterator.hasNext();
			) {
			AbstractInsnNode insn = iterator.next();
			if(insn instanceof LabelNode) {
				currentLabel = ((LabelNode)insn).getLabel();
			} else if(insn instanceof JumpInsnNode) {
				// save the fact that there is a jump from the current label 
				// block to the target label
				JumpInsnNode jump = (JumpInsnNode)insn;
				Label target = jump.label.getLabel();
				List<Label> sourceList = jumpSourceMap.get(target);
				if(sourceList == null) {
					sourceList = new LinkedList<Label>();
					jumpSourceMap.put(target, sourceList);
				}
				sourceList.add(currentLabel);
			}
		}
		currentLabel = null; // no longer needed

		InstructionBlockLocation currentIB = null;
		// go through all instructions again
		for (	@SuppressWarnings("unchecked")
				Iterator<AbstractInsnNode> iterator = instructions.iterator(); 
				iterator.hasNext();
			) {
			AbstractInsnNode insn = iterator.next();
			
			// look for labels that are marked to start an instruction block
			if(insn instanceof LabelNode) {
				Label label = ((LabelNode)insn).getLabel();
				// select the block
				// the block cannot be null because every label has been visited
				// before and therefore has been added to labelBlocks
				currentIB = findLabelBlockByLabel.get(label);
			} else {
				if(currentIB.lineNumber < 0) {
					// the label has no line number assigned
					// find it in the label map
					List<Label> sourceLabels = jumpSourceMap.get(currentIB.label);
					assert sourceLabels.size() == 1 : "Expected exactly one jump source.";
					Label jumpSource = sourceLabels.get(0);
					currentIB.lineNumber = 
						findLabelBlockByLabel.get(jumpSource).lineNumber;
				}
				// add the instruction to the block
				InstructionBlockDescriptor.addInstruction(currentIB.labelBlock, insn);
			}
		}
	}

	/**
	 * @param labels Array of labels to search.
	 * @param label {@link Label} to find the index of.
	 * @return The index of the label in the array of labels or -1 if the label
	 * is not in the array
	 */
	private int findLabelIndex(Label[] labels, Label label) {
		for(int i = 0; i < labels.length; i++) {
			if(labels[i].equals(label)) {
				return i;
			}
		}
		return -1;
	}

//	@Override
	public void postAnalysisEvent(InsnList instructions) {
		if(this.basicBlockLabels == null) {
			throw new IllegalStateException("RangeBlockAnalyser: basic block labels have not been specified.");
		}
		

		// * first of all, gather the instructions for each label block
		this.createInstructionBlocks(instructions);

		// Check whether all specified line numbers exist in this method
		if(!this.lineNumbersNotYetFound.isEmpty()) {
			log.severe("Could not find specified line numbers in " + this.methodDescriptorString);
			StringBuilder exceptionText = new StringBuilder();
			exceptionText.append("The following " + this.lineNumbersNotYetFound.size() 
					+ " source code line numbers should be instrumented, but do not exist in the Bytecode: ");
			for(int lineNr : this.lineNumbersNotYetFound) {
				exceptionText.append(lineNr + ";");
			}
			IllegalArgumentException iae = new IllegalArgumentException(exceptionText.toString());
			log.log(Level.SEVERE, "", iae);
			throw iae; 
		}

		// * then, in a second step: merge label blocks to range blocks
		//   (take care of the goto containing label blocks there)
		this.constructRangeBlocks();
	}

	/**
	 * @param basicBlockLabels Labels for the basic blocks found by the 
	 * {@link BasicBlockAnalyser}. The index is used to refer to the basic block.
	 */
	public void setBasicBlockLabels(Label[] basicBlockLabels) {
		this.basicBlockLabels = basicBlockLabels;
	}
}
