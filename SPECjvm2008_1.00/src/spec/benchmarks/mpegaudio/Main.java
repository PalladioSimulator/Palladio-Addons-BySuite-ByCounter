/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */
package spec.benchmarks.mpegaudio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import spec.harness.Context;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
	
    public static int _MK_songIndexStarting  =  0;
    public static int _MK_songIndexEndingIncl = 5;
	
    private static Logger log = Logger.getLogger(Main.class.getCanonicalName());
    
    public static final int DEFAULT_NUMBER_OF_BENCHMARK_RUNS = 200;
    
    public static final int FRAMES_LIMIT = 999999999;//MK increased to let the results match...
    
    public static final int _MK_DEFAULT_INCREASE_STEP = Main.TRACKS_NUMBER;
    
	/**
	 * Set to 1 for instrumentation and prediction, to 10 for Xint, to 100 for JIT
	 */
	private static int _MK_numberOfRepetitions = 21;
    
    public static boolean _MK_useInnerInsertedMeasurements = false;
    
    /**
     * Very important - influences performance behaviour
     */
    public static boolean _MK_useOuterInsertedMeasurements = false;
    
    public static long _MK_durationRunSimple = 0;

	public static boolean _MK_verbose = false;//TODO unused

	public static final String prefix = Context.getSpecBasePath() + "/resources/mpegaudio/input/";

	static final int TRACKS_NUMBER = 6;

	public static boolean _MK_run_single_file_as_if_instrumented = true;

	public static void main(String[] args) {
		if(_MK_verbose) log.info("MK:  MK_numberOfRepetitions: "+_MK_numberOfRepetitions); //+", TRACKS_NUMBER: "+TRACKS_NUMBER);
		if(_MK_verbose) log.info("MK:  MK_useOuterInsertedMeasurements: "+_MK_useOuterInsertedMeasurements); 
		if(_MK_verbose) log.info("MK:  MK_run_single_file_as_if_instrumented: "+_MK_run_single_file_as_if_instrumented); 
    	
		List<Long> MK_meas = new ArrayList<Long>();
    	double sum = 0D;
        for(int i=0; i<_MK_numberOfRepetitions ; i++){
        	_MK_durationRunSimple= System.nanoTime(); //MK
        	//TODO think/analyse ByCounter output: what has to be instrumented?
        	runSimple(Main.class, args);//void means this is single-threaded, single-instance (?)
		   	_MK_durationRunSimple = System.nanoTime()-_MK_durationRunSimple;
			MK_meas.add(_MK_durationRunSimple);
			sum += _MK_durationRunSimple;
				log.info("MK: repetition "+(i+1)+" out of "+_MK_numberOfRepetitions+": \n"+
		    		"duration in ns of runSimple(..)"+_MK_durationRunSimple+" ns\n\n");
        }
		log.info("\n" +
				"MK: MPEGaudio 1s from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
		Collections.sort(MK_meas);
		log.info("\n" +
				"MK: MPEGaudio min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0)+"\n"+
				"MK: MPEGaudio runSimple med from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2)+"\n"+
				"MK: MPEGaudio runSimple avg from "+_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions+"\n"+
				"MK: MPEGaudio runSimple max from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1)+"\n");
    }

	static long runBenchmark(int btid) {
		if(_MK_verbose) log.info("btid: "+btid);
        long startTime = System.currentTimeMillis();
        try {
            new Harness().inst_main(btid);
        } catch (Exception e) {
            e.printStackTrace(Context.getOut());
        }
        return System.currentTimeMillis() - startTime;
    }

	/** Run this in multi mode, next to each other. *///MK TODO change this!
    public static String testType() {
        /*TODO if(Harness.*/return SpecJVMBenchmarkBase.SINGLE;
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
    
    public void harnessMain() {
    	if(Main._MK_verbose) log.info("");
        runBenchmark(super.getThreadId());
    }
}
