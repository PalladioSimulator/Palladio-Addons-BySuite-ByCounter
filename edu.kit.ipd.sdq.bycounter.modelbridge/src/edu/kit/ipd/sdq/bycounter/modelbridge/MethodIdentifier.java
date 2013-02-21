/**
 * 
 */
package edu.kit.ipd.sdq.bycounter.modelbridge;

import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;

/**Bundles the information about a method signature and it's defining class.
 * Used to group multiple {@link LineNumberRange} by method.
 * @author groenda
 *
 */
public class MethodIdentifier {
	/** Fully qualified name of the method. */
	public String fqMethodName;
	/** Signature of the method. */
	public String signature;

	public MethodIdentifier(String fqMethodName, String descriptor) {
		this.fqMethodName = fqMethodName;
		this.signature = descriptor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MethodIdentifier) {
			MethodIdentifier id = ((MethodIdentifier)obj);
			return id.fqMethodName.equals(fqMethodName) 
				&& id.signature.equals(signature);
		}
		return super.equals(obj);
	}

}
