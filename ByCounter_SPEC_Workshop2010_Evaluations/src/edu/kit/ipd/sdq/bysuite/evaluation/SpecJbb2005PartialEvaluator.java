package edu.kit.ipd.sdq.bysuite.evaluation;

//import java.lang.management.ManagementFactory;
//import java.lang.management.OperatingSystemMXBean;
//import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

//import spec.jbb.JBBmain;
//import sun.misc.Perf; TODO fix Apple issues with this!

/**
 * 1. select function to benchmark
 * Do this for both Xint and JIT
 * @author michaelkuperberg
 * TODO plot with JFreeChart into PNG
 *
 */

public class SpecJbb2005PartialEvaluator {
	
//	private static final Perf _MK_highResCounter;
//	private static long _MK_highResCounterFreq; 
//	private static double _MK_highResCounterTickInNs ;
//	private static long _MK_highResCounterTickInNsAsLong;
//	private static int _MK_highResCounterInvCostInTicks = 50;//Mac, approximated
	
	private static final char   alnum[]           
        = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	public static boolean benchmarkInsteadOfNormalSingleRun = true;
	
    private static Logger log = Logger.getLogger(SpecJbb2005PartialEvaluator.class.getCanonicalName());

    static int nrOfMeasurements = 2000;
    
    private static Random rd = new Random();
    public static int startingStringLength = 20;
    
    public static int stoppingStringLength = 20;
    
    // WRR: Array for Random streams (per-warehouse)
    private static Random[]     warehouse_random_stream;
    
    private static final int WAREHOUSE_RANDOM_STREAM_SIZE = 100;

    static{
//		_MK_highResCounter = Perf.getPerf(); //not visible: new Perf();
//		_MK_highResCounterFreq = _MK_highResCounter.highResFrequency();
//		_MK_highResCounterTickInNs = ((1000000000.0D)/((double) _MK_highResCounterFreq));
//		_MK_highResCounterTickInNsAsLong = new Double(_MK_highResCounterTickInNs).longValue();
    	warehouse_random_stream = new Random[WAREHOUSE_RANDOM_STREAM_SIZE];
    	for(int i=0; i<warehouse_random_stream.length; i++){
    		warehouse_random_stream[i]=new Random();
    	}
    }

    /**
     * Returns nextInt() results or first char of string - NOT a timing value...
     * @return
     */
    public static int _functionToBenchmark(){
//		return rd.nextInt();
		return create_random_a_string(startingStringLength, stoppingStringLength, (short) 1)[0];//TODO constants: extract
	}
	
