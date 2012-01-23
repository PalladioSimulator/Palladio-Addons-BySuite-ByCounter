package de.uka.ipd.sdq.ByCounter.execution;

import java.util.Set;

/**
 * This class holds a collection of settings that relate to the execution of
 * instrumented Java byte code and the details of counting or aggregating the 
 * instrumentation  results.
 * 
 * @author Martin Krogmann
 *
 */
public class ExecutionSettings implements Cloneable {

	/**
	 * This character ('{@value #INTERNAL_CLASSES_DEFINITION_WILDCARD_CHAR}') is
	 * used in the definition of internal classes.
	 * @see #setInternalClassesDefinition(Set) 
	 */
	public static final char INTERNAL_CLASSES_DEFINITION_WILDCARD_CHAR = '*';

	/** Default value of {@link #getInternalClassesDefinition()}. */
	public static final Set<String> INTERNAL_CLASSES_DEFINITION_DEFAULT = null;
	
	/** Default value of {@link #getCountingResultCollectorMode()}. */
	private static final CountingResultCollectorMode COUNTING_RESULT_COLLECTOR_MODE_DEFAULT = CountingResultCollectorMode.UseReportingMethodChoiceByInstrumentedMethods;

	/**
	 * Classes defined as internal when using recursive result retrieval.
	 * 
	 * @see #setInternalClassesDefinition(Set)
	 */
	private Set<String> internalClassesDefinition;
	
	/**
	 * Counting mode to use.
	 */
	private CountingResultCollectorMode countingResultCollectorMode;
	
	/**
	 * Construct {@link ExecutionSettings} by setting every field to it's 
	 * default value. 
	 */
	public ExecutionSettings() {
		this.countingResultCollectorMode = COUNTING_RESULT_COLLECTOR_MODE_DEFAULT;
		this.internalClassesDefinition = INTERNAL_CLASSES_DEFINITION_DEFAULT;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public ExecutionSettings clone() {
		ExecutionSettings copy = null;
		
		try {
			copy = (ExecutionSettings) super.clone();
		} catch (CloneNotSupportedException e) {
			// object.clone() cannot fail
			return null;
		}
		// copy fields
		copy.internalClassesDefinition = this.internalClassesDefinition;
		copy.countingResultCollectorMode = this.countingResultCollectorMode;
		
		return copy;
	}

	/**
	 * Uses {@link #getInternalClassesDefinition()} to decide whether the given 
	 * name is considered an internal class.
	 * @param qualifyingMethodName Name of the class to check.
	 * @return True when the class is internal.
	 */
	public boolean isInternalClass(String qualifyingMethodName) {
		if(this.internalClassesDefinition == null) {
			return true;
		}
		// find public parent class in case of internal class
		int i = qualifyingMethodName.indexOf('$');
		String className = qualifyingMethodName;
		if(i >= 0) {
			className = qualifyingMethodName.substring(0, i);
		}
		
		for(String s : this.internalClassesDefinition) {
			if(s.charAt(s.length() - 1) == INTERNAL_CLASSES_DEFINITION_WILDCARD_CHAR) {
				final String prefix = s.substring(0, s.length() - 1);
				// prefix matching
				if(qualifyingMethodName.startsWith(prefix)) {
					return true;
				}
			} else {
				// exact matching 
				if(className.equals(s)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param internalClassesDefinition The definition of internal classes.
	 * When adding up results when retrieving results recursively, this 
	 * definition allows for adding up only results for classes defined as 
	 * internal. A value of null means all classes are considered internal.
	 * <p>
	 * For each string, specifying a 
	 * '{@value #INTERNAL_CLASSES_DEFINITION_WILDCARD_CHAR}' at the end enabled 
	 * prefix matching, 
	 * i.e. all classes with the prefix are matched. If a string specifies a 
	 * class name, non-public/internal classes are also considered internal.
	 * </p>
	 * <p>
	 * Examples:
	 * <code>
	 * <list>
	 * <li>de.uka{@value #INTERNAL_CLASSES_DEFINITION_WILDCARD_CHAR} matches de.ukap.Test, de.uka.ipd, ...</li>
	 * <li>de.uka.Test matches de.uka.Test, de.uka.Test$XXX, de.uka.Test$XXX$YYY, ...</li>
	 * <li>de.uka.Test matches de.uka.Test, but not de.uka.Test.{ENUM Y}</li>
	 * </list>
	 * </code>
	 * </p>
	 */
	public void setInternalClassesDefinition(
			Set<String> internalClassesDefinition) {
		this.internalClassesDefinition = internalClassesDefinition;
	}

	/**
	 * @return The definition of internal classes.
	 * @see #setInternalClassesDefinition(Set)
	 */
	public Set<String> getInternalClassesDefinition() {
		return internalClassesDefinition;
	}

	/**
	 * @return The mode in which the {@link CountingResultCollector} operates.
	 */
	public CountingResultCollectorMode getCountingResultCollectorMode() {
		return countingResultCollectorMode;
	}

	/**
	 * @param countingResultCollectorMode The mode in which the 
	 * {@link CountingResultCollector} operates.
	 */
	public void setCountingResultCollectorMode(
			CountingResultCollectorMode countingResultCollectorMode) {
		this.countingResultCollectorMode = countingResultCollectorMode;
	}

}
