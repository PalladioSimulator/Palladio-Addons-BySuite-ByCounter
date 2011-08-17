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
 * A representation of the model object '<em><b>Model Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getMeasurementRuns <em>Measurement Runs</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getEnvironmentCharacterisations <em>Environment Characterisations</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getOutputModelRepository()
 * @model
 * @generated
 */
public interface OutputModelRepository extends Identifier {
	/**
	 * Returns the value of the '<em><b>Measurement Runs</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measurement Runs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurement Runs</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getOutputModelRepository_MeasurementRuns()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<MeasurementRun> getMeasurementRuns();

	/**
	 * Returns the value of the '<em><b>Environment Characterisations</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment Characterisations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment Characterisations</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getOutputModelRepository_EnvironmentCharacterisations()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<EnvironmentDescription> getEnvironmentCharacterisations();

} // OutputModelRepository
