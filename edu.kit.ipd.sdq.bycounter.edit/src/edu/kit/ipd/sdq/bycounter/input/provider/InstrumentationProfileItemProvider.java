/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.input.provider;


import de.fzi.gast.functions.functionsFactory;
import de.uka.ipd.sdq.identifier.provider.IdentifierItemProvider;

import edu.kit.ipd.sdq.bycounter.input.InputFactory;
import edu.kit.ipd.sdq.bycounter.input.InputPackage;
import edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile;

import edu.kit.ipd.sdq.bycounter.output.provider.ByCounterEditPlugin;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link edu.kit.ipd.sdq.bycounter.input.InstrumentationProfile} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class InstrumentationProfileItemProvider
	extends IdentifierItemProvider
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InstrumentationProfileItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addInstrumentRecursivelyPropertyDescriptor(object);
			addAggregateInternalCallsTransparentlyPropertyDescriptor(object);
			addAggregationTypePropertyDescriptor(object);
			addPersistInstrumentedClassesPropertyDescriptor(object);
			addTemporaryResultsTypePropertyDescriptor(object);
			addInstrumentUsingLongCountersPropertyDescriptor(object);
			addInstrumentUsingBasicBlocksPropertyDescriptor(object);
			addTraceAndIdentifyRequestsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Instrument Recursively feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInstrumentRecursivelyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_instrumentRecursively_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_instrumentRecursively_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Aggregate Internal Calls Transparently feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAggregateInternalCallsTransparentlyPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_aggregateInternalCallsTransparently_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_aggregateInternalCallsTransparently_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Aggregation Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addAggregationTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_aggregationType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_aggregationType_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Persist Instrumented Classes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPersistInstrumentedClassesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_persistInstrumentedClasses_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_persistInstrumentedClasses_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Temporary Results Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTemporaryResultsTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_temporaryResultsType_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_temporaryResultsType_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Instrument Using Long Counters feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInstrumentUsingLongCountersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_instrumentUsingLongCounters_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_instrumentUsingLongCounters_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Instrument Using Basic Blocks feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInstrumentUsingBasicBlocksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_instrumentUsingBasicBlocks_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_instrumentUsingBasicBlocks_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Trace And Identify Requests feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTraceAndIdentifyRequestsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_InstrumentationProfile_traceAndIdentifyRequests_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_InstrumentationProfile_traceAndIdentifyRequests_feature", "_UI_InstrumentationProfile_type"),
				 InputPackage.Literals.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(InputPackage.Literals.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS);
			childrenFeatures.add(InputPackage.Literals.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT);
			childrenFeatures.add(InputPackage.Literals.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns InstrumentationProfile.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/InstrumentationProfile"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((InstrumentationProfile)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_InstrumentationProfile_type") :
			getString("_UI_InstrumentationProfile_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(InstrumentationProfile.class)) {
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_RECURSIVELY:
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATE_INTERNAL_CALLS_TRANSPARENTLY:
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_TYPE:
			case InputPackage.INSTRUMENTATION_PROFILE__PERSIST_INSTRUMENTED_CLASSES:
			case InputPackage.INSTRUMENTATION_PROFILE__TEMPORARY_RESULTS_TYPE:
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_LONG_COUNTERS:
			case InputPackage.INSTRUMENTATION_PROFILE__INSTRUMENT_USING_BASIC_BLOCKS:
			case InputPackage.INSTRUMENTATION_PROFILE__TRACE_AND_IDENTIFY_REQUESTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case InputPackage.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS:
			case InputPackage.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT:
			case InputPackage.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(InputPackage.Literals.INSTRUMENTATION_PROFILE__DEFINED_LOGICAL_SETS,
				 InputFactory.eINSTANCE.createLogicalSet()));

		newChildDescriptors.add
			(createChildParameter
				(InputPackage.Literals.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT,
				 InputFactory.eINSTANCE.createInstrumentedCodeArea()));

		newChildDescriptors.add
			(createChildParameter
				(InputPackage.Literals.INSTRUMENTATION_PROFILE__ENTITIES_TO_INSTRUMENT,
				 InputFactory.eINSTANCE.createInstrumentedMethod()));

		newChildDescriptors.add
			(createChildParameter
				(InputPackage.Literals.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES,
				 functionsFactory.eINSTANCE.createMethod()));

		newChildDescriptors.add
			(createChildParameter
				(InputPackage.Literals.INSTRUMENTATION_PROFILE__AGGREGATION_EXCLUDES,
				 functionsFactory.eINSTANCE.createGenericMethod()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return ByCounterEditPlugin.INSTANCE;
	}

}
