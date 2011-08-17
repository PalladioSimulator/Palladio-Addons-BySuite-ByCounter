/**************************************************************************
 *                                                                         *
 *             Java Grande Forum Benchmark Suite - Version 2.0             *
 *                                                                         *
 *                            produced by                                  *
 *                                                                         *
 *                  Java Grande Benchmarking Project                       *
 *                                                                         *
 *                                at                                       *
 *                                                                         *
 *                Edinburgh Parallel Computing Centre                      *
 *                                                                         * 
 *                email: epcc-javagrande@epcc.ed.ac.uk                     *
 *                                                                         *
 *                                                                         *
 *      This version copyright (c) The University of Edinburgh, 1999.      *
 *                         All rights reserved.                            *
 *                                                                         *
 **************************************************************************/

package crypt;

import jgfutil.JGFInstrumentor;
import jgfutil.JGFSection2;

public class JGFCryptBench extends IDEATest implements JGFSection2 {

	private int datasizes[] = { 3000000, 20000000, 50000000 };
	private int size;

	public void JGFinitialise() {
		array_rows = datasizes[size];
		buildTestData();//MK is in superclass
	}

	public void JGFkernel() {
		Do();
	}

	public void JGFrun(int size) {

		JGFInstrumentor.addTimer("Section2:Crypt:Kernel", "Kbyte", size);

		JGFsetsize(size);
		JGFinitialise();
		JGFkernel();//calls Do() of the superclass
		JGFvalidate();
		JGFtidyup();

		JGFInstrumentor.addOpsToTimer("Section2:Crypt:Kernel",
				(2 * array_rows) / 1000.);
		JGFInstrumentor.printTimer("Section2:Crypt:Kernel");
	}

	public void JGFsetsize(int size) {
		this.size = size;
	}

	public void JGFtidyup() {
		freeTestData();
	}

	public void JGFvalidate() {
		boolean error;

		error = false;
		for (int i = 0; i < array_rows; i++) {
			error = (plain1[i] != plain2[i]);
			if (error) {
				System.out.println("Validation failed");
				System.out.println("Original Byte " + i + " = " + plain1[i]);
				System.out.println("Encrypted Byte " + i + " = " + crypt1[i]);
				System.out.println("Decrypted Byte " + i + " = " + plain2[i]);
				// break;
			}
		}
	}
}
