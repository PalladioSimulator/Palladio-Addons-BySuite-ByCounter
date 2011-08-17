/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.ParameterInstance;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Characterisation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl#getCharacterisation <em>Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl#getParameterInstance <em>Parameter Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ParameterCharacterisationImpl extends IdentifierImpl implements ParameterCharacterisation {
	/**
	 * The default value of the '{@link #getCharacterisation() <em>Characterisation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterisation()
	 * @generated
	 * @ordered
	 */
	protected static final String CHARACTERISATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCharacterisation() <em>Characterisation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharacterisation()
	 * @generated
	 * @ordered
	 */
	protected String characterisation = CHARACTERISATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterCharacterisationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.PARAMETER_CHARACTERISATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCharacterisation() {
		return characterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCharacterisation(String newCharacterisation) {
		String oldCharacterisation = characterisation;
		characterisation = newCharacterisation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.PARAMETER_CHARACTERISATION__CHARACTERISATION, oldCharacterisation, characterisation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterInstance getParameterInstance() {
		if (eContainerFeatureID() != OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE) return null;
		return (ParameterInstance)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParameterInstance(ParameterInstance newParameterInstance, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newParameterInstance, OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterInstance(ParameterInstance newParameterInstance) {
		if (newParameterInstance != eInternalContainer() || (eContainerFeatureID() != OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE && newParameterInstance != null)) {
			if (EcoreUtil.isAncestor(this, newParameterInstance))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newParameterInstance != null)
				msgs = ((InternalEObject)newParameterInstance).eInverseAdd(this, OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION, ParameterInstance.class, msgs);
			msgs = basicSetParameterInstance(newParameterInstance, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE, newParameterInstance, newParameterInstance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetParameterInstance((ParameterInstance)otherEnd, msgs);
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
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				return basicSetParameterInstance(null, msgs);
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
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				return eInternalContainer().eInverseRemove(this, OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION, ParameterInstance.class, msgs);
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
			case OutputPackage.PARAMETER_CHARACTERISATION__CHARACTERISATION:
				return getCharacterisation();
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				return getParameterInstance();
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
			case OutputPackage.PARAMETER_CHARACTERISATION__CHARACTERISATION:
				setCharacterisation((String)newValue);
				return;
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				setParameterInstance((ParameterInstance)newValue);
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
			case OutputPackage.PARAMETER_CHARACTERISATION__CHARACTERISATION:
				setCharacterisation(CHARACTERISATION_EDEFAULT);
				return;
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				setParameterInstance((ParameterInstance)null);
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
			case OutputPackage.PARAMETER_CHARACTERISATION__CHARACTERISATION:
				return CHARACTERISATION_EDEFAULT == null ? characterisation != null : !CHARACTERISATION_EDEFAULT.equals(characterisation);
			case OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE:
				return getParameterInstance() != null;
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
		result.append(" (characterisation: ");
		result.append(characterisation);
		result.append(')');
		return result.toString();
	}

} //ParameterCharacterisationImpl
