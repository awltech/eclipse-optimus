/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation Sets</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSets <em>Transformation Sets</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSetExtensions <em>Transformation Set Extensions</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getExternalTransformations <em>External Transformations</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSets()
 * @model
 * @generated
 */
public interface TransformationSets extends EObject {
	/**
	 * Returns the value of the '<em><b>Transformation Sets</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformation Sets</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformation Sets</em>' containment reference list.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSets_TransformationSets()
	 * @model containment="true"
	 * @generated
	 */
	EList<TransformationSet> getTransformationSets();

	/**
	 * Returns the value of the '<em><b>Transformation Set Extensions</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transformation Set Extensions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transformation Set Extensions</em>' containment reference list.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSets_TransformationSetExtensions()
	 * @model containment="true"
	 * @generated
	 */
	EList<TransformationSetExtension> getTransformationSetExtensions();

	/**
	 * Returns the value of the '<em><b>External Transformations</b></em>' containment reference list.
	 * The list contents are of type {@link net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Transformations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Transformations</em>' containment reference list.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getTransformationSets_ExternalTransformations()
	 * @model containment="true"
	 * @generated
	 */
	EList<ExternalTransformation> getExternalTransformations();

} // TransformationSets
