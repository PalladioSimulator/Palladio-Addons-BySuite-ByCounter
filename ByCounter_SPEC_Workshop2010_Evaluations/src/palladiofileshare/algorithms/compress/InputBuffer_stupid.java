package palladiofileshare.algorithms.compress;

public final class InputBuffer_stupid {
    private int cnt;
    private int current;
    private byte[] buffer;
    
    public InputBuffer_stupid(int c, byte[] b) {
        this.cnt = c;
        this.buffer = b;
    }
    
    public int readByte() {
        return this.cnt-- > 0 ? (this.buffer[this.current++] & 0x00FF) : -1;
    }
    
    public int readBytes(byte[] buf, int n) {
        if (this.cnt <= 0)
            return -1;
        int num = Math.min(n, this.cnt);
        for (int i = 0; i < num; i++) {
            buf[i] = this.buffer[this.current++];
            this.cnt--;
        }
        return num;
    }
}
