package de.uka.ipd.sdq.ByCounter.execution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.reporting.ICountingResultWriter;
import de.uka.ipd.sdq.ByCounter.utils.FullOpcodeMapper;
import de.uka.ipd.sdq.ByCounter.utils.IAllJavaOpcodes;

/**
 * Class that holds the results of an (instrumented) method run.
 * TODO test serialisation? test O/R-mapper? test XML serialisation?
 * TODO in the long term, "section counts" should accept entire method as sections,
 * and method call counts as well as opcodeCounts should become redundant
 * TODO all setter method should return the old value of the set/overwritten variable
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public class CountingResultBase
implements Serializable, Cloneable, IFullCountingResult, Comparable<IFullCountingResult>{

	/**
	 * Version for {@link Serializable} interface.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The highest possible number of a Java bytecode opcode.
	 */
	public static final int MAX_OPCODE = FullOpcodeMapper.mnemonics.length;
	
	/** Newline character for log output. */
	private static final String NEWLINE = System.getProperty("line.separator");

	/**
	 * see http://en.wikipedia.org/wiki/Data_log
	 */
	private static Logger log = Logger.getLogger(CountingResultBase.class.getCanonicalName());


	/**
	 * The returned CountingResult is completely different from the summands
	 * w.r.t. the method name, etc. Hence, it is not initialised and only
	 * holds the sum of the two added {@link CountingResultBase}s.
	 * Calling this method is quite expensive.
	 * @param left the first summand
	 * @param right the second summand
	 * @return an instance of {@link CountingResultBase} where only counts are initialised
	 */
	@SuppressWarnings("dep-ann")
	public synchronized static CountingResultBase add(CountingResultBase left, CountingResultBase right){
		long[] resultOpcodeCounts = new long[MAX_OPCODE];
		SortedMap<String,Long>  resultMethodCallCounts = new TreeMap<String, Long>();

		// add up all opcode counts
		resultOpcodeCounts = addOpcodeCounts(left.opcodeCounts, right.opcodeCounts);
		// add up all method call counts
		resultMethodCallCounts = addMethodCallCounts(left.methodCallCounts, right.methodCallCounts);
		// add up all array creation parameters
		ArrayParameters leftAP = ArrayParameters.copyFromCountingResult(left);
		ArrayParameters rightAP = ArrayParameters.copyFromCountingResult(right);
		ArrayParameters arrayParameters = ArrayParameters.add(leftAP, rightAP);

		CountingResultBase cr;
		cr = new CountingResultBase(
				null,
				null,
				null,
				new String(""),
				new String(""),
				-1L,
				-1L,
				resultOpcodeCounts,
				resultMethodCallCounts,
				arrayParameters.newArrayCounts,
				arrayParameters.newArrayDim,
				arrayParameters.newArrayTypes);

		return cr;
	}

	/**
	 * Add up method call counts.
	 * @param leftMethodCallCounts left operand
	 * @param rightMethodCallCounts right operand
	 * @return Sum of counts.
	 */
	private static SortedMap<String, Long> addMethodCallCounts(
			SortedMap<String, Long> leftMethodCallCounts,
			SortedMap<String, Long> rightMethodCallCounts) {
		TreeMap<String, Long> resultMethodCallCounts = new TreeMap<String, Long>();
		String keyString;
		Long resultValue;
		String rightKey_String;
		Long rightValue;

		// set all method call counts for which 'left' has keys:
		Iterator<String> iteratorMethods = leftMethodCallCounts.keySet().iterator();
		while (iteratorMethods.hasNext()) {
			keyString = iteratorMethods.next();
			rightValue = rightMethodCallCounts.get(keyString);
			resultValue = leftMethodCallCounts.get(keyString);
			if(rightValue != null) {
				resultValue += rightValue;
			}
			resultMethodCallCounts.put(
					new String(keyString),
					new Long(resultValue));
		}
		// set all method call counts for which only 'right' has keys:
		Iterator<String> methodsKeysetRight = rightMethodCallCounts.keySet().iterator();
		while (methodsKeysetRight.hasNext()) {
			rightKey_String = methodsKeysetRight.next();
			rightValue = rightMethodCallCounts.get(rightKey_String);
			resultValue = resultMethodCallCounts.get(rightKey_String);
			if(resultValue == null) {
				resultMethodCallCounts.put(rightKey_String, rightValue);
			}
		}
		return resultMethodCallCounts;
	}

	/**
	 * Add up the opcode count arrays
	 * @param leftOpcodeCounts Opcode counts of first summand.
	 * @param rightOpcodeCounts Opcode counts of second summand.
	 * @return The sum array.
	 */
	private static long[] addOpcodeCounts(long[] leftOpcodeCounts, long[] rightOpcodeCounts) {
		long[] resultOpcodeCounts = new long[MAX_OPCODE];
		// add up all opcode counts
		for(int i = 0; i < MAX_OPCODE; i++) {
			resultOpcodeCounts[i] = leftOpcodeCounts[i] + rightOpcodeCounts[i];
		}
		return resultOpcodeCounts;
	}

	/**
	 * The returned CountingResult is completely different from the summands
	 * w.r.t. the method name, etc. Hence, it is not initialised and only
	 * holds the sum of the two added {@link CountingResultBase}s.
	 * Calling this method is quite expensive.
	 * @param left the first summand
	 * @param right the second summand
	 * @return an instance of {@link CountingResultBase} where only counts are initialised
	 */
	@SuppressWarnings("dep-ann")
	public synchronized static CountingResultBase addMethodsAndInstructionsOnly(
			CountingResultBase left, CountingResultBase right){
		long[] resultOpcodeCounts = new long[MAX_OPCODE];
		SortedMap<String,Long>  resultMethodCallCounts = new TreeMap<String, Long>();

		// add up all opcode counts
		resultOpcodeCounts = addOpcodeCounts(left.opcodeCounts, right.opcodeCounts);
		// add up all method call counts
		resultMethodCallCounts = addMethodCallCounts(left.methodCallCounts, right.methodCallCounts);

		CountingResultBase cr;
		cr = new CountingResultBase(
				null, //requestID
				null, //ownID
				null, //callerID
				new String(""),
				new String(""),
				-1L,
				-1L,
				resultOpcodeCounts,
				resultMethodCallCounts,
				null, //resultNewArrayCountsArray,
				null, //resultNewArrayDimArray,
				null //resultNewArrayTypeArray,
				);
		return cr;
	}

	/**
	 * This is extremely sub-optimal and redundant. Optimise later, because called infrequently.
	 *
	 * @param opcodeCounts2
	 * @param opcodeCounts3
	 * @return
	 */
	private static final boolean compareCounts(
			long[] opcodeCounts2,
			long[] opcodeCounts3) {
		long currCountLeft;
		long currCountRight;
		for(int i = 0; i < MAX_OPCODE; i++) {//TODO move most likely case to the top...
			currCountLeft = opcodeCounts2[i];
			currCountRight = opcodeCounts3[i];
			if(currCountLeft==0){
				if(currCountRight==0){
					//everything is fine
				}else if(currCountRight>0){
//					log.fine("For instruction "+currKey+", " +
//							"the first CountingResult has count 0 while "
//							"the second CountingResult has count "+currCountRight);
					return false;
				}else if(currCountRight<0){
//					log.fine("For instruction "+currKey+", " +
//					"the first CountingResult has count 0 while "
//					"the second CountingResult has count "+currCountRight+", "+
//					"even though negative counts are not allowed!");
					return false;
				}
			}else if(currCountLeft<0){
//				log.severe("For instruction "+currKey+", " +
//				"the first CountingResult has a negative count of "+
//				currCountLeft+" even though negative counts are not allowed!");
				return false;
			}else{//if(currCountLeft>0)
				if(currCountRight==currCountLeft){//TODO make sure equals is not needed here... object level!
					//everything is fine
				}else{
//					log.severe("opcode "+currKey+": " +
//							"positive first count of "+currCountLeft+
//							" different from second count of "+currCountRight);
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String args[]){
		System.out.println("The main method of CountingResult serves as " +
				"a test case for compareCounts method");
		long[] opcodeCounts1 = new long[MAX_OPCODE];
		long[] opcodeCounts2 = new long[MAX_OPCODE];
		long[] opcodeCounts3 = new long[MAX_OPCODE];
		opcodeCounts1[1] = 11L;
		opcodeCounts1[2] =  0L;

		opcodeCounts2[1] =  11L;
		opcodeCounts2[3] =  0L;

		opcodeCounts3[1] =  12L;
		opcodeCounts3[2] =  0L;

		if(compareCounts(opcodeCounts1, opcodeCounts1)){
			System.out.println("Properly compared opcodeCounts1 with itself");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts1 with itself");
		}

		if(compareCounts(opcodeCounts2, opcodeCounts2)){
			System.out.println("Properly compared opcodeCounts2 with itself");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts2 with itself");
		}

		if(compareCounts(opcodeCounts3, opcodeCounts3)){
			System.out.println("Properly compared opcodeCounts3 with itself");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts3 with itself");
		}


		if(compareCounts(opcodeCounts1, opcodeCounts2)){
			System.out.println("Properly compared opcodeCounts1 and opcodeCounts2");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts1 and opcodeCounts2");
		}

		if(!compareCounts(opcodeCounts1, opcodeCounts3)){
			System.out.println("Properly compared opcodeCounts1 and opcodeCounts3");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts1 and opcodeCounts3");
		}

		if(compareCounts(opcodeCounts2, opcodeCounts1)){
			System.out.println("Properly compared opcodeCounts2 and opcodeCounts1");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts2 and opcodeCounts1");
		}

		if(!compareCounts(opcodeCounts2, opcodeCounts3)){
			System.out.println("Properly compared opcodeCounts2 and opcodeCounts3");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts2 and opcodeCounts3");
		}

		if(!compareCounts(opcodeCounts3, opcodeCounts1)){
			System.out.println("Properly compared opcodeCounts3 and opcodeCounts1");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts3 and opcodeCounts1");
		}

		if(!compareCounts(opcodeCounts3, opcodeCounts2)){
			System.out.println("Properly compared opcodeCounts3 and opcodeCounts2");
		}else{
			System.err.println("IMPROPERLY compared opcodeCounts3 and opcodeCounts2");
		}
	}

	/**
	 * TODO
	 */
	private transient long[] arrayCreationCounts = null;

	/**
	 * TODO
	 */
	private transient int[] arrayCreationDimensions = null;

	/**
	 * TODO
	 */
	private transient String[] arrayCreationTypeInfo = null;

	/**
	 * A {@link UUID} that is linked to the method calling the method that
	 * calls the protocol function. Used with {@link #ownID} to construct a CCT.
	 */
	private UUID callerID = null;

	/**
	 * TODO
	 */
	private transient List<Object> characterisations = null;

	/**
	 * TODO
	 */
	private transient List<String> characterisationTitles = null;

	/**
	 * TODO
	 */
	private transient List<Integer> characterisationTypes = null;


	/**
	 * If this counting result describes "forced inlining",
	 * this field describes the earliest start of any of the inlined methods.
	 * Note that forced inlining may start *after* this value,
	 * since the reporting of the (forced-inlined) method can have happened
	 * *after* forced inlining was switched on.
	 */
	public long forcedInlining_earliestStartOfInlinedMethod;

	//idea: QualifyingMethodSignatureInclClassName+.+
	//methodInvocationBeginning+.+
	//methodReportingTime+_+<optional:ParamDescription>_.SCResult
	/**
	 * There are no strict rules for the ID. For SPECjvm2008, we are using the
	 * file name of the file which is being compressed.
	 */
	private String ID;

	private boolean invariantMethodsAreInlined = false;

	/**
	 * This Map contains counts of method invocations, where the key is the
	 * method signature, the value is the invocation count.
	 */
	private SortedMap<String, Long> methodCallCounts = null;

	/**
	 * The timestamp which marks the beginning of execution (i.e. run)
	 * of the method for which this CountingResult holds bytecode counts.
	 */
	private long methodInvocationBeginning;

	/**
	 * The timestamp which was set immediately before this method called
	 * the CountingResultCollector. In other words, this is
	 * <b>approximately</b> the time when the method execution was finished.
	 */
	private long methodReportingTime;

	/**
	 * This array contains the counts of elementary bytecode instructions.
	 * The array index equals the opcode of the instruction.
	 */
	private long[] opcodeCounts;
	
	/**
	 * A {@link UUID} that is linked to the method calling the protocol function.
	 * Used with {@link #callerID} to construct a CCT.
	 */
	private UUID ownID;

	/**
	 * The name of the method whose execution was counted.
	 */
	private String qualifyingMethodName;

	/**
	 * A {@link UUID} that is linked to a request. This is used to keep track
	 * of execution sequences when dealing with parallel execution.
	 */
	private UUID requestID;

	/**
	 * Total count of all opcodes, except the four INVOKE* opcodes
	 */
	private Long totalCountExclInvokes;

	/**
	 * Total count of all opcodes, including the four INVOKE* opcodes
	 */
	private Long totalCountInclInvokes;

	public CountingResultBase(//TODO make sure the instructions are "full", even if some instruction counts are zero
			String qualifyingMethodName,
			long methodInvocationBeginning,
			long methodReportingTime,
			long[] opcodeCounts,
			SortedMap<String, Long> methodCallCounts) {
		this(//TODO make sure the instructions are "full", even if some instruction counts are zero
				null,null,null,null,
				qualifyingMethodName,
				methodInvocationBeginning,
				methodReportingTime,
				
				opcodeCounts,
				methodCallCounts,
				null,null,null);
	}
	
	/**
	 * Internal field which is using for "lazy computation"
	 */
	private boolean totalCountsAlreadyComputed = false;

	/**
	 * If range blocks were used, this is the index of the range block in the
	 * method ({@link #qualifyingMethodName}).
	 * Otherwise this is -1;
	 */
	private int indexOfRangeBlock;

	/** 
	 * Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	private long threadId;

	/** This constructor passes the arguments to the corresponding fields;
	 * the five fields that do not appear (this.characterisations,
	 * this.characterisationTitles, this.characterisationTypes and
	 * this.totalCountExclInvokes / this.totalCountInclInvokes) are
	 * initialised to empty collections (the first three) or set to 0
	 * (the last two).
	 * @param requestID
	 * @param ownID
	 * @param callerID
	 * @param ID
	 * @param qualifyingMethodName
	 * @param methodInvocationBeginning
	 * @param methodReportingTime
	 * @param opcodeCounts
	 * @param methodCallCounts
	 * @param arrayCreationCounts
	 * @param arrayCreationDimensions
	 * @param arrayCreationTypeInfo
	 */
	public CountingResultBase(//TODO make sure the instructions are "full", even if some instruction counts are zero
			UUID requestID,
			UUID ownID,
			UUID callerID,
			
			String ID,
			String qualifyingMethodName,
						
			long methodInvocationBeginning,
			long methodReportingTime,
			
			long[] opcodeCounts,
			SortedMap<String, Long> methodCallCounts,
			
			long[] arrayCreationCounts,
			int[] arrayCreationDimensions,
			String[] arrayCreationTypeInfo) {
		this.setRequestID(requestID);
		this.setOwnID(ownID);
		this.setCallerID(callerID);
		this.setID(ID);
		this.setThreadId(-1);
		
		this.arrayCreationCounts = arrayCreationCounts;
		this.arrayCreationDimensions = arrayCreationDimensions;
		this.arrayCreationTypeInfo = arrayCreationTypeInfo;
		
		this.indexOfRangeBlock = -1;
		this.methodCallCounts = methodCallCounts;
		this.methodInvocationBeginning = methodInvocationBeginning;
		this.methodReportingTime = methodReportingTime;
		assert(opcodeCounts.length==MAX_OPCODE);
		this.opcodeCounts = opcodeCounts;
		this.qualifyingMethodName = qualifyingMethodName; //should be a PRIVATE setter
		this.totalCountExclInvokes = 0L;
		this.totalCountInclInvokes = 0L;

		this.characterisations = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Object>(0);//this is eating up memory...
		this.characterisationTitles = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<String>(0);
		this.characterisationTypes = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Integer>(0);

//		this.computeTotalOpcodeCounts();
	}
	/**
	 * Construct result with fields set to 0/null;
	 */
	public CountingResultBase() {
		this.setRequestID(null);
		this.setOwnID(null);
		this.setCallerID(null);
		this.setID(null);
		this.setThreadId(-1);
		
		this.arrayCreationCounts = null;
		this.arrayCreationDimensions = null;
		this.arrayCreationTypeInfo = null;
		
		this.indexOfRangeBlock = -1;
		this.methodCallCounts = null;
		this.methodInvocationBeginning = 0;
		this.methodReportingTime = 0;
		this.opcodeCounts = null;
		this.qualifyingMethodName = null; //should be a PRIVATE setter
		this.totalCountExclInvokes = 0L;
		this.totalCountInclInvokes = 0L;

		this.characterisations = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Object>(0);//this is eating up memory...
		this.characterisationTitles = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<String>(0);
		this.characterisationTypes = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Integer>(0);
	}

	/** Adds the counts of this {@link CountingResultBase} instance to
	 * the counting results
	 * of the {@link CountingResultBase} instance given as parameter
	 * @param toBeAdded {@link CountingResultBase} instance whose counts are to be added
	 */
	@SuppressWarnings("dep-ann")
	public synchronized void add(CountingResultBase toBeAdded){
		CountingResultBase skeletonResult = add(this,toBeAdded);
		this.methodCallCounts = skeletonResult.getMethodCallCounts();
		this.opcodeCounts = skeletonResult.getOpcodeCounts();
		this.arrayCreationCounts = skeletonResult.getNewArrayCounts();
		this.arrayCreationDimensions = skeletonResult.getNewArrayDim();
		this.arrayCreationTypeInfo = skeletonResult.getNewArrayTypes();
		skeletonResult = null;
	}

	/** Adds the counts of this {@link CountingResultBase} instance to
	 * the counting results
	 * of the {@link CountingResultBase} instance given as parameter
	 * @param toBeAdded {@link CountingResultBase} instance whose counts are to be added
	 */
	@SuppressWarnings("dep-ann")
	public synchronized void add_methodsInstructionsOnly(CountingResultBase toBeAdded){
		CountingResultBase skeletonResult = addMethodsAndInstructionsOnly(this,toBeAdded);
		this.methodCallCounts = skeletonResult.getMethodCallCounts();
		this.opcodeCounts = skeletonResult.getOpcodeCounts();
		skeletonResult = null;
	}

	/** TODO document
	 * @param characterisationTitle
	 * @param characterisationType
	 * @param characterisationValue
	 */
	public void addCharacterisation(
			String characterisationTitle,
			Integer characterisationType,
			Object characterisationValue){
		if(this.characterisationTitles==null){
			this.characterisationTitles = new ArrayList<String>();
		}
		if(this.characterisationTypes==null){
			this.characterisationTypes = new ArrayList<Integer>();
		}
		if(this.characterisations==null){
			this.characterisations = new ArrayList<Object>();
		}
		this.characterisationTitles.add(characterisationTitle);
		this.characterisationTypes.add(characterisationType);
		this.characterisations.add(characterisationValue);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public CountingResultBase clone(){ //TODO fix/test/coverage-test this!
		CountingResultBase copy = null;

		try {
			copy = (CountingResultBase) super.clone();
		} catch (CloneNotSupportedException e) {
			// object.clone() cannot fail
			return null;
		}
		long[] copyOfArrayCreationCounts = null;
		int[] copyOfArrayCreationDimensions = null;
		String[] copyOfArrayCreationTypeInfo = null;
		
		if(this.arrayCreationCounts != null
				&& this.arrayCreationDimensions != null
				&& this.arrayCreationTypeInfo != null) {
			copyOfArrayCreationCounts = Arrays.copyOf(this.arrayCreationCounts, this.arrayCreationCounts.length);
			copyOfArrayCreationDimensions = Arrays.copyOf(this.arrayCreationDimensions, this.arrayCreationDimensions.length);
			copyOfArrayCreationTypeInfo = Arrays.copyOf(this.arrayCreationTypeInfo, this.arrayCreationTypeInfo.length);
		}
		
		copy.setRequestID(this.getRequestID());
		copy.setOwnID(this.getOwnID());
		copy.setCallerID(this.getCallerID());
		copy.setID(this.getID());
		copy.setQualifyingMethodName(this.qualifyingMethodName);
		copy.methodInvocationBeginning = this.methodInvocationBeginning;
		copy.methodReportingTime = this.methodReportingTime;
		copy.opcodeCounts = Arrays.copyOf(this.opcodeCounts, this.opcodeCounts.length);
		copy.methodCallCounts = new TreeMap<String,Long>(this.methodCallCounts);
		copy.arrayCreationCounts = copyOfArrayCreationCounts;
		copy.arrayCreationDimensions = copyOfArrayCreationDimensions;
		copy.arrayCreationTypeInfo = copyOfArrayCreationTypeInfo;
		copy.setThreadId(this.threadId);
		return copy;
	}

	/**
	 * TODO
	 * @throws Exception
	 */
	private synchronized void computeTotalOpcodeCounts() {
		long tentativeNewTotalCountInclInvokes = 0L;
		long tentativeNewTotalCountExclInvokes = 0L;
		long prevNewTotalCountInclInvokes = 0L;
		long prevNewTotalCountExclInvokes = 0L;
		for(int i = 0; i < MAX_OPCODE; i++){
			tentativeNewTotalCountInclInvokes =
				prevNewTotalCountInclInvokes
				+this.opcodeCounts[i];
			if(tentativeNewTotalCountInclInvokes<prevNewTotalCountInclInvokes){
				throw new RuntimeException("Overflow detected while " +
						"computing total opcode counts INCL invoke*");
			}
			prevNewTotalCountInclInvokes = tentativeNewTotalCountInclInvokes;
			if(i==IAllJavaOpcodes.INVOKEINTERFACE
					|| i==IAllJavaOpcodes.INVOKESPECIAL
					|| i==IAllJavaOpcodes.INVOKESTATIC
					|| i==IAllJavaOpcodes.INVOKEVIRTUAL){
				//not counting them
			}else{
				tentativeNewTotalCountExclInvokes =
					prevNewTotalCountExclInvokes
					+opcodeCounts[i];
				if(tentativeNewTotalCountExclInvokes<prevNewTotalCountExclInvokes){
					throw new RuntimeException("Overflow detected while " +
							"computing total opcode counts EXCL invoke*");
				}
				prevNewTotalCountExclInvokes = tentativeNewTotalCountExclInvokes;
			}
		}
		this.totalCountExclInvokes = prevNewTotalCountExclInvokes;
		this.totalCountInclInvokes = prevNewTotalCountInclInvokes;
		this.totalCountsAlreadyComputed = true;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public long[] getArrayCreationCounts() {
		return this.arrayCreationCounts;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public int[] getArrayCreationDimensions() {
		return arrayCreationDimensions;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public String[] getArrayCreationTypeInfo() {
		return arrayCreationTypeInfo;
	}

	/**
	 * @return the callerID
	 */
	public UUID getCallerID() {
		return callerID;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public List<Object> getCharacterisations() {
		return characterisations;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public List<String> getCharacterisationTitles() {
		return characterisationTitles;
	}

	/**
	 * TODO
	 * @return TODO
	 */
	public List<Integer> getCharacterisationTypes() {
		return characterisationTypes;
	}

	/**
	 * @return the ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Simple getter for method call counts.
	 * @return A {@link HashMap} were the method name is mapped to the number
	 * of calls of that method.
	 */
	public SortedMap<String, Long> getMethodCallCounts() {
		return methodCallCounts;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getMethodCount(java.lang.String)
	 */
	public Long getMethodCount(String methodName){
		return getMethodCountByString(methodName);
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getMethodCountByString(java.lang.String)
	 */
	public Long getMethodCountByString(String methodName){
		Long count = this.methodCallCounts.get(methodName);//TODO check keys!
		if(count==null){
			return new Long(NO_COUNT_AVAILABLE);
		}
		return count;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getMethodInvocationBeginning()
	 */
	public long getMethodInvocationBeginning() {
		return methodInvocationBeginning;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getMethodReportingTime()
	 */
	public long getMethodReportingTime() {
		return methodReportingTime;
	}

	/**
	 * Simple getter
	 * @return Counts for array constructions when recording was enabled; <code>null</code> else.
	 */
	public long[] getNewArrayCounts() {
		return arrayCreationCounts;
	}

	/**
	 * Simple getter for the dimension of the new array if applicable; -1 else.
	 * @return The array dimension when recording was enabled as descriped
	 * above. Null else.
	 */
	public int[] getNewArrayDim() {
		return arrayCreationDimensions;
	}

	/**
	 * Simple getter for the types of the new arrays.
	 * @return The array type when recording was enabled. Null else.
	 */
	public String[] getNewArrayTypes() {
		return arrayCreationTypeInfo;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getOpcodeCount(int)
	 */
	public Long getOpcodeCount(int opcode){
		return getOpcodeCountByInteger(opcode);
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getOpcodeCount(java.lang.String)
	 */
	public Long getOpcodeCount(String opcode){
		return getOpcodeCountByString(opcode);
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getOpcodeCountByInteger(int)
	 */
	public Long getOpcodeCountByInteger(int opcode){
		Long count = this.opcodeCounts[opcode];
		if(count==null){
			return new Long(NO_COUNT_AVAILABLE);
		}
		return count;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getOpcodeCountByString(java.lang.String)
	 */
	public Long getOpcodeCountByString(String opcode){
		Long count = this.opcodeCounts[FullOpcodeMapper.getOpcodeOfMnemonic(opcode)];
		if(count==null){
			return new Long(NO_COUNT_AVAILABLE);
		}
		return count;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getOpcodeCounts()
	 */
	public long[] getOpcodeCounts() {
		return opcodeCounts;
	}

	/**
	 * @return the ownID
	 */
	public UUID getOwnID() {
		return ownID;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getQualifyingMethodName()
	 */
	public String getQualifyingMethodName() {
		return qualifyingMethodName;
	}

	/**
	 * @return the requestID
	 */
	public UUID getRequestID() {
		return requestID;
	}

	/**
	 * @return Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	public long getThreadId() {
		return threadId;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getTotalCount(boolean)
	 */
	public synchronized Long getTotalCount(boolean includeInvokeOpcodes) {
		if(!totalCountsAlreadyComputed){
			this.computeTotalOpcodeCounts();
		}
		if(includeInvokeOpcodes){
			return totalCountInclInvokes;
		}
		return totalCountExclInvokes;
	}

	/**
	 * @return totalCountExclInvokes
	 */
	public Long getTotalCountExclInvokes() {
		return totalCountExclInvokes;
	}

	/**
	 * @return totalCountInclInvokes
	 */
	public Long getTotalCountInclInvokes() {
		return totalCountInclInvokes;
	}

	/**
	 * @return Value of the flag indicating whether called methods are inlined 
	 * (already part of bytecode counts)
	 */
	public boolean isInvariantMethodsAreInlined() {
		return invariantMethodsAreInlined;
	}

	/**
	 * Returns the total number of method invocations
	 * @return the total number of method invocations
	 */
	public synchronized Long methodCountSum() {
		return getTotalCount(true) - getTotalCount(false);
	}

	/**
	 * Does not perform any plausibility checks
	 * @param methodCounts
	 * @return old method counts
	 */
	public SortedMap<String, Long> overwriteMethodCallCounts(SortedMap<String, Long> methodCounts){//TODO consider warning/logging
		SortedMap<String, Long> methodCountsOld = this.methodCallCounts;
		this.methodCallCounts = methodCounts;
		return methodCountsOld;
	}

	/**
	 * Does not perform any plausibility checks
	 * @param newOpcodeCounts
	 * @return old opcode counts (before overwriting)
	 */
	public long[] overwriteOpcodeCounts(long[] newOpcodeCounts){
		long[] oldOpcodeCounts = this.opcodeCounts;
		this.opcodeCounts = newOpcodeCounts;
		return oldOpcodeCounts;
	}

	/**
	 * Does not affect parametric counts, etc.
	 */
	public void resetMethodAndInstructionCounts(){
		this.opcodeCounts = new long[MAX_OPCODE];
		this.methodCallCounts = new TreeMap<String, Long>();
	}

	/**
	 * @param callerID the callerID to set
	 * @return The previously set caller id.
	 */
	public UUID setCallerID(UUID callerID) {
		UUID oldCallerId = this.callerID;
		this.callerID = callerID;
		return oldCallerId;
	}

	/**
	 * @param characterisations
	 * @return The previously set characterisations.
	 */
	public List<Object> setCharacterisations(List<Object> characterisations) {
		List<Object> oldCharacterisations = this.characterisations;
		this.characterisations = characterisations;
		return oldCharacterisations;
	}

	/**
	 * @param characterisationTitles
	 * @return The previously set characterisationTitles.
	 */
	public List<String> setCharacterisationTitles(List<String> characterisationTitles) {
		List<String> oldCharacterisationTitles = this.characterisationTitles;
		this.characterisationTitles = characterisationTitles;
		return oldCharacterisationTitles;
	}

	/**
	 * @param characterisationTypes
	 */
	public void setCharacterisationTypes(List<Integer> characterisationTypes) {
		this.characterisationTypes = characterisationTypes;
	}

	/**
	 * @param newID
	 */
	public void setID(String newID) {
		ID = newID;
	}

	/**
	 * Flag indicating whether called methods are inlined (already part of bytecode counts)
	 * @param invariantMethodsAreInlined
	 */
	public void setInvariantMethodsAreInlined(boolean invariantMethodsAreInlined) {
		this.invariantMethodsAreInlined = invariantMethodsAreInlined;
	}

	/**
	 * @param methodInvocationBeginning
	 */
	public void setMethodInvocationBeginning(long methodInvocationBeginning) {
		this.methodInvocationBeginning = methodInvocationBeginning;
	}

	/**
	 * TODO consider adding a logger to CountingResult;
	 * @param methodReportingTime
	 */
	public void setMethodReportingTime(long methodReportingTime) {
		if(this.methodReportingTime==0){
			System.err.println("Method reporting time "+this.methodReportingTime+
					" about to be overwritten with "+methodReportingTime);
		}
		this.methodReportingTime = methodReportingTime;
	}
	
	/**
	 * @see #getArrayCreationCounts()
	 * @param arrayCreationCounts array creation counts
	 */
	public void setArrayCreationCounts(long[] arrayCreationCounts) {
		this.arrayCreationCounts = arrayCreationCounts;
	}
	
	/**
	 * @see #getArrayCreationDimensions()
	 * @param arrayCreationDimensions .
	 */
	public void setArrayCreationDimensions(int[] arrayCreationDimensions) {
		this.arrayCreationDimensions = arrayCreationDimensions;
	}
	
	/**
	 * @see #getArrayCreationTypeInfo()
	 * @param arrayCreationTypeInfo .
	 */
	public void setArrayCreationTypeInfo(String[] arrayCreationTypeInfo) {
		this.arrayCreationTypeInfo = arrayCreationTypeInfo;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#setOpcodeCount(int, java.lang.Long)
	 */
	public void setOpcodeCount(int opcode, Long count) {
		this.opcodeCounts[opcode] = count;
		this.totalCountsAlreadyComputed = false;
	}
	
	/**
	 * @param opcodeCounts Execution counts per opcode.
	 * @see #getOpcodeCounts()
	 */
	public void setOpcodeCounts(long[] opcodeCounts) {
		this.opcodeCounts = opcodeCounts;
	}

	/**
	 * @param ownID the ownID to set
	 */
	public void setOwnID(UUID ownID) {
		this.ownID = ownID;
	}
	
	/**
	 * @param qualifyingMethodName {@link de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getQualifyingMethodName()}
	 */
	public void setQualifyingMethodName(String qualifyingMethodName) {
		this.qualifyingMethodName = qualifyingMethodName;
	}

	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(UUID requestID) {
		this.requestID = requestID;
	}

	/**
	 * @param threadId Id of the thread from which the result was reported.
	 * @see Thread#getId()
	 */
	public void setThreadId(long threadId) {
		this.threadId = threadId;
	}

	public void setTotalCountExclInvokes(Long totalCountExclInvokes) {
		this.totalCountExclInvokes = totalCountExclInvokes;
		this.totalCountsAlreadyComputed = true; //TODO: not quite true - not portable
	}

	public void setTotalCountInclInvokes(Long totalCountInclInvokes) {
		this.totalCountInclInvokes = totalCountInclInvokes;
		this.totalCountsAlreadyComputed = true; //TODO: not quite true - not portable
	}

	/**
	 * TODO replace return type to indicate precise location of difference (using the sum 1+2+4+8+16+etc.)
	 * @param vr
	 * @return True, if counting results equal (shallow).
	 */
	public boolean shallowEquals(CountingResultBase vr){
		if(this.invariantMethodsAreInlined!=vr.invariantMethodsAreInlined){
			System.out.println("CountingResult.shallowEquals: == on invariantMethodsAreInlined: "+this.invariantMethodsAreInlined+" vs "+vr.invariantMethodsAreInlined);
			return false;
		}
		if(this.qualifyingMethodName!=vr.qualifyingMethodName){
			System.out.println("CountingResult.shallowEquals: == on qualifyingMethodName: "+this.qualifyingMethodName+" vs "+vr.qualifyingMethodName);
			return false;
		}
		if(this.totalCountExclInvokes!=vr.totalCountExclInvokes){
			System.out.println("CountingResult.shallowEquals: == on totalCountExclInvokes: "+this.totalCountExclInvokes+" vs "+vr.totalCountExclInvokes);
			return false;
		}
		if(this.totalCountInclInvokes!=vr.totalCountInclInvokes){
			System.out.println("CountingResult.shallowEquals: == on totalCountInclInvokes returns false: "+this.totalCountInclInvokes+" vs "+vr.totalCountInclInvokes);
			return false;
		}

		if(!this.methodCallCounts.equals(vr.methodCallCounts)){
			System.out.println("CountingResult.shallowEquals: equals on methodCallCounts returns false"/*+this.methodCallCounts+" vs "+vr.methodCallCounts*/);
			return false;
		}

		//TODO care for the case of zeros vs. non-figurement...
		if(this.opcodeCounts.equals(vr.opcodeCounts)){
			//could say "return true;" here directly, because no other check is following
		}else{
			if(!compareCounts(opcodeCounts,vr.opcodeCounts)){
				System.out.println("CountingResult.shallowEquals: " +
						"equals on opcodeCounts returns false"
						/*+this.opcodeCounts+" vs "+vr.opcodeCounts*/);
				return false;
			}
//			else{
				//could say "return true;" here directly, because no other check is following
				//
				//everything is fine despite the fact that "equals" returned false:
				//this is due to the possibility that CountingResult is not
				//obliged to explicitly list zero-counted opcodes; they may
				//be skipped internally because values are stored using TreeMaps
				//
				//TODO instead of this dirty hack, consider enforcing fully
				//complete TreeMaps (this may result in ca. 200 additional
				//TreeMap entries, though...
//			}
		}
//		if(!Arrays.equals(this.arrayCreationCounts, vr.arrayCreationCounts)){
//			return false;
//		}
//		if(!Arrays.equals(this.arrayCreationDimensions, vr.arrayCreationDimensions)){
//			return false;
//		}
//		if(!Arrays.equals(this.arrayCreationTypeInfo, vr.arrayCreationTypeInfo)){
//			return false;
//		}
//		if(!this.characterisations.equals(vr.characterisations)){
//			return false;
//		}
//		if(!this.characterisationTitles.equals(vr.characterisationTitles)){
//			return false;
//		}
		return true;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){ //TODO add missing fields (data type, ...)
		StringBuffer sb = new StringBuffer();
		sb.append("\n"+
				  "      "+this.getClass().getSimpleName()+" (hash code: "+this.hashCode()+")\n");
		sb.append("      > Method name     : "+this.qualifyingMethodName+ " (Invocation UUID: " + this.ownID + ")\n");
		sb.append("      > Method duration : "+(this.methodReportingTime-this.methodInvocationBeginning)+
				"(start: "+this.methodInvocationBeginning+", end: "+this.methodReportingTime+" \n");
		sb.append("      > Opcode counts   : "+Arrays.toString(this.opcodeCounts)+"\n");
		sb.append("      > Method counts   : "+this.methodCallCounts+"\n");
//		sb.append("      > Method input    : "+this.inputParams+"\n");
//		sb.append("      > Method output   : "+this.outputParams+"\n");
//		sb.append("      > Array creations : "+this.arrayCreationCounts+"\n");
//		sb.append("      > Array dimensions: "+this.arrayCreationDimensions+"\n");
//		sb.append("      > Array type infos: "+this.arrayCreationTypeInfo+"\n");
//		sb.append("      > Sect. opc. cnts : "+this.sectionInstCounts+"\n");
//		sb.append("      > Sect. meth. cnts: "+this.sectionMethCounts+"\n");
		return sb.toString();
	}
	
	/**
	 * @param indexOfRangeBlock the indexOfRangeBlock to set
	 */
	public void setIndexOfRangeBlock(int indexOfRangeBlock) {
		this.indexOfRangeBlock = indexOfRangeBlock;
	}

	/**
	 * @return If range blocks were used, this is the index of the range block 
	 * in the method ({@link #getQualifyingMethodName()}).
	 * Otherwise this is -1;
	 */
	public int getIndexOfRangeBlock() {
		return indexOfRangeBlock;
	}

	public long getForcedInlining_earliestStartOfInlinedMethod() {
		return this.forcedInlining_earliestStartOfInlinedMethod;
	}

	public boolean isTotalCountsAlreadyComputed() {
		return this.totalCountsAlreadyComputed;
	}

	/**
	 * Return an appropriate number of tabs to follow the given string.
	 * This is for log formatting purposes only.
	 * @param str String after which the tabs shall follow
	 * @param maxNumTabs maximum number of tabs to return.
	 * @return A string containing a fitting number of tabs.
	 */
	private static synchronized String getTabs(String str, int maxNumTabs) {
		StringBuilder tabs = new StringBuilder();
		for(int i = maxNumTabs; i > 0; i--) {
			if(str.length() < 8*i) {//TODO encode tab width variably?
				tabs.append("\t");
			} else {
				break;
			}
		}
		return tabs.toString();
	}

	/**
	 * Print a log message that reports the result, listing all counts and
	 * data that was collected.
	 * @param printZeros When true, opcodes with an execution count of 0 are printed.
	 * @param vertically When true, print as one opcode/method count per line.
	 * @return The string with the logged message.
	 */
	public synchronized String logResult(
			boolean printZeros, //eigentlich 3 Abstufungen: gar nicht; wie gespeichert; alle opcodes (auch wenn nicht gespeichert)
			boolean vertically //TODO currently ignored
			){
		return logResult(printZeros, vertically, Level.INFO);
	}

	/**
	 * Print a log message that reports the result, listing all counts and
	 * data that was collected.
	 * @param printZeros When true, opcodes with an execution count of 0 are printed.
	 * @param vertically When true, print as one opcode/method count per line.
	 * @param loggingLevel {@link Level} used to log the message.
	 * @return The string with the logged message.
	 */
	public synchronized String logResult(
			boolean printZeros, //eigentlich 3 Abstufungen: gar nicht; wie gespeichert; alle opcodes (auch wenn nicht gespeichert)
			boolean vertically, //TODO currently ignored
			Level loggingLevel
			) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n==START========= Logging CountingResult ================");
		sb.append(NEWLINE);
		String qualifyingMethodName = getQualifyingMethodName();
		if(qualifyingMethodName==null || qualifyingMethodName.equals("")) {
			log.severe("Qualifying method name is null or empty, EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}
		sb.append("qualifyingMethodName: ");
		sb.append(qualifyingMethodName);
		sb.append(NEWLINE);
		sb.append("requestID: ");
		sb.append(getRequestID());
		sb.append(", ownID: ");
		sb.append(getOwnID());
		sb.append(", callerID: ");
		sb.append(getCallerID());
		sb.append(", threadId: ");
		sb.append(getThreadId());
		sb.append(NEWLINE);
		if (getIndexOfRangeBlock() == -1) {
			sb.append("The whole method was measured (cr.getIndexOfRangeBlock() == -1 in CountingResultCollector.logResult)");
			sb.append(NEWLINE);
		} else {
			sb.append("Section number ");
			sb.append(getIndexOfRangeBlock());
			sb.append(" was measured.");
			sb.append(NEWLINE);
		}
//		if(cr==null){
//			log.severe("The CountingResult to log is null - nothing to do, returning immediately.");
//			log.info("== END ========= Logging CountingResult ================");
//			return;
//		}

		long[] opcodeCounts = getOpcodeCounts();
		if(opcodeCounts == null) {
			log.severe("Opcode counts is null... EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		SortedMap<String, Long> methodCallCounts = getMethodCallCounts();
		if(methodCallCounts == null) {
			log.severe("Method counts hashmap is null... EXITING");
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		long time = getMethodInvocationBeginning();
		if(time<0) {
			log.severe("Wrong time: "+time);//TODO which kind of time is this?
			sb.append("== END ========= Logging CountingResult ================");
			sb.append(NEWLINE);
			log.info(sb.toString());
			return sb.toString();
		}

		List<ICountingResultWriter> resultWriters = CountingResultCollector.getInstance().getAllResultWriters();
		if(resultWriters.size()>0){
			log.fine("Logging CountinResult using "+resultWriters.size()+
					" registered result writers");
			for(ICountingResultWriter rw : resultWriters){
				rw.writeResultToFile(this, false, -1);//TODO make this better/parameterised
			}
		}

		long[] newArrayCounts 						= getNewArrayCounts();
		int[] newArrayDims 							= getNewArrayDim();
		String[] newArrayTypes 						= getNewArrayTypes();

		// No checks here (but below!) for array results, because null is also
		// returned when array parameter recording is disabled.

		// make sure DisplayOpcodes does not interfere with the output...?
		long totalCountOfAllOpcodes = 0; //you need longs for that...
		long totalCountOfAllMethods = 0; //you need longs for that...

		String 	tabs;					// tabulators (for logging)
		String 	currentOpcodeString;	// opcode as string
		long 	currentOpcodeCount;		// opcode count
		long 	currentMethodCount = 0;	// method count


		int numberOfOpcodesWithNonzeroFrequencies=0;
		for(int i = 0; i < CountingResultBase.MAX_OPCODE; i++) {
			currentOpcodeString = FullOpcodeMapper.getMnemonicOfOpcode(i);
			currentOpcodeCount 	= opcodeCounts[i];
			tabs 				= getTabs(currentOpcodeString + ":", 2);
//			dataset.addValue(currentOpcodeCount, qualifyingMethodName+": instructions", currentOpcodeString);
			if(currentOpcodeCount!=0 || printZeros){
				sb.append(currentOpcodeString);
				sb.append(":");
				sb.append(tabs);
				sb.append(currentOpcodeCount);
				sb.append(NEWLINE);
			}
			if((totalCountOfAllOpcodes+currentOpcodeCount)<totalCountOfAllOpcodes){
				log.severe("OVERFLOW while adding opcode counts... use BigInteger instead");
			}else{
				totalCountOfAllOpcodes += currentOpcodeCount;
				if(currentOpcodeCount>0){
					numberOfOpcodesWithNonzeroFrequencies++;
				}
			}
		}

		Iterator<String> methodSigs = methodCallCounts.keySet().iterator();
		SortedSet<String> classesContainingMethodSigs = new TreeSet<String>();
		String currentSig, className;
		while(methodSigs.hasNext()) {
			currentSig = methodSigs.next();
			className = currentSig.split("\\.")[0];
			classesContainingMethodSigs.add(className);
			currentMethodCount = methodCallCounts.get(currentSig);
			tabs = getTabs(currentSig + ":", 9);
//			dataset.addValue(currentMethodCount, qualifyingMethodName+": methods", currentMethodSignature);
			sb.append(currentSig);
			sb.append(":");
			sb.append(tabs);
			sb.append(currentMethodCount);
//			sb.append(" (class: "+className+")");
			sb.append(NEWLINE);
			if(totalCountOfAllMethods + currentMethodCount<totalCountOfAllMethods){
				log.severe("OVERFLOW while adding method counts");
			}else{
				totalCountOfAllMethods += currentMethodCount;
			}
//			instrnames_texSB.append(currentMethodSignature+" & ");
//			instrcounts_texSB.append(currentMethodCount+" & ");
		}
//		instrnames_texSB.append("total \\\\");
//		instrcounts_texSB.append(totalCountOfAllMethods+" \\\\");

		// because null is a valid value for the array*Something* arrays,
		// we need to be carefull here.
		if(newArrayCounts != null
				&& newArrayDims != null
				&& newArrayTypes != null) {
			for(int i = 0; i < newArrayCounts.length; i++) {
				sb.append("new array of type '");
				sb.append(newArrayTypes[i]);
				sb.append("'");
				sb.append((newArrayDims[i] > 0 ? ", dim " + newArrayDims[i] : ""));
				sb.append(": ");
				sb.append(newArrayCounts[i]);
				sb.append(NEWLINE);
			}
		}
		sb.append("====================================================");
		sb.append(NEWLINE);
		sb.append(totalCountOfAllOpcodes);
		sb.append(" instruc. executions of ");
		sb.append(numberOfOpcodesWithNonzeroFrequencies);
		sb.append(" different opcodes were counted.");
		sb.append(NEWLINE);
		sb.append(totalCountOfAllMethods);
		sb.append(" methods invocations of ");
		sb.append(methodCallCounts.size());
		sb.append(" different signatures were counted, from "+classesContainingMethodSigs.size()+" classes.");
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		int i=1;
		int approxNrOfJavaPlatformClasses = 0;
		StringBuffer sb2 = new StringBuffer();
		sb.append("API / platform classes: \n");
		for(String classs : classesContainingMethodSigs){
			if(classs.startsWith("java/") || classs.startsWith("javax/") || classs.startsWith("sun/")){
				approxNrOfJavaPlatformClasses++;
				sb.append("class "+i+": "+classs+"\n");
			}else{
				sb2.append("class "+i+": "+classs+"\n");
			}
			i++;
		}
		sb.append((classesContainingMethodSigs.size()-approxNrOfJavaPlatformClasses)+
				" are 'business' classes outside of the Java platform:\n");
		sb.append(sb2.toString());
		sb.append(NEWLINE);
		sb.append(NEWLINE);
		sb.append("== END ========= Logging CountingResult ================");
		sb.append(NEWLINE);
		String ret = sb.toString();
		log.log(loggingLevel, sb.toString());
		return ret;
	}

	/** Compares {@link #getMethodInvocationBeginning()}.
	 * When {@link #getMethodInvocationBeginning()} is the same, but the objects 
	 * differ, this object is always smaller than the given object.
	 * @param o {@link IFullCountingResult} to compare to.*/
	public int compareTo(IFullCountingResult o) {
		int compareInvBeginning = new Long(methodInvocationBeginning).compareTo(o.getMethodInvocationBeginning());
		if(compareInvBeginning == 0 && this.hashCode() != o.hashCode()) {
			return -1;
		}
		return compareInvBeginning;
	}
}
