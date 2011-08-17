/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription;
import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.Request;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measurement Run</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl#getEnvironmentCharacterisation <em>Environment Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl#getRequests <em>Requests</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementRunImpl extends IdentifierImpl implements MeasurementRun {
	/**
	 * The cached value of the '{@link #getEnvironmentCharacterisation() <em>Environment Characterisation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentCharacterisation()
	 * @generated
	 * @ordered
	 */
	protected EnvironmentDescription environmentCharacterisation;

	/**
	 * The cached value of the '{@link #getRequests() <em>Requests</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequests()
	 * @generated
	 * @ordered
	 */
	protected EList<Request> requests;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasurementRunImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.MEASUREMENT_RUN;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentDescription getEnvironmentCharacterisation() {
		if (environmentCharacterisation != null && environmentCharacterisation.eIsProxy()) {
			InternalEObject oldEnvironmentCharacterisation = (InternalEObject)environmentCharacterisation;
			environmentCharacterisation = (EnvironmentDescription)eResolveProxy(oldEnvironmentCharacterisation);
			if (environmentCharacterisation != oldEnvironmentCharacterisation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION, oldEnvironmentCharacterisation, environmentCharacterisation));
			}
		}
		return environmentCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvironmentDescription basicGetEnvironmentCharacterisation() {
		return environmentCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvironmentCharacterisation(EnvironmentDescription newEnvironmentCharacterisation) {
		EnvironmentDescription oldEnvironmentCharacterisation = environmentCharacterisation;
		environmentCharacterisation = newEnvironmentCharacterisation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION, oldEnvironmentCharacterisation, environmentCharacterisation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Request> getRequests() {
		if (requests == null) {
			requests = new EObjectContainmentWithInverseEList<Request>(Request.class, this, OutputPackage.MEASUREMENT_RUN__REQUESTS, OutputPackage.REQUEST__MEASUREMENT_RUN);
		}
		return requests;
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
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequests()).basicAdd(otherEnd, msgs);
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
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				return ((InternalEList<?>)getRequests()).basicRemove(otherEnd, msgs);
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
			case OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION:
				if (resolve) return getEnvironmentCharacterisation();
				return basicGetEnvironmentCharacterisation();
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				return getRequests();
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
			case OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION:
				setEnvironmentCharacterisation((EnvironmentDescription)newValue);
				return;
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				getRequests().clear();
				getRequests().addAll((Collection<? extends Request>)newValue);
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
			case OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION:
				setEnvironmentCharacterisation((EnvironmentDescription)null);
				return;
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				getRequests().clear();
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
			case OutputPackage.MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION:
				return environmentCharacterisation != null;
			case OutputPackage.MEASUREMENT_RUN__REQUESTS:
				return requests != null && !requests.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MeasurementRunImpl
