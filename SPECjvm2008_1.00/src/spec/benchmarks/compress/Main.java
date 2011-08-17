/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.compress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    //same-package harness
	public static final Harness COMPRESS_HARNESS = new Harness();
    
	public static long _MK_val = 0;

	private static int _MK_numberOfRepetitions = 20;

	public static void main(String[] args) throws Exception {// TODO clarify:
																// how has added
																// this?
		List<Long> _MK_meas = new ArrayList<Long>();
		double sum = 0D;
		for (int i = 0; i < _MK_numberOfRepetitions; i++) {
			_MK_val = System.nanoTime(); // MK
			runSimple(Main.class, args);
			_MK_val = System.nanoTime() - _MK_val;
			_MK_meas.add(_MK_val);
			sum += _MK_val;
			System.err.println("MK "+i+": compress.Main.main: " + _MK_val
					+ " ns");
		}
		System.err.println("MK: compress.Main.main 1st from "
				+ _MK_numberOfRepetitions + " meas: " + _MK_meas.get(0));
		Collections.sort(_MK_meas);
		System.err.println("MK: compress.Main.main min from "
				+ _MK_numberOfRepetitions + " meas: " + _MK_meas.get(0));
		System.err.println("MK: compress.Main.main med from "
				+ _MK_numberOfRepetitions + " meas: "
				+ _MK_meas.get(_MK_meas.size() / 2));
		System.err.println("MK: compress.Main.main avg from "
				+ _MK_numberOfRepetitions + " meas: " + sum
				/ _MK_numberOfRepetitions);
		System.err.println("MK: compress.Main.main max from "
				+ _MK_numberOfRepetitions + " meas: "
				+ _MK_meas.get(_MK_meas.size() - 1));
	}
    
    /**
     * Called by SPECCompressCountingStarter (CBSE2008)
     * @param args
     * @param index
     * @param introduceMutation
     * @throws Exception
     */
    public static void MK_main_mine(
    		String[] args, 
    		int index, 
    		boolean introduceMutation 
    		) throws Exception {
    	System.out.println("MK calling Main.main_mine, file index "+index+
    			", introduceMutation: "+introduceMutation);
    	if(introduceMutation){
    		Compress.MK_setMutatation(true);
    	}
		Harness.MK_setFileIndex(index/*Integer.parseInt(args[0])*/);
		runSimple( Main.class, args );
    }
    
    @SuppressWarnings("static-access")
	public static void setupBenchmark() {
    	System.out.println("MK calling Main.setupBenchmark");
        COMPRESS_HARNESS.prepareBuffers();
    }
    
    /**
     * Run this in single mode.
     * No multi threading for this benchmark right now.
     */
    public static String testType() {
    	System.out.println("MK calling Main.testType");
        return MULTI;
    }
    
    public Main() {//MK TODO : document: added this constructor, but it has the same name as the above method...!
    	super();//implicit super-constructor is undefined, but must be called using super...
    	System.out.println("MK called Main.Main() (constructor)");
    }
    
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    	System.out.println("MK called Main.Main(BenchmarkResult bmResult, int threadId) (constructor)");
    }
    
    /* (non-Javadoc)
     * @see spec.harness.BenchmarkThread#harnessMain()
     */
    public void harnessMain() { //called by SpecJVMBenchmarkBase
    	System.out.println("MK calling Main.harnessMain()");
        runBenchmark();
    }
    
    public void Main() {//TODO : document: that this is a method, not a constructor - made by SPEC
    	System.out.println("MK calling Main.Main() (method)");
        runBenchmark();
    }
    
    public long runBenchmark() {
    	System.out.println("MK calling Main.runBenchmark()");
        return COMPRESS_HARNESS.inst_main(super.getThreadId());
        
    }
}
