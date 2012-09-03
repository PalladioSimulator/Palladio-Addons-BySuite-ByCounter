/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#isAddUpResultsRecursively <em>Add Up Results Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#getInternalClassesDefinition <em>Internal Classes Definition</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.ExecutionProfileImpl#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}</li>
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
	protected static final boolean ADD_UP_RESULTS_RECURSIVELY_EDEFAULT = false;

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
	 * The cached value of the '{@link #getInternalClassesDefinition() <em>Internal Classes Definition</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalClassesDefinition()
	 * @generated
	 * @ordered
	 */
	protected EList<String> internalClassesDefinition;

	/**
	 * The default value of the '{@link #isWaitForThreadsToFinnish() <em>Wait For Threads To Finnish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isWaitForThreadsToFinnish()
	 * @generated
	 * @ordered
	 */
	protected static final boolean WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT = false;

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
	public EList<String> getInternalClassesDefinition() {
		if (internalClassesDefinition == null) {
			internalClassesDefinition = new EDataTypeUniqueEList<String>(String.class, this, InputPackage.EXECUTION_PROFILE__INTERNAL_CLASSES_DEFINITION);
		}
		return internalClassesDefinition;
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InputPackage.EXECUTION_PROFILE__ADD_UP_RESULTS_RECURSIVELY:
				return isAddUpResultsRecursively();
			case InputPackage.EXECUTION_PROFILE__INTERNAL_CLASSES_DEFINITION:
				return getInternalClassesDefinition();
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				return isWaitForThreadsToFinnish();
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
			case InputPackage.EXECUTION_PROFILE__INTERNAL_CLASSES_DEFINITION:
				getInternalClassesDefinition().clear();
				getInternalClassesDefinition().addAll((Collection<? extends String>)newValue);
				return;
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				setWaitForThreadsToFinnish((Boolean)newValue);
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
			case InputPackage.EXECUTION_PROFILE__INTERNAL_CLASSES_DEFINITION:
				getInternalClassesDefinition().clear();
				return;
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				setWaitForThreadsToFinnish(WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT);
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
			case InputPackage.EXECUTION_PROFILE__INTERNAL_CLASSES_DEFINITION:
				return internalClassesDefinition != null && !internalClassesDefinition.isEmpty();
			case InputPackage.EXECUTION_PROFILE__WAIT_FOR_THREADS_TO_FINNISH:
				return waitForThreadsToFinnish != WAIT_FOR_THREADS_TO_FINNISH_EDEFAULT;
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
		result.append(", internalClassesDefinition: ");
		result.append(internalClassesDefinition);
		result.append(", waitForThreadsToFinnish: ");
		result.append(waitForThreadsToFinnish);
		result.append(')');
		return result.toString();
	}

} //ExecutionProfileImpl
