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
 * A representation of the model object '<em><b>Method Call Count</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getQualifiedFunctionName <em>Qualified Function Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount()
 * @model
 * @generated
 */
public interface MethodCallCount extends EObject {
	/**
	 * Returns the value of the '<em><b>Qualified Function Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualified Function Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualified Function Name</em>' attribute.
	 * @see #setQualifiedFunctionName(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount_QualifiedFunctionName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getQualifiedFunctionName();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getQualifiedFunctionName <em>Qualified Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualified Function Name</em>' attribute.
	 * @see #getQualifiedFunctionName()
	 * @generated
	 */
	void setQualifiedFunctionName(Object value);

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
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount_Count()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getCount();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(long value);

} // MethodCallCount
