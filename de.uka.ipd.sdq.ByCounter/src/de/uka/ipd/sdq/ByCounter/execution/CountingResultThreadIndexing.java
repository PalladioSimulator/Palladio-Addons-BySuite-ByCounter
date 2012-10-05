package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.UUID;

import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;

/**
 * Indexing for the thread hierarchy of counting results.
 * @author Martin Krogmann
 */
public class CountingResultThreadIndexing {
	/**
	 * Class used to correctly hash incomplete results.
	 * @author Martin Krogmann
	 *
	 */
	private static class ResultHash {
		private UUID ownID;
		private UUID observedID;

		public ResultHash(CountingResult r) {
			this.ownID = r.getMethodExecutionID();
			this.observedID = r.getObservedElement().getId();
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((this.observedID == null) ? 0 : this.observedID
							.hashCode());
			result = prime * result
					+ ((this.ownID == null) ? 0 : this.ownID.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			ResultHash other = (ResultHash) obj;
			if (this.observedID == null) {
				if (other.observedID != null)
					return false;
			} else if (!this.observedID.equals(other.observedID))
				return false;
			if (this.ownID == null) {
				if (other.ownID != null)
					return false;
			} else if (!this.ownID.equals(other.ownID))
				return false;
			return true;
		}
		
	}
	
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
	 * All incomplete results reported when spawning threads are gathered here.
	 */
	Map<ResultHash, ThreadedCountingResult> incompleteResults;
	
	/**
	 * Construct the empty infrastructure.
	 */
	public CountingResultThreadIndexing() {
		this.resultsSpawnedThreads = new HashMap<ThreadedCountingResult, ArrayList<Long>>();
		this.spawningThreadForThreadId = new HashMap<Long, ThreadedCountingResult>();
		this.rootThreadsByThreadId = new HashMap<Long, List<CountingResult>>();
		this.incompleteResults = new HashMap<ResultHash, ThreadedCountingResult>();
	}

	/**
	 * @param res Partial counting result. This assumes that result that have 
	 * spawned threads are of the type {@link ThreadedCountingResult}.
	 * @param spawnedThreadsIds Threads spawned by the result elements thread.
	 */
	public CountingResult apply(final CountingResult res, final ArrayList<Long> spawnedThreadsIds) {
		CountingResult result = res;
		ThreadedCountingResult tcr = getThreadedCountingResult(result);
		boolean existingResultStubFound = false;
		
		// use existing stubs if possible to ensure object equality
		ThreadedCountingResult resultStub = this.incompleteResults.get(new ResultHash(result));
		if(resultStub != null) {
			existingResultStubFound = true;
			tcr = resultStub;
			result = resultStub;
			if(res.getFinal() == true) {
				// copy from complete result
				SortedSet<ThreadedCountingResult> savedSpawns = resultStub.getSpawnedThreadedCountingResults();
				ThreadedCountingResult savedSource = resultStub.getThreadedCountingResultSource();
				resultStub.set(res);	// use res instead of result because result == resultStaub!
				resultStub.setSpawnedThreadedCountingResults(savedSpawns);
				resultStub.setThreadedCountingResultSource(savedSource);
			}
		} else if(res.getFinal() == false) {
			this.incompleteResults.put(new ResultHash(tcr), tcr);
		}

		// handle child threads
		if(spawnedThreadsIds != null && !spawnedThreadsIds.isEmpty()) {
			final ArrayList<Long> threadSpawns = interpretSpawnedList(spawnedThreadsIds,
					result);
			// for each spawned thread id, save this result as the spawning thread
			for(final long id : threadSpawns) {
				if(!this.spawningThreadForThreadId.containsKey(id)) {
					this.spawningThreadForThreadId.put(id, tcr);
				}
			}
		}

		// handle parent threads
		ThreadedCountingResult spawningThread = spawningThreadForThreadId.get(result.getThreadId());
		if(spawningThread != null) {
			result = tcr;
			tcr.setThreadedCountingResultSource(spawningThread);
			spawningThread.getSpawnedThreadedCountingResults().add(tcr);
		} else if(!existingResultStubFound) {
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
	private ArrayList<Long> interpretSpawnedList(
			final ArrayList<Long> spawnedThreadsIds,
			final CountingResult result) {
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
		this.incompleteResults.clear();
	}
}
