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
package net.atos.optimus.m2m.engine.ui.prefs;

import java.io.IOException;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TemporaryTransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.masks.EditableTransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.IEditableTransformationMask;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationMaskCreationDialog;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationMaskDeletionDialog;
import net.atos.optimus.m2m.engine.ui.prefs.masks.list.TransformationMasksCheckProvider;
import net.atos.optimus.m2m.engine.ui.prefs.masks.list.TransformationMasksContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.masks.list.TransformationMasksLabelProvider;
import net.atos.optimus.m2m.engine.ui.prefs.transformations.tree.TransformationsCheckProvider;
import net.atos.optimus.m2m.engine.ui.prefs.transformations.tree.TransformationsContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.transformations.tree.TransformationsLabelProvider;
import net.atos.optimus.m2m.engine.ui.prefs.transformations.tree.TransformationsTreeCheckListener;
import net.atos.optimus.m2m.engine.ui.prefs.transformations.tree.TransformationsTreeDoubleClickListener;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.xml.sax.SAXException;

/**
 * Preference Page, for the transformation mask configuration
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMasksPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

	/** The height size of the transformation masks table */
	public static final int MASK_TABLE_HEIGHT = 70;

	/** The height size of description windows */
	public static final int DESCRIPTION_HEIGHT = 70;

	/** The width size of buttons */
	public static final int BUTTON_WIDTH = 100;

	/** The label holding the preferred mask */
	protected Label currentPreferredMaskLabel;

	/** The text holding the description of the mask */
	protected Text descriptionText;

	/** The delete mask button */
	protected Button deleteButton;

	/** The table viewer holding transformation masks */
	protected CheckboxTableViewer transformationMasksTableViewer;

	/** The tree viewer holding transformations */
	protected CheckboxTreeViewer transformationsTreeViewer;

	/** The temporary transformation mask */
	private TemporaryTransformationMask tmpTransformationMask;

	/**
	 * The check listener used to detect user actions of check boxes associated
	 * to the transformation enables/disables in the mask
	 */
	private TransformationsTreeCheckListener checkListener;

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		// Find the preferred transformation reference and initialize the
		// temporary transformation mask
		TransformationMaskReference preferredTransformationMask = TransformationMaskDataSourceManager.INSTANCE
				.getPreferredTransformationMask();
		this.tmpTransformationMask = new TemporaryTransformationMask(preferredTransformationMask.getImplementation());

		// SWT controls creation
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label transformationMasksLabel = new Label(composite, SWT.NONE);
		transformationMasksLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATION_MASKS_LABEL.message());

		Table transformationMasksTable = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.SINGLE);
		this.transformationMasksTableViewer = new CheckboxTableViewer(transformationMasksTable);
		this.transformationMasksTableViewer.setLabelProvider(new TransformationMasksLabelProvider());
		this.transformationMasksTableViewer.setContentProvider(new TransformationMasksContentsProvider());
		this.transformationMasksTableViewer.setCheckStateProvider(new TransformationMasksCheckProvider());
		this.transformationMasksTableViewer.setInput(TransformationMaskDataSourceManager.INSTANCE);

		Composite preferredMaskComposite = new Composite(composite, SWT.NONE);
		preferredMaskComposite.setLayout(new FormLayout());
		Label preferredMaskLabel = new Label(preferredMaskComposite, SWT.NONE);
		preferredMaskLabel.setText(TransformationMasksPreferencesMessages.PREFERRED_MASK_LABEL.message());
		this.currentPreferredMaskLabel = new Label(preferredMaskComposite, SWT.NONE);

		Composite buttonComposite = new Composite(composite, SWT.NONE);
		buttonComposite.setLayout(new FormLayout());
		Button creationButton = new Button(buttonComposite, SWT.PUSH);
		creationButton.setText(TransformationMasksPreferencesMessages.CREATION_BUTTON.message());
		this.deleteButton = new Button(buttonComposite, SWT.PUSH);
		this.deleteButton.setText(TransformationMasksPreferencesMessages.DELETE_BUTTON.message());
		Button importButton = new Button(buttonComposite, SWT.PUSH);
		importButton.setText(TransformationMasksPreferencesMessages.IMPORT_BUTTON.message());
		Button exportButton = new Button(buttonComposite, SWT.PUSH);
		exportButton.setText(TransformationMasksPreferencesMessages.EXPORT_BUTTON.message());

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText(TransformationMasksPreferencesMessages.MASK_DESCRIPTION.message());
		descriptionGroup.setLayout(new FormLayout());

		this.descriptionText = new Text(descriptionGroup, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		Label transformationListLabel = new Label(composite, SWT.NONE);
		transformationListLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATIONS_LABEL.message());

		Tree transformationsTree = new Tree(composite, SWT.CHECK);
		this.transformationsTreeViewer = new CheckboxTreeViewer(transformationsTree);
		this.transformationsTreeViewer.setLabelProvider(new TransformationsLabelProvider());
		this.transformationsTreeViewer.setContentProvider(new TransformationsContentsProvider());
		this.transformationsTreeViewer.setCheckStateProvider(new TransformationsCheckProvider(
				this.tmpTransformationMask));
		this.transformationsTreeViewer.addDoubleClickListener(new TransformationsTreeDoubleClickListener());
		this.checkListener = new TransformationsTreeCheckListener(this.transformationsTreeViewer,
				this.tmpTransformationMask);
		this.transformationsTreeViewer.addCheckStateListener(this.checkListener);
		this.transformationsTreeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		// Layout configuration
		FormDataBuilder.on(transformationMasksLabel).top().horizontal();
		FormDataBuilder.on(transformationMasksTable).top(transformationMasksLabel).horizontal()
				.height(TransformationMasksPreferencesPage.MASK_TABLE_HEIGHT);

		FormDataBuilder.on(preferredMaskLabel).vertical().left();
		FormDataBuilder.on(this.currentPreferredMaskLabel).vertical().left(preferredMaskLabel).right();
		FormDataBuilder.on(preferredMaskComposite).top(transformationMasksTable).horizontal();

		FormDataBuilder.on(creationButton).vertical().left().width(TransformationMasksPreferencesPage.BUTTON_WIDTH);
		FormDataBuilder.on(this.deleteButton).vertical().left(creationButton)
				.width(TransformationMasksPreferencesPage.BUTTON_WIDTH);
		FormDataBuilder.on(importButton).vertical().left(this.deleteButton)
				.width(TransformationMasksPreferencesPage.BUTTON_WIDTH);
		FormDataBuilder.on(exportButton).vertical().left(importButton)
				.width(TransformationMasksPreferencesPage.BUTTON_WIDTH);

		FormDataBuilder.on(buttonComposite).top(preferredMaskComposite).horizontal();

		FormDataBuilder.on(this.descriptionText).vertical().horizontal();
		FormDataBuilder.on(descriptionGroup).horizontal().top(buttonComposite)
				.height(TransformationMasksPreferencesPage.DESCRIPTION_HEIGHT);

		FormDataBuilder.on(transformationListLabel).top(descriptionGroup);

		FormDataBuilder.on(transformationsTree).top(transformationListLabel).bottom().horizontal();

		// Adding listeners
		this.transformationMasksTableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getElement() instanceof TransformationMaskReference) {
					TransformationMaskReference transformationMaskReference = (TransformationMaskReference) event
							.getElement();
					TransformationMaskDataSourceManager.INSTANCE
							.setPreferredTransformationMask(transformationMaskReference);
					TransformationMasksPreferencesPage.this.refreshTransformationMaskPreferencePage();
				}
			}
		});

		this.transformationMasksTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				TransformationMasksPreferencesPage.this.refreshTransformationMaskPreferencePage();
			}
		});

		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TransformationMasksPreferencesImex.exportTransformationMask(TransformationMasksPreferencesPage.this
						.getSelectedTransformationMaskReference());
			}
		});

		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String maskName = TransformationMasksPreferencesImex.importTransformationMask();
					TransformationMasksPreferencesPage.this.refreshTransformationMaskPreferencePage();
					int index = 0;
					TransformationMaskReference transformationMaskReference;
					do {
						transformationMaskReference = (TransformationMaskReference) TransformationMasksPreferencesPage.this.transformationMasksTableViewer
								.getElementAt(index);
						index++;
					} while (transformationMaskReference != null
							&& !maskName.equals(transformationMaskReference.getName()));
					if (transformationMaskReference != null) {
						TransformationMasksPreferencesPage.this.transformationMasksTableViewer
								.setSelection(new StructuredSelection(transformationMaskReference));
					}
				} catch (SAXException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.deleteButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMasksPreferencesPage.this
						.getSelectedTransformationMaskReference();
				if (transformationMaskReference instanceof EditableTransformationMaskReference) {
					Dialog transformationMaskDeletionDialog = new TransformationMaskDeletionDialog(getShell(),
							(EditableTransformationMaskReference) transformationMaskReference);
					if (transformationMaskDeletionDialog.open() == Window.OK) {
						transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
								.getPreferredTransformationMask();
						TransformationMasksPreferencesPage.this
								.refreshTransformationMaskPreferencePage(transformationMaskReference);
					}
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		creationButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				TransformationMaskCreationDialog transformationMaskCreationDialog = new TransformationMaskCreationDialog(
						getShell());
				if (transformationMaskCreationDialog.open() == Window.OK) {
					TransformationMasksPreferencesPage.this.refreshTransformationMaskPreferencePage();
					String maskName = transformationMaskCreationDialog.getNewMaskName();
					int index = 0;
					TransformationMaskReference transformationMaskReference;
					do {
						transformationMaskReference = (TransformationMaskReference) TransformationMasksPreferencesPage.this.transformationMasksTableViewer
								.getElementAt(index);
						index++;
					} while (transformationMaskReference != null
							&& !maskName.equals(transformationMaskReference.getName()));
					if (transformationMaskReference != null) {
						TransformationMasksPreferencesPage.this.transformationMasksTableViewer
								.setSelection(new StructuredSelection(transformationMaskReference));
					}
				}

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// Refresh dynamic contents
		this.refreshTransformationMaskPreferencePage(preferredTransformationMask);

		return composite;
	}

	/**
	 * Refresh the transformation mask preference page according to the current
	 * selected transformation mask
	 * 
	 */
	protected void refreshTransformationMaskPreferencePage() {
		TransformationMaskReference transformationMaskReference = this.getSelectedTransformationMaskReference();

		// Update the preferred mask
		this.currentPreferredMaskLabel.setText(TransformationMaskDataSourceManager.INSTANCE
				.getPreferredTransformationMask().getName());

		// Update the deletion button
		this.deleteButton
				.setEnabled(transformationMaskReference.getImplementation() instanceof IEditableTransformationMask);

		// Update the description of the mask
		this.descriptionText.setText(transformationMaskReference.getDescription());
		this.descriptionText.setEditable(transformationMaskReference instanceof EditableTransformationMaskReference);

		// Reset the temporary mask
		this.tmpTransformationMask.resetTransformationMask(transformationMaskReference.getImplementation());

		// Update the tree with transformations list
		this.transformationsTreeViewer.refresh();

		// Update the table with transformation masks list
		this.transformationMasksTableViewer.refresh();
	}

	/**
	 * Refresh the transformation mask preference page according to a mask
	 * reference
	 * 
	 * @param transformationMaskReference
	 *            the transformation mask currently chosen.
	 */
	protected void refreshTransformationMaskPreferencePage(TransformationMaskReference transformationMaskReference) {
		// Update the selection of the transformation masks list
		this.transformationMasksTableViewer.setSelection(new StructuredSelection(transformationMaskReference));
	}

	@Override
	protected void performDefaults() {
		this.refreshTransformationMaskPreferencePage(this.getSelectedTransformationMaskReference());
	}

	@Override
	public boolean performCancel() {
		this.performDefaults();
		return super.performCancel();
	};

	@Override
	protected void performApply() {
		TransformationMaskReference transformationMaskReference = this.getSelectedTransformationMaskReference();
		ITransformationMask mask = this.tmpTransformationMask.getOriginalTransformationMask();
		if (transformationMaskReference instanceof EditableTransformationMaskReference) {
			((EditableTransformationMaskReference) transformationMaskReference).setDescription(this.descriptionText
					.getText());
		}
		/* Save the changes in transformation enabled/disabled */
		this.checkListener.apply();
	};

	@Override
	public boolean performOk() {
		this.performApply();
		return super.performOk();
	}

	/**
	 * Get the selected transformation mask reference in the transformation mask
	 * table
	 * 
	 * @return the selected transformation mask reference in the transformation
	 *         mask table.
	 */
	private TransformationMaskReference getSelectedTransformationMaskReference() {
		IStructuredSelection selection = (IStructuredSelection) this.transformationMasksTableViewer.getSelection();
		if (!selection.isEmpty() && selection.getFirstElement() instanceof TransformationMaskReference) {
			return (TransformationMaskReference) selection.getFirstElement();
		}
		return null;
	}

}
