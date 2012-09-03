/**
 * 
 */
package de.fzi.se.bycounter.modelbridge.tests;

import java.io.File;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.se.bycounter.modelbridge.example.impl.Calls;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;
import edu.kit.ipd.sdq.bycounter.modelbridge.ByCounterWrapper;
import edu.kit.ipd.sdq.bycounter.modelbridge.util.ModelHandlingUtil;
import edu.kit.ipd.sdq.bycounter.output.ResultCollection;

/*
 * Actions required after updating the GAST
 * Open models/ByCounter/ExternalCallTest.input:
 * 1. If there is a gast loaded, remove it.
 * 2. Right click: select "Load Ressource" and open the Fibonacci(merged).gast model.
 * 3. Navigate to the Logical Set in the Instrumentation Profile and 
 *    in the "Properties" view, change remove the value of Internal Classes.
 * 4. Save, close and reopen the .input model.
 * 5. Change the value described in 3. (click '...') and add the FibonacciAlgorithm class.
 * 6. Change the properties of Instrumented Code Area so that both the 
 *    'from' and 'to' properties reference the id of the main Block Statement of the 
 *    'fibonacci' method in FibonacciAlgorithm (look up in gast).
 */

/**Test case for measuring external calls with the BytecodeCounter modelbridge.
 * @author martin
 * @author groenda
 */
public class CallsTest {

	/** Path in the file system to the file containing the GAST model (input). */
	private static final String FILEPATH_GAST_MODEL = "models/GAST/Fibonacci(merged).gast";
	/** Path in the file system to the file containing the Measurement run (as output). */
	private static final String FILEPATH_OUTPUT_MODEL = "models/Validation/ExternalCallTest_MeasurementRun.output";
	/** Path in the file system to the file containing the instrumentation profile (input). */
	private static final String FILEPATH_INPUT_INSTRUMENTATION_PROFILE = "models/ByCounter/CallsTest.input";
	

	/**Initialization of the test environment.
	 */
	@BeforeClass
	public static void beforeClass() {
		ModelHandlingUtil.initializeEmfFactories();
	}
	
	/**Test measurement equality for whole function via InstrumentedCodeArea and InstrumentedMethod.
	 */
	@Test
	public void methodMeasurementsMustBeIdentical() {
		// TODO @Martin: Execute and verify that execution via InstrumentedMethod and InstrumentedCodeArea with the method body are the same. methodInternCall() can be used for this purpose.
		Assert.fail();
	}
	
	/**Test measurement of pure internal 'calls' / Bytecode instructions.
	 */
	@Test
	public void testInternalCall() {
		// TODO @Martin: Execute and verify method methodInternalCall();
		Assert.fail();
	}
	
	/**Test measurement of measurement external calls without aggregation.
	 */
	@Test
	public void testMethodExternalCallWithOutAggregation() {
		// TODO @Martin: Execute and verify method methodExternalCall();
		Assert.fail();
	}

	/**Test measurement of measurement external calls with aggregation.
	 */
	@Test
	public void testMethodExternalCallWithAggregation() {
		// TODO @Martin: Execute and verify method methodExternalCall();
		Assert.fail();
	}
	
	/**Test measurement of measurement external calls with aggregation but exclusion rules.
	 */
	@Test
	public void testMethodExternalCallWithAggregationExclusion() {
		/* TODO @Martin: Execute and verify method methodExternalCall();
		 * Exclude Method myMethod via InstrumentationProfile.aggregationExcludes.
		 */
		Assert.fail();
	}

	/**Test call to java API (not including Bytecode instructions).
	 */
	@Test
	public void testJavaApiCall() {
		// TODO @Martin: Execute and verify method javaApiCall().
		Assert.fail();
	}
	
	/**Test class-external and logical-set internal calls.
	 * Shows if the aggregation work correctly beyond a single class.
	 */
	@Test
	public void testClassExternalButLogicalSetInternalCalls() {
		/* TODO @Martin: Execute and verify class-external method classExternalCall().
		 * InstrumentrationProfile: LogicalSet contains External(.class).
		 */
		Assert.fail();
	}
	
	/**Test using ByCounter with online execution updates so that 
	 * changes in the result model can be observed.
	 */
	@Test
	public void testResultUpdates() {
		Assert.fail();
		// Instrument
		InstrumentationProfileRepository profileRepo = (InstrumentationProfileRepository) ModelHandlingUtil
				.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_INPUT_INSTRUMENTATION_PROFILE);
		ByCounterWrapper byCounterWrapper = new ByCounterWrapper();
		if (profileRepo.getInstrumentationProfile().size() != 1) {
			throw new IllegalStateException("File contains more than one instrumentation profile. This is not expected by the test case.");
		}
		byCounterWrapper.setInstrumentationConfiguration(profileRepo.getInstrumentationProfile().get(0));
		
