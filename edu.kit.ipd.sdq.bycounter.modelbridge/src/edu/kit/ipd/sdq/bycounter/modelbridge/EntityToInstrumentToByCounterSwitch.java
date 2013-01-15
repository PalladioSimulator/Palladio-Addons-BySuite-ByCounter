/**
 * 
 */
package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.core.Position;
import de.fzi.gast.core.Root;
import de.fzi.gast.core.corePackage;
import de.fzi.gast.functions.Method;
import de.fzi.gast.statements.BlockStatement;
import de.fzi.gast.statements.Statement;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedCodeArea;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedMethod;
import edu.kit.ipd.sdq.bycounter.input.InstrumentedRegion;
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
		return true;
	}

	@Override
	public Boolean caseInstrumentedRegion(InstrumentedRegion instrumentedRegion) {
		MethodDescriptor startMethod = createMethodDescriptorForMethod(instrumentedRegion.getStartMethod());
		MethodDescriptor stopMethod = createMethodDescriptorForMethod(instrumentedRegion.getStopMethod());
		de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion bcInstrumentedRegion = 
				new de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion(
						startMethod, instrumentedRegion.getStartLine(), 
						stopMethod, instrumentedRegion.getStopLine());
		this.entitiesToInstrument.add(bcInstrumentedRegion);
		// TODO Auto-generated method stub
		return super.caseInstrumentedRegion(instrumentedRegion);
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
		// construct a method descriptor
		MethodDescriptor methodDesc = new MethodDescriptor(
				mid.fqMethodName,
				mid.signature);
		// add a new range for the instrumented code area
		LineNumberRange newRange = instrumentedCodeAreaToLineNumberRange(area); 
		logger.info("Range from " + newRange.firstLine + " to " + newRange.lastLine + " added for method " + methodDesc.getCanonicalMethodName() + ".");
		// 
		de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedCodeArea bcCodeArea = 
				new de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedCodeArea(methodDesc, newRange);
		this.entitiesToInstrument.add(bcCodeArea);
		this.entitiesToInstrumentMap.put(bcCodeArea.getId(), area);
		return true;
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
