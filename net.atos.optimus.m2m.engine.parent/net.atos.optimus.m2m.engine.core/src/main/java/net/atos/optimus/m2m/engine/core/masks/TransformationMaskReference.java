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

import java.util.UUID;

/**
 * This class is mainly a wrapper. It is used to name the transformation masks,
 * for user reusability
 * 
 * @author mvanbesien
 * @since 1.1
 * 
 */
public class TransformationMaskReference {

	/**
	 * Mask ID
	 */
	protected long id;

	/**
	 * Mask Name
	 */
	protected String name;

	/**
	 * Mask description
	 */
	protected String description;

	/**
	 * Mask implementation
	 */
	protected ITransformationMask implementation;

	/**
	 * Create a new transformation mask reference
	 * 
	 * @param name
	 *            the mask name.
	 * @param description
	 *            the mask description.
	 * @param implementation
	 *            the implementation of the mask.
	 */
	public TransformationMaskReference(String name, String description, ITransformationMask implementation) {
		this.id = UUID.randomUUID().getMostSignificantBits();
		this.name = name;
		this.description = description;
		this.implementation = implementation;
	}

	/**
	 * @return transformation mask implementation
	 */
	public ITransformationMask getImplementation() {
		return implementation;
	}
	
	/**
	 * @return transformation mask ID
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * @return transformation mask name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return transformation mask description
	 */
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TransformationMaskReference) {
			return this.getId() == ((TransformationMaskReference) obj).getId();
		}
		return false;
	}

}
