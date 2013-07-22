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

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.eclipse.core.runtime.Plugin;

/**
 * Subclass of FileHandler, that logs into the metadata/plugins folder of a
 * plugin
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class MetadataHandler extends Handler {

	/**
	 * Delegate that holds the file handling.
	 */
	private FileHandler delegate = null;

	/**
	 * Pattern used for files.
	 */
	private static final String FILEPATTERN = "%s/logs/logs-%d.log";

	/**
	 * Creates new handler for plugin with formatter
	 * 
	 * @param plugin
	 * @param formatter
	 * @throws IOException
	 * @throws SecurityException
	 */
	public MetadataHandler(Plugin plugin, Formatter formatter) throws IOException, SecurityException {
		String filePath = String
				.format(FILEPATTERN, plugin.getStateLocation().toOSString(), System.currentTimeMillis());
		File file = new File(filePath);
		if (file.getParentFile() == null || !file.getParentFile().exists())
			file.getParentFile().mkdirs();

		this.delegate = new FileHandler(filePath);
		this.delegate.setFormatter(formatter);
	}

	/**
	 * Creates new Handler for plugin
	 * 
	 * @param plugin
	 * @throws IOException
	 * @throws SecurityException
	 */
	public MetadataHandler(Plugin plugin) throws IOException, SecurityException {
		this(plugin, new LoggerFormatter());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Handler#publish(java.util.logging.LogRecord)
	 */
	@Override
	public synchronized void publish(LogRecord record) {
		this.delegate.publish(record);
		this.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Handler#flush()
	 */
	@Override
	public void flush() {
		this.delegate.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.logging.Handler#close()
	 */
	@Override
	public void close() throws SecurityException {
		this.delegate.close();
	}

}
