/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.MethodCallCount;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Call Count</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getQualifiedFunctionName <em>Qualified Function Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodCallCountImpl extends EObjectImpl implements MethodCallCount {
	/**
	 * The default value of the '{@link #getQualifiedFunctionName() <em>Qualified Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifiedFunctionName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFIED_FUNCTION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQualifiedFunctionName() <em>Qualified Function Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifiedFunctionName()
	 * @generated
	 * @ordered
	 */
	protected String qualifiedFunctionName = QUALIFIED_FUNCTION_NAME_EDEFAULT;

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
	protected MethodCallCountImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.METHOD_CALL_COUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifiedFunctionName() {
		return qualifiedFunctionName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifiedFunctionName(String newQualifiedFunctionName) {
		String oldQualifiedFunctionName = qualifiedFunctionName;
		qualifiedFunctionName = newQualifiedFunctionName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME, oldQualifiedFunctionName, qualifiedFunctionName));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.METHOD_CALL_COUNT__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				return getQualifiedFunctionName();
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				return getCount();
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				setQualifiedFunctionName((String)newValue);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				setCount((Long)newValue);
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				setQualifiedFunctionName(QUALIFIED_FUNCTION_NAME_EDEFAULT);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				setCount(COUNT_EDEFAULT);
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				return QUALIFIED_FUNCTION_NAME_EDEFAULT == null ? qualifiedFunctionName != null : !QUALIFIED_FUNCTION_NAME_EDEFAULT.equals(qualifiedFunctionName);
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				return count != COUNT_EDEFAULT;
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
		result.append(" (qualifiedFunctionName: ");
		result.append(qualifiedFunctionName);
		result.append(", count: ");
		result.append(count);
		result.append(')');
		return result.toString();
	}

} //MethodCallCountImpl
