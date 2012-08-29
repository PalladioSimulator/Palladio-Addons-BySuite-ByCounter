/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.fzi.gast.types.GASTClass;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logical Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInternalClasses <em>Internal Classes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInstrumentationProfile <em>Instrumentation Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getLogicalSet()
 * @model
 * @generated
 */
public interface LogicalSet extends Identifier {
	/**
	 * Returns the value of the '<em><b>Internal Classes</b></em>' reference list.
	 * The list contents are of type {@link de.fzi.gast.types.GASTClass}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Internal Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Internal Classes</em>' reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getLogicalSet_InternalClasses()
	 * @model ordered="false"
	 * @generated
	 */
	EList<GASTClass> getInternalClasses();

	/**
	 * Returns the value of the '<em><b>Instrumentation Profile</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getDefinedLogicalSets <em>Defined Logical Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrumentation Profile</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instrumentation Profile</em>' container reference.
	 * @see #setInstrumentationProfile(InstrumentationProfile)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getLogicalSet_InstrumentationProfile()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getDefinedLogicalSets
	 * @model opposite="definedLogicalSets" required="true" transient="false" ordered="false"
	 * @generated
	 */
	InstrumentationProfile getInstrumentationProfile();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInstrumentationProfile <em>Instrumentation Profile</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrumentation Profile</em>' container reference.
	 * @see #getInstrumentationProfile()
	 * @generated
	 */
	void setInstrumentationProfile(InstrumentationProfile value);

} // LogicalSet
