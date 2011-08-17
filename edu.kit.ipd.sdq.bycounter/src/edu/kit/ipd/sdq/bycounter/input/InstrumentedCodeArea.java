/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.fzi.gast.statements.Statement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instrumented Code Area</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getFrom <em>From</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedCodeArea()
 * @model
 * @generated
 */
public interface InstrumentedCodeArea extends EntityToInstrument {
	/**
	 * Returns the value of the '<em><b>From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' reference.
	 * @see #setFrom(Statement)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedCodeArea_From()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Statement getFrom();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getFrom <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' reference.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(Statement value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' reference.
	 * @see #setTo(Statement)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedCodeArea_To()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Statement getTo();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea#getTo <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' reference.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(Statement value);

} // InstrumentedCodeArea
