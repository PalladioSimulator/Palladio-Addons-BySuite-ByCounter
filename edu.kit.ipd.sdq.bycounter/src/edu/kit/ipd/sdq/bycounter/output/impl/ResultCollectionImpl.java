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

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Result Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl#getRequestResults <em>Request Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ResultCollectionImpl#getCountingResults <em>Counting Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResultCollectionImpl extends EObjectImpl implements ResultCollection {
	/**
	 * The cached value of the '{@link #getRequestResults() <em>Request Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestResults()
	 * @generated
	 * @ordered
	 */
	protected EList<RequestResult> requestResults;

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
	protected ResultCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.RESULT_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequestResult> getRequestResults() {
		if (requestResults == null) {
			requestResults = new EObjectContainmentWithInverseEList<RequestResult>(RequestResult.class, this, OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS, OutputPackage.REQUEST_RESULT__RESULT_COLLECTION);
		}
		return requestResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CountingResult> getCountingResults() {
		if (countingResults == null) {
			countingResults = new EObjectContainmentWithInverseEList<CountingResult>(CountingResult.class, this, OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS, OutputPackage.COUNTING_RESULT__RESULT_COLLECTION);
		}
		return countingResults;
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequestResults()).basicAdd(otherEnd, msgs);
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCountingResults()).basicAdd(otherEnd, msgs);
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				return ((InternalEList<?>)getRequestResults()).basicRemove(otherEnd, msgs);
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				return getRequestResults();
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				getRequestResults().clear();
				getRequestResults().addAll((Collection<? extends RequestResult>)newValue);
				return;
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				getRequestResults().clear();
				return;
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
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
			case OutputPackage.RESULT_COLLECTION__REQUEST_RESULTS:
				return requestResults != null && !requestResults.isEmpty();
			case OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS:
				return countingResults != null && !countingResults.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ResultCollectionImpl
