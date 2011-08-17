/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.Identifier;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Observed Entity Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 *     Execution sequence of code areas which was observed in the measurement run.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands <em>Resource Demands</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedForkedExecutionSequence <em>Observed Forked Execution Sequence</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy <em>Observed Execution Sequence Forked By</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getEntity <em>Entity</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest <em>Request</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution()
 * @model
 * @generated
 */
public interface ObservedEntityExecution extends Identifier {
	/**
	 * Returns the value of the '<em><b>Resource Demands</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution <em>Observed Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Demands</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Demands</em>' containment reference.
	 * @see #setResourceDemands(ResourceDemands)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution_ResourceDemands()
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution
	 * @model opposite="observedExecution" containment="true" required="true"
	 * @generated
	 */
	ResourceDemands getResourceDemands();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands <em>Resource Demands</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Demands</em>' containment reference.
	 * @see #getResourceDemands()
	 * @generated
	 */
	void setResourceDemands(ResourceDemands value);

	/**
	 * Returns the value of the '<em><b>Observed Forked Execution Sequence</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy <em>Observed Execution Sequence Forked By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 *     Execution sequence of a <em>single</em> forked thread. Only one thread can be forked within a ObservedCodeAreaSequence.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Observed Forked Execution Sequence</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution_ObservedForkedExecutionSequence()
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy
	 * @model opposite="observedExecutionSequenceForkedBy" containment="true"
	 * @generated
	 */
	EList<ObservedEntityExecution> getObservedForkedExecutionSequence();

	/**
	 * Returns the value of the '<em><b>Observed Execution Sequence Forked By</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedForkedExecutionSequence <em>Observed Forked Execution Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Observed Execution Sequence Forked By</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Observed Execution Sequence Forked By</em>' container reference.
	 * @see #setObservedExecutionSequenceForkedBy(ObservedEntityExecution)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution_ObservedExecutionSequenceForkedBy()
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedForkedExecutionSequence
	 * @model opposite="observedForkedExecutionSequence" transient="false" ordered="false"
	 * @generated
	 */
	ObservedEntityExecution getObservedExecutionSequenceForkedBy();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy <em>Observed Execution Sequence Forked By</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Observed Execution Sequence Forked By</em>' container reference.
	 * @see #getObservedExecutionSequenceForkedBy()
	 * @generated
	 */
	void setObservedExecutionSequenceForkedBy(ObservedEntityExecution value);

	/**
	 * Returns the value of the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity</em>' reference.
	 * @see #setEntity(EntityToInstrument)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution_Entity()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EntityToInstrument getEntity();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getEntity <em>Entity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(EntityToInstrument value);

	/**
	 * Returns the value of the '<em><b>Request</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.Request#getObservedExecutionSequence <em>Observed Execution Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Request</em>' container reference.
	 * @see #setRequest(Request)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getObservedEntityExecution_Request()
	 * @see edu.kit.ipd.sdq.bycounter.output.Request#getObservedExecutionSequence
	 * @model opposite="observedExecutionSequence" transient="false" ordered="false"
	 * @generated
	 */
	Request getRequest();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest <em>Request</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Request</em>' container reference.
	 * @see #getRequest()
	 * @generated
	 */
	void setRequest(Request value);

} // ObservedEntityExecution
