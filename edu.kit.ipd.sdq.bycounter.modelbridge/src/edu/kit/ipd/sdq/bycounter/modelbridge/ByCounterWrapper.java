package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.logging.Logger;

import org.eclipse.emf.common.util.EList;

import de.fzi.gast.accesses.DeclarationTypeAccess;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Constructor;
import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.gast.types.GASTType;
import de.fzi.gast.variables.FormalParameter;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationCounterPrecision;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.ASMOpcodesMapper;
import de.uka.ipd.sdq.ByCounter.utils.JavaType;
import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.AggregationType;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.InternalResultStorageType;
import edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription;
import edu.kit.ipd.sdq.bycounter.output.FunctionCall;
import edu.kit.ipd.sdq.bycounter.output.JavaVMCall;
import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;
import edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.Request;
import edu.kit.ipd.sdq.bycounter.output.ResourceDemands;

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
	/** Mapping of ByCounter ranges to {@link InstrumentedCodeArea}. */
	private Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap;
	/** Mapping of the fully qualified method name to a {@link InstrumentedMethod}. */
	private Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap;
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
		if(input.getTemporaryResultsType() == InternalResultStorageType.IN_MEMORY) {
			// TODO: IN_MEMORY: This seems to be the default method and work correctly. Either remove the TODO or configure ByCounter in a correct way for the case that the default behavior is changed.
		} else if(input.getTemporaryResultsType() == InternalResultStorageType.ON_HDD) {
			// TODO: Is ON_HDD supported?
			throw new IllegalArgumentException("The parameter value ON_HDD for temporaryResultsType is not supported yet.");
		} else {
			throw new IllegalArgumentException("The parameter value " + input.getTemporaryResultsType() + " for temporaryResultsType is not supported yet.");
		}
		handlePersistInstrumentedClasses(instrumentationParams, input);
		input.isAggregateInternalCallsTransparently(); // TODO: implement aggregateInterncalCallsTransparently
		input.getDefinedLogicalSets(); // TODO: implement handling definedLogicalSets
		instrumentationParams.setInstrumentRecursively(input.isInstrumentRecursively(), 50); // TODO: Why is there a max depth of 50 specified? There should be the possibility to specify no maximal depth and instrument _all_ invoked methods. 
		handleBasicBlocks(instrumentationParams, input);
		final Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap = new HashMap<LineNumberRange, InstrumentedCodeArea>();
		final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap = new HashMap<String, InstrumentedMethod>();
		handleEntitiesToInstrument(instrumentationParams, rangeCodeAreaMap,
				methodNameInstrumentedMethodMap, input);

		// update instrumentation parameters and instrument
		this.inputModel = input;
		this.rangeCodeAreaMap = rangeCodeAreaMap;
		this.methodNameInstrumentedMethodMap = methodNameInstrumentedMethodMap;
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
	public MeasurementRun generateResult() {
		MeasurementRun run = outputFactory.createMeasurementRun();

		// TODO @Martin: Why is the input model and not the information in ByCounter used for this distinction?
		if(this.inputModel.getAggregationType() == AggregationType.OFFLINE) {
			throw new IllegalArgumentException("AggregationType " + this.inputModel.getAggregationType() + " is not supported.");
		} else if(inputModel.getAggregationType() == AggregationType.ONLINE) {
			// do nothing
		}
		// TODO: Enhance by supporting EnvironmentCharacterisation 
		EnvironmentDescription environmentDesc = null;
		run.setEnvironmentCharacterisation(environmentDesc);
		
		SortedSet<CountingResult> results;
		results = CountingResultCollector.getInstance().retrieveAllCountingResults();
		
		Request req = outputFactory.createRequest();
		run.getRequests().add(req);
		
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
		for(CountingResult result : results) {
			ObservedEntityExecution oee = convertCountingResultToOEE(
					fqMethodNameToLineNumberRanges, result);
			if (oee != null) {
				// store only if the result is valid
				req.getObservedExecutionSequence().add(oee);
			} else {
				// counting result generated due to automated instrumentation of internal classes which are not covered by an OEE.
			}
		}
		
		// results have been stored in the EMF model and are now removed from ByCounter
		CountingResultCollector.getInstance().clearResults();
		
		return run;
	}

	/**Converts a {@link CountingResult} into an {@link ObservedEntityExecution} including Java VM calls and method invocations. 
	 * @param lineNumberRangesByMethodName A map to get LineNumerRange arrays for a given method
	 * @return A {@link ObservedEntityExecution} instance with all the informations of the provided {@link CountingResult} instance. <code>null</code> if the result could not be matched. 
	 */
	private ObservedEntityExecution convertCountingResultToOEE(Map<String, LineNumberRange[]> lineNumberRangesByMethodName,
			CountingResult result) {
		ObservedEntityExecution oee = outputFactory.createObservedEntityExecution();
		// find the EntityToInstrument to which the result belongs
		EntityToInstrument instrumentedEntity;
		if(result.getIndexOfRangeBlock() >= 0) {
			// we have the result for an instrumented code area
			instrumentedEntity = rangeCodeAreaMap.get(getLineNumberRange(lineNumberRangesByMethodName, result));
		} else {
			// we have the result for an instrumented method
			instrumentedEntity = methodNameInstrumentedMethodMap.get(result.getQualifyingMethodName());
			if (instrumentedEntity == null) {
				return null;
			}
		}
		if ( instrumentedEntity == null ) {
			return null;
		} else {
			oee.setEntity(instrumentedEntity);
		}
		oee.setEntity(instrumentedEntity);
		ResourceDemands resourceDemands = outputFactory.createResourceDemands();
		oee.setResourceDemands(resourceDemands);

		// set the bytecode counts
		JavaVMCall vmCall;
		for(int opc = 0; opc < result.getOpcodeCounts().length; opc++) {
			vmCall = outputFactory.createJavaVMCall();
			vmCall.setId(new Integer(opc).byteValue());
			vmCall.setNumberObservations(result.getOpcodeCounts()[opc]);
			vmCall.setName(ASMOpcodesMapper.getInstance().getOpcodeString(opc));
			resourceDemands.getJavaVMCall().add(vmCall);
		}
		
		// set the method call counts
		FunctionCall fCall;
		for(Entry<String, Long> methodCall : result.getMethodCallCounts().entrySet()) {
			fCall = outputFactory.createFunctionCall();
			fCall.setNumberObservations(methodCall.getValue());
			fCall.setFunction(findFunctionForString(methodCall.getKey()));
			
			// TODO @Martin: correct content or model to reflect actual behavior
			fCall.setNative(false);
			fCall.setSynchronized(false);
			// parameter instances are not recorded and hence not logged in the model

			resourceDemands.getFunctionCalls().add(fCall);
		}
		return oee;
	}

	/**Find the Function which belongs to the provided BytecodeCounter output string.
	 * @param bcName BytecodeCounter representation of the function name.
	 * @return GAST Function referenced by the name.
	 */
	private Function findFunctionForString(String bcName) {
		int sigStartIndex = bcName.indexOf('(');
		String fqmn = bcName.substring(0, sigStartIndex); // fully qualified method name
		String sig = bcName.substring(sigStartIndex);	  // signature part including parameters in braces and return type (== desc)
		
		String fqcn = fqmn.substring(0, fqmn.lastIndexOf('.'));	// FullyQualifiedClassName (== owner)
		String fn = fqmn.substring(fqmn.lastIndexOf('.') + 1, fqmn.length()).replace('/', '.'); // FunctionName (== name)
		// use the MethodDescriptor class to consider the details
		MethodDescriptor methodDesc = MethodDescriptor._constructMethodDescriptorFromASM(fqcn, fn, sig); 
		String fqpn = methodDesc.getPackageName(); // FullyQualifiedPackageName 
		
		for (Root node : availableGastRootNodes) {
			// find Package
			de.fzi.gast.core.Package pkg = node.getPackageByQualifiedName(fqpn);
			if (pkg != null) {
				for (GASTClass clazz : pkg.getClasses()) {
					// find Class
					if (clazz.getQualifiedName().equals(fqcn)) {
						// TODO @Martin: Check which native classes need special handling 
						if (fqcn.startsWith("java.lang")) {
							return null;
						}
						// find and return Method/Constructor
						for (Constructor constructor : clazz.getConstructors()) {
							if (methodDesc.getSimpleMethodName().equals(constructor.getSimpleName())) {
								if(doSignaturesMatch(constructor, sig)) {
									return constructor;
								}
							}
						}
						for (Method method : clazz.getMethods()) {
							if (methodDesc.getSimpleMethodName().equals(method.getSimpleName())) {
								if(doSignaturesMatch(method, sig)) {
									return method;
								}
							}
						}
					}
				}
			}
		}
		throw new IllegalArgumentException("Could not find a method for the given BytecodeCounter name '" + bcName + "'. ");
	}

	/**
	 * @param fn GAST function to check.
	 * @param sig Signature of a method as given by ByCounter. This starts 
	 * with '(' and ends with the return type. Example: <code>()V<code> for a 
	 * method without parameters and a void return type.
	 * @return When the formal parameters and the return type of fn match the 
	 * description of sig, true is returned. False in all other cases.
	 */
	private boolean doSignaturesMatch(Function fn, String sig) {
		EList<FormalParameter> formalParams = fn.getFormalParameters();
		DeclarationTypeAccess retTypeDec = fn.getReturnTypeDeclaration();
		JavaType retType = MethodDescriptor.getReturnTypeFromDesc(sig);
		JavaType[] paramTypes = MethodDescriptor.getParametersTypesFromDesc(sig);
		
		// compare return type
		if(retTypeDec == null) { // TODO: verify null==void?
			if(retType.getType() != JavaTypeEnum.Void) {
				return false;
			} else {
				// return types match; continue with parameter tests
			}
		} else {
			if(!doTypesMatch(retTypeDec.getTargetType(), retType)) {
				return false;
			} else {
				// return types match; continue with parameter tests
			}
		}
		
		// compare parameters
		if(formalParams.size() != paramTypes.length) {
			return false;
		}		
		if(paramTypes.length == 0) {
			// no parameters: no need to do more tests
			return true;
		} else {
			// compare all parameters
			int i = 0;
			for(FormalParameter p : formalParams) {
				if(doTypesMatch(p.getType(), paramTypes[i])) {
					i++;
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @param type GAST type
	 * @param javaType Java type as parsed by ByCounter
	 * @return True when the types match. False otherwise.
	 */
	private boolean doTypesMatch(GASTType type, JavaType javaType) {
		String typeName = type.getQualifiedName();
		if(gastTypeJavaTypeMap.containsKey(typeName)) {
			// we have a simple java type
			if(gastTypeJavaTypeMap.get(typeName).equals(javaType.getType())) {
				return true;
			} else {
				return false;
			}
		} else {
			// gast array types are identical to the Java notation, ie. int[][]
			int arrayDim = 0;	// the dimension of the array if type is an array type.
			int ind = typeName.indexOf("[");
			String innerTypeFQCN = "";
			if(ind >= 0) {
				innerTypeFQCN = typeName.substring(0, ind);
				do {
					ind = typeName.indexOf("[", ind+2);
					arrayDim++;
				} while(ind >= 0);
				
				// check all dimensions of the array
				JavaType currentType = javaType;
				for(int i = 0; i < arrayDim; i++) {
					if(!currentType.getType().equals(JavaTypeEnum.Array)) {
						return false;
					}
					currentType = currentType.getChildElementType();
				}
				// check the inner type (int for the example above)
				if(currentType.getType().equals(JavaTypeEnum.Object)) {
					return currentType.getCanonicalClassName().equals(innerTypeFQCN);
				} else {
					if(gastTypeJavaTypeMap.containsKey(innerTypeFQCN)) {
						// we have a simple java type
						if(gastTypeJavaTypeMap.get(innerTypeFQCN).equals(currentType.getType())) {
							return true;
						} else {
							return false;
						}
					}
					throw new RuntimeException("Unknown type: " + innerTypeFQCN);
				}
			} else if(javaType.getType().equals(JavaTypeEnum.Object)) {
				if(typeName.contains("$")) {
					// assume a generic type
					return javaType.getCanonicalClassName().equals(Object.class.getCanonicalName());
				} else {
					// object type
					return typeName.equals(javaType.getCanonicalClassName());
				}
			}
			throw new RuntimeException("The type matching for this type ('" 
					+ type.getQualifiedName() + "') is not implemented yet.");
		}
	}

	/**Gets the {@link LineNumberRange} for given results.
	 * @param lineNumberRangesByMethodName A map to get LineNumerRange arrays for a given method
	 * @param result A {@link CountingResult} for a line number range
	 * @return The line number range to which the CountingResult belongs
	 */
	private static LineNumberRange getLineNumberRange(
			final Map<String, LineNumberRange[]> lineNumberRangesByMethodName, 
			final CountingResult result) {
		int index = result.getIndexOfRangeBlock();
		LineNumberRange[] ranges = 
			lineNumberRangesByMethodName.get(result.getQualifyingMethodName());
		return ranges[index];
	}
}