    /**
     * Whether to call polymorphically, or whether to call the hard-wired method (Random.nextInt())
     * @param polymorphically (true: call method functionToBenchmark; false: 
     */
    public static void benchmark(boolean polymorphically, boolean printOnlyMedian, int a_nrOfMethodCallsInOneMeasurement){	
		long[] highResCounterNsForOneCall1aIterations = new long[nrOfMeasurements];
		long[] highResCounterNsForOneCall2aIterations = new long[nrOfMeasurements];
		long[] highResCounterNsForOneCall3aIterations = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall1aIterations = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall2aIterations = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall3aIterations = new long[nrOfMeasurements];
		
		long[] highResCounterNsForOneCall1aIterationsSorted = new long[nrOfMeasurements];
		long[] highResCounterNsForOneCall2aIterationsSorted = new long[nrOfMeasurements];
		long[] highResCounterNsForOneCall3aIterationsSorted = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall1aIterationsSorted = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall2aIterationsSorted = new long[nrOfMeasurements];
		long[] highResCounterNsForTwoCall3aIterationsSorted = new long[nrOfMeasurements];
		
		//TODO warmup?
		
//		int a_nrOfMethodCallsInOneMeasurement = 10240;//nr of methods calls in one measurement
		int timerIndexToUse = 0;//the the timing value is the first of the two values... 
		long sumOne1a = 0L;
		long sumOne2a = 0L;
		long sumOne3a = 0L;
		long sumTwo1a = 0L;
		long sumTwo2a = 0L;
		long sumTwo3a = 0L;
		
		
		if(polymorphically){
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall1aIterations[i]=benchmarkOnePolymorphically(1*a_nrOfMethodCallsInOneMeasurement)[timerIndexToUse];
				sumOne1a += highResCounterNsForOneCall1aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall2aIterations[i]=benchmarkOnePolymorphically(a_nrOfMethodCallsInOneMeasurement*2)[timerIndexToUse];
				sumOne2a += highResCounterNsForOneCall2aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall3aIterations[i]=benchmarkOnePolymorphically(a_nrOfMethodCallsInOneMeasurement*3)[timerIndexToUse];
				sumOne3a += highResCounterNsForOneCall3aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall1aIterations[i]=benchmarkTwoPolymorphically(1*a_nrOfMethodCallsInOneMeasurement)[timerIndexToUse];
				sumTwo1a += highResCounterNsForTwoCall1aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall2aIterations[i]=benchmarkTwoPolymorphically(a_nrOfMethodCallsInOneMeasurement*2)[timerIndexToUse];
				sumTwo2a += highResCounterNsForTwoCall2aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall3aIterations[i]=benchmarkTwoPolymorphically(a_nrOfMethodCallsInOneMeasurement*3)[timerIndexToUse];
				sumTwo3a += highResCounterNsForTwoCall3aIterations[i];
			}
		}else{
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall1aIterations[i]=benchmarkOneRandomNextInt(1*a_nrOfMethodCallsInOneMeasurement)[timerIndexToUse];
				sumOne1a += highResCounterNsForOneCall1aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall2aIterations[i]=benchmarkOneRandomNextInt(a_nrOfMethodCallsInOneMeasurement*2)[timerIndexToUse];
				sumOne2a += highResCounterNsForOneCall2aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForOneCall3aIterations[i]=benchmarkOneRandomNextInt(a_nrOfMethodCallsInOneMeasurement*3)[timerIndexToUse];
				sumOne3a += highResCounterNsForOneCall3aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall1aIterations[i]=benchmarkTwoRandomNextInt(1*a_nrOfMethodCallsInOneMeasurement)[timerIndexToUse];
				sumTwo1a += highResCounterNsForTwoCall1aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall2aIterations[i]=benchmarkTwoRandomNextInt(a_nrOfMethodCallsInOneMeasurement*2)[timerIndexToUse];
				sumTwo2a += highResCounterNsForTwoCall2aIterations[i];
			}
			for(int i=0; i<nrOfMeasurements; i++){
				highResCounterNsForTwoCall3aIterations[i]=benchmarkTwoRandomNextInt(a_nrOfMethodCallsInOneMeasurement*3)[timerIndexToUse];
				sumTwo3a += highResCounterNsForTwoCall3aIterations[i];
			}
		}
		
		System.arraycopy(highResCounterNsForOneCall1aIterations, 0, highResCounterNsForOneCall1aIterationsSorted, 0, highResCounterNsForOneCall1aIterations.length);
		System.arraycopy(highResCounterNsForOneCall2aIterations, 0, highResCounterNsForOneCall2aIterationsSorted, 0, highResCounterNsForOneCall2aIterations.length);
		System.arraycopy(highResCounterNsForOneCall3aIterations, 0, highResCounterNsForOneCall3aIterationsSorted, 0, highResCounterNsForOneCall3aIterations.length);
		System.arraycopy(highResCounterNsForTwoCall1aIterations, 0, highResCounterNsForTwoCall1aIterationsSorted, 0, highResCounterNsForTwoCall1aIterations.length);
		System.arraycopy(highResCounterNsForTwoCall2aIterations, 0, highResCounterNsForTwoCall2aIterationsSorted, 0, highResCounterNsForTwoCall2aIterations.length);
		System.arraycopy(highResCounterNsForTwoCall3aIterations, 0, highResCounterNsForTwoCall3aIterationsSorted, 0, highResCounterNsForTwoCall3aIterations.length);
		Arrays.sort(highResCounterNsForOneCall1aIterationsSorted);
		Arrays.sort(highResCounterNsForOneCall2aIterationsSorted);
		Arrays.sort(highResCounterNsForOneCall3aIterationsSorted);
		Arrays.sort(highResCounterNsForTwoCall1aIterationsSorted);
		Arrays.sort(highResCounterNsForTwoCall2aIterationsSorted);
		Arrays.sort(highResCounterNsForTwoCall3aIterationsSorted);

		long minOne1a = highResCounterNsForOneCall1aIterationsSorted[0];
		long minOne2a = highResCounterNsForOneCall2aIterationsSorted[0];
		long minOne3a = highResCounterNsForOneCall3aIterationsSorted[0];
		long minTwo1a = highResCounterNsForTwoCall1aIterationsSorted[0];
		long minTwo2a = highResCounterNsForTwoCall2aIterationsSorted[0];
		long minTwo3a = highResCounterNsForTwoCall3aIterationsSorted[0];
		
		long medOne1a = highResCounterNsForOneCall1aIterationsSorted[nrOfMeasurements/2];
		long medOne2a = highResCounterNsForOneCall2aIterationsSorted[nrOfMeasurements/2];
		long medOne3a = highResCounterNsForOneCall3aIterationsSorted[nrOfMeasurements/2];
		long medTwo1a = highResCounterNsForTwoCall1aIterationsSorted[nrOfMeasurements/2];
		long medTwo2a = highResCounterNsForTwoCall2aIterationsSorted[nrOfMeasurements/2];
		long medTwo3a = highResCounterNsForTwoCall3aIterationsSorted[nrOfMeasurements/2];
		
		long avgOne1a = sumOne1a/nrOfMeasurements;
		long avgOne2a = sumOne2a/nrOfMeasurements;
		long avgOne3a = sumOne3a/nrOfMeasurements;
		long avgTwo1a = sumTwo1a/nrOfMeasurements;
		long avgTwo2a = sumTwo2a/nrOfMeasurements;
		long avgTwo3a = sumTwo3a/nrOfMeasurements;
		
		long maxOne1a = highResCounterNsForOneCall1aIterationsSorted[nrOfMeasurements-1];
		long maxOne2a = highResCounterNsForOneCall2aIterationsSorted[nrOfMeasurements-1];
		long maxOne3a = highResCounterNsForOneCall3aIterationsSorted[nrOfMeasurements-1];
		long maxTwo1a = highResCounterNsForTwoCall1aIterationsSorted[nrOfMeasurements-1];
		long maxTwo2a = highResCounterNsForTwoCall2aIterationsSorted[nrOfMeasurements-1];
		long maxTwo3a = highResCounterNsForTwoCall3aIterationsSorted[nrOfMeasurements-1];
		
		System.out.println("");
		System.out.println("Median result to take [ns]: "+(medTwo3a/(3*a_nrOfMethodCallsInOneMeasurement)-medOne3a/(3*a_nrOfMethodCallsInOneMeasurement))+"\n");
		System.out.println("");
		System.out.println("Median per one invocation "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medOne1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medOne1a+")");
		System.out.println("Median per one invocation "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medOne2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medOne2a+")");
		System.out.println("Median per one invocation "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medOne3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medOne3a+")");
		System.out.println("Median per two invocations "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medTwo1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medTwo1a+")");
		System.out.println("Median per two invocations "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medTwo2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medTwo2a+")");
		System.out.println("Median per two invocations "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+medTwo3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+medTwo3a+")");
		if(!printOnlyMedian){
			System.out.println("");
			System.out.println("\tMin per one invocation "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minOne1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minOne1a+")");
			System.out.println("\tMin per one invocation "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minOne2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minOne2a+")");
			System.out.println("\tMin per one invocation "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minOne3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minOne3a+")");
			System.out.println("\tMin per two invocations "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minTwo1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minTwo1a+")");
			System.out.println("\tMin per two invocations "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minTwo2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minTwo2a+")");
			System.out.println("\tMin per two invocations "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+minTwo3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+minTwo3a+")");
			System.out.println("");
			System.out.println("\tAvg per one invocation "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgOne1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgOne1a+")");
			System.out.println("\tAvg per one invocation "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgOne2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgOne2a+")");
			System.out.println("\tAvg per one invocation "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgOne3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgOne3a+")");
			System.out.println("\tAvg per two invocations "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgTwo1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgTwo1a+")");
			System.out.println("\tAvg per two invocations "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgTwo2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgTwo2a+")");
			System.out.println("\tAvg per two invocations "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+avgTwo3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+avgTwo3a+")");
			System.out.println("");
			System.out.println("\tMax per one invocation "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxOne1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxOne1a+")");
			System.out.println("\tMax per one invocation "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxOne2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxOne2a+")");
			System.out.println("\tMax per one invocation "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxOne3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxOne3a+")");
			System.out.println("\tMax per two invocations "+(1*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxTwo1a/(1*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxTwo1a+")");
			System.out.println("\tMax per two invocations "+(2*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxTwo2a/(2*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxTwo2a+")");
			System.out.println("\tMax per two invocations "+(3*a_nrOfMethodCallsInOneMeasurement)+" iterations: "+maxTwo3a/(3*a_nrOfMethodCallsInOneMeasurement)+" (raw: "+maxTwo3a+")");
		}
	}
	
	/**
	 * TODO call with different nrOfIterations to subtract timer costs...
	 * @param nrOfIterations
	 * @return
	 */
	public static long[] benchmarkOnePolymorphically(int nrOfIterations){
		int resToPrint = 0;
		long[] start = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		for(int i=0; i<nrOfIterations; i++){
			resToPrint += _functionToBenchmark();
		}
		long[] finish = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		long[] diff = new long[]{finish[0]-start[0]};//JBBmain.computeDistance(start, finish);

		long[] ret= new long[start.length+1];//i.e. 2 elements
//		System.out.print(resToPrint);
		for(int i=0; i<start.length; i++){
			ret[i] = diff[i];
		}
		ret[ret.length-1]=resToPrint;
		return ret;
	}
	
	/**
	 * TODO call with different nrOfIterations to subtract timer costs...
	 * @param nrOfIterations
	 * @return
	 */
	public static long[] benchmarkOneRandomNextInt(int nrOfIterations){
		int resToPrint = 0;
		long[] start = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		for(int i=0; i<nrOfIterations; i++){
			resToPrint += rd.nextInt();
		}
		long[] finish = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		long[] diff = new long[]{finish[0]-start[0]};//JBBmain.computeDistance(start, finish);
		long[] ret= new long[start.length+1];
		for(int i=0; i<start.length; i++){
			ret[i] = diff[i];
		}
		ret[ret.length-1]=resToPrint;
		return ret;
	}
	
	/**
	 * TODO call with different nrOfIterations to subtract timer costs...
	 * @param nrOfIterations
	 * @return
	 */
	public static long[] benchmarkTwoPolymorphically(int nrOfIterations){
		int resToPrint = 0;
		long[] start = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		for(int i=0; i<nrOfIterations; i++){
			resToPrint += _functionToBenchmark()+_functionToBenchmark();
		}
		long[] finish = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		long[] diff = new long[]{finish[0]-start[0]};//JBBmain.computeDistance(start, finish);
		long[] ret= new long[start.length+1];
		for(int i=0; i<start.length; i++){
			ret[i] = diff[i];
		}
		ret[ret.length-1]=resToPrint;
		return ret;
	}
	
	/**
	 * TODO call with different nrOfIterations to subtract timer costs...
	 * @param nrOfIterations
	 * @return
	 */
	public static long[] benchmarkTwoRandomNextInt(int nrOfIterations){
		int resToPrint = 0;
		long[] start = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		for(int i=0; i<nrOfIterations; i++){
			resToPrint += rd.nextInt() + rd.nextInt();
		}
		long[] finish = new long[]{System.nanoTime()};//JBBmain._MK_getAllTimerValuesInNs();
		long[] diff = new long[]{finish[0]-start[0]};//JBBmain.computeDistance(start, finish);
		long[] ret= new long[start.length+1];
		for(int i=0; i<start.length; i++){
			ret[i] = diff[i];
		}
		ret[ret.length-1]=resToPrint;
		return ret;
	}
	
	// WRR: Takes warehouseId as parameter.//_MK to evaluate
    /**
     * @param length_lo
     * @param length_hi
     * @param warehouseId
     * @return the temp character array
     */
    public static char[] create_random_a_string(int length_lo, int length_hi, short warehouseId) {
        int i, actual_length, aRandInt;
        Random r = warehouse_random_stream[warehouseId];//_MK usage of parameter warehouseId
        actual_length = random(length_lo, length_hi, r);//_MK usage of r
        char[] temp = new char[actual_length];
        for (i = 0; i < actual_length; i++) {
            aRandInt = random(0, 61, r);//_MK usage of r
            if (aRandInt > 61)
                aRandInt = 61;
            temp[i] = alnum[aRandInt];
        }
        return temp;
    }
	
	public static void main(String[] args) {
		nrOfMeasurements = 256;//-XX:+PrintCompilation
		log.info("\n"+
				"startingStringLength="+startingStringLength+", \n"+
				"stoppingStringLength="+stoppingStringLength+".");
		if(benchmarkInsteadOfNormalSingleRun){
			log.info("Logging polymorphically, i.e. create_random_a_string; "+
					"nrOfMeasurements="+nrOfMeasurements);
			long start = System.nanoTime();
			benchmark(true/*polymorphically*/, true/*printOnlyMedian*/, 10240);
			log.info("Total duration: "+(System.nanoTime()-start)+" ns");
		}else{
			create_random_a_string(startingStringLength, stoppingStringLength, (short) 1);
		}
		//		nrOfMeasurements = 1024;
//		log.info("Logging statically, i.e. Random.nextInt");
//		benchmark(false, true);
	}
	
	// WRR: Takes a Random stream as input.//_MK to evaluate
    public static int random(int low, int high, Random r) {
        return ((r.nextInt() & 0x7fffffff) % (high - low + 1)) + low;
    }
}
