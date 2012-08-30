/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationBeginning <em>Method Invocation Beginning</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getReportingTime <em>Reporting Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOpcodeCounts <em>Opcode Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId <em>Method Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifiedMethodName <em>Qualified Method Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection <em>Result Collection</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}</li>
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
	 * Returns the value of the '<em><b>Array Creation Counts</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Creation Counts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Creation Counts</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ArrayCreationCounts()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ArrayCreationCount> getArrayCreationCounts();

	/**
	 * Returns the value of the '<em><b>Caller Id</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ID of the method that invoked the method of the result. Can be null if the caller was not instrumented.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Caller Id</em>' reference.
	 * @see #setCallerId(UUID)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_CallerId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	UUID getCallerId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller Id</em>' reference.
	 * @see #getCallerId()
	 * @generated
	 */
	void setCallerId(UUID value);

	/**
	 * Returns the value of the '<em><b>Method Call Counts</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Call Counts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Call Counts</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodCallCounts()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<MethodCallCount> getMethodCallCounts();

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

	/**
	 * Returns the value of the '<em><b>Method Invocation Beginning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Invocation Beginning</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time at which the method that produced this result was invoked.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Invocation Beginning</em>' attribute.
	 * @see #setMethodInvocationBeginning(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodInvocationBeginning()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMethodInvocationBeginning();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationBeginning <em>Method Invocation Beginning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Invocation Beginning</em>' attribute.
	 * @see #getMethodInvocationBeginning()
	 * @generated
	 */
	void setMethodInvocationBeginning(long value);

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
	 * If the meaning of the '<em>Observed Element</em>' attribute isn't clear,
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
	 * <p>
	 * If the meaning of the '<em>Opcode Counts</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Opcode Counts</em>' attribute list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_OpcodeCounts()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Long> getOpcodeCounts();

	/**
	 * Returns the value of the '<em><b>Method Id</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The ID of the method of this result.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Method Id</em>' reference.
	 * @see #setMethodId(UUID)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	UUID getMethodId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodId <em>Method Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Id</em>' reference.
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

} // CountingResult
