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
	 * The index of the section that was last added for the method.
	 */
	private Map<UUID, Integer> lastUpdatedSectionIndex;
	
	/**
	 * Map that holds counting results by method execution id.
	 */
	private Map<UUID, Queue<CountingResult>> sectionUpdatesByMethod;
	
	/** The method for which the last update was provided. */
	private UUID lastUpdatedMethod;
	
	/** The last basic block execution sequence of a result. */
	private Map<UUID, List<Integer>> lastBlockExecutionSequenceByMethod;
	
	/**
	 * Construct the indexing structure.
	 */
	public CountingResultUpdateIndexing() {
		lastUpdatedSectionIndex = new HashMap<UUID, Integer>();
		sectionUpdatesByMethod = new HashMap<UUID, Queue<CountingResult>>();
		lastUpdatedMethod = null;
		lastBlockExecutionSequenceByMethod = new HashMap<UUID, List<Integer>>();
	}


	/**
	 * This handles updates reported for individual section when online 
	 * updates are enabled.
	 * @param result The calculated counting result for the update.
	 * @param list Basic block execution sequence used to 
	 * see if consecutive results contain new information.
	 */
	public void add(CountingResult result, List<Integer> list) {
		final UUID methodID = result.getMethodExecutionID();
		if(lastUpdatedMethod != null && !methodID.equals(lastUpdatedMethod)) {
			// we entered a new method
			// provide an update for the previous method
			setMethodDone(lastUpdatedMethod);
		}
		
		Queue<CountingResult> resultQueue = sectionUpdatesByMethod.get(methodID);
		if(resultQueue == null) {
			// no entry for this method yet
			resultQueue = new LinkedList<CountingResult>();
			sectionUpdatesByMethod.put(methodID, resultQueue);
			// initialise last section index
			lastUpdatedSectionIndex.put(methodID, -1);
		}
		Integer luSectionIndex = lastUpdatedSectionIndex.get(methodID);
		
		if(luSectionIndex >= 0 
				&& luSectionIndex != result.getIndexOfRangeBlock()) {
			// a new section is being executed
			updateObserversWithSection(resultQueue);
		}
		
		if(lastBlockExecutionSequenceByMethod.get(methodID) == null
				|| list != null
				&& !lastBlockExecutionSequenceByMethod.get(methodID).equals(list)) {
			// This is new information, so add it to the queue
			resultQueue.add(result);
		}

		// update last section index for the method
		lastUpdatedMethod = methodID;
		lastUpdatedSectionIndex.put(methodID, result.getIndexOfRangeBlock());
		lastBlockExecutionSequenceByMethod.put(methodID, new ArrayList<Integer>(list));
	}


	/**
	 * Add up all results for the finished section and send an update.
	 * The results for the section are removed by this method.
	 * @param resultQueue Result queue with the partial results for the section.
	 */
	private static void updateObserversWithSection(Queue<CountingResult> resultQueue) {
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
		this.lastUpdatedSectionIndex.clear();
		this.sectionUpdatesByMethod.clear();
		this.lastUpdatedMethod = null;
	}

	/**
	 * Signal that no further updates for the method are to be expected.
	 * @param methodID {@link UUID} of the method in question.
	 */
	public void setMethodDone(UUID methodID) {
		Queue<CountingResult> resultQueue = this.sectionUpdatesByMethod.get(methodID);
		if(resultQueue == null) {
			return;
		}
		updateObserversWithSection(resultQueue);
		this.sectionUpdatesByMethod.remove(lastUpdatedMethod);
	}
}
