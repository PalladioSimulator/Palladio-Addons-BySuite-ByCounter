package de.uka.ipd.sdq.ByCounter.instrumentation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.execution.ProtocolCountStructure;
import de.uka.ipd.sdq.ByCounter.execution.ProtocolCountUpdateStructure;
import de.uka.ipd.sdq.ByCounter.utils.JavaType;
import de.uka.ipd.sdq.ByCounter.utils.JavaTypeEnum;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * <p>
 * Visitor for a method declaration. This class implements the method modifying 
 * instrumentation.
 * </p>
 * <p>
 * It creates initialisation code for local variables that hold counters,
 * it insert incrementing code for these counters. Finally, it inserts code 
 * that creates arrays and fills them with the values of the counter variables. 
 * These arrays are then used as parameters for the code that calls 
 * {@link CountingResultCollector} on method exit.
 * </p>
 * <p>
 * There are separate counter variables/arrays for 
 * <ul>
 *   <li>either opcode counts or basic block counts</li>
 *   <li>method invocation counts</li>
 *   <li>array construction counts.</li>
 * </ul>
 * </p>
 * <p>
 * Basic block and range blocks work not by counting opcodes but by incrementing
 * counters whenever the label a block starts with is visited. Results are then 
 * reported with a mark that allows for identifying the blocks.
 * </p>
 * 
 * @author Martin Krogmann
 * @author Michael Kuperberg
 * @since 0.1
 * @version 1.2
 */
public final class MethodCountMethodAdapter extends MethodAdapter implements Opcodes {	// evil implements instead of static import
	
	/**
	 * Statistical counter.
	 * This is a counter that is increased for every inserted counting statement.
	 */
	public int statNumberOfCountingStatementsAdded = 0;
	
	/**
	 * This is the magic string constant that is inserted (loaded and popped 
	 * off the stack again) at the beginning of 
	 * instrumented methods to mark them as instrumented.
	 */
	public static final String INSTRUMENTATION_MARKER = 
		"_____BYCOUNTER_INSTRUMENTED______";

	/**
	 * The fully qualified class name of {@link CountingResultCollector}, but 
	 * in bytecode form.
	 */
	private static final String CountingResultCollectorCanonicalNameDescriptor = 
		CountingResultCollector.class.getCanonicalName().replace('.', '/');

	private static final int MAX_OPCODE = CountingResult.MAX_OPCODE;

	/**
	 * all invoked methods and array information
	 */
	private AdditionalOpcodeInformation additionalOpcInfo; 
	
	private boolean arrayCountersInitialised = false;
	
	/**
	 * map from index:indexInAddOpcInfo to value:variable/register index
	 */
	private int[] arrayCreationCounters = null; 
	
	private HashSet<ICharacterisationHook> characterisationHooks;
	
	/**
	 * map from index:opcode to value:variable/register index
	 */
	private int[] instructionCounters = null;

	/**
	 * map from key:Label to value:BlockCounterData
	 */
	private Map<Label, BlockCounterData> basicBlockCounters = null;
	
	private boolean instructionCountersInitialised = false;
	
	private InstrumentationParameters instrumentationParameters;
	
	private Logger log;
	
	private LocalVariableManager lVarManager = null;
	
	/**
	 * map from key:methodsignature to value:variable/register index
	 */
	private HashMap<String,  Integer> methodCounters = null;			// 
	
	private boolean methodCountersInitialised = false;
	
	private int methodInvocationArrayVar;
	
	/**
	 * The fully qualifying name of the current method.
	 */
	private String qualifyingMethodName;

	/**
	 * variable register holding the nanoTime()
	 */
	private int timeVar;				// 

	/**
	 * @see #setIsAlreadyInstrumented(boolean)
	 */
	private boolean isAlreadyInstrumented = false;
	
	/**
	 * Index of the local variable that holds the requestID.
	 */
	private int requestIDLocalVarIndex;
	
	/**
	 * Index of the local variable that holds the requestID.
	 */
	private int ownIDLocalVarIndex;
	
	/**
	 * Index of the local variable that holds the requestID.
	 */
	private int callerIDLocalVarIndex;

	/**
	 * Parameter descriptor of this method in Java bytecode notation.
	 */
	private String parameterDesc;

	/**
	 * Access flags for this method.
	 */
	private int accessFlags;

	/**
	 * Name of the super class (bytecode notation).
	 */
	private String superNameBC;

	
	/**
	 * Class name (bytecode notation).	
	 */
	@SuppressWarnings("unused")
	private String classNameBC;

	/**
	 * The {@link MethodDescriptor} for the current class.
	 */
	private MethodDescriptor methodDescriptor;

	/**
	 * When true, this specific method uses basic blocks or range blocks and 
	 * {@link #basicBlockLabels} are defined.
	 */
	private boolean useBlockCounters;

	/**
	 * When true, this specific method uses range blocks.
	 */
	private boolean useRangeBlocks;

	/**
	 * The variable of the {@link ArrayList} created in 
	 * {@link #initialiseBlockExecutionOrderArrayList()}.
	 */
	private int blockExecutionOrderArrayListVar;
	
	/**
	 * The variable of the {@link ArrayList} created in 
	 * {@link #initialiseBlockExecutionOrderArrayList()} for ordering of 
	 * defined ranges.
	 */
	private int rangeBlockExecutionOrderArrayListVar;

	/**
	 * Intermediate instrumentation state.
	 */
	private InstrumentationState instrumentationState;
	
