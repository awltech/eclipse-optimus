package net.atos.optimus.m2m.engine.ui.prefs;

import java.util.ResourceBundle;

import java.text.MessageFormat;

public enum TransformationsInfoDialogMessages {
	ID_LABEL, DESCRIPTION_LABEL, CONTRIBUTOR_LABEL, TRNSET_LABEL, REQUIRES_LABEL, REQUIRES_ITEM_LABEL, TRN_LABEL
	;
	
	/*
	 * ResourceBundle instance
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("TransformationsInfoDialogMessages");
	
	/*
	 * Returns value of the message
	 */ 
	public String value() {
		if (TransformationsInfoDialogMessages.resourceBundle == null || !TransformationsInfoDialogMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return TransformationsInfoDialogMessages.resourceBundle.getString(this.name());
	}
	
	/*
	 * Returns value of the formatted message
	 */ 
	public String value(Object... args) {
		if (TransformationsInfoDialogMessages.resourceBundle == null || !TransformationsInfoDialogMessages.resourceBundle.containsKey(this.name()))
			return "!!"+this.name()+"!!";
		
		return MessageFormat.format(TransformationsInfoDialogMessages.resourceBundle.getString(this.name()), args);
	}
	
}
