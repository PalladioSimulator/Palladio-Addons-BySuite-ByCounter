package palladiofileshare.algorithms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class OldSimpleByteArrayHashMap extends HashMap<MessageDigest,byte[]> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OldSimpleByteArrayHashMap(int size){
		super(size);
	}
	
	
//
//	private static boolean equal(byte[] left, byte[] right){
//		if (left==null && right==null){
//			return true;
//		}else if(left.length!=right.length){
//			return false;
//		}else{
//			for(int i=0; i<left.length; i++){
//				if(left[i]!=right[i]){
//					return false;
//				}
//			}
//			return true;
//		}
//	}
//	
	public static void main(String args[]){
		OldSimpleByteArrayHashMap bav = new OldSimpleByteArrayHashMap(10);
		bav.test();
	}
//	
//	public boolean add(byte[] addedElement){
//		return this.add(addedElement, false);
//	}
//	
//	public boolean add(byte[] addedElement, boolean verbose){
//		super.add(addedElement);
//		if(verbose) System.out.println("Added "+SimpleLZW.convertByteArrayToString(addedElement));
//		return true;
//	}
//	
//	public boolean contains(Object o){
//		if(o instanceof byte[]){
//			int firstIndex = this.firstIndexOf(o);
//			if (firstIndex<0){
//				return false;
//			}else{
//				return true;
//			}
//		}else {
//			return false;
//		}
//	}
//	
//	public boolean equals(Object o){
//		if(!(o instanceof SimpleByteArrayHashMap)){
//			return false;
//		}else{
//			SimpleByteArrayHashMap bav = (SimpleByteArrayHashMap) o;
//			if(bav.size() != this.size()){
//				return false;
//			}
//			for(int i=0; i<this.size(); i++){
//				if(! SimpleByteArrayHashMap.equal(this.get(i), bav.get(i))){
//					return false;
//				}
//			}
//			return true;
//		}
//	}
//	
//	/** Performance bottlenecks
//	 * @param o
//	 * @return
//	 */
//	public final int firstIndexOf(Object o){
//		if (o==null || !(o instanceof byte[])){
//			return -1;
//		}else{
//			byte[] prmArr = (byte[]) o;
//			byte[] current;
//			boolean containsPrm = false;
//			int i=0;
//			int foundIndex = -1;
//			for(; i< this.size(); i++){
//				current = this.get(i);
//				if(current.length==prmArr.length){
//					int j=0;
//					containsPrm = true;
//					for(; j<prmArr.length; j++){
//						if(current[j]!=prmArr[j]){
//							containsPrm = false;
//							j=prmArr.length;
//						}
//					}
//				}
//				if(containsPrm==true){
//					foundIndex=i;
//					i=this.size();
//				}
//			}
//			return foundIndex;
//		}
//	}
//	
//	/**
//	 * @deprecated
//	 */
//	public int indexOf(Object o){
//		return firstIndexOf(o);
//	}
//
	private void test() {
		MessageDigest md1;
		try {
			md1 = MessageDigest.getInstance("MD5");
			md1.digest(new byte[]{1});
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
//		this.add(new byte[]{1});
//		this.add(new byte[]{2});
//		System.out.println(this.get(0).hashCode());
//		System.out.println((new byte[]{1}).hashCode());
//		System.out.println("");
//		System.out.println(this.get(0));
//		System.out.println((new byte[]{2}).hashCode());
//		System.out.println(this.contains(new byte[]{1}));
//		System.out.println(this.contains(new byte[]{2}));
//		System.out.println(this.contains(new byte[]{3}));
//		System.out.println(this.firstIndexOf(new byte[]{1}));
//		System.out.println(this.firstIndexOf(new byte[]{2}));
//		System.out.println(this.firstIndexOf(new byte[]{3}));
//		System.out.println(new byte[]{1,2,3}.hashCode());
//		System.out.println(new byte[]{1,2,3}.hashCode());
//		System.out.println(((byte[]) this.get(0)).equals(new byte[]{2}));
	}
}