	/**
	 * Creates the method adapter.
	 * @param v Preceding visitor in the chain.
	 * @param access Access flags for this method.
	 * @param superName Name of the superclass.
	 * @param qualifyingMethodName Qualifying name of the method (used for reporting).
	 * @param desc Parameter descriptor in Java bytecode notation.
	 * @param instrumentationParameters User specified parameters.
	 * @param instrumentationState Intermediate instrumention state.
	 */
	public MethodCountMethodAdapter(
			MethodVisitor v, 
			int access,
			String superName,
			String className,
			String qualifyingMethodName, 
			String desc,
			InstrumentationParameters instrumentationParameters,
			InstrumentationState instrumentationState,
			MethodDescriptor method) {
		super(v);
		this.log = Logger.getLogger(this.getClass().getCanonicalName());
		this.instrumentationParameters = instrumentationParameters;
		this.instrumentationState = instrumentationState;
		// basicBlockCounters xor instrunctionCounters are initialised in visitCode()
		this.methodCounters 		= new HashMap<String, Integer>();
		this.arrayCreationCounters	= null;	// this is set in #setMethodInvocations
		this.characterisationHooks	= new HashSet<ICharacterisationHook>();
		this.classNameBC = className;
		this.superNameBC = superName;
		this.qualifyingMethodName	= qualifyingMethodName;
		this.accessFlags = access;
		this.parameterDesc = desc;
		this.methodDescriptor = method;
		this.lVarManager = new LocalVariableManager(instrumentationParameters.getUseHighRegistersForCounting());
		this.useBlockCounters = false;
		this.useRangeBlocks = false;
	}
	
	/**
	 * Count the construction of an array with the given parameters 
	 * where applicable. Use AdditionalOpcodeInformation.NO_INFORMATION*
	 * if a parameter is not used. If this method is called, 
	 * then is counted only in addition to countOpcode, which is called for any opcode.
	 * TODO assert that this method is not called on its own without countOpcode
	 * @param integer Integer information. For array of a primitive type 
	 * such as int[], long[], etc. this encodes the type while 's' contains 
	 * no information.
	 * For multidimensional arrays, this holds the dimension while 's' holds the 
	 * type.
	 * <p>
	 * For new object type arrays this is empty and 's' holds the type.
	 * </p>
	 * @param s String information. For primitive type arrays such as int[], long[],
	 * etc. this is empty. In all other cases this holds the type information.
	 */
	private void countArrayConstruction(int integer, String s) {
		if(this.useBlockCounters) {
			return;
		}
		if(this.additionalOpcInfo == null) {
			this.log.severe("The AdditionalOpcodeInfo was not set.");
		} else {
			// use the assigned variable index
			int id = this.additionalOpcInfo.getIndexOfAdditionInformation(integer, s);
			assert id != -1;
			int index = this.arrayCreationCounters[id];
			insertCounterIncrement(index);
		}
	}

	/**
	 * Count the call to the method described by the given parameters.
	 * The parameters may be taken directly from the visit instruction.
	 * @param owner Owner of the method, i.e. name of parent class. 
	 * @param name Method name.
	 * @param desc Method descriptor, i.e. parameter types and return type in asm/bytecode syntax.
	 */
	private void countMethodCall(String owner, String name, String desc) {
		if(this.useBlockCounters) {
			return;
		}
		if(this.additionalOpcInfo == null) {
			this.log.severe("The AdditionalOpcodeInfo was not set.");
		} else {
			if(this.methodCounters==null){
				this.log.severe("methodCounters is null");
			}
			// use the assigned variable index
			String signature = MethodDescriptor._constructMethodDescriptorFromASM(
						owner, name, desc).getCanonicalMethodName();
			if(signature==null){
				this.log.severe("signature is null");
			}
		
			int index = this.methodCounters.get(signature);
			insertCounterIncrement(index);
		}
	}

	/**
	 * Count the given opcode, i.e. increment its register.
	 * This will use a new register, if the opcode has not 
	 * appeared before.
	 * @param pOpcode Opcode to count.
	 */
	@SuppressWarnings("boxing")
	private void countOpcode(int pOpcode) {
		if(this.useBlockCounters) {
			return;
		}
		int index;	// index of the opcode counter register
		index = this.instructionCounters[pOpcode];
//		log.fine("Adding counter increment for counter "+index+" (opcode "+pOpcode+").");
		insertCounterIncrement(index);
	}
	
	/**
	 * Gets a new variable intended for use as a counter and respects the
	 * precision set in {@link InstrumentationParameters}.
	 * @return The index of the new variable.
	 */
	private int getNewCounterVar() {
		if(this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Long) {
			return this.lVarManager.getNewLongVar(this.mv);
		}
		return this.lVarManager.getNewIntVar(this.mv);
	}

	/**
	 * Initializes long locals that are intended to hold array counts. 
	 */
	private void initialiseArrayCounters() {
		if(!this.instructionCountersInitialised ||
				(!this.useBlockCounters && !this.methodCountersInitialised)){
			throw new RuntimeException(new IllegalStateException(
					"initialiseArrayCounts must be called after " +
					"initialiseInstructionCounters and before initialiseMethodCounters"));
		}
		this.arrayCountersInitialised = true;
		int additionalInformationSize = this.additionalOpcInfo.getIntInformation().length;
		int var;	// the index for the new variable
		// Initialize registers for all array constructions.
		// make sure the information lists are synchronized 
		assert additionalInformationSize
			== this.additionalOpcInfo.getStringInformation().size();
		for(int i = 0; 
				i < additionalInformationSize; 
				i++) {
			var = getNewCounterVar();
			// use index in list as id
			this.arrayCreationCounters[i] = var;
		}
	}
	
