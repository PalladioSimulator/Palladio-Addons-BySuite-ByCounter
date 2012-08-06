package de.uka.ipd.sdq.ByCounter.test;

import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.ActiveSection;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.RequestResult;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectLineNumbers;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectResultObservation;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests querying of {@link CountingResultCollector} for
 * ByCounter.
 *
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestQueryUpdates extends AbstractByCounterTest {

	private final class QueryCRCRunnable implements Runnable {
		private static final int SLEEP_TIME = 25;
		public boolean done = false;
		@Override
		public void run() {
			CountingResultCollector crc = CountingResultCollector.getInstance();
			while(!done) {
				ActiveSection s = crc.queryActiveSection();
				System.out.println("ActiveSection: " + s);
				try {
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/** Signature of the method that is used to test in {@link #testCallTreeObservation()} */
    private static final String SIGNATURE_METHOD1 = "public void method1(boolean firstLevel) {";

	/** Signature of the method that is used to test in {@link #testRangeBlockOrderedCounting()} */
    private static final String SIGNATURE_METHOD_CALLS = "public void testNestedNormalisedLoopsWithExternalCalls(int i)";

    /**
     * This constructor is used by the Parametrized runner for running tests with different
     * parameters.
     *
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestQueryUpdates(final InstrumentationParameters params) {
        super(params);
    }

    /**
     * Cleans up results after every test.
     */
    @After
    @Override
    public void cleanResults() {
        // clear all collected results
        super.cleanResults();
        // delete all observers
        CountingResultCollector.getInstance().deleteObservers();
    }

    /**
     * Tests the counting of user defined line number ranges while recording the order of execution.
     */
    @Test
    public void testRangeBlockOrderedCounting() {
        // define expectations
        Expectation e = new Expectation(true);
        e.add(51, 53).add(Opcodes.ICONST_0, 3)
                     .add(Opcodes.ISTORE, 3);
        e.add(54, 54).add(Opcodes.BIPUSH, 1)
                     .add(Opcodes.GOTO, 1)
                     .add(Opcodes.IF_ICMPLT, 1)
                     .add(Opcodes.ILOAD, 1);
        e.add(55, 55).add(Opcodes.IINC, 1);
        e.add(57, 57).add(Opcodes.IINC, 1);
        e.add(58, 58).add(Opcodes.BIPUSH, 1)
                     .add(Opcodes.GOTO, 1)
                     .add(Opcodes.IF_ICMPLT, 1)
                     .add(Opcodes.ILOAD, 1);
        for (int i = 0; i < 12; i++) {
            e.add(59, 59).add(Opcodes.ICONST_2, 1)
                         .add(Opcodes.ILOAD, 1)
                         .add(Opcodes.IMUL, 1)
                         .add(Opcodes.ISTORE, 1);
            e.add(61, 61).add(Opcodes.IINC, 1);
            e.add(58, 58).add(Opcodes.BIPUSH, 1)
                         .add(Opcodes.IF_ICMPLT, 1)
                         .add(Opcodes.ILOAD, 1);
        }
        e.add(63, 63).add(Opcodes.IINC, 1);
        e.add(54, 54).add(Opcodes.BIPUSH, 1)
                     .add(Opcodes.IF_ICMPLT, 1)
                     .add(Opcodes.ILOAD, 1);
        
        // run ByCounter
        RequestResult[] rResults = this.instrumentAndExecute(e.getRanges());
        Assert.assertEquals(1, rResults.length);
        CountingResult[] results = rResults[0].getCountingResults().toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results);
    }

    /**
     * This test instruments and executes a mildly complex call tree to verify
     * that the online results are not mixed up.
     * @throws InterruptedException Can be thrown when trying to join the 
     * querying thread.
     */
    @Test
    public void testCallTreeObservation() throws InterruptedException {
		// initialize ByCounter
        BytecodeCounter counter = setupQueryByCounter();

        MethodDescriptor method1 = new MethodDescriptor(
        		TestSubjectResultObservation.class.getCanonicalName(),
        		SIGNATURE_METHOD1);

        Expectation eInit = new Expectation(true);	// false because the execution sequence is specified manually down below
        eInit.add(0).add(Opcodes.ICONST_2, 1)
        			  .add(Opcodes.ISTORE, 1);
        LineNumberRange eInitLnr = new LineNumberRange(29, 29);
        Expectation eInt = new Expectation(true);
        eInt.add(1).add(Opcodes.IINC, 1)
        			.add(Opcodes.GOTO, 1);
        LineNumberRange eIntLnr = new LineNumberRange(31, 31);
        Expectation eDouble = new Expectation(true);
        eDouble.add(2).add(Opcodes.ILOAD, 1)
        				   .add(Opcodes.I2D, 1)
        				   .add(Opcodes.LDC, 1)
        				   .add(Opcodes.DADD, 1)
        				   .add(Opcodes.D2I, 1)
        				   .add(Opcodes.ISTORE, 1);
        LineNumberRange eDoubleLnr = new LineNumberRange(35, 35);

        final Expectation[] expectations = new Expectation[] {
        		eInit,					// method1
        		eInit, eInt, eDouble, 	// method1->method2->method1
        		eDouble 				// method1
        };

        // instrument all ranges
        LinkedList<LineNumberRange> ranges = new LinkedList<LineNumberRange>();
        ranges.add(eInitLnr);
        ranges.add(eIntLnr);
        ranges.add(eDoubleLnr);

        method1.setCodeAreasToInstrument(ranges.toArray(new LineNumberRange[0]));
        counter.instrument(method1);

        // start a thread that queries CountingResultCollector
        QueryCRCRunnable queryCRCRunnable = new QueryCRCRunnable();
		Thread queryThread = new Thread(queryCRCRunnable);
        queryThread.start();
        
        // execute with (true)
        Object[] executionParameters = new Object[] { true };
        counter.execute(method1, executionParameters);
        queryCRCRunnable.done = true;
        queryThread.join();

        int i = 0;
        for(CountingResult cr : CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults()) {
        	cr.logResult(false, true);
        	System.out.println(cr.getMethodInvocationBeginning());
        	expectations[i].compare(new CountingResult[] {cr});
        	i++;
        }
    }

	private RequestResult[] instrumentAndExecute(LineNumberRange[] codeAreasToInstrument) {
		// initialize ByCounter
        BytecodeCounter counter = setupQueryByCounter();

        MethodDescriptor methodRanged = new MethodDescriptor(
        		TestSubjectLineNumbers.class.getCanonicalName(),
        		SIGNATURE_METHOD_CALLS);
        methodRanged.setCodeAreasToInstrument(codeAreasToInstrument);
        counter.instrument(methodRanged);
        // execute with (10)
        Object[] executionParameters = new Object[] { 10 };
        counter.execute(methodRanged, executionParameters);

        return CountingResultCollector.getInstance().retrieveAllCountingResults().getRequestResults().toArray(new RequestResult[0]);
	}

	/**
	 * @return A {@link BytecodeCounter} instance setup with online section
	 * execution updates.
	 */
	private BytecodeCounter setupQueryByCounter() {
		BytecodeCounter counter = this.setupByCounter();
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(true);
        counter.getInstrumentationParams().setProvideOnlineSectionActiveUpdates(true);
        counter.getInstrumentationParams().setTraceAndIdentifyRequests(true);
//        counter.getInstrumentationParams().setWriteClassesToDisk(true);
		return counter;
	}
}
