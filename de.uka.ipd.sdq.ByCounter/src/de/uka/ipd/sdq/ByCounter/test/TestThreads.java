package de.uka.ipd.sdq.ByCounter.test;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ThreadedCountingResult;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.RunnableForThreading;
import de.uka.ipd.sdq.ByCounter.test.helpers.ThreadedTestSubject;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests ByCounter when instrumenting multi-threaded 
 * applications.
 *
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestThreads extends AbstractByCounterTest {
	private static MethodDescriptor methodThreadRun;

	private static MethodDescriptor methodRun;
	
	{
		methodThreadRun = new MethodDescriptor(
				RunnableForThreading.class.getCanonicalName(),
				"public void run()");
		methodRun = new MethodDescriptor(
				ThreadedTestSubject.class.getCanonicalName(),
				"public void runThreads()");
	}

    /**
     * This constructor is used by the {@link Parameterized} runner for running tests with different
     * parameters.
     *
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestThreads(final InstrumentationParameters params) {
        super(params);
    }

    /**
     * Tests the counting of user defined line number ranges while recording the order of execution.
     */
    @Test
    public void testInstrumentRunnable() {
        // define expectations
        Expectation e = new Expectation(true);
        // the thread is executed four times
        for(int i = 0 ; i < 4; i++) {
	        e.add().add(Opcodes.LDC, 5)
			        .add(Opcodes.ALOAD, 4)
			        .add(Opcodes.ASTORE, 2)
			        .add(Opcodes.DUP, 3)
			        .add(Opcodes.LREM, 1)
			        .add(Opcodes.GOTO, 1)
			        .add(Opcodes.RETURN, 1)
			        .add(Opcodes.GETSTATIC, 3)
			        .add(Opcodes.INVOKEVIRTUAL, 13)
			        .add(Opcodes.INVOKESPECIAL, 3)
			        .add(Opcodes.INVOKESTATIC, 3)
			        .add(Opcodes.NEW, 3)
			        .add("java.lang.Math", "public long abs(long l)", 1)
//			        .add("java.lang.RuntimeException", "public void RuntimeException(java.lang.Throwable t)", 0)
			        .add("java.lang.StringBuilder", "public StringBuilder(java.lang.String s)", 3)
			        .add("java.lang.StringBuilder", "public java.lang.StringBuilder append(long l)", 1)
			        .add("java.lang.StringBuilder", "public java.lang.StringBuilder append(java.lang.String s)", 4)
			        .add("java.lang.StringBuilder", "java.lang.String toString()", 3)
			        .add("java.lang.Thread", "public java.lang.Thread currentThread()", 1)
			        .add("java.lang.Thread", "public long getId()", 1)
			        .add("java.lang.Thread", "public java.lang.String getName()", 1)
			        .add("java.lang.Thread", "public void sleep(long t)", 1)
			        .add("java.util.Random", "public long nextLong()", 1)
			        .add("java.util.logging.Logger" , "public void info(java.lang.String s)", 2)
	                ;
        }
		// initialize ByCounter
		BytecodeCounter counter = this.setupByCounter();
		
		counter.instrument(methodThreadRun);
		
		// execute with ()
		Object[] executionParameters = new Object[0];
		counter.execute(methodRun, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults();
		removeMethodCallsWithFrequency0(countingResults);
        
        // print ByCounter results
		CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results);
    }
    
    /**
     * Instrument the run method that spawns threads recursively and check 
     * for results. 
     */
    @Test
    public void testInstrumentRunRecursivly() {
		// initialize ByCounter
		BytecodeCounter counter = setupByCounter();
		counter.getInstrumentationParams().setInstrumentRecursively(true);
		counter.instrument(methodRun);
		
		Object[] executionParameters = new Object[0];
		counter.execute(methodRun, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults();
		removeMethodCallsWithFrequency0(countingResults);
        
        // print ByCounter results
        CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
    }
    
    /**
     * Tests for the correct structure of threaded counting results.
     */
    @Test
    public void testThreadStructure() {
    	// initialize ByCounter
		BytecodeCounter counter = setupByCounter();
		counter.instrument(methodRun);
		counter.instrument(methodThreadRun);
		
		Object[] executionParameters = new Object[0];
		counter.execute(methodRun, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults();
		removeMethodCallsWithFrequency0(countingResults);
        
        // print ByCounter results
        CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        // we expect 1 result with 4 child thread results
        Assert.assertEquals(1, results.length);
        Assert.assertTrue("Expected ThreadedCountingResult.", results[0] instanceof ThreadedCountingResult);
        ThreadedCountingResult tcr = (ThreadedCountingResult) results[0];
        SortedSet<ThreadedCountingResult> spawnedResults = tcr.getSpawnedThreadedCountingResults();
		Assert.assertEquals(4, spawnedResults.size());
		for(ThreadedCountingResult spawn : spawnedResults) {
			Assert.assertTrue(spawn.getSpawnedThreadedCountingResults().isEmpty());
			Assert.assertEquals(tcr, spawn.getThreadedCountingResultSource());
		}
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
    }

    /**
     * Remove method calls with frequency 0.
     * @param countingResults Counting results to change.
     */
	private static void removeMethodCallsWithFrequency0(SortedSet<CountingResult> countingResults) {
		for(CountingResult cr : countingResults) {
			List<String> methodsToDiscard = new LinkedList<String>();
			for(String m : cr.getMethodCallCounts().keySet()) {
				if(cr.getMethodCallCounts().get(m) == 0) {
					// mark for removal
					methodsToDiscard.add(m);
				}
			}
			// remove
			for(String m : methodsToDiscard) {
				cr.getMethodCallCounts().remove(m);
			}
		}
	}
}
