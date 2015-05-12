package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.TextChangeListener;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.TextChangeHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;

/**
 * Models a zone with a large input text and a label at his left
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class InputLargeTextZone extends InputTextWithLabelZone {

	public static final int HEIGHT_INPUT_FILED = 50;

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
	public InputLargeTextZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelText, int labelWidth) {
		super(parent, isGroup, associatedModelFeature, labelText, labelWidth);
	}

	/**
	 * Add a large input text in the zone
	 * 
	 */
	protected void addInputField() {
		this.input = this.getWidgetFactory().createText(getZone(), "",
				SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
	}

	@Override
	public void addLayoutsToItems() {
		super.addLayoutsToItems();
		((FormData) this.input.getLayoutData()).height = InputLargeTextZone.HEIGHT_INPUT_FILED;
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper nameTextListener = new TextChangeListener(this);

		/* Listener setting */
		nameTextListener.startListeningTo(this.input);
	}

}
