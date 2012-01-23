/**
 * 
 */
package de.uka.ipd.sdq.ByCounter.instrumentation;

/**
 * Modes of grouping instructions when instrumenting.
 * @author Martin Krogmann
 */
public enum BlockCountingMode {
	/**
	 * Instructions are counted individually.
	 */
	NoBlocks,
	/**
	 * Instructions are grouped as basic blocks.
	 */
	BasicBlocks,
	/**
	 * Instructions are grouped into line number ranges.
	 */
	RangeBlocks
}
