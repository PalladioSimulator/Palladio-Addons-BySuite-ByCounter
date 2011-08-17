package edu.kit.ipd.sdq.bysuite.evaluation.legacy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import java.util.zip.ZipOutputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import palladiofileshare.algorithms.SimpleLZW;
import palladiofileshare.algorithms.SimpleTestDriver;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.TestASMBytecodes;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import de.uka.ipd.sdq.ByCounter.utils.PathMapper;

/**
 * JUnit Test for the instrumentation of compress.
 *
 */
@RunWith(Parameterized.class)
public class TestCompress {
	private static final String INSTRUMENTED_CLASSFILES_ROOT_PATH = "."+File.separator + "output" + File.separator + "instrumented_classfiles"; //"de"+File.separator+"uka"+File.separator+"ipd"+File.separator+"sdq"+File.separator+"ByCounter"+File.separator+"evaluation";
	private static final String TEST_CLASS_CANONICAL_NAME_SimpleLZW 
		= SimpleLZW.class.getCanonicalName();
//	private static final String TEST_CLASS_CANONICAL_NAME_SimpleByteArrayVector
//		= SimpleByteArrayVector.class.getCanonicalName();
	private static final String TEST_CLASS_CANONICAL_NAME_SimpleTestDriver
		= SimpleTestDriver.class.getCanonicalName();
	
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_CompressionRunner
//		= CompressionRunner.class.getCanonicalName();
//	private static final String TEST_CLASS_CANONICAL_NAME_Compressor
//		= Compressor.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_Decompressor 
//		= Decompressor.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_DeStack
//		= DeStack.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_HashTable
//		= HashTable.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_InputBuffer
//		= InputBuffer.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_OutputBuffer
//		= OutputBuffer.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_Source
//		= Source.class.getCanonicalName();
//	@SuppressWarnings("unused")
//	private static final String TEST_CLASS_CANONICAL_NAME_SuffixTable 			  //TODO test
//		= SuffixTable.class.getCanonicalName();

	private static final String[] TEST_CLASSES = new String[]{
//		TEST_CLASS_CANONICAL_NAME_SimpleByteArrayVector,
		TEST_CLASS_CANONICAL_NAME_SimpleLZW,
		TEST_CLASS_CANONICAL_NAME_SimpleTestDriver
//		TEST_CLASS_CANONICAL_NAME_CodeTable,
//		TEST_CLASS_CANONICAL_NAME_CompBase,
//		TEST_CLASS_CANONICAL_NAME_Compress,
//		TEST_CLASS_CANONICAL_NAME_CompressionRunner,
//		TEST_CLASS_CANONICAL_NAME_Compressor,
//		TEST_CLASS_CANONICAL_NAME_Decompressor,
//		TEST_CLASS_CANONICAL_NAME_DeStack,
//		TEST_CLASS_CANONICAL_NAME_HashTable,
//		TEST_CLASS_CANONICAL_NAME_InputBuffer,
//		TEST_CLASS_CANONICAL_NAME_OutputBuffer,
//		TEST_CLASS_CANONICAL_NAME_Source,
//		TEST_CLASS_CANONICAL_NAME_SuffixTable
		};
	
	private static Logger log = Logger.getLogger(TestCompress.class.getCanonicalName());
	
	/**
	 * Generates the different parameters with which all tests are run.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.parameterSetup()
	 */
	@SuppressWarnings({ "unchecked"})
	@Parameters
	public static Collection parameterSetup() {
		return TestASMBytecodes.parameterSetup();
	}

	private InstrumentationParameters instrumentationParameters;

	private String instrumentedClassfilesRelativePath = null;
	private String instrumentedClassfilesRootPath = INSTRUMENTED_CLASSFILES_ROOT_PATH;

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	public TestCompress(InstrumentationParameters params) {
		// create a BytecodeCounter
		this.instrumentationParameters = params;
	}
	
