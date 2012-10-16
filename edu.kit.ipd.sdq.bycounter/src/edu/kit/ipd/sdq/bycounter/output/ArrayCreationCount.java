/**
 */
package edu.kit.ipd.sdq.bycounter.output;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Creation Count</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount <em>Count</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getNumberOfDimensions <em>Number Of Dimensions</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount()
 * @model
 * @generated
 */
public interface ArrayCreationCount extends EObject {
	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_Count()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getCount();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(long value);

	/**
	 * Returns the value of the '<em><b>Type Descriptor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Descriptor of the type of the array elements.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type Descriptor</em>' attribute.
	 * @see #setTypeDescriptor(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_TypeDescriptor()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getTypeDescriptor();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getTypeDescriptor <em>Type Descriptor</em>}' attribute.
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
	 * <p>
	 * If the meaning of the '<em>Number Of Dimensions</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Of Dimensions</em>' attribute.
	 * @see #setNumberOfDimensions(int)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_NumberOfDimensions()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getNumberOfDimensions();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getNumberOfDimensions <em>Number Of Dimensions</em>}' attribute.
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
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_ArrayType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ArrayType getArrayType();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getArrayType <em>Array Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Array Type</em>' attribute.
	 * @see edu.kit.ipd.sdq.bycounter.output.ArrayType
	 * @see #getArrayType()
	 * @generated
	 */
	void setArrayType(ArrayType value);

	/**
	 * Returns the value of the '<em><b>Counting Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts <em>Array Creation Counts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Counting Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Counting Result</em>' container reference.
	 * @see #setCountingResult(CountingResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getArrayCreationCount_CountingResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.CountingResult#getArrayCreationCounts
	 * @model opposite="arrayCreationCounts" required="true" transient="false" ordered="false"
	 * @generated
	 */
	CountingResult getCountingResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount#getCountingResult <em>Counting Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Counting Result</em>' container reference.
	 * @see #getCountingResult()
	 * @generated
	 */
	void setCountingResult(CountingResult value);

} // ArrayCreationCount
