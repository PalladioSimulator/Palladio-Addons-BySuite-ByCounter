/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.crypto.signverify;

import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import spec.benchmarks.crypto.Util;
import spec.harness.Context;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.StopBenchmarkException;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    
    private static PublicKey rsaPub;
    private static PrivateKey rsaPriv;
    
    private static PublicKey dsaPub;
    private static PrivateKey dsaPriv;
    
    private final static int iterations = 10;
    
    /**
     * Constructor provided by SPEC
     * @param bmResult
     * @param threadId
     */
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
    }
    
    /**
     * MK default constructor needed by ByCounter
     */
    public Main() {
        super();
    }
    
    /** Run this in multi mode, next to each other. */
    public static String testType() {
        return MULTI;
    }
    
    public byte [] sign(byte[] indata, String algorithm, PrivateKey privKey) {
        
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privKey);
            signature.update(indata);
            return signature.sign();
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in verify for " + algorithm + ".", e);
        }
    }
    
    public boolean verify(byte[] indata, String algorithm, byte [] signed, PublicKey pubKey) {
        
        try {
            
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(pubKey);
            
            signature.update(indata);
            
            return signature.verify(signed);
            
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in verify for " + algorithm + ".", e);
        }
    }
    
    
    
    public void runSignVerify(byte[] indata, String algorithm, PrivateKey privKey, PublicKey pubKey) {
        
        byte [] signed = sign(indata, algorithm, privKey);
        boolean verification = verify(indata, algorithm, signed, pubKey);
        
        if (verification) {
            spec.harness.Context.getOut().println(algorithm + " Verification PASS");
        } else {
            spec.harness.Context.getOut().println(algorithm + " Verification FAILED.");
        }
        
    }
    
    public void harnessMain() {
        for (int i = 0; i < iterations; i++) {
            Context.getOut().println("Iteration " + i + ".");
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_4), "SHA256withRSA", rsaPriv, rsaPub);
            
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_5), "SHA256withRSA", rsaPriv, rsaPub);
            
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "MD5withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA1withRSA", rsaPriv, rsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA1withDSA", dsaPriv, dsaPub);
            runSignVerify(Util.getTestData(Util.TEST_DATA_6), "SHA256withRSA", rsaPriv, rsaPub);
        }
    }
    
    public static void setupBenchmark() {
        
        try {
            Context.getFileCache().loadFile(Util.TEST_DATA_4);
            Context.getFileCache().loadFile(Util.TEST_DATA_5);
            Context.getFileCache().loadFile(Util.TEST_DATA_6);
            
            KeyPairGenerator rsaKeyPairGen = KeyPairGenerator.getInstance("RSA");
            // 512, 768 and 1024 are commonly used
            rsaKeyPairGen.initialize(1024);
            
            KeyPair rsaKeyPair = rsaKeyPairGen.generateKeyPair();
            
            rsaPub = rsaKeyPair.getPublic();
            rsaPriv = rsaKeyPair.getPrivate();
            
            KeyPairGenerator dsaKeyPairGen = KeyPairGenerator.getInstance("DSA");
            dsaKeyPairGen.initialize(1024);
            
            KeyPair dsaKeyPair = dsaKeyPairGen.generateKeyPair();
            
            dsaPub = dsaKeyPair.getPublic();
            dsaPriv = dsaKeyPair.getPrivate();
        } catch (Exception e) {
            throw new StopBenchmarkException("Error in setup of crypto.aes." + e);
        }
    }
        
    public static void createTestData() throws IOException {
        Util.createRandomTestData(Util.TEST_DATA_4, 1024);
        Util.createRandomTestData(Util.TEST_DATA_5, 16384);
        Util.createRandomTestData(Util.TEST_DATA_6, 1048576);
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
