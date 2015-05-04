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
package net.atos.optimus.m2m.engine.masks.logging;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.logging.OptimusMessage;

/**
 * Class that contains prioritized messages for the transformation mask
 * extension point loader logger
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum OptimusM2MMaskMessages implements OptimusMessage {

	// Message related to Transformation mask Extension Point loading process
	TMEP01(Level.INFO), TMEP02(Level.INFO), TMEP03(Level.INFO), TMEP04(Level.WARNING), TMEP05(Level.WARNING), TMEP06(
			Level.WARNING), TMEP07(Level.INFO), TMEP08(Level.INFO), TMEP09(Level.INFO), TMEP10(Level.INFO), TMEP11(
			Level.INFO), TMEP12(Level.INFO), TMEP13(Level.INFO);

	/**
	 * Message bundle
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("OptimusM2MMaskMessages");

	/**
	 * Priority level
	 */
	private Level level;

	/**
	 * Creates new Message with level
	 * 
	 * @param level
	 */
	private OptimusM2MMaskMessages(final Level level) {
		this.level = level;
	}

	@Override
	public Level getLevel() {
		return level;
	}

	/**
	 * Logs with provided parameters, in the Optimus Logger
	 * 
	 * @param args
	 */
	@Override
	public void log(final Object... args) {
		OptimusLogger.log(this, args);
	}

	/**
	 * Gets the message that is calculated by the logger.
	 * 
	 * @param args
	 */
	@Override
	public String message(final Object... args) {
		return MessageFormat.format(OptimusM2MMaskMessages.resourceBundle.getString(this.toString()), args);
	}

}
