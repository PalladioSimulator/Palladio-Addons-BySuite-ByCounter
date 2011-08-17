/**
 * 
 */
package de.fzi.se.bycounter.modelbridge.example.impl;

/**Example for exclusion of methods for the summation component-internal classes.
 * a1, a2, and b are externally provided operations. Service effect description
 * may exist for a1, a2, and b. Hence, summation of component-internal generated
 * load (in number of byte code instruction or method invocations) should be kept
 * separate.
 * @author groenda
 *
 */
public class SummationExcludes {
	
	/**Expected test result: ILOAD 0, ..., 1x b().
	 * @return meaningless value.
	 */
	public int a1() {
		int i = 0;
		return i + b();
	}
	
	/**Expected test result: ILOAD 0, ..., ICONST 3, ... .
	 * @return Meaningless value.
	 */
	public int a2() {
		int i = 0;
		return i + c();
	}

	/**Expected test result: ICONST 3, ... .
	 * @return Meaningless value.
	 */
	public int b() {
		return c();
		
	}
	
	/**Cannot be invoked from the outside. Simulates component-internal visibility.
	 * @return Meaningless value.
	 */
	protected int c() {
		return 3;
	}
}
