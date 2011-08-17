package edu.kit.ipd.sdq.bysuite.evaluation.legacy;

import java.util.logging.Logger;

import SciMark.MonteCarlo;
import SciMark.Random;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.test.helpers.JGFCastBench_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.Linpack_MK;
import de.uka.ipd.sdq.ByCounter.test.helpers.SciMarkMonteCarlo_MK;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
/** Tests for bytecode instrumentation.
 * Demonstrates how to use the BytecodeCounter.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public class LegacyCountingStarter {
	
//	private static final int defaultProblemSize = 10000;

	private static final int USE_JGF 					= 0;
	private static final int USE_LINPACK 				= 1;
	private static final int USE_SCIMARK 				= 2;
	private static final int USE_LU2008 				= 3; //TODO test
	private static final int USE_MC2008 				= 4; //TODO test
	private static final int USE_RD2008 				= 5; //TODO test
	private static final int USE_SPEC2008_COMPRESS		= 6; //TODO test
	

	private static final MethodDescriptor METHOD_TO_EXECUTE = //null;//FIXME
		new MethodDescriptor(SciMarkMonteCarlo_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");
//		new MethodDescriptor(Linpack_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");
//		new MethodDescriptor(JGFCastBench_MK.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");
//		new MethodDescriptor(Launch.class.getCanonicalName(), "public static void main(java.lang.String[] args)");

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
		= "public final double integrate_MK(int Num_samples)";
	
	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_RD2008  //TODO test
		= Random.class.getCanonicalName();
	private static final String TEST_METHODSIG_RD2008 			  //TODO test
		= "public final double integrate_MK_inlined(int Num_samples)";    

	/**
	 * NEW
	 */
	private static final String TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS  //TODO test
		= "spec.benchmarks.compress.Compressor";//.class.getCanonicalName();
	private static final String TEST_METHODSIG_SPEC2008_COMPRESS 			  //TODO test
		= "public void compress()";    

	/** Tests application entry point.
	 * @param args No arguments needed or evaluated.
	 */
	public static void main(String[] args) {
		//MK TODO wozu war das? new Thread().start();//FIXME
		// early CountingResultCollector construction; initialize the singleton
		LegacyCountingStarter cs = new LegacyCountingStarter();
		cs.count();
	}
	private BytecodeCounter counter;
	private Logger log;
	private CountingResultCollector resultColl;
	private String testClassName;
	private String testMethodSignature;

	private int use;

	/**
	 * Default constructor, see source
	 */
	public LegacyCountingStarter(){
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		
		this.use = USE_SCIMARK;//USE_SPEC2008_COMPRESS; //TODO add command line interpretation...
		
		if(this.use == USE_JGF){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_JFG;
			this.testMethodSignature 	= TEST_METHODSIG_JFG;
		}else if(this.use == USE_LINPACK){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_LI;
			this.testMethodSignature 	= TEST_METHODSIG_LI;
		}else if(this.use == USE_SCIMARK){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_MC;
			this.testMethodSignature 	= TEST_METHODSIG_MC;
		}else if(this.use == USE_LU2008){ //TODO: test
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_LU2008;
			this.testMethodSignature 	= TEST_METHODSIG_LU2008;
		}else if(this.use == USE_MC2008){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_MC2008;
			this.testMethodSignature 	= TEST_METHODSIG_MC2008;
		}else if(this.use == USE_RD2008){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_RD2008;
			this.testMethodSignature 	= TEST_METHODSIG_RD2008;
		}else if(this.use == USE_SPEC2008_COMPRESS){
			this.testClassName 			= TEST_CLASS_CANONICAL_NAME_SPEC2008_COMPRESS;
			this.testMethodSignature 	= TEST_METHODSIG_SPEC2008_COMPRESS;
		}else{
			//TODO
		}
		this.resultColl = CountingResultCollector.getInstance();
		this.counter = new BytecodeCounter();
		this.log.fine("Using class "+this.testClassName+" " +
				"(instrumented: "+this.testMethodSignature+", " +
				"called: "+METHOD_TO_EXECUTE+").");
	}
	
	/**
	 * Performs the actual counting by calling BytecodeCounter.count(...)
	 */
	private void count(){
		MethodDescriptor methDesc = new MethodDescriptor(this.testClassName, this.testMethodSignature);
//		log.fine("Method descriptor: "+methDesc);
		
		long start = System.nanoTime();
		this.log.fine("(NOT INITIALISED)" + this.counter.getInstrumentationParams().toString());
		this.counter.instrument(methDesc);
		this.counter.execute(METHOD_TO_EXECUTE, 	//FIXME generalise 
				new Object[]{new String[]{}});/*no params for run_MK*/ /*new String[0] : for main(String args[])*//*problemSize*///TODO: document, generalise
		long stop = System.nanoTime();
		long counting = stop-start;
		
		this.log.fine(this.counter.getInstrumentationParams().toString());
		this.log.info(counting+    "ns to count (aka \t"+
				Math.round((double) counting/1000)+"us aka \t"+
				Math.round((double) counting/1000000)+"ms aka \t"+
				Math.round((double) counting/1000000000)+"s)");
		
		CountingResult[] finalResults = this.resultColl.retrieveAllCountingResultsAsArray(false);
		this.log.info(finalResults.length+" counting results found, logging them: ");
		for(CountingResult r : finalResults) {
			this.resultColl.logResult(r, true, true); //from Martin
		}
		// clear all collected results
		this.resultColl.clearResults();
	}
}
