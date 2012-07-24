package de.uka.ipd.sdq.ByCounter.execution;

import de.uka.ipd.sdq.ByCounter.results.CountingResult;


/**
 * This class is used to update observers registered to 
 * {@link CountingResultCollector} when a section has been executed.
 * @author Martin Krogmann
 */
public class CountingResultSectionExecutionUpdate implements CountingResultUpdate {
	/**
	 * Index of the updated section.
	 */
	public final Integer sectionIndex;
	
	/**
	 * Counting result for the updated section.
	 */
	public final CountingResult sectionResult;
	
	/**
	 * Construct the update object.
	 * @param sectionIndex {@link #sectionIndex}
	 * @param sectionResult {@link #sectionResult}
	 */
	public CountingResultSectionExecutionUpdate(final Integer sectionIndex,
			final CountingResult sectionResult) {
		this.sectionIndex = sectionIndex;
		this.sectionResult = sectionResult;
	}
	@Override
	public String toString() {
		return "CountingResultSectionExecutionUpdate[sectionIndex=" + sectionIndex 
				+ ", sectionResult=" + sectionResult +"]";
	}
}