package de.uka.ipd.sdq.ByCounter.execution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;

/**
 * When online updates are enabled, this class holds updates to individual 
 * sections, until a new section is reported or the method ends.
 * @see InstrumentationParameters#getProvideOnlineSectionExecutionUpdates()
 * @author Martin Krogmann
 *
 */
public class CountingResultUpdateIndexing {
	/**
	 * The index of the section that was last added.
	 */
	private int lastUpdatedSectionIndex;
	
	/**
	 * Map that holds counting results by method.
	 */
	private Map<String, Queue<CountingResult>> sectionUpdatesByMethod;
	
	/**
	 * Construct the indexing structure.
	 */
	public CountingResultUpdateIndexing() {
		lastUpdatedSectionIndex = -1;
		sectionUpdatesByMethod = new HashMap<String, Queue<CountingResult>>();
	}


	/**
	 * This handles updates reported for individual section when online 
	 * updates are enabled.
	 * @param result The calculated counting result for the update.
	 */
	public void add(CountingResult result) {
		final String qualifyingMethodName = result.getQualifyingMethodName();
		Queue<CountingResult> resultQueue = sectionUpdatesByMethod.get(qualifyingMethodName);
		if(resultQueue == null) {
			// no entry for this method yet
			resultQueue = new LinkedList<CountingResult>();
			sectionUpdatesByMethod.put(qualifyingMethodName, resultQueue);
		}
		
		if(lastUpdatedSectionIndex >= 0 
				&& lastUpdatedSectionIndex != result.getIndexOfRangeBlock()) {
			// a new section is being executed
			updateObserversWithSection(resultQueue);
		}

		resultQueue.add(result);
		lastUpdatedSectionIndex = result.getIndexOfRangeBlock();
	}


	/**
	 * Add up all results for the finished section and send an update.
	 * The results for the section are removed by this method.
	 * @param resultQueue Result queue with the partial results for the section.
	 */
	private void updateObserversWithSection(Queue<CountingResult> resultQueue) {
		CountingResult resultSumForSection = resultQueue.remove();
		for(CountingResult r : resultQueue) {
			resultSumForSection.add(r);
		}
		CountingResultSectionExecutionUpdate update = 
				new CountingResultSectionExecutionUpdate(lastUpdatedSectionIndex,
															resultSumForSection);
		CountingResultCollector.getInstance().setChanged();
		CountingResultCollector.getInstance().notifyObservers(update);
	}

	/**
	 * Clears all results in the structure.
	 */
	public void clearResults() {
		this.lastUpdatedSectionIndex = -1;
		this.sectionUpdatesByMethod.clear();
	}

	/**
	 * @return The index of the section that was last added.
	 */
	public int getLastUpdatedSectionIndex() {
		return lastUpdatedSectionIndex;
	}

	/**
	 * Signal that no further updates for the method are to be expected.
	 * @param qualifyingMethodName Qualifying name of the method in question.
	 */
	public void setMethodDone(String qualifyingMethodName) {
		Queue<CountingResult> resultQueue = this.sectionUpdatesByMethod.get(qualifyingMethodName);
		if(resultQueue == null) {
			return;
		}
		updateObserversWithSection(resultQueue);
	}
}
