package edu.kit.ipd.sdq.bysuite.evaluation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.uka.ipd.sdq.ByBen.analysis.ByBenchOutputFileParser;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingArtefactInformation;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollectorModeEnum;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import de.uka.ipd.sdq.ByCounter.utils.CountingResultCSVWriter;
import edu.kit.ipd.sdq.BySuite.benchmarking.SerializableOpcodeBenchmarkingResultsCollection;
import edu.kit.ipd.sdq.BySuite.benchmarking.exceptions.BenchmarkingResultNotInitialisedProperlyException;
import edu.kit.ipd.sdq.BySuite.benchmarking.exceptions.ParametersRequiredButNotSetException;
import edu.kit.ipd.sdq.bysuite.mappings.FullOpcodeMapper;

/**
 * This class has no main method. It's not clear how it is used: as a JUnit test?
 * TODO explain dependencies on existing evaluation classes...
 * TODO rather than instrumenting classes one-by-one inside every test* method: instrument all classes in one place; 
 * then just call the main methods and be sure that everything is instrumented..... 
 */
@RunWith(Parameterized.class)// not clear what this means, JUnit says "When a class is annotated with @RunWith or extends a class annotated with @RunWith, JUnit will invoke the class it references to run the tests in that class instead of the runner built into JUnit."
public class InstrumentApplicationsGenerically extends ClassAndMethodNames {	

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ImportantForLogging imp = new ImportantForLogging();
		ByBenchOutputFileParser bbofp = new ByBenchOutputFileParser();

		SerializableOpcodeBenchmarkingResultsCollection result = bbofp.readBenchmarkResults(ByBenchOutputFileParser.DEFAULT_RESULTS_MASTER_CSV_FILE_RELATIVE_PATH);
		SortedMap<String,SerializableOpcodeBenchmarkingResultsCollection> collected = bbofp.readCollectedBenchmarkingResultsFromDefaultDir();
//		InstrumentApplicationsGenerically me = new InstrumentApplicationsGenerically(instrumentationParameters);
//		d_innter_predict_for_passed_counting_result(r, result, null);
		

	}
	
	/**
	 * TODO add "forced inline", as recently implemented in CountingResultCollector...
	 * TODO "direct logging to HDD" not reflected here
	 * @see getInstrumentationParametersWrappedInArrayAsCollection and the 
	 * corresponding documentation of JUnit's Parameterized class... 
	 * @author michaelkuperberg
	 */
	enum InstrumentationAndCountingConfiguration {
		IntCounters,
		IntCountersForcedInline,
		LongCounters,
		LongCountersBasicBlocks,
		LongCountersBasicBlocksForcedInline,
		LongCountersForcedInline
	}

	public static final Integer DEFAULT_INSTRUMENTED_NUMBER_OF_MEASUREMENTS=1; //16;

	public static final Integer DEFAULT_INSTRUMENTED_NUMBER_OF_WARMUPS=0; //128;

	public static final Integer DEFAULT_UNINSTRUMENTED_NUMBER_OF_MEASUREMENTS=0;//should be 32
	
	public static final Integer DEFAULT_UNINSTRUMENTED_NUMBER_OF_WARMUPS=0;//should be 256
	
	/**
	 * Selects how to measure. TODO clarify that this setting is rather an instrumentation setting - not a measurements setting.
	 */
	protected static InstrumentationAndCountingConfiguration instrumentationAndCountingConfiguration = 
		InstrumentationAndCountingConfiguration.LongCountersForcedInline;
	
	protected static final boolean LINPACK_INSTRUMENT_ALL_BUT_MAIN = true;//TODO make a configurable parameter

	/**
	 * java.util.Logging logger - does not require libraries or configuration as for log4j
	 */
	private static Logger log = Logger.getLogger(InstrumentApplicationsGenerically.class.getCanonicalName());
	
	static boolean verbose = false;

	/**
	 * This method is called by the JUnit runner to generate parameters.
	 * Generates the different instrumentation parameters with which all tests 
	 * marked with the "@Test" annotation are run, 
	 * see the docs of JUnit's Parameterized class for explanations. 
	 * The returned Collection is required by Junit.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.getInstrumentationParametersWrappedInArrayAsCollection()
	 */
	@SuppressWarnings({ "rawtypes"})	// raw type Collection is necessary for JUnit
	@Parameters
	protected static Collection getInstrumentationParametersWrappedInArrayAsCollection() {
		InstrumentationParameters ipToUse = null;
		ipToUse = getNewInstrumentationParametersInstance();
		return Arrays.asList(new Object[][] { {ipToUse} });
	}

	/**
	 * Also includes forceInlining settings
	 * TODO add logging?
	 * @return
	 */
	protected static InstrumentationParameters getNewInstrumentationParametersInstance(
			) {
		InstrumentationParameters ipToUse = new InstrumentationParameters(
				new ArrayList<MethodDescriptor>(), 
				true, 
				true, 
				false, 
				false, 
				InstrumentationParameters.COUNTER_PRECISION_LONG);
//		ipToUse.setCounterPrecisionIsLong(counterPrecisionIsLong);
		ipToUse.setInstrumentRecursively(false);
//		ipToUse.setInstrumentRecursivelyMaxDepth(instrumentRecursivelyMaxDepth);
//		ipToUse.setMethodsToInstrument(methodsToInstrument);
//		ipToUse.setRangeBlocks(ranges);
//		ipToUse.setResultLogFileName(resultLogFileName);
//		ipToUse.setTraceAndIdentifyRequests(false);
		ipToUse.setUseArrayParameterRecording(false);
		ipToUse.setUseBasicBlocks(false);
		ipToUse.setUseHighRegistersForCounting(false);//TODO
		ipToUse.setUseResultCollector(true);
		ipToUse.setWriteClassesToDisk(false);
//		ipToUse.setWriteClassesToDiskDirectory(outputClassDirectory);
//		ipToUse.setIgnoredPackagePrefixes(ignoredPackagePrefixes);
		switch(instrumentationAndCountingConfiguration) {
			case IntCounters:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(false);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_INT);
				break;
			case IntCountersForcedInline:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(true);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_INT);
				break;
			case LongCounters:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(false);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_LONG);
				break;
			case LongCountersForcedInline:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(true);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_LONG);
				break;
			case LongCountersBasicBlocks:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(false);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_LONG);
				ipToUse.setUseBasicBlocks(true);
