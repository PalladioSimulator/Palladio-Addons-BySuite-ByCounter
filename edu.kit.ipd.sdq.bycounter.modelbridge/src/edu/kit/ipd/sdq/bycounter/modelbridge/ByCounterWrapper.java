package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTArray;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTType;
import de.fzi.gast.variables.FormalParameter;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCompleteMethodExecutionUpdate;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultSectionExecutionUpdate;
import de.uka.ipd.sdq.ByCounter.execution.ExecutionSettings;
import de.uka.ipd.sdq.ByCounter.execution.InvalidQueryException;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationCounterPrecision;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.ArrayCreation;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.RequestResult;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;
import edu.kit.ipd.sdq.bycounter.modelbridge.util.TypeMapping;
import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.MethodCallCount;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;
import edu.kit.ipd.sdq.bycounter.output.UUID;

/**
 * Control ByCounter using EMF models.
 * This wrapper enables using EMF models as input and output for ByCounter. 
 * @author Martin Krogmann
 * @author groenda 
 *
 */
public class ByCounterWrapper {
	/**
	 * Class used to handle online updates by ByCounter.
	 * @author Martin Krogmann
	 */
	private final class UpdateObserver implements Observer {

		/**
		 * Receive an update
		 * @param crc {@link CountingResultCollector} that send the update.
		 * @param updateData Update data provided by crc.
		 */
		public void update(Observable crc, Object updateData) {
			if(updateData instanceof CountingResultSectionExecutionUpdate) {
				log.info("Notification received: " + updateData);
				CountingResult observation = ((CountingResultSectionExecutionUpdate)updateData).sectionResult;

				// convert result
				edu.kit.ipd.sdq.bycounter.output.CountingResult cr;
				cr = mapCountingResult(observation);
				cr.setResultCollection(currentRun);

				// for threaded results, parent/child relationships need to be mapped.
				CountingResult threadedCountingResult = observation;
				// is there a parent?
				CountingResult source = threadedCountingResult.getThreadedCountingResultSource();
				if(source != null) {
					edu.kit.ipd.sdq.bycounter.output.CountingResult tcrSource = 
							countingResultToEMFMap.get(source);
					cr.setThreadedCountingResult(tcrSource);
					tcrSource.getSpawnedThreadedCountingResults().add(cr);
				}
				// are there children?
				for(CountingResult spawn : threadedCountingResult.getSpawnedThreadedCountingResults()) {
					edu.kit.ipd.sdq.bycounter.output.CountingResult mappedSpawn = 
							countingResultToEMFMap.get(spawn);
					if(mappedSpawn != null) {
						cr.getSpawnedThreadedCountingResults().add(mappedSpawn);
					}
				}
				
				// add the result to the correct collection.
				if(observation.getRequestResult() == null) {
					currentRun.getCountingResults().add(cr);

				} else { // requestResult != null
					RequestResult requestResult = observation.getRequestResult();
					// convert request result
					edu.kit.ipd.sdq.bycounter.output.RequestResult req = 
							mapRequestResult(requestResult);
					req.setResultCollection(currentRun);
					currentRun.getRequestResults().add(req);
				}
				
			} else if(updateData instanceof CountingResultCompleteMethodExecutionUpdate) {
				// skip complete result
			} else {
				throw new IllegalArgumentException("Incorrect updateData type.");
			}
			
		}
	}

	/** Logger of this class. */
	private static final Logger log = Logger.getLogger(ByCounterWrapper.class.getCanonicalName());
	/** EMF Factory for the Output package. */
	private static final OutputFactory outputFactory = OutputFactory.eINSTANCE;

	/** Wrapped instance of BytecodeCounter. */
	private final BytecodeCounter bycounter;
	/** Current instrumentation configuration. */
	private InstrumentationProfile instrumentationProfile;
	/** Current execution configuration. */
	private ExecutionProfile executionProfile;
	/** List of available GAST Root Nodes. */
	private final LinkedList<Root> availableGastRootNodes;
	/**
	 * Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 */
	private Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentIdMap;
	/**
	 * Maps from {@link CountingResult} to the EMF element that was created for 
	 * it.
	 */
	private final Map<CountingResult, edu.kit.ipd.sdq.bycounter.output.CountingResult> countingResultToEMFMap;
	/** Object used to handle online updates by ByCounter. */
	private final UpdateObserver updateObserver;
	/** {@link ResultCollection} for the current ByCounter run. It is set on construction time. It is not set in 
	 * {@link #execute(Method, Object, Object[])} in order to allow external applications to listen on changes during execution.*/
	private ResultCollection currentRun;
	
