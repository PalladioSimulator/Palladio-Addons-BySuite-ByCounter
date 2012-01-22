package de.uka.ipd.sdq.ByCounter.instrumentation;

import java.io.File;
import java.util.List;

import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * A collection of properties that determine the way the instrumentation
 * is done.
 * 
 * <p>
 * Some combination of parameters only make sense in combination with others.
 * The following list summarizes these dependencies.
 * <ul>
 * <li>{@link #setResultLogFileName(String)} only applies if {@link #getUseResultCollector()} == false</li>
 * <li>{@link #setWriteClassesToDiskDirectory(File)} only applies if {@link #getWriteClassesToDisk()} == true</li>
 * <li>{@link #setRecordBlockExecutionOrder(boolean)} only applies if either range blocks or basic blocks are used, i.e. if {@link #getUseBasicBlocks()} == true</li>
 * <li>{@link #setUseArrayParameterRecording(boolean)} is currently only supported when not using basic/range blocks.</li>
 * </ul>
 * </p>
 * 
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class InstrumentationParameters implements Cloneable {

	/** Default value for {@link #getIgnoredPackagePrefixes()}. */
	public static final String[] IGNORED_PACKAGES_DEFAULT = {
			"java/",
			"javax/",
			"sun/",
			"org/w3c/dom"
		};

	/** Default value for {@link #getUseBasicBlocks()}. */
	public static final boolean USE_BASIC_BLOCKS_DEFAULT = false;

	/** Default value for {@link #getRecordBlockExecutionOrder()}. */
	public static final boolean RECORD_BLOCK_EXECUTION_ORDER_DEFAULT = true;

	/** Default value for {@link #getInstrumentationScopeOverrideClassLevel()}. */
	public static final InstrumentationScopeModeEnum INSTRUMENTATION_SCOPE_OVERRIDE_CLASS_LEVEL_DEFAULT = InstrumentationScopeModeEnum.InstrumentAsSpecified;
	
	/** Default value for {@link #getInstrumentationScopeOverrideMethodLevel()}. */
	public static final InstrumentationScopeModeEnum INSTRUMENTATION_SCOPE_OVERRIDE_METHOD_LEVEL_DEFAULT = InstrumentationScopeModeEnum.InstrumentAsSpecified;

	/** Default value for {@link #getWriteClassesToDiskDirectory()}. */
	public static final File WRITE_CLASSES_TO_DISK_DIRECTORY_DEFAULT = new File("bin_instrumented");

	/** Default value for {@link #getTraceAndIdentifyRequests()}. */
	public static final boolean TRACE_AND_IDENTIFY_REQUESTS_DEFAULT = false;

	/** Default value for {@link #getWriteClassesToDisk()}. */
	public static final boolean WRITE_CLASSES_TO_DISK_DEFAULT = false;

	/** Default value for {@link #getParentClassLoader()}. */
	public static final ClassLoader PARENT_CLASS_LOADER_DEFAULT = null;

	/** Default value for {@link #getInstrumentRecursively()}. */
	public static final boolean INSTRUMENT_RECURSIVELY_DEFAULT = false;
	
	/** Default value for {@link #getInstrumentRecursivelyMaxDepth()}. */
	public static final int INSTRUMENT_RECURSIVELY_MAX_DEPTH_DEFAULT = 0;

	/** Default value for {@link #getMethodsToInstrument()}. */
	public static final List<MethodDescriptor> METHODS_TO_INSTRUMENT_DEFAULT = null;

	/** Default value for {@link #getUseHighRegistersForCounting()}. */
	public static final boolean USE_HIGH_REGISTERS_FOR_COUNTING_DEFAULT = true;

	/** Default value for {@link #getUseResultCollector()}. */
	public static final boolean USE_RESULT_COLLECTOR_DEFAULT = true;

	/** Default value for {@link #getUseArrayParameterRecording()}. */
	public static final boolean USE_ARRAY_PARAMETER_RECORDING = false;

	/** Default value for {@link #getCountStatically()}. */
	public static final boolean COUNT_STATICALLY_DEFAULT = false;
	
	
	/**
	 * Use integer variables as counters.
	 */
	public static final boolean COUNTER_PRECISION_INT = false;

	/**
	 * Use long variables as counters.
	 */
	public static final boolean COUNTER_PRECISION_LONG = true;

	/**
	 * Directory in which result log files are written by default.
	 * This only applies if 
	 * <code>{@link #getUseResultCollector()} == true</code>.
	 * @see #RESULT_LOG_DEFAULT_PREFIX
	 */
	public static final String RESULT_LOG_DEFAULT_DIRECTORY = 
		"ByCounter_logged_counting_results";

	/**
	 * Default file name prefix of the result log files.
	 * This only applies if 
	 * <code>{@link #getUseResultCollector()} == true</code>.
	 * @see #RESULT_LOG_DEFAULT_DIRECTORY
	 */
	public static final String RESULT_LOG_DEFAULT_PREFIX = 
		RESULT_LOG_DEFAULT_DIRECTORY + 
		File.separatorChar;
	
	/**
	 * A list of strings that cause a class to be ignored in the parsing 
	 * when found at the start of a package name.
	 */
	protected static String[] ignoredPackagePrefixes = IGNORED_PACKAGES_DEFAULT;

	/** Decides on the precision of the variables used for counting. For 
	 * very high counts integers might not be enough and you want to use 
	 * long variables instead. See the COUNTER_PRECISION_ constants. */
	private boolean counterPrecisionIsLong;

	/** When true, ByCounter makes a static analysis of the specified code. */
	@Deprecated
	private boolean countStatically;
	
	/**
	 * @see #setInstrumentRecursivly(boolean, int)
	 */
	private boolean instrumentRecursively;
	
	/**
	 * @see #instrumentRecursively
	 */
	private int instrumentRecursivelyMaxDepth;
	
	/** Descriptions of the methods that shall be instrumented. */
	private List<MethodDescriptor> methodsToInstrument;
	
	/** The filename of the log containing the results, that is used if useResultCollector == false. */
	private String resultLogFileName;

	/** Decides whether instrumentation for the recording of parameters of array construction takes place. 
	 * Causes some additional overhead.
	 * When false, results are written to disk directly.  */
	private boolean useArrayParameterRecording;
	
	/**
	 * When true, bytecode instructions will be counted in groups made up of 
	 * identified basic blocks. The execution numbers of single instructions 
	 * are calculated after the execution.
	 * When false, every single bytecode instruction will be counted 
	 * by an individual counter.
	 */
	private boolean useBasicBlocks;
	
	/** Decides whether to preallocate registers near max_locals instead of using LocalVariablesSorter. */
	private boolean useHighRegistersForCounting;
	
	/** Decides whether to use the CountingResultCollector framework. */
	private boolean useResultCollector;
	
	/**
	 * When true, ByCounter will write the instrumented class files
	 * to the "bin_instrumented" directory.
	 */
	private boolean writeClassesToDisk;

	/**
	 * @see #getParentClassLoader()
	 */
	private ClassLoader parentClassLoader;

	/**
	 * @see #getRangeBlocks()
	 */
	private LineNumberRange[] rangeBlocks;
	
	/**
	 * @see #getRecordBlockExecutionOrder()
	 */
	private boolean recordBlockExecutionOrder;
	
	/**
	 * @see #getTraceAndIdentifyRequests()
	 */
	private boolean traceAndIdentifyRequests;

	/**
	 * If {@link #getWriteClassesToDisk()} is enabled, classes will be written 
	 * to the here specified directory.
	 * @see {@link #setWriteClassesToDisk(boolean)}
	 */
	private File writeClassesToDiskDirectory;

	/**
	 * Specifies if all or no methods shall be instrumented instead of the 
	 * otherwise specified methods.
	 */
	private InstrumentationScopeModeEnum instrumentationScopeOverrideClassLevel;
	
	/**
	 * Overrides the instrumentation behavior in a method.
	 */
	private InstrumentationScopeModeEnum instrumentationScopeOverrideMethodLevel;

	/**
	 * This is intended only for construction in multiple steps.
	 * Methods to instrument are NOT set - you must do so manually! 
	 * Assumes dynamic analysis and usage of the CountingResultCollector.
	 * Uses high registers for counting and the CountingResultCollector 
	 * framework.
	 */
	@SuppressWarnings("dep-ann")
	public InstrumentationParameters() {
		this(	METHODS_TO_INSTRUMENT_DEFAULT,
				USE_HIGH_REGISTERS_FOR_COUNTING_DEFAULT,
				USE_RESULT_COLLECTOR_DEFAULT,	// use CountingResultCollector instead of result log
				USE_ARRAY_PARAMETER_RECORDING,
				COUNT_STATICALLY_DEFAULT,
				COUNTER_PRECISION_LONG
			);
	}
	
	/**
	 * Assumes dynamic analysis and usage of the CountingResultCollector. Array 
	 * construction parameters will not be recorded.
	 * Uses high registers for counting.
	 * @param pMethodsToInstrument Name of the methods that shall be instrumented.
	 * When false, results are written to disk directly. 
	 */
	@SuppressWarnings("dep-ann")
	public InstrumentationParameters(List<MethodDescriptor> pMethodsToInstrument) {
		this(pMethodsToInstrument, 
				USE_HIGH_REGISTERS_FOR_COUNTING_DEFAULT,
				USE_RESULT_COLLECTOR_DEFAULT,	// use CountingResultCollector instead of result log
				USE_ARRAY_PARAMETER_RECORDING,
				COUNT_STATICALLY_DEFAULT,
				COUNTER_PRECISION_LONG
				);
	}
	
	/**
	 * @param pMethodsToInstrument Name of the methods that shall be instrumented.
	 * @param pUseHighRegistersForCounting Decides whether to preallocate registers near max_locals instead of using LocalVariablesSorter.
	 * @param pUseResultCollector Decides whether to use the CountingResultCollector framework.
	 * @param pUseArrayParameterRecording Decides whether instrumentation for the recording of parameters of array construction takes place. Causes some additional overhead.
	 * @param countStatically When true, ByCounter makes a static analysis of the specified code.
	 * @param counterPrecision Decides on the precision of the variables used 
	 * for counting. See the COUNTER_PRECISION_ constants. 
	 */
	public InstrumentationParameters(
			List<MethodDescriptor> pMethodsToInstrument,
			boolean pUseHighRegistersForCounting, 
			boolean pUseResultCollector,
			boolean pUseArrayParameterRecording,
			boolean countStatically,
			boolean counterPrecision) {
		this.setMethodsToInstrument(pMethodsToInstrument);
		this.setParentClassLoader(PARENT_CLASS_LOADER_DEFAULT);
		this.setUseBasicBlocks(USE_BASIC_BLOCKS_DEFAULT);
		this.setUseHighRegistersForCounting(pUseHighRegistersForCounting);
		this.setUseResultCollector(pUseResultCollector);
		this.setCountStatically(countStatically);
		this.setUseArrayParameterRecording(pUseArrayParameterRecording);
		this.counterPrecisionIsLong = counterPrecision;
		this.writeClassesToDisk = WRITE_CLASSES_TO_DISK_DEFAULT;
		this.traceAndIdentifyRequests = TRACE_AND_IDENTIFY_REQUESTS_DEFAULT;
		this.writeClassesToDiskDirectory = WRITE_CLASSES_TO_DISK_DIRECTORY_DEFAULT;
		this.instrumentationScopeOverrideClassLevel = INSTRUMENTATION_SCOPE_OVERRIDE_CLASS_LEVEL_DEFAULT;
		this.instrumentationScopeOverrideMethodLevel = INSTRUMENTATION_SCOPE_OVERRIDE_METHOD_LEVEL_DEFAULT;
		this.recordBlockExecutionOrder = RECORD_BLOCK_EXECUTION_ORDER_DEFAULT;
		this.instrumentRecursively = INSTRUMENT_RECURSIVELY_DEFAULT;
		this.instrumentRecursivelyMaxDepth = INSTRUMENT_RECURSIVELY_MAX_DEPTH_DEFAULT;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public InstrumentationParameters clone() {
		InstrumentationParameters copy = null;
		
		try {
			copy = (InstrumentationParameters) super.clone();
		} catch (CloneNotSupportedException e) {
			// object.clone() cannot fail
			return null;
		}
		
		// shallow copy all fields
		copy.counterPrecisionIsLong = this.counterPrecisionIsLong;
		copy.countStatically = this.countStatically;
		copy.instrumentationScopeOverrideClassLevel = this.instrumentationScopeOverrideClassLevel;
		copy.instrumentationScopeOverrideMethodLevel = this.instrumentationScopeOverrideMethodLevel;
		copy.instrumentRecursively = this.instrumentRecursively;
		copy.instrumentRecursivelyMaxDepth = this.instrumentRecursivelyMaxDepth;
		copy.methodsToInstrument = this.methodsToInstrument;
		copy.parentClassLoader = this.parentClassLoader;
		copy.rangeBlocks = this.rangeBlocks;
		copy.recordBlockExecutionOrder = this.recordBlockExecutionOrder;
		copy.resultLogFileName = this.resultLogFileName;
		copy.traceAndIdentifyRequests = this.traceAndIdentifyRequests;
		copy.useArrayParameterRecording = this.useArrayParameterRecording;
		copy.useBasicBlocks = this.useBasicBlocks;
		copy.useHighRegistersForCounting = this.useHighRegistersForCounting;
		copy.useResultCollector = this.useResultCollector;
		copy.writeClassesToDisk = this.writeClassesToDisk;
		copy.writeClassesToDiskDirectory = this.writeClassesToDiskDirectory;
		
		return copy;
	}

	/**
	 * @deprecated Static counting is implemented only in it's basics (opcode and method call counts). 
	 * It is not a focus of ByCounter.
	 * @return Reflects, whether dynamic or static method analysis is employed.
	 */
	@SuppressWarnings("dep-ann")
	public boolean getCountStatically() {
		return this.countStatically;
	}
	
	/**
	 * @return @see {@link #setInstrumentRecursively(boolean)}.
	 */
	public boolean getInstrumentRecursively() {
		return this.instrumentRecursively;
	}
	
	/**
	 * @return The methods to instrument described as <code>MethodDescriptor</code>.
	 */
	public List<MethodDescriptor> getMethodsToInstrument() {
		return this.methodsToInstrument;
	}

	/**
	 * @return The result log filename used if useResultCollector == false.
	 * The given filename is a prefix to the generated filename that includes 
	 * the method descriptor and a timestamp.
	 * @see #setResultLogFileName(String)
	 */
	public String getResultLogFileName() {
		return this.resultLogFileName;
	}

	/**
	 * Decides whether instrumentation for the recording of parameters of array construction takes place. Causes some additional overhead. 
	 * @return True if recording is inserted, false otherwise.
	 */
	public boolean getUseArrayParameterRecording() {
		return this.useArrayParameterRecording;
	}

	/**
	 * @return useHighRegistersForCounting
	 */
	public boolean getUseHighRegistersForCounting() {
		return this.useHighRegistersForCounting;
	}

	/**
	 * @return useResultCollector
	 */
	public boolean getUseResultCollector() {
		return this.useResultCollector;
	}

	/**
	 * When true, ByCounter will write the instrumented class files
	 * to the "bin_instrumented" directory.
	 * @return The value of writeClassesToDisk.
	 */
	public boolean getWriteClassesToDisk() {
		return this.writeClassesToDisk;
	}

	/**
	 * @deprecated Static counting is implemented only in it's basics (opcode and method call counts). 
	 * It is not a focus of ByCounter.
	 * @param countStatically When true, no runtime analysis is done. Instead the
	 * method is statically analysed.
	 */
	@SuppressWarnings("dep-ann")
	public void setCountStatically(boolean countStatically) {
		this.countStatically = countStatically;
	}

	/**
	 * @param methodsToInstrument Sets the methods to instrument described as <code>MethodDescriptor</code>.
	 */
	public void setMethodsToInstrument(
			List<MethodDescriptor> methodsToInstrument) {
		this.methodsToInstrument = methodsToInstrument;
	}


	/**
	 * Sets the filename for the log that is created if useResultCollector == false.
	 * Use this if you want to override the default file name 
	 * {@link #RESULT_LOG_DEFAULT_PREFIX}
	 * that consists a time stamp and the class and method name,
	 * and will be written to
	 * the {@link InstrumentationParameters#RESULT_LOG_DEFAULT_DIRECTORY} directory.
	 * The given filename is a prefix to the generated filename that includes 
	 * the method descriptor and a timestamp.
	 * 
	 * @param resultLogFileName the resultLogFileName to set
	 */
	public void setResultLogFileName(String resultLogFileName) {
		this.resultLogFileName = resultLogFileName;
	}


	/**
	 * Decides whether instrumentation for the recording of parameters of array construction takes place. Causes some additional overhead.
	 * @param useArrayParameterRecording Set to true if recording is to be inserted, false otherwise.
	 */
	public void setUseArrayParameterRecording(boolean useArrayParameterRecording) {
		this.useArrayParameterRecording = useArrayParameterRecording;
	}

	/**
	 * Decides whether to preallocate registers near max_locals instead of using LocalVariablesSorter.
	 * The default (when not called) is false.
	 * When true, the instrumented bytecode remains closer to the original bytecode in that the register numbers stay the same.
	 * Setting this to true might cause problems if the instrumented code uses very high register numbers (near 65000).
	 * @param useHighRegistersForCounting Defaults to false.
	 */
	public void setUseHighRegistersForCounting(boolean useHighRegistersForCounting) {
		this.useHighRegistersForCounting = useHighRegistersForCounting;
	}

	/**
	 * @param useResultCollector Set whether to use the 
	 * <code>CountingResultCollector</code>. When false, a log file is written.
	 * @see #setResultLogFileName(String)
	 */
	public void setUseResultCollector(boolean useResultCollector) {
		this.useResultCollector = useResultCollector;

		if(useResultCollector == false 
				&& (this.resultLogFileName == null 
					|| this.resultLogFileName.isEmpty())) {
			this.setResultLogFileName( 
				RESULT_LOG_DEFAULT_PREFIX);
		}
	}

	/**
	 * Sets the value of writeClassesToDisk.
	 * When true, ByCounter will write the instrumented class files
	 * to the "bin_instrumented" directory.
	 * @param write The new value for writeClassesToDisk.
	 */
	public void setWriteClassesToDisk(boolean write) {
		this.writeClassesToDisk = write;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {//TODO replace tabs with whitespaces, possibly using getTabs method used in printing counting results
		StringBuilder b = new StringBuilder("InstrumentationParameters {\n");
		b.append("counterPrecisionIsLong:             " + this.counterPrecisionIsLong + ", \n");
		b.append("countStatically:                    " + this.countStatically + ", \n");
		b.append("instrumentRecursively:              " + this.instrumentRecursively + ", \n");
		b.append("instrumentRecursivelyMaxDepth:      " + this.instrumentRecursivelyMaxDepth + ", \n");
		b.append("methodsToInstrument:                " + this.methodsToInstrument + ", \n");
		b.append("rangeBlocks:                        " + this.rangeBlocks + ", \n");
		b.append("recordBlockExecutionOrder:          " + this.recordBlockExecutionOrder + ", \n");
		b.append("resultLogFileName:                  " + this.resultLogFileName + ", \n");
		b.append("traceAndIdentifyRequests:           " + this.traceAndIdentifyRequests + ", \n");
		b.append("useArrayParameterRecording:         " + this.useArrayParameterRecording + ", \n");
		b.append("useBasicBlocks:                     " + this.useBasicBlocks + ", \n");
		b.append("useHighRegistersForCounting:        " + this.useHighRegistersForCounting + ", \n");
		b.append("useResultCollector:                 " + this.useResultCollector + ", \n");
		b.append("writeClassesToDisk:                 " + this.writeClassesToDisk + ", \n");
		b.append("writeClassesToDiskDirectory:        " + this.writeClassesToDiskDirectory + "\n");
		b.append("}");
		return b.toString();
	}

	/**
	 * This is used for instantiation of classes that are set to execute.
	 * For some applications it may be necessary to use a different ClassLoader 
	 * than the SystemClassLoader. For instance Eclipse plugins each have their 
	 * own ClassLoader which means Class.forName() may not have access to the 
	 * correct classpath.
	 * @param parentClassLoader The {@link ClassLoader} that will be used to 
	 * create instances of the classes to execute.
	 */
	public void setParentClassLoader(ClassLoader parentClassLoader) {
		this.parentClassLoader = parentClassLoader;
	}

	/**
	 * @return The {@link ClassLoader} set using {@link #setParentClassLoader(ClassLoader)}.
	 */
	public ClassLoader getParentClassLoader() {
		return parentClassLoader;
	}

	/**
	 * When true, bytecode instructions will be counted in groups made up of 
	 * identified basic blocks. The execution numbers of single instructions 
	 * are calculated after the execution.
	 * When false, every single bytecode instruction will be counted 
	 * by an individual counter.
	 * @param useBasicBlocks the useBasicBlocks to set
	 */
	public void setUseBasicBlocks(boolean useBasicBlocks) {
		this.useBasicBlocks = useBasicBlocks;
	}

	/**
	 * When true, bytecode instructions will be counted in groups made up of 
	 * identified basic blocks. The execution numbers of single instructions 
	 * are calculated after the execution.
	 * When false, every single bytecode instruction will be counted 
	 * by an individual counter.
	 * @return the useBasicBlocks
	 */
	public boolean getUseBasicBlocks() {
		return useBasicBlocks;
	}

	/**
	 * RequestIDs are UUIDs used to track the method call graph across threads.
	 * They are created in a root method (a method called with no request ID) 
	 * and then passed recursively on to all methods it calls.
	 * Note: Currently, constructors cannot be tracked.
	 * @param traceAndIdentifyRequests True, when request IDs are to be used. 
	 * False otherwise.
	 * Default is false.
	 */
	public void setTraceAndIdentifyRequests(boolean traceAndIdentifyRequests) {
		this.traceAndIdentifyRequests = traceAndIdentifyRequests;
	}

	/**
	 * RequestIDs are UUIDs used to track the method call graph across threads.
	 * They are created in a root method (a method called with no request ID) 
	 * and then passed recursively on to all methods it calls.
	 * Note: Currently, constructors cannot be tracked.
	 * @return True, when request IDs are used. False otherwise.
	 */
	public boolean getTraceAndIdentifyRequests() {
		return traceAndIdentifyRequests;
	}

	/**
	 * If {@link #getWriteClassesToDisk()} is enabled, classes will be written 
	 * to the here specified directory.
	 * @param outputClassDirectory The directory where instrumented class files 
	 * will be written to.
	 */
	public void setWriteClassesToDiskDirectory(File outputClassDirectory) {
		this.writeClassesToDiskDirectory = outputClassDirectory;
		
	}

	/**
	 * @return If {@link #getWriteClassesToDisk()} is enabled, classes will be written 
	 * to the here specified directory.
	 */
	public File getWriteClassesToDiskDirectory() {
		return this.writeClassesToDiskDirectory;
	}

	/**
	 * @param instrumentationScopeOverrideClassLevel the instrumentationScopeOverrideClassLevel to set
	 */
	public void setInstrumentationScopeOverrideClassLevel(
			InstrumentationScopeModeEnum instrumentationScopeOverrideClassLevel) {
		this.instrumentationScopeOverrideClassLevel = instrumentationScopeOverrideClassLevel;
	}

	/**
	 * @return the instrumentationScopeOverrideClassLevel
	 */
	public InstrumentationScopeModeEnum getInstrumentationScopeOverrideClassLevel() {
		return instrumentationScopeOverrideClassLevel;
	}

	/**
	 * @param instrumentationScopeOverrideMethodLevel the instrumentationScopeOverrideMethodLevel to set
	 */
	public void setInstrumentationScopeOverrideMethodLevel(
			InstrumentationScopeModeEnum instrumentationScopeOverrideMethodLevel) {
		this.instrumentationScopeOverrideMethodLevel = instrumentationScopeOverrideMethodLevel;
	}

	/**
	 * @return the instrumentationScopeOverrideMethodLevel
	 */
	public InstrumentationScopeModeEnum getInstrumentationScopeOverrideMethodLevel() {
		return instrumentationScopeOverrideMethodLevel;
	}
	
	/**
	 * @return
	 * A list of strings that cause a class to be ignored in the parsing 
	 * when found at the start of a package name.
	 */
	public String[] getIgnoredPackagePrefixes() {
		return ignoredPackagePrefixes;
	}

	/**
	 * @see #COUNTER_PRECISION_INT
	 * @see #COUNTER_PRECISION_LONG
	 * @return The precision.
	 */
	public boolean isCounterPrecisionIsLong() {
		return this.counterPrecisionIsLong;
	}

	/**
	 * @see #COUNTER_PRECISION_INT
	 * @see #COUNTER_PRECISION_LONG
	 * @param counterPrecisionIsLong The precision to set.
	 */
	public void setCounterPrecisionIsLong(boolean counterPrecisionIsLong) {
		this.counterPrecisionIsLong = counterPrecisionIsLong;
	}

	/**
	 * When set, instruments methods called from the {@link #setMethodsToInstrument(List)} that 
	 * are not Java API methods (packages java.*, javax.* sun.*) and not 
	 * native methods.
	 * Recursion will stop when {@link #getInstrumentRecursivelyMaxDepth} is 
	 * reached.
	 * @param instrumentRecursively When true, the above aplies.
	 * specified for instrumentation (@see {@link #setMethodsToInstrument}).
	 */
	public void setInstrumentRecursively(boolean instrumentRecursively) {
		this.instrumentRecursively = instrumentRecursively;
	}

	/**
	 * @see #setInstrumentRecursively(boolean)
	 */
	public void setInstrumentRecursively(boolean instrumentRecursively, int maxDepth) {
		this.instrumentRecursively = instrumentRecursively;
		this.instrumentRecursivelyMaxDepth = maxDepth;
	}

	/**
	 * @see #setInstrumentRecursively(boolean)
	 * @return depth
	 */
	public int getInstrumentRecursivelyMaxDepth() {
		return this.instrumentRecursivelyMaxDepth;
	}

	/**
	 * @see #getIgnoredPackagePrefixes()
	 * @param ignoredPackagePrefixes Prefixes of packages that are ignored.
	 */
	public static void setIgnoredPackagePrefixes(String[] ignoredPackagePrefixes) {
		InstrumentationParameters.ignoredPackagePrefixes = ignoredPackagePrefixes;
	}
	
	/**
	 * @see #getRecordBlockExecutionOrder()
	 * @param recordBlockExecutionOrder the recordBlockExecutionOrder to set
	 */
	public void setRecordBlockExecutionOrder(boolean recordBlockExecutionOrder) {
		this.recordBlockExecutionOrder = recordBlockExecutionOrder;
	}

	/**
	 * When true, record the exact order in which blocks are executed. This 
	 * applies to range blocks and to basic blocks.
	 * The order of the results as returned by the 
	 * {@link CountingResultCollector} then reflects the execution order.
	 * If this flag is set to false, the execution counts for each section are 
	 * aggregated.
	 * 
	 * <p>
	 * Note that enabling this option may result in memory and processing 
	 * overhead if the instrumented code contains sections that are executed 
	 * extremely often.
	 * </p>
	 * @return the recordBlockExecutionOrder
	 */
	public boolean getRecordBlockExecutionOrder() {
		return recordBlockExecutionOrder;
	}

	/**
	 * @return True, if there is a method that has codeareas defined using 
	 * the {@link MethodDescriptor#setCodeAreasToInstrument(LineNumberRange[])}
	 * method.
	 */
	public boolean hasMethodsWithCodeAreas() {
		if(getMethodsToInstrument() == null) {
			return false;
		}
		for(MethodDescriptor method : getMethodsToInstrument()) {
			if(method.getCodeAreasToInstrument() != null
							&& method.getCodeAreasToInstrument().length != 0) {
				return true;
			}
		}
		return false;
	}
}
