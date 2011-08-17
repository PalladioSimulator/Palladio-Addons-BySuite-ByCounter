package de.uka.ipd.sdq.ByCounter.test;

import java.util.ArrayList;
import java.util.Collection;

import junit.framework.Assert;

import org.junit.After;
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
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectLineNumbers;
import de.uka.ipd.sdq.ByCounter.test.helpers.Utils;
import de.uka.ipd.sdq.ByCounter.utils.ASMOpcodesMapper;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests several different usage patterns for ByCounter.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
@RunWith(Parameterized.class)
public class TestLineNumbers {

	private static final String SIGNATURE_TEST_FOREACH = "public int testForeach()";

	private static final String DEFAULT_SIGNATURE_FOR_INSTRUMENTATION = 
		"public void testNestedNormalisedLoops(int i)";
	
	private static final String DEFAULT_RANGED_SIGNATURE_FOR_INSTRUMENTATION =
		"public void testNestedNormalisedLoopsWithExternalCalls(int i)";

	private static final String DEFAULT_PACKAGE_NAME = 
		"de.uka.ipd.sdq.ByCounter";

	private static final String HELPERS_CLASS_PACKAGE =		
		DEFAULT_PACKAGE_NAME+".test.helpers.";
	
	/**
	 * Generates the different parameters with which all tests are run.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.parameterSetup()
	 */
	@SuppressWarnings({"rawtypes"})
	@Parameters
	public static Collection parameterSetup() {
		return TestASMBytecodes.parameterSetup();
	}
	
	private InstrumentationParameters instrumentationParameters;

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	public TestLineNumbers(InstrumentationParameters params) {
		// create a BytecodeCounter
		this.instrumentationParameters = params;
	}
	
	@After
	public void cleanResults() {
		// clear all collected results
		CountingResultCollector.getInstance().clearResults();
	}
	