	/**Initializes the wrapper.
	 */
	public ByCounterWrapper() {
		this.bycounter = new BytecodeCounter();
		this.currentRun = outputFactory.createResultCollection();
		this.updateObserver = new UpdateObserver();
		CountingResultCollector.getInstance().addObserver(updateObserver);
		this.instrumentationProfile = null;
		this.availableGastRootNodes = new LinkedList<Root>();
		this.countingResultToEMFMap = new HashMap<CountingResult, edu.kit.ipd.sdq.bycounter.output.CountingResult>();
	}

	/**Gets the current configuration for the instrumentation.
	 * @return The configuration.
	 */
	public InstrumentationProfile getInstrumentationProfile() {
		return instrumentationProfile;
	}
	
	/**
	 * Sets the current configuration for the instrumentation.
	 * The configuration is applied to the wrapped {@link BytecodeCounter} instance.
	 * @param input The configuration.
	 */
	public void setInstrumentationProfile(InstrumentationProfile input) {
		if (input == null) {
			throw new IllegalArgumentException("input must not be null.");
		}
		// create ByCounter instrumentation parameters
		InstrumentationParameters instrumentationParams = new InstrumentationParameters();
		this.handlePersistInstrumentedClasses(instrumentationParams, input);
		instrumentationParams.setInstrumentRecursively(input.isInstrumentRecursively()); 
		this.handleBasicBlocks(instrumentationParams, input);
		final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap = new HashMap<String, InstrumentedMethod>();
		this.entitiesToInstrumentIdMap = new HashMap<java.util.UUID, EntityToInstrument>();
		handleEntitiesToInstrument(instrumentationParams, 
				entitiesToInstrumentIdMap,
				methodNameInstrumentedMethodMap, input);
		instrumentationParams.setProvideOnlineSectionExecutionUpdates(true);
		instrumentationParams.setProvideJoinThreadsAbility(input.isProvideJoinThreadsAbility());
		instrumentationParams.setProvideOnlineSectionActiveUpdates(input.isProvideOnlineSectionActiveUpdates());
		instrumentationParams.setExternalToClassLoaderClassesDefinition(mapLogicalSet(input.getLogicalSetExternalToClassLoader()));
		instrumentationParams.setNumberCallGraphClassAnalyserThreads(input.getNumberCallGraphClassAnalyserThreads());

		// update instrumentation parameters and instrument
		this.instrumentationProfile = input;
		this.bycounter.setInstrumentationParams(instrumentationParams);
		this.bycounter.getInstrumentationParams().setInstrumentRecursively(true);
		this.bycounter.instrument();
	}
	
	/**
	 * @return The {@link ExecutionProfile} to use for executing
	 * instrumented code and collecting results produced by it.
	 */
	public ExecutionProfile getExecutionProfile() {
		return executionProfile;
	}

	/**
	 * @param executionProfile The {@link ExecutionProfile} to use for executing
	 * instrumented code and collecting results produced by it.
	 */
	public void setExecutionProfile(ExecutionProfile executionProfile) {
		if(executionProfile == null) {
			throw new IllegalArgumentException("Execution profile must not be null.");
		}
		
		ExecutionSettings executionSettings = new ExecutionSettings();
		executionSettings.setAddUpResultsRecursively(executionProfile.isAddUpResultsRecursively());
		executionSettings.setWaitForThreadsToFinnish(executionProfile.isWaitForThreadsToFinish());
		executionSettings.setInternalClassesDefinition(mapLogicalSet(executionProfile.getDefinedLogicalSets()));
		executionSettings.setOnlyAddCountsForMostRecentRegion(true);
		
		// update execution parameters
		this.executionProfile = executionProfile;
		this.bycounter.setExecutionSettings(executionSettings);
	}

	/**
	 * @param definedLogicalSets Definition of class sets in the instrumentation 
	 * profile.
	 * @return Set of Strings that ByCounter recognises as class patterns.
	 */
	private Set<String> mapLogicalSet(EList<LogicalSet> definedLogicalSets) {
		Set<String> bcSet = new HashSet<String>();
		for(LogicalSet lSet : definedLogicalSets) {
			for(GASTClass iClass : lSet.getInternalClasses()) {
				String s = iClass.getQualifiedName();
				bcSet.add(s);
			}
		}
		return bcSet;
	}

