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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
 * Preference Page, for the transformation registration/enablement
 * 
 * @author mvanbesien
 * 
 */
public class TransformationsPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

	// List of levels
	private List<Level> levels = new ArrayList<Level>();

	// Temp value corresponding to the level chosen by user.
	private int newLevel = -1;

	private TransformationsTreeCheckListener checkListener;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		levels.clear();
		levels.add(Level.OFF);
		levels.add(Level.SEVERE);
		levels.add(Level.WARNING);
		levels.add(Level.INFO);
		levels.add(Level.CONFIG);
		levels.add(Level.FINE);
		levels.add(Level.FINER);
		levels.add(Level.FINEST);
		levels.add(Level.ALL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse
	 * .swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText("About : ");
		descriptionGroup.setLayout(new FormLayout());

		Label descriptionLabel = new Label(descriptionGroup, SWT.WRAP);
		FormDataBuilder.on(descriptionLabel).fill();

		descriptionLabel.setText(TransformationsPreferencesMessages.DESCRIPTION.message());

		Group levelGroup = new Group(composite, SWT.NONE);
		levelGroup.setLayout(new FormLayout());

		// Create elements
		Label label = new Label(levelGroup, SWT.NONE);
		label.setText(TransformationsPreferencesMessages.LOGGER_LEVEL.message());

		final Combo combo = new Combo(levelGroup, SWT.READ_ONLY);
		for (Level level : levels)
			combo.add(level.getName());
		combo.select(levels.indexOf(OptimusLogger.logger.getLevel()));

		// Add a listener to save the user selection
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newLevel = combo.getSelectionIndex();
			}
		});

		Label label2 = new Label(composite, SWT.NONE);
		label2.setText(TransformationsPreferencesMessages.TRANSFORMATIONS_LABEL.message());

		// Manage the layout
		Tree tree = new Tree(composite, SWT.CHECK | SWT.BORDER);
		
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(tree);
		treeViewer.setLabelProvider(new TransformationsTreeLabelProvider());
		treeViewer.setContentProvider(new TransformationsTreeContentsProvider());
		treeViewer.setCheckStateProvider(new TransformationsTreeCheckProvider());
		treeViewer.addDoubleClickListener(new TransformationsTreeDoubleClickListener());
		this.checkListener = new TransformationsTreeCheckListener(treeViewer);
		treeViewer.addCheckStateListener(this.checkListener);
		treeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		Button exportButton = new Button(composite, SWT.PUSH);
		exportButton.setText(TransformationsPreferencesMessages.EXPORT_BUTTON.message());
		exportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationsPreferencesImex.exportPreferences();
			}
		});

		Button importButton = new Button(composite, SWT.PUSH);
		importButton.setText(TransformationsPreferencesMessages.IMPORT_BUTTON.message());
		importButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationsPreferencesImex.importPreferences();
				treeViewer.refresh();
			}
		});

		FormDataBuilder.on(descriptionGroup).top().left().right();

		FormDataBuilder.on(label2).left().top(descriptionGroup, 15).right();
		FormDataBuilder.on(tree).left().top(label2).bottom(levelGroup).right(exportButton);
		FormDataBuilder.on(exportButton).top(label2).right().width(90).height(25);
		FormDataBuilder.on(importButton).top(exportButton).right().width(90).height(25);

		FormDataBuilder.on(levelGroup).bottom().left().right(exportButton);

		FormDataBuilder.on(label).left().top().bottom().right(combo);
		FormDataBuilder.on(combo).top().width(120).bottom().right();

		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performApply()
	 */
	@Override
	protected void performApply() {
		this.checkListener.apply();
		super.performApply();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performDefaults()
	 */
	@Override
	protected void performDefaults() {
		super.performDefaults();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performCancel()
	 */
	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	@Override
	public boolean performOk() {
		int currentLevel = levels.indexOf(OptimusLogger.logger.getLevel());
		if (this.newLevel != currentLevel && this.newLevel > -1) {
			OptimusLogger.logger.setLevel(levels.get(newLevel));
			this.getPreferenceStore().setValue(Activator.LOGGER_LEVEL_KEY, this.levels.get(this.newLevel).getName());
		}
		return super.performOk();
	}

}
