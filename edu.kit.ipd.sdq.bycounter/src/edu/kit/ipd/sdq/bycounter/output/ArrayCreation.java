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
 * A representation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getNumberOfDimensions <em>Number Of Dimensions</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getArrayType <em>Array Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreation()
 * @model
 * @generated
 */
public interface ArrayCreation extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Descriptor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Descriptor of the type of the array elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Descriptor</em>' attribute.
	 * @see #setTypeDescriptor(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreation_TypeDescriptor()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getTypeDescriptor();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getTypeDescriptor <em>Type Descriptor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Descriptor</em>' attribute.
	 * @see #getTypeDescriptor()
	 * @generated
	 */
	void setTypeDescriptor(String value);

	/**
	 * Returns the value of the '<em><b>Number Of Dimensions</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Dimensions</em>' attribute.
	 * @see #setNumberOfDimensions(int)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreation_NumberOfDimensions()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getNumberOfDimensions();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getNumberOfDimensions <em>Number Of Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Of Dimensions</em>' attribute.
	 * @see #getNumberOfDimensions()
	 * @generated
	 */
	void setNumberOfDimensions(int value);

	/**
	 * Returns the value of the '<em><b>Array Type</b></em>' attribute.
	 * The literals are from the enumeration {@link edu.kit.ipd.sdq.bycounter.output.ArrayType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The opcode of the array type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Array Type</em>' attribute.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
	 * @see #setArrayType(ArrayType)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreation_ArrayType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ArrayType getArrayType();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreation#getArrayType <em>Array Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Type</em>' attribute.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
	 * @see #getArrayType()
	 * @generated
	 */
	void setArrayType(ArrayType value);

} // ArrayCreation
