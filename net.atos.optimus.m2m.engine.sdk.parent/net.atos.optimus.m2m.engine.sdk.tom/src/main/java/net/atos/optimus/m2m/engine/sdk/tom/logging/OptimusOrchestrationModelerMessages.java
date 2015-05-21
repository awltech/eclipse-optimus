package net.atos.optimus.m2m.engine.sdk.tom.logging;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.logging.OptimusMessage;

public enum OptimusOrchestrationModelerMessages implements OptimusMessage {
	
	// Message related to Transformation Orchestration Modeler
	OM01(Level.WARNING),OM02(Level.WARNING),OM03(Level.INFO),OM04(Level.WARNING);
	
	/**
	 * Message bundle
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("OptimusOrchestrationModelerMessages");

	/**
	 * Priority level
	 */
	private Level level;

	/**
	 * Creates new Message with level
	 * 
	 * @param level
	 */
	private OptimusOrchestrationModelerMessages(final Level level) {
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
		return MessageFormat.format(OptimusOrchestrationModelerMessages.resourceBundle.getString(this.toString()), args);
	}

}
