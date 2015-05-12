/**
 */
package net.atos.optimus.m2m.engine.sdk.tom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Requirement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.atos.optimus.m2m.engine.sdk.tom.Requirement#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getRequirement()
 * @model abstract="true"
 * @generated
 */
public interface Requirement extends EObject {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(TransformationReference)
	 * @see net.atos.optimus.m2m.engine.sdk.tom.TomPackage#getRequirement_Reference()
	 * @model required="true"
	 * @generated
	 */
	TransformationReference getReference();

	/**
	 * Sets the value of the '{@link net.atos.optimus.m2m.engine.sdk.tom.Requirement#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(TransformationReference value);

} // Requirement
