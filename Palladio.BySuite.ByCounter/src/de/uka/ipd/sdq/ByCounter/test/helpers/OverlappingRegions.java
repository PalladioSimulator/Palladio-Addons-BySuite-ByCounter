package de.uka.ipd.sdq.ByCounter.test.helpers;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion;

/**
 * Class that allows for the demonstration of overlapping 
 * {@link InstrumentedRegion}s.
 * Since InstrumentationRegions rely on line number information, this class
 * is sensitive to formatting changes.
 * @author Martin Krogmann
 *
 */
public class OverlappingRegions {

	/**
	 * Method containing the start point of region A.
	 */
	public void startA() {
		int i = 0;
		stopCommon("" + i);
	}
	
	/**
	 * Method containing the start point of region B.
	 */
	public void startB() {
		double d = 1.1;
		stopCommon("" + d);
	}
	
	/**
	 * Method containing the stop point of both region A and region B.
	 */
	public boolean stopCommon(final String input) {
		boolean b = true;
		System.out.println("StopCommon: " + b + input);
		return false;
	}
	
	/**
	 * Execute both start methods.
	 */
	public void execute() {
		startA();
		startB();
	}
}
