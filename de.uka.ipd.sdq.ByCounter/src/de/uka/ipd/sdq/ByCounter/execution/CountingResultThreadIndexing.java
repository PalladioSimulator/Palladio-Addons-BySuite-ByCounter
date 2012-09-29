package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;

/**
 * Indexing for the thread hierarchy of counting results.
 * @author Martin Krogmann
 */
public class CountingResultThreadIndexing {
	
	/**
	 * For each {@link ThreadedCountingResult} a list of thread ids for spawned threads.
	 */
	Map<ThreadedCountingResult, ArrayList<Long>> resultsSpawnedThreads;
	
	/**
	 * For a thread <i>t</i> with the key == id, this map contains the counting result for 
	 * the thread that spawned <i>t</i> if such a result exists.
	 */
	Map<Long, ThreadedCountingResult> spawningThreadForThreadId;
	
	/**
	 * For threads <i>t</i> with key == t.id, this map contains the entry
	 * (key, list_of_ts) if the thread could not be linked to a spawning thread.
	 */
	Map<Long, List<CountingResult>> rootThreadsByThreadId;
	
	/**
	 * Construct the empty infrastructure.
	 */
	public CountingResultThreadIndexing() {
		this.resultsSpawnedThreads = new HashMap<ThreadedCountingResult, ArrayList<Long>>();
		this.spawningThreadForThreadId = new HashMap<Long, ThreadedCountingResult>();
		this.rootThreadsByThreadId = new HashMap<Long, List<CountingResult>>();
	}

	/**
	 * @param res Partial counting result. This assumes that result that have 
	 * spawned threads are of the type {@link ThreadedCountingResult}.
	 * @param spawnedThreadsIds Threads spawned by the result elements thread.
	 */
	public CountingResult apply(final CountingResult res, final ArrayList<Long> spawnedThreadsIds) {
		CountingResult result = res;
		
		if(spawnedThreadsIds != null && !spawnedThreadsIds.isEmpty()) {
			// when spawning threads, the CountingResult should already be of 
			// type ThreadedCountingResult.
			final ThreadedCountingResult tcr = getThreadedCountingResult(result);
			result = tcr;
			
			final ArrayList<Long> threadSpawns = interpretSpawnedList(spawnedThreadsIds,
					result);
			// look for threads that could have been spawned by this result
			for(long threadId : threadSpawns) {
				List<CountingResult> spawnedThreads = rootThreadsByThreadId.get(threadId);
				if(spawnedThreads != null) {
					for(CountingResult stResult : spawnedThreads) {
						ThreadedCountingResult threadedCountingResult = getThreadedCountingResult(stResult);
						tcr.getSpawnedThreadedCountingResults().add(threadedCountingResult);
						threadedCountingResult.setThreadedCountingResultSource(tcr);
						// since stResult is no longer a root node, remove it from the collection
						stResult.getResultCollection().getCountingResults().remove(stResult);
						stResult.getResultCollection().getRequestResults().remove(stResult);
					}
					spawnedThreads.clear();
				}
			}
			this.resultsSpawnedThreads.put(tcr, threadSpawns);

			// for each spawned thread id, save this result as the spawning thread
			for(long id : threadSpawns) {
				this.spawningThreadForThreadId.put(id, tcr);
			}
		}


		ThreadedCountingResult spawningThread = spawningThreadForThreadId.get(res.getThreadId());
		if(spawningThread != null) {
			ThreadedCountingResult tcr = getThreadedCountingResult(res);
			result = tcr;
			tcr.setThreadedCountingResultSource(spawningThread);
			spawningThread.getSpawnedThreadedCountingResults().add(tcr);
		} else {
			// add result to root nodes; maybe a parent is found later
			long threadId = result.getThreadId();
			List<CountingResult> resultList = this.rootThreadsByThreadId.get(threadId);
			if(resultList == null) {
				resultList = new LinkedList<CountingResult>();
				this.rootThreadsByThreadId.put(threadId, resultList);
			}
			resultList.add(result);
		}
		return result;
	}

	/**
	 * Since the list of spawned threads can also contain information on the 
	 * source section, this method correctly interprets the available 
	 * information.
	 * @param spawnedThreadsIds List with thread spawning information.
	 * @param result The relevant counting result.
	 * @return List that only contains thread ids.
	 */
	private ArrayList<Long> interpretSpawnedList(final ArrayList<Long> spawnedThreadsIds,
			CountingResult result) {
		final int indexOfRangeBlock = result.getIndexOfRangeBlock();
		final ArrayList<Long> threadSpawns = new ArrayList<Long>();
		if(indexOfRangeBlock >= 0) {
			// using range blocks
			// the list contains threadId, srcSectionNumber, ... (alternating)
			for(int i = 0; i < spawnedThreadsIds.size()/2; i++) {
				long threadId = spawnedThreadsIds.get(2*i);
				long srcSectionNumber = spawnedThreadsIds.get(2*i+1);
				if(result.getIndexOfRangeBlock() == srcSectionNumber) {
					threadSpawns.add(threadId);
				}
			}
		} else {
			// not using sections
			// only threadIds in the list
			for(long threadId : spawnedThreadsIds) {
				threadSpawns.add(threadId);
			}
		}
		return threadSpawns;
	}
	
	/**
	 * This method checks if the given result is of the type 
	 * {@link ThreadedCountingResult}. If that is the case, the type casted 
	 * object is returned. Otherwise a new {@link ThreadedCountingResult} is 
	 * created for the result.
	 * @param result The result to possibly convert.
	 * @return For the given counting result a threaded counting result.
	 */
	private ThreadedCountingResult getThreadedCountingResult(final CountingResult result) {
		if(!(result instanceof ThreadedCountingResult)) {
			return new ThreadedCountingResult(result);
		} else { 
			return (ThreadedCountingResult) result;
		}
	}

	/**
	 * Remove all results from the indexing infrastructure.
	 */
	public void clearResults() {
		this.resultsSpawnedThreads.clear();
		this.spawningThreadForThreadId.clear();
		this.rootThreadsByThreadId.clear();
	}
}
