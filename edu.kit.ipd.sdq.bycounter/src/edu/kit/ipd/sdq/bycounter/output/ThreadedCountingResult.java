/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Threaded Counting Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadId <em>Thread Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getThreadedCountingResult()
 * @model
 * @generated
 */
public interface ThreadedCountingResult extends CountingResult {
	/**
	 * Returns the value of the '<em><b>Spawned Threaded Counting Results</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spawned Threaded Counting Results</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spawned Threaded Counting Results</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getThreadedCountingResult_SpawnedThreadedCountingResults()
	 * @see edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadedCountingResult
	 * @model opposite="threadedCountingResult" containment="true" ordered="false"
	 * @generated
	 */
	EList<ThreadedCountingResult> getSpawnedThreadedCountingResults();

	/**
	 * Returns the value of the '<em><b>Threaded Counting Result</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getSpawnedThreadedCountingResults <em>Spawned Threaded Counting Results</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Threaded Counting Result</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Threaded Counting Result</em>' container reference.
	 * @see #setThreadedCountingResult(ThreadedCountingResult)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getThreadedCountingResult_ThreadedCountingResult()
	 * @see edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getSpawnedThreadedCountingResults
	 * @model opposite="spawnedThreadedCountingResults" transient="false" ordered="false"
	 * @generated
	 */
	ThreadedCountingResult getThreadedCountingResult();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadedCountingResult <em>Threaded Counting Result</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threaded Counting Result</em>' container reference.
	 * @see #getThreadedCountingResult()
	 * @generated
	 */
	void setThreadedCountingResult(ThreadedCountingResult value);

	/**
	 * Returns the value of the '<em><b>Thread Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thread Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thread Id</em>' attribute.
	 * @see #setThreadId(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getThreadedCountingResult_ThreadId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getThreadId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ThreadedCountingResult#getThreadId <em>Thread Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thread Id</em>' attribute.
	 * @see #getThreadId()
	 * @generated
	 */
	void setThreadId(long value);

} // ThreadedCountingResult