//				ipToUse.setUseBasicBlocksType(BasicBlockType.STANDARD_BASIC_BLOCKS);//The (only) other option would be user-specified range blocks
				break;
			case LongCountersBasicBlocksForcedInline:
				CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(true);
				ipToUse.setCounterPrecisionIsLong(InstrumentationParameters.COUNTER_PRECISION_LONG);
				ipToUse.setUseBasicBlocks(true);
//				ipToUse.setUseBasicBlocksType(BasicBlockType.STANDARD_BASIC_BLOCKS);//The (only) other option would be user-specified range blocks
				break; 
			
		}
		return ipToUse;
	}

	protected static void printResults(Long[] values) {
		Set<CountingArtefactInformation> set = CountingResultCollector.getInstance().getAllCountingResultsByArtefacts().keySet();
		for(CountingArtefactInformation cai : set){
			System.out.println("CountingResults for method "+cai.getMethodName());
		}
		if(DEFAULT_INSTRUMENTED_NUMBER_OF_MEASUREMENTS>0){
			System.out.println("out_instrumentedAvgMeasurement: "+values[0]);
			System.out.println("out_instrumentedMaxMeasurement: "+values[1]);
			System.out.println("out_instrumentedMedMeasurement: "+values[2]);
			System.out.println("out_instrumentedMinMeasurement: "+values[3]);
			System.out.println("out_instrumentedNumberOfPerformedExecutionWarmups: "+values[4]);
		}
		if(DEFAULT_UNINSTRUMENTED_NUMBER_OF_MEASUREMENTS>0){
			System.out.println("out_uninstrumentedAvgMeasurement: "+values[5]);
			System.out.println("out_uninstrumentedMaxMeasurement: "+values[6]);
			System.out.println("out_uninstrumentedMedMeasurement: "+values[7]);
			System.out.println("out_uninstrumentedMinMeasurement: "+values[8]);
			System.out.println("out_uninstrumentedNumberOfPerformedExecutionWarmups: "+values[9]);
		}
	}

	/**
	 * List of canonical class names for classes that will be instrumented 
	 * completely, i.e. including all methods.
	 */
	protected List<String> classesToInstrumentCompletely;
	
	/**
	 * ByCounter instance
	 */
	protected BytecodeCounter counter;

	protected Object[] executionParameters;

	protected InstrumentationParameters instrumentationParameters;

	/**
	 * TODO explain the relation to classesToInstrumentCompletely
	 */
	protected List<MethodDescriptor> methodsToInstrument;
	
	protected MethodDescriptor methodToExecute;
	
	protected int nrOfWarmups = DEFAULT_INSTRUMENTED_NUMBER_OF_WARMUPS;

