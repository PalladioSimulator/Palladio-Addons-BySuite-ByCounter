package edu.kit.ipd.sdq.bysuite.evaluation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

import jgfutil.JGFInstrumentor;

import org.junit.Test;

import section2.JGFCryptBenchSizeA;

import crypt.IDEATest;
import crypt.JGFCryptBench;

import Linpack.LinpackEnhanced;
import Whetstone.Dhrystone;
import Whetstone.DhrystoneConstants;
import Whetstone.DhrystoneGlobalVariables;
import Whetstone.DhrystoneRecord_Type;
import Whetstone.Whetstone;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.helpers.JGFCastBench_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.SciMarkMonteCarlo_MK;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;


public class InstrumentSPECjvm2008andOthers extends InstrumentApplicationsGenerically implements InstrumentationConfigurable {
	
	private static InstrumentationAndCountingConfiguration byCounterConfiguration =
		InstrumentationAndCountingConfiguration.LongCountersBasicBlocksForcedInline;
	
	/**
	 * Methods which were instrumented, reported counts, and were force-inlined, are reflected in this 
	 * map by the number of reportings they have accomplished
	 */
	static SortedMap<String, Integer> forceInlinedCounts_CompilerCompiler = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_JFreeChartRegression = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_JGF_Section2_IdeaCrypt = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_CompilerSunflow = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Compress = new TreeMap<String, Integer>(); 	
	
	static SortedMap<String, Integer> forceInlinedCounts_CompressInlined = new TreeMap<String, Integer>();

	static SortedMap<String, Integer> forceInlinedCounts_Crypto_AES = new TreeMap<String, Integer>();
		
	static SortedMap<String, Integer> forceInlinedCounts_Crypto_AES_twoClasses = new TreeMap<String, Integer>();

	static SortedMap<String, Integer> forceInlinedCounts_Crypto_RSA = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Crypto_RSA_twoClasses = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Crypto_SignVerify = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Derby = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_HelloWorld = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Linpack = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_SpecJbb2005Partial = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_MPEGaudio = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_ScimarkFFT = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_ScimarkLU = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_ScimarkMC = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_ScimarkSOR = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_ScimarkSparse = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Serial = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Startup = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Whetstone = new TreeMap<String, Integer>();
	
	static SortedMap<String, Integer> forceInlinedCounts_Dhrystone = new TreeMap<String, Integer>();
	
	/**
	 * java.util.Logging logger - does not require libraries or configuration as for log4j
	 */
	private static Logger log = Logger.getLogger(InstrumentSPECjvm2008andOthers.class.getCanonicalName());
	
	/** The returned array contains the following values: 
	 * out_instrumentedAvgMeasurement,
	 * out_instrumentedMaxMeasurement,
	 * out_instrumentedMedMeasurement,
	 * out_instrumentedMinMeasurement,
	 * out_instrumentedNumberOfPerformedExecutionWarmups,
	 * out_uninstrumentedAvgMeasurement,
	 * out_uninstrumentedMaxMeasurement,
	 * out_uninstrumentedMedMeasurement,
	 * out_uninstrumentedMinMeasurement,
	 * out_uninstrumentedNumberOfPerformedExecutionWarmups
	 */
	static Long[] results_CompilerCompiler = new Long[]{};
	
	static Long[] results_JFreechartRegression = new Long[]{};
	
	static Long[] results_CompilerSunflow = new Long[]{};
	
	static Long[] results_Compress = new Long[]{};
	
	static Long[] results_CompressInlined = new Long[]{};
	
	static Long[] results_Crypto_AES = new Long[]{};
	
	static Long[] results_Crypto_AES_twoClasses = new Long[]{};
	
	static Long[] results_Crypto_RSA = new Long[]{};
	
	static Long[] results_Crypto_RSA_twoClasses = new Long[]{};
	
	static Long[] results_Crypto_SignVerify = new Long[]{};
	
	static Long[] results_Derby = new Long[]{};
	
	static Long[] results_HelloWorld = new Long[]{};
	
	static Long[] results_Linpack = new Long[]{};
	
	static Long[] results_SpecJbb2005Partial = new Long[]{};
	
	static Long[] results_MPEGaudio = new Long[]{};
	
	static Long[] results_ScimarkFFT = new Long[]{};
	
	static Long[] results_ScimarkLU = new Long[]{};
	
	static Long[] results_ScimarkMC = new Long[]{};
	
	static Long[] results_ScimarkSOR = new Long[]{};
	
	static Long[] results_ScimarkSparse = new Long[]{};
		
	static Long[] results_JGF_Section2_IdeaCrypt = new Long[]{};

	static Long[] results_Serial = new Long[]{};

	static Long[] results_Startup = new Long[]{};
	
	static Long[] results_Dhrystone = new Long[]{};
	
	static Long[] results_Whetstone = new Long[]{};
	
