package de.uka.ipd.sdq.ByCounter.results;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * A collection of individual request results and general counting results.
 * @author Martin Krogmann
 *
 */
public class ResultCollection {
	/**
	 * Result of requests.
	 */
	private SortedSet<RequestResult> requestResults;
	
	private SortedSet<CountingResult> countingResults;
	
	/**
	 * Default constructor: initilises empty lists.
	 */
	public ResultCollection() {
		this.requestResults = new TreeSet<RequestResult>();
		this.countingResults = new TreeSet<CountingResult>();
	}

	/**
	 * @return Result of requests.
	 */
	public SortedSet<RequestResult> getRequestResults() {
		return this.requestResults;
	}
	
	/**
	 * @return Result of requests.
	 */
	public SortedSet<CountingResult> getCountingResults() {
		return this.countingResults;
	}
	
	/**
	 * Adds another result to this result.
	 * @param lr {@link ResultCollection} to add.
	 */
	public void add(ResultCollection lr) {
		this.requestResults.addAll(lr.requestResults);
		this.countingResults.addAll(lr.countingResults);
	}
}
