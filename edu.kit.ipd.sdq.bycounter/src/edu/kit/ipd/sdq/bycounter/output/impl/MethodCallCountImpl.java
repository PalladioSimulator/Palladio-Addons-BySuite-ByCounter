/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.fzi.gast.functions.Function;

import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.MethodCallCount;
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
 * An implementation of the model object '<em><b>Method Call Count</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getQualifiedFunctionName <em>Qualified Function Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getCount <em>Count</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MethodCallCountImpl#getCountingResult <em>Counting Result</em>}</li>
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
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
	protected Function function;

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
	public Function getFunction() {
		if (function != null && function.eIsProxy()) {
			InternalEObject oldFunction = (InternalEObject)function;
			function = (Function)eResolveProxy(oldFunction);
			if (function != oldFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.METHOD_CALL_COUNT__FUNCTION, oldFunction, function));
			}
		}
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function basicGetFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunction(Function newFunction) {
		Function oldFunction = function;
		function = newFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.METHOD_CALL_COUNT__FUNCTION, oldFunction, function));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CountingResult getCountingResult() {
		if (eContainerFeatureID() != OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT) return null;
		return (CountingResult)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCountingResult(CountingResult newCountingResult, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCountingResult, OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountingResult(CountingResult newCountingResult) {
		if (newCountingResult != eInternalContainer() || (eContainerFeatureID() != OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT && newCountingResult != null)) {
			if (EcoreUtil.isAncestor(this, newCountingResult))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCountingResult != null)
				msgs = ((InternalEObject)newCountingResult).eInverseAdd(this, OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS, CountingResult.class, msgs);
			msgs = basicSetCountingResult(newCountingResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT, newCountingResult, newCountingResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
				return eInternalContainer().eInverseRemove(this, OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS, CountingResult.class, msgs);
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				return getQualifiedFunctionName();
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				return getCount();
			case OutputPackage.METHOD_CALL_COUNT__FUNCTION:
				if (resolve) return getFunction();
				return basicGetFunction();
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				setQualifiedFunctionName((String)newValue);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				setCount((Long)newValue);
				return;
			case OutputPackage.METHOD_CALL_COUNT__FUNCTION:
				setFunction((Function)newValue);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				setQualifiedFunctionName(QUALIFIED_FUNCTION_NAME_EDEFAULT);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case OutputPackage.METHOD_CALL_COUNT__FUNCTION:
				setFunction((Function)null);
				return;
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
			case OutputPackage.METHOD_CALL_COUNT__QUALIFIED_FUNCTION_NAME:
				return QUALIFIED_FUNCTION_NAME_EDEFAULT == null ? qualifiedFunctionName != null : !QUALIFIED_FUNCTION_NAME_EDEFAULT.equals(qualifiedFunctionName);
			case OutputPackage.METHOD_CALL_COUNT__COUNT:
				return count != COUNT_EDEFAULT;
			case OutputPackage.METHOD_CALL_COUNT__FUNCTION:
				return function != null;
			case OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT:
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
		result.append(" (qualifiedFunctionName: ");
		result.append(qualifiedFunctionName);
		result.append(", count: ");
		result.append(count);
		result.append(')');
		return result.toString();
	}

} //MethodCallCountImpl
