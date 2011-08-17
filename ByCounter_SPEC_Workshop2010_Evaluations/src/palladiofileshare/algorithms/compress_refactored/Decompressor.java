package palladiofileshare.algorithms.compress_refactored;

/*
 * Decompress stdin to stdout. This routine adapts to the codes in the file
 * building the "string" table on-the-fly; requiring no table to be stored in
 * the compressed file. The tables used herein are shared with those of the
 * compress() routine. See the definitions above.
 */

public /*final*/ class Decompressor extends CompBase {
    private int size;
    
    private CodeTable tabPrefix;
    
    private SuffixTable tabSuffix;
    
    private DeStack deStack;
    
        /*
         * Read one code from the standard input. If EOF, return -1. Inputs: stdin
         * Outputs: code or -1 is returned.
         */
    
    public Decompressor(InputBuffer in, OutputBuffer out) {
        super(in, out);
        
        /* Check the magic number */
        if (((this.input.readByte() & 0xFF) != (Compress.magic_header[0] & 0xFF))
        || ((this.input.readByte() & 0xFF) != (Compress.magic_header[1] & 0xFF))) {
            System.err.println("stdin: not in compressed format");
        }
        
        this.maxBits = this.input.readByte(); /* set -b from file */
        this.blockCompress = this.maxBits & Compress.BLOCK_MASK;
        this.maxBits &= Compress.BIT_MASK;
        this.maxMaxCode = 1 << this.maxBits;
        if (this.maxBits > Compress.BITS) {
            System.err.println("stdin: compressed with " + this.maxBits
                    + " bits, can only handle " + Compress.BITS + " bits");
        }
        this.bitsNumber = Compress.INIT_BITS;
        this.maxCode = getMaxCode();
        
        this.offset = 0;
        this.size = 0;
        this.clearFlag = 0;
        this.freeEntry = ((this.blockCompress != 0) ? Compress.FIRST : 256);
        
        this.tabPrefix = new CodeTable();
        this.tabSuffix = new SuffixTable();
        this.deStack = new DeStack();
        
                /*
                 * As above, initialize the first 256 entries in the table.
                 */
        this.tabPrefix.clear(256);
        this.tabSuffix.init(256);
    }
    
    
    public void decompress() {
        int code, oldcode, incode;
        
        int finchar = oldcode = getCode();
        if (oldcode == -1)  {/* EOF already? */
            return; /* Get out of here */
        }
        this.output.writeByte((byte) finchar); /* first code must be 8 bits = byte */
        
        while ((code = getCode()) > -1) {
            if ((code == Compress.CLEAR) && (this.blockCompress != 0)) {
                this.tabPrefix.clear(256);
                this.clearFlag = 1;
                this.freeEntry = Compress.FIRST - 1;
                if ((code = getCode()) == -1) /* O, untimely death! */
                    break;
            }
            incode = code;
                        /*
                         * Special case for KwKwK string.
                         */
            if (code >= this.freeEntry) {
                this.deStack.push((byte) finchar);
                code = oldcode;
            }
            
                        /*
                         * Generate output characters in reverse order
                         */
            while (code >= 256) {
                this.deStack.push(this.tabSuffix.of(code));
                code = this.tabPrefix.of(code);
            }
            this.deStack.push((byte) (finchar = this.tabSuffix.of(code)));
            
                        /*
                         * And put them out in forward order
                         */
            do {
                this.output.writeByte(this.deStack.pop());
            } while (!this.deStack.isEmpty());
            
                        /*
                         * Generate the new entry.
                         */
            if ((code = this.freeEntry) < this.maxMaxCode) {
                this.tabPrefix.set(code, oldcode);
                this.tabSuffix.set(code, (byte) finchar);
                this.freeEntry = code + 1;
            }
                        /*
                         * Remember previous code.
                         */
            oldcode = incode;
        }
    }
    
    
    private int getCode() {
        int code;
        int rOff, bits;
        int bp = 0;
        
        if (this.clearFlag > 0 || this.offset >= this.size || this.freeEntry > this.maxCode) {
                        /*
                         * If the next entry will be too big for the current code size, then
                         * we must increase the size. This implies reading a new buffer
                         * full, too.
                         */
            if (this.freeEntry > this.maxCode) {
                this.bitsNumber++;
                if (this.bitsNumber == this.maxBits) {
                    this.maxCode = this.maxMaxCode; /* won't get any bigger now */
                } else {
                    this.maxCode = getMaxCode();
                }
            }
            if (this.clearFlag > 0) {
                this.bitsNumber = Compress.INIT_BITS;
                this.maxCode = getMaxCode();
                this.clearFlag = 0;
            }
            this.size = this.input.readBytes(this.buf, this.bitsNumber);
            if (this.size <= 0) {
                return -1; /* end of file */
            }
            this.offset = 0;
            /* Round size down to integral number of codes */
            this.size = (this.size << 3) - (this.bitsNumber - 1);
        }
        rOff = this.offset;
        bits = this.bitsNumber;
                /*
                 * Get to the first byte.
                 */
        bp += rOff >> 3;
        rOff &= 7;
        /* Get first part (low order bits) */
        code = ((this.buf[bp++] >> rOff) & Compress.rmask[8 - rOff]) & 0xff;
        bits -= 8 - rOff;
        rOff = 8 - rOff; /* now, offset into code word */
        /* Get any 8 bit parts in the middle ( <=1 for up to 16 bits). */
        if (bits >= 8) {
            code |= (this.buf[bp++] & 0xff) << rOff;
            rOff += 8;
            bits -= 8;
        }
        /* high order bits. */
        //	code |= (buf[bp] & Compress.rmask[bits]) << r_off; // kmd
        // Don McCauley/kmd - IBM 02/26/98
        if (bits > 0) {
            code |= (this.buf[bp] & Compress.rmask[bits]) << rOff;
        }
        this.offset += this.bitsNumber;
        
        return code;
    }
}
