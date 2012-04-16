package de.uka.ipd.sdq.ByCounter.execution;

import java.io.File;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.parsing.BasicBlockSerialisation;

public class BlockDefinitionContext {
	/**
	 * The basic block definitions in case of basic block usage.
	 * Keep in memory to avoid the need for repeated parsing.
	 */
	public BasicBlockSerialisation bbSerialisation;
	/**
	 * The range block definitions in case of range block usage.
	 * Keep in memory to avoid the need for repeated parsing.
	 */
	public BasicBlockSerialisation rbSerialisation;


	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private Logger log;

	public BlockDefinitionContext() {
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
	}
	

	/**
	 * Load the basic block definitions. Only necessary once after 
	 * instrumentation.
	 */
	public void tryToLoadBasicBlockSerialisation() {
			File file = new File(BasicBlockSerialisation.FILE_BASIC_BLOCK_SERIALISATION);
			if(file.exists()) {
				try {
					this.bbSerialisation = BasicBlockSerialisation.deserialise(file);
					log.fine("BasicBlockSerialisation: "+this.bbSerialisation.toString());
				} catch (Exception e) {
					// n
					log.severe("Could not load BasicBlockSerialisation "+this.bbSerialisation.toString());
					log.severe(e.getMessage());
					e.printStackTrace();
				}
			} else {
				log.fine("No BasicBlockSerialisation loaded.");
			}
	}
	
	/**
	 * Load the range block definitions. Only necessary once after 
	 * instrumentation.
	 */
	public void loadRangeBlockSerialisation() {
		try {
			this.rbSerialisation = BasicBlockSerialisation.deserialise(
					new File(BasicBlockSerialisation.FILE_RANGE_BLOCK_SERIALISATION));
			log.fine("RangeBlockSerialisation: "+this.rbSerialisation.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateRangeBlocks() {
		if(this.rbSerialisation == null
				|| this.rbSerialisation.getBasicBlocksByMethod().isEmpty()) {
			loadRangeBlockSerialisation();
		}
	}


	public void updateBasicBlocks() {
		if(this.bbSerialisation == null 
				|| this.bbSerialisation.getBasicBlocksByMethod().isEmpty()) {
			this.tryToLoadBasicBlockSerialisation();
		}
	}
}