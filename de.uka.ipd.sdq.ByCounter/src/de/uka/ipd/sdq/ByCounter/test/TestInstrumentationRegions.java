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
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.OverlappingRegions;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject;
import de.uka.ipd.sdq.ByCounter.test.helpers.subjects.ExecutionOrder;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests ByCounter when instrumenting using 
 * {@link InstrumentedRegion}s.
 *
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestInstrumentationRegions extends AbstractByCounterTest {

    private static MethodDescriptor methodStart;

	private static MethodDescriptor methodStop;

	private static MethodDescriptor mdParameterTest;
	
	static {
		methodStart = new MethodDescriptor(
				TestSubject.class.getCanonicalName(),
				"public void methodCallTest()");
		methodStop = new MethodDescriptor(
				TestSubject.class.getCanonicalName(),
				"public int loopTest()");
		mdParameterTest = new MethodDescriptor(
				TestSubject.class.getCanonicalName(),
				"public boolean parameterTest(int i, float f, java.lang.String s)");
	}

    /**
     * This constructor is used by the {@link Parameterized} runner for running tests with different
     * parameters.
     *
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestInstrumentationRegions(final InstrumentationParameters params) {
        super(params);
    }
    
    /**
     * Instruments a region starting in methodStart and ending in 
     * methodStop.
     */
    @Test
    public void testInstrumentRegion() {
		// initialize ByCounter
		BytecodeCounter counter = this.setupByCounter();
		counter.getInstrumentationParams().setInstrumentRecursively(true);
		
		CountingResultCollector.getInstance().addObserver(new java.util.Observer() {

			@Override
			public void update(java.util.Observable crc, Object update) {
				if(update instanceof de.uka.ipd.sdq.ByCounter.execution.CountingResultSectionExecutionUpdate) {
					de.uka.ipd.sdq.ByCounter.execution.CountingResultSectionExecutionUpdate u = (de.uka.ipd.sdq.ByCounter.execution.CountingResultSectionExecutionUpdate) update;
					if(u.sectionResult.getOpcodeCount(Opcodes.ICONST_0) != 0) {
						System.out.println(u.sectionResult.getOpcodeCount(Opcodes.ICONST_0) + "*ICONST_0");
					}
				}
				System.out.println(update);
			}
			
		});
		
		InstrumentedRegion r1 = new InstrumentedRegion(
				methodStart, 112, 
				methodStop, 99);
		counter.getInstrumentationParams().getEntitiesToInstrument().add(r1);

		// expectations is the sum of the opcodes in the different methods.
		Expectation expectation = new Expectation();
		expectation.add().add(Opcodes.ALOAD, 2)		// methodCallTest():1x
						 .add(Opcodes.ICONST_1, 1+1)// methodCallTest():1x, parameterTest(..):1x
						 .add(Opcodes.FCONST_2, 1)	// methodCallTest():1x
						 .add(Opcodes.LDC, 1)		// methodCallTest():1x
						 .add(Opcodes.IFEQ, 1)		// methodCallTest():1x
						 .add(Opcodes.ICONST_2, 1)	// methodCallTest():1x
						 .add(Opcodes.ISTORE, 1+2+5)// methodCallTest():1x, loopTest():2x, parameterTest(..):5x
						 .add(Opcodes.ICONST_0, 2)	// loopTest():2x
						 .add(Opcodes.INVOKEVIRTUAL, 2) // methodCallTest():2x
						 .add(Opcodes.BIPUSH, 6)	// parameterTest(..):6x
						 .add(Opcodes.FLOAD, 1)		// parameterTest(..):1x
						 .add(Opcodes.ILOAD, 1)		// parameterTest(..):1x
						 .add(Opcodes.IADD, 1)		// parameterTest(..):1x
						 .add(Opcodes.I2F, 1)		// parameterTest(..):1x
						 .add(Opcodes.FMUL, 1)		// parameterTest(..):1x
						 .add(Opcodes.FSTORE, 1)	// parameterTest(..):1x
						 .add(Opcodes.IRETURN, 1)	// parameterTest(..):1x
						 .add(mdParameterTest.getCanonicalMethodName(), 1)
						 .add(methodStop.getCanonicalMethodName(), 1);
		
		counter.instrument();
		
		Object[] executionParameters = new Object[0];
		counter.execute(methodStart, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults();
        
        // print ByCounter results
		CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        expectation.compare(results);
    }
    

	/**
	 * Tests if instrumentation of a single line works correctly.
	 */
	@Test
	public void testSingleLineInstrumentation() {
		// define expectations
		Expectation e = new Expectation();
		e.add().add(Opcodes.ICONST_0, 1)
			   .add(Opcodes.ISTORE, 1);
        // run ByCounter
		String canonicalClassName = ExecutionOrder.class.getCanonicalName();
		String methodSignature = "void process()";
        MethodDescriptor methodRanged = new MethodDescriptor(canonicalClassName, methodSignature);
		InstrumentedRegion region = new InstrumentedRegion(methodRanged, 15, methodRanged, 15);

		// initialise
        BytecodeCounter counter = setupByCounter();
        List<InstrumentedRegion> instrumentationRegions = new LinkedList<InstrumentedRegion>();
        instrumentationRegions.add(region);
		counter.getInstrumentationParams().getEntitiesToInstrument().addAll(instrumentationRegions);
        counter.instrument();
        counter.execute(methodRanged, new Object[0]);

        CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults().toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        // compare
        e.compare(results);
	}
	

	/**
	 * Tests feeding ByCounter with incompatible instrumentation parameters.
	 * Here regions are used but useBasicBlocks is set to false.
	 */
	@Test
	public void testBadInstrumentationParameters_noBasicBlocks() {
        // run ByCounter
		String canonicalClassName = ExecutionOrder.class.getCanonicalName();
		String methodSignature = "void process()";
        MethodDescriptor methodRanged = new MethodDescriptor(canonicalClassName, methodSignature);
		InstrumentedRegion region = new InstrumentedRegion(methodRanged, 15, methodRanged, 15);

		// initialise
        BytecodeCounter counter = setupByCounter();
        // set up illegal parameters
        
        // try no basic blocks
        counter.getInstrumentationParams().setUseBasicBlocks(false);
        List<InstrumentedRegion> instrumentationRegions = new LinkedList<InstrumentedRegion>();
        instrumentationRegions.add(region);
		counter.getInstrumentationParams().getEntitiesToInstrument().addAll(instrumentationRegions);
		// try to instrument
		boolean exceptionThrown = false;
		try {
			counter.addEntityToInstrument(methodRanged);
			counter.instrument();
		} catch(IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		Assert.assertTrue("The expected exception was not thrown. ", exceptionThrown);
	}
	

	/**
	 * Tests feeding ByCounter with incompatible instrumentation parameters.
	 * Here regions are used but online execution updates are set to false.
	 */
	@Test
	public void testBadInstrumentationParameters_noOnlineUpdates() {
        // run ByCounter
		String canonicalClassName = ExecutionOrder.class.getCanonicalName();
		String methodSignature = "void process()";
        MethodDescriptor methodRanged = new MethodDescriptor(canonicalClassName, methodSignature);
        InstrumentedRegion region = new InstrumentedRegion(methodRanged, 15, methodRanged, 15);

		// initialise
        BytecodeCounter counter = setupByCounter();
        // set up illegal parameters
        
		// try no online updates
		counter.getInstrumentationParams().setProvideOnlineSectionExecutionUpdates(false);
		List<InstrumentedRegion> instrumentationRegions = new LinkedList<InstrumentedRegion>();
        instrumentationRegions.add(region);
		counter.getInstrumentationParams().getEntitiesToInstrument().addAll(instrumentationRegions);
		boolean exceptionThrown = false;
		try {
			counter.instrument();
		} catch(IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		Assert.assertTrue("The expected exception was not thrown. ", exceptionThrown);
	}
	
	/**
	 * Tests definition and counting of two instrumentation regions that 
	 * overlap.
	 */
	@Test
	public void testOverlappingRegions() {
		// initialize ByCounter
		BytecodeCounter counter = this.setupByCounter();
		counter.getInstrumentationParams().setInstrumentRecursively(true);
		
		final MethodDescriptor methodStartA = new MethodDescriptor(
				OverlappingRegions.class.getCanonicalName(), 
				"public void startA()");
		final MethodDescriptor methodStartB = new MethodDescriptor(
				OverlappingRegions.class.getCanonicalName(), 
				"public void startB()");
		final MethodDescriptor methodStopCommon = new MethodDescriptor(
				OverlappingRegions.class.getCanonicalName(), 
				"public boolean stopCommon(final java.lang.String input)");
		final MethodDescriptor methodExecute = new MethodDescriptor(
				OverlappingRegions.class.getCanonicalName(), 
				"public void execute()");

		InstrumentedRegion regionA = new InstrumentedRegion(
				methodStartA, 19, 
				methodStopCommon, 36);
		InstrumentedRegion regionB = new InstrumentedRegion(
				methodStartB, 27, 
				methodStopCommon, 36);
		counter.getInstrumentationParams().getEntitiesToInstrument().add(regionA);
		counter.getInstrumentationParams().getEntitiesToInstrument().add(regionB);

		// expectations is the sum of the opcodes in the different methods.
		Expectation expectation = new Expectation();
		// regionA
		expectation.add().add(Opcodes.ICONST_0, 1)		// startA():1x
						 .add(Opcodes.ISTORE, 2)		// startA():1x, stopCommon():1x
						 .add(Opcodes.ALOAD, 2)			// startA():1x, stopCommon():1x
						 .add(Opcodes.NEW, 2)			// startA():1x, stopCommon():1x
						 .add(Opcodes.DUP, 2)			// startA():1x, stopCommon():1x
						 .add(Opcodes.INVOKESPECIAL, 2)	// startA():1x, stopCommon():1x
						 .add(Opcodes.INVOKEVIRTUAL, 7) // startA():3x, stopCommon():4x
						 .add(Opcodes.ILOAD, 2)			// startA():1x, stopCommon():1x
						 .add(methodStopCommon.getCanonicalMethodName(), 1) // startA():1x
						 .add("java.lang.StringBuilder.StringBuilder()V", 1) // startA():1x, stopCommon():1x
						 .add("java.lang.StringBuilder.append(I)Ljava/lang/StringBuilder;", 1) // startA():1x
						 .add("java.lang.StringBuilder.toString()Ljava/lang/String;", 2) // startA():1x, stopCommon():1x
						 .add(Opcodes.ICONST_1, 1)		// stopCommon():1x
						 .add(Opcodes.GETSTATIC, 1)		// stopCommon():1x
						 .add(Opcodes.LDC, 1)			// stopCommon():1x
						 .add("java.lang.StringBuilder.StringBuilder(Ljava/lang/String;)V", 1) // stopCommon():1x
						 .add("java.lang.StringBuilder.append(Z)Ljava/lang/StringBuilder;", 1) // stopCommon():1x
						 .add("java.lang.StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;", 1) // stopCommon():1x
						 .add("java.io.PrintStream.println(Ljava/lang/String;)V", 1) // stopCommon():1x
						 ;
		// regionB
		expectation.add().add(Opcodes.LDC, 2)			// startB():1x, stopCommon():1x
						 .add(Opcodes.DSTORE, 1)		// startB():1x
						 .add(Opcodes.ALOAD, 2)			// startB():1x, stopCommon():1x
						 .add(Opcodes.NEW, 2)			// startB():1x, stopCommon():1x
						 .add(Opcodes.DUP, 2)			// startB():1x, stopCommon():1x
						 .add(Opcodes.INVOKESPECIAL, 2)	// startB():1x, stopCommon():1x
						 .add(Opcodes.INVOKEVIRTUAL, 7) // startB():3x, stopCommon():4x
						 .add(Opcodes.DLOAD, 1)			// startB():1x
						 .add(methodStopCommon.getCanonicalMethodName(), 1) // startA():1x
						 .add("java.lang.StringBuilder.StringBuilder(Ljava/lang/String;)V", 1) // stopCommon():1x
						 .add("java.lang.StringBuilder.StringBuilder()V", 1) // startB():1x
						 .add("java.lang.StringBuilder.append(D)Ljava/lang/StringBuilder;", 1) // startB():1x
						 .add("java.lang.StringBuilder.toString()Ljava/lang/String;", 2) // startB():1x, stopCommon():1x
						 .add(Opcodes.ICONST_1, 1)		// stopCommon():1x
						 .add(Opcodes.ISTORE, 1)		// stopCommon():1x
						 .add(Opcodes.GETSTATIC, 1)		// stopCommon():1x
						 .add(Opcodes.ILOAD, 1)			// stopCommon():1x
						 .add("java.lang.StringBuilder.append(Z)Ljava/lang/StringBuilder;", 1) // stopCommon():1x
						 .add("java.lang.StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;", 1) // stopCommon():1x
						 .add("java.io.PrintStream.println(Ljava/lang/String;)V", 1) // stopCommon():1x
						 ;
		
		counter.instrument();
		
		Object[] executionParameters = new Object[0];
		counter.execute(methodExecute, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults();
        
        // print ByCounter results
		CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        expectation.compare(results);
	}
    
    @Override
    protected BytecodeCounter setupByCounter() {
    	BytecodeCounter counter = super.setupByCounter();
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setProvideOnlineSectionExecutionUpdates(true);
    	return counter;
    }
}
