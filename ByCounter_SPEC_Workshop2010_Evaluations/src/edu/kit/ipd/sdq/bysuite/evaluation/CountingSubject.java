package edu.kit.ipd.sdq.bysuite.evaluation;

import java.util.logging.Logger;

/**
 * Class for running the instrumentation for paper validation
 * TODO not clear where it is used...
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public class CountingSubject {
	public static void main(String args[]){
		CountingSubject dts = new CountingSubject();
		dts.decimalsForCounting(1000);
		dts.fibonacciForCounting(1000);
		dts.decimalsForCounting_equalized(1000);
		dts.fibonacciForCounting_equalized(1000);
		int[] dummy = new int[1];
		@SuppressWarnings("unused")
		int dummy2;
		dummy[0] = 1;
		dummy[0] = 1;
		dummy[0] = 1;
		dummy2 = dummy [0];
		dummy2 = dummy [0];
		dummy2 = dummy [0];
	}
	
	public long end = 0L;//PAPER
	
	private Logger log;
	
	public int[]numbers;
	
	public long start=0L;

	public int TEMP = 2;
	
	public CountingSubject(){
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.log.info(getClass().getCanonicalName() + " was constructed.");
	}
	
	public long decimalsForCounting(int nr) {
		this.numbers = new int[nr];
		this.start = System.nanoTime();
		int dividend = 22;
		int divisor = 7;

		for (int i = 0; i<nr; i++) {
			this.numbers[i] = dividend/divisor;
			dividend = 10*(dividend
					- this.numbers[i]*divisor);
		}
		this.end = System.nanoTime();
		return this.end - this.start;
	}

	public long decimalsForCounting_equalized(int nr) {
		this.start = System.nanoTime();
		this.numbers = new int[nr];
		int dividend = 22;
		int divisor = 7;

		for (int i = 0; i<nr; i++) {
			this.numbers[i] = dividend/divisor;
			dividend = 10*(dividend
					- this.numbers[i]*divisor);
		}
		this.end = System.nanoTime();
		dividend++; //MK EQUALIZER
		dividend--; //MK EQUALIZER
		dividend++; //MK EQUALIZER
		dividend--; //MK EQUALIZER
		dividend++; //MK EQUALIZER
		dividend--; //MK EQUALIZER
		return this.end - this.start;
	}
	
	public long fibonacciForCounting(int nr) {
		this.start = System.nanoTime();
		this.numbers = new int[nr];
		this.numbers[0] = 1;
		this.numbers[1] = 1;

		for (int i = 2; i<nr; i++) { //TODO warum stuerzt der Zaehler ab, wenn ich "i<(nr+2) mache?
			this.numbers[i] = this.numbers[i - 1]
			           + this.numbers[i - 2];
		}
		this.end = System.nanoTime();
		return this.end - this.start;
	}
	
	public long fibonacciForCounting_efficient(int nr) {
		this.numbers = new int[nr];
		this.start = System.nanoTime();
		int beforeLast;
		int last;
		int current;
		this.numbers[0] = 1;
		this.numbers[1] = 1;
		beforeLast = 1;
		last = 1;

		for (int i = 2; i<nr; i++) {
			current = last+beforeLast;
			beforeLast=last;
			last=current;
			this.numbers[i] = current;
		}
		this.end = System.nanoTime();
		return this.end - this.start;
	}

	public long fibonacciForCounting_equalized(int nr) {
		this.start = System.nanoTime();
		this.numbers = new int[nr];
		this.numbers[0] = 1;
		this.numbers[1] = 1;

		for (int i = 2; i<nr; i++) {
			this.numbers[i] = this.numbers[i - 1]
			           + this.numbers[i - this.TEMP];//equalizer
		}
		this.end = System.nanoTime();
		return this.end - this.start;
	}
}
