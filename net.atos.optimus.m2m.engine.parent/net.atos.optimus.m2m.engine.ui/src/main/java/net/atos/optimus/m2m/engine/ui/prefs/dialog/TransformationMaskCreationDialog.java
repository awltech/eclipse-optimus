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
package net.atos.optimus.m2m.engine.ui.prefs.dialog;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;
import net.atos.optimus.m2m.engine.ui.prefs.masks.list.TransformationMasksContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.masks.list.TransformationMasksLabelProvider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog window used to create a new user transformation mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMaskCreationDialog extends Dialog {

	/** The label width */
	public static final int LABEL_WIDTH = 70;

	/** The text area holding the transformation mask name */
	protected Text creationText;

	/** The text area holding the transformation mask description */
	protected Text descriptionText;

	/** The combo viewer holding transformation masks */
	protected ComboViewer transformationMasksComboViewer;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public TransformationMaskCreationDialog(Shell parentShell) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite mainContainer = (Composite) super.createDialogArea(parent);
		mainContainer.setLayout(new FormLayout());

		Composite creationContainer = new Composite(mainContainer, SWT.NONE);
		creationContainer.setLayout(new FormLayout());
		Label creationLabel = new Label(creationContainer, SWT.NONE);
		creationLabel.setText(TransformationMasksDialogMessages.CREATION_MESSAGE.message());
		this.creationText = new Text(creationContainer, SWT.NONE);

		Composite descriptionContainer = new Composite(mainContainer, SWT.NONE);
		descriptionContainer.setLayout(new FormLayout());
		Label descriptionLabel = new Label(descriptionContainer, SWT.NONE);
		descriptionLabel.setText(TransformationMasksDialogMessages.CREATION_DESCRIPTION.message());
		this.descriptionText = new Text(descriptionContainer, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);

		Composite extensionContainer = new Composite(mainContainer, SWT.NONE);
		extensionContainer.setLayout(new FormLayout());
		Label extensionLabel = new Label(extensionContainer, SWT.NONE);
		extensionLabel.setText(TransformationMasksDialogMessages.EXTENSION_MESSAGE.message());

		Combo transformationMaskExtensionCombo = new Combo(extensionContainer, SWT.READ_ONLY);
		this.transformationMasksComboViewer = new ComboViewer(transformationMaskExtensionCombo);
		this.transformationMasksComboViewer.setLabelProvider(new TransformationMasksLabelProvider());
		this.transformationMasksComboViewer.setContentProvider(new TransformationMasksContentsProvider());
		this.transformationMasksComboViewer.setInput(TransformationMaskDataSourceManager.INSTANCE);
		transformationMaskExtensionCombo.select(0);

		final Label errorLabel = new Label(mainContainer, SWT.NONE);
		errorLabel.setVisible(false);

		FormDataBuilder.on(creationLabel).left().top().width(TransformationMaskCreationDialog.LABEL_WIDTH);
		FormDataBuilder.on(this.creationText).left(creationLabel).right().vertical();
		FormDataBuilder.on(creationContainer).top().horizontal();

		FormDataBuilder.on(descriptionLabel).left().top().width(TransformationMaskCreationDialog.LABEL_WIDTH);
		FormDataBuilder.on(this.descriptionText).left(descriptionLabel).right().height(50);
		FormDataBuilder.on(descriptionContainer).top(creationContainer).horizontal();

		FormDataBuilder.on(extensionLabel).left().top(15).bottom().width(TransformationMaskCreationDialog.LABEL_WIDTH);
		FormDataBuilder.on(transformationMaskExtensionCombo).left(extensionLabel).right().vertical();
		FormDataBuilder.on(extensionContainer).top(descriptionContainer).horizontal();

		FormDataBuilder.on(errorLabel).horizontal().top(extensionContainer);

		return mainContainer;
	}

	@Override
	protected void okPressed() {
		String newMaskName = this.creationText.getText();
		String maskDescription = this.descriptionText.getText();
		IStructuredSelection selection = (IStructuredSelection) this.transformationMasksComboViewer.getSelection();
		TransformationMaskReference extendedTransformationMask = (TransformationMaskReference) selection
				.getFirstElement();
		ITransformationMask maskImplementation = extendedTransformationMask.getImplementation();
		TransformationMaskReference newTransformationMask = new TransformationMaskReference(newMaskName,
				maskDescription, maskImplementation);
		UserTransformationMaskTool.createUserTransformationMask(newTransformationMask);
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TransformationMasksDialogMessages.CREATION_TITLE.message());
	}

	@Override
	protected Point getInitialSize() {
		return new Point(400, 250);
	}

}
