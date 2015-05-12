/**
 */
package net.atos.optimus.m2m.engine.sdk.tom.impl;

import java.util.Collection;

import net.atos.optimus.m2m.engine.sdk.tom.TomPackage;
import net.atos.optimus.m2m.engine.sdk.tom.Transformation;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transformation Set Extension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl#getTransformationSetId <em>Transformation Set Id</em>}</li>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.impl.TransformationSetExtensionImpl#getTransformations <em>Transformations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransformationSetExtensionImpl extends MinimalEObjectImpl.Container implements TransformationSetExtension {
	/**
	 * The default value of the '{@link #getTransformationSetId() <em>Transformation Set Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationSetId()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSFORMATION_SET_ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getTransformationSetId() <em>Transformation Set Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationSetId()
	 * @generated
	 * @ordered
	 */
	protected String transformationSetId = TRANSFORMATION_SET_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTransformations() <em>Transformations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformations()
	 * @generated
	 * @ordered
	 */
	protected EList<Transformation> transformations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransformationSetExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TomPackage.Literals.TRANSFORMATION_SET_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransformationSetId() {
		return transformationSetId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformationSetId(String newTransformationSetId) {
		String oldTransformationSetId = transformationSetId;
		transformationSetId = newTransformationSetId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID, oldTransformationSetId, transformationSetId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transformation> getTransformations() {
		if (transformations == null) {
			transformations = new EObjectContainmentEList<Transformation>(Transformation.class, this, TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS);
		}
		return transformations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS:
				return ((InternalEList<?>)getTransformations()).basicRemove(otherEnd, msgs);
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
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID:
				return getTransformationSetId();
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS:
				return getTransformations();
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
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID:
				setTransformationSetId((String)newValue);
				return;
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS:
				getTransformations().clear();
				getTransformations().addAll((Collection<? extends Transformation>)newValue);
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
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID:
				setTransformationSetId(TRANSFORMATION_SET_ID_EDEFAULT);
				return;
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS:
				getTransformations().clear();
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
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATION_SET_ID:
				return TRANSFORMATION_SET_ID_EDEFAULT == null ? transformationSetId != null : !TRANSFORMATION_SET_ID_EDEFAULT.equals(transformationSetId);
			case TomPackage.TRANSFORMATION_SET_EXTENSION__TRANSFORMATIONS:
				return transformations != null && !transformations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (transformationSetId: ");
		result.append(transformationSetId);
		result.append(')');
		return result.toString();
	}

} //TransformationSetExtensionImpl
