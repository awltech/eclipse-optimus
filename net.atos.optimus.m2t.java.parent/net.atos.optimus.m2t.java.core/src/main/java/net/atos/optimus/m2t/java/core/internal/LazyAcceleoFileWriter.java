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
package net.atos.optimus.m2t.java.core.internal;

import java.io.File;
import java.io.IOException;

import org.eclipse.acceleo.engine.generation.writers.AbstractAcceleoWriter;

/**
 * Internal class used to delay the creation of AcceleoFileWriters be Modisco.
 * 
 * This has be done, so the AcceleoFileWriters are never created in case the
 * VetoStrategy refuses the writing of the source code (because I/O FileWriters
 * are created in the constructors of the Acceleo File Writers)
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class LazyAcceleoFileWriter {

	/**
	 * Enumeration used to determine which constructor and parameters should be
	 * used to create the underlying Acceleo Writers.
	 * 
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 * 
	 */
	private static enum AcceleoFileWriterType {
		TYPE1, TYPE2, TYPE3, TYPE4;
	}

	/**
	 * Type of Writer to be created
	 */
	private AcceleoFileWriterType type;

	/**
	 * Underlying AcceleoFileWriter instance
	 */
	private AbstractAcceleoWriter writer;

	/**
	 * Target File
	 */
	private File target;

	/**
	 * Append model
	 */
	private boolean appendMode;

	/**
	 * Charset
	 */
	private String charset;

	/**
	 * FilePath
	 */
	private String filePath;

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param target
	 * @param appendMode
	 * @throws IOException
	 */
	public LazyAcceleoFileWriter(File target, boolean appendMode) throws IOException {
		this.target = target;
		this.appendMode = appendMode;
		this.type = AcceleoFileWriterType.TYPE1;
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param target
	 * @param appendMode
	 * @param charset
	 * @throws IOException
	 */
	public LazyAcceleoFileWriter(File target, boolean appendMode, String charset) throws IOException {
		this.target = target;
		this.appendMode = appendMode;
		this.charset = charset;
		this.type = AcceleoFileWriterType.TYPE2;
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param filePath
	 */
	public LazyAcceleoFileWriter(String filePath) {
		this.filePath = filePath;
		this.type = AcceleoFileWriterType.TYPE3;
	}

	/**
	 * Create new XA Acceleo Writer
	 * 
	 * @param filePath
	 * @param charset
	 */
	public LazyAcceleoFileWriter(String filePath, String charset) {
		this.filePath = filePath;
		this.charset = charset;
		this.type = AcceleoFileWriterType.TYPE4;
	}

	/**
	 * Returns underlying AcceleoFileWriter. If it has not been created yet,
	 * this method will internally do so.
	 * 
	 * @return AcceleoFileWriter instance
	 * @throws IOException
	 */
	public AbstractAcceleoWriter getAcceleoFileWriter() throws IOException {
		if (writer == null) {
			switch (this.type) {
			case TYPE1:
				this.writer = new NoJMergeAcceleoFileWriter(target, appendMode);
				break;
			case TYPE2:
				this.writer = new NoJMergeAcceleoFileWriter(target, appendMode, charset);
				break;
			case TYPE3:
				this.writer = new NoJMergeAcceleoFileWriter(filePath);
				break;
			case TYPE4:
				this.writer = new NoJMergeAcceleoFileWriter(filePath, charset);
				break;
			}
		}
		return writer;
	}

	/**
	 * @return the target path of the source code
	 */
	public String getTargetPath() {
		switch (this.type) {
		case TYPE1:
		case TYPE2:
			return target.getAbsolutePath();
		case TYPE3:
		case TYPE4:
			return filePath;
		}
		return null;
	}
}
