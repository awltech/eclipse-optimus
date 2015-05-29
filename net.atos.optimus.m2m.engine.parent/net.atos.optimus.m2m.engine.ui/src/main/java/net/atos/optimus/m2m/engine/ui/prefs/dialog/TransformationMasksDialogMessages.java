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
package net.atos.optimus.m2m.engine.ui.prefs.dialog;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Messages for input dialog in transformation mask preference
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum TransformationMasksDialogMessages {

	CREATION_TITLE, CREATION_MESSAGE, CREATION_DESCRIPTION, EXTENSION_MESSAGE, NAME_CONFLICT, RENAME_TITLE, RENAME_OLD_NAME, RENAME_NEW_NAME, RENAME_INFO, DELETION_TITLE, DELETION_MESSAGE, PREFERRED_MASK_WARNING;

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("TransformationMasksDialogMessages");

	public String message() {
		return resourceBundle.getString(this.toString());
	}

	public String message(Object... args) {
		return MessageFormat.format(message(), args);
	}

}
