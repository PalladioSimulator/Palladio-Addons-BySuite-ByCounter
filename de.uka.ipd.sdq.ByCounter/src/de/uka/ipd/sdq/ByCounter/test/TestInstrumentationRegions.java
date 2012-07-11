package de.uka.ipd.sdq.ByCounter.test;

import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationRegion;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests ByCounter when instrumenting using 
 * {@link InstrumentationRegion}s.
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
	
	{
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
		
		InstrumentationRegion r1 = new InstrumentationRegion(
				methodStart, 112, 
				methodStop, 99);
		counter.getInstrumentationParams().getInstrumentationRegions().add(r1);

		// expectations is the sum of the opcodes in the different methods.
		// TODO: document where values come from
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
		
		counter.instrument(methodStart);
		
		Object[] executionParameters = new Object[0];
		counter.execute(methodStart, executionParameters);
		
		SortedSet<CountingResult> countingResults = CountingResultCollector.getInstance().retrieveAllCountingResults();
        
        // print ByCounter results
        CountingResult[] results = countingResults.toArray(new CountingResult[0]);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        expectation.compare(results);
        CountingResultCollector.getInstance().clearResults();
    }
    
    @Override
    protected BytecodeCounter setupByCounter() {
    	BytecodeCounter counter = super.setupByCounter();
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setProvideOnlineSectionExecutionUpdates(true);
    	return counter;
    }
}
