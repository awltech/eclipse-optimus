/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getCustomRequirement()
 * @model
 * @generated
 */
public interface CustomRequirement extends Requirement {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getCustomRequirement_Implementation()
	 * @model
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

} // CustomRequirement
