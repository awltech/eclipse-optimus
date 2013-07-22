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
package net.atos.optimus.m2m.engine.sdk.wizards;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public enum Messages {
	
	JOBNAME, DIALOG_TITLE,EXTPT_GROUP_TITLE, TRN_ID, TRNSET_ID,JAVA_GROUP_TITLE, JAVAPACK_LABEL, JAVAPACK_BUTTON, TRNCLASS_LABEL, TRNFACT_LABEL, TRNELT_LABEL, TRNELT_BUTTON, OK_BUTTON, JAVAPACK_SELECTOR_LABEL, TRNELT_SELECTOR_LABEL, JOBTASK,JOB_SUBTASK_1, JOB_SUBTASK_2, JOB_SUBTASK_3, JOB_SUBTASK_4, TRN_ID_TOOLTIP,TRNSET_ID_TOOLTIP, JAVAPACK_TOOLTIP, TRNCLASS_TOOLTIP, TRNFACT_TOOLTIP,TRNELT_TOOLTIP;

	private static ResourceBundle bundle = ResourceBundle.getBundle("Messages");
	
	public String message() {
		String key = this.toString();
		return bundle.containsKey(key) ? bundle.getString(key) : key;
	}
	
	public String message(Object... args) {
		String key = this.toString();
		return bundle.containsKey(key) ? MessageFormat.format(bundle.getString(key), args) : key;
	}
}
