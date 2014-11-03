package net.atos.optimus.m2t.java.core.config;

import net.atos.optimus.m2t.java.core.internal.Activator;

import org.eclipse.jface.preference.IPreferenceStore;

public enum JavaGenerationPreferencesHandler {
	INSTANCE;

	private static final String IMPORTS_DEORG_DISABLED_KEY = "imports_desorganisation_disabled";

	private static IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();

	public void disableImportDesorganisation(boolean value) {
		JavaGenerationPreferencesHandler.preferenceStore.setValue(IMPORTS_DEORG_DISABLED_KEY, value);
	}

	public boolean isImportDesorganisationDisabled() {
		return JavaGenerationPreferencesHandler.preferenceStore.getBoolean(IMPORTS_DEORG_DISABLED_KEY);
	}
}
