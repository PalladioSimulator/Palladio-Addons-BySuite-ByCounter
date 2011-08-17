package edu.kit.ipd.sdq.bysuite.evaluation;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import de.uka.ipd.sdq.ByCounter.execution.BytecodeCounter;
import de.uka.ipd.sdq.ByCounter.execution.CountingResult;
import de.uka.ipd.sdq.ByCounter.execution.CountingResultCollector;
import de.uka.ipd.sdq.ByCounter.utils.MethodDescriptor;


/**
 * Make sure that the SPECjbb2005 project does not contain InternalActionInstrumenter instrumentation, 
 * as ByCounter is not able to recognise it, and will instrument the non-original, IAI-instrumented bytecode. 
 * The secure way is to rebuild (i.e. clean and recompile) SPECjbb2005. 
 * This instrumentation should be run with the working directory set to that of the SPECjbb2005 project.
 * 
 * Before SPECjbb2005 can be instrumented and run, it should be checked that
 * (i) it calls CountingResultCollector and/or persists the counting results
 * (ii) its build path contains up-to-date JAR of CountingResultCollector (in the same way, if 
 *      InternalActionINstrumenter is applied (which MUST be separate!), the JAR file of 
 *      InternalActionInstrumenter should be bundled)
 * (iii) the output of "normal" (uninstumented) run is available for consultation purposes 
 * (iv) _MK_isByCounterInstrumentedRun is set to true in JBBmain
 * (v) collect serialised results, use InstrumentApplicationsGenerically (main method, parameters) for prediction
 * (vi) set thread stack size for JBBMain to 16M 
 * 
 * Command line arguments (full load) : 
 * -Xmx1500M -Xss3M -XX:+UnlockDiagnosticVMOptions -XX:+LogCompilation -XX:+PrintCompilation  -XX:+PrintInlining -Xprof -Xloggc:GarbageCollection.log -XX:-CITime -XX:-PrintGCDetails -XX:-PrintGCTimeStamps
 * 
 * TODO separate the instrumentation (--> persist classes) from the execution
 * @author Martin Krogmann, Michael Kuperberg
 */
public class SpecJbb2005Instrumenter {
	
	private static boolean instrumentRecursively = false;
	
//	/**
//	 * TODO does it help to make all classes public - why is it needed?
//	 * class is private and therefore needs to be accessed by name.
//	 */
//	static String DELIVERYTRANSACTION_CLASSNAME = "spec.jbb.DeliveryTransaction";

	private static Logger log = Logger.getLogger(SpecJbb2005Instrumenter.class.getCanonicalName());
	
	private static boolean requireUserInteraction = false;
	
	private static long sleepToAllowUserConnection = 0L;
	
	private static boolean tryInvokingSPECjbb2005Programmatically = false;
	
	private static boolean useBasicBlocks = true;
	
	private static boolean writeClassFilesToDisk = true;
	
	public static void main(String[] args) {
		System.err.println("Make sure that the SPECjbb2005 project does not contain InternalActionInstrumenter instrumentation, " +
				"as ByCounter is not able to recognise it, and will instrument the non-original, instrumented bytecode. " +
				"The secure way is to rebuild (i.e. clean and recompile) SPECjbb2005");
		System.err.println("This instrumentation should be run with the working directory set to that of the SPECjbb2005 project");
		System.out.println("Current directory (i.e. working directory): "+new File(".").getAbsolutePath());
		SpecJbb2005Instrumenter si = new SpecJbb2005Instrumenter();
		si.instrumentSpecJbb2005();
	}

