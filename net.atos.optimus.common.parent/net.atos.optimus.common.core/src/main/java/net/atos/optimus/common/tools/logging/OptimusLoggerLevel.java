package net.atos.optimus.common.tools.logging;

import java.util.logging.Level;

/**
 * 
 * Logging levels for Optimus Logger. This is reorganised as an enumeration
 * mainly for management convenience.
 * 
 * @author mvanbesien
 * @since 1.1
 *
 */
public enum OptimusLoggerLevel {

	OFF(Level.OFF, "Off"), SEVERE(Level.SEVERE, "Severe"), WARNING(Level.WARNING, "Warning"), INFO(Level.INFO, "Info"), CONFIG(
			Level.CONFIG, "Config"), FINE(Level.FINE, "Fine"), FINER(Level.FINER, "Finer"), FINEST(Level.FINEST,
			"Finest"), ALL(Level.ALL, "All");

	/**
	 * Effective Logging level
	 */
	private final Level level;

	/**
	 * User-friendly value for level
	 */
	private final String label;

	/*
	 * 
	 */
	OptimusLoggerLevel(final Level level, final String label) {
		this.level = level;
		this.label = label;
	}

	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @return
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * Returns OptimusLoggerLevel for level passed as parameter. If input is not recognized or null, null is returned.
	 * @param level
	 * @return
	 */
	public static OptimusLoggerLevel getLoggerLevel(Level level) {
		if (level == Level.OFF) {
			return OptimusLoggerLevel.OFF;
		}
		if (level == Level.SEVERE) {
			return OptimusLoggerLevel.SEVERE;
		}
		if (level == Level.WARNING) {
			return OptimusLoggerLevel.WARNING;
		}
		if (level == Level.INFO) {
			return OptimusLoggerLevel.INFO;
		}
		if (level == Level.CONFIG) {
			return OptimusLoggerLevel.CONFIG;
		}
		if (level == Level.FINE) {
			return OptimusLoggerLevel.FINE;
		}
		if (level == Level.FINER) {
			return OptimusLoggerLevel.FINER;
		}
		if (level == Level.FINEST) {
			return OptimusLoggerLevel.FINEST;
		}
		if (level == Level.ALL) {
			return OptimusLoggerLevel.ALL;
		}
		return null;
	}

}
