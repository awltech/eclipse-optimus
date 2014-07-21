/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Comment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.emf.metamodels.properties.Comment#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getComment()
 * @model
 * @generated
 */
public interface Comment extends Content {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getComment_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link net.atos.optimus.emf.metamodels.properties.Comment#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Comment
