package palladiofileshare.algorithms.compress_refactored;

public /*final*/ class HashTable { // moved 4/15/98 dm/kmd
    /*
     * Use protected instead of private to allow access by parent class of inner
     * class. wnb 4/17/98
     */
    
    protected int tab[]; // for dynamic table sizing */
    
    protected int size;
    
    public HashTable() {
        this.size = Compress.HSIZE;
        this.tab = new int[this.size];
    }
    
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.tab[i] = -1;
        }
    }
    
    public int hsize() {
        return this.size;
    }
    
    public int of(int i) {
        return this.tab[i];
    }
    
    public void set(int i, int v) {
        this.tab[i] = v;
    }
}
