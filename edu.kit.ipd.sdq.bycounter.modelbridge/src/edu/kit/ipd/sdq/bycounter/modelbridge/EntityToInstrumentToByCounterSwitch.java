/**
 * 
 */
package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.core.Root;
import de.fzi.gast.core.corePackage;
import de.fzi.gast.functions.Method;
import de.fzi.gast.statements.BlockStatement;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.statements.statementsPackage;
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
	private final List<MethodDescriptor> methodsToInstrument;
	/** Mapping of ByCounter ranges to code areas in the model. */
	private final Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap;
	/** Mapping of the fully qualified method name to a {@link InstrumentedMethod}. */
	private final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap;
	/** Provides the LineNumberRanges corresponding to a given statement. */
	private static final StatementToLineNumberRangeSwitch statementToLineNumberRangeSwitch = new StatementToLineNumberRangeSwitch();
	/**  List of available GAST Root Nodes. */
	private final LinkedList<Root> availableGastRootNodes;

	public EntityToInstrumentToByCounterSwitch(
			final List<MethodDescriptor> methodsToInstrument,
			final Map<LineNumberRange, InstrumentedCodeArea> rangeCodeAreaMap,
			final Map<String, InstrumentedMethod> methodNameInstrumentedMethodMap,
			final LinkedList<Root> availableGastRootNodes) {
		this.methodsToInstrument = methodsToInstrument;
		this.rangeCodeAreaMap = rangeCodeAreaMap;
		this.methodNameInstrumentedMethodMap = methodNameInstrumentedMethodMap;
		this.availableGastRootNodes = availableGastRootNodes;
	}
	
	@Override
	public Boolean caseInstrumentedMethod(InstrumentedMethod instrumentedMethod) {
		addRootNodeToAvailableRootNodes(instrumentedMethod);
		Method method = instrumentedMethod.getMethod();
		MethodDescriptor methodDesc = new MethodDescriptor(
				method.getSurroundingClass().getQualifiedName(),
				ByCounterWrapper.constructSignature(method));
		methodsToInstrument.add(methodDesc);
		methodNameInstrumentedMethodMap.put(
				methodDesc.getCanonicalMethodName(), instrumentedMethod);
		return true;
	}
	
	@Override
	public Boolean caseInstrumentedCodeArea(
			InstrumentedCodeArea area) {
		addRootNodeToAvailableRootNodes(area.getFrom());
		// identify surrounding method
		Method surroundingMethod = getSourroundingMethod(area.getFrom());
		String errorMsg = null;
		if (surroundingMethod == null) {
			errorMsg = "Could not get surrounding method for statement " + area.getFrom().getId();
			logger.severe(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		if (surroundingMethod.getSurroundingClass() == null) {
			errorMsg = "Could not get surrounding class for surrounding method for statement " + area.getFrom().getId();
			logger.severe(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		if (surroundingMethod.getSurroundingClass().getQualifiedName() == null) {
			errorMsg = "Could not get qualified name for surrounding class of surrounding method for statement " + area.getFrom().getId();
			logger.severe(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}
		
		MethodIdentifier mid = new MethodIdentifier(
				surroundingMethod.getSurroundingClass()
						.getQualifiedName(),
				ByCounterWrapper.constructSignature(surroundingMethod));
		// ensure that surrounding method is instrumented
		MethodDescriptor methodDesc = new MethodDescriptor(
				mid.fqMethodName,
				mid.signature);
		if (!methodsToInstrument.contains(methodDesc)) {
			methodsToInstrument.add(methodDesc);
		} else {
			methodDesc = methodsToInstrument.get(methodsToInstrument.indexOf(methodDesc));
		}
		// check for existing ranges within this method
		List<LineNumberRange> ranges;
		if (methodDesc.getCodeAreasToInstrument() == null) {
			ranges = new LinkedList<LineNumberRange>();
		} else {
			ranges = new LinkedList<LineNumberRange>(Arrays.asList(methodDesc.getCodeAreasToInstrument()));
		}
		// add a new range for the instrumented code area
		LineNumberRange newRange = instrumentedCodeAreaToLineNumberRange(area); 
		ranges.add(newRange);
		logger.info("Range from " + newRange.firstLine + " to " + newRange.lastLine + " added for method " + methodDesc.getCanonicalMethodName() + ".");
		methodDesc.setCodeAreasToInstrument(ranges.toArray(new LineNumberRange[0]));
		rangeCodeAreaMap.put(newRange, area);
		return true;
	}

	/**Generates a {@link LineNumberRange} corresponding to the given {@link InstrumentedCodeArea}.
	 * @param area The code area.
	 * @return The corresponding range.
	 */
	private LineNumberRange instrumentedCodeAreaToLineNumberRange(
			InstrumentedCodeArea area) {
		int firstLine;
		int lastLine;
		LineNumberRange tempRange;
		tempRange = statementToLineNumberRangeSwitch.doSwitch(area.getFrom());
		if (tempRange == null) {
			throw new IllegalArgumentException("The statement ("
					+ area.getFrom()
					+ ") linked by getFrom() of the instrumentation area("
					+ area + ") could not be mapped to a LineNumberRang.");
		} else {
			firstLine = tempRange.firstLine;
		}
		tempRange = statementToLineNumberRangeSwitch.doSwitch(area.getTo());
		if (tempRange == null) {
			throw new IllegalArgumentException("The statement ("
					+ area.getTo()
					+ ") linked by getTo() of the instrumentation area("
					+ area + ") could not be mapped to a LineNumberRang.");
		} else {
			lastLine = tempRange.lastLine;
		}
		LineNumberRange newRange = new LineNumberRange(firstLine,
					lastLine);
		return newRange;
	}

	/**Returns the surrounding method for a given statement.
	 * @param statement The statement.
	 * @return The method containing the statement.
	 */
	private Method getSourroundingMethod(Statement statement) {
		assert(statement != null);
		while (statement.getSurroundingStatement() != null) {
			statement = statement.getSurroundingStatement();
			if (statement.getBranch() != null) {
				statement = statement.getBranch().getBranchstatement();
			}
		}
//		while (statement.eClass().getClassifierID() != statementsPackage.BLOCK_STATEMENT && statement != null) {
//			statement = statement.getSurroundingStatement();
//		}
		if (statement == null) {
			throw new IllegalArgumentException("No sourrounding method could be found for the provided statement.");
		}
		Method surroundingMethod = (Method)((BlockStatement) statement).getSurroundingFunction();
		return surroundingMethod;
	}

	/**Search for the Root node which contains a GAST element. Add this node to
	 * the list of known Root nodes.
	 * @param gastEObject The GAST element. 
	 */
	private void addRootNodeToAvailableRootNodes(
			EObject gastEObject) {
		// traverse tree until Root node is found
		while (gastEObject.eClass().getClassifierID() != corePackage.ROOT && gastEObject.eContainer() != null) {
			gastEObject = gastEObject.eContainer();
		}
		// add object if it is not contained in the list of known nodes
		if (gastEObject.eClass().getClassifierID() == corePackage.ROOT) {
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
