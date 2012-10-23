/**
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.uml2.types.TypesPackage;

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
import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.ArrayType;
import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.MethodCallCount;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.OutputModelRepository;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.RequestResult;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;

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
	private EClass resultCollectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requestResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass countingResultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uuidEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayCreationCountEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass methodCallCountEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum arrayTypeEEnum = null;

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
		TypesPackage.eINSTANCE.eClass();

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
	public EReference getOutputModelRepository_ResultCollection() {
		return (EReference)outputModelRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResultCollection() {
		return resultCollectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResultCollection_RequestResults() {
		return (EReference)resultCollectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResultCollection_CountingResults() {
		return (EReference)resultCollectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequestResult() {
		return requestResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequestResult_CountingResults() {
		return (EReference)requestResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequestResult_ResultCollection() {
		return (EReference)requestResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCountingResult() {
		return countingResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_CallerId() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_MethodInvocationStartTime() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_ReportingTime() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_ObservedElement() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_OpcodeCounts() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_MethodId() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_QualifiedMethodName() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_ResultCollection() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_ArrayCreationCounts() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_MethodCallCounts() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_ThreadId() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_Final() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_SpawnedThreadedCountingResults() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_ThreadedCountingResult() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCountingResult_RequestResult() {
		return (EReference)countingResultEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUUID() {
		return uuidEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUUID_StringRepresentation() {
		return (EAttribute)uuidEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArrayCreationCount() {
		return arrayCreationCountEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayCreationCount_Count() {
		return (EAttribute)arrayCreationCountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayCreationCount_TypeDescriptor() {
		return (EAttribute)arrayCreationCountEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayCreationCount_NumberOfDimensions() {
		return (EAttribute)arrayCreationCountEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getArrayCreationCount_ArrayType() {
		return (EAttribute)arrayCreationCountEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getArrayCreationCount_CountingResult() {
		return (EReference)arrayCreationCountEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMethodCallCount() {
		return methodCallCountEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodCallCount_QualifiedFunctionName() {
		return (EAttribute)methodCallCountEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMethodCallCount_Count() {
		return (EAttribute)methodCallCountEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodCallCount_Function() {
		return (EReference)methodCallCountEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMethodCallCount_CountingResult() {
		return (EReference)methodCallCountEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getArrayType() {
		return arrayTypeEEnum;
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
		createEReference(outputModelRepositoryEClass, OUTPUT_MODEL_REPOSITORY__RESULT_COLLECTION);

		resultCollectionEClass = createEClass(RESULT_COLLECTION);
		createEReference(resultCollectionEClass, RESULT_COLLECTION__REQUEST_RESULTS);
		createEReference(resultCollectionEClass, RESULT_COLLECTION__COUNTING_RESULTS);

		requestResultEClass = createEClass(REQUEST_RESULT);
		createEReference(requestResultEClass, REQUEST_RESULT__COUNTING_RESULTS);
		createEReference(requestResultEClass, REQUEST_RESULT__RESULT_COLLECTION);

		countingResultEClass = createEClass(COUNTING_RESULT);
		createEReference(countingResultEClass, COUNTING_RESULT__CALLER_ID);
		createEAttribute(countingResultEClass, COUNTING_RESULT__METHOD_INVOCATION_START_TIME);
		createEAttribute(countingResultEClass, COUNTING_RESULT__REPORTING_TIME);
		createEReference(countingResultEClass, COUNTING_RESULT__OBSERVED_ELEMENT);
		createEAttribute(countingResultEClass, COUNTING_RESULT__OPCODE_COUNTS);
		createEReference(countingResultEClass, COUNTING_RESULT__METHOD_ID);
		createEAttribute(countingResultEClass, COUNTING_RESULT__QUALIFIED_METHOD_NAME);
		createEReference(countingResultEClass, COUNTING_RESULT__RESULT_COLLECTION);
		createEReference(countingResultEClass, COUNTING_RESULT__ARRAY_CREATION_COUNTS);
		createEReference(countingResultEClass, COUNTING_RESULT__METHOD_CALL_COUNTS);
		createEAttribute(countingResultEClass, COUNTING_RESULT__THREAD_ID);
		createEAttribute(countingResultEClass, COUNTING_RESULT__FINAL);
		createEReference(countingResultEClass, COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS);
		createEReference(countingResultEClass, COUNTING_RESULT__THREADED_COUNTING_RESULT);
		createEReference(countingResultEClass, COUNTING_RESULT__REQUEST_RESULT);

		uuidEClass = createEClass(UUID);
		createEAttribute(uuidEClass, UUID__STRING_REPRESENTATION);

		arrayCreationCountEClass = createEClass(ARRAY_CREATION_COUNT);
		createEAttribute(arrayCreationCountEClass, ARRAY_CREATION_COUNT__COUNT);
		createEAttribute(arrayCreationCountEClass, ARRAY_CREATION_COUNT__TYPE_DESCRIPTOR);
		createEAttribute(arrayCreationCountEClass, ARRAY_CREATION_COUNT__NUMBER_OF_DIMENSIONS);
		createEAttribute(arrayCreationCountEClass, ARRAY_CREATION_COUNT__ARRAY_TYPE);
		createEReference(arrayCreationCountEClass, ARRAY_CREATION_COUNT__COUNTING_RESULT);

		methodCallCountEClass = createEClass(METHOD_CALL_COUNT);
		createEAttribute(methodCallCountEClass, METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME);
		createEAttribute(methodCallCountEClass, METHOD_CALL_COUNT__COUNT);
		createEReference(methodCallCountEClass, METHOD_CALL_COUNT__FUNCTION);
		createEReference(methodCallCountEClass, METHOD_CALL_COUNT__COUNTING_RESULT);

		// Create enums
		arrayTypeEEnum = createEEnum(ARRAY_TYPE);
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
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		functionsPackage thefunctionsPackage = (functionsPackage)EPackage.Registry.INSTANCE.getEPackage(functionsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		outputModelRepositoryEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		requestResultEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

		// Initialize classes and features; add operations and parameters
		initEClass(outputModelRepositoryEClass, OutputModelRepository.class, "OutputModelRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutputModelRepository_ResultCollection(), this.getResultCollection(), null, "resultCollection", null, 0, -1, OutputModelRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resultCollectionEClass, ResultCollection.class, "ResultCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResultCollection_RequestResults(), this.getRequestResult(), this.getRequestResult_ResultCollection(), "requestResults", null, 0, -1, ResultCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getResultCollection_CountingResults(), this.getCountingResult(), this.getCountingResult_ResultCollection(), "countingResults", null, 0, -1, ResultCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(requestResultEClass, RequestResult.class, "RequestResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequestResult_CountingResults(), this.getCountingResult(), this.getCountingResult_RequestResult(), "countingResults", null, 0, -1, RequestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRequestResult_ResultCollection(), this.getResultCollection(), this.getResultCollection_RequestResults(), "resultCollection", null, 1, 1, RequestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(countingResultEClass, CountingResult.class, "CountingResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCountingResult_CallerId(), this.getUUID(), null, "callerId", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_MethodInvocationStartTime(), ecorePackage.getELong(), "methodInvocationStartTime", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_ReportingTime(), ecorePackage.getELong(), "reportingTime", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_ObservedElement(), theInputPackage.getEntityToInstrument(), null, "observedElement", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_OpcodeCounts(), ecorePackage.getELong(), "opcodeCounts", null, 0, -1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_MethodId(), this.getUUID(), null, "methodId", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_QualifiedMethodName(), ecorePackage.getEString(), "qualifiedMethodName", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_ResultCollection(), this.getResultCollection(), this.getResultCollection_CountingResults(), "resultCollection", null, 0, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_ArrayCreationCounts(), this.getArrayCreationCount(), this.getArrayCreationCount_CountingResult(), "arrayCreationCounts", null, 0, -1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_MethodCallCounts(), this.getMethodCallCount(), this.getMethodCallCount_CountingResult(), "methodCallCounts", null, 0, -1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_ThreadId(), ecorePackage.getELong(), "threadId", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_Final(), ecorePackage.getEBoolean(), "final", "false", 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_SpawnedThreadedCountingResults(), this.getCountingResult(), this.getCountingResult_ThreadedCountingResult(), "spawnedThreadedCountingResults", null, 0, -1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_ThreadedCountingResult(), this.getCountingResult(), this.getCountingResult_SpawnedThreadedCountingResults(), "threadedCountingResult", null, 0, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCountingResult_RequestResult(), this.getRequestResult(), this.getRequestResult_CountingResults(), "requestResult", null, 0, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(uuidEClass, edu.kit.ipd.sdq.bycounter.output.UUID.class, "UUID", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUUID_StringRepresentation(), ecorePackage.getEString(), "stringRepresentation", null, 1, 1, edu.kit.ipd.sdq.bycounter.output.UUID.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(arrayCreationCountEClass, ArrayCreationCount.class, "ArrayCreationCount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArrayCreationCount_Count(), theEcorePackage.getELong(), "count", null, 1, 1, ArrayCreationCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getArrayCreationCount_TypeDescriptor(), theEcorePackage.getEString(), "typeDescriptor", null, 1, 1, ArrayCreationCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getArrayCreationCount_NumberOfDimensions(), theEcorePackage.getEInt(), "numberOfDimensions", null, 1, 1, ArrayCreationCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getArrayCreationCount_ArrayType(), this.getArrayType(), "arrayType", null, 1, 1, ArrayCreationCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getArrayCreationCount_CountingResult(), this.getCountingResult(), this.getCountingResult_ArrayCreationCounts(), "countingResult", null, 1, 1, ArrayCreationCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(methodCallCountEClass, MethodCallCount.class, "MethodCallCount", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMethodCallCount_QualifiedFunctionName(), theEcorePackage.getEString(), "qualifiedFunctionName", null, 1, 1, MethodCallCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMethodCallCount_Count(), theEcorePackage.getELong(), "count", null, 1, 1, MethodCallCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodCallCount_Function(), thefunctionsPackage.getFunction(), null, "function", null, 0, 1, MethodCallCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMethodCallCount_CountingResult(), this.getCountingResult(), this.getCountingResult_MethodCallCounts(), "countingResult", null, 1, 1, MethodCallCount.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(arrayTypeEEnum, ArrayType.class, "ArrayType");
		addEEnumLiteral(arrayTypeEEnum, ArrayType.INVALID);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.BOOLEAN);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.BYTE);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.CHAR);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.DOUBLE);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.FLOAT);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.INT);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.LONG);
		addEEnumLiteral(arrayTypeEEnum, ArrayType.SHORT);

		// Create resource
		createResource(eNS_URI);
	}

} //OutputPackageImpl
