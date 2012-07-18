package de.uka.ipd.sdq.ByCounter.execution;

public interface ISimpleCountingResult {
	final int NO_COUNT_AVAILABLE = -1;
	
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
