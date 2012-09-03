/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#getInternalClassesDefinition <em>Internal Classes Definition</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.ExecutionProfile#isWaitForThreadsToFinnish <em>Wait For Threads To Finnish</em>}</li>
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
	 * @model required="true" ordered="false"
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
	 * Returns the value of the '<em><b>Internal Classes Definition</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The definition of internal classes. When adding up results when retrieving results recursively, this definition allows for adding up only results for classes defined as internal. A value of null means all classes are considered internal.
	 * For each string, specifying a '*' at the end enables prefix matching, i.e. all classes with the prefix are matched. If a string specifies a class name, non-public/internal classes are also considered internal.
	 * 
	 * Examples:
	 * de.uka* matches de.ukap.Test, de.uka.ipd, ...
	 * de.uka.Test matches de.uka.Test, de.uka.Test$XXX, de.uka.Test$XXX$YYY, ...
	 * de.uka.Test matches de.uka.Test, but not de.uka.Test.{ENUM Y}
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Internal Classes Definition</em>' attribute list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_InternalClassesDefinition()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getInternalClassesDefinition();

	/**
	 * Returns the value of the '<em><b>Wait For Threads To Finnish</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, execution tries to wait for all threads from instrumented methods to finish before returning.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Wait For Threads To Finnish</em>' attribute.
	 * @see #setWaitForThreadsToFinnish(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getExecutionProfile_WaitForThreadsToFinnish()
	 * @model required="true" ordered="false"
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

} // ExecutionProfile
