/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage
 * @generated
 */
public interface InputFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InputFactory eINSTANCE = edu.kit.ipd.sdq.bycounter.input.impl.InputFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Instrumentation Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumentation Profile</em>'.
	 * @generated
	 */
	InstrumentationProfile createInstrumentationProfile();

	/**
	 * Returns a new object of class '<em>Logical Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Logical Set</em>'.
	 * @generated
	 */
	LogicalSet createLogicalSet();

	/**
	 * Returns a new object of class '<em>Instrumentation Profile Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumentation Profile Repository</em>'.
	 * @generated
	 */
	InstrumentationProfileRepository createInstrumentationProfileRepository();

	/**
	 * Returns a new object of class '<em>Execution Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Profile</em>'.
	 * @generated
	 */
	ExecutionProfile createExecutionProfile();

	/**
	 * Returns a new object of class '<em>Instrumented Code Area</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumented Code Area</em>'.
	 * @generated
	 */
	InstrumentedCodeArea createInstrumentedCodeArea();

	/**
	 * Returns a new object of class '<em>Instrumented Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumented Method</em>'.
	 * @generated
	 */
	InstrumentedMethod createInstrumentedMethod();

	/**
	 * Returns a new object of class '<em>Instrumented Region</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Instrumented Region</em>'.
	 * @generated
	 */
	InstrumentedRegion createInstrumentedRegion();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	InputPackage getInputPackage();

} //InputFactory
