/**
 */
package net.atos.optimus.m2m.engine.sdk.tom.util;

import net.atos.optimus.m2m.engine.sdk.tom.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage
 * @generated
 */
public class TomAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TomPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TomAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TomPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TomSwitch<Adapter> modelSwitch =
		new TomSwitch<Adapter>() {
			@Override
			public Adapter caseTransformationSets(TransformationSets object) {
				return createTransformationSetsAdapter();
			}
			@Override
			public Adapter caseTransformationSet(TransformationSet object) {
				return createTransformationSetAdapter();
			}
			@Override
			public Adapter caseTransformationSetExtension(TransformationSetExtension object) {
				return createTransformationSetExtensionAdapter();
			}
			@Override
			public Adapter caseTransformation(Transformation object) {
				return createTransformationAdapter();
			}
			@Override
			public Adapter caseRequirement(Requirement object) {
				return createRequirementAdapter();
			}
			@Override
			public Adapter caseSelfRequirement(SelfRequirement object) {
				return createSelfRequirementAdapter();
			}
			@Override
			public Adapter caseParentRequirement(ParentRequirement object) {
				return createParentRequirementAdapter();
			}
			@Override
			public Adapter caseCustomRequirement(CustomRequirement object) {
				return createCustomRequirementAdapter();
			}
			@Override
			public Adapter caseTransformationReference(TransformationReference object) {
				return createTransformationReferenceAdapter();
			}
			@Override
			public Adapter caseExternalTransformation(ExternalTransformation object) {
				return createExternalTransformationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSets <em>Transformation Sets</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSets
	 * @generated
	 */
	public Adapter createTransformationSetsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSet <em>Transformation Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSet
	 * @generated
	 */
	public Adapter createTransformationSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension <em>Transformation Set Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension
	 * @generated
	 */
	public Adapter createTransformationSetExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.Transformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Transformation
	 * @generated
	 */
	public Adapter createTransformationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.Requirement <em>Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.Requirement
	 * @generated
	 */
	public Adapter createRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement <em>Self Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.SelfRequirement
	 * @generated
	 */
	public Adapter createSelfRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement <em>Parent Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.ParentRequirement
	 * @generated
	 */
	public Adapter createParentRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement <em>Custom Requirement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.CustomRequirement
	 * @generated
	 */
	public Adapter createCustomRequirementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.TransformationReference <em>Transformation Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TransformationReference
	 * @generated
	 */
	public Adapter createTransformationReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation <em>External Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation
	 * @generated
	 */
	public Adapter createExternalTransformationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TomAdapterFactory
