/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Creation Count</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayCreation <em>Array Creation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount <em>Count</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount()
 * @model
 * @generated
 */
public interface ArrayCreationCount extends EObject {
	/**
	 * Returns the value of the '<em><b>Array Creation</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getArrayCreationCount <em>Array Creation Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Array Creation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Array Creation</em>' containment reference.
	 * @see #setArrayCreation(ArrayCreation)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_ArrayCreation()
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getArrayCreationCount
	 * @model opposite="arrayCreationCount" containment="true" required="true" ordered="false"
	 * @generated
	 */
	ArrayCreation getArrayCreation();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayCreation <em>Array Creation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Creation</em>' containment reference.
	 * @see #getArrayCreation()
	 * @generated
	 */
	void setArrayCreation(ArrayCreation value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_Count()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getCount();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(long value);

	/**
	 * Returns the value of the '<em><b>Counting Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Result</em>' container reference.
	 * @see #setCountingResult(CountingResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_CountingResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts
	 * @model opposite="arrayCreationCounts" required="true" transient="false" ordered="false"
	 * @generated
	 */
	CountingResult getCountingResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Counting Result</em>' container reference.
	 * @see #getCountingResult()
	 * @generated
	 */
	void setCountingResult(CountingResult value);

} // ArrayCreationCount
