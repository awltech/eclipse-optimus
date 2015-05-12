package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.worldline.gmf.propertysections.core.AbstractZone;

/** Models an input zone with useful methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public abstract class InputZone extends AbstractZone {
	
	/**
	 * The model feature of the model associated to the value in the input text
	 * field
	 */
	protected EStructuralFeature associatedModelFeature;
	
	/**
	 * Constructor
	 * 
	 * @param parent
	 *            parent composite.
	 * @param isGroup
	 *            true creates a Group, false creates a standard Composite.
	 * @param associatedModelFeature
	 *            the model feature of the model associated to the value in the
	 *            input text field.
	 */
	public InputZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature) {
		super(parent, isGroup);
		this.associatedModelFeature = associatedModelFeature;
	}
	
	/**
	 * Get the value in the input field
	 * 
	 * @return the value in the input field.
	 */
	public abstract Object getInputValue();
	
	/**
	 * The model feature of the model associated to the value in the input text
	 * field
	 * 
	 * @return the model feature of the model associated to the value in the
	 *         input text field
	 */
	public EStructuralFeature getAssociatedFeature() {
		return this.associatedModelFeature;
	}

	/**
	 * The TransactionalEditingDomain, retrieved from Opened Diagram
	 * 
	 * 
	 * @return TransactionalEditingDomain, retrieved from Opened Diagram.
	 */
	public final TransactionalEditingDomain giveEditingDomain() {
		return super.getEditingDomain();
	}

	/**
	 * The selected EObject beyond the selected EditPart in the Diagram
	 * 
	 * @return selected EObject beyond the selected EditPart in the Diagram.
	 */
	public final EObject giveEObject() {
		return super.getEObject();
	}
	
	/**
	 * Refreshes the current zone, such as the EditPart bound to this zone.
	 * 
	 */
	public void refreshZoneAndDiagram() {
		super.refreshZoneAndDiagram();
	}
	
}
