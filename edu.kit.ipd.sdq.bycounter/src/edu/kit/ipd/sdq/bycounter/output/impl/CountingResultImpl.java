/**
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;

import edu.kit.ipd.sdq.bycounter.output.ArrayCreationCount;
import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.MethodCallCount;
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

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Counting Result</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getCallerId <em>Caller Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodInvocationStartTime <em>Method Invocation Start Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getReportingTime <em>Reporting Time</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getObservedElement <em>Observed Element</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getOpcodeCounts <em>Opcode Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodId <em>Method Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getQualifiedMethodName <em>Qualified Method Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getResultCollection <em>Result Collection</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getArrayCreationCounts <em>Array Creation Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getMethodCallCounts <em>Method Call Counts</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getThreadId <em>Thread Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getThreadedCountingResult <em>Threaded Counting Result</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.CountingResultImpl#getRequestResult <em>Request Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CountingResultImpl extends EObjectImpl implements CountingResult {
	/**
	 * The cached value of the '{@link #getCallerId() <em>Caller Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallerId()
	 * @generated
	 * @ordered
	 */
	protected UUID callerId;

	/**
	 * The default value of the '{@link #getMethodInvocationStartTime() <em>Method Invocation Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodInvocationStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final long METHOD_INVOCATION_START_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMethodInvocationStartTime() <em>Method Invocation Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodInvocationStartTime()
	 * @generated
	 * @ordered
	 */
	protected long methodInvocationStartTime = METHOD_INVOCATION_START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getReportingTime() <em>Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReportingTime()
	 * @generated
	 * @ordered
	 */
	protected static final long REPORTING_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getReportingTime() <em>Reporting Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReportingTime()
	 * @generated
	 * @ordered
	 */
	protected long reportingTime = REPORTING_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getObservedElement() <em>Observed Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedElement()
	 * @generated
	 * @ordered
	 */
	protected EntityToInstrument observedElement;

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
	 * The cached value of the '{@link #getMethodId() <em>Method Id</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodId()
	 * @generated
	 * @ordered
	 */
	protected UUID methodId;

	/**
	 * The default value of the '{@link #getQualifiedMethodName() <em>Qualified Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifiedMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFIED_METHOD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQualifiedMethodName() <em>Qualified Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualifiedMethodName()
	 * @generated
	 * @ordered
	 */
	protected String qualifiedMethodName = QUALIFIED_METHOD_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArrayCreationCounts() <em>Array Creation Counts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArrayCreationCounts()
	 * @generated
	 * @ordered
	 */
	protected EList<ArrayCreationCount> arrayCreationCounts;

	/**
	 * The cached value of the '{@link #getMethodCallCounts() <em>Method Call Counts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodCallCounts()
	 * @generated
	 * @ordered
	 */
	protected EList<MethodCallCount> methodCallCounts;

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
	 * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FINAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFinal()
	 * @generated
	 * @ordered
	 */
	protected boolean final_ = FINAL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSpawnedThreadedCountingResults() <em>Spawned Threaded Counting Results</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpawnedThreadedCountingResults()
	 * @generated
	 * @ordered
	 */
	protected EList<CountingResult> spawnedThreadedCountingResults;

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
	public UUID getCallerId() {
		return callerId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCallerId(UUID newCallerId, NotificationChain msgs) {
		UUID oldCallerId = callerId;
		callerId = newCallerId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__CALLER_ID, oldCallerId, newCallerId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallerId(UUID newCallerId) {
		if (newCallerId != callerId) {
			NotificationChain msgs = null;
			if (callerId != null)
				msgs = ((InternalEObject)callerId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OutputPackage.COUNTING_RESULT__CALLER_ID, null, msgs);
			if (newCallerId != null)
				msgs = ((InternalEObject)newCallerId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OutputPackage.COUNTING_RESULT__CALLER_ID, null, msgs);
			msgs = basicSetCallerId(newCallerId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__CALLER_ID, newCallerId, newCallerId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMethodInvocationStartTime() {
		return methodInvocationStartTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodInvocationStartTime(long newMethodInvocationStartTime) {
		long oldMethodInvocationStartTime = methodInvocationStartTime;
		methodInvocationStartTime = newMethodInvocationStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME, oldMethodInvocationStartTime, methodInvocationStartTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getReportingTime() {
		return reportingTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReportingTime(long newReportingTime) {
		long oldReportingTime = reportingTime;
		reportingTime = newReportingTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__REPORTING_TIME, oldReportingTime, reportingTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityToInstrument getObservedElement() {
		if (observedElement != null && observedElement.eIsProxy()) {
			InternalEObject oldObservedElement = (InternalEObject)observedElement;
			observedElement = (EntityToInstrument)eResolveProxy(oldObservedElement);
			if (observedElement != oldObservedElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT, oldObservedElement, observedElement));
			}
		}
		return observedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityToInstrument basicGetObservedElement() {
		return observedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObservedElement(EntityToInstrument newObservedElement) {
		EntityToInstrument oldObservedElement = observedElement;
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
			opcodeCounts = new EDataTypeEList<Long>(Long.class, this, OutputPackage.COUNTING_RESULT__OPCODE_COUNTS);
		}
		return opcodeCounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UUID getMethodId() {
		return methodId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMethodId(UUID newMethodId, NotificationChain msgs) {
		UUID oldMethodId = methodId;
		methodId = newMethodId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_ID, oldMethodId, newMethodId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethodId(UUID newMethodId) {
		if (newMethodId != methodId) {
			NotificationChain msgs = null;
			if (methodId != null)
				msgs = ((InternalEObject)methodId).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OutputPackage.COUNTING_RESULT__METHOD_ID, null, msgs);
			if (newMethodId != null)
				msgs = ((InternalEObject)newMethodId).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OutputPackage.COUNTING_RESULT__METHOD_ID, null, msgs);
			msgs = basicSetMethodId(newMethodId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__METHOD_ID, newMethodId, newMethodId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getQualifiedMethodName() {
		return qualifiedMethodName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualifiedMethodName(String newQualifiedMethodName) {
		String oldQualifiedMethodName = qualifiedMethodName;
		qualifiedMethodName = newQualifiedMethodName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME, oldQualifiedMethodName, qualifiedMethodName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultCollection getResultCollection() {
		if (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__RESULT_COLLECTION) return null;
		return (ResultCollection)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResultCollection(ResultCollection newResultCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResultCollection, OutputPackage.COUNTING_RESULT__RESULT_COLLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResultCollection(ResultCollection newResultCollection) {
		if (newResultCollection != eInternalContainer() || (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__RESULT_COLLECTION && newResultCollection != null)) {
			if (EcoreUtil.isAncestor(this, newResultCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResultCollection != null)
				msgs = ((InternalEObject)newResultCollection).eInverseAdd(this, OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS, ResultCollection.class, msgs);
			msgs = basicSetResultCollection(newResultCollection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__RESULT_COLLECTION, newResultCollection, newResultCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ArrayCreationCount> getArrayCreationCounts() {
		if (arrayCreationCounts == null) {
			arrayCreationCounts = new EObjectContainmentWithInverseEList<ArrayCreationCount>(ArrayCreationCount.class, this, OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS, OutputPackage.ARRAY_CREATION_COUNT__COUNTING_RESULT);
		}
		return arrayCreationCounts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MethodCallCount> getMethodCallCounts() {
		if (methodCallCounts == null) {
			methodCallCounts = new EObjectContainmentWithInverseEList<MethodCallCount>(MethodCallCount.class, this, OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS, OutputPackage.METHOD_CALL_COUNT__COUNTING_RESULT);
		}
		return methodCallCounts;
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__THREAD_ID, oldThreadId, threadId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFinal() {
		return final_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinal(boolean newFinal) {
		boolean oldFinal = final_;
		final_ = newFinal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__FINAL, oldFinal, final_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CountingResult> getSpawnedThreadedCountingResults() {
		if (spawnedThreadedCountingResults == null) {
			spawnedThreadedCountingResults = new EObjectContainmentWithInverseEList<CountingResult>(CountingResult.class, this, OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS, OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT);
		}
		return spawnedThreadedCountingResults;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CountingResult getThreadedCountingResult() {
		if (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT) return null;
		return (CountingResult)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThreadedCountingResult(CountingResult newThreadedCountingResult, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newThreadedCountingResult, OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThreadedCountingResult(CountingResult newThreadedCountingResult) {
		if (newThreadedCountingResult != eInternalContainer() || (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT && newThreadedCountingResult != null)) {
			if (EcoreUtil.isAncestor(this, newThreadedCountingResult))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newThreadedCountingResult != null)
				msgs = ((InternalEObject)newThreadedCountingResult).eInverseAdd(this, OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS, CountingResult.class, msgs);
			msgs = basicSetThreadedCountingResult(newThreadedCountingResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT, newThreadedCountingResult, newThreadedCountingResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequestResult getRequestResult() {
		if (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__REQUEST_RESULT) return null;
		return (RequestResult)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRequestResult(RequestResult newRequestResult, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRequestResult, OutputPackage.COUNTING_RESULT__REQUEST_RESULT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestResult(RequestResult newRequestResult) {
		if (newRequestResult != eInternalContainer() || (eContainerFeatureID() != OutputPackage.COUNTING_RESULT__REQUEST_RESULT && newRequestResult != null)) {
			if (EcoreUtil.isAncestor(this, newRequestResult))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRequestResult != null)
				msgs = ((InternalEObject)newRequestResult).eInverseAdd(this, OutputPackage.REQUEST_RESULT__COUNTING_RESULTS, RequestResult.class, msgs);
			msgs = basicSetRequestResult(newRequestResult, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.COUNTING_RESULT__REQUEST_RESULT, newRequestResult, newRequestResult));
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
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResultCollection((ResultCollection)otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getArrayCreationCounts()).basicAdd(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMethodCallCounts()).basicAdd(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSpawnedThreadedCountingResults()).basicAdd(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetThreadedCountingResult((CountingResult)otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRequestResult((RequestResult)otherEnd, msgs);
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
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				return basicSetCallerId(null, msgs);
			case OutputPackage.COUNTING_RESULT__METHOD_ID:
				return basicSetMethodId(null, msgs);
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				return basicSetResultCollection(null, msgs);
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return ((InternalEList<?>)getArrayCreationCounts()).basicRemove(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return ((InternalEList<?>)getMethodCallCounts()).basicRemove(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return ((InternalEList<?>)getSpawnedThreadedCountingResults()).basicRemove(otherEnd, msgs);
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				return basicSetThreadedCountingResult(null, msgs);
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				return basicSetRequestResult(null, msgs);
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
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				return eInternalContainer().eInverseRemove(this, OutputPackage.RESULT_COLLECTION__COUNTING_RESULTS, ResultCollection.class, msgs);
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				return eInternalContainer().eInverseRemove(this, OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS, CountingResult.class, msgs);
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				return eInternalContainer().eInverseRemove(this, OutputPackage.REQUEST_RESULT__COUNTING_RESULTS, RequestResult.class, msgs);
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
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				return getCallerId();
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME:
				return getMethodInvocationStartTime();
			case OutputPackage.COUNTING_RESULT__REPORTING_TIME:
				return getReportingTime();
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				if (resolve) return getObservedElement();
				return basicGetObservedElement();
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				return getOpcodeCounts();
			case OutputPackage.COUNTING_RESULT__METHOD_ID:
				return getMethodId();
			case OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME:
				return getQualifiedMethodName();
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				return getResultCollection();
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return getArrayCreationCounts();
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return getMethodCallCounts();
			case OutputPackage.COUNTING_RESULT__THREAD_ID:
				return getThreadId();
			case OutputPackage.COUNTING_RESULT__FINAL:
				return isFinal();
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return getSpawnedThreadedCountingResults();
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				return getThreadedCountingResult();
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				return getRequestResult();
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
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				setCallerId((UUID)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME:
				setMethodInvocationStartTime((Long)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__REPORTING_TIME:
				setReportingTime((Long)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				setObservedElement((EntityToInstrument)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				getOpcodeCounts().clear();
				getOpcodeCounts().addAll((Collection<? extends Long>)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_ID:
				setMethodId((UUID)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME:
				setQualifiedMethodName((String)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				setResultCollection((ResultCollection)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				getArrayCreationCounts().clear();
				getArrayCreationCounts().addAll((Collection<? extends ArrayCreationCount>)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				getMethodCallCounts().clear();
				getMethodCallCounts().addAll((Collection<? extends MethodCallCount>)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__THREAD_ID:
				setThreadId((Long)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__FINAL:
				setFinal((Boolean)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				getSpawnedThreadedCountingResults().clear();
				getSpawnedThreadedCountingResults().addAll((Collection<? extends CountingResult>)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				setThreadedCountingResult((CountingResult)newValue);
				return;
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				setRequestResult((RequestResult)newValue);
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
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				setCallerId((UUID)null);
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME:
				setMethodInvocationStartTime(METHOD_INVOCATION_START_TIME_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__REPORTING_TIME:
				setReportingTime(REPORTING_TIME_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				setObservedElement((EntityToInstrument)null);
				return;
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				getOpcodeCounts().clear();
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_ID:
				setMethodId((UUID)null);
				return;
			case OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME:
				setQualifiedMethodName(QUALIFIED_METHOD_NAME_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				setResultCollection((ResultCollection)null);
				return;
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				getArrayCreationCounts().clear();
				return;
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				getMethodCallCounts().clear();
				return;
			case OutputPackage.COUNTING_RESULT__THREAD_ID:
				setThreadId(THREAD_ID_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__FINAL:
				setFinal(FINAL_EDEFAULT);
				return;
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				getSpawnedThreadedCountingResults().clear();
				return;
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				setThreadedCountingResult((CountingResult)null);
				return;
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				setRequestResult((RequestResult)null);
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
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
				return callerId != null;
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME:
				return methodInvocationStartTime != METHOD_INVOCATION_START_TIME_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__REPORTING_TIME:
				return reportingTime != REPORTING_TIME_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
				return observedElement != null;
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
				return opcodeCounts != null && !opcodeCounts.isEmpty();
			case OutputPackage.COUNTING_RESULT__METHOD_ID:
				return methodId != null;
			case OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME:
				return QUALIFIED_METHOD_NAME_EDEFAULT == null ? qualifiedMethodName != null : !QUALIFIED_METHOD_NAME_EDEFAULT.equals(qualifiedMethodName);
			case OutputPackage.COUNTING_RESULT__RESULT_COLLECTION:
				return getResultCollection() != null;
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
				return arrayCreationCounts != null && !arrayCreationCounts.isEmpty();
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				return methodCallCounts != null && !methodCallCounts.isEmpty();
			case OutputPackage.COUNTING_RESULT__THREAD_ID:
				return threadId != THREAD_ID_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__FINAL:
				return final_ != FINAL_EDEFAULT;
			case OutputPackage.COUNTING_RESULT__SPAWNED_THREADED_COUNTING_RESULTS:
				return spawnedThreadedCountingResults != null && !spawnedThreadedCountingResults.isEmpty();
			case OutputPackage.COUNTING_RESULT__THREADED_COUNTING_RESULT:
				return getThreadedCountingResult() != null;
			case OutputPackage.COUNTING_RESULT__REQUEST_RESULT:
				return getRequestResult() != null;
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
		result.append(" (methodInvocationStartTime: ");
		result.append(methodInvocationStartTime);
		result.append(", reportingTime: ");
		result.append(reportingTime);
		result.append(", opcodeCounts: ");
		result.append(opcodeCounts);
		result.append(", qualifiedMethodName: ");
		result.append(qualifiedMethodName);
		result.append(", threadId: ");
		result.append(threadId);
		result.append(", final: ");
		result.append(final_);
		result.append(')');
		return result.toString();
	}

} //CountingResultImpl
