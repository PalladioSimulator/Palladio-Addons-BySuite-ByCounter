package de.uka.ipd.sdq.ByCounter.execution;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Indexing infrastructure for {@link CountingResult}s.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 *
 */
public class CountingResultIndexing {
	/** Logging for this class. */
	private Logger log;

	/**
	 * retrieve the full counting artefact information by the beginning time
	 */
	private HashMap<Long,CountingArtefactInformation> countingInformationsByBeginning; //later, use SortedSet (after defining a comparator...)

	/**
	 * Retrieve all invocations of a method by its signature
	 */
	private HashMap<String, List<CountingArtefactInformation>> countingInformationsByMethodname; //later, use SortedSet (after defining a comparator...)

	/**
	 * Database replacement ;-)
	 * TODO does not consider forced inlining
	 */
	private HashMap<CountingArtefactInformation, CountingResult> countingResultsByArtefactInformation;

	public CountingResultIndexing() {
		this.log = Logger.getLogger(getClass().getCanonicalName());
		this.countingInformationsByBeginning = new HashMap<Long, CountingArtefactInformation>();
		this.countingInformationsByMethodname = new HashMap<String, List<CountingArtefactInformation>>();//TODO consider removing this...
		this.countingResultsByArtefactInformation = new HashMap<CountingArtefactInformation, CountingResult>();
	}
	
	public void clearResults() {
		this.countingInformationsByBeginning.clear();
		this.countingInformationsByMethodname.clear();
		this.countingResultsByArtefactInformation.clear();
	}

	/**
	 * Add to indexing infrastructure.
	 * @param res
	 * @param reportingStart
	 */
	public void add(CountingResult res,
			long reportingStart) {
		CountingArtefactInformation artefact;//does THIS create too much overhead? it requires ALL elements to be in memory?
		final String qualifyingMethodName = res.getQualifyingMethodName();
		final Long executionStart = res.getMethodInvocationBeginning();
		artefact = new CountingArtefactInformation(
				this,
				qualifyingMethodName,
				executionStart,
				null,//input parameters
				reportingStart,
				null //output parameters
				);

		this.countingInformationsByBeginning.put(executionStart, artefact);
		Set<String> keys = this.countingInformationsByMethodname.keySet();
		if(keys.contains(qualifyingMethodName)){
			this.countingInformationsByMethodname.get(qualifyingMethodName).add(artefact);
		}else{
			List<CountingArtefactInformation> list = new ArrayList<CountingArtefactInformation>();
			list.add(artefact);
			this.countingInformationsByMethodname.put(qualifyingMethodName, list);
		}
		this.countingResultsByArtefactInformation.put(artefact, res);
	}

