/**
 * <copyright>
 * </copyright>
 *
 * $Id$
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
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getDefinedLogicalSets <em>Defined Logical Sets</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentRecursively <em>Instrument Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isAggregateInternalCallsTransparently <em>Aggregate Internal Calls Transparently</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClasses <em>Persist Instrumented Classes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getAggregationExcludes <em>Aggregation Excludes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getEntitiesToInstrument <em>Entities To Instrument</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile()
 * @model
 * @generated
 */
public interface InstrumentationProfile extends Identifier {
	/**
	 * Returns the value of the '<em><b>Defined Logical Sets</b></em>' containment reference list.
	 * The list contents are of type {@link edu.kit.ipd.sdq.bycounter.input.LogicalSet}.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInstrumentationProfile <em>Instrumentation Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defined Logical Sets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Defined Logical Sets</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_DefinedLogicalSets()
	 * @see edu.kit.ipd.sdq.bycounter.input.LogicalSet#getInstrumentationProfile
	 * @model opposite="instrumentationProfile" containment="true" ordered="false"
	 * @generated
	 */
	EList<LogicalSet> getDefinedLogicalSets();

	/**
	 * Returns the value of the '<em><b>Instrument Recursively</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instrument Recursively</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * Returns the value of the '<em><b>Aggregate Internal Calls Transparently</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregate Internal Calls Transparently</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregate Internal Calls Transparently</em>' attribute.
	 * @see #setAggregateInternalCallsTransparently(boolean)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_AggregateInternalCallsTransparently()
	 * @model default="true" required="true" ordered="false"
	 * @generated
	 */
	boolean isAggregateInternalCallsTransparently();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#isAggregateInternalCallsTransparently <em>Aggregate Internal Calls Transparently</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregate Internal Calls Transparently</em>' attribute.
	 * @see #isAggregateInternalCallsTransparently()
	 * @generated
	 */
	void setAggregateInternalCallsTransparently(boolean value);

	/**
	 * Returns the value of the '<em><b>Persist Instrumented Classes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Persist Instrumented Classes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Persist Instrumented Classes</em>' attribute.
	 * @see #setPersistInstrumentedClasses(String)
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_PersistInstrumentedClasses()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getPersistInstrumentedClasses();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile#getPersistInstrumentedClasses <em>Persist Instrumented Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Persist Instrumented Classes</em>' attribute.
	 * @see #getPersistInstrumentedClasses()
	 * @generated
	 */
	void setPersistInstrumentedClasses(String value);

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
	 * <p>
	 * If the meaning of the '<em>Instrument Using Long Counters</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * <p>
	 * If the meaning of the '<em>Instrument Using Basic Blocks</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * Returns the value of the '<em><b>Trace And Identify Requests</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace And Identify Requests</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entities To Instrument</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entities To Instrument</em>' containment reference list.
	 * @see edu.kit.ipd.sdq.bycounter.input.InputPackage#getInstrumentationProfile_EntitiesToInstrument()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<EntityToInstrument> getEntitiesToInstrument();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * self.summationExcludes->size()>0 implies self.aggregateInternalCallsTransparently=true
	 * 
	 * @param diagnostics The chain of diagnostics to which problems are to be appended.
	 * @param context The cache of context-specific information.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean SummationexcludesonlyallowedifsummationisactiveandaggregateInternalCallsTransparentlyistrue(DiagnosticChain diagnostics, Map<Object, Object> context);

} // InstrumentationProfile
