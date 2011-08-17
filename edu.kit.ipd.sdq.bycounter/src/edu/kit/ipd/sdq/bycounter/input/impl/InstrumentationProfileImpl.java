/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.functions.Method;
import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.input.AggregationType;
import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;
import edu.kit.ipd.sdq.bycounter.input.InternalResultStorageType;
import edu.kit.ipd.sdq.bycounter.input.LogicalSet;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumentation Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getDefinedLogicalSets <em>Defined Logical Sets</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentRecursively <em>Instrument Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isAggregateInternalCallsTransparently <em>Aggregate Internal Calls Transparently</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getAggregationType <em>Aggregation Type</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getPersistInstrumentedClasses <em>Persist Instrumented Classes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getTemporaryResultsType <em>Temporary Results Type</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getEntitiesToInstrument <em>Entities To Instrument</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getAggregationExcludes <em>Aggregation Excludes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentationProfileImpl extends IdentifierImpl implements InstrumentationProfile {
	/**
	 * The cached value of the '{@link #getDefinedLogicalSets() <em>Defined Logical Sets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinedLogicalSets()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalSet> definedLogicalSets;

	/**
	 * The default value of the '{@link #isInstrumentRecursively() <em>Instrument Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentRecursively()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTRUMENT_RECURSIVELY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInstrumentRecursively() <em>Instrument Recursively</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentRecursively()
	 * @generated
	 * @ordered
	 */
	protected boolean instrumentRecursively = INSTRUMENT_RECURSIVELY_EDEFAULT;

	/**
	 * The default value of the '{@link #isAggregateInternalCallsTransparently() <em>Aggregate Internal Calls Transparently</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAggregateInternalCallsTransparently()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAggregateInternalCallsTransparently() <em>Aggregate Internal Calls Transparently</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAggregateInternalCallsTransparently()
	 * @generated
	 * @ordered
	 */
	protected boolean aggregateInternalCallsTransparently = AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getAggregationType() <em>Aggregation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationType()
	 * @generated
	 * @ordered
	 */
	protected static final AggregationType AGGREGATION_TYPE_EDEFAULT = AggregationType.ONLINE;

	/**
	 * The cached value of the '{@link #getAggregationType() <em>Aggregation Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationType()
	 * @generated
	 * @ordered
	 */
	protected AggregationType aggregationType = AGGREGATION_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPersistInstrumentedClasses() <em>Persist Instrumented Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistInstrumentedClasses()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSIST_INSTRUMENTED_CLASSES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistInstrumentedClasses() <em>Persist Instrumented Classes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistInstrumentedClasses()
	 * @generated
	 * @ordered
	 */
	protected String persistInstrumentedClasses = PERSIST_INSTRUMENTED_CLASSES_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemporaryResultsType() <em>Temporary Results Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporaryResultsType()
	 * @generated
	 * @ordered
	 */
	protected static final InternalResultStorageType TEMPORARY_RESULTS_TYPE_EDEFAULT = InternalResultStorageType.IN_MEMORY;

	/**
	 * The cached value of the '{@link #getTemporaryResultsType() <em>Temporary Results Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemporaryResultsType()
	 * @generated
	 * @ordered
	 */
	protected InternalResultStorageType temporaryResultsType = TEMPORARY_RESULTS_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEntitiesToInstrument() <em>Entities To Instrument</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntitiesToInstrument()
	 * @generated
	 * @ordered
	 */
	protected EList<EntityToInstrument> entitiesToInstrument;

	/**
	 * The default value of the '{@link #isInstrumentUsingLongCounters() <em>Instrument Using Long Counters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentUsingLongCounters()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTRUMENT_USING_LONG_COUNTERS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInstrumentUsingLongCounters() <em>Instrument Using Long Counters</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentUsingLongCounters()
	 * @generated
	 * @ordered
	 */
	protected boolean instrumentUsingLongCounters = INSTRUMENT_USING_LONG_COUNTERS_EDEFAULT;

	/**
	 * The default value of the '{@link #isInstrumentUsingBasicBlocks() <em>Instrument Using Basic Blocks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentUsingBasicBlocks()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INSTRUMENT_USING_BASIC_BLOCKS_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isInstrumentUsingBasicBlocks() <em>Instrument Using Basic Blocks</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInstrumentUsingBasicBlocks()
	 * @generated
	 * @ordered
	 */
	protected boolean instrumentUsingBasicBlocks = INSTRUMENT_USING_BASIC_BLOCKS_EDEFAULT;

	/**
	 * The default value of the '{@link #isTraceAndIdentifyRequests() <em>Trace And Identify Requests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTraceAndIdentifyRequests()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTraceAndIdentifyRequests() <em>Trace And Identify Requests</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTraceAndIdentifyRequests()
	 * @generated
	 * @ordered
	 */
	protected boolean traceAndIdentifyRequests = TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAggregationExcludes() <em>Aggregation Excludes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationExcludes()
	 * @generated
	 * @ordered
	 */
	protected EList<Method> aggregationExcludes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InstrumentationProfileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InputPackage.Literals.INSTRUMENTATION_PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LogicalSet> getDefinedLogicalSets() {
		if (definedLogicalSets == null) {
			definedLogicalSets = new EObjectContainmentWithInverseEList<LogicalSet>(LogicalSet.class, this, InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS, InputPackage.LOGICAL_SET__INSTRUMENTATION_PROFILE);
		}
		return definedLogicalSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstrumentRecursively() {
		return instrumentRecursively;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentRecursively(boolean newInstrumentRecursively) {
		boolean oldInstrumentRecursively = instrumentRecursively;
		instrumentRecursively = newInstrumentRecursively;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY, oldInstrumentRecursively, instrumentRecursively));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAggregateInternalCallsTransparently() {
		return aggregateInternalCallsTransparently;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAggregateInternalCallsTransparently(boolean newAggregateInternalCallsTransparently) {
		boolean oldAggregateInternalCallsTransparently = aggregateInternalCallsTransparently;
		aggregateInternalCallsTransparently = newAggregateInternalCallsTransparently;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY, oldAggregateInternalCallsTransparently, aggregateInternalCallsTransparently));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AggregationType getAggregationType() {
		return aggregationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAggregationType(AggregationType newAggregationType) {
		AggregationType oldAggregationType = aggregationType;
		aggregationType = newAggregationType == null ? AGGREGATION_TYPE_EDEFAULT : newAggregationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE, oldAggregationType, aggregationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersistInstrumentedClasses() {
		return persistInstrumentedClasses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistInstrumentedClasses(String newPersistInstrumentedClasses) {
		String oldPersistInstrumentedClasses = persistInstrumentedClasses;
		persistInstrumentedClasses = newPersistInstrumentedClasses;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES, oldPersistInstrumentedClasses, persistInstrumentedClasses));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InternalResultStorageType getTemporaryResultsType() {
		return temporaryResultsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemporaryResultsType(InternalResultStorageType newTemporaryResultsType) {
		InternalResultStorageType oldTemporaryResultsType = temporaryResultsType;
		temporaryResultsType = newTemporaryResultsType == null ? TEMPORARY_RESULTS_TYPE_EDEFAULT : newTemporaryResultsType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE, oldTemporaryResultsType, temporaryResultsType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EntityToInstrument> getEntitiesToInstrument() {
		if (entitiesToInstrument == null) {
			entitiesToInstrument = new EObjectContainmentWithInverseEList<EntityToInstrument>(EntityToInstrument.class, this, InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT, InputPackage.ENTITY_TO_INSTRUMENT__INSTRUMENTATION_PROFILE);
		}
		return entitiesToInstrument;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfileRepository getIntrumentationProfileRepository() {
		if (eContainerFeatureID() != InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY) return null;
		return (InstrumentationProfileRepository)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIntrumentationProfileRepository(InstrumentationProfileRepository newIntrumentationProfileRepository, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newIntrumentationProfileRepository, InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntrumentationProfileRepository(InstrumentationProfileRepository newIntrumentationProfileRepository) {
		if (newIntrumentationProfileRepository != eInternalContainer() || (eContainerFeatureID() != InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY && newIntrumentationProfileRepository != null)) {
			if (EcoreUtil.isAncestor(this, newIntrumentationProfileRepository))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newIntrumentationProfileRepository != null)
				msgs = ((InternalEObject)newIntrumentationProfileRepository).eInverseAdd(this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE, InstrumentationProfileRepository.class, msgs);
			msgs = basicSetIntrumentationProfileRepository(newIntrumentationProfileRepository, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY, newIntrumentationProfileRepository, newIntrumentationProfileRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstrumentUsingLongCounters() {
		return instrumentUsingLongCounters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentUsingLongCounters(boolean newInstrumentUsingLongCounters) {
		boolean oldInstrumentUsingLongCounters = instrumentUsingLongCounters;
		instrumentUsingLongCounters = newInstrumentUsingLongCounters;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS, oldInstrumentUsingLongCounters, instrumentUsingLongCounters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInstrumentUsingBasicBlocks() {
		return instrumentUsingBasicBlocks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstrumentUsingBasicBlocks(boolean newInstrumentUsingBasicBlocks) {
		boolean oldInstrumentUsingBasicBlocks = instrumentUsingBasicBlocks;
		instrumentUsingBasicBlocks = newInstrumentUsingBasicBlocks;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS, oldInstrumentUsingBasicBlocks, instrumentUsingBasicBlocks));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTraceAndIdentifyRequests() {
		return traceAndIdentifyRequests;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTraceAndIdentifyRequests(boolean newTraceAndIdentifyRequests) {
		boolean oldTraceAndIdentifyRequests = traceAndIdentifyRequests;
		traceAndIdentifyRequests = newTraceAndIdentifyRequests;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS, oldTraceAndIdentifyRequests, traceAndIdentifyRequests));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Method> getAggregationExcludes() {
		if (aggregationExcludes == null) {
			aggregationExcludes = new EObjectContainmentEList<Method>(Method.class, this, InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES);
		}
		return aggregationExcludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDefinedLogicalSets()).basicAdd(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntitiesToInstrument()).basicAdd(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetIntrumentationProfileRepository((InstrumentationProfileRepository)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				return ((InternalEList<?>)getDefinedLogicalSets()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return ((InternalEList<?>)getEntitiesToInstrument()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return basicSetIntrumentationProfileRepository(null, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return ((InternalEList<?>)getAggregationExcludes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return eInternalContainer().eInverseRemove(this, InputPackage.INSTRUMENTATION_PROFILE_REPOSITORY__INSTRUMENTATION_PROFILE, InstrumentationProfileRepository.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				return getDefinedLogicalSets();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				return isInstrumentRecursively();
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY:
				return isAggregateInternalCallsTransparently();
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE:
				return getAggregationType();
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES:
				return getPersistInstrumentedClasses();
			case InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE:
				return getTemporaryResultsType();
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return getEntitiesToInstrument();
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return getIntrumentationProfileRepository();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				return isInstrumentUsingLongCounters();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				return isInstrumentUsingBasicBlocks();
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				return isTraceAndIdentifyRequests();
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return getAggregationExcludes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				getDefinedLogicalSets().clear();
				getDefinedLogicalSets().addAll((Collection<? extends LogicalSet>)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				setInstrumentRecursively((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY:
				setAggregateInternalCallsTransparently((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE:
				setAggregationType((AggregationType)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES:
				setPersistInstrumentedClasses((String)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE:
				setTemporaryResultsType((InternalResultStorageType)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				getEntitiesToInstrument().clear();
				getEntitiesToInstrument().addAll((Collection<? extends EntityToInstrument>)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				setIntrumentationProfileRepository((InstrumentationProfileRepository)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				setInstrumentUsingLongCounters((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				setInstrumentUsingBasicBlocks((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				setTraceAndIdentifyRequests((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				getAggregationExcludes().clear();
				getAggregationExcludes().addAll((Collection<? extends Method>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				getDefinedLogicalSets().clear();
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				setInstrumentRecursively(INSTRUMENT_RECURSIVELY_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY:
				setAggregateInternalCallsTransparently(AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE:
				setAggregationType(AGGREGATION_TYPE_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES:
				setPersistInstrumentedClasses(PERSIST_INSTRUMENTED_CLASSES_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE:
				setTemporaryResultsType(TEMPORARY_RESULTS_TYPE_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				getEntitiesToInstrument().clear();
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				setIntrumentationProfileRepository((InstrumentationProfileRepository)null);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				setInstrumentUsingLongCounters(INSTRUMENT_USING_LONG_COUNTERS_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				setInstrumentUsingBasicBlocks(INSTRUMENT_USING_BASIC_BLOCKS_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				setTraceAndIdentifyRequests(TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				getAggregationExcludes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
				return definedLogicalSets != null && !definedLogicalSets.isEmpty();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				return instrumentRecursively != INSTRUMENT_RECURSIVELY_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY:
				return aggregateInternalCallsTransparently != AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE:
				return aggregationType != AGGREGATION_TYPE_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES:
				return PERSIST_INSTRUMENTED_CLASSES_EDEFAULT == null ? persistInstrumentedClasses != null : !PERSIST_INSTRUMENTED_CLASSES_EDEFAULT.equals(persistInstrumentedClasses);
			case InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE:
				return temporaryResultsType != TEMPORARY_RESULTS_TYPE_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return entitiesToInstrument != null && !entitiesToInstrument.isEmpty();
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return getIntrumentationProfileRepository() != null;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				return instrumentUsingLongCounters != INSTRUMENT_USING_LONG_COUNTERS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				return instrumentUsingBasicBlocks != INSTRUMENT_USING_BASIC_BLOCKS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				return traceAndIdentifyRequests != TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return aggregationExcludes != null && !aggregationExcludes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (instrumentRecursively: ");
		result.append(instrumentRecursively);
		result.append(", aggregateInternalCallsTransparently: ");
		result.append(aggregateInternalCallsTransparently);
		result.append(", aggregationType: ");
		result.append(aggregationType);
		result.append(", persistInstrumentedClasses: ");
		result.append(persistInstrumentedClasses);
		result.append(", temporaryResultsType: ");
		result.append(temporaryResultsType);
		result.append(", instrumentUsingLongCounters: ");
		result.append(instrumentUsingLongCounters);
		result.append(", instrumentUsingBasicBlocks: ");
		result.append(instrumentUsingBasicBlocks);
		result.append(", traceAndIdentifyRequests: ");
		result.append(traceAndIdentifyRequests);
		result.append(')');
		return result.toString();
	}

} //InstrumentationProfileImpl
