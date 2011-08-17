/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OutputFactoryImpl extends EFactoryImpl implements OutputFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OutputFactory init() {
		try {
			OutputFactory theOutputFactory = (OutputFactory)EPackage.Registry.INSTANCE.getEFactory("http://sdq.ipd.kit.edu/ByCounterModels/Output/0.3"); 
			if (theOutputFactory != null) {
				return theOutputFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OutputFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY: return createOutputModelRepository();
			case OutputPackage.MEASUREMENT_RUN: return createMeasurementRun();
			case OutputPackage.REQUEST: return createRequest();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION: return createObservedEntityExecution();
			case OutputPackage.RESOURCE_DEMANDS: return createResourceDemands();
			case OutputPackage.FUNCTION_CALL: return createFunctionCall();
			case OutputPackage.PARAMETER_INSTANCE: return createParameterInstance();
			case OutputPackage.JAVA_VM_CALL: return createJavaVMCall();
			case OutputPackage.DOUBLE_CHARACTERISATION: return createDoubleCharacterisation();
			case OutputPackage.LONG_CHARACTERISATION: return createLongCharacterisation();
			case OutputPackage.BOOLEAN_CHARACTERISATION: return createBooleanCharacterisation();
			case OutputPackage.STRING_CHARACTERISATION: return createStringCharacterisation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputModelRepository createOutputModelRepository() {
		OutputModelRepositoryImpl outputModelRepository = new OutputModelRepositoryImpl();
		return outputModelRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementRun createMeasurementRun() {
		MeasurementRunImpl measurementRun = new MeasurementRunImpl();
		return measurementRun;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Request createRequest() {
		RequestImpl request = new RequestImpl();
		return request;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObservedEntityExecution createObservedEntityExecution() {
		ObservedEntityExecutionImpl observedEntityExecution = new ObservedEntityExecutionImpl();
		return observedEntityExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDemands createResourceDemands() {
		ResourceDemandsImpl resourceDemands = new ResourceDemandsImpl();
		return resourceDemands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionCall createFunctionCall() {
		FunctionCallImpl functionCall = new FunctionCallImpl();
		return functionCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterInstance createParameterInstance() {
		ParameterInstanceImpl parameterInstance = new ParameterInstanceImpl();
		return parameterInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaVMCall createJavaVMCall() {
		JavaVMCallImpl javaVMCall = new JavaVMCallImpl();
		return javaVMCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleCharacterisation createDoubleCharacterisation() {
		DoubleCharacterisationImpl doubleCharacterisation = new DoubleCharacterisationImpl();
		return doubleCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongCharacterisation createLongCharacterisation() {
		LongCharacterisationImpl longCharacterisation = new LongCharacterisationImpl();
		return longCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanCharacterisation createBooleanCharacterisation() {
		BooleanCharacterisationImpl booleanCharacterisation = new BooleanCharacterisationImpl();
		return booleanCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringCharacterisation createStringCharacterisation() {
		StringCharacterisationImpl stringCharacterisation = new StringCharacterisationImpl();
		return stringCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPackage getOutputPackage() {
		return (OutputPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OutputPackage getPackage() {
		return OutputPackage.eINSTANCE;
	}

} //OutputFactoryImpl
