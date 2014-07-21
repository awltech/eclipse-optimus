/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.emf.metamodels.properties.Content#getProperties <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getContent()
 * @model
 * @generated
 */
public interface Content extends EObject {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link net.atos.optimus.emf.metamodels.properties.Properties#getContents <em>Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Properties</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' container reference.
	 * @see #setProperties(Properties)
	 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getContent_Properties()
	 * @see net.atos.optimus.emf.metamodels.properties.Properties#getContents
	 * @model opposite="contents" transient="false"
	 * @generated
	 */
	Properties getProperties();

	/**
	 * Sets the value of the '{@link net.atos.optimus.emf.metamodels.properties.Content#getProperties <em>Properties</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Properties</em>' container reference.
	 * @see #getProperties()
	 * @generated
	 */
	void setProperties(Properties value);

} // Content
