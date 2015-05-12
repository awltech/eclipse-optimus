package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.ResourceSelectorListener;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models a zone displaying a resource selector with a button at right and a
 * label at left
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class ResourceSelectorZone extends InputTextWithLabelZone {

	public static final int BUTTON_WIDTH = 60;

	public static final String BUTTON_TEXT = "Browse";

	/** The button launching the resource selector */
	protected Button researchButton;

	/** Tell if the input field is enable */
	protected boolean inputFieldEnable;

	/** The title of the resource selector */
	protected String titleResourceSelector;

	/** The factory used to create the resource processor */
	protected ResourceProcessorFactory resourceFactory;

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
	 * @param inputFieldEnable
	 *            tell if the input field is enable.
	 * @param titleResourceSelector
	 *            the title of the resource selector.
	 * @param resourceFactory
	 *            the factory used to create the resource processor.
	 */
	public ResourceSelectorZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature,
			String labelText, int labelWidth, boolean inputFieldEnable, String titleResourceSelector,
			ResourceProcessorFactory resourceFactory) {
		super(parent, isGroup, associatedModelFeature, labelText, labelWidth);
		this.inputFieldEnable = inputFieldEnable;
		this.titleResourceSelector = titleResourceSelector;
		this.resourceFactory = resourceFactory;
	}
	
	/** The title of the resource selector
	 * 
	 * @return the title of the current resource selector.
	 */
	public String getTitleResourceSelector(){
		return this.titleResourceSelector;
	}

	/**
	 * The factory used to create the resource processor
	 * 
	 * @return the factory used to create the resource processor.
	 */
	public ResourceProcessorFactory getResourceProcessorFactory() {
		return this.resourceFactory;
	}

	@Override
	public void addItemsToZone() {
		super.addItemsToZone();
		this.input.setEnabled(this.inputFieldEnable);
		this.researchButton = this.getWidgetFactory().createButton(getZone(), ResourceSelectorZone.BUTTON_TEXT,
				SWT.NONE);
	}

	@Override
	public void addLayoutsToItems() {
		super.addLayoutsToItems();
		((FormData) this.input.getLayoutData()).right = new FormAttachment(this.researchButton);
		FormDataBuilder.on(this.researchButton).right().top().bottom().width(ResourceSelectorZone.BUTTON_WIDTH);
	}

	@Override
	public void addListenersToItems() {
		super.addListenersToItems();
		SelectionListener selectionChangeListener = new ResourceSelectorListener(this);

		/* Listener setting */
		this.researchButton.addSelectionListener(selectionChangeListener);
	}

}
