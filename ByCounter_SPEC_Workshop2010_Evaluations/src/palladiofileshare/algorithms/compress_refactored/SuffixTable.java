package palladiofileshare.algorithms.compress_refactored;

public /*final*/ class SuffixTable {
    protected byte tab[];
    public SuffixTable() {
        this.tab = new byte[Compress.SUFFIX_TAB_SZ];
    }
    
    public void init(int size) {
        for (int code = 0; code < size; code++) {
            this.tab[code] = (byte) code;
        }
    }
    
    public byte of(int i) {
        return this.tab[i];
    }
    
    public void set(int i, byte v) {
        this.tab[i] = v;
    }
}

