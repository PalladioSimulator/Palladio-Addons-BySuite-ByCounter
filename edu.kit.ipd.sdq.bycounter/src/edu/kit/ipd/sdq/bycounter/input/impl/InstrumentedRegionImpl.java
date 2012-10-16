/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.functions.Method;

import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumented Region</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl#getStartLine <em>Start Line</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl#getStopLine <em>Stop Line</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl#getStartMethod <em>Start Method</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentedRegionImpl#getStopMethod <em>Stop Method</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentedRegionImpl extends EntityToInstrumentImpl implements InstrumentedRegion {
	/**
	 * The default value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected static final int START_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStartLine() <em>Start Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartLine()
	 * @generated
	 * @ordered
	 */
	protected int startLine = START_LINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStopLine() <em>Stop Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStopLine()
	 * @generated
	 * @ordered
	 */
	protected static final int STOP_LINE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStopLine() <em>Stop Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStopLine()
	 * @generated
	 * @ordered
	 */
	protected int stopLine = STOP_LINE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStartMethod() <em>Start Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartMethod()
	 * @generated
	 * @ordered
	 */
	protected Method startMethod;

	/**
	 * The cached value of the '{@link #getStopMethod() <em>Stop Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStopMethod()
	 * @generated
	 * @ordered
	 */
	protected Method stopMethod;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentedRegionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.INSTRUMENTED_REGION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStartLine() {
		return startLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartLine(int newStartLine) {
		int oldStartLine = startLine;
		startLine = newStartLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_REGION__START_LINE, oldStartLine, startLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStopLine() {
		return stopLine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStopLine(int newStopLine) {
		int oldStopLine = stopLine;
		stopLine = newStopLine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_REGION__STOP_LINE, oldStopLine, stopLine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getStartMethod() {
		if (startMethod != null && startMethod.eIsProxy()) {
			InternalEObject oldStartMethod = (InternalEObject)startMethod;
			startMethod = (Method)eResolveProxy(oldStartMethod);
			if (startMethod != oldStartMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InputPackage.INSTRUMENTED_REGION__START_METHOD, oldStartMethod, startMethod));
			}
		}
		return startMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetStartMethod() {
		return startMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartMethod(Method newStartMethod) {
		Method oldStartMethod = startMethod;
		startMethod = newStartMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_REGION__START_METHOD, oldStartMethod, startMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method getStopMethod() {
		if (stopMethod != null && stopMethod.eIsProxy()) {
			InternalEObject oldStopMethod = (InternalEObject)stopMethod;
			stopMethod = (Method)eResolveProxy(oldStopMethod);
			if (stopMethod != oldStopMethod) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, InputPackage.INSTRUMENTED_REGION__STOP_METHOD, oldStopMethod, stopMethod));
			}
		}
		return stopMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method basicGetStopMethod() {
		return stopMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStopMethod(Method newStopMethod) {
		Method oldStopMethod = stopMethod;
		stopMethod = newStopMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTED_REGION__STOP_METHOD, oldStopMethod, stopMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InputPackage.INSTRUMENTED_REGION__START_LINE:
				return getStartLine();
			case InputPackage.INSTRUMENTED_REGION__STOP_LINE:
				return getStopLine();
			case InputPackage.INSTRUMENTED_REGION__START_METHOD:
				if (resolve) return getStartMethod();
				return basicGetStartMethod();
			case InputPackage.INSTRUMENTED_REGION__STOP_METHOD:
				if (resolve) return getStopMethod();
				return basicGetStopMethod();
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
			case InputPackage.INSTRUMENTED_REGION__START_LINE:
				setStartLine((Integer)newValue);
				return;
			case InputPackage.INSTRUMENTED_REGION__STOP_LINE:
				setStopLine((Integer)newValue);
				return;
			case InputPackage.INSTRUMENTED_REGION__START_METHOD:
				setStartMethod((Method)newValue);
				return;
			case InputPackage.INSTRUMENTED_REGION__STOP_METHOD:
				setStopMethod((Method)newValue);
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
			case InputPackage.INSTRUMENTED_REGION__START_LINE:
				setStartLine(START_LINE_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTED_REGION__STOP_LINE:
				setStopLine(STOP_LINE_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTED_REGION__START_METHOD:
				setStartMethod((Method)null);
				return;
			case InputPackage.INSTRUMENTED_REGION__STOP_METHOD:
				setStopMethod((Method)null);
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
			case InputPackage.INSTRUMENTED_REGION__START_LINE:
				return startLine != START_LINE_EDEFAULT;
			case InputPackage.INSTRUMENTED_REGION__STOP_LINE:
				return stopLine != STOP_LINE_EDEFAULT;
			case InputPackage.INSTRUMENTED_REGION__START_METHOD:
				return startMethod != null;
			case InputPackage.INSTRUMENTED_REGION__STOP_METHOD:
				return stopMethod != null;
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
		result.append(" (startLine: ");
		result.append(startLine);
		result.append(", stopLine: ");
		result.append(stopLine);
		result.append(')');
		return result.toString();
	}

} //InstrumentedRegionImpl
