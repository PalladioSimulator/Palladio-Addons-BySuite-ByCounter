/**
 * 
 */
package de.fzi.se.bycountertest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;

import org.objectweb.asm.Opcodes;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import de.uka.ipd.sdq.ByCounter.utils.OpcodeToMethodMapper;

/**
 * Demonstrates unexpected behavior of Bytecode Counter.
 * 
 * @author Henning Groenda
 * @author Florian Schreier
 */
public class RunTest {
	
	/** The basic signature of the method which is instrumented in various test cases. Do not use this directly. */
	private static final String SIGNATURE_METHOD_BASIC = "%s process(%s)";
	/** This is the method signature without any parameter and return type void. */
	private static final String SIGNATURE_METHOD = String.format(SIGNATURE_METHOD_BASIC, "void", "");
	/** This is the method signature with an int as parameter and return type int. */
	private static final String SIGNATURE_METHOD_INT = String.format(SIGNATURE_METHOD_BASIC, "int", "int input");
	
	/** Bytecode Counter instance used for testing. */
	BytecodeCounter counter;

	@Before
	public void before() {
		CountingResultCollector.getInstance().clearResults();
		counter = new BytecodeCounter();
	}

	/**
	 * Helper function to check the TestSummation: Tests the helperMethod().
	 * 
	 * @param results
	 *            Counting results.
	 * @param resultsIndex
	 *            Index position of result within counting results.
	 * @param expectedIndexLNR
	 *            Expected LNR for the result.
	 * @return Expected number of opcode counts.
	 */
	private long checkExpectation_Summation_HelperMethod(CountingResult[] results, int resultsIndex,
			int expectedIndexLNR) {
		long expectedOpcodeInvocations = 3;
		assertEquals("Result " + resultsIndex + " must be for LNR " + expectedIndexLNR + ".", expectedIndexLNR,
				results[resultsIndex].getIndexOfRangeBlock());
		assertEquals("Result " + resultsIndex + ": Invocations of ICONST_1 must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.ICONST_1));
		assertEquals("Result " + resultsIndex + ": Invocations of ISTORE must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.ISTORE));
		assertEquals("Result " + resultsIndex + ": Invocations of RETURN must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.RETURN));
		assertTrue("Result " + resultsIndex + ": Overall number of opcode counts must be bigger or equal "
				+ expectedOpcodeInvocations + ".",
				expectedOpcodeInvocations <= sumUpOpcpdeInvocations(results[resultsIndex]));
		assertEquals("Result " + resultsIndex + ": Method invocations must be equal.", Long.valueOf(0),
				results[resultsIndex].methodCountSum());
		return expectedOpcodeInvocations;
	}

	/**
	 * Helper function to check the TestSummation: Tests the method in the dependent class.
	 * 
	 * @param results
	 *            Counting results.
	 * @param resultsIndex
	 *            Index position of result within counting results.
	 * @param expectedIndexLNR
	 *            Expected LNR for the result.
	 * @return Expected number of opcode counts.
	 */
	private long checkExpectation_Summation_ExternalMethod(CountingResult[] results, int resultsIndex,
			int expectedIndexLNR) {
		long expectedOpcodeInvocations = 3;
		assertEquals("Result " + resultsIndex + " must be for LNR " + expectedIndexLNR + ".", expectedIndexLNR,
				results[resultsIndex].getIndexOfRangeBlock());
		assertEquals("Result " + resultsIndex + ": Invocations of LDC must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.LDC));
		assertEquals("Result " + resultsIndex + ": Invocations of LSTORE must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.LSTORE));
		assertEquals("Result " + resultsIndex + ": Invocations of RETURN must be equal.", Long.valueOf(1),
				results[resultsIndex].getOpcodeCount(OpcodeToMethodMapper.RETURN));
		assertTrue("Result " + resultsIndex + ": Overall number of opcode counts must be bigger or equal "
				+ expectedOpcodeInvocations + ".",
				expectedOpcodeInvocations <= sumUpOpcpdeInvocations(results[resultsIndex]));
		assertEquals("Result " + resultsIndex + ": Method invocations must be equal.", Long.valueOf(0),
				results[resultsIndex].methodCountSum());
		return expectedOpcodeInvocations;
	}

	/**
	 * Tests the summation of opcodes and function calls. This case covers the situation of an internal call which
	 * should be included transparently (with the content of the called method but without the call to the method
	 * itself).
	 */
	@Test
	public void summationOfClassInternalCallsTransparent() {
		MethodDescriptor descriptor = new MethodDescriptor(TestSummation.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(22, 22));
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		// execute
		counter.execute(descriptor, target, new Object[0]);

		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(
				new CountingResult[0]);
		assertNotNull("Results must not be null.", results);
		assertEquals("Number of results must be equal.", 1, results.length);
		long expectedOpcodes = 0;
		expectedOpcodes += checkExpectation_Summation_HelperMethod(results, 0, 0);
		long sumMeasured = sumUpOpcodeInvocations(results);
		assertEquals("Overall number of opcode counts must be equal.", expectedOpcodes, sumMeasured);
		assertEquals("Overall method invocations must be equal.", Long.valueOf(0), results[0].methodCountSum());
	}

	/**
	 * Tests the summation of opcodes and function calls under the condition that certain methods are excluded from the
	 * summation. This case covers the situation of an internal call which should be included transparently (with the
	 * content of the called method but without the call to the method itself).
	 */
	@Test
	public void summationOfClassInternalCallsTransparentExcludedMethods() {
		MethodDescriptor descriptor = new MethodDescriptor(TestSummation.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(22, 22));
		// TODO: Exclude method from instrumentation. Adapt following method by adding an additional parameter.
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(
				new CountingResult[0]);
		assertNotNull("Results must not be null.", results);
		assertEquals("Number of results must be equal.", 1, results.length);

		// opcodes
		assertEquals("Result 0: Invocations of ALOAD must be equal.", Long.valueOf(1), results[0]
				.getOpcodeCount(OpcodeToMethodMapper.ALOAD));
		assertEquals("Result 0: Invocations of INVOKEVIRTUAL must be equal.", Long.valueOf(1), results[0]
				.getOpcodeCount(OpcodeToMethodMapper.INVOKEVIRTUAL));
		assertTrue("Result 0: Overall number of opcode counts must be bigger or equal " + 2 + ".",
				2 <= sumUpOpcpdeInvocations(results[0]));
		// function calls
		assertEquals("Result 0: Method invocations must be equal.", Long.valueOf(1), results[0].methodCountSum());
		// TODO check method name and use in next assertion
		// for (String name : results[0].getMethodCallCounts().keySet()) {
		// System.out.println(name);
		// }
		assertEquals("Result 0: Method invocations for internal Method must be equal.", Long.valueOf(1), results[0]
				.getMethodCount("de.fzi.se.bycountertest.TestSummation.helperMethod()"));
	}

	/**
	 * Tests the summation of opcodes and function calls. This case covers the situation of an external call which
	 * should be included transparently (with the content of the called method but without the call to the method
	 * itself). The two classes are both considered as one unit (and hence the transparent summation).
	 */
	@Test
	public void summationOfClassExternalCallsTransparent() {
		MethodDescriptor descriptor = new MethodDescriptor(TestSummation.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(23, 23));
		// TODO: Include external class in transparent instrumentation. Adapt following method by adding an additional
		// parameter or show how result retrieval must be changed.
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(
				new CountingResult[0]);
		assertNotNull("Results must not be null.", results);
		assertEquals("Number of results must be equal.", 1, results.length);
		long expectedOpcodes = checkExpectation_Summation_ExternalMethod(results, 0, 0);
		long sumMeasured = sumUpOpcodeInvocations(results);
		assertEquals("Overall number of opcode counts must be equal.", expectedOpcodes, sumMeasured);
		assertEquals("Overall method invocations must be equal.", Long.valueOf(0), results[0].methodCountSum());
	}

	/**
	 * Tests the summation of opcodes and function. This case covers the situation of an external call which should be
	 * included transparently (with the content of the called method but without the call to the method itself) but the
	 * method is excluded from transparent summation.
	 */
	@Test
	public void summationOfClassExternalCallsTransparentExcludedMethods() {
		MethodDescriptor descriptor = new MethodDescriptor(TestSummation.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(23, 23));
		// TODO: Exclude method from transparent instrumentation. Adapt following method by adding an additional
		// parameter or show how result retrieval must be changed.
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(
				new CountingResult[0]);
		assertNotNull("Results must not be null.", results);
		assertEquals("Number of results must be equal.", 1, results.length);
		// opcodes
		assertEquals("Result 0: Invocations of ALOAD must be equal.", Long.valueOf(1), results[0]
				.getOpcodeCount(OpcodeToMethodMapper.ALOAD));
		assertEquals("Result 0: Invocations of GETFIELD must be equal.", Long.valueOf(1), results[0]
				.getOpcodeCount(OpcodeToMethodMapper.GETFIELD));
		assertEquals("Result 0: Invocations of INVOKEVIRTUAL must be equal.", Long.valueOf(1), results[0]
				.getOpcodeCount(OpcodeToMethodMapper.INVOKEVIRTUAL));
		assertTrue("Result 0: Overall number of opcode counts must be bigger or equal " + 3 + ".",
				3 <= sumUpOpcpdeInvocations(results[0]));
		// function calls
		assertEquals("Result 0: Method invocations must be equal.", Long.valueOf(1), results[0].methodCountSum());
		// TODO check method name and use in next assertion
		// for (String name : results[0].getMethodCallCounts().keySet()) {
		// System.out.println(name);
		// }
		assertEquals("Result 0: Method invocations for internal Method must be equal.", Long.valueOf(1), results[0]
				.getMethodCount("de.fzi.se.bycountertest.TestSummationExternal.process()"));
	}

	/**
	 * Tests if dependencies of a Class Under Test (CUT) can successfully be resolved after instantiation and before
	 * execution.
	 */
	@Test
	public void dependencyResolutionOfClassUnderTest() {
		MethodDescriptor descriptor = new MethodDescriptor(TestLoopExternalAction.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(28, 30)); // loop
		lnrs.add(new LineNumberRange(31, 31)); // external call within loop
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		// resolve dependencies
		DoNothing doNothing = new DoNothing();
		((TestLoopExternalAction) target).setRequiredComponent(doNothing);
		// execute
		counter.execute(descriptor, target, new Object[0]);
		// show non-null results
		SortedSet<CountingResult> results = CountingResultCollector.getInstance().retrieveAllCountingResults();
		for (CountingResult result : results) {
			result.logResult(false, true);
		}
		assertNotNull("Results must not be null.", results);
	}

	/**
	 * Tests if instrumentation of a single line works correctly.
	 */
	@Test
	public void singleLineInstrumentation() {
		Expectation e = new Expectation();
		e.add(17, 17).add(Opcodes.ICONST_0, 1)
					 .add(Opcodes.ISTORE, 1);
		
		MethodDescriptor descriptor = new MethodDescriptor(TestExecutionOrder.class.getCanonicalName(), SIGNATURE_METHOD);
		Object target = instrumentAndInstantiate(descriptor, Arrays.asList(e.getRanges()));
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
		
		e.compare(results);
	}

	/**
	 * Tests if the order of execution during runtime is preserved in the results. This test case is different from
	 * {@link #preserveExecutionOrderOfMeasurements_21()} in the order of the defined line number ranges.
	 */
	@Test
	public void preserveExecutionOrderOfMeasurements_12() {
		Expectation e = new Expectation(true);
		e.add(0).add(Opcodes.ICONST_0, 1) // first two lines
				.add(Opcodes.IINC, 1)
				.add(Opcodes.ISTORE, 1);
		e.add(1).add(Opcodes.ICONST_1, 1) // third line
				.add(Opcodes.ILOAD, 1)
				.add(Opcodes.IMUL, 1)
				.add(Opcodes.ISTORE, 1);
		
		MethodDescriptor descriptor = new MethodDescriptor(TestExecutionOrder.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(17, 18)); // first two lines
		lnrs.add(new LineNumberRange(19, 19)); // third line
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
		
		e.compare(results);
	}

	/**
	 * Tests if the order of execution during runtime is preserved in the results. This test case is different from
	 * {@link #preserveExecutionOrderOfMeasurements_12()} in the order of the defined line number ranges.
	 */
	@Test
	public void preserveExecutionOrderOfMeasurements_21() {
		Expectation e = new Expectation(true);
		e.add(1).add(Opcodes.ICONST_0, 1) // first two lines
				.add(Opcodes.IINC, 1)
				.add(Opcodes.ISTORE, 1);
		e.add(0).add(Opcodes.ICONST_1, 1) // third line
				.add(Opcodes.ILOAD, 1)
				.add(Opcodes.IMUL, 1)
				.add(Opcodes.ISTORE, 1);
		
		MethodDescriptor descriptor = new MethodDescriptor(TestExecutionOrder.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(19, 19)); // third line
		lnrs.add(new LineNumberRange(17, 18)); // first two lines
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
		
		e.compare(results);
	}

	@Test
	public void preserveExecutionOrderOfMeasurements_Loop() {
		Expectation e = new Expectation();
		e.add(15, 15).add(Opcodes.GOTO, 1)
					 .add(Opcodes.ICONST_0, 1)
					 .add(Opcodes.ICONST_2, 1)
					 .add(Opcodes.IF_ICMPLT, 1)
					 .add(Opcodes.ILOAD, 1)
					 .add(Opcodes.ISTORE, 1);
		for (int i = 0; i < 5; i++) {
			e.add(16, 16).add(Opcodes.DUP, 1)
						 .add(Opcodes.GETSTATIC, 1)
						 .add(Opcodes.ILOAD, 1)
						 .add(Opcodes.INVOKESPECIAL, 1)
						 .add(Opcodes.INVOKEVIRTUAL, 3)
						 .add(Opcodes.LDC, 1)
						 .add(Opcodes.NEW, 1);
			e.add(15, 15).add(Opcodes.IINC, 1)
						 .add(Opcodes.ICONST_2, 1)
						 .add(Opcodes.IF_ICMPLT, 1)
						 .add(Opcodes.ILOAD, 1);
		}
		
		MethodDescriptor descriptor = new MethodDescriptor(TestLoopExternalActionNoDependency.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(28, 30)); // loop
		lnrs.add(new LineNumberRange(31, 31)); // body | external call within loop
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		counter.execute(descriptor, target, new Object[0]);
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
		
		e.compare(results);
	}

	/**
	 * Uses ByCounter to instrument and instantiate the class containing the provided method and LNRs.
	 * 
	 * @param descriptor
	 *            Method descriptor.
	 * @param lineNumberRanges
	 *            LineNumberRanges to instrument.
	 * @return Instantiated and instrumented class.
	 */
	private Object instrumentAndInstantiate(MethodDescriptor descriptor, List<LineNumberRange> lineNumberRanges) {
		// instrument
		InstrumentationParameters instrumentationParams = new InstrumentationParameters();
		instrumentationParams.setInstrumentRecursively(true, 50);
		instrumentationParams.setUseBasicBlocks(true);
		// instrumentationParams.setCounterPrecisionIsLong(true);
		// instrumentationParams.setTraceAndIdentifyRequests(false);
		List<MethodDescriptor> methodsToInstrument = new ArrayList<MethodDescriptor>();
		descriptor.setCodeAreasToInstrument(lineNumberRanges.toArray(new LineNumberRange[0]));
		methodsToInstrument.add(descriptor);
		instrumentationParams.setMethodsToInstrument(methodsToInstrument);
		instrumentationParams.setWriteClassesToDisk(false);
		counter.setInstrumentationParams(instrumentationParams);
		counter.instrument();
		// instantiate
		Object target = counter.instantiate(descriptor);
		return target;
	}

	/**
	 * Test that ByCounter does not fail due to an Invocation Target Exception (Stack Overflow) although the problem is
	 * not due to the implementation or structure of it. If an interface instead of a direct class is used ByCounter
	 * should not fail due to an Invocation Target Exception.
	 */
	@Test
	public void callToMethodsViaInterface() {
		// ensure direct execution works and no exceptions are thrown
		TestLoopExternalActionStackOverflow cut = new TestLoopExternalActionStackOverflow();
		cut.process();
		// instrument and execute
		MethodDescriptor descriptor = new MethodDescriptor(
				TestLoopExternalActionStackOverflow.class.getCanonicalName(), SIGNATURE_METHOD);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(28, 30)); // loop
		lnrs.add(new LineNumberRange(31, 31)); // external call within loop
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		// execute
		counter.execute(descriptor, target, new Object[0]);
		// show non-null results
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(
				new CountingResult[0]);
		assertNotNull("Results must not be null.", results);
		assertTrue("Number of results for LNRs must be bigger than 0.", results.length > 0);
		for (CountingResult result : results) {
			result.logResult(false, true);
		}
	}

	@Test
	public void measureMultipleLNRForOneMethod_NoResults() {
		Expectation e = new Expectation();
		// expect no sections at all

		CountingResult[] results = runTestBranch(-1);
		e.compare(results);
	}

	@Test
	public void measureMultipleLNRForOneMethod_ResultsLNR1() {
		Expectation e = new Expectation();
		e.add(0).add(Opcodes.IINC, 1);

		CountingResult[] results = runTestBranch(1);
		e.compare(results);
	}

	@Test
	public void measureMultipleLNRForOneMethod_ResultsLNR2() {
		Expectation e = new Expectation();
		e.add(1).add(Opcodes.ICONST_1, 1)
				.add(Opcodes.ILOAD, 1)
				.add(Opcodes.ISTORE, 1)
				.add(Opcodes.IMUL, 1);
		
		CountingResult[] results = runTestBranch(10);
		e.compare(results);
	}

	@Test
	public void measureMultipleLNRForOneMethod_ResultsLNR12() {
		Expectation e = new Expectation();
		e.add(0).add(Opcodes.IINC, 1);
		e.add(1).add(Opcodes.ICONST_1, 1)
				.add(Opcodes.ILOAD, 1)
				.add(Opcodes.ISTORE, 1)
				.add(Opcodes.IMUL, 1);

		CountingResult[] results = runTestBranch(9);
		e.compare(results);
	}

	/**
	 * Runs the method {@link TestBranch#process(int)} with the given parameter.
	 * 
	 * @param inputValue
	 *            Input parameter.
	 * @return Counting results after measurement.
	 */
	private CountingResult[] runTestBranch(int inputValue) {
		MethodDescriptor descriptor = new MethodDescriptor(TestBranch.class.getCanonicalName(), SIGNATURE_METHOD_INT);
		ArrayList<LineNumberRange> lnrs = new ArrayList<LineNumberRange>();
		lnrs.add(new LineNumberRange(14, 14)); // first branch
		lnrs.add(new LineNumberRange(17, 17)); // second branch
		Object target = instrumentAndInstantiate(descriptor, lnrs);
		// execute
		Object[] params = new Object[1];
		params[0] = new Integer(inputValue);
		counter.execute(descriptor, target, params);

		return CountingResultCollector.getInstance().retrieveAllCountingResults().toArray(new CountingResult[0]);
	}

	/**
	 * Sums up all invocations on opcode counts for all results.
	 * 
	 * @param results
	 *            Result set.
	 * @return Sum.
	 */
	private long sumUpOpcodeInvocations(CountingResult[] results) {
		boolean resultsFound = false;
		long sum = 0;
		for (CountingResult result : results) {
			sum += sumUpOpcpdeInvocations(result);
			boolean callsMeasured = !result.getMethodCallCounts().isEmpty();
			resultsFound = resultsFound || (sum > 0 || callsMeasured == true);
			if (sum > 0 || callsMeasured == true) {
				result.logResult(false, true);
			}
		}
		return sum;
	}

	/**
	 * Sums up all invocations on opcodes for a result.
	 * 
	 * @param result
	 *            Counting result.
	 * @return sum.
	 */
	private long sumUpOpcpdeInvocations(CountingResult result) {
		long sum = 0;
		for (long count : result.getOpcodeCounts()) {
			sum += count;
		}
		return sum;
	}
}
