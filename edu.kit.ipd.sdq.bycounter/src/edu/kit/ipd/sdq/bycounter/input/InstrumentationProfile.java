/**
 */
package edu.kit.ipd.sdq.bycounter.input;

import de.fzi.gast.functions.Method;

import de.uka.ipd.sdq.identifier.Identifier;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instrumentation Profile</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p></p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentRecursively <em>Instrument Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getAggregationExcludes <em>Aggregation Excludes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument <em>Entities To Instrument</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getNumberCallGraphClassAnalyserThreads <em>Number Call Graph Class Analyser Threads</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideJoinThreadsAbility <em>Provide Join Threads Ability</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClassesToOSPath <em>Persist Instrumented Classes To OS Path</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideOnlineSectionActiveUpdates <em>Provide Online Section Active Updates</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getLogicalSetExternalToClassLoader <em>Logical Set External To Class Loader</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile()
 * @model
 * @generated
 */
public interface InstrumentationProfile extends Identifier {
	/**
	 * Returns the value of the '<em><b>Instrument Recursively</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When set, instruments methods called from the methods specified in 
	 * entitesToInstrument that  are not Java API methods (packages java.*, javax.* sun.*) and not  native methods.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instrument Recursively</em>' attribute.
	 * @see #setInstrumentRecursively(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_InstrumentRecursively()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isInstrumentRecursively();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentRecursively <em>Instrument Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrument Recursively</em>' attribute.
	 * @see #isInstrumentRecursively()
	 * @generated
	 */
	void setInstrumentRecursively(boolean value);

	/**
	 * Returns the value of the '<em><b>Aggregation Excludes</b></em>' containment reference list.
	 * The list contents are of type {@link de.fzi.gast.functions.Method}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation Excludes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation Excludes</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_AggregationExcludes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Method> getAggregationExcludes();

	/**
	 * Returns the value of the '<em><b>Entities To Instrument</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entities To Instrument</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entities To Instrument</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_EntitiesToInstrument()
	 * @see edu.kit.ipd.sdq.bycounter.input.EntityToInstrument#getInstrumentationProfile
	 * @model opposite="instrumentationProfile" containment="true" ordered="false"
	 * @generated
	 */
	EList<EntityToInstrument> getEntitiesToInstrument();

	/**
	 * Returns the value of the '<em><b>Intrumentation Profile Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getInstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intrumentation Profile Repository</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intrumentation Profile Repository</em>' container reference.
	 * @see #setIntrumentationProfileRepository(InstrumentationProfileRepository)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_IntrumentationProfileRepository()
	 * @see edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository#getInstrumentationProfile
	 * @model opposite="instrumentationProfile" transient="false" ordered="false"
	 * @generated
	 */
	InstrumentationProfileRepository getIntrumentationProfileRepository();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intrumentation Profile Repository</em>' container reference.
	 * @see #getIntrumentationProfileRepository()
	 * @generated
	 */
	void setIntrumentationProfileRepository(InstrumentationProfileRepository value);

	/**
	 * Returns the value of the '<em><b>Instrument Using Long Counters</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, counters are long values instead of integers. Must be used for counting large execution frequencies. Setting this to false offers some performance benifits but can result in overflows.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instrument Using Long Counters</em>' attribute.
	 * @see #setInstrumentUsingLongCounters(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_InstrumentUsingLongCounters()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isInstrumentUsingLongCounters();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrument Using Long Counters</em>' attribute.
	 * @see #isInstrumentUsingLongCounters()
	 * @generated
	 */
	void setInstrumentUsingLongCounters(boolean value);

	/**
	 * Returns the value of the '<em><b>Instrument Using Basic Blocks</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, bytecode instructions will be counted in groups made up of identified basic blocks. The execution numbers of single instructions are calculated after the execution. When false, every single bytecode instruction will be counted by an individual counter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instrument Using Basic Blocks</em>' attribute.
	 * @see #setInstrumentUsingBasicBlocks(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_InstrumentUsingBasicBlocks()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isInstrumentUsingBasicBlocks();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instrument Using Basic Blocks</em>' attribute.
	 * @see #isInstrumentUsingBasicBlocks()
	 * @generated
	 */
	void setInstrumentUsingBasicBlocks(boolean value);

