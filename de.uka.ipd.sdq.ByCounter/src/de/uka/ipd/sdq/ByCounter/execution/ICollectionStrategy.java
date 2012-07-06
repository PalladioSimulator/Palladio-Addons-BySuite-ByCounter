package de.uka.ipd.sdq.ByCounter.execution;

import java.util.SortedSet;

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
	 */
	public abstract boolean protocolCount(ProtocolCountStructure result);

	/**
	 * @return The {@link CountingResult}s produced from the handling and 
	 * accepting of 
	 * results by this strategy.  
	 */
	public abstract SortedSet<CountingResult> retrieveAllCountingResults();

}