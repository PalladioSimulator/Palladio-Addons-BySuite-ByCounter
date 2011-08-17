/**
 * 
 */
package de.fzi.se.bycounter.modelbridge.example.impl;

/**Implementation of IExternal for testing purposes.
 * @author groenda
 *
 */
public class External implements IExternal {

	/* (non-Javadoc)
	 * @see de.fzi.bycounter.example.impl.IExternal#externalCall()
	 */
	public int externalCall() {
		int i1 = 1;
		int i2 = 1;
		int i3 = i1 + i2;
		return i3;
	}

}
