/**
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	 * The feature id for the '<em><b>Result Collection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY__RESULT_COLLECTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Counting Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__COUNTING_RESULTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Result Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT__RESULT_COLLECTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Request Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_RESULT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

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
	 * The feature id for the '<em><b>Caller Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__CALLER_ID = 0;

	/**
	 * The feature id for the '<em><b>Method Invocation Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_INVOCATION_START_TIME = 1;

	/**
	 * The feature id for the '<em><b>Reporting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__REPORTING_TIME = 2;

	/**
	 * The feature id for the '<em><b>Observed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__OBSERVED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Opcode Counts</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__OPCODE_COUNTS = 4;

	/**
	 * The feature id for the '<em><b>Method Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_ID = 5;

	/**
	 * The feature id for the '<em><b>Qualified Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__QUALIFIED_METHOD_NAME = 6;

	/**
	 * The feature id for the '<em><b>Result Collection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__RESULT_COLLECTION = 7;

	/**
	 * The feature id for the '<em><b>Array Creation Counts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__ARRAY_CREATION_COUNTS = 8;

	/**
	 * The feature id for the '<em><b>Method Call Counts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__METHOD_CALL_COUNTS = 9;

	/**
	 * The feature id for the '<em><b>Thread Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__THREAD_ID = 10;

	/**
	 * The feature id for the '<em><b>Final</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__FINAL = 11;

	/**
	 * The feature id for the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS = 12;

	/**
	 * The feature id for the '<em><b>Threaded Counting Result</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__THREADED_COUNTING_RESULT = 13;

	/**
	 * The feature id for the '<em><b>Request Result</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT__REQUEST_RESULT = 14;

	/**
	 * The number of structural features of the '<em>Counting Result</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COUNTING_RESULT_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.UUIDImpl <em>UUID</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.UUIDImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getUUID()
	 * @generated
	 */
	int UUID = 4;

	/**
	 * The feature id for the '<em><b>String Representation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UUID__STRING_REPRESENTATION = 0;

	/**
	 * The number of structural features of the '<em>UUID</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UUID_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl <em>Array Creation Count</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getArrayCreationCount()
	 * @generated
	 */
	int ARRAY_CREATION_COUNT = 5;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT__COUNT = 0;

	/**
	 * The feature id for the '<em><b>Type Descriptor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT__TYPE_DESCRIPTOR = 1;

	/**
	 * The feature id for the '<em><b>Number Of Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT__NUMBER_OF_DIMENSIONS = 2;

	/**
	 * The feature id for the '<em><b>Array Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT__ARRAY_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Counting Result</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT__COUNTING_RESULT = 4;

	/**
	 * The number of structural features of the '<em>Array Creation Count</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_CREATION_COUNT_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl <em>Method Call Count</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getMethodCallCount()
	 * @generated
	 */
	int METHOD_CALL_COUNT = 6;

	/**
	 * The feature id for the '<em><b>Qualified Function Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME = 0;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CALL_COUNT__COUNT = 1;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CALL_COUNT__FUNCTION = 2;

	/**
	 * The feature id for the '<em><b>Counting Result</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CALL_COUNT__COUNTING_RESULT = 3;

	/**
	 * The number of structural features of the '<em>Method Call Count</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_CALL_COUNT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayType <em>Array Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getArrayType()
	 * @generated
	 */
	int ARRAY_TYPE = 7;


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
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Result Collection</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getResultCollection()
	 * @see #getOutputModelRepository()
	 * @generated
	 */
	EReference getOutputModelRepository_ResultCollection();

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
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Result Collection</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection()
	 * @see #getRequestResult()
	 * @generated
	 */
	EReference getRequestResult_ResultCollection();

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
	 * Returns the meta object for the containment reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Caller Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_CallerId();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationStartTime <em>Method Invocation Start Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Invocation Start Time</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationStartTime()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_MethodInvocationStartTime();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getReportingTime <em>Reporting Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reporting Time</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getReportingTime()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_ReportingTime();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Observed Element</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_ObservedElement();

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
	 * Returns the meta object for the containment reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId <em>Method Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Method Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_MethodId();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifiedMethodName <em>Qualified Method Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Method Name</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifiedMethodName()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_QualifiedMethodName();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Result Collection</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_ResultCollection();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Array Creation Counts</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_ArrayCreationCounts();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Method Call Counts</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_MethodCallCounts();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadId <em>Thread Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Thread Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadId()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_ThreadId();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#isFinal <em>Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Final</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#isFinal()
	 * @see #getCountingResult()
	 * @generated
	 */
	EAttribute getCountingResult_Final();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spawned Threaded Counting Results</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getSpawnedThreadedCountingResults()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_SpawnedThreadedCountingResults();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Threaded Counting Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_ThreadedCountingResult();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult <em>Request Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Request Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult()
	 * @see #getCountingResult()
	 * @generated
	 */
	EReference getCountingResult_RequestResult();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.UUID <em>UUID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UUID</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.UUID
	 * @generated
	 */
	EClass getUUID();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.UUID#getStringRepresentation <em>String Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Representation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.UUID#getStringRepresentation()
	 * @see #getUUID()
	 * @generated
	 */
	EAttribute getUUID_StringRepresentation();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount <em>Array Creation Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Creation Count</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount
	 * @generated
	 */
	EClass getArrayCreationCount();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount()
	 * @see #getArrayCreationCount()
	 * @generated
	 */
	EAttribute getArrayCreationCount_Count();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getTypeDescriptor <em>Type Descriptor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type Descriptor</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getTypeDescriptor()
	 * @see #getArrayCreationCount()
	 * @generated
	 */
	EAttribute getArrayCreationCount_TypeDescriptor();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getNumberOfDimensions <em>Number Of Dimensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Of Dimensions</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getNumberOfDimensions()
	 * @see #getArrayCreationCount()
	 * @generated
	 */
	EAttribute getArrayCreationCount_NumberOfDimensions();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Array Type</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayType()
	 * @see #getArrayCreationCount()
	 * @generated
	 */
	EAttribute getArrayCreationCount_ArrayType();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Counting Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult()
	 * @see #getArrayCreationCount()
	 * @generated
	 */
	EReference getArrayCreationCount_CountingResult();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount <em>Method Call Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method Call Count</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount
	 * @generated
	 */
	EClass getMethodCallCount();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getQualifiedFunctionName <em>Qualified Function Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Function Name</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getQualifiedFunctionName()
	 * @see #getMethodCallCount()
	 * @generated
	 */
	EAttribute getMethodCallCount_QualifiedFunctionName();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCount()
	 * @see #getMethodCallCount()
	 * @generated
	 */
	EAttribute getMethodCallCount_Count();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getFunction()
	 * @see #getMethodCallCount()
	 * @generated
	 */
	EReference getMethodCallCount_Function();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult <em>Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Counting Result</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult()
	 * @see #getMethodCallCount()
	 * @generated
	 */
	EReference getMethodCallCount_CountingResult();

	/**
	 * Returns the meta object for enum '{@link edu.kit.ipd.sdq.bycounter.output.ArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Array Type</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
	 * @generated
	 */
	EEnum getArrayType();

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
		 * The meta object literal for the '<em><b>Result Collection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_MODEL_REPOSITORY__RESULT_COLLECTION = eINSTANCE.getOutputModelRepository_ResultCollection();

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
		 * The meta object literal for the '<em><b>Counting Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST_RESULT__COUNTING_RESULTS = eINSTANCE.getRequestResult_CountingResults();

		/**
		 * The meta object literal for the '<em><b>Result Collection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST_RESULT__RESULT_COLLECTION = eINSTANCE.getRequestResult_ResultCollection();

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
		 * The meta object literal for the '<em><b>Caller Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__CALLER_ID = eINSTANCE.getCountingResult_CallerId();

		/**
		 * The meta object literal for the '<em><b>Method Invocation Start Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__METHOD_INVOCATION_START_TIME = eINSTANCE.getCountingResult_MethodInvocationStartTime();

		/**
		 * The meta object literal for the '<em><b>Reporting Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__REPORTING_TIME = eINSTANCE.getCountingResult_ReportingTime();

		/**
		 * The meta object literal for the '<em><b>Observed Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__OBSERVED_ELEMENT = eINSTANCE.getCountingResult_ObservedElement();

		/**
		 * The meta object literal for the '<em><b>Opcode Counts</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__OPCODE_COUNTS = eINSTANCE.getCountingResult_OpcodeCounts();

		/**
		 * The meta object literal for the '<em><b>Method Id</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__METHOD_ID = eINSTANCE.getCountingResult_MethodId();

		/**
		 * The meta object literal for the '<em><b>Qualified Method Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__QUALIFIED_METHOD_NAME = eINSTANCE.getCountingResult_QualifiedMethodName();

		/**
		 * The meta object literal for the '<em><b>Result Collection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__RESULT_COLLECTION = eINSTANCE.getCountingResult_ResultCollection();

		/**
		 * The meta object literal for the '<em><b>Array Creation Counts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__ARRAY_CREATION_COUNTS = eINSTANCE.getCountingResult_ArrayCreationCounts();

		/**
		 * The meta object literal for the '<em><b>Method Call Counts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__METHOD_CALL_COUNTS = eINSTANCE.getCountingResult_MethodCallCounts();

		/**
		 * The meta object literal for the '<em><b>Thread Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__THREAD_ID = eINSTANCE.getCountingResult_ThreadId();

		/**
		 * The meta object literal for the '<em><b>Final</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COUNTING_RESULT__FINAL = eINSTANCE.getCountingResult_Final();

		/**
		 * The meta object literal for the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS = eINSTANCE.getCountingResult_SpawnedThreadedCountingResults();

		/**
		 * The meta object literal for the '<em><b>Threaded Counting Result</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__THREADED_COUNTING_RESULT = eINSTANCE.getCountingResult_ThreadedCountingResult();

		/**
		 * The meta object literal for the '<em><b>Request Result</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COUNTING_RESULT__REQUEST_RESULT = eINSTANCE.getCountingResult_RequestResult();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.UUIDImpl <em>UUID</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.UUIDImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getUUID()
		 * @generated
		 */
		EClass UUID = eINSTANCE.getUUID();

		/**
		 * The meta object literal for the '<em><b>String Representation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UUID__STRING_REPRESENTATION = eINSTANCE.getUUID_StringRepresentation();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl <em>Array Creation Count</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getArrayCreationCount()
		 * @generated
		 */
		EClass ARRAY_CREATION_COUNT = eINSTANCE.getArrayCreationCount();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_CREATION_COUNT__COUNT = eINSTANCE.getArrayCreationCount_Count();

		/**
		 * The meta object literal for the '<em><b>Type Descriptor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_CREATION_COUNT__TYPE_DESCRIPTOR = eINSTANCE.getArrayCreationCount_TypeDescriptor();

		/**
		 * The meta object literal for the '<em><b>Number Of Dimensions</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_CREATION_COUNT__NUMBER_OF_DIMENSIONS = eINSTANCE.getArrayCreationCount_NumberOfDimensions();

		/**
		 * The meta object literal for the '<em><b>Array Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARRAY_CREATION_COUNT__ARRAY_TYPE = eINSTANCE.getArrayCreationCount_ArrayType();

		/**
		 * The meta object literal for the '<em><b>Counting Result</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_CREATION_COUNT__COUNTING_RESULT = eINSTANCE.getArrayCreationCount_CountingResult();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl <em>Method Call Count</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getMethodCallCount()
		 * @generated
		 */
		EClass METHOD_CALL_COUNT = eINSTANCE.getMethodCallCount();

		/**
		 * The meta object literal for the '<em><b>Qualified Function Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME = eINSTANCE.getMethodCallCount_QualifiedFunctionName();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD_CALL_COUNT__COUNT = eINSTANCE.getMethodCallCount_Count();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CALL_COUNT__FUNCTION = eINSTANCE.getMethodCallCount_Function();

		/**
		 * The meta object literal for the '<em><b>Counting Result</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference METHOD_CALL_COUNT__COUNTING_RESULT = eINSTANCE.getMethodCallCount_CountingResult();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayType <em>Array Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getArrayType()
		 * @generated
		 */
		EEnum ARRAY_TYPE = eINSTANCE.getArrayType();

	}

} //OutputPackage
