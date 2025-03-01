/**
 * 
 */
package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.core.Position;
import de.fzi.gast.core.Root;
import de.fzi.gast.functions.Method;
import de.fzi.gast.statements.Statement;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.util.InputSwitch;

/**Converter for {@link EntityToInstrument} to ByCounter terms.
 * Maps are automatically updated with links between the areas.
 * @author groenda
 */
public class EntityToInstrumentToByCounterSwitch extends InputSwitch<Boolean> {
	/** Logger for this class. */
	private static final Logger logger = Logger.getLogger(EntityToInstrumentToByCounterSwitch.class.getCanonicalName());
	
	/** List of methods to instrument. */
	private final List<de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument> entitiesToInstrument;
	/** Mapping of the fully qualified method name to a {@link InstrumentedMethod}. */
	private final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap;
	/** Provides the LineNumberRanges corresponding to a given statement. */
	private static final StatementToLineNumberRangeSwitch statementToLineNumberRangeSwitch = new StatementToLineNumberRangeSwitch();
	/**  List of available GAST Root Nodes. */
	private final LinkedList<Root> availableGastRootNodes;
	/** Line numbers found in the bytecode by canonical class name, then by canonical method name. */
	private final Map<String, Map<String, List<Integer>>> lineNumbersInBytecode;

	/**
	 * Maps from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to.
	 */
	private Map<UUID, EntityToInstrument> entitiesToInstrumentMap;

	/**
	 * Constructs the entity switch and specifies the data structures that are 
	 * filled by this class.
	 * @param entitiesToInstrument Initialized {@link List} of 
	 * {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument}s that is filled by this class.
	 * @param entityToInstrumentMap Initialized map from {@link de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument#getId()}
	 * to the {@link EntityToInstrument} it was mapped to. Created by this class.
	 * @param methodNameInstrumentedMethodMap Mapping of the fully qualified 
	 * method name to a {@link InstrumentedMethod}. Created by this class.
	 * @param availableGastRootNodes List of available GAST root nodes that 
	 * will be extended by this class if new GAST root nodes are encountered.
	 */
	public EntityToInstrumentToByCounterSwitch(
			final List<de.uka.ipd.sdq.ByCounter.instrumentation.EntityToInstrument> entitiesToInstrument,
			final Map<UUID, EntityToInstrument> entityToInstrumentMap,
			final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap,
			final LinkedList<Root> availableGastRootNodes) {
		this.entitiesToInstrument = entitiesToInstrument;
		this.entitiesToInstrumentMap = entityToInstrumentMap;
		this.methodNameInstrumentedMethodMap = methodNameInstrumentedMethodMap;
		this.availableGastRootNodes = availableGastRootNodes;
		this.lineNumbersInBytecode = new HashMap<String, Map<String,List<Integer>>>();
	}
	
	@Override
	public Boolean caseInstrumentedMethod(InstrumentedMethod instrumentedMethod) {
		addRootNodeToAvailableRootNodes(instrumentedMethod);
		final Method method = instrumentedMethod.getMethod();
		MethodDescriptor methodDesc = createMethodDescriptorForMethod(method);
		de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedMethod bcInstrumentedMethod =
				new de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedMethod(methodDesc);
		entitiesToInstrument.add(bcInstrumentedMethod);
		methodNameInstrumentedMethodMap.put(
				methodDesc.getCanonicalMethodName(), instrumentedMethod);
		this.entitiesToInstrumentMap.put(bcInstrumentedMethod.getId(), instrumentedMethod);
		logger.info("Instrumented method " + bcInstrumentedMethod.getMethod().getCanonicalMethodName() 
				+ " with id " + bcInstrumentedMethod.getId() + ".");
		return true;
	}
	
