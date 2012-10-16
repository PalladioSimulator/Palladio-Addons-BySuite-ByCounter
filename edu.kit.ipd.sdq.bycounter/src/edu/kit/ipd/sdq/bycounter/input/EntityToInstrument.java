/**
 */
package edu.kit.ipd.sdq.bycounter.input;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity To Instrument</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile <em>Instrumentation Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getEntityToInstrument()
 * @model abstract="true"
 * @generated
 */
public interface EntityToInstrument extends EObject {
	/**
	 * Returns the value of the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument <em>Entities To Instrument</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrumentation Profile</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instrumentation Profile</em>' container reference.
	 * @see #setInstrumentationProfile(InstrumentationProfile)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getEntityToInstrument_InstrumentationProfile()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument
	 * @model opposite="entitiesToInstrument" transient="false" ordered="false"
	 * @generated
	 */
	InstrumentationProfile getInstrumentationProfile();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile <em>Instrumentation Profile</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrumentation Profile</em>' container reference.
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	void setInstrumentationProfile(InstrumentationProfile value);

} // EntityToInstrument
