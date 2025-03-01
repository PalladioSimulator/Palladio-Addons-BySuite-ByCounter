/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * Copyright (c) 1997,1998 Sun Microsystems, Inc. All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.scimark.monte_carlo;
import java.io.PrintStream;
import java.util.logging.Logger;

import spec.benchmarks.scimark.utils.Constants;
import spec.benchmarks.scimark.utils.Random;
import spec.benchmarks.scimark.utils.Stopwatch;

/**
 * Estimate Pi by approximating the area of a circle.
 *
 * How: generate N random numbers in the unit square, (0,0) to (1,1)
 * and see how are within a radius of 1 or less, i.e.
 * <pre>
 *
 * sqrt(x^2 + y^2) < r
 *
 * </pre>
 * since the radius is 1.0, we can square both sides
 * and avoid a sqrt() computation:
 * <pre>
 *
 * x^2 + y^2 <= 1.0
 *
 * </pre>
 * this area under the curve is (Pi * r^2)/ 4.0,
 * and the area of the unit of square is 1.0,
 * so Pi can be approximated by
 * <pre>
 * # points with x^2+y^2 < 1
 * Pi =~ 		--------------------------  * 4.0
 * total # points
 *
 * </pre>
 *
 */

public class MonteCarloMK {
    private static boolean measureMK = true;
	
    final static int SEED = 113;
	
    public static void main() {
        MonteCarloMK mc = new MonteCarloMK();
        mc.run();
    }
	
    public static void main(String[] args) {
        MonteCarloMK mc = new MonteCarloMK();
        mc.run();
    }

    private Logger logMK;

	private long startMK;
    
    private long stopMK;
    
    public MonteCarloMK() {
		super();
		logMK = Logger.getLogger(this.getClass().getName());
	}
    
    public final double integrate(int numSamples) {
        
//        if(MonteCarloMK.measureMK){
//        	startMK = System.nanoTime();
//        }
        Random R = new Random(SEED);
//        if(MonteCarloMK.measureMK){
//        	stopMK = System.nanoTime();
//        	logMK.info("Initialising Random with SEEF "+SEED+" took "+(stopMK-startMK)+" ns");
//        }
        
        int underCurve = 0;
        for (int count = 0; count < numSamples; count++) {
            
            double x = R.nextDouble();
            double y = R.nextDouble();
            
            if ( x*x + y*y <= 1.0) {
                underCurve ++;
            }
        }
        return ((double) underCurve / numSamples) * 4.0;
    }
    
    
    
    public double measureMonteCarlo(double min_time, Random R) {
        PrintStream p = spec.harness.Context.getOut();
        Stopwatch Q = new Stopwatch();
        
        // Cycles set to integrate into SPECjvm2008 benchmark harness.  Testing done on
        // Apple Macbook Pro 2.0Ghz Intel Core Duo, 1GB 667mhz SODIMM
        // J2SE 5.0_06 (Apple)
        // Tuning: -server
        int cycles=16777216;
        double x =0.0;
        
        Q.start();
    	System.out.println("MK: input to Monte Carlo (monte_carlo.MonteCarlo.integrate): " +
    			cycles+" cycles");
//        if(MonteCarloMK.measureMK){
        	startMK = System.nanoTime();
//        }
        x = integrate(cycles);
//        if(MonteCarloMK.measureMK){
        	stopMK = System.nanoTime();
//        }
    	System.out.println("MK: Monte Carlo integrating ("+cycles+" cycles) took "+(stopMK-startMK)+" ns");
        Q.stop();
        
        p.println(x);
        return num_flops(cycles) / Q.read() * 1.0e-6;
    }
    
    public final double num_flops(int Num_samples) {
        // 3 flops in x^2+y^2 and 1 flop in random routine
        
        return ((double) Num_samples)* 4.0;
        
    }
    
    public void run() {
        double min_time = Constants.RESOLUTION_DEFAULT;
        
        // run the benchmark
        
        double res = 0.0;
        Random R = new Random(Constants.RANDOM_SEED);
        res = measureMonteCarlo(min_time, R);
    }
    
}



