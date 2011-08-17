package Whetstone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Dr. Reinhold P. Weicker. Dhrystone benchmark: Rationale for
 * version 2 and measurement rules. SIGPLAN Notices 23,8 (Aug.
 * 1988), [49-62], 1988.
 * 
 * MK Retrieved from http://www.aicas.com/download/Dhrystone.java on August 6th, 2010
 */

public class DhrystoneAfterDeadCodeEliminationPrevention extends DhrystoneGlobalVariables {
	public static long numberOfLoops = 100000;
	public static long numberOfRuns = 1000;//MK changed
	private static int globalVar=0;

	public static double execute(boolean performInternalMeasurement) {
		int Int_Loc_1, Int_Loc_2, Int_Loc_3;
		int[] Int_Loc_3_Ref = new int[1];
		int[] Int_Loc_1_Ref = new int[1];
		char Char_Index;
		int[] Enum_Loc = new int[1];
		String String_Loc_1, String_Loc_2;
		long begin_time=0;
		long end_time=0; 
		long total_time=0;
		if(performInternalMeasurement){
			begin_time = System.nanoTime();//MK replaced uncommented statement below
		}
		int Run_Index, Meas_Index;
		Next_Record_Glob = Second_Record;
		Record_Glob = First_Record;
		Record_Glob.Record_Comp = Next_Record_Glob;
		Record_Glob.Discr = Ident_1;
		Record_Glob.Enum_Comp = Ident_3;
		Record_Glob.Int_Comp = 40;
		Record_Glob.String_Comp = "DHRYSTONE PROGRAM, SOME STRING";
		String_Loc_1 = "DHRYSTONE PROGRAM, 1'ST STRING";
//		begin_time = System.currentTimeMillis();//TODO replace
		for (Run_Index = 1; Run_Index <= numberOfLoops; ++Run_Index) {
			globalVar += Proc_5();
			globalVar += Proc_4();
			Int_Loc_1 = 2;
			Int_Loc_2 = 3;
			String_Loc_2 = "DHRYSTONE PROGRAM, 2'ND STRING";
			Enum_Loc[0] = Ident_2;
			Bool_Glob = !Func_2(String_Loc_1, String_Loc_2);
			while (Int_Loc_1 < Int_Loc_2) {
				Int_Loc_3_Ref[0] = 5 * Int_Loc_1 - Int_Loc_2;
				globalVar += Proc_7(Int_Loc_1, Int_Loc_2, Int_Loc_3_Ref);
				Int_Loc_1 += 1;
			}
			Int_Loc_3 = Int_Loc_3_Ref[0];
			globalVar += Proc_8(Array_Glob_1, Array_Glob_2, Int_Loc_1, Int_Loc_3);
			globalVar += Proc_1(Record_Glob);
			for (Char_Index = 'A'; Char_Index <= Char_Glob_2; ++Char_Index) {
				if (Enum_Loc[0] == Func_1(Char_Index, 'C')) {
					globalVar += Proc_6(Ident_1, Enum_Loc);
				}
			}
			Int_Loc_3 = Int_Loc_2 * Int_Loc_1;
			Int_Loc_2 = Int_Loc_3 / Int_Loc_1;
			Int_Loc_2 = 7 * (Int_Loc_3 - Int_Loc_2) - Int_Loc_1;
			Int_Loc_1_Ref[0] = Int_Loc_1;
			globalVar += Proc_2(Int_Loc_1_Ref);
			Int_Loc_1 = Int_Loc_1_Ref[0];
		}
//		end_time = System.currentTimeMillis();
		if(performInternalMeasurement){
			end_time = System.nanoTime();
		total_time = end_time - begin_time;
		System.out.println(" (time for " + numberOfLoops + "): " + total_time //MK: TODO: measure this method!
				+ " ns");
		}
//		+ " millisec.");
		return (total_time);
	}

	public static int Func_1(char Char_Par_1_Val, char Char_Par_2_Val) {
		char Char_Loc_1, Char_Loc_2;
		Char_Loc_1 = Char_Par_1_Val;
		Char_Loc_2 = Char_Loc_1;
		if (Char_Loc_2 != Char_Par_2_Val)
			return Ident_1;
		else
			return Ident_2;
	}

