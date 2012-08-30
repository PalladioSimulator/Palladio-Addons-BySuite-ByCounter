/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.fzi.gast.functions.Function;
import de.fzi.gast.functions.Method;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instrumented Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartMethod <em>Start Method</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopMethod <em>Stop Method</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopLine <em>Stop Line</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedRegion()
 * @model
 * @generated
 */
public interface InstrumentedRegion extends EntityToInstrument {
	/**
	 * Returns the value of the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Absolute line number in the file defining startMethod.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Start Line</em>' attribute.
	 * @see #setStartLine(int)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedRegion_StartLine()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getStartLine();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartLine <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Line</em>' attribute.
	 * @see #getStartLine()
	 * @generated
	 */
	void setStartLine(int value);

	/**
	 * Returns the value of the '<em><b>Stop Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stop Line</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Absolute line number in the file defining stopMethod.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stop Line</em>' attribute.
	 * @see #setStopLine(int)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedRegion_StopLine()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getStopLine();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopLine <em>Stop Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Line</em>' attribute.
	 * @see #getStopLine()
	 * @generated
	 */
	void setStopLine(int value);

	/**
	 * Returns the value of the '<em><b>Start Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Method</em>' reference.
	 * @see #setStartMethod(Function)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedRegion_StartMethod()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Function getStartMethod();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStartMethod <em>Start Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Method</em>' reference.
	 * @see #getStartMethod()
	 * @generated
	 */
	void setStartMethod(Function value);

	/**
	 * Returns the value of the '<em><b>Stop Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stop Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stop Method</em>' reference.
	 * @see #setStopMethod(Function)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedRegion_StopMethod()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Function getStopMethod();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion#getStopMethod <em>Stop Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stop Method</em>' reference.
	 * @see #getStopMethod()
	 * @generated
	 */
	void setStopMethod(Function value);

} // InstrumentedRegion
