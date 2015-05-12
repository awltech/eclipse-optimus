/**
 */
package net.atos.optimus.m2m.engine.sdk.tom.impl;

import net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation;
import net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;
import net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement;
import net.atos.optimus.m2m.engine.sdk.tom.TomFactory;
import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.Transformation;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSet;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSets;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TomPackageImpl extends EPackageImpl implements TomPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationSetsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationSetExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass requirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass selfRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parentRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customRequirementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass transformationReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalTransformationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TomPackageImpl() {
		super(eNS_URI, TomFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TomPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TomPackage init() {
		if (isInited) return (TomPackage)EPackage.Registry.INSTANCE.getEPackage(TomPackage.eNS_URI);

		// Obtain or create and register package
		TomPackageImpl theTomPackage = (TomPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TomPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TomPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTomPackage.createPackageContents();

		// Initialize created meta-data
		theTomPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTomPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TomPackage.eNS_URI, theTomPackage);
		return theTomPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformationSets() {
		return transformationSetsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationSets_TransformationSets() {
		return (EReference)transformationSetsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationSets_TransformationSetExtensions() {
		return (EReference)transformationSetsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationSets_ExternalTransformations() {
		return (EReference)transformationSetsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformationSet() {
		return transformationSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationSet_Name() {
		return (EAttribute)transformationSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationSet_Description() {
		return (EAttribute)transformationSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationSet_Implementation() {
		return (EAttribute)transformationSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationSet_Transformations() {
		return (EReference)transformationSetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationSet_Private() {
		return (EAttribute)transformationSetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformationSetExtension() {
		return transformationSetExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationSetExtension_TransformationSetId() {
		return (EAttribute)transformationSetExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformationSetExtension_Transformations() {
		return (EReference)transformationSetExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformation() {
		return transformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformation_Description() {
		return (EAttribute)transformationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformation_Factory() {
		return (EAttribute)transformationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformation_Priority() {
		return (EAttribute)transformationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTransformation_Requirements() {
		return (EReference)transformationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRequirement() {
		return requirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRequirement_Reference() {
		return (EReference)requirementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSelfRequirement() {
		return selfRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParentRequirement() {
		return parentRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomRequirement() {
		return customRequirementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomRequirement_Implementation() {
		return (EAttribute)customRequirementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTransformationReference() {
		return transformationReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTransformationReference_Name() {
		return (EAttribute)transformationReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalTransformation() {
		return externalTransformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TomFactory getTomFactory() {
		return (TomFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		transformationSetsEClass = createEClass(TRANSFORMATION_SETS);
		createEReference(transformationSetsEClass, TRANSFORMATION_SETS__TRANSFORMATION_SETS);
		createEReference(transformationSetsEClass, TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS);
		createEReference(transformationSetsEClass, TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS);

		transformationSetEClass = createEClass(TRANSFORMATION_SET);
		createEAttribute(transformationSetEClass, TRANSFORMATION_SET__NAME);
		createEAttribute(transformationSetEClass, TRANSFORMATION_SET__DESCRIPTION);
		createEAttribute(transformationSetEClass, TRANSFORMATION_SET__IMPLEMENTATION);
		createEReference(transformationSetEClass, TRANSFORMATION_SET__TRANSFORMATIONS);
		createEAttribute(transformationSetEClass, TRANSFORMATION_SET__PRIVATE);

		transformationSetExtensionEClass = createEClass(TRANSFORMATION_SET_EXTENSION);
		createEAttribute(transformationSetExtensionEClass, TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID);
		createEReference(transformationSetExtensionEClass, TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS);

		transformationEClass = createEClass(TRANSFORMATION);
		createEAttribute(transformationEClass, TRANSFORMATION__DESCRIPTION);
		createEAttribute(transformationEClass, TRANSFORMATION__FACTORY);
		createEAttribute(transformationEClass, TRANSFORMATION__PRIORITY);
		createEReference(transformationEClass, TRANSFORMATION__REQUIREMENTS);

		requirementEClass = createEClass(REQUIREMENT);
		createEReference(requirementEClass, REQUIREMENT__REFERENCE);

		selfRequirementEClass = createEClass(SELF_REQUIREMENT);

		parentRequirementEClass = createEClass(PARENT_REQUIREMENT);

		customRequirementEClass = createEClass(CUSTOM_REQUIREMENT);
		createEAttribute(customRequirementEClass, CUSTOM_REQUIREMENT__IMPLEMENTATION);

		transformationReferenceEClass = createEClass(TRANSFORMATION_REFERENCE);
		createEAttribute(transformationReferenceEClass, TRANSFORMATION_REFERENCE__NAME);

		externalTransformationEClass = createEClass(EXTERNAL_TRANSFORMATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		transformationEClass.getESuperTypes().add(this.getTransformationReference());
		selfRequirementEClass.getESuperTypes().add(this.getRequirement());
		parentRequirementEClass.getESuperTypes().add(this.getRequirement());
		customRequirementEClass.getESuperTypes().add(this.getRequirement());
		externalTransformationEClass.getESuperTypes().add(this.getTransformationReference());

		// Initialize classes, features, and operations; add parameters
		initEClass(transformationSetsEClass, TransformationSets.class, "TransformationSets", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTransformationSets_TransformationSets(), this.getTransformationSet(), null, "transformationSets", null, 0, -1, TransformationSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSets_TransformationSetExtensions(), this.getTransformationSetExtension(), null, "transformationSetExtensions", null, 0, -1, TransformationSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSets_ExternalTransformations(), this.getExternalTransformation(), null, "externalTransformations", null, 0, -1, TransformationSets.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationSetEClass, TransformationSet.class, "TransformationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformationSet_Name(), ecorePackage.getEString(), "name", "", 0, 1, TransformationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformationSet_Description(), ecorePackage.getEString(), "description", "", 0, 1, TransformationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformationSet_Implementation(), ecorePackage.getEString(), "implementation", "", 0, 1, TransformationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSet_Transformations(), this.getTransformation(), null, "transformations", null, 1, -1, TransformationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformationSet_Private(), ecorePackage.getEBoolean(), "private", "false", 0, 1, TransformationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationSetExtensionEClass, TransformationSetExtension.class, "TransformationSetExtension", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformationSetExtension_TransformationSetId(), ecorePackage.getEString(), "transformationSetId", "", 0, 1, TransformationSetExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformationSetExtension_Transformations(), this.getTransformation(), null, "transformations", null, 1, -1, TransformationSetExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationEClass, Transformation.class, "Transformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformation_Description(), ecorePackage.getEString(), "description", "", 0, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformation_Factory(), ecorePackage.getEString(), "factory", null, 0, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransformation_Priority(), ecorePackage.getEInt(), "priority", "0", 0, 1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTransformation_Requirements(), this.getRequirement(), null, "requirements", null, 0, -1, Transformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(requirementEClass, Requirement.class, "Requirement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRequirement_Reference(), this.getTransformationReference(), null, "reference", null, 1, 1, Requirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(selfRequirementEClass, SelfRequirement.class, "SelfRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(parentRequirementEClass, ParentRequirement.class, "ParentRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(customRequirementEClass, CustomRequirement.class, "CustomRequirement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomRequirement_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, CustomRequirement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformationReferenceEClass, TransformationReference.class, "TransformationReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransformationReference_Name(), ecorePackage.getEString(), "name", null, 0, 1, TransformationReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalTransformationEClass, ExternalTransformation.class, "ExternalTransformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TomPackageImpl
