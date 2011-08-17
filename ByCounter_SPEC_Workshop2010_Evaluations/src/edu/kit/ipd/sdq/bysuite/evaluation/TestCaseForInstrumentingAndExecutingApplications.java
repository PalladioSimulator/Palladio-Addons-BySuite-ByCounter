package edu.kit.ipd.sdq.bysuite.evaluation;

import java.util.Collection;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import SciMark.MonteCarlo;
import SciMark.Random;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.TestASMBytecodes;
import de.uka.ipd.sdq.ByCounter.test.helpers.JGFCastBench_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.Linpack_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.SciMarkMonteCarlo_MK;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * JUnit Test for the instrumentation of compress.
 *
 */
@RunWith(Parameterized.class)
public class TestCaseForInstrumentingAndExecutingApplications {	

	private static final Object[] EXECUTION_PARAMETERS_NONE = new Object[]{new String[]{}};

	private static final int PROBLEM_SIZE_RD2008 = 1000*1000;

	private static Logger log = Logger.getLogger(TestCaseForInstrumentingAndExecutingApplications.class.getCanonicalName());
	
	private static final MethodDescriptor METHOD_TO_EXECUTE_MC2008 =
		new MethodDescriptor(MonteCarlo.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");

	private static final MethodDescriptor METHOD_TO_EXECUTE_RD2008 =
		new MethodDescriptor(Random.class.getCanonicalName(), "public final synchronized void nextDoubles (double x[])");

	private static final String TEST_CLASS_CANONICAL_NAME_JFG = 
		JGFCastBench_MK.class.getCanonicalName();

	private static final String TEST_METHODSIG_JFG			= 
		"public void JGFrun_MK()";
	
	private static final String TEST_CLASS_CANONICAL_NAME_LI 	= 
		Linpack_MK.class.getCanonicalName();
	
	private static final String TEST_METHODSIG_LI 			= 
		"public void run_benchmark()";
	
	private static final String TEST_CLASS_CANONICAL_NAME_MC	= 
		SciMarkMonteCarlo_MK.class.getCanonicalName();
	
	private static final String TEST_METHODSIG_MC 			= 
		"public static final double integrateMK()";
	
//	private static final MethodDescriptor METHOD_TO_EXECUTE_Launch =
//		new MethodDescriptor(Launch.class.getCanonicalName(), "public static void main(java.lang.String[] args)");
	
//	private static final MethodDescriptor METHOD_TO_EXECUTE_SPEC2008_COMPRESS =
//		new MethodDescriptor(
//			spec.compress.Main.class.getCanonicalName(), 
//			"public static void main(java.lang.String args[])");

	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_LU2008  //TODO test
		= null; //TODO LU.class.getCanonicalName();
	private static final String TEST_METHODSIG_LU2008 			  //TODO test
		= "public int factor(double A[][], int pivot[])";
	
	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_MC2008  //TODO test
		= MonteCarlo.class.getCanonicalName();
	private static final String TEST_METHODSIG_MC2008 			  //TODO test
		= "public static final double integrate(int Num_samples)";
	
	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_RD2008  //TODO test
		= Random.class.getCanonicalName();
	
	private static final String TEST_METHODSIG_RD2008 			  //TODO test
		= "public final synchronized void nextDoubles (double x[])";
	
	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS  //TODO test
		= "spec.benchmarks.compress.Compressor";//.class.getCanonicalName();
	private static final String TEST_METHODSIG_SPEC2008_COMPRESS 			  //TODO test
		= "public void compress()";
	
	/**
	 * Generates the different parameters with which all tests are run.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.parameterSetup()
	 */
	@SuppressWarnings({ "unchecked"})
	@Parameters
	public static Collection parameterSetup() {
		return TestASMBytecodes.parameterSetup();
	}
	private InstrumentationParameters instrumentationParameters;
	
	private static final MethodDescriptor METHOD_TO_EXECUTE_SCIMARK = 
		new MethodDescriptor(SciMarkMonteCarlo_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");
	private static final MethodDescriptor METHOD_TO_EXECUTE_LINPACK = 
		new MethodDescriptor(Linpack_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");    

	private static final MethodDescriptor METHOD_TO_EXECUTE_JGF =
		new MethodDescriptor(JGFCastBench_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");
	private BytecodeCounter counter;    

	private CountingResultCollector resultColl;
	private String testClassName;
	private String testMethodSignature;
	private MethodDescriptor methodToExecute;
	private Object[] executionParameters;

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	public TestCaseForInstrumentingAndExecutingApplications(InstrumentationParameters params) {
		// create a BytecodeCounter
		this.instrumentationParameters = params;
	}

	/**
	 * Performs the actual counting by calling BytecodeCounter.count(...)
	 */
	private void count(){
		MethodDescriptor methDesc = new MethodDescriptor(this.testClassName, this.testMethodSignature);
		counter.setInstrumentationParams(this.instrumentationParameters);
//		log.fine("Method descriptor: "+methDesc);
		
		long start = System.nanoTime();
		log.fine("(NOT INITIALISED)" + this.counter.getInstrumentationParams().toString());
		this.counter.getInstrumentationParams().setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_INT);
		this.counter.instrument(methDesc);
		this.counter.execute(this.methodToExecute, 
				this.executionParameters);
		long stop = System.nanoTime();
		long counting = stop-start;
		
		log.fine(this.counter.getInstrumentationParams().toString());
		log.info(counting+    "ns to count (aka \t"+
				Math.round((double) counting/1000)+"us aka \t"+
				Math.round((double) counting/1000000)+"ms aka \t"+
				Math.round((double) counting/1000000000)+"s)");
		
		CountingResult[] finalResults = this.resultColl.retrieveAllCountingResultsAsArray(false);
		Assert.assertNotSame("Number of results must be != 0.", 0, finalResults.length);
		log.info(finalResults.length+" counting results found, logging them: ");
		for(CountingResult r : finalResults) {
			this.resultColl.logResult(r, true, true); //from Martin
		}
		// clear all collected results
		this.resultColl.clearResults();
	}
	
	/**
	 * Default constructor, see source
	 */
	public void init(){
		this.resultColl = CountingResultCollector.getInstance();
		this.counter = new BytecodeCounter();
		log.fine("Using class "+this.testClassName+" " +
				"(instrumented: "+this.testMethodSignature+", " +
				"called: "+ this.methodToExecute +").");
	}
	
	@Test
	public void testJFG() {
		init();
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_JFG;
		this.testMethodSignature 	= TEST_METHODSIG_JFG;
		this.methodToExecute		= METHOD_TO_EXECUTE_JGF;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		count();
	}
	
//	@Test TODO: this test is not defined properly; see classname constant
//	public void testLU2008() {
//		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_LU2008;
//		this.testMethodSignature 	= TEST_METHODSIG_LU2008;
//		this.methodToExecute		= null;
//		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
//		init();
//		count();
//	}
	
	@Test
	public void testLinpack() {
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_LI;
		this.testMethodSignature 	= TEST_METHODSIG_LI;
		this.methodToExecute		= METHOD_TO_EXECUTE_LINPACK;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		init();
		count();
	}
	

	@Test
	public void testMC2008() {
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_MC2008;
		this.testMethodSignature 	= TEST_METHODSIG_MC2008;
		this.methodToExecute		= METHOD_TO_EXECUTE_MC2008;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		init();
		count();
	}

	@Test
	public void testRD2008() {
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_RD2008;
		this.testMethodSignature 	= TEST_METHODSIG_RD2008;
		this.methodToExecute		= METHOD_TO_EXECUTE_RD2008;
		this.executionParameters	= new Object[] {new double[PROBLEM_SIZE_RD2008]};
		init();
		count();
	}
	
	
	@Test
	public void testScimark() {
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_MC;
		this.testMethodSignature 	= TEST_METHODSIG_MC;
		this.methodToExecute		= METHOD_TO_EXECUTE_SCIMARK;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		init();
		count();
	}
	
	@Test
	public void testSPEC2008_COMPRESS() {
		Assert.fail("SPEC2008_compress is not fully defined.");
		this.testClassName 			= TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS;
		this.testMethodSignature 	= TEST_METHODSIG_SPEC2008_COMPRESS;
//		this.methodToExecute		= METHOD_TO_EXECUTE_SPEC2008_COMPRESS;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		init();
		count();
	}
}
