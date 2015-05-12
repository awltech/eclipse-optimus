/**
 */
package net.atos.optimus.m2m.engine.sdk.tom.impl;

import net.atos.optimus.m2m.engine.sdk.tom.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TomFactoryImpl extends EFactoryImpl implements TomFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TomFactory init() {
		try {
			TomFactory theTomFactory = (TomFactory)EPackage.Registry.INSTANCE.getEFactory(TomPackage.eNS_URI);
			if (theTomFactory != null) {
				return theTomFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TomFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TomFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TomPackage.TRANSFORMATION_SETS: return createTransformationSets();
			case TomPackage.TRANSFORMATION_SET: return createTransformationSet();
			case TomPackage.TRANSFORMATION_SET_EXTENSION: return createTransformationSetExtension();
			case TomPackage.TRANSFORMATION: return createTransformation();
			case TomPackage.SELF_REQUIREMENT: return createSelfRequirement();
			case TomPackage.PARENT_REQUIREMENT: return createParentRequirement();
			case TomPackage.CUSTOM_REQUIREMENT: return createCustomRequirement();
			case TomPackage.EXTERNAL_TRANSFORMATION: return createExternalTransformation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationSets createTransformationSets() {
		TransformationSetsImpl transformationSets = new TransformationSetsImpl();
		return transformationSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationSet createTransformationSet() {
		TransformationSetImpl transformationSet = new TransformationSetImpl();
		return transformationSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationSetExtension createTransformationSetExtension() {
		TransformationSetExtensionImpl transformationSetExtension = new TransformationSetExtensionImpl();
		return transformationSetExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transformation createTransformation() {
		TransformationImpl transformation = new TransformationImpl();
		return transformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelfRequirement createSelfRequirement() {
		SelfRequirementImpl selfRequirement = new SelfRequirementImpl();
		return selfRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParentRequirement createParentRequirement() {
		ParentRequirementImpl parentRequirement = new ParentRequirementImpl();
		return parentRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomRequirement createCustomRequirement() {
		CustomRequirementImpl customRequirement = new CustomRequirementImpl();
		return customRequirement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalTransformation createExternalTransformation() {
		ExternalTransformationImpl externalTransformation = new ExternalTransformationImpl();
		return externalTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TomPackage getTomPackage() {
		return (TomPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TomPackage getPackage() {
		return TomPackage.eINSTANCE;
	}

} //TomFactoryImpl
