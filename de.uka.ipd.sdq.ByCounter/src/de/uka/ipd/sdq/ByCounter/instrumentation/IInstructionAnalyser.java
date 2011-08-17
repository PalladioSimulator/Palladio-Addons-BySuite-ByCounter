package de.uka.ipd.sdq.ByCounter.instrumentation;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.TryCatchBlockNode;

public interface IInstructionAnalyser {
	
	void analyseInstruction(AbstractInsnNode insn);
	
	void analyseTryCatchBlock(final TryCatchBlockNode tryCatchNode);
	
	void postAnalysisEvent(InsnList instructions);

}
