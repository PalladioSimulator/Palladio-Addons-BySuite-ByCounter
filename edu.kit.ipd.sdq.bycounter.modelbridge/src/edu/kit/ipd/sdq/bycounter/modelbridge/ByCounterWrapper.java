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
import de.fzi.gast.types.GASTClass;
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
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;
import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;
import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.ArrayType;
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
				
				if(observation.getRequestResult() == null) {
					// convert ordinary result
					edu.kit.ipd.sdq.bycounter.output.CountingResult cr;
					cr = mapCountingResult(observation, entitiesToInstrumentIdMap);
					cr.setResultCollection(currentRun);
					currentRun.getCountingResults().add(cr);

				} else { // requestResult != null
					RequestResult requestResult = observation.getRequestResult();
					// convert request result
					edu.kit.ipd.sdq.bycounter.output.RequestResult req = 
							mapRequestResult(requestResult, entitiesToInstrumentIdMap);
					req.setResultCollection(currentRun);
					currentRun.getRequestResults().add(req);
				}				
				
			} else if(updateData instanceof CountingResultCompleteMethodExecutionUpdate) {
				// skip complete result
			}else {
				throw new IllegalArgumentException("Incorrect updateData type.");
			}
			
		}
	}

	/** Logger of this class. */
	private static final Logger log = Logger.getLogger(ByCounterWrapper.class.getCanonicalName());
	/** EMF Factory for the Output package. */
	private static final OutputFactory outputFactory = OutputFactory.eINSTANCE;

	/** Wrapped instance of BytecodeCounter. */
	private BytecodeCounter bycounter;
	/** Current instrumentation configuration. */
	private InstrumentationProfile instrumentationProfile;
	/** Current execution configuration. */
	private ExecutionProfile executionProfile;
	/** List of available GAST Root Nodes. */
	private final LinkedList<Root> availableGastRootNodes;
	/** A map that maps the string representation of simple java 
	 * types in the GAST to the JavaTypeEnum. */
	private static Map<String, JavaTypeEnum> gastTypeJavaTypeMap;
	/**
	 * Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 */
	private Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentIdMap;
	/** Object used to handle online updates by ByCounter. */
	private UpdateObserver updateObserver;
	/** {@link ResultCollection} for the current ByCounter run. It is set on construction time. It is not set in 
	 * {@link #execute(Method, Object, Object[])} in order to allow external applications to listen on changes during execution.*/
	private ResultCollection currentRun;
	
	static {
		// initialize the gastTypeJavaTypeMap
		gastTypeJavaTypeMap = new HashMap<String, JavaTypeEnum>();
		gastTypeJavaTypeMap.put("boolean", JavaTypeEnum.Boolean);
		gastTypeJavaTypeMap.put("byte", JavaTypeEnum.Byte);
		gastTypeJavaTypeMap.put("char", JavaTypeEnum.Char);
		gastTypeJavaTypeMap.put("double", JavaTypeEnum.Double);
		gastTypeJavaTypeMap.put("float", JavaTypeEnum.Float);
		gastTypeJavaTypeMap.put("int", JavaTypeEnum.Int);
		gastTypeJavaTypeMap.put("long", JavaTypeEnum.Long);
		gastTypeJavaTypeMap.put("short", JavaTypeEnum.Short);
		gastTypeJavaTypeMap.put("void", JavaTypeEnum.Void);
	}

	/**Initializes the wrapper.
	 */
	public ByCounterWrapper() {
		this.bycounter = new BytecodeCounter();
		this.currentRun = outputFactory.createResultCollection();
		this.updateObserver = new UpdateObserver();
		CountingResultCollector.getInstance().addObserver(updateObserver);
		this.instrumentationProfile = null;
		this.availableGastRootNodes = new LinkedList<Root>();
	}

	/**Gets the current configuration for the instrumentation.
	 * @return The configuration.
	 */
	public InstrumentationProfile getInstrumentationConfiguration() {
		return instrumentationProfile;
	}
	
	/**
	 * Sets the current configuration for the instrumentation.
	 * The configuration is applied to the wrapped {@link BytecodeCounter} instance.
	 * @param input The configuration.
	 */
	public void setInstrumentationConfiguration(InstrumentationProfile input) {
		if (input == null) {
			throw new IllegalArgumentException("input must not be null.");
		}
		// create ByCounter instrumentation parameters
		InstrumentationParameters instrumentationParams = new InstrumentationParameters();
		this.handlePersistInstrumentedClasses(instrumentationParams, input);
		input.isAggregateInternalCallsTransparently(); // TODO: implement aggregateInterncalCallsTransparently
		input.getDefinedLogicalSets(); // TODO: implement handling definedLogicalSets
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

		// update instrumentation parameters and instrument
		this.instrumentationProfile = input;
		this.bycounter.setInstrumentationParams(instrumentationParams);
		this.bycounter.instrument();
	}
	
	public ExecutionProfile getExecutionProfile() {
		return executionProfile;
	}
	
	public void setExecutionProfile(ExecutionProfile executionProfile) {
		if(executionProfile == null) {
			throw new IllegalArgumentException("Execution profile must not be null.");
		}
		
		ExecutionSettings executionSettings = new ExecutionSettings();
		executionSettings.setAddUpResultsRecursively(executionProfile.isAddUpResultsRecursively());
		executionSettings.setWaitForThreadsToFinnish(executionProfile.isWaitForThreadsToFinnish());
		handleInternalClassesDefinition(executionSettings, this.instrumentationProfile.getDefinedLogicalSets());
		
		// update execution parameters
		this.executionProfile = executionProfile;
		this.bycounter.setExecutionSettings(executionSettings);
	}

	/**
	 * @param executionSettings ByCounters {@link ExecutionSettings}.
	 * @param definedLogicalSets Definition of class sets in the instrumentation 
	 * profile.
	 */
	private void handleInternalClassesDefinition(ExecutionSettings executionSettings,
			EList<LogicalSet> definedLogicalSets) {
		Set<String> bcSet = new HashSet<String>();
		for(LogicalSet lSet : definedLogicalSets) {
			for(GASTClass iClass : lSet.getInternalClasses()) {
				String s = iClass.getQualifiedName();
				bcSet.add(s);
			}
		}
		executionSettings.setInternalClassesDefinition(bcSet);
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
		b.append(method.getReturnTypeDeclaration().getTargetType().getQualifiedName());
		b.append(" ");
		b.append(method.getSimpleName());
		b.append("(");
		for(FormalParameter p : method.getFormalParameters()) {
			b.append(p.getType().getQualifiedName());
			b.append(" " + p.getSimpleName());
		}
		b.append(")");
		
		return b.toString();
	}

	/**
	 * Uses ByCounter as setup using 
	 * {@link #setInstrumentationConfiguration(InstrumentationProfile)} to execute 
	 * the specified method. The observed results can be accessed via {@link #getResultCollection()}.
	 * @param m Method to execute.
	 * @param target Object on which the method is executed.
	 * @param params Arguments of the method to execute.
	 */
	public Object execute(Method m, Object target, Object[] params) {
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
			edu.kit.ipd.sdq.bycounter.output.RequestResult req = mapRequestResult(rr, entitiesToInstrumentIdMap);
			req.setResultCollection(copy);
			copy.getRequestResults().add(req);
		}
		
		// convert ordinary results including threaded counting results
		final SortedSet<CountingResult> results;
		results = resultCollection.getCountingResults();

		edu.kit.ipd.sdq.bycounter.output.CountingResult cr;
		for(CountingResult result : results)  { 
			cr = mapCountingResult(result, entitiesToInstrumentIdMap);
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
	private static edu.kit.ipd.sdq.bycounter.output.RequestResult mapRequestResult(
			RequestResult rr, Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentMap2) {
		edu.kit.ipd.sdq.bycounter.output.RequestResult req = 
				outputFactory.createRequestResult();
		req.setId(mapUUID(rr.getRequestId()).toString());
		for(CountingResult cr : rr.getCountingResults()) {
			req.getCountingResults().add(mapCountingResult(cr, entitiesToInstrumentMap2));
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
	private static edu.kit.ipd.sdq.bycounter.output.CountingResult mapCountingResult(
			CountingResult cr, Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentMap2) {
		edu.kit.ipd.sdq.bycounter.output.CountingResult result;
		if(cr instanceof ThreadedCountingResult) {
			final ThreadedCountingResult tcr = (ThreadedCountingResult)cr;
			edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult tResult = 
					outputFactory.createThreadedCountingResult();
			// map properties specific to threaded counting result
			tResult.setThreadId(tcr.getThreadId());
			for(ThreadedCountingResult tcrr : tcr.getSpawnedThreadedCountingResults()) {
				// map spawned result
				edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult mappedTcrr = 
						(edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult)mapCountingResult(tcrr, entitiesToInstrumentMap2);
				tResult.getSpawnedThreadedCountingResults().add(mappedTcrr);
				mappedTcrr.setThreadedCountingResult(tResult);
			}
			result = tResult;
		} else {
			result = outputFactory.createCountingResult();
		}
		// map the properies of the base class
		result.getArrayCreationCounts().addAll(mapArrayCreationCounts(cr.getArrayCreationCounts()));
		result.setCallerId(mapUUID(cr.getCallerID()));
		result.getMethodCallCounts().addAll(mapMethodCallCounts(cr.getMethodCallCounts()));
		result.setMethodId(mapUUID(cr.getMethodID()));
		result.setMethodInvocationStartTime(cr.getMethodInvocationBeginning());
		result.getOpcodeCounts().addAll(mapOpcodeCounts(cr.getOpcodeCounts()));
		result.setObservedElement(mapEntityToInstrument(cr.getObservedElement(), entitiesToInstrumentMap2));
		result.setQualifiedMethodName(cr.getQualifiedMethodName());
		result.setReportingTime(cr.getReportingTime());
	
		return result;
	}

	/**
	 * Converts ByCounters {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument}
	 * to it's EMF model counterpart.
	 * @param entityToInstrument Element that was instrumented to produce the result.
	 * @param entitiesToInstrumentMap2 Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 * @return EMF {@link EntityToInstrument}.
	 */
	private static EntityToInstrument mapEntityToInstrument(de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument entityToInstrument, Map<java.util.UUID, EntityToInstrument> entitiesToInstrumentMap2) {
		java.util.UUID id = entityToInstrument.getId();
		return entitiesToInstrumentMap2.get(id);
	}

	/**
	 * @param methodCallCounts Map from a qualified method name to a count.
	 * @return List of {@link MethodCallCount}s.
	 */
	private static Collection<? extends MethodCallCount> mapMethodCallCounts(
			SortedMap<String, Long> methodCallCounts) {
		List<MethodCallCount> result = new LinkedList<MethodCallCount>();
		for(Entry<String, Long> e : methodCallCounts.entrySet()) {
			MethodCallCount mcc = outputFactory.createMethodCallCount();
			mcc.setQualifiedFunctionName(e.getKey());
			mcc.setCount(e.getValue());
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
				r.setArrayType(mapArrayType(arrayCreation.getTypeOpcode()));
				r.setNumberOfDimensions(arrayCreation.getNumberOfDimensions());
				r.setTypeDescriptor(arrayCreation.getTypeDesc());
				r.setCount(e.getValue());
			}
		}
		return result;
	}

	// Field descriptor #263 I
	private static final int T_BOOLEAN = 4;
	  
	// Field descriptor #263 I
	private static final int T_CHAR = 5;
	  
	// Field descriptor #263 I
	private static final int T_FLOAT = 6;
	 
	// Field descriptor #263 I
	private static final int T_DOUBLE = 7;
	 
	// Field descriptor #263 I
	private static final int T_BYTE = 8;
	  
	// Field descriptor #263 I
	private static final int T_SHORT = 9;
	  
	// Field descriptor #263 I
	private static final int T_INT = 10;
	  
	// Field descriptor #263 I
	private static final int T_LONG = 11;

	/**
	 * @param typeOpcode Array type opcode.
	 * @return Type in the {@link ArrayType} enum.
	 */
	private static ArrayType mapArrayType(int typeOpcode) {
		switch(typeOpcode) {
		case(T_BOOLEAN):
			return ArrayType.BOOLEAN;
		case(T_BYTE):
			return ArrayType.BYTE;
		case(T_CHAR):
			return ArrayType.CHAR;
		case(T_FLOAT):
			return ArrayType.FLOAT;
		case(T_DOUBLE):
			return ArrayType.DOUBLE;
		case(T_SHORT):
			return ArrayType.SHORT;
		case(T_INT):
			return ArrayType.INT;
		case(T_LONG):
			return ArrayType.LONG;
		default:
			return ArrayType.INVALID;
		}
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
		return mapEntityToInstrument(activeSection, entitiesToInstrumentIdMap);
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
	}
}