	@Override
	public Boolean caseInstrumentedCodeArea(
			InstrumentedCodeArea area) {
		addRootNodeToAvailableRootNodes(area.getFrom());
		// identify surrounding method
		Method surroundingMethodFrom = getSourroundingMethod(area.getFrom());
		Method surroundingMethodTo = getSourroundingMethod(area.getTo());
		MethodIdentifier midFrom = new MethodIdentifier(
				surroundingMethodFrom.getSurroundingClass()
						.getQualifiedName(),
				ByCounterWrapper.constructSignature(surroundingMethodFrom));
		MethodIdentifier midTo = new MethodIdentifier(
				surroundingMethodTo.getSurroundingClass()
						.getQualifiedName(),
				ByCounterWrapper.constructSignature(surroundingMethodTo));
		if(false && midFrom.equals(midTo)) {
			// from and to are in the same method; create instrumented code area
			// construct a method descriptor
			MethodDescriptor methodDesc = new MethodDescriptor(
					midFrom.fqMethodName,
					midFrom.signature);
			// create list of lines that exist in bytecode
			List<Integer> methodLineNumbers = getLinenumbersInMethod(methodDesc);
			// add a new range for the instrumented code area
			LineNumberRange newRange = instrumentedCodeAreaToLineNumberRange(area, methodLineNumbers);
			de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedCodeArea bcCodeArea = 
					new de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedCodeArea(methodDesc, newRange);
			this.entitiesToInstrument.add(bcCodeArea);
			this.entitiesToInstrumentMap.put(bcCodeArea.getId(), area);
			logger.info("Range from " + newRange.firstLine + " to " + newRange.lastLine + " with id " + bcCodeArea.getId() + " added for method " + methodDesc.getCanonicalMethodName() + ".");
		} else {
			// from and to are in different methods; create instrumented region
			// construct method descriptors
			MethodDescriptor methodDescFrom = new MethodDescriptor(
					midFrom.fqMethodName,
					midFrom.signature);
			MethodDescriptor methodDescTo = new MethodDescriptor(
					midTo.fqMethodName,
					midTo.signature);
			// create list of lines that exist in bytecode
			List<Integer> methodLineNumbersFrom = getLinenumbersInMethod(methodDescFrom);
			List<Integer> methodLineNumbersTo = getLinenumbersInMethod(methodDescTo);
			// add a new range for the instrumented code area
			int firstLineNumber = instrumentedCodeAreaToLineNumberRange(area, methodLineNumbersFrom).firstLine;
			int lastLineNumber = instrumentedCodeAreaToLineNumberRange(area, methodLineNumbersTo).lastLine;
			
			de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion bcRegion = 
					new de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion(methodDescFrom, firstLineNumber, methodDescTo, lastLineNumber);
			this.entitiesToInstrument.add(bcRegion);
			this.entitiesToInstrumentMap.put(bcRegion.getId(), area);
			logger.info("Region from " + firstLineNumber + " to " + lastLineNumber + " with id " + bcRegion.getId() + " added for method " + methodDescFrom.getCanonicalMethodName() + ".");
		}
		return true;
	}

	/**
	 * Find the methods line numbers in {@link #lineNumbersInBytecode} or 
	 * compute and add them. 
	 * @param methodDesc Method to search line numbers for.
	 * @return Line numbers available in the method.
	 */
	private List<Integer> getLinenumbersInMethod(MethodDescriptor methodDesc) {
		Map<String, List<Integer>> lnsInClass = lineNumbersInBytecode.get(methodDesc.getCanonicalClassName());
		if(lineNumbersInBytecode.get(methodDesc.getCanonicalClassName()) == null) {
			try {
				lnsInClass = BytecodeCounter.findLineNumbersIn(methodDesc.getCanonicalClassName());
			} catch (IOException e) {
				throw new RuntimeException("Could not read class " + methodDesc.getCanonicalClassName() + " for finding line numbers.", e);
			}
			lineNumbersInBytecode.put(methodDesc.getCanonicalClassName(), lnsInClass);
		}
		List<Integer> methodLineNumbers = lnsInClass.get(methodDesc.getCanonicalMethodName());
		return methodLineNumbers;
	}

	/**
	 * @param method GAST {@link de.fzi.gast.functions.Method}.
	 * @return ByCounter {@link MethodDescriptor}.
	 */
	private MethodDescriptor createMethodDescriptorForMethod(final Method method) {
		MethodDescriptor methodDesc = new MethodDescriptor(
				method.getSurroundingClass().getQualifiedName(),
				ByCounterWrapper.constructSignature(method));
		return methodDesc;
	}