	public static boolean Func_2(String String_Par_1_Ref, String String_Par_2_Ref) {
		int Int_Loc;
		char Char_Loc = '\0';
		Int_Loc = 2;
		while (Int_Loc <= 2)
			if (Func_1(String_Par_1_Ref.charAt(Int_Loc),
					String_Par_2_Ref.charAt(Int_Loc + 1)) == Ident_1) {
				Char_Loc = 'A';
				Int_Loc += 1;
			}
		if (Char_Loc >= 'W' && Char_Loc < 'Z')
			Int_Loc = 7;
		if (Char_Loc == 'X')
			return true;
		else {
			if (String_Par_1_Ref.compareTo(String_Par_2_Ref) > 0) {
				Int_Loc += 7;
				return true;
			} else
				return false;
		}
	}

	public static boolean Func_3(int Enum_Par_Val) {
		int Enum_Loc;
		Enum_Loc = Enum_Par_Val;
		if (Enum_Loc == Ident_3)
			return true;
		else
			return false;
	}

	public static void main(String argv[]) {
		boolean executeOnce = false;
		boolean measureEvenIfExecutingOnce = false;
		boolean measureInExecute = false;
		
		long executeResultSum = 0L;
		if(executeOnce){//how can we know that the method is not pure?
			if(measureEvenIfExecutingOnce){
				long start = System.nanoTime();
				executeResultSum += execute(measureInExecute);
				long stopp = System.nanoTime();
				System.out.println(
						"external measurment: \n"+
						(stopp-start)+" ns");
			}else{
				executeResultSum += execute(measureInExecute);
			}
			System.out.println("Sum of execute results: "+executeResultSum);
			return;
		}
		int nrOfMeasurements = 512;
		List<Long> measurements = new ArrayList<Long>();
		double sum = 0D;
		for(int n=0; n<nrOfMeasurements; n++){
			long start = System.nanoTime();
			executeResultSum += execute(measureInExecute);
			long stopp = System.nanoTime();
			measurements.add(stopp-start);
			sum += (stopp-start);
		}
		Collections.sort(measurements);
		long min = measurements.get(0);
		long med = measurements.get(measurements.size()/2);
		long avg = new Double(sum/nrOfMeasurements).longValue();
		long max = measurements.get(measurements.size()-1);
		System.out.println(
				"external measurement [ns]: \n"+
				"min="+min+", \nmed="+med+",\navg="+avg+",\nmax="+max);
		if(measureInExecute){
			System.out.println(
				"internal measurement average: "+
				executeResultSum/nrOfMeasurements);
		}
		System.out.println("Global var: "+globalVar);
		
//		// Msg.out = System.err;
//		int i;
//		double mean_time = 0;
//		for (i = 1; i <= numberOfRuns; i++) {
//			System.out.print(i + ". Test");
//			mean_time = mean_time + execute();
//		}
//		System.out.println("\nAverage Time over " + numberOfRuns + " runs: "
//				+ new Double(mean_time / numberOfRuns).longValue() + " ns"); //MK changed to ns
	}

	public static int Proc_1(DhrystoneRecord_Type Pointer_Par_Val) {
		DhrystoneRecord_Type Next_Record = Pointer_Par_Val.Record_Comp;
		Pointer_Par_Val.Record_Comp = Record_Glob;
		Pointer_Par_Val.Int_Comp = 5;
		Next_Record.Int_Comp = Pointer_Par_Val.Int_Comp;
		Next_Record.Record_Comp = Pointer_Par_Val.Record_Comp;
		globalVar += Proc_3(Next_Record.Record_Comp);
		if (Next_Record.Discr == Ident_1) {
			Next_Record.Int_Comp = 6;
			Int_Ref[0] = Next_Record.Enum_Comp;
			globalVar += Proc_6(Pointer_Par_Val.Enum_Comp, Int_Ref);
			Next_Record.Enum_Comp = Int_Ref[0];
			Next_Record.Record_Comp = Record_Glob.Record_Comp;
			Int_Ref[0] = Next_Record.Int_Comp;
			globalVar += Proc_7(Next_Record.Int_Comp, 10, Int_Ref);
			Next_Record.Int_Comp = Int_Ref[0];
		} else{
			Pointer_Par_Val = Pointer_Par_Val.Record_Comp;
		}
		return Next_Record.Int_Comp;
	}

