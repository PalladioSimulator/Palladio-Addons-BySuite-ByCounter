package experimental;

public class Test {

	public int add(int a, int b){
		return a+b;
	}

	int a,b,c;
	public void addFields(){
		c=a+b;
		throw new RuntimeException("bloed");
	}
	public long addFieldsMeasured(){
		long start = System.nanoTime();
		c=a+b;
		long stop = System.nanoTime();
		return stop-start;
	}
//	public void test(){
//		int d,e,f;
//		public void add(){
//			f=d+e;
//		}
//	}
}
