/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Set Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformationSetId <em>Transformation Set Id</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformations <em>Transformations</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSetExtension()
 * @model
 * @generated
 */
public interface TransformationSetExtension extends EObject {
	/**
	 * Returns the value of the '<em><b>Transformation Set Id</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformation Set Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformation Set Id</em>' attribute.
	 * @see #setTransformationSetId(String)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSetExtension_TransformationSetId()
	 * @model default=""
	 * @generated
	 */
	String getTransformationSetId();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformationSetId <em>Transformation Set Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Set Id</em>' attribute.
	 * @see #getTransformationSetId()
	 * @generated
	 */
	void setTransformationSetId(String value);

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
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSetExtension_Transformations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Transformation> getTransformations();

} // TransformationSetExtension
