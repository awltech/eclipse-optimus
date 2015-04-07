package net.atos.optimus.common.tools.logging;

import java.util.logging.Level;

public interface OptimusMessage {

	Level getLevel();

	String message(Object... args);
	
	public void log(final Object... args); 

}