	public static void main(String[] args) {
		instrumentationAndCountingConfiguration = byCounterConfiguration ;
		
		InstrumentationParameters ip = InstrumentApplicationsGenerically.getNewInstrumentationParametersInstance();
		log.info("Instrumentation parameters (workload-independent, pre-instrumentation): "+ip);
		log.info("instrumentationAndCountingConfiguration: "+instrumentationAndCountingConfiguration);
		InstrumentSPECjvm2008andOthers ia = new InstrumentSPECjvm2008andOthers(ip);
		CountingResultCollector crc = CountingResultCollector.getInstance();

		if(runCompilerCompiler){
			results_CompilerCompiler = ia.testSPECjvm2008_CompilerCompiler_delegating();
			forceInlinedCounts_CompilerCompiler = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCompilerSunflow){
			results_CompilerSunflow = ia.testSPECjvm2008_CompilerSunflow_delegating();
			forceInlinedCounts_CompilerSunflow = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCompress){
			results_Compress = ia.testSPECjvm2008_Compress_delegating();
			forceInlinedCounts_Compress = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runJgfSection2_IdeaCrypt){
			results_JGF_Section2_IdeaCrypt = ia.test_JGF_Section2_IdeaCrypt_delegating();
			forceInlinedCounts_JGF_Section2_IdeaCrypt = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCompressInlined){
			results_CompressInlined = ia.testSPECjvm2008_Compress_inlined_delegating();
			forceInlinedCounts_CompressInlined = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCryptoAES){
			results_Crypto_AES = ia.testSPECjvm2008_Crypto_AES_delegating();
			//TOO late, restuls already flushed here...: crc.logResult(crc.getForcedInlining_CountingResult(), false, true, Level.WARNING);
			forceInlinedCounts_Crypto_AES = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCryptoAES_twoClasses){
			results_Crypto_AES_twoClasses = ia.testSPECjvm2008_Crypto_AES_delegating_twoClasses();
			forceInlinedCounts_Crypto_AES_twoClasses = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCryptoRSA){
			results_Crypto_RSA = ia.testSPECjvm2008_Crypto_RSA_delegating();
			forceInlinedCounts_Crypto_RSA = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCryptoRSA_twoClasses){
			results_Crypto_RSA_twoClasses = ia.testSPECjvm2008_Crypto_RSA_delegating_twoClasses();
			forceInlinedCounts_Crypto_RSA_twoClasses = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runCryptoSignVerify){
			results_Crypto_SignVerify = ia.testSPECjvm2008_Crypto_Signverify_delegating();
			forceInlinedCounts_Crypto_SignVerify = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runDerby){
			results_Derby = ia.testSPECjvm2008_Derby_delegating();
			forceInlinedCounts_Derby = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runHelloWorld){
			results_HelloWorld = ia.testSPECjvm2008_HelloWorld_delegating();
			forceInlinedCounts_HelloWorld = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runJfreechartRegression){
			results_JFreechartRegression = ia.testJFreechartRegression_delegating();
			forceInlinedCounts_JFreeChartRegression = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runLinpack){
			results_Linpack = ia.testLinpack_delegating();
			forceInlinedCounts_Linpack = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runSpecJbb2005Partial){
			results_SpecJbb2005Partial = ia.testSPECjbb2005create_random_a_string_delegating();
			forceInlinedCounts_SpecJbb2005Partial = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runMPEGaudio){
			results_MPEGaudio = ia.testSPECjvm2008_MPEGaudio_delegating();
			forceInlinedCounts_MPEGaudio = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runScimarkFFT){
			results_ScimarkFFT = ia.testSPECjvm2008_Scimark_FFT_delegating();
			forceInlinedCounts_ScimarkFFT = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runScimarkLU){
			results_ScimarkLU = ia.testSPECjvm2008_Scimark_LU_delegating();
			forceInlinedCounts_ScimarkLU = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runScimarkMC){
			results_ScimarkMC = ia.testSPECjvm2008_Scimark_MonteCarlo_delegating();
			forceInlinedCounts_ScimarkMC = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runScimarkSOR){
			results_ScimarkSOR = ia.testSPECjvm2008_Scimark_Sor_delegating();
			forceInlinedCounts_MPEGaudio = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runScimarkSparse){
			results_ScimarkSparse = ia.testSPECjvm2008_Scimark_Sparse_delegating();
			forceInlinedCounts_ScimarkSparse = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runSerial){
			results_Serial = ia.testSPECjvm2008_Serial_delegating();
			forceInlinedCounts_Serial = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runStartup){
			results_Startup = ia.testSPECjvm2008_Startup_delegating();
			forceInlinedCounts_Startup = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runWhetstone){
			results_Whetstone = ia.testWhetstone_delegating();
			forceInlinedCounts_Whetstone = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		if(runDhrystone){
			results_Dhrystone = ia.testDhrystone_delegating();
			forceInlinedCounts_Dhrystone = crc.getForcedInlining_OccurenceCountsReportingMethods();
		}
		

		//TODO printing of the the missing forceInlinedCounts_*s...
		if(runCompilerCompiler){
			log.info("Counts of force-inlined methods for CompilerCompiler workload: ");
			for(String key: forceInlinedCounts_CompilerCompiler.keySet()){
				System.out.println(key+" -> "+forceInlinedCounts_CompilerCompiler.get(key));
			}
		}
		if(runCompilerSunflow){
			log.info("Counts of force-inlined methods for CompilerSunflow workload: ");
			for(String key: forceInlinedCounts_CompilerSunflow.keySet()){
				System.out.println(key+" -> "+forceInlinedCounts_CompilerSunflow.get(key));
			}
		}
		if(runCompress){
			log.info("Counts of force-inlined methods for Compress workload: ");
			for(String key: forceInlinedCounts_Compress.keySet()){
				System.out.println(key+" -> "+forceInlinedCounts_Compress.get(key));
			}
		}
		if(runCompressInlined){
			log.info("Counts of force-inlined methods for CompressInlined workload: ");
			for(String key: forceInlinedCounts_CompressInlined.keySet()){
				System.out.println(key+" -> "+forceInlinedCounts_CompressInlined.get(key));
			}
		}
		//TODO printing of the the remaining forceInlinedCounts_*s...

		//TODO if we assume that exactly one benchmark is instrumented (--> use enum instead of booleans!), 
		//the print results can be moved into the "if" clauses above
		if(runCompilerCompiler){
			printResults(results_CompilerCompiler);
		}
		if(runCompilerSunflow){
			printResults(results_CompilerSunflow);
		}
		if(runCompress){
			printResults(results_Compress);
		}
		if(runCompressInlined){
			printResults(results_CompressInlined);
		}
		if(runCryptoAES){
			printResults(results_Crypto_AES);
		}
		if(runCryptoAES_twoClasses){
			printResults(results_Crypto_AES_twoClasses);
		}
		if(runCryptoRSA){
			printResults(results_Crypto_RSA);
		}
		if(runCryptoRSA_twoClasses){
			printResults(results_Crypto_RSA_twoClasses);
		}
		if(runCryptoSignVerify){
			printResults(results_Crypto_SignVerify);
		}
		if(runDerby){
			printResults(results_Derby);
		}
		if(runHelloWorld){
			printResults(results_HelloWorld);
		}
		if(runLinpack){
			printResults(results_Linpack);
		}
		if(runSpecJbb2005Partial){
			printResults(results_SpecJbb2005Partial);
		}
		if(runMPEGaudio){
			printResults(results_MPEGaudio);
		}
		if(runScimarkFFT){
			printResults(results_ScimarkFFT);
		}
		if(runScimarkLU){
			printResults(results_ScimarkLU);
		}
		if(runScimarkMC){
			printResults(results_ScimarkMC);
		}
		if(runScimarkSOR){
			printResults(results_ScimarkSOR);
		}
		if(runScimarkSparse){
			printResults(results_ScimarkSparse);
		}
		if(runSerial){
			printResults(results_Serial);
		}
		if(runStartup){
			printResults(results_Startup);
		}
	}
	
	public static final String nsToBestUnit(long ns/* TODO, int decimals*/){
//		NumberFormat
		if(ns<0){
			return "negative: "+ns+" ns";
		}else if(ns<1000){
			return ns+" ns";
		}else if(ns<1000000){
			return "ca. "+ns/1000+" us";
		}else if(ns<1000000000){
			return "ca. "+ns/1000000+" ms";
		}else{
			return "ca. "+ns/1000000000+" s";
		}
	}
	
	public InstrumentSPECjvm2008andOthers(InstrumentationParameters ip) {
		super(ip);
	}
	
	private void explainRequirementsOfTheCompiler() {
		log.warning("The compiler workloads require packaged sources which are located " +
				"in the redistributable_sources of SPECjvm2008. So to allow the compiler to " +
				"find the source files, execute ByCounter and/or instrumented classes with " +
				"the working directory being that of SPECjvm2008, not that of ByCounter");
	}
	
	@Test
	private void testJFG() {
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				JGFCastBench_MK.class.getCanonicalName(),//TODO replace by TEST_CLASSNAME_JFG or similar constant?
				TEST_METHODSIG_JFG));//JGF_runMK
		this.methodToExecute		= METHOD_TO_EXECUTE_JGF;//the main(String[]) method
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();//TODO merge mit reset()?
		d_executeInstrumentedAndCount(true, false, null,null,null);
	}

	/**
	 * @deprecated because not delegatinv
	 */
	@Test
	private void testLinpack() {//TODO test recursive instrumentation...
		//TODO I need a helper class that extracts all fully-qualified String names...
		//do we really need fully qualified Strings?
		this.a_resetInstrumentationInputs();
		this.methodsToInstrument.add(new MethodDescriptor(
				LinpackEnhanced.class.getCanonicalName(),
				this.TEST_METHODSIG_LINPACK));//run_benchmarka
		if(LINPACK_INSTRUMENT_ALL_BUT_MAIN ){
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double abs (double d)"));
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void daxpy( int n, double da, double dx[], int dx_off, int incx, double dy[], int dy_off, int incy)"));
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double ddot( int n, double dx[], int dx_off, int incx, double dy[], int dy_off, int incy)"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final int dgefa( double a[][], int lda, int n, int ipvt[])"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dgesl( double a[][], int lda, int n, int ipvt[], double b[], int job)"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dmxpy ( int n1, double y[], int n2, int ldm, double x[], double m[][])"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dscal( int n, double da, double dx[], int dx_off, int incx)"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double epslon (double x)"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final int idamax( int n, double dx[], int dx_off, int incx)"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double matgen (double a[][], int lda, int n, double b[])"));//run_benchmarka
			this.methodsToInstrument.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"double second()"));//run_benchmarka
			
		}
		this.methodToExecute		= METHOD_TO_EXECUTE_LINPACK;//main...
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();
		this.d_executeInstrumentedAndCount(true,false,null,null,null);
	}

	@Test
	private Long[] testLinpack_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		SortedSet<MethodDescriptor> in_methodsForInstrumentation = new TreeSet<MethodDescriptor>();
		
		in_methodsForInstrumentation.add(new MethodDescriptor(
				LinpackEnhanced.class.getCanonicalName(),
				TEST_METHODSIG_LINPACK));//public void run_benchmark()
		if(LINPACK_INSTRUMENT_ALL_BUT_MAIN ){
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double abs(double d)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void daxpy(int n, double da, double dx[], int dx_off, int incx, double dy[], int dy_off, int incy)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double ddot(int n, double dx[], int dx_off, int incx, double dy[], int dy_off, int incy)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final int dgefa(double a[][], int lda, int n, int ipvt[])"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dgesl(double a[][], int lda, int n, int ipvt[], double b[], int job)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dmxpy (int n1, double y[], int n2, int ldm, double x[], double m[][])"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final void dscal(int n, double da, double dx[], int dx_off, int incx)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double epslon (double x)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final int idamax(int n, double dx[], int dx_off, int incx)"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"final double matgen (double a[][], int lda, int n, double b[])"));
			in_methodsForInstrumentation.add(new MethodDescriptor(
					LinpackEnhanced.class.getCanonicalName(),
					"double second()"));
			//already covered above!
//			in_methodsForInstrumentation.add(new MethodDescriptor(
//					LinpackEnhanced.class.getCanonicalName(),
//					"double run_benchmark()"));
		}

		String in_methodForExecutionClassName = LinpackEnhanced.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation, 
				in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjbb2005create_random_a_string_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		SortedSet<MethodDescriptor> in_methodsForInstrumentation = new TreeSet<MethodDescriptor>();
		
		in_methodsForInstrumentation.add(new MethodDescriptor(
				SpecJbb2005PartialEvaluator.class.getCanonicalName(),
				TEST_METHODSIG_SPECJBB2005_1));

		in_methodsForInstrumentation.add(new MethodDescriptor(
				SpecJbb2005PartialEvaluator.class.getCanonicalName(),
				TEST_METHODSIG_SPECJBB2005_2));

		String in_methodForExecutionClassName = SpecJbb2005PartialEvaluator.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation, 
				in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testWhetstone_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		SortedSet<MethodDescriptor> in_methodsForInstrumentation = new TreeSet<MethodDescriptor>();
		
		in_classesForCompleteInstrumentation.add(Whetstone.class.getCanonicalName());

		String in_methodForExecutionClassName = Whetstone.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation, 
				in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testDhrystone_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		SortedSet<MethodDescriptor> in_methodsForInstrumentation = new TreeSet<MethodDescriptor>();
		
		in_classesForCompleteInstrumentation.add(Dhrystone.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(DhrystoneConstants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(DhrystoneGlobalVariables.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(DhrystoneRecord_Type.class.getCanonicalName());

		String in_methodForExecutionClassName = Dhrystone.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation, 
				in_methodForExecutionClassName);
	}
	
	/**
	 * The montecarlo test from SciMark in Spec2008 modified by MK in the 
	 * evaluation package of ByCounter.
	 */
	@Test
	private void testMC2008() {
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				SciMark.MonteCarlo.class.getCanonicalName(),
				TEST_METHODSIG_MC2008));
		this.methodToExecute		= METHOD_TO_EXECUTE_MC2008;
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();
		d_executeInstrumentedAndCount(true,false,null,null,null);
	}
	
	@Test
	private void testRD2008() {
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				SciMark.Random.class.getCanonicalName(),
				TEST_METHODSIG_RD2008));
		this.methodToExecute		= METHOD_TO_EXECUTE_RD2008;
		this.executionParameters	= new Object[] {new double[PROBLEM_SIZE_RD2008]};
		b_initCounterAndResultCollector();
		d_executeInstrumentedAndCount(true,false,null);
	}
	
	/**
	 * The montecarlo test from SciMark in Spec2008 modified by MK.
	 */
	@Test
	private void testScimarkMonteCarlo() {
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				SciMarkMonteCarlo_MK.class.getCanonicalName(),
				TEST_METHODSIG_SCIMARK_MONTECARLO));//TODO is this correct?
		this.methodToExecute		= METHOD_TO_EXECUTE_SCIMARK_MONTECARLO;
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();
		d_executeInstrumentedAndCount(true,false,null);
	}

	/**
	 * Instrumentation fails on the Mac
	 * @return
	 */
	@Test
	private Long[] testSPECjvm2008_CompilerCompiler_delegating() {
		warnIfOsX();
		explainRequirementsOfTheCompiler();
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		//1. 
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.Compiler.class.getCanonicalName());
		//2. 
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.MainBase.class.getCanonicalName());
//TODO?!		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.SpecFileManager.class.getCanonicalName());
		//4. 
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.Util.class.getCanonicalName());
		
		//5. 
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.compiler.Main.class.getCanonicalName());
		//6. 
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.sunflow.Main.class.getCanonicalName());

		//7.
		in_classesForCompleteInstrumentation.add(spec.harness.BenchmarkThread.class.getCanonicalName());
		//8.
		in_classesForCompleteInstrumentation.add(spec.harness.Context.class.getCanonicalName());
		//9.
		in_classesForCompleteInstrumentation.add(spec.harness.Launch.class.getCanonicalName());
		//10.
		in_classesForCompleteInstrumentation.add(spec.harness.SpecJVMBenchmarkBase.class.getCanonicalName());
		//11.
		in_classesForCompleteInstrumentation.add(spec.harness.StopBenchmarkException.class.getCanonicalName());
		//12.
		in_classesForCompleteInstrumentation.add(spec.harness.Util.class.getCanonicalName());
		//13. 
		in_classesForCompleteInstrumentation.add(spec.harness.results.BenchmarkResult.class.getCanonicalName());
		//14. 
		in_classesForCompleteInstrumentation.add(spec.harness.results.TestResult.class.getCanonicalName());
		//15. 
		in_classesForCompleteInstrumentation.add(spec.io.FileCache.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.compiler.compiler.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}

	/**
	 * Instrumentation fails on the Mac
	 * @return
	 */
	@Test
	private Long[] testSPECjvm2008_CompilerSunflow_delegating() {
		warnIfOsX();
		explainRequirementsOfTheCompiler();
		
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.Compiler.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.MainBase.class.getCanonicalName());
//TODO?!		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.SpecFileManager.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.Util.class.getCanonicalName());
		
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.compiler.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compiler.sunflow.Main.class.getCanonicalName());
		
		in_classesForCompleteInstrumentation.add(spec.harness.StopBenchmarkException.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.results.BenchmarkResult.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.compiler.sunflow.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	/**
	 * @deprecated because just one method is instrumented
	 */
	@Test
	private void testSPECjvm2008_Compress() {//TODO does this have to be public?
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS,
				TEST_METHODSIG_SPEC2008_COMPRESS));
		this.methodToExecute		= METHOD_TO_EXECUTE_SPEC2008_COMPRESS;
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();
//		c_ is missing
		d_executeInstrumentedAndCount(true,false,null,null,null);
	}
	
	@Test
	private Long[] testSPECjvm2008_Compress_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.Compress.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.CSVWriterAndAppenderForSPEC.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.GlobalCompressMeasurementsBlackboardForSPEC.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.Harness.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.MK_Starter.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.compress.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] test_JGF_Section2_IdeaCrypt_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(JGFCryptBench.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(IDEATest.class.getCanonicalName());//had to make this class public...
		in_classesForCompleteInstrumentation.add(JGFInstrumentor.class.getCanonicalName());//TODO there are several versions of this class in current projects
//		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.Harness.class.getCanonicalName());
//		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.Main.class.getCanonicalName());
//		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress.MK_Starter.class.getCanonicalName());

		String in_methodForExecutionClassName = JGFCryptBenchSizeA.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testJFreechartRegression_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(JfreechartRegression.class.getCanonicalName());

		String in_methodForExecutionClassName = JfreechartRegressionSingleRunStarter.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Compress_inlined_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.CodeTable.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Compress.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.CompBase.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Compressor.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Decompressor.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.DeStack.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Harness.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.HashTable.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.InputBuffer.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.OutputBuffer.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.Source.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.compress_inlined.SuffixTable.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.compress_inlined.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	/**
	 * @deprecated because superseded by the delegating variant
	 */
	@Test
	private void testSPECjvm2008_Crypto_AES() {//TODO refactor this into a generic method...
		a_resetInstrumentationInputs();		
		classesToInstrumentCompletely.add(spec.benchmarks.crypto.aes.Main.class.getCanonicalName());
		classesToInstrumentCompletely.add(spec.benchmarks.crypto.Util.class.getCanonicalName());
//		TODO instrument as well?
//		import spec.harness.Context;//really used: spec/harness/Context.getFileCache(), 
//		import spec.harness.SpecJVMBenchmarkBase;
//		import spec.harness.StopBenchmarkException;
//		import spec.harness.results.BenchmarkResult;
//		spec/io/FileCache: too, transitively...

		this.methodToExecute		= METHOD_TO_EXECUTE_SPEC2008_CRYPTO_AES;
		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY;
		b_initCounterAndResultCollector();
		long instrumentationDuration = c_instrument();
		log.info("Instrumentation took "+instrumentationDuration+" ns");
		
		log.info("Starting "+nrOfWarmups+" warmup runs");//TODO
		List<Long> warmupDurations = new ArrayList<Long>();
		int lastWarmupRun = -1;
		long warmupDurationsSum = 0L;
		long currDuration=0;
		long lastDuration=0;
		if(nrOfWarmups>0){
			int onePercent = nrOfWarmups/100;
			if(onePercent==0) onePercent=1;
			for(int i=0; i<nrOfWarmups ; i++){
				if((i+1) % onePercent==0){
					log.info((i+1)/onePercent+" percent completed ("+i+" counts)");
				}
				lastDuration = currDuration;
				currDuration = d_executeInstrumentedAndCount((true && i==0),false,null,null,null);
				if(currDuration<(lastDuration/2)){//significant decrease, stop warmup
					log.info("Significant decrease during count() warmup: from "+lastDuration+" to "+currDuration);
					lastWarmupRun = i;
					i = nrOfWarmups;
				}
				
				warmupDurations.add(currDuration);
				warmupDurationsSum += currDuration;
			}
			Collections.sort(warmupDurations);
			long avgWarmup = 0;
			if(lastWarmupRun>=0){
				avgWarmup = warmupDurationsSum/(lastWarmupRun+1);
			}else{
				avgWarmup = warmupDurationsSum/nrOfWarmups;
			}
			log.info(nrOfWarmups+" warmups completed: avg="+avgWarmup+", " +
					"min="+warmupDurations.get(0)+" ns, "+
					"med="+warmupDurations.get(warmupDurations.size()/2)+" ns, "+
					"max="+warmupDurations.get(warmupDurations.size()-1)+" ns"
					);
		}
		CountingResultCollector.getInstance().clearResults();
		long countingDuration = d_executeInstrumentedAndCount(true,false,null,null,null);
		log.info("Proper counting took "+countingDuration+" ns, aka "+
				nsToBestUnit(countingDuration)+
				" (rounded), after "+nrOfWarmups+" warmups");
	}
	
	@Test
	private Long[] testSPECjvm2008_Crypto_AES_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.aes.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.Util.class.getCanonicalName());
		
		in_classesForCompleteInstrumentation.add(spec.harness.BenchmarkThread.class.getCanonicalName());
