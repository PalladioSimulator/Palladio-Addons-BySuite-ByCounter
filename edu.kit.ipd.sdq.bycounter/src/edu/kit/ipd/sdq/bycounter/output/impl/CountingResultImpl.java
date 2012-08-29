/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Counting Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getArrayCreationCounts <em>Array Creation Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getCallerId <em>Caller Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodCallCounts <em>Method Call Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodInvocationBeginning <em>Method Invocation Beginning</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodReportingTime <em>Method Reporting Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getObservedElement <em>Observed Element</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getOpcodeCounts <em>Opcode Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getOwnId <em>Own Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getQualifyingMethodName <em>Qualifying Method Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CountingResultImpl extends EObjectImpl implements CountingResult {
	/**
	 * The default value of the '{@link #getArrayCreationCounts() <em>Array Creation Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayCreationCounts()
	 * @generated
	 * @ordered
	 */
	protected static final Object ARRAY_CREATION_COUNTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArrayCreationCounts() <em>Array Creation Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayCreationCounts()
	 * @generated
	 * @ordered
	 */
	protected Object arrayCreationCounts = ARRAY_CREATION_COUNTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCallerId() <em>Caller Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallerId()
	 * @generated
	 * @ordered
	 */
	protected static final Object CALLER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCallerId() <em>Caller Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallerId()
	 * @generated
	 * @ordered
	 */
	protected Object callerId = CALLER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodCallCounts() <em>Method Call Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodCallCounts()
	 * @generated
	 * @ordered
	 */
	protected static final Object METHOD_CALL_COUNTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMethodCallCounts() <em>Method Call Counts</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodCallCounts()
	 * @generated
	 * @ordered
	 */
	protected Object methodCallCounts = METHOD_CALL_COUNTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodInvocationBeginning() <em>Method Invocation Beginning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodInvocationBeginning()
	 * @generated
	 * @ordered
	 */
	protected static final long METHOD_INVOCATION_BEGINNING_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMethodInvocationBeginning() <em>Method Invocation Beginning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodInvocationBeginning()
	 * @generated
	 * @ordered
	 */
	protected long methodInvocationBeginning = METHOD_INVOCATION_BEGINNING_EDEFAULT;

	/**
	 * The default value of the '{@link #getMethodReportingTime() <em>Method Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodReportingTime()
	 * @generated
	 * @ordered
	 */
	protected static final long METHOD_REPORTING_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMethodReportingTime() <em>Method Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodReportingTime()
	 * @generated
	 * @ordered
	 */
	protected long methodReportingTime = METHOD_REPORTING_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getObservedElement() <em>Observed Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedElement()
	 * @generated
	 * @ordered
	 */
	protected static final Object OBSERVED_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getObservedElement() <em>Observed Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedElement()
	 * @generated
	 * @ordered
	 */
	protected Object observedElement = OBSERVED_ELEMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOpcodeCounts() <em>Opcode Counts</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOpcodeCounts()
	 * @generated
	 * @ordered
	 */
	protected EList<Long> opcodeCounts;

	/**
	 * The default value of the '{@link #getOwnId() <em>Own Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnId()
	 * @generated
	 * @ordered
	 */
	protected static final Object OWN_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOwnId() <em>Own Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnId()
	 * @generated
	 * @ordered
	 */
	protected Object ownId = OWN_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getQualifyingMethodName() <em>Qualifying Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifyingMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFYING_METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQualifyingMethodName() <em>Qualifying Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifyingMethodName()
	 * @generated
	 * @ordered
	 */
	protected String qualifyingMethodName = QUALIFYING_METHOD_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CountingResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.COUNTING_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getArrayCreationCounts() {
		return arrayCreationCounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArrayCreationCounts(Object newArrayCreationCounts) {
		Object oldArrayCreationCounts = arrayCreationCounts;
		arrayCreationCounts = newArrayCreationCounts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS, oldArrayCreationCounts, arrayCreationCounts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getCallerId() {
		return callerId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallerId(Object newCallerId) {
		Object oldCallerId = callerId;
		callerId = newCallerId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__CALLER_ID, oldCallerId, callerId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getMethodCallCounts() {
		return methodCallCounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodCallCounts(Object newMethodCallCounts) {
		Object oldMethodCallCounts = methodCallCounts;
		methodCallCounts = newMethodCallCounts;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS, oldMethodCallCounts, methodCallCounts));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMethodInvocationBeginning() {
		return methodInvocationBeginning;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodInvocationBeginning(long newMethodInvocationBeginning) {
		long oldMethodInvocationBeginning = methodInvocationBeginning;
		methodInvocationBeginning = newMethodInvocationBeginning;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_BEGINNING, oldMethodInvocationBeginning, methodInvocationBeginning));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMethodReportingTime() {
		return methodReportingTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodReportingTime(long newMethodReportingTime) {
		long oldMethodReportingTime = methodReportingTime;
		methodReportingTime = newMethodReportingTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_REPORTING_TIME, oldMethodReportingTime, methodReportingTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getObservedElement() {
		return observedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObservedElement(Object newObservedElement) {
		Object oldObservedElement = observedElement;
		observedElement = newObservedElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT, oldObservedElement, observedElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Long> getOpcodeCounts() {
		if (opcodeCounts == null) {
			opcodeCounts = new EDataTypeUniqueEList<Long>(Long.class, this, OutputPackage.COUNTING_RESULT__OPCODE_COUNTS);
		}
		return opcodeCounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getOwnId() {
		return ownId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOwnId(Object newOwnId) {
		Object oldOwnId = ownId;
		ownId = newOwnId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__OWN_ID, oldOwnId, ownId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifyingMethodName() {
		return qualifyingMethodName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifyingMethodName(String newQualifyingMethodName) {
		String oldQualifyingMethodName = qualifyingMethodName;
		qualifyingMethodName = newQualifyingMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__QUALIFYING_METHOD_NAME, oldQualifyingMethodName, qualifyingMethodName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return getArrayCreationCounts();
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				return getCallerId();
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return getMethodCallCounts();
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_BEGINNING:
				return getMethodInvocationBeginning();
			case OutputPackage.COUNTING_RESULT__METHOD_REPORTING_TIME:
				return getMethodReportingTime();
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				return getObservedElement();
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				return getOpcodeCounts();
			case OutputPackage.COUNTING_RESULT__OWN_ID:
				return getOwnId();
			case OutputPackage.COUNTING_RESULT__QUALIFYING_METHOD_NAME:
				return getQualifyingMethodName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				setArrayCreationCounts(newValue);
				return;
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				setCallerId(newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				setMethodCallCounts(newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_BEGINNING:
				setMethodInvocationBeginning((Long)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_REPORTING_TIME:
				setMethodReportingTime((Long)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				setObservedElement(newValue);
				return;
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				getOpcodeCounts().clear();
				getOpcodeCounts().addAll((Collection<? extends Long>)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__OWN_ID:
				setOwnId(newValue);
				return;
			case OutputPackage.COUNTING_RESULT__QUALIFYING_METHOD_NAME:
				setQualifyingMethodName((String)newValue);
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
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				setArrayCreationCounts(ARRAY_CREATION_COUNTS_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				setCallerId(CALLER_ID_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				setMethodCallCounts(METHOD_CALL_COUNTS_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_BEGINNING:
				setMethodInvocationBeginning(METHOD_INVOCATION_BEGINNING_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_REPORTING_TIME:
				setMethodReportingTime(METHOD_REPORTING_TIME_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				setObservedElement(OBSERVED_ELEMENT_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				getOpcodeCounts().clear();
				return;
			case OutputPackage.COUNTING_RESULT__OWN_ID:
				setOwnId(OWN_ID_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__QUALIFYING_METHOD_NAME:
				setQualifyingMethodName(QUALIFYING_METHOD_NAME_EDEFAULT);
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
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return ARRAY_CREATION_COUNTS_EDEFAULT == null ? arrayCreationCounts != null : !ARRAY_CREATION_COUNTS_EDEFAULT.equals(arrayCreationCounts);
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				return CALLER_ID_EDEFAULT == null ? callerId != null : !CALLER_ID_EDEFAULT.equals(callerId);
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return METHOD_CALL_COUNTS_EDEFAULT == null ? methodCallCounts != null : !METHOD_CALL_COUNTS_EDEFAULT.equals(methodCallCounts);
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_BEGINNING:
				return methodInvocationBeginning != METHOD_INVOCATION_BEGINNING_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__METHOD_REPORTING_TIME:
				return methodReportingTime != METHOD_REPORTING_TIME_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				return OBSERVED_ELEMENT_EDEFAULT == null ? observedElement != null : !OBSERVED_ELEMENT_EDEFAULT.equals(observedElement);
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				return opcodeCounts != null && !opcodeCounts.isEmpty();
			case OutputPackage.COUNTING_RESULT__OWN_ID:
				return OWN_ID_EDEFAULT == null ? ownId != null : !OWN_ID_EDEFAULT.equals(ownId);
			case OutputPackage.COUNTING_RESULT__QUALIFYING_METHOD_NAME:
				return QUALIFYING_METHOD_NAME_EDEFAULT == null ? qualifyingMethodName != null : !QUALIFYING_METHOD_NAME_EDEFAULT.equals(qualifyingMethodName);
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
		result.append(" (arrayCreationCounts: ");
		result.append(arrayCreationCounts);
		result.append(", callerId: ");
		result.append(callerId);
		result.append(", methodCallCounts: ");
		result.append(methodCallCounts);
		result.append(", methodInvocationBeginning: ");
		result.append(methodInvocationBeginning);
		result.append(", methodReportingTime: ");
		result.append(methodReportingTime);
		result.append(", observedElement: ");
		result.append(observedElement);
		result.append(", opcodeCounts: ");
		result.append(opcodeCounts);
		result.append(", ownId: ");
		result.append(ownId);
		result.append(", qualifyingMethodName: ");
		result.append(qualifyingMethodName);
		result.append(')');
		return result.toString();
	}

} //CountingResultImpl
