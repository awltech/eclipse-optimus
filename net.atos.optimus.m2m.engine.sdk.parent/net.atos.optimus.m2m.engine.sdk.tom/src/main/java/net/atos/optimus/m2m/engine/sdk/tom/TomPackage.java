/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomFactory
 * @model kind="package"
 * @generated
 */
public interface TomPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tom";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/emf/net/atos/optimus/";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "net.atos.optimus.m2m.engine.sdk.tom";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TomPackage eINSTANCE = net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl.init();

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl <em>Transformation Sets</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSets()
	 * @generated
	 */
	int TRANSFORMATION_SETS = 0;

	/**
	 * The feature id for the '<em><b>Transformation Sets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SETS__TRANSFORMATION_SETS = 0;

	/**
	 * The feature id for the '<em><b>Transformation Set Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS = 1;

	/**
	 * The feature id for the '<em><b>External Transformations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS = 2;

	/**
	 * The number of structural features of the '<em>Transformation Sets</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SETS_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Transformation Sets</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SETS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetImpl <em>Transformation Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSet()
	 * @generated
	 */
	int TRANSFORMATION_SET = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET__IMPLEMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Transformations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET__TRANSFORMATIONS = 3;

	/**
	 * The feature id for the '<em><b>Private</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET__PRIVATE = 4;

	/**
	 * The number of structural features of the '<em>Transformation Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Transformation Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl <em>Transformation Set Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSetExtension()
	 * @generated
	 */
	int TRANSFORMATION_SET_EXTENSION = 2;

	/**
	 * The feature id for the '<em><b>Transformation Set Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID = 0;

	/**
	 * The feature id for the '<em><b>Transformations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS = 1;

	/**
	 * The number of structural features of the '<em>Transformation Set Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_EXTENSION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Transformation Set Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_SET_EXTENSION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationReferenceImpl <em>Transformation Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationReferenceImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationReference()
	 * @generated
	 */
	int TRANSFORMATION_REFERENCE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_REFERENCE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Transformation Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Transformation Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationImpl <em>Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformation()
	 * @generated
	 */
	int TRANSFORMATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__NAME = TRANSFORMATION_REFERENCE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__DESCRIPTION = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__FACTORY = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__PRIORITY = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Requirements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__REQUIREMENTS = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_FEATURE_COUNT = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_OPERATION_COUNT = TRANSFORMATION_REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.RequirementImpl <em>Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.RequirementImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getRequirement()
	 * @generated
	 */
	int REQUIREMENT = 4;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT__REFERENCE = 0;

	/**
	 * The number of structural features of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REQUIREMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.SelfRequirementImpl <em>Self Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.SelfRequirementImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getSelfRequirement()
	 * @generated
	 */
	int SELF_REQUIREMENT = 5;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REQUIREMENT__REFERENCE = REQUIREMENT__REFERENCE;

	/**
	 * The number of structural features of the '<em>Self Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Self Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SELF_REQUIREMENT_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.ParentRequirementImpl <em>Parent Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.ParentRequirementImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getParentRequirement()
	 * @generated
	 */
	int PARENT_REQUIREMENT = 6;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_REQUIREMENT__REFERENCE = REQUIREMENT__REFERENCE;

	/**
	 * The number of structural features of the '<em>Parent Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parent Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARENT_REQUIREMENT_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.CustomRequirementImpl <em>Custom Requirement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.CustomRequirementImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getCustomRequirement()
	 * @generated
	 */
	int CUSTOM_REQUIREMENT = 7;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_REQUIREMENT__REFERENCE = REQUIREMENT__REFERENCE;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_REQUIREMENT__IMPLEMENTATION = REQUIREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_REQUIREMENT_FEATURE_COUNT = REQUIREMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Custom Requirement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_REQUIREMENT_OPERATION_COUNT = REQUIREMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.ExternalTransformationImpl <em>External Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.ExternalTransformationImpl
	 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getExternalTransformation()
	 * @generated
	 */
	int EXTERNAL_TRANSFORMATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_TRANSFORMATION__NAME = TRANSFORMATION_REFERENCE__NAME;

	/**
	 * The number of structural features of the '<em>External Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_TRANSFORMATION_FEATURE_COUNT = TRANSFORMATION_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>External Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_TRANSFORMATION_OPERATION_COUNT = TRANSFORMATION_REFERENCE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets <em>Transformation Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation Sets</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSets
	 * @generated
	 */
	EClass getTransformationSets();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSets <em>Transformation Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformation Sets</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSets()
	 * @see #getTransformationSets()
	 * @generated
	 */
	EReference getTransformationSets_TransformationSets();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSetExtensions <em>Transformation Set Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformation Set Extensions</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getTransformationSetExtensions()
	 * @see #getTransformationSets()
	 * @generated
	 */
	EReference getTransformationSets_TransformationSetExtensions();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getExternalTransformations <em>External Transformations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>External Transformations</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSets#getExternalTransformations()
	 * @see #getTransformationSets()
	 * @generated
	 */
	EReference getTransformationSets_ExternalTransformations();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet <em>Transformation Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation Set</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet
	 * @generated
	 */
	EClass getTransformationSet();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getName()
	 * @see #getTransformationSet()
	 * @generated
	 */
	EAttribute getTransformationSet_Name();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getDescription()
	 * @see #getTransformationSet()
	 * @generated
	 */
	EAttribute getTransformationSet_Description();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getImplementation()
	 * @see #getTransformationSet()
	 * @generated
	 */
	EAttribute getTransformationSet_Implementation();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getTransformations <em>Transformations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformations</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#getTransformations()
	 * @see #getTransformationSet()
	 * @generated
	 */
	EReference getTransformationSet_Transformations();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#isPrivate <em>Private</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Private</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet#isPrivate()
	 * @see #getTransformationSet()
	 * @generated
	 */
	EAttribute getTransformationSet_Private();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension <em>Transformation Set Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation Set Extension</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension
	 * @generated
	 */
	EClass getTransformationSetExtension();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformationSetId <em>Transformation Set Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Transformation Set Id</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformationSetId()
	 * @see #getTransformationSetExtension()
	 * @generated
	 */
	EAttribute getTransformationSetExtension_TransformationSetId();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformations <em>Transformations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Transformations</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension#getTransformations()
	 * @see #getTransformationSetExtension()
	 * @generated
	 */
	EReference getTransformationSetExtension_Transformations();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation
	 * @generated
	 */
	EClass getTransformation();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation#getDescription()
	 * @see #getTransformation()
	 * @generated
	 */
	EAttribute getTransformation_Description();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factory</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation#getFactory()
	 * @see #getTransformation()
	 * @generated
	 */
	EAttribute getTransformation_Factory();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation#getPriority()
	 * @see #getTransformation()
	 * @generated
	 */
	EAttribute getTransformation_Priority();

	/**
	 * Returns the meta object for the containment reference list '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation#getRequirements <em>Requirements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Requirements</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation#getRequirements()
	 * @see #getTransformation()
	 * @generated
	 */
	EReference getTransformation_Requirements();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Requirement</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Requirement
	 * @generated
	 */
	EClass getRequirement();

	/**
	 * Returns the meta object for the reference '{@link net.atos.optimus.m2m.engine.sdk.tom.Requirement#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reference</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Requirement#getReference()
	 * @see #getRequirement()
	 * @generated
	 */
	EReference getRequirement_Reference();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement <em>Self Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Self Requirement</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement
	 * @generated
	 */
	EClass getSelfRequirement();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement <em>Parent Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parent Requirement</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement
	 * @generated
	 */
	EClass getParentRequirement();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement <em>Custom Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Requirement</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement
	 * @generated
	 */
	EClass getCustomRequirement();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement#getImplementation()
	 * @see #getCustomRequirement()
	 * @generated
	 */
	EAttribute getCustomRequirement_Implementation();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationReference <em>Transformation Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation Reference</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationReference
	 * @generated
	 */
	EClass getTransformationReference();

	/**
	 * Returns the meta object for the attribute '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationReference#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationReference#getName()
	 * @see #getTransformationReference()
	 * @generated
	 */
	EAttribute getTransformationReference_Name();

	/**
	 * Returns the meta object for class '{@link net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation <em>External Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Transformation</em>'.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation
	 * @generated
	 */
	EClass getExternalTransformation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TomFactory getTomFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl <em>Transformation Sets</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSets()
		 * @generated
		 */
		EClass TRANSFORMATION_SETS = eINSTANCE.getTransformationSets();

		/**
		 * The meta object literal for the '<em><b>Transformation Sets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_SETS__TRANSFORMATION_SETS = eINSTANCE.getTransformationSets_TransformationSets();

		/**
		 * The meta object literal for the '<em><b>Transformation Set Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS = eINSTANCE.getTransformationSets_TransformationSetExtensions();

		/**
		 * The meta object literal for the '<em><b>External Transformations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS = eINSTANCE.getTransformationSets_ExternalTransformations();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetImpl <em>Transformation Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSet()
		 * @generated
		 */
		EClass TRANSFORMATION_SET = eINSTANCE.getTransformationSet();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_SET__NAME = eINSTANCE.getTransformationSet_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_SET__DESCRIPTION = eINSTANCE.getTransformationSet_Description();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_SET__IMPLEMENTATION = eINSTANCE.getTransformationSet_Implementation();

		/**
		 * The meta object literal for the '<em><b>Transformations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_SET__TRANSFORMATIONS = eINSTANCE.getTransformationSet_Transformations();

		/**
		 * The meta object literal for the '<em><b>Private</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_SET__PRIVATE = eINSTANCE.getTransformationSet_Private();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl <em>Transformation Set Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationSetExtension()
		 * @generated
		 */
		EClass TRANSFORMATION_SET_EXTENSION = eINSTANCE.getTransformationSetExtension();

		/**
		 * The meta object literal for the '<em><b>Transformation Set Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID = eINSTANCE.getTransformationSetExtension_TransformationSetId();

		/**
		 * The meta object literal for the '<em><b>Transformations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS = eINSTANCE.getTransformationSetExtension_Transformations();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationImpl <em>Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformation()
		 * @generated
		 */
		EClass TRANSFORMATION = eINSTANCE.getTransformation();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION__DESCRIPTION = eINSTANCE.getTransformation_Description();

		/**
		 * The meta object literal for the '<em><b>Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION__FACTORY = eINSTANCE.getTransformation_Factory();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION__PRIORITY = eINSTANCE.getTransformation_Priority();

		/**
		 * The meta object literal for the '<em><b>Requirements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION__REQUIREMENTS = eINSTANCE.getTransformation_Requirements();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.RequirementImpl <em>Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.RequirementImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getRequirement()
		 * @generated
		 */
		EClass REQUIREMENT = eINSTANCE.getRequirement();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REQUIREMENT__REFERENCE = eINSTANCE.getRequirement_Reference();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.SelfRequirementImpl <em>Self Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.SelfRequirementImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getSelfRequirement()
		 * @generated
		 */
		EClass SELF_REQUIREMENT = eINSTANCE.getSelfRequirement();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.ParentRequirementImpl <em>Parent Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.ParentRequirementImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getParentRequirement()
		 * @generated
		 */
		EClass PARENT_REQUIREMENT = eINSTANCE.getParentRequirement();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.CustomRequirementImpl <em>Custom Requirement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.CustomRequirementImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getCustomRequirement()
		 * @generated
		 */
		EClass CUSTOM_REQUIREMENT = eINSTANCE.getCustomRequirement();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOM_REQUIREMENT__IMPLEMENTATION = eINSTANCE.getCustomRequirement_Implementation();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationReferenceImpl <em>Transformation Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationReferenceImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getTransformationReference()
		 * @generated
		 */
		EClass TRANSFORMATION_REFERENCE = eINSTANCE.getTransformationReference();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_REFERENCE__NAME = eINSTANCE.getTransformationReference_Name();

		/**
		 * The meta object literal for the '{@link net.atos.optimus.m2m.engine.sdk.tom.impl.ExternalTransformationImpl <em>External Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.ExternalTransformationImpl
		 * @see net.atos.optimus.m2m.engine.sdk.tom.impl.TomPackageImpl#getExternalTransformation()
		 * @generated
		 */
		EClass EXTERNAL_TRANSFORMATION = eINSTANCE.getExternalTransformation();

	}

} //TomPackage