//		in_classesForCompleteInstrumentation.add(spec.harness.CommandLineParser.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.Configuration.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.Context.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.Launch.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.SpecJVMBenchmarkBase.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.StopBenchmarkException.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.Util.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.results.BenchmarkResult.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.results.SuiteResult.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.results.TestResult.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.io.FileCache.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.crypto.aes.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Crypto_AES_delegating_twoClasses() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.aes.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.Util.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.crypto.aes.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Crypto_RSA_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.rsa.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.Util.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.Context.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.SpecJVMBenchmarkBase.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.harness.StopBenchmarkException.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.io.FileCache.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.crypto.rsa.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Crypto_RSA_delegating_twoClasses() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.rsa.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.Util.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.crypto.rsa.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Crypto_Signverify_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.signverify.Main.class.getCanonicalName());//refactor: used twice
		in_classesForCompleteInstrumentation.add(spec.benchmarks.crypto.Util.class.getCanonicalName());//refactor: used thrice
//		TODO instrument as well?
//		import spec.harness.Context;//really used: spec/harness/Context.getFileCache(), 
//		import spec.harness.SpecJVMBenchmarkBase;
//		import spec.harness.StopBenchmarkException;
//		import spec.harness.results.BenchmarkResult;
//		spec/io/FileCache: too, transitively...

		String in_methodForExecutionClassName = spec.benchmarks.crypto.signverify.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Derby_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		//1.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.CyclicReader.class.getCanonicalName());
		//2.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.DataReader.class.getCanonicalName());
		//3.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.DerbyHarness.class.getCanonicalName());
		//4.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.InitThread.class.getCanonicalName());//required to make the class public... TODO clarify in ByCounter
		//5.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.Main.class.getCanonicalName());
		//6.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.derby.Utils.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.derby.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_HelloWorld_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.helloworld.HelloWorld.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.helloworld.Main.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.helloworld.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
