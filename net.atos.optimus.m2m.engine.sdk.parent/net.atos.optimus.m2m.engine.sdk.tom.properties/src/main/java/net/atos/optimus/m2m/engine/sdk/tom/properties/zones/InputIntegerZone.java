package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.TextChangeListener;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.TextChangeHelper;
import org.eclipse.swt.widgets.Composite;

/**
 * Models a zone with an integer input and a label at his left
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class InputIntegerZone extends InputTextWithLabelZone {

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
	public InputIntegerZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelText, int labelWidth) {
		super(parent, isGroup, associatedModelFeature, labelText, labelWidth);
	}
	
	@Override
	public Object getInputValue(){
		String text = this.input.getText();
		int value = 0;
		if (text.matches("[0-9]+")) {
			value = Integer.parseInt(text);
		}
		return value;
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper nameTextListener = new TextChangeListener(this);

		/* Listener setting */
		nameTextListener.startListeningTo(this.input);
		nameTextListener.startListeningForEnter(this.input);
	}

}
