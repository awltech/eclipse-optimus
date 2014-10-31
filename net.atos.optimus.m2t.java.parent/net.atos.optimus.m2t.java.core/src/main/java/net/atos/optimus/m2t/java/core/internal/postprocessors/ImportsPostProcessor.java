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
package net.atos.optimus.m2t.java.core.internal.postprocessors;

import java.io.StringWriter;

import net.atos.optimus.common.tools.ltk.ImportsGenerator;
import net.atos.optimus.m2t.java.core.FileHandler;
import net.atos.optimus.m2t.java.core.IPostProcessor;

/**
 * Post process that invokes the imports cleaner
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 *
 */
public class ImportsPostProcessor implements IPostProcessor {

	/*
	 * (non-Javadoc)
	 * @see net.atos.xa.generator.m2t.java.IPostProcessor#beforeWrite(net.atos.xa.generator.m2t.java.FileHandler)
	 */
	public void beforeWrite(FileHandler fileHandler) {
		 CharSequence merged = new ImportsGenerator().execute(fileHandler.getFilePath(), fileHandler.getWriter().toString());
		 StringWriter mergedWriter = new StringWriter();
		 mergedWriter.append(merged);
		 fileHandler.setWriter(mergedWriter);
	}
}
