/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity To Instrument</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.EntityToInstrumentImpl#getInstrumentationProfile <em>Instrumentation Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EntityToInstrumentImpl extends EObjectImpl implements EntityToInstrument {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityToInstrumentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.ENTITY_TO_INSTRUMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfile getInstrumentationProfile() {
		if (eContainerFeatureID() != InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE) return null;
		return (InstrumentationProfile)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInstrumentationProfile(InstrumentationProfile newInstrumentationProfile, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newInstrumentationProfile, InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentationProfile(InstrumentationProfile newInstrumentationProfile) {
		if (newInstrumentationProfile != eInternalContainer() || (eContainerFeatureID() != InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE && newInstrumentationProfile != null)) {
			if (EcoreUtil.isAncestor(this, newInstrumentationProfile))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newInstrumentationProfile != null)
				msgs = ((InternalEObject)newInstrumentationProfile).eInverseAdd(this, InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT, InstrumentationProfile.class, msgs);
			msgs = basicSetInstrumentationProfile(newInstrumentationProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE, newInstrumentationProfile, newInstrumentationProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetInstrumentationProfile((InstrumentationProfile)otherEnd, msgs);
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				return basicSetInstrumentationProfile(null, msgs);
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				return eInternalContainer().eInverseRemove(this, InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT, InstrumentationProfile.class, msgs);
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				return getInstrumentationProfile();
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				setInstrumentationProfile((InstrumentationProfile)newValue);
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				setInstrumentationProfile((InstrumentationProfile)null);
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
			case InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE:
				return getInstrumentationProfile() != null;
		}
		return super.eIsSet(featureID);
	}

} //EntityToInstrumentImpl
