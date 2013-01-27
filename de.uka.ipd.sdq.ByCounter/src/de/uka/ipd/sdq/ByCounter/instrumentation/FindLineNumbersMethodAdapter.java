package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;


public class FindLineNumbersMethodAdapter extends MethodAdapter {
	
	/** Line numbers in this method. */
	protected List<Integer> lineNumbers;

	/**
	 * @param mv MethodVisitor in the transformation chain.
	 */
	public FindLineNumbersMethodAdapter(MethodVisitor mv) {
		super(mv);
		this.lineNumbers = new LinkedList<Integer>();
	}
	
	@Override
	public void visitLineNumber(int line, Label start) {
		lineNumbers.add(line);
	}
	/**
	 * @return A list of the line numbers for which there are labels in the 
	 * visited method.
	 */
	public List<Integer> getLineNumbers() {
		return this.lineNumbers;
	}
}
