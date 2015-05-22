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

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Preferences messages for transformation masks preferences
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum TransformationMasksPreferencesMessages {

	IMPORT_BUTTON, IMPORT_WINDOW, EXPORT_BUTTON, EXPORT_WINDOW, PREFERRED_MASK_LABEL, TRANSFORMATION_MASKS_LABEL, NON_EDITABLE_MASK, DELETE_BUTTON, CREATION_BUTTON, MASK_DESCRIPTION, TRANSFORMATIONS_LABEL;

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("TransformationMasksPreferencesMessages");

	public String message() {
		return resourceBundle.getString(this.toString());
	}

	public String message(Object... args) {
		return MessageFormat.format(message(), args);
	}

}
