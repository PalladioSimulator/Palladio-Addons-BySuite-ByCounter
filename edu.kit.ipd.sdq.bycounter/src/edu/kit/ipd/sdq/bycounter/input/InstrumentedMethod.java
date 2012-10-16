/**
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.fzi.gast.functions.Method;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instrumented Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getImplementationOrDerived <em>Implementation Or Derived</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent <em>Declaration Or Parent</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#isInstrumentDerived <em>Instrument Derived</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedMethod()
 * @model
 * @generated
 */
public interface InstrumentedMethod extends EntityToInstrument {
	/**
	 * Returns the value of the '<em><b>Implementation Or Derived</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent <em>Declaration Or Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation Or Derived</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation Or Derived</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedMethod_ImplementationOrDerived()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent
	 * @model opposite="declarationOrParent" containment="true" ordered="false"
	 * @generated
	 */
	EList<InstrumentedMethod> getImplementationOrDerived();

	/**
	 * Returns the value of the '<em><b>Declaration Or Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getImplementationOrDerived <em>Implementation Or Derived</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Declaration Or Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Declaration Or Parent</em>' container reference.
	 * @see #setDeclarationOrParent(InstrumentedMethod)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedMethod_DeclarationOrParent()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getImplementationOrDerived
	 * @model opposite="implementationOrDerived" transient="false" ordered="false"
	 * @generated
	 */
	InstrumentedMethod getDeclarationOrParent();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getDeclarationOrParent <em>Declaration Or Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration Or Parent</em>' container reference.
	 * @see #getDeclarationOrParent()
	 * @generated
	 */
	void setDeclarationOrParent(InstrumentedMethod value);

	/**
	 * Returns the value of the '<em><b>Instrument Derived</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrument Derived</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instrument Derived</em>' attribute.
	 * @see #setInstrumentDerived(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedMethod_InstrumentDerived()
	 * @model default="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isInstrumentDerived();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#isInstrumentDerived <em>Instrument Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrument Derived</em>' attribute.
	 * @see #isInstrumentDerived()
	 * @generated
	 */
	void setInstrumentDerived(boolean value);

	/**
	 * Returns the value of the '<em><b>Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' reference.
	 * @see #setMethod(Method)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentedMethod_Method()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Method getMethod();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod#getMethod <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(Method value);

} // InstrumentedMethod
