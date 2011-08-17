package palladiofileshare.algorithms.compress_refactored;

import java.util.zip.CRC32;

public class Source {
	
    static int MAX_LENGTH;
    private byte[] buffer;
    private long crc;
    private int length;
    
    public Source(byte[] inputFile) {
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
