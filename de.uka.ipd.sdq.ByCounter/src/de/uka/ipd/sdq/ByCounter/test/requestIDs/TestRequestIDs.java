package de.uka.ipd.sdq.ByCounter.test.requestIDs;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultBase;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ResultCollection;
import de.uka.ipd.sdq.ByCounter.test.AbstractByCounterTest;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * JUnit Test for the instrumentation of compress.
 *
 */
@RunWith(Parameterized.class)
public class TestRequestIDs extends AbstractByCounterTest {	

	private static final Object[] EXECUTION_PARAMETERS_NONE = new Object[]{new String[]{}};

	private static Logger log = Logger.getLogger(TestRequestIDs.class.getCanonicalName());
	
	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	public TestRequestIDs(InstrumentationParameters params) {
		super(params);
	}

	private static final MethodDescriptor METHOD_TO_EXECUTE = 
		new MethodDescriptor(A.class.getCanonicalName(), "public static void main(java.lang.String argv[]) {");

	private BytecodeCounter counter;
	private CountingResultCollector resultColl;
	private String testClassName;
	private MethodDescriptor methodToExecute;

	private Object[] executionParameters;

	@Test
	public void testRequestIDInstrumentation() {
		this.testClassName 			= A.class.getCanonicalName();
		this.methodToExecute		= METHOD_TO_EXECUTE;
		this.executionParameters	= EXECUTION_PARAMETERS_NONE;
		init();
		this.instrumentationParameters.setWriteClassesToDisk(true);
		count();
	}
	
	
	/**
	 * Default constructor, see source
	 */
	public void init(){
		this.resultColl = CountingResultCollector.getInstance();
		this.counter = setupByCounter();
	}
	
	/**
	 * Performs the actual counting by calling BytecodeCounter.count(...)
	 */
	private void count(){
		MethodDescriptor methDesc1 = new MethodDescriptor(this.testClassName, "public void methodA(int reqID)");
		MethodDescriptor methDesc2 = new MethodDescriptor(this.testClassName, "public void methodB(int reqID)");
		MethodDescriptor methDesc3 = new MethodDescriptor(this.testClassName, "public java.lang.String doSth()");
		MethodDescriptor methDesc4 = new MethodDescriptor(this.testClassName, "public char[] doSthElse()");
		MethodDescriptor methDesc5 = new MethodDescriptor(this.testClassName, "public static boolean doSthDifferent(short s)");
		MethodDescriptor methDesc6 = new MethodDescriptor(this.testClassName, "public static byte doSthStatic()");
		MethodDescriptor methDesc7 = new MethodDescriptor(this.testClassName, "public boolean parameterTest(int i, float f, java.lang.String s)");
		MethodDescriptor methDesc8 = new MethodDescriptor(de.uka.ipd.sdq.ByCounter.test.requestIDs.A.class.getCanonicalName(), "public A(int param)");
		
		counter.setInstrumentationParams(this.instrumentationParameters);
		counter.getInstrumentationParams().setTraceAndIdentifyRequests(true);
//		log.fine("Method descriptor: "+methDesc);
		
		long start = System.nanoTime();
		log.fine("(NOT INITIALISED)" + this.counter.getInstrumentationParams().toString());
		List<MethodDescriptor> methDescs = new ArrayList<MethodDescriptor>();
		methDescs.add(methDesc1);
		methDescs.add(methDesc2);
		methDescs.add(methDesc3);
		methDescs.add(methDesc4);
		methDescs.add(methDesc5);
		methDescs.add(methDesc6);
		methDescs.add(methDesc7);
		methDescs.add(methDesc8);
		this.counter.instrument(methDescs);
		this.counter.execute(this.methodToExecute, 
				this.executionParameters);
		long stop = System.nanoTime();
		long counting = stop-start;
		
		log.fine(this.counter.getInstrumentationParams().toString());
		log.info(counting+    "ns to count (aka \t"+
				Math.round((double) counting/1000)+"us aka \t"+
				Math.round((double) counting/1000000)+"ms aka \t"+
				Math.round((double) counting/1000000000)+"s)");
		ResultCollection retrieveAllCountingResults = this.resultColl.retrieveAllCountingResults();
		SortedSet<CountingResult> finalResults = retrieveAllCountingResults.getCountingResults();
		Assert.assertNotSame("Number of results must be != 0.", 0, finalResults.size());
		log.info(finalResults.size()+" counting results found, logging them: ");
		for(CountingResultBase r : finalResults) {
			r.logResult(false, true);
		}
		// clear all collected results
		this.resultColl.clearResults();
	}
}
