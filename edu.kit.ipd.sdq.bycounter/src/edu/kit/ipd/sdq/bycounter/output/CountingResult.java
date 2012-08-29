/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

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
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodInvocationBeginning <em>Method Invocation Beginning</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodReportingTime <em>Method Reporting Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOpcodeCounts <em>Opcode Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOwnId <em>Own Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifyingMethodName <em>Qualifying Method Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult()
 * @model
 * @generated
 */
public interface CountingResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Array Creation Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Creation Counts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Creation Counts</em>' attribute.
	 * @see #setArrayCreationCounts(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ArrayCreationCounts()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getArrayCreationCounts();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Creation Counts</em>' attribute.
	 * @see #getArrayCreationCounts()
	 * @generated
	 */
	void setArrayCreationCounts(Object value);

	/**
	 * Returns the value of the '<em><b>Caller Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caller Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caller Id</em>' attribute.
	 * @see #setCallerId(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_CallerId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getCallerId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getCallerId <em>Caller Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caller Id</em>' attribute.
	 * @see #getCallerId()
	 * @generated
	 */
	void setCallerId(Object value);

	/**
	 * Returns the value of the '<em><b>Method Call Counts</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Call Counts</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Call Counts</em>' attribute.
	 * @see #setMethodCallCounts(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodCallCounts()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getMethodCallCounts();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Call Counts</em>' attribute.
	 * @see #getMethodCallCounts()
	 * @generated
	 */
	void setMethodCallCounts(Object value);

	/**
	 * Returns the value of the '<em><b>Method Invocation Beginning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Invocation Beginning</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * Returns the value of the '<em><b>Method Reporting Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method Reporting Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method Reporting Time</em>' attribute.
	 * @see #setMethodReportingTime(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_MethodReportingTime()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMethodReportingTime();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodReportingTime <em>Method Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method Reporting Time</em>' attribute.
	 * @see #getMethodReportingTime()
	 * @generated
	 */
	void setMethodReportingTime(long value);

	/**
	 * Returns the value of the '<em><b>Observed Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Observed Element</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Element</em>' attribute.
	 * @see #setObservedElement(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_ObservedElement()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getObservedElement();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getObservedElement <em>Observed Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Observed Element</em>' attribute.
	 * @see #getObservedElement()
	 * @generated
	 */
	void setObservedElement(Object value);

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
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<Long> getOpcodeCounts();

	/**
	 * Returns the value of the '<em><b>Own Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Own Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Own Id</em>' attribute.
	 * @see #setOwnId(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_OwnId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getOwnId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getOwnId <em>Own Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Own Id</em>' attribute.
	 * @see #getOwnId()
	 * @generated
	 */
	void setOwnId(Object value);

	/**
	 * Returns the value of the '<em><b>Qualifying Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifying Method Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifying Method Name</em>' attribute.
	 * @see #setQualifyingMethodName(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getCountingResult_QualifyingMethodName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getQualifyingMethodName();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getQualifyingMethodName <em>Qualifying Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifying Method Name</em>' attribute.
	 * @see #getQualifyingMethodName()
	 * @generated
	 */
	void setQualifyingMethodName(String value);

} // CountingResult
