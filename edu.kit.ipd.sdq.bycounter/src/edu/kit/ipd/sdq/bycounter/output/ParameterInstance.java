/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.fzi.gast.variables.FormalParameter;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFormalParameter <em>Formal Parameter</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getParameterCharacterisation <em>Parameter Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall <em>Function Call</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterInstance()
 * @model
 * @generated
 */
public interface ParameterInstance extends Identifier {
	/**
	 * Returns the value of the '<em><b>Formal Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameter</em>' reference.
	 * @see #setFormalParameter(FormalParameter)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterInstance_FormalParameter()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	FormalParameter getFormalParameter();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFormalParameter <em>Formal Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Parameter</em>' reference.
	 * @see #getFormalParameter()
	 * @generated
	 */
	void setFormalParameter(FormalParameter value);

	/**
	 * Returns the value of the '<em><b>Parameter Characterisation</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance <em>Parameter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Characterisation</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Characterisation</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterInstance_ParameterCharacterisation()
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance
	 * @model opposite="parameterInstance" containment="true" ordered="false"
	 * @generated
	 */
	EList<ParameterCharacterisation> getParameterCharacterisation();

	/**
	 * Returns the value of the '<em><b>Function Call</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getParameterInstance <em>Parameter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Function Call</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Function Call</em>' container reference.
	 * @see #setFunctionCall(FunctionCall)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterInstance_FunctionCall()
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getParameterInstance
	 * @model opposite="parameterInstance" required="true" transient="false" ordered="false"
	 * @generated
	 */
	FunctionCall getFunctionCall();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall <em>Function Call</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Call</em>' container reference.
	 * @see #getFunctionCall()
	 * @generated
	 */
	void setFunctionCall(FunctionCall value);

} // ParameterInstance