	/**
	 * Performs the actual counting by calling BytecodeCounter.count(...)
	 */
	@SuppressWarnings({ "unchecked" })
	@Test
	public void runAll(){
		byte[][] instrumentedClasses = new byte[TEST_CLASSES.length][];
		Class<?> cl = null;
		int nrOfInstrClasses = 2; //TODO TEST_CLASSES.length
		if(nrOfInstrClasses>TEST_CLASSES.length){
			//TODO notify the user about the problem...
			nrOfInstrClasses=TEST_CLASSES.length;
		}
		for(int i=0; i< nrOfInstrClasses; i++){
			try {
				cl = ClassLoader.getSystemClassLoader().loadClass(TEST_CLASSES[i]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			ArrayList<MethodDescriptor> methodsToInstrument = new ArrayList<MethodDescriptor>();
			log.info("Declared methods that CAN be instrumented");
			Method[] declaredMethods = cl.getDeclaredMethods();//TODO potential null pointer access
			for(Method m : declaredMethods) {
				System.out.println("\t\t"+m);
			}

			for(Method m : declaredMethods) {
				methodsToInstrument.add(new MethodDescriptor(m));
			}
//			methodsToInstrument.add(new MethodDescriptor(declaredMethods[0]));
//			methodsToInstrument.add(new MethodDescriptor(declaredMethods[1]));
//			methodsToInstrument.add(new MethodDescriptor(declaredMethods[2]));
			log.info("Declared methods that WILL be instrumented");
			for(MethodDescriptor m : methodsToInstrument) {
				System.out.println("\t\t"+m);
			}
			InstrumentationParameters instPrms = this.instrumentationParameters;
			instPrms.setMethodsToInstrument(methodsToInstrument);
			log.fine("Instrumentation parameters to be used: "+instPrms);
			BytecodeCounter counter;
			counter = new BytecodeCounter();
			counter.setInstrumentationParams(instPrms);
			counter.instrument();
			instrumentedClasses[i] = counter.getInstrumentedBytes();
			Assert.assertNotNull(instrumentedClasses[i]);
			long timestamp = System.nanoTime();
			this.saveInstrumentedClassfileToDisk(instrumentedClasses[i], cl.getCanonicalName(), timestamp);
		}
	}
	
	/** TODO
	 * @param array
	 * @param path
	 * @param createFileIfNeeded
	 */
	private void saveByteArray(byte[] array, String path, boolean createFileIfNeeded){
		log.fine(path+" will be saving byte array of size "+array.length); 
		File f = new File(path);
		if(!f.exists()){
			f.getParentFile().mkdirs();
		}else{
			if(f.isDirectory()){
				log.severe("Cannot write byte array to directory! (path given: "+path+")");
				return;
			}else{
				log.warning("Overwriting file "+f.getAbsolutePath());
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			fos.write(array);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				//nothing
			}
		}
	}
	
	private boolean saveInstrumentedClassfileToDisk(byte[] bytes, String classCanonicalName, long timestamp) {
		@SuppressWarnings("unused")
		ZipOutputStream fos;
		
		log.fine(classCanonicalName+" ("+bytes.length+" bytes) will be saved with timestamp "+timestamp);
		String instrumentedClassfilesRelativePath = "";
		if(classCanonicalName==null){
			log.severe("Cannot initialise class instance " +
					"because classCanonicalName is null");
			return false;
		}
		ArrayList<String> packageTokens 
			= PathMapper.derivePackageTokensFromCanonicalName(classCanonicalName);
		for (Iterator<String> iterator = packageTokens.iterator(); iterator.hasNext();) {
			instrumentedClassfilesRelativePath += File.separator+iterator.next();
		}
		this.instrumentedClassfilesRelativePath += File.separator;

		String path = this.instrumentedClassfilesRootPath //something like ./test
			+ File.separator
			+ timestamp //z.B. System.nanoTime()
			+ File.separator
			+ instrumentedClassfilesRelativePath
			+ File.separator
			+ PathMapper.deriveShortClassNameFromCanonicalName(classCanonicalName) //has no .class extension
			+ ".class";
		log.fine("Saving instrumented class to "+path);
		this.saveByteArray(bytes, path, true);
		return true;
	}

//		log.info("Methods");
//		for(Method m : cl.getMethods()) {
//			System.out.println(m);
////			if(m.getParameterTypes().length == 0) {
////				methods.add(new MethodDescriptor(m));
////			}
//		}
//		log.info("Declared constructors");
//		for(Constructor c : cl.getDeclaredConstructors()) {
//			System.out.println(c);
//			System.out.println(new MethodDescriptor(c.toGenericString()));
////			if(m.getParameterTypes().length == 0) {
////				methods.add(new MethodDescriptor(m));
////			}
//		}
//		log.info("All constructors");
//		for(Constructor<DeStack> c : cl.getConstructors()) {
//			System.out.println(c);
////			if(m.getParameterTypes().length == 0) {
////				methods.add(new MethodDescriptor(m));
////			}
//		}
//		c.getConstructors();
//		c.getMethods();
//		c.getPackage();
		//protected :-( ClassLoader.getSystemClassLoader().getPackages();
		
		// create a list with empty parameters for all methods
//		ArrayList<RuntimeMethodParameters> parameters 
//					= new ArrayList<RuntimeMethodParameters>(methods.size());
//		
//		for(int i = 0; i < methods.size(); i++) {
//			parameters.add(new RuntimeMethodParameters());
//		}
		
/*		
		MethodDescriptor methDesc = new MethodDescriptor(this.testMethodSignature);
//		log.fine("Method descriptor: "+methDesc);
		
		long start = System.nanoTime();
//		log.fine("(NOT INITIALISED)" + counter.getInstrumParams().toString());
//		this.counter.countMethod(
//				methDesc, 			//which is instrumented //TODO refactor
//				METHOD_TO_EXECUTE, 	//FIXME generalise
//				new Object[]{}); 	//no params for run_MK, used to be new String[0] : for main(String args[]) //TODO: document, generalise
		long stop = System.nanoTime();
		long counting = stop-start;
		
		log.fine(counter.getInstrumentationParams().toString());
		log.info(counting+    "ns to count (aka \t"+
				Math.round((double) counting/1000)+"us aka \t"+
				Math.round((double) counting/1000000)+"ms aka \t"+
				Math.round((double) counting/1000000000)+"s)");
		
		CountingResult[] finalResults = resultColl.getResultsAsArray();
		log.info(finalResults.length+" counting results found, logging them: ");
		for(CountingResult r : finalResults) {
			resultColl.logResult(r); //from Martin
		}
		// clear all collected results
		resultColl.clearResults();
*/
}
