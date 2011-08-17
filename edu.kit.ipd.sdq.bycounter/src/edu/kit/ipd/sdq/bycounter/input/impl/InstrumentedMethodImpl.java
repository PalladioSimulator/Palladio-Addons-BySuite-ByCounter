/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.functions.Method;

import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;

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
 * An implementation of the model object '<em><b>Instrumented Method</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl#getImplementationOrDerived <em>Implementation Or Derived</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl#getDeclarationOrParent <em>Declaration Or Parent</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl#isInstrumentDerived <em>Instrument Derived</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedMethodImpl#getMethod <em>Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentedMethodImpl extends EntityToInstrumentImpl implements InstrumentedMethod {
	/**
	 * The cached value of the '{@link #getImplementationOrDerived() <em>Implementation Or Derived</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationOrDerived()
	 * @generated
	 * @ordered
	 */
	protected EList<InstrumentedMethod> implementationOrDerived;

	/**
	 * The default value of the '{@link #isInstrumentDerived() <em>Instrument Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentDerived()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTRUMENT_DERIVED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInstrumentDerived() <em>Instrument Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentDerived()
	 * @generated
	 * @ordered
	 */
	protected boolean instrumentDerived = INSTRUMENT_DERIVED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMethod() <em>Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethod()
	 * @generated
	 * @ordered
	 */
	protected Method method;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentedMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.INSTRUMENTED_METHOD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstrumentedMethod> getImplementationOrDerived() {
		if (implementationOrDerived == null) {
			implementationOrDerived = new EObjectContainmentWithInverseEList<InstrumentedMethod>(InstrumentedMethod.class, this, InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED, InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT);
		}
		return implementationOrDerived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentedMethod getDeclarationOrParent() {
		if (eContainerFeatureID() != InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT) return null;
		return (InstrumentedMethod)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDeclarationOrParent(InstrumentedMethod newDeclarationOrParent, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDeclarationOrParent, InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeclarationOrParent(InstrumentedMethod newDeclarationOrParent) {
		if (newDeclarationOrParent != eInternalContainer() || (eContainerFeatureID() != InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT && newDeclarationOrParent != null)) {
			if (EcoreUtil.isAncestor(this, newDeclarationOrParent))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDeclarationOrParent != null)
				msgs = ((InternalEObject)newDeclarationOrParent).eInverseAdd(this, InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED, InstrumentedMethod.class, msgs);
			msgs = basicSetDeclarationOrParent(newDeclarationOrParent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT, newDeclarationOrParent, newDeclarationOrParent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstrumentDerived() {
		return instrumentDerived;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentDerived(boolean newInstrumentDerived) {
		boolean oldInstrumentDerived = instrumentDerived;
		instrumentDerived = newInstrumentDerived;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_METHOD__INSTRUMENT_DERIVED, oldInstrumentDerived, instrumentDerived));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getMethod() {
		if (method != null && method.eIsProxy()) {
			InternalEObject oldMethod = (InternalEObject)method;
			method = (Method)eResolveProxy(oldMethod);
			if (method != oldMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InputPackage.INSTRUMENTED_METHOD__METHOD, oldMethod, method));
			}
		}
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetMethod() {
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMethod(Method newMethod) {
		Method oldMethod = method;
		method = newMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_METHOD__METHOD, oldMethod, method));
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getImplementationOrDerived()).basicAdd(otherEnd, msgs);
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDeclarationOrParent((InstrumentedMethod)otherEnd, msgs);
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				return ((InternalEList<?>)getImplementationOrDerived()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				return basicSetDeclarationOrParent(null, msgs);
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
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				return eInternalContainer().eInverseRemove(this, InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED, InstrumentedMethod.class, msgs);
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				return getImplementationOrDerived();
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				return getDeclarationOrParent();
			case InputPackage.INSTRUMENTED_METHOD__INSTRUMENT_DERIVED:
				return isInstrumentDerived();
			case InputPackage.INSTRUMENTED_METHOD__METHOD:
				if (resolve) return getMethod();
				return basicGetMethod();
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				getImplementationOrDerived().clear();
				getImplementationOrDerived().addAll((Collection<? extends InstrumentedMethod>)newValue);
				return;
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				setDeclarationOrParent((InstrumentedMethod)newValue);
				return;
			case InputPackage.INSTRUMENTED_METHOD__INSTRUMENT_DERIVED:
				setInstrumentDerived((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTED_METHOD__METHOD:
				setMethod((Method)newValue);
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				getImplementationOrDerived().clear();
				return;
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				setDeclarationOrParent((InstrumentedMethod)null);
				return;
			case InputPackage.INSTRUMENTED_METHOD__INSTRUMENT_DERIVED:
				setInstrumentDerived(INSTRUMENT_DERIVED_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTED_METHOD__METHOD:
				setMethod((Method)null);
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
			case InputPackage.INSTRUMENTED_METHOD__IMPLEMENTATION_OR_DERIVED:
				return implementationOrDerived != null && !implementationOrDerived.isEmpty();
			case InputPackage.INSTRUMENTED_METHOD__DECLARATION_OR_PARENT:
				return getDeclarationOrParent() != null;
			case InputPackage.INSTRUMENTED_METHOD__INSTRUMENT_DERIVED:
				return instrumentDerived != INSTRUMENT_DERIVED_EDEFAULT;
			case InputPackage.INSTRUMENTED_METHOD__METHOD:
				return method != null;
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
		result.append(" (instrumentDerived: ");
		result.append(instrumentDerived);
		result.append(')');
		return result.toString();
	}

} //InstrumentedMethodImpl
