/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.atos.optimus.emf.metamodels.properties;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pair</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.emf.metamodels.properties.Pair#getKey <em>Key</em>}</li>
 *   <li>{@link net.atos.optimus.emf.metamodels.properties.Pair#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getPair()
 * @model
 * @generated
 */
public interface Pair extends Content {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getPair_Key()
	 * @model required="true"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link net.atos.optimus.emf.metamodels.properties.Pair#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

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
	 * @see net.atos.optimus.emf.metamodels.properties.PropertiesPackage#getPair_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link net.atos.optimus.emf.metamodels.properties.Pair#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Pair
