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
 * A representation of the model object '<em><b>Result Collection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getRequestResults <em>Request Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getCountingResults <em>Counting Results</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResultCollection()
 * @model
 * @generated
 */
public interface ResultCollection extends EObject {
	/**
	 * Returns the value of the '<em><b>Request Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.RequestResult}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResultCollection_RequestResults()
	 * @see edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection
	 * @model opposite="resultCollection" containment="true" ordered="false"
	 * @generated
	 */
	EList<RequestResult> getRequestResults();

	/**
	 * Returns the value of the '<em><b>Counting Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection <em>Result Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResultCollection_CountingResults()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getResultCollection
	 * @model opposite="resultCollection" containment="true" ordered="false"
	 * @generated
	 */
	EList<CountingResult> getCountingResults();

} // ResultCollection
