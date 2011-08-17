/**
 * 
 */
package edu.kit.ipd.sdq.bysuite.evaluation.legacy;

import java.sql.Timestamp;
import java.util.Collections;

import spec.benchmarks.compress.Harness;
import spec.benchmarks.compress.Main;
import spec.benchmarks.compress.Compress;

/**
 * A helper class to iteratate over files, etc.
 * @author Michael Kuperberg
 *
 */
public class SPECCompressExecutionStarter {
	
	public static final int DEFAULT_FILE_INDEX = 0 ;
	
	public static final int DEFAULT_NUMBER_OF_MEASUREMENTS = 1;

	private static final boolean MK_TSE_EXPERIMENT = true;
	
	/**
	 * Compresses the first 15 files, whereas each file is 
	 * compressed several times, and byte mutation is applied to prevent 
	 * caching and elimination of iterations.
	 * @param args
	 */
	public static void main(String[] args) {
		SPECCompressExecutionStarter sces = new SPECCompressExecutionStarter();
		for(int i=0; i<15; i++){
			sces.start(i);
		}
	}

	/**
	 * The actual number of measurements.
	 */
	private int numberOfMeasurements = DEFAULT_NUMBER_OF_MEASUREMENTS;

	/**
	 * The default constructor
	 */
	public SPECCompressExecutionStarter() {
	}
	
	/**
	 * Invoke SPECjvm2008 Main.main belonging to the Compress benchmark 
	 * {@link #numberOfMeasurements} times and evaluate results.
	 * @param fileIndex
	 */
	public void start(int fileIndex) {
		Harness.MK_setFileIndex(fileIndex);
		Compress.lastResults.clear();//sorted plain measurements...
		int tencentile = numberOfMeasurements/10;
		if(numberOfMeasurements>10 && numberOfMeasurements%10 != 0){
			System.out.println("Last tencentile contains "+
					numberOfMeasurements%10+" measurements more than others");
		}
		
		System.out.println("File index: "+fileIndex+", " +
				"nr of measurements: "+numberOfMeasurements);
		for(int i=0; i<numberOfMeasurements; i++){
			if(numberOfMeasurements>10 && tencentile!=0 && i%tencentile==0){
				System.out.println("Starting "+((i/tencentile)+1)+"st " +
						"tencentile at "+new Timestamp(System.currentTimeMillis()));
				Collections.sort(Compress.lastResults);
				int currResultsSize = Compress.lastResults.size();
				System.out.println("So far: "+currResultsSize+" results:");
				if(Compress.lastResults.size()>0){
					System.out.println("Min: "+Compress.lastResults.get(0));
					System.out.println("Med: "+Compress.lastResults.get(currResultsSize/2));
					System.out.println("Max: "+Compress.lastResults.get(currResultsSize-1));
				}
			}
			try{
				Main.main(new String[]{});
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collections.sort(Compress.lastResults);
		if(MK_TSE_EXPERIMENT){
		}else{
			try{
				System.out.println("\n==================\nFinal results:");
				System.out.println("File index: "+fileIndex+", " +
						"nr of measurements: "+numberOfMeasurements);
				System.out.println("Min: "+Compress.lastResults.get(0));
				System.out.println("Med: "+Compress.lastResults.get(numberOfMeasurements/2));
				System.out.println("Max: "+Compress.lastResults.get(numberOfMeasurements-1));
				System.out.println("Compressed file size: "+Harness.MK_getLastOutputSize());
			}catch (Exception e) {
				System.out.println("Compress.lastResults may be empty," +
						" or another error in SPECCompressExecutionStarter.start");
	//			e.printStackTrace();
			}
		}
		
	}

}