	/**
	 * Gets the mapping of {@link CountingArtefactInformation} to {@link CountingResult}s.
	 * @return The mapping as {@link HashMap}.
	 */
	public HashMap<CountingArtefactInformation, CountingResult> getAllCountingResultsByArtefacts() {
		log.warning("getAllCountingResultsByArtefacts disregards inlined and force-inlined methods, " +
				"use retrieveAllCountingResults instead");
		return this.countingResultsByArtefactInformation;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?!
	 * @return A {@link HashMap}. The long value is the time as
	 * returned by System.nanoTime().
	 */
	public HashMap<Long, CountingArtefactInformation> getCountingArtefactsByBeginning() {
		log.warning("getCountingArtefactsByBeginning disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by method name.
	 * TODO does not consider forced inlining?
	 * @return A {@link HashMap}. The {@link String} is the method name.
	 */
	public HashMap<String, List<CountingArtefactInformation>> getCountingArtefactsByMethodname() {
		log.warning("getCountingArtefactsByMethodname disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByMethodname;
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by method name.
	 * TODO does not consider forced inlining?
	 * @param name The method name used to select the
	 * {@link CountingArtefactInformation} that is returned.
	 * @return The specified list of {@link CountingArtefactInformation}.
	 */
	public List<CountingArtefactInformation> getCountingArtefactsByName(String name){
		log.warning("getCountingArtefactsByName disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByMethodname.get(name);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param time A time as returned by System.nanoTime().
	 * @return The specified {@link CountingArtefactInformation}.
	 */
	public CountingArtefactInformation getCountingArtefactsByTime(long time){
		log.warning("getCountingArtefactsByTime disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning.get(time);
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param timestamp A time as {@link Timestamp}.
	 * @return The specified {@link CountingArtefactInformation}.
	 */
	public CountingArtefactInformation getCountingArtefactsByTimestamp(Timestamp timestamp){
		log.warning("getCountingArtefactsByTimestamp disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingInformationsByBeginning.get(timestamp.getTime());
	}

	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * Timestamp should be unique.
	 * TODO does not consider forced inlining?
	 * @param timestamp A time as {@link Timestamp}.
	 * @return The specified {@link CountingResult}.
	 */
	public CountingResult getCountingResultByMethodStartTimestamp(Timestamp timestamp){
		log.warning("getCountingResultByMethodStartTimestamp disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		return this.countingResultsByArtefactInformation.get(this.countingInformationsByBeginning.get(timestamp.getTime()));
	}


	/**
	 * Gets the {@link CountingArtefactInformation} by the time of
	 * method execution beginning.
	 * TODO does not consider forced inlining?
	 * @param time A time as returned by System.nanoTime().
	 * @return The specified {@link CountingResult}.
	 */
	public synchronized CountingResult retrieveCountingResultByMethodStartTime(long time){
		CountingArtefactInformation cai = this.countingInformationsByBeginning.get(time);
		if(cai==null){
			this.log.severe("No counting artefact information for starting time "+time);
			return null;
		}
		return this.countingResultsByArtefactInformation.get(cai);

	}

	/**TODO
	 * Gets a {@link CountingResult} that is the accumulation of all
	 *
	 * @param callerStartTime
	 * @param suppressDebugMessages
	 * @return The calculated {@link CountingResult}.
	 */
	public synchronized CountingResult retrieveCountingResultByStartTime_evaluateCallingTree(
			Long callerStartTime,
			boolean suppressDebugMessages){
		this.log.info("Evaluating calling tree for method start time "+callerStartTime);
		CountingResult candidateCountingResult;		// The currently considered counting result
		CountingArtefactInformation canditateCAI;	// corresponding CAI
		Long candidateStartTime;					// the start time of the considered result
		Long candidateReportingTime;				// the reporting time of the considered result

		Long callerReportingTime
			= this.countingInformationsByBeginning
				.get(callerStartTime)
					.getResultsReceivedByCollectorTime();
		this.log.fine("Corresponding caller reporting time: "+callerReportingTime);

		Set<Long> allKeys = this.countingInformationsByBeginning.keySet();
		// create a list of results sorted by start time
		ArrayList<Long> keysCopy = new ArrayList<Long>(allKeys);
		Collections.sort(keysCopy);
		// skip all result of methods executed before callerStartTime
		Iterator<Long> iter = keysCopy.iterator();
		candidateStartTime = iter.next();
		CountingResult totalCountingResult = this.retrieveCountingResultByMethodStartTime(callerStartTime);
		this.log.fine("Counting result before Type2 addition: "+totalCountingResult);

		while(candidateStartTime<callerStartTime){//ECHT kleiner!
			if(!suppressDebugMessages) this.log.fine("Ignoring time "+candidateStartTime+" because <"+callerStartTime);
			candidateStartTime=iter.next();
		}

		boolean firstIt = true;
		// now add the results of the methods executed before the current
		// result was reported.
		do {
			if(!firstIt) {
				candidateStartTime = iter.next();
			}
			firstIt = false;
			canditateCAI = this.countingInformationsByBeginning.get(candidateStartTime);
			candidateReportingTime = canditateCAI.getResultsReceivedByCollectorTime();
			if(!suppressDebugMessages) this.log.fine("Considering for addition: "+canditateCAI+"");
			if(!suppressDebugMessages){
				this.log.fine("Just for the record: trying to add " +
					"["+candidateStartTime+","+candidateReportingTime+"] to " +
					"["+callerStartTime+","+callerReportingTime+"].");
			}
			// candidate results were reported before the caller was
			// assume that the caller has called the candidate and add it
			if(candidateReportingTime.longValue()<callerReportingTime.longValue()){
				if(!suppressDebugMessages){
					this.log.fine("Adding callee counts of time "+candidateStartTime+
						" because its start >"+callerStartTime+
						" and because its reporting time " +
						"("+canditateCAI.getResultsReceivedByCollectorTime()+")< " +
						"caller reporting time ("+callerReportingTime+").");
				}
				candidateCountingResult = canditateCAI.getCountingResult();
				if(!suppressDebugMessages) this.log.fine("Added counting result: "+candidateCountingResult);
				totalCountingResult.add(candidateCountingResult);
				if(!suppressDebugMessages) this.log.fine("Intermediate total counting result: "+totalCountingResult);
			}else if(candidateReportingTime.longValue()>callerReportingTime.longValue()){
				if(!suppressDebugMessages) this.log.fine("Skipping callee counts of time "+candidateStartTime+
						" because, while its start time >"+callerStartTime+
						", its reporting time " +
						"("+canditateCAI.getResultsReceivedByCollectorTime()+")> " +
						"caller reporting time ("+callerReportingTime+").");
			}else if(candidateReportingTime.longValue()==callerReportingTime.longValue()){
				if(candidateStartTime.longValue()==callerStartTime.longValue()){
					if(!suppressDebugMessages) this.log.fine("Potential callee is the caller herself -> skipping");
				}else{
					if(!suppressDebugMessages) this.log.fine("A real callee that ends at the same instant " +
							"that the caller --> SKIPPING");
				}
			}else{
				this.log.severe("This should not happen :-)");//TODO document better...
			}
			//do something with the total counting result
//			candidateStartTime = iter.next();
		} while (candidateStartTime<callerReportingTime && iter.hasNext());
		if(!suppressDebugMessages) this.log.fine("Finished the active part");

		if(!suppressDebugMessages) {
			while(iter.hasNext()) {
				this.log.fine("Skipping callers with time "+iter.next());
			}
		}
		return totalCountingResult;
	}
	
	/**
	 * Gets the {@link CountingResult}s that exist for the given method name.
	 * @param name The name of a method measured by ByCounter.
	 * @return A {@link Set} of {@link CountingResult}s for the given name.
	 */
	public synchronized SortedSet<CountingResult> retrieveCountingResultsByMethodName(String name){
		log.warning("retrieveCountingResultsByMethodName disregards inlined and force-inlined methods, " +
			"use retrieveAllCountingResults instead");
		SortedSet<CountingResult> counts = new TreeSet<CountingResult>();
		Iterator<CountingArtefactInformation> iter = this.countingInformationsByMethodname.get(name).iterator();
		CountingArtefactInformation cai = null;
		CountingResult cr = null;
		for(;iter.hasNext();){
			cai = iter.next();
			cr = this.countingResultsByArtefactInformation.get(cai);
			if(cr!=null){
				counts.add(cr);
			}
		}
		return counts;
	}
}
