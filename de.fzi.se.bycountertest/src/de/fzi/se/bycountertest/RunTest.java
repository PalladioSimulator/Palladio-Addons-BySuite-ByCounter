/**
 *
 */
package de.fzi.se.bycountertest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import de.uka.ipd.sdq.ByCounter.utils.OpcodeToMethodMapper;

/**
 * Demonstrates unexpected behavior of Bytecode Counter.
 *
 * @author Henning Groenda
 * @author Florian Schreier
 */
public class RunTest {

	/** This is the method signature without any parameter and return type void. */
	private static final String SIGNATURE_METHOD = "void process()";
	
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
