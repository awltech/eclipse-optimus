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
package net.atos.optimus.m2m.engine.core.logging;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.atos.optimus.m2m.engine.core.Activator;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Logger externalized from Messages manager.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class OptimusM2MEngineLogger {

	/**
	 * Console instance, in which messages will be logged
	 */
	public static final Logger logger = Logger.getLogger("Optimus-M2M");

	/**
	 * Initializes the logger.
	 */
	static {
		logger.setUseParentHandlers(false);
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		String preferenceLevel = preferenceStore.getString(Activator.LOGGER_LEVEL_KEY);
		logger.setLevel(Level.WARNING);
		try {
			logger.setLevel(Level.parse(preferenceLevel));
		} catch (IllegalArgumentException iae) {
			OptimusM2MEngineMessages.LG01.log(preferenceLevel);
		}
	}

	/**
	 * Logs with provided parameters, in the internal logger
	 * 
	 * @param args
	 */
	public static void log(OptimusM2MEngineMessages message, final Object... args) {
		if (logger.getLevel().intValue() <= message.getLevel().intValue()) {
			logger.log(message.getLevel(), message.message(args));
			OptimusM2MEngineLogger.flush();
		}
	}

	private static void flush() {
		for (Handler handler : logger.getHandlers())
			handler.flush();
	}
}
