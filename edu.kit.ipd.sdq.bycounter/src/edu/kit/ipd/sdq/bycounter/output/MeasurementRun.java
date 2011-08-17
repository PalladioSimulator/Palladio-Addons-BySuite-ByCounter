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
 * A representation of the model object '<em><b>Measurement Run</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getEnvironmentCharacterisation <em>Environment Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getRequests <em>Requests</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMeasurementRun()
 * @model
 * @generated
 */
public interface MeasurementRun extends Identifier {
	/**
	 * Returns the value of the '<em><b>Environment Characterisation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment Characterisation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Characterisation</em>' reference.
	 * @see #setEnvironmentCharacterisation(EnvironmentDescription)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMeasurementRun_EnvironmentCharacterisation()
	 * @model ordered="false"
	 * @generated
	 */
	EnvironmentDescription getEnvironmentCharacterisation();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getEnvironmentCharacterisation <em>Environment Characterisation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment Characterisation</em>' reference.
	 * @see #getEnvironmentCharacterisation()
	 * @generated
	 */
	void setEnvironmentCharacterisation(EnvironmentDescription value);

	/**
	 * Returns the value of the '<em><b>Requests</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.Request}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun <em>Measurement Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requests</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getMeasurementRun_Requests()
	 * @see edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun
	 * @model opposite="measurementRun" containment="true" ordered="false"
	 * @generated
	 */
	EList<Request> getRequests();

} // MeasurementRun
