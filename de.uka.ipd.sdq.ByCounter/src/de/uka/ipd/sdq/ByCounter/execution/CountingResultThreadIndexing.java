package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;

/**
 * Indexing for the thread hierarchy of counting results.
 * @author Martin Krogmann
 */
public class CountingResultThreadIndexing {
	
	/**
	 * For each {@link CountingResult} a list of thread ids for spawned threads.
	 */
	Map<CountingResult, ArrayList<Long>> resultsSpawnedThreads;
	
	/**
	 * Construct the empty infrastructure.
	 */
	public CountingResultThreadIndexing() {
		this.resultsSpawnedThreads = new HashMap<CountingResult, ArrayList<Long>>();
	}

	/**
	 * @param res Partial counting result.
	 * @param spawnedThreads Threads spawned by the result elements thread.
	 */
	public void add(final CountingResult res, final ArrayList<Long> spawnedThreads) {
		final int indexOfRangeBlock = res.getIndexOfRangeBlock();
		final ArrayList<Long> threadSpawns = new ArrayList<Long>();
		if(indexOfRangeBlock >= 0) {
			// using range blocks
			// the list contains threadId, srcSectionNumber, ... (alternating)
			for(int i = 0; i < spawnedThreads.size()/2; i++) {
				long threadId = spawnedThreads.get(2*i);
				long srcSectionNumber = spawnedThreads.get(2*i+1);
				if(res.getIndexOfRangeBlock() == srcSectionNumber) {
					threadSpawns.add(threadId);
				}
			}
		} else {
			// not using sections
			// only threadIds in the list
			for(long threadId : spawnedThreads) {
				threadSpawns.add(threadId);
			}
		}
		this.resultsSpawnedThreads.put(res, threadSpawns);
	}

	/**
	 * Remove all results from the indexing infrastructure.
	 */
	public void clearResults() {
		this.resultsSpawnedThreads.clear();
	}

	/**
	 * Structure the results. Results with no parent, i.e. 
	 * <code>{@link ThreadedCountingResult#getThreadedCountingResultSource()} == null</code>
	 * are in the set. All other results are part of the tree structure.
	 * @param countingResults The set of {@link CountingResult}s that is 
	 * analysed for a threaded structure.
	 * @see ThreadedCountingResult#getSpawnedThreadedCountingResults()
	 */
	public void applyThreadStructure(SortedSet<CountingResult> countingResults) {
		List<CountingResult> removedResults = new LinkedList<CountingResult>();
		List<CountingResult> addedResults = new LinkedList<CountingResult>();
		for(CountingResult r : countingResults) {
			ArrayList<Long> spawnedThreads = this.resultsSpawnedThreads.get(r);
			if(spawnedThreads == null || spawnedThreads.isEmpty()) {
				continue;
			}
			// we have spawned threads
			ThreadedCountingResult tcr = new ThreadedCountingResult(r);//((ThreadedCountingResult)r);
			tcr.setThreadId(r.threadId);
			// find and add them
			for(CountingResult rInner : countingResults) {
				if(spawnedThreads.contains(rInner.getThreadId())) {
					ThreadedCountingResult tcrInner = new ThreadedCountingResult(rInner);
					tcrInner.setThreadId(rInner.getThreadId());
					tcr.getSpawnedThreadedCountingResults().add(tcrInner);
					tcrInner.setThreadedCountingResultSource(tcr);
					removedResults.add(rInner);
				}
			}
			// replace the CountingResult with a ThreadedCountingResult
			removedResults.add(r);
			addedResults.add(tcr);
		}
		countingResults.removeAll(removedResults);
		countingResults.addAll(addedResults);
	}
}
