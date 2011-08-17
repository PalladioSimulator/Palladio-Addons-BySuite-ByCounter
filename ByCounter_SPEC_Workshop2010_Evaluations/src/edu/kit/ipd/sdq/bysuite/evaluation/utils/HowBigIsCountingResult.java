package edu.kit.ipd.sdq.bysuite.evaluation.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import de.uka.ipd.sdq.ByCounter.execution.CountingResult;

public class HowBigIsCountingResult {
	static Random rd;
	
	static{
		rd = new Random();
	}
	
	public static String randomString(int length/*, boolean onlyFirst256Chars*/){
		char[] chars = new char[length];
		for(int i=0; i<chars.length; i++){
			chars[i] = (char) rd.nextInt(256);
		}
		return new String(chars);
	}
	public static void main(String[] args) {
		SortedMap<String,Long> methodCallCounts0010 = new TreeMap<String,Long>();
		SortedMap<String,Long> methodCallCounts0100 = new TreeMap<String,Long>();
		SortedMap<String,Long> methodCallCounts1000 = new TreeMap<String,Long>();
		
		for(int i=0; i<10; i++){
			methodCallCounts0010.put(randomString(30), (long) i);
			methodCallCounts0100.put(randomString(30), (long) i);
			methodCallCounts1000.put(randomString(30), (long) i);
		}
		for(int i=10; i<100; i++){
			methodCallCounts0100.put(randomString(30), (long) i);
			methodCallCounts1000.put(randomString(30), (long) i);
		}
		for(int i=100; i<1000; i++){
			methodCallCounts1000.put(randomString(30), (long) i);
		}
		CountingResult cr0010methods = new CountingResult(
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new String("ten"), 
				new String("0010"), 
				1, //fileType, 
				1L, //inputCharacterisation, 
				1L, //outputCharacterisation, 
				1L, //methodInvocationBeginning, 
				1L, //methodReportingTime, 
				new long[201], //opcodeCounts, 
				methodCallCounts0010, 
				null, //arrayCreationCounts, 
				null, //arrayCreationDimensions, 
				null, //arrayCreationTypeInfo, 
				null, //sectionInstCounts, 
				null  //sectionMethCounts
				);
		CountingResult cr0100methods = new CountingResult(
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new String("hundred"), 
				new String("0100"), 
				1, //fileType, 
				1L, //inputCharacterisation, 
				1L, //outputCharacterisation, 
				1L, //methodInvocationBeginning, 
				1L, //methodReportingTime, 
				new long[201], //opcodeCounts, 
				methodCallCounts0100, 
				null, //arrayCreationCounts, 
				null, //arrayCreationDimensions, 
				null, //arrayCreationTypeInfo, 
				null, //sectionInstCounts, 
				null  //sectionMethCounts
				);
		CountingResult cr1000methods = new CountingResult(
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new UUID(1L, 1L), 
				new String("thousand"), 
				new String("1000"), 
				1, //fileType, 
				1L, //inputCharacterisation, 
				1L, //outputCharacterisation, 
				1L, //methodInvocationBeginning, 
				1L, //methodReportingTime, 
				new long[201], //opcodeCounts, 
				methodCallCounts1000, 
				null, //arrayCreationCounts, 
				null, //arrayCreationDimensions, 
				null, //arrayCreationTypeInfo, 
				null, //sectionInstCounts, 
				null  //sectionMethCounts
				);
		File cr0010file = new File("."+File.separator+"CountingResult.0010.ser");
		File cr0100file = new File("."+File.separator+"CountingResult.0100.ser");
		File cr1000file = new File("."+File.separator+"CountingResult.1000.ser");
		try {
			cr0010file.createNewFile();
			ObjectOutputStream oos0010 = new ObjectOutputStream(new FileOutputStream(cr0010file));
			oos0010.writeObject(cr0010methods);
			System.out.println("0010 ser: "+cr0010file.length());
			
			cr0100file.createNewFile();
			ObjectOutputStream oos0100 = new ObjectOutputStream(new FileOutputStream(cr0100file));
			oos0100.writeObject(cr0100methods);
			System.out.println("0100 ser: "+cr0100file.length());
			
			cr1000file.createNewFile();
			ObjectOutputStream oos1000 = new ObjectOutputStream(new FileOutputStream(cr1000file));
			oos1000.writeObject(cr1000methods);
			System.out.println("1000 ser: "+cr1000file.length());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
