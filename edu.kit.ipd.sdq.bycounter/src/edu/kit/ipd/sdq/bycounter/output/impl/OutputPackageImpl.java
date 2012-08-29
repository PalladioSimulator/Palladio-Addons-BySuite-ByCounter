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

import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.OutputFactory;
import edu.kit.ipd.sdq.bycounter.output.OutputModelRepository;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.RequestResult;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;
import edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult;

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
	private EClass threadedCountingResultEClass = null;

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
	public EReference getRequestResult_ResultCollection() {
		return (EReference)requestResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRequestResult_RequestId() {
		return (EAttribute)requestResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequestResult_CountingResults() {
		return (EReference)requestResultEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getCountingResult_ArrayCreationCounts() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_CallerId() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_MethodCallCounts() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_MethodInvocationBeginning() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_MethodReportingTime() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_ObservedElement() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_OpcodeCounts() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_OwnId() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCountingResult_QualifyingMethodName() {
		return (EAttribute)countingResultEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getThreadedCountingResult() {
		return threadedCountingResultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getThreadedCountingResult_SpawnedThreadedCountingResults() {
		return (EReference)threadedCountingResultEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getThreadedCountingResult_ThreadId() {
		return (EAttribute)threadedCountingResultEClass.getEStructuralFeatures().get(1);
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

		resultCollectionEClass = createEClass(RESULT_COLLECTION);
		createEReference(resultCollectionEClass, RESULT_COLLECTION__REQUEST_RESULTS);
		createEReference(resultCollectionEClass, RESULT_COLLECTION__COUNTING_RESULTS);

		requestResultEClass = createEClass(REQUEST_RESULT);
		createEReference(requestResultEClass, REQUEST_RESULT__RESULT_COLLECTION);
		createEAttribute(requestResultEClass, REQUEST_RESULT__REQUEST_ID);
		createEReference(requestResultEClass, REQUEST_RESULT__COUNTING_RESULTS);

		countingResultEClass = createEClass(COUNTING_RESULT);
		createEAttribute(countingResultEClass, COUNTING_RESULT__ARRAY_CREATION_COUNTS);
		createEAttribute(countingResultEClass, COUNTING_RESULT__CALLER_ID);
		createEAttribute(countingResultEClass, COUNTING_RESULT__METHOD_CALL_COUNTS);
		createEAttribute(countingResultEClass, COUNTING_RESULT__METHOD_INVOCATION_BEGINNING);
		createEAttribute(countingResultEClass, COUNTING_RESULT__METHOD_REPORTING_TIME);
		createEAttribute(countingResultEClass, COUNTING_RESULT__OBSERVED_ELEMENT);
		createEAttribute(countingResultEClass, COUNTING_RESULT__OPCODE_COUNTS);
		createEAttribute(countingResultEClass, COUNTING_RESULT__OWN_ID);
		createEAttribute(countingResultEClass, COUNTING_RESULT__QUALIFYING_METHOD_NAME);

		threadedCountingResultEClass = createEClass(THREADED_COUNTING_RESULT);
		createEReference(threadedCountingResultEClass, THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS);
		createEAttribute(threadedCountingResultEClass, THREADED_COUNTING_RESULT__THREAD_ID);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		outputModelRepositoryEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		threadedCountingResultEClass.getESuperTypes().add(this.getCountingResult());

		// Initialize classes and features; add operations and parameters
		initEClass(outputModelRepositoryEClass, OutputModelRepository.class, "OutputModelRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(resultCollectionEClass, ResultCollection.class, "ResultCollection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResultCollection_RequestResults(), this.getRequestResult(), null, "requestResults", null, 0, -1, ResultCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getResultCollection_CountingResults(), this.getCountingResult(), null, "countingResults", null, 0, -1, ResultCollection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(requestResultEClass, RequestResult.class, "RequestResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequestResult_ResultCollection(), this.getResultCollection(), null, "resultCollection", null, 0, -1, RequestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getRequestResult_RequestId(), ecorePackage.getEJavaObject(), "requestId", null, 1, 1, RequestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getRequestResult_CountingResults(), this.getCountingResult(), null, "countingResults", null, 0, -1, RequestResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(countingResultEClass, CountingResult.class, "CountingResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCountingResult_ArrayCreationCounts(), ecorePackage.getEJavaObject(), "arrayCreationCounts", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_CallerId(), ecorePackage.getEJavaObject(), "callerId", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_MethodCallCounts(), ecorePackage.getEJavaObject(), "methodCallCounts", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_MethodInvocationBeginning(), ecorePackage.getELong(), "methodInvocationBeginning", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_MethodReportingTime(), ecorePackage.getELong(), "methodReportingTime", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_ObservedElement(), ecorePackage.getEJavaObject(), "observedElement", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_OpcodeCounts(), ecorePackage.getELong(), "opcodeCounts", null, 1, -1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_OwnId(), ecorePackage.getEJavaObject(), "ownId", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCountingResult_QualifyingMethodName(), ecorePackage.getEString(), "qualifyingMethodName", null, 1, 1, CountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(threadedCountingResultEClass, ThreadedCountingResult.class, "ThreadedCountingResult", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getThreadedCountingResult_SpawnedThreadedCountingResults(), this.getThreadedCountingResult(), null, "spawnedThreadedCountingResults", null, 0, -1, ThreadedCountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getThreadedCountingResult_ThreadId(), ecorePackage.getELong(), "threadId", null, 1, 1, ThreadedCountingResult.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //OutputPackageImpl
