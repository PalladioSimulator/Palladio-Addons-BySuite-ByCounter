/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */
package spec.benchmarks.scimark.sor;

import spec.benchmarks.scimark.utils.Constants;
import spec.benchmarks.scimark.utils.Random;
import spec.benchmarks.scimark.utils.Stopwatch;
import spec.benchmarks.scimark.utils.kernel;

public class SOR {
    int id;
    
    public SOR(int id) {//MK invariant
        this.id = id;
    }
    
    public SOR() {//MK invariant
        this(1);
    }
    
    private static boolean MK_measureAndLogRunDuration = true;
    private static boolean MK_measureAndLogExecuteDuration = true;
    private static long MK_runStart;
    private static long MK_runStop;
    private static long MK_executeStart;
    private static long MK_executeStop;
    
    public static void main(int id) {//MK invariant
        SOR sor = new SOR(id);
        if(MK_measureAndLogRunDuration) MK_runStart = System.nanoTime();
        sor.run();
        if(MK_measureAndLogRunDuration) MK_runStop = System.nanoTime();
        if(MK_measureAndLogRunDuration) System.out.println("MK: SOR.run took "+(MK_runStop-MK_runStart)+
        		" ns (incl. measurement and logging overhead");
    }
    
    public static void main(String args[]) {//MK invariant
        SOR sor = new SOR();
        if(MK_measureAndLogRunDuration) MK_runStart = System.nanoTime();
        sor.run();
        if(MK_measureAndLogRunDuration) MK_runStop = System.nanoTime();
        if(MK_measureAndLogRunDuration) System.out.println("MK: SOR.run took "+(MK_runStop-MK_runStart)+
        		" ns (incl. measurement and logging overhead");
    }
    
    public final double num_flops(int M, int N, int num_iterations) {//MK invariant //TODO statisch auszaehlen
    	long a=0;
    	a++;
        double Md = (double) M;
        double Nd = (double) N;
        double num_iterD = (double) num_iterations;
        
        return (Md-1)*(Nd-1)*num_iterD*6.0;
    }
    
    public final double execute(
    		double omega, 
    		double G[][], 
    		int num_iterations) {
        int M = G.length;
        int N = G[0].length;
        
        double omega_over_four = omega * 0.25;
        double one_minus_omega = 1.0 - omega;
        double [] Gi = null;
        double Gi_Sum = 0.0;
        // update interior points
        //
        int Mm1 = M-1;
        int Nm1 = N-1;
        for (int p=0; p<num_iterations; p++) {
            for (int i=1; i<Mm1; i++) {
                Gi = G[i];
                double[] Gim1 = G[i-1];
                double[] Gip1 = G[i+1];
                for (int j=1; j<Nm1; j++)
                    Gi[j] = omega_over_four * (Gim1[j] + Gip1[j] + Gi[j-1]
                            + Gi[j+1]) + one_minus_omega * Gi[j];
            }
        }
        for(int k=0;k<Gi.length;k++)Gi_Sum += Gi[k];
        
        return Gi_Sum;
    }
    
    private static final ThreadLocal <double[][]> threadLocalMatrix =
            new ThreadLocal<double[][]> () {
        @Override protected double[][] initialValue() {
            return new double[kernel.CURRENT_SOR_SIZE][kernel.CURRENT_SOR_SIZE];
        }
    };
    
    public double measureSOR(int N, double min_time, Random R) {
        double G[][];
        G = threadLocalMatrix.get();
        if(G.length != N){
            System.out.println("G.length: " + G.length + " N: " + N);
            G = new double[N][N];
            threadLocalMatrix.set(G);
        }
        G = kernel.RandomizeMatrix(G, R);
        
        Stopwatch Q = new Stopwatch();
        int cycles=256;
        //while(true)
        //{
//        System.out.println("MK: input to SOR (sor.SOR.execute): omega=1.25, " +//TODO verbose!
//        		"G[0][0]="+G[0][0]+", " +
//        		"G[last][last]="+G[G.length-1][G[G.length-1].length-1]+", " +
//        		"cycles="+cycles);
        Q.start();
        if(MK_measureAndLogExecuteDuration) MK_executeStart = System.nanoTime();
        double x = execute(1.25, G, cycles);
        if(MK_measureAndLogExecuteDuration) MK_executeStop = System.nanoTime();
        Q.stop();
        if(MK_measureAndLogExecuteDuration) {
        	System.out.println("MK: SOR.execute took "+(MK_executeStop-MK_executeStart)+
        		" ns (incl. measurement and logging overhead)");
        }
        //	if (Q.read() >= min_time) break;
        
        //	cycles *= 2;
        //}
        //System.out.println("SOR cycles = " + cycles);
        // approx Mflops
        kernel.checkResults(kernel.CURRENT_SOR_RESULT, "" + x, id);
        return num_flops(N, N, cycles) / Q.read() * 1.0e-6;
    }
    
    public void run() {//MK invariant
        // default to the (small) cache-contained version
        
        double min_time = Constants.RESOLUTION_DEFAULT;
        int SOR_size =  kernel.CURRENT_SOR_SIZE;
        // run the benchmark
        
        double res = 0.0;
        Random R = new Random(Constants.RANDOM_SEED);
        res = measureSOR( SOR_size, min_time, R);
    }
}

