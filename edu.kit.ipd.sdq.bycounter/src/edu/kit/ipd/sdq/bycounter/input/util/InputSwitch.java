/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.util;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.bycounter.input.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage
 * @generated
 */
public class InputSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static InputPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputSwitch() {
		if (modelPackage == null) {
			modelPackage = InputPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case InputPackage.INSTRUMENTATION_PROFILE: {
				InstrumentationProfile instrumentationProfile = (InstrumentationProfile)theEObject;
				T result = caseInstrumentationProfile(instrumentationProfile);
				if (result == null) result = caseIdentifier(instrumentationProfile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.LOGICAL_SET: {
				LogicalSet logicalSet = (LogicalSet)theEObject;
				T result = caseLogicalSet(logicalSet);
				if (result == null) result = caseIdentifier(logicalSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY: {
				InstrumentationProfileRepository instrumentationProfileRepository = (InstrumentationProfileRepository)theEObject;
				T result = caseInstrumentationProfileRepository(instrumentationProfileRepository);
				if (result == null) result = caseIdentifier(instrumentationProfileRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.ENTITY_TO_INSTRUMENT: {
				EntityToInstrument entityToInstrument = (EntityToInstrument)theEObject;
				T result = caseEntityToInstrument(entityToInstrument);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.INSTRUMENTED_CODE_AREA: {
				InstrumentedCodeArea instrumentedCodeArea = (InstrumentedCodeArea)theEObject;
				T result = caseInstrumentedCodeArea(instrumentedCodeArea);
				if (result == null) result = caseEntityToInstrument(instrumentedCodeArea);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.INSTRUMENTED_METHOD: {
				InstrumentedMethod instrumentedMethod = (InstrumentedMethod)theEObject;
				T result = caseInstrumentedMethod(instrumentedMethod);
				if (result == null) result = caseEntityToInstrument(instrumentedMethod);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case InputPackage.INSTRUMENTED_REGION: {
				InstrumentedRegion instrumentedRegion = (InstrumentedRegion)theEObject;
				T result = caseInstrumentedRegion(instrumentedRegion);
				if (result == null) result = caseEntityToInstrument(instrumentedRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumentation Profile</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumentation Profile</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentationProfile(InstrumentationProfile object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logical Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logical Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogicalSet(LogicalSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumentation Profile Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumentation Profile Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentationProfileRepository(InstrumentationProfileRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity To Instrument</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity To Instrument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntityToInstrument(EntityToInstrument object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumented Code Area</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumented Code Area</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentedCodeArea(InstrumentedCodeArea object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumented Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumented Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentedMethod(InstrumentedMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instrumented Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instrumented Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstrumentedRegion(InstrumentedRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifier(Identifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //InputSwitch
