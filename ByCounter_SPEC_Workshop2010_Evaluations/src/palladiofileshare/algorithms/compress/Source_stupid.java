package palladiofileshare.algorithms.compress;

import java.util.zip.CRC32;

public class Source_stupid {
	
    static int MAX_LENGTH;
    private byte[] buffer;
    private long crc;
    private int length;
    
    public Source_stupid(byte[] inputFile) {
        this.buffer = inputFile;
        this.length = this.buffer.length;
        MAX_LENGTH = Math.max(this.length, MAX_LENGTH);        
        CRC32 crc32 = new CRC32();
        crc32.update(this.buffer, 0, this.length);
        this.crc = crc32.getValue();
    }
    
    public byte[] getBuffer() {
        return this.buffer;
    }
    
    public long getCRC() {
        return this.crc;
    }
    
    public int getLength() {
        return this.length;
    }
    

    
}
