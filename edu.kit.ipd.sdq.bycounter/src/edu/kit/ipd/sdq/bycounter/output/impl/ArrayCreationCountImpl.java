/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.ArrayCreation;
import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.CountingResult;
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
 * An implementation of the model object '<em><b>Array Creation Count</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl#getArrayCreation <em>Array Creation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl#getCount <em>Count</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ArrayCreationCountImpl#getCountingResult <em>Counting Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArrayCreationCountImpl extends EObjectImpl implements ArrayCreationCount {
	/**
	 * The cached value of the '{@link #getArrayCreation() <em>Array Creation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayCreation()
	 * @generated
	 * @ordered
	 */
	protected ArrayCreation arrayCreation;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final long COUNT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected long count = COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayCreationCountImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.ARRAY_CREATION_COUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayCreation getArrayCreation() {
		return arrayCreation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArrayCreation(ArrayCreation newArrayCreation, NotificationChain msgs) {
		ArrayCreation oldArrayCreation = arrayCreation;
		arrayCreation = newArrayCreation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION, oldArrayCreation, newArrayCreation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayCreation(ArrayCreation newArrayCreation) {
		if (newArrayCreation != arrayCreation) {
			NotificationChain msgs = null;
			if (arrayCreation != null)
				msgs = ((InternalEObject)arrayCreation).eInverseRemove(this, OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT, ArrayCreation.class, msgs);
			if (newArrayCreation != null)
				msgs = ((InternalEObject)newArrayCreation).eInverseAdd(this, OutputPackage.ARRAY_CREATION__ARRAY_CREATION_COUNT, ArrayCreation.class, msgs);
			msgs = basicSetArrayCreation(newArrayCreation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION, newArrayCreation, newArrayCreation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(long newCount) {
		long oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION_COUNT__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CountingResult getCountingResult() {
		if (eContainerFeatureID() != OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT) return null;
		return (CountingResult)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCountingResult(CountingResult newCountingResult, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCountingResult, OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountingResult(CountingResult newCountingResult) {
		if (newCountingResult != eInternalContainer() || (eContainerFeatureID() != OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT && newCountingResult != null)) {
			if (EcoreUtil.isAncestor(this, newCountingResult))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCountingResult != null)
				msgs = ((InternalEObject)newCountingResult).eInverseAdd(this, OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS, CountingResult.class, msgs);
			msgs = basicSetCountingResult(newCountingResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT, newCountingResult, newCountingResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				if (arrayCreation != null)
					msgs = ((InternalEObject)arrayCreation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION, null, msgs);
				return basicSetArrayCreation((ArrayCreation)otherEnd, msgs);
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCountingResult((CountingResult)otherEnd, msgs);
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
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				return basicSetArrayCreation(null, msgs);
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				return basicSetCountingResult(null, msgs);
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
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				return eInternalContainer().eInverseRemove(this, OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS, CountingResult.class, msgs);
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
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				return getArrayCreation();
			case OutputPackage.ARRAY_CREATION_COUNT__COUNT:
				return getCount();
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				return getCountingResult();
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
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				setArrayCreation((ArrayCreation)newValue);
				return;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNT:
				setCount((Long)newValue);
				return;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				setCountingResult((CountingResult)newValue);
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
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				setArrayCreation((ArrayCreation)null);
				return;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				setCountingResult((CountingResult)null);
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
			case OutputPackage.ARRAY_CREATION_COUNT__ARRAY_CREATION:
				return arrayCreation != null;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNT:
				return count != COUNT_EDEFAULT;
			case OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT:
				return getCountingResult() != null;
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
		result.append(" (count: ");
		result.append(count);
		result.append(')');
		return result.toString();
	}

} //ArrayCreationCountImpl
