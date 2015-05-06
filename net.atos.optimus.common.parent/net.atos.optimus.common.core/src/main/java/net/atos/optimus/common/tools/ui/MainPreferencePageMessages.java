package net.atos.optimus.common.tools.ui;

import java.util.ResourceBundle;

import java.text.MessageFormat;

public enum MainPreferencePageMessages {
	DESCRIPTION, MORE_INFO, MORE_INFO_1, MORE_INFO_2, BROWSER_TEXT, LOGGER_LEVEL
	;
	
	/*
	 * ResourceBundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("MainPreferencePageMessages");
	
	/*
	 * Returns value of the message
	 */ 
	public String value() {
		if (MainPreferencePageMessages.resourceBundle == null || !MainPreferencePageMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return MainPreferencePageMessages.resourceBundle.getString(this.name());
	}
	
	/*
	 * Returns value of the formatted message
	 */ 
	public String value(Object... args) {
		if (MainPreferencePageMessages.resourceBundle == null || !MainPreferencePageMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return MessageFormat.format(MainPreferencePageMessages.resourceBundle.getString(this.name()), args);
	}
	
}
