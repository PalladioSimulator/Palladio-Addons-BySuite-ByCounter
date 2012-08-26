package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.logging.Logger;

import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.variables.FormalParameter;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationCounterPrecision;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.RequestResult;
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;
import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;

/**
 * Control ByCounter using EMF models.
 * This wrapper enables using EMF models as input and output for ByCounter. 
 * @author Martin Krogmann
 * @author groenda 
 *
 */
public class ByCounterWrapper {
	/** Logger of this class. */
	private static final Logger log = Logger.getLogger(ByCounterWrapper.class.getCanonicalName());
	/** EMF Factory for the Output package. */
	private static final OutputFactory outputFactory = OutputFactory.eINSTANCE;

	/** Wrapped instance of BytecodeCounter. */
	private BytecodeCounter bycounter;
	/** Current instrumentation configuration. */
	private InstrumentationProfile inputModel;
	/** List of available GAST Root Nodes. */
	private final LinkedList<Root> availableGastRootNodes = new LinkedList<Root>();
	/** A map that maps the string representation of simple java 
	 * types in the GAST to the JavaTypeEnum. */
	private static Map<String, JavaTypeEnum> gastTypeJavaTypeMap;
	
	static {
		// initialise the gastTypeJavaTypeMap
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

	public ByCounterWrapper() {
		this.bycounter = new BytecodeCounter();
	}

	/**Gets the current configuration for the instrumentation.
	 * @return The configuration.
	 */
	public InstrumentationProfile getInstrumentationConfiguration() {
		return inputModel;
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
		handlePersistInstrumentedClasses(instrumentationParams, input);
		input.isAggregateInternalCallsTransparently(); // TODO: implement aggregateInterncalCallsTransparently
		input.getDefinedLogicalSets(); // TODO: implement handling definedLogicalSets
		instrumentationParams.setInstrumentRecursively(input.isInstrumentRecursively()); 
		handleBasicBlocks(instrumentationParams, input);
		final Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap = new HashMap<LineNumberRange, InstrumentedCodeArea>();
		final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap = new HashMap<String, InstrumentedMethod>();
		handleEntitiesToInstrument(instrumentationParams, rangeCodeAreaMap,
				methodNameInstrumentedMethodMap, input);

		// update instrumentation parameters and instrument
		this.inputModel = input;
		this.bycounter.setInstrumentationParams(instrumentationParams);
		this.bycounter.instrument();
	}

	/**Handles updates of the input model regarding all {@link EntityToInstrument}.
	 * @param instrumentationParams The ByCounter instrumentation parameters which are updated.
	 * @param entityToLineNumberMap Map which links each create ByCounter {@link LineNumberRange} with the corresponding {@link EntityToInstrument}. 
	 * @param instrumentationProfile The instrumentation profile with the new parameters.
	 */
	private void handleEntitiesToInstrument(
			InstrumentationParameters instrumentationParams,
			final Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap,
			final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap,
			final InstrumentationProfile instrumentationProfile) {
		
		final List<MethodDescriptor> methodsToInstrument = new ArrayList<MethodDescriptor>();
		EntityToInstrumentToByCounterSwitch entitySwitch = new EntityToInstrumentToByCounterSwitch(
				methodsToInstrument, rangeCodeAreaMap,
				methodNameInstrumentedMethodMap, availableGastRootNodes);
		for(EntityToInstrument entity : instrumentationProfile.getEntitiesToInstrument()) {
			boolean success = entitySwitch.doSwitch(entity);
			if(!success) {
				String errorMsg = "Could not interpret the entity to instrument: " + entity;
				log.severe(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
		}
		instrumentationParams.setMethodsToInstrument(methodsToInstrument);
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
		String outputClassDirectory = instrumentationProfile.getPersistInstrumentedClasses();
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
	 * the specified method
	 * @param m Method to execute.
	 * @param target Object on which the method is executed.
	 * @param params Arguments of the method to execute.
	 */
	public Object execute(Method m, Object target, Object[] params) {
		MethodDescriptor methodToExecute = new MethodDescriptor(
				m.getSurroundingClass().getQualifiedName(),
				ByCounterWrapper.constructSignature(m));

		this.bycounter.getExecutionSettings().setAddUpResultsRecursively(this.inputModel.isInstrumentRecursively());
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
	
	/**
	 * After executing instrumented code ({@link #execute(Method, Object[])}), 
	 * this method retrieves the ByCounter results and saves them in the 
	 * OutputModel.
	 * @return An instance of {@link MeasurementRun} filled with all results 
	 * created by executing the ByCounter instrumented code using 
	 * {@link #execute(Method, Object[])}
	 * since the last call to this method.
	 */
	public ResultCollection generateResult() {
		ResultCollection run = outputFactory.createResultCollection();
		final de.uka.ipd.sdq.ByCounter.results.ResultCollection resultCollection = 
				CountingResultCollector.getInstance().retrieveAllCountingResults();

		// convert request results
		final SortedSet<RequestResult> requestResults;
		requestResults = resultCollection.getRequestResults();
		for(RequestResult rr : requestResults) {
			edu.kit.ipd.sdq.bycounter.output.RequestResult req = mapRequestResult(rr);
			run.getRequestResults().add(req);
		}
		
		/* create a lookup map to find the LineNumberRange arrays of methods. 
		 * This is necessary because CountingResults only know the index of the corresponding 
		 * LineNumberRange of the containing method.
		 */
		Map<String, LineNumberRange[]> fqMethodNameToLineNumberRanges = 
			new HashMap<String, LineNumberRange[]>();
		Iterator<MethodDescriptor> iter = this.bycounter.getInstrumentationParams().getMethodsToInstrument().iterator();
		while(iter.hasNext()) {
			MethodDescriptor md = iter.next();
			fqMethodNameToLineNumberRanges.put(md.getCanonicalMethodName(), md.getCodeAreasToInstrument());
		}

		// convert ordinary results including threaded counting results
		final SortedSet<CountingResult> results;
		results = resultCollection.getCountingResults();

		edu.kit.ipd.sdq.bycounter.output.CountingResult cr;
		for(CountingResult result : results)  { 
			cr = mapCountingResult(result);
			run.getCountingResults().add(cr);
		}
		
		// results have been stored in the EMF model and are now removed from ByCounter
		CountingResultCollector.getInstance().clearResults();
		
		return run;
	}

	/**
	 * Maps from {@link RequestResult} to 
	 * {@link edu.kit.ipd.sdq.bycounter.output.RequestResult}.
	 * @param rr {@link RequestResult} to convert.
	 * @return {@link edu.kit.ipd.sdq.bycounter.output.RequestResult} created 
	 * from rr.
	 */
	private static edu.kit.ipd.sdq.bycounter.output.RequestResult mapRequestResult(
			RequestResult rr) {
		edu.kit.ipd.sdq.bycounter.output.RequestResult req = 
				outputFactory.createRequestResult();
		req.setRequestId(rr.getRequestId());
		for(CountingResult cr : rr.getCountingResults()) {
			req.getCountingResults().add(mapCountingResult(cr));
		}
		return req;
	}

	/**
	 * Converts from the Java {@link CountingResult} to
	 * the EMF {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * @param cr {@link CountingResult} to convert.
	 * @return EMF {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 */
	private static edu.kit.ipd.sdq.bycounter.output.CountingResult mapCountingResult(
			CountingResult cr) {
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
						(edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult)mapCountingResult(tcrr);
				tResult.getSpawnedThreadedCountingResults().add(mappedTcrr);
			}
			result = tResult;
		} else {
			result = outputFactory.createCountingResult();
		}
		// map the properies of the base class
		result.setArrayCreationCounts(cr.getArrayCreationCounts());
		result.setCallerId(cr.getCallerID());
		result.setMethodCallCounts(cr.getMethodCallCounts());
		result.setMethodInvocationBeginning(cr.getMethodInvocationBeginning());
		result.setMethodReportingTime(cr.getMethodReportingTime());
		result.setObservedElement(cr.getObservedElement());
		result.setOwnId(cr.getOwnID());
		result.setQualifyingMethodName(cr.getQualifyingMethodName());
	
		return result;
	}
}
