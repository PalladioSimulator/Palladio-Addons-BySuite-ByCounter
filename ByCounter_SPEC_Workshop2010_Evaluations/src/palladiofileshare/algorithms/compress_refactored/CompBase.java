package palladiofileshare.algorithms.compress_refactored;

public class CompBase {
    protected int bitsNumber; /* number of bits/code */
    
    protected int maxBits; /* user settable max # bits/code */
    
    protected int maxCode; /* maximum code, given n_bits */
    
    protected int maxMaxCode; /* should NEVER generate this code */
    
    protected int offset;
    
    protected int blockCompress;
    
    protected int freeEntry; /* first unused entry */
    
    protected int clearFlag;
    
    protected InputBuffer input;
    
    protected OutputBuffer output;
    
    protected byte buf[];
    
    public CompBase(InputBuffer in, OutputBuffer out) {
        this.input = in;
        this.output = out;
        this.maxBits = Compress.BITS;
        this.blockCompress = Compress.BLOCK_MASK;
        this.buf = new byte[Compress.BITS];
    }
    
    public int getMaxCode() {
        return ((1 << (this.bitsNumber)) - 1);
    }
}

