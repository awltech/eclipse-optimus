/**
 */
package net.atos.optimus.m2m.engine.sdk.tom.impl;

import java.util.Collection;

import net.atos.optimus.m2m.engine.sdk.tom.ExternalTransformation;
import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSet;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSets;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation Sets</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl#getTransformationSets <em>Transformation Sets</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl#getTransformationSetExtensions <em>Transformation Set Extensions</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetsImpl#getExternalTransformations <em>External Transformations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationSetsImpl extends MinimalEObjectImpl.Container implements TransformationSets {
	/**
	 * The cached value of the '{@link #getTransformationSets() <em>Transformation Sets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationSets()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationSet> transformationSets;

	/**
	 * The cached value of the '{@link #getTransformationSetExtensions() <em>Transformation Set Extensions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationSetExtensions()
	 * @generated
	 * @ordered
	 */
	protected EList<TransformationSetExtension> transformationSetExtensions;

	/**
	 * The cached value of the '{@link #getExternalTransformations() <em>External Transformations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalTransformations()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalTransformation> externalTransformations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationSetsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TomPackage.Literals.TRANSFORMATION_SETS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationSet> getTransformationSets() {
		if (transformationSets == null) {
			transformationSets = new EObjectContainmentEList<TransformationSet>(TransformationSet.class, this, TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS);
		}
		return transformationSets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransformationSetExtension> getTransformationSetExtensions() {
		if (transformationSetExtensions == null) {
			transformationSetExtensions = new EObjectContainmentEList<TransformationSetExtension>(TransformationSetExtension.class, this, TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS);
		}
		return transformationSetExtensions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalTransformation> getExternalTransformations() {
		if (externalTransformations == null) {
			externalTransformations = new EObjectContainmentEList<ExternalTransformation>(ExternalTransformation.class, this, TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS);
		}
		return externalTransformations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS:
				return ((InternalEList<?>)getTransformationSets()).basicRemove(otherEnd, msgs);
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS:
				return ((InternalEList<?>)getTransformationSetExtensions()).basicRemove(otherEnd, msgs);
			case TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS:
				return ((InternalEList<?>)getExternalTransformations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS:
				return getTransformationSets();
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS:
				return getTransformationSetExtensions();
			case TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS:
				return getExternalTransformations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS:
				getTransformationSets().clear();
				getTransformationSets().addAll((Collection<? extends TransformationSet>)newValue);
				return;
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS:
				getTransformationSetExtensions().clear();
				getTransformationSetExtensions().addAll((Collection<? extends TransformationSetExtension>)newValue);
				return;
			case TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS:
				getExternalTransformations().clear();
				getExternalTransformations().addAll((Collection<? extends ExternalTransformation>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS:
				getTransformationSets().clear();
				return;
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS:
				getTransformationSetExtensions().clear();
				return;
			case TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS:
				getExternalTransformations().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SETS:
				return transformationSets != null && !transformationSets.isEmpty();
			case TomPackage.TRANSFORMATION_SETS__TRANSFORMATION_SET_EXTENSIONS:
				return transformationSetExtensions != null && !transformationSetExtensions.isEmpty();
			case TomPackage.TRANSFORMATION_SETS__EXTERNAL_TRANSFORMATIONS:
				return externalTransformations != null && !externalTransformations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransformationSetsImpl
