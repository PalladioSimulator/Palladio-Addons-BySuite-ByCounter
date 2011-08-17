package edu.kit.ipd.sdq.bysuite.evaluation.externalactionmeasuring;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.logging.Logger;

import spec.jbb.JBBmain;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;
import edu.kit.ipd.sdq.InternalActionInstrumenter.instrumentation.Configuration;
import edu.kit.ipd.sdq.InternalActionInstrumenter.instrumentation.InternalActionInstrumenter;
import edu.kit.ipd.sdq.InternalActionInstrumenter.instrumentation.PerfIndicatorInstrumentation;
import edu.kit.ipd.sdq.InternalActionInstrumenter.reporting.StandardResultCollector;

/**
 * This class applies InternalActionInstrumenter to SPECjbb2005 - it does NOT 
 * run the instrumented benchmark. It directly instruments TODO
 * 
 * TODO the class is not robust against changes to .class files 
 * by Eclipse or other building tools! In Eclipse, make sure that "scrubbing" 
 * the working directory (and its bin subdir) is disabled/deactivated. 
 * Also note that InternalActionInstrumenter does NOT contain code 
 * to prevent double-instrumentation - use at your own risk...
 * @author Martin Krogmann, Michael Kuperberg
 *
 */
public class SPECjbb2005ExternalActionsInstrumenter {
	
	private static Logger log = Logger.getLogger(SPECjbb2005ExternalActionsInstrumenter.class.getCanonicalName());

	private static final String COMPONENT_NAME_SPECJBB = "specJBB";

	/**
	 * Instruments all classes in SpecJBB2005 with 
	 * {@link InternalActionInstrumenter} 
	 * to report nanoTime() value to {@link StandardResultCollector}.
	 * Overrides the .class files , so repeated execution 
	 * produces undesirable results (more instrumentation added than wanted). 
	 * To avoid this, you need to clean the project after each run.
	 * @param args are ignored
	 */
	public static void main(String[] args) {

		//1. specify what method calls to insert
		final String PERF_CLASS = 
			System.class.getCanonicalName();
		final String PERF_METHOD = 
			"public static long nanoTime()";
		
		List<PerfIndicatorInstrumentation> perfOptions =
			new ArrayList<PerfIndicatorInstrumentation>();
		MethodDescriptor perfMethod = 
			new MethodDescriptor(PERF_CLASS, PERF_METHOD);
		MethodDescriptor reportingMethod = 
			StandardResultCollector.reportLongResultMethodDescriptor;
		perfOptions.add(
				new PerfIndicatorInstrumentation(perfMethod, reportingMethod));

		//2. Define what is internal and what is external, to allow for 
		//finding component-external calls and instrumenting 
		//performance indicator calls in front of them and 
		//following them. With other words, this helps to define on 
		//which occasions to instrument
		//TODO, currently, there is a bug so that only API calls are considered 
		//external and as occasions to instrument
		InternalActionInstrumenter instrumenter = 
			new InternalActionInstrumenter();
		specifyComponentsForSpecJbb2005(instrumenter);
		
		//3. open classes INTO which to instrument
		File jbbBinDirectory = new File(
				JBBmain.class.getProtectionDomain().getCodeSource().getLocation().getFile());//Aha!
		Set<File> classFiles = new HashSet<File>();
		findClassesInDirectory(classFiles, "", jbbBinDirectory);
		try {
			for(File inClass : classFiles) {
				if(!inClass.exists()) {
					log.severe("File not found: " + inClass.getAbsolutePath());
					System.exit(1);
				}
				byte[] inputClass;
				log.info("Instrumenting " + inClass.getCanonicalPath());
				inputClass = InternalActionInstrumenter.readBytesFromFile(inClass);
				// transform the class
				byte[] outputClass = instrumenter.instrument(inputClass, perfOptions);
				// override the original .class file 
				//Eclipse automatic built will not re-overwrite it until a clean or a manual (re-)build
				InternalActionInstrumenter.writeBytesToFile(inClass, outputClass);
			}
			instrumenter.writeConstantsClassToFile(jbbBinDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Specify what to consider internal/external.
	 * @param instrumenter The {@link InternalActionInstrumenter} to apply 
	 * the specification to.
	 */
	private static void specifyComponentsForSpecJbb2005(InternalActionInstrumenter instrumenter) {
		Configuration configuration; 
		instrumenter.getClass();
		// if the following line produces a NoSuchMethodError, you need to 
		// update the internalactionsinstrumenter jar file that is used in the 
		// specJBB2005 project
		configuration = instrumenter.getConfiguration(); //getConfiguration();
		// define the data structure for components
		SortedMap<String, SortedSet<String>> componentDefinition =
			new TreeMap<String, SortedSet<String>>();
		// define a data structure for holding component-internal classes
		TreeSet<String> includedClasses = new TreeSet<String>();
		includedClasses.add("spec.jbb.*");
		//these classes now belong to specJbb
		componentDefinition.put(COMPONENT_NAME_SPECJBB, includedClasses);
		
		// add the definition of the component to the configuration, 
		//it remains to be said whether it is an included or an excluded component (see below)
		configuration.setComponentDefinition(componentDefinition);
		
		// add the specjbb component as an *included* component
		SortedSet<String> includedComponents = new TreeSet<String>(); 
		includedComponents.add(COMPONENT_NAME_SPECJBB);
		configuration.setIncludedComponents(includedComponents);//TODO what is the difference between setIncludedComponents and setComponentDefinition?
		configuration.setSurroundAPIcalls(true);
		configuration.setSurroundInternalCalls(false);
		configuration.setSurroundExternalCalls(false);
	}
	
	/** Filters files for .class files */
	private static final FileFilter classFilter = new FileFilter() {
		/* (non-Javadoc)
		 * @see java.io.FileFilter#accept(java.io.File)
		 */
		public boolean accept(File file) {
			return file.isDirectory() || file.getName().toLowerCase().endsWith(".class");//TODO problems when enums are defined in a class...
		}
	};
	
	private static void findClassesInDirectory(Set<File> classes,
			final String prefix, final File directory) {
		File[] files = directory.listFiles(classFilter);
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				// recurse
				String pre;
				if(prefix.isEmpty()) {
					pre = files[i].getName();
				} else {
					pre = prefix + File.separator + files[i].getName();
				}
				findClassesInDirectory(classes, pre, files[i]);//the last parameter is final, and won't be modified!
			} else {
				// a .class file
				classes.add(files[i]);
			}
			files = directory.listFiles(classFilter);// find only ".class" files
		}
	}
}
