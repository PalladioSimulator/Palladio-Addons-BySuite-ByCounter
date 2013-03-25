package de.uka.ipd.sdq.ByCounter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.uka.ipd.sdq.ByCounter.test.requestIDs.TestRequestIDs;


/**
 * Test suite that contains all tests for ByCounter.
 * This makes running all tests at once simpler.
 * NOTE: When some tests fail due to heap space issues, try to add 
 * the VM option "-Xmx512M". Also, the option "-Xss2M" for more stack 
 * size might be necessary for some tests.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestASMBytecodes.class,
	TestBytecodeCounter.class,
	TestMethodDescriptor.class,
	TestResultWriters.class,
	TestLineNumbers.class,
	TestExternalClassDefinition.class,
	TestInternalClassDefinition.class,
	TestResultObservation.class,
	TestInstrumentationRegions.class,
	TestRequestIDs.class,
	TestQueryUpdates.class,
	TestThreads.class,
})
public class AllTestsSuite {
	// no build up / tear down necessary
}
