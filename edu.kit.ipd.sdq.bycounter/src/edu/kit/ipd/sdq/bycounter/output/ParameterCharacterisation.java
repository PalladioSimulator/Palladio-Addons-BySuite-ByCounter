/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Characterisation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getCharacterisation <em>Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance <em>Parameter Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterCharacterisation()
 * @model abstract="true"
 * @generated
 */
public interface ParameterCharacterisation extends Identifier {
	/**
	 * Returns the value of the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Characterisation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Characterisation</em>' attribute.
	 * @see #setCharacterisation(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterCharacterisation_Characterisation()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getCharacterisation();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getCharacterisation <em>Characterisation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Characterisation</em>' attribute.
	 * @see #getCharacterisation()
	 * @generated
	 */
	void setCharacterisation(String value);

	/**
	 * Returns the value of the '<em><b>Parameter Instance</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getParameterCharacterisation <em>Parameter Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Instance</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Instance</em>' container reference.
	 * @see #setParameterInstance(ParameterInstance)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getParameterCharacterisation_ParameterInstance()
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getParameterCharacterisation
	 * @model opposite="parameterCharacterisation" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ParameterInstance getParameterInstance();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance <em>Parameter Instance</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Instance</em>' container reference.
	 * @see #getParameterInstance()
	 * @generated
	 */
	void setParameterInstance(ParameterInstance value);

} // ParameterCharacterisation
