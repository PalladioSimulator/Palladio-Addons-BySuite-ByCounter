/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.statements.Statement;

import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumented Code Area</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl#getFrom <em>From</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedCodeAreaImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentedCodeAreaImpl extends EntityToInstrumentImpl implements InstrumentedCodeArea {
	/**
	 * The cached value of the '{@link #getFrom() <em>From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrom()
	 * @generated
	 * @ordered
	 */
	protected Statement from;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected Statement to;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentedCodeAreaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.INSTRUMENTED_CODE_AREA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getFrom() {
		if (from != null && from.eIsProxy()) {
			InternalEObject oldFrom = (InternalEObject)from;
			from = (Statement)eResolveProxy(oldFrom);
			if (from != oldFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InputPackage.INSTRUMENTED_CODE_AREA__FROM, oldFrom, from));
			}
		}
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement basicGetFrom() {
		return from;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrom(Statement newFrom) {
		Statement oldFrom = from;
		from = newFrom;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_CODE_AREA__FROM, oldFrom, from));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement getTo() {
		if (to != null && to.eIsProxy()) {
			InternalEObject oldTo = (InternalEObject)to;
			to = (Statement)eResolveProxy(oldTo);
			if (to != oldTo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InputPackage.INSTRUMENTED_CODE_AREA__TO, oldTo, to));
			}
		}
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Statement basicGetTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(Statement newTo) {
		Statement oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_CODE_AREA__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InputPackage.INSTRUMENTED_CODE_AREA__FROM:
				if (resolve) return getFrom();
				return basicGetFrom();
			case InputPackage.INSTRUMENTED_CODE_AREA__TO:
				if (resolve) return getTo();
				return basicGetTo();
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
			case InputPackage.INSTRUMENTED_CODE_AREA__FROM:
				setFrom((Statement)newValue);
				return;
			case InputPackage.INSTRUMENTED_CODE_AREA__TO:
				setTo((Statement)newValue);
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
			case InputPackage.INSTRUMENTED_CODE_AREA__FROM:
				setFrom((Statement)null);
				return;
			case InputPackage.INSTRUMENTED_CODE_AREA__TO:
				setTo((Statement)null);
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
			case InputPackage.INSTRUMENTED_CODE_AREA__FROM:
				return from != null;
			case InputPackage.INSTRUMENTED_CODE_AREA__TO:
				return to != null;
		}
		return super.eIsSet(featureID);
	}

} //InstrumentedCodeAreaImpl
