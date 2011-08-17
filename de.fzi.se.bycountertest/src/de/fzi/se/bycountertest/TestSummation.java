/**
 * 
 */
package de.fzi.se.bycountertest;

/**Test the summation of internal and external calls.
 * @author groenda
 */
public class TestSummation implements ISimple {
	/** External class. */
	TestSummationExternal tse;
	
	public TestSummation() {
		tse = new TestSummationExternal();
	}
	
	/* (non-Javadoc)
	 * @see de.fzi.se.bycountertest.ISimple#process()
	 */
	@Override
	public void process() {
		helperMethod();
		tse.process();
	}
	
	/**Class-internal method.
	 * Can be tested if it can be included and excluded in the summation.
	 */
	public void helperMethod() {
		int i = 1;
	}

}
