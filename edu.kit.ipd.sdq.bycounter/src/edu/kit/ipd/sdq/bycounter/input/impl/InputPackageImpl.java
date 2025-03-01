/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
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
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InputFactory;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;
import edu.kit.ipd.sdq.bycounter.input.util.InputValidator;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InputPackageImpl extends EPackageImpl implements InputPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentationProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentationProfileRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionProfileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentedCodeAreaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass instrumentedMethodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityToInstrumentEClass = null;

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
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private InputPackageImpl() {
		super(eNS_URI, InputFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link InputPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static InputPackage init() {
		if (isInited) return (InputPackage)EPackage.Registry.INSTANCE.getEPackage(InputPackage.eNS_URI);

		// Obtain or create and register package
		InputPackageImpl theInputPackage = (InputPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof InputPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new InputPackageImpl());

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
		OutputPackageImpl theOutputPackage = (OutputPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OutputPackage.eNS_URI) instanceof OutputPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OutputPackage.eNS_URI) : OutputPackage.eINSTANCE);

		// Create package meta-data objects
		theInputPackage.createPackageContents();
		theOutputPackage.createPackageContents();

		// Initialize created meta-data
		theInputPackage.initializePackageContents();
		theOutputPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theInputPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return InputValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theInputPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(InputPackage.eNS_URI, theInputPackage);
		return theInputPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstrumentationProfile() {
		return instrumentationProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_InstrumentRecursively() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfile_AggregationExcludes() {
		return (EReference)instrumentationProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfile_EntitiesToInstrument() {
		return (EReference)instrumentationProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfile_IntrumentationProfileRepository() {
		return (EReference)instrumentationProfileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_InstrumentUsingLongCounters() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_InstrumentUsingBasicBlocks() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_NumberCallGraphClassAnalyserThreads() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_ProvideJoinThreadsAbility() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_PersistInstrumentedClassesToOSPath() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_ProvideOnlineSectionActiveUpdates() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentationProfile_TraceAndIdentifyRequests() {
		return (EAttribute)instrumentationProfileEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfile_LogicalSetExternalToClassLoader() {
		return (EReference)instrumentationProfileEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstrumentationProfileRepository() {
		return instrumentationProfileRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfileRepository_ExecutionProfile() {
		return (EReference)instrumentationProfileRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentationProfileRepository_InstrumentationProfile() {
		return (EReference)instrumentationProfileRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionProfile() {
		return executionProfileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionProfile_AddUpResultsRecursively() {
		return (EAttribute)executionProfileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getExecutionProfile_WaitForThreadsToFinish() {
		return (EAttribute)executionProfileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionProfile_DefinedLogicalSets() {
		return (EReference)executionProfileEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionProfile_InstrumentationProfileRepository() {
		return (EReference)executionProfileEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLogicalSet() {
		return logicalSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalSet_InternalClasses() {
		return (EReference)logicalSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getLogicalSet_ExecutionProfile() {
		return (EReference)logicalSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstrumentedCodeArea() {
		return instrumentedCodeAreaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentedCodeArea_From() {
		return (EReference)instrumentedCodeAreaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentedCodeArea_To() {
		return (EReference)instrumentedCodeAreaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInstrumentedMethod() {
		return instrumentedMethodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentedMethod_ImplementationOrDerived() {
		return (EReference)instrumentedMethodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentedMethod_DeclarationOrParent() {
		return (EReference)instrumentedMethodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInstrumentedMethod_InstrumentDerived() {
		return (EAttribute)instrumentedMethodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInstrumentedMethod_Method() {
		return (EReference)instrumentedMethodEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntityToInstrument() {
		return entityToInstrumentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEntityToInstrument_InstrumentationProfile() {
		return (EReference)entityToInstrumentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputFactory getInputFactory() {
		return (InputFactory)getEFactoryInstance();
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
		instrumentationProfileEClass = createEClass(INSTRUMENTATION_PROFILE);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY);
		createEReference(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES);
		createEReference(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT);
		createEReference(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES);
		createEAttribute(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS);
		createEReference(instrumentationProfileEClass, INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER);

		instrumentationProfileRepositoryEClass = createEClass(INSTRUMENTATION_PROFILE_REPOSITORY);
		createEReference(instrumentationProfileRepositoryEClass, INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE);
		createEReference(instrumentationProfileRepositoryEClass, INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE);

		executionProfileEClass = createEClass(EXECUTION_PROFILE);
		createEAttribute(executionProfileEClass, EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY);
		createEAttribute(executionProfileEClass, EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINISH);
		createEReference(executionProfileEClass, EXECUTION_PROFILE__DEFINED_LOGICAL_SETS);
		createEReference(executionProfileEClass, EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY);

		logicalSetEClass = createEClass(LOGICAL_SET);
		createEReference(logicalSetEClass, LOGICAL_SET__INTERNAL_CLASSES);
		createEReference(logicalSetEClass, LOGICAL_SET__EXECUTION_PROFILE);

		instrumentedCodeAreaEClass = createEClass(INSTRUMENTED_CODE_AREA);
		createEReference(instrumentedCodeAreaEClass, INSTRUMENTED_CODE_AREA__FROM);
		createEReference(instrumentedCodeAreaEClass, INSTRUMENTED_CODE_AREA__TO);

		instrumentedMethodEClass = createEClass(INSTRUMENTED_METHOD);
		createEReference(instrumentedMethodEClass, INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED);
		createEReference(instrumentedMethodEClass, INSTRUMENTED_METHOD__DECLARATION_OR_PARENT);
		createEAttribute(instrumentedMethodEClass, INSTRUMENTED_METHOD__INSTRUMENT_DERIVED);
		createEReference(instrumentedMethodEClass, INSTRUMENTED_METHOD__METHOD);

		entityToInstrumentEClass = createEClass(ENTITY_TO_INSTRUMENT);
		createEReference(entityToInstrumentEClass, ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE);
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
		functionsPackage thefunctionsPackage = (functionsPackage)EPackage.Registry.INSTANCE.getEPackage(functionsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		typesPackage thetypesPackage = (typesPackage)EPackage.Registry.INSTANCE.getEPackage(typesPackage.eNS_URI);
		statementsPackage thestatementsPackage = (statementsPackage)EPackage.Registry.INSTANCE.getEPackage(statementsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		instrumentationProfileEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		instrumentationProfileRepositoryEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		logicalSetEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
		instrumentedCodeAreaEClass.getESuperTypes().add(this.getEntityToInstrument());
		instrumentedMethodEClass.getESuperTypes().add(this.getEntityToInstrument());

		// Initialize classes and features; add operations and parameters
		initEClass(instrumentationProfileEClass, InstrumentationProfile.class, "InstrumentationProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInstrumentationProfile_InstrumentRecursively(), ecorePackage.getEBoolean(), "instrumentRecursively", "true", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentationProfile_AggregationExcludes(), thefunctionsPackage.getMethod(), null, "aggregationExcludes", null, 0, -1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentationProfile_EntitiesToInstrument(), this.getEntityToInstrument(), this.getEntityToInstrument_InstrumentationProfile(), "entitiesToInstrument", null, 0, -1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentationProfile_IntrumentationProfileRepository(), this.getInstrumentationProfileRepository(), this.getInstrumentationProfileRepository_InstrumentationProfile(), "intrumentationProfileRepository", null, 0, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_InstrumentUsingLongCounters(), ecorePackage.getEBoolean(), "instrumentUsingLongCounters", "true", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_InstrumentUsingBasicBlocks(), ecorePackage.getEBoolean(), "instrumentUsingBasicBlocks", "true", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_NumberCallGraphClassAnalyserThreads(), ecorePackage.getEIntegerObject(), "numberCallGraphClassAnalyserThreads", null, 0, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_ProvideJoinThreadsAbility(), ecorePackage.getEBoolean(), "provideJoinThreadsAbility", "true", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_PersistInstrumentedClassesToOSPath(), theEcorePackage.getEString(), "persistInstrumentedClassesToOSPath", null, 0, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_ProvideOnlineSectionActiveUpdates(), ecorePackage.getEBoolean(), "provideOnlineSectionActiveUpdates", "true", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentationProfile_TraceAndIdentifyRequests(), ecorePackage.getEBoolean(), "traceAndIdentifyRequests", "false", 1, 1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentationProfile_LogicalSetExternalToClassLoader(), this.getLogicalSet(), null, "logicalSetExternalToClassLoader", null, 0, -1, InstrumentationProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = addEOperation(instrumentationProfileEClass, ecorePackage.getEBoolean(), "atLeastOneThread", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(instrumentationProfileRepositoryEClass, InstrumentationProfileRepository.class, "InstrumentationProfileRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstrumentationProfileRepository_ExecutionProfile(), this.getExecutionProfile(), this.getExecutionProfile_InstrumentationProfileRepository(), "executionProfile", null, 0, -1, InstrumentationProfileRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentationProfileRepository_InstrumentationProfile(), this.getInstrumentationProfile(), this.getInstrumentationProfile_IntrumentationProfileRepository(), "instrumentationProfile", null, 0, -1, InstrumentationProfileRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(executionProfileEClass, ExecutionProfile.class, "ExecutionProfile", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getExecutionProfile_AddUpResultsRecursively(), ecorePackage.getEBoolean(), "addUpResultsRecursively", "true", 1, 1, ExecutionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getExecutionProfile_WaitForThreadsToFinish(), ecorePackage.getEBoolean(), "waitForThreadsToFinish", "true", 1, 1, ExecutionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionProfile_DefinedLogicalSets(), this.getLogicalSet(), this.getLogicalSet_ExecutionProfile(), "definedLogicalSets", null, 0, -1, ExecutionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionProfile_InstrumentationProfileRepository(), this.getInstrumentationProfileRepository(), this.getInstrumentationProfileRepository_ExecutionProfile(), "instrumentationProfileRepository", null, 0, 1, ExecutionProfile.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(logicalSetEClass, LogicalSet.class, "LogicalSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLogicalSet_InternalClasses(), thetypesPackage.getGASTClass(), null, "internalClasses", null, 0, -1, LogicalSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getLogicalSet_ExecutionProfile(), this.getExecutionProfile(), this.getExecutionProfile_DefinedLogicalSets(), "executionProfile", null, 0, 1, LogicalSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(instrumentedCodeAreaEClass, InstrumentedCodeArea.class, "InstrumentedCodeArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstrumentedCodeArea_From(), thestatementsPackage.getStatement(), null, "from", null, 1, 1, InstrumentedCodeArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentedCodeArea_To(), thestatementsPackage.getStatement(), null, "to", null, 1, 1, InstrumentedCodeArea.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(instrumentedMethodEClass, InstrumentedMethod.class, "InstrumentedMethod", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstrumentedMethod_ImplementationOrDerived(), this.getInstrumentedMethod(), this.getInstrumentedMethod_DeclarationOrParent(), "implementationOrDerived", null, 0, -1, InstrumentedMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentedMethod_DeclarationOrParent(), this.getInstrumentedMethod(), this.getInstrumentedMethod_ImplementationOrDerived(), "declarationOrParent", null, 0, 1, InstrumentedMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getInstrumentedMethod_InstrumentDerived(), ecorePackage.getEBoolean(), "instrumentDerived", "false", 1, 1, InstrumentedMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInstrumentedMethod_Method(), thefunctionsPackage.getMethod(), null, "method", null, 1, 1, InstrumentedMethod.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(entityToInstrumentEClass, EntityToInstrument.class, "EntityToInstrument", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEntityToInstrument_InstrumentationProfile(), this.getInstrumentationProfile(), this.getInstrumentationProfile_EntitiesToInstrument(), "instrumentationProfile", null, 0, 1, EntityToInstrument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //InputPackageImpl
