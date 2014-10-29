/**
 *
 */
package net.atos.optimus.m2m.engine.sdk.wizards.parentinfo;

import java.util.Locale;
import java.util.ResourceBundle;

import java.text.MessageFormat;

/**
 * Enumeration containing internationalisation-related messages and API.
 *
 * @generated com.worldline.awltech.i18ntools.wizard
 */
public enum DisplayParentInformationMessages {
	DIALOG_TITLE("DIALOG_TITLE"), MESSAGE_NULL_ELEMENT("MESSAGE_NULL_ELEMENT"), MESSAGE_NULL_PARENT("MESSAGE_NULL_PARENT"), MESSAGE_INFO("MESSAGE_INFO"), IS("IS"), IS_NOT("IS_NOT")
	;

	/*
	 * Value of the key
	 */
	private final String messageKey;

	/*
	 * Constant ResourceBundle instance
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("DisplayParentInformationMessages",
			Locale.getDefault());

	/**
	 * Private Enumeration Literal constructor
	 * 
	 * @param messageKey
	 *            value
	 */
	private DisplayParentInformationMessages(final String messageKey) {
		this.messageKey = messageKey;
	}

	/**
	 * @return the message associated with the current value
	 */
	public String value() {
		if (DisplayParentInformationMessages.RESOURCE_BUNDLE == null
				|| !DisplayParentInformationMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return DisplayParentInformationMessages.RESOURCE_BUNDLE.getString(this.messageKey);
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
		if (DisplayParentInformationMessages.RESOURCE_BUNDLE == null
				|| !DisplayParentInformationMessages.RESOURCE_BUNDLE.containsKey(this.messageKey)) {
			return "!!" + this.messageKey + "!!";
		}
		return MessageFormat.format(DisplayParentInformationMessages.RESOURCE_BUNDLE.getString(this.messageKey), args);
	}

}
