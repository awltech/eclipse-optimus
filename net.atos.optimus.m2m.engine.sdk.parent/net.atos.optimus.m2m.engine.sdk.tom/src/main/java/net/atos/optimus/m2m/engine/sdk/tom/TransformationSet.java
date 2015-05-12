/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getName <em>Name</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getDescription <em>Description</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getTransformations <em>Transformations</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#isPrivate <em>Private</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet()
 * @model
 * @generated
 */
public interface TransformationSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet_Name()
	 * @model default="" id="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet_Description()
	 * @model default=""
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet_Implementation()
	 * @model default=""
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Transformations</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.m2m.engine.sdk.tom.Transformation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformations</em>' containment reference list.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet_Transformations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Transformation> getTransformations();

	/**
	 * Returns the value of the '<em><b>Private</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Private</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Private</em>' attribute.
	 * @see #setPrivate(boolean)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSet_Private()
	 * @model default="false"
	 * @generated
	 */
	boolean isPrivate();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#isPrivate <em>Private</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Private</em>' attribute.
	 * @see #isPrivate()
	 * @generated
	 */
	void setPrivate(boolean value);

} // TransformationSet
