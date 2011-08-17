package edu.kit.ipd.sdq.bysuite.evaluation;

import java.util.Random;

/**
 * Performance of invoke bei return draufschlagen?
 * @author michaelkuperberg
 *
 */
public class InvokeBenchmarker {
	private static double nsPerHighResCounterTick; 
//	
//	private static int numberOfProblemSizeIncreases = 0;
//	
//	private static int numberOfWarmups = 30000;
	
	private static final sun.misc.Perf perf;
	
//	private static int problemSizeIncreaseStep = 512;
	
	private static Random rd;
	
//	private static int repetitionsPerProblemSize = 100;
	
	private static boolean useNanoTime = false;
	
	static{
		rd = new Random();
		perf = sun.misc.Perf.getPerf();
		nsPerHighResCounterTick = 1000000000.0D/(double) perf.highResFrequency();
	}
	
	public static void main(String[] args) {
		InvokeBenchmarker ib = new InvokeBenchmarker();
		int controlVar = 0;
		for(int i=0; i<120000; i++){
			controlVar += ib.randomMethodA(i);
			controlVar += ib.simpleMethodB(i);
			controlVar += ib.randomMethodA(i);
			controlVar += ib.simpleMethodB(i);
		}
		ib.durationOfInvoke();
	}
	
	private static long timeInNs(){
		if(useNanoTime){
			return System.nanoTime();
		}else{
			return new Double((double) perf.highResCounter()*(double) nsPerHighResCounterTick).longValue();
		}
	}
	
	public double durationOfInvoke(){
		int nrOfMeasurements = 10000;//abhŠngigkeit plotten! Genauigkeitsbeeinflussend!
		double ret = 0.0D;
		int controlVar = 0;
		long start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += simpleMethodA(i);
		}
		long stopp = timeInNs();
		double durationSimpleMethodA = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += simpleMethodB(i);
		}
		stopp = timeInNs();
		double durationSimpleMethodB = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += simpleMethodA(i)+simpleMethodA(i);
		}
		stopp = timeInNs();
		double durationSimpleMethodAA = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += simpleMethodB(i)+simpleMethodB(i);
		}
		stopp = timeInNs();
		double durationSimpleMethodBB = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		
		
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += randomMethodA(i);
		}
		stopp = timeInNs();
		double durationrandomMethodA = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += randomMethodB(i);
		}
		stopp = timeInNs();
		double durationrandomMethodB = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += randomMethodA(i)+randomMethodA(i);
		}
		stopp = timeInNs();
		double durationrandomMethodAA = (double) (stopp-start)/(double) nrOfMeasurements;
		System.out.println("Control var: "+controlVar);	
		
		start = timeInNs();
		for(int i=0; i<nrOfMeasurements; i++){
			controlVar += randomMethodB(i)+randomMethodB(i);
		}
		stopp = timeInNs();
		double durationrandomMethodBB = (double) (stopp-start)/(double) nrOfMeasurements;

		System.out.println("Control var: "+controlVar);	
		
		System.err.println("");
		System.err.println("A : "+durationSimpleMethodA);
		System.err.println("B : "+durationSimpleMethodB);
		System.err.println("> A-(B-A) : "+(durationSimpleMethodA-(durationSimpleMethodB-durationSimpleMethodA)));

		System.err.println("AA: "+durationSimpleMethodAA);
		System.err.println("BB: "+durationSimpleMethodBB);
		System.err.println("AA-A: "+(durationSimpleMethodAA-durationSimpleMethodA));
		System.err.println("> AA-B: "+(durationSimpleMethodAA-durationSimpleMethodB));
		System.err.println("BB-B: "+(durationSimpleMethodBB-durationSimpleMethodB));
		
		System.err.println("");
		System.err.println("rA : "+durationrandomMethodA);
		System.err.println("rB : "+durationrandomMethodB);
		System.err.println("> rA-(rB-rA) : "+(durationrandomMethodA-(durationrandomMethodB-durationrandomMethodA)));

		System.err.println("rAA: "+durationrandomMethodAA);
		System.err.println("rBB: "+durationrandomMethodBB);
		System.err.println("rAA-rA: "+(durationrandomMethodAA-durationrandomMethodA));
		System.err.println("> rAA-rB: "+(durationrandomMethodAA-durationrandomMethodB));
		System.err.println("rBB-rB: "+(durationrandomMethodBB-durationrandomMethodB));
		
		return ret;
	}
	
	int randomMethodA(int i){
		return rd.nextInt(i+1);
	}
	
	int randomMethodB(int i){
		return rd.nextInt(i+1)+rd.nextInt(i+1);
	}
	
	int simpleMethodA(int i){
		return i*i%(i+i+1);
	}
	
	int simpleMethodB(int i){
		return i*i%(i+i+1)*i%(i+i+1);
	}

}
