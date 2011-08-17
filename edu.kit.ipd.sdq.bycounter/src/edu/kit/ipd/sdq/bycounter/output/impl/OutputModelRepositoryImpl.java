/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription;
import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;
import edu.kit.ipd.sdq.bycounter.output.OutputModelRepository;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl#getMeasurementRuns <em>Measurement Runs</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl#getEnvironmentCharacterisations <em>Environment Characterisations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutputModelRepositoryImpl extends IdentifierImpl implements OutputModelRepository {
	/**
	 * The cached value of the '{@link #getMeasurementRuns() <em>Measurement Runs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMeasurementRuns()
	 * @generated
	 * @ordered
	 */
	protected EList<MeasurementRun> measurementRuns;

	/**
	 * The cached value of the '{@link #getEnvironmentCharacterisations() <em>Environment Characterisations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironmentCharacterisations()
	 * @generated
	 * @ordered
	 */
	protected EList<EnvironmentDescription> environmentCharacterisations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutputModelRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OutputPackage.Literals.OUTPUT_MODEL_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MeasurementRun> getMeasurementRuns() {
		if (measurementRuns == null) {
			measurementRuns = new EObjectContainmentEList<MeasurementRun>(MeasurementRun.class, this, OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS);
		}
		return measurementRuns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnvironmentDescription> getEnvironmentCharacterisations() {
		if (environmentCharacterisations == null) {
			environmentCharacterisations = new EObjectContainmentEList<EnvironmentDescription>(EnvironmentDescription.class, this, OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS);
		}
		return environmentCharacterisations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS:
				return ((InternalEList<?>)getMeasurementRuns()).basicRemove(otherEnd, msgs);
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS:
				return ((InternalEList<?>)getEnvironmentCharacterisations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS:
				return getMeasurementRuns();
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS:
				return getEnvironmentCharacterisations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS:
				getMeasurementRuns().clear();
				getMeasurementRuns().addAll((Collection<? extends MeasurementRun>)newValue);
				return;
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS:
				getEnvironmentCharacterisations().clear();
				getEnvironmentCharacterisations().addAll((Collection<? extends EnvironmentDescription>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS:
				getMeasurementRuns().clear();
				return;
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS:
				getEnvironmentCharacterisations().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS:
				return measurementRuns != null && !measurementRuns.isEmpty();
			case OutputPackage.OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS:
				return environmentCharacterisations != null && !environmentCharacterisations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OutputModelRepositoryImpl
