/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

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
 * <!-- begin-model-doc -->
 * Decorator&nbsp;models&nbsp;which&nbsp;annotate&nbsp;raw&nbsp;resource&nbsp;demands&nbsp;to&nbsp;AbstractInternalControlFlowActions.
 * <!-- end-model-doc -->
 * @see edu.kit.ipd.sdq.bycounter.output.OutputFactory
 * @model kind="package"
 * @generated
 */
public interface OutputPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "output";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sdq.ipd.kit.edu/ByCounterModels/Output/0.3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ByCounter.Output";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OutputPackage eINSTANCE = edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl <em>Model Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getOutputModelRepository()
	 * @generated
	 */
	int OUTPUT_MODEL_REPOSITORY = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The number of structural features of the '<em>Model Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl <em>Result Collection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getResultCollection()
	 * @generated
	 */
	int RESULT_COLLECTION = 1;

	/**
	 * The feature id for the '<em><b>Request Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_COLLECTION__REQUEST_RESULTS = 0;

	/**
	 * The feature id for the '<em><b>Counting Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_COLLECTION__COUNTING_RESULTS = 1;

	/**
	 * The number of structural features of the '<em>Result Collection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESULT_COLLECTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl <em>Request Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getRequestResult()
	 * @generated
	 */
	int REQUEST_RESULT = 2;

	/**
	 * The feature id for the '<em><b>Result Collection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__RESULT_COLLECTION = 0;

	/**
	 * The feature id for the '<em><b>Request Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__REQUEST_ID = 1;

	/**
	 * The feature id for the '<em><b>Counting Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__COUNTING_RESULTS = 2;

	/**
	 * The number of structural features of the '<em>Request Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl <em>Counting Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getCountingResult()
	 * @generated
	 */
	int COUNTING_RESULT = 3;

	/**
	 * The feature id for the '<em><b>Array Creation Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__ARRAY_CREATION_COUNTS = 0;

	/**
	 * The feature id for the '<em><b>Caller Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__CALLER_ID = 1;

	/**
	 * The feature id for the '<em><b>Method Call Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_CALL_COUNTS = 2;

	/**
	 * The feature id for the '<em><b>Method Invocation Beginning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_INVOCATION_BEGINNING = 3;

	/**
	 * The feature id for the '<em><b>Method Reporting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_REPORTING_TIME = 4;

	/**
	 * The feature id for the '<em><b>Observed Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__OBSERVED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Opcode Counts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__OPCODE_COUNTS = 6;

	/**
	 * The feature id for the '<em><b>Own Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__OWN_ID = 7;

	/**
	 * The feature id for the '<em><b>Qualifying Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__QUALIFYING_METHOD_NAME = 8;

	/**
	 * The number of structural features of the '<em>Counting Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl <em>Threaded Counting Result</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getThreadedCountingResult()
	 * @generated
	 */
	int THREADED_COUNTING_RESULT = 4;

	/**
	 * The feature id for the '<em><b>Array Creation Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__ARRAY_CREATION_COUNTS = COUNTING_RESULT__ARRAY_CREATION_COUNTS;

	/**
	 * The feature id for the '<em><b>Caller Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__CALLER_ID = COUNTING_RESULT__CALLER_ID;

	/**
	 * The feature id for the '<em><b>Method Call Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__METHOD_CALL_COUNTS = COUNTING_RESULT__METHOD_CALL_COUNTS;

	/**
	 * The feature id for the '<em><b>Method Invocation Beginning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__METHOD_INVOCATION_BEGINNING = COUNTING_RESULT__METHOD_INVOCATION_BEGINNING;

	/**
	 * The feature id for the '<em><b>Method Reporting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__METHOD_REPORTING_TIME = COUNTING_RESULT__METHOD_REPORTING_TIME;

	/**
	 * The feature id for the '<em><b>Observed Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__OBSERVED_ELEMENT = COUNTING_RESULT__OBSERVED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Opcode Counts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__OPCODE_COUNTS = COUNTING_RESULT__OPCODE_COUNTS;

	/**
	 * The feature id for the '<em><b>Own Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__OWN_ID = COUNTING_RESULT__OWN_ID;

	/**
	 * The feature id for the '<em><b>Qualifying Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__QUALIFYING_METHOD_NAME = COUNTING_RESULT__QUALIFYING_METHOD_NAME;

	/**
	 * The feature id for the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS = COUNTING_RESULT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Thread Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT__THREAD_ID = COUNTING_RESULT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Threaded Counting Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THREADED_COUNTING_RESULT_FEATURE_COUNT = COUNTING_RESULT_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository <em>Model Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Repository</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputModelRepository
	 * @generated
	 */
	EClass getOutputModelRepository();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Collection</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResultCollection
	 * @generated
	 */
	EClass getResultCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getRequestResults <em>Request Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Request Results</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResultCollection#getRequestResults()
	 * @see #getResultCollection()
	 * @generated
	 */
	EReference getResultCollection_RequestResults();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getCountingResults <em>Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Counting Results</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResultCollection#getCountingResults()
	 * @see #getResultCollection()
	 * @generated
	 */
	EReference getResultCollection_CountingResults();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult <em>Request Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Request Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult
	 * @generated
	 */
	EClass getRequestResult();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Result Collection</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection()
	 * @see #getRequestResult()
	 * @generated
	 */
	EReference getRequestResult_ResultCollection();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getRequestId <em>Request Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Request Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getRequestId()
	 * @see #getRequestResult()
	 * @generated
	 */
	EAttribute getRequestResult_RequestId();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults <em>Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Counting Results</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults()
	 * @see #getRequestResult()
	 * @generated
	 */
	EReference getRequestResult_CountingResults();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult <em>Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Counting Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult
	 * @generated
	 */
	EClass getCountingResult();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Array Creation Counts</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_ArrayCreationCounts();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Caller Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_CallerId();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Call Counts</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_MethodCallCounts();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationBeginning <em>Method Invocation Beginning</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Invocation Beginning</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationBeginning()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_MethodInvocationBeginning();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodReportingTime <em>Method Reporting Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Reporting Time</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodReportingTime()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_MethodReportingTime();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Observed Element</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_ObservedElement();

	/**
	 * Returns the meta object for the attribute list '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOpcodeCounts <em>Opcode Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Opcode Counts</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getOpcodeCounts()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_OpcodeCounts();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOwnId <em>Own Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Own Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getOwnId()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_OwnId();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifyingMethodName <em>Qualifying Method Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualifying Method Name</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifyingMethodName()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_QualifyingMethodName();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult <em>Threaded Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Threaded Counting Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult
	 * @generated
	 */
	EClass getThreadedCountingResult();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spawned Threaded Counting Results</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getSpawnedThreadedCountingResults()
	 * @see #getThreadedCountingResult()
	 * @generated
	 */
	EReference getThreadedCountingResult_SpawnedThreadedCountingResults();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadId <em>Thread Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Thread Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadId()
	 * @see #getThreadedCountingResult()
	 * @generated
	 */
	EAttribute getThreadedCountingResult_ThreadId();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OutputFactory getOutputFactory();

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
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl <em>Model Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getOutputModelRepository()
		 * @generated
		 */
		EClass OUTPUT_MODEL_REPOSITORY = eINSTANCE.getOutputModelRepository();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl <em>Result Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getResultCollection()
		 * @generated
		 */
		EClass RESULT_COLLECTION = eINSTANCE.getResultCollection();

		/**
		 * The meta object literal for the '<em><b>Request Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESULT_COLLECTION__REQUEST_RESULTS = eINSTANCE.getResultCollection_RequestResults();

		/**
		 * The meta object literal for the '<em><b>Counting Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESULT_COLLECTION__COUNTING_RESULTS = eINSTANCE.getResultCollection_CountingResults();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl <em>Request Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getRequestResult()
		 * @generated
		 */
		EClass REQUEST_RESULT = eINSTANCE.getRequestResult();

		/**
		 * The meta object literal for the '<em><b>Result Collection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST_RESULT__RESULT_COLLECTION = eINSTANCE.getRequestResult_ResultCollection();

		/**
		 * The meta object literal for the '<em><b>Request Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REQUEST_RESULT__REQUEST_ID = eINSTANCE.getRequestResult_RequestId();

		/**
		 * The meta object literal for the '<em><b>Counting Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST_RESULT__COUNTING_RESULTS = eINSTANCE.getRequestResult_CountingResults();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl <em>Counting Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getCountingResult()
		 * @generated
		 */
		EClass COUNTING_RESULT = eINSTANCE.getCountingResult();

		/**
		 * The meta object literal for the '<em><b>Array Creation Counts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__ARRAY_CREATION_COUNTS = eINSTANCE.getCountingResult_ArrayCreationCounts();

		/**
		 * The meta object literal for the '<em><b>Caller Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__CALLER_ID = eINSTANCE.getCountingResult_CallerId();

		/**
		 * The meta object literal for the '<em><b>Method Call Counts</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__METHOD_CALL_COUNTS = eINSTANCE.getCountingResult_MethodCallCounts();

		/**
		 * The meta object literal for the '<em><b>Method Invocation Beginning</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__METHOD_INVOCATION_BEGINNING = eINSTANCE.getCountingResult_MethodInvocationBeginning();

		/**
		 * The meta object literal for the '<em><b>Method Reporting Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__METHOD_REPORTING_TIME = eINSTANCE.getCountingResult_MethodReportingTime();

		/**
		 * The meta object literal for the '<em><b>Observed Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__OBSERVED_ELEMENT = eINSTANCE.getCountingResult_ObservedElement();

		/**
		 * The meta object literal for the '<em><b>Opcode Counts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__OPCODE_COUNTS = eINSTANCE.getCountingResult_OpcodeCounts();

		/**
		 * The meta object literal for the '<em><b>Own Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__OWN_ID = eINSTANCE.getCountingResult_OwnId();

		/**
		 * The meta object literal for the '<em><b>Qualifying Method Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__QUALIFYING_METHOD_NAME = eINSTANCE.getCountingResult_QualifyingMethodName();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl <em>Threaded Counting Result</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getThreadedCountingResult()
		 * @generated
		 */
		EClass THREADED_COUNTING_RESULT = eINSTANCE.getThreadedCountingResult();

		/**
		 * The meta object literal for the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS = eINSTANCE.getThreadedCountingResult_SpawnedThreadedCountingResults();

		/**
		 * The meta object literal for the '<em><b>Thread Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute THREADED_COUNTING_RESULT__THREAD_ID = eINSTANCE.getThreadedCountingResult_ThreadId();

	}

} //OutputPackage
