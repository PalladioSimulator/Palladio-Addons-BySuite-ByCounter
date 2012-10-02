/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.input.InputFactory
 * @model kind="package"
 * @generated
 */
public interface InputPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "input";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sdq.ipd.kit.edu/ByCounterModels/Input/0.3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ByCounter.Input";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InputPackage eINSTANCE = edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl <em>Instrumentation Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentationProfile()
	 * @generated
	 */
	int INSTRUMENTATION_PROFILE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Instrument Recursively</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Aggregation Excludes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Entities To Instrument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Intrumentation Profile Repository</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Instrument Using Long Counters</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Instrument Using Basic Blocks</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Provide Join Threads Ability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Persist Instrumented Classes To OS Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Provide Online Section Active Updates</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Trace And Identify Requests</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Instrumentation Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 10;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl <em>Instrumentation Profile Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentationProfileRepository()
	 * @generated
	 */
	int INSTRUMENTATION_PROFILE_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE_REPOSITORY__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Execution Profile</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Instrumentation Profile Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTATION_PROFILE_REPOSITORY_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl <em>Execution Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getExecutionProfile()
	 * @generated
	 */
	int EXECUTION_PROFILE = 2;

	/**
	 * The feature id for the '<em><b>Add Up Results Recursively</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY = 0;

	/**
	 * The feature id for the '<em><b>Wait For Threads To Finnish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH = 1;

	/**
	 * The feature id for the '<em><b>Defined Logical Sets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROFILE__DEFINED_LOGICAL_SETS = 2;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile Repository</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY = 3;

	/**
	 * The number of structural features of the '<em>Execution Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_PROFILE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl <em>Logical Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getLogicalSet()
	 * @generated
	 */
	int LOGICAL_SET = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_SET__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Internal Classes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_SET__INTERNAL_CLASSES = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Profile</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_SET__EXECUTION_PROFILE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Logical Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_SET_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.EntityToInstrumentImpl <em>Entity To Instrument</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.EntityToInstrumentImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getEntityToInstrument()
	 * @generated
	 */
	int ENTITY_TO_INSTRUMENT = 7;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE = 0;

	/**
	 * The number of structural features of the '<em>Entity To Instrument</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_TO_INSTRUMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl <em>Instrumented Code Area</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedCodeArea()
	 * @generated
	 */
	int INSTRUMENTED_CODE_AREA = 4;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_CODE_AREA__INSTRUMENTATION_PROFILE = ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE;

	/**
	 * The feature id for the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_CODE_AREA__FROM = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_CODE_AREA__TO = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Instrumented Code Area</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_CODE_AREA_FEATURE_COUNT = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl <em>Instrumented Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedMethod()
	 * @generated
	 */
	int INSTRUMENTED_METHOD = 5;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD__INSTRUMENTATION_PROFILE = ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE;

	/**
	 * The feature id for the '<em><b>Implementation Or Derived</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Declaration Or Parent</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD__DECLARATION_OR_PARENT = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Instrument Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD__INSTRUMENT_DERIVED = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD__METHOD = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Instrumented Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_METHOD_FEATURE_COUNT = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl <em>Instrumented Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl
	 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedRegion()
	 * @generated
	 */
	int INSTRUMENTED_REGION = 6;

	/**
	 * The feature id for the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION__INSTRUMENTATION_PROFILE = ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE;

	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION__START_LINE = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stop Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION__STOP_LINE = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Start Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION__START_METHOD = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Stop Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION__STOP_METHOD = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Instrumented Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTRUMENTED_REGION_FEATURE_COUNT = ENTITY_TO_INSTRUMENT_FEATURE_COUNT + 4;


	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumentation Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile
	 * @generated
	 */
	EClass getInstrumentationProfile();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentRecursively <em>Instrument Recursively</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instrument Recursively</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentRecursively()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_InstrumentRecursively();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getAggregationExcludes <em>Aggregation Excludes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aggregation Excludes</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getAggregationExcludes()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EReference getInstrumentationProfile_AggregationExcludes();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument <em>Entities To Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entities To Instrument</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EReference getInstrumentationProfile_EntitiesToInstrument();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Intrumentation Profile Repository</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EReference getInstrumentationProfile_IntrumentationProfileRepository();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instrument Using Long Counters</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingLongCounters()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_InstrumentUsingLongCounters();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instrument Using Basic Blocks</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingBasicBlocks()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_InstrumentUsingBasicBlocks();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideJoinThreadsAbility <em>Provide Join Threads Ability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provide Join Threads Ability</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideJoinThreadsAbility()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_ProvideJoinThreadsAbility();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClassesToOSPath <em>Persist Instrumented Classes To OS Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Persist Instrumented Classes To OS Path</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClassesToOSPath()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_PersistInstrumentedClassesToOSPath();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideOnlineSectionActiveUpdates <em>Provide Online Section Active Updates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Provide Online Section Active Updates</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideOnlineSectionActiveUpdates()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_ProvideOnlineSectionActiveUpdates();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace And Identify Requests</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isTraceAndIdentifyRequests()
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	EAttribute getInstrumentationProfile_TraceAndIdentifyRequests();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository <em>Instrumentation Profile Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumentation Profile Repository</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository
	 * @generated
	 */
	EClass getInstrumentationProfileRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getExecutionProfile <em>Execution Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getExecutionProfile()
	 * @see #getInstrumentationProfileRepository()
	 * @generated
	 */
	EReference getInstrumentationProfileRepository_ExecutionProfile();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getInstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Instrumentation Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getInstrumentationProfile()
	 * @see #getInstrumentationProfileRepository()
	 * @generated
	 */
	EReference getInstrumentationProfileRepository_InstrumentationProfile();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile <em>Execution Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.ExecutionProfile
	 * @generated
	 */
	EClass getExecutionProfile();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isAddUpResultsRecursively <em>Add Up Results Recursively</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Add Up Results Recursively</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isAddUpResultsRecursively()
	 * @see #getExecutionProfile()
	 * @generated
	 */
	EAttribute getExecutionProfile_AddUpResultsRecursively();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Threads To Finnish</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isWaitForThreadsToFinnish()
	 * @see #getExecutionProfile()
	 * @generated
	 */
	EAttribute getExecutionProfile_WaitForThreadsToFinnish();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getDefinedLogicalSets <em>Defined Logical Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Defined Logical Sets</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getDefinedLogicalSets()
	 * @see #getExecutionProfile()
	 * @generated
	 */
	EReference getExecutionProfile_DefinedLogicalSets();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getInstrumentationProfileRepository <em>Instrumentation Profile Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Instrumentation Profile Repository</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getInstrumentationProfileRepository()
	 * @see #getExecutionProfile()
	 * @generated
	 */
	EReference getExecutionProfile_InstrumentationProfileRepository();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet <em>Logical Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical Set</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.LogicalSet
	 * @generated
	 */
	EClass getLogicalSet();

	/**
	 * Returns the meta object for the reference list '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInternalClasses <em>Internal Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Internal Classes</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInternalClasses()
	 * @see #getLogicalSet()
	 * @generated
	 */
	EReference getLogicalSet_InternalClasses();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getExecutionProfile <em>Execution Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Execution Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.LogicalSet#getExecutionProfile()
	 * @see #getLogicalSet()
	 * @generated
	 */
	EReference getLogicalSet_ExecutionProfile();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea <em>Instrumented Code Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumented Code Area</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea
	 * @generated
	 */
	EClass getInstrumentedCodeArea();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getFrom()
	 * @see #getInstrumentedCodeArea()
	 * @generated
	 */
	EReference getInstrumentedCodeArea_From();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>To</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getTo()
	 * @see #getInstrumentedCodeArea()
	 * @generated
	 */
	EReference getInstrumentedCodeArea_To();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod <em>Instrumented Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumented Method</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod
	 * @generated
	 */
	EClass getInstrumentedMethod();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getImplementationOrDerived <em>Implementation Or Derived</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Implementation Or Derived</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getImplementationOrDerived()
	 * @see #getInstrumentedMethod()
	 * @generated
	 */
	EReference getInstrumentedMethod_ImplementationOrDerived();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent <em>Declaration Or Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Declaration Or Parent</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent()
	 * @see #getInstrumentedMethod()
	 * @generated
	 */
	EReference getInstrumentedMethod_DeclarationOrParent();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#isInstrumentDerived <em>Instrument Derived</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instrument Derived</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#isInstrumentDerived()
	 * @see #getInstrumentedMethod()
	 * @generated
	 */
	EAttribute getInstrumentedMethod_InstrumentDerived();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Method</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getMethod()
	 * @see #getInstrumentedMethod()
	 * @generated
	 */
	EReference getInstrumentedMethod_Method();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion <em>Instrumented Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instrumented Region</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion
	 * @generated
	 */
	EClass getInstrumentedRegion();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartLine <em>Start Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Line</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartLine()
	 * @see #getInstrumentedRegion()
	 * @generated
	 */
	EAttribute getInstrumentedRegion_StartLine();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopLine <em>Stop Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stop Line</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopLine()
	 * @see #getInstrumentedRegion()
	 * @generated
	 */
	EAttribute getInstrumentedRegion_StopLine();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartMethod <em>Start Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Method</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartMethod()
	 * @see #getInstrumentedRegion()
	 * @generated
	 */
	EReference getInstrumentedRegion_StartMethod();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopMethod <em>Stop Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Stop Method</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopMethod()
	 * @see #getInstrumentedRegion()
	 * @generated
	 */
	EReference getInstrumentedRegion_StopMethod();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument <em>Entity To Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity To Instrument</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.EntityToInstrument
	 * @generated
	 */
	EClass getEntityToInstrument();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Instrumentation Profile</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile()
	 * @see #getEntityToInstrument()
	 * @generated
	 */
	EReference getEntityToInstrument_InstrumentationProfile();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	InputFactory getInputFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl <em>Instrumentation Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentationProfile()
		 * @generated
		 */
		EClass INSTRUMENTATION_PROFILE = eINSTANCE.getInstrumentationProfile();

		/**
		 * The meta object literal for the '<em><b>Instrument Recursively</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY = eINSTANCE.getInstrumentationProfile_InstrumentRecursively();

		/**
		 * The meta object literal for the '<em><b>Aggregation Excludes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES = eINSTANCE.getInstrumentationProfile_AggregationExcludes();

		/**
		 * The meta object literal for the '<em><b>Entities To Instrument</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT = eINSTANCE.getInstrumentationProfile_EntitiesToInstrument();

		/**
		 * The meta object literal for the '<em><b>Intrumentation Profile Repository</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY = eINSTANCE.getInstrumentationProfile_IntrumentationProfileRepository();

		/**
		 * The meta object literal for the '<em><b>Instrument Using Long Counters</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS = eINSTANCE.getInstrumentationProfile_InstrumentUsingLongCounters();

		/**
		 * The meta object literal for the '<em><b>Instrument Using Basic Blocks</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS = eINSTANCE.getInstrumentationProfile_InstrumentUsingBasicBlocks();

		/**
		 * The meta object literal for the '<em><b>Provide Join Threads Ability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY = eINSTANCE.getInstrumentationProfile_ProvideJoinThreadsAbility();

		/**
		 * The meta object literal for the '<em><b>Persist Instrumented Classes To OS Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH = eINSTANCE.getInstrumentationProfile_PersistInstrumentedClassesToOSPath();

		/**
		 * The meta object literal for the '<em><b>Provide Online Section Active Updates</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES = eINSTANCE.getInstrumentationProfile_ProvideOnlineSectionActiveUpdates();

		/**
		 * The meta object literal for the '<em><b>Trace And Identify Requests</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS = eINSTANCE.getInstrumentationProfile_TraceAndIdentifyRequests();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl <em>Instrumentation Profile Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentationProfileRepository()
		 * @generated
		 */
		EClass INSTRUMENTATION_PROFILE_REPOSITORY = eINSTANCE.getInstrumentationProfileRepository();

		/**
		 * The meta object literal for the '<em><b>Execution Profile</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE = eINSTANCE.getInstrumentationProfileRepository_ExecutionProfile();

		/**
		 * The meta object literal for the '<em><b>Instrumentation Profile</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE = eINSTANCE.getInstrumentationProfileRepository_InstrumentationProfile();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl <em>Execution Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getExecutionProfile()
		 * @generated
		 */
		EClass EXECUTION_PROFILE = eINSTANCE.getExecutionProfile();

		/**
		 * The meta object literal for the '<em><b>Add Up Results Recursively</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY = eINSTANCE.getExecutionProfile_AddUpResultsRecursively();

		/**
		 * The meta object literal for the '<em><b>Wait For Threads To Finnish</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH = eINSTANCE.getExecutionProfile_WaitForThreadsToFinnish();

		/**
		 * The meta object literal for the '<em><b>Defined Logical Sets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_PROFILE__DEFINED_LOGICAL_SETS = eINSTANCE.getExecutionProfile_DefinedLogicalSets();

		/**
		 * The meta object literal for the '<em><b>Instrumentation Profile Repository</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY = eINSTANCE.getExecutionProfile_InstrumentationProfileRepository();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl <em>Logical Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getLogicalSet()
		 * @generated
		 */
		EClass LOGICAL_SET = eINSTANCE.getLogicalSet();

		/**
		 * The meta object literal for the '<em><b>Internal Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_SET__INTERNAL_CLASSES = eINSTANCE.getLogicalSet_InternalClasses();

		/**
		 * The meta object literal for the '<em><b>Execution Profile</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOGICAL_SET__EXECUTION_PROFILE = eINSTANCE.getLogicalSet_ExecutionProfile();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl <em>Instrumented Code Area</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedCodeArea()
		 * @generated
		 */
		EClass INSTRUMENTED_CODE_AREA = eINSTANCE.getInstrumentedCodeArea();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_CODE_AREA__FROM = eINSTANCE.getInstrumentedCodeArea_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_CODE_AREA__TO = eINSTANCE.getInstrumentedCodeArea_To();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl <em>Instrumented Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedMethod()
		 * @generated
		 */
		EClass INSTRUMENTED_METHOD = eINSTANCE.getInstrumentedMethod();

		/**
		 * The meta object literal for the '<em><b>Implementation Or Derived</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED = eINSTANCE.getInstrumentedMethod_ImplementationOrDerived();

		/**
		 * The meta object literal for the '<em><b>Declaration Or Parent</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_METHOD__DECLARATION_OR_PARENT = eINSTANCE.getInstrumentedMethod_DeclarationOrParent();

		/**
		 * The meta object literal for the '<em><b>Instrument Derived</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTED_METHOD__INSTRUMENT_DERIVED = eINSTANCE.getInstrumentedMethod_InstrumentDerived();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_METHOD__METHOD = eINSTANCE.getInstrumentedMethod_Method();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl <em>Instrumented Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getInstrumentedRegion()
		 * @generated
		 */
		EClass INSTRUMENTED_REGION = eINSTANCE.getInstrumentedRegion();

		/**
		 * The meta object literal for the '<em><b>Start Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTED_REGION__START_LINE = eINSTANCE.getInstrumentedRegion_StartLine();

		/**
		 * The meta object literal for the '<em><b>Stop Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INSTRUMENTED_REGION__STOP_LINE = eINSTANCE.getInstrumentedRegion_StopLine();

		/**
		 * The meta object literal for the '<em><b>Start Method</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_REGION__START_METHOD = eINSTANCE.getInstrumentedRegion_StartMethod();

		/**
		 * The meta object literal for the '<em><b>Stop Method</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTRUMENTED_REGION__STOP_METHOD = eINSTANCE.getInstrumentedRegion_StopMethod();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.input.impl.EntityToInstrumentImpl <em>Entity To Instrument</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.EntityToInstrumentImpl
		 * @see edu.kit.ipd.sdq.bycounter.input.impl.InputPackageImpl#getEntityToInstrument()
		 * @generated
		 */
		EClass ENTITY_TO_INSTRUMENT = eINSTANCE.getEntityToInstrument();

		/**
		 * The meta object literal for the '<em><b>Instrumentation Profile</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE = eINSTANCE.getEntityToInstrument_InstrumentationProfile();

	}

} //InputPackage
