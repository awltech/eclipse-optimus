package net.atos.optimus.m2t.java.ui.internal;

import net.atos.optimus.common.tools.swt.FormDataBuilder;
import net.atos.optimus.m2t.java.core.config.JavaGenerationPreferencesHandler;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class JavaGenerationPreferencesPage extends PreferencePage implements IWorkbenchPreferencePage {

	private Button importDeorgDisabledButton;

	@Override
	public void init(IWorkbench workbench) {
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FormLayout());

		this.importDeorgDisabledButton = new Button(composite, SWT.CHECK);
		importDeorgDisabledButton.setSelection(JavaGenerationPreferencesHandler.INSTANCE
				.isImportDesorganisationDisabled());
		FormDataBuilder.on(importDeorgDisabledButton).top().horizontal();
		importDeorgDisabledButton.setText(JavaGenerationPreferencesPageMessages.IMPORTS_DEORG_BUTTON_TEXT.value());
		importDeorgDisabledButton.setToolTipText(JavaGenerationPreferencesPageMessages.IMPORTS_DEORG_BUTTON_TOOLTIP
				.value());
		return composite;
	}

	@Override
	protected void performApply() {
		super.performApply();
	}

	@Override
	public boolean performOk() {
		JavaGenerationPreferencesHandler.INSTANCE.disableImportDesorganisation(this.importDeorgDisabledButton
				.getSelection());
		return super.performOk();
	}

	@Override
	public boolean performCancel() {
		return super.performCancel();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		this.importDeorgDisabledButton.setSelection(JavaGenerationPreferencesHandler.INSTANCE
				.isImportDesorganisationDisabled());
		this.importDeorgDisabledButton.update();
	}

}
