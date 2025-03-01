package spec.benchmarks.compress_inlined;

public /*static*/ class DeStack { // moved 4/15/98 dm/kmd
    /*
     * Use protected instead of private to allow access by parent class of inner
     * class. wnb 4/17/98
     */
    
    protected int index;
    
    protected byte tab[];
    
    public DeStack() {
        tab = new byte[Compress.STACK_SZ];
    }
    
    public boolean isEmpty() {
        return index == 0;
    }
    
    public byte pop() {
        return tab[--index];
    }
    
    public void push(byte c) {
        tab[index++] = c;
    }
}
