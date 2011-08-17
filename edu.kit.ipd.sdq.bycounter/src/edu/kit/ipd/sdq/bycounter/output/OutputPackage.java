/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Decorator&nbsp;models&nbsp;which&nbsp;annotate&nbsp;raw&nbsp;resource&nbsp;demands&nbsp;to&nbsp;AbstractInternalControlFlowActions.
 * <!-- end-model-doc -->
 * @see edu.kit.ipd.sdq.bycounter.output.OutputFactory
 * @model kind="package"
 * @generated
 */
public interface OutputPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "output";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sdq.ipd.kit.edu/ByCounterModels/Output/0.3";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ByCounter.Output";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OutputPackage eINSTANCE = edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl.init();

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl <em>Model Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getOutputModelRepository()
	 * @generated
	 */
	int OUTPUT_MODEL_REPOSITORY = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Measurement Runs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Environment Characterisations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_MODEL_REPOSITORY_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl <em>Measurement Run</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getMeasurementRun()
	 * @generated
	 */
	int MEASUREMENT_RUN = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RUN__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Environment Characterisation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Requests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RUN__REQUESTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Measurement Run</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASUREMENT_RUN_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.EnvironmentDescriptionImpl <em>Environment Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.EnvironmentDescriptionImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getEnvironmentDescription()
	 * @generated
	 */
	int ENVIRONMENT_DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DESCRIPTION__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The number of structural features of the '<em>Environment Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENVIRONMENT_DESCRIPTION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl <em>Request</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getRequest()
	 * @generated
	 */
	int REQUEST = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Observed Execution Sequence</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST__OBSERVED_EXECUTION_SEQUENCE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Measurement Run</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST__MEASUREMENT_RUN = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Request</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUEST_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl <em>Observed Entity Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getObservedEntityExecution()
	 * @generated
	 */
	int OBSERVED_ENTITY_EXECUTION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Resource Demands</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Observed Forked Execution Sequence</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Observed Execution Sequence Forked By</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Entity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__ENTITY = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Request</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION__REQUEST = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Observed Entity Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBSERVED_ENTITY_EXECUTION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl <em>Resource Demands</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getResourceDemands()
	 * @generated
	 */
	int RESOURCE_DEMANDS = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEMANDS__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Function Calls</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEMANDS__FUNCTION_CALLS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Java VM Call</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEMANDS__JAVA_VM_CALL = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Observed Execution</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEMANDS__OBSERVED_EXECUTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Resource Demands</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_DEMANDS_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl <em>Function Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getFunctionCall()
	 * @generated
	 */
	int FUNCTION_CALL = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__PARAMETER_INSTANCE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__FUNCTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Number Observations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__NUMBER_OBSERVATIONS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__NATIVE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Synchronized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__SYNCHRONIZED = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Resource Demands</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL__RESOURCE_DEMANDS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Function Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_CALL_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl <em>Parameter Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getParameterInstance()
	 * @generated
	 */
	int PARAMETER_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INSTANCE__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Formal Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INSTANCE__FORMAL_PARAMETER = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter Characterisation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Function Call</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INSTANCE__FUNCTION_CALL = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Parameter Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_INSTANCE_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl <em>Parameter Characterisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getParameterCharacterisation()
	 * @generated
	 */
	int PARAMETER_CHARACTERISATION = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CHARACTERISATION__ID = IdentifierPackage.IDENTIFIER__ID;

	/**
	 * The feature id for the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CHARACTERISATION__CHARACTERISATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Parameter Characterisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_CHARACTERISATION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl <em>Java VM Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getJavaVMCall()
	 * @generated
	 */
	int JAVA_VM_CALL = 9;

	/**
	 * The feature id for the '<em><b>Number Observations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_VM_CALL__NUMBER_OBSERVATIONS = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_VM_CALL__ID = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_VM_CALL__NAME = 2;

	/**
	 * The feature id for the '<em><b>Resource Demands</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_VM_CALL__RESOURCE_DEMANDS = 3;

	/**
	 * The number of structural features of the '<em>Java VM Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JAVA_VM_CALL_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.DoubleCharacterisationImpl <em>Double Characterisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.DoubleCharacterisationImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getDoubleCharacterisation()
	 * @generated
	 */
	int DOUBLE_CHARACTERISATION = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_CHARACTERISATION__ID = PARAMETER_CHARACTERISATION__ID;

	/**
	 * The feature id for the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_CHARACTERISATION__CHARACTERISATION = PARAMETER_CHARACTERISATION__CHARACTERISATION;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_CHARACTERISATION__PARAMETER_INSTANCE = PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_CHARACTERISATION__VALUE = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Characterisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_CHARACTERISATION_FEATURE_COUNT = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.LongCharacterisationImpl <em>Long Characterisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.LongCharacterisationImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getLongCharacterisation()
	 * @generated
	 */
	int LONG_CHARACTERISATION = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_CHARACTERISATION__ID = PARAMETER_CHARACTERISATION__ID;

	/**
	 * The feature id for the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_CHARACTERISATION__CHARACTERISATION = PARAMETER_CHARACTERISATION__CHARACTERISATION;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_CHARACTERISATION__PARAMETER_INSTANCE = PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_CHARACTERISATION__VALUE = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Characterisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_CHARACTERISATION_FEATURE_COUNT = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.BooleanCharacterisationImpl <em>Boolean Characterisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.BooleanCharacterisationImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getBooleanCharacterisation()
	 * @generated
	 */
	int BOOLEAN_CHARACTERISATION = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CHARACTERISATION__ID = PARAMETER_CHARACTERISATION__ID;

	/**
	 * The feature id for the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CHARACTERISATION__CHARACTERISATION = PARAMETER_CHARACTERISATION__CHARACTERISATION;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CHARACTERISATION__PARAMETER_INSTANCE = PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CHARACTERISATION__VALUE = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Characterisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CHARACTERISATION_FEATURE_COUNT = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.StringCharacterisationImpl <em>String Characterisation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.StringCharacterisationImpl
	 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getStringCharacterisation()
	 * @generated
	 */
	int STRING_CHARACTERISATION = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CHARACTERISATION__ID = PARAMETER_CHARACTERISATION__ID;

	/**
	 * The feature id for the '<em><b>Characterisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CHARACTERISATION__CHARACTERISATION = PARAMETER_CHARACTERISATION__CHARACTERISATION;

	/**
	 * The feature id for the '<em><b>Parameter Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CHARACTERISATION__PARAMETER_INSTANCE = PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CHARACTERISATION__VALUE = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Characterisation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_CHARACTERISATION_FEATURE_COUNT = PARAMETER_CHARACTERISATION_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository <em>Model Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Repository</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputModelRepository
	 * @generated
	 */
	EClass getOutputModelRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getMeasurementRuns <em>Measurement Runs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurement Runs</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getMeasurementRuns()
	 * @see #getOutputModelRepository()
	 * @generated
	 */
	EReference getOutputModelRepository_MeasurementRuns();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getEnvironmentCharacterisations <em>Environment Characterisations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Environment Characterisations</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputModelRepository#getEnvironmentCharacterisations()
	 * @see #getOutputModelRepository()
	 * @generated
	 */
	EReference getOutputModelRepository_EnvironmentCharacterisations();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun <em>Measurement Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measurement Run</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MeasurementRun
	 * @generated
	 */
	EClass getMeasurementRun();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getEnvironmentCharacterisation <em>Environment Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Environment Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getEnvironmentCharacterisation()
	 * @see #getMeasurementRun()
	 * @generated
	 */
	EReference getMeasurementRun_EnvironmentCharacterisation();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getRequests <em>Requests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requests</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.MeasurementRun#getRequests()
	 * @see #getMeasurementRun()
	 * @generated
	 */
	EReference getMeasurementRun_Requests();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription <em>Environment Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Environment Description</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.EnvironmentDescription
	 * @generated
	 */
	EClass getEnvironmentDescription();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.Request <em>Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Request</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.Request
	 * @generated
	 */
	EClass getRequest();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.Request#getObservedExecutionSequence <em>Observed Execution Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Observed Execution Sequence</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.Request#getObservedExecutionSequence()
	 * @see #getRequest()
	 * @generated
	 */
	EReference getRequest_ObservedExecutionSequence();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun <em>Measurement Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Measurement Run</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.Request#getMeasurementRun()
	 * @see #getRequest()
	 * @generated
	 */
	EReference getRequest_MeasurementRun();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution <em>Observed Entity Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Observed Entity Execution</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution
	 * @generated
	 */
	EClass getObservedEntityExecution();

	/**
	 * Returns the meta object for the containment reference '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Resource Demands</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getResourceDemands()
	 * @see #getObservedEntityExecution()
	 * @generated
	 */
	EReference getObservedEntityExecution_ResourceDemands();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedForkedExecutionSequence <em>Observed Forked Execution Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Observed Forked Execution Sequence</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedForkedExecutionSequence()
	 * @see #getObservedEntityExecution()
	 * @generated
	 */
	EReference getObservedEntityExecution_ObservedForkedExecutionSequence();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy <em>Observed Execution Sequence Forked By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Observed Execution Sequence Forked By</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getObservedExecutionSequenceForkedBy()
	 * @see #getObservedEntityExecution()
	 * @generated
	 */
	EReference getObservedEntityExecution_ObservedExecutionSequenceForkedBy();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getEntity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entity</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getEntity()
	 * @see #getObservedEntityExecution()
	 * @generated
	 */
	EReference getObservedEntityExecution_Entity();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest <em>Request</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Request</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ObservedEntityExecution#getRequest()
	 * @see #getObservedEntityExecution()
	 * @generated
	 */
	EReference getObservedEntityExecution_Request();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Demands</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands
	 * @generated
	 */
	EClass getResourceDemands();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getFunctionCalls <em>Function Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function Calls</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getFunctionCalls()
	 * @see #getResourceDemands()
	 * @generated
	 */
	EReference getResourceDemands_FunctionCalls();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getJavaVMCall <em>Java VM Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Java VM Call</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getJavaVMCall()
	 * @see #getResourceDemands()
	 * @generated
	 */
	EReference getResourceDemands_JavaVMCall();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution <em>Observed Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Observed Execution</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getObservedExecution()
	 * @see #getResourceDemands()
	 * @generated
	 */
	EReference getResourceDemands_ObservedExecution();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Call</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall
	 * @generated
	 */
	EClass getFunctionCall();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getParameterInstance <em>Parameter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Instance</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getParameterInstance()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_ParameterInstance();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getFunction()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_Function();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getNumberObservations <em>Number Observations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Observations</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getNumberObservations()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EAttribute getFunctionCall_NumberObservations();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#isNative()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EAttribute getFunctionCall_Native();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#isSynchronized <em>Synchronized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Synchronized</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#isSynchronized()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EAttribute getFunctionCall_Synchronized();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource Demands</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.FunctionCall#getResourceDemands()
	 * @see #getFunctionCall()
	 * @generated
	 */
	EReference getFunctionCall_ResourceDemands();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance <em>Parameter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Instance</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance
	 * @generated
	 */
	EClass getParameterInstance();

	/**
	 * Returns the meta object for the reference '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFormalParameter <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Formal Parameter</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFormalParameter()
	 * @see #getParameterInstance()
	 * @generated
	 */
	EReference getParameterInstance_FormalParameter();

	/**
	 * Returns the meta object for the containment reference list '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getParameterCharacterisation <em>Parameter Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getParameterCharacterisation()
	 * @see #getParameterInstance()
	 * @generated
	 */
	EReference getParameterInstance_ParameterCharacterisation();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall <em>Function Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Function Call</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterInstance#getFunctionCall()
	 * @see #getParameterInstance()
	 * @generated
	 */
	EReference getParameterInstance_FunctionCall();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation <em>Parameter Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation
	 * @generated
	 */
	EClass getParameterCharacterisation();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getCharacterisation <em>Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getCharacterisation()
	 * @see #getParameterCharacterisation()
	 * @generated
	 */
	EAttribute getParameterCharacterisation_Characterisation();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance <em>Parameter Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parameter Instance</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.ParameterCharacterisation#getParameterInstance()
	 * @see #getParameterCharacterisation()
	 * @generated
	 */
	EReference getParameterCharacterisation_ParameterInstance();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall <em>Java VM Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java VM Call</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall
	 * @generated
	 */
	EClass getJavaVMCall();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getNumberObservations <em>Number Observations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Observations</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getNumberObservations()
	 * @see #getJavaVMCall()
	 * @generated
	 */
	EAttribute getJavaVMCall_NumberObservations();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getId()
	 * @see #getJavaVMCall()
	 * @generated
	 */
	EAttribute getJavaVMCall_Id();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getName()
	 * @see #getJavaVMCall()
	 * @generated
	 */
	EAttribute getJavaVMCall_Name();

	/**
	 * Returns the meta object for the container reference '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands <em>Resource Demands</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource Demands</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands()
	 * @see #getJavaVMCall()
	 * @generated
	 */
	EReference getJavaVMCall_ResourceDemands();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.DoubleCharacterisation <em>Double Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.DoubleCharacterisation
	 * @generated
	 */
	EClass getDoubleCharacterisation();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.DoubleCharacterisation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.DoubleCharacterisation#getValue()
	 * @see #getDoubleCharacterisation()
	 * @generated
	 */
	EAttribute getDoubleCharacterisation_Value();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.LongCharacterisation <em>Long Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.LongCharacterisation
	 * @generated
	 */
	EClass getLongCharacterisation();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.LongCharacterisation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.LongCharacterisation#getValue()
	 * @see #getLongCharacterisation()
	 * @generated
	 */
	EAttribute getLongCharacterisation_Value();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.BooleanCharacterisation <em>Boolean Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.BooleanCharacterisation
	 * @generated
	 */
	EClass getBooleanCharacterisation();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.BooleanCharacterisation#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.BooleanCharacterisation#isValue()
	 * @see #getBooleanCharacterisation()
	 * @generated
	 */
	EAttribute getBooleanCharacterisation_Value();

	/**
	 * Returns the meta object for class '{@link edu.kit.ipd.sdq.bycounter.output.StringCharacterisation <em>String Characterisation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Characterisation</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.StringCharacterisation
	 * @generated
	 */
	EClass getStringCharacterisation();

	/**
	 * Returns the meta object for the attribute '{@link edu.kit.ipd.sdq.bycounter.output.StringCharacterisation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see edu.kit.ipd.sdq.bycounter.output.StringCharacterisation#getValue()
	 * @see #getStringCharacterisation()
	 * @generated
	 */
	EAttribute getStringCharacterisation_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OutputFactory getOutputFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl <em>Model Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputModelRepositoryImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getOutputModelRepository()
		 * @generated
		 */
		EClass OUTPUT_MODEL_REPOSITORY = eINSTANCE.getOutputModelRepository();

		/**
		 * The meta object literal for the '<em><b>Measurement Runs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_MODEL_REPOSITORY__MEASUREMENT_RUNS = eINSTANCE.getOutputModelRepository_MeasurementRuns();

		/**
		 * The meta object literal for the '<em><b>Environment Characterisations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTPUT_MODEL_REPOSITORY__ENVIRONMENT_CHARACTERISATIONS = eINSTANCE.getOutputModelRepository_EnvironmentCharacterisations();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl <em>Measurement Run</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.MeasurementRunImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getMeasurementRun()
		 * @generated
		 */
		EClass MEASUREMENT_RUN = eINSTANCE.getMeasurementRun();

		/**
		 * The meta object literal for the '<em><b>Environment Characterisation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_RUN__ENVIRONMENT_CHARACTERISATION = eINSTANCE.getMeasurementRun_EnvironmentCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Requests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASUREMENT_RUN__REQUESTS = eINSTANCE.getMeasurementRun_Requests();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.EnvironmentDescriptionImpl <em>Environment Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.EnvironmentDescriptionImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getEnvironmentDescription()
		 * @generated
		 */
		EClass ENVIRONMENT_DESCRIPTION = eINSTANCE.getEnvironmentDescription();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl <em>Request</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.RequestImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getRequest()
		 * @generated
		 */
		EClass REQUEST = eINSTANCE.getRequest();

		/**
		 * The meta object literal for the '<em><b>Observed Execution Sequence</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST__OBSERVED_EXECUTION_SEQUENCE = eINSTANCE.getRequest_ObservedExecutionSequence();

		/**
		 * The meta object literal for the '<em><b>Measurement Run</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUEST__MEASUREMENT_RUN = eINSTANCE.getRequest_MeasurementRun();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl <em>Observed Entity Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ObservedEntityExecutionImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getObservedEntityExecution()
		 * @generated
		 */
		EClass OBSERVED_ENTITY_EXECUTION = eINSTANCE.getObservedEntityExecution();

		/**
		 * The meta object literal for the '<em><b>Resource Demands</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVED_ENTITY_EXECUTION__RESOURCE_DEMANDS = eINSTANCE.getObservedEntityExecution_ResourceDemands();

		/**
		 * The meta object literal for the '<em><b>Observed Forked Execution Sequence</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVED_ENTITY_EXECUTION__OBSERVED_FORKED_EXECUTION_SEQUENCE = eINSTANCE.getObservedEntityExecution_ObservedForkedExecutionSequence();

		/**
		 * The meta object literal for the '<em><b>Observed Execution Sequence Forked By</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVED_ENTITY_EXECUTION__OBSERVED_EXECUTION_SEQUENCE_FORKED_BY = eINSTANCE.getObservedEntityExecution_ObservedExecutionSequenceForkedBy();

		/**
		 * The meta object literal for the '<em><b>Entity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVED_ENTITY_EXECUTION__ENTITY = eINSTANCE.getObservedEntityExecution_Entity();

		/**
		 * The meta object literal for the '<em><b>Request</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBSERVED_ENTITY_EXECUTION__REQUEST = eINSTANCE.getObservedEntityExecution_Request();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl <em>Resource Demands</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ResourceDemandsImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getResourceDemands()
		 * @generated
		 */
		EClass RESOURCE_DEMANDS = eINSTANCE.getResourceDemands();

		/**
		 * The meta object literal for the '<em><b>Function Calls</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DEMANDS__FUNCTION_CALLS = eINSTANCE.getResourceDemands_FunctionCalls();

		/**
		 * The meta object literal for the '<em><b>Java VM Call</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DEMANDS__JAVA_VM_CALL = eINSTANCE.getResourceDemands_JavaVMCall();

		/**
		 * The meta object literal for the '<em><b>Observed Execution</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_DEMANDS__OBSERVED_EXECUTION = eINSTANCE.getResourceDemands_ObservedExecution();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl <em>Function Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.FunctionCallImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getFunctionCall()
		 * @generated
		 */
		EClass FUNCTION_CALL = eINSTANCE.getFunctionCall();

		/**
		 * The meta object literal for the '<em><b>Parameter Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__PARAMETER_INSTANCE = eINSTANCE.getFunctionCall_ParameterInstance();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__FUNCTION = eINSTANCE.getFunctionCall_Function();

		/**
		 * The meta object literal for the '<em><b>Number Observations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_CALL__NUMBER_OBSERVATIONS = eINSTANCE.getFunctionCall_NumberObservations();

		/**
		 * The meta object literal for the '<em><b>Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_CALL__NATIVE = eINSTANCE.getFunctionCall_Native();

		/**
		 * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_CALL__SYNCHRONIZED = eINSTANCE.getFunctionCall_Synchronized();

		/**
		 * The meta object literal for the '<em><b>Resource Demands</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_CALL__RESOURCE_DEMANDS = eINSTANCE.getFunctionCall_ResourceDemands();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl <em>Parameter Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ParameterInstanceImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getParameterInstance()
		 * @generated
		 */
		EClass PARAMETER_INSTANCE = eINSTANCE.getParameterInstance();

		/**
		 * The meta object literal for the '<em><b>Formal Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_INSTANCE__FORMAL_PARAMETER = eINSTANCE.getParameterInstance_FormalParameter();

		/**
		 * The meta object literal for the '<em><b>Parameter Characterisation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_INSTANCE__PARAMETER_CHARACTERISATION = eINSTANCE.getParameterInstance_ParameterCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Function Call</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_INSTANCE__FUNCTION_CALL = eINSTANCE.getParameterInstance_FunctionCall();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl <em>Parameter Characterisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.ParameterCharacterisationImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getParameterCharacterisation()
		 * @generated
		 */
		EClass PARAMETER_CHARACTERISATION = eINSTANCE.getParameterCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Characterisation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_CHARACTERISATION__CHARACTERISATION = eINSTANCE.getParameterCharacterisation_Characterisation();

		/**
		 * The meta object literal for the '<em><b>Parameter Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_CHARACTERISATION__PARAMETER_INSTANCE = eINSTANCE.getParameterCharacterisation_ParameterInstance();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl <em>Java VM Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.JavaVMCallImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getJavaVMCall()
		 * @generated
		 */
		EClass JAVA_VM_CALL = eINSTANCE.getJavaVMCall();

		/**
		 * The meta object literal for the '<em><b>Number Observations</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_VM_CALL__NUMBER_OBSERVATIONS = eINSTANCE.getJavaVMCall_NumberObservations();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_VM_CALL__ID = eINSTANCE.getJavaVMCall_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_VM_CALL__NAME = eINSTANCE.getJavaVMCall_Name();

		/**
		 * The meta object literal for the '<em><b>Resource Demands</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JAVA_VM_CALL__RESOURCE_DEMANDS = eINSTANCE.getJavaVMCall_ResourceDemands();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.DoubleCharacterisationImpl <em>Double Characterisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.DoubleCharacterisationImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getDoubleCharacterisation()
		 * @generated
		 */
		EClass DOUBLE_CHARACTERISATION = eINSTANCE.getDoubleCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_CHARACTERISATION__VALUE = eINSTANCE.getDoubleCharacterisation_Value();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.LongCharacterisationImpl <em>Long Characterisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.LongCharacterisationImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getLongCharacterisation()
		 * @generated
		 */
		EClass LONG_CHARACTERISATION = eINSTANCE.getLongCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LONG_CHARACTERISATION__VALUE = eINSTANCE.getLongCharacterisation_Value();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.BooleanCharacterisationImpl <em>Boolean Characterisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.BooleanCharacterisationImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getBooleanCharacterisation()
		 * @generated
		 */
		EClass BOOLEAN_CHARACTERISATION = eINSTANCE.getBooleanCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_CHARACTERISATION__VALUE = eINSTANCE.getBooleanCharacterisation_Value();

		/**
		 * The meta object literal for the '{@link edu.kit.ipd.sdq.bycounter.output.impl.StringCharacterisationImpl <em>String Characterisation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.StringCharacterisationImpl
		 * @see edu.kit.ipd.sdq.bycounter.output.impl.OutputPackageImpl#getStringCharacterisation()
		 * @generated
		 */
		EClass STRING_CHARACTERISATION = eINSTANCE.getStringCharacterisation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_CHARACTERISATION__VALUE = eINSTANCE.getStringCharacterisation_Value();

	}

} //OutputPackage
