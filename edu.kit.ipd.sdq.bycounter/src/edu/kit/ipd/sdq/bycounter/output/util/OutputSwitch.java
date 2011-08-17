/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.util;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.bycounter.output.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
public class OutputSwitch<T> {
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
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY: {
				OutputModelRepository outputModelRepository = (OutputModelRepository)theEObject;
				T result = caseOutputModelRepository(outputModelRepository);
				if (result == null) result = caseIdentifier(outputModelRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.MEASUREMENT_RUN: {
				MeasurementRun measurementRun = (MeasurementRun)theEObject;
				T result = caseMeasurementRun(measurementRun);
				if (result == null) result = caseIdentifier(measurementRun);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.ENVIRONMENT_DESCRIPTION: {
				EnvironmentDescription environmentDescription = (EnvironmentDescription)theEObject;
				T result = caseEnvironmentDescription(environmentDescription);
				if (result == null) result = caseIdentifier(environmentDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.REQUEST: {
				Request request = (Request)theEObject;
				T result = caseRequest(request);
				if (result == null) result = caseIdentifier(request);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.OBSERVED_ENTITY_EXECUTION: {
				ObservedEntityExecution observedEntityExecution = (ObservedEntityExecution)theEObject;
				T result = caseObservedEntityExecution(observedEntityExecution);
				if (result == null) result = caseIdentifier(observedEntityExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.RESOURCE_DEMANDS: {
				ResourceDemands resourceDemands = (ResourceDemands)theEObject;
				T result = caseResourceDemands(resourceDemands);
				if (result == null) result = caseIdentifier(resourceDemands);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.FUNCTION_CALL: {
				FunctionCall functionCall = (FunctionCall)theEObject;
				T result = caseFunctionCall(functionCall);
				if (result == null) result = caseIdentifier(functionCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.PARAMETER_INSTANCE: {
				ParameterInstance parameterInstance = (ParameterInstance)theEObject;
				T result = caseParameterInstance(parameterInstance);
				if (result == null) result = caseIdentifier(parameterInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.PARAMETER_CHARACTERISATION: {
				ParameterCharacterisation parameterCharacterisation = (ParameterCharacterisation)theEObject;
				T result = caseParameterCharacterisation(parameterCharacterisation);
				if (result == null) result = caseIdentifier(parameterCharacterisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.JAVA_VM_CALL: {
				JavaVMCall javaVMCall = (JavaVMCall)theEObject;
				T result = caseJavaVMCall(javaVMCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.DOUBLE_CHARACTERISATION: {
				DoubleCharacterisation doubleCharacterisation = (DoubleCharacterisation)theEObject;
				T result = caseDoubleCharacterisation(doubleCharacterisation);
				if (result == null) result = caseParameterCharacterisation(doubleCharacterisation);
				if (result == null) result = caseIdentifier(doubleCharacterisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.LONG_CHARACTERISATION: {
				LongCharacterisation longCharacterisation = (LongCharacterisation)theEObject;
				T result = caseLongCharacterisation(longCharacterisation);
				if (result == null) result = caseParameterCharacterisation(longCharacterisation);
				if (result == null) result = caseIdentifier(longCharacterisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.BOOLEAN_CHARACTERISATION: {
				BooleanCharacterisation booleanCharacterisation = (BooleanCharacterisation)theEObject;
				T result = caseBooleanCharacterisation(booleanCharacterisation);
				if (result == null) result = caseParameterCharacterisation(booleanCharacterisation);
				if (result == null) result = caseIdentifier(booleanCharacterisation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case OutputPackage.STRING_CHARACTERISATION: {
				StringCharacterisation stringCharacterisation = (StringCharacterisation)theEObject;
				T result = caseStringCharacterisation(stringCharacterisation);
				if (result == null) result = caseParameterCharacterisation(stringCharacterisation);
				if (result == null) result = caseIdentifier(stringCharacterisation);
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
	 * Returns the result of interpreting the object as an instance of '<em>Measurement Run</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Measurement Run</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMeasurementRun(MeasurementRun object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Environment Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Environment Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnvironmentDescription(EnvironmentDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Request</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Request</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRequest(Request object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Observed Entity Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Observed Entity Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseObservedEntityExecution(ObservedEntityExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Demands</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Demands</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceDemands(ResourceDemands object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunctionCall(FunctionCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterInstance(ParameterInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter Characterisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterCharacterisation(ParameterCharacterisation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Java VM Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Java VM Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseJavaVMCall(JavaVMCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Characterisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleCharacterisation(DoubleCharacterisation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Characterisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongCharacterisation(LongCharacterisation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Characterisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanCharacterisation(BooleanCharacterisation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Characterisation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringCharacterisation(StringCharacterisation object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //OutputSwitch
