/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.fzi.gast.variables.FormalParameter;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.FunctionCall;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;
import edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation;
import edu.kit.ipd.sdq.bycounter.output.ParameterInstance;

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
 * An implementation of the model object '<em><b>Parameter Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl#getFormalParameter <em>Formal Parameter</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl#getParameterCharacterisation <em>Parameter Characterisation</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl#getFunctionCall <em>Function Call</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterInstanceImpl extends IdentifierImpl implements ParameterInstance {
	/**
	 * The cached value of the '{@link #getFormalParameter() <em>Formal Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormalParameter()
	 * @generated
	 * @ordered
	 */
	protected FormalParameter formalParameter;

	/**
	 * The cached value of the '{@link #getParameterCharacterisation() <em>Parameter Characterisation</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterCharacterisation()
	 * @generated
	 * @ordered
	 */
	protected EList<ParameterCharacterisation> parameterCharacterisation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.PARAMETER_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameter getFormalParameter() {
		if (formalParameter != null && formalParameter.eIsProxy()) {
			InternalEObject oldFormalParameter = (InternalEObject)formalParameter;
			formalParameter = (FormalParameter)eResolveProxy(oldFormalParameter);
			if (formalParameter != oldFormalParameter) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER, oldFormalParameter, formalParameter));
			}
		}
		return formalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParameter basicGetFormalParameter() {
		return formalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormalParameter(FormalParameter newFormalParameter) {
		FormalParameter oldFormalParameter = formalParameter;
		formalParameter = newFormalParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER, oldFormalParameter, formalParameter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ParameterCharacterisation> getParameterCharacterisation() {
		if (parameterCharacterisation == null) {
			parameterCharacterisation = new EObjectContainmentWithInverseEList<ParameterCharacterisation>(ParameterCharacterisation.class, this, OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION, OutputPackage.PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE);
		}
		return parameterCharacterisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FunctionCall getFunctionCall() {
		if (eContainerFeatureID() != OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL) return null;
		return (FunctionCall)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFunctionCall(FunctionCall newFunctionCall, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newFunctionCall, OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFunctionCall(FunctionCall newFunctionCall) {
		if (newFunctionCall != eInternalContainer() || (eContainerFeatureID() != OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL && newFunctionCall != null)) {
			if (EcoreUtil.isAncestor(this, newFunctionCall))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newFunctionCall != null)
				msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE, FunctionCall.class, msgs);
			msgs = basicSetFunctionCall(newFunctionCall, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL, newFunctionCall, newFunctionCall));
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
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameterCharacterisation()).basicAdd(otherEnd, msgs);
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetFunctionCall((FunctionCall)otherEnd, msgs);
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
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				return ((InternalEList<?>)getParameterCharacterisation()).basicRemove(otherEnd, msgs);
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				return basicSetFunctionCall(null, msgs);
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
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				return eInternalContainer().eInverseRemove(this, OutputPackage.FUNCTION_CALL__PARAMETER_INSTANCE, FunctionCall.class, msgs);
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
			case OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER:
				if (resolve) return getFormalParameter();
				return basicGetFormalParameter();
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				return getParameterCharacterisation();
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				return getFunctionCall();
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
			case OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER:
				setFormalParameter((FormalParameter)newValue);
				return;
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				getParameterCharacterisation().clear();
				getParameterCharacterisation().addAll((Collection<? extends ParameterCharacterisation>)newValue);
				return;
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				setFunctionCall((FunctionCall)newValue);
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
			case OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER:
				setFormalParameter((FormalParameter)null);
				return;
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				getParameterCharacterisation().clear();
				return;
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				setFunctionCall((FunctionCall)null);
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
			case OutputPackage.PARAMETER_INSTANCE__FORMAL_PARAMETER:
				return formalParameter != null;
			case OutputPackage.PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION:
				return parameterCharacterisation != null && !parameterCharacterisation.isEmpty();
			case OutputPackage.PARAMETER_INSTANCE__FUNCTION_CALL:
				return getFunctionCall() != null;
		}
		return super.eIsSet(featureID);
	}

} //ParameterInstanceImpl
