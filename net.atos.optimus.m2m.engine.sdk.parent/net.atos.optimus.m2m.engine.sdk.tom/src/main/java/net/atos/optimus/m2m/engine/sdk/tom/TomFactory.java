/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage
 * @generated
 */
public interface TomFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TomFactory eINSTANCE = net.atos.optimus.m2m.engine.sdk.tom.impl.TomFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Transformation Sets</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformation Sets</em>'.
	 * @generated
	 */
	TransformationSets createTransformationSets();

	/**
	 * Returns a new object of class '<em>Transformation Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformation Set</em>'.
	 * @generated
	 */
	TransformationSet createTransformationSet();

	/**
	 * Returns a new object of class '<em>Transformation Set Extension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformation Set Extension</em>'.
	 * @generated
	 */
	TransformationSetExtension createTransformationSetExtension();

	/**
	 * Returns a new object of class '<em>Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformation</em>'.
	 * @generated
	 */
	Transformation createTransformation();

	/**
	 * Returns a new object of class '<em>Self Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Self Requirement</em>'.
	 * @generated
	 */
	SelfRequirement createSelfRequirement();

	/**
	 * Returns a new object of class '<em>Parent Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parent Requirement</em>'.
	 * @generated
	 */
	ParentRequirement createParentRequirement();

	/**
	 * Returns a new object of class '<em>Custom Requirement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Custom Requirement</em>'.
	 * @generated
	 */
	CustomRequirement createCustomRequirement();

	/**
	 * Returns a new object of class '<em>External Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Transformation</em>'.
	 * @generated
	 */
	ExternalTransformation createExternalTransformation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TomPackage getTomPackage();

} //TomFactory
