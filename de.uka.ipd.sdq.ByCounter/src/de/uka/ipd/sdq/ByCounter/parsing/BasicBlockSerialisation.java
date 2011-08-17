package de.uka.ipd.sdq.ByCounter.parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * This class is used to serialise and deserialise basic block definitions of an
 * instrumentation run.
 */
public final class BasicBlockSerialisation {
	
	public static final String FILE_BASIC_BLOCK_SERIALISATION ="BasicBlock.serialisation";
	public static final String FILE_RANGE_BLOCK_SERIALISATION ="RangeBlock.serialisation";
	
	private HashMap<String, InstructionBlockDescriptor[]> basicBlocksByMethod;
	
	public BasicBlockSerialisation() {
		this.basicBlocksByMethod = new HashMap<String, InstructionBlockDescriptor[]>();
	}
	
	@Override
	public String toString() {
		return "BasicBlockSerialization["+basicBlocksByMethod+"]";
	}
	
	/**
	 * Serialise basic block definitions. You need to create an instance of 
	 * {@link BasicBlockSerialisation} and use the 
	 * {@link #addBasicBlocksForMethod(String, InstructionBlockDescriptor[])}
	 * method to add basic block definitions.
	 * @param bbs An instance of {@link BasicBlockSerialisation}.
	 * @param file The file to write to.
	 * @throws IOException Thrown when writing to disk fails.
	 */
	public static void serialise(
			BasicBlockSerialisation bbs,
			File file) throws IOException {
		
		ObjectOutputStream outStream = null;
		try {
			outStream = new ObjectOutputStream(new FileOutputStream(file));
			outStream.writeObject(bbs.basicBlocksByMethod);
		} finally {
			if(outStream != null) {
				outStream.close();
			}
		}
	}
	
	/**
	 * Read a serialisation file written using the 
	 * {@link #serialise(BasicBlockSerialisation, File)} method.
	 * @param file The file to read the serialisation from.
	 * @return An instance of {@link BasicBlockSerialisation}.
	 * @throws FileNotFoundException Thrown when the specified file cannot be 
	 * found.
	 * @throws IOException Thrown when reading fails.
	 * @throws ClassNotFoundException Thrown when the format of the serialised 
	 * file is wrong.
	 */
	@SuppressWarnings("unchecked")
	public static BasicBlockSerialisation deserialise(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		BasicBlockSerialisation bbs = new BasicBlockSerialisation();
		ObjectInputStream outStream = null;
		try {
			outStream = new ObjectInputStream(new FileInputStream(file));
			bbs.basicBlocksByMethod = (HashMap<String, InstructionBlockDescriptor[]>) outStream.readObject();
		} finally {
			if(outStream != null) {
				outStream.close();
			}
		}
		return bbs;
	}

	/**
	 * Adds basic block definitions for a given method.
	 * @param method Method description for the method to which the basic block
	 * belongs.
	 * @param basicBlocks Basic blocks to add.
	 * method.
	 */
	public void addBasicBlocksForMethod(String method, InstructionBlockDescriptor[] basicBlocks) {
		this.basicBlocksByMethod.put(method, basicBlocks);
	}

	/**
	 * @return the basicBlocksByMethod
	 */
	public HashMap<String, InstructionBlockDescriptor[]> getBasicBlocksByMethod() {
		return basicBlocksByMethod;
	}

}
