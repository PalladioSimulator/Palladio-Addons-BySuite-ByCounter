package de.uka.ipd.sdq.ByCounter.parsing;

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationState;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion.StopPointType;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

public class RegionAnalyser extends LabelBlockAnalyser {
	
	/** User-specified {@link InstrumentedRegion}s. */
	private List<InstrumentedRegion> regions;
	/** Current method. */
	private MethodDescriptor method;
	/**
	 * {@link LineNumberAnalyser} to find line numbers of labels.
	 */
	private LineNumberAnalyser lineNumberAnalyser;


	/**
	 * @param instrumentationState {@link InstrumentationState}.
	 * @param method current method
	 * @param regions User-specified {@link InstrumentedRegion}s.
	 * @param lineNumberAnalyser {@link LineNumberAnalyser}.
	 */
	public RegionAnalyser(InstrumentationState instrumentationState,
			MethodDescriptor method,
			List<InstrumentedRegion> regions,
			LineNumberAnalyser lineNumberAnalyser) {
		super(method.getCanonicalMethodName(), instrumentationState);
		this.regions = regions;
		this.method = method;
		this.lineNumberAnalyser = lineNumberAnalyser;
	}

	@Override
	public void postAnalysisEvent(InsnList instructions) {
		super.postAnalysisEvent(instructions);
		this.saveLabelIdsForRegions();
	}


	/**
	 * For regions specified by the user, create ranges that apply to the 
	 * current method. The ids of ranges are also saved in the instrumentation
	 * context.
	 */
	private void saveLabelIdsForRegions() {
		// calculate current regions
		for(InstrumentedRegion reg : regions) {
			if(reg.getStartMethod().getCanonicalMethodName().equals(this.method.getCanonicalMethodName())) {
				int startLine = reg.getStartLine();
				List<Integer> labelIds = new LinkedList<Integer>();
				
				{
					// try to find out if the region actually should start on a label for a bigger line number
					final LineNumberNode lnNode = this.lineNumberAnalyser.findLineNumberNodeByLine(startLine);
					AbstractInsnNode prevNode = lnNode;
					if(prevNode != null && prevNode.getPrevious() != null) {
						int prevLine = -1;
						do {
							do {
								prevNode = prevNode.getPrevious();
							} while(prevNode != null && !(prevNode instanceof LineNumberNode));
							if(prevNode != null) {
								final LineNumberNode lnPrevNode = (LineNumberNode) prevNode;
								prevLine = lnPrevNode.line;
								if(prevLine >= reg.getStartLine() && prevLine <= reg.getStopLine()) {
									startLine = prevLine;
								}
							}
						} while(prevNode != null && prevLine > reg.getStartLine());
					}
				}
				
				List<InstructionBlockLocation> startLabels = this.lineNumberAnalyser.findLabelBlockByLine(startLine);
				if(startLabels == null) {
					throw new IllegalStateException("Cannot find label for " + reg.getStartMethod().getCanonicalMethodName() + " line number " + reg.getStartLine() + ".");
				}
				for(InstructionBlockLocation loc : startLabels) {
					labelIds.add(this.instructionBlockLabels.indexOf(loc.label));
				}
				reg.setStartLabelIds(labelIds);
				// save the region
				instrumentationState.getInstrumentationContext().getInstrumentationRegions().add(reg);
			}
			if(reg.getStopMethod().getCanonicalMethodName().equals(this.method.getCanonicalMethodName())) {
				List<Integer> stopLabelIds = new LinkedList<Integer>();
				boolean isReturnLineSpecialCase = false;
				if(reg.getStartLine() != reg.getStopLine() && reg.getStartMethod().getCanonicalMethodName().equals(reg.getStopMethod().getCanonicalMethodName())) {
					// start and stop in same method
					for(int startLine : new int[] {reg.getStartLine(), reg.getStartLine()-1}) {
						List<InstructionBlockLocation> startLabels = this.lineNumberAnalyser.findLabelBlockByLine(startLine);
						if(startLabels == null) {
							continue;
						}
						for(InstructionBlockLocation loc : startLabels) {
							int labelBlockIndex = this.instructionBlockLabels.indexOf(loc.label);
							InstructionBlockDescriptor instructions = this.instrumentationState.getInstrumentationContext().getLabelBlocks().getInstructionBlocksByMethod().get(this.method.getCanonicalMethodName())[labelBlockIndex];
							if(containsReturnStatement(instructions.getOpcodeCounts())) {
								isReturnLineSpecialCase = true;
								stopLabelIds.add(labelBlockIndex);
								reg.setStopPointType(StopPointType.BEFORE_SPECIFIED_LABEL);
							}
						}
						if(isReturnLineSpecialCase) {
							break;
						}
					}
				}
				if(!isReturnLineSpecialCase) {
					int stopLine = reg.getStopLine();
					if(stopLine == this.lineNumberAnalyser.getFoundLineNumbers().last()) {
						// line number of the return (or '}' for void methods)
						// we cannot get the next line because the stop line is the last
						reg.setStopPointType(StopPointType.AFTER_SPECIFIED_LABEL);
					} else {
						if(!this.lineNumberAnalyser.getFoundLineNumbers().contains(stopLine)) {
							if(stopLine > this.lineNumberAnalyser.getFoundLineNumbers().last()) {
								throw new IllegalArgumentException("Stop line number " + stopLine + " is > than the last line of the method '" + this.method.getCanonicalMethodName() + "'.");
							}
							do {
								stopLine++;
							} while(!this.lineNumberAnalyser.getFoundLineNumbers().contains(stopLine));
						} else {
							final LineNumberNode lnNode = this.lineNumberAnalyser.findLineNumberNodeByLine(stopLine);
							AbstractInsnNode nextNode = lnNode;
							do {
								nextNode = nextNode.getNext();
							} while(nextNode != null && !(nextNode instanceof LineNumberNode));
							if(nextNode == null) {
								throw new IllegalStateException("Cannot find next line number for region end: " + reg);
							}
							final LineNumberNode lnNextNode = (LineNumberNode) nextNode;
							stopLine = lnNextNode.line;
						}
						reg.setStopPointType(StopPointType.BEFORE_SPECIFIED_LABEL);
					}
					List<InstructionBlockLocation> stopLabels = this.lineNumberAnalyser.findLabelBlockByLine(stopLine);
					if(stopLabels == null) {
						throw new IllegalStateException("Cannot find label for " + reg.getStopMethod().getCanonicalMethodName() + " line number " + reg.getStopLine() + ".");
					}
					for(InstructionBlockLocation loc : stopLabels) {
						stopLabelIds.add(this.instructionBlockLabels.indexOf(loc.label));
					}
				}
				reg.setStopLabelIds(stopLabelIds);
				// save the region
				instrumentationState.getInstrumentationContext().getInstrumentationRegions().add(reg);
			}
		}
	}

	/**
	 * @param opcodes Array of opcode counts. The index determines the opcode.
	 * @return True when the given array contains a return statement.
	 */
	private boolean containsReturnStatement(final int[] opcodes) {
		return opcodes[Opcodes.ARETURN] > 0
				|| opcodes[Opcodes.DRETURN] > 0
				|| opcodes[Opcodes.FRETURN] > 0
				|| opcodes[Opcodes.IRETURN] > 0
				|| opcodes[Opcodes.LRETURN] > 0
				|| opcodes[Opcodes.RETURN] > 0
				|| opcodes[Opcodes.ATHROW] > 0;
	}
}
