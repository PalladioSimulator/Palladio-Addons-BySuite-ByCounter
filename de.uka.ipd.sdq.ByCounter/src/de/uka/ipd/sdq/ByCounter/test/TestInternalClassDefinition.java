package de.uka.ipd.sdq.ByCounter.test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Opcodes;
import de.uka.ipd.sdq.ByCounter.test.helpers.ClassZ;
import de.uka.ipd.sdq.ByCounter.test.helpers.InterfaceX;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectInterfaceMethods;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests the internal class definition of ByCounter.
 * 
 * @author ?
 * @author Florian Schreier
 * @see <a href="https://sdqweb.ipd.kit.edu/bugzilla/show_bug.cgi?id=726">Bugzilla Bug 726</a>
 */
public class TestInternalClassDefinition {
	
	private static final String TEST_SUITE_CANONCICAL = TestInternalClassDefinition.class.getCanonicalName();
	private static final String TEST_SUBJECT_CANONICAL = TestSubjectInterfaceMethods.class.getCanonicalName();
	
	private static final String INTERNAL_CLASS_CANONICAL = ClassZ.class.getCanonicalName();
	private static final String SIGNATURE_METHOD = "public void methodA1()";
	
	/** This logger is used to log all kinds of messages of this test suite. */
	private static final Logger LOG = Logger.getLogger(TEST_SUITE_CANONCICAL);


  /**
   * Cleans up after every test.
   */
  @After
  public void cleanResults() {
      // clear all collected results
      CountingResultCollector.getInstance().clearResults();
  }
	
	/**
	 * Tests if pattern matching on internal classes works correctly.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testInternalClassPatternMatcher() {
		String[] definitions = {"de.uka*", "de.uka.Test"};
		HashMap<String, Boolean>[] expectations = new HashMap[2];
		
		expectations[0] = new HashMap<String, Boolean>();
		expectations[0].put("de.ukap.Test", true);
		expectations[0].put("de.uka.ipd", true);
		
		expectations[1] = new HashMap<String, Boolean>();
		expectations[1].put("de.uka.Test", true);
		expectations[1].put("de.uka.Test$XXX$YYY", true);
		expectations[1].put("de.uka.Test.{ENUM Y}", false);

		// iterate through different input definitions
		for (int i = 0; i < 2; i++) {
			HashSet<String> internalClassesDef = new HashSet<String>();
			internalClassesDef.add(definitions[i]);
			CountingResultCollector.getInstance().setInternalClassesDefinition(internalClassesDef);
			// iterates through all expectations and compares them with reality
			for (String test : expectations[i].keySet()) {
				String message = definitions[i] + " matcht " + test;
				boolean actual = CountingResultCollector.getInstance().isInternalClass(test);
				if (expectations[i].get(test)) {
					Assert.assertTrue(message + " nicht", actual);
					LOG.info(message);
				} else {
					Assert.assertFalse(message, actual);
					LOG.info(message + " nicht");
				}
			}
		}
	}
	

	/**
	 * This unit test tries to instrument a class that is given as byte[].
	 * In this case the .class file of ASMBytecodeOccurences is used.
	 *
	 *
	 *TODO: TestSubjectInterfaces benutzen?
	 */
	@Test
	public void testRetriveInternalResults() {
		// define expectations (TODO no real expectations, this is what is counted)
		Expectation e = new Expectation(true);
		e.add().add(Opcodes.GETSTATIC, 1) // ClassY.methodX1()
					 .add(Opcodes.INVOKEVIRTUAL, 1)
					 .add(Opcodes.LDC, 1)
					 .add(Opcodes.RETURN, 1)
					 .add(PrintStream.class.getCanonicalName(), "public void println(java.lang.String x)", 1);
		e.add().add(Opcodes.ALOAD, 1) // ClassZ.ClassZ()
					 .add(Opcodes.RETURN, 1)
					 .add(Opcodes.INVOKESPECIAL, 1)
					 .add("java.lang.Object.Object()V", 1);
		e.add().add(Opcodes.GETSTATIC, 1) // ClassZ.methodX1()
					 .add(Opcodes.INVOKEVIRTUAL, 1)
					 .add(Opcodes.LDC, 1)
					 .add(Opcodes.RETURN, 1)
					 .add(PrintStream.class.getCanonicalName(), "public void println(java.lang.String x)", 1);
		e.add().add(Opcodes.ALOAD, 3) // TestSubjectInterfaceMethods.methodA1()
					 .add(Opcodes.DUP, 1)
					 .add(Opcodes.GETFIELD, 2)
					 .add(Opcodes.NEW, 1)
					 .add(Opcodes.PUTFIELD, 1)
					 .add(Opcodes.RETURN, 1)
					 .add(Opcodes.INVOKEINTERFACE, 2)
					 .add(Opcodes.INVOKESPECIAL, 1)
					 .add("de.uka.ipd.sdq.ByCounter.test.helpers.ClassZ.ClassZ()V", 1)
					 .add(InterfaceX.class.getCanonicalName(), "void methodX1()", 2);
		
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(TEST_SUBJECT_CANONICAL, SIGNATURE_METHOD);
		
		counter.getInstrumentationParams().setInstrumentRecursively(true, 5);
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		
		counter.execute(myMethod, new Object[0]);

		// define internal classes
		Set<String> internalClassesDefinition = new HashSet<String>();
		internalClassesDefinition.add(INTERNAL_CLASS_CANONICAL);
		CountingResultCollector.getInstance().setInternalClassesDefinition(internalClassesDefinition);
		
		// retrieve results TODO does not work for recursive = true
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray_noInlining(false);
    e.compare(results, false);
    
    List<CountingResult> resultsList = CountingResultCollector.getInstance().retrieveAllCountingResults_recursively();
		Assert.assertNotNull(resultsList);
		Assert.assertTrue(resultsList.size() > 1);
		for(CountingResult newResult: resultsList) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
		
		counter.getInstrumentationParams().setInstrumentRecursively(false, 0);
	}

}
