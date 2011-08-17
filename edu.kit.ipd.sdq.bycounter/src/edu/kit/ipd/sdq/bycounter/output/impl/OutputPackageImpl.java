/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.fzi.gast.accesses.accessesPackage;

import de.fzi.gast.annotations.annotationsPackage;

import de.fzi.gast.core.corePackage;

import de.fzi.gast.functions.functionsPackage;

import de.fzi.gast.statements.statementsPackage;

import de.fzi.gast.types.typesPackage;

import de.fzi.gast.variables.variablesPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import edu.kit.ipd.sdq.bycounter.input.InputPackage;

import edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl;

import edu.kit.ipd.sdq.bycounter.output.BooleanCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.DoubleCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription;
import edu.kit.ipd.sdq.bycounter.output.FunctionCall;
import edu.kit.ipd.sdq.bycounter.output.JavaVMCall;
import edu.kit.ipd.sdq.bycounter.output.LongCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;
import edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.OutputModelRepository;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.ParameterInstance;
import edu.kit.ipd.sdq.bycounter.output.Request;
import edu.kit.ipd.sdq.bycounter.output.ResourceDemands;
import edu.kit.ipd.sdq.bycounter.output.StringCharacterisation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class OutputPackageImpl extends EPackageImpl implements OutputPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass outputModelRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measurementRunEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass environmentDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass observedEntityExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceDemandsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterCharacterisationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass javaVMCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleCharacterisationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longCharacterisationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanCharacterisationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringCharacterisationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private OutputPackageImpl() {
		super(eNS_URI, OutputFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link OutputPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static OutputPackage init() {
		if (isInited) return (OutputPackage)EPackage.Registry.INSTANCE.getEPackage(OutputPackage.eNS_URI);

		// Obtain or create and register package
		OutputPackageImpl theOutputPackage = (OutputPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OutputPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new OutputPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		IdentifierPackage.eINSTANCE.eClass();
		statementsPackage.eINSTANCE.eClass();
		corePackage.eINSTANCE.eClass();
		annotationsPackage.eINSTANCE.eClass();
		typesPackage.eINSTANCE.eClass();
		accessesPackage.eINSTANCE.eClass();
		functionsPackage.eINSTANCE.eClass();
		variablesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		InputPackageImpl theInputPackage = (InputPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InputPackage.eNS_URI) instanceof InputPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InputPackage.eNS_URI) : InputPackage.eINSTANCE);

		// Create package meta-data objects
		theOutputPackage.createPackageContents();
		theInputPackage.createPackageContents();

		// Initialize created meta-data
		theOutputPackage.initializePackageContents();
		theInputPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theOutputPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(OutputPackage.eNS_URI, theOutputPackage);
		return theOutputPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOutputModelRepository() {
		return outputModelRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutputModelRepository_MeasurementRuns() {
		return (EReference)outputModelRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOutputModelRepository_EnvironmentCharacterisations() {
		return (EReference)outputModelRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasurementRun() {
		return measurementRunEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementRun_EnvironmentCharacterisation() {
		return (EReference)measurementRunEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasurementRun_Requests() {
		return (EReference)measurementRunEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnvironmentDescription() {
		return environmentDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequest() {
		return requestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequest_ObservedExecutionSequence() {
		return (EReference)requestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequest_MeasurementRun() {
		return (EReference)requestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getObservedEntityExecution() {
		return observedEntityExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObservedEntityExecution_ResourceDemands() {
		return (EReference)observedEntityExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObservedEntityExecution_ObservedForkedExecutionSequence() {
		return (EReference)observedEntityExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObservedEntityExecution_ObservedExecutionSequenceForkedBy() {
		return (EReference)observedEntityExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObservedEntityExecution_Entity() {
		return (EReference)observedEntityExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getObservedEntityExecution_Request() {
		return (EReference)observedEntityExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceDemands() {
		return resourceDemandsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceDemands_FunctionCalls() {
		return (EReference)resourceDemandsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceDemands_JavaVMCall() {
		return (EReference)resourceDemandsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceDemands_ObservedExecution() {
		return (EReference)resourceDemandsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFunctionCall() {
		return functionCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionCall_ParameterInstance() {
		return (EReference)functionCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionCall_Function() {
		return (EReference)functionCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionCall_NumberObservations() {
		return (EAttribute)functionCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionCall_Native() {
		return (EAttribute)functionCallEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFunctionCall_Synchronized() {
		return (EAttribute)functionCallEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFunctionCall_ResourceDemands() {
		return (EReference)functionCallEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterInstance() {
		return parameterInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterInstance_FormalParameter() {
		return (EReference)parameterInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterInstance_ParameterCharacterisation() {
		return (EReference)parameterInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterInstance_FunctionCall() {
		return (EReference)parameterInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterCharacterisation() {
		return parameterCharacterisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getParameterCharacterisation_Characterisation() {
		return (EAttribute)parameterCharacterisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterCharacterisation_ParameterInstance() {
		return (EReference)parameterCharacterisationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getJavaVMCall() {
		return javaVMCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaVMCall_NumberObservations() {
		return (EAttribute)javaVMCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaVMCall_Id() {
		return (EAttribute)javaVMCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getJavaVMCall_Name() {
		return (EAttribute)javaVMCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getJavaVMCall_ResourceDemands() {
		return (EReference)javaVMCallEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleCharacterisation() {
		return doubleCharacterisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleCharacterisation_Value() {
		return (EAttribute)doubleCharacterisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLongCharacterisation() {
		return longCharacterisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLongCharacterisation_Value() {
		return (EAttribute)longCharacterisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanCharacterisation() {
		return booleanCharacterisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanCharacterisation_Value() {
		return (EAttribute)booleanCharacterisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringCharacterisation() {
		return stringCharacterisationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringCharacterisation_Value() {
		return (EAttribute)stringCharacterisationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputFactory getOutputFactory() {
		return (OutputFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		outputModelRepositoryEClass = createEClass(OUTPUT_MODEL_REPOSITORY);
		createEReference(outputModelRepositoryEClass, OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS);
		createEReference(outputModelRepositoryEClass, OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS);

		measurementRunEClass = createEClass(MEASUREMENT_RUN);
		createEReference(measurementRunEClass, MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION);
		createEReference(measurementRunEClass, MEASUREMENT_RUN__REQUESTS);

		environmentDescriptionEClass = createEClass(ENVIRONMENT_DESCRIPTION);

		requestEClass = createEClass(REQUEST);
		createEReference(requestEClass, REQUEST__OBSERVED_EXECUTION_SEQUENCE);
		createEReference(requestEClass, REQUEST__MEASUREMENT_RUN);

		observedEntityExecutionEClass = createEClass(OBSERVED_ENTITY_EXECUTION);
		createEReference(observedEntityExecutionEClass, OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS);
		createEReference(observedEntityExecutionEClass, OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE);
		createEReference(observedEntityExecutionEClass, OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY);
		createEReference(observedEntityExecutionEClass, OBSERVED_ENTITY_EXECUTION__ENTITY);
		createEReference(observedEntityExecutionEClass, OBSERVED_ENTITY_EXECUTION__REQUEST);

		resourceDemandsEClass = createEClass(RESOURCE_DEMANDS);
		createEReference(resourceDemandsEClass, RESOURCE_DEMANDS__FUNCTION_CALLS);
		createEReference(resourceDemandsEClass, RESOURCE_DEMANDS__JAVA_VM_CALL);
		createEReference(resourceDemandsEClass, RESOURCE_DEMANDS__OBSERVED_EXECUTION);

		functionCallEClass = createEClass(FUNCTION_CALL);
		createEReference(functionCallEClass, FUNCTION_CALL__PARAMETER_INSTANCE);
		createEReference(functionCallEClass, FUNCTION_CALL__FUNCTION);
		createEAttribute(functionCallEClass, FUNCTION_CALL__NUMBER_OBSERVATIONS);
		createEAttribute(functionCallEClass, FUNCTION_CALL__NATIVE);
		createEAttribute(functionCallEClass, FUNCTION_CALL__SYNCHRONIZED);
		createEReference(functionCallEClass, FUNCTION_CALL__RESOURCE_DEMANDS);

		parameterInstanceEClass = createEClass(PARAMETER_INSTANCE);
		createEReference(parameterInstanceEClass, PARAMETER_INSTANCE__FORMAL_PARAMETER);
		createEReference(parameterInstanceEClass, PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION);
		createEReference(parameterInstanceEClass, PARAMETER_INSTANCE__FUNCTION_CALL);

		parameterCharacterisationEClass = createEClass(PARAMETER_CHARACTERISATION);
		createEAttribute(parameterCharacterisationEClass, PARAMETER_CHARACTERISATION__CHARACTERISATION);
		createEReference(parameterCharacterisationEClass, PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE);

		javaVMCallEClass = createEClass(JAVA_VM_CALL);
		createEAttribute(javaVMCallEClass, JAVA_VM_CALL__NUMBER_OBSERVATIONS);
		createEAttribute(javaVMCallEClass, JAVA_VM_CALL__ID);
		createEAttribute(javaVMCallEClass, JAVA_VM_CALL__NAME);
		createEReference(javaVMCallEClass, JAVA_VM_CALL__RESOURCE_DEMANDS);

		doubleCharacterisationEClass = createEClass(DOUBLE_CHARACTERISATION);
		createEAttribute(doubleCharacterisationEClass, DOUBLE_CHARACTERISATION__VALUE);

		longCharacterisationEClass = createEClass(LONG_CHARACTERISATION);
		createEAttribute(longCharacterisationEClass, LONG_CHARACTERISATION__VALUE);

		booleanCharacterisationEClass = createEClass(BOOLEAN_CHARACTERISATION);
		createEAttribute(booleanCharacterisationEClass, BOOLEAN_CHARACTERISATION__VALUE);

		stringCharacterisationEClass = createEClass(STRING_CHARACTERISATION);
		createEAttribute(stringCharacterisationEClass, STRING_CHARACTERISATION__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);
		InputPackage theInputPackage = (InputPackage)EPackage.Registry.INSTANCE.getEPackage(InputPackage.eNS_URI);
		functionsPackage thefunctionsPackage = (functionsPackage)EPackage.Registry.INSTANCE.getEPackage(functionsPackage.eNS_URI);
		variablesPackage thevariablesPackage = (variablesPackage)EPackage.Registry.INSTANCE.getEPackage(variablesPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		outputModelRepositoryEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		measurementRunEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		environmentDescriptionEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		requestEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		observedEntityExecutionEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		resourceDemandsEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		functionCallEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		parameterInstanceEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		parameterCharacterisationEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		doubleCharacterisationEClass.getESuperTypes().add(this.getParameterCharacterisation());
		longCharacterisationEClass.getESuperTypes().add(this.getParameterCharacterisation());
		booleanCharacterisationEClass.getESuperTypes().add(this.getParameterCharacterisation());
		stringCharacterisationEClass.getESuperTypes().add(this.getParameterCharacterisation());

		// Initialize classes and features; add operations and parameters
		initEClass(outputModelRepositoryEClass, OutputModelRepository.class, "OutputModelRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutputModelRepository_MeasurementRuns(), this.getMeasurementRun(), null, "measurementRuns", null, 0, -1, OutputModelRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOutputModelRepository_EnvironmentCharacterisations(), this.getEnvironmentDescription(), null, "environmentCharacterisations", null, 0, -1, OutputModelRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(measurementRunEClass, MeasurementRun.class, "MeasurementRun", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMeasurementRun_EnvironmentCharacterisation(), this.getEnvironmentDescription(), null, "environmentCharacterisation", null, 0, 1, MeasurementRun.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMeasurementRun_Requests(), this.getRequest(), this.getRequest_MeasurementRun(), "requests", null, 0, -1, MeasurementRun.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(environmentDescriptionEClass, EnvironmentDescription.class, "EnvironmentDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(requestEClass, Request.class, "Request", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequest_ObservedExecutionSequence(), this.getObservedEntityExecution(), this.getObservedEntityExecution_Request(), "observedExecutionSequence", null, 0, -1, Request.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRequest_MeasurementRun(), this.getMeasurementRun(), this.getMeasurementRun_Requests(), "measurementRun", null, 1, 1, Request.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(observedEntityExecutionEClass, ObservedEntityExecution.class, "ObservedEntityExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getObservedEntityExecution_ResourceDemands(), this.getResourceDemands(), this.getResourceDemands_ObservedExecution(), "resourceDemands", null, 1, 1, ObservedEntityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObservedEntityExecution_ObservedForkedExecutionSequence(), this.getObservedEntityExecution(), this.getObservedEntityExecution_ObservedExecutionSequenceForkedBy(), "observedForkedExecutionSequence", null, 0, -1, ObservedEntityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getObservedEntityExecution_ObservedExecutionSequenceForkedBy(), this.getObservedEntityExecution(), this.getObservedEntityExecution_ObservedForkedExecutionSequence(), "observedExecutionSequenceForkedBy", null, 0, 1, ObservedEntityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getObservedEntityExecution_Entity(), theInputPackage.getEntityToInstrument(), null, "entity", null, 1, 1, ObservedEntityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getObservedEntityExecution_Request(), this.getRequest(), this.getRequest_ObservedExecutionSequence(), "request", null, 0, 1, ObservedEntityExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resourceDemandsEClass, ResourceDemands.class, "ResourceDemands", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceDemands_FunctionCalls(), this.getFunctionCall(), this.getFunctionCall_ResourceDemands(), "functionCalls", null, 0, -1, ResourceDemands.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getResourceDemands_JavaVMCall(), this.getJavaVMCall(), this.getJavaVMCall_ResourceDemands(), "javaVMCall", null, 0, -1, ResourceDemands.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getResourceDemands_ObservedExecution(), this.getObservedEntityExecution(), this.getObservedEntityExecution_ResourceDemands(), "observedExecution", null, 1, 1, ResourceDemands.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(functionCallEClass, FunctionCall.class, "FunctionCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFunctionCall_ParameterInstance(), this.getParameterInstance(), this.getParameterInstance_FunctionCall(), "parameterInstance", null, 0, -1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFunctionCall_Function(), thefunctionsPackage.getFunction(), null, "function", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFunctionCall_NumberObservations(), ecorePackage.getELong(), "numberObservations", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFunctionCall_Native(), ecorePackage.getEBoolean(), "native", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFunctionCall_Synchronized(), ecorePackage.getEBoolean(), "synchronized", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getFunctionCall_ResourceDemands(), this.getResourceDemands(), this.getResourceDemands_FunctionCalls(), "resourceDemands", null, 1, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(parameterInstanceEClass, ParameterInstance.class, "ParameterInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterInstance_FormalParameter(), thevariablesPackage.getFormalParameter(), null, "formalParameter", null, 1, 1, ParameterInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getParameterInstance_ParameterCharacterisation(), this.getParameterCharacterisation(), this.getParameterCharacterisation_ParameterInstance(), "parameterCharacterisation", null, 0, -1, ParameterInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getParameterInstance_FunctionCall(), this.getFunctionCall(), this.getFunctionCall_ParameterInstance(), "functionCall", null, 1, 1, ParameterInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(parameterCharacterisationEClass, ParameterCharacterisation.class, "ParameterCharacterisation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterCharacterisation_Characterisation(), theEcorePackage.getEString(), "characterisation", null, 1, 1, ParameterCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getParameterCharacterisation_ParameterInstance(), this.getParameterInstance(), this.getParameterInstance_ParameterCharacterisation(), "parameterInstance", null, 1, 1, ParameterCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(javaVMCallEClass, JavaVMCall.class, "JavaVMCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getJavaVMCall_NumberObservations(), ecorePackage.getELong(), "numberObservations", null, 1, 1, JavaVMCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getJavaVMCall_Id(), ecorePackage.getEByte(), "id", null, 1, 1, JavaVMCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getJavaVMCall_Name(), ecorePackage.getEString(), "name", null, 1, 1, JavaVMCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getJavaVMCall_ResourceDemands(), this.getResourceDemands(), this.getResourceDemands_JavaVMCall(), "resourceDemands", null, 1, 1, JavaVMCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(doubleCharacterisationEClass, DoubleCharacterisation.class, "DoubleCharacterisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleCharacterisation_Value(), ecorePackage.getEDouble(), "value", null, 1, 1, DoubleCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(longCharacterisationEClass, LongCharacterisation.class, "LongCharacterisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLongCharacterisation_Value(), ecorePackage.getELong(), "value", null, 1, 1, LongCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(booleanCharacterisationEClass, BooleanCharacterisation.class, "BooleanCharacterisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanCharacterisation_Value(), ecorePackage.getEBoolean(), "value", null, 1, 1, BooleanCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(stringCharacterisationEClass, StringCharacterisation.class, "StringCharacterisation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringCharacterisation_Value(), ecorePackage.getEString(), "value", null, 1, 1, StringCharacterisation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OutputPackageImpl
