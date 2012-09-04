/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Request Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getCountingResults <em>Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult()
 * @model
 * @generated
 */
public interface RequestResult extends Identifier {
	/**
	 * Returns the value of the '<em><b>Counting Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.CountingResult}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult <em>Request Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult_CountingResults()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getRequestResult
	 * @model opposite="requestResult" containment="true" ordered="false"
	 * @generated
	 */
	EList<CountingResult> getCountingResults();

	/**
	 * Returns the value of the '<em><b>Result Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ResultCollection#getRequestResults <em>Request Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Collection</em>' container reference.
	 * @see #setResultCollection(ResultCollection)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequestResult_ResultCollection()
	 * @see edu.kit.ipd.sdq.bycounter.output.ResultCollection#getRequestResults
	 * @model opposite="requestResults" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ResultCollection getResultCollection();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.RequestResult#getResultCollection <em>Result Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Collection</em>' container reference.
	 * @see #getResultCollection()
	 * @generated
	 */
	void setResultCollection(ResultCollection value);

} // RequestResult
