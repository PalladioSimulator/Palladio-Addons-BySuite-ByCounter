/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;
import edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Request</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl#getObservedExecutionSequence <em>Observed Execution Sequence</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl#getMeasurementRun <em>Measurement Run</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RequestImpl extends IdentifierImpl implements Request {
	/**
	 * The cached value of the '{@link #getObservedExecutionSequence() <em>Observed Execution Sequence</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedExecutionSequence()
	 * @generated
	 * @ordered
	 */
	protected EList<ObservedEntityExecution> observedExecutionSequence;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RequestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.REQUEST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObservedEntityExecution> getObservedExecutionSequence() {
		if (observedExecutionSequence == null) {
			observedExecutionSequence = new EObjectContainmentWithInverseEList<ObservedEntityExecution>(ObservedEntityExecution.class, this, OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE, OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST);
		}
		return observedExecutionSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementRun getMeasurementRun() {
		if (eContainerFeatureID() != OutputPackage.REQUEST__MEASUREMENT_RUN) return null;
		return (MeasurementRun)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMeasurementRun(MeasurementRun newMeasurementRun, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMeasurementRun, OutputPackage.REQUEST__MEASUREMENT_RUN, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeasurementRun(MeasurementRun newMeasurementRun) {
		if (newMeasurementRun != eInternalContainer() || (eContainerFeatureID() != OutputPackage.REQUEST__MEASUREMENT_RUN && newMeasurementRun != null)) {
			if (EcoreUtil.isAncestor(this, newMeasurementRun))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMeasurementRun != null)
				msgs = ((InternalEObject)newMeasurementRun).eInverseAdd(this, OutputPackage.MEASUREMENT_RUN__REQUESTS, MeasurementRun.class, msgs);
			msgs = basicSetMeasurementRun(newMeasurementRun, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.REQUEST__MEASUREMENT_RUN, newMeasurementRun, newMeasurementRun));
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getObservedExecutionSequence()).basicAdd(otherEnd, msgs);
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMeasurementRun((MeasurementRun)otherEnd, msgs);
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				return ((InternalEList<?>)getObservedExecutionSequence()).basicRemove(otherEnd, msgs);
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				return basicSetMeasurementRun(null, msgs);
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
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				return eInternalContainer().eInverseRemove(this, OutputPackage.MEASUREMENT_RUN__REQUESTS, MeasurementRun.class, msgs);
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				return getObservedExecutionSequence();
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				return getMeasurementRun();
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				getObservedExecutionSequence().clear();
				getObservedExecutionSequence().addAll((Collection<? extends ObservedEntityExecution>)newValue);
				return;
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				setMeasurementRun((MeasurementRun)newValue);
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				getObservedExecutionSequence().clear();
				return;
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				setMeasurementRun((MeasurementRun)null);
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
			case OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE:
				return observedExecutionSequence != null && !observedExecutionSequence.isEmpty();
			case OutputPackage.REQUEST__MEASUREMENT_RUN:
				return getMeasurementRun() != null;
		}
		return super.eIsSet(featureID);
	}

} //RequestImpl
