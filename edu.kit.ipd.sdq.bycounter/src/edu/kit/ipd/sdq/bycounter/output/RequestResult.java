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
 * A representation of the model object '<em><b>Request Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getRequestId <em>Request Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults <em>Counting Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult()
 * @model
 * @generated
 */
public interface RequestResult extends EObject {
	/**
	 * Returns the value of the '<em><b>Result Collection</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ResultCollection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Collection</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Collection</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult_ResultCollection()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ResultCollection> getResultCollection();

	/**
	 * Returns the value of the '<em><b>Request Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Id</em>' attribute.
	 * @see #setRequestId(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult_RequestId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getRequestId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getRequestId <em>Request Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request Id</em>' attribute.
	 * @see #getRequestId()
	 * @generated
	 */
	void setRequestId(Object value);

	/**
	 * Returns the value of the '<em><b>Counting Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult_CountingResults()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<CountingResult> getCountingResults();

} // RequestResult
