package de.uka.ipd.sdq.ByCounter.execution;

/**
 * This class represents a section of an instrumented method that is currently
 * active, i.e. is being executed.
 * @author Martin Krogmann
 *
 */
public class ActiveSection {
	
	/**
	 * Fully qualifying method name of the entered sections method.
	 */
	public String qualifyingMethodName;
	
	/**
	 * Section id with respect to the method it is part of.
	 */
	public int sectionId;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActiveSection [qualifyingMethodName=");
		builder.append(this.qualifyingMethodName);
		builder.append(", sectionId=");
		builder.append(this.sectionId);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((this.qualifyingMethodName == null) ? 0
						: this.qualifyingMethodName.hashCode());
		result = prime * result + this.sectionId;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActiveSection other = (ActiveSection) obj;
		if (this.qualifyingMethodName == null) {
			if (other.qualifyingMethodName != null)
				return false;
		} else if (!this.qualifyingMethodName
				.equals(other.qualifyingMethodName))
			return false;
		if (this.sectionId != other.sectionId)
			return false;
		return true;
	}
}
