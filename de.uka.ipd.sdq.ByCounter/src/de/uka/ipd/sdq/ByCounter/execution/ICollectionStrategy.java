package de.uka.ipd.sdq.ByCounter.execution;

import de.uka.ipd.sdq.ByCounter.results.ResultCollection;

/**
 * Interface used by {@link CountingResultCollector} to control different 
 * methods of result collection and storage.
 * @author Martin Krogmann
 */
public interface ICollectionStrategy {

	/** Clear the result storage fields. */
	public abstract void clearResults();

	/**
	 * Protocol the count to the collection strategy.
	 * @param result The result to protocol.
	 * @return True, if the result was accepted.
	 */
	public abstract boolean protocolCount(ProtocolCountStructure result);

	/**
	 * @return The {@link CountingResultBase}s produced from the handling and 
	 * accepting of 
	 * results by this strategy.  
	 */
	public abstract ResultCollection retrieveAllCountingResults();

}