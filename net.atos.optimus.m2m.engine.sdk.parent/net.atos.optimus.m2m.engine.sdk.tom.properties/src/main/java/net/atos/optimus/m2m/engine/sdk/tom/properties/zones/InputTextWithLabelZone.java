package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * Models a zone with a simple input text and a label at his left
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class InputTextWithLabelZone extends InputWithLabel<Text> {

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
	 * @param labelText
	 *            the text of the label associated to the input field.
	 * @param labelWidth
	 *            the width of the label associated to the input field.
	 */
	public InputTextWithLabelZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelText, int labelWidth) {
		super(parent, isGroup, associatedModelFeature, labelText, labelWidth);
	}

	/**
	 * Add a simple input text in the zone
	 * 
	 */
	protected void addInputField() {
		this.input = this.getWidgetFactory().createText(getZone(), "", SWT.NONE);
	}
	
	@Override
	public Object getInputValue(){
		return this.input.getText();
	}

	@Override
	public void updateItemsValues() {
		Object o = getEObject().eGet(this.associatedModelFeature);
		this.input.setText(o != null ? String.valueOf(o) : "");
	}

}
