package edu.kit.ipd.sdq.bysuite.evaluation;

import Linpack.LinpackEnhanced;
import de.uka.ipd.sdq.ByCounter.test.helpers.JGFCastBench_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.SciMarkMonteCarlo_MK;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

public class ClassAndMethodNames {

	protected static final String DEFAULT_MAIN_METHOD = "public static void main(java.lang.String args[])";
	
	protected static final Object[] EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY = new Object[]{new String[]{}};
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_JGF = new MethodDescriptor(
			JGFCastBench_MK.class.getCanonicalName(), 
			"public static void main(java.lang.String argv[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_LINPACK = new MethodDescriptor(
			LinpackEnhanced.class.getCanonicalName(), 
			"public static void main(java.lang.String argv[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_MC2008 = new MethodDescriptor(
			SciMark.MonteCarlo.class.getCanonicalName(), 
			"public static void main(java.lang.String argv[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_RD2008 = new MethodDescriptor(
			SciMark.Random.class.getCanonicalName(), 
			"public final synchronized void nextDoubles (double x[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_SCIMARK_MONTECARLO = new MethodDescriptor(
			SciMarkMonteCarlo_MK.class.getCanonicalName(), 
			"public static void main(java.lang.String argv[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_SCIMARK_MONTECARLO_UNMODIFIED = new MethodDescriptor(
			spec.benchmarks.scimark.monte_carlo.MonteCarlo.class.getCanonicalName(), 
			"public static void main()");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_SPEC2008_COMPRESS = new MethodDescriptor(
			spec.benchmarks.compress.Main.class.getCanonicalName(), 
			"public static void main(java.lang.String args[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_SPEC2008_CRYPTO_AES = new MethodDescriptor(
			spec.benchmarks.crypto.aes.Main.class.getCanonicalName(), 
			"public static void main(java.lang.String args[])");
	
	protected static final MethodDescriptor METHOD_TO_EXECUTE_SPEC2008_MPEGAUDIO = new MethodDescriptor(
			spec.benchmarks.mpegaudio.Harness.class.getCanonicalName(), 
			"public static void runOnce(java.lang.String args[])");
	
	protected static final Object[] NO_EXECUTION_PARAMETERS = new Object[]{};
	
	protected static final int PROBLEM_SIZE_RD2008 = 1000*1000;
	
	protected static final String TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS = "spec.benchmarks.compress.Compressor";
	/**
	 * TODO What is the relation between this and METHOD_TO_EXECUTE_JGF? Explain - is this the method to instrument?
	 * TODO Same question arises for other test subjects..
	 */
	protected static final String TEST_METHODSIG_JFG = "public void JGFrun_MK()";
	
	/**
	 * Linpack
	 */
	protected static final String TEST_METHODSIG_LINPACK = "public double run_benchmark()";
	
	/**
	 * SPECjbb2005 TODO problem here: the "Random" parameter was given without package...
	 */
	protected static final String TEST_METHODSIG_SPECJBB2005_1 = "public static int random(int low, int high, java.util.Random r)";
	
	/**
	 * SPECjbb2005
	 */
	protected static final String TEST_METHODSIG_SPECJBB2005_2 = "public static char[] create_random_a_string(int length_lo, int length_hi, short warehouseId)";
	
	protected static final String TEST_METHODSIG_LU2008 = "public int factor(double A[][], int pivot[])";
	protected static final String TEST_METHODSIG_MC2008 = "public static final double integrate(int Num_samples)";
	protected static final String TEST_METHODSIG_RD2008 = "public final synchronized void nextDoubles (double x[])";
	protected static final String TEST_METHODSIG_SCIMARK_MONTECARLO = "public static final double integrateMK()";
	protected static final String TEST_METHODSIG_SCIMARK_MONTECARLO_INTEGRATE = "public final double integrate(int numSamples)";
	protected static final String TEST_METHODSIG_SPEC2008_COMPRESS = "public void compress()";

	public ClassAndMethodNames() {
		super();
	}

}