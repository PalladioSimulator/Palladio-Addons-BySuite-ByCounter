package de.uka.ipd.sdq.ByCounter.results;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.execution.CountingResultBase;

/**
 * A {@link CountingResult} emitted from a specific thread.
 * @author Martin Krogmann
 * 
 * @see de.uka.ipd.sdq.ByCounter.results
 *
 */
public class ThreadedCountingResult extends CountingResult {
	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private static Logger log = Logger.getLogger(CountingResultBase.class.getCanonicalName());

	/**
	 * Serialisation version.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A {@link SortedSet} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	private SortedSet<ThreadedCountingResult> spawnedThreadedCountingResults;
	
	/**
	 * The {@link ThreadedCountingResult} that contains this 
	 * {@link ThreadedCountingResult} as part of 
	 * {@link ThreadedCountingResult#getSpawnedThreadedCountingResults()}.
	 */
	private ThreadedCountingResult threadedCountingResultSource;

	/**
	 * Default constructor. Properties need to be set
	 * manually.
	 */
	public ThreadedCountingResult() {
		super();
		this.spawnedThreadedCountingResults = new TreeSet<ThreadedCountingResult>();
		this.threadedCountingResultSource = null;
		this.threadId = -1;
	}
	
	/**
	 * Copy properties of src.
	 * @param src {@link CountingResult} to copy attributes from.
	 */
	public ThreadedCountingResult(final CountingResult src) {
		this.set(src);
	}
	
	/** {@inheritDoc} */
	@Override
	public void set(CountingResultBase src) {
		super.set(src);
		if(src instanceof ThreadedCountingResult) {
			ThreadedCountingResult tcrSrc = (ThreadedCountingResult) src;
			this.spawnedThreadedCountingResults = new TreeSet<ThreadedCountingResult>(tcrSrc.getSpawnedThreadedCountingResults());
			this.threadedCountingResultSource = tcrSrc.threadedCountingResultSource;
		} else {
			this.spawnedThreadedCountingResults = new TreeSet<ThreadedCountingResult>();
			this.threadedCountingResultSource = null;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ThreadedCountingResult clone(){
		ThreadedCountingResult copy = null;

		copy = (ThreadedCountingResult) super.clone();
		copy.setThreadId(this.threadId);
		copy.setSpawnedThreadedCountingResults(
				new TreeSet<ThreadedCountingResult>(this.spawnedThreadedCountingResults));
		copy.setThreadedCountingResultSource(this.threadedCountingResultSource);
		return copy;
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
	 * @return A {@link SortedSet} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	public SortedSet<ThreadedCountingResult> getSpawnedThreadedCountingResults() {
		return spawnedThreadedCountingResults;
	}

	/**
	 * @param spawnedThreadedCountingResults A {@link SortedSet} of {@link ThreadedCountingResult} from threads that
	 * where spawned from the thread of this result.
	 */
	public void setSpawnedThreadedCountingResults(
			SortedSet<ThreadedCountingResult> spawnedThreadedCountingResults) {
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized String logResult(boolean printZeros, boolean vertically) {
		StringBuilder builder = new StringBuilder();
		builder.append("ThreadedCountingResult [threadId=");
		builder.append(this.threadId);
		builder.append(", threadedCountingResultSource=");
		builder.append(this.threadedCountingResultSource);
		builder.append(super.logResult(printZeros, vertically, Level.FINEST));
		builder.append(", spawnedThreadedCountingResults=");
		builder.append(this.spawnedThreadedCountingResults);
		builder.append("]");
		String string = builder.toString();
		log.log(Level.INFO, string);
		return string;
	}
	
	/**
	 * @return Short summary of the counting result.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n"+
				  "      "+this.getClass().getSimpleName()+" (hash code: "+this.hashCode()+")\n");
		sb.append("      > Method name     : "+this.getQualifiedMethodName()+ " (Own UUID: " + this.getMethodExecutionID() + ", threadId: " + this.threadId + ")\n");
		sb.append("      > Method duration : "+(this.getReportingTime()-this.getMethodInvocationBeginning())+
				"(start: "+this.getMethodInvocationBeginning()+", end: "+this.getReportingTime()+")\n");
		sb.append("      > Opcode counts   : "+Arrays.toString(this.getOpcodeCounts())+"\n");
		sb.append("      > Method counts   : "+this.getMethodCallCounts()+"\n");
		if(this.getThreadedCountingResultSource() != null) {
			sb.append("      > Thread source   : "+this.getThreadedCountingResultSource().hashCode()+" (hash code)\n");	// toString() would cause infinite recursion
		}
		sb.append("      > Thread spawns   : "+this.getSpawnedThreadedCountingResults()+"\n");
		return sb.toString();
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
		ThreadedCountingResult other = (ThreadedCountingResult) obj;
		if (this.spawnedThreadedCountingResults == null) {
			if (other.spawnedThreadedCountingResults != null)
				return false;
		} else if (!this.spawnedThreadedCountingResults
				.equals(other.spawnedThreadedCountingResults))
			return false;
		if (this.threadedCountingResultSource == null) {
			if (other.threadedCountingResultSource != null)
				return false;
		} else if (!this.threadedCountingResultSource
				.equals(other.threadedCountingResultSource))
			return false;
		return true;
	}
}
