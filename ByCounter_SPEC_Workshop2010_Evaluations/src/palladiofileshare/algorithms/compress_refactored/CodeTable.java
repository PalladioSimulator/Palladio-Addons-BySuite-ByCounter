package palladiofileshare.algorithms.compress_refactored;

public /*final*/ class CodeTable {
    private short tab[];
    
    public CodeTable() {
        this.tab = new short[Compress.HSIZE];
    }
    
    public void clear(int size) {
        for (int code = 0; code < size; code++) {
            this.tab[code] = 0;
        }
    }
    
    public int of(int i) {
        return (int) this.tab[i] << 16 >>> 16;
    }
    
    public void set(int i, int v) {
        this.tab[i] = (short) v;
    }

}
