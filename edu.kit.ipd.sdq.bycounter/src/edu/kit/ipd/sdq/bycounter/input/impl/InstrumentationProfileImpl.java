/**
 */
package edu.kit.ipd.sdq.bycounter.input.impl;

import de.fzi.gast.functions.Method;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import edu.kit.ipd.sdq.bycounter.input.EntityToInstrument;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfileRepository;

import edu.kit.ipd.sdq.bycounter.input.LogicalSet;
import edu.kit.ipd.sdq.bycounter.input.util.InputValidator;
import java.util.Collection;

import java.util.Map;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Instrumentation Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentRecursively <em>Instrument Recursively</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getAggregationExcludes <em>Aggregation Excludes</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getEntitiesToInstrument <em>Entities To Instrument</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getIntrumentationProfileRepository <em>Intrumentation Profile Repository</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentUsingLongCounters <em>Instrument Using Long Counters</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isInstrumentUsingBasicBlocks <em>Instrument Using Basic Blocks</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getNumberCallGraphClassAnalyserThreads <em>Number Call Graph Class Analyser Threads</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isProvideJoinThreadsAbility <em>Provide Join Threads Ability</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getPersistInstrumentedClassesToOSPath <em>Persist Instrumented Classes To OS Path</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isProvideOnlineSectionActiveUpdates <em>Provide Online Section Active Updates</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#isTraceAndIdentifyRequests <em>Trace And Identify Requests</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.input.impl.InstrumentationProfileImpl#getLogicalSetExternalToClassLoader <em>Logical Set External To Class Loader</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstrumentationProfileImpl extends IdentifierImpl implements InstrumentationProfile {
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
	 * The cached value of the '{@link #getAggregationExcludes() <em>Aggregation Excludes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAggregationExcludes()
	 * @generated
	 * @ordered
	 */
	protected EList<Method> aggregationExcludes;

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
	 * The default value of the '{@link #getNumberCallGraphClassAnalyserThreads() <em>Number Call Graph Class Analyser Threads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberCallGraphClassAnalyserThreads()
	 * @generated
	 * @ordered
	 */
	protected static final Integer NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumberCallGraphClassAnalyserThreads() <em>Number Call Graph Class Analyser Threads</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberCallGraphClassAnalyserThreads()
	 * @generated
	 * @ordered
	 */
	protected Integer numberCallGraphClassAnalyserThreads = NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS_EDEFAULT;

	/**
	 * The default value of the '{@link #isProvideJoinThreadsAbility() <em>Provide Join Threads Ability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProvideJoinThreadsAbility()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROVIDE_JOIN_THREADS_ABILITY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isProvideJoinThreadsAbility() <em>Provide Join Threads Ability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProvideJoinThreadsAbility()
	 * @generated
	 * @ordered
	 */
	protected boolean provideJoinThreadsAbility = PROVIDE_JOIN_THREADS_ABILITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPersistInstrumentedClassesToOSPath() <em>Persist Instrumented Classes To OS Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistInstrumentedClassesToOSPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPersistInstrumentedClassesToOSPath() <em>Persist Instrumented Classes To OS Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPersistInstrumentedClassesToOSPath()
	 * @generated
	 * @ordered
	 */
	protected String persistInstrumentedClassesToOSPath = PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #isProvideOnlineSectionActiveUpdates() <em>Provide Online Section Active Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProvideOnlineSectionActiveUpdates()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isProvideOnlineSectionActiveUpdates() <em>Provide Online Section Active Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isProvideOnlineSectionActiveUpdates()
	 * @generated
	 * @ordered
	 */
	protected boolean provideOnlineSectionActiveUpdates = PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES_EDEFAULT;

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
	 * The cached value of the '{@link #getLogicalSetExternalToClassLoader() <em>Logical Set External To Class Loader</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLogicalSetExternalToClassLoader()
	 * @generated
	 * @ordered
	 */
	protected EList<LogicalSet> logicalSetExternalToClassLoader;

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
	public Integer getNumberCallGraphClassAnalyserThreads() {
		return numberCallGraphClassAnalyserThreads;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberCallGraphClassAnalyserThreads(Integer newNumberCallGraphClassAnalyserThreads) {
		Integer oldNumberCallGraphClassAnalyserThreads = numberCallGraphClassAnalyserThreads;
		numberCallGraphClassAnalyserThreads = newNumberCallGraphClassAnalyserThreads;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS, oldNumberCallGraphClassAnalyserThreads, numberCallGraphClassAnalyserThreads));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProvideJoinThreadsAbility() {
		return provideJoinThreadsAbility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvideJoinThreadsAbility(boolean newProvideJoinThreadsAbility) {
		boolean oldProvideJoinThreadsAbility = provideJoinThreadsAbility;
		provideJoinThreadsAbility = newProvideJoinThreadsAbility;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY, oldProvideJoinThreadsAbility, provideJoinThreadsAbility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPersistInstrumentedClassesToOSPath() {
		return persistInstrumentedClassesToOSPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPersistInstrumentedClassesToOSPath(String newPersistInstrumentedClassesToOSPath) {
		String oldPersistInstrumentedClassesToOSPath = persistInstrumentedClassesToOSPath;
		persistInstrumentedClassesToOSPath = newPersistInstrumentedClassesToOSPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH, oldPersistInstrumentedClassesToOSPath, persistInstrumentedClassesToOSPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isProvideOnlineSectionActiveUpdates() {
		return provideOnlineSectionActiveUpdates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvideOnlineSectionActiveUpdates(boolean newProvideOnlineSectionActiveUpdates) {
		boolean oldProvideOnlineSectionActiveUpdates = provideOnlineSectionActiveUpdates;
		provideOnlineSectionActiveUpdates = newProvideOnlineSectionActiveUpdates;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES, oldProvideOnlineSectionActiveUpdates, provideOnlineSectionActiveUpdates));
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
	public EList<LogicalSet> getLogicalSetExternalToClassLoader() {
		if (logicalSetExternalToClassLoader == null) {
			logicalSetExternalToClassLoader = new EObjectContainmentEList<LogicalSet>(LogicalSet.class, this, InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER);
		}
		return logicalSetExternalToClassLoader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean atLeastOneThread(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 InputValidator.DIAGNOSTIC_SOURCE,
						 InputValidator.INSTRUMENTATION_PROFILE__AT_LEAST_ONE_THREAD,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "atLeastOneThread", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
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
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return ((InternalEList<?>)getAggregationExcludes()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return ((InternalEList<?>)getEntitiesToInstrument()).basicRemove(otherEnd, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return basicSetIntrumentationProfileRepository(null, msgs);
			case InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER:
				return ((InternalEList<?>)getLogicalSetExternalToClassLoader()).basicRemove(otherEnd, msgs);
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
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				return isInstrumentRecursively();
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return getAggregationExcludes();
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return getEntitiesToInstrument();
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return getIntrumentationProfileRepository();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				return isInstrumentUsingLongCounters();
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				return isInstrumentUsingBasicBlocks();
			case InputPackage.INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS:
				return getNumberCallGraphClassAnalyserThreads();
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY:
				return isProvideJoinThreadsAbility();
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH:
				return getPersistInstrumentedClassesToOSPath();
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES:
				return isProvideOnlineSectionActiveUpdates();
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				return isTraceAndIdentifyRequests();
			case InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER:
				return getLogicalSetExternalToClassLoader();
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
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				setInstrumentRecursively((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				getAggregationExcludes().clear();
				getAggregationExcludes().addAll((Collection<? extends Method>)newValue);
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
			case InputPackage.INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS:
				setNumberCallGraphClassAnalyserThreads((Integer)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY:
				setProvideJoinThreadsAbility((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH:
				setPersistInstrumentedClassesToOSPath((String)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES:
				setProvideOnlineSectionActiveUpdates((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				setTraceAndIdentifyRequests((Boolean)newValue);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER:
				getLogicalSetExternalToClassLoader().clear();
				getLogicalSetExternalToClassLoader().addAll((Collection<? extends LogicalSet>)newValue);
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
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				setInstrumentRecursively(INSTRUMENT_RECURSIVELY_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				getAggregationExcludes().clear();
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
			case InputPackage.INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS:
				setNumberCallGraphClassAnalyserThreads(NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY:
				setProvideJoinThreadsAbility(PROVIDE_JOIN_THREADS_ABILITY_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH:
				setPersistInstrumentedClassesToOSPath(PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES:
				setProvideOnlineSectionActiveUpdates(PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				setTraceAndIdentifyRequests(TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT);
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER:
				getLogicalSetExternalToClassLoader().clear();
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
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
				return instrumentRecursively != INSTRUMENT_RECURSIVELY_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				return aggregationExcludes != null && !aggregationExcludes.isEmpty();
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
				return entitiesToInstrument != null && !entitiesToInstrument.isEmpty();
			case InputPackage.INSTRUMENTATION_PROFILE__INTRUMENTATION_PROFILE_REPOSITORY:
				return getIntrumentationProfileRepository() != null;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
				return instrumentUsingLongCounters != INSTRUMENT_USING_LONG_COUNTERS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
				return instrumentUsingBasicBlocks != INSTRUMENT_USING_BASIC_BLOCKS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS:
				return NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS_EDEFAULT == null ? numberCallGraphClassAnalyserThreads != null : !NUMBER_CALL_GRAPH_CLASS_ANALYSER_THREADS_EDEFAULT.equals(numberCallGraphClassAnalyserThreads);
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_JOIN_THREADS_ABILITY:
				return provideJoinThreadsAbility != PROVIDE_JOIN_THREADS_ABILITY_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH:
				return PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH_EDEFAULT == null ? persistInstrumentedClassesToOSPath != null : !PERSIST_INSTRUMENTED_CLASSES_TO_OS_PATH_EDEFAULT.equals(persistInstrumentedClassesToOSPath);
			case InputPackage.INSTRUMENTATION_PROFILE__PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES:
				return provideOnlineSectionActiveUpdates != PROVIDE_ONLINE_SECTION_ACTIVE_UPDATES_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				return traceAndIdentifyRequests != TRACE_AND_IDENTIFY_REQUESTS_EDEFAULT;
			case InputPackage.INSTRUMENTATION_PROFILE__LOGICAL_SET_EXTERNAL_TO_CLASS_LOADER:
				return logicalSetExternalToClassLoader != null && !logicalSetExternalToClassLoader.isEmpty();
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
		result.append(", instrumentUsingLongCounters: ");
		result.append(instrumentUsingLongCounters);
		result.append(", instrumentUsingBasicBlocks: ");
		result.append(instrumentUsingBasicBlocks);
		result.append(", numberCallGraphClassAnalyserThreads: ");
		result.append(numberCallGraphClassAnalyserThreads);
		result.append(", provideJoinThreadsAbility: ");
		result.append(provideJoinThreadsAbility);
		result.append(", persistInstrumentedClassesToOSPath: ");
		result.append(persistInstrumentedClassesToOSPath);
		result.append(", provideOnlineSectionActiveUpdates: ");
		result.append(provideOnlineSectionActiveUpdates);
		result.append(", traceAndIdentifyRequests: ");
		result.append(traceAndIdentifyRequests);
		result.append(')');
		return result.toString();
	}

} //InstrumentationProfileImpl
