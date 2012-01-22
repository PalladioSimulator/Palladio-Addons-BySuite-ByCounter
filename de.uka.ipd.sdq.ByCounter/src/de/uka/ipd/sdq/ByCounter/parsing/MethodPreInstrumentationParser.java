package de.uka.ipd.sdq.ByCounter.parsing;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.IntInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.MultiANewArrayInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.TypeInsnNode;

import de.uka.ipd.sdq.ByCounter.instrumentation.AdditionalOpcodeInformation;
import de.uka.ipd.sdq.ByCounter.instrumentation.IInstructionAnalyser;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationState;
import de.uka.ipd.sdq.ByCounter.instrumentation.MethodCountMethodAdapter;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * MethodPreInstrumentationParser implements a quick method visiting pass for 
 * finding method invocations and array constructions. The knowledge obtained 
 * from this pass can be used to count method invocations more efficiently 
 * using simple int counters and 'iinc' instructions. In addition to that, 
 * array type and dimension information can be obtained.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class MethodPreInstrumentationParser extends MethodAdapter {
		
	private AdditionalOpcodeInformation additionalOpcInfo = null;
	
	/** User specified parameters */
	private InstrumentationParameters instrumentationParameters;
	
	private MethodCountMethodAdapter methodCountMethodAdapter;
	
	private MethodVisitor nextVisitor;

	private Logger log;

	private BasicBlockAnalyser basicBlockAnalyser;
	
	private RangeBlockAnalyser rangeBlockAnalyser;
	
	private List<IInstructionAnalyser> instructionAnalysers;

	private boolean hasRangeBlocks;

	/** Intermediate results of the instrumentation. */
	private InstrumentationState instrumentationState;

	/**
	 * @param access As from ClassVisitor.
	 * @param name As from ClassVisitor.
	 * @param desc As from ClassVisitor.
	 * @param methodCountMethodAdapter {@link MethodCountMethodAdapter} that needs the method
	 * @param parameters Parameters for instrumentation. Also contains information that decides what is done before instrumetation.
	 * invocation information for proper instrumentation.
	 * @param method The currently analysed method.
	 */
	public MethodPreInstrumentationParser(
			MethodVisitor mv,
			int access, 
			String owner,
			String name,
			String desc, 
			MethodCountMethodAdapter methodCountMethodAdapter,
			InstrumentationParameters parameters,
			InstrumentationState state,
			MethodDescriptor method) {
		super(new MethodNode(access, name, desc, null, null));
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.nextVisitor = mv;
		this.additionalOpcInfo = new AdditionalOpcodeInformation();
		this.methodCountMethodAdapter = methodCountMethodAdapter;
		this.instrumentationParameters = parameters;
		this.instrumentationState = state;
		this.hasRangeBlocks = method.getCodeAreasToInstrument() != null
						&& method.getCodeAreasToInstrument().length != 0;
		
		this.instructionAnalysers = new ArrayList<IInstructionAnalyser>();
		if(this.instrumentationParameters.getUseBasicBlocks()) {
//			MethodNode methodNode = (MethodNode)this.mv;
			log.info("Analysing method for basic blocks.");
			this.basicBlockAnalyser = new BasicBlockAnalyser(
					MethodDescriptor._constructMethodDescriptorFromASM(owner, name, desc).getCanonicalMethodName(),
					this.instrumentationState);
			this.instructionAnalysers.add(basicBlockAnalyser);
			// are code areas specified for the method?
			if(hasRangeBlocks) {
				log.info("Analysing method for range blocks.");
				this.rangeBlockAnalyser = new RangeBlockAnalyser(
						method, 
						this.instrumentationState);
				this.instructionAnalysers.add(rangeBlockAnalyser);
			}
		}
	}

	/**
	 * Get the list containing all method signatures that were called in the 
	 * visited method.
	 * @return A map containing method signatures as keys in the form 
	 * <code>owner + "." + name + desc</code>. Signatures are specific to 
	 * the object they are invoked on. This means that a certain methods 
	 * may be more than once in the array. However, their signatures differ, 
	 * as the owner property differs.
	 * @see MethodDescriptor#getCanonicalMethodName()
	 */
	public AdditionalOpcodeInformation getAdditionalOpcodeInformation() {
		return this.additionalOpcInfo;
	}

	/**
	 * Visiting the end of the method allows to collect the needed method 
	 * invocation information.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void visitEnd() {
		// Use a MethodNode to analyse all instructions in the method
		MethodNode mn = (MethodNode) this.mv;

		{			
			Iterator<AbstractInsnNode> iterator = mn.instructions.iterator();
			while(iterator.hasNext()) {
				AbstractInsnNode insn = iterator.next();
				analyseCountedInstruction(insn);
			}
		}
		
		// try catch blocks are not in the normal instructions list and need to 
		// be analysed separately.
		{
			Iterator<TryCatchBlockNode> iterator = mn.tryCatchBlocks.iterator();
			while(iterator.hasNext()) {
				TryCatchBlockNode tryCatchNode = iterator.next();
				for(IInstructionAnalyser analyser : this.instructionAnalysers) {
					analyser.analyseTryCatchBlock(tryCatchNode);
				}
			}
		}
		
		// execute post analysis methods
		

		if(this.instrumentationParameters.getUseBasicBlocks()) {
			// range block analyser depends on the basic block analyser
			this.basicBlockAnalyser.postAnalysisEvent(mn.instructions);
			if(this.hasRangeBlocks) {
				this.rangeBlockAnalyser.setBasicBlockLabels(this.basicBlockAnalyser.getBasicBlockLabels());
				this.rangeBlockAnalyser.postAnalysisEvent(mn.instructions);
			}
		}
		
		for(IInstructionAnalyser analyser : this.instructionAnalysers) {
			if(analyser == this.basicBlockAnalyser 
					|| analyser == this.rangeBlockAnalyser) {
				// already done
				continue;
			}
			analyser.postAnalysisEvent(mn.instructions);
		}
		
		if(this.instrumentationParameters.getUseBasicBlocks()) {
			Label[] basicBlockLabels = ((BasicBlockAnalyser)this.basicBlockAnalyser).getBasicBlockLabels();
			this.methodCountMethodAdapter.setBasicBlockLabels(
					basicBlockLabels);
		}
		
		// pass the gathered information to the methodCountMethodAdapter
		this.methodCountMethodAdapter.setMethodInvocations(this.additionalOpcInfo);

		
		// check whether the method is marked as instrumented.
		checkMethodMarkedAsInstrumented();

		mn.accept(this.nextVisitor);
	}


	/**
	 * Parse the instruction in order to prepare data structures etc.
	 * @param insn An instruction that has to be counted.
	 */
	private void analyseCountedInstruction(final AbstractInsnNode insn) {

		// Initialize registers for all appearing method invocations
		if(insn instanceof MethodInsnNode) {
			// gather signature for the method invocation
			MethodInsnNode method = ((MethodInsnNode)insn);
			String sig = MethodDescriptor._constructMethodDescriptorFromASM(
					method.owner, method.name, method.desc).getCanonicalMethodName();
			if(!this.additionalOpcInfo.getMethodInvokations().contains(sig)) {
				this.additionalOpcInfo.getMethodInvokations().add(sig);
			}
		} else if(this.instrumentationParameters.getUseArrayParameterRecording()) { 
			analyseForArrayParameterRecording(insn);
		}

		for(IInstructionAnalyser analyser : this.instructionAnalysers) {
			analyser.analyseInstruction(insn);
		}
	}
	


	/**
	 * 
	 * @param insn Instruction to analyse.
	 */
	private void analyseForArrayParameterRecording(final AbstractInsnNode insn) {
		if(insn instanceof IntInsnNode 
				&& insn.getOpcode() == Opcodes.NEWARRAY) {
			// get the type integer for the newarray call
			IntInsnNode node = ((IntInsnNode)insn);
			// since the integer contains the type, duplicates are not wanted.
			if(this.additionalOpcInfo.getIndexOfAdditionInformation(
					node.operand, 
					AdditionalOpcodeInformation.NO_INFORMATION_STRING) == -1) {
				// add the entry
				this.additionalOpcInfo.addAdditionalInformation(node.operand,
						AdditionalOpcodeInformation.NO_INFORMATION_STRING);
			}
		} else if(insn instanceof TypeInsnNode 
				&& insn.getOpcode() == Opcodes.ANEWARRAY) {
			// get the type string for the anewarray call
			TypeInsnNode node = ((TypeInsnNode)insn);
			if(this.additionalOpcInfo.getIndexOfAdditionInformation(
					AdditionalOpcodeInformation.NO_INFORMATION_INT, 
					node.desc) == -1) {
				this.additionalOpcInfo.addAdditionalInformation(
						AdditionalOpcodeInformation.NO_INFORMATION_INT,
						node.desc);
			}
		} else if(insn instanceof MultiANewArrayInsnNode) {
			// get the type string and dimension integer
			MultiANewArrayInsnNode node = ((MultiANewArrayInsnNode)insn);
			if(this.additionalOpcInfo.getIndexOfAdditionInformation(
					node.dims, 
					node.desc) == -1) {
				this.additionalOpcInfo.addAdditionalInformation(
						node.dims,
						node.desc);
			}
		}
	}


	/**
	 * Checks whether the method is marked as instrumented and sets the status
	 * on the {@link #methodCountMethodAdapter}.
	 */
	private void checkMethodMarkedAsInstrumented() {
		MethodNode methodNode = (MethodNode)mv;
		if(methodNode.instructions.size() > 0) {
			int i = 0;
			// skip nodes that are no "real" instructions
			while(methodNode.instructions.get(i) instanceof LabelNode
					|| methodNode.instructions.get(i) instanceof LineNumberNode) {
				i++;
			}
			AbstractInsnNode firstInsn = methodNode.instructions.get(i);
			if(firstInsn.getOpcode() == Opcodes.LDC) {
				LdcInsnNode ldcInsn = ((LdcInsnNode)firstInsn);
				if(ldcInsn.cst.getClass().equals(String.class)) {//"cst" means "constant"
					String strConstant = (String)ldcInsn.cst;
					if(strConstant.equals(MethodCountMethodAdapter.INSTRUMENTATION_MARKER)) {
						this.methodCountMethodAdapter.setIsAlreadyInstrumented(true, methodNode.name, methodNode.signature);
					}
				}
			}
		}
	}

}
