package net.atos.optimus.m2m.engine.ctxinject.internal;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.logging.OptimusMessage;

public enum OptimusM2MEngineCtxInjectMessages implements OptimusMessage {
	// Messages related to Context Injection process
	CI01(Level.FINE), CI02(Level.FINE), CI03(Level.FINE), CI04(Level.FINE), CI05(Level.INFO), CI06(Level.INFO), CI07(
			Level.INFO), CI08(Level.INFO), CI09(Level.INFO), CI10(Level.INFO), CI11(Level.WARNING), CI12(Level.WARNING), CI13(
			Level.WARNING), CI14(Level.WARNING), CI15(Level.WARNING), CI16(Level.INFO), CI17(Level.SEVERE);

	/**
	 * Priority level
	 */
	private Level level;

	/**
	 * Message bundle
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("OptimusM2MEngineCtxInjectMessages");

	@Override
	public Level getLevel() {
		return level;
	}

	/**
	 * Creates new Message with level
	 * 
	 * @param level
	 */
	private OptimusM2MEngineCtxInjectMessages(final Level level) {
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
		return MessageFormat.format(OptimusM2MEngineCtxInjectMessages.resourceBundle.getString(this.toString()), args);
	}

}