//	protected static boolean useBasicBlocks = true;
	
	protected CountingResultCollector resultColl;
	

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * TODO this constructor is used NOWHERE in the workspace? Maybe because it is a JUnit test? Still, a main(String[] args) would be very nice...
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	protected InstrumentApplicationsGenerically(InstrumentationParameters params) {
		//TODO why are other fields not used?
//		this.classesToInstrumentCompletely//TODO
//		this.counter //TODO
//		this.executionParameters //TODO
		this.instrumentationParameters = params;
//		this.methodsToInstrument //TODO
//		this.methodToExecute //TODO
//		this.nrOfWarmups //TODO
//		this.resultColl //TODO
		
		File loggerConf = new File("."+File.separator+"logging.properties");
		if(loggerConf.exists()){
			try {
				LogManager.getLogManager().readConfiguration(new FileInputStream(loggerConf));
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.err.println(loggerConf.getAbsolutePath()+" does not exist");
		}
		String dotLevelProperty = LogManager.getLogManager().getProperty(".level");
		String consoleHandlerLevelProperty = LogManager.getLogManager().getProperty("java.util.logging.ConsoleHandler.level");
//		Level.ALL
		Level dotLevel = Level.parse(dotLevelProperty);
		Level consoleHandlerLevel = Level.parse(consoleHandlerLevelProperty);
		System.out.println("dotLevel: "+dotLevel+", console handler level: "+consoleHandlerLevel);
		
		log.setLevel(consoleHandlerLevel);//should be equal to or more abstract than the handlerlevel: 
		//otherwise, useless effort as the handler won't output anything, anyway...
		
		log.finest	("Test: Finest logging level");
		log.finer	("Test: Finer logging level");
		log.fine	("Test: Fine logging level");
		log.info	("Test: Info logging level");
		log.warning	("Test: Warning logging level");
		log.severe	("Test: Severe logging level");
	}

	/**
	 * Reset class state. //TODO methods to execute? execution parameters?
	 * TODO merge with init()?
	 */
	protected void a_resetInstrumentationInputs() {
		this.classesToInstrumentCompletely = new ArrayList<String>();//TODO why not clear() instead?
//		this.counter //TODO
		this.executionParameters = null;
//		this.instrumentationParameters //TODO re-set or re-initalize?
		this.methodsToInstrument = new ArrayList<MethodDescriptor>();//TODO why not clear() instead?
//		this.methodToExecute //TODO
//		this.nrOfWarmups //TODO
//		this.resultColl //TODO
	}
	

	/**
	 * Employs default constructors, see source
	 * TODO merge with reset()?
	 */
	protected void b_initCounterAndResultCollector(){
		this.counter = new BytecodeCounter();//which effects? memory?
		this.resultColl = CountingResultCollector.getInstance();
		this.resultColl.clearResults();
	}

	/**
	 * @return duration of instrumentation
	 */
	protected long c_instrument() {
		long start = System.nanoTime();
		counter.setInstrumentationParams(this.instrumentationParameters);//TODO log this?
		
		log.fine("(NOT INITIALISED)" + this.counter.getInstrumentationParams().toString());//TODO *what* is not initialised?

		this.counter.instrument(this.methodsToInstrument);
		
		for(int i = 0; i < this.classesToInstrumentCompletely.size(); i++) {
			this.counter.instrumentAllInClass(this.classesToInstrumentCompletely.get(i), new String[0]/*list of exceptions*/);
		}
		log.info("Instrumented "+this.methodsToInstrument.size()+" methods " +
				"by name, and "+0+" from "+classesToInstrumentCompletely.size()+
				" passed classes");
		return System.nanoTime()-start;
	}
	
	protected long d_executeInstrumentedAndCount(
			boolean verbose, 
			boolean saveCountingResultToFile, 
			String prefixForFileSaving){
			return d_executeInstrumentedAndCount(
					verbose, 
					saveCountingResultToFile, 
					prefixForFileSaving, 
					null,
					null);
	}
	/**
	 * Performs the actual instrumentation and counting using its instance of BytecodeCounter class
	 */
	protected long d_executeInstrumentedAndCount(
			boolean verbose, 
			boolean saveCountingResultToFile, 
			String prefixForFileSaving, 
			SortedSet<String> in_classesForCompleteInstrumentation, 
			SortedSet<MethodDescriptor> in_methodForInstrumentation 
			){
		long start = System.nanoTime();
		if(this.methodToExecute==null){
			log.severe("MK this.methodToExecute is null");
		}else{
			if(verbose) log.info("MK method to count (i.e. to execute): "+this.methodToExecute);
		}
		if(this.executionParameters==null){
			log.severe("MK this.executionParameters is null");
		}else{
			if(verbose) log.info("MK method execution parameters: "+ Arrays.toString(this.executionParameters)+", " +
					"this.executionParameters.length: "+this.executionParameters.length);
		}
		this.counter.execute(this.methodToExecute, 
				this.executionParameters);
		long stop = System.nanoTime();
		long countingDuration = stop-start;
		
		if(verbose) {
			log.fine(this.counter.getInstrumentationParams().toString());
		}
		if(CountingResultCollector.getInstance().isForceInliningIgnoringMethodWishes()){
			if(verbose){
				log.info("CountinResultCollector enforced immediate inlining, overriding method wishes");
			}
		}
		if(verbose){
			log.info(countingDuration+    "ns to count (aka \t"+
				Math.round((double) countingDuration/1000)+"us aka \t"+
				Math.round((double) countingDuration/1000000)+"ms aka \t"+
				Math.round((double) countingDuration/1000000000)+"s)");
		}
//		this.resultColl.getAllCountingResults_nonRecursively()
		CountingResult[] finalResults = this.resultColl.retrieveAllCountingResultsAsArray(false);//includes forcedInlining result, if any
		if(!CountingResultCollector.getInstance().getMode().equals(CountingResultCollectorModeEnum.DiscardAllIncomingCountingResults)){
			Assert.assertNotSame("Number of results must be != 0.", 0, finalResults.length);
		}
		log.info(finalResults.length+" counting results found, logging them (does not count towards the duration of counting!): ");
		if(saveCountingResultToFile){
			log.info("Saving CountringResult instances to files");
		}
		//TODO let the user print "enter" here 
//		if(verbose){
			for(CountingResult r : finalResults) {
				if(saveCountingResultToFile){
					String filename = r.getQualifyingMethodName()+"."+r.getMethodReportingTime()+".csv"; //+r.getMethodInvocationBeginning()
					CountingResultCSVWriter csvcrw = new CountingResultCSVWriter(true,';', true, true, "Michael", "csv", ".", true, true, false, false, false);
					csvcrw.writeResultToCSV(r, false, -1L);
				}
				this.resultColl.logResult(r, false, true, Level.WARNING); //from Martin
				this.d_inner_helper_predict(r);
			}
//		}	
		d_inner_helper_checkInstrumentationCoverage(
				in_classesForCompleteInstrumentation,
				in_methodForInstrumentation);

		// clear all collected results
		this.resultColl.clearResults();//duplicate...
//		TODO serialize as CSV?
		return countingDuration;
	}
	
	private void d_inner_helper_predict(CountingResult r) {
		if(r.getTotalCount(true)==0){
			log.info("Skipping empty counting result " +
					"(ID "+r.getID()+", " +
					"qualifyingName: "+r.getQualifyingMethodName()+")");
			return;
		}
		log.severe("PREDICTION : " +
				"Considering only instructions, only using default " +
				"benchmarking results on the local platform, if any");
		ByBenchOutputFileParser bbofp = new ByBenchOutputFileParser();

		SerializableOpcodeBenchmarkingResultsCollection result = bbofp.readBenchmarkResults(ByBenchOutputFileParser.DEFAULT_RESULTS_MASTER_CSV_FILE_RELATIVE_PATH);
		
		d_innter_predict_for_passed_counting_result(r, result, null);
		
		SortedMap<String,SerializableOpcodeBenchmarkingResultsCollection> collected = bbofp.readCollectedBenchmarkingResultsFromDefaultDir();
		if(collected!=null){
			if(collected.isEmpty()){
				log.severe("The collection of benchmarking results is empty");
			}else{
				Double[] results;
				StringBuffer sb = new StringBuffer();
				for(String key : collected.keySet()){
					results = d_innter_predict_for_passed_counting_result(r, collected.get(key), null);
					sb.append("\nPREDICTION: "+key+": "+results[0].longValue()+" with all, "+results[1].longValue()+" with pos only");
				}
				log.info(sb.toString());
			}
		}else{
			log.severe("The collection of benchmarking results is null");
		}
	}

	private Double[] d_innter_predict_for_passed_counting_result(CountingResult r,
			SerializableOpcodeBenchmarkingResultsCollection result, 
			SortedMap<Integer,Double> output_parameter_contributions
			) {
		if(result==null){
			log.severe("Benchmarking result null - cannot continue");
			return null;
		}else if(r==null){
			log.severe("Counting result null - cannot continue");
			return null;
		}else{
			log.warning("Starting...");
			Double prediction = 0.0D;
			Double predictionWithoutNegativeContributions = 0.0D;
			Long currCount = 0L;
			Double currBenchmarkingResult = 0.0D;
			Double currContribution = 0.0D;
			output_parameter_contributions = new TreeMap<Integer, Double>();
			StringBuffer sb = new StringBuffer();
//			sb.append("[");
			for(int i=0; i<FullOpcodeMapper.NUMBER_OF_EXISTING_OPCODES; i++){
				try {
					currCount = r.getOpcodeCount(i);
					currBenchmarkingResult = result.getBenchmarkingResult(i).getBenchmarkingValue();
					currContribution = currCount*currBenchmarkingResult;
					output_parameter_contributions.put(i, currContribution);
					sb.append(i+"->"+currContribution+"(="+currCount+"*"+currBenchmarkingResult+")\n");
					prediction+=currContribution;
					if(currContribution>0){
						predictionWithoutNegativeContributions+=currContribution;
					}
				} catch (ParametersRequiredButNotSetException e) {
					e.printStackTrace();
				} catch (BenchmarkingResultNotInitialisedProperlyException e) {
					e.printStackTrace();
				}
			}
//			sb.append("]");
			log.info("Prediction: "+prediction+" (as long: "+prediction.longValue()+")");
			log.info("Prediction w/o negative contributions: "+predictionWithoutNegativeContributions+" (as long: "+predictionWithoutNegativeContributions.longValue()+")");
//			log.fine("Contributions: \n"+sb.toString());
			return new Double[]{prediction, predictionWithoutNegativeContributions};
		}
	}

	/**
	 * TODO ensure double execution and execution of method with parameters other than String[]Êis prevented...
	 * @param b
	 * @return
	 */
	protected long d_executeUninstrumented(boolean b) {
		
		log.warning("d_executeUninstrumented BASED ON UNOPTIMIZED REFLECTION!!!");
		long start = System.nanoTime();
		//assumption: method is static...
		this.methodToExecute.getCanonicalClassName();
		try {
			Class<?> clazz = Class.forName(this.methodToExecute.getCanonicalClassName());
			for(Method m : clazz.getMethods()){
				if(m.getName().equals("main")){
					try {
						//TODO this won't work in spec.benchmarks.helloworld.Main or in spec.benchmarks.scimark.monte_carlo.Main(_MK)
						m.invoke(null, new Object[]{new String[]{}});
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return System.nanoTime()-start;
	}
	
	protected void requireUserInteraction() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("\n\n--------------Type any key to continue--------------");
		try {
			@SuppressWarnings("unused")
			int byteRead = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * TODO perform plausibility checks
	 * TODO did I already build something similar in evaluations package of ByCounter?
	 * @param in_classesForCompleteInstrumentation
	 * @param in_methodForInstrumentation
	 * @param in_methodForExecution
	 * @param in_parametersForExecution
	 * @param in_instrumentedNumberOfExecutionMeasurements
	 * @param in_instrumentedNumberOfExecutionWarmups
	 * @param in_uninstrumentedNumberOfExecutionMeasurements
	 * @param in_uninstrumentedNumberOfExecutionWarmups
	 * @param out_instrumentedAvgMeasurement
	 * @param out_instrumentedMaxMeasurement
	 * @param out_instrumentedMedMeasurement
	 * @param out_instrumentedMinMeasurement
	 * @param out_instrumentedNumberOfPerformedExecutionWarmups
	 * @param out_uninstrumentedAvgMeasurement
	 * @param out_uninstrumentedMaxMeasurement
	 * @param out_uninstrumentedMedMeasurement
	 * @param out_uninstrumentedMinMeasurement
	 * @param out_uninstrumentedNumberOfPerformedExecutionWarmups
	 * @return
	 */
	protected final Long[] testGeneric_fullParams(
			SortedSet<String> in_classesForCompleteInstrumentation, 
			SortedSet<MethodDescriptor> in_methodForInstrumentation, 
			
			MethodDescriptor in_methodForExecution,
			Object[] in_parametersForExecution,

			Integer in_instrumentedNumberOfExecutionMeasurements,//could be an array (of 2 or 4 Integers)
			Integer in_instrumentedNumberOfExecutionWarmups,
			Integer in_uninstrumentedNumberOfExecutionMeasurements,
			Integer in_uninstrumentedNumberOfExecutionWarmups, 	
			
			Long out_instrumentedAvgMeasurement, //could be an array (of 5 or 10 Longs)
			Long out_instrumentedMaxMeasurement, 
			Long out_instrumentedMedMeasurement, 
			Long out_instrumentedMinMeasurement,
			Long out_instrumentedNumberOfPerformedExecutionWarmups,
			Long out_uninstrumentedAvgMeasurement,
			Long out_uninstrumentedMaxMeasurement,
			Long out_uninstrumentedMedMeasurement,
			Long out_uninstrumentedMinMeasurement,				
			Long out_uninstrumentedNumberOfPerformedExecutionWarmups
			) {
		assert(in_classesForCompleteInstrumentation != null);
		assert(in_methodForInstrumentation != null);
		assert(!in_classesForCompleteInstrumentation.isEmpty()||!in_methodForInstrumentation.isEmpty());
		
		assert(in_methodForExecution!=null);
		assert(in_parametersForExecution!=null);
		
		assert(in_instrumentedNumberOfExecutionMeasurements>=0);
		assert(in_instrumentedNumberOfExecutionWarmups>=0);
		assert(in_uninstrumentedNumberOfExecutionMeasurements>=0);
		assert(in_uninstrumentedNumberOfExecutionWarmups>=0);
		
		a_resetInstrumentationInputs();		
		this.methodToExecute		= in_methodForExecution;
		this.executionParameters	= in_parametersForExecution;
		if(verbose) log.info("MK this.executionParameters: "+Arrays.toString(this.executionParameters));
		
		long helper_currDuration;
		List<Long> helper_durations = new ArrayList<Long>();
		long helper_durationsSum;
		int helper_i;
		long helper_lastDuration;
		int helper_lastWarmupRun;
		
		if(in_uninstrumentedNumberOfExecutionWarmups>0){
			helper_currDuration=0;
			helper_durations.clear();
			helper_durationsSum = 0L;
			helper_i=0;
			helper_lastDuration = 0;
			helper_lastWarmupRun = -1;
			
			log.warning("Starting "+in_uninstrumentedNumberOfExecutionWarmups+" uninstrumented warmup runs");
			for(; helper_i<in_uninstrumentedNumberOfExecutionWarmups; helper_i++){
	//			if((i+1) % onePercent==0){
	//				log.info((i+1)/onePercent+" percent completed ("+i+" counts)");
	//			}
				helper_lastDuration = helper_currDuration;
				helper_currDuration = d_executeUninstrumented(true && helper_i==0);
				if(helper_currDuration<(helper_lastDuration/2)){//significant decrease, stop warmup
					log.info("Significant decrease during uninstrumented warmup: from "+helper_lastDuration+" to "+helper_currDuration);
					helper_lastWarmupRun = helper_i;
					helper_i = in_uninstrumentedNumberOfExecutionWarmups;
				}
				
				helper_durations.add(helper_currDuration);
				helper_durationsSum += helper_currDuration;
			}
			Collections.sort(helper_durations);
			long avgWarmup = 0;
			if(helper_lastWarmupRun>=0){
				avgWarmup = helper_durationsSum/(helper_lastWarmupRun+1);
				nrOfWarmups=helper_lastWarmupRun+1;
			}else{
				avgWarmup = helper_durationsSum/in_uninstrumentedNumberOfExecutionWarmups;
				nrOfWarmups = in_uninstrumentedNumberOfExecutionWarmups;
			}
			log.warning(nrOfWarmups+" uninstrumented warmups completed: avg="+avgWarmup+", " +
					"min="+helper_durations.get(0)+" ns, "+
					"med="+helper_durations.get(helper_durations.size()/2)+" ns, "+
					"max="+helper_durations.get(helper_durations.size()-1)+" ns"
					);
		}
		
		if(in_uninstrumentedNumberOfExecutionMeasurements>0){
			helper_currDuration=0;
			helper_durations.clear();
			helper_durationsSum = 0L;
			helper_i=0;
			helper_lastDuration = 0;
			helper_lastWarmupRun = -1;
			
			log.warning("Starting "+in_uninstrumentedNumberOfExecutionMeasurements+" uninstrumented measurement runs");
			for(helper_i=0; helper_i<in_uninstrumentedNumberOfExecutionMeasurements ; helper_i++){
				helper_currDuration = d_executeUninstrumented(true && helper_i==0);
				helper_durations.add(helper_currDuration);
				helper_durationsSum += helper_currDuration;
			}
			Collections.sort(helper_durations);
			out_uninstrumentedAvgMeasurement = helper_durationsSum/in_uninstrumentedNumberOfExecutionMeasurements;
			out_uninstrumentedMinMeasurement = helper_durations.get(0);
			out_uninstrumentedMedMeasurement=helper_durations.get(helper_durations.size()/2);
			out_uninstrumentedMaxMeasurement = helper_durations.get(helper_durations.size()-1);
			helper_durations.clear();
			helper_durationsSum = 0L;
		}
		if(in_instrumentedNumberOfExecutionMeasurements>0 || in_instrumentedNumberOfExecutionWarmups>0){
			for(String classCanonicalName : in_classesForCompleteInstrumentation){//TODO check for nullness
				classesToInstrumentCompletely.add(classCanonicalName);
			}
			for(MethodDescriptor md : in_methodForInstrumentation){//TODO check for nullness
				methodsToInstrument.add(md);//TODO check if really considered...
			}
			
			b_initCounterAndResultCollector();
			long instrumentationDuration = c_instrument();
			log.warning("Instrumentation took "+instrumentationDuration+" ns");
		}
		
//		requireUserInteraction();
		
		if(in_instrumentedNumberOfExecutionWarmups>0){
			log.warning("Starting "+in_instrumentedNumberOfExecutionWarmups+" instrumented warmup runs");//TODO
			helper_currDuration=0;
			helper_durations.clear();
			helper_durationsSum = 0L;
			helper_i=0;
			helper_lastDuration = 0;
			helper_lastWarmupRun = -1;

			Double onePercent = new Double(in_instrumentedNumberOfExecutionWarmups)/100.0D;//TODO convert this to double - >100% possible otherwise
			if(onePercent.equals(0.0D)) onePercent=1.0D;
			for(helper_i=0; helper_i<in_instrumentedNumberOfExecutionWarmups ; helper_i++){
				if((helper_i+1) % onePercent<0.01){//TODO check and test this...
					if(verbose) log.info((helper_i+1)/onePercent+" percent completed ("+helper_i+" counts)");
				}
				helper_lastDuration = helper_currDuration;
				//the following method expects some fields to be set!
				helper_currDuration = d_executeInstrumentedAndCount(
						(true && helper_i==0), 
						true, //saveCountingResultToFile //TODO propagate the control var here!
						null, 
						in_classesForCompleteInstrumentation, 
						in_methodForInstrumentation);//TODO
				if(helper_currDuration<(helper_lastDuration/2)){//significant decrease, stop warmup
					log.severe("Significant decrease during instrumented warmup: from "+helper_lastDuration+" to "+helper_currDuration);
					helper_lastWarmupRun = helper_i;
					helper_i = in_instrumentedNumberOfExecutionWarmups;
				}
				
				helper_durations.add(helper_currDuration);
				helper_durationsSum += helper_currDuration;
				CountingResultCollector.getInstance().clearResults();
			}
			Collections.sort(helper_durations);
			long avgWarmup;
			if(helper_lastWarmupRun>=0){
				avgWarmup = helper_durationsSum/(helper_lastWarmupRun+1);
				nrOfWarmups = helper_lastWarmupRun+1;
			}else{
				avgWarmup = helper_durationsSum/in_instrumentedNumberOfExecutionWarmups;
				nrOfWarmups = in_instrumentedNumberOfExecutionWarmups;
			}
			log.warning(nrOfWarmups+" instrumented warmups completed: avg="+avgWarmup+", " +
					"min="+helper_durations.get(0)+" ns, "+
					"med="+helper_durations.get(helper_durations.size()/2)+" ns, "+
					"max="+helper_durations.get(helper_durations.size()-1)+" ns"
					);
		}
		
		if(in_instrumentedNumberOfExecutionMeasurements>0){
			helper_currDuration=0;
			helper_durations.clear();
			helper_durationsSum = 0L;
			helper_i=0;
			helper_lastDuration = 0;
			helper_lastWarmupRun = -1;
			log.warning("Starting "+in_instrumentedNumberOfExecutionMeasurements+" instrumented measurement runs");
			for(helper_i=0; helper_i<in_instrumentedNumberOfExecutionMeasurements ; helper_i++){
				helper_currDuration = d_executeInstrumentedAndCount(true, false, null, in_classesForCompleteInstrumentation, in_methodForInstrumentation);
				helper_durations.add(helper_currDuration);
				helper_durationsSum += helper_currDuration;
				CountingResultCollector.getInstance().clearResults();
			}
			Collections.sort(helper_durations);
			out_instrumentedAvgMeasurement = helper_durationsSum/in_instrumentedNumberOfExecutionMeasurements;
			out_instrumentedMinMeasurement = helper_durations.get(0);
			out_instrumentedMedMeasurement = helper_durations.get(helper_durations.size()/2);
			out_instrumentedMaxMeasurement = helper_durations.get(helper_durations.size()-1);
		}
//		SortedMap<String,Integer> forcedCounts = 
		
		helper_currDuration=0;
		helper_durations.clear();
		helper_durationsSum = 0L;
		helper_i=0;
		helper_lastDuration = 0;
		helper_lastWarmupRun = -1;
		return new Long[]{
				out_instrumentedAvgMeasurement,
				out_instrumentedMaxMeasurement,
				out_instrumentedMedMeasurement,
				out_instrumentedMinMeasurement,
				out_instrumentedNumberOfPerformedExecutionWarmups,
				out_uninstrumentedAvgMeasurement,
				out_uninstrumentedMaxMeasurement,
				out_uninstrumentedMedMeasurement,
				out_uninstrumentedMinMeasurement,
				out_uninstrumentedNumberOfPerformedExecutionWarmups
		};
	}

	private void d_inner_helper_checkInstrumentationCoverage(
			SortedSet<String> in_classesForCompleteInstrumentation,
			SortedSet<MethodDescriptor> in_methodForInstrumentation/*,TODO
			SortedSet<String> in_classesForPurposefulIgnoring,
			SortedSet<MethodDescriptor> in_methodsForPurposefulIgnoring*/) {
		List<CountingResult> crs = CountingResultCollector.getInstance().retrieveAllCountingResults_nonRecursively();//DOES contain forcedInlining and inlining
		log.info("Checking instrumentation coverage on "+crs.size()+" counting resutls");
		SortedMap<String,Long> methodCallCounts;
		
		for(CountingResult cr : crs){
			if(cr!=null){
				methodCallCounts = cr.getMethodCallCounts();
				log.fine("Analysing CountingResult "+cr.getQualifyingMethodName()+" with "+methodCallCounts.size()+" signatures of counted methods");
				for(String methodName : methodCallCounts.keySet()){
					boolean covered = false;
					if(methodName.startsWith("java/")
							|| methodName.startsWith("javax/")
							|| methodName.startsWith("com/apple/")
							|| methodName.startsWith("com/sun/")
							|| methodName.startsWith("sun/")
							|| methodName.startsWith("org/ietf/")
							|| methodName.startsWith("org/omg/")
							|| methodName.startsWith("org/w3c/")
							|| methodName.startsWith("org/xml/")){
						log.fine("method "+methodName+" belongs to the Java platform API");
						covered = true;
					}
					if(!covered && in_classesForCompleteInstrumentation!=null){
						for(String className : in_classesForCompleteInstrumentation){
							if(methodName.startsWith(className.replace(".", "/"))){
								log.fine("method "+methodName+" one of those to instrument");
								covered = true;
								break;
							}else{
								log.fine("Method "+methodName+" not covered by "+className);
							}
						}
					}
					if(!covered && in_methodForInstrumentation!=null){
						for(MethodDescriptor md : in_methodForInstrumentation){
							String mdName = md.getCanonicalMethodName();
							if(mdName.equals(methodName)){
								log.fine("method "+methodName+" one of those to instrument");
								covered = true;
								break;
							}else{
								log.fine("Method "+methodName+" not covered by "+mdName);
							}
						}
					}
					if(!covered){
						log.severe("Method "+methodName+" should be added to instrumentation");
					}
				}
			}else{
				log.severe("CountingResult returned by CountingResultCollector is null");
			}
		}
	}
	
	/**
	 * Delegates to full-featured testGeneric method
	 * @param in_classesForCompleteInstrumentation
	 * @param in_executionClassName
	 * @param in_executionMethodSignature
	 * @return
	 */
	protected final Long[] testGeneric_onlyClasses(
			SortedSet<String> in_classesForCompleteInstrumentation, 
			String in_executionClassName,
			String in_executionMethodSignature) {
		assert(in_classesForCompleteInstrumentation!=null);
		assert(!in_classesForCompleteInstrumentation.isEmpty());
		Long out_instrumentedAvgMeasurement=0L; 
		Long out_instrumentedMaxMeasurement=0L; 
		Long out_instrumentedMedMeasurement=0L; 
		Long out_instrumentedMinMeasurement=0L;//could be an array (of 4 or 8 Longs)
		Long out_instrumentedNumberOfPerformedWarmups = 0L;
		Long out_uninstrumentedAvgMeasurement = 0L;
		Long out_uninstrumentedMaxMeasurement=0L;
		Long out_uninstrumentedMedMeasurement=0L;
		Long out_uninstrumentedMinMeasurement=0L;
		Long out_uninstrumentedNumberOfPerformedWarmups = 0L;
			return testGeneric_fullParams(
				in_classesForCompleteInstrumentation, 
				new TreeSet<MethodDescriptor>(),//methods for instrumentation - empty
				new MethodDescriptor(in_executionClassName, in_executionMethodSignature), 
				EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY,//parameters for execution
				DEFAULT_INSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_INSTRUMENTED_NUMBER_OF_WARMUPS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_WARMUPS,
				out_instrumentedAvgMeasurement, 
				out_instrumentedMaxMeasurement, 
				out_instrumentedMedMeasurement, 
				out_instrumentedMinMeasurement, 
				out_instrumentedNumberOfPerformedWarmups,
				out_uninstrumentedAvgMeasurement, 
				out_uninstrumentedMaxMeasurement,
				out_uninstrumentedMedMeasurement, 
				out_uninstrumentedMinMeasurement,
				out_uninstrumentedNumberOfPerformedWarmups
				);
	}
	
	/**
	 * Delegates to full-featured testGeneric method
	 * @param in_classesForCompleteInstrumentation
	 * @param in_executionClassName
	 * @return
	 */
	protected final Long[] testGeneric_onlyClasses_defaultMainMethod(
			SortedSet<String> in_classesForCompleteInstrumentation, 
			String in_executionClassName){
		return testGeneric_onlyClasses(
				in_classesForCompleteInstrumentation, 
				in_executionClassName,
				DEFAULT_MAIN_METHOD);
	}
	
	/**
	 * Delegates to full-featured testGeneric method
	 * @param in_classesForCompleteInstrumentation
	 * @param in_methodsForInstrumentation
	 * @param in_executionClassName
	 * @param in_executionMethodSignature
	 * @return
	 */
	protected final Long[] testGeneric_withMethods(
			SortedSet<String> in_classesForCompleteInstrumentation, 
			SortedSet<MethodDescriptor> in_methodsForInstrumentation, 
			String in_executionClassName,
			String in_executionMethodSignature) {
		assert(in_classesForCompleteInstrumentation!=null);
		assert(!in_classesForCompleteInstrumentation.isEmpty());
		Long out_instrumentedAvgMeasurement=0L; 
		Long out_instrumentedMaxMeasurement=0L; 
		Long out_instrumentedMedMeasurement=0L; 
		Long out_instrumentedMinMeasurement=0L;//could be an array (of 4 or 8 Longs)
		Long out_instrumentedNumberOfPerformedWarmups = 0L;
		Long out_uninstrumentedAvgMeasurement = 0L;
		Long out_uninstrumentedMaxMeasurement=0L;
		Long out_uninstrumentedMedMeasurement=0L;
		Long out_uninstrumentedMinMeasurement=0L;
		Long out_uninstrumentedNumberOfPerformedWarmups = 0L;
			return testGeneric_fullParams(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation,//methods for instrumentation - empty
				new MethodDescriptor(in_executionClassName, in_executionMethodSignature), 
				EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY,//parameters for execution
				DEFAULT_INSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_INSTRUMENTED_NUMBER_OF_WARMUPS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_WARMUPS,
				out_instrumentedAvgMeasurement, 
				out_instrumentedMaxMeasurement, 
				out_instrumentedMedMeasurement, 
				out_instrumentedMinMeasurement, 
				out_instrumentedNumberOfPerformedWarmups,
				out_uninstrumentedAvgMeasurement, 
				out_uninstrumentedMaxMeasurement,
				out_uninstrumentedMedMeasurement, 
				out_uninstrumentedMinMeasurement,
				out_uninstrumentedNumberOfPerformedWarmups
				);
	}
	
	protected final Long[] testGeneric_withMethods_defaultMainMethod(
			SortedSet<String> in_classesForCompleteInstrumentation, 
			SortedSet<MethodDescriptor> in_methodsForInstrumentation, 
			String in_executionClassName) {
		assert(in_classesForCompleteInstrumentation!=null);
		assert(!in_classesForCompleteInstrumentation.isEmpty());
		Long out_instrumentedAvgMeasurement=0L; 
		Long out_instrumentedMaxMeasurement=0L; 
		Long out_instrumentedMedMeasurement=0L; 
		Long out_instrumentedMinMeasurement=0L;//could be an array (of 4 or 8 Longs)
		Long out_instrumentedNumberOfPerformedWarmups = 0L;
		Long out_uninstrumentedAvgMeasurement = 0L;
		Long out_uninstrumentedMaxMeasurement=0L;
		Long out_uninstrumentedMedMeasurement=0L;
		Long out_uninstrumentedMinMeasurement=0L;
		Long out_uninstrumentedNumberOfPerformedWarmups = 0L;
			return testGeneric_fullParams(
				in_classesForCompleteInstrumentation, 
				in_methodsForInstrumentation,//methods for instrumentation - empty
				new MethodDescriptor(in_executionClassName, DEFAULT_MAIN_METHOD), 
				EXECUTION_PARAMETERS_EMPTY_STRING_ARRAY,//parameters for execution
				DEFAULT_INSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_INSTRUMENTED_NUMBER_OF_WARMUPS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_MEASUREMENTS,
				DEFAULT_UNINSTRUMENTED_NUMBER_OF_WARMUPS,
				out_instrumentedAvgMeasurement, 
				out_instrumentedMaxMeasurement, 
				out_instrumentedMedMeasurement, 
				out_instrumentedMinMeasurement, 
				out_instrumentedNumberOfPerformedWarmups,
				out_uninstrumentedAvgMeasurement, 
				out_uninstrumentedMaxMeasurement,
				out_uninstrumentedMedMeasurement, 
				out_uninstrumentedMinMeasurement,
				out_uninstrumentedNumberOfPerformedWarmups
				);
	}
		
}