	/**
	 * Returns the value of the '<em><b>Number Call Graph Class Analyser Threads</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Call Graph Class Analyser Threads</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p >The specific amount of thread to spawn in the CallGraphClassAnalyser which is used when instrumenting recursively. Can be used to potentially limit memory demands etc. When not set a heuristic is used instead.</p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Number Call Graph Class Analyser Threads</em>' attribute.
	 * @see #setNumberCallGraphClassAnalyserThreads(int)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_NumberCallGraphClassAnalyserThreads()
	 * @model ordered="false"
	 * @generated
	 */
	int getNumberCallGraphClassAnalyserThreads();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getNumberCallGraphClassAnalyserThreads <em>Number Call Graph Class Analyser Threads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Call Graph Class Analyser Threads</em>' attribute.
	 * @see #getNumberCallGraphClassAnalyserThreads()
	 * @generated
	 */
	void setNumberCallGraphClassAnalyserThreads(int value);

	/**
	 * Returns the value of the '<em><b>Provide Join Threads Ability</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When true, allows ByCounter to wait for all threads spawned from instrumented methods to complete.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Provide Join Threads Ability</em>' attribute.
	 * @see #setProvideJoinThreadsAbility(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_ProvideJoinThreadsAbility()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isProvideJoinThreadsAbility();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideJoinThreadsAbility <em>Provide Join Threads Ability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provide Join Threads Ability</em>' attribute.
	 * @see #isProvideJoinThreadsAbility()
	 * @generated
	 */
	void setProvideJoinThreadsAbility(boolean value);

	/**
	 * Returns the value of the '<em><b>Persist Instrumented Classes To OS Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When set, classes will be written to the hereby specified directory.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Persist Instrumented Classes To OS Path</em>' attribute.
	 * @see #setPersistInstrumentedClassesToOSPath(String)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_PersistInstrumentedClassesToOSPath()
	 * @model ordered="false"
	 * @generated
	 */
	String getPersistInstrumentedClassesToOSPath();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClassesToOSPath <em>Persist Instrumented Classes To OS Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persist Instrumented Classes To OS Path</em>' attribute.
	 * @see #getPersistInstrumentedClassesToOSPath()
	 * @generated
	 */
	void setPersistInstrumentedClassesToOSPath(String value);

	/**
	 * Returns the value of the '<em><b>Provide Online Section Active Updates</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  When true, the instrumentation for providing updates on the execution of user specified code sections is inserted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Provide Online Section Active Updates</em>' attribute.
	 * @see #setProvideOnlineSectionActiveUpdates(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_ProvideOnlineSectionActiveUpdates()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isProvideOnlineSectionActiveUpdates();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isProvideOnlineSectionActiveUpdates <em>Provide Online Section Active Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Provide Online Section Active Updates</em>' attribute.
	 * @see #isProvideOnlineSectionActiveUpdates()
	 * @generated
	 */
	void setProvideOnlineSectionActiveUpdates(boolean value);

	/**
	 * Returns the value of the '<em><b>Trace And Identify Requests</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * RequestIDs are UUIDs used to track the method call graph across threads. They are created in a root method (a method called with no request ID) and then passed recursively on to all methods it calls. Note: Currently, constructors cannot be tracked.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Trace And Identify Requests</em>' attribute.
	 * @see #setTraceAndIdentifyRequests(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_TraceAndIdentifyRequests()
	 * @model default="false" required="true" ordered="false"
	 * @generated
	 */
	boolean isTraceAndIdentifyRequests();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace And Identify Requests</em>' attribute.
	 * @see #isTraceAndIdentifyRequests()
	 * @generated
	 */
	void setTraceAndIdentifyRequests(boolean value);

	/**
	 * Returns the value of the '<em><b>Logical Set External To Class Loader</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.LogicalSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Logical Set External To Class Loader</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Logical Set External To Class Loader</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_LogicalSetExternalToClassLoader()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<LogicalSet> getLogicalSetExternalToClassLoader();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * (self.numberCallGraphClassAnalyserThreads = null) or (self.numberCallGraphClassAnalyserThreads >= 1)
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean atLeastOneThread(DiagnosticChain diagnostics, Map<Object, Object> context);

} // InstrumentationProfile
