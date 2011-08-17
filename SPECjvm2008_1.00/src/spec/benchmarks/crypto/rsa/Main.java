/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.crypto.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;

import spec.benchmarks.crypto.Util;
import spec.harness.Context;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.StopBenchmarkException;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    
    final static int level = 1;
    
    static PublicKey rsaPub;
    static PrivateKey rsaPriv;
    
    /**Default constructor provided by SPEC
     * @param bmResult
     * @param threadId
     */
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    }
    
    /**
     * MK Parameterless constructor needed by ByCounter
     * @param bmResult
     * @param threadId
     */
    public Main() {
        super();
    }
    
    /** Run this in multi mode, next to each other. */
    public static String testType() {
        return MULTI;
    }
    
    public byte [] encrypt(byte[] indata, String algorithm) {
        
        try {
            Cipher c = Cipher.getInstance(algorithm);
            byte[] result = indata;
            
            c.init(Cipher.ENCRYPT_MODE, rsaPub);
            result = c.doFinal(result);
            
            return result;
            
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in encrypt for " + algorithm + ".", e);
        }
    }
    
    public byte[] decrypt(byte[] indata, String algorithm) {
        
        try {
            Cipher c = Cipher.getInstance(algorithm);
            
            byte[] result = indata;
            
            c.init(Cipher.DECRYPT_MODE, rsaPriv);
            result = c.doFinal(result);
            
            return result;
            
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in decrypt for " + algorithm + ".", e);
        }
    }
    
    public void runSingleEncryptDecrypt(String algorithm, String inputFile) {
        byte [] indata = Util.getTestData(inputFile);
        Context.getOut().println("Algorithm=" + algorithm + " indata length=" + indata.length);
        byte [] cipher = encrypt(indata, algorithm);
        byte [] plain = decrypt(cipher, algorithm);
        boolean match = Util.check(indata, plain);
        Context.getOut().println(algorithm + ":"
                + " plaincheck=" + Util.checkSum(plain)
                + (match ? " PASS" : " FAIL"));
    }
    
    public void runMultiEncryptDecrypt(String algorithm, String inputFile) {
        int blockSize = 64;
        byte [] fullIndata = Util.getTestData(inputFile);
        byte [] indata = new byte[blockSize];
        int pass = 0;
        int fail = 0;
        int check = 0;
        Context.getOut().println("Algorithm=" + algorithm + " indata length=" + fullIndata.length);
        for (int i = 0; i + blockSize < fullIndata.length; i+= blockSize) {
            System.arraycopy(fullIndata, i, indata, 0, blockSize);
            byte [] cipher = encrypt(indata, algorithm);
            byte [] plain = decrypt(cipher, algorithm);
            if (Util.check(indata, plain)) {
                pass++;
                check += Util.checkSum(plain);
            } else {
                fail++;
            }
        }
        Context.getOut().println(algorithm + ":"
                + " checksum=" + check
                + " pass=" + pass
                + " fail=" + fail);
    }
    
    public void harnessMain() {
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", Util.TEST_DATA_3);
        runMultiEncryptDecrypt("RSA/ECB/PKCS1Padding", Util.TEST_DATA_5);
        // Run some more, in order to increase operation workload.
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", Util.TEST_DATA_3);
        runMultiEncryptDecrypt("RSA/ECB/PKCS1Padding", Util.TEST_DATA_5);
        runSingleEncryptDecrypt("RSA/ECB/PKCS1Padding", Util.TEST_DATA_3);
    }
    
    public static void setupBenchmark() {
        try {
            Context.getFileCache().loadFile(Util.TEST_DATA_3);
            Context.getFileCache().loadFile(Util.TEST_DATA_5);
            
            KeyPairGenerator rsaKeyPairGen = KeyPairGenerator.getInstance("RSA");
            // 512, 768 and 1024 are commonly used
            rsaKeyPairGen.initialize(1024);
            
            KeyPair rsaKeyPair = rsaKeyPairGen.generateKeyPair();
            
            rsaPub = rsaKeyPair.getPublic();
            rsaPriv = rsaKeyPair.getPrivate();
            
        } catch (Exception e) {//MK TODO very irritating...
            throw new StopBenchmarkException("Error in setup of crypto.aes." + e);
        }
    }
    
	public static long _MK_val = 0;

	private static int _MK_numberOfRepetitions = 1;

	public static void main(String[] args) throws Exception {// TODO clarify:
																// how has added
																// this?
//		List<Long> _MK_meas = new ArrayList<Long>();
//		double sum = 0D;
		for (int i = 0; i < _MK_numberOfRepetitions; i++) {
			_MK_val = System.nanoTime(); // MK
			runSimple(Main.class, args);
			_MK_val = System.nanoTime() - _MK_val;
//			_MK_meas.add(_MK_val);
//			sum += _MK_val;
//			System.err.println("MK: compiler.compiler.Main.main: " + _MK_val
//					+ " ns");
		}
//		Collections.sort(_MK_meas);
//		System.err.println("MK: compiler.compiler.Main.main min from "
//				+ _MK_numberOfRepetitions + " meas: " + _MK_meas.get(0));
//		System.err.println("MK: compiler.compiler.Main.main med from "
//				+ _MK_numberOfRepetitions + " meas: "
//				+ _MK_meas.get(_MK_meas.size() / 2));
//		System.err.println("MK: compiler.compiler.Main.main avg from "
//				+ _MK_numberOfRepetitions + " meas: " + sum
//				/ _MK_numberOfRepetitions);
//		System.err.println("MK: compiler.compiler.Main.main max from "
//				+ _MK_numberOfRepetitions + " meas: "
//				+ _MK_meas.get(_MK_meas.size() - 1));
	}
    
}
