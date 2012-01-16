package de.uka.ipd.sdq.ByCounter.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.instrumentation.Instrumenter;
import de.uka.ipd.sdq.ByCounter.test.helpers.ASMBytecodeOccurences;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubject;
import de.uka.ipd.sdq.ByCounter.test.helpers.TestSubjectInterfaceMethods;
import de.uka.ipd.sdq.ByCounter.test.helpers.Utils;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This test suite tests several different usage patterns for ByCounter.
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
@RunWith(Parameterized.class)
public class TestBytecodeCounter {
	
	private static final String CLASS_DIR = "bin" + File.separatorChar + 
						File.separatorChar + "de" + 
						File.separatorChar + "uka" +
						File.separatorChar + "ipd" +
						File.separatorChar + "sdq" +
						File.separatorChar + "ByCounter" +
						File.separatorChar + "test" +
						File.separatorChar + "helpers" + 
						File.separatorChar;

	private static final String METHOD_SIGNATURE = "public static void arrayInstructions()";
	
	private static String nestedClassMethodSig1 = "public TestSubject$InnerClass$InnerClassLevel2()";
	
	private static String nestedClassMethodSig2 = "public boolean isWorking(int a)";

	private static String nestedClassRunMethodSig = "public void useInnerClassLevel2()";

	private static final String resultLogFileName = "output" + File.separatorChar +"tmpLogFile.log";

	private static String testCallingTreeMethodSignature = "public void methodCallTest()";

	private static Class<?> testClass = TestSubject.class;
	
	private static String testClassMethodCallTest = "public void methodCallTest()";
	
	/**
	 * Generates the different parameters with which all tests are run.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.parameterSetup()
	 */
	@SuppressWarnings({ "rawtypes"})
	@Parameters
	public static Collection parameterSetup() {
		return TestASMBytecodes.parameterSetup();
	}
	
	/**
	 * An instance of {@link InstrumentationParameters} based on 
	 * {@link #instrumentationParametersTemplate} used in the tests.  
	 * Individual tests may override some parameters.
	 */
	private InstrumentationParameters instrumentationParameters;

