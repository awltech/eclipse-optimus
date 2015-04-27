package net.atos.optimus.m2m.engine.ui.prefs.mask;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationsTreeContentsProvider;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationsTreeDoubleClickListener;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationsTreeLabelProvider;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
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

public class TransformationsMaskPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

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

		final Combo combo = new Combo(composite, SWT.READ_ONLY);
		for (TransformationMaskDataSource transformationMaskDataSource : TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskDataSources()) {
			for (TransformationMaskReference transformationMaskReference : transformationMaskDataSource.getAllMasks()) {
				combo.add(transformationMaskReference.getName());
			}
		}
		combo.select(0);
		FormDataBuilder.on(combo).top(selectionLabel).horizontal();

		Group descriptionGroup = new Group(composite, SWT.NONE);
		descriptionGroup.setText(TransformationMasksPreferencesMessages.MASK_DESCRIPTION.message());
		descriptionGroup.setLayout(new FormLayout());

		final Label descriptionLabel = new Label(descriptionGroup, SWT.WRAP);
		FormDataBuilder.on(descriptionLabel).fill();
		descriptionLabel.setText(TransformationMaskDataSourceManager.INSTANCE
				.getTransformationMaskById(combo.getText()).getDescription());

		FormDataBuilder.on(descriptionGroup).top(combo).horizontal();

		Label transformationListLabel = new Label(composite, SWT.NONE);
		transformationListLabel.setText(TransformationMasksPreferencesMessages.TRANSFORMATIONS_LABEL.message());
		FormDataBuilder.on(transformationListLabel).top(descriptionGroup);

		final Tree tree = new Tree(composite, SWT.CHECK | SWT.BORDER);
		final CheckboxTreeViewer treeViewer = new CheckboxTreeViewer(tree);

		treeViewer.setLabelProvider(new TransformationsTreeLabelProvider());
		treeViewer.setContentProvider(new TransformationsTreeContentsProvider());
		treeViewer.setCheckStateProvider(new TransformationMaskTreeCheckProvider(combo));
		treeViewer.addDoubleClickListener(new TransformationsTreeDoubleClickListener());
		treeViewer.setInput(TransformationDataSourceManager.INSTANCE);

		FormDataBuilder.on(tree).top(transformationListLabel).horizontal().bottom();

		// Add a listener to display the description of the selected mask
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
						.getTransformationMaskById(combo.getText());
				descriptionLabel.setText(transformationMaskReference.getDescription());
			}
		});

		// Add a listener to display the transformations enable/disable for the
		// selected mask
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				treeViewer.refresh();
			}
		});
		
		// Attach a listener directly after the creation of the tree to lock 

		return composite;
	}

}
