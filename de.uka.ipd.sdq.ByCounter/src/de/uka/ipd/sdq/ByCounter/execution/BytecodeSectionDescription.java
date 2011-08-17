package de.uka.ipd.sdq.ByCounter.execution;


/**
 * A small class to describe bytecode sections (for partial instrumentation).
 * TODO why isn't it used anywhere?
 * 
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class BytecodeSectionDescription {
	/**
	 * TODO
	 */
	public static final int DESCRIBED_BY_LINE_NUMBERS = 1;
	
	/**
	 * TODO
	 */
	public int describedBy;

	/**
	 * TODO
	 */
	public int endingLineNr_incl;
	
	/**
	 * TODO
	 */
	public int startingLineNr_incl;
	
	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return "BytecodeSectionDescription" +
				"["+this.startingLineNr_incl+"-"+this.endingLineNr_incl+"]";
	}
	
}
