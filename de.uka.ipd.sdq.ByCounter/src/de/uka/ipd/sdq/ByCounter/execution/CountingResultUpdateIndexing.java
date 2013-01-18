package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;

/**
 * When online updates are enabled, this class holds updates to individual 
 * sections, until a new section is reported or the method ends.
 * @see InstrumentationParameters#getProvideOnlineSectionExecutionUpdates()
 * @author Martin Krogmann
 *
 */
public class CountingResultUpdateIndexing {
	/**
	 * This class groups all indexing information relevant to a single thread.
	 * @author Martin Krogmann
	 */
	private class ThreadIndex {
		/**
		 * {@link MethodIndex} by method id.
		 */
		public Map<UUID, MethodIndex> methodIndexById;
		/** The method for which the last update was provided. */
		public UUID lastUpdatedMethod;
		/** Id of the thread that this index is for. */
		public long threadId;
		
		/**
		 * @param threadId Id of the thread that this index is for.
		 */
		public ThreadIndex(final long threadId) {
			this.threadId = threadId;
			lastUpdatedMethod = null;
			this.methodIndexById = new HashMap<UUID, CountingResultUpdateIndexing.MethodIndex>();
		}

		@Override
		public String toString() {
			return "ThreadIndex [methodIndexById=" + this.methodIndexById
					+ ", lastUpdatedMethod=" + this.lastUpdatedMethod
					+ ", threadId=" + this.threadId + "]";
		}
	}
	/**
	 * This class groups all indexing information relevant to a single method.
	 * @author Martin Krogmann
	 */
	private class MethodIndex {
		/**
		 * The index of the section that was last added for the method.
		 */
		public int lastUpdatedSectionIndex;
		/**
		 * Queue that holds counting results.
		 */
		public Queue<CountingResult> sectionUpdates;

		/** The last basic block execution sequence of a result. */
		public List<Integer> lastBlockExecutionSequence;
		
		/** Id of the method that this index is for. */
		public UUID methodId;

		/**
		 * @param methodId Id of the method that this index is for.
		 */
		public MethodIndex(final UUID methodId) {
			this.methodId = methodId;
			this.sectionUpdates = new LinkedList<CountingResult>(); 
			this.lastBlockExecutionSequence = null;
			this.lastUpdatedSectionIndex = -1;
		}

		@Override
		public String toString() {
			return "MethodIndex [lastUpdatedSectionIndex="
					+ this.lastUpdatedSectionIndex + ", sectionUpdates="
					+ this.sectionUpdates + ", lastBlockExecutionSequence="
					+ this.lastBlockExecutionSequence + ", methodId="
					+ this.methodId + "]";
		}
	}

	/**
	 * Map that holds a {@link ThreadIndex} for each thread by thread id.
	 */
	private Map<Long, ThreadIndex> indexForThread;
	
	/**
	 * Construct the indexing structure.
	 */
	public CountingResultUpdateIndexing() {
		this.indexForThread = new HashMap<Long, CountingResultUpdateIndexing.ThreadIndex>();
	}

	/**
	 * This handles updates reported for individual section when online 
	 * updates are enabled.
	 * @param result The calculated counting result for the update.
	 * @param blockExecutionSequence Basic block execution sequence used to 
	 * see if consecutive results contain new information.
	 */
	public void add(CountingResult result, final List<Integer> blockExecutionSequence) {
		long currentThreadId = result.getThreadId();
		ThreadIndex currentThreadIndex = this.indexForThread.get(currentThreadId);
		if(currentThreadIndex == null) {
			currentThreadIndex = new ThreadIndex(currentThreadId);
			this.indexForThread.put(currentThreadId, currentThreadIndex);
		}
		final UUID methodID = result.getMethodExecutionID();
		if(currentThreadIndex.lastUpdatedMethod != null && !methodID.equals(currentThreadIndex.lastUpdatedMethod)) {
			// we entered a new method
			// provide an update for the previous method
			setMethodDone(currentThreadId, currentThreadIndex.lastUpdatedMethod);
		}

		MethodIndex currentMethodIndex = currentThreadIndex.methodIndexById.get(methodID);
		if(currentMethodIndex == null) {
			// no entry for this method yet
			currentMethodIndex = new MethodIndex(methodID);
			currentThreadIndex.methodIndexById.put(methodID, currentMethodIndex);
		}
		Queue<CountingResult> resultQueue = currentMethodIndex.sectionUpdates;
		Integer luSectionIndex = currentMethodIndex.lastUpdatedSectionIndex;
		
		if(luSectionIndex >= 0 
				&& luSectionIndex != result.getIndexOfRangeBlock()) {
			// a new section is being executed
			updateObserversWithSection(resultQueue);
		}
		
		if(currentMethodIndex.lastBlockExecutionSequence == null
				|| blockExecutionSequence != null
				&& !currentMethodIndex.lastBlockExecutionSequence.equals(blockExecutionSequence)) {
			// This is new information, so add it to the queue
			resultQueue.add(result);
		}

		// update last section index for the method
		currentThreadIndex.lastUpdatedMethod = methodID;
		currentMethodIndex.lastUpdatedSectionIndex = result.getIndexOfRangeBlock();
		currentMethodIndex.lastBlockExecutionSequence = new ArrayList<Integer>(blockExecutionSequence);
	}


	/**
	 * Add up all results for the finished section and send an update.
	 * The results for the section are removed by this method.
	 * @param resultQueue Result queue with the partial results for the section. This queue will be cleared with the update.
	 */
	private static void updateObserversWithSection(Queue<CountingResult> resultQueue) {
		if(resultQueue == null || resultQueue.isEmpty()) {
			throw new RuntimeException("Cannot update observers when result queue is empty.");
		}
		CountingResult resultSumForSection = resultQueue.remove();
		for(CountingResult r : resultQueue) {
			resultSumForSection.add(r);
		}
		MethodExecutionRecord lastMethodExecutionDetails = CountingResultCollector.getInstance().getLastMethodExecutionDetails();
		CountingResultIndexing.removeInternalCalls(lastMethodExecutionDetails, resultSumForSection);
		CountingResultSectionExecutionUpdate update = 
				new CountingResultSectionExecutionUpdate(resultSumForSection);
		resultQueue.clear();
		CountingResultCollector.getInstance().setChanged();
		CountingResultCollector.getInstance().notifyObservers(update);
	}

	/**
	 * Clears all results in the structure.
	 */
	public void clearResults() {
		this.indexForThread.clear();
	}

	/**
	 * Signal that no further updates for the method are to be expected.
	 * @param threadID {@link Thread#getId()} of the thread the method executed in.
	 * @param methodID {@link UUID} of the method in question.
	 */
	public void setMethodDone(final long threadID, final UUID methodID) {
		ThreadIndex threadIndex = this.indexForThread.get(threadID);
		if(threadIndex == null) {
			return;
		}
		MethodIndex methodIndex = threadIndex.methodIndexById.get(methodID);
		if(methodIndex == null) {
			return;
		}
		Queue<CountingResult> resultQueue = methodIndex.sectionUpdates;
		if(resultQueue == null) {
			return;
		}
		updateObserversWithSection(resultQueue);
		methodIndex.sectionUpdates.clear();
	}
}