	/**Handles updates of the input model regarding all {@link EntityToInstrument}.
	 * @param instrumentationParams The ByCounter instrumentation parameters which are updated.
	 * @param entitiesToInstrumentMap Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 * @param entityToLineNumberMap Map which links each create ByCounter {@link LineNumberRange} with the corresponding {@link EntityToInstrument}. 
	 * @param instrumentationProfile The instrumentation profile with the new parameters.
	 */
	private void handleEntitiesToInstrument(
			InstrumentationParameters instrumentationParams,
			final Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentMap, 
			final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap,
			final InstrumentationProfile instrumentationProfile) {
		
		final List<de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument> entitiesToInstrument = new ArrayList<de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument>();
		EntityToInstrumentToByCounterSwitch entitySwitch = new EntityToInstrumentToByCounterSwitch(
				entitiesToInstrument, 
				entitiesToInstrumentMap,
				methodNameInstrumentedMethodMap, availableGastRootNodes);
		for(EntityToInstrument entity : instrumentationProfile.getEntitiesToInstrument()) {
			boolean success = entitySwitch.doSwitch(entity);
			if(!success) {
				String errorMsg = "Could not interpret the entity to instrument: " + entity;
				log.severe(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
		}
		instrumentationParams.getEntitiesToInstrument().clear();
		instrumentationParams.getEntitiesToInstrument().addAll(entitiesToInstrument);
	}

	/**Handles updates of the input model regarding Basic Blocks.
	 * @param instrumentationParams The ByCounter instrumentation parameters which are updated.
	 * @param instrumentationProfile The instrumentation profile with the new parameters.
	 */
	private void handleBasicBlocks(InstrumentationParameters instrumentationParams, final InstrumentationProfile instrumentationProfile) {
		instrumentationParams.setUseBasicBlocks(instrumentationProfile.isInstrumentUsingBasicBlocks());
		// set the counter precision specified in the input model
		if(instrumentationProfile.isInstrumentUsingLongCounters()) {
			instrumentationParams.setCounterPrecision(InstrumentationCounterPrecision.Long);
		} else {
			instrumentationParams.setCounterPrecision(InstrumentationCounterPrecision.Integer);
		}
		instrumentationParams.setTraceAndIdentifyRequests(instrumentationProfile.isTraceAndIdentifyRequests());
	}

	/**Handles updates of the input model property persistsInstrumentedClasses.
	 * @param instrumentationParams The ByCounter instrumentation parameters which are updated.
	 * @param instrumentationProfile The instrumentation profile with the new parameters.
	 */
	private void handlePersistInstrumentedClasses(InstrumentationParameters instrumentationParams, final InstrumentationProfile instrumentationProfile) {
		String outputClassDirectory = instrumentationProfile.getPersistInstrumentedClassesToOSPath();
		if(outputClassDirectory != null && outputClassDirectory.length() != 0) {
			instrumentationParams.setWriteClassesToDisk(true);
			instrumentationParams.setWriteClassesToDiskDirectory(new File(outputClassDirectory));
		} else {
			instrumentationParams.setWriteClassesToDisk(false);
		}
	}
	
	/**
	 * Construct a Java signature from the given {@link Method}.
	 * @param method Method to construct signature for.
	 * @return A {@link String} with a method signature suitable for using 
	 * {@link BytecodeCounter}.
	 */
	public static String constructSignature(Method method) {
		StringBuilder b = new StringBuilder();
		GASTType targetType = method.getReturnTypeDeclaration().getTargetType();
		if(targetType instanceof GASTArray) {
			GASTArray ar = (GASTArray) targetType;
			b.append(ar.getUndecoratedType().getQualifiedName()+"[]");
		} else {
			b.append(method.getReturnTypeDeclaration().getTargetType().getQualifiedName());
		}
		b.append(" ");
		b.append(method.getSimpleName());
		b.append("(");
		for(int i = 0; i < method.getFormalParameters().size(); i++) {
			FormalParameter p = method.getFormalParameters().get(i);
			b.append(p.getType().getQualifiedName());
			b.append(" " + p.getSimpleName());
			if(i + 1 < method.getFormalParameters().size()) {
				// separate parameters with a comma
				b.append(", ");
			}
		}
		b.append(")");
		
		return b.toString();
	}

	/**
	 * Uses ByCounter as setup using 
	 * {@link #setInstrumentationProfile(InstrumentationProfile)} to execute 
	 * the specified method. The observed results can be accessed via {@link #getResultCollection()}.
	 * @param m Method to execute.
	 * @param target Object on which the method is executed.
	 * @param params Arguments of the method to execute.
	 */
	public Object execute(Method m, Object target, Object[] params) {
		// check state
		if(this.instrumentationProfile == null) {
			log.warning("Execute: instrumentation profile is null. Is this usage of ByCounterWrapper.execute intended?");
		}
		// an execution profile should be specified
		if(this.executionProfile == null) {
			throw new IllegalStateException("ExecutionProfile must not be null when calling execute.");
		}
		// specify the method and execute
		MethodDescriptor methodToExecute = new MethodDescriptor(
				m.getSurroundingClass().getQualifiedName(),
				ByCounterWrapper.constructSignature(m));
		this.bycounter.getExecutionSettings().setAddUpResultsRecursively(this.instrumentationProfile.isInstrumentRecursively());
		return this.bycounter.execute(methodToExecute, target, params).returnValue;
	}
	
	/**Uses ByCounter to create an instance of the class containing the method.
	 * @param m Method within the class which is later on executed.
	 * @return Instance of the class.
	 */
	public Object instantiate(Method m) {
		// an instrumentation profile should be specified
		if(this.instrumentationProfile == null) {
			throw new IllegalStateException("InstrumentationProfile must not be null when calling execute.");
		}
		MethodDescriptor methodToExecute = new MethodDescriptor(
				m.getSurroundingClass().getQualifiedName(),
				ByCounterWrapper.constructSignature(m));
		return this.bycounter.instantiate(methodToExecute);
	}
	
	/**Provides a copy of all results stored in the {@link CountingResultCollector} singleton instance.
	 * @return Copy of results.
	 */
	public ResultCollection generateResult() {
		final de.uka.ipd.sdq.ByCounter.results.ResultCollection resultCollection = 
				CountingResultCollector.getInstance().retrieveAllCountingResults();
		ResultCollection copy = OutputFactory.eINSTANCE.createResultCollection();
		
		// convert request results
		final SortedSet<RequestResult> requestResults;
		requestResults = resultCollection.getRequestResults();
		for(RequestResult rr : requestResults) {
			edu.kit.ipd.sdq.bycounter.output.RequestResult req = mapRequestResult(rr);
			req.setResultCollection(copy);
			copy.getRequestResults().add(req);
		}
		
		// convert ordinary results including threaded counting results
		final SortedSet<CountingResult> results;
		results = resultCollection.getCountingResults();

		edu.kit.ipd.sdq.bycounter.output.CountingResult cr;
		for(CountingResult result : results)  { 
			cr = mapCountingResult(result);
			cr.setResultCollection(copy);
			copy.getCountingResults().add(cr);
		}
		
		return copy;
	}

	/**
	 * Maps from {@link RequestResult} to 
	 * {@link edu.kit.ipd.sdq.bycounter.output.RequestResult}.
	 * @param rr {@link RequestResult} to convert.
	 * @param entitiesToInstrumentMap2 Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to. 
	 * @return {@link edu.kit.ipd.sdq.bycounter.output.RequestResult} created 
	 * from rr.
	 */
	private edu.kit.ipd.sdq.bycounter.output.RequestResult mapRequestResult(
			RequestResult rr) {
		edu.kit.ipd.sdq.bycounter.output.RequestResult req = 
				outputFactory.createRequestResult();
		req.setId(mapUUID(rr.getRequestId()).toString());
		for(CountingResult cr : rr.getCountingResults()) {
			req.getCountingResults().add(mapCountingResult(cr));
		}
		return req;
	}
	
	/**
	 * @param uuid Java {@link UUID}.
	 * @return EMF {@link UUID}.
	 */
	private static UUID mapUUID(java.util.UUID uuid) {
		if(uuid == null) {
			return null;
		}
		UUID result = outputFactory.createUUID();
		result.setStringRepresentation(uuid.toString());
		return result;
	}

	/**
	 * Converts from the Java {@link CountingResult} to
	 * the EMF {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * @param cr {@link CountingResult} to convert.
	 * @param entitiesToInstrumentMap2 Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 * @return EMF {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 */
	private edu.kit.ipd.sdq.bycounter.output.CountingResult mapCountingResult(
			CountingResult cr) {
		edu.kit.ipd.sdq.bycounter.output.CountingResult result;
		result = outputFactory.createCountingResult();
		
		// map the properies of the base class
		result.getArrayCreationCounts().addAll(mapArrayCreationCounts(cr.getArrayCreationCounts()));
		result.setCallerId(mapUUID(cr.getCallerID()));
		result.getMethodCallCounts().addAll(mapMethodCallCounts(result, cr.getMethodCallCounts()));
		result.setMethodId(mapUUID(cr.getMethodExecutionID()));
		result.setMethodInvocationStartTime(cr.getMethodInvocationBeginning());
		result.getOpcodeCounts().addAll(mapOpcodeCounts(cr.getOpcodeCounts()));
		result.setObservedElement(entitiesToInstrumentIdMap.get(cr.getObservedElement().getId()));
		result.setQualifiedMethodName(cr.getQualifiedMethodName());
		result.setReportingTime(cr.getReportingTime());

		// map properties specific to threaded counting result
		result.setThreadId(cr.getThreadId());
		result.setFinal(cr.getFinal());
		for(CountingResult tcrr : cr.getSpawnedThreadedCountingResults()) {
			// map spawned result
			edu.kit.ipd.sdq.bycounter.output.CountingResult mappedTcrr = 
					mapCountingResult(tcrr);
			result.getSpawnedThreadedCountingResults().add(mappedTcrr);
			mappedTcrr.setThreadedCountingResult(result);
		}
		// map parent/source
		CountingResult tcrSource = cr.getThreadedCountingResultSource();
		if(tcrSource != null) {
			result.setThreadedCountingResult(this.countingResultToEMFMap.get(tcrSource));
		}
		
		// save mapping
		this.countingResultToEMFMap.put(cr, result);
	
		return result;
	}

	/**
	 * @param cr Reference to the containing CountingResult
	 * @param methodCallCounts Map from a qualified method name to a count.
	 * @return List of {@link MethodCallCount}s.
	 */
	private Collection<? extends MethodCallCount> mapMethodCallCounts(
			final edu.kit.ipd.sdq.bycounter.output.CountingResult cr, final SortedMap<String, Long> methodCallCounts) {
		List<MethodCallCount> result = new LinkedList<MethodCallCount>();
		for(Entry<String, Long> e : methodCallCounts.entrySet()) {
			MethodCallCount mcc = outputFactory.createMethodCallCount();
			mcc.setQualifiedFunctionName(e.getKey());
			mcc.setFunction(TypeMapping.findFunctionForString(e.getKey(), availableGastRootNodes));
			mcc.setCount(e.getValue());
			mcc.setCountingResult(cr);
			result.add(mcc);
		}
		return result;
	}
	

	/**
	 * @param opcodeCounts Opcode counts indexed by the opcode. Value is the count.
	 * @return List of opcode counts.
	 */
	private static Collection<? extends Long> mapOpcodeCounts(
			long[] opcodeCounts) {
		List<Long> result = new LinkedList<Long>();
		for(long v : opcodeCounts) {
			result.add(v);
		}
		return result;
	}

	/**
	 * @param arrayCreationCounts ByCounter {@link ArrayCreation}s mapped to a 
	 * count.
	 * @return List of {@link ArrayCreationCount}s.
	 */
	private static Collection<? extends ArrayCreationCount> mapArrayCreationCounts(
			Map<ArrayCreation, Long> arrayCreationCounts) {
		List<ArrayCreationCount> result = new LinkedList<ArrayCreationCount>();
		if(arrayCreationCounts != null) {
			for(Entry<ArrayCreation, Long> e : arrayCreationCounts.entrySet()) {
				ArrayCreationCount r = outputFactory.createArrayCreationCount();
				ArrayCreation arrayCreation = e.getKey();
				r.setArrayType(TypeMapping.mapArrayType(arrayCreation.getTypeOpcode()));
				r.setNumberOfDimensions(arrayCreation.getNumberOfDimensions());
				r.setTypeDescriptor(arrayCreation.getTypeDesc());
				r.setCount(e.getValue());
			}
		}
		return result;
	}
	
	/**
	 * @param threadId Id of the thread of the executed code.
	 * @return The {@link EntityToInstrument} that is currently being executed.
	 * Null otherwise. 
	 */
	public EntityToInstrument queryActiveSection(long threadId) {
		de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument activeSection;
		try {
			activeSection = CountingResultCollector.getInstance().queryActiveSection(threadId);
		} catch (InvalidQueryException e) {
			throw new RuntimeException(e);
		}
		if(activeSection == null) {
			return null;
		} else {
			return entitiesToInstrumentIdMap.get(activeSection.getId());
		}
	}

	/**Returns the current result collection.
	 * @return The result collection.
	 */
	public ResultCollection getResultCollection() {
		return currentRun;
	}
	
	/**Clears the result collection (singleton used by ByCounter as well as the EMF model).
	 */
	public void clearResultCollection() {
		currentRun = OutputFactory.eINSTANCE.createResultCollection();
		CountingResultCollector.getInstance().clearResults();
		this.countingResultToEMFMap.clear();
	}
	
	/**
	 * @return ByCounters {@link ClassLoader} used to load instrumented 
	 * classes.
	 */
	public ClassLoader getClassLoader() {
		return this.bycounter.getClassLoader();
	}
}
