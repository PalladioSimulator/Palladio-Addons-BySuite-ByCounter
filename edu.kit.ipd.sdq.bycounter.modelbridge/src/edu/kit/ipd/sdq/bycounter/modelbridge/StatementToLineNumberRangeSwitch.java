/**
 * 
 */
package edu.kit.ipd.sdq.bycounter.modelbridge;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.emf.ecore.EObject;

import de.fzi.gast.statements.BlockStatement;
import de.fzi.gast.statements.BranchStatement;
import de.fzi.gast.statements.ExceptionHandler;
import de.fzi.gast.statements.JumpStatement;
import de.fzi.gast.statements.LoopStatement;
import de.fzi.gast.statements.LoopStatementKind;
import de.fzi.gast.statements.SimpleStatement;
import de.fzi.gast.statements.Statement;
import de.fzi.gast.statements.util.statementsSwitch;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.parsing.LineNumberRange;

/**Provides the ByCounter {@link LineNumberRange} for a given {@link Statement}.
 * Enables the mapping of generalized abstract syntax tree elements to {@link BytecodeCounter} input.
 * @author groenda
 */
public class StatementToLineNumberRangeSwitch extends
		statementsSwitch<LineNumberRange> {
	/** Logger of this class. */
	private static final Logger logger = Logger.getLogger(StatementToLineNumberRangeSwitch.class.getCanonicalName());
	/** Indicator that a line number is not valid. Valid line numbers are > 0. */
	private static final int NO_VALID_LINE = -1;

	@Override
	public LineNumberRange caseSimpleStatement(SimpleStatement object) {
		return generateLNR(object.getPosition().getStartLine(), object.getPosition().getEndLine(), object);
	}
	
	@Override
	public LineNumberRange caseLoopStatement(LoopStatement object) {
		int firstLine;
		int lastLine;
		// determine first line
		if (object.getKind().equals(LoopStatementKind.FOREACH)) {
			// Start line of the loop statement itself
			firstLine = object.getPosition().getStartLine();
		} else {
			if (object.getInitExpression() != null) {
				// Start line of the initialization expression
				firstLine = object.getInitExpression().getPosition().getStartLine();
			} else {
				if (object.getBreakConditionExpression() != null) {
					// Start line of the stop condition of the loop
					firstLine = object.getBreakConditionExpression().getPosition().getStartLine();
				} else {
					// Start line of the body
					LineNumberRange subRange = this.doSwitch(object.getBody());
					firstLine = subRange.firstLine;
				}
			}
		}
		// determine last line
		if (object.getIncrementExpression() != null) {
			lastLine = object.getIncrementExpression().getPosition().getEndLine();
		} else if (object.getBreakConditionExpression() != null) {
			// Start line of the stop condition of the loop
			lastLine = object.getBreakConditionExpression().getPosition().getEndLine();
		} else {
			// TODO LoopStatement: Insert proper start and end line number range 
			// last line of the body
			LineNumberRange subRange = this.doSwitch(object);
			lastLine = subRange.lastLine;
		}
		return generateLNR(firstLine, lastLine, object);
	}
	
	@Override
	public LineNumberRange caseJumpStatement(JumpStatement object) {
		// computations of values are always made before the jump -> Reversed order of lines
		int firstLine = object.getPosition().getEndLine(); 
		int lastLine = object.getPosition().getStartLine();
		return generateLNR(firstLine, lastLine, object);
	}
	
	@Override
	public LineNumberRange caseBranchStatement(BranchStatement object) {
		int firstLine = object.getPosition().getStartLine();
		int lastLine = NO_VALID_LINE;
		LineNumberRange subRange; 
		// search for last non-empty branch from the end
		for (int position = object.getBranches().size()-1; position >= 0; position--) {
			subRange = this.doSwitch(object.getBranches().get(position));
			if (subRange.lastLine > 0) {
				lastLine = subRange.lastLine;
				break;
			}
		}
		return generateLNR(firstLine, lastLine, object);
	}
	
	@Override
	public LineNumberRange caseBlockStatement(BlockStatement object) {
		int firstLine = NO_VALID_LINE; // no first line could be found. Valid numbers are > 0.
		int lastLine = NO_VALID_LINE; // no last line could be found. Valid numbers are > 0.
		LineNumberRange subRange;
		// search first non-empty statement within the block from the beginning
		for (Statement statement : object.getStatements()) {
			subRange = this.doSwitch(statement);
			if (subRange.firstLine > 0) {
				firstLine = subRange.firstLine;
				break;
			}
		}
		// search for the last non-empty statement in the block from the end
		for (int position = object.getStatements().size()-1; position >= 0; position--) {
			subRange = this.doSwitch(object.getStatements().get(position));
			if (subRange.lastLine > 0) {
				lastLine = subRange.lastLine;
				break;
			}
		}
		return generateLNR(firstLine, lastLine, object);
	}
	
	@Override
	public LineNumberRange caseExceptionHandler(ExceptionHandler object) {
		int firstLine = NO_VALID_LINE; // no first line could be found. Valid numbers are > 0.
		int lastLine = NO_VALID_LINE; // no last line could be found. Valid numbers are > 0.
		LineNumberRange subRange;
		// search first non-empty statement within the guarded block from the beginning
		subRange = this.doSwitch(object.getGuardedBlock());
		if (subRange.firstLine > 0) {
			firstLine = subRange.firstLine;
		} else {
			/* if there is nothing in the guarded block there may be statements in the finally block.
			 * These are handled like simple statements. 
			 */
			subRange = this.doSwitch(object.getFinallyBlock());
			firstLine = subRange.firstLine;
			lastLine = subRange.lastLine;
		}
		// determine last line (finally block, catch blocks in reversed order, guarded block)
		if (object.getFinallyBlock() != null && lastLine == NO_VALID_LINE) {
			// end line of finally block (which contains automatically generated error handling code)
			lastLine = object.getFinallyBlock().getPosition().getEndLine();
		} 
		if (lastLine == NO_VALID_LINE) {
			// last line of last non-empty catch block. There must be at least one catch block if there is no finally block.
			for (int position = object.getCatchBlocks().size()-1; position >= 0; position--) {
				subRange = this.doSwitch(object.getCatchBlocks().get(position));
				if (subRange.lastLine > 0) {
					lastLine = subRange.lastLine;
					break;
				}
			}
		}
		if (lastLine == NO_VALID_LINE) {
			// last line of guarded/try block
			subRange = this.doSwitch(object.getGuardedBlock());
			lastLine = subRange.lastLine;
		}
		return generateLNR(firstLine, lastLine, object);
	}
	
	@Override
	public LineNumberRange caseStatement(Statement object) {
		throw new IllegalArgumentException("The provided statement (" + object + ") is not supported.");
	}
	
	/**Generates a {@link LineNumberRange} with the given parameters. 
	 * @param firstLine The first line within the range.
	 * @param lastLine The last line within the range.
	 * @param object The {@link Statement} which is mapped to the range.
	 * @return The corresponding {@link LineNumberRange}.
	 */
	private LineNumberRange generateLNR(int firstLine, int lastLine, EObject object) {
		if (firstLine >= 0 && lastLine != 0) {
			LineNumberRange range = new LineNumberRange(firstLine, lastLine);
			logger.log(Level.FINEST, "Statement(" + object + ") mapped to LineNumberRange(" + range + ")");
			return range;
		} else {
			LineNumberRange range = new LineNumberRange(firstLine, lastLine);
			logger.log(Level.FINER, "Statement(" + object + ") cannot be mapped to Bytecode instructions. Identified lines were from " + firstLine + " to " + lastLine + " (-1 means could not be found).");
			return range;
		}
	}
}
