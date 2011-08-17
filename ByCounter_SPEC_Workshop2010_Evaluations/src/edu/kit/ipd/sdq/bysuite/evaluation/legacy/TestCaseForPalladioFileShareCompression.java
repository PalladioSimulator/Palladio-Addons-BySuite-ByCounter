package edu.kit.ipd.sdq.bysuite.evaluation.legacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import palladiofileshare.algorithms.SimpleLZW;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationParameters;
import de.uka.ipd.sdq.ByCounter.test.TestASMBytecodes;
import de.uka.ipd.sdq.ByCounter.utils.CountingResultCSVWriter;
import de.uka.ipd.sdq.ByCounter.utils.CSVGenericWriterAndAppender;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * JUnit Test for the evaluation of CBSE.
 *
 */
@RunWith(Parameterized.class)
public class TestCaseForPalladioFileShareCompression {
	
	public static final Boolean COMPRESSED = true;
	public static final Boolean UNCOMPRESSED = false;
	
	public static final Boolean[] fileflags = new Boolean[]{
		UNCOMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		COMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		UNCOMPRESSED,
		};
	
	public static final String[]  filenames = new String[]{
		"testfileless",
		"j0.jpg",
		"j1.jpg",
		"j2.jpg",
		"j3.jpg",
		"j4.jpg",
		"j5.jpg",
		"j6.jpg",
		"j7.jpg",
		"j8.jpg",
		"j9.jpg",
		"p0.pdf",
		"p1.pdf",
		"p2.pdf",
		"p3.pdf",
		"p4.pdf",
		"p5.pdf",
		"p6.pdf",
		"p7.pdf",
		"p8.pdf",
		"p9.pdf"
		};
	
	public static final Integer[] filesizes = new Integer[]{
		40,
		36747, //j0
		29510, //j1
		44853, //j2
		36654, //j3
		20061, //j4
		25149, //j5
		9578,  //j6
		23230, //j7
		29038, //j8
		29423, //j9
		24789, //p0
		26457, //p1
		31762, //p2
		29091, //p3
		33265, //p4
		37831, //p5
		37616, //p6
		30999, //p7
		38526, //p8
		40151  //p9
		};
	
	private static Logger log = Logger.getLogger(TestCaseForPalladioFileShareCompression.class.getCanonicalName());
	
	private static String testClassName = SimpleLZW.class.getCanonicalName();
	
	private static Integer fileIndex;
	
	/**
	 * Generates the different parameters with which all tests are run.
	 * This reuses the parameters from TestASMBytecodes.parameterSetup().
	 * @return The parameter collection for calling the test constructor.
	 * @see #TestASMBytecodes.parameterSetup()
	 */
	@SuppressWarnings({ "unchecked"})
	@Parameters
	public static Collection parameterSetup() {
		fileIndex = 20; //compare for SimpleLZW.run_MK().variant local var
		return TestASMBytecodes.parameterSetup();
	}

	private InstrumentationParameters instrumentationParameters;

	/**
	 * This constructor is used by the Parametrized runner 
	 * for running tests with different parameters.
	 * @param params {@link InstrumentationParameters} for the counting setup.
	 */
	public TestCaseForPalladioFileShareCompression(InstrumentationParameters params) {
		// create a BytecodeCounter
		this.instrumentationParameters = params;
	}
	
