/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.scimark.monte_carlo;

import spec.benchmarks.scimark.utils.kernel;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.results.BenchmarkResult;

public class MainMK extends SpecJVMBenchmarkBase {
    
    public static void main(String[] args) throws Exception {
        runSimple( MainMK.class, args );
    }
    
    public static void Main() {
        runBenchmark();
    }
    
    public static void MainMK() {
        runBenchmark();
    }
    
    static void runBenchmark() {
        // Loop a few times, to create some more work in each ops.
        for (int i = kernel.MC_LOOPS; i > 0; i --) {
            MonteCarloMK.main();
        }
    }
    
    /** Run this in multi mode, next to each other. */
    public static String testType() {
        return MULTI;
    }
    
    /**
     * Constructor introduced by Michael to circumvent ByCounter restriction
     */
    public MainMK() {
        super();
    }
    
    public MainMK(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    }

    public void harnessMain() {
        runBenchmark();
    }
}