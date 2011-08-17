package spec.benchmarks.crypto.aes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainCaller {
	private static final int MK_numberOfRepetitions = 21;

	/**
	 * TODO ensure that Main itself is only 1 iteration
	 * @param args
	 */
	public static void main(String[] args) {
    	List<Long> MK_meas = new ArrayList<Long>();
    	long MK_read_val = 0L;
    	double sum = 0D;
        for(int i=0; i<MK_numberOfRepetitions ; i++){
        	try {
				Main.main(new String[]{});
			} catch (Exception e) {
				e.printStackTrace();
			}
        	MK_read_val = Main._MK_val;
        	MK_meas.add(MK_read_val);
        	sum += MK_read_val;
            System.err.println("MK: AES main "+i+": "+MK_read_val+" ns");
        }
        System.err.println("MK: AES 1st from "+MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
        Collections.sort(MK_meas);
        System.err.println("MK: AES min from "+MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
        System.err.println("MK: AES med from "+MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2));
        System.err.println("MK: AES avg from "+MK_numberOfRepetitions+" meas: "+sum/MK_numberOfRepetitions);
        System.err.println("MK: AES max from "+MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1));
	}
}
