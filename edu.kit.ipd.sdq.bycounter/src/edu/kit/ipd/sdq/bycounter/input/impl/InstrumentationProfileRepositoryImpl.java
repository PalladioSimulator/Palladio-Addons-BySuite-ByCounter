/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.input.ExecutionProfile;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumentation Profile Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl#getExecutionProfile <em>Execution Profile</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileRepositoryImpl#getInstrumentationProfile <em>Instrumentation Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentationProfileRepositoryImpl extends IdentifierImpl implements InstrumentationProfileRepository {
	/**
	 * The cached value of the '{@link #getExecutionProfile() <em>Execution Profile</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionProfile()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionProfile> executionProfile;

	/**
	 * The cached value of the '{@link #getInstrumentationProfile() <em>Instrumentation Profile</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstrumentationProfile()
	 * @generated
	 * @ordered
	 */
	protected EList<InstrumentationProfile> instrumentationProfile;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentationProfileRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.INSTRUMENTATION_PROFILE_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionProfile> getExecutionProfile() {
		if (executionProfile == null) {
			executionProfile = new EObjectContainmentEList<ExecutionProfile>(ExecutionProfile.class, this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE);
		}
		return executionProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstrumentationProfile> getInstrumentationProfile() {
		if (instrumentationProfile == null) {
			instrumentationProfile = new EObjectContainmentWithInverseEList<InstrumentationProfile>(InstrumentationProfile.class, this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE, InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY);
		}
		return instrumentationProfile;
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInstrumentationProfile()).basicAdd(otherEnd, msgs);
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE:
				return ((InternalEList<?>)getExecutionProfile()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				return ((InternalEList<?>)getInstrumentationProfile()).basicRemove(otherEnd, msgs);
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE:
				return getExecutionProfile();
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				return getInstrumentationProfile();
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE:
				getExecutionProfile().clear();
				getExecutionProfile().addAll((Collection<? extends ExecutionProfile>)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				getInstrumentationProfile().clear();
				getInstrumentationProfile().addAll((Collection<? extends InstrumentationProfile>)newValue);
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE:
				getExecutionProfile().clear();
				return;
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				getInstrumentationProfile().clear();
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
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__EXECUTION_PROFILE:
				return executionProfile != null && !executionProfile.isEmpty();
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE:
				return instrumentationProfile != null && !instrumentationProfile.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InstrumentationProfileRepositoryImpl
