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

import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;

/**
 * Central Console Manager for XA Applications
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class OptimusConsoleManager {

	/**
	 * Name of the console
	 */
	private static final String CONSOLE_NAME = "Optimus Console";

	/**
	 * Internal instance of Eclipse console
	 */
	private MessageConsole console;

	/**
	 * Singleton holder class
	 * 
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 * 
	 */
	private static class SingletonHolder {
		private static OptimusConsoleManager instance = new OptimusConsoleManager();
	}

	/**
	 * Private constructor. Internally creates and initializes console instance.
	 */
	private OptimusConsoleManager() {
		this.console = new MessageConsole(CONSOLE_NAME, null);
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { this.console });
		this.console.initialize();
	}

	/**
	 * @return instance of Console Manager
	 */
	public static OptimusConsoleManager getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * This method is used to link the provided logger with this console
	 * instance. It uses the provided formatter for formatting messages.
	 * 
	 * @param logger
	 * @param formatter
	 */
	public void register(Logger logger, Formatter formatter) {
		if (formatter == null)
			throw new IllegalStateException("Cannot register logger without formatter");
		StreamHandler streamHandler = new StreamHandler(this.console.newMessageStream(), formatter) {

			@Override
			public synchronized void publish(LogRecord record) {
				super.publish(record);
				this.flush();
			}
		};
		logger.addHandler(streamHandler);
	}

	/**
	 * This method is used to link the provided logger with this console
	 * instance. It uses a default message formatter.
	 * 
	 * @see LoggerFormatter
	 * @param logger
	 */
	public void register(Logger logger) {
		this.register(logger, new LoggerFormatter());
	}

	/**
	 * Programmatically shows console in editor
	 */
	public void show() {
		this.console.activate();
	}

	/**
	 * Clears the contents of the console
	 */
	public void clear() {
		Display display = Display.getCurrent() == null ? Display.getDefault() : Display.getCurrent();
		display.asyncExec(new Runnable() {

			public void run() {
				OptimusConsoleManager.this.console.getDocument().set("");
			}
		});
	}

}
