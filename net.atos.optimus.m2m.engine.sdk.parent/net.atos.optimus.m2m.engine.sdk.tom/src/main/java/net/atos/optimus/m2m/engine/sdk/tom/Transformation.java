/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getDescription <em>Description</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getFactory <em>Factory</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getPriority <em>Priority</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getRequirements <em>Requirements</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends TransformationReference {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformation_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory</em>' attribute.
	 * @see #setFactory(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformation_Factory()
	 * @model
	 * @generated
	 */
	String getFactory();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getFactory <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' attribute.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformation_Priority()
	 * @model default="0"
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

	/**
	 * Returns the value of the '<em><b>Requirements</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.m2m.engine.sdk.tom.Requirement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requirements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requirements</em>' containment reference list.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformation_Requirements()
	 * @model containment="true"
	 * @generated
	 */
	EList<Requirement> getRequirements();

} // Transformation
