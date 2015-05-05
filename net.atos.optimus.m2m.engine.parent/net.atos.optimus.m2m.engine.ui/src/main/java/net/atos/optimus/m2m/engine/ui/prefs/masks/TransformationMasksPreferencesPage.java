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
import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.masks.TemporaryTransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationsTreeContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationsTreeDoubleClickListener;

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
		FormDataBuilder.on(selectionLabel).top();

		Button creationButton = new Button(composite, SWT.NONE);
		creationButton.setText(TransformationMasksPreferencesMessages.CREATION_BUTTON.message());
		FormDataBuilder.on(creationButton).top(selectionLabel).right();

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

		FormDataBuilder.on(this.transformationMaskCombo).top(selectionLabel).left().right(creationButton);

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText(TransformationMasksPreferencesMessages.MASK_DESCRIPTION.message());
		descriptionGroup.setLayout(new FormLayout());

		final Label descriptionLabel = new Label(descriptionGroup, SWT.WRAP);
		FormDataBuilder.on(descriptionLabel).height(TransformationMasksPreferencesPage.DESCRIPTION_SIZE);
		descriptionLabel.setText(transformationMaskReference.getDescription());

		FormDataBuilder.on(descriptionGroup).top(this.transformationMaskCombo).horizontal();

		Label transformationListLabel = new Label(composite, SWT.NONE);
		transformationListLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATIONS_LABEL.message());
		FormDataBuilder.on(transformationListLabel).top(descriptionGroup);

		final Tree tree = new Tree(composite, SWT.CHECK | SWT.BORDER);
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(tree);

		treeViewer.setLabelProvider(new TransformationMasksTreeLabelProvider(this.tmpTransformationMask));
		treeViewer.setContentProvider(new TransformationsTreeContentsProvider());
		treeViewer.setCheckStateProvider(new TransformationMasksTreeCheckProvider(this.tmpTransformationMask));
		treeViewer.addDoubleClickListener(new TransformationsTreeDoubleClickListener());
		this.checkListener = new TransformationMasksTreeCheckListener(treeViewer, this.tmpTransformationMask);
		treeViewer.addCheckStateListener(this.checkListener);
		treeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		FormDataBuilder.on(tree).top(transformationListLabel).bottom().horizontal();

		// Add a listener to display the description of the selected mask
		this.transformationMaskCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(TransformationMasksPreferencesPage.this.transformationMaskCombo
								.getText());
				TransformationMasksPreferencesPage.this.tmpTransformationMask
						.resetTransformationMask(transformationMaskReference.getImplementation());

				descriptionLabel.setText(transformationMaskReference.getDescription());
				treeViewer.refresh();
			}
		});

		// Add listener to ask for a new transformation mask name
		creationButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskCreationDialog transformationMaskCreationDialog = new TransformationMaskCreationDialog(
						getShell());
				if (transformationMaskCreationDialog.open() == Window.OK) {
					TransformationMasksPreferencesPage.this.transformationMaskCombo
							.add(transformationMaskCreationDialog.getValue());
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		return composite;
	}

	@Override
	public boolean performOk() {
		this.checkListener.apply();
		this.getPreferenceStore().putValue(TransformationMaskDataSourceManager.PREFERED_MASK_STORE_KEY,
				this.transformationMaskCombo.getText());
		return super.performOk();
	}

}
