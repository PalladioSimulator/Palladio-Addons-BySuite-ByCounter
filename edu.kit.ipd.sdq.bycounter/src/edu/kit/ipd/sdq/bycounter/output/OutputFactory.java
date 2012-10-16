/**
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
	 * Returns a new object of class '<em>Result Collection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Result Collection</em>'.
	 * @generated
	 */
	ResultCollection createResultCollection();

	/**
	 * Returns a new object of class '<em>Request Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Request Result</em>'.
	 * @generated
	 */
	RequestResult createRequestResult();

	/**
	 * Returns a new object of class '<em>Counting Result</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Counting Result</em>'.
	 * @generated
	 */
	CountingResult createCountingResult();

	/**
	 * Returns a new object of class '<em>UUID</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UUID</em>'.
	 * @generated
	 */
	UUID createUUID();

	/**
	 * Returns a new object of class '<em>Array Creation Count</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Creation Count</em>'.
	 * @generated
	 */
	ArrayCreationCount createArrayCreationCount();

	/**
	 * Returns a new object of class '<em>Method Call Count</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method Call Count</em>'.
	 * @generated
	 */
	MethodCallCount createMethodCallCount();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	OutputPackage getOutputPackage();

} //OutputFactory
