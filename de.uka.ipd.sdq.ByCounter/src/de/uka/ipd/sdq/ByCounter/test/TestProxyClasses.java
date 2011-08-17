package de.uka.ipd.sdq.ByCounter.test;

import java.lang.reflect.Proxy;

import junit.framework.Assert;

import org.junit.Test;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependency;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependencyImpl;
import de.uka.ipd.sdq.ByCounter.test.helpers.ProxyDependencyIssue;
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
		ProxyDependencyIssue target = (ProxyDependencyIssue) counter.instantiate(myMethod);;
		ProxyDependency dep = new ProxyDependencyImpl();
		target.setDependency(dep);
		counter.execute(myMethod, target, new Object[0]);

		// now try the same using a proxy class
		Object proxyObj = ProxyLogger.newInstance(dep);
		ProxyDependency proxy = (ProxyDependency) proxyObj;
		target.setDependency(proxy);
		counter.execute(myMethod, target, new Object[0]);

		proxy.calculate();
	}
}
