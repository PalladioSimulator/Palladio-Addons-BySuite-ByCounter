/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */
package spec.benchmarks.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import spec.harness.Context;
import spec.harness.Launch;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.StopBenchmarkException;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
	static boolean doEquals=true;
    
    /** Run this in mutli mode, next to each other. */
    public static String testType() {
        return MULTI;
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
	        System.err.println("MK: Serial main "+i+": "+_MK_val+" ns");
	    }
	    System.err.println("MK: Serial min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    Collections.sort(MK_meas);
	    System.err.println("MK: Serial min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    System.err.println("MK: Serial med from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2));
	    System.err.println("MK: Serial avg from "+_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions);
	    System.err.println("MK: Serial max from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1));
    }
    
    
    
    public static Object[][] instances;
    public static ByteArrayOutputStream[] streams;
    public Object[] threadInstances;
    ByteArrayOutputStream bos;
    
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
        threadInstances = instances[threadId - 1];     
        bos = streams[threadId - 1]; 
    }
    
    public void harnessMain() {
    	try {
            serialize();
            serialize();
            serialize();
    	} catch (Exception e) {
    		e.printStackTrace(Context.getOut());
    	}
    }
    
    public static void setupBenchmark(){    	
        int threads = Launch.currentNumberBmThreads;
        instances = new Object[threads][Utils.classesNumber];
        streams = new ByteArrayOutputStream[threads];       
        try {      
            for (int i = 0; i < threads; i ++) {          	
        	    instances[i] = Utils.createInstances();
        	    streams[i] = new ByteArrayOutputStream() {
        	    	public synchronized byte[] toByteArray() {
        	    		return buf;
        	    	}
        	    };
            }    
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new StopBenchmarkException("error in setupBenchmark of serial workload");
        }
    }
    
    
    public void serialize() throws Exception {    	
    	BitSet result = Utils.createBitSet();
    	bos.reset();
    	ObjectOutputStream oos = new ObjectOutputStream(bos);    	
    	for (int i = 0; i < Utils.singleLoop; i ++) {
    	    for (int j = 0; j < threadInstances.length; j ++) {
    	    	oos.writeObject(threadInstances[j]);
    	    }
    	    oos.flush();
    	    oos.reset();
    	}   	
    	
    	ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray(), 0, bos.size());
    	ObjectInputStream ois = new ObjectInputStream(bis);
    	for (int i = 0; i < Utils.singleLoop; i ++) {    		
    	    for (int j = 0; j < threadInstances.length; j ++) {
    	    	Object obj = ois.readObject();   
    	    	if (doEquals) {   	    		
    	    	    result.set(j, result.get(j) && obj.equals(threadInstances[j]));
    	    	}    
    	    }
    	    
    	}
    	oos.close();
    	ois.close();    	
    	Utils.printResult(Context.getOut(), result);
    }	
}
