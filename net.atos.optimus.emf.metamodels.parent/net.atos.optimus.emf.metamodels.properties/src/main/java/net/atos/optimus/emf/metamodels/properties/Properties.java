/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Properties</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.emf.metamodels.properties.Properties#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getProperties()
 * @model
 * @generated
 */
public interface Properties extends EObject {
	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.emf.metamodels.properties.Content}.
	 * It is bidirectional and its opposite is '{@link net.atos.optimus.emf.metamodels.properties.Content#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contents</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getProperties_Contents()
	 * @see net.atos.optimus.emf.metamodels.properties.Content#getProperties
	 * @model opposite="properties" containment="true"
	 * @generated
	 */
	EList<Content> getContents();

} // Properties
