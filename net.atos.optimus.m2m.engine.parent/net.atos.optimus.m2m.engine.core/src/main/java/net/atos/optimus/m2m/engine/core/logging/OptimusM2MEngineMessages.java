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

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.logging.OptimusMessage;

/**
 * Class that contains prioritized messages for the M2M logger
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public enum OptimusM2MEngineMessages implements OptimusMessage {

	// Messages related to the Transformation Engine
	TE01(Level.INFO), TE02(Level.INFO), TE03(Level.INFO), TE04(Level.INFO), TE05(Level.INFO), TE06(Level.INFO), TE07(
			Level.WARNING), TE08(Level.INFO), TE09(Level.INFO), TE10(Level.WARNING), TE11(Level.INFO), TE12(
			Level.WARNING), TE13(Level.INFO), TE14(Level.INFO), TE15(Level.INFO), TE16(Level.INFO), TE17(Level.INFO), TE18(
			Level.INFO), TE19(Level.INFO), TE20(Level.WARNING), TE21(Level.INFO), TE22(Level.SEVERE), TE23(Level.SEVERE), TE24(
			Level.INFO), TE25(Level.INFO), TE26(Level.INFO), TE27(Level.INFO), TE28(Level.INFO), TE29(Level.INFO), TE30(
			Level.INFO), TE31(Level.INFO), TE32(Level.WARNING), 

	// Messages related to the Transformation Adapters
	AD01(Level.FINE), AD02(Level.FINE), AD03(Level.SEVERE), AD04(Level.INFO), AD05(Level.INFO),

	// Messages related to the Transformation Sets
	TS01(Level.SEVERE),

	// Messages related to the TransformationFailedException
	TFE(Level.SEVERE),

	// Messages related to the Default context implementation
	DC01(Level.WARNING), DC02(Level.WARNING), DC03(Level.WARNING),

	// Messages related to logger
	LG01(Level.WARNING),

	// Messages related to Extension Point loading process
	EP01(Level.INFO), EP02(Level.INFO), EP03(Level.INFO), EP04(Level.INFO), EP05(Level.INFO), EP06(Level.INFO), EP07(
			Level.INFO), EP08(Level.INFO), EP09(Level.INFO), EP10(Level.INFO), EP11(Level.INFO), EP12(Level.INFO), EP13(
			Level.INFO), EP14(Level.SEVERE), EP15(Level.WARNING),

	// Messages related to the Transformation Data Sources registration
	DS01(Level.INFO), DS02(Level.WARNING), DS03(Level.WARNING), DS04(Level.WARNING), DS05(Level.FINE), DS06(Level.FINE),

	// Messages related to the Transformation Mask Data Sources registration
	MS01(Level.FINE),MS02(Level.INFO),MS03(Level.WARNING),MS04(Level.WARNING),MS05(Level.WARNING),MS06(Level.FINE),
	
	// Messages related to Transformation Hooks management
	TH01(Level.FINE), TH02(Level.FINE), TH03(Level.INFO), TH04(Level.WARNING), TH05(Level.WARNING), TH06(Level.WARNING), TH07(
			Level.WARNING), TH08(Level.WARNING);

	/**
	 * Priority level
	 */
	private Level level;

	/**
	 * Message bundle
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("OptimusM2MEngineMessages");

	@Override
	public Level getLevel() {
		return level;
	}

	/**
	 * Creates new Message with level
	 * 
	 * @param level
	 */
	private OptimusM2MEngineMessages(final Level level) {
		this.level = level;
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
		return MessageFormat.format(OptimusM2MEngineMessages.resourceBundle.getString(this.toString()), args);
	}

}
