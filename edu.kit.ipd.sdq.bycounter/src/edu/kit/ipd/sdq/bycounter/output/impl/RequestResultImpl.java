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

import edu.kit.ipd.sdq.bycounter.output.UUID;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Request Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getRequestId <em>Request Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getCountingResults <em>Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestResultImpl#getResultCollection <em>Result Collection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequestResultImpl extends EObjectImpl implements RequestResult {
	/**
	 * The cached value of the '{@link #getRequestId() <em>Request Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestId()
	 * @generated
	 * @ordered
	 */
	protected UUID requestId;

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
	public ResultCollection getResultCollection() {
		if (eContainerFeatureID() != OutputPackage.REQUEST_RESULT__RESULT_COLLECTION) return null;
		return (ResultCollection)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResultCollection(ResultCollection newResultCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResultCollection, OutputPackage.REQUEST_RESULT__RESULT_COLLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultCollection(ResultCollection newResultCollection) {
		if (newResultCollection != eInternalContainer() || (eContainerFeatureID() != OutputPackage.REQUEST_RESULT__RESULT_COLLECTION && newResultCollection != null)) {
			if (EcoreUtil.isAncestor(this, newResultCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResultCollection != null)
				msgs = ((InternalEObject)newResultCollection).eInverseAdd(this, OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS, ResultCollection.class, msgs);
			msgs = basicSetResultCollection(newResultCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.REQUEST_RESULT__RESULT_COLLECTION, newResultCollection, newResultCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCountingResults()).basicAdd(otherEnd, msgs);
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResultCollection((ResultCollection)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UUID getRequestId() {
		if (requestId != null && requestId.eIsProxy()) {
			InternalEObject oldRequestId = (InternalEObject)requestId;
			requestId = (UUID)eResolveProxy(oldRequestId);
			if (requestId != oldRequestId) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.REQUEST_RESULT__REQUEST_ID, oldRequestId, requestId));
			}
		}
		return requestId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UUID basicGetRequestId() {
		return requestId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestId(UUID newRequestId) {
		UUID oldRequestId = requestId;
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
			countingResults = new EObjectContainmentWithInverseEList<CountingResult>(CountingResult.class, this, OutputPackage.REQUEST_RESULT__COUNTING_RESULTS, OutputPackage.COUNTING_RESULT__REQUEST_RESULT);
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
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return ((InternalEList<?>)getCountingResults()).basicRemove(otherEnd, msgs);
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return basicSetResultCollection(null, msgs);
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
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return eInternalContainer().eInverseRemove(this, OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS, ResultCollection.class, msgs);
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
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				if (resolve) return getRequestId();
				return basicGetRequestId();
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return getCountingResults();
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return getResultCollection();
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
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				setRequestId((UUID)newValue);
				return;
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				getCountingResults().clear();
				getCountingResults().addAll((Collection<? extends CountingResult>)newValue);
				return;
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				setResultCollection((ResultCollection)newValue);
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
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				setRequestId((UUID)null);
				return;
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				getCountingResults().clear();
				return;
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				setResultCollection((ResultCollection)null);
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
			case OutputPackage.REQUEST_RESULT__REQUEST_ID:
				return requestId != null;
			case OutputPackage.REQUEST_RESULT__COUNTING_RESULTS:
				return countingResults != null && !countingResults.isEmpty();
			case OutputPackage.REQUEST_RESULT__RESULT_COLLECTION:
				return getResultCollection() != null;
		}
		return super.eIsSet(featureID);
	}

} //RequestResultImpl
