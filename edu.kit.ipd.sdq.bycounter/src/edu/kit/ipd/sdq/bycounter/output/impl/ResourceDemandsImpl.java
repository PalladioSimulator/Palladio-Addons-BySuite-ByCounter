/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.FunctionCall;
import edu.kit.ipd.sdq.bycounter.output.JavaVMCall;
import edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
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
 * An implementation of the model object '<em><b>Resource Demands</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl#getFunctionCalls <em>Function Calls</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl#getJavaVMCall <em>Java VM Call</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl#getObservedExecution <em>Observed Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceDemandsImpl extends IdentifierImpl implements ResourceDemands {
	/**
	 * The cached value of the '{@link #getFunctionCalls() <em>Function Calls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunctionCalls()
	 * @generated
	 * @ordered
	 */
	protected EList<FunctionCall> functionCalls;

	/**
	 * The cached value of the '{@link #getJavaVMCall() <em>Java VM Call</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJavaVMCall()
	 * @generated
	 * @ordered
	 */
	protected EList<JavaVMCall> javaVMCall;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceDemandsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.RESOURCE_DEMANDS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FunctionCall> getFunctionCalls() {
		if (functionCalls == null) {
			functionCalls = new EObjectContainmentWithInverseEList<FunctionCall>(FunctionCall.class, this, OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS, OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS);
		}
		return functionCalls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JavaVMCall> getJavaVMCall() {
		if (javaVMCall == null) {
			javaVMCall = new EObjectContainmentWithInverseEList<JavaVMCall>(JavaVMCall.class, this, OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL, OutputPackage.JAVA_VM_CALL__RESOURCE_DEMANDS);
		}
		return javaVMCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObservedEntityExecution getObservedExecution() {
		if (eContainerFeatureID() != OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION) return null;
		return (ObservedEntityExecution)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetObservedExecution(ObservedEntityExecution newObservedExecution, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newObservedExecution, OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObservedExecution(ObservedEntityExecution newObservedExecution) {
		if (newObservedExecution != eInternalContainer() || (eContainerFeatureID() != OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION && newObservedExecution != null)) {
			if (EcoreUtil.isAncestor(this, newObservedExecution))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newObservedExecution != null)
				msgs = ((InternalEObject)newObservedExecution).eInverseAdd(this, OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS, ObservedEntityExecution.class, msgs);
			msgs = basicSetObservedExecution(newObservedExecution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION, newObservedExecution, newObservedExecution));
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFunctionCalls()).basicAdd(otherEnd, msgs);
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getJavaVMCall()).basicAdd(otherEnd, msgs);
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetObservedExecution((ObservedEntityExecution)otherEnd, msgs);
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				return ((InternalEList<?>)getFunctionCalls()).basicRemove(otherEnd, msgs);
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				return ((InternalEList<?>)getJavaVMCall()).basicRemove(otherEnd, msgs);
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				return basicSetObservedExecution(null, msgs);
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
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				return eInternalContainer().eInverseRemove(this, OutputPackage.OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS, ObservedEntityExecution.class, msgs);
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				return getFunctionCalls();
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				return getJavaVMCall();
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				return getObservedExecution();
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				getFunctionCalls().clear();
				getFunctionCalls().addAll((Collection<? extends FunctionCall>)newValue);
				return;
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				getJavaVMCall().clear();
				getJavaVMCall().addAll((Collection<? extends JavaVMCall>)newValue);
				return;
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				setObservedExecution((ObservedEntityExecution)newValue);
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				getFunctionCalls().clear();
				return;
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				getJavaVMCall().clear();
				return;
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				setObservedExecution((ObservedEntityExecution)null);
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
			case OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS:
				return functionCalls != null && !functionCalls.isEmpty();
			case OutputPackage.RESOURCE_DEMANDS__JAVA_VM_CALL:
				return javaVMCall != null && !javaVMCall.isEmpty();
			case OutputPackage.RESOURCE_DEMANDS__OBSERVED_EXECUTION:
				return getObservedExecution() != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceDemandsImpl
