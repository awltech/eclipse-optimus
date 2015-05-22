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

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.masks.TemporaryTransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.masks.EditableTransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.IEditableTransformationMask;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationMaskCreationDialog;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationMaskDeletionDialog;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeCheckListener;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeCheckProvider;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeDoubleClickListener;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeLabelProvider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference Page, for the transformation mask configuration
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMasksPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

	/** The height size of description windows */
	public static final int DESCRIPTION_SIZE = 70;

	/** The icon for the editable mask */
	public static final String ICONS_EDITABLE_PNG = "icons/editable.png";

	/** The icon for the non editable mask */
	public static final String ICONS_NON_EDITABLE_PNG = "icons/non-editable.png";

	/** The label holding the preferred mask */
	protected Label currentPreferredMaskLabel;

	/** The combo box displaying the available transformation masks */
	protected Combo transformationMaskCombo;

	/** The text holding the description of the mask */
	protected Text descriptionText;

	/** The label holding the image with the editable state of the mask */
	protected Label editableStateLabel;

	/** The delete mask button */
	protected Button deleteButton;

	/** The tree viewer holding transformations */
	protected CheckboxTreeViewer transformationsTreeViewer;

	/** The temporary transformation mask */
	private TemporaryTransformationMask tmpTransformationMask;

	/**
	 * The check listener used to detect user actions of check boxes associated
	 * to the transformation enables/disables in the mask
	 */
	private TransformationMasksTreeCheckListener checkListener;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected Control createContents(Composite parent) {

		// Find the preferred transformation reference and initialize the
		// temporary transformation mask
		TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getPreferredTransformationMask();
		this.tmpTransformationMask = new TemporaryTransformationMask(transformationMaskReference.getImplementation());

		// SWT controls creation
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Composite preferredMaskComposite = new Composite(composite, SWT.NONE);
		preferredMaskComposite.setLayout(new FormLayout());

		Label preferredMaskLabel = new Label(preferredMaskComposite, SWT.NONE);
		preferredMaskLabel.setText(TransformationMasksPreferencesMessages.PREFERRED_MASK_LABEL.message());

		this.currentPreferredMaskLabel = new Label(preferredMaskComposite, SWT.NONE);

		Composite maskSelectionComposite = new Composite(composite, SWT.NONE);
		maskSelectionComposite.setLayout(new FormLayout());

		Label selectionLabel = new Label(maskSelectionComposite, SWT.NONE);
		selectionLabel.setText(TransformationMasksPreferencesMessages.CHECK_BOX_LABEL.message());

		this.transformationMaskCombo = new Combo(maskSelectionComposite, SWT.READ_ONLY);

		Button creationButton = new Button(maskSelectionComposite, SWT.NONE);
		creationButton.setText(TransformationMasksPreferencesMessages.CREATION_BUTTON.message());
		this.deleteButton = new Button(maskSelectionComposite, SWT.NONE);
		this.deleteButton.setText(TransformationMasksPreferencesMessages.DELETE_BUTTON.message());
		Button importButton = new Button(maskSelectionComposite, SWT.PUSH);
		importButton.setText(TransformationMasksPreferencesMessages.IMPORT_BUTTON.message());
		Button exportButton = new Button(maskSelectionComposite, SWT.PUSH);
		exportButton.setText(TransformationMasksPreferencesMessages.EXPORT_BUTTON.message());

		this.editableStateLabel = new Label(composite, SWT.NONE);

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText(TransformationMasksPreferencesMessages.MASK_DESCRIPTION.message());
		descriptionGroup.setLayout(new FormLayout());

		this.descriptionText = new Text(descriptionGroup, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		Label transformationListLabel = new Label(composite, SWT.NONE);
		transformationListLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATIONS_LABEL.message());

		Tree transformationsTree = new Tree(composite, SWT.CHECK | SWT.BORDER);
		this.transformationsTreeViewer = new CheckboxTreeViewer(transformationsTree);
		this.transformationsTreeViewer.setLabelProvider(new TransformationMasksTreeLabelProvider());
		this.transformationsTreeViewer.setContentProvider(new TransformationMasksTreeContentsProvider());
		this.transformationsTreeViewer.setCheckStateProvider(new TransformationMasksTreeCheckProvider(
				this.tmpTransformationMask));
		this.transformationsTreeViewer.addDoubleClickListener(new TransformationMasksTreeDoubleClickListener());
		this.checkListener = new TransformationMasksTreeCheckListener(this.transformationsTreeViewer,
				this.tmpTransformationMask);
		this.transformationsTreeViewer.addCheckStateListener(this.checkListener);
		this.transformationsTreeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		// Layout configuration
		FormDataBuilder.on(preferredMaskLabel).vertical().left();
		FormDataBuilder.on(this.currentPreferredMaskLabel).vertical().left(preferredMaskLabel).right();

		FormDataBuilder.on(preferredMaskComposite).top().horizontal();

		FormDataBuilder.on(selectionLabel).top().horizontal();
		FormDataBuilder.on(this.transformationMaskCombo).top(selectionLabel).horizontal();
		FormDataBuilder.on(creationButton).top(this.transformationMaskCombo).left();
		FormDataBuilder.on(this.deleteButton).top(this.transformationMaskCombo).left(creationButton);
		FormDataBuilder.on(importButton).top(this.transformationMaskCombo).left(this.deleteButton);
		FormDataBuilder.on(exportButton).top(this.transformationMaskCombo).left(importButton);

		FormDataBuilder.on(this.editableStateLabel).right().bottom(descriptionGroup).height(70).width(70);

		FormDataBuilder.on(maskSelectionComposite).top(preferredMaskComposite, 0).left().right(editableStateLabel);

		FormDataBuilder.on(this.descriptionText).vertical().horizontal();
		FormDataBuilder.on(descriptionGroup).horizontal().top(maskSelectionComposite)
				.height(TransformationMasksPreferencesPage.DESCRIPTION_SIZE);

		FormDataBuilder.on(transformationListLabel).top(descriptionGroup);

		FormDataBuilder.on(transformationsTree).top(transformationListLabel).bottom().horizontal();

		// Refresh dynamic contents
		this.refreshTransformationMaskPreferencePage(transformationMaskReference);

		// Adding listeners
		this.transformationMaskCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo
								.getText());
				TransformationMasksPreferencesPage.this
						.refreshTransformationMaskPreferencePage(transformationMaskReference);
			}
		});

		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo
								.getText());
				TransformationMasksPreferencesImex.exportTransformationMask(transformationMaskReference);
			}
		});

		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String maskName = TransformationMasksPreferencesImex.importTransformationMask();
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(maskName);
				TransformationMasksPreferencesPage.this
						.refreshTransformationMaskPreferencePage(transformationMaskReference);
			}
		});

		this.deleteButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String maskName = TransformationMasksPreferencesPage.this.transformationMaskCombo.getText();
				Dialog transformationMaskDeletionDialog = new TransformationMaskDeletionDialog(getShell(), maskName);
				if (transformationMaskDeletionDialog.open() == Window.OK) {
					TransformationMasksPreferencesPage.this.transformationMaskCombo.remove(maskName);
					TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
							.getPreferredTransformationMask();
					TransformationMasksPreferencesPage.this
							.refreshTransformationMaskPreferencePage(transformationMaskReference);
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
					TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
							.getTransformationMaskById(transformationMaskCreationDialog.getMaskName());
					TransformationMasksPreferencesPage.this.transformationMaskCombo.add(transformationMaskReference
							.getName());
					TransformationMasksPreferencesPage.this
							.refreshTransformationMaskPreferencePage(transformationMaskReference);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		return composite;
	}

	/**
	 * Refresh the transformation mask preference page according to a mask
	 * reference
	 * 
	 * @param transformationMaskReference
	 *            the transformation mask currently chosen.
	 */
	protected void refreshTransformationMaskPreferencePage(TransformationMaskReference transformationMaskReference) {
		// Update the preferred mask
		this.currentPreferredMaskLabel.setText(TransformationMaskDataSourceManager.INSTANCE
				.getPreferredTransformationMask().getName());
		
		// Update the combo box field
		this.transformationMaskCombo.removeAll();
		for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskDataSources()) {
			for (TransformationMaskReference transformationMaskReferenceFind : transformationMaskDataSource
					.getAllMasks()) {
				if (transformationMaskReferenceFind instanceof EditableTransformationMaskReference) {
					this.transformationMaskCombo.add(transformationMaskReferenceFind.getName());
				} else {
					this.transformationMaskCombo.add(transformationMaskReferenceFind.getName() + " "
							+ TransformationMasksPreferencesMessages.NON_EDITABLE_MASK.message());
				}
			}
		}

		// Find the transformation mask index in the combo box, doesn't add the
		// mask if not found
		int indexMaskName = 0;
		String[] maskNames = this.transformationMaskCombo.getItems();
		while (indexMaskName < maskNames.length - 1
				&& !transformationMaskReference.getName().equals(
						maskNames[indexMaskName].replace(" (non editable mask)", ""))) {
			indexMaskName++;
		}
		// Update the combo box value
		this.transformationMaskCombo.select(indexMaskName);
		transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(this.transformationMaskCombo.getText());

		// Update the editable state image of the mask
		if (transformationMaskReference.getImplementation() instanceof IEditableTransformationMask) {
			this.editableStateLabel.setImage(net.atos.optimus.m2m.engine.ui.Activator.getDefault().getImage(
					TransformationMasksPreferencesPage.ICONS_EDITABLE_PNG));
		} else {
			this.editableStateLabel.setImage(net.atos.optimus.m2m.engine.ui.Activator.getDefault().getImage(
					TransformationMasksPreferencesPage.ICONS_NON_EDITABLE_PNG));
		}

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
	}

	@Override
	protected void performDefaults() {
		TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo.getText());
		this.refreshTransformationMaskPreferencePage(transformationMaskReference);
	}

	@Override
	public boolean performCancel() {
		this.performDefaults();
		return super.performCancel();
	};

	@Override
	protected void performApply() {
		/* Save the changes in transformation enabled/disabled */
		this.checkListener.apply();
		/* Update the preferred mask */
		this.getPreferenceStore().putValue(TransformationMaskDataSourceManager.PREFERED_MASK_STORE_KEY,
				this.transformationMaskCombo.getText());
		TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo.getText());
		this.refreshTransformationMaskPreferencePage(transformationMaskReference);
	};

	@Override
	public boolean performOk() {
		this.performApply();
		return super.performOk();
	}

}
