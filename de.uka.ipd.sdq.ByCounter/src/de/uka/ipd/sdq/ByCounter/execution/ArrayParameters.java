package de.uka.ipd.sdq.ByCounter.execution;

import java.util.ArrayList;



/**
 * Recorded information about array creations; counts, dimension and types.
 * All three arrays need to keep their indices in sync.
 * @author Martin Krogmann
 *
 */
public class ArrayParameters {
	/**
	 * @see CountingResultBase#getNewArrayCounts()
	 */
	public long[] newArrayCounts;
	/**
	 * @see CountingResultBase#getNewArrayDim()
	 */
	public int[] newArrayDim;
	/**
	 * @see CountingResultBase#getNewArrayTypes()
	 */
	public String[] newArrayTypes;

	/**
	 * Copies array specific parameters from the {@link CountingResultBase}
	 * @param cr {@link CountingResultBase} to copy from.
	 * @return A filled instance of {@link ArrayParameters}.
	 */
	public static ArrayParameters copyFromCountingResult(CountingResultBase cr) {
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
	
	/**
	 * Add up two {@link ArrayParameters}.
	 * @param left First operand.
	 * @param right Second operand.
	 * @return The combined {@link ArrayParameters}.
	 */
	public static ArrayParameters add(
			ArrayParameters left,
			ArrayParameters right
			) {
		
		ArrayParameters result = new ArrayParameters();
		
		if(left.newArrayCounts == null || right.newArrayCounts == null) {
			// nothing to add
			return result;
		}
		
		ArrayList<Long> resultNewArrayCounts = new ArrayList<Long>();
		ArrayList<Integer> resultNewArrayDim = new ArrayList<Integer>();
		ArrayList<String> resultNewArrayType = new ArrayList<String>();
		// copy array counts from 'left':
		for(int i = 0; i < left.newArrayCounts.length; i++) {
			resultNewArrayCounts .add(left.newArrayCounts[i]);
			resultNewArrayDim.add(left.newArrayDim[i]);
			resultNewArrayType.add(left.newArrayTypes[i]);
		}

		int rightDim;
		String rightType;
		long rightCount;
		int resultDim;
		String resultType;
		boolean currentCountDone;
		// now merge in the array counts from 'right'
		for(int i = 0; i < right.newArrayCounts.length; i++) {
			rightDim = right.newArrayDim[i];
			rightType = right.newArrayTypes[i];
			rightCount = right.newArrayCounts[i];
			currentCountDone = false;
			// is this entry in the list already?
			for(int j = 0; j < resultNewArrayCounts.size() && !currentCountDone; j++) {
				resultDim = resultNewArrayDim.get(j);
				resultType = resultNewArrayType.get(j);
				if(rightDim == resultDim
						&& rightType.equals(resultType)) {
					// add the count value
					resultNewArrayCounts.set(j,
							resultNewArrayCounts.get(j) + rightCount);
					currentCountDone = true;
				}
			}
			if(!currentCountDone) {
				// we have a new type/dimension; add it to the result
				resultNewArrayCounts.add(rightCount);
				resultNewArrayDim.add(rightDim);
				resultNewArrayType.add(rightType);
			}
		}
		// create arrays from the arraylists
		final int numOfNewArray = resultNewArrayCounts.size();
		result.newArrayCounts = new long[numOfNewArray];
		result.newArrayDim = new int[numOfNewArray];
		result.newArrayTypes = resultNewArrayType.toArray(new String[numOfNewArray]);
		for(int i = 0; i < numOfNewArray; i++) {
			result.newArrayCounts[i] = resultNewArrayCounts.get(i);
			result.newArrayDim[i] = resultNewArrayDim.get(i);
			result.newArrayTypes[i] = resultNewArrayType.get(i);
		}
		
		return result;
	}

}
