package de.uka.ipd.sdq.ByCounter.execution;

/**
 * This class is used to update observers registered to 
 * {@link CountingResultCollector} when a section has been executed.
 * @author Martin Krogmann
 */
public class CountingResultSectionExecutionUpdate implements CountingResultUpdate {
	public final Integer sectionIndex;
	public CountingResultSectionExecutionUpdate(final Integer sectionIndex) {
		this.sectionIndex = sectionIndex;
	}
	@Override
	public String toString() {
		return "CountingResultSectionExecutionUpdate[sectionIndex=" + sectionIndex +"]";
	}
}