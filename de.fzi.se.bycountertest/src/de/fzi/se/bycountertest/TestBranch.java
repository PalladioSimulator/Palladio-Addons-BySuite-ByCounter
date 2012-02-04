/**
 * 
 */
package de.fzi.se.bycountertest;

/**
 * Test for branches (return value)
 * 
 * @author groenda
 */
public class TestBranch {
	public int process(int input) {
		if (input > 0 && input < 10) {
			input++;
		}
		if (input >= 10) {
			input *= 1;
		}
		return input;
	}

}
