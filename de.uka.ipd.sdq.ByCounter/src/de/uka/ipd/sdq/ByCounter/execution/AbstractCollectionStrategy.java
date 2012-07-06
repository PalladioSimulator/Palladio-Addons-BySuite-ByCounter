package de.uka.ipd.sdq.ByCounter.execution;

import java.util.logging.Logger;

/**
 * Abstract class implementing {@link ICollectionStrategy} that keeps track
 * of the {@link CountingResultCollector} and logging.
 * @author Martin Krogmann
 *
 */
public abstract class AbstractCollectionStrategy implements ICollectionStrategy {

	/** The {@link CountingResultCollector} that uses this strategy. */
	protected CountingResultCollector parentResultCollector;

	/** see http://en.wikipedia.org/wiki/Data_log */
	protected Logger log;

	/**
	 * Construction of the strategy object.
	 * @param parent CountingResultCollector that makes use of this strategy.
	 */
	public AbstractCollectionStrategy(CountingResultCollector parent) {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.parentResultCollector = parent;
	}
}