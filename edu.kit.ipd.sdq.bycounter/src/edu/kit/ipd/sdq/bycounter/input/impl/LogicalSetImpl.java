/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.types.GASTClass;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl#getInternalClasses <em>Internal Classes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.LogicalSetImpl#getExecutionProfile <em>Execution Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalSetImpl extends IdentifierImpl implements LogicalSet {
	/**
	 * The cached value of the '{@link #getInternalClasses() <em>Internal Classes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<GASTClass> internalClasses;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogicalSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.LOGICAL_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GASTClass> getInternalClasses() {
		if (internalClasses == null) {
			internalClasses = new EObjectResolvingEList<GASTClass>(GASTClass.class, this, InputPackage.LOGICAL_SET__INTERNAL_CLASSES);
		}
		return internalClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionProfile getExecutionProfile() {
		if (eContainerFeatureID() != InputPackage.LOGICAL_SET__EXECUTION_PROFILE) return null;
		return (ExecutionProfile)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutionProfile(ExecutionProfile newExecutionProfile, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExecutionProfile, InputPackage.LOGICAL_SET__EXECUTION_PROFILE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionProfile(ExecutionProfile newExecutionProfile) {
		if (newExecutionProfile != eInternalContainer() || (eContainerFeatureID() != InputPackage.LOGICAL_SET__EXECUTION_PROFILE && newExecutionProfile != null)) {
			if (EcoreUtil.isAncestor(this, newExecutionProfile))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExecutionProfile != null)
				msgs = ((InternalEObject)newExecutionProfile).eInverseAdd(this, InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS, ExecutionProfile.class, msgs);
			msgs = basicSetExecutionProfile(newExecutionProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.LOGICAL_SET__EXECUTION_PROFILE, newExecutionProfile, newExecutionProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExecutionProfile((ExecutionProfile)otherEnd, msgs);
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
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				return basicSetExecutionProfile(null, msgs);
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
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				return eInternalContainer().eInverseRemove(this, InputPackage.EXECUTION_PROFILE__DEFINED_LOGICAL_SETS, ExecutionProfile.class, msgs);
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
			case InputPackage.LOGICAL_SET__INTERNAL_CLASSES:
				return getInternalClasses();
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				return getExecutionProfile();
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
			case InputPackage.LOGICAL_SET__INTERNAL_CLASSES:
				getInternalClasses().clear();
				getInternalClasses().addAll((Collection<? extends GASTClass>)newValue);
				return;
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				setExecutionProfile((ExecutionProfile)newValue);
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
			case InputPackage.LOGICAL_SET__INTERNAL_CLASSES:
				getInternalClasses().clear();
				return;
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				setExecutionProfile((ExecutionProfile)null);
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
			case InputPackage.LOGICAL_SET__INTERNAL_CLASSES:
				return internalClasses != null && !internalClasses.isEmpty();
			case InputPackage.LOGICAL_SET__EXECUTION_PROFILE:
				return getExecutionProfile() != null;
		}
		return super.eIsSet(featureID);
	}

} //LogicalSetImpl
