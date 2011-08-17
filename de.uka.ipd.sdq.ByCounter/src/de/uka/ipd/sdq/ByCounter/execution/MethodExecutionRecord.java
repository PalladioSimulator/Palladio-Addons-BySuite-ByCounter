package de.uka.ipd.sdq.ByCounter.execution;

import java.util.List;

import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This class holds method execution details, i.e. how 
 * (with which parameters etc.) was which method executed.
 */
public class MethodExecutionRecord {
	public String canonicalClassName;
	public List<MethodDescriptor> methodsToCalled;
	public List<RuntimeMethodParameters> methodCallParams;
}
