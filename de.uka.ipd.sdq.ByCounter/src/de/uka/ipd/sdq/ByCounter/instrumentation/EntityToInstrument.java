package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.io.Serializable;
import java.util.UUID;

import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * Common base class for entities that can be instrumented using ByCounter.
 * @author Martin Krogmann
 */
public abstract class EntityToInstrument implements Serializable {
	
	/**
	 * Serialisation version.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * A unique identifier for tracking the entity.
	 */
	private UUID id;

	/**
	 * Initialise the id ({@link #getId()}.
	 */
	public EntityToInstrument() {
		id = UUID.randomUUID();
	}
	
	/**
	 * @return A unique identifier for tracking the entity.
	 */
	public UUID getId() {
		return this.id;
	}
	
	/**
	 * @return {@link MethodDescriptor}s for methods that are specified by this
	 * {@link EntityToInstrument}.
	 */
	public abstract MethodDescriptor[] getMethodsToInstrument();
}
