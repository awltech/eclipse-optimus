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
	ML01(Level.INFO), ML02(Level.INFO), ML03(Level.INFO), ML04(Level.WARNING), ML05(Level.WARNING), ML06(Level.WARNING), ML07(
			Level.INFO), ML08(Level.INFO), ML09(Level.INFO), ML10(Level.INFO), ML11(Level.INFO), ML12(Level.INFO), ML13(
			Level.INFO),

	// Message related to Specified by User Transformation Mask
	UM01(Level.INFO), UM02(Level.WARNING), UM03(Level.INFO), UM04(Level.INFO), UM05(Level.WARNING), UM06(Level.WARNING), UM07(
			Level.SEVERE), UM08(Level.SEVERE), UM09(Level.INFO), UM10(Level.WARNING), UM11(Level.WARNING), UM12(
			Level.INFO), UM13(Level.WARNING), UM14(Level.WARNING), UM15(Level.WARNING), UM16(Level.WARNING), UM17(
			Level.FINE), UM18(Level.FINE), UM19(Level.INFO), UM20(Level.INFO);

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
