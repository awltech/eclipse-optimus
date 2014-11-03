/**
 *
 */
package net.atos.optimus.m2t.java.ui.internal;

import java.util.Locale;
import java.util.ResourceBundle;

import java.text.MessageFormat;

/**
 * Enumeration containing internationalisation-related messages and API.
 *
 * @generated com.worldline.awltech.i18ntools.wizard
 */
public enum JavaGenerationPreferencesPageMessages {
	IMPORTS_DEORG_BUTTON_TEXT("IMPORTS_DEORG_BUTTON_TEXT"), IMPORTS_DEORG_BUTTON_TOOLTIP("IMPORTS_DEORG_BUTTON_TOOLTIP")
	;

	/*
	 * Value of the key
	 */
	private final String messageKey;

	/*
	 * Constant ResourceBundle instance
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
			"JavaGenerationPreferencesPageMessages", Locale.getDefault());

	/**
	 * Private Enumeration Literal constructor
	 * 
	 * @param messageKey
	 *            value
	 */
	private JavaGenerationPreferencesPageMessages(final String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the message associated with the current value
	 */
	public String value() {
		if (JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE == null
				|| !JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE.getString(this.messageKey);
	}

	/**
	 * Formats and returns the message associated with the current value.
	 *
	 * @see java.text.MessageFormat
	 * @param parameters
	 *            to use during formatting phase
	 * @return formatted message
	 */
	public String value(final Object... args) {
		if (JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE == null
				|| !JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return MessageFormat.format(JavaGenerationPreferencesPageMessages.RESOURCE_BUNDLE.getString(this.messageKey),
				args);
	}

}
