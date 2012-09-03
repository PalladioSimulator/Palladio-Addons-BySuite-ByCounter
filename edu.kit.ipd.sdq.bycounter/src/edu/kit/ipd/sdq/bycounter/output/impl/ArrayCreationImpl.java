/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.ArrayCreation;
import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.ArrayType;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array Creation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationImpl#getTypeDescriptor <em>Type Descriptor</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationImpl#getNumberOfDimensions <em>Number Of Dimensions</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationImpl#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationImpl#getArrayCreationCount <em>Array Creation Count</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayCreationImpl extends EObjectImpl implements ArrayCreation {
	/**
	 * The default value of the '{@link #getTypeDescriptor() <em>Type Descriptor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeDescriptor()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_DESCRIPTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypeDescriptor() <em>Type Descriptor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeDescriptor()
	 * @generated
	 * @ordered
	 */
	protected String typeDescriptor = TYPE_DESCRIPTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberOfDimensions() <em>Number Of Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfDimensions()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMBER_OF_DIMENSIONS_EDEFAULT = 0; // TODO The default value literal "1" is not valid.

	/**
	 * The cached value of the '{@link #getNumberOfDimensions() <em>Number Of Dimensions</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfDimensions()
	 * @generated
	 * @ordered
	 */
	protected int numberOfDimensions = NUMBER_OF_DIMENSIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getArrayType() <em>Array Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayType()
	 * @generated
	 * @ordered
	 */
	protected static final ArrayType ARRAY_TYPE_EDEFAULT = ArrayType.INVALID;

	/**
	 * The cached value of the '{@link #getArrayType() <em>Array Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayType()
	 * @generated
	 * @ordered
	 */
	protected ArrayType arrayType = ARRAY_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayCreationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.ARRAY_CREATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypeDescriptor() {
		return typeDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeDescriptor(String newTypeDescriptor) {
		String oldTypeDescriptor = typeDescriptor;
		typeDescriptor = newTypeDescriptor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION__TYPE_DESCRIPTOR, oldTypeDescriptor, typeDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumberOfDimensions() {
		return numberOfDimensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfDimensions(int newNumberOfDimensions) {
		int oldNumberOfDimensions = numberOfDimensions;
		numberOfDimensions = newNumberOfDimensions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION__NUMBER_OF_DIMENSIONS, oldNumberOfDimensions, numberOfDimensions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayType getArrayType() {
		return arrayType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayType(ArrayType newArrayType) {
		ArrayType oldArrayType = arrayType;
		arrayType = newArrayType == null ? ARRAY_TYPE_EDEFAULT : newArrayType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION__ARRAY_TYPE, oldArrayType, arrayType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayCreationCount getArrayCreationCount() {
		if (eContainerFeatureID() != OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT) return null;
		return (ArrayCreationCount)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayCreationCount(ArrayCreationCount newArrayCreationCount, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newArrayCreationCount, OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayCreationCount(ArrayCreationCount newArrayCreationCount) {
		if (newArrayCreationCount != eInternalContainer() || (eContainerFeatureID() != OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT && newArrayCreationCount != null)) {
			if (EcoreUtil.isAncestor(this, newArrayCreationCount))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newArrayCreationCount != null)
				msgs = ((InternalEObject)newArrayCreationCount).eInverseAdd(this, OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION, ArrayCreationCount.class, msgs);
			msgs = basicSetArrayCreationCount(newArrayCreationCount, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT, newArrayCreationCount, newArrayCreationCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetArrayCreationCount((ArrayCreationCount)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				return basicSetArrayCreationCount(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				return eInternalContainer().eInverseRemove(this, OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION, ArrayCreationCount.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__TYPE_DESCRIPTOR:
				return getTypeDescriptor();
			case OutputPackage.ARRAY_CREATION__NUMBER_OF_DIMENSIONS:
				return getNumberOfDimensions();
			case OutputPackage.ARRAY_CREATION__ARRAY_TYPE:
				return getArrayType();
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				return getArrayCreationCount();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__TYPE_DESCRIPTOR:
				setTypeDescriptor((String)newValue);
				return;
			case OutputPackage.ARRAY_CREATION__NUMBER_OF_DIMENSIONS:
				setNumberOfDimensions((Integer)newValue);
				return;
			case OutputPackage.ARRAY_CREATION__ARRAY_TYPE:
				setArrayType((ArrayType)newValue);
				return;
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				setArrayCreationCount((ArrayCreationCount)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__TYPE_DESCRIPTOR:
				setTypeDescriptor(TYPE_DESCRIPTOR_EDEFAULT);
				return;
			case OutputPackage.ARRAY_CREATION__NUMBER_OF_DIMENSIONS:
				setNumberOfDimensions(NUMBER_OF_DIMENSIONS_EDEFAULT);
				return;
			case OutputPackage.ARRAY_CREATION__ARRAY_TYPE:
				setArrayType(ARRAY_TYPE_EDEFAULT);
				return;
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				setArrayCreationCount((ArrayCreationCount)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION__TYPE_DESCRIPTOR:
				return TYPE_DESCRIPTOR_EDEFAULT == null ? typeDescriptor != null : !TYPE_DESCRIPTOR_EDEFAULT.equals(typeDescriptor);
			case OutputPackage.ARRAY_CREATION__NUMBER_OF_DIMENSIONS:
				return numberOfDimensions != NUMBER_OF_DIMENSIONS_EDEFAULT;
			case OutputPackage.ARRAY_CREATION__ARRAY_TYPE:
				return arrayType != ARRAY_TYPE_EDEFAULT;
			case OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT:
				return getArrayCreationCount() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (typeDescriptor: ");
		result.append(typeDescriptor);
		result.append(", numberOfDimensions: ");
		result.append(numberOfDimensions);
		result.append(", arrayType: ");
		result.append(arrayType);
		result.append(')');
		return result.toString();
	}

} //ArrayCreationImpl
