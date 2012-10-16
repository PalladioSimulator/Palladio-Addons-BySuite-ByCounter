/**
 */
package edu.kit.ipd.sdq.bycounter.output;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Counting Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationStartTime <em>Method Invocation Start Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getReportingTime <em>Reporting Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOpcodeCounts <em>Opcode Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId <em>Method Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifiedMethodName <em>Qualified Method Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection <em>Result Collection</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadId <em>Thread Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#isFinal <em>Final</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult <em>Request Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult()
 * @model
 * @generated
 */
public interface CountingResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Caller Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ID of the method that invoked the method of the result. Can be null if the caller was not instrumented.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Caller Id</em>' containment reference.
	 * @see #setCallerId(UUID)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_CallerId()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	UUID getCallerId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller Id</em>' containment reference.
	 * @see #getCallerId()
	 * @generated
	 */
	void setCallerId(UUID value);

	/**
	 * Returns the value of the '<em><b>Method Invocation Start Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time at which the method that produced this result was invoked.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Invocation Start Time</em>' attribute.
	 * @see #setMethodInvocationStartTime(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodInvocationStartTime()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMethodInvocationStartTime();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationStartTime <em>Method Invocation Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Invocation Start Time</em>' attribute.
	 * @see #getMethodInvocationStartTime()
	 * @generated
	 */
	void setMethodInvocationStartTime(long value);

	/**
	 * Returns the value of the '<em><b>Reporting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time at which the result was reported.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reporting Time</em>' attribute.
	 * @see #setReportingTime(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ReportingTime()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getReportingTime();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getReportingTime <em>Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reporting Time</em>' attribute.
	 * @see #getReportingTime()
	 * @generated
	 */
	void setReportingTime(long value);

	/**
	 * Returns the value of the '<em><b>Observed Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Observed Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Element</em>' reference.
	 * @see #setObservedElement(EntityToInstrument)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ObservedElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EntityToInstrument getObservedElement();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Observed Element</em>' reference.
	 * @see #getObservedElement()
	 * @generated
	 */
	void setObservedElement(EntityToInstrument value);

	/**
	 * Returns the value of the '<em><b>Opcode Counts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Long}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A list of counts for all possible opcodes, even if a particular opcode was not counted, i.e. the count is 0.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Opcode Counts</em>' attribute list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_OpcodeCounts()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<Long> getOpcodeCounts();

	/**
	 * Returns the value of the '<em><b>Method Id</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ID of the method of this result.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Id</em>' containment reference.
	 * @see #setMethodId(UUID)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodId()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	UUID getMethodId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId <em>Method Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Id</em>' containment reference.
	 * @see #getMethodId()
	 * @generated
	 */
	void setMethodId(UUID value);

	/**
	 * Returns the value of the '<em><b>Qualified Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Method Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Method Name</em>' attribute.
	 * @see #setQualifiedMethodName(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_QualifiedMethodName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getQualifiedMethodName();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifiedMethodName <em>Qualified Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualified Method Name</em>' attribute.
	 * @see #getQualifiedMethodName()
	 * @generated
	 */
	void setQualifiedMethodName(String value);

	/**
	 * Returns the value of the '<em><b>Result Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getCountingResults <em>Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Collection</em>' container reference.
	 * @see #setResultCollection(ResultCollection)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ResultCollection()
	 * @see edu.kit.ipd.sdq.bycounter.output.ResultCollection#getCountingResults
	 * @model opposite="countingResults" transient="false" ordered="false"
	 * @generated
	 */
	ResultCollection getResultCollection();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection <em>Result Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Collection</em>' container reference.
	 * @see #getResultCollection()
	 * @generated
	 */
	void setResultCollection(ResultCollection value);

	/**
	 * Returns the value of the '<em><b>Array Creation Counts</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Creation Counts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Creation Counts</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ArrayCreationCounts()
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult
	 * @model opposite="countingResult" containment="true" ordered="false"
	 * @generated
	 */
	EList<ArrayCreationCount> getArrayCreationCounts();

	/**
	 * Returns the value of the '<em><b>Method Call Counts</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult <em>Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Call Counts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Call Counts</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodCallCounts()
	 * @see edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult
	 * @model opposite="countingResult" containment="true" ordered="false"
	 * @generated
	 */
	EList<MethodCallCount> getMethodCallCounts();

	/**
	 * Returns the value of the '<em><b>Thread Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thread Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thread Id</em>' attribute.
	 * @see #setThreadId(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ThreadId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getThreadId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadId <em>Thread Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thread Id</em>' attribute.
	 * @see #getThreadId()
	 * @generated
	 */
	void setThreadId(long value);

	/**
	 * Returns the value of the '<em><b>Final</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, the result is complete and all field have their final value.
	 * When false, the result exists to allow for the correct result structure. The values of fields and references however are not all determined yet. In particular, counts for opcodes, method calls or array creations are subject to change.
	 * An instance in which final is used is a result for code which spawns threads. In order to correctly create a parent->child thread result relationship, an incomplete result (i.e. final=false) is created that correctly references the spawned result but is incomplete until the entirety of the instrumented element is executed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Final</em>' attribute.
	 * @see #setFinal(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_Final()
	 * @model default="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * Returns the value of the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spawned Threaded Counting Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spawned Threaded Counting Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_SpawnedThreadedCountingResults()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult
	 * @model opposite="threadedCountingResult" containment="true" ordered="false"
	 * @generated
	 */
	EList<CountingResult> getSpawnedThreadedCountingResults();

	/**
	 * Returns the value of the '<em><b>Threaded Counting Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Threaded Counting Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Threaded Counting Result</em>' container reference.
	 * @see #setThreadedCountingResult(CountingResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ThreadedCountingResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getSpawnedThreadedCountingResults
	 * @model opposite="spawnedThreadedCountingResults" transient="false" ordered="false"
	 * @generated
	 */
	CountingResult getThreadedCountingResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threaded Counting Result</em>' container reference.
	 * @see #getThreadedCountingResult()
	 * @generated
	 */
	void setThreadedCountingResult(CountingResult value);

	/**
	 * Returns the value of the '<em><b>Request Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults <em>Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Result</em>' container reference.
	 * @see #setRequestResult(RequestResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_RequestResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults
	 * @model opposite="countingResults" transient="false" ordered="false"
	 * @generated
	 */
	RequestResult getRequestResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult <em>Request Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request Result</em>' container reference.
	 * @see #getRequestResult()
	 * @generated
	 */
	void setRequestResult(RequestResult value);

} // CountingResult
