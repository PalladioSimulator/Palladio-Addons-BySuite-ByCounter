package de.uka.ipd.sdq.ByCounter.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectInterfaceMethods;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

public class TestInternalClassDefinition {

	private static Class<?> testClass = TestSubjectInterfaceMethods.class;
	private static String testClassMethodCallTest = "public void methodA1()";
	
	
	@Test
	public void testInternalClassDefinition() {
		HashSet<String> internalClassesDef = new HashSet<String>();
		internalClassesDef.add("de.uka*");
		CountingResultCollector.getInstance().setInternalClassesDefinition(internalClassesDef);
		System.out.println("de.uka* matcht de.ukap.Test, de.uka.ipd, ...");
		Assert.assertTrue(CountingResultCollector.getInstance().isInternalClass("de.ukap.Test"));
		Assert.assertTrue(CountingResultCollector.getInstance().isInternalClass("de.uka.ipd"));
		internalClassesDef = new HashSet<String>();
		internalClassesDef.add("de.uka.Test");
		CountingResultCollector.getInstance().setInternalClassesDefinition(internalClassesDef);
		System.out.println("de.uka.Test matcht de.uka.Test, de.uka.Test.$XXX, de.uka.Test.$XXX.$YYY, ...");
		Assert.assertTrue(CountingResultCollector.getInstance().isInternalClass("de.uka.Test"));
		Assert.assertTrue(CountingResultCollector.getInstance().isInternalClass("de.uka.Test$XXX$YYY"));
		System.out.println("de.uka.Test matcht aber nicht de.uka.Test.{ENUM Y}");
		Assert.assertFalse(CountingResultCollector.getInstance().isInternalClass("de.uka.Test.{ENUM Y}"));
		System.out.println();
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
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(
				testClass.getCanonicalName(),
				testClassMethodCallTest); //$NON-NLS-1$
		
		counter.getInstrumentationParams().setInstrumentRecursively(true, 5);
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		
		counter.execute(myMethod, new Object[0]);

		// define internal classes
		Set<String> internalClassesDefinition = new HashSet<String>();
		internalClassesDefinition.add(de.uka.ipd.sdq.ByCounter.test.helpers.ClassZ.class.getCanonicalName());
		CountingResultCollector.getInstance().setInternalClassesDefinition(internalClassesDefinition);
		
		// retrieve results
		Assert.assertNotNull(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively());
		Assert.assertTrue(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively().size() > 1);
		for(CountingResult newResult: CountingResultCollector.getInstance().retrieveAllCountingResults_recursively()) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
		
		counter.getInstrumentationParams().setInstrumentRecursively(false, 0);
	}

}
