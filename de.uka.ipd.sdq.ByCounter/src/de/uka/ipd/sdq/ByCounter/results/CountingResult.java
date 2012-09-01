package de.uka.ipd.sdq.ByCounter.results;

import java.io.Serializable;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultBase;
import de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument;

/**
 * This class is a container for a result obtained by executing bytecode 
 * instrumented using {@link BytecodeCounter}. The element in the code that 
 * lead to the production of this result can be a specific method, a range of 
 * line numbers in a method, a region specified by start and stop points, etc. 
 * It is referenced in {@link #getObservedElement()}. 
 * <p>
 * The execution counts for specific bytecodes is available as 
 * {@link #getOpcodeCounts()}. Method invocations are saved in 
 * {@link #getMethodCallCounts()}.
 * Depending on the type of instrumentation, more information is available. 
 * Refer to the documentation on the get methods of this class for details. 
 * </p> 
 * @see CountingResultBase
 * @see de.uka.ipd.sdq.ByCounter.results
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

	/**
	 * The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	private EntityToInstrument observedElement;

	/**
	 * Set fields to null.
	 */
	public CountingResult() {
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
		copy.setRequestResult(this.requestResult);
		copy.setObservedElement(this.observedElement);
		return copy;
	}

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

	/**
	 * @return The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	public EntityToInstrument getObservedElement() {
		return observedElement;
	}

	/**
	 * @param observedElement The specification of instrumentation that lead to the observation of 
	 * this result.
	 */
	public void setObservedElement(EntityToInstrument observedElement) {
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
