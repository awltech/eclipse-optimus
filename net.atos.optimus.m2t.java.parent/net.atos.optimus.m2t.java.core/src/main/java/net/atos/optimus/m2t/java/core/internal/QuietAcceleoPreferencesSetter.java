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
package net.atos.optimus.m2t.java.core.internal;

import org.eclipse.acceleo.common.preference.AcceleoPreferences;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class invoked only once, to change Acceleo preferences and make it quiet
 * (i.e. to disable all its notifications except warn and error)
 * 
 * @author mvanbesien
 * 
 */
public class QuietAcceleoPreferencesSetter {

	/**
	 * All methods of this class should be accessed in a static way.
	 */
	private QuietAcceleoPreferencesSetter() {
	}

	/**
	 * Runs the setting process
	 */
	public static void call() {
		IPreferenceStore thisPreferenceStore = Activator.getDefault().getPreferenceStore();
		String executionKey = QuietAcceleoPreferencesSetter.class.getName() + ".alreadycalled";
		if (!thisPreferenceStore.getBoolean(executionKey)) {

			// We enable logging
			AcceleoPreferences.switchForceDeactivationNotifications(false);

			// We disable all levels before WARN
			AcceleoPreferences.switchCancelNotifications(false);
			AcceleoPreferences.switchInfoNotifications(false);
			AcceleoPreferences.switchDebugMessages(false);
			AcceleoPreferences.switchSuccessNotifications(false);
			AcceleoPreferences.switchOKNotifications(false);

			// We enable errors and warnings
			AcceleoPreferences.switchErrorNotifications(true);
			AcceleoPreferences.switchWarningNotifications(true);

			// And we finally save the configuration
			AcceleoPreferences.save();

			// We say this method has been run. So when user changes Acceleo
			// preferences, we keep his changes.
			thisPreferenceStore.setValue(executionKey, true);
		}
	}

}
