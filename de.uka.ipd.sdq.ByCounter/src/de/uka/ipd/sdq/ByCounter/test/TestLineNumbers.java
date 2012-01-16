package de.uka.ipd.sdq.ByCounter.test;

import java.util.Collection;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.TestASMBytecodes;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Opcodes;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectLineNumbers;
import de.uka.ipd.sdq.ByCounter.test.helpers.Utils;
import de.uka.ipd.sdq.ByCounter.utils.ASMOpcodesMapper;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests several different usage patterns for ByCounter.
 * 
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @author Florian Schreier
 */
@RunWith(Parameterized.class)
public class TestLineNumbers {

    /** A switch whether counted zeros on method calls shall be handled as error (true) or not (false). */
    private static final boolean HANDLE_ZERO_ON_METHOD_AS_ERROR = false;

    /** The canonical name of the test subject's class. */
    private static final String TEST_SUBJECT_CANONICAL = TestSubjectLineNumbers.class.getCanonicalName();

    /** Signature of the method that is used to test in testRangeBlocksForeach(). */
    private static final String SIGNATURE_FOREACH = "public int testForeach()";

    /** Signature of the method that is used to test in testBasicBlockCounting(). */
    private static final String SIGNATURE_BASIC_BLOCK = "public void testNestedNormalisedLoops(int i)";

    /** Signature of the method that is used to test in both testRangeBlock{|Ordered}Counting(). */
    private static final String SIGNATURE_RANGE_BLOCK = "public void testNestedNormalisedLoopsWithExternalCalls(int i)";

    /** Signature of the method that is used to test in testMethodCallOrderedCounting(). */
    private static final String SIGNATURE_METHOD_CALLS = SIGNATURE_RANGE_BLOCK;

    /** Signature of the method that is used to test in testLabelAndLineNumbers(). */
    private static final String SIGNATURE_LINE_NUMBERS = "public void testLabelAndLineNumbers()";

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
    public TestLineNumbers(final InstrumentationParameters params) {
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
     * Tests for counting a method using the detected invariant sections aka basic blocks.
     */
    @Test
    public void testBasicBlockCounting() {
        // start standard counting
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(false);
        MethodDescriptor methodNormalise = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_BASIC_BLOCK);
        counter.instrument(methodNormalise);
        Object[] executionParameters = new Object[] { 5 };
        counter.execute(methodNormalise, executionParameters);

        CountingResult originalResult = Utils.getAssertedResult(false);
        CountingResultCollector.getInstance().logResult(originalResult, false, true);
        CountingResultCollector.getInstance().clearResults();

        // enable usage of basic blocks and count again
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(false);
        counter.getInstrumentationParams().setWriteClassesToDisk(true);
        counter.instrument(methodNormalise);
        counter.execute(methodNormalise, executionParameters);

        CountingResult newResult = Utils.getAssertedResult(false);
        CountingResultCollector.getInstance().logResult(newResult, false, true);

        // now assert that the results are equal
        Assert.assertEquals(originalResult.getMethodCallCounts().size(), newResult.getMethodCallCounts().size());
        for (String methodName : originalResult.getMethodCallCounts().keySet()) {
            Long expected = originalResult.getMethodCallCounts().get(methodName);
            Long actual = newResult.getMethodCallCounts().get(methodName);
            Assert.assertEquals(expected, actual);
        }
        Assert.assertEquals(originalResult.getOpcodeCounts().length, newResult.getOpcodeCounts().length);
        for (int opcode = 0; opcode < originalResult.getOpcodeCounts().length; opcode++) {
            StringBuilder message = new StringBuilder();
            message.append("Counts for the ");
            message.append(ASMOpcodesMapper.getInstance().getOpcodeString(opcode));
            message.append(" instruction do not match.");
            long expected = originalResult.getOpcodeCounts()[opcode];
            long actual = newResult.getOpcodeCounts()[opcode];
            Assert.assertEquals(message.toString(), expected, actual);
        }

    }

    /**
     * Tests the counting of user defined line number ranges.
     */
    @Test
    public void testRangeBlockCounting() {
        // define expectations
        Expectation e = new Expectation(false);
        e.add(51, 53).add(Opcodes.ICONST_0, 3)
                     .add(Opcodes.ISTORE, 3);
        e.add(54, 54).add(Opcodes.BIPUSH, 2)
                     .add(Opcodes.GOTO, 1)
                     .add(Opcodes.IF_ICMPLT, 2)
                     .add(Opcodes.ILOAD, 2);
        e.add(55, 55).add(Opcodes.IINC, 1);
        e.add(57, 57).add(Opcodes.IINC, 1);
        e.add(58, 58).add(Opcodes.BIPUSH, 13)
                     .add(Opcodes.GOTO, 1)
                     .add(Opcodes.IF_ICMPLT, 13)
                     .add(Opcodes.ILOAD, 13);
        e.add(59, 59).add(Opcodes.ICONST_2, 12)
                     .add(Opcodes.ILOAD, 12)
                     .add(Opcodes.IMUL, 12)
                     .add(Opcodes.ISTORE, 12);
        e.add(61, 61).add(Opcodes.IINC, 12);
        e.add(63, 63).add(Opcodes.IINC, 1);
        // initialize ByCounter
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(false);
        MethodDescriptor methodRanged = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_RANGE_BLOCK);
        methodRanged.setCodeAreasToInstrument(e.getRanges());
        counter.instrument(methodRanged);
        // execute with (10)
        Object[] executionParameters = new Object[] { 10 };
        counter.execute(methodRanged, executionParameters);

        CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
        Assert.assertTrue("No or not enough results counted", results.length > 1);
        for (CountingResult r : results) {
            CountingResultCollector.getInstance().logResult(r, false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results, HANDLE_ZERO_ON_METHOD_AS_ERROR);
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
        // initialize ByCounter
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(true);
        MethodDescriptor methodRanged = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_RANGE_BLOCK);
        methodRanged.setCodeAreasToInstrument(e.getRanges());
        counter.instrument(methodRanged);
        // execute with (10)
        Object[] executionParameters = new Object[] { 10 };
        counter.execute(methodRanged, executionParameters);

        CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
        Assert.assertTrue("No or not enough results counted", results.length > 1);
        for (CountingResult r : results) {
            CountingResultCollector.getInstance().logResult(r, false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results, HANDLE_ZERO_ON_METHOD_AS_ERROR);
    }

    /**
     * Tests the method {@link TestSubjectLineNumbers#testForeach()} that contains a "foreach" loop.
     */
    @Test
    public void testRangeBlocksForeach() {
        // define expectations
        Expectation e = new Expectation(true);
        e.add(102, 108).add(Opcodes.AALOAD, 3)
                       .add(Opcodes.AASTORE, 3)
                       .add(Opcodes.ARRAYLENGTH, 1)
                       .add(Opcodes.ALOAD, 7)
                       .add(Opcodes.ASTORE, 8)
                       .add(Opcodes.ANEWARRAY, 1)
                       .add(Opcodes.DUP, 7)
                       .add(Opcodes.GOTO, 1)
                       .add(Opcodes.ICONST_0, 3)
                       .add(Opcodes.ICONST_1, 1)
                       .add(Opcodes.ICONST_2, 1)
                       .add(Opcodes.ICONST_3, 1)
                       .add(Opcodes.IF_ICMPLT, 4)
                       .add(Opcodes.IINC, 6)
                       .add(Opcodes.ILOAD, 12)
                       .add(Opcodes.INVOKESPECIAL, 3)
                       .add(Opcodes.INVOKESTATIC, 3)
                       .add(Opcodes.INVOKEVIRTUAL, 3)
                       .add(Opcodes.IRETURN, 1)
                       .add(Opcodes.ISTORE, 3)
                       .add(Opcodes.LDC, 3)
                       .add(Opcodes.NEW, 3)
                       .add(String.class.getCanonicalName(), "public static java.lang.String valueOf(java.lang.Object obj)", 3)
                       .add("java.lang.StringBuilder.StringBuilder(Ljava/lang/String;)V", 3)
                       .add("java.lang.StringBuilder.toString()Ljava/lang/String;", 3);
        // initialize ByCounter
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        MethodDescriptor methodForeach = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_FOREACH);
        methodForeach.setCodeAreasToInstrument(e.getRanges());
        counter.instrument(methodForeach);
        // execute
        Object[] executionParameters = new Object[] {};
        counter.execute(methodForeach, executionParameters);

        CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
        Assert.assertTrue("No or too many results counted", results.length == 1);
        for (CountingResult r : results) {
            CountingResultCollector.getInstance().logResult(r, false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results, HANDLE_ZERO_ON_METHOD_AS_ERROR);
    }

    /**
     * This test tests ByCounter's method call counting ability.
     */
    @Test
    public void testMethodCallOrderedCounting() {
        /*
         * expect three different sections
         * [51, 52] is part of a basic block that contains no external calls
         * [55, 56] itself contains an external call
         * [57, 57] itself contains no external call, but is part of a basic block that contains an external call
         */
        Expectation e = new Expectation(true);
        e.add(51, 52).add(Opcodes.ICONST_0, 2)
                     .add(Opcodes.ISTORE, 2);
        e.add(55, 56).add(Opcodes.ALOAD, 1)
                     .add(Opcodes.IINC, 1)
                     .add(Opcodes.INVOKESPECIAL, 1)
                     .add(TEST_SUBJECT_CANONICAL + ".extCall1()V", 1);
        e.add(57, 57).add(Opcodes.IINC, 1);
        // initialize ByCounter
        BytecodeCounter counter = new BytecodeCounter();
        counter.setInstrumentationParams(this.instrumentationParameters);
        counter.getInstrumentationParams().setUseBasicBlocks(true);
        counter.getInstrumentationParams().setRecordBlockExecutionOrder(true);
        MethodDescriptor methodRanged = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_METHOD_CALLS);
        methodRanged.setCodeAreasToInstrument(e.getRanges());
        counter.instrument(methodRanged);
        // execute with (10)
        Object[] executionParameters = new Object[] { 10 };
        counter.execute(methodRanged, executionParameters);

        CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
        for (CountingResult r : results) {
            CountingResultCollector.getInstance().logResult(r, false, true);
        }
        CountingResultCollector.getInstance().clearResults();
        // compare
        e.compare(results, HANDLE_ZERO_ON_METHOD_AS_ERROR);
    }

    /**
     * A test.
     */
    @Test
    public void testLabelAndLineNumbers() {
        BytecodeCounter counter = new BytecodeCounter();
        MethodDescriptor d = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_LINE_NUMBERS);
        counter.instrument(d);
        counter.execute(d, new Object[0]);
        CountingResultCollector.getInstance().clearResults();
    }

}
