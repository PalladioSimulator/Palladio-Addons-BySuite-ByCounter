/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Threaded Counting Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ThreadedCountingResultImpl#getThreadId <em>Thread Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ThreadedCountingResultImpl extends CountingResultImpl implements ThreadedCountingResult {
	/**
	 * The cached value of the '{@link #getSpawnedThreadedCountingResults() <em>Spawned Threaded Counting Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpawnedThreadedCountingResults()
	 * @generated
	 * @ordered
	 */
	protected EList<ThreadedCountingResult> spawnedThreadedCountingResults;

	/**
	 * The default value of the '{@link #getThreadId() <em>Thread Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadId()
	 * @generated
	 * @ordered
	 */
	protected static final long THREAD_ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getThreadId() <em>Thread Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThreadId()
	 * @generated
	 * @ordered
	 */
	protected long threadId = THREAD_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ThreadedCountingResultImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.THREADED_COUNTING_RESULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ThreadedCountingResult> getSpawnedThreadedCountingResults() {
		if (spawnedThreadedCountingResults == null) {
			spawnedThreadedCountingResults = new EObjectContainmentEList<ThreadedCountingResult>(ThreadedCountingResult.class, this, OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS);
		}
		return spawnedThreadedCountingResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getThreadId() {
		return threadId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreadId(long newThreadId) {
		long oldThreadId = threadId;
		threadId = newThreadId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.THREADED_COUNTING_RESULT__THREAD_ID, oldThreadId, threadId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return ((InternalEList<?>)getSpawnedThreadedCountingResults()).basicRemove(otherEnd, msgs);
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
			case OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return getSpawnedThreadedCountingResults();
			case OutputPackage.THREADED_COUNTING_RESULT__THREAD_ID:
				return getThreadId();
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
			case OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				getSpawnedThreadedCountingResults().clear();
				getSpawnedThreadedCountingResults().addAll((Collection<? extends ThreadedCountingResult>)newValue);
				return;
			case OutputPackage.THREADED_COUNTING_RESULT__THREAD_ID:
				setThreadId((Long)newValue);
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
			case OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				getSpawnedThreadedCountingResults().clear();
				return;
			case OutputPackage.THREADED_COUNTING_RESULT__THREAD_ID:
				setThreadId(THREAD_ID_EDEFAULT);
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
			case OutputPackage.THREADED_COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return spawnedThreadedCountingResults != null && !spawnedThreadedCountingResults.isEmpty();
			case OutputPackage.THREADED_COUNTING_RESULT__THREAD_ID:
				return threadId != THREAD_ID_EDEFAULT;
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
		result.append(" (threadId: ");
		result.append(threadId);
		result.append(')');
		return result.toString();
	}

} //ThreadedCountingResultImpl
