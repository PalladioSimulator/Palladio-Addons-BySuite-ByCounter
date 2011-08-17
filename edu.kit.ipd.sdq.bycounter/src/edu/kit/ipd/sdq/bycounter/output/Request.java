/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Request</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.Request#getObservedExecutionSequence <em>Observed Execution Sequence</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun <em>Measurement Run</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequest()
 * @model
 * @generated
 */
public interface Request extends Identifier {
	/**
	 * Returns the value of the '<em><b>Observed Execution Sequence</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest <em>Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ordered set or sequence of
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Observed Execution Sequence</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequest_ObservedExecutionSequence()
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest
	 * @model opposite="request" containment="true"
	 * @generated
	 */
	EList<ObservedEntityExecution> getObservedExecutionSequence();

	/**
	 * Returns the value of the '<em><b>Measurement Run</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getRequests <em>Requests</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measurement Run</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurement Run</em>' container reference.
	 * @see #setMeasurementRun(MeasurementRun)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getRequest_MeasurementRun()
	 * @see edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getRequests
	 * @model opposite="requests" required="true" transient="false" ordered="false"
	 * @generated
	 */
	MeasurementRun getMeasurementRun();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun <em>Measurement Run</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Measurement Run</em>' container reference.
	 * @see #getMeasurementRun()
	 * @generated
	 */
	void setMeasurementRun(MeasurementRun value);

} // Request
