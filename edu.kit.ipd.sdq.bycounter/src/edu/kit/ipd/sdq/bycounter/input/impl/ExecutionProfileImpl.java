/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;

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
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#isAddUpResultsRecursively <em>Add Up Results Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#getDefinedLogicalSets <em>Defined Logical Sets</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#getInstrumentationProfileRepository <em>Instrumentation Profile Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionProfileImpl extends EObjectImpl implements ExecutionProfile {
	/**
	 * The default value of the '{@link #isAddUpResultsRecursively() <em>Add Up Results Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAddUpResultsRecursively()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ADD_UP_RESULTS_RECURSIVELY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAddUpResultsRecursively() <em>Add Up Results Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAddUpResultsRecursively()
	 * @generated
	 * @ordered
	 */
	protected boolean addUpResultsRecursively = ADD_UP_RESULTS_RECURSIVELY_EDEFAULT;

	/**
	 * The default value of the '{@link #isWaitForThreadsToFinnish() <em>Wait For Threads To Finnish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWaitForThreadsToFinnish()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isWaitForThreadsToFinnish() <em>Wait For Threads To Finnish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWaitForThreadsToFinnish()
	 * @generated
	 * @ordered
	 */
	protected boolean waitForThreadsToFinnish = WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDefinedLogicalSets() <em>Defined Logical Sets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinedLogicalSets()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalSet> definedLogicalSets;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.EXECUTION_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAddUpResultsRecursively() {
		return addUpResultsRecursively;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddUpResultsRecursively(boolean newAddUpResultsRecursively) {
		boolean oldAddUpResultsRecursively = addUpResultsRecursively;
		addUpResultsRecursively = newAddUpResultsRecursively;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY, oldAddUpResultsRecursively, addUpResultsRecursively));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isWaitForThreadsToFinnish() {
		return waitForThreadsToFinnish;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaitForThreadsToFinnish(boolean newWaitForThreadsToFinnish) {
		boolean oldWaitForThreadsToFinnish = waitForThreadsToFinnish;
		waitForThreadsToFinnish = newWaitForThreadsToFinnish;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH, oldWaitForThreadsToFinnish, waitForThreadsToFinnish));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LogicalSet> getDefinedLogicalSets() {
		if (definedLogicalSets == null) {
			definedLogicalSets = new EObjectContainmentWithInverseEList<LogicalSet>(LogicalSet.class, this, InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS, InputPackage.LOGICAL_SET__EXECUTION_PROFILE);
		}
		return definedLogicalSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfileRepository getInstrumentationProfileRepository() {
		if (eContainerFeatureID() != InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY) return null;
		return (InstrumentationProfileRepository)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstrumentationProfileRepository(InstrumentationProfileRepository newInstrumentationProfileRepository, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInstrumentationProfileRepository, InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentationProfileRepository(InstrumentationProfileRepository newInstrumentationProfileRepository) {
		if (newInstrumentationProfileRepository != eInternalContainer() || (eContainerFeatureID() != InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY && newInstrumentationProfileRepository != null)) {
			if (EcoreUtil.isAncestor(this, newInstrumentationProfileRepository))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInstrumentationProfileRepository != null)
				msgs = ((InternalEObject)newInstrumentationProfileRepository).eInverseAdd(this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE, InstrumentationProfileRepository.class, msgs);
			msgs = basicSetInstrumentationProfileRepository(newInstrumentationProfileRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY, newInstrumentationProfileRepository, newInstrumentationProfileRepository));
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
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDefinedLogicalSets()).basicAdd(otherEnd, msgs);
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInstrumentationProfileRepository((InstrumentationProfileRepository)otherEnd, msgs);
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
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				return ((InternalEList<?>)getDefinedLogicalSets()).basicRemove(otherEnd, msgs);
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				return basicSetInstrumentationProfileRepository(null, msgs);
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
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				return eInternalContainer().eInverseRemove(this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE, InstrumentationProfileRepository.class, msgs);
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
			case InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY:
				return isAddUpResultsRecursively();
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				return isWaitForThreadsToFinnish();
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				return getDefinedLogicalSets();
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				return getInstrumentationProfileRepository();
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
			case InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY:
				setAddUpResultsRecursively((Boolean)newValue);
				return;
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				setWaitForThreadsToFinnish((Boolean)newValue);
				return;
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				getDefinedLogicalSets().clear();
				getDefinedLogicalSets().addAll((Collection<? extends LogicalSet>)newValue);
				return;
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				setInstrumentationProfileRepository((InstrumentationProfileRepository)newValue);
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
			case InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY:
				setAddUpResultsRecursively(ADD_UP_RESULTS_RECURSIVELY_EDEFAULT);
				return;
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				setWaitForThreadsToFinnish(WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT);
				return;
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				getDefinedLogicalSets().clear();
				return;
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				setInstrumentationProfileRepository((InstrumentationProfileRepository)null);
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
			case InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY:
				return addUpResultsRecursively != ADD_UP_RESULTS_RECURSIVELY_EDEFAULT;
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				return waitForThreadsToFinnish != WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT;
			case InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS:
				return definedLogicalSets != null && !definedLogicalSets.isEmpty();
			case InputPackage.EXECUTION_PROFILE__INSTRUMENTATION_PROFILE_REPOSITORY:
				return getInstrumentationProfileRepository() != null;
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
		result.append(" (addUpResultsRecursively: ");
		result.append(addUpResultsRecursively);
		result.append(", waitForThreadsToFinnish: ");
		result.append(waitForThreadsToFinnish);
		result.append(')');
		return result.toString();
	}

} //ExecutionProfileImpl
