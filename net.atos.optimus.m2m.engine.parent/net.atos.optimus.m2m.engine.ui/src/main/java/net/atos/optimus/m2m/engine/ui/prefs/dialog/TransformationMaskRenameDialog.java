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
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.masks.EditableTransformationMaskReference;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog window used to rename a user transformation mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMaskRenameDialog extends Dialog {

	/** The label width */
	public static final int LABEL_WIDTH = 210;

	/** The name of the mask to delete */
	protected EditableTransformationMaskReference transformationMaskReference;

	/** The text area holding the new transformation mask name */
	protected Text renameText;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * @param transformationMaskReference
	 *            the transformation mask reference to delete.
	 */
	public TransformationMaskRenameDialog(Shell parentShell,
			EditableTransformationMaskReference transformationMaskReference) {
		super(parentShell);
		this.setShellStyle(getShellStyle() | SWT.RESIZE);
		this.transformationMaskReference = transformationMaskReference;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite mainContainer = (Composite) super.createDialogArea(parent);
		mainContainer.setLayout(new FormLayout());

		Composite oldNameContainer = new Composite(mainContainer, SWT.NONE);
		oldNameContainer.setLayout(new FormLayout());
		Label oldNameLabel = new Label(oldNameContainer, SWT.NONE);
		oldNameLabel.setText(TransformationMasksDialogMessages.RENAME_OLD_NAME.message(this.transformationMaskReference
				.getName()));
		Label oldText = new Label(oldNameContainer, SWT.NONE);
		oldText.setText(this.transformationMaskReference.getName());

		Composite newNameContainer = new Composite(mainContainer, SWT.NONE);
		newNameContainer.setLayout(new FormLayout());
		Label newNameLabel = new Label(newNameContainer, SWT.NONE);
		newNameLabel.setText(TransformationMasksDialogMessages.RENAME_NEW_NAME.message(this.transformationMaskReference
				.getName()));
		this.renameText = new Text(newNameContainer, SWT.NONE);
		this.renameText.setText(this.transformationMaskReference.getName());
		
		final Composite errorContainer = new Composite(mainContainer, SWT.NONE);
		errorContainer.setLayout(new FormLayout());
		Label errorImage = new Label(errorContainer, SWT.ICON_ERROR);
		errorImage.setImage(Display.getDefault().getSystemImage(SWT.ICON_ERROR));
		final Label errorLabel = new Label(errorContainer, SWT.NONE);
		errorContainer.setVisible(false);

		FormDataBuilder.on(oldNameLabel).left().top().width(TransformationMaskRenameDialog.LABEL_WIDTH);
		FormDataBuilder.on(oldText).left(oldNameLabel).right().vertical();
		FormDataBuilder.on(oldNameContainer).top().horizontal();

		FormDataBuilder.on(newNameLabel).left().top().width(TransformationMaskRenameDialog.LABEL_WIDTH);
		FormDataBuilder.on(this.renameText).left(newNameLabel).right().vertical();
		FormDataBuilder.on(newNameContainer).top(oldNameContainer).horizontal();

		FormDataBuilder.on(errorImage).top().left();
		FormDataBuilder.on(errorLabel).top(20).left(errorImage).right();
		FormDataBuilder.on(errorContainer).top(newNameContainer).horizontal();

		this.renameText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String newMaskName = TransformationMaskRenameDialog.this.renameText.getText().trim();
				if ("".equals(newMaskName)) {
					TransformationMaskRenameDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorContainer.setVisible(false);
				} else if(TransformationMaskDataSourceManager.INSTANCE.findTransformationMaskByName(newMaskName) != null){
					TransformationMaskRenameDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText(TransformationMasksDialogMessages.NAME_CONFLICT.message(newMaskName));
					errorContainer.setVisible(true);
				} else {
					TransformationMaskRenameDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
					errorContainer.setVisible(false);
				}
			}
		});

		return mainContainer;
	}

	@Override
	protected void okPressed() {
		this.transformationMaskReference.setName(this.renameText.getText());
		super.okPressed();
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TransformationMasksDialogMessages.RENAME_TITLE.message());
	}

	@Override
	protected Point getInitialSize() {
		return new Point(500, 200);
	}

}
