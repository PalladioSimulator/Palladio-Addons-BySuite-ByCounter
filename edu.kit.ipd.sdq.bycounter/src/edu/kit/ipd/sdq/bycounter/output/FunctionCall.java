/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.fzi.gast.functions.Function;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getParameterInstance <em>Parameter Instance</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getFunction <em>Function</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getNumberObservations <em>Number Observations</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isNative <em>Native</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands <em>Resource Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall()
 * @model
 * @generated
 */
public interface FunctionCall extends Identifier {
	/**
	 * Returns the value of the '<em><b>Parameter Instance</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Instance</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Instance</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_ParameterInstance()
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall
	 * @model opposite="functionCall" containment="true" ordered="false"
	 * @generated
	 */
	EList<ParameterInstance> getParameterInstance();

	/**
	 * Returns the value of the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function</em>' reference.
	 * @see #setFunction(Function)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_Function()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Function getFunction();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getFunction <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(Function value);

	/**
	 * Returns the value of the '<em><b>Number Observations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Observations</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Observations</em>' attribute.
	 * @see #setNumberObservations(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_NumberObservations()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getNumberObservations();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getNumberObservations <em>Number Observations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Observations</em>' attribute.
	 * @see #getNumberObservations()
	 * @generated
	 */
	void setNumberObservations(long value);

	/**
	 * Returns the value of the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Native</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Native</em>' attribute.
	 * @see #setNative(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_Native()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isNative();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isNative <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Native</em>' attribute.
	 * @see #isNative()
	 * @generated
	 */
	void setNative(boolean value);

	/**
	 * Returns the value of the '<em><b>Synchronized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The entire method is synchronized, not just a section (or several) inside it.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Synchronized</em>' attribute.
	 * @see #setSynchronized(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_Synchronized()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	boolean isSynchronized();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isSynchronized <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronized</em>' attribute.
	 * @see #isSynchronized()
	 * @generated
	 */
	void setSynchronized(boolean value);

	/**
	 * Returns the value of the '<em><b>Resource Demands</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getFunctionCalls <em>Function Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Demands</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Demands</em>' container reference.
	 * @see #setResourceDemands(ResourceDemands)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getFunctionCall_ResourceDemands()
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getFunctionCalls
	 * @model opposite="functionCalls" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ResourceDemands getResourceDemands();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands <em>Resource Demands</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Demands</em>' container reference.
	 * @see #getResourceDemands()
	 * @generated
	 */
	void setResourceDemands(ResourceDemands value);

} // FunctionCall
