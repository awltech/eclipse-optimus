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
package net.atos.optimus.m2t.merger.java.core.internal;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.atos.optimus.common.tools.logging.OptimusConsoleManager;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class MergerLogger {

	private static Logger logger = Logger.getLogger("Optimus-Merger");

	static {
		OptimusConsoleManager.getInstance().register(logger);
		logger.setLevel(Level.WARNING);
		logger.setUseParentHandlers(false);
	}

	public static void log(String message) {
		logger.log(Level.INFO, message);
		for (Handler handler : logger.getHandlers())
			handler.flush();
	}

	public static boolean enabled() {
		return logger.getLevel().intValue() >= Level.INFO.intValue();
	}
}
