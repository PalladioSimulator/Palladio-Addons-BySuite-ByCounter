package de.uka.ipd.sdq.ByCounter.results;

import java.util.List;

/**
 * A {@link CountingResult} emitted from a specific thread.
 * @author Martin Krogmann
 *
 */
public class ThreadedCountingResult extends CountingResult {

	/**
	 * Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	private long threadId;
	
	/**
	 * A {@link List} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	private List<ThreadedCountingResult> spawnedThreadedCountingResults;
	
	/**
	 * The {@link ThreadedCountingResult} that contains this 
	 * {@link ThreadedCountingResult} as part of 
	 * {@link ThreadedCountingResult#getSpawnedThreadedCountingResults()}.
	 */
	private ThreadedCountingResult threadedCountingResultSource;

	/**
	 * Construct the result.
	 * @param parent The parent {@link ResultCollection} that this {@link CountingResult} 
	 * is a part of.
	 * @param threadId Id of the thread from which the result was reported.
	 */
	public ThreadedCountingResult(final ResultCollection parent, final long threadId) {
		super(parent);
		this.threadId = threadId;
		this.spawnedThreadedCountingResults = null;
		this.threadedCountingResultSource = null;
	}

	/**
	 * Construct the result.
	 * @param parent The parent {@link RequestResult} that this {@link CountingResult} 
	 * is a part of.
	 * @param threadId Id of the thread from which the result was reported.
	 */
	public ThreadedCountingResult(final RequestResult parent, final long threadId) {
		super(parent);
		this.threadId = threadId;
	}
	

	/**
	 * 
	 * @return Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	public long getThreadId() {
		return threadId;
	}

	/**
	 * 
	 * @param threadId Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	/**
	 * @return A {@link List} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	public List<ThreadedCountingResult> getSpawnedThreadedCountingResults() {
		return spawnedThreadedCountingResults;
	}

	/**
	 * @param spawnedThreadedCountingResults A {@link List} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	public void setSpawnedThreadedCountingResults(
			List<ThreadedCountingResult> spawnedThreadedCountingResults) {
		this.spawnedThreadedCountingResults = spawnedThreadedCountingResults;
	}

	/**
	 * @return The {@link ThreadedCountingResult} that contains this 
	 * {@link ThreadedCountingResult} as part of 
	 * {@link ThreadedCountingResult#getSpawnedThreadedCountingResults()}.
	 */
	public ThreadedCountingResult getThreadedCountingResultSource() {
		return threadedCountingResultSource;
	}

	/**
	 * @param threadedCountingResultSource The {@link ThreadedCountingResult} that contains this 
	 * {@link ThreadedCountingResult} as part of 
	 * {@link ThreadedCountingResult#getSpawnedThreadedCountingResults()}.
	 */
	public void setThreadedCountingResultSource(
			ThreadedCountingResult threadedCountingResultSource) {
		this.threadedCountingResultSource = threadedCountingResultSource;
	}
}
