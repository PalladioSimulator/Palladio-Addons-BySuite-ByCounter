package de.uka.ipd.sdq.ByCounter.test;

import java.lang.reflect.Proxy;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.Opcodes;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.test.framework.expectations.Expectation;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependency;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependencyImpl;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependencyIssue;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependencyIssueInterface;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyLogger;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * Test of ByCounter using Java {@link Proxy} classes.
 */
public class TestProxyClasses {

	/**
	 * This instruments a method and ...
	 */
	@Test
	public void testProxyClass() {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor.
		BytecodeCounter counter = new BytecodeCounter();
		counter.getInstrumentationParams().setInstrumentRecursively(true);

		//2. Specify the method to be instrumented (several methods are supported as well)
		String className = ProxyDependencyIssue.class.getCanonicalName();
		MethodDescriptor myMethod = new MethodDescriptor(
				className,
				"public void doSomething()");


		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		
		// create an instance of the default implementation of ProxyDependeny
		ProxyDependencyIssueInterface target0 = (ProxyDependencyIssueInterface) counter.instantiate(myMethod);
		Assert.assertTrue("The object instantiated by ByCounter does not have the correct type.", 
				target0 instanceof ProxyDependencyIssueInterface);
		ProxyDependencyIssueInterface target = null;
	
		// get an instance using ByCounter and cast it to the interface
		target = (ProxyDependencyIssueInterface) counter.instantiate(myMethod);
		ProxyDependency dep = new ProxyDependencyImpl();
		target.setDependency(dep);
		counter.execute(myMethod, target, new Object[0]);
		
		// define an expectation
		MethodDescriptor mCalculate = new MethodDescriptor(ProxyDependency.class.getCanonicalName(), 
				"public void calculate()");
		Expectation expectation = new Expectation();
		expectation.add().add(Opcodes.ALOAD, 1)
						 .add(Opcodes.GETFIELD, 1)
						 .add(Opcodes.INVOKEINTERFACE, 1)
						 .add(Opcodes.RETURN, 1)
						 .add(mCalculate.getCanonicalMethodName(), 1);

		// check the result
		CountingResultCollector countingResultCollector = CountingResultCollector.getInstance();
		CountingResult[] observation = countingResultCollector.retrieveAllCountingResults().toArray(new CountingResult[0]);
		expectation.compare(observation);
		countingResultCollector.clearResults();

		// now try the same using a proxy class
		Object proxyObj = ProxyLogger.newInstance(dep);
		ProxyDependency proxy = (ProxyDependency) proxyObj;
		target.setDependency(proxy);
		counter.execute(myMethod, target, new Object[0]);

		proxy.calculate();
		
		// check the result
		observation = countingResultCollector.retrieveAllCountingResults().toArray(new CountingResult[0]);
		expectation.compare(observation);
		countingResultCollector.clearResults();
	}
}
