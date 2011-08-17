package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.util.logging.Logger;

/**
 * Tracing Hook for debugging purposes.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
*/
public final class TracingCharacterisationHook implements ICharacterisationHook {
	
	/**
	 * java.util logger for this class.
	 */
	Logger log = Logger.getLogger(this.getClass().getCanonicalName());

	public void methodCallHook(
			@SuppressWarnings("unused") MethodCountMethodAdapter methodCountMethodAdapter,
			@SuppressWarnings("unused") InstrumentationParameters instrumentationParameters, 
			@SuppressWarnings("unused") int opcode,
			String owner, 
			String name, 
			String desc) {
		this.log.info("[TracingCharacterisationHook] Method call (" + owner + "." + name + "):");
		this.log.info("Descriptor: " + desc);
	}

	public void methodReturnHook(
			@SuppressWarnings("unused") MethodCountMethodAdapter methodCountMethodAdapter, 
			@SuppressWarnings("unused") InstrumentationParameters instrumentationParameters) {
		this.log.info("[TracingCharacterisationHook] Method return");
	}

	public void methodStartHook(
			@SuppressWarnings("unused") MethodCountMethodAdapter methodCountMethodAdapter,
			@SuppressWarnings("unused") InstrumentationParameters instrumentationParameters, 
			@SuppressWarnings("unused") int access,
			String name, 
			String desc, 
			String signature,
			String[] exceptions) {
		this.log.info("[TracingCharacterisationHook] Method start (" + name + "):");
		this.log.info("Descriptor: " + desc);
		this.log.info("Signature: " + signature);
		if(exceptions != null) {
			for(String except : exceptions) {
				this.log.info("Exception: " + except);
			}
		}
	}
}
