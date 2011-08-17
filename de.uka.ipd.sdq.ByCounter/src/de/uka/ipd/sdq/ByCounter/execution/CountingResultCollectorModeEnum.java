package de.uka.ipd.sdq.ByCounter.execution;

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
		
		UseReportingMethodChoiceByInstrumentedMethods, //default...
		
		UseThresholdPerReportingMethod_UntilTotalThresholdReachedThenForceInline,//unlimited threshold allowed --> define constant
		
		UseTotalThreshold_RegardlessOfIndividualMethodCountsThenForceInline//unlimited threshold allowed --> define constant
}
