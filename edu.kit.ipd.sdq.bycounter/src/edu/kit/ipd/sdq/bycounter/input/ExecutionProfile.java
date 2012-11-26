/**
 */
package edu.kit.ipd.sdq.bycounter.input;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isAddUpResultsRecursively <em>Add Up Results Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getDefinedLogicalSets <em>Defined Logical Sets</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getLogicalSetExternalToClassLoader <em>Logical Set External To Class Loader</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getInstrumentationProfileRepository <em>Instrumentation Profile Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile()
 * @model
 * @generated
 */
public interface ExecutionProfile extends EObject {
	/**
	 * Returns the value of the '<em><b>Add Up Results Recursively</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True, when result retrieval adds up counting results recursively.
	 * This means that results for methods that call other methods include the counts of these called methods, i.e. of the entire calling tree.
	 * When false, only the counts for operations done in the method itself - not those by called methods - are returned.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Add Up Results Recursively</em>' attribute.
	 * @see #setAddUpResultsRecursively(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_AddUpResultsRecursively()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isAddUpResultsRecursively();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isAddUpResultsRecursively <em>Add Up Results Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Add Up Results Recursively</em>' attribute.
	 * @see #isAddUpResultsRecursively()
	 * @generated
	 */
	void setAddUpResultsRecursively(boolean value);

	/**
	 * Returns the value of the '<em><b>Wait For Threads To Finnish</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, execution tries to wait for all threads from instrumented methods to finish before returning.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wait For Threads To Finnish</em>' attribute.
	 * @see #setWaitForThreadsToFinnish(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_WaitForThreadsToFinnish()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isWaitForThreadsToFinnish();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait For Threads To Finnish</em>' attribute.
	 * @see #isWaitForThreadsToFinnish()
	 * @generated
	 */
	void setWaitForThreadsToFinnish(boolean value);

	/**
	 * Returns the value of the '<em><b>Defined Logical Sets</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.LogicalSet}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getExecutionProfile <em>Execution Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Logical Sets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Logical Sets</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_DefinedLogicalSets()
	 * @see edu.kit.ipd.sdq.bycounter.input.LogicalSet#getExecutionProfile
	 * @model opposite="executionProfile" containment="true" ordered="false"
	 * @generated
	 */
	EList<LogicalSet> getDefinedLogicalSets();

	/**
	 * Returns the value of the '<em><b>Logical Set External To Class Loader</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.LogicalSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Set External To Class Loader</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Set External To Class Loader</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_LogicalSetExternalToClassLoader()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<LogicalSet> getLogicalSetExternalToClassLoader();

	/**
	 * Returns the value of the '<em><b>Instrumentation Profile Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getExecutionProfile <em>Execution Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrumentation Profile Repository</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instrumentation Profile Repository</em>' container reference.
	 * @see #setInstrumentationProfileRepository(InstrumentationProfileRepository)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_InstrumentationProfileRepository()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getExecutionProfile
	 * @model opposite="executionProfile" transient="false" ordered="false"
	 * @generated
	 */
	InstrumentationProfileRepository getInstrumentationProfileRepository();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getInstrumentationProfileRepository <em>Instrumentation Profile Repository</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrumentation Profile Repository</em>' container reference.
	 * @see #getInstrumentationProfileRepository()
	 * @generated
	 */
	void setInstrumentationProfileRepository(InstrumentationProfileRepository value);

} // ExecutionProfile
