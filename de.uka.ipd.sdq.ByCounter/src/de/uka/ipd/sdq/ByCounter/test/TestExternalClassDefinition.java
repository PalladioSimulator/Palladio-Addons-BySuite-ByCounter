package de.uka.ipd.sdq.ByCounter.test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedMethod;
import de.uka.ipd.sdq.ByCounter.results.CountingResult;
import de.uka.ipd.sdq.ByCounter.results.ResultCollection;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.SectionExpectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.ReferenceExternalClass;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject;
import de.uka.ipd.sdq.ByCounter.utils.InvocationResultData;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests the behaviour of ByCounter when dealing with classes
 * used by instrumented code that have been loaded by the system 
 * {@link ClassLoader} before invoking ByCounter.
 *
 * @since 0.1
 * @version 2.0
 * @author Martin Krogmann
 */
@RunWith(Parameterized.class)
public class TestExternalClassDefinition extends AbstractByCounterTest {
	
    /**
     * This constructor is used by the Parameterized runner for running tests with different
     * parameters.
     *
     * @param params
     *            {@link InstrumentationParameters} template for the counting setup.
     */
    public TestExternalClassDefinition(final InstrumentationParameters params) {
        super(params);
    }

    /**
     * This test instruments and executes a test subject that returns 
     * the class {@link TestSubject}. An exception should be thrown because the 
     * class is instantiated by one {@link ClassLoader} and then used with the 
     * definition of the other {@link ClassLoader}.
     */
    @Test
    public void testExpectedFailure() {
        // initialize ByCounter
        BytecodeCounter counter = setupByCounter();
        MethodDescriptor methodExecute = new MethodDescriptor(ReferenceExternalClass.class.getCanonicalName(), 
        		"public de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject execute()");
        List<EntityToInstrument> entitiesToInstrument = new LinkedList<EntityToInstrument>();
       	entitiesToInstrument.add(new InstrumentedMethod(methodExecute));
        counter.addEntityToInstrument(entitiesToInstrument);
        counter.instrument();
        Object instantiated = counter.instantiate(methodExecute);
        // execute with ()
        Object[] executionParameters = new Object[] {};
        System.out.println("executing");
        InvocationResultData executionResult = counter.execute(methodExecute, instantiated, executionParameters);
        
        boolean exceptionThrown = false;
        try {
        // this throws an expectation
        TestSubject ts2 = (TestSubject) executionResult.returnValue;
        ts2.printTest();
        } catch(ClassCastException cce) {
        	exceptionThrown = true;
        }
        Assert.assertTrue("Expected a ClassCastException to be thrown.", exceptionThrown);
    }
    
    /**
     * This test instruments and executes a test subject that returns 
     * the class {@link TestSubject}. By defining the class {@link TestSubject}
     * as external, no exception should be thrown.
     */
    @Test
    public void testExternalDefinition() {
        // define expectations
        Expectation e = new Expectation(false);
        e.add(createExecuteExpectations());
        // initialize ByCounter
        BytecodeCounter counter = setupByCounter();
        MethodDescriptor methodExecute = new MethodDescriptor(ReferenceExternalClass.class.getCanonicalName(), 
        		"public de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject execute()");
        List<EntityToInstrument> entitiesToInstrument = new LinkedList<EntityToInstrument>();
       	entitiesToInstrument.add(new InstrumentedMethod(methodExecute));
        counter.addEntityToInstrument(entitiesToInstrument);
        counter.instrument();
        
        // define TestSubject as external
        Set<String> externalClasses = new HashSet<String>();
        externalClasses.add(TestSubject.class.getCanonicalName());
        counter.getExecutionSettings().setExternalToClassLoaderClassesDefinition(externalClasses);
        Object instantiated = counter.instantiate(methodExecute);
        // execute with ()
        Object[] executionParameters = new Object[] {};
        System.out.println("executing");
        InvocationResultData executionResult = counter.execute(methodExecute, instantiated, executionParameters);
        
        boolean exceptionThrown = false;
        try {
        // this throws an expectation
        TestSubject ts2 = (TestSubject) executionResult.returnValue;
        ts2.printTest();
        } catch(ClassCastException cce) {
        	exceptionThrown = true;
        	throw cce;
        }
        Assert.assertFalse("A unexpected ClassCastException was thrown.", exceptionThrown);

        ResultCollection resultCollection = CountingResultCollector.getInstance().retrieveAllCountingResults();
		CountingResult[] results = resultCollection.getCountingResults().toArray(new CountingResult[0]);
        Assert.assertTrue("No or not enough results counted", results.length >= 1);
        for (CountingResult r : results) {
        	r.logResult(false, true);
        }
        // compare
        e.compare(results);
    }
    
    /**
     * @return The Expectations for {@link ReferenceExternalClass#execute()}.
     */
    private static SectionExpectation createExecuteExpectations() {
    	SectionExpectation s = new SectionExpectation();
    	s.add(Opcodes.ICONST_0, 1)
    	.add(Opcodes.ICONST_2, 3)
    	.add(Opcodes.ILOAD, 3)
    	.add(Opcodes.ALOAD, 3)
    	.add(Opcodes.ISTORE, 1)
    	.add(Opcodes.IINC, 2)
    	.add(Opcodes.IF_ICMPLT, 3)
    	.add(Opcodes.GOTO, 1)
    	.add(Opcodes.ARETURN, 1)
    	.add(Opcodes.GETFIELD, 3)
    	.add(Opcodes.INVOKEVIRTUAL, 2)
    	.add("de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject.printTest()V", 2)
    	;
    	return s;
    }
}
