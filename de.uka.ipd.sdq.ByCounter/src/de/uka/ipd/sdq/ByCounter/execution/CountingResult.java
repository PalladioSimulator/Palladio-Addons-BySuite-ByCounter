package de.uka.ipd.sdq.ByCounter.execution;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

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
public final class CountingResult
implements Serializable, Cloneable, IFullCountingResult{

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	public static final int MAX_OPCODE = FullOpcodeMapper.mnemonics.length;

	/**
	 * The returned CountingResult is completely different from the summands
	 * w.r.t. the method name, etc. Hence, it is not initialised and only
	 * holds the sum of the two added {@link CountingResult}s.
	 * Calling this method is quite expensive.
	 * @param left the first summand
	 * @param right the second summand
	 * @return an instance of {@link CountingResult} where only counts are initialised
	 * @deprecated because not adapted to the new fields
	 */
	@SuppressWarnings("dep-ann")
	public synchronized static CountingResult add(CountingResult left, CountingResult right){
		//TODO replace cloning
		long[] resultOpcodeCounts = new long[MAX_OPCODE];
		TreeMap<String,Long>  resultMethodCallCounts = new TreeMap<String, Long>();
		// the following three lists need to keep their indices in sync
		ArrayList<Long> resultNewArrayCounts = new ArrayList<Long>();
		ArrayList<Integer> resultNewArrayDim = new ArrayList<Integer>();
		ArrayList<String> resultNewArrayType = new ArrayList<String>();

		long[] resultNewArrayCountsArray = null;
		int[] resultNewArrayDimArray = null;
		String[] resultNewArrayTypeArray = null;

		String keyString;

		Long resultValue;
		String rightKey_String;
		Long rightValue;

		// add up all opcode counts
		for(int i = 0; i < left.opcodeCounts.length; i++) {
			resultOpcodeCounts[i] = left.opcodeCounts[i] + right.opcodeCounts[i];
		}

		// set all method call counts for which 'left' has keys:
		Iterator<String> iteratorMethods = left.methodCallCounts.keySet().iterator();
		while (iteratorMethods.hasNext()) {
			keyString = iteratorMethods.next();
			rightValue = right.methodCallCounts.get(keyString);
			resultValue = left.methodCallCounts.get(keyString);
			if(rightValue != null) {
				resultValue += rightValue;
			}
			resultMethodCallCounts.put(
					new String(keyString),
					new Long(resultValue));
		}
		// set all method call counts for which only 'right' has keys:
		Iterator<String> methodsKeysetRight = right.getMethodCallCounts().keySet().iterator();
		while (methodsKeysetRight.hasNext()) {
			rightKey_String = methodsKeysetRight.next();
			rightValue = right.methodCallCounts.get(rightKey_String);
			resultValue = resultMethodCallCounts.get(rightKey_String);
			if(resultValue == null) {
				resultMethodCallCounts.put(rightKey_String, rightValue);
			}
		}
		if(left.getNewArrayCounts() != null && right.getNewArrayCounts() != null) {
			// copy array counts from 'left':
			for(int i = 0; i < left.getNewArrayCounts().length; i++) {
				resultNewArrayCounts.add(left.getNewArrayCounts()[i]);
				resultNewArrayDim.add(left.getNewArrayDim()[i]);
				resultNewArrayType.add(left.getNewArrayTypes()[i]);
			}

			int rightDim;
			String rightType;
			long rightCount;
			int resultDim;
			String resultType;
			boolean currentCountDone;
			// now merge in the array counts from 'right'
			for(int i = 0; i < right.getNewArrayCounts().length; i++) {
				rightDim = right.getNewArrayDim()[i];
				rightType = right.getNewArrayTypes()[i];
				rightCount = right.getNewArrayCounts()[i];
				currentCountDone = false;
				// is this entry in the list already?
				for(int j = 0; j < resultNewArrayCounts.size() && !currentCountDone; j++) {
					resultDim = resultNewArrayDim.get(j);
					resultType = resultNewArrayType.get(j);
					if(rightDim == resultDim
							&& rightType.equals(resultType)) {
						// add the count value
						resultNewArrayCounts.set(j,
								resultNewArrayCounts.get(j) + rightCount);
						currentCountDone = true;
					}
				}
				if(!currentCountDone) {
					// we have a new type/dimension; add it to the result
					resultNewArrayCounts.add(rightCount);
					resultNewArrayDim.add(rightDim);
					resultNewArrayType.add(rightType);
				}
			}
			// create arrays from the arraylists
			int numOfNewArray = resultNewArrayCounts.size();
			resultNewArrayCountsArray = new long[numOfNewArray];
			resultNewArrayDimArray = new int[numOfNewArray];
			resultNewArrayTypeArray = resultNewArrayType.toArray(new String[numOfNewArray]);
			for(int i = 0; i < numOfNewArray; i++) {
				resultNewArrayCountsArray[i] = resultNewArrayCounts.get(i);
				resultNewArrayDimArray[i] = resultNewArrayDim.get(i);
			}
		}

		CountingResult cr;
		cr = new CountingResult(
				null,
				null,
				null,
				new String(""),
				new String(""),
				0,
				0L,
				0L,
				-1L,
				-1L,
				resultOpcodeCounts,
				resultMethodCallCounts,
				resultNewArrayCountsArray,
				resultNewArrayDimArray,
				resultNewArrayTypeArray,
				null,
				null);
		return cr;
	}

	/**
	 * The returned CountingResult is completely different from the summands
	 * w.r.t. the method name, etc. Hence, it is not initialised and only
	 * holds the sum of the two added {@link CountingResult}s.
	 * Calling this method is quite expensive.
	 * @param left the first summand
	 * @param right the second summand
	 * @return an instance of {@link CountingResult} where only counts are initialised
	 * @deprecated because not adapted to the new fields
	 */
	@SuppressWarnings("dep-ann")
	public synchronized static CountingResult add_methodInstructionsOnly(
			CountingResult left, CountingResult right){
		//TODO replace cloning
		long[] resultOpcodeCounts = new long[MAX_OPCODE];
		TreeMap<String,Long>  resultMethodCallCounts = new TreeMap<String, Long>();

		String keyString;
		Long resultValue;
		String rightKey_String;
		Long rightValue;


		// add up all opcode counts
		for(int i = 0; i < left.opcodeCounts.length; i++) {
			resultOpcodeCounts[i] = left.opcodeCounts[i] + right.opcodeCounts[i];
		}

		// set all method call counts for which 'left' has keys:
		Iterator<String> iteratorMethods = left.methodCallCounts.keySet().iterator();
		while (iteratorMethods.hasNext()) {
			keyString = iteratorMethods.next();
			rightValue = right.methodCallCounts.get(keyString);
			resultValue = left.methodCallCounts.get(keyString);
			if(rightValue != null) {
				resultValue += rightValue;
			}
			resultMethodCallCounts.put(
					new String(keyString),
					new Long(resultValue));
		}
		// set all method call counts for which only 'right' has keys:
		Iterator<String> methodsKeysetRight = right.getMethodCallCounts().keySet().iterator();
		while (methodsKeysetRight.hasNext()) {
			rightKey_String = methodsKeysetRight.next();
			rightValue = right.methodCallCounts.get(rightKey_String);
			resultValue = resultMethodCallCounts.get(rightKey_String);
			if(resultValue == null) {
				resultMethodCallCounts.put(rightKey_String, rightValue);
			}
		}

		CountingResult cr;
		cr = new CountingResult(
				null, //requestID
				null, //ownID
				null, //callerID
				new String(""),
				new String(""),
				0,
				0L,
				0L,
				-1L,
				-1L,
				resultOpcodeCounts,
				resultMethodCallCounts,
				null, //resultNewArrayCountsArray,
				null, //resultNewArrayDimArray,
				null, //resultNewArrayTypeArray,
				null,
				null);
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
	 * A {@link UUID} that is linked to a the method calling the method that
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
	 * For SPECjvm2008, we are using the constants from compress.Harness:
	 *     public static final int DATA_TYPE_COMPRESSED=3;
     *     public static final int DATA_TYPE_MIXED=2;
     *     public static final int DATA_TYPE_UNCOMPRESSED=1;
	 *     public static final int DATA_TYPE_UNKNOWN=0;//
 	 */
	private int SPECjvm2008_compress_fileType=0;

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

	/**
	 * For example, for SPECjvm2008.Compress, this is the size of the input file (in bytes)
	 */
	private long inputCharacterisation=0;

	private boolean invariantMethodsAreInlined = false;

	/**
	 * This Map contains counts of method invocations, where the key is the
	 * method signature
	 */
	private SortedMap<String, Long> methodCallCounts = null;

	/**
	 * This Map says whether the counts of the methods have been computed and
	 * integrated into this CountingResults's method and instruction counts TODO
	 */
	@SuppressWarnings("unused")
	private SortedMap<String, Boolean> methodCountsExpansionStatus = null;

	/**
	 * The timestamp which marks the beginning of execution (i.e. run)
	 * of the method for which this CountingResult holds bytecode counts
	 */
	private long methodInvocationBeginning;

	/**
	 * The timestamp which was set immediately before this method called
	 * the CountingResultCollector. In other words, this is
	 * <b>approximately</b> the time when the method execution was finished.
	 */
	private long methodReportingTime;

	/**
	 * This Map contains the counts of elementary bytecode instructions
	 * TODO this is highly inefficient (even for sparse arrays!) due to boxing and unboxing --> go back to arrays!
	 */
	private long[] opcodeCounts;
	
	/**
	 * For example, for SPECjvm2008.Compress, this is the size of the
	 * buffer which holds the compressed data of the input file
	 */
	private long outputCharacterisation=0;

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
	 * Should be used by the next release
	 */
	private transient SortedMap<BytecodeSectionDescription,SortedMap<Integer,Long>> sectionInstCounts = null;

	/**
	 * Should be used by the next release
	 */
	private transient SortedMap<BytecodeSectionDescription,SortedMap<String,Long>> sectionMethCounts = null;

	/**
	 * Total count of all opcodes, except the four INVOKE* opcodes
	 */
	private Long totalCountExclInvokes;

	/**
	 * Total count of all opcodes, including the four INVOKE* opcodes
	 */
	private Long totalCountInclInvokes;

	public CountingResult(//TODO make sure the instructions are "full", even if some instruction counts are zero
			String qualifyingMethodName,
			long methodInvocationBeginning,
			long methodReportingTime,
			long[] opcodeCounts,
			SortedMap<String, Long> methodCallCounts) {
		this(//TODO make sure the instructions are "full", even if some instruction counts are zero
				null,null,null,null,
				qualifyingMethodName,
				-1,-1,-1,
				methodInvocationBeginning,
				methodReportingTime,
				
				opcodeCounts,
				methodCallCounts,
				null,null,null,null,null);
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
	 * @param fileType
	 * @param inputCharacterisation
	 * @param outputCharacterisation
	 * @param methodInvocationBeginning
	 * @param methodReportingTime
	 * @param opcodeCounts
	 * @param methodCallCounts
	 * @param arrayCreationCounts
	 * @param arrayCreationDimensions
	 * @param arrayCreationTypeInfo
	 * @param sectionInstCounts
	 * @param sectionMethCounts
	 */
	public CountingResult(//TODO make sure the instructions are "full", even if some instruction counts are zero
			UUID requestID,
			UUID ownID,
			UUID callerID,
			
			String ID,
			String qualifyingMethodName,
			
			int fileType,
			long inputCharacterisation,
			long outputCharacterisation,
			
			long methodInvocationBeginning,
			long methodReportingTime,
			
			long[] opcodeCounts,
			SortedMap<String, Long> methodCallCounts,
			
			long[] arrayCreationCounts,
			int[] arrayCreationDimensions,
			String[] arrayCreationTypeInfo,
			
			SortedMap<BytecodeSectionDescription,SortedMap<Integer,Long>> sectionInstCounts,
			SortedMap<BytecodeSectionDescription,SortedMap<String,Long>> sectionMethCounts) {
		this.setRequestID(requestID);
		this.setOwnID(ownID);
		this.setCallerID(callerID);
		this.setID(ID);
		
		this.arrayCreationCounts = arrayCreationCounts;
		this.arrayCreationDimensions = arrayCreationDimensions;
		this.arrayCreationTypeInfo = arrayCreationTypeInfo;
		
		this.indexOfRangeBlock = -1;
		this.inputCharacterisation = inputCharacterisation;
		this.methodCallCounts = methodCallCounts;
		this.methodInvocationBeginning = methodInvocationBeginning;
		this.methodReportingTime = methodReportingTime;
		this.outputCharacterisation = outputCharacterisation;
		assert(opcodeCounts.length==201);//TODO document, propagate...
		this.opcodeCounts = opcodeCounts;
		this.qualifyingMethodName = qualifyingMethodName; //should be a PRIVATE setter
		this.sectionInstCounts = sectionInstCounts;
		this.sectionMethCounts = sectionMethCounts;
		this.SPECjvm2008_compress_fileType = fileType;
		this.totalCountExclInvokes = 0L;
		this.totalCountInclInvokes = 0L;

		this.characterisations = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Object>(0);//this is eating up memory...
		this.characterisationTitles = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<String>(0);
		this.characterisationTypes = null; //now, methods modifying characterisations must check for non-nullness //used to be new ArrayList<Integer>(0);
//		try {
//			this.computeTotalOpcodeCounts();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/** Adds the counts of this {@link CountingResult} instance to
	 * the counting results
	 * of the {@link CountingResult} instance given as parameter
	 * @param toBeAdded {@link CountingResult} instance whose counts are to be added
	 * @deprecated because yet untested w.r.t. new fields and side effects
	 */
	@SuppressWarnings("dep-ann")
	public synchronized void add(CountingResult toBeAdded){
		CountingResult skeletonResult = add(this,toBeAdded);
		this.methodCallCounts = skeletonResult.getMethodCallCounts();
		this.opcodeCounts = skeletonResult.getOpcodeCounts();
		this.arrayCreationCounts = skeletonResult.getNewArrayCounts();
		this.arrayCreationDimensions = skeletonResult.getNewArrayDim();
		this.arrayCreationTypeInfo = skeletonResult.getNewArrayTypes();
		skeletonResult = null;
//		TODO add section counts ... and other non-copied fields
	}

	/** Adds the counts of this {@link CountingResult} instance to
	 * the counting results
	 * of the {@link CountingResult} instance given as parameter
	 * @param toBeAdded {@link CountingResult} instance whose counts are to be added
	 * @deprecated because yet untested w.r.t. new fields and side effects
	 */
	@SuppressWarnings("dep-ann")
	public synchronized void add_methodsInstructionsOnly(CountingResult toBeAdded){
		CountingResult skeletonResult = add_methodInstructionsOnly(this,toBeAdded);
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
	@SuppressWarnings("unchecked")
	public Object clone(){ //TODO fix/test/coverage-test this!
//		SortedMap<String,Long> clonedMap = 
		return new CountingResult(
				UUID.fromString(this.getRequestID().toString()),
				UUID.fromString(this.getOwnID().toString()),
				UUID.fromString(this.getCallerID().toString()),
				new String(this.ID),
				new String(this.qualifyingMethodName),
				this.SPECjvm2008_compress_fileType,
				this.inputCharacterisation,
				this.outputCharacterisation,
				this.methodInvocationBeginning,
				this.methodReportingTime,
				Arrays.copyOf(this.opcodeCounts, this.opcodeCounts.length),
				(TreeMap<String,Long>) ((TreeMap<String,Long>) this.methodCallCounts).clone(),
				null, //this.arrayCreationCounts.clone(),
				null, //this.arrayCreationDimensions.clone(),
				null, //this.arrayCreationTypeInfo.clone(),
				null, //TODO: this.sectionInstCounts,
				null //this.sectionMethCounts
				);
	}

	/**
	 * TODO
	 * @throws Exception
	 */
	private synchronized void computeTotalOpcodeCounts() throws Exception {
		long tentativeNewTotalCountInclInvokes = 0L;
		long tentativeNewTotalCountExclInvokes = 0L;
		long prevNewTotalCountInclInvokes = 0L;
		long prevNewTotalCountExclInvokes = 0L;
		for(int i = 0; i < MAX_OPCODE; i++){
			tentativeNewTotalCountInclInvokes =
				prevNewTotalCountInclInvokes
				+this.opcodeCounts[i];
			if(tentativeNewTotalCountInclInvokes<prevNewTotalCountInclInvokes){
				throw new Exception("Overflow detected while " +
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
					throw new Exception("Overflow detected while " +
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

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#getFileType()
	 */
	public int getFileType() {
		return SPECjvm2008_compress_fileType;
	}

//	/**
//	 * Constructs a new Result instance with the given values.
//	 * @param time Time of method execution as returned by System.nanoTime().
//	 * @param qualifyingMethodName Name of the evaluated method.
//	 * @param opcodeCounts HashMap containing counts for every opcode occurrence.
//	 * @param methodCallCounts HashMap containing count for every method that was called.
//	 * @param newArrayCounts The counts for the specific array construction.
//	 * @param newArrayDim The dimension information for the
//	 * specific array construction if more than one dimension exists.
//	 * @param newArrayType The descriptor string for array construction of non-
//	 * simple type or a string identifying the type else.
//	 * @deprecated
//	 */
//	public CountingResult(
//			long time,
//			String qualifyingMethodName,
//			HashMap<Integer, Long> opcodeCounts,
//			HashMap<String, Long> methodCallCounts,
//			long[] newArrayCounts,
//			int[] newArrayDim,
//			String[] newArrayType) {
//		this.methodInvocationBeginning = time;
//		this.qualifyingMethodName = qualifyingMethodName;
//		this.opcodeCounts = opcodeCounts;
//		this.methodCallCounts = methodCallCounts;
//		this.arrayCreationCounts = newArrayCounts;
//		this.arrayCreationDimensions = newArrayDim;
//		this.arrayCreationTypeInfo = newArrayType;
//	}


	/**
	 * @return the ID
	 */
	public String getID() {
		return ID;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#getInputCharacterisation()
	 */
	public long getInputCharacterisation() {
		return inputCharacterisation;
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

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#getOutputCharacterisation()
	 */
	public long getOutputCharacterisation() {
		return outputCharacterisation;
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
	 * @return sectionInstCounts
	 */
	public SortedMap<BytecodeSectionDescription, SortedMap<Integer, Long>> getSectionInstCounts() {
		return sectionInstCounts;
	}

	/**
	 * @return sectionMethCounts
	 */
	public SortedMap<BytecodeSectionDescription, SortedMap<String, Long>> getSectionMethCounts() {
		return sectionMethCounts;
	}

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.IFullCountingResult#getTotalCount(boolean)
	 */
	public synchronized Long getTotalCount(boolean includeInvokeOpcodes) {
		if(!totalCountsAlreadyComputed){
			try {
				this.computeTotalOpcodeCounts();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	 * Flag indicating whether called methods are inlined (already part of bytecode counts)
	 * @return TODO
	 */
	public boolean isInvariantMethodsAreInlined() {
		return invariantMethodsAreInlined;
	}

//	/* (non-Javadoc)
//	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#KK_getMethodCount(java.lang.String)
//	 */
//	public Long KK_getMethodCount(String methodName){
//		return getMethodCountByString(methodName);
//	}
//
//	/* (non-Javadoc)
//	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#KK_getOpcodeCount(int)
//	 */
//	public Long KK_getOpcodeCount(int opcode){
//		return getOpcodeCountByInteger(opcode);
//	}
//
//	/* (non-Javadoc)
//	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#KK_getOpcodeCount(java.lang.String)
//	 */
//	public Long KK_getOpcodeCount(String opcode){
//		return getOpcodeCountByString(opcode);
//	}

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
	 * @param fileType
	 */
	public void setFileType(int fileType) {
		this.SPECjvm2008_compress_fileType = fileType;
	}

	/**
	 * @param newID
	 */
	public void setID(String newID) {
		ID = newID;
	}

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ITestCountingResult#setInputCharacterisation(long)
	 */
	public void setInputCharacterisation(long inputCharacterisation) {
		this.inputCharacterisation = inputCharacterisation;
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

	/** (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ISimpleCountingResult#setOpcodeCount(int, java.lang.Long)
	 */
	public void setOpcodeCount(int opcode, Long count) {
		this.opcodeCounts[opcode] = count;
		this.totalCountsAlreadyComputed = false;
	}

	/* (non-Javadoc)
	 * @see de.uka.ipd.sdq.ByCounter.execution.ITestCountingResult#setOutputCharacterisation(long)
	 */
	public void setOutputCharacterisation(long outputCharacterisation) {
		this.outputCharacterisation = outputCharacterisation;
	}

	/**
	 * @param ownID the ownID to set
	 */
	public void setOwnID(UUID ownID) {
		this.ownID = ownID;
	}

	/**
	 * @param requestID the requestID to set
	 */
	public void setRequestID(UUID requestID) {
		this.requestID = requestID;
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
	public boolean shallowEquals(CountingResult vr){
		if(this.inputCharacterisation!=vr.inputCharacterisation){
			System.out.println("CountingResult.shallowEquals: == on inputCharacterisation: "+this.inputCharacterisation+" vs "+vr.inputCharacterisation);
			return false;
		}
		if(this.invariantMethodsAreInlined!=vr.invariantMethodsAreInlined){
			System.out.println("CountingResult.shallowEquals: == on invariantMethodsAreInlined: "+this.invariantMethodsAreInlined+" vs "+vr.invariantMethodsAreInlined);
			return false;
		}
		if(this.outputCharacterisation!=vr.outputCharacterisation){
			System.out.println("CountingResult.shallowEquals: == on outputCharacterisation: "+this.outputCharacterisation+" vs "+vr.outputCharacterisation);
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
		sb.append("      > Method name     : "+this.qualifyingMethodName+"\n");
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

	public int getSPECjvm2008_compress_fileType() {
		return this.SPECjvm2008_compress_fileType;
	}

	public long getForcedInlining_earliestStartOfInlinedMethod() {
		return this.forcedInlining_earliestStartOfInlinedMethod;
	}

	public SortedMap<String, Boolean> getMethodCountsExpansionStatus() {
		return this.methodCountsExpansionStatus;
	}

	public boolean isTotalCountsAlreadyComputed() {
		return this.totalCountsAlreadyComputed;
	}


}
