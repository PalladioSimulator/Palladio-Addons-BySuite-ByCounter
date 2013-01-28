/**
 */
package edu.kit.ipd.sdq.bycounter.output.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see edu.kit.ipd.sdq.bycounter.output.util.OutputResourceFactoryImpl
 * @generated
 */
public class OutputResourceImpl extends XMLResourceImpl {
	/** Map for XML name features used to optimize loading performance. */
	private HashMap<Object,Object> xmlNameToFeatureMap;

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated not
	 */
	public OutputResourceImpl(URI uri) {
		super(uri);
		this.xmlNameToFeatureMap = new HashMap<Object,Object>();
		
		this.setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		this.defaultLoadOptions = createDefaultLoadOptions();
	}

	/**
	 * Create default load options that speed up loading of gast models.
	 * @return The generated load options.
	 * @generated NOT
	 */
	public Map<Object, Object> createDefaultLoadOptions() {
		Map<Object, Object> loadOptions = super.getDefaultLoadOptions();
		// Options specified by the user on a load command are automatically 
		// merged into the map that is used for loading by the super class.
		// Therefore we can put our default options without checks for 
		// existing values.
		loadOptions.put(XMIResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
		loadOptions.put(XMIResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
		loadOptions.put(XMIResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
		loadOptions.put(XMIResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());		
		loadOptions.put(XMIResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, xmlNameToFeatureMap);
		return loadOptions;
	}
	
} //OutputResourceImpl
