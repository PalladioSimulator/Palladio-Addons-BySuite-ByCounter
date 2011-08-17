/**
 * 
 */
package de.fzi.se.bycountertest;

/**Implementation used to test the preservation of the execution order in resulting measurements.
 * @author groenda
 *
 */
public class TestExecutionOrder implements ISimple {

	/* (non-Javadoc)
	 * @see de.fzi.se.bycountertest.ISimple#process()
	 */
	@Override
	public void process() {
		int i = 0;
		i++;
		i *= 1;
	}

}
