/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.util;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.bycounter.output.*;

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
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage
 * @generated
 */
public class OutputSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static OutputPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputSwitch() {
		if (modelPackage == null) {
			modelPackage = OutputPackage.eINSTANCE;
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
			case OutputPackage.OUTPUT_MODEL_REPOSITORY: {
				OutputModelRepository outputModelRepository = (OutputModelRepository)theEObject;
				T result = caseOutputModelRepository(outputModelRepository);
				if (result == null) result = caseIdentifier(outputModelRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.RESULT_COLLECTION: {
				ResultCollection resultCollection = (ResultCollection)theEObject;
				T result = caseResultCollection(resultCollection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.REQUEST_RESULT: {
				RequestResult requestResult = (RequestResult)theEObject;
				T result = caseRequestResult(requestResult);
				if (result == null) result = caseIdentifier(requestResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.COUNTING_RESULT: {
				CountingResult countingResult = (CountingResult)theEObject;
				T result = caseCountingResult(countingResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.UUID: {
				UUID uuid = (UUID)theEObject;
				T result = caseUUID(uuid);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.ARRAY_CREATION_COUNT: {
				ArrayCreationCount arrayCreationCount = (ArrayCreationCount)theEObject;
				T result = caseArrayCreationCount(arrayCreationCount);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.METHOD_CALL_COUNT: {
				MethodCallCount methodCallCount = (MethodCallCount)theEObject;
				T result = caseMethodCallCount(methodCallCount);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.THREADED_COUNTING_RESULT: {
				ThreadedCountingResult threadedCountingResult = (ThreadedCountingResult)theEObject;
				T result = caseThreadedCountingResult(threadedCountingResult);
				if (result == null) result = caseCountingResult(threadedCountingResult);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutputModelRepository(OutputModelRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Result Collection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Result Collection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResultCollection(ResultCollection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Request Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Request Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequestResult(RequestResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Counting Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Counting Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCountingResult(CountingResult object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>UUID</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UUID</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUUID(UUID object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Array Creation Count</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Creation Count</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayCreationCount(ArrayCreationCount object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method Call Count</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method Call Count</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethodCallCount(MethodCallCount object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Threaded Counting Result</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Threaded Counting Result</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseThreadedCountingResult(ThreadedCountingResult object) {
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

} //OutputSwitch
