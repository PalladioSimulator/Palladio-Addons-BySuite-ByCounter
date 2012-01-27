package de.uka.ipd.sdq.ByCounter.execution;

import java.util.SortedSet;
import java.util.logging.Logger;

/**
 * Interface used by {@link CountingResultCollector} to control different 
 * methods of result collection and storage.
 * @author Martin Krogmann
 *
 */
public abstract class AbstractCollectionStrategy {

	/** The {@link CountingResultCollector} that uses this strategy. */
	protected CountingResultCollector parentResultCollector;

	/** see http://en.wikipedia.org/wiki/Data_log */
	protected Logger log;

	/** Clear the result storage fields. */
	public abstract void clearResults();

	/**
	 * Protocol the count to the collection strategy.
	 * @param result The result to protocol.
	 * @param reportingStart Time stamp of the time of reporting the result.
	 * @return True, when the result is accepted by the strategy. False, when
	 * the result needs to be handled by a different strategy.
	 */
	public abstract boolean protocolCount(
			ProtocolCountStructure result,
			long reportingStart);

	/**
	 * Construction of the strategy object.
	 * @param parent CountingResultCollector that makes use of this strategy.
	 */
	public AbstractCollectionStrategy(CountingResultCollector parent) {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.parentResultCollector = parent;
	}
	
	/**
	 * @return The {@link CountingResult}s produced from the handling and 
	 * accepting of 
	 * results by this strategy.  
	 */
	public abstract SortedSet<CountingResult> retrieveAllCountingResults();
}