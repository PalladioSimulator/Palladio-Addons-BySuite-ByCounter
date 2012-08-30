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
 * A representation of the model object '<em><b>UUID</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.UUID#getStringRepresentation <em>String Representation</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getUUID()
 * @model
 * @generated
 */
public interface UUID extends EObject {
	/**
	 * Returns the value of the '<em><b>String Representation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Representation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Representation</em>' attribute.
	 * @see #setStringRepresentation(Object)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getUUID_StringRepresentation()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Object getStringRepresentation();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.UUID#getStringRepresentation <em>String Representation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Representation</em>' attribute.
	 * @see #getStringRepresentation()
	 * @generated
	 */
	void setStringRepresentation(Object value);

} // UUID
