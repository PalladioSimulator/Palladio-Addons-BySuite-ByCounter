package de.uka.ipd.sdq.ByCounter.execution;

/**
 * This enum lists modes in which the {@link CountingResultCollector} can 
 * handle the counting of results.
 */
public enum CountingResultCollectorModeEnum {
		DiscardAllIncomingCountingResults,
		
		ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts,
		
		ForceInlineDisregardingInstrumentMethodWishes_InstructionAndMethodCounts_ButCountReportsPerSignature,
		
//		/**
//		 * Setting this to true just appends method counts to forcedInlining_CollectedMethodCallCounts 
//		 * as well as appends method signatures to forcedInlining_CollectedMethodCallSignatures
//		 * TODO this is not evaluated properly so far
//		 */
//		ForceInlineDisregardingInstrumentMethodWishes_InstructionCountsOnly, //TODO appending method counts may still blow up memory
//		
//		ForceInlineDisregardingInstrumentMethodWishes_InstructionCountsOnly_ButCountReportsPerSignature,
		
		/** default */
		UseReportingMethodChoiceByInstrumentedMethods,
		/** unlimited threshold allowed --> define constant */
		UseThresholdPerReportingMethod_UntilTotalThresholdReachedThenForceInline,
		/** sunlimited threshold allowed --> define constant */
		UseTotalThreshold_RegardlessOfIndividualMethodCountsThenForceInline
}
