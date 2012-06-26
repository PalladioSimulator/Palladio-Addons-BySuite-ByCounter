package de.uka.ipd.sdq.ByCounter.parsing;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class is used to serialise and deserialise basic block definitions of an
 * instrumentation run.
 */
public final class BasicBlockSerialisation implements Serializable {
	
	/**
	 * Serialisation version.
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, InstructionBlockDescriptor[]> basicBlocksByMethod;
	
	public BasicBlockSerialisation() {
		this.basicBlocksByMethod = new HashMap<String, InstructionBlockDescriptor[]>();
	}
	
	@Override
	public String toString() {
		return "BasicBlockSerialization["+basicBlocksByMethod+"]";
	}

	/**
	 * Adds basic block definitions for a given method.
	 * @param method Method description for the method to which the basic block
	 * belongs.
	 * @param basicBlocks Basic blocks to add.
	 * method.
	 */
	public void addBasicBlocksForMethod(String method, InstructionBlockDescriptor[] basicBlocks) {
		this.basicBlocksByMethod.put(method, basicBlocks);
	}

	/**
	 * @return the basicBlocksByMethod
	 */
	public HashMap<String, InstructionBlockDescriptor[]> getBasicBlocksByMethod() {
		return basicBlocksByMethod;
	}

}
