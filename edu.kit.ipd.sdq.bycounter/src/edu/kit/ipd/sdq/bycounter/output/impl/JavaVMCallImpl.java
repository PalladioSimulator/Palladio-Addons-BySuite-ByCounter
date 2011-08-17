/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.JavaVMCall;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ResourceDemands;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java VM Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl#getNumberObservations <em>Number Observations</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl#getId <em>Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl#getName <em>Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl#getResourceDemands <em>Resource Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaVMCallImpl extends EObjectImpl implements JavaVMCall {
	/**
	 * The default value of the '{@link #getNumberObservations() <em>Number Observations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberObservations()
	 * @generated
	 * @ordered
	 */
	protected static final long NUMBER_OBSERVATIONS_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNumberObservations() <em>Number Observations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberObservations()
	 * @generated
	 * @ordered
	 */
	protected long numberObservations = NUMBER_OBSERVATIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final byte ID_EDEFAULT = 0x00;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected byte id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JavaVMCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.JAVA_VM_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getNumberObservations() {
		return numberObservations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberObservations(long newNumberObservations) {
		long oldNumberObservations = numberObservations;
		numberObservations = newNumberObservations;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.JAVA_VM_CALL__NUMBER_OBSERVATIONS, oldNumberObservations, numberObservations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public byte getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(byte newId) {
		byte oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.JAVA_VM_CALL__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.JAVA_VM_CALL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDemands getResourceDemands() {
		if (eContainerFeatureID() != OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS) return null;
		return (ResourceDemands)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceDemands(ResourceDemands newResourceDemands, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResourceDemands, OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceDemands(ResourceDemands newResourceDemands) {
		if (newResourceDemands != eInternalContainer() || (eContainerFeatureID() != OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS && newResourceDemands != null)) {
			if (EcoreUtil.isAncestor(this, newResourceDemands))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResourceDemands != null)
				msgs = ((InternalEObject)newResourceDemands).eInverseAdd(this, OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL, ResourceDemands.class, msgs);
			msgs = basicSetResourceDemands(newResourceDemands, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS, newResourceDemands, newResourceDemands));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResourceDemands((ResourceDemands)otherEnd, msgs);
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
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				return basicSetResourceDemands(null, msgs);
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
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				return eInternalContainer().eInverseRemove(this, OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL, ResourceDemands.class, msgs);
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
			case OutputPackage.JAVA_VM_CALL__NUMBER_OBSERVATIONS:
				return getNumberObservations();
			case OutputPackage.JAVA_VM_CALL__ID:
				return getId();
			case OutputPackage.JAVA_VM_CALL__NAME:
				return getName();
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				return getResourceDemands();
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
			case OutputPackage.JAVA_VM_CALL__NUMBER_OBSERVATIONS:
				setNumberObservations((Long)newValue);
				return;
			case OutputPackage.JAVA_VM_CALL__ID:
				setId((Byte)newValue);
				return;
			case OutputPackage.JAVA_VM_CALL__NAME:
				setName((String)newValue);
				return;
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				setResourceDemands((ResourceDemands)newValue);
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
			case OutputPackage.JAVA_VM_CALL__NUMBER_OBSERVATIONS:
				setNumberObservations(NUMBER_OBSERVATIONS_EDEFAULT);
				return;
			case OutputPackage.JAVA_VM_CALL__ID:
				setId(ID_EDEFAULT);
				return;
			case OutputPackage.JAVA_VM_CALL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				setResourceDemands((ResourceDemands)null);
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
			case OutputPackage.JAVA_VM_CALL__NUMBER_OBSERVATIONS:
				return numberObservations != NUMBER_OBSERVATIONS_EDEFAULT;
			case OutputPackage.JAVA_VM_CALL__ID:
				return id != ID_EDEFAULT;
			case OutputPackage.JAVA_VM_CALL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS:
				return getResourceDemands() != null;
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
		result.append(" (numberObservations: ");
		result.append(numberObservations);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //JavaVMCallImpl
