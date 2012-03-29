/**
 * 
 */
package de.uka.ipd.sdq.ByCounter.test.helpers;

/**
 * Implementation used to test the preservation of the execution order in resulting measurements.
 * 
 * @author groenda
 * 
 */
public class TestSubjectExecutionOrder {

	public void process() {
		int i = 0;
		i++;
		i *= 1;
	}

}
