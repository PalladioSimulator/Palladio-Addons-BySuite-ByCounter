package de.uka.ipd.sdq.ByCounter.parsing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.objectweb.asm.tree.TableSwitchInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;

import de.uka.ipd.sdq.ByCounter.instrumentation.IInstructionAnalyser;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;

/**
 * An implementation of {@link IInstructionAnalyser} that analyses instructions 
 * in order to find basicBlocks. The results of this {@link IInstructionAnalyser}
 * can be queried with the method {@link #getBasicBlockLabels()}.  
 *
 */
public final class BasicBlockAnalyser implements IInstructionAnalyser {
	

	private HashSet<Label> basicBlockLabels;
	private boolean isFirstInstruction;
	@SuppressWarnings("unused")
	private Logger log;
	private InstrumentationParameters instrumentationParameters;
	private String methodDescriptorString;

	
	public BasicBlockAnalyser(String methodDescriptorString,
			InstrumentationParameters instrumentationParameters) {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.basicBlockLabels = new HashSet<Label>();
		isFirstInstruction = true;
		this.methodDescriptorString = methodDescriptorString;
		this.instrumentationParameters = instrumentationParameters;
	}
	

	public void analyseInstruction(AbstractInsnNode insn) {
		if(isFirstInstruction) {
			// The first label always starts the first basic block 
			this.addNextLabelForBasicBlock(insn);
			isFirstInstruction = false;
		}
		this.analyseForBasicBlocks(insn);
	}
	

	private void addLabelForBasicBlock(LabelNode l) {
		if(l != null)
		this.basicBlockLabels.add(l.getLabel());
	}
	

	/**
	 * Look for basic block defining instructions.
	 * @param insn Instruction to analyse.
	 */
	@SuppressWarnings("unchecked")
	private void analyseForBasicBlocks(final AbstractInsnNode insn) {
		if(insn instanceof JumpInsnNode) {
			JumpInsnNode jump = (JumpInsnNode)insn;
			addLabelForBasicBlock(jump.label);
			// for conditional jumps, we need to start a new basic block after
			// the comparison because the jump ends the basic block, yet the 
			// following instruction is not "jumped" to, but executed in normal 
			// order if the comparison results in false
			if(jump.getOpcode() == Opcodes.IF_ACMPEQ
					|| jump.getOpcode() == Opcodes.IF_ACMPNE
					|| jump.getOpcode() == Opcodes.IF_ICMPEQ
					|| jump.getOpcode() == Opcodes.IF_ICMPGE
					|| jump.getOpcode() == Opcodes.IF_ICMPGT
					|| jump.getOpcode() == Opcodes.IF_ICMPLE
					|| jump.getOpcode() == Opcodes.IF_ICMPLT
					|| jump.getOpcode() == Opcodes.IF_ICMPNE
					|| jump.getOpcode() == Opcodes.IFEQ
					|| jump.getOpcode() == Opcodes.IFGE
					|| jump.getOpcode() == Opcodes.IFGT
					|| jump.getOpcode() == Opcodes.IFLE
					|| jump.getOpcode() == Opcodes.IFLT
					|| jump.getOpcode() == Opcodes.IFNE
					|| jump.getOpcode() == Opcodes.IFNONNULL
					|| jump.getOpcode() == Opcodes.IFNULL) {
				addNextLabelForBasicBlock(insn.getNext());
			}
		} else if(insn instanceof LookupSwitchInsnNode) {
			LookupSwitchInsnNode switchNode = (LookupSwitchInsnNode)insn;
			// add the label for the default handler block
			addLabelForBasicBlock(switchNode.dflt);
			
			// add the labels for the case handler blocks
			ListIterator<LabelNode> it = switchNode.labels.listIterator();
			while(it.hasNext()) {
				LabelNode l = it.next();
				addLabelForBasicBlock(l);
			}
		} else if(insn instanceof TableSwitchInsnNode) {
			TableSwitchInsnNode switchNode = (TableSwitchInsnNode)insn;
			// add the label for the default handler block
			addLabelForBasicBlock(switchNode.dflt);
			
			// add the labels for the case handler blocks
			ListIterator<LabelNode> it = switchNode.labels.listIterator();
			while(it.hasNext()) {
				LabelNode l = it.next();
				addLabelForBasicBlock(l);
			}
		}
	}


	
	/**
	 * 
	 * @param tryCatchNode The {@link TryCatchBlockNode} to analyse.
	 */
	public void analyseTryCatchBlock(final TryCatchBlockNode tryCatchNode) {
		// TODO: start and end of an exception mark a region in which the 
		// assumption of basic blocks is not valid as the block may be left
		// at any time
		addLabelForBasicBlock(tryCatchNode.start);
		addLabelForBasicBlock(tryCatchNode.handler);
	}


	/**
	 * Does {@link #addLabelForBasicBlock(LabelNode)} for the {@link LabelNode}
	 * instruction that is insn or the first {@link LabelNode} that follows 
	 * after the given instruction.
	 * @param insn Instruction after which to look for a {@link LabelNode}.
	 */
	private void addNextLabelForBasicBlock(final AbstractInsnNode insn) {
		AbstractInsnNode currentInsn = insn;
		while(currentInsn != null 
				&& !(currentInsn instanceof LabelNode)) {
			currentInsn = currentInsn.getNext();
		}
		addLabelForBasicBlock((LabelNode) currentInsn);
	}
	
	/**
	 * Walk through the instructions and use the {@link #basicBlockLabels} to
	 * construct basic blocks.
	 * @param instructions The list of instructions in the analysed method.
	 */
	private void constructBasicBlocks(Iterator<AbstractInsnNode> instructionIterator) {
		Label[] labels = this.basicBlockLabels.toArray(new Label[this.basicBlockLabels.size()]);
		
		InstructionBlockDescriptor[] basicBlocks = new InstructionBlockDescriptor[labels.length];
		InstructionBlockDescriptor bbDesc = new InstructionBlockDescriptor();

		// go through all instructions again
		for (	Iterator<AbstractInsnNode> iterator = instructionIterator; 
				iterator.hasNext();
			) {
			AbstractInsnNode insn = iterator.next();
			
			// look for labels that are marked to start a basic block
			if(insn instanceof LabelNode) {
				int labelIndex = -1;
				// check whether this label starts a new basic block
				for(int i = 0; i < labels.length; i++) {
					if(((LabelNode) insn).getLabel().equals(labels[i])) {
						labelIndex = i;
						break;
					}
				}
				if(labelIndex >= 0) {
					bbDesc = new InstructionBlockDescriptor();
					basicBlocks[labelIndex] = bbDesc;
					bbDesc.setBlockIndex(labelIndex);
				}
			} else {
				// add the instruction to the basic block
				InstructionBlockDescriptor.addInstruction(bbDesc, insn);
			}
		}
		
		instrumentationParameters.getBasicBlockSerialisation().addBasicBlocksForMethod(
				methodDescriptorString, 
				basicBlocks);
	}

	
	@SuppressWarnings("unchecked")
	public void postAnalysisEvent(InsnList instructions) {
		this.constructBasicBlocks(instructions.iterator());
	}
	
	public Label[] getBasicBlockLabels() {
		return this.basicBlockLabels.toArray(new Label[this.basicBlockLabels.size()]);
	}
}
