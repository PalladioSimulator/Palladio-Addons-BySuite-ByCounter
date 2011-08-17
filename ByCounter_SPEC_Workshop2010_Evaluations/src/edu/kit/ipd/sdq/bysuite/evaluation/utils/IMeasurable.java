package edu.kit.ipd.sdq.bysuite.evaluation.utils;

/**
 * An interface for a specific measurement. Allows initilization
 * and a call for the measurement itself.
 *
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public interface IMeasurable {
	
	/**
	 * This is called to be measured repeatedly.
	 */
	public void callForMeasuring();
	
	/**
	 * Initialize before the actual measurement happens.
	 * @return Returns true if the initialisation was successful. False otherwise.
	 */
	public boolean prepareMeasuring();
}
