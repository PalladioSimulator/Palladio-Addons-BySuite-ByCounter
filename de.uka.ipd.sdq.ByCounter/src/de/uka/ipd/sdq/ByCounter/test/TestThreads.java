package de.uka.ipd.sdq.ByCounter.test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.RunnableForThreading;
import de.uka.ipd.sdq.ByCounter.test.helpers.ThreadedTestSubject;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests ByCounter when instrumenting multithreaded 
 * applications.
 *
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestThreads {

    /** These parameters are used by all tests. Revert potential modifications in &#064;After-method. */
    private InstrumentationParameters instrumentationParameters;

	private final InstrumentationParameters instrumentationParametersTemplate;

    /**
     * Generates the different parameters with which all tests are run. This reuses the parameters
     * from TestASMBytecodes.parameterSetup().
     *
     * @return The parameter collection for calling the test constructor.
     * @see #TestASMBytecodes.parameterSetup()
     */
    @Parameters
    public static Collection<?> parameterSetup() {
        return TestASMBytecodes.parameterSetup();
    }

    /**
     * This constructor is used by the {@link Parameterized} runner for running tests with different
     * parameters.
     *
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestThreads(final InstrumentationParameters params) {
        // create a BytecodeCounter
        this.instrumentationParametersTemplate = params;
    }

    /**
     * Clones an instance of {@link InstrumentationParameters} from the template.
     */
    @Before
    public void setupInstrumentationParameters() {
    	this.instrumentationParameters = this.instrumentationParametersTemplate.clone();
    }

    /**
     * Cleans up results after every test.
     */
    @After
    public void cleanResults() {
        // clear all collected results
        CountingResultCollector.getInstance().clearResults();
    }

    /**
     * Tests the counting of user defined line number ranges while recording the order of execution.
     */
    @Test
    public void testRangeBlockOrderedCounting() {
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
        
        // run ByCounter
        CountingResult[] results = this.instrumentAndExecute();
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results);
    }

	private CountingResult[] instrumentAndExecute() {
		// initialize ByCounter
        BytecodeCounter counter = setupByCounter();

        MethodDescriptor methodThreadRun = new MethodDescriptor(
        		RunnableForThreading.class.getCanonicalName(),
        		"public void run()");
        counter.instrument(methodThreadRun);
        // execute with ()
        MethodDescriptor methodRun = new MethodDescriptor(
        		ThreadedTestSubject.class.getCanonicalName(),
        		"public void run()");
        Object[] executionParameters = new Object[0];
        counter.execute(methodRun, executionParameters);

        SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults();
        // remove method calls with frequency 0
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
		return countingResults.toArray(new CountingResult[0]);
	}

	/**
	 * @return A {@link BytecodeCounter} instance with parameters selected 
	 * by the test runner.
	 */
	private BytecodeCounter setupByCounter() {
		BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
		return counter;
	}
}
