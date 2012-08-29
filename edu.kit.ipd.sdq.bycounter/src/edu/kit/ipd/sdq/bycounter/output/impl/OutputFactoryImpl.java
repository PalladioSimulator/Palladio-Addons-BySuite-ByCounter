/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output.impl;

import edu.kit.ipd.sdq.bycounter.output.*;

import org.eclipse.emf.ecore.EClass;
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
public class OutputFactoryImpl extends EFactoryImpl implements OutputFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OutputFactory init() {
		try {
			OutputFactory theOutputFactory = (OutputFactory)EPackage.Registry.INSTANCE.getEFactory("http://sdq.ipd.kit.edu/ByCounterModels/Output/0.3"); 
			if (theOutputFactory != null) {
				return theOutputFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OutputFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputFactoryImpl() {
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
			case OutputPackage.OUTPUT_MODEL_REPOSITORY: return createOutputModelRepository();
			case OutputPackage.RESULT_COLLECTION: return createResultCollection();
			case OutputPackage.REQUEST_RESULT: return createRequestResult();
			case OutputPackage.COUNTING_RESULT: return createCountingResult();
			case OutputPackage.THREADED_COUNTING_RESULT: return createThreadedCountingResult();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputModelRepository createOutputModelRepository() {
		OutputModelRepositoryImpl outputModelRepository = new OutputModelRepositoryImpl();
		return outputModelRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResultCollection createResultCollection() {
		ResultCollectionImpl resultCollection = new ResultCollectionImpl();
		return resultCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RequestResult createRequestResult() {
		RequestResultImpl requestResult = new RequestResultImpl();
		return requestResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CountingResult createCountingResult() {
		CountingResultImpl countingResult = new CountingResultImpl();
		return countingResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThreadedCountingResult createThreadedCountingResult() {
		ThreadedCountingResultImpl threadedCountingResult = new ThreadedCountingResultImpl();
		return threadedCountingResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OutputPackage getOutputPackage() {
		return (OutputPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OutputPackage getPackage() {
		return OutputPackage.eINSTANCE;
	}

} //OutputFactoryImpl
