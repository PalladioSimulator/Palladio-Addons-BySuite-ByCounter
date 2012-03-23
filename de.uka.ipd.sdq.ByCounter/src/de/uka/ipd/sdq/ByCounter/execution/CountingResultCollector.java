package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.reporting.ICountingResultWriter;

/**
 * Class used to collect statistics about an instrumented method.
 * <p>
 * This class is observable ({@link #addObserver(java.util.Observer)}) and can 
 * provide online updates on the collection of results. Updates consist of 
 * update objects with types implementing the {@link ObservedEvent} interface.
 * The following update 
 * types are currently available:
 * <ul>
 * <li>{@link ObservedSectionExecutionUpdate}</li>
 * <li>{@link ObservedCompleteMethodExecutionUpdate}</li>
 * </ul>
 * </p>
 * TODO implement an "adaptation-oriented inlining", where after a certain (threshold) number of invocations, a method is inlined (callees independently, too)
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class CountingResultCollector extends Observable {
	
	/**
	 * This is the common interface of {@link CountingResultCollector} events 
	 * that can be observed.
	 * @author Martin Krogmann
	 */
	public interface ObservedEvent {}
	
	/**
	 * This class is used to update observers registered to 
	 * {@link CountingResultCollector} when a section has been executed.
	 * @author Martin Krogmann
	 */
	public class ObservedSectionExecutionUpdate implements ObservedEvent {
		public final Integer sectionIndex;
		public ObservedSectionExecutionUpdate(final Integer sectionIndex) {
			this.sectionIndex = sectionIndex;
		}
		@Override
		public String toString() {
			return "ObservedSectionExecutionUpdate[sectionIndex=" + sectionIndex +"]";
		}
	}
	
	/**
	 * This class is used to update observers registered to 
	 * {@link CountingResultCollector} when a complete method has been executed,
	 * i.e. reached a return statement or a throw statement.
	 * @author Martin Krogmann
	 */
	public class ObservedCompleteMethodExecutionUpdate implements ObservedEvent {
		
	}

	/** Default value for {@link #getMode()}. */
	public static final CountingResultCollectorMode MODE_DEFAULT = CountingResultCollectorMode.UseReportingMethodChoiceByInstrumentedMethods;

	/**
	 * Think about the singleton pattern here :-)
	 */
	private static CountingResultCollector instance = null;

	/**
	 * The bytecode parameter descriptor for
	 * {@link #protocolCount(ProtocolCountStructure)}.
	 */
	public static final String SIGNATURE_protocolCount = "(Lde/uka/ipd/sdq/ByCounter/execution/ProtocolCountStructure;)V";

	/**
	 * Public singleton accessor. Use this to get a reference
	 * to the singleton instance.
	 * @return The singleton instance of {@link CountingResultCollector}.
	 */
	public synchronized static CountingResultCollector getInstance() {
		if (instance == null) {
			instance = new CountingResultCollector();
		}
		return instance;
	}

	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private Logger log;
	
	/**
	 * The current result collector mode which switches forced inlining etc. on.
	 */
	private CountingResultCollectorMode mode;

	/**
	 * For usage related to the class CountingResultCollectorMonitor...
	 * TODO refactor this.
	 */
	private boolean monitorShouldStop;

	/**
	 * When a {@link CountingResult} is logged, all known writers will be
	 * asked to log (write) it as well. This mechanism is introduced to
	 * decouple {@link CountingResultCollector} from specific mechanisms and
	 * frameworks, such as CSV writing, JFreeChart creation etc.
	 */
	private List<ICountingResultWriter> resultWriters;

	/** Basic block and range block definitions. */
	public BlockDefinitionContext blockContext;
	
	/** Indexing infrastructure for counting results. */
	private CountingResultIndexing countingResultIndexing;
	
	/**
	 * Method execution details on how BytecodeCounters execute method was 
	 * last called.
	 */
	private MethodExecutionRecord lastMethodExecutionDetails;

	/** Forced inlining result collection strategy. */
	private AbstractCollectionStrategy inliningStrategyForced;

	/** Inlining result collection strategy for methods requesting inlining. */
	private AbstractCollectionStrategy inliningStrategyWished;

	/** Default result collection Strategy */
	private AbstractCollectionStrategy strategyDefault;
	
	/** List of all used result collection strategies */
	private List<AbstractCollectionStrategy> collectionStrategies;

	/**
	 * Private constructor that is invoked to create the singleton instance
	 */
	private CountingResultCollector() {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.mode = MODE_DEFAULT;
		this.resultWriters = new ArrayList<ICountingResultWriter>();
		this.blockContext = new BlockDefinitionContext();
		this.blockContext.tryToLoadBasicBlockSerialisation();
		this.countingResultIndexing = new CountingResultIndexing();
		
		this.collectionStrategies = new LinkedList<AbstractCollectionStrategy>();
		this.strategyDefault = new CollectionStrategyDefault(this);
		this.inliningStrategyForced = new CollectionStrategyForceInlining(this);
		this.inliningStrategyWished = new CollectionStrategyWishedInlining(this);
		this.collectionStrategies.add(this.strategyDefault);
		this.collectionStrategies.add(this.inliningStrategyWished);
		this.collectionStrategies.add(this.inliningStrategyForced);
	}

	/**
	 * Clear all results in the internal list.
	 */
	public synchronized void clearResults() {
		for(AbstractCollectionStrategy s : this.collectionStrategies) {
			s.clearResults();
		}
	}

	/**
	 * Gets all result writers registered to the collector.
	 * @return A list of {@link ICountingResultWriter}s.
	 */
	public List<ICountingResultWriter> getAllResultWriters() {
		return this.resultWriters;
	}

	/** 
	 * @see BytecodeCounter#setExecutionSettings(ExecutionSettings)
	 * @returnCurrent Counting mode.
	 */
	public CountingResultCollectorMode getMode() {
		return this.mode;
	}

	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 */
	public boolean getMonitorShouldStop() {
		return this.monitorShouldStop;
	}

	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 */
	public void monitorShouldStop() {
		this.setMonitorShouldStop(true);
	}

	/**
	 * An instrumented class calls this method to report the instruction and method call counts.
	 * TODO: how far is "synchronized" problematic in multi-threading?
	 * @param result The result reported by an instrumented method.
	 */
	public synchronized void protocolCount(ProtocolCountStructure result) {
		if(!(result instanceof ProtocolCountUpdateStructure)) {
			long reportingStart = System.nanoTime();//TODO make this configurable and clear, move to an interface/class that is accessed
			boolean handledResult = false;
			if(this.mode==CountingResultCollectorMode.DiscardAllIncomingCountingResults){
				log.fine("Discarding counting result of method "+result.qualifyingMethodName+", which started execution " +
						"at "+result.executionStart);
				handledResult = true;
			} else if(this.mode.getForceInliningPossible()){
				handledResult = this.inliningStrategyForced.protocolCount(result, reportingStart);
			}
			
			if(!handledResult) {
				// the result was not accepted by a strategy yet
				if(result.inliningSpecified) {
					this.inliningStrategyWished.protocolCount(result, reportingStart);
				} else {
					this.strategyDefault.protocolCount(result, reportingStart);
				}
			}
		}

		// notify observers
		this.setChanged();
		if(result instanceof ProtocolCountUpdateStructure) {
			// A section has been executed.
			final Integer lastExecutedSection = 
					result.rangeBlockExecutionSequence.get(
					result.rangeBlockExecutionSequence.size()-1);
			if(lastUpdatedSection != lastExecutedSection) {
				lastUpdatedSection = lastExecutedSection;
				this.notifyObservers(
					new ObservedSectionExecutionUpdate(lastExecutedSection));
			}
		} else {
			this.notifyObservers(
					new ObservedCompleteMethodExecutionUpdate());
		}
	}
	/**
	 * For updates on section execution this holds the last executed section 
	 * number.
	 */
	private Integer lastUpdatedSection;

	/**
	 * Adds an additional result writer used in {@link CountingResult#logResult(boolean, boolean, Level)}.
	 * @param resultWriter {@link ICountingResultWriter} used when logging result.
	 */
	public synchronized void registerWriter(ICountingResultWriter resultWriter){
		if(resultWriter==null){
			log.severe("Passed resultWriter is null, adding nonetheless");
		}
		this.resultWriters.add(resultWriter);
	}

	/**
	 * Get all results the {@link CountingResultCollector} holds.
	 * This does not clear the {@link CountingResultCollector} list.
	 * You have to explicitly
	 * call <code>clearResults()</code> if that is your intention.
	 * @return A <code>Result</code> list.
	 */
	public synchronized SortedSet<CountingResult> retrieveAllCountingResults() {
		SortedSet<CountingResult> ret = new TreeSet<CountingResult>();

		// add the results of all strategies
		for(AbstractCollectionStrategy s : this.collectionStrategies) {
			SortedSet<CountingResult> results = s.retrieveAllCountingResults();
			ret.addAll(results);
		}

		return ret;
	}
	
	/**
	 * @deprecated because only GUI-used but the GUI is outdated
	 * @param monitorShouldStop
	 */
	public void setMonitorShouldStop(boolean monitorShouldStop) {
		this.monitorShouldStop = monitorShouldStop;
	}
	
	/**
	 * This is called by {@link BytecodeCounter} when an execute method is 
	 * executed to provide the details of the execution to 
	 * {@link CountingResultCollector}.
	 * <p>
	 * Do not call; instead use {@link BytecodeCounter#setExecutionSettings(ExecutionSettings)}.
	 * </p>
	 * @param lastMethodExecutionDetails Method execution details.
	 */
	public void setLastMethodExecutionDetails(MethodExecutionRecord lastMethodExecutionDetails) {
		this.lastMethodExecutionDetails = lastMethodExecutionDetails;
		// set the counting mode
		this.mode = lastMethodExecutionDetails.executionSettings.getCountingResultCollectorMode();
	}

	/**
	 * The settings used for the last execution relevant to the 
	 * {@link CountingResultCollector}.
	 * <p>Do not call to change these settings.
	 * Instead, use {@link BytecodeCounter#setExecutionSettings(ExecutionSettings)}.
	 * </p>
	 * @see #setLastMethodExecutionDetails(MethodExecutionRecord)
	 * @return the lastMethodExecutionDetails
	 */
	public MethodExecutionRecord getLastMethodExecutionDetails() {
		return lastMethodExecutionDetails;
	}

	/** 
	 * @return Indexing infrastructure for counting results.
	 */
	public CountingResultIndexing getCountingResultIndexing() {
		return countingResultIndexing;
	}
}