	/**
	 * @param counter
	 * @param mdJBBmain
	 * @deprecated
	 */
	private static void runJbbDoesNotWork(BytecodeCounter counter,
			MethodDescriptor mdJBBmain) {
		// we need to inline to cope with the memory demands
		CountingResultCollector.getInstance().setForceInliningIgnoringMethodWishes(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		counter.execute(mdJBBmain, new Object[]{new String[]{"-propfile", "SPECjbb.props"}});
		
		List<CountingResult> results = 
			CountingResultCollector.getInstance().retrieveAllCountingResults_recursively();
		//TODO currently, forcedINlining is nto being output here
		
		//output the results to the console/log
		for(CountingResult r : results) {
			CountingResultCollector.getInstance().logResult(r, false, true);
		}
		
		//clear the results as we do not need them anymore
		CountingResultCollector.getInstance().clearResults();
	}

	/**
	 * Make this method non-static
	 */
	public void instrumentSpecJbb2005() {
		BytecodeCounter counter = new BytecodeCounter();

//		String className = DELIVERYTRANSACTION_CLASSNAME;
//		
//		MethodDescriptor mdRunWarehouse = new MethodDescriptor(spec.jbb.JBBmain.class.getCanonicalName(),
//				"public boolean runWarehouse(int cur_warehouses, int num_wh, float min_btps)");
//		
//		MethodDescriptor mdJBBcrash = MethodDescriptor.forConstructor(
//				"spec.jbb.infra.Util.TransactionLogBuffer", 
//				"public TransactionLogBuffer()");
		MethodDescriptor mdPreprocess = new MethodDescriptor(
				spec.jbb.DeliveryTransaction.class.getCanonicalName(),
				"public boolean preprocess()");

		MethodDescriptor mdJBBmain = new MethodDescriptor(
				spec.jbb.JBBmain.class.getCanonicalName(),
				"public static void main(java.lang.String[] args)");

		log.info("Settings: "+this.toString());
		counter.getInstrumentationParams().setUseBasicBlocks(useBasicBlocks);
		counter.getInstrumentationParams().setUseResultCollector(true);
//		counter.getInstrumentationParams().set

		if(instrumentRecursively){
			counter.getInstrumentationParams().setInstrumentRecursively(true, 20);//TODO: exclude API, etc.
			counter.instrument(mdJBBmain);
			counter.instrument(mdPreprocess);
//			counter.instrument(mdRunWarehouse);
//			counter.instrument(mdJBBcrash);
		}else{
			if(writeClassFilesToDisk){
				File binOfSpecJbb2005 = new File("."+File.separator+"bin");
				if(!binOfSpecJbb2005.exists()){
					binOfSpecJbb2005.mkdirs(); //also recurively
				}else{
					System.out.println("Since "+binOfSpecJbb2005.getAbsolutePath()+" already exists, " +
							"Eclipse automatic compilation should be disabled to prevent overwriting of it");
				}
				counter.getInstrumentationParams().setWriteClassesToDisk(true);//this is NOT optional - next lines does NOT do this implicitly
				counter.getInstrumentationParams().setWriteClassesToDiskDirectory(binOfSpecJbb2005);
			}
			log.info("Sleeping "+sleepToAllowUserConnection+" ms to allow user-controlled tools " +
					"(e.g. JConsole, VisualVM, jstat) to attach. Change the value in source code if needed.");
			try {
				Thread.sleep(sleepToAllowUserConnection);//Time to connect to this JVM, using JConsole or VisualVM
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SortedSet<String> classNames = new TreeSet<String>();

//			classNames.add(spec.jbb.JBBmain.class.getCanonicalName());
			//			this is an enum - should not (?) be instrumented... classNames.add(spec.jbb.Company.runModes.class.getCanonicalName());
//			1.
			classNames.add(spec.jbb.Address.class.getCanonicalName());
//			2.
			classNames.add(spec.jbb.Company.class.getCanonicalName());
//			3.
			classNames.add(spec.jbb.Controller.class.getCanonicalName());
//			4.
			classNames.add(spec.jbb.Customer.class.getCanonicalName());
//			5.
			classNames.add(spec.jbb.CustomerReportTransaction.class.getCanonicalName());
//			6.
			classNames.add(spec.jbb.DeliveryHandler.class.getCanonicalName());
//			7.
			classNames.add(spec.jbb.DeliveryTransaction.class.getCanonicalName());
//			8.
			classNames.add(spec.jbb.District.class.getCanonicalName());
//			9.
			classNames.add(spec.jbb.History.class.getCanonicalName());
//			10.
			classNames.add(spec.jbb.Infrastructure.class.getCanonicalName());
//			11.
			classNames.add(spec.jbb.Item.class.getCanonicalName());
//			12.
			classNames.add(spec.jbb.JBBDataStorage.class.getCanonicalName());
//			13.
			classNames.add(spec.jbb.JBBLogFormatter.class.getCanonicalName());
//			14.
			classNames.add(spec.jbb.JBBProperties.class.getCanonicalName());
//			15.
			classNames.add(spec.jbb.JBBSortedStorage.class.getCanonicalName());
//			16.
			classNames.add(spec.jbb.JBButil.class.getCanonicalName());
//			17.
			classNames.add(spec.jbb.MapDataStorage.class.getCanonicalName());
//			18.
			classNames.add(spec.jbb.NewOrder.class.getCanonicalName());
//			19.
			classNames.add(spec.jbb.NewOrderTransaction.class.getCanonicalName());
//			20.
			classNames.add(spec.jbb.Order.class.getCanonicalName());
//			21.
			classNames.add(spec.jbb.OrderStatusTransaction.class.getCanonicalName());
//			22.
			classNames.add(spec.jbb.Orderline.class.getCanonicalName());
//			23.
			classNames.add(spec.jbb.PaymentTransaction.class.getCanonicalName());
//			24.
			classNames.add(spec.jbb.PrintLastSeq.class.getCanonicalName());
//			25.
			classNames.add(spec.jbb.ResFilter.class.getCanonicalName());
//			26.
			classNames.add(spec.jbb.RunSequencer.class.getCanonicalName());
//			27.
			classNames.add(spec.jbb.SaveOutput.class.getCanonicalName());
//			28.
			classNames.add(spec.jbb.Stock.class.getCanonicalName());
//			29.
			classNames.add(spec.jbb.StockLevelTransaction.class.getCanonicalName());
//			30.
			classNames.add(spec.jbb.SynchronizedJBBDataStorage.class.getCanonicalName());//TODO ByCounter fails on these two...
//			31. 
			classNames.add(spec.jbb.SynchronizedJBBSortedStorage.class.getCanonicalName());
//			32.
			classNames.add(spec.jbb.TimerData.class.getCanonicalName());
//			33.
			classNames.add(spec.jbb.Transaction.class.getCanonicalName());
//			34.
			classNames.add(spec.jbb.TransactionManager.class.getCanonicalName());
//			35.
			classNames.add(spec.jbb.TreeMapDataStorage.class.getCanonicalName());
//			36.
			classNames.add(spec.jbb.Warehouse.class.getCanonicalName());
//			37.
			classNames.add(spec.jbb.infra.Util.ScreenException.class.getCanonicalName());
//			38.
			classNames.add(spec.jbb.infra.Util.TransactionLogBuffer.class.getCanonicalName());
//			39.
			classNames.add(spec.jbb.infra.Util.XMLLineDocumentException.class.getCanonicalName());
//			40.
			classNames.add(spec.jbb.infra.Util.XMLTransactionLog.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.Check.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.digest.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.digestExpected.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.LoopBounds.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.LoopBounds2.class.getCanonicalName());
//			classNames.add(spec.jbb.validity.Check.class.getCanonicalName());
//			41.
			classNames.add(spec.reporter.GraphImage.class.getCanonicalName());
//			42.
			classNames.add(spec.reporter.HTMLTableGraphGenerator3.class.getCanonicalName());
//			43.
			classNames.add(spec.reporter.JBBReportFilenameFilter.class.getCanonicalName());
//			44.
			classNames.add(spec.reporter.Metrics.class.getCanonicalName());
//			45.
			classNames.add(spec.reporter.MultiVMReport.class.getCanonicalName());
//			46.
			classNames.add(spec.reporter.MultiVMReporter.class.getCanonicalName());
//			47.
			classNames.add(spec.reporter.MultiVMTxtReport.class.getCanonicalName());
//			48.
			classNames.add(spec.reporter.Report.class.getCanonicalName());
//			49.
			classNames.add(spec.reporter.ReportProps.class.getCanonicalName());
//			50.
			classNames.add(spec.reporter.Reporter.class.getCanonicalName());
//			51.
			classNames.add(spec.reporter.Result.class.getCanonicalName());
//			52.
			classNames.add(spec.reporter.Run.class.getCanonicalName());
//			53.
			classNames.add(spec.reporter.Table.class.getCanonicalName());
//			54.
			classNames.add(spec.reporter.TableGroup.class.getCanonicalName());
//			55.
			classNames.add(spec.reporter.TableGroupCompare.class.getCanonicalName());
//			56.
			classNames.add(spec.reporter.Template.class.getCanonicalName());
//			57.
			classNames.add(spec.reporter.TemplateCompare.class.getCanonicalName());
//			58.
			classNames.add(spec.reporter.TextBlock.class.getCanonicalName());
//			59.
			classNames.add(spec.reporter.TextColumn.class.getCanonicalName());
//			60.
			classNames.add(spec.reporter.TextMetrics.class.getCanonicalName());
//			61.
			classNames.add(spec.reporter.TextiReport.class.getCanonicalName());
//			62.
			classNames.add(spec.reporter.Title.class.getCanonicalName());

			long pauseInMs = 2000L;
			int nrOfClasses = classNames.size();
			int currClassIndex=0;
			for(String classNameToInstrument : classNames){
				counter.instrumentAllInClass(classNameToInstrument, new String[]{});
				System.out.println("Completed "+(currClassIndex+1)+". class out of "+nrOfClasses);
				System.gc(); //I believe this helps very well
				if(requireUserInteraction || currClassIndex==nrOfClasses-2){
					try {
						Thread.sleep(pauseInMs);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				if(requireUserInteraction){
					System.err.println("\n\nPress enter to proceed\n\n");
					try {
						System.in.read();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				currClassIndex++;
			}

		}
		
		if(tryInvokingSPECjbb2005Programmatically){
			runJbbDoesNotWork(counter, mdJBBmain);
		}
	}

//	@Override
//	public String toString() {
//		return "SpecJbb2005Instrumenter [instrumentRecursively="
//				+ instrumentRecursively + ", writeClassFilesToDisk="
//				+ writeClassFilesToDisk + ", useBasicBlocks=" + useBasicBlocks
//				+ ", requireUserInteraction=" + requireUserInteraction + "]";
//	}

}