	private void initialiseBlockExecutionOrderArrayList() {
		// get the number for a local variable of the type ArrayList
		this.blockExecutionOrderArrayListVar = this.lVarManager.getNewVarFor(
				"ArrayList", mv, Type.getType(java.util.ArrayList.class), 1);

		String TYPE_ARRAYLIST = "java/util/ArrayList";
		mv.visitTypeInsn(NEW, TYPE_ARRAYLIST);
		mv.visitInsn(DUP);
		mv.visitMethodInsn(INVOKESPECIAL, TYPE_ARRAYLIST, "<init>", "()V");
		mv.visitVarInsn(ASTORE, blockExecutionOrderArrayListVar);
		
		if(this.useRangeBlocks) {
			this.rangeBlockExecutionOrderArrayListVar = this.lVarManager.getNewVarFor(
					"ArrayList", mv, Type.getType(java.util.ArrayList.class), 1);

			mv.visitTypeInsn(NEW, TYPE_ARRAYLIST);
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, TYPE_ARRAYLIST, "<init>", "()V");
			mv.visitVarInsn(ASTORE, rangeBlockExecutionOrderArrayListVar);
		}
	}

	/**
	 * Initializes integer locals that are intended to hold opcode counts. 
	 * If block execution order recording is enabled, the execution order 
	 * array list is initialised instead.
	 */
	private void initialiseInstructionCounters() {
		if(this.arrayCountersInitialised || this.methodCountersInitialised){
			throw new RuntimeException(new IllegalStateException(
					"initialiseInstructionCounts must be called before " +
					"initialiseMethodCounters and initialiseArrayCounters"));
		}
		this.instructionCountersInitialised = true;
		
		if(this.useBlockCounters) {
			Label[] basicBlockLabels = this.instrumentationState.getBasicBlockLabels();
			for(int i = 0; i < basicBlockLabels.length; i++) {
				BlockCounterData d = new BlockCounterData();
				d.blockIndex = i;
				if(!this.instrumentationParameters.getRecordBlockExecutionOrder()) {
					d.variableIndex = getNewCounterVar();	// the index for the new variable
				}
				this.basicBlockCounters.put(basicBlockLabels[i], d);
			}
		} else {
			// Initialize registers for all possible opcodes.
			// use the fixed set of registers near max_locals
			for(int i = 0; i < MAX_OPCODE; i++) {
				//save the reference from opcode "i" to its counter in local variable var
				this.instructionCounters[i] = getNewCounterVar();	// the index for the new variable
			}
		}
	}
	
	/**
	 * Initializes integer locals that are intended to hold method counts. 
	 */
	private void initialiseMethodCounters() {
		if(!this.instructionCountersInitialised || this.arrayCountersInitialised){
			throw new RuntimeException(new IllegalStateException(
					"initialiseMethodCounts must be called after " +
					"initialiseInstructionCounters and before initialiseArrayCounters"));
		}
		this.methodCountersInitialised = true;
		int var;	// the index for the new variable
		// Initialize registers for all method invocations.
		int nrOfMethods = this.additionalOpcInfo.getMethodInvokations().size();
		this.log.fine(nrOfMethods+" methods to allocate counters for");
		String method;
		for(int i = 0; 
				i < nrOfMethods;
				i++) {
			var = getNewCounterVar();
			method = this.additionalOpcInfo.getMethodInvokations().get(i);
			this.methodCounters.put(method, var);
		}
	}

	/**
	 * Insert code that calls the {@link ArrayList#add(Object)} method on the 
	 * specified array list to add the given integer as a constant.
	 * @param arrayListVar Variable index of the {@link ArrayList}.
	 * @param integer {@link Integer} value to add to the array list.
	 */
	private void insertAddIntegerToArrayList(int arrayListVar, Integer integer) {
		mv.visitVarInsn(ALOAD, arrayListVar);
		mv.visitLdcInsn(integer);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "add", "(Ljava/lang/Object;)Z");
		mv.visitInsn(POP); // pop the return value of add
	}

	/**
	 * Inserts bytecode that creates a new array and fills it with the
	 * contents of the locals for which the indices are given.
	 * @param indicesOfArrayCounters The indices of the counter registers.
	 * @return The variable index for the new array.
	 */
	private int insertAndFillCounterArrayFromRegisters(
			int[] indicesOfArrayCounters) {
		int index;	// the index to return
		int numElements = indicesOfArrayCounters.length;
		insertIntegerPushInsn(numElements); //load array size - has NOTHING to do with the counter type		

		// choose the instructions by precision:
		if(this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Long) {
			this.mv.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_LONG);
			index = this.lVarManager.getNewLongArrayVar(this.mv);
			this.mv.visitVarInsn(Opcodes.ASTORE, index);	// store the array
			
			for(int i = 0; i < numElements; i++) {
				// store the value in the array
				this.mv.visitVarInsn(Opcodes.ALOAD, index);
				insertIntegerPushInsn(i); //load index - NOT the counter value...
				this.mv.visitVarInsn(Opcodes.LLOAD, indicesOfArrayCounters[i]);	// load counter value from local variable
				this.mv.visitInsn(Opcodes.LASTORE);
			}
		} else {
			this.mv.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_INT);
			index = this.lVarManager.getNewIntArrayVar(mv);
			mv.visitVarInsn(Opcodes.ASTORE, index);	// store the array

			for(int i = 0; i < numElements; i++) {
				// store the value in the array
				mv.visitVarInsn(Opcodes.ALOAD, index);
				insertIntegerPushInsn(i); //load index - NOT the counter value...
				mv.visitVarInsn(Opcodes.ILOAD, indicesOfArrayCounters[i]);	// load counter value from local variable
				mv.visitInsn(Opcodes.IASTORE);
			}
		}
		return index;
	}

	/**
	 * Inserts bytecode that creates a new int array and fills it with the
	 * contents of the given integerList.
	 * @param integerList The integer contents.
	 * @return The variable index for the new int array.
	 */
	private int insertAndFillIntArray(int[] integerList) {
		int numElements = integerList.length;
		insertIntegerPushInsn(numElements);
		mv.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_INT);
		
		int index = this.lVarManager.getNewIntArrayVar(mv);
		mv.visitVarInsn(Opcodes.ASTORE, index);	// store the array
		
		for(int i = 0; i < numElements; i++) {
			// store the value in the array
			mv.visitVarInsn(Opcodes.ALOAD, index);
			insertIntegerPushInsn(i);
			mv.visitLdcInsn(integerList[i]);
			mv.visitInsn(Opcodes.IASTORE);
		}
		return index;
	}

	/**
	 * Inserts bytecode that creates a new string array and fills it with the
	 * contents of list.
	 * @param list The strings to store in the array.
	 * @return The variable index for the new string array.
	 */
	private int insertAndFillNewStringArray(List<String> list) {
		int numElements = list.size();
		insertIntegerPushInsn(numElements);
		mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/String");
		
		int index = this.lVarManager.getNewStringArrayVar(mv);
		mv.visitVarInsn(Opcodes.ASTORE, index);	// store the array
		
		for(int i = 0; i < numElements; i++) {
			// store the string in the array
			mv.visitVarInsn(Opcodes.ALOAD, index);
			insertIntegerPushInsn(i);
			mv.visitLdcInsn(list.get(i));
			mv.visitInsn(Opcodes.AASTORE);
		}
		return index;
	}

	/**
	 * Increments the counter with the given index.
	 * Chooses IINC or LADD depending on counter precision.
	 * @param index The index of the counter to increment.
	 */
	private void insertCounterIncrement(int index) {
		// choose the counter incrementation instructions by precision:
		if(this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Long) {
			this.mv.visitVarInsn(Opcodes.LLOAD, index);
			this.mv.visitInsn(LCONST_1);
			this.mv.visitInsn(LADD);
			this.mv.visitVarInsn(LSTORE, index);
		}else{
			this.mv.visitIincInsn(index, 1);	// increment the register
		}
		// increase the statistics counter
		this.statNumberOfCountingStatementsAdded++;
	}

	/**
	 * Insert a call such as BIPUSH or SIPUSH. The latter is used if 
	 * the given integer is too big to fit into a byte.
	 * @param i Integer to push
	 */
	private void insertIntegerPushInsn(int i) {
		if(i <= Byte.MAX_VALUE) {
			mv.visitIntInsn(Opcodes.BIPUSH, i);
		} else {
			mv.visitIntInsn(Opcodes.SIPUSH, i);
		}
	}
	
	/**
	 * Calls the result collector after the method has been completed.
	 * @see #insertResultCollectorCall(String)
	 */
	private void insertResultCollectorCompleteCall() {
		this.insertResultCollectorCall(ProtocolCountStructure.class.getCanonicalName().replace('.', '/'));
	}
	
	/**
	 * Calls the result collector after a part of the method has been completed.
	 * @see #insertResultCollectorCall(String)
	 */
	private void insertResultCollectorUpdateCall() {
		this.insertResultCollectorCall(ProtocolCountUpdateStructure.class.getCanonicalName().replace('.', '/'));
	}

	/**
	 * This is being called at the end of the method to report the resulting counts.
	 * @param protocolCountStructClassName Canonical class name of the result structure.
	 */
	@SuppressWarnings("boxing")
	private void insertResultCollectorCall(final String protocolCountStructClassName) {
//		boolean skip = true;
//		if(skip) {
//			return;
//		}
		if(!instrumentationParameters.getUseResultCollector()
				&& !instrumentationParameters.getUseResultLogWriter()) {
			log.info("Not using ResultCollector or result logs since both are disabled.");
			return;
		}
		
		int opcodeOrBasicBlockListVar = -1;
		int methodListVar = -1;
		if(this.useBlockCounters) {
			if(!this.instrumentationParameters.getRecordBlockExecutionOrder()) {
				opcodeOrBasicBlockListVar = insertResultCollectorCall_createBasicBlockCountsArray();
			}
		} else {
			opcodeOrBasicBlockListVar = insertResultCollectorCall_createOpcodeCountsArray();
			methodListVar = insertResultCollectorCall_createMethodCountsArrays();
		}
		
		int newArrayListVar = -1;
		int newArrayTypeOrDimListVar = -1;
		int newArrayDescVar = -1;
		if(instrumentationParameters.getUseArrayParameterRecording()) {
			// store the newarray counts in an array
			newArrayListVar = insertAndFillCounterArrayFromRegisters(this.arrayCreationCounters);
			newArrayTypeOrDimListVar = insertAndFillIntArray(additionalOpcInfo.getIntInformation());
			newArrayDescVar = insertAndFillNewStringArray(additionalOpcInfo.getStringInformation());
			log.fine("Array parameter recording ON: "+arrayCreationCounters.length+
					" array counters");
		}
		

		String qualifyingMethodNameAndDesc = 
			this.qualifyingMethodName + this.parameterDesc;
		log.fine("Inserting a call to protocol* method, " +
				"being in the following state: "+
				"qualifyingMethodName: "+qualifyingMethodNameAndDesc+", "+
				"instrumentationParameters: "+this.instrumentationParameters+", ");
		boolean inlineImmediately = false;
		boolean isInvariant = false;
		UUID uuid = null;
		inlineImmediately = this.methodDescriptor.isInlineImmediately();
		isInvariant = this.methodDescriptor.isInvariant();
		uuid = this.methodDescriptor.getContext();
		log.fine("Found that the currently inserted " +
						"protocol* method " +
						"should be inlined: "+inlineImmediately+
						" (UUID: "+uuid+")"+
						" and is invariant: "+isInvariant);
		log.fine("UUID passing not implemented yet");
		log.fine("isInvariant ignored by instrumentation now...");
		String protocolCountSignature = CountingResultCollector.SIGNATURE_protocolCount;
		String protocolStructConstructorSignature = null;
		String protocolCountMethodName = "protocolCount";
		String directWritingToLogSignature = MethodCountClassAdapter.DIRECT_LOG_WRITE_SIGNATURE;
		// Choose the proper protocolCountMethod depending on the precision
		if(this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Integer) {
			protocolStructConstructorSignature = ProtocolCountStructure.SIGNATURE_CONSTRUCTOR_INT;
		} else if(this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Long) {
			protocolStructConstructorSignature = ProtocolCountStructure.SIGNATURE_CONSTRUCTOR_LONG;
		}
		
		if(instrumentationParameters.getUseResultCollector()){
			//use CountingResultCollector
			this.mv.visitMethodInsn(Opcodes.INVOKESTATIC, 
					CountingResultCollectorCanonicalNameDescriptor, 
					"getInstance", 
					"()L"+CountingResultCollectorCanonicalNameDescriptor + ";");
		} else {
			log.fine("Instrumenting "+qualifyingMethodNameAndDesc+
					" without result collector: skipping getting CountingResultCollector instance");
		}

		// create result object
		mv.visitTypeInsn(Opcodes.NEW, protocolCountStructClassName);
		mv.visitInsn(Opcodes.DUP);
		
		this.mv.visitVarInsn(Opcodes.LLOAD, this.timeVar); //converted to long --> taking two bytes!
		this.mv.visitLdcInsn(qualifyingMethodNameAndDesc);
		
		if(this.useBlockCounters &&
				this.instrumentationParameters.getRecordBlockExecutionOrder()) {
			// no counts when recording the execution order: use null
			this.mv.visitInsn(Opcodes.ACONST_NULL);
			this.mv.visitInsn(Opcodes.ACONST_NULL);
			this.mv.visitInsn(Opcodes.ACONST_NULL);
		} else {
			this.mv.visitVarInsn(Opcodes.ALOAD, opcodeOrBasicBlockListVar);
			if(this.useBlockCounters) {
				this.mv.visitInsn(Opcodes.ACONST_NULL);
				this.mv.visitInsn(Opcodes.ACONST_NULL);
			} else {
				this.mv.visitVarInsn(Opcodes.ALOAD, methodListVar);
				this.mv.visitVarInsn(Opcodes.ALOAD, methodInvocationArrayVar);
			}
		}

		if(!(inlineImmediately 
				&& this.instrumentationParameters.getCounterPrecision() == InstrumentationCounterPrecision.Long)) {
			if(instrumentationParameters.getUseArrayParameterRecording()) {
				this.mv.visitVarInsn(Opcodes.ALOAD, newArrayListVar);
				this.mv.visitVarInsn(Opcodes.ALOAD, newArrayTypeOrDimListVar);
				this.mv.visitVarInsn(Opcodes.ALOAD, newArrayDescVar);
			} else {
				this.mv.visitInsn(Opcodes.ACONST_NULL);
				this.mv.visitInsn(Opcodes.ACONST_NULL);
				this.mv.visitInsn(Opcodes.ACONST_NULL);
			}
		}
		
		if(this.instrumentationParameters.getTraceAndIdentifyRequests() 
				&& !this.methodDescriptor.isConstructor()) {
			// load the request UUID local variable
			this.mv.visitVarInsn(ALOAD, requestIDLocalVarIndex);
			// load the own UUID and the caller UUID local variable
			this.mv.visitVarInsn(ALOAD, ownIDLocalVarIndex);
//			this.mv.visitInsn(Opcodes.ACONST_NULL);
//			this.mv.visitInsn(Opcodes.ACONST_NULL);
			this.mv.visitVarInsn(ALOAD, callerIDLocalVarIndex);
		} else {
			this.mv.visitInsn(Opcodes.ACONST_NULL);
			this.mv.visitInsn(Opcodes.ACONST_NULL);
			this.mv.visitInsn(Opcodes.ACONST_NULL);
		}
		
		if(inlineImmediately) {
			this.mv.visitInsn(Opcodes.ICONST_1); // inline == true
		} else {
			this.mv.visitInsn(Opcodes.ICONST_0); // inline == false
		}
		
		// specify block counting mode
		if(useBlockCounters) {
			if(useRangeBlocks) {
				this.mv.visitIntInsn(Opcodes.BIPUSH, BlockCountingMode.RangeBlocks.ordinal());
			} else {
				this.mv.visitIntInsn(Opcodes.BIPUSH, BlockCountingMode.BasicBlocks.ordinal());
			}
		} else {
			this.mv.visitIntInsn(Opcodes.BIPUSH, BlockCountingMode.NoBlocks.ordinal());
		}
		
		// construct the new result object
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, protocolCountStructClassName, "<init>", protocolStructConstructorSignature);

		if(this.useBlockCounters && this.instrumentationParameters.getRecordBlockExecutionOrder()) {
			// set value of order arraylist
			mv.visitInsn(Opcodes.DUP);
			mv.visitVarInsn(ALOAD, this.blockExecutionOrderArrayListVar);
			mv.visitFieldInsn(PUTFIELD, protocolCountStructClassName, 
					"blockExecutionSequence", "Ljava/util/ArrayList;");
			
			if(this.useRangeBlocks) {
				// set value of range order arraylist
				mv.visitInsn(Opcodes.DUP);
				mv.visitVarInsn(ALOAD, this.rangeBlockExecutionOrderArrayListVar);
				mv.visitFieldInsn(PUTFIELD, protocolCountStructClassName, 
						"rangeBlockExecutionSequence", "Ljava/util/ArrayList;");
			}
		}
		

		if(instrumentationParameters.getUseResultLogWriter()) {
			if(instrumentationParameters.getUseResultCollector()) {
				// we need to dup the result object for the result collector
				this.mv.visitInsn(Opcodes.DUP);
			}
			
			String classname = qualifyingMethodName.substring
					(0, qualifyingMethodName.lastIndexOf('.'))
					.replace('.', '/');
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, 
					classname, 
					"___directWritingToLog___", 
					directWritingToLogSignature);
		}
		if(instrumentationParameters.getUseResultCollector()) {
			this.mv.visitMethodInsn(//note that the stack contents are taken as input parameters! (see method signature in CountingResultCollector)
					Opcodes.INVOKEVIRTUAL, 
					CountingResultCollectorCanonicalNameDescriptor, 
					protocolCountMethodName, 
					protocolCountSignature
					);
				
		}
	}

	private int insertResultCollectorCall_createMethodCountsArrays() {
		// set up an index array for the method counters
		int[] indicesOfMethodCounters = new int[this.methodCounters.size()];
		for(int i = 0; i < indicesOfMethodCounters.length; i++) {
			indicesOfMethodCounters[i] = this.methodCounters.get(this.additionalOpcInfo.getMethodInvokations().get(i));
		}
		// store the method counts
		int methodListVar = insertAndFillCounterArrayFromRegisters(indicesOfMethodCounters);
		this.log.fine("variable "+methodListVar+" holds array of longs that hold "+
				indicesOfMethodCounters.length+" method counts ");
		
		// Store the method signatures.
		this.methodInvocationArrayVar = insertAndFillNewStringArray(this.additionalOpcInfo.getMethodInvokations());
		this.log.fine("variable "+this.methodInvocationArrayVar+" holds array of string that hold "+
				this.additionalOpcInfo.getMethodInvokations().size()+" method names");
		return methodListVar;
	}

	private int insertResultCollectorCall_createOpcodeCountsArray() {
		// store the opcode counts
		int opcodeListVar = insertAndFillCounterArrayFromRegisters(this.instructionCounters);
		return opcodeListVar;
	}

	private int insertResultCollectorCall_createBasicBlockCountsArray() {
		// set up an index array that holds the variable indices for the basic block counters
		int[] indicesOfBasicBlockCounters = new int[this.basicBlockCounters.size()];
		Label[] basicBlockLabels = this.instrumentationState.getBasicBlockLabels();
		for(int i = 0; i < basicBlockLabels.length; i++) {
			indicesOfBasicBlockCounters[i] = this.basicBlockCounters.get(basicBlockLabels[i]).variableIndex;
		}
		// store the basic block counts
		int basicBlockListVar = insertAndFillCounterArrayFromRegisters(indicesOfBasicBlockCounters);
		this.log.fine("variable "+basicBlockListVar+" holds array of longs that hold "+
				indicesOfBasicBlockCounters.length+" opcode counts ");
		return basicBlockListVar;
	}

	/**
	 * Decides whether the given opcode is a return statement or a throw
	 * which both terminate the method.
	 * @param opcode Opcode to analyze.
	 * @return True if opcode is a returnstatement/throwstatement, false otherwise.
	 */
	private boolean isReturnStatement(int opcode) {
		switch(opcode) {
			case Opcodes.ARETURN:
			case Opcodes.DRETURN:
			case Opcodes.FRETURN:
			case Opcodes.IRETURN:
			case Opcodes.LRETURN:
			case Opcodes.RETURN:
			case Opcodes.ATHROW:	// exceptions will also terminate the method TODO does this conflict with resultCollector into a log file?
				return true;
			default:
				return false;
		}
	}


	/**
	 * Record the parameters for methodcalls.
	 * @param methodCountMethodAdapter
	 * @param instrumentationParams
	 * @param opcode
	 * @param owner
	 * @param name
	 * @param desc
	 */
	private void recordParameters(
			MethodCountMethodAdapter methodCountMethodAdapter,
			InstrumentationParameters instrumentationParams, 
			int opcode,
			String owner, String name, String desc) {
		// Loop through all hooks and gather the characterisations.
		for(ICharacterisationHook hook : characterisationHooks) {
			hook.methodCallHook(methodCountMethodAdapter, 
					instrumentationParameters, opcode, owner, name, desc);
		}
	}
	
	/**
	 * Register a {@link ICharacterisationHook}.
	 * @see ICharacterisationHook
	 * @param hook
	 */
	public void registerCharacterisationHook(ICharacterisationHook hook) {
		this.characterisationHooks.add(hook);
		log.info("Registered new CharacterisationHook " + hook + " ...");
	}

	/*
	 * Insert counter into all kind of instructions.
	 */

	/**
	 * Sets the LocalVariableSorter that is used to generate new locals.
	 * This must be called before this Adapter is evaluated.
	 * @param pLvars LocalVariableSorter
	 */
	public void setLVS(LocalVariablesSorter pLvars) {
		this.lVarManager.setLVS(pLvars);
	}

	/**
	 * Sets the MethodInvocationsFinder that is used to determine registers
	 * for method invocation counts.
	 * This must be called before this Adapter is evaluated.//TODO is this ensured/asserted/checken?
	 * @param additionalOpcInfo Finder instance.
	 */
	public void setMethodInvocations(AdditionalOpcodeInformation additionalOpcInfo) {
		this.additionalOpcInfo = additionalOpcInfo;
		this.arrayCreationCounters = new int[additionalOpcInfo.getIntInformation().length];
	}


	/**
	 * This is being called at the beginning of the method.
	 */
	@SuppressWarnings({ "unqualified-field-access", "boxing" })
	@Override
	public void visitCode() {
		super.visitCode();
		
		if(this.isAlreadyInstrumented) {
			this.instrumentationParameters.setInstrumentationScopeOverrideMethodLevel(
					InstrumentationScopeModeEnum.InstrumentNothing);
		}
		
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel()
				== InstrumentationScopeModeEnum.InstrumentNothing) {
			return;
		}
		
		// at this point, setBasicBlockLabels has been called, if it is called
		// therefore we know whether or not to use blocks
		Label[] basicBlockLabels = this.instrumentationState.getBasicBlockLabels();
		if(basicBlockLabels == null || basicBlockLabels.length == 0) {
			// only use blocks if according labels have been specified
			this.useBlockCounters = false;
		} else if(this.instrumentationParameters.getUseBasicBlocks()) {
			this.useBlockCounters = true;
			if(this.methodDescriptor.getCodeAreasToInstrument() != null
					&& this.methodDescriptor.getCodeAreasToInstrument().length != 0) {
				this.useRangeBlocks = true;
			}
			if(this.instrumentationParameters.getUseArrayParameterRecording()) {
				throw new RuntimeException("Array parameter recording is not currently supported in block counting modes.");
			}
		}
		
		// initialise counter maps here, because we now know whether basic/range 
		// blocks need to be used
		if(this.useBlockCounters) {
			this.basicBlockCounters = new HashMap<Label, BlockCounterData>();
		} else {
			this.instructionCounters = new int[MAX_OPCODE];
		}

		if(this.instrumentationParameters.getTraceAndIdentifyRequests()) {
			this.requestIDLocalVarIndex = calculateRequestIDLocalVarIndex();
			// if we use continuous register indices, we need to make the local 
			// variable counter aware of the request UUID 
			if(!this.instrumentationParameters.getUseHighRegistersForCounting()) {
				this.requestIDLocalVarIndex = lVarManager.getNewVarFor("UUID", mv, Type.getType(UUID.class), 1);
				this.callerIDLocalVarIndex = lVarManager.getNewVarFor("UUID", mv, Type.getType(UUID.class), 1);
			} else {
				lVarManager.reserveLocalVar(Type.getType(UUID.class));// requestID
				lVarManager.reserveLocalVar(Type.getType(UUID.class));// callerID
				this.callerIDLocalVarIndex = this.requestIDLocalVarIndex + 1;
			}
		}
		
		log.fine("Instrumenting " + this.qualifyingMethodName + "...");
		
		// insert magic string constant, load it and pop it from the stack again
		mv.visitLdcInsn(INSTRUMENTATION_MARKER);
		mv.visitInsn(POP);

		
		if(this.instrumentationParameters.getTraceAndIdentifyRequests()) {
			this.ownIDLocalVarIndex = lVarManager.getNewVarFor("UUID", mv, Type.getType(UUID.class), 1);
			
			// create the own UUID
			mv.visitMethodInsn(INVOKESTATIC, "java/util/UUID", "randomUUID", "()Ljava/util/UUID;");
			mv.visitVarInsn(ASTORE, ownIDLocalVarIndex);
		}		

		
		timeVar = lVarManager.getNewLongVar(mv);

		// save the time
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
		mv.visitVarInsn(Opcodes.LSTORE, timeVar);
		

		
		// initialise counters or block execution list
		initialiseInstructionCounters();
		
		if(this.useBlockCounters 
				&& this.instrumentationParameters.getRecordBlockExecutionOrder()) {
			initialiseBlockExecutionOrderArrayList();
		} else if(!this.useBlockCounters) {
			// blocks already include method calls
			initialiseMethodCounters();
		}
		if(this.instrumentationParameters.getUseArrayParameterRecording()) {
			initialiseArrayCounters();
		}
	}
	
	@Override
	public void visitEnd() {
		super.visitEnd();
		log.info("Added " + 
				this.statNumberOfCountingStatementsAdded
				+ " counter statements to the method '" 
				+ this.qualifyingMethodName + "'.");
	}

	/**
	 * Calculates the index of the local variable that holds the requestID that 
	 * was passed to this method.
	 */
	private int calculateRequestIDLocalVarIndex() {
		// calculate the index of the requestID local variable
		JavaType[] types = MethodDescriptor.getParametersTypesFromDesc(
				this.parameterDesc);
		int index;
		if(((this.accessFlags & Opcodes.ACC_STATIC) > 0)) {
			index = 0;
		} else {
			index = 1;	// index starts at 1 because this=0
		}
		
		// find the index of the last parameter, ie the UUID
		for(int i = 0; i < types.length; i++) {
			JavaTypeEnum t = types[i].getType();
			if(t == JavaTypeEnum.Double || t == JavaTypeEnum.Long) {
				index += 2;
			} else {
				index += 1;
			}
		}
		return index;
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
		}
		this.mv.visitFieldInsn(opcode, owner, name, desc);
	}

	/*
	 * Insert counter into all kind of instructions.
	 */

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitIincInsn(int, int)
	 */
	@Override
	public void visitIincInsn(int var, int increment) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(Opcodes.IINC);
		}
		mv.visitIincInsn(var, increment);
	}

	/**
	 * Step into every call to insert counters.
	 */
	@Override
	public void visitInsn(int opcode) {

		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
			if(isReturnStatement(opcode)) {
				// call the hooks
				for(ICharacterisationHook hook : characterisationHooks) {//TODO costly collection inquiry -> wrap in a "if" query on a flag
					hook.methodReturnHook(this, instrumentationParameters);
				}
				// method ends, so report the results
				insertResultCollectorCompleteCall();
			}
		}
		// visit statement
		mv.visitInsn(opcode);
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
			// Check whether this is a NEWARRAY instruction.
			if(instrumentationParameters.getUseArrayParameterRecording() 
					&& opcode == Opcodes.NEWARRAY) {
				this.countArrayConstruction(
						operand, //(primitive) array type 
						AdditionalOpcodeInformation.NO_INFORMATION_STRING);
			}
		}
		mv.visitIntInsn(opcode, operand);
	}

	@Override
	public void visitJumpInsn(int opcode, Label label) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
		}
		mv.visitJumpInsn(opcode, label);
	}

	/* (non-Javadoc)
	 * @see org.objectweb.asm.MethodAdapter#visitLabel(org.objectweb.asm.Label)
	 */
	@Override
	public void visitLabel(Label label) {
		super.visitLabel(label);

		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			if(this.useBlockCounters) {
				BlockCounterData blockData = this.basicBlockCounters.get(label);
				if(blockData != null) {
					if(this.instrumentationParameters.getRecordBlockExecutionOrder()) {
						this.insertAddIntegerToArrayList(
								this.blockExecutionOrderArrayListVar, 
								new Integer(blockData.blockIndex));
					} else {
						// a new basic block or range block starts
						this.insertCounterIncrement(blockData.variableIndex);
					}	
				}
			}
			if(this.useRangeBlocks 
					&& this.instrumentationParameters.getRecordBlockExecutionOrder()) {
				Integer rngeBlockIndex = this.instrumentationState.getRangeBlockContainsLabels().get(label);
				if(rngeBlockIndex != null) {
					// label is part of a range block
					this.insertAddIntegerToArrayList(
							this.rangeBlockExecutionOrderArrayListVar, 
							rngeBlockIndex);
					if(this.instrumentationParameters.getProvideOnlineSectionExecutionUpdates()) {
						this.insertResultCollectorUpdateCall();
					}
				}
			}
		}
	}

	@Override
	public void visitLdcInsn(Object constant) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(LDC);
		}
		mv.visitLdcInsn(constant);
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(LOOKUPSWITCH);
		}
		mv.visitLookupSwitchInsn(dflt, keys, labels);
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				== InstrumentationScopeModeEnum.InstrumentNothing) {
			// early out
			mv.visitMethodInsn(opcode, owner, name, desc);
			return;
		}
		countOpcode(opcode); //TODO document this double-counting externally
		countMethodCall(owner, name, desc);
		recordParameters(this, instrumentationParameters, 
				opcode, owner, name, desc);
		
		if(this.instrumentationParameters.getTraceAndIdentifyRequests()
				&& !name.equalsIgnoreCase("<init>")	// ignore constructor calls 
				&& !name.equalsIgnoreCase(this.superNameBC)) {	// same for super calls
			// here we need to replace the call to the original method for all instrumented methods
			int methodIndex = 
				MethodDescriptor.findMethodInList(
						this.instrumentationState.getMethodsToInstrumentCalculated(),
						owner.replace('/', '.'),
						name,
						desc);
			
			if(methodIndex >= 0) {
				// the method call is a call to an instrumented method
				// load the request UUID local variable
				mv.visitVarInsn(ALOAD, requestIDLocalVarIndex);
				mv.visitVarInsn(ALOAD, ownIDLocalVarIndex);
//				this.mv.visitInsn(Opcodes.ACONST_NULL);
				// call the new version of the method
				mv.visitMethodInsn(opcode, owner, 
						MethodCountClassAdapter.generateInstrumentedMethodName(name), 
						MethodCountClassAdapter.getDescWithRequestID(desc));
			} else {
				mv.visitMethodInsn(opcode, owner, name, desc);
			}
		} else {
			mv.visitMethodInsn(opcode, owner, name, desc);
		}
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {		
			countOpcode(Opcodes.MULTIANEWARRAY);
			if(instrumentationParameters.getUseArrayParameterRecording()) {
				this.countArrayConstruction(
						dims, 	//dimensions 
						desc);	//type (?)
			}
		}
		mv.visitMultiANewArrayInsn(desc, dims);
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(TABLESWITCH);
		}
		mv.visitTableSwitchInsn(min, max, dflt, labels);
	}

	@Override
	public void visitTypeInsn(int opcode, String desc) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
			// Check whether this is a ANEWARRAY instruction.
			if(instrumentationParameters.getUseArrayParameterRecording() 
					&& opcode == Opcodes.ANEWARRAY) {
				this.countArrayConstruction(
						AdditionalOpcodeInformation.NO_INFORMATION_INT, //dimension of the array is passed separately, not on the stack TODO: how?
						desc);
			}
		}
		mv.visitTypeInsn(opcode, desc);
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		if(this.instrumentationParameters.getInstrumentationScopeOverrideMethodLevel() 
				!= InstrumentationScopeModeEnum.InstrumentNothing) {
			countOpcode(opcode);
		}
		mv.visitVarInsn(opcode, var);
	}

	/**
	 * 
	 * @param isInstrumented When true, this method will not be instrumented 
	 * again.
	 * @param signature 
	 * @param name 
	 */
	public void setIsAlreadyInstrumented(boolean isInstrumented, String name, String signature) {
		if(isInstrumented) {
			this.isAlreadyInstrumented = isInstrumented; //simply means that visitCode won't instrument - does not impact the instrumentation of other method nodes
			throw new AlreadyInstrumentedException(name + "("+signature+") already instrumented!");
		}
		
	}
}
