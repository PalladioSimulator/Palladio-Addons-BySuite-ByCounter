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
 * A representation of the model object '<em><b>Resource Demands</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Stores&nbsp;the measured&nbsp;resource demands&nbsp;for a single code area and single invocation, e.g. all JVM library
 * calls and byte code instructions for an internal action or an external method invocation for an external call.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getFunctionCalls <em>Function Calls</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getJavaVMCall <em>Java VM Call</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution <em>Observed Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResourceDemands()
 * @model
 * @generated
 */
public interface ResourceDemands extends Identifier {
	/**
	 * Returns the value of the '<em><b>Function Calls</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.FunctionCall}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Calls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Calls</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResourceDemands_FunctionCalls()
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands
	 * @model opposite="resourceDemands" containment="true" ordered="false"
	 * @generated
	 */
	EList<FunctionCall> getFunctionCalls();

	/**
	 * Returns the value of the '<em><b>Java VM Call</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Java VM Call</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Java VM Call</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResourceDemands_JavaVMCall()
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands
	 * @model opposite="resourceDemands" containment="true" ordered="false"
	 * @generated
	 */
	EList<JavaVMCall> getJavaVMCall();

	/**
	 * Returns the value of the '<em><b>Observed Execution</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Observed Execution</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Execution</em>' container reference.
	 * @see #setObservedExecution(ObservedEntityExecution)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getResourceDemands_ObservedExecution()
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands
	 * @model opposite="resourceDemands" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ObservedEntityExecution getObservedExecution();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution <em>Observed Execution</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Observed Execution</em>' container reference.
	 * @see #getObservedExecution()
	 * @generated
	 */
	void setObservedExecution(ObservedEntityExecution value);

} // ResourceDemands