//	/**
//	 * See JProfiler results (in particular HotSpots without Autotune) to understand this choice. 
//	 * @deprecated because non-delegating
//	 */
//	@Test
//	private void testSPECjvm2008_MPEGaudio() {
//		a_resetInstrumentationInputs();
//		classesToInstrumentCompletely.add(javazoom.jl.decoder.Decoder.class.getCanonicalName());
//		classesToInstrumentCompletely.add("javazoom.jl.decoder.LayerIDecoder");//TODO completely but not recursively?
//		classesToInstrumentCompletely.add("javazoom.jl.decoder.LayerIIDecoder");
//		classesToInstrumentCompletely.add("javazoom.jl.decoder.LayerIIIDecoder");
//		classesToInstrumentCompletely.add("javazoom.jl.decoder.BitReserve");
////		the init method is too large, would make the class too large. classesToInstrumentCompletely.add("javazoom.jl.decoder.huffcodetab");
//		
////		counter: see init* method
//		this.executionParameters	= EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY; //NO_EXECUTION_PARAMETERS_STRING;
////		instrumentationParameters set by the constructor
//		methodsToInstrument.add(new MethodDescriptor(
//				javazoom.jl.decoder.huffcodetab.class.getCanonicalName(),
//				TEST_METHODSIG_SPEC2008_MPEGAUDIO_HUFFMAN));//decode
//		
//		methodsToInstrument.add(new MethodDescriptor(
//				spec.benchmarks.mpegaudio.Harness.class.getCanonicalName(),
//				TEST_METHODSIG_SPEC2008_MPEGAUDIO_1));//decode
//		
//		methodsToInstrument.add(new MethodDescriptor(
//				spec.benchmarks.mpegaudio.Harness.class.getCanonicalName(),
//				TEST_METHODSIG_SPEC2008_MPEGAUDIO_2));//updateCRC32
//		
//		this.methodToExecute		= METHOD_TO_EXECUTE_SPEC2008_MPEGAUDIO;
////		resultColl: see init* method
//		b_initCounterAndResultCollector();
//		d_executeInstrumentedAndCount(true,false,null,null,null);
//	}
	
	@Test
	private Long[] testSPECjvm2008_MPEGaudio_delegating() {
		//CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(false);
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		SortedSet<MethodDescriptor> methodsToInstrument_local = new TreeSet<MethodDescriptor>();
		
//		01. in_classesForCompleteInstrumentation.add(javazoom.jl.converter.Converter.class.getCanonicalName());//not used
//		02. in_classesForCompleteInstrumentation.add(javazoom.jl.converter.jlc.class.getCanonicalName());//not used
//		03. in_classesForCompleteInstrumentation.add(javazoom.jl.converter.RiffFile.class.getCanonicalName());//not used
//		04. in_classesForCompleteInstrumentation.add(javazoom.jl.converter.WaveFile.class.getCanonicalName());//not used
//		05. in_classesForCompleteInstrumentation.add(javazoom.jl.converter.WaveFileObuffer.class.getCanonicalName());//not used
		
//		06.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.BitReserve.class.getCanonicalName());//appears in counting results
//		07.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Bitstream.class.getCanonicalName());//dito
//		08.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.BitstreamErrors.class.getCanonicalName());
//		09.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.BitstreamException.class.getCanonicalName());
//		10.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Control.class.getCanonicalName());//dito
//		11.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Crc16.class.getCanonicalName());//dito
//		12.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Decoder.class.getCanonicalName());//dito; inner class Params does not appear, but is not used frequently
//		13.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.DecoderErrors.class.getCanonicalName());
//		14.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.DecoderException.class.getCanonicalName());
//		15.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Equalizer.class.getCanonicalName());//dito
//		16.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.FrameDecoder.class.getCanonicalName());//dito
//		17.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.gr_info_s.class.getCanonicalName());
//		18.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.gr_info_s.class.getCanonicalName());
//		19.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Header.class.getCanonicalName());//dito
//		20. huffman intentionally skipped, see below
//		TODO first, try to instrument the ENTIRE class and check whether the instrumented classes execute correctly (use big thread stack size). 
//		IF that does not work: 
//		TODO instrument constructor: private huffcodetab(String S,int XLEN, int YLEN, int LINBITS, int LINMAX, int REF,
//                int[] TABLE, int[] HLEN, int[][] VAL, int TREELEN) - or create simple short methods
		String TEST_METHODSIG_SPEC2008_MPEGAUDIO_HUFFMAN1 = "public static int huffman_decoder(javazoom.jl.decoder.huffcodetab h, int[] x, int[] y, int[] v, int[] w, javazoom.jl.decoder.BitReserve br)";
		methodsToInstrument_local.add(new MethodDescriptor(
				javazoom.jl.decoder.huffcodetab.class.getCanonicalName(),
				TEST_METHODSIG_SPEC2008_MPEGAUDIO_HUFFMAN1));//decode
		
		String TEST_METHODSIG_SPEC2008_MPEGAUDIO_HUFFMAN2 = "void inithuff()";//TODO instrument constructor, too!!!
		methodsToInstrument_local.add(new MethodDescriptor(
				javazoom.jl.decoder.huffcodetab.class.getCanonicalName(),
				TEST_METHODSIG_SPEC2008_MPEGAUDIO_HUFFMAN2));//decode
//		21. 
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.III_side_info_t.class.getCanonicalName());
//		22.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.InputStreamSource.class.getCanonicalName());
//		23.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.JavaLayerError.class.getCanonicalName());
//		24.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.JavaLayerErrors.class.getCanonicalName());
//		25.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.JavaLayerException.class.getCanonicalName());//dito
//		26.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.JavaLayerHook.class.getCanonicalName());//dito
//		27.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.JavaLayerUtils.class.getCanonicalName());
//		28.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.LayerIDecoder.class.getCanonicalName());
//		29.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.LayerIIDecoder.class.getCanonicalName());
//		30.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.LayerIIIDecoder.class.getCanonicalName());//TODO inner classes...
//		31.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Manager.class.getCanonicalName());
//		32.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Obuffer.class.getCanonicalName());
//		33.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.OutputChannels.class.getCanonicalName());
//		34.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Params.class.getCanonicalName());
//		35.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.SampleBuffer.class.getCanonicalName());
//		36.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.SBI.class.getCanonicalName());
//		37.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Sftable.class.getCanonicalName());
//		38. in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.Source.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.SynthesisFilter.class.getCanonicalName());
//		39.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.temporaire.class.getCanonicalName());
//		40.
		in_classesForCompleteInstrumentation.add(javazoom.jl.decoder.temporaire2.class.getCanonicalName());
		
