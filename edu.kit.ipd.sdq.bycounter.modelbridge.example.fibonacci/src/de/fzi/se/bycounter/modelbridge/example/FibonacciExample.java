/**
 * 
 */
package de.fzi.se.bycounter.modelbridge.example;

import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.types.GASTClass;
import de.fzi.se.bycounter.modelbridge.example.impl.FibonacciAlgorithm;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;
import edu.kit.ipd.sdq.bycounter.modelbridge.ByCounterWrapper;
import edu.kit.ipd.sdq.bycounter.modelbridge.util.ModelHandlingUtil;
import edu.kit.ipd.sdq.bycounter.output.MeasurementRun;

/**Example for using the ByCounter modelbridge for measurements.
 * @author groenda
 */
public class FibonacciExample {

	/** Path in the file system to the file containing the GAST model (input). */
	private static final String FILEPATH_GAST_MODEL = "models/GAST/Fibonacci(merged).gast";
	/** Path in the file system to the file containing the Measurement run (as output). */
	private static final String FILEPATH_OUTPUT_MODEL = "models/ByCounter/Fibonacci.output";
	/** Path in the file system to the file containing the instrumentation profile (input). */
	private static final String FILEPATH_INPUT_INSTRUMENTATION_PROFILE = "models/ByCounter/Fibonacci.input";

	/**Runs the example as java application.
	 * @param args Arguments are not used in this example.
	 */
	public static void main(String args[]) {
		FibonacciExample example = new FibonacciExample();
		example.run();
	}

	/**Runs the example and stores the output in the position specified by {@link FILEPATH_OUTPUT_MODEL}.
	 */
	public void run() {
		ModelHandlingUtil.initializeEmfFactories();

		// Instrument
		InstrumentationProfileRepository profileRepo = (InstrumentationProfileRepository) ModelHandlingUtil
				.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_INPUT_INSTRUMENTATION_PROFILE);
		ByCounterWrapper byCounterWrapper = new ByCounterWrapper();
		if (profileRepo.getInstrumentationProfile().size() != 1) {
			throw new IllegalStateException("File contains more than one instrumentation profile. Example code expects exactly one profile.");
		}
		byCounterWrapper.setInstrumentationConfiguration(profileRepo.getInstrumentationProfile().get(0));
		
		// Execute
		Method targetMethod = getFibonacciMethod();
		byCounterWrapper.execute(targetMethod, new Object[] {100});

		// Store results
		MeasurementRun result = byCounterWrapper.generateResult();
		ModelHandlingUtil.saveToFile(ModelHandlingUtil.getResourceSet(),
				FILEPATH_OUTPUT_MODEL,
				result);
	}

	/**Gets the first method with the name fibonacci from <code>FILEPATH_GAST_MODEL</code>.
	 * @return The fibonacci method in the GAST model.
	 */
	private Method getFibonacciMethod() {
		Root gastRepo = (Root) ModelHandlingUtil.loadFromFile(ModelHandlingUtil.getResourceSet(), FILEPATH_GAST_MODEL);
		
		final String fqpn = FibonacciAlgorithm.class.getPackage().getName();
		// find Package
		de.fzi.gast.core.Package pkg = gastRepo.getPackageByQualifiedName(fqpn);
		if (pkg != null) {
			for (GASTClass clazz : pkg.getClasses()) {
				// find Class
				if (clazz.getQualifiedName().equals(FibonacciAlgorithm.class.getCanonicalName())) {
					for(Method meth : clazz.getMethods()) {
						if(meth.getSimpleName().equals("fibonacci")) {
							return meth;
						}
					}
				}
			}
		}
		return null;
	}
}
