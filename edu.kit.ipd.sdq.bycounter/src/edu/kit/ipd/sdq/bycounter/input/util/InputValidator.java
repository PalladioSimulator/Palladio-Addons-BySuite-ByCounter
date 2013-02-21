/**
 */
package edu.kit.ipd.sdq.bycounter.input.util;

import de.uka.ipd.sdq.identifier.util.IdentifierValidator;

import edu.kit.ipd.sdq.bycounter.input.*;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage
 * @generated
 */
public class InputValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final InputValidator INSTANCE = new InputValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "edu.kit.ipd.sdq.bycounter.input";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'At Least One Thread' of 'Instrumentation Profile'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INSTRUMENTATION_PROFILE__AT_LEAST_ONE_THREAD = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdentifierValidator identifierValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputValidator() {
		super();
		identifierValidator = IdentifierValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return InputPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case InputPackage.INSTRUMENTATION_PROFILE:
				return validateInstrumentationProfile((InstrumentationProfile)value, diagnostics, context);
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY:
				return validateInstrumentationProfileRepository((InstrumentationProfileRepository)value, diagnostics, context);
			case InputPackage.EXECUTION_PROFILE:
				return validateExecutionProfile((ExecutionProfile)value, diagnostics, context);
			case InputPackage.LOGICAL_SET:
				return validateLogicalSet((LogicalSet)value, diagnostics, context);
			case InputPackage.INSTRUMENTED_CODE_AREA:
				return validateInstrumentedCodeArea((InstrumentedCodeArea)value, diagnostics, context);
			case InputPackage.INSTRUMENTED_METHOD:
				return validateInstrumentedMethod((InstrumentedMethod)value, diagnostics, context);
			case InputPackage.ENTITY_TO_INSTRUMENT:
				return validateEntityToInstrument((EntityToInstrument)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstrumentationProfile(InstrumentationProfile instrumentationProfile, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(instrumentationProfile, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= identifierValidator.validateIdentifier_idHasToBeUnique(instrumentationProfile, diagnostics, context);
		if (result || diagnostics != null) result &= validateInstrumentationProfile_atLeastOneThread(instrumentationProfile, diagnostics, context);
		return result;
	}

	/**
	 * Validates the atLeastOneThread constraint of '<em>Instrumentation Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstrumentationProfile_atLeastOneThread(InstrumentationProfile instrumentationProfile, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return instrumentationProfile.atLeastOneThread(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstrumentationProfileRepository(InstrumentationProfileRepository instrumentationProfileRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(instrumentationProfileRepository, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(instrumentationProfileRepository, diagnostics, context);
		if (result || diagnostics != null) result &= identifierValidator.validateIdentifier_idHasToBeUnique(instrumentationProfileRepository, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExecutionProfile(ExecutionProfile executionProfile, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(executionProfile, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLogicalSet(LogicalSet logicalSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(logicalSet, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(logicalSet, diagnostics, context);
		if (result || diagnostics != null) result &= identifierValidator.validateIdentifier_idHasToBeUnique(logicalSet, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstrumentedCodeArea(InstrumentedCodeArea instrumentedCodeArea, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(instrumentedCodeArea, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInstrumentedMethod(InstrumentedMethod instrumentedMethod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(instrumentedMethod, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEntityToInstrument(EntityToInstrument entityToInstrument, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(entityToInstrument, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //InputValidator