//		41.
		String TEST_METHODSIG_SPEC2008_MPEGAUDIO_HARNESS_1 = "public long decode(java.lang.String name)";
		//TODO why is Harness not instrumented entirely? just to save time?
		methodsToInstrument_local.add(new MethodDescriptor(
				spec.benchmarks.mpegaudio.Harness.class.getCanonicalName(),
				TEST_METHODSIG_SPEC2008_MPEGAUDIO_HARNESS_1));//decode
		
		String TEST_METHODSIG_SPEC2008_MPEGAUDIO_HARNESS_2 = "private void updateCRC32(java.util.zip.CRC32 crc32, short[] buffer)";
		methodsToInstrument_local.add(new MethodDescriptor(
				spec.benchmarks.mpegaudio.Harness.class.getCanonicalName(),
				TEST_METHODSIG_SPEC2008_MPEGAUDIO_HARNESS_2));//updateCRC32
		
//		42.
		in_classesForCompleteInstrumentation.add(spec.benchmarks.mpegaudio.Main.class.getCanonicalName());
//		43.
		in_classesForCompleteInstrumentation.add(spec.harness.BenchmarkThread.class.getCanonicalName());
//		TODO ASM exception thrown here in_classesForCompleteInstrumentation.add(spec.harness.CommandLineParser.class.getCanonicalName());
//		44.
		in_classesForCompleteInstrumentation.add(spec.harness.Context.class.getCanonicalName());