	/**
	 * The {@link InstrumentationParameters} template used for all the tests.
	 */
	private InstrumentationParameters instrumentationParametersTemplate;

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} template for the counting setup.
	 */
	public TestBytecodeCounter(InstrumentationParameters params) {
		this.instrumentationParametersTemplate = params;
	}
	
	/**
	 * Clone an instance of {@link InstrumentationParameters}.
	 */
	@Before
	public void setupInstrumentationParameters() {
		// create a fresh instance of InstrumentationParameters
		this.instrumentationParameters = this.instrumentationParametersTemplate.clone();
	}
	
	/**
	 * Clear results in the {@link CountingResultCollector}. 
	 */
	@After
	public void cleanResults() {
		// clear all collected results
		CountingResultCollector.getInstance().clearResults();
	}
	
	/**
	 * Reads the class file and returns its contents.
	 * @param file A {@link File} pointing to a class
	 * @return The contents of the file as byte[].
	 */
	private static byte[] readClassFromFile(File file) {
		FileInputStream fs = null;
		
		// create a file stream for the .class data
		try {
			fs = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		// get the data into a Byte[] array.
		ArrayList<Byte> bytelist = new ArrayList<Byte>();
		try {
			while(fs.available() > 0) {
				bytelist.add((byte)fs.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Byte[] bytesBig = bytelist.toArray(new Byte[bytelist.size()]);
	
		// get the data from the Byte[] array into the byte[] array.
		byte[] bytes = new byte[bytelist.size()];
		for(int i = 0; i < bytesBig.length; i++) {
			bytes[i] = bytesBig[i].byteValue();
		}
		return bytes;
	}

	/**
	 * This unit test tries to instrument a class that is given as byte[].
	 * In this case the .class file of ASMBytecodeOccurences is used.
	 *
	 */
	@Test
	public void testByteClass() {
		File file = new File(CLASS_DIR + ASMBytecodeOccurences.class.getSimpleName() + ".class");
		byte[] bytes = readClassFromFile(file);
		
		// early CountingResultCollector construction; initialize the singleton
		CountingResultCollector resultColl = CountingResultCollector.getInstance();
		
		Assert.assertNotNull(resultColl);
		
		// create a BytecodeCounter
		BytecodeCounter counter = new BytecodeCounter();
		Assert.assertNotNull(counter);
		counter.setClassToInstrument(bytes);
		counter.setInstrumentationParams(this.instrumentationParameters);
		
		// do the de.uka.ipd.sdq.ByCount
		// let the counter do its work on a method
		counter.instrument(new MethodDescriptor(ASMBytecodeOccurences.class.getCanonicalName(), METHOD_SIGNATURE));

		
		Assert.assertNotNull(resultColl.retrieveAllCountingResults_nonRecursively());
		// print the results into the log
		for(CountingResult r : resultColl.retrieveAllCountingResults_nonRecursively()) {
			Assert.assertNotSame(r.getOpcodeCounts().length, 0);
			resultColl.logResult(r, false, true);
		}
		// clear all collected results
		resultColl.clearResults();

	}
	
	/**TODO: This test does no tests yet
	 * This test uses the {@link Instrumenter} on the method methodTest(),
	 * which is a method that calls other methods of the TestSubject class.
	 * It is then tested whether the results calculated by 
	 * {@link CountingResultCollector} are sane.
	 */
	@Test
	public void testCallingTreeResults() {
		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);
		// instrument the methodCallTest
		// flat results
		MethodDescriptor methodDescriptorMCT = 
			new MethodDescriptor(TestSubject.class.getCanonicalName(), 
				testCallingTreeMethodSignature);
		MethodDescriptor methodDescriptorLT = 
			new MethodDescriptor(TestSubject.class.getCanonicalName(), 
					"public int loopTest()");
		MethodDescriptor methodDescriptorPT =  
			new MethodDescriptor(TestSubject.class.getCanonicalName(), 
			"public void printTest()");
		
		ArrayList<MethodDescriptor> methodsToInstrument = new ArrayList<MethodDescriptor>();
		methodsToInstrument.add(methodDescriptorMCT);
		methodsToInstrument.add(methodDescriptorLT);
		methodsToInstrument.add(methodDescriptorPT);
		this.instrumentationParameters.setMethodsToInstrument(methodsToInstrument);
		// FIXME: The following does not work
//		counter.instrument(methodDescriptorMCT);
//		counter.instrument(methodDescriptorLT);
		counter.instrument();
		counter.execute(methodDescriptorMCT, new Object[0]);

		CountingResult[] countingResults = 
			CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray(false);
		Assert.assertTrue("Could not get any counting results.", countingResults.length > 0);
		CountingResult r1 = countingResults[countingResults.length-1];	// last is methodCallTest
		CountingResultCollector.getInstance().logResult(r1, true, true);
		CountingResultCollector.getInstance().clearResults();
		// calling tree results
		counter.execute(methodDescriptorMCT, new Object[0]);
		
		countingResults = CountingResultCollector.getInstance().retrieveAllCountingResultsAsArray(true);
		CountingResult r2 = countingResults[countingResults.length-1];
		CountingResultCollector.getInstance().logResult(r2, false, true);
	}

	/**
	 * Test for writing to log file instead of using CountingResultCollector.
	 */
	@Test
	public void testDirectResultWriting() {
		// create a BytecodeCounter
		BytecodeCounter counter = new BytecodeCounter();
		Assert.assertNotNull(counter);
		counter.setInstrumentationParams(this.instrumentationParameters);
		
		// disable usage of result collector
		Assert.assertNotNull(counter.getInstrumentationParams());
		boolean oldUseResultCollector = 
			counter.getInstrumentationParams().getUseResultCollector();
		counter.getInstrumentationParams().setUseResultCollector(false);
		Assert.assertEquals(false, counter.getInstrumentationParams().getUseResultCollector());
		
		// set the file name for the result log
		String oldResultLogFileName = 
			counter.getInstrumentationParams().getResultLogFileName();
		counter.getInstrumentationParams().setResultLogFileName(resultLogFileName);
		boolean oldUseArrayParameterRecording = 
			counter.getInstrumentationParams().getUseArrayParameterRecording();
		counter.getInstrumentationParams().setUseArrayParameterRecording(true);
		Assert.assertEquals(resultLogFileName, counter.getInstrumentationParams().getResultLogFileName());


		// test with void method
		MethodDescriptor methodDescriptor = new MethodDescriptor(
				testClass.getCanonicalName(), "public void methodCallTest()");
		counter.instrument(methodDescriptor);
		counter.execute(methodDescriptor, new Object[]{});
		
		// The log file name is dynamic and cannot be checked
		cleanResults();

		// test with boolean method
		methodDescriptor = new MethodDescriptor(testClass.getCanonicalName(), 
			"public boolean parameterTest(int i, float f, java.lang.String s)");
		counter.instrument(methodDescriptor);
		counter.execute(methodDescriptor, 
			new Object[]{2, 2, TestSubject.class.getCanonicalName()});
				
		// reset to old instrumentation parameters
		this.instrumentationParameters.setUseResultCollector(oldUseResultCollector);
		this.instrumentationParameters.setResultLogFileName(oldResultLogFileName);
		this.instrumentationParameters.setUseArrayParameterRecording(oldUseArrayParameterRecording);
	}
	
	/**
	 * This unit test tries to instrument a class that is given as byte[].
	 * In this case the .class file of ASMBytecodeOccurences is used.
	 *
	 */
	@Test
	public void testGenerateCallTree() {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(
				testClass.getCanonicalName(),
				testClassMethodCallTest); //$NON-NLS-1$
		
		counter.getInstrumentationParams().setInstrumentRecursively(true, 5);
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		
		counter.execute(myMethod, new Object[0]);

		Assert.assertNotNull(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively());
		Assert.assertTrue(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively().size() > 1);
		for(CountingResult newResult: CountingResultCollector.getInstance().retrieveAllCountingResults_recursively()) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
		
		counter.getInstrumentationParams().setInstrumentRecursively(false, 0);
	}

	/**
	 * This unit test tries to instrument a class using instrumentAll.
	 */
	@Test
	public void testInstrumentAll() {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();
		
		//2. now tell ByCounter to instrument all methods
		counter.instrumentAllInClass(TestSubject.class.getCanonicalName(), new String[0], true);

		//3. Specify the method to be executed
		MethodDescriptor myMethod = new MethodDescriptor(
				testClass.getCanonicalName(),
				testClassMethodCallTest);
		counter.execute(myMethod, new Object[0]);

		Assert.assertNotNull(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively());
		Assert.assertTrue(CountingResultCollector.getInstance().retrieveAllCountingResults_recursively().size() > 1);
		for(CountingResult newResult: CountingResultCollector.getInstance().retrieveAllCountingResults_recursively()) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
		
		counter.getInstrumentationParams().setInstrumentRecursively(false, 0);
	}
	
	
	/**
	 * This unit test tries to instrument a class that is given as byte[].
	 * In this case the .class file of ASMBytecodeOccurences is used.
	 *
	 */
	@Test
	public void testInstrumetingTwice() {
		File file = new File(CLASS_DIR + ((Class<?>) ASMBytecodeOccurences.class).getSimpleName() + ".class");
		byte[] bytes = readClassFromFile(file);
		
		Assert.assertNotNull(bytes);
		
		if(bytes == null) {
			return;
		}
		
		// CountingResultCollector construction; initialize the singleton
		CountingResultCollector resultColl = CountingResultCollector.getInstance();
		
		Assert.assertNotNull(resultColl);
		
		// create a BytecodeCounter
		BytecodeCounter counter = new BytecodeCounter();
		Assert.assertNotNull(counter);
		counter.setClassToInstrument(bytes);
		counter.setInstrumentationParams(this.instrumentationParameters);
		
		// do the de.uka.ipd.sdq.ByCount
		// let the counter do its work on a method
		counter.instrument(
				new MethodDescriptor(((Class<?>) ASMBytecodeOccurences.class).getCanonicalName(), METHOD_SIGNATURE));
		
		byte[] instrumentedBytes = counter.getInstrumentedBytes();
		Assert.assertNotNull(instrumentedBytes);
		
		counter.setClassToInstrument(instrumentedBytes);
	
		counter.instrument(
				new MethodDescriptor(((Class<?>) ASMBytecodeOccurences.class).getCanonicalName(), METHOD_SIGNATURE));
//		
//		Assert.assertNotNull(resultColl.getAllCountingResults_nonRecursively());
//		// print the results into the log
//		for(CountingResult r : resultColl.getAllCountingResults_nonRecursively()) {
//			Assert.assertNotSame(r.getOpcodeCounts().size(), 0);
//			resultColl.logResult(r, true, true);
//		}
		// clear all collected results
		resultColl.clearResults();

	}
	
	/**
	 * This test uses the instrumenter on inner classes using the classfile 
	 * overwriting option which is important as 
	 * nested classes follow a special .class file naming scheme.
	 */
	@Test
	public void testNestedClassInstrumentation() {
		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);

		String classNameInnerClassLevel2 = TestSubject.class.getCanonicalName() 
			+"$" + TestSubject.InnerClass.class.getSimpleName() 
			+"$" + TestSubject.InnerClass.InnerClassLevel2.class.getSimpleName();
		
		// instrument the constructor of the nested class		
		CountingResult r = Utils.getCountingResultForTest(
				counter,
				MethodDescriptor.forConstructor(classNameInnerClassLevel2, 
						nestedClassMethodSig1),
				new MethodDescriptor(TestSubject.class.getCanonicalName(), 
						nestedClassRunMethodSig));

		CountingResultCollector.getInstance().logResult(r, false, true);
		
		cleanResults();
		
		// instrument the method of the nested class
		r = Utils.getCountingResultForTest(
				counter,
				new MethodDescriptor(classNameInnerClassLevel2, 
						nestedClassMethodSig2),
				new MethodDescriptor(TestSubject.class.getCanonicalName(), 
						nestedClassRunMethodSig));

		CountingResultCollector.getInstance().logResult(r, false, true);
	}
	

	/**
	 * This unit test tries to recursively instrument a class that makes calls 
	 * to an interface.
	 *
	 */
	@Test
	public void testInterfaceMethodRecursiveInstrumentation() {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(
				TestSubjectInterfaceMethods.class.getCanonicalName(),
				"void methodA1()");
		
		counter.getInstrumentationParams().setInstrumentRecursively(true, 5);
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		
		counter.execute(myMethod, new Object[0]);

		List<CountingResult> allCountingResultsRecursively = CountingResultCollector.getInstance().retrieveAllCountingResults_recursively();
		Assert.assertNotNull(allCountingResultsRecursively);
		Assert.assertTrue("More counting results are expected.", allCountingResultsRecursively.size() > 1);
		for(CountingResult newResult: allCountingResultsRecursively) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
		
		counter.getInstrumentationParams().setInstrumentRecursively(false, 0);
	}
	

	/**
	 * This unit test tries to recursively instrument a class that makes calls 
	 * to an interface.
	 *
	 */
	@Test
	public void testInstrumentationNoPackageName() {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		MethodDescriptor myMethod = new MethodDescriptor(
				"ClassInDefaultPackage",
				"public static void main(java.lang.String[] args)");
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		counter.execute(myMethod, new Object[]{new String[0]});

		List<CountingResult> allCountingResultsRecursively = CountingResultCollector.getInstance().retrieveAllCountingResults_recursively();
		Assert.assertNotNull(allCountingResultsRecursively);
		for(CountingResult newResult: allCountingResultsRecursively) {
			CountingResultCollector.getInstance().logResult(newResult, false, true);
		}
	}
	
	/**
	 * This instruments a method in {@link TestSubject} and writes the 
	 * instrumented .class file. 
	 * @throws IOException When writing fails.
	 */
	@Test
	public void testWriteClass() throws IOException {
		//1. Set up a BytecodeCounter instance to use ByCounter, using a parameterless constructor. 
		BytecodeCounter counter = new BytecodeCounter();

		//2. Specify the method to be instrumented (several methods are supported as well)
		String className = TestSubject.class.getCanonicalName();
		MethodDescriptor myMethod = new MethodDescriptor(
				className,
				"public void methodCallTest()"); //$NON-NLS-1$
		
		
		//3. now tell ByCounter to instrument the specified method
		counter.instrument(myMethod);
		

		byte[] bytes = counter.getInstrumentedBytes();
		
		File file = new File("instrumented_" + className + ".class");
		FileOutputStream fos = new FileOutputStream(
				file);
		fos.write(bytes);
		Logger.getAnonymousLogger().info("Wrote " + file.getAbsolutePath());
		fos.close();
	}
}
