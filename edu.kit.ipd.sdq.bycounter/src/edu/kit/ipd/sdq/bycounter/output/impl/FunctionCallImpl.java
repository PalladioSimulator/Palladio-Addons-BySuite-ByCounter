/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.fzi.gast.functions.Function;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.FunctionCall;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ParameterInstance;
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
 * An implementation of the model object '<em><b>Function Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#getParameterInstance <em>Parameter Instance</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#getFunction <em>Function</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#getNumberObservations <em>Number Observations</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#isNative <em>Native</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl#getResourceDemands <em>Resource Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FunctionCallImpl extends IdentifierImpl implements FunctionCall {
	/**
	 * The cached value of the '{@link #getParameterInstance() <em>Parameter Instance</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterInstance()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterInstance> parameterInstance;

	/**
	 * The cached value of the '{@link #getFunction() <em>Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFunction()
	 * @generated
	 * @ordered
	 */
	protected Function function;

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
	 * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected boolean native_ = NATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNCHRONIZED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronized()
	 * @generated
	 * @ordered
	 */
	protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionCallImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.FUNCTION_CALL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterInstance> getParameterInstance() {
		if (parameterInstance == null) {
			parameterInstance = new EObjectContainmentWithInverseEList<ParameterInstance>(ParameterInstance.class, this, OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE, OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL);
		}
		return parameterInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function getFunction() {
		if (function != null && function.eIsProxy()) {
			InternalEObject oldFunction = (InternalEObject)function;
			function = (Function)eResolveProxy(oldFunction);
			if (function != oldFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.FUNCTION_CALL__FUNCTION, oldFunction, function));
			}
		}
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Function basicGetFunction() {
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunction(Function newFunction) {
		Function oldFunction = function;
		function = newFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.FUNCTION_CALL__FUNCTION, oldFunction, function));
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
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.FUNCTION_CALL__NUMBER_OBSERVATIONS, oldNumberObservations, numberObservations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isNative() {
		return native_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNative(boolean newNative) {
		boolean oldNative = native_;
		native_ = newNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.FUNCTION_CALL__NATIVE, oldNative, native_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSynchronized() {
		return synchronized_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronized(boolean newSynchronized) {
		boolean oldSynchronized = synchronized_;
		synchronized_ = newSynchronized;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.FUNCTION_CALL__SYNCHRONIZED, oldSynchronized, synchronized_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceDemands getResourceDemands() {
		if (eContainerFeatureID() != OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS) return null;
		return (ResourceDemands)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResourceDemands(ResourceDemands newResourceDemands, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResourceDemands, OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceDemands(ResourceDemands newResourceDemands) {
		if (newResourceDemands != eInternalContainer() || (eContainerFeatureID() != OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS && newResourceDemands != null)) {
			if (EcoreUtil.isAncestor(this, newResourceDemands))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResourceDemands != null)
				msgs = ((InternalEObject)newResourceDemands).eInverseAdd(this, OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS, ResourceDemands.class, msgs);
			msgs = basicSetResourceDemands(newResourceDemands, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS, newResourceDemands, newResourceDemands));
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameterInstance()).basicAdd(otherEnd, msgs);
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				return ((InternalEList<?>)getParameterInstance()).basicRemove(otherEnd, msgs);
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
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
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
				return eInternalContainer().eInverseRemove(this, OutputPackage.RESOURCE_DEMANDS__FUNCTION_CALLS, ResourceDemands.class, msgs);
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				return getParameterInstance();
			case OutputPackage.FUNCTION_CALL__FUNCTION:
				if (resolve) return getFunction();
				return basicGetFunction();
			case OutputPackage.FUNCTION_CALL__NUMBER_OBSERVATIONS:
				return getNumberObservations();
			case OutputPackage.FUNCTION_CALL__NATIVE:
				return isNative();
			case OutputPackage.FUNCTION_CALL__SYNCHRONIZED:
				return isSynchronized();
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
				return getResourceDemands();
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				getParameterInstance().clear();
				getParameterInstance().addAll((Collection<? extends ParameterInstance>)newValue);
				return;
			case OutputPackage.FUNCTION_CALL__FUNCTION:
				setFunction((Function)newValue);
				return;
			case OutputPackage.FUNCTION_CALL__NUMBER_OBSERVATIONS:
				setNumberObservations((Long)newValue);
				return;
			case OutputPackage.FUNCTION_CALL__NATIVE:
				setNative((Boolean)newValue);
				return;
			case OutputPackage.FUNCTION_CALL__SYNCHRONIZED:
				setSynchronized((Boolean)newValue);
				return;
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				getParameterInstance().clear();
				return;
			case OutputPackage.FUNCTION_CALL__FUNCTION:
				setFunction((Function)null);
				return;
			case OutputPackage.FUNCTION_CALL__NUMBER_OBSERVATIONS:
				setNumberObservations(NUMBER_OBSERVATIONS_EDEFAULT);
				return;
			case OutputPackage.FUNCTION_CALL__NATIVE:
				setNative(NATIVE_EDEFAULT);
				return;
			case OutputPackage.FUNCTION_CALL__SYNCHRONIZED:
				setSynchronized(SYNCHRONIZED_EDEFAULT);
				return;
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
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
			case OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE:
				return parameterInstance != null && !parameterInstance.isEmpty();
			case OutputPackage.FUNCTION_CALL__FUNCTION:
				return function != null;
			case OutputPackage.FUNCTION_CALL__NUMBER_OBSERVATIONS:
				return numberObservations != NUMBER_OBSERVATIONS_EDEFAULT;
			case OutputPackage.FUNCTION_CALL__NATIVE:
				return native_ != NATIVE_EDEFAULT;
			case OutputPackage.FUNCTION_CALL__SYNCHRONIZED:
				return synchronized_ != SYNCHRONIZED_EDEFAULT;
			case OutputPackage.FUNCTION_CALL__RESOURCE_DEMANDS:
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
		result.append(", native: ");
		result.append(native_);
		result.append(", synchronized: ");
		result.append(synchronized_);
		result.append(')');
		return result.toString();
	}

} //FunctionCallImpl
