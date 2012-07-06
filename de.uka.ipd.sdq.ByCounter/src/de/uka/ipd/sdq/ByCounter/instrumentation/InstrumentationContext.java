package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.parsing.InstructionBlockSerialisation;

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

	/** Default value of {@link #getBlockCountingMode()}. */
	private static final BlockCountingMode BLOCK_COUNTING_MODE_DEFAULT = BlockCountingMode.BasicBlocks;
	
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
	private InstructionBlockSerialisation basicBlocks;
	
	/**
	 * Label block definitions.
	 */
	private InstructionBlockSerialisation labelBlocks;

	/**
	 * Range block definitions.
	 */
	private InstructionBlockSerialisation rangeBlocks;
	
	/**
	 * Instrumentation region definitions.
	 */
	private Set<InstrumentationRegion> instrumentationRegions;
	
	/**
	 * The mode of counting when using block based counting.
	 */
	private BlockCountingMode blockCountingMode;
	
	/**
	 * Construct the instrumentation context.
	 */
	public InstrumentationContext() {
		this.version = serialVersionUID;
		this.basicBlocks = new InstructionBlockSerialisation();
		this.rangeBlocks = new InstructionBlockSerialisation();
		this.labelBlocks = new InstructionBlockSerialisation();
		this.instrumentationRegions = new HashSet<InstrumentationRegion>();
		this.blockCountingMode = BLOCK_COUNTING_MODE_DEFAULT;
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
	 * @return Basic block definitions when set. Null otherwise.
	 */
	public InstructionBlockSerialisation getBasicBlocks() {
		return this.basicBlocks;
	}
	
	/**
	 * @return Range block definitions when set. Null otherwise.
	 */
	public InstructionBlockSerialisation getRangeBlocks() {
		return this.rangeBlocks;
	}

	/**
	 * @return Label block definitions when set. Null otherwise.
	 */
	public InstructionBlockSerialisation getLabelBlocks() {
		return this.labelBlocks;
	}
	
	/**
	 * @return Instrumentation region definitions.
	 */
	public Set<InstrumentationRegion> getInstrumentationRegions() {
		return this.instrumentationRegions;
	}
	
	/**
	 * @return True unless basicBlocks is null or empty.
	 */
	public boolean isBasicBlocksLoaded() {
		if(this.basicBlocks == null
				|| this.basicBlocks.getInstructionBlocksByMethod().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return True unless rangeBlocks is null or empty.
	 */
	public boolean isRangeBlocksLoaded() {
		if(this.rangeBlocks == null
				|| this.rangeBlocks.getInstructionBlocksByMethod().isEmpty()) {
			return false;
		}
		return true;
	}
	
	/**
	 * @return True unless labelBlocks is null or empty.
	 */
	public boolean isLabelBlocksLoaded() {
		if(this.labelBlocks == null
				|| this.labelBlocks.getInstructionBlocksByMethod().isEmpty()) {
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

	/**
	 * @return The mode of counting when using block based counting.
	 */
	public BlockCountingMode getBlockCountingMode() {
		return this.blockCountingMode;
	}
	
	/**
	 * @param blockCountingMode The mode of counting when using block based counting.
	 */
	public void setBlockCountingMode(BlockCountingMode blockCountingMode) {
		this.blockCountingMode = blockCountingMode;
	}
}
