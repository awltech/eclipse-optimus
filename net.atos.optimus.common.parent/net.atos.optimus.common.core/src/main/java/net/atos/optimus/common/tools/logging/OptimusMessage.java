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
package net.atos.optimus.common.tools.logging;

import java.util.logging.Level;

/**
 * Interface determining the logging messages behaviour.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.2
 * 
 */
public interface OptimusMessage {

	/**
	 * @return level of the current processed message
	 */
	Level getLevel();

	/**
	 * Formats the current message with contextual objects passed as parameter
	 * @param args
	 * @return
	 */
	String message(Object... args);
	
	/**
	 * Logs the current message with contextual objects passed as parameter
	 * @param args
	 * @return
	 */
	public void log(final Object... args); 

}
