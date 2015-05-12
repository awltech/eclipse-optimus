package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.TextChangeListener;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.properties.views.TextChangeHelper;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models a zone with a label and an input field
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 * @param <T>
 *            the type of the input field
 */

public abstract class InputWithLabel<T extends Control> extends InputZone {

	/** The input field */
	protected T input;

	/** The label describing the input field containment */
	protected CLabel associatedLabel;

	/** The text of the label */
	protected String labelText;

	/** The width of the label */
	protected int labelWidth;

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
	public InputWithLabel(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelText, int labelWidth) {
		super(parent, isGroup, associatedModelFeature);
		this.labelText = labelText;
		this.labelWidth = labelWidth;
	}

	/**
	 * Add an input field in the zone
	 * 
	 */
	protected abstract void addInputField();

	@Override
	public void addItemsToZone() {
		this.associatedLabel = this.getWidgetFactory().createCLabel(getZone(), this.labelText);
		this.addInputField();
	}

	@Override
	public void addLayoutsToItems() {
		FormDataBuilder.on(this.associatedLabel).left().top().bottom().width(this.labelWidth);
		FormDataBuilder.on(this.input).left(this.associatedLabel).top().right().bottom();
	}

	@Override
	public void addListenersToItems() {
		TextChangeHelper nameTextListener = new TextChangeListener(this);

		/* Listener setting */
		nameTextListener.startListeningTo(this.input);
		nameTextListener.startListeningForEnter(this.input);
	}

	@Override
	public abstract void updateItemsValues();

}
