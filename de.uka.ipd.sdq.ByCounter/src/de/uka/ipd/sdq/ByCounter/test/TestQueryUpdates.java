package de.uka.ipd.sdq.ByCounter.test;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.StatefulRunnable;
import de.uka.ipd.sdq.ByCounter.test.helpers.SynchronizedTestSubject;
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

	/**
	 * 
	 * @author Martin Krogmann
	 *
	 */
	private final class InstrumentAndExecuteMethod implements Runnable {
		
		private MethodDescriptor method;
		/**
		 * Instance of the class containing the method specified with
		 * {@link InstrumentAndExecuteMethod#InstrumentAndExecuteMethod(MethodDescriptor)}.
		 */
		public Object classInstance;
		
		private Lock lock;
		/**
		 * Use {@link Object#wait()} on this object to wait until
		 * {@link #classInstance} is set.
		 */
		public Condition classInstanceAvailable;
		private boolean bClassInstanceAvailable;

		public InstrumentAndExecuteMethod(final MethodDescriptor method) {
			this.method = method;
			this.classInstance = null;
			this.lock = new ReentrantLock();
			this.bClassInstanceAvailable = false;
			this.classInstanceAvailable = lock.newCondition();
		}

		@Override
		public void run() {
			// initialize ByCounter
			this.lock.lock();
			BytecodeCounter counter;
	        counter = setupQueryByCounter();
	        counter.instrument(this.method);
	        this.classInstance = counter.instantiate(this.method);
	        this.bClassInstanceAvailable = true;
	        this.classInstanceAvailable.signalAll();
	        this.lock.unlock();
	        Object[] executionParameters = new Object[] {};
	        counter.execute(method, this.classInstance, executionParameters);
		}
		
		public boolean isClassInstanceAvailable() {
			return bClassInstanceAvailable;
		}
	}
	
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
    }

    /**
     * This test instruments and executes a test subject that executes specific 
     * sections of code in a controlled way in order to observe the currently 
     * active sections.
     * @throws InterruptedException Can be thrown when trying to join the 
     * querying thread.
     * @throws BrokenBarrierException 
     */
    @Test
    public void testActiveSectionQuerying() throws InterruptedException, BrokenBarrierException {
    	// section 1
        LineNumberRange rangeS1 = new LineNumberRange(32, 34);
        // section 2
        LineNumberRange rangeS2 = new LineNumberRange(37, 42);
        // section 3
        LineNumberRange rangeS3 = new LineNumberRange(46, 48);

        // instrument all ranges
        LinkedList<LineNumberRange> ranges = new LinkedList<LineNumberRange>();
        ranges.add(rangeS1);
        ranges.add(rangeS2);
        ranges.add(rangeS3);

        MethodDescriptor methodRun = new MethodDescriptor(
        		SynchronizedTestSubject.class.getCanonicalName(), 
        		"public void run()");
        methodRun.setCodeAreasToInstrument(ranges.toArray(new LineNumberRange[0]));

        InstrumentAndExecuteMethod iaem = new InstrumentAndExecuteMethod(methodRun);
        final Thread executeThread = new Thread(iaem);
        executeThread.start();
        System.out.println("exec started");
        // wait for the construction of the class instance
        iaem.lock.lock();
        if(!iaem.isClassInstanceAvailable()) {
        	iaem.classInstanceAvailable.await();
        }
        
        final StatefulRunnable classInstance = (StatefulRunnable) iaem.classInstance;
        iaem.lock.unlock();
        
        // query the sections
		CountingResultCollector crc = CountingResultCollector.getInstance();
		for(int i = 0; i < 2; i++) {
			while(classInstance.getCurrentState() < i+2) {Thread.yield();} // wait for other thread
			ActiveSection s = crc.queryActiveSection();
			Assert.assertEquals(i+1, s.sectionId);
			classInstance.nextState();
	    }
        executeThread.join();
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