	public static int Proc_2(int Int_Par_Ref[]) {
		int Int_Loc;
		int Enum_Loc;
		Int_Loc = Int_Par_Ref[0] + 10;
		Enum_Loc = 0;
		do
			if (Char_Glob_1 == 'A') {
				Int_Loc -= 1;
				Int_Par_Ref[0] = Int_Loc - Int_Glob;
				Enum_Loc = Ident_1;
			}
		while (Enum_Loc != Ident_1);
		return Enum_Loc;
	}

	public static int Proc_3(DhrystoneRecord_Type Pointer_Par_Ref) {
		if (Record_Glob != null)
			Pointer_Par_Ref = Record_Glob.Record_Comp;
		else
			Int_Glob = 100;
		Int_Comp_Ref[0] = Record_Glob.Int_Comp;
		globalVar += Proc_7(10, Int_Glob, Int_Comp_Ref);
		Record_Glob.Int_Comp = Int_Comp_Ref[0];
		return Record_Glob.Int_Comp;
	}

	public static char Proc_4() {
		boolean Bool_Loc;
		Bool_Loc = Char_Glob_1 == 'A';
		Bool_Loc = Bool_Loc || Bool_Glob;
		Char_Glob_2 = 'B';
		return Char_Glob_2;
	}

	public static char Proc_5() {//aaaaa...
		Char_Glob_1 = 'A';
		Bool_Glob = false;
		return Char_Glob_1;
	}

	public static int Proc_6(int Enum_Par_Val, int Enum_Par_Ref[]) {
		Enum_Par_Ref[0] = Enum_Par_Val;
		if (!Func_3(Enum_Par_Val))
			Enum_Par_Ref[0] = Ident_4;
		switch (Enum_Par_Val) {
		case Ident_1:
			Enum_Par_Ref[0] = Ident_1;
			break;
		case Ident_2:
			if (Int_Glob > 100)
				Enum_Par_Ref[0] = Ident_1;
			else
				Enum_Par_Ref[0] = Ident_4;
			break;
		case Ident_3:
			Enum_Par_Ref[0] = Ident_2;
			break;
		case Ident_4:
			break;
		case Ident_5:
			Enum_Par_Ref[0] = Ident_3;
			break;
		}
		return Enum_Par_Ref[0];
	}

	public static int Proc_7(int Int_Par_Val1, int Int_Par_Val2, int Int_Par_Ref[]) {
		int Int_Loc;
		Int_Loc = Int_Par_Val1 + 2;
		Int_Par_Ref[0] = Int_Par_Val2 + Int_Loc;
		return Int_Par_Ref[0];
	}

	public static int Proc_8(int[] Array_Par_1_Ref, int[][] Array_Par_2_Ref,
			int Int_Par_Val_1, int Int_Par_Val_2) {
		int Int_Index, Int_Loc;
		Int_Loc = Int_Par_Val_1 + 5;
		Array_Par_1_Ref[Int_Loc] = Int_Par_Val_2;
		Array_Par_1_Ref[Int_Loc + 1] = Array_Par_1_Ref[Int_Loc];
		Array_Par_1_Ref[Int_Loc + 30] = Int_Loc;
		for (Int_Index = Int_Loc; Int_Index <= Int_Loc + 1; ++Int_Index)
			Array_Par_2_Ref[Int_Loc][Int_Index] = Int_Loc;
		Array_Par_2_Ref[Int_Loc][Int_Loc - 1] += 1;
		Array_Par_2_Ref[Int_Loc + 20][Int_Loc] = Array_Par_1_Ref[Int_Loc];
		Int_Glob = 5;
		return Array_Par_2_Ref[Int_Loc + 20][Int_Loc];
	}
}
