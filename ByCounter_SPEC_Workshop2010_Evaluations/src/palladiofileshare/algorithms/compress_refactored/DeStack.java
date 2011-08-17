package palladiofileshare.algorithms.compress_refactored;

public /*final*/ class DeStack { // moved 4/15/98 dm/kmd
    /*
     * Use protected instead of private to allow access by parent class of inner
     * class. wnb 4/17/98
     */
    
    protected byte tab[];
    
    protected int index;
    
    public DeStack() {
        this.tab = new byte[Compress.STACK_SZ];
    }
    
    public boolean isEmpty() {
        return this.index == 0;
    }
    
    public byte pop() {
        return this.tab[--this.index];
    }
    
    public void push(byte c) {
        this.tab[this.index++] = c;
    }
}

