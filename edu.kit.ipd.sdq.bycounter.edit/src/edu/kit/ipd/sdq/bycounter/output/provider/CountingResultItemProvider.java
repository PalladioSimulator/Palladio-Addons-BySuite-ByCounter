/**
 */
package edu.kit.ipd.sdq.bycounter.output.provider;


import edu.kit.ipd.sdq.bycounter.output.CountingResult;
import edu.kit.ipd.sdq.bycounter.output.OutputPackage;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link edu.kit.ipd.sdq.bycounter.output.CountingResult} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class CountingResultItemProvider
	extends ItemProviderAdapter
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
	public CountingResultItemProvider(AdapterFactory adapterFactory) {
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

			addCallerIdPropertyDescriptor(object);
			addMethodInvocationStartTimePropertyDescriptor(object);
			addReportingTimePropertyDescriptor(object);
			addObservedElementPropertyDescriptor(object);
			addOpcodeCountsPropertyDescriptor(object);
			addMethodIdPropertyDescriptor(object);
			addQualifiedMethodNamePropertyDescriptor(object);
			addArrayCreationCountsPropertyDescriptor(object);
			addMethodCallCountsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Caller Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCallerIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_callerId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_callerId_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__CALLER_ID,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Method Invocation Start Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMethodInvocationStartTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_methodInvocationStartTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_methodInvocationStartTime_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__METHOD_INVOCATION_START_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Reporting Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReportingTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_reportingTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_reportingTime_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__REPORTING_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Observed Element feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addObservedElementPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_observedElement_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_observedElement_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__OBSERVED_ELEMENT,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Opcode Counts feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOpcodeCountsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_opcodeCounts_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_opcodeCounts_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__OPCODE_COUNTS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Method Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMethodIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_methodId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_methodId_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__METHOD_ID,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Qualified Method Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addQualifiedMethodNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_qualifiedMethodName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_qualifiedMethodName_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__QUALIFIED_METHOD_NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Array Creation Counts feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addArrayCreationCountsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_arrayCreationCounts_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_arrayCreationCounts_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__ARRAY_CREATION_COUNTS,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Method Call Counts feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMethodCallCountsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_CountingResult_methodCallCounts_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_CountingResult_methodCallCounts_feature", "_UI_CountingResult_type"),
				 OutputPackage.Literals.COUNTING_RESULT__METHOD_CALL_COUNTS,
				 true,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This returns CountingResult.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/CountingResult"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((CountingResult)object).getQualifiedMethodName();
		return label == null || label.length() == 0 ?
			getString("_UI_CountingResult_type") :
			getString("_UI_CountingResult_type") + " " + label;
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

		switch (notification.getFeatureID(CountingResult.class)) {
			case OutputPackage.COUNTING_RESULT__CALLER_ID:
			case OutputPackage.COUNTING_RESULT__METHOD_INVOCATION_START_TIME:
			case OutputPackage.COUNTING_RESULT__REPORTING_TIME:
			case OutputPackage.COUNTING_RESULT__OBSERVED_ELEMENT:
			case OutputPackage.COUNTING_RESULT__OPCODE_COUNTS:
			case OutputPackage.COUNTING_RESULT__QUALIFIED_METHOD_NAME:
			case OutputPackage.COUNTING_RESULT__ARRAY_CREATION_COUNTS:
			case OutputPackage.COUNTING_RESULT__METHOD_CALL_COUNTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
