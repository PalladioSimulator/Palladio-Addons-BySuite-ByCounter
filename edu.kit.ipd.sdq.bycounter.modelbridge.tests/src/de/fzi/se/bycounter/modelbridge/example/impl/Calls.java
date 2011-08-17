/**
 * 
 */
package de.fzi.se.bycounter.modelbridge.example.impl;

/**Small examples for different kinds of internal and external calls.
 * This class is used for testing purposes and does not compute meaningful results.
 * @author groenda
 */
public class Calls {
	/**Class-External dependency. */
	private static final IExternal external = new External();
	
	/**Test of handling method-internal 'calls' to Java Bytecodes.
	 * @return Meaningless value.
	 */
	public long methodInternalCall() {
		long i1 = 1;
		long i2 = 1;
		long i3 = i1 + i2;
		return i3;
	}
	
	/**Test of handling class-internal and method external calls as well as complex operations.
	 * @return Meaningless value.
	 */
	public long methodExternalCall() {
		// internal
		long result;
		// method-external, class-internal
		result = myMethod("", 2, false, new long[] {2L}, 2L);
		// internal
		return result;
	}
	
	/**Test of handling class-external calls.
	 * @return Meaningless value.
	 */
	public long classExternalCall() {
		// internal
		long result;
		// class-external
		result = external.externalCall();
		// internal
		return result;
	}
	
	/**Test if handling of java API calls.
	 * @return Meaningless value.
	 */
	public long javaApiCall() {
		// class-external
		System.out.println("abc");
		// internal
		long i1 = 1;
		long i2 = 1;
		long i3 = i1 + i2;
		return i3;
	}
	
	/** Helper method for checking mapping of functions in the output model.
	 * @param <T> Test of generic type mapping.
	 * @param myString Test of Object mapping.
	 * @param i Test of integer mapping.
	 * @param x Test of boolean mapping.
	 * @param arr Test of array mapping.
	 * @param genericArg Test of generic type mapping.
	 * @return Meaningless value.
	 */
	protected <T> int myMethod(String myString, int i, boolean x, long[] arr, T genericArg) {
		return -1;
	}
}
