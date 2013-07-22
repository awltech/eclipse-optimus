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

import net.atos.optimus.common.tools.jdt.GeneratedAnnotationHelper;
import net.atos.optimus.m2t.java.core.FileHandler;
import net.atos.optimus.m2t.java.core.IPostProcessor;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class GeneratedPostProcessor implements IPostProcessor {

	@Override
	public void beforeWrite(FileHandler fileHandler) {
		String updated = GeneratedAnnotationHelper.updateHashcodeInSource(fileHandler.getWriter().toString());
		StringWriter updatedWriter = new StringWriter();
		updatedWriter.write(updated);
		fileHandler.setWriter(updatedWriter);
	}

}