	/**
	 * Performs the actual counting by calling BytecodeCounter.instrument(...) 
	 * and BytecodeCounter.execute(...).
	 */
	@Test
	public void runAll() {
		BytecodeCounter counter = new BytecodeCounter();
		counter.setInstrumentationParams(this.instrumentationParameters);

		MethodDescriptor testMethodToInstrument = new MethodDescriptor(testClassName, "public static final byte[] lzwcompress_inlined(byte[] input, int maxDictSize)");
		MethodDescriptor testMethodToExecute 	= new MethodDescriptor(testClassName, "public void run_MK(int fileIndex)");//it is very helpful not to have any parameters here...

		log.fine("Using class "+testClassName+" " +
				"\n\t\t"+"(instrumented: "+testMethodToInstrument+", " +
				"\n\t\t"+"called: "+testMethodToExecute+").");
		
		ArrayList<MethodDescriptor> methodsToInstrument = new ArrayList<MethodDescriptor>();
		methodsToInstrument.add(testMethodToInstrument);
		
// START CANNOT UNCOMMENT because of javassist.
// At this point, the test does not run through without these settings, 
// because ByCounter now employs 
// javassist for class loading. Javassist exits with a 
// {@link CannotCompileException} ("too much data").
		InstrumentationParameters instParams = new InstrumentationParameters(
				methodsToInstrument, //list of methods to instrument
				false,	//use high registers for counting (if false, registers will be created dynamically)
				true, 	//use CountingResultCollector (if false, write directly to the HDD)
				true, 	// use array parameter recording //TODO TOTEST
				false, 	//count statically //TODO TOTEST
				InstrumentationParameters.COUNTER_PRECISION_INT // use integer counters
			);
		counter.setInstrumentationParams(instParams);
// END CANNOT UNCOMMENT		
		
		log.fine("" + counter.getInstrumentationParams()/*.toString()*/);
		
		long start = System.nanoTime();
		counter.instrument(methodsToInstrument);
		counter.execute(
				testMethodToExecute, 
				new Object[]{new Integer(fileIndex)}
			);
		long stop = System.nanoTime();
		long counting = stop-start;
		
		log.info(counting								+ " ns to count (aka \t"+
				Math.round((double) counting/1000)		+ " us aka \t"+
				Math.round((double) counting/1000000)	+ " ms aka \t"+
				Math.round((double) counting/1000000000)+ " s)");
		
		String resultFileNameSpecifier = ""+fileIndex;
		if(fileIndex<10){
			resultFileNameSpecifier = "0"+resultFileNameSpecifier;
		}
		CountingResultCollector resultColl = CountingResultCollector.getInstance();
		resultColl.registerWriter(
				new CountingResultCSVWriter(
						true, 	// append grand total at the end
						';',  	// char (character) for separating entries in the file 
						true, 	// listInvokeOpcodes
						true, 	// performIntegrityCheckOnInvokeOpcodes
						"CBSE."+resultFileNameSpecifier, // CSV file name "core"
						"csv", 	// CSV file name "suffix" (file extension)
						".", 	// path to CSV file
						true, 	// write booleans as integers
						true, 	// write opcodes as integers (not as String pseudonames)
						true, 	// write unused opcodes, too (i.e. those with count 0)
						true,	// truncate undefined opcodes (i.e. those with >199)
						true	// write array details to a separate file
				)
			);
		CountingResult[] finalResults = resultColl.retrieveAllCountingResultsAsArray(false);
		Assert.assertNotNull(finalResults);
		log.info(finalResults.length+" counting results found, logging them: ");
		Assert.assertEquals("Expecting only one result.", 1, finalResults.length);
		for(CountingResult r : finalResults) {
			r.addCharacterisation(
					"Filename", 
					CSVGenericWriterAndAppender.STRING_COLUMN_TYPE, 
					filenames[fileIndex]);
			r.addCharacterisation(
					"Filesize", 
					CSVGenericWriterAndAppender.INTEGER_COLUMN_TYPE, 
					filesizes[fileIndex]);
			r.addCharacterisation(
					"Compressed", 
					CSVGenericWriterAndAppender.BOOLEAN_COLUMN_TYPE, 
					fileflags[fileIndex]);
			resultColl.logResult(r, true, true); //including delegation to registered writers
			
			// make sure the results match the validated counts
			Assert.assertEquals("Counting result for opcode ICONST_M1 is not as expected.", 80305, (long)r.getOpcodeCount(2));
			Assert.assertEquals("Counting result for opcode ICONST_0 is not as expected.", 40615152, (long)r.getOpcodeCount(3));
			Assert.assertEquals("Counting result for opcode ICONST_1 is not as expected.", 20406045, (long)r.getOpcodeCount(4));
			Assert.assertEquals("Counting result for opcode ICONST_2 is not as expected.", 1, (long)r.getOpcodeCount(5));
			Assert.assertEquals("Counting result for opcode BIPUSH is not as expected.", 1, (long)r.getOpcodeCount(16));
			Assert.assertEquals("Counting result for opcode SIPUSH is not as expected.", 35831, (long)r.getOpcodeCount(17));
			Assert.assertEquals("Counting result for opcode LDC is not as expected.", 16, (long)r.getOpcodeCount(18));
			Assert.assertEquals("Counting result for opcode ILOAD is not as expected.", 315169062, (long)r.getOpcodeCount(21));
			Assert.assertEquals("Counting result for opcode DLOAD is not as expected.", 3, (long)r.getOpcodeCount(24));
			Assert.assertEquals("Counting result for opcode ALOAD is not as expected.", 117327524, (long)r.getOpcodeCount(25));
			Assert.assertEquals("Counting result for opcode AALOAD is not as expected.", 38059313, (long)r.getOpcodeCount(50));
			Assert.assertEquals("Counting result for opcode BALOAD is not as expected.", 40792074, (long)r.getOpcodeCount(51));
			Assert.assertEquals("Counting result for opcode ISTORE is not as expected.", 81257523, (long)r.getOpcodeCount(54));
			Assert.assertEquals("Counting result for opcode DSTORE is not as expected.", 3, (long)r.getOpcodeCount(57));
			Assert.assertEquals("Counting result for opcode ASTORE is not as expected.", 38139620, (long)r.getOpcodeCount(58));
			Assert.assertEquals("Counting result for opcode AASTORE is not as expected.", 1024, (long)r.getOpcodeCount(83));
			Assert.assertEquals("Counting result for opcode BASTORE is not as expected.", 122889, (long)r.getOpcodeCount(84));
			Assert.assertEquals("Counting result for opcode CASTORE is not as expected.", 35573, (long)r.getOpcodeCount(85));
			Assert.assertEquals("Counting result for opcode DUP is not as expected.", 35831, (long)r.getOpcodeCount(89));
			Assert.assertEquals("Counting result for opcode IADD is not as expected.", 75981, (long)r.getOpcodeCount(96));
			Assert.assertEquals("Counting result for opcode ISUB is not as expected.", 40151, (long)r.getOpcodeCount(100));
			Assert.assertEquals("Counting result for opcode IMUL is not as expected.", 2, (long)r.getOpcodeCount(104));
			Assert.assertEquals("Counting result for opcode DDIV is not as expected.", 4, (long)r.getOpcodeCount(111));
			Assert.assertEquals("Counting result for opcode IINC is not as expected.", 58517946, (long)r.getOpcodeCount(132));
			Assert.assertEquals("Counting result for opcode I2D is not as expected.", 6, (long)r.getOpcodeCount(135));
			Assert.assertEquals("Counting result for opcode D2I is not as expected.", 2, (long)r.getOpcodeCount(142));
			Assert.assertEquals("Counting result for opcode I2B is not as expected.", 256, (long)r.getOpcodeCount(145));
			Assert.assertEquals("Counting result for opcode I2C is not as expected.", 35573, (long)r.getOpcodeCount(146));
			Assert.assertEquals("Counting result for opcode IFEQ is not as expected.", 20213935, (long)r.getOpcodeCount(153));
			Assert.assertEquals("Counting result for opcode IF_ICMPEQ is not as expected.", 20374871, (long)r.getOpcodeCount(159));
			Assert.assertEquals("Counting result for opcode IF_ICMPNE is not as expected.", 38099464, (long)r.getOpcodeCount(160));
			Assert.assertEquals("Counting result for opcode IF_ICMPLT is not as expected.", 78775587, (long)r.getOpcodeCount(161));
			Assert.assertEquals("Counting result for opcode IF_ICMPGE is not as expected.", 35573, (long)r.getOpcodeCount(162));
			Assert.assertEquals("Counting result for opcode IF_ICMPLE is not as expected.", 769, (long)r.getOpcodeCount(164));
			Assert.assertEquals("Counting result for opcode GOTO is not as expected.", 20298817, (long)r.getOpcodeCount(167));
			Assert.assertEquals("Counting result for opcode ARETURN is not as expected.", 1, (long)r.getOpcodeCount(176));
			Assert.assertEquals("Counting result for opcode GETSTATIC is not as expected.", 4, (long)r.getOpcodeCount(178));
			Assert.assertEquals("Counting result for opcode INVOKEVIRTUAL is not as expected.", 23, (long)r.getOpcodeCount(182));
			Assert.assertEquals("Counting result for opcode INVOKESPECIAL is not as expected.", 3, (long)r.getOpcodeCount(183));
			Assert.assertEquals("Counting result for opcode INVOKESTATIC is not as expected.", 6, (long)r.getOpcodeCount(184));
			Assert.assertEquals("Counting result for opcode NEW is not as expected.", 3, (long)r.getOpcodeCount(187));
			Assert.assertEquals("Counting result for opcode NEWARRAY is not as expected.", 75983, (long)r.getOpcodeCount(188));
			Assert.assertEquals("Counting result for opcode ANEWARRAY is not as expected.", 1, (long)r.getOpcodeCount(189));
			Assert.assertEquals("Counting result for opcode ARRAYLENGTH is not as expected.", 38306979, (long)r.getOpcodeCount(190));
			Assert.assertEquals("Counting result for opcode IFNONNULL is not as expected.", 40150, (long)r.getOpcodeCount(199));
			
//			// generate assert code
//			for(Entry<Integer, Long> e : r.getOpcodeCounts().entrySet()) {
//				System.out.println("Assert.assertEquals(\"Counting result for opcode " + ASMOpcodesMapper.getInstance().getOpcodeString(e.getKey()) + " is not as expected.\", " + e.getValue() + ", (long)r.getOpcodeCount(" + e.getKey() + "));");
//			}
		}
		// clear all collected results - they should not be output during the next run
		resultColl.clearResults();
	}

}

//TODO verify that instrumentation parameters have been set -> ODT document; 
//will be delegated to 
//countMethods(
//	List<MethodDescriptor>, 
//	MethodDescriptor, 
//List<RuntimeMethodParameters>)
//