/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */
package spec.benchmarks.derby;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spec.harness.Context;
import spec.harness.Launch;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    
    /**
     * Run this in multi mode, next to each other.
     */
    public static String testType() {
        return MULTI;
    }
    
    static long runBenchmark(int btid) {
        try {
            long res = System.currentTimeMillis();
            int databaseNum = dataBaseNum[btid - 1];
            DerbyHarness.main(btid, dataBaseNum[btid - 1] + 1,
                    clientsNumber[databaseNum], indInDatabase[btid - 1]);
            return System.currentTimeMillis() - res;
        } catch (Exception e) {
            e.printStackTrace(Context.getOut());
        }
        return 100;
    }
    
    public static int getThrNumber(int benchmarkThreadIndex) {
        if (DATABASES_NUM == 1) {
            return HWTFACTOR;
        } else  {
            if (HWTFACTOR % THREADSPERDB == 0) {
                return THREADSPERDB;
            }  else {
                int threadsLast = HWTFACTOR  % THREADSPERDB  + THREADSPERDB;
                int half = threadsLast / 2;
                if (DATABASES_NUM - 1 == benchmarkThreadIndex) {
                    return half;
                } else if (DATABASES_NUM == benchmarkThreadIndex) {
                    return threadsLast - half;
                } else {
                    return THREADSPERDB;
                }
            }
        }
    }
    
    public static int[] clientsNumber;
    public static int MAX_CLIENTS_NUMBER_PER_DB;
    public static int[] dataBaseNum;
    public static int[] indInDatabase;
    public static int THREADSPERDB;
    public static int HWTFACTOR;
    public static int DATABASES_NUM;
    
    private static void configure() {
        HWTFACTOR = Launch.currentNumberBmThreads;
        THREADSPERDB = 4;
        DATABASES_NUM = (HWTFACTOR - 1) / THREADSPERDB + 1;
    }
    
    public static void setupBenchmark() {
        configure();
        clientsNumber = new int[DATABASES_NUM];
        dataBaseNum = new int[HWTFACTOR];
        indInDatabase = new int[HWTFACTOR];
        MAX_CLIENTS_NUMBER_PER_DB = Integer.MIN_VALUE;
        for (int i = 0; i < clientsNumber.length; i ++) {
            int clients = getThrNumber(i + 1);
            MAX_CLIENTS_NUMBER_PER_DB = Math.max(MAX_CLIENTS_NUMBER_PER_DB, clients);
            clientsNumber[i] = clients;
        }
        
        int res = 0;
        for (int i = 0; i < clientsNumber.length; i ++) {
            for(int j = 0; j < clientsNumber[i]; j ++) {
                dataBaseNum[res] = i;
                indInDatabase[res] = j;
                res++;
            }
        }
        
        Utils.print("#hwt\tdatabase\tshift");
        for (int i = 0; i < dataBaseNum.length; i ++) {
            Utils.print(i + "\t\t" + dataBaseNum[i] + "\t" + indInDatabase[i]);
        }
        
        Utils.initRates();
        DerbyHarness.initDatabases();
    }
    
    
    public static void tearDownBenchmark() {
        DerbyHarness.shutdownDerbySystem();
        Utils.releaseResources();
        
        // Remove derby disk resources
        File derbyLogFile = new File("derby.log");
        if (derbyLogFile.exists()) {
        	derbyLogFile.delete();
        }

        File derbyDir = new File("derby_dir");
        if (derbyDir.exists() && derbyDir.isDirectory()) {
        	deleteDirectory(derbyDir);
        }
    }

    static public void deleteDirectory(File root) {
		if (root.exists()) {
			File[] files = root.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		root.delete();
	}
    
	private static int _MK_numberOfRepetitions = 1;
    
	public static long _MK_val = 0;
    
    public static void main(String[] args) {
    	List<Long> MK_meas = new ArrayList<Long>();
    	double sum = 0D;
        for(int i=0; i<_MK_numberOfRepetitions ; i++){

        	//1. works once, but not twice
//        	Main m = new Main();
//	        m.harnessMain(); 
	        
        	//2. works once, but not twice
        	_MK_val= System.nanoTime(); //MK
        	runSimple(Main.class, args);
	    	_MK_val = System.nanoTime()-_MK_val;
        	
	    	//3. works once, but not twice
//        	Main m = new Main();
//        	setupBenchmark();
//        	runBenchmark(i+1);
////	        m.harnessMain(); 
//	        tearDownBenchmark();
//	    	_MK_val = System.nanoTime()-_MK_val;
	    	MK_meas.add(_MK_val);
	    	sum += _MK_val;
	        System.err.println("MK: Derby main "+i+": "+_MK_val+" ns");
	    }
	    System.err.println("MK: Derby 1st from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    Collections.sort(MK_meas);
	    System.err.println("MK: Derby min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
	    System.err.println("MK: Derby med from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2));
	    System.err.println("MK: Derby avg from "+_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions);
	    System.err.println("MK: Derby max from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1));
    }
    
    
    /**
     * Constructor provided by SPEC
     * @param bmResult
     * @param threadId
     */
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    }
    
    /**
     * MK parameterless constructor needed by ByCounter
     */
    public Main() {
        super();
    }
    
    public void harnessMain() {
        runBenchmark(super.getThreadId());
    }
}
