package de.uka.ipd.sdq.ByCounter.parsing;

import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LineNumberNode;

import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentedRegion;
import de.uka.ipd.sdq.ByCounter.instrumentation.InstrumentationState;
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
				List<InstructionBlockLocation> startLabels = this.lineNumberAnalyser.findLabelBlockByLine(reg.getStartLine());
				List<Integer> labelIds = new LinkedList<Integer>();
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
				int stopLine = reg.getStopLine();
				if(stopLine == this.lineNumberAnalyser.getFoundLineNumbers().last()) {
					// line number of the return (or '}' for void methods)
					// we cannot get the next line because the stop line is the last
					reg.setStopPointType(StopPointType.AFTER_SPECIFIED_LABEL);
				} else {
					if(!this.lineNumberAnalyser.getFoundLineNumbers().contains(stopLine)) {
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
				List<Integer> labelIds = new LinkedList<Integer>();
				if(stopLabels == null) {
					throw new IllegalStateException("Cannot find label for " + reg.getStopMethod().getCanonicalMethodName() + " line number " + reg.getStopLine() + ".");
				}
				for(InstructionBlockLocation loc : stopLabels) {
					labelIds.add(this.instructionBlockLabels.indexOf(loc.label));
				}
				reg.setStopLabelIds(labelIds);
				// save the region
				instrumentationState.getInstrumentationContext().getInstrumentationRegions().add(reg);
			}
		}
	}
}