	/**
	 * Tests for counting a method using the detected invariant sections aka
	 * basic blocks.
	 *
	 */
	@Test
	public void testBasicBlockCounting() {
		// start standard counting
		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);
		counter.getInstrumentationParams().setUseBasicBlocks(false);
		MethodDescriptor methodNormalise = new MethodDescriptor(
				HELPERS_CLASS_PACKAGE + TestSubjectLineNumbers.class.getSimpleName(), 
				DEFAULT_SIGNATURE_FOR_INSTRUMENTATION);
		counter.instrument(methodNormalise);
		Object[] executionParameters = new Object[] {5};
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
		Assert.assertEquals(originalResult.getMethodCallCounts().size(),
				newResult.getMethodCallCounts().size());
		for(String methodName : originalResult.getMethodCallCounts().keySet()) {
			Assert.assertEquals(originalResult.getMethodCallCounts().get(methodName),
					newResult.getMethodCallCounts().get(methodName));
		}
		Assert.assertEquals(originalResult.getOpcodeCounts().length,
				newResult.getOpcodeCounts().length);
		for(int opcode = 0; opcode < originalResult.getOpcodeCounts().length; opcode++) {
			Assert.assertEquals("Counts for the " 
					+ ASMOpcodesMapper.getInstance().getOpcodeString(opcode)
					+ " instruction do not match.",
					originalResult.getOpcodeCounts()[opcode],
					newResult.getOpcodeCounts()[opcode]);
		}

	}
	
	/**
	 * Tests the counting of user defined line number ranges.
	 */
	@Test
	public void testRangeBlockCounting() {
		ArrayList<LineNumberRange> ranges = new ArrayList<LineNumberRange>();
		ranges.add(new LineNumberRange(51, 53));
		ranges.add(new LineNumberRange(54, 54));
		ranges.add(new LineNumberRange(55, 55));
		ranges.add(new LineNumberRange(57, 57));
		ranges.add(new LineNumberRange(58, 58));
		ranges.add(new LineNumberRange(59, 59));
		ranges.add(new LineNumberRange(61, 61));
		ranges.add(new LineNumberRange(63, 63));
		

		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);

		counter.getInstrumentationParams().setUseBasicBlocks(true);
		MethodDescriptor methodRanged = new MethodDescriptor(
				HELPERS_CLASS_PACKAGE + TestSubjectLineNumbers.class.getSimpleName(), 
				DEFAULT_RANGED_SIGNATURE_FOR_INSTRUMENTATION);
		methodRanged.setCodeAreasToInstrument(
				ranges.toArray(new LineNumberRange[ranges.size()]));
		counter.instrument(methodRanged);
		// execute with (5)
		Object[] executionParameters = new Object[] {5};
		counter.execute(methodRanged, executionParameters);
		
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
		Assert.assertTrue("No or not enough results counted", results.length > 1);
		for(CountingResult r : results) {
			CountingResultCollector.getInstance().logResult(r, false, true);
		}
		CountingResultCollector.getInstance().clearResults();
	}
	

	/**
	 * Tests the counting of user defined line number ranges while  
	 * recording the order of execution.
	 */
	@Test
	public void testRangeBlockOrderedCounting() {
		ArrayList<LineNumberRange> ranges = new ArrayList<LineNumberRange>();
		ranges.add(new LineNumberRange(51, 53));
		ranges.add(new LineNumberRange(54, 54));
		ranges.add(new LineNumberRange(55, 55));
		ranges.add(new LineNumberRange(57, 57));
		ranges.add(new LineNumberRange(58, 58));
		ranges.add(new LineNumberRange(59, 59));
		ranges.add(new LineNumberRange(61, 61));
		ranges.add(new LineNumberRange(63, 63));
		

		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);

		counter.getInstrumentationParams().setUseBasicBlocks(true);
		counter.getInstrumentationParams().setRecordBlockExecutionOrder(true);
		MethodDescriptor methodRanged = new MethodDescriptor(
				HELPERS_CLASS_PACKAGE + TestSubjectLineNumbers.class.getSimpleName(), 
				DEFAULT_RANGED_SIGNATURE_FOR_INSTRUMENTATION);
		methodRanged.setCodeAreasToInstrument(
				ranges.toArray(new LineNumberRange[ranges.size()]));
		counter.instrument(methodRanged);
		// execute with (5)
		Object[] executionParameters = new Object[] {5};
		counter.execute(methodRanged, executionParameters);
		
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
		Assert.assertTrue("No or not enough results counted", results.length > 1);
		for(CountingResult r : results) {
			CountingResultCollector.getInstance().logResult(r, false, true);
		}
		CountingResultCollector.getInstance().clearResults();

		counter.getInstrumentationParams().setRecordBlockExecutionOrder(false);
	}

	/**
	 * Tests the method {@link TestSubjectLineNumbers#testForeach()} that 
	 * contains a "foreach" loop.
	 */
	@Test
	public void testRangeBlocksForeach() {
		ArrayList<LineNumberRange> ranges = new ArrayList<LineNumberRange>();
		ranges.add(new LineNumberRange(102, 108));
		

		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);

		counter.getInstrumentationParams().setUseBasicBlocks(true);
		MethodDescriptor methodForeach = new MethodDescriptor(
				HELPERS_CLASS_PACKAGE + TestSubjectLineNumbers.class.getSimpleName(), 
				SIGNATURE_TEST_FOREACH);
		methodForeach.setCodeAreasToInstrument(
				ranges.toArray(new LineNumberRange[ranges.size()]));
		counter.instrument(methodForeach);
		// execute 
		Object[] executionParameters = new Object[] {};
		counter.execute(methodForeach, executionParameters);
		
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
		Assert.assertTrue("No or too many results counted", results.length == 1);
		for(CountingResult r : results) {
			CountingResultCollector.getInstance().logResult(r, false, true);
			Assert.assertTrue("Not all instructions are counted.", r.getOpcodeCount(Opcodes.IF_ICMPLT) >= 1);
		}
		CountingResultCollector.getInstance().clearResults();
	}

	@Test
	public void testLabelAndLineNumbers() {
		BytecodeCounter counter = new BytecodeCounter();
		MethodDescriptor d = new MethodDescriptor(
				TestSubjectLineNumbers.class.getCanonicalName(), 
				"public void testLabelAndLineNumbers()");
		counter.instrument(d);
		counter.execute(d, new Object[0]);
		CountingResultCollector.getInstance().clearResults();
	}

}
