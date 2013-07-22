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
package net.atos.optimus.m2t.java.core;

import java.io.StringWriter;
import java.io.Writer;

/**
 * File Handlers used in post processors. This class is just a wrapper,
 * containing source contents, and file path.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class FileHandler {

	/**
	 * Writer instance
	 */
	private Writer writer;

	/**
	 * Path to the file that will contain the written element
	 */
	private String filePath;

	/**
	 * Creates new file Handler
	 * 
	 * @param writer
	 * @param filePath
	 */
	public FileHandler(Writer writer, String filePath) {
		this.writer = writer;
		this.filePath = filePath;
	}

	/**
	 * @return path to the file that will contain the written source
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * @return contained writer
	 */
	public Writer getWriter() {
		return this.writer;
	}

	/**
	 * Sets the writer in this FileHandler
	 * 
	 * @param writer
	 */
	public void setWriter(StringWriter writer) {
		this.writer = writer;
	}

}
