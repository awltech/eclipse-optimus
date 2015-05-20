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
package net.atos.optimus.m2m.engine.sdk.tom.properties.transformations;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Contains message for the transformation generation process
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum TransformationDialogMessages {

	JOBNAME, JOBTASK, JOB_SUBTASK_1, JOB_SUBTASK_2, JOB_SUBTASK_3, DIALOG_TITLE, SOURCEFOLDER_LABEL, SOURCEFOLDER_TOOLTIP, SOURCEFOLDER_BUTTON, JAVAPACK_LABEL, JAVAPACK_TOOLTIP, JAVAPACK_BUTTON, TRNCLASS_LABEL, TRNCLASS_TOOLTIP, TRNFACT_LABEL, TRNFACT_TOOLTIP, TRNELT_LABEL, TRNELT_TOOLTIP, TRNELT_BUTTON, JAVAPACK_SELECTOR_LABEL, TRNELT_SELECTOR_LABEL;

	private static ResourceBundle bundle = ResourceBundle.getBundle("TransformationGeneratorMessages");

	public String message() {
		String key = this.toString();
		return bundle.containsKey(key) ? bundle.getString(key) : key;
	}

	public String message(Object... args) {
		String key = this.toString();
		return bundle.containsKey(key) ? MessageFormat.format(bundle.getString(key), args) : key;
	}

}
