/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import edu.kit.ipd.sdq.bycounter.input.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class InputFactoryImpl extends EFactoryImpl implements InputFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static InputFactory init() {
		try {
			InputFactory theInputFactory = (InputFactory)EPackage.Registry.INSTANCE.getEFactory("http://sdq.ipd.kit.edu/ByCounterModels/Input/0.3"); 
			if (theInputFactory != null) {
				return theInputFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new InputFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case InputPackage.INSTRUMENTATION_PROFILE: return createInstrumentationProfile();
			case InputPackage.LOGICAL_SET: return createLogicalSet();
			case InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY: return createInstrumentationProfileRepository();
			case InputPackage.INSTRUMENTED_CODE_AREA: return createInstrumentedCodeArea();
			case InputPackage.INSTRUMENTED_METHOD: return createInstrumentedMethod();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case InputPackage.AGGREGATION_TYPE:
				return createAggregationTypeFromString(eDataType, initialValue);
			case InputPackage.INTERNAL_RESULT_STORAGE_TYPE:
				return createInternalResultStorageTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case InputPackage.AGGREGATION_TYPE:
				return convertAggregationTypeToString(eDataType, instanceValue);
			case InputPackage.INTERNAL_RESULT_STORAGE_TYPE:
				return convertInternalResultStorageTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfile createInstrumentationProfile() {
		InstrumentationProfileImpl instrumentationProfile = new InstrumentationProfileImpl();
		return instrumentationProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogicalSet createLogicalSet() {
		LogicalSetImpl logicalSet = new LogicalSetImpl();
		return logicalSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfileRepository createInstrumentationProfileRepository() {
		InstrumentationProfileRepositoryImpl instrumentationProfileRepository = new InstrumentationProfileRepositoryImpl();
		return instrumentationProfileRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentedCodeArea createInstrumentedCodeArea() {
		InstrumentedCodeAreaImpl instrumentedCodeArea = new InstrumentedCodeAreaImpl();
		return instrumentedCodeArea;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentedMethod createInstrumentedMethod() {
		InstrumentedMethodImpl instrumentedMethod = new InstrumentedMethodImpl();
		return instrumentedMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationType createAggregationTypeFromString(EDataType eDataType, String initialValue) {
		AggregationType result = AggregationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAggregationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalResultStorageType createInternalResultStorageTypeFromString(EDataType eDataType, String initialValue) {
		InternalResultStorageType result = InternalResultStorageType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInternalResultStorageTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputPackage getInputPackage() {
		return (InputPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static InputPackage getPackage() {
		return InputPackage.eINSTANCE;
	}

} //InputFactoryImpl
