/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;

import edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.Request;
import edu.kit.ipd.sdq.bycounter.output.ResourceDemands;

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
 * An implementation of the model object '<em><b>Observed Entity Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl#getResourceDemands <em>Resource Demands</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl#getObservedForkedExecutionSequence <em>Observed Forked Execution Sequence</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl#getObservedExecutionSequenceForkedBy <em>Observed Execution Sequence Forked By</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl#getEntity <em>Entity</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl#getRequest <em>Request</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ObservedEntityExecutionImpl extends IdentifierImpl implements ObservedEntityExecution {
	/**
	 * The cached value of the '{@link #getResourceDemands() <em>Resource Demands</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceDemands()
	 * @generated
	 * @ordered
	 */
	protected ResourceDemands resourceDemands;

	/**
	 * The cached value of the '{@link #getObservedForkedExecutionSequence() <em>Observed Forked Execution Sequence</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObservedForkedExecutionSequence()
	 * @generated
	 * @ordered
	 */
	protected EList<ObservedEntityExecution> observedForkedExecutionSequence;

	/**
	 * The cached value of the '{@link #getEntity() <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntity()
	 * @generated
	 * @ordered
	 */
	protected EntityToInstrument entity;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObservedEntityExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.OBSERVED_ENTITY_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDemands getResourceDemands() {
		return resourceDemands;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceDemands(ResourceDemands newResourceDemands, NotificationChain msgs) {
		ResourceDemands oldResourceDemands = resourceDemands;
		resourceDemands = newResourceDemands;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS, oldResourceDemands, newResourceDemands);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceDemands(ResourceDemands newResourceDemands) {
		if (newResourceDemands != resourceDemands) {
			NotificationChain msgs = null;
			if (resourceDemands != null)
				msgs = ((InternalEObject)resourceDemands).eInverseRemove(this, OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION, ResourceDemands.class, msgs);
			if (newResourceDemands != null)
				msgs = ((InternalEObject)newResourceDemands).eInverseAdd(this, OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION, ResourceDemands.class, msgs);
			msgs = basicSetResourceDemands(newResourceDemands, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS, newResourceDemands, newResourceDemands));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObservedEntityExecution> getObservedForkedExecutionSequence() {
		if (observedForkedExecutionSequence == null) {
			observedForkedExecutionSequence = new EObjectContainmentWithInverseEList<ObservedEntityExecution>(ObservedEntityExecution.class, this, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY);
		}
		return observedForkedExecutionSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObservedEntityExecution getObservedExecutionSequenceForkedBy() {
		if (eContainerFeatureID() != OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY) return null;
		return (ObservedEntityExecution)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetObservedExecutionSequenceForkedBy(ObservedEntityExecution newObservedExecutionSequenceForkedBy, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newObservedExecutionSequenceForkedBy, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObservedExecutionSequenceForkedBy(ObservedEntityExecution newObservedExecutionSequenceForkedBy) {
		if (newObservedExecutionSequenceForkedBy != eInternalContainer() || (eContainerFeatureID() != OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY && newObservedExecutionSequenceForkedBy != null)) {
			if (EcoreUtil.isAncestor(this, newObservedExecutionSequenceForkedBy))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newObservedExecutionSequenceForkedBy != null)
				msgs = ((InternalEObject)newObservedExecutionSequenceForkedBy).eInverseAdd(this, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE, ObservedEntityExecution.class, msgs);
			msgs = basicSetObservedExecutionSequenceForkedBy(newObservedExecutionSequenceForkedBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY, newObservedExecutionSequenceForkedBy, newObservedExecutionSequenceForkedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityToInstrument getEntity() {
		if (entity != null && entity.eIsProxy()) {
			InternalEObject oldEntity = (InternalEObject)entity;
			entity = (EntityToInstrument)eResolveProxy(oldEntity);
			if (entity != oldEntity) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY, oldEntity, entity));
			}
		}
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EntityToInstrument basicGetEntity() {
		return entity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEntity(EntityToInstrument newEntity) {
		EntityToInstrument oldEntity = entity;
		entity = newEntity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY, oldEntity, entity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Request getRequest() {
		if (eContainerFeatureID() != OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST) return null;
		return (Request)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRequest(Request newRequest, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRequest, OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequest(Request newRequest) {
		if (newRequest != eInternalContainer() || (eContainerFeatureID() != OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST && newRequest != null)) {
			if (EcoreUtil.isAncestor(this, newRequest))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRequest != null)
				msgs = ((InternalEObject)newRequest).eInverseAdd(this, OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE, Request.class, msgs);
			msgs = basicSetRequest(newRequest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST, newRequest, newRequest));
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				if (resourceDemands != null)
					msgs = ((InternalEObject)resourceDemands).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS, null, msgs);
				return basicSetResourceDemands((ResourceDemands)otherEnd, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getObservedForkedExecutionSequence()).basicAdd(otherEnd, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetObservedExecutionSequenceForkedBy((ObservedEntityExecution)otherEnd, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRequest((Request)otherEnd, msgs);
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				return basicSetResourceDemands(null, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				return ((InternalEList<?>)getObservedForkedExecutionSequence()).basicRemove(otherEnd, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				return basicSetObservedExecutionSequenceForkedBy(null, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				return basicSetRequest(null, msgs);
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				return eInternalContainer().eInverseRemove(this, OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE, ObservedEntityExecution.class, msgs);
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				return eInternalContainer().eInverseRemove(this, OutputPackage.REQUEST__OBSERVED_EXECUTION_SEQUENCE, Request.class, msgs);
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				return getResourceDemands();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				return getObservedForkedExecutionSequence();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				return getObservedExecutionSequenceForkedBy();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY:
				if (resolve) return getEntity();
				return basicGetEntity();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				return getRequest();
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				setResourceDemands((ResourceDemands)newValue);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				getObservedForkedExecutionSequence().clear();
				getObservedForkedExecutionSequence().addAll((Collection<? extends ObservedEntityExecution>)newValue);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				setObservedExecutionSequenceForkedBy((ObservedEntityExecution)newValue);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY:
				setEntity((EntityToInstrument)newValue);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				setRequest((Request)newValue);
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				setResourceDemands((ResourceDemands)null);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				getObservedForkedExecutionSequence().clear();
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				setObservedExecutionSequenceForkedBy((ObservedEntityExecution)null);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY:
				setEntity((EntityToInstrument)null);
				return;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				setRequest((Request)null);
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
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS:
				return resourceDemands != null;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE:
				return observedForkedExecutionSequence != null && !observedForkedExecutionSequence.isEmpty();
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY:
				return getObservedExecutionSequenceForkedBy() != null;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__ENTITY:
				return entity != null;
			case OutputPackage.OBSERVED_ENTITY_EXECUTION__REQUEST:
				return getRequest() != null;
		}
		return super.eIsSet(featureID);
	}

} //ObservedEntityExecutionImpl
