package net.atos.optimus.m2m.engine.sdk.tom.extension.logging;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;

import net.atos.optimus.common.tools.logging.OptimusLogger;
import net.atos.optimus.common.tools.logging.OptimusMessage;

/**
 * Class that contains prioritized messages for the dependency diagram logger
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum DependencyDiagramMessages implements OptimusMessage {

	// Messages related to Extension Point loading process
	EP01(Level.INFO), EP02(Level.INFO), EP03(Level.INFO), EP04(Level.INFO), EP05(Level.INFO), EP06(Level.INFO), EP07(
			Level.INFO), EP08(Level.SEVERE), EP09(Level.INFO), EP10(Level.SEVERE), EP11(Level.INFO), EP12(Level.SEVERE), EP13(
			Level.INFO), EP14(Level.INFO), EP15(Level.INFO), EP16(Level.INFO), EP17(Level.SEVERE), EP18(Level.SEVERE), EP19(
			Level.WARNING);

	/**
	 * Priority level
	 */
	protected Level level;

	/**
	 * Message bundle
	 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("DependencyDiagramMessages");

	@Override
	public Level getLevel() {
		return this.level;
	}

	/**
	 * Creates new Message with level
	 * 
	 * @param level
	 */
	private DependencyDiagramMessages(final Level level) {
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
		return MessageFormat.format(DependencyDiagramMessages.resourceBundle.getString(this.toString()), args);
	}

}
