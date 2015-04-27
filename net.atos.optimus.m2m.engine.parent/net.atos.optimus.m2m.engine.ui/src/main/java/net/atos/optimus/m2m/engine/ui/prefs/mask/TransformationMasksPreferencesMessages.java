package net.atos.optimus.m2m.engine.ui.prefs.mask;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/** Preferences messages for transformation masks preferences
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum TransformationMasksPreferencesMessages {
	
	CHECK_BOX_LABEL,MASK_DESCRIPTION,TRANSFORMATIONS_LABEL;
	
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("TransformationMasksPreferencesMessages");
	
	public String message() {
		return resourceBundle.getString(this.toString());
	}
	
	public String message(Object... args) {
		return MessageFormat.format(message(), args);
	}

}
