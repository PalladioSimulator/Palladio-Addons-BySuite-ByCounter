/**
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.fzi.gast.functions.Function;

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
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getFunction <em>Function</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult <em>Counting Result</em>}</li>
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
	 * @see #setQualifiedFunctionName(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount_QualifiedFunctionName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getQualifiedFunctionName();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getQualifiedFunctionName <em>Qualified Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualified Function Name</em>' attribute.
	 * @see #getQualifiedFunctionName()
	 * @generated
	 */
	void setQualifiedFunctionName(String value);

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

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reference to the called function (if mapping is available).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(Function)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount_Function()
	 * @model ordered="false"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

	/**
	 * Returns the value of the '<em><b>Counting Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts <em>Method Call Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Result</em>' container reference.
	 * @see #setCountingResult(CountingResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMethodCallCount_CountingResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getMethodCallCounts
	 * @model opposite="methodCallCounts" required="true" transient="false" ordered="false"
	 * @generated
	 */
	CountingResult getCountingResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MethodCallCount#getCountingResult <em>Counting Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Counting Result</em>' container reference.
	 * @see #getCountingResult()
	 * @generated
	 */
	void setCountingResult(CountingResult value);

} // MethodCallCount