//		45.
		in_classesForCompleteInstrumentation.add(spec.harness.Launch.class.getCanonicalName());
//		46.
		in_classesForCompleteInstrumentation.add(spec.harness.SpecJVMBenchmarkBase.class.getCanonicalName());
//		47.
		in_classesForCompleteInstrumentation.add(spec.harness.Util.class.getCanonicalName());
//		48.
		in_classesForCompleteInstrumentation.add(spec.harness.results.BenchmarkResult.class.getCanonicalName());
//		49.
		in_classesForCompleteInstrumentation.add(spec.io.FileCache.class.getCanonicalName());
				
		//execution
		
		String in_methodForExecutionClassName = spec.benchmarks.mpegaudio.Main.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				methodsToInstrument_local, 
				in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Scimark_FFT_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.fft.FFT.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.fft.Main.class.getCanonicalName());

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.fft.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Scimark_LU_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.lu.LU.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.lu.Main.class.getCanonicalName());

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.lu.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Scimark_MonteCarlo_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.monte_carlo.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.monte_carlo.MonteCarlo.class.getCanonicalName());

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.monte_carlo.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	/**
	 * The montecarlo test from SciMark in SPEGjvm2008.
	 * @deprecated because non-delegating
	 */
	@Test
	private void testSPECjvm2008_Scimark_MonteCarlo_unmodified() {
		a_resetInstrumentationInputs();
		methodsToInstrument.add(new MethodDescriptor(
				spec.benchmarks.scimark.monte_carlo.MonteCarlo.class.getCanonicalName(),
				TEST_METHODSIG_SCIMARK_MONTECARLO_INTEGRATE));
		this.methodToExecute		= METHOD_TO_EXECUTE_SCIMARK_MONTECARLO_UNMODIFIED;
		this.executionParameters	= NO_EXECUTION_PARAMETERS;
		b_initCounterAndResultCollector();
		d_executeInstrumentedAndCount(true,false,null,null,null);
	}
	
	@Test
	private Long[] testSPECjvm2008_Scimark_Sor_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sor.Main.class.getCanonicalName());//TODO remove invariants...
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sor.SOR.class.getCanonicalName());//TODO remove invariants

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.sor.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	/**
	 * @return
	 * @deprecated because uncompleted
	 */
	@Test
	private Long[] testSPECjvm2008_Scimark_Sor_delegating_invariantsAware() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sor.Main.class.getCanonicalName());//TODO remove invariants...
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sor.SOR.class.getCanonicalName());//TODO remove invariants

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.sor.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}
	
	@Test
	private Long[] testSPECjvm2008_Scimark_Sor_hotMethodOnly() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		
		SortedSet<MethodDescriptor> in_methodsForInstrumentation = new TreeSet<MethodDescriptor>();
