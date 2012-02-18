package de.uka.ipd.sdq.ByCounter.test;

import java.lang.reflect.Proxy;

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
		Object target0 = counter.instantiate(myMethod);
		ProxyDependencyIssue pdi = new ProxyDependencyIssue();
		ProxyDependencyIssue target = null;
		
		// Problem: Anstatt alles mit ByCounter zu laden und auszuführen, wird 
		// hier manuell die mit ByCounter instrumentierte Klasse aus ByCounter 
		// geholt und dann zusammen mit den uninstrumentierten Klassen verwendet.
		// Da instrumentierte Klasse != uninstrumentierte Klasse muss es 
		// natürlich zu Problemen kommen.
		
		target = (ProxyDependencyIssue) counter.instantiate(myMethod);
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
