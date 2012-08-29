/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instrumentation Profile Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getInstrumentationProfile <em>Instrumentation Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfileRepository()
 * @model
 * @generated
 */
public interface InstrumentationProfileRepository extends Identifier {
	/**
	 * Returns the value of the '<em><b>Instrumentation Profile</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrumentation Profile</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instrumentation Profile</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfileRepository_InstrumentationProfile()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository
	 * @model opposite="intrumentationProfileRepository" containment="true" ordered="false"
	 * @generated
	 */
	EList<InstrumentationProfile> getInstrumentationProfile();

} // InstrumentationProfileRepository