//		in_methodsForInstrumentation.add(new MethodDescriptor(
//				spec.benchmarks.scimark.sor.SOR.class.getCanonicalName(),
//				"public final double execute(double omega, double G[][], int num_iterations)")
//		);
		in_methodsForInstrumentation.add(new MethodDescriptor(
				spec.benchmarks.scimark.utils.kernel.class.getCanonicalName(),
				"public static double[][] RandomizeMatrix(double[][] A, spec.benchmarks.scimark.utils.Random R)")
		);

		String in_methodForExecutionClassName = spec.benchmarks.scimark.sor.Main.class.getCanonicalName();
		return testGeneric_withMethods_defaultMainMethod(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation, 
				in_methodForExecutionClassName);
	}

	@Test
	private Long[] testSPECjvm2008_Scimark_Sparse_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sparse.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.sparse.SparseCompRow.class.getCanonicalName());

		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Constants.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.kernel.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Random.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.scimark.utils.Stopwatch.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.scimark.sparse.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}

	@Test
	private Long[] testSPECjvm2008_Serial_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.Main.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.Utils.class.getCanonicalName());

		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.Child.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.Handler.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.ProxiedClass.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestArray.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestArrayDouble.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestArrayList.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestBigInteger.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestByteArray.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestClassReferenceTest.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestClassWithSQLDateOnly.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestDomainObject.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestExceptionReference.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestExternalizable.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestHandler.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestHugeData.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestParent.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestPayload.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestProxy.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestReadResolve.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestSimple.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestWithBigDecimal.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.serial.data.TestWithFinalField.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.serial.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}

	@Test
	private Long[] testSPECjvm2008_Startup_delegating() {
		SortedSet<String> in_classesForCompleteInstrumentation = new TreeSet<String>();
		in_classesForCompleteInstrumentation.add(spec.benchmarks.startup.StartupBenchmarkOutput.class.getCanonicalName());
		in_classesForCompleteInstrumentation.add(spec.benchmarks.startup.Main.class.getCanonicalName());

		String in_methodForExecutionClassName = spec.benchmarks.startup.Main.class.getCanonicalName();
		return testGeneric_onlyClasses_defaultMainMethod(in_classesForCompleteInstrumentation, in_methodForExecutionClassName);
	}


	private void warnIfOsX() {
		if(System.getProperties().getProperty("os.name").toLowerCase().startsWith("mac")){
			log.warning("http://www.spec.org/jvm2008/docs/KnownIssues.html states that " +
					"on Mac OS X, Check and the two Compiler workloads will result in a failure, and " +
					"one has to prepend SPECJVM2008_HOME/lib/javac.jar on bootclasspath to " +
					"fix the issues, e.g. using \"java -Xbootclasspath/p:lib/javac.jar -jar SPECjvm2008.jar\". "+
					"In Eclipse, the javac.jar can be directly prepended in the Run Configuration");
      	}
	}

}
