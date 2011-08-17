package edu.kit.ipd.sdq.bysuite.evaluation.utils;
import java.io.File;
import java.util.List;

import spec.jbb.JBBmain;
import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;

/**
 * This class executes SPECjbb2005 - uninstrumented. Potentially, 
 * the JBBMain class can be found either in the (official or instrumented) 
 * jbb.jar file, but also the class can be found when the (source code) 
 * Eclipse project of SPECjbb2005 is imported. 
 * 
 * However, the design of SPECjbb2005 is such that when importing 
 * Eclipse project of SPECjbb2005, this class currently does not work, 
 * even if the working directory for this class is set to the working 
 * directory of the SPECjbb2005 project. JAR file invocation to be tried
 * 
 * @author Martin Krogmann, Michael Kuperberg
 */
public class UninstrumentedSPECjbb2005_Runner {

	public static void main(String[] args) {
		System.out.println("Working directory: "+new File(".").getAbsolutePath());
		
		final String jbbMainClassName = JBBmain.class.getCanonicalName();
		final String mainMethodSig = "public static void main(java.lang.String args[])";
		final MethodDescriptor methodToExecute = 
			new MethodDescriptor(jbbMainClassName, mainMethodSig);
		
		//ProcessBuilder is NOT an option here, because separate process and separate JVM cannot use CountingResultCollector
		boolean useByCounterApi = false;
		BytecodeCounter counter = new BytecodeCounter();
		//what is the underlying mechanism: Reflection?
		//TODO is there an implicit assumption that the classes are (or are NOT?) already instrumented?
		if(useByCounterApi){
			counter.execute(methodToExecute, new Object[]{new String[]{"-propfile", "SPECjbb.props"}});//does not work...
		}else{
			JBBmain.main(new String[]{"-propfile", "SPECjbb.props"}); //this does not work, too
		}
		
		List<CountingResult> results = 
			CountingResultCollector.getInstance().retrieveAllCountingResults_recursively();
		
		//output the results to the console/log
		for(CountingResult r : results) {
			CountingResultCollector.getInstance().logResult(r, true, true);
		}
		
		//clear the results as we do not need them anymore
		CountingResultCollector.getInstance().clearResults();
	}
}
