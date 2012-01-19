package de.uka.ipd.sdq.ByCounter.test.framework.expectations.test;

import static org.junit.Assert.fail;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;

/**
 * 
 * 
 * @author Florian Schreier
 */
public class TestZeroMethodCount {

	/**
	 * This test tests if the expectation framework handles zero counted method calls correctly.
	 */
	@Test
	public void testZeroCountIsError() {
		Expectation e = new Expectation(false);
		e.add().add("testMethodTwo", 2L);

		long[] opcodeCounts = new long[201];
		SortedMap<String, Long> methodCallCounts = new TreeMap<String, Long>();
		methodCallCounts.put("testMethodTwo", 2L);
		methodCallCounts.put("testMethodZero", 0L);
		CountingResult[] observation = new CountingResult[1];
		observation[0] = new CountingResult(null, 0L, 0L, opcodeCounts, methodCallCounts);

		// compare and catch eventual assertion error, if no error occurred or the message was wrong fail the test
		try {
			e.compare(observation);
			fail("No assertion thrown on method call equal to zero");
		} catch (AssertionError err) {
			if (!err.getMessage().equals("Actual testMethodZero in round 0 in SectionExpectation [sectionNumber=-1, range=null] not expected but counted as 0")) {
				fail(err.getMessage());
			}
		}
	}
}
