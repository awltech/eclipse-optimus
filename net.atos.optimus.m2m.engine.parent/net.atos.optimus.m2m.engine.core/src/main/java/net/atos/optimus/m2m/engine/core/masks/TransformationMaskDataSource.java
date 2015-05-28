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
package net.atos.optimus.m2m.engine.core.masks;

import java.util.Collection;

/**
 * Transformation Mask Data Source interface. The purpose of this mask data
 * source is to provide the runtime the list of available transformations masks,
 * referenced through the application
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public abstract class TransformationMaskDataSource {

	/**
	 * The name of the transformation mask data source
	 */
	private final String name;

	/**
	 * Creates new transformation mask data source instance with name
	 * 
	 * @param name
	 *            the name of the transformation mask data source.
	 */
	public TransformationMaskDataSource(String name) {
		this.name = name;
	}

	/**
	 * The name of the transformation mask data source
	 * 
	 * @return the name of the current transformation mask data source.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the list of all the transformations masks
	 * 
	 * @return the list of all the transformations masks.
	 */
	public abstract Collection<TransformationMaskReference> getAllMasks();

}
