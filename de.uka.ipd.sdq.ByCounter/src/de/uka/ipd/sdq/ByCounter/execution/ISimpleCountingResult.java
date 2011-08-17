package de.uka.ipd.sdq.ByCounter.execution;

public interface ISimpleCountingResult {
	final int NO_COUNT_AVAILABLE = -1;
	
	/**
	 * Simple getter for input file type.
	 * For SPECjvm2008, we are using the constants from compress.Harness:
	 *     public static final int DATA_TYPE_COMPRESSED=3;
     *     public static final int DATA_TYPE_MIXED=2;
     *     public static final int DATA_TYPE_UNCOMPRESSED=1;
	 *     public static final int DATA_TYPE_UNKNOWN=0;//
	 * @return The input file type.
	 */
	public int getFileType();
	
	/**
	 * @return For example, for SPECjvm2008.Compress, this is the size of the input file (in bytes)
	 */
	public long getInputCharacterisation();
	
	/**
	 * TODO
	 * @param methodName
	 * @return Method execution count
	 */
	public Long getMethodCount(String methodName);
	
	/**
	 * TODO
	 * @return Opcode execution count
	 */
	public Long getOpcodeCount(int opcode);
	
	/**
	 * TODO
	 * @return Opcode execution count
	 */
	public Long getOpcodeCount(String opcode);
	
	/**
	 * @return For example, for SPECjvm2008.Compress, this is the size of the 
	 * buffer which holds the compressed data of the input file.
	 */
	public long getOutputCharacterisation();	

	/**
	 * TODO
	 * @param includeInvokeOpcodes
	 * @return Total number of executed opcodes.
	 */
	public Long getTotalCount(boolean includeInvokeOpcodes);

	/**
	 * Set counted BCs for a opcode-specified BC
	 * @param opcode
	 * @param count
	 */
	public void setOpcodeCount(int opcode, Long count);

}
