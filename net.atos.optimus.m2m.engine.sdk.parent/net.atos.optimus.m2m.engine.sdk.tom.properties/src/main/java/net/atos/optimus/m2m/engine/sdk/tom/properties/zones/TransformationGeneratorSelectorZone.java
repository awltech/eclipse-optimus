/**
 * Optimus, framework for Model Transformation
 *
 * Copyright (C) 2013 Worldline or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import net.atos.optimus.m2m.engine.sdk.tom.properties.listeners.TransformationGeneratorListener;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

import com.worldline.gmf.propertysections.core.tools.FormDataBuilder;

/**
 * Models a zone displaying a resource selector with transformation generation and
 * selection buttons at right and a label at left
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationGeneratorSelectorZone extends ResourceSelectorZone {

	public static final String BUTTON_CREATION_TEXT = "Generate";

	/** The button launching the creation pop-up */
	protected Button creationButton;

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
	public TransformationGeneratorSelectorZone(Composite parent, boolean isGroup,
			EStructuralFeature associatedModelFeature, String labelText, int labelWidth, boolean inputFieldEnable,
			String titleResourceSelector, ResourceProcessorFactory resourceFactory) {
		super(parent, isGroup, associatedModelFeature, labelText, labelWidth, inputFieldEnable, titleResourceSelector,
				resourceFactory);
	}

	@Override
	public void addItemsToZone() {
		super.addItemsToZone();
		this.creationButton = this.getWidgetFactory().createButton(getZone(),
				TransformationGeneratorSelectorZone.BUTTON_CREATION_TEXT, SWT.NONE);
	}

	@Override
	public void updateItemsValues() {
		super.updateItemsValues();
		IProject project = WorkspaceSynchronizer.getFile(this.giveEObject().eResource()).getProject();
		try {
			this.creationButton.setEnabled(project.isNatureEnabled("org.eclipse.jdt.core.javanature"));
		} catch (CoreException e) {
			this.creationButton.setEnabled(false);
		}
	}

	@Override
	public void addLayoutsToItems() {
		super.addLayoutsToItems();
		((FormData) this.input.getLayoutData()).right = new FormAttachment(this.creationButton,-5);
		FormDataBuilder.on(this.creationButton).right(this.researchButton).top().bottom()
				.width(ResourceSelectorZone.BUTTON_WIDTH);
	}

	@Override
	public void addListenersToItems() {
		super.addListenersToItems();
		SelectionListener selectionChangeListener = new TransformationGeneratorListener(this);

		/* Listener setting */
		this.creationButton.addSelectionListener(selectionChangeListener);
	}

}
