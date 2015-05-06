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
package net.atos.optimus.m2m.engine.ui.prefs.masks;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.XMLTransformationMaskCreationTool;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

	/** The combo box holding the available transformation masks */
	protected Combo transformationMaskExtensionCombo;

	/** The last created transformation mask name */
	protected String newMaskName;

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
		creationLabel.setText(TransformationMasksCreationDialogMessages.CREATION_MESSAGE.message());
		FormDataBuilder.on(creationLabel).left().vertical().width(TransformationMaskCreationDialog.LABEL_WIDTH);

		this.creationText = new Text(creationContainer, SWT.NONE);
		FormDataBuilder.on(creationText).left(creationLabel).right().vertical();

		FormDataBuilder.on(creationContainer).top().horizontal();

		Composite extensionContainer = new Composite(mainContainer, SWT.NONE);
		extensionContainer.setLayout(new FormLayout());

		Label extensionLabel = new Label(extensionContainer, SWT.NONE);
		extensionLabel.setText(TransformationMasksCreationDialogMessages.EXTENSION_MESSAGE.message());
		FormDataBuilder.on(extensionLabel).left().top(7).bottom().width(TransformationMaskCreationDialog.LABEL_WIDTH);

		this.transformationMaskExtensionCombo = new Combo(extensionContainer, SWT.READ_ONLY);
		for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskDataSources()) {
			for (TransformationMaskReference transformationMaskReference : transformationMaskDataSource.getAllMasks()) {
				transformationMaskExtensionCombo.add(transformationMaskReference.getName());
			}
		}
		transformationMaskExtensionCombo.select(0);
		FormDataBuilder.on(transformationMaskExtensionCombo).left(extensionLabel).right().vertical();

		FormDataBuilder.on(extensionContainer).top(creationContainer).horizontal();

		final Label errorLabel = new Label(mainContainer, SWT.NONE);
		FormDataBuilder.on(errorLabel).horizontal().top(extensionContainer);
		errorLabel.setVisible(false);

		creationText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String transformationName = TransformationMaskCreationDialog.this.creationText.getText();
				if (TransformationMaskDataSourceManager.INSTANCE.getTransformationMaskById(transformationName) != null) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText("The transformation mask " + transformationName + " already exist");
					errorLabel.setVisible(true);
				} else {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
					errorLabel.setVisible(false);
				}
			}
		});

		return mainContainer;
	}

	@Override
	protected void okPressed() {
		String maskName = this.creationText.getText();
		String extendedMaskName = this.transformationMaskExtensionCombo.getText();
		TransformationMaskReference extendedTransformationMask = TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(extendedMaskName);
		XMLTransformationMaskCreationTool.createUserTransformationMask(maskName,
				extendedTransformationMask.getImplementation());
		this.newMaskName = maskName;
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TransformationMasksCreationDialogMessages.CREATION_TITLE.message());
	}

	@Override
	protected Point getInitialSize() {
		return new Point(300, 200);
	}

	public String getValue() {
		return this.newMaskName;
	}

}