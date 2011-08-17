package palladiofileshare.algorithms.compress_refactored;

/*
 * compress (Originally: stdin to stdout -- Changed by SPEC to: memory to
 * memory)
 *
 * Algorithm: use open addressing double hashing (no chaining) on the prefix
 * code / next character combination. We do a variant of Knuth's algorithm D
 * (vol. 3, sec. 6.4) along with G. Knott's relatively-prime secondary probe.
 * Here, the modular division first probe is gives way to a faster exclusive-or
 * manipulation. Also do block compression with an adaptive reset, whereby the
 * code table is cleared when the compression ratio decreases, but after the
 * table fills. The variable-length output codes are re-sized at this point, and
 * a special CLEAR code is generated for the decompressor. Late addition:
 * construct the table according to file size for noticeable speed improvement
 * on small files. Please direct questions about this implementation to
 * ames!jaw.
 */

public /*final*/ class Compressor extends CompBase {
    
    private final static int CHECK_GAP = 10000; /* ratio check interval */
    
    private int ratio;
    
    private int checkpoint;
    
    private int inCount; /* length of input */
    
    private int outCount; /* # of codes output */
    
    private int bytesOut; /* length of compressed output */
    
    private HashTable htab;
    
    private CodeTable codetab;
    
    public Compressor(InputBuffer in, OutputBuffer out) {
        super(in, out);
        if (this.maxBits < Compress.INIT_BITS) {
            this.maxBits = Compress.INIT_BITS;
        }
        if (this.maxBits > Compress.BITS) {
            this.maxBits = Compress.BITS;
        }
        this.maxMaxCode = 1 << this.maxBits;
        this.bitsNumber = Compress.INIT_BITS;
        this.maxCode = getMaxCode();
        
        this.offset = 0;
        this.bytesOut = 3; /* includes 3-byte header mojo */
        this.outCount = 0;
        this.clearFlag = 0;
        this.ratio = 0;
        this.inCount = 1;
        this.checkpoint = CHECK_GAP;
        this.freeEntry = ((this.blockCompress != 0) ? Compress.FIRST : 256);
        
        this.htab = new HashTable(); // dm/kmd 4/10/98
        this.codetab = new CodeTable();
        
        this.output.writeByte(Compress.magic_header[0]);
        this.output.writeByte(Compress.magic_header[1]);
        this.output.writeByte((byte) (this.maxBits | this.blockCompress));
    }
    
        /*
         * Output the given code. Inputs: code: A n_bits-bit integer. If == -1, then
         * EOF. This assumes that n_bits = < (long)wordsize - 1. Outputs: Outputs
         * code to the file. Assumptions: Chars are 8 bits long. Algorithm: Maintain
         * a BITS character long buffer (so that 8 codes will fit in it exactly).
         */
    
    /* table clear for block compress */
    @SuppressWarnings("unused")
	private void clBlock() {
        int rat;
        
        this.checkpoint = this.inCount + CHECK_GAP;
        
        if (this.inCount > 0x007fffff) { /* shift will overflow */
            rat = this.bytesOut >> 8;
            if (rat == 0) { /* Don't divide by zero */
                rat = 0x7fffffff;
            } else {
                rat = this.inCount / rat;
            }
        } else {
            rat = (this.inCount << 8) / this.bytesOut; /* 8 fractional bits */
        }
        if (rat > this.ratio) {
            this.ratio = rat;
        } else {
            this.ratio = 0;
            this.htab.clear();
            this.freeEntry = Compress.FIRST;
            this.clearFlag = 1;
            output((int) Compress.CLEAR);
        }
    }
    
    public void compress() {
        int fcode;
        int i = 0;
        int c;
        int disp;
        int hshift = 0;
        
        int ent = this.input.readByte();
        
        for (fcode = this.htab.hsize(); fcode < 65536; fcode *= 2) {
            hshift++;
        }
        hshift = 8 - hshift; /* set hash code range bound */
        
        int hsizeReg = this.htab.hsize();
        this.htab.clear(); /* clear hash table */
        
        /*next_byte: */while ((c = this.input.readByte()) != -1) {
            this.inCount++;
            fcode = (((int) c << this.maxBits) + ent);
            i = ((c << hshift) ^ ent); /* xor hashing */
            int temphtab = this.htab.of(i);
            if (temphtab == fcode) {
                ent = this.codetab.of(i);
                /*continue next_byte;*/
            }else{
            	boolean continueAbove = false;
	            if (temphtab >= 0) { /* non-empty slot dm kmd 4/15 */
	                disp = hsizeReg - i; /* secondary hash (after G. Knott) */
	                if (i == 0) {
	                    disp = 1;
	                }
	                do {
	                    if ((i -= disp) < 0) {
	                        i += hsizeReg;
	                    }
//	                    temphtab = htab.of(i);
	                    if (temphtab == fcode) {
	                        ent = this.codetab.of(i);
	                        continueAbove=true; /*continue next_byte;*/
	                    }
	                } while (temphtab > 0 && !continueAbove);
	            }
	            if(!continueAbove){
		            output(ent);
		            this.outCount++;
		            ent = c;
		            if (this.freeEntry < this.maxMaxCode) {
//		                codetab.set(i, freeEntry++); /* code -> hashtable */
//		                htab.set(i, fcode);
		            } else if (this.inCount >= this.checkpoint && this.blockCompress != 0) {
//		                clBlock();
		            }
	            }
            }
        }
        /*
         * Put out the final code.
         */
        output(ent);
        this.outCount++;
        output(-1);
        return;
    }
    
    private void output(int code) {
        int rOff = this.offset, bits = this.bitsNumber;
        int bp = 0;
        
        if (code >= 0) {
                        /*
                         * Get to the first byte.
                         */
            bp += rOff >> 3;
            rOff &= 7;
                        /*
                         * Since code is always >= 8 bits, only need to mask the first hunk
                         * on the left.
                         */
            this.buf[bp] = (byte) ((this.buf[bp] & Compress.rmask[rOff]) | (code << rOff)
            & Compress.lmask[rOff]);
            bp++;
            bits -= 8 - rOff;
            code >>= 8 - rOff;
            /* Get any 8 bit parts in the middle ( <=1 for up to 16 bits). */
            if (bits >= 8) {
                this.buf[bp++] = (byte) code;
                code >>= 8;
                bits -= 8;
            }
            /* Last bits. */
            if (bits != 0) {
                this.buf[bp] = (byte) code;
            }
            this.offset += this.bitsNumber;
            if (this.offset == (this.bitsNumber << 3)) {
                bp = 0;
                bits = this.bitsNumber;
                this.bytesOut += bits;
                do {
                    this.output.writeByte(this.buf[bp++]);
                } while (--bits != 0);
                this.offset = 0;
            }
            
                        /*
                         * If the next entry is going to be too big for the code size, then
                         * increase it, if possible.
                         */
            if (this.freeEntry > this.maxCode || this.clearFlag > 0) {
                                /*
                                 * Write the whole buffer, because the input side won't discover
                                 * the size increase until after it has read it.
                                 */
                if (this.offset > 0) {
                    this.output.writebytes(this.buf, this.bitsNumber);
                    this.bytesOut += this.bitsNumber;
                }
                this.offset = 0;
                
                if (this.clearFlag != 0) {
                    this.bitsNumber = Compress.INIT_BITS;
                    this.maxCode = getMaxCode();
                    this.clearFlag = 0;
                } else {
                    this.bitsNumber++;
                    if (this.bitsNumber == this.maxBits) {
                        this.maxCode = this.maxMaxCode;
                    } else {
                        this.maxCode = getMaxCode();
                    }
                }
            }
        } else {
                        /*
                         * At EOF, write the rest of the buffer.
                         */
            if (this.offset > 0) {
                this.output.writebytes(this.buf, ((this.offset + 7) / 8));
            }
            this.bytesOut += (this.offset + 7) / 8;
            this.offset = 0;
        }
    }
    
}