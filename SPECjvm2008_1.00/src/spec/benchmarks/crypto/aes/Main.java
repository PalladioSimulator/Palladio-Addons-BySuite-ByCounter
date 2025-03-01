/*
 * Copyright (c) 2008 Standard Performance Evaluation Corporation (SPEC)
 *               All rights reserved.
 *
 * This source code is provided as is, without any express or implied warranty.
 */

package spec.benchmarks.crypto.aes;

import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import spec.benchmarks.crypto.Util;
import spec.harness.Context;
import spec.harness.SpecJVMBenchmarkBase;
import spec.harness.StopBenchmarkException;
import spec.harness.results.BenchmarkResult;

public class Main extends SpecJVMBenchmarkBase {
    
    public final static boolean DEBUG = false;
    
    final static int aesKeySize = 128;
    final static int desKeySize = 168;
    final static int level = 12;
    
    static SecretKey aesKey = null;
    static SecretKey desKey = null;
    
    static KeyGenerator aesKeyGen = null;
    static KeyGenerator desKeyGen = null;

	/**
	 * 
	 */
	private static int _MK_numberOfRepetitions = 1;
    
    AlgorithmParameters algorithmParameters;
    
    /**
     * SPEC-defined constructor
     * @param bmResult
     * @param threadId
     */
    public Main(BenchmarkResult bmResult, int threadId) {
        super(bmResult, threadId);
        algorithmParameters = null;
    }
    
    /**
     * MK manually introduced constructor to satisfy the requirements of ByCounter
     */
    public Main() {
        super();
        algorithmParameters = null;
    }
    
    /** Run this in multi mode, next to each other. */
    public static String testType() {
        return MULTI;
    }
    
    private void printMe(String name, byte [] arr) {
        System.out.print("  " + name + ":");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    
    /**
     * Will encrypt the indata level number of times.
     * @param indata Data to encrypt.
     * @param key Key to use for encryption.
     * @param algorithm Algorithm/Standard to use.
     * @param level Number of times to encrypt.
     * @return The encrypted version of indata.
     */
    private byte[] encrypt(byte [] indata, SecretKey key, String algorithm, int level) {
        
        if (DEBUG) printMe("indata", indata);
        
        byte[] result = indata;
        
        try {
            Cipher c = Cipher.getInstance(algorithm);
            c.init(Cipher.ENCRYPT_MODE, key);
            algorithmParameters = c.getParameters();
            
            for (int i = 0; i < level; i++) {
                byte[] r1 = c.update(result);
                byte[] r2 = c.doFinal();
                
                if (DEBUG) printMe("[" + i + "] r1", r1);
                if (DEBUG) printMe("[" + i + "] r2", r2);
                
                result = new byte[r1.length + r2.length];
                System.arraycopy(r1, 0, result, 0, r1.length);
                System.arraycopy(r2, 0, result, r1.length, r2.length);
            }
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in encrypt for " + algorithm + ".", e);
        }
        
        if (DEBUG) printMe("result", result);
        return result;
    }
    
    /**
     * Will decrypt the indata level number of times.
     * @param indata Data to decrypt.
     * @param key Key to use for encryption.
     * @param algorithm
     * @param level
     * @return
     */
    private byte[] decrypt(byte[] indata, SecretKey key, String algorithm, int level) {
        
        if (DEBUG) printMe("indata", indata);
        
        byte[] result = indata;
        
        try {
            Cipher c = Cipher.getInstance(algorithm);
            c.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            
            for (int i = 0; i < level; i++) {
                byte[] r1 = c.update(result);
                byte[] r2 = c.doFinal();
                if (DEBUG) printMe("[" + i + "] r1", r1);
                if (DEBUG) printMe("[" + i + "] r2", r2);
                
                result = new byte[r1.length + r2.length];
                System.arraycopy(r1, 0, result, 0, r1.length);
                System.arraycopy(r2, 0, result, r1.length, r2.length);
            }
            
        } catch (Exception e) {
            throw new StopBenchmarkException("Exception in encrypt for " + algorithm + ".", e);
        }
        
        if (DEBUG) printMe("result", result);
        return result;
    }
    
    public void runEncryptDecrypt(SecretKey key, String algorithm, String inputFile) {
        byte [] indata = Util.getTestData(inputFile);
        byte [] cipher = encrypt(indata, key, algorithm, level);
        byte [] plain = decrypt(cipher, key, algorithm, level);
        boolean match = Util.check(indata, plain);
        Context.getOut().println(algorithm + ":" + " plaincheck="
                + Util.checkSum(plain) + (match ? " PASS" : " FAIL"));
    }
    
    public void harnessMain() {
        runEncryptDecrypt(Main.aesKey, "AES/CBC/NoPadding", Util.TEST_DATA_1);
        runEncryptDecrypt(Main.aesKey, "AES/CBC/PKCS5Padding", Util.TEST_DATA_1);
        runEncryptDecrypt(Main.desKey, "DESede/CBC/NoPadding", Util.TEST_DATA_1);
        runEncryptDecrypt(Main.desKey, "DESede/CBC/PKCS5Padding", Util.TEST_DATA_1);
        runEncryptDecrypt(Main.aesKey, "AES/CBC/NoPadding", Util.TEST_DATA_2);
        runEncryptDecrypt(Main.aesKey, "AES/CBC/PKCS5Padding", Util.TEST_DATA_2);
        runEncryptDecrypt(Main.desKey, "DESede/CBC/NoPadding", Util.TEST_DATA_2);
        runEncryptDecrypt(Main.desKey, "DESede/CBC/PKCS5Padding", Util.TEST_DATA_2);
    }
    
    public static void setupBenchmark() {
    	//System.out.println("MK current dir: "+new File(".").getAbsolutePath());
        try {
            byte [] seed =  {0x4, 0x7, 0x1, 0x1};
            SecureRandom random = new SecureRandom(seed);
            Context.getFileCache().loadFile(Util.TEST_DATA_1);
            Context.getFileCache().loadFile(Util.TEST_DATA_2);
            aesKeyGen = KeyGenerator.getInstance("AES");
            aesKeyGen.init(aesKeySize, random);
            desKeyGen = KeyGenerator.getInstance("DESede");
            desKeyGen.init(desKeySize, random);
            aesKey = aesKeyGen.generateKey();
            desKey = desKeyGen.generateKey();
        } catch (Exception e) {
            throw new StopBenchmarkException("Error in setup of crypto.aes." + e);
        }
    }
    
    public static long _MK_val = 0;
    
    public static void main(String[] args) throws Exception {
        //setupBenchmark();
        //Main m = new Main(new BenchmarkResult(), 0);
        //m.harnessMain();
//    	javax.crypto.Cipher
//    	List<Long> MK_meas = new ArrayList<Long>();
//    	double sum = 0D;
//        for(int i=0; i<_MK_numberOfRepetitions ; i++){
        	_MK_val= System.nanoTime(); //MK
        	runSimple(Main.class, args);
        	_MK_val = System.nanoTime()-_MK_val;
//        	MK_meas.add(_MK_val);
//        	sum += _MK_val;
//            System.err.println("MK "+i+": AES main: "+_MK_val+" ns");
//        }
//        System.err.println("MK: AES 1st from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
//        Collections.sort(MK_meas);
//        System.err.println("MK: AES min from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(0));
//        System.err.println("MK: AES med from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()/2));
//        System.err.println("MK: AES avg from "+_MK_numberOfRepetitions+" meas: "+sum/_MK_numberOfRepetitions);
//        System.err.println("MK: AES max from "+_MK_numberOfRepetitions+" meas: "+MK_meas.get(MK_meas.size()-1));
    }
}
