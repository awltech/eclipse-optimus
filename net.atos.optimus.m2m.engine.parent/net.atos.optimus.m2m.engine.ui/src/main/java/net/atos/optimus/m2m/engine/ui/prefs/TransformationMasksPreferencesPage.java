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
import net.atos.optimus.m2m.engine.masks.IEditableTransformationMask;
import net.atos.optimus.m2m.engine.masks.UserTransformationMaskTool;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationMaskCreationDialog;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeCheckListener;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeCheckProvider;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeDoubleClickListener;
import net.atos.optimus.m2m.engine.ui.prefs.tree.TransformationMasksTreeLabelProvider;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
	public static final int DESCRIPTION_SIZE = 35;

	/** The combo box displaying the available transformation masks */
	protected Combo transformationMaskCombo;

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
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Label selectionLabel = new Label(composite, SWT.NONE);
		selectionLabel.setText(TransformationMasksPreferencesMessages.CHECK_BOX_LABEL.message());
		Button exportButton = new Button(composite, SWT.PUSH);
		exportButton.setText(TransformationMasksPreferencesMessages.EXPORT_BUTTON.message());
		Button importButton = new Button(composite, SWT.PUSH);
		importButton.setText(TransformationMasksPreferencesMessages.IMPORT_BUTTON.message());
		final Button deleteButton = new Button(composite, SWT.NONE);
		deleteButton.setText(TransformationMasksPreferencesMessages.DELETE_BUTTON.message());
		Button creationButton = new Button(composite, SWT.NONE);
		creationButton.setText(TransformationMasksPreferencesMessages.CREATION_BUTTON.message());

		this.transformationMaskCombo = new Combo(composite, SWT.READ_ONLY);
		for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskDataSources()) {
			for (TransformationMaskReference transformationMaskReference : transformationMaskDataSource.getAllMasks()) {
				this.transformationMaskCombo.add(transformationMaskReference.getName());
			}
		}
		TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getPreferredTransformationMask();
		int indexMaskName = 0;
		String[] maskNames = this.transformationMaskCombo.getItems();
		while (indexMaskName < maskNames.length - 1
				&& !transformationMaskReference.getName().equals(maskNames[indexMaskName])) {
			indexMaskName++;
		}
		this.transformationMaskCombo.select(indexMaskName);
		transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(this.transformationMaskCombo.getText());

		this.tmpTransformationMask = new TemporaryTransformationMask(transformationMaskReference.getImplementation());
		deleteButton.setEnabled(transformationMaskReference.getImplementation() instanceof IEditableTransformationMask);

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText(TransformationMasksPreferencesMessages.MASK_DESCRIPTION.message());
		descriptionGroup.setLayout(new FormLayout());

		final Label descriptionLabel = new Label(descriptionGroup, SWT.WRAP);
		descriptionLabel.setText(transformationMaskReference.getDescription());
		Label transformationListLabel = new Label(composite, SWT.NONE);
		transformationListLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATIONS_LABEL.message());

		Tree transformationsTree = new Tree(composite, SWT.CHECK | SWT.BORDER);
		this.transformationsTreeViewer = new CheckboxTreeViewer(transformationsTree);
		this.transformationsTreeViewer.setLabelProvider(new TransformationMasksTreeLabelProvider(
				this.tmpTransformationMask));
		this.transformationsTreeViewer.setContentProvider(new TransformationMasksTreeContentsProvider());
		this.transformationsTreeViewer.setCheckStateProvider(new TransformationMasksTreeCheckProvider(
				this.tmpTransformationMask));
		this.transformationsTreeViewer.addDoubleClickListener(new TransformationMasksTreeDoubleClickListener());
		this.checkListener = new TransformationMasksTreeCheckListener(this.transformationsTreeViewer,
				this.tmpTransformationMask);
		this.transformationsTreeViewer.addCheckStateListener(this.checkListener);
		this.transformationsTreeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		FormDataBuilder.on(selectionLabel).top();
		FormDataBuilder.on(exportButton).top(selectionLabel).right();
		FormDataBuilder.on(importButton).top(selectionLabel).right(exportButton);
		FormDataBuilder.on(deleteButton).top(selectionLabel).right(importButton);
		FormDataBuilder.on(creationButton).top(selectionLabel).right(deleteButton);
		FormDataBuilder.on(this.transformationMaskCombo).top(selectionLabel).left().right(creationButton);

		FormDataBuilder.on(descriptionLabel).height(TransformationMasksPreferencesPage.DESCRIPTION_SIZE);
		FormDataBuilder.on(descriptionGroup).top(this.transformationMaskCombo).horizontal();
		FormDataBuilder.on(transformationListLabel).top(descriptionGroup);

		FormDataBuilder.on(transformationsTree).top(transformationListLabel).bottom().horizontal();

		// Add a listener to display the description of the selected mask
		this.transformationMaskCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo
								.getText());
				TransformationMasksPreferencesPage.this.tmpTransformationMask
						.resetTransformationMask(transformationMaskReference.getImplementation());
				deleteButton.setEnabled(transformationMaskReference.getImplementation() instanceof IEditableTransformationMask);

				descriptionLabel.setText(transformationMaskReference.getDescription());
				TransformationMasksPreferencesPage.this.transformationsTreeViewer.refresh();
			}
		});

		this.transformationMaskCombo.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				String transformationName = TransformationMasksPreferencesPage.this.transformationMaskCombo.getText();
				TransformationMasksPreferencesPage.this.transformationMaskCombo.removeAll();
				TransformationMasksPreferencesPage.this.setErrorMessage(null);
				for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskDataSources()) {
					for (TransformationMaskReference transformationMaskReference : transformationMaskDataSource
							.getAllMasks()) {
						TransformationMasksPreferencesPage.this.transformationMaskCombo.add(transformationMaskReference
								.getName());
					}
				}
				int indexMaskName = 0;
				String[] maskNames = TransformationMasksPreferencesPage.this.transformationMaskCombo.getItems();
				while (indexMaskName < maskNames.length - 1 && !transformationName.equals(maskNames[indexMaskName])) {
					indexMaskName++;
				}
				TransformationMasksPreferencesPage.this.transformationMaskCombo.select(indexMaskName);
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
				TransformationMasksPreferencesImex.importTransformationMask();
			}
		});

		deleteButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String maskName = TransformationMasksPreferencesPage.this.transformationMaskCombo.getText();
				if (maskName.equals(TransformationMaskDataSourceManager.INSTANCE.getPreferredTransformationMask()
						.getName())) {
					TransformationMasksPreferencesPage.this.setErrorMessage("Can't delete the current preferred mask");
				} else {
					UserTransformationMaskTool.suppressUserTransformationMask(maskName);
					TransformationMasksPreferencesPage.this.transformationMaskCombo.select(0);
					TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
							.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo
									.getText());
					deleteButton.setEnabled(transformationMaskReference.getImplementation() instanceof IEditableTransformationMask);
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
				transformationMaskCreationDialog.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		return composite;
	}

	@Override
	protected void performDefaults() {
		this.tmpTransformationMask.resetTransformationMask(this.tmpTransformationMask.getOrginalTransformationMask());
		this.transformationsTreeViewer.refresh();
	}

	@Override
	public boolean performCancel() {
		this.performDefaults();
		return super.performCancel();
	};

	@Override
	protected void performApply() {
		this.checkListener.apply();
		this.getPreferenceStore().putValue(TransformationMaskDataSourceManager.PREFERED_MASK_STORE_KEY,
				this.transformationMaskCombo.getText());
	};

	@Override
	public boolean performOk() {
		this.performApply();
		return super.performOk();
	}

}
