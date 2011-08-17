/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package edu.kit.ipd.sdq.bycounter.output;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java VM Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getNumberObservations <em>Number Observations</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getId <em>Id</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getName <em>Name</em>}</li>
 *   <li>{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands <em>Resource Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getJavaVMCall()
 * @model
 * @generated
 */
public interface JavaVMCall extends EObject {
	/**
	 * Returns the value of the '<em><b>Number Observations</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Number Observations</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Observations</em>' attribute.
	 * @see #setNumberObservations(long)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getJavaVMCall_NumberObservations()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getNumberObservations();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getNumberObservations <em>Number Observations</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Observations</em>' attribute.
	 * @see #getNumberObservations()
	 * @generated
	 */
	void setNumberObservations(long value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(byte)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getJavaVMCall_Id()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	byte getId();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(byte value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getJavaVMCall_Name()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Resource Demands</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getJavaVMCall <em>Java VM Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Demands</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Demands</em>' container reference.
	 * @see #setResourceDemands(ResourceDemands)
	 * @see edu.kit.ipd.sdq.bycounter.output.OutputPackage#getJavaVMCall_ResourceDemands()
	 * @see edu.kit.ipd.sdq.bycounter.output.ResourceDemands#getJavaVMCall
	 * @model opposite="javaVMCall" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ResourceDemands getResourceDemands();

	/**
	 * Sets the value of the '{@link edu.kit.ipd.sdq.bycounter.output.JavaVMCall#getResourceDemands <em>Resource Demands</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Demands</em>' container reference.
	 * @see #getResourceDemands()
	 * @generated
	 */
	void setResourceDemands(ResourceDemands value);

} // JavaVMCall
