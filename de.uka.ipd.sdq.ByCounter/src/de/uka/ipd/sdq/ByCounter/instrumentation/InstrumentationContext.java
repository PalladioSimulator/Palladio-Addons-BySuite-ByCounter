package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.parsing.BasicBlockSerialisation;

/**
 * This class serialises all information produced by {@link BytecodeCounter} 
 * during the instrumentation phase that is necessary for execution and result 
 * evaluation.
 * 
 * @author Martin Krogmann
 *
 */
public class InstrumentationContext implements Serializable {

	/**
	 * Default filename for the {@link InstrumentationContext}.
	 */
	public static final String FILE_SERIALISATION_DEFAULT_NAME ="instrumentation_context.bcic";

	/**
	 * Version of the serialisation.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Version that the object was constructed with.
	 * @see #serialVersionUID
	 */
	private long version;

	/**
	 * Basic block definitions.
	 */
	private BasicBlockSerialisation basicBlocks;

	/**
	 * Range block definitions.
	 */
	private BasicBlockSerialisation rangeBlocks;
	
	/**
	 * Construct the instrumentation context.
	 */
	public InstrumentationContext() {
		this.version = serialVersionUID;
		this.basicBlocks = null;
		this.rangeBlocks = null;
	}
	
	private static void checkVersion(final long version) {
		if(version != serialVersionUID) {
			throw new RuntimeException("Wrong version of " 
				+ InstrumentationContext.class.getClass().getCanonicalName() 
				+ ". Was " + version + " but expected " + serialVersionUID +".");
		}
	}

	/**
	 * Serialise the context. You need to create an instance of 
	 * {@link InstrumentationContext} and set all properties.
	 * @param ic An instance of {@link InstrumentationContext}.
	 * @param file The file to write to.
	 * @throws IOException Thrown when writing to disk fails.
	 */
	public static void serialise(
			InstrumentationContext ic,
			File file) throws IOException {
		
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(file));
			outStream.writeObject(ic);
		} finally {
			if(outStream != null) {
				outStream.close();
			}
		}
	}

	/**
	 * Read a serialisation file written using the 
	 * {@link #serialise(InstrumentationContext, File)} method.
	 * @param file The file to read the serialisation from.
	 * @return An instance of {@link InstrumentationContext}
	 * @throws FileNotFoundException Thrown when the specified file cannot be 
	 * found.
	 * @throws IOException Thrown when reading fails.
	 * @throws ClassNotFoundException Thrown when the format of the serialised 
	 * file is wrong.
	 */
	public static InstrumentationContext deserialise(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		InstrumentationContext ic = new InstrumentationContext();
		ObjectInputStream outStream = null;
		try {
			outStream = new ObjectInputStream(new FileInputStream(file));
			ic = (InstrumentationContext)outStream.readObject();
			checkVersion(ic.version);
		} finally {
			if(outStream != null) {
				outStream.close();
			}
		}
		return ic;
	}

	/**
	 * @param basicBlockSerialisation Basic block definitions.
	 */
	public void setBasicBlocks(BasicBlockSerialisation basicBlockSerialisation) {
		this.basicBlocks = basicBlockSerialisation;
	}

	/**
	 * @param rangeBlockSerialisation Range block definitions.
	 */
	public void setRangeBlocks(BasicBlockSerialisation rangeBlockSerialisation) {
		this.rangeBlocks = rangeBlockSerialisation;
	}
	
	/**
	 * @return Basic block definitions when set. Null otherwise.
	 */
	public BasicBlockSerialisation getBasicBlocks() {
		return this.basicBlocks;
	}
	
	/**
	 * @return Range block definitions when set. Null otherwise.
	 */
	public BasicBlockSerialisation getRangeBlocks() {
		return this.rangeBlocks;
	}
	
	/**
	 * @return True unless basicBlocks is null or empty.
	 */
	public boolean isBasicBlocksLoaded() {
		if(this.basicBlocks == null
				|| this.basicBlocks.getBasicBlocksByMethod().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return True unless rangeBlocks is null or empty.
	 */
	public boolean isRangeBlocksLoaded() {
		if(this.rangeBlocks == null
				|| this.rangeBlocks.getBasicBlocksByMethod().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * Try to load the context from {@link #FILE_SERIALISATION_DEFAULT_NAME}.
	 * @return The loaded {@link InstrumentationContext}.
	 */
	public static InstrumentationContext loadFromDefaultPath() {
		File iContextFile = new File(InstrumentationContext.FILE_SERIALISATION_DEFAULT_NAME);
		try {
			return InstrumentationContext.deserialise(iContextFile);
		} catch (Exception e) {
			throw new RuntimeException("Could not load instrumentation context from '" + iContextFile + "'.", e);
		}
	}
}
