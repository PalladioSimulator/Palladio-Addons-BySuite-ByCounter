package de.uka.ipd.sdq.ByCounter.test;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.execution.ExecutionSettings;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
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
	
	private static final String SIGNATURE_METHOD = "public void methodA1()";
	
	/** This logger is used to log all kinds of messages of this test suite. */
	private static final Logger LOG = Logger.getLogger(TestInternalClassDefinition.class.getCanonicalName());


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
			ExecutionSettings execParameters = new ExecutionSettings();
			execParameters.setInternalClassesDefinition(internalClassesDef);
			// iterates through all expectations and compares them with reality
			for (String test : expectations[i].keySet()) {
				String message = definitions[i] + " matcht " + test;
				boolean actual = execParameters.isInternalClass(test);
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
	 */
	@Test
	public void testRetriveInternalResults() {
		// define expectations (the external call to ClassY should not be inlined)
		// the comments behind add() state where the opcode comes from
		Expectation e = new Expectation(true);
		e.add().add(Opcodes.ALOAD, 4) // 1x ClassZ(), 3x methodA1()
			   .add(Opcodes.DUP, 1) // 1x methodA1()
			   .add(Opcodes.GETFIELD, 2) // 2x methodA1()
			   .add(Opcodes.GETSTATIC, 2) // 1x ClassY.mexthodX1(), 1x ClassZ.mexthodX1()
			   .add(Opcodes.INVOKEINTERFACE, 2) // 2x methodA1()
			   .add(Opcodes.INVOKESPECIAL, 2) // 1x ClassZ(), 1x methodA1()
			   .add(Opcodes.INVOKEVIRTUAL, 2) // 1x ClassY.mexthodX1(), 1x ClassZ.mexthodX1()
			   .add(Opcodes.LDC, 2) // 1x ClassZ.mexthodX1(), 1x ClassY.methodX1()
			   .add(Opcodes.NEW, 1) // 1x methodA1()
			   .add(Opcodes.PUTFIELD, 1) // 1x mexthodA1()
			   .add(Opcodes.RETURN, 4) // 1x ClassY.mexthodX1(), 1x ClassZ.mexthodX1(), 1x ClassZ(), 1x methodA1()
			   .add("de.uka.ipd.sdq.ByCounter.test.helpers.ClassZ.ClassZ()V", 1) // 1x methodA1()
			   .add("java.lang.Object.Object()V", 1) // 1x ClassZ()
			   .add(InterfaceX.class.getCanonicalName(), "void methodX1()", 2) // 2x methodA1()
			   .add(PrintStream.class.getCanonicalName(), "public void println(java.lang.String x)", 2); // 1x ClassY.methodX1(), 1x ClassZ.methodX1()
		
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(TestSubjectInterfaceMethods.class.getCanonicalName(), SIGNATURE_METHOD);
		
		counter.getInstrumentationParams().setInstrumentRecursively(true, 5);
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);

		counter.getExecutionSettings().setAddUpResultsRecursively(true);
		counter.execute(myMethod, new Object[0]);

		// define internal classes
		Set<String> internalClassesDefinition = new HashSet<String>();
		internalClassesDefinition.add(TestSubjectInterfaceMethods.class.getCanonicalName());
		internalClassesDefinition.add(ClassZ.class.getCanonicalName());
		counter.getExecutionSettings().setInternalClassesDefinition(internalClassesDefinition);
		
		// retrieve results
		CountingResult[] results = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults().toArray(new CountingResult[0]);
		e.compare(results);
		// try retrieving results again and make sure they still match
		results = CountingResultCollector.getInstance().retrieveAllCountingResults().getCountingResults().toArray(new CountingResult[0]);
		e.compare(results);

		Assert.assertNotNull(results);
		Assert.assertTrue(results.length >= 1);
		for(CountingResult newResult: results) {
			newResult.logResult(false, true);
		}
	}

}
