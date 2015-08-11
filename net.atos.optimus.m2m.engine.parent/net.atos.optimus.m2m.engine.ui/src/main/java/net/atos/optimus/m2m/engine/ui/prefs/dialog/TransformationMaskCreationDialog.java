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

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.masks.JavaTransformationMask;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.list.AvailableTransformationMasksContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.list.AvailableTransformationMasksLabelProvider;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.list.SelectedTransformationMask;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.list.SelectedTransformationMasksContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.list.SelectedTransformationMasksLabelProvider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * Dialog window used to create a new user transformation mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMaskCreationDialog extends Dialog {

	/** The width size of the selection buttons */
	public static final int BUTTON_SELECT_WIDTH = 80;

	/** The width size of the ordering buttons */
	public static final int BUTTON_ORDER_WIDTH = 20;

	/** The label width */
	public static final int LABEL_WIDTH = 70;

	/** The extended transformation mask by default */
	protected TransformationMaskReference transformationMask;

	/** The text area holding the transformation mask name */
	protected Text creationText;

	/** The text area holding the transformation mask description */
	protected Text descriptionText;

	/** The new mask name */
	protected String newMaskName;

	/** The table viewer holding available transformation mask */
	protected TableViewer availableTransformationMasksTableViewer;

	/** The set holding the available transformation mask */
	protected Set<TransformationMaskReference> availableTransformationMasks;

	/** The table viewer holding selected transformation mask */
	protected TableViewer selectedTransformationMasksTableViewer;

	/** The list holding the selected transformation mask */
	protected LinkedList<SelectedTransformationMask> selectedTransformationMasks;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 * @param transformationMask
	 *            the extended transformation mask by default.
	 */
	public TransformationMaskCreationDialog(Shell parentShell, TransformationMaskReference transformationMask) {
		super(parentShell);
		this.transformationMask = transformationMask;
		this.setShellStyle(getShellStyle() | SWT.RESIZE);
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		Composite mainContainer = (Composite) super.createDialogArea(parent);
		mainContainer.setLayout(new FormLayout());

		// Name container

		Composite creationContainer = new Composite(mainContainer, SWT.NONE);
		creationContainer.setLayout(new FormLayout());
		Label creationLabel = new Label(creationContainer, SWT.NONE);
		creationLabel.setText(TransformationMasksDialogMessages.CREATION_MESSAGE.message());
		this.creationText = new Text(creationContainer, SWT.NONE);
		this.creationText.setText("");

		// Description container

		Composite descriptionContainer = new Composite(mainContainer, SWT.NONE);
		descriptionContainer.setLayout(new FormLayout());
		Label descriptionLabel = new Label(descriptionContainer, SWT.NONE);
		descriptionLabel.setText(TransformationMasksDialogMessages.CREATION_DESCRIPTION.message());
		this.descriptionText = new Text(descriptionContainer, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);

		// Merge container

		Composite mergeContainer = new Composite(mainContainer, SWT.NONE);
		mergeContainer.setLayout(new FormLayout());

		Group availableMaskGroup = new Group(mergeContainer, SWT.NONE);
		availableMaskGroup.setText("Available Mask");
		availableMaskGroup.setLayout(new FormLayout());
		Table availableTransformationMasksTable = new Table(availableMaskGroup, SWT.BORDER | SWT.SINGLE);
		this.availableTransformationMasksTableViewer = new TableViewer(availableTransformationMasksTable);
		this.availableTransformationMasksTableViewer.setLabelProvider(new AvailableTransformationMasksLabelProvider());
		this.availableTransformationMasksTableViewer
				.setContentProvider(new AvailableTransformationMasksContentsProvider());
		this.availableTransformationMasks = new HashSet<TransformationMaskReference>();
		for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskDataSources()) {
			this.availableTransformationMasks.addAll(transformationMaskDataSource.getAllMasks());
		}
		availableTransformationMasksTableViewer.setInput(this.availableTransformationMasks);

		Composite mergeButtons = new Composite(mergeContainer, SWT.NONE);
		mergeButtons.setLayout(new FormLayout());
		Button addIncludedButton = new Button(mergeButtons, SWT.PUSH);
		addIncludedButton.setText(">>> included");
		Button addExcludedButton = new Button(mergeButtons, SWT.PUSH);
		addExcludedButton.setText(">>> excluded");
		Button cancelButton = new Button(mergeButtons, SWT.PUSH);
		cancelButton.setText("<<<");

		Composite selectedContainer = new Composite(mergeContainer, SWT.NONE);
		selectedContainer.setLayout(new FormLayout());

		Group selectedMaskGroup = new Group(selectedContainer, SWT.NONE);
		selectedMaskGroup.setLayout(new FormLayout());
		selectedMaskGroup.setText("Selected Mask");
		Table selectedTransformationMasksTable = new Table(selectedMaskGroup, SWT.BORDER | SWT.SINGLE);
		this.selectedTransformationMasksTableViewer = new TableViewer(selectedTransformationMasksTable);
		this.selectedTransformationMasksTableViewer.setLabelProvider(new SelectedTransformationMasksLabelProvider());
		this.selectedTransformationMasksTableViewer
				.setContentProvider(new SelectedTransformationMasksContentsProvider());
		this.selectedTransformationMasks = new LinkedList<SelectedTransformationMask>();
		selectedTransformationMasksTableViewer.setInput(this.selectedTransformationMasks);

		Composite arrowButtons = new Composite(selectedContainer, SWT.NONE);
		arrowButtons.setLayout(new FormLayout());
		Button arrowUpButton = new Button(arrowButtons, SWT.PUSH);
		arrowUpButton.setText("^");
		Button arrowDownButton = new Button(arrowButtons, SWT.PUSH);
		arrowDownButton.setText("v");

		// Error container

		final Composite errorContainer = new Composite(mainContainer, SWT.NONE);
		errorContainer.setLayout(new FormLayout());
		Label errorImage = new Label(errorContainer, SWT.ICON_ERROR);
		errorImage.setImage(Display.getDefault().getSystemImage(SWT.ICON_ERROR));
		final Label errorLabel = new Label(errorContainer, SWT.NONE);
		errorContainer.setVisible(false);

		FormDataBuilder.on(creationLabel).left().top().width(TransformationMaskCreationDialog.LABEL_WIDTH);
		FormDataBuilder.on(this.creationText).left(creationLabel).right().vertical();
		FormDataBuilder.on(creationContainer).top().horizontal();

		FormDataBuilder.on(errorImage).top().left();
		FormDataBuilder.on(errorLabel).top().left(errorImage).right();
		FormDataBuilder.on(errorContainer).top(creationContainer).horizontal();

		FormDataBuilder.on(descriptionLabel).left().top().width(TransformationMaskCreationDialog.LABEL_WIDTH);
		FormDataBuilder.on(this.descriptionText).left(descriptionLabel).height(50).right();
		FormDataBuilder.on(descriptionContainer).top(errorContainer).horizontal();

		FormDataBuilder.on(availableTransformationMasksTable).vertical().horizontal();
		FormDataBuilder.on(availableMaskGroup).left().vertical();

		FormDataBuilder.on(addIncludedButton).top().width(TransformationMaskCreationDialog.BUTTON_SELECT_WIDTH);
		FormDataBuilder.on(addExcludedButton).top(addIncludedButton)
				.width(TransformationMaskCreationDialog.BUTTON_SELECT_WIDTH);
		FormDataBuilder.on(cancelButton).top(addExcludedButton)
				.width(TransformationMaskCreationDialog.BUTTON_SELECT_WIDTH);
		FormDataBuilder.on(mergeButtons).top(10).left(availableMaskGroup);

		FormDataBuilder.on(selectedTransformationMasksTable).vertical().horizontal();
		FormDataBuilder.on(selectedMaskGroup).left().right(arrowButtons).vertical();

		FormDataBuilder.on(arrowUpButton).top().width(TransformationMaskCreationDialog.BUTTON_ORDER_WIDTH);
		FormDataBuilder.on(arrowDownButton).top(arrowUpButton)
				.width(TransformationMaskCreationDialog.BUTTON_ORDER_WIDTH);
		FormDataBuilder.on(arrowButtons).right().top(10);

		FormDataBuilder.on(selectedContainer).left(mergeButtons).right().vertical();

		FormDataBuilder.on(mergeContainer).top(descriptionContainer).bottom().horizontal();

		creationText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String transformationMaskName = TransformationMaskCreationDialog.this.creationText.getText().trim();
				if ("".equals(transformationMaskName)) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorContainer.setVisible(false);
				} else if (TransformationMaskDataSourceManager.INSTANCE
						.findTransformationMaskByName(transformationMaskName) != null) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText(TransformationMasksDialogMessages.NAME_CONFLICT.message(transformationMaskName));
					errorContainer.setVisible(true);
				} else if (TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.getElementAt(0) == null) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText(TransformationMasksDialogMessages.NONE_MERGE.message());
					errorContainer.setVisible(true);
				} else {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
					errorContainer.setVisible(false);
				}
			}
		});

		selectedTransformationMasksTable.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				String transformationMaskName = TransformationMaskCreationDialog.this.creationText.getText().trim();
				if ("".equals(transformationMaskName)) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorContainer.setVisible(false);
				} else if (TransformationMaskDataSourceManager.INSTANCE
						.findTransformationMaskByName(transformationMaskName) != null) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText(TransformationMasksDialogMessages.NAME_CONFLICT.message(transformationMaskName));
					errorContainer.setVisible(true);
				} else if (TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.getElementAt(0) == null) {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
					errorLabel.setText(TransformationMasksDialogMessages.NONE_MERGE.message());
					errorContainer.setVisible(true);
				} else {
					TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(true);
					errorContainer.setVisible(false);
				}
			}
		});

		addIncludedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selectionOnMask = (StructuredSelection) TransformationMaskCreationDialog.this.availableTransformationMasksTableViewer
						.getSelection();
				if (selectionOnMask.getFirstElement() instanceof TransformationMaskReference) {
					TransformationMaskReference transformationMaskReference = (TransformationMaskReference) selectionOnMask
							.getFirstElement();
					TransformationMaskCreationDialog.this.availableTransformationMasks
							.remove(transformationMaskReference);
					TransformationMaskCreationDialog.this.selectedTransformationMasks
							.add(new SelectedTransformationMask(transformationMaskReference, true));
					TransformationMaskCreationDialog.this.availableTransformationMasksTableViewer.refresh();
					TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.refresh();
				}
			}
		});

		addExcludedButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selectionOnMask = (StructuredSelection) TransformationMaskCreationDialog.this.availableTransformationMasksTableViewer
						.getSelection();
				if (selectionOnMask.getFirstElement() instanceof TransformationMaskReference) {
					TransformationMaskReference transformationMaskReference = (TransformationMaskReference) selectionOnMask
							.getFirstElement();
					TransformationMaskCreationDialog.this.availableTransformationMasks
							.remove(transformationMaskReference);
					TransformationMaskCreationDialog.this.selectedTransformationMasks
							.add(new SelectedTransformationMask(transformationMaskReference, false));
					TransformationMaskCreationDialog.this.availableTransformationMasksTableViewer.refresh();
					TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.refresh();
				}
			}
		});

		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selectionOnMask = (StructuredSelection) TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer
						.getSelection();
				if (selectionOnMask.getFirstElement() instanceof SelectedTransformationMask) {
					SelectedTransformationMask selectedTransformationMask = (SelectedTransformationMask) selectionOnMask
							.getFirstElement();
					TransformationMaskCreationDialog.this.selectedTransformationMasks
							.remove(selectedTransformationMask);
					TransformationMaskCreationDialog.this.availableTransformationMasks.add(selectedTransformationMask
							.getTransformationMaskReference());
					TransformationMaskCreationDialog.this.availableTransformationMasksTableViewer.refresh();
					TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.refresh();
				}
			}
		});

		arrowUpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selectionOnMask = (StructuredSelection) TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer
						.getSelection();
				if (selectionOnMask.getFirstElement() instanceof SelectedTransformationMask) {
					SelectedTransformationMask selectedTransformationMask = (SelectedTransformationMask) selectionOnMask
							.getFirstElement();
					Iterator<SelectedTransformationMask> iterator = TransformationMaskCreationDialog.this.selectedTransformationMasks
							.iterator();
					int index = 0;
					while (!iterator.next().equals(selectedTransformationMask)) {
						index++;
					}
					if (index != 0) {
						iterator.remove();
						TransformationMaskCreationDialog.this.selectedTransformationMasks.add(index - 1,
								selectedTransformationMask);
						TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.refresh();
					}
				}
			}
		});

		arrowDownButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection selectionOnMask = (StructuredSelection) TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer
						.getSelection();
				if (selectionOnMask.getFirstElement() instanceof SelectedTransformationMask) {
					SelectedTransformationMask selectedTransformationMask = (SelectedTransformationMask) selectionOnMask
							.getFirstElement();
					Iterator<SelectedTransformationMask> iterator = TransformationMaskCreationDialog.this.selectedTransformationMasks
							.iterator();
					int index = 0;
					while (!iterator.next().equals(selectedTransformationMask)) {
						index++;
					}
					if (iterator.hasNext()) {
						iterator.remove();
						TransformationMaskCreationDialog.this.selectedTransformationMasks.add(index + 1,
								selectedTransformationMask);
						TransformationMaskCreationDialog.this.selectedTransformationMasksTableViewer.refresh();
					}
				}
			}
		});

		return mainContainer;
	}

	@Override
	protected void okPressed() {
		this.newMaskName = this.creationText.getText();
		String maskDescription = this.descriptionText.getText();
		TransformationMaskReference newTransformationMask = new TransformationMaskReference(this.newMaskName,
				maskDescription, this.mergeSelectedTransformationMask());
		UserTransformationMaskTool.createUserTransformationMask(newTransformationMask);
		super.okPressed();
	}

	/**
	 * Merge the selected transformation masks
	 * 
	 * @return the transformation mask resulting of the merge of selected
	 *         transformation masks.
	 */
	protected ITransformationMask mergeSelectedTransformationMask() {
		JavaTransformationMask mergeMask = this.selectedTransformationMasks.get(0).isInclusive() ? JavaTransformationMask
				.allOff() : JavaTransformationMask.allOn();
		for (SelectedTransformationMask selectedTransformationMask : this.selectedTransformationMasks) {
			ITransformationMask mask = selectedTransformationMask.getTransformationMaskReference().getImplementation();
			if (selectedTransformationMask.isInclusive()) {
				for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
						.getTransformationDataSources()) {
					for (TransformationReference reference : transformationDataSource.getAll()) {
						if (mask.isTransformationEnabled(reference.getId())) {
							mergeMask.withTransformation(reference.getId());
						}
					}
				}
			} else {
				for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
						.getTransformationDataSources()) {
					for (TransformationReference reference : transformationDataSource.getAll()) {
						if (!mask.isTransformationEnabled(reference.getId())) {
							mergeMask.withoutTransformation(reference.getId());
						}
					}
				}
			}
		}
		return mergeMask;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(TransformationMasksDialogMessages.CREATION_TITLE.message());
	}

	@Override
	protected Point getInitialSize() {
		return new Point(600, 450);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		super.createButtonsForButtonBar(parent);
		TransformationMaskCreationDialog.this.getButton(IDialogConstants.OK_ID).setEnabled(false);
	}

	/**
	 * The name of the new created mask
	 * 
	 * @return the name of the new created mask.
	 */
	public String getNewMaskName() {
		return this.newMaskName;
	}

}
