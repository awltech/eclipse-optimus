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

import net.atos.optimus.m2t.merger.java.core.JavaCodeMerger;


/**
 * Implementation of default Java Code merger.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class DefaultJavaCodeMerger extends JavaCodeMerger {

	/*
	 * Default name of XA Generators
	 */
	private static final String GENERATORNAME = "XA";

	/*
	 * Generator name 
	 */
	private String name;

	/**
	 * Creates default code merger with provided name
	 * @param name
	 */
	public DefaultJavaCodeMerger(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.atos.xa.common.jdt.merger.JavaCodeMerger#getGeneratorName()
	 */
	@Override
	protected String getGeneratorName() {
		return name != null ? name : GENERATORNAME;
	}

}
