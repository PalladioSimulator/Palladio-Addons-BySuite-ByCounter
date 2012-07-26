package de.uka.ipd.sdq.ByCounter.results;

import java.io.Serializable;

import de.uka.ipd.sdq.ByCounter.execution.CountingResultBase;

/**
 * 
 * @author Martin Krogmann
 *
 */
public class CountingResult extends CountingResultBase implements Cloneable {

	/**
	 * Version for {@link Serializable} interface.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The parent {@link ResultCollection} that this {@link CountingResult} 
	 * is a part of.
	 */
	private ResultCollection resultCollection;
	
	/**
	 * A reference to the parent if this result has a parent {@link RequestResult}.
	 */
	private RequestResult requestResult;

//	/**
//	 * This Map contains counts of method invocations, where the key is the
//	 * method signature, the value is the invocation count.
//	 */
//	private SortedMap<String, Long> methodCalls;

//	/**
//	 * This array contains the counts of elementary bytecode instructions.
//	 * The array index equals the opcode of the instruction.
//	 */
//	private long[] bytecodes;
	
	/**
	 * The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	private Object observedElement;
//	
//	/**
//	 * The time stamp which marks the beginning of execution (i.e. run)
//	 * of the method for which this CountingResult holds bytecode counts.
//	 */
//	private long methodInvocationBeginning;
//
//	/**
//	 * The time stamp which was set immediately before this method called
//	 * the CountingResultCollector. In other words, this is
//	 * <b>approximately</b> the time when the method execution was finished.
//	 */
//	private long methodReportingTime;
	
	/**
	 * Set fields to null.
	 */
	public CountingResult() {
//		this.methodCalls = null;
//		this.bytecodes = null;
		this.observedElement = null;
		this.resultCollection = null;
		this.requestResult = null;
	}
	
	/**
	 * Copy constructor.
	 * @param src Source to copy attributes from.
	 */
	public CountingResult(final CountingResult src) {
		super(src);
		this.observedElement = src.observedElement;
		this.resultCollection = src.resultCollection;
		this.requestResult = src.requestResult;
	}
	
	/**
	 * Constructs the result and initialises the fields with null.
	 * @param parent The parent {@link ResultCollection} that this {@link CountingResult} 
	 * is a part of.
	 */
	public CountingResult(final ResultCollection parent) {
		this();
		this.resultCollection = parent;
	}
	
	/**
	 * Constructs the result and initialises the fields with null.
	 * @param parent The parent {@link RequestResult} that this {@link CountingResult} 
	 * is a part of.
	 */
	public CountingResult(final RequestResult parent) {
		this();
		this.requestResult = parent;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public CountingResult clone(){
		CountingResult copy = null;

		copy = (CountingResult) super.clone();
		copy.setResultCollection(this.resultCollection);
//		copy.setBytecodes(this.bytecodes);
		copy.setRequestResult(this.requestResult);
//		copy.setMethodCalls(this.methodCalls);
		copy.setObservedElement(this.observedElement);
		return copy;
	}

//	/**
//	 * 
//	 * @return This Map contains counts of method invocations, where the key is the
//	 * method signature, the value is the invocation count.
//	 */
//	public SortedMap<String, Long> getMethodCalls() {
//		return methodCalls;
//	}
//
//	/**
//	 * 
//	 * @param methodCalls This Map contains counts of method invocations, where the key is the
//	 * method signature, the value is the invocation count.
//	 */
//	public void setMethodCalls(SortedMap<String, Long> methodCalls) {
//		this.methodCalls = methodCalls;
//	}
//
//	/**
//	 * 
//	 * @return This array contains the counts of elementary bytecode instructions.
//	 * The array index equals the opcode of the instruction.
//	 */
//	public long[] getBytecodes() {
//		return bytecodes;
//	}
//
//	/**
//	 * 
//	 * @param bytecodes This array contains the counts of elementary bytecode instructions.
//	 * The array index equals the opcode of the instruction.
//	 */
//	public void setBytecodes(long[] bytecodes) {
//		this.bytecodes = bytecodes;
//	}

	/**
	 * @return The parent {@link ResultCollection} that this {@link CountingResult} 
	 * is a part of.
	 */
	public ResultCollection getResultCollection() {
		return resultCollection;
	}

	/**
	 * 
	 * @param resultCollection The parent {@link ResultCollection} that this {@link CountingResult} 
	 * is a part of.
	 */
	public void setResultCollection(ResultCollection resultCollection) {
		this.resultCollection = resultCollection;
	}

	/**
	 * @return A reference to the parent if this result has a parent {@link RequestResult}.
	 */
	public RequestResult getRequestResult() {
		return requestResult;
	}

	/**
	 * @param requestResult A reference to the parent if this result has a parent {@link RequestResult}.
	 */
	public void setRequestResult(RequestResult requestResult) {
		this.requestResult = requestResult;
	}
//	
//	/**
//	 * @param methodInvocationBeginning The time stamp which marks the beginning of execution (i.e. run)
//	 * of the method for which this CountingResult holds bytecode counts.
//	 */
//	public void setMethodInvocationBeginning(long methodInvocationBeginning) {
//		this.methodInvocationBeginning = methodInvocationBeginning;
//	}
//
//	/**
//	 * @param methodReportingTime The time stamp which was set immediately before this method called
//	 * the CountingResultCollector. In other words, this is
//	 * <b>approximately</b> the time when the method execution was finished.
//	 */
//	public void setMethodReportingTime(long methodReportingTime) {
//		this.methodReportingTime = methodReportingTime;
//	}
//	
//	/**
//	 * @return The time stamp which marks the beginning of execution (i.e. run)
//	 * of the method for which this CountingResult holds bytecode counts.
//	 */
//	public long getMethodInvocationBeginning() {
//		return this.methodInvocationBeginning;
//	}
//	
//	/**
//	 * 
//	 * @return The time stamp which was set immediately before this method called
//	 * the CountingResultCollector. In other words, this is
//	 * <b>approximately</b> the time when the method execution was finished.
//	 */
//	public long getMethodReportingTime() {
//		return this.methodReportingTime;
//	}

	/**
	 * @return The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	public Object getObservedElement() {
		return observedElement;
	}

	/**
	 * @param observedElement The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	public void setObservedElement(Object observedElement) {
		this.observedElement = observedElement;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.observedElement == null) ? 0 : this.observedElement
						.hashCode());
		result = prime
				* result
				+ ((this.requestResult == null) ? 0 : this.requestResult
						.hashCode());
		result = prime
				* result
				+ ((this.resultCollection == null) ? 0 : this.resultCollection
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountingResult other = (CountingResult) obj;
		if (this.observedElement == null) {
			if (other.observedElement != null)
				return false;
		} else if (!this.observedElement.equals(other.observedElement))
			return false;
		if (this.requestResult == null) {
			if (other.requestResult != null)
				return false;
		} else if (!this.requestResult.equals(other.requestResult))
			return false;
		if (this.resultCollection == null) {
			if (other.resultCollection != null)
				return false;
		} else if (!this.resultCollection.equals(other.resultCollection))
			return false;
		return true;
	}
}
