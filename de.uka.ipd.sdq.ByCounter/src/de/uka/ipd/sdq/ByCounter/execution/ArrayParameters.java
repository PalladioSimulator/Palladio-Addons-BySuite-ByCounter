package de.uka.ipd.sdq.ByCounter.execution;


/**
 * Recorded information about array creations; counts, dimension and types.
 * All three arrays need to keep their indices in sync.
 * @author Martin Krogmann
 *
 */
public class ArrayParameters {
	/**
	 * @see CountingResult#getNewArrayCounts()
	 */
	long[] newArrayCounts;
	/**
	 * @see CountingResult#getNewArrayDim()
	 */
	int[] newArrayDim;
	/**
	 * @see CountingResult#getNewArrayTypes()
	 */
	String[] newArrayTypes;

	/**
	 * Copies array specific parameters from the {@link CountingResult}
	 * @param cr {@link CountingResult} to copy from.
	 * @return A filled instance of {@link ArrayParameters}.
	 */
	static ArrayParameters copyFromCountingResult(CountingResult cr) {
		if(cr == null) {
			throw new RuntimeException("Cannot copy ArrayParameters from CountingResult that is null.");
		}
		ArrayParameters result = new ArrayParameters();
		result.newArrayCounts = cr.getNewArrayCounts();
		result.newArrayDim = cr.getNewArrayDim();
		result.newArrayTypes = cr.getNewArrayTypes();
		if(result.newArrayCounts != null) {
			if(result.newArrayCounts.length != result.newArrayDim.length 
					|| result.newArrayCounts.length != result.newArrayTypes.length) {
				throw new RuntimeException("Error: The arrays newArrayCounts, newArrayDim and newArrayTypes need to match in length. " 
						+ "Was " + result.newArrayCounts.length + ", "
						+ result.newArrayDim.length + ", "
						+ result.newArrayTypes.length + ".");
			}
		}
		return result;
	}
}
