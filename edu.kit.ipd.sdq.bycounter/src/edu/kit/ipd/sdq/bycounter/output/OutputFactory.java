/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage
 * @generated
 */
public interface OutputFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OutputFactory eINSTANCE = edu.kit.ipd.sdq.bycounter.output.impl.OutputFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Repository</em>'.
	 * @generated
	 */
	OutputModelRepository createOutputModelRepository();

	/**
	 * Returns a new object of class '<em>Measurement Run</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement Run</em>'.
	 * @generated
	 */
	MeasurementRun createMeasurementRun();

	/**
	 * Returns a new object of class '<em>Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Request</em>'.
	 * @generated
	 */
	Request createRequest();

	/**
	 * Returns a new object of class '<em>Observed Entity Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Observed Entity Execution</em>'.
	 * @generated
	 */
	ObservedEntityExecution createObservedEntityExecution();

	/**
	 * Returns a new object of class '<em>Resource Demands</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Demands</em>'.
	 * @generated
	 */
	ResourceDemands createResourceDemands();

	/**
	 * Returns a new object of class '<em>Function Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Function Call</em>'.
	 * @generated
	 */
	FunctionCall createFunctionCall();

	/**
	 * Returns a new object of class '<em>Parameter Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parameter Instance</em>'.
	 * @generated
	 */
	ParameterInstance createParameterInstance();

	/**
	 * Returns a new object of class '<em>Java VM Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Java VM Call</em>'.
	 * @generated
	 */
	JavaVMCall createJavaVMCall();

	/**
	 * Returns a new object of class '<em>Double Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Characterisation</em>'.
	 * @generated
	 */
	DoubleCharacterisation createDoubleCharacterisation();

	/**
	 * Returns a new object of class '<em>Long Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Long Characterisation</em>'.
	 * @generated
	 */
	LongCharacterisation createLongCharacterisation();

	/**
	 * Returns a new object of class '<em>Boolean Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Characterisation</em>'.
	 * @generated
	 */
	BooleanCharacterisation createBooleanCharacterisation();

	/**
	 * Returns a new object of class '<em>String Characterisation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Characterisation</em>'.
	 * @generated
	 */
	StringCharacterisation createStringCharacterisation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OutputPackage getOutputPackage();

} //OutputFactory
