package palladiofileshare.algorithms.compress;

import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Replaces Harness.java from original SPEC compression
 * @author kelsaka
 *
 */
public class CompressionRunner_stupid {    
	private Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
	
    public Source_stupid SOURCE;
    public byte[][] COMPRESS_BUFFERS;
    public byte[][] DECOMPRESS_BUFFERS;
    public Compress_stupid CB;
	
	void prepareBuffers(byte[] inputFile) {
        this.CB = new Compress_stupid();
        
    	this.SOURCE = new Source_stupid(inputFile);

        //DECOMPRESS_BUFFERS = new byte[Launch.currentNumberBmThreads][Source_stupid.MAX_LENGTH];
        //COMPRESS_BUFFERS = new byte[Launch.currentNumberBmThreads][Source_stupid.MAX_LENGTH];
        this.DECOMPRESS_BUFFERS = new byte[20][Source_stupid.MAX_LENGTH]; //FIXME: 20
        this.COMPRESS_BUFFERS = new byte[20][Source_stupid.MAX_LENGTH]; //FIXME: 20
    }

	OutputBuffer_stupid runCompress(int btid) {		
    	
	    @SuppressWarnings("unused")
		OutputBuffer_stupid comprBuffer, decomprBufer;
	    comprBuffer = Compress_stupid.performAction(this.SOURCE.getBuffer(),
	    		this.SOURCE.getLength(),
	    		Compress_stupid.COMPRESS,
	            this.COMPRESS_BUFFERS[btid - 1]);
	    /*decomprBufer = CB.performAction(COMPRESS_BUFFERS[btid - 1],
	            comprBuffer.getLength(),
	            CB.UNCOMPRESS,
	            DECOMPRESS_BUFFERS[btid - 1]);*/
        this.logger.fine("src length: " + this.SOURCE.getLength() + " -- " + this.SOURCE.getCRC() + " ");
        this.logger.fine("compressed length: " + comprBuffer.getLength() + " -- " + comprBuffer.getCRC() + " ");                
        //logger.info(decomprBufer.getLength() + " " + decomprBufer.getCRC());
    
        return comprBuffer;
	}
	
    public byte[] compress(byte[] inputFile) {				
		prepareBuffers(inputFile);

		//int threadID = (int)Thread.currentThread().getId();				
		int threadID = 1; //TODO: check use of threads
		OutputBuffer_stupid outBuffer = runCompress(threadID);
		byte[] returnBytes = Arrays.copyOf(outBuffer.getBuffer(), outBuffer.getLength());
		return returnBytes;
	}

	
}
