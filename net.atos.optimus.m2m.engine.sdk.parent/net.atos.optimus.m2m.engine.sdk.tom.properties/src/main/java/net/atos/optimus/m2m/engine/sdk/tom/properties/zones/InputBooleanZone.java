package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.CheckboxListener;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models a zone displaying a checkbox with an associated label
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class InputBooleanZone extends InputZone {
	
	/** The checkbox */
	protected Button checkbox;

	/** The text of the check-box */
	protected String labelCheckbox;

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
	 * @param labelCheckbox
	 *            the text of the check-box.
	 */
	public InputBooleanZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelCheckbox) {
		super(parent, isGroup, associatedModelFeature);
		this.labelCheckbox = labelCheckbox;
	}

	@Override
	public Object getInputValue() {
		return this.checkbox.getSelection();
	}
	
	@Override
	public void addItemsToZone() {
		this.checkbox = this.getWidgetFactory().createButton(getZone(), this.labelCheckbox, SWT.CHECK);
	}
	
	@Override
	public void addLayoutsToItems() {
		FormDataBuilder.on(this.checkbox).left().top().bottom();
	}
	
	@Override
	public void addListenersToItems() {
		SelectionListener selectionChangeListener = new CheckboxListener(this);

		/* Listener setting */
		this.checkbox.addSelectionListener(selectionChangeListener);
	}

	@Override
	public void updateItemsValues() {
		Object o = getEObject().eGet(this.associatedModelFeature);
		this.checkbox.setSelection(o != null ? Boolean.parseBoolean(String.valueOf(o)) : false);
	}

}
