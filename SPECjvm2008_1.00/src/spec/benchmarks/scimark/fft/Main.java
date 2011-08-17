/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.scimark.fft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spec.benchmarks.scimark.utils.kernel;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    
    /**
     * Run this in multi mode, next to each other.
     */
    public static String testType() {
        return MULTI;
    }
    
    static void runBenchmark() {
        //return new FFT().inst_main(args);
        // Loop a few times, to create some more work in each ops.
        for (int i = kernel.FFT_LOOPS; i > 0; i --) {//MK FFT_LOOPS is set to 1...
            FFT.main(i);
        }
    }
    
    public void harnessMain() {
        runBenchmark();
    }
    
    /**
     * MK parameterless constructor needed by ByCounter
     */
    public Main() {
        super();
    }

    /**
     * Constructor provided by SPEC
     * @param bmResult
     * @param threadId
     */
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    }
    
    public static void setupBenchmark() {
        kernel.init();
    }
    
	private static int _MK_numberOfRepetitions = 21;
    public static long _MK_val = 0;
    
    
    public static void main(String[] args) {
    	List<Long> MK_meas = new ArrayList<Long>();
    	double sum = 0D;
        for(int i=0; i<_MK_numberOfRepetitions ; i++){
        	_MK_val= System.nanoTime(); //MK
	        runSimple(Main.class, args);
	    	_MK_val = System.nanoTime()-_MK_val;
	    	MK_meas.add(_MK_val);
	    	sum += _MK_val;
	        System.err.println("MK: FFT main "+i+": "+_MK_val+" ns");
	    }
	    System.err.println("MK: FFT 1st from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    Collections.sort(MK_meas);
	    System.err.println("MK: FFT min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    System.err.println("MK: FFT med from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2));
	    System.err.println("MK: FFT avg from "+_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions);
	    System.err.println("MK: FFT max from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1));
    }
    
    
}
