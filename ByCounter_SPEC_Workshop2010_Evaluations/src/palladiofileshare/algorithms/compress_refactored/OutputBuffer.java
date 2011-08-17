package palladiofileshare.algorithms.compress_refactored;

import java.util.zip.CRC32;

public /*final*/ class OutputBuffer {
    private int cnt;
    private byte[] buffer;
    
    public OutputBuffer(byte[] b) {
        this.buffer = b;
    }
    
    public byte[] getBuffer() {
    	return this.buffer;
    }
    
    public long getCRC() {
        CRC32 crc32 = new CRC32();
        crc32.update(this.buffer, 0, this.cnt);
        return crc32.getValue();
    }
    
    public int getLength() {
        return this.cnt;
    }
    
    public void writeByte(byte c) {
        this.buffer[this.cnt++] = c;
    }
    
    public void writebytes(byte[] buf, int n) {
        for (int i = 0; i < n; i++) {
            this.buffer[this.cnt++] = buf[i];
        }
    }
}