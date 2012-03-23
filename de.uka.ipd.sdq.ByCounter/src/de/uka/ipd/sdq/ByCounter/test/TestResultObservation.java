package de.uka.ipd.sdq.ByCounter.test;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

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
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectLineNumbers;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests observing of {@link CountingResultCollector} for 
 * ByCounter.
 * 
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestResultObservation {

    /** The canonical name of the test subject's class. */
    private static final String TEST_SUBJECT_CANONICAL = TestSubjectLineNumbers.class.getCanonicalName();

    /** Signature of the method that is used to test in both testRangeBlock{|Ordered}Counting(). */
    private static final String SIGNATURE_RANGE_BLOCK = "public void testNestedNormalisedLoopsWithExternalCalls(int i)";

    /** Signature of the method that is used to test in testMethodCallOrderedCounting(). */
    private static final String SIGNATURE_METHOD_CALLS = SIGNATURE_RANGE_BLOCK;

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
     * This constructor is used by the Parametrized runner for running tests with different
     * parameters.
     * 
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestResultObservation(final InstrumentationParameters params) {
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
        
        CountingResultCollector.getInstance().addObserver(new Observer() {
			@Override
			public void update(Observable crc, Object updateData) {
				System.out.println("Notification received: " + updateData);
//				if(updateData instanceof CountingResultCollector.ObservedSectionExecutionUpdate) {
//					CountingResultCollector.getInstance().retrieveAllCountingResults().last().logResult(false, false);
//				}
			}
        });
        
        // run ByCounter
        CountingResult[] results = this.instrumentAndExecute(e.getRanges());
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results);
    }
	
	private CountingResult[] instrumentAndExecute(LineNumberRange[] codeAreasToInstrument) {
		// initialize ByCounter
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(true);
        counter.getInstrumentationParams().setProvideOnlineSectionExecutionUpdates(true);
  
        MethodDescriptor methodRanged = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_METHOD_CALLS);
        methodRanged.setCodeAreasToInstrument(codeAreasToInstrument);
        counter.instrument(methodRanged);
        // execute with (10)
        Object[] executionParameters = new Object[] { 10 };
        counter.execute(methodRanged, executionParameters);

        return CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
	}
}