	/**Generates a {@link LineNumberRange} corresponding to the given {@link InstrumentedCodeArea}.
	 * @param area The code area.
	 * @param methodLineNumbers Line numbers that exist in the code areas method.
	 * @return The corresponding range.
	 */
	private LineNumberRange instrumentedCodeAreaToLineNumberRange(
			final InstrumentedCodeArea area, final List<Integer> methodLineNumbers) {
		int firstLine;
		int lastLine;
		LineNumberRange tempRange;
		statementToLineNumberRangeSwitch.setMethodLineNumbers(methodLineNumbers);
		tempRange = statementToLineNumberRangeSwitch.doSwitch(area.getFrom());
		if (tempRange == null) {
			throw new IllegalArgumentException("The statement ("
					+ area.getFrom()
					+ ") linked by getFrom() of the instrumentation area("
					+ area + ") could not be mapped to a LineNumberRange.");
		} else {
			firstLine = tempRange.firstLine;
		}
		tempRange = statementToLineNumberRangeSwitch.doSwitch(area.getTo());
		if (tempRange == null) {
			throw new IllegalArgumentException("The statement ("
					+ area.getTo()
					+ ") linked by getTo() of the instrumentation area("
					+ area + ") could not be mapped to a LineNumberRange.");
		} else {
			lastLine = tempRange.lastLine;
		}
		if (firstLine < 0) {
			StringBuilder result = new StringBuilder("The provided starting line number was smaller than zero for an InstrumentedCodeArea.");
			result.append("The firstLine is: " + firstLine+ ". ");
			result.append("The position is: ");
			Position pos = (Position) area.getFrom().getPosition();
			if (pos.getSourceFile() != null) {
				result.append(pos.getSourceFile().getSimpleName() + ": ");
			}
			result.append("(" + pos.getStartLine() +":" + pos.getStartColumn() + " - " + pos.getEndLine() + ":" + pos.getEndColumn() + ")" + ": " + area.getFrom().eClass().getName());
			throw new IllegalArgumentException(result.toString());
		}
		if (lastLine < 0) {
			StringBuilder result = new StringBuilder("The provided ending line number was smaller than zero for an InstrumentedCodeArea.");
			result.append("The lastLine is: " + lastLine + ". ");
			result.append("The position is: ");
			Position pos = (Position) area.getTo().getPosition();
			if (pos.getSourceFile() != null) {
				result.append(pos.getSourceFile().getSimpleName() + ": ");
			}
			result.append("(" + pos.getStartLine() +":" + pos.getStartColumn() + " - " + pos.getEndLine() + ":" + pos.getEndColumn() + ")" + ": " + area.getTo().eClass().getName());
			throw new IllegalArgumentException(result.toString());
		}
		if(lastLine < firstLine) {
			int tmp = firstLine;
			firstLine = lastLine;
			lastLine = tmp;
		}

		LineNumberRange newRange = new LineNumberRange(firstLine,
					lastLine);
		while (firstLine < lastLine && !methodLineNumbers.contains(firstLine)) {
			// try to adjust the line number until a line exists in bytecode
			firstLine++;
		}
		boolean findSpecificExistingLastLine = false;
		while (findSpecificExistingLastLine && firstLine < lastLine && !methodLineNumbers.contains(lastLine)) {
			// try to adjust the line number until a line exists in bytecode
			lastLine--;
		}
		newRange.firstLine = firstLine;
		newRange.lastLine = lastLine;

		return newRange;
	}

	/**Returns the surrounding method for a given statement.
	 * @param statement The statement.
	 * @return The method containing the statement.
	 */
	private Method getSourroundingMethod(Statement statement) {
		Method surroundingMethod = null;
		assert(statement != null);
		EObject eObject = statement;
		while (eObject.eContainer() != null && !( eObject.eContainer() instanceof Method) ) {
			eObject = eObject.eContainer();
		}
		if (eObject.eContainer() instanceof Method) {
			surroundingMethod = (Method) eObject.eContainer();
			String errorMsg = null;
			if (surroundingMethod == null) {
				errorMsg = "Could not get surrounding method for statement " + statement.getId();
				logger.severe(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
			if (surroundingMethod.getSurroundingClass() == null) {
				errorMsg = "Could not get surrounding class for surrounding method for statement " + statement.getId();
				logger.severe(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
			if (surroundingMethod.getSurroundingClass().getQualifiedName() == null) {
				errorMsg = "Could not get qualified name for surrounding class of surrounding method for statement " + statement.getId();
				logger.severe(errorMsg);
				throw new IllegalArgumentException(errorMsg);
			}
			return surroundingMethod;
		} else {
			throw new IllegalArgumentException("No sourrounding method could be found for the provided statement.");
		}
	}

	/**Search for the Root node which contains a GAST element. Add this node to
	 * the list of known Root nodes.
	 * @param gastEObject The GAST element. 
	 */
	private void addRootNodeToAvailableRootNodes(
			EObject gastEObject) {
		// traverse tree until Root node is found
		while (!( gastEObject instanceof Root ) && gastEObject.eContainer() != null) {
			gastEObject = gastEObject.eContainer();
		}
		// add object if it is not contained in the list of known nodes
		if (gastEObject instanceof Root) {
			if (!availableGastRootNodes.contains(gastEObject)) {
				availableGastRootNodes.add((Root)gastEObject);
			}
		} else {
			throw new IllegalArgumentException(
					"Could not find a Root element which contains the supplied GAST element. " +
					"Either the element is not a GAST element or it is not properly contained " +
					"in a GAST model.");
		}
	}
}