		// Execution
		Method targetMethod = getFibonacciMethod();
		Object instance = byCounterWrapper.instantiate(targetMethod);
		byCounterWrapper.execute(targetMethod, instance, new Object[] {100});

		// Store results
		ResultCollection result = byCounterWrapper.generateResult();
		
		result.eAdapters().add(new EContentAdapter() {
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				System.out.println("Notification received: " + notification);
			}
		});
	}
	
	/**Test class and logical-set internal calls.
	 * Shows if internal call is measured correctly.
	 */
	@Test
	public void testClassAndLogicalSetInternalCalls() {
		/* TODO @Martin: Execute and verify class-external method classExternalCall().
		 * InstrumentrationProfile: LogicalSet does not contain External(.class).
		 */
		Assert.fail();
		Assert.assertTrue("Instrumentation profile repository file not found.", new File(FILEPATH_INPUT_INSTRUMENTATION_PROFILE).exists());
		// Instrument
		InstrumentationProfileRepository profileRepo = (InstrumentationProfileRepository) ModelHandlingUtil
				.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_INPUT_INSTRUMENTATION_PROFILE);
		ByCounterWrapper byCounterWrapper = new ByCounterWrapper();
		if (profileRepo.getInstrumentationProfile().size() != 1) {
			throw new IllegalStateException("File contains more than one instrumentation profile. This is not expected by the test case.");
		}
		byCounterWrapper.setInstrumentationConfiguration(profileRepo.getInstrumentationProfile().get(0));
		
		// Execution
		Method targetMethod = findMethodInCalls("methodInternalCall");
		Object instance = byCounterWrapper.instantiate(targetMethod);
		byCounterWrapper.execute(targetMethod, instance, new Object[] {});

		// Store results
		ResultCollection result = byCounterWrapper.generateResult();
		ModelHandlingUtil.saveToFile(ModelHandlingUtil.getResourceSet(),
				FILEPATH_OUTPUT_MODEL,
				result);

	}
	
	/**Test class and logical-set external calls.
	 * Shows if external call is measured correctly.
	 */
	@Test
	public void testClassAndLogicalSetExternalCalls() {
		/* TODO @Martin: Execute and verify class-external method classExternalCall().
		 * InstrumentrationProfile: LogicalSet does not contain External(.class).
		 */
		Assert.fail();
		Assert.assertTrue("Instrumentation profile repository file not found.", new File(FILEPATH_INPUT_INSTRUMENTATION_PROFILE).exists());
		// Instrument
		InstrumentationProfileRepository profileRepo = (InstrumentationProfileRepository) ModelHandlingUtil
				.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_INPUT_INSTRUMENTATION_PROFILE);
		ByCounterWrapper byCounterWrapper = new ByCounterWrapper();
		if (profileRepo.getInstrumentationProfile().size() != 1) {
			throw new IllegalStateException("File contains more than one instrumentation profile. This is not expected by the test case.");
		}
		byCounterWrapper.setInstrumentationConfiguration(profileRepo.getInstrumentationProfile().get(0));
		
		// Execution
		Method targetMethod = getMethodExternalCall();
		Object instance = byCounterWrapper.instantiate(targetMethod);
		byCounterWrapper.execute(targetMethod, instance, new Object[] {});

		// Store results
		ResultCollection result = byCounterWrapper.generateResult();
		ModelHandlingUtil.saveToFile(ModelHandlingUtil.getResourceSet(),
				FILEPATH_OUTPUT_MODEL,
				result);

	}

	/**Gets the first method with the name fibonacci from <code>FILEPATH_GAST_MODEL</code>.
	 * @return The fibonacci method in the gast.
	 */
	private Method getFibonacciMethod() {
		return findMethodInCalls("fibonacci");
	}

	/**Gets the first method with the name methodExternalCall from <code>FILEPATH_GAST_MODEL</code>.
	 * @return The methodExternalCall method in the gast.
	 */
	private Method getMethodExternalCall() {
		return findMethodInCalls("methodExternalCall");
	}

	/**
	 * Find the specified method in the {@link Calls} class GAST.
	 * @param simpleMethodName Simple method name.
	 * @return {@link Method} when found. Null otherwise.
	 */
	private Method findMethodInCalls(final String simpleMethodName) {
		Root gastRepo = (Root) ModelHandlingUtil.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_GAST_MODEL);
		
		final String fqpn = Calls.class.getPackage().getName();
		// find Package
		de.fzi.gast.core.Package pkg = gastRepo.getPackageByQualifiedName(fqpn);
		if (pkg != null) {
			for (GASTClass clazz : pkg.getClasses()) {
				// find Class
				if (clazz.getQualifiedName().equals(Calls.class.getCanonicalName())) {
					for(Method meth : clazz.getMethods()) {
						if(meth.getSimpleName().equals(simpleMethodName)) {
							return meth;
						}
					}
				}
			}
		}
		return null;
	}
}
