/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.RequestResult;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Request Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getResultCollection <em>Result Collection</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getRequestId <em>Request Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getCountingResults <em>Counting Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequestResultImpl extends EObjectImpl implements RequestResult {
	/**
	 * The cached value of the '{@link #getResultCollection() <em>Result Collection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResultCollection()
	 * @generated
	 * @ordered
	 */
	protected EList<ResultCollection> resultCollection;

	/**
	 * The default value of the '{@link #getRequestId() <em>Request Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestId()
	 * @generated
	 * @ordered
	 */
	protected static final Object REQUEST_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestId() <em>Request Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestId()
	 * @generated
	 * @ordered
	 */
	protected Object requestId = REQUEST_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCountingResults() <em>Counting Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountingResults()
	 * @generated
	 * @ordered
	 */
	protected EList<CountingResult> countingResults;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequestResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.REQUEST_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResultCollection> getResultCollection() {
		if (resultCollection == null) {
			resultCollection = new EObjectContainmentEList<ResultCollection>(ResultCollection.class, this, OutputPackage.REQUEST_RESULT__RESULT_COLLECTION);
		}
		return resultCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getRequestId() {
		return requestId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestId(Object newRequestId) {
		Object oldRequestId = requestId;
		requestId = newRequestId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.REQUEST_RESULT__REQUEST_ID, oldRequestId, requestId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CountingResult> getCountingResults() {
		if (countingResults == null) {
			countingResults = new EObjectContainmentEList<CountingResult>(CountingResult.class, this, OutputPackage.REQUEST_RESULT__COUNTING_RESULTS);
		}
		return countingResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return ((InternalEList<?>)getResultCollection()).basicRemove(otherEnd, msgs);
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return ((InternalEList<?>)getCountingResults()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return getResultCollection();
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				return getRequestId();
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return getCountingResults();
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
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				getResultCollection().clear();
				getResultCollection().addAll((Collection<? extends ResultCollection>)newValue);
				return;
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				setRequestId(newValue);
				return;
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				getCountingResults().clear();
				getCountingResults().addAll((Collection<? extends CountingResult>)newValue);
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
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				getResultCollection().clear();
				return;
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				setRequestId(REQUEST_ID_EDEFAULT);
				return;
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				getCountingResults().clear();
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
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return resultCollection != null && !resultCollection.isEmpty();
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				return REQUEST_ID_EDEFAULT == null ? requestId != null : !REQUEST_ID_EDEFAULT.equals(requestId);
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return countingResults != null && !countingResults.isEmpty();
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
		result.append(" (requestId: ");
		result.append(requestId);
		result.append(')');
		return result.toString();
	}

} //RequestResultImpl
