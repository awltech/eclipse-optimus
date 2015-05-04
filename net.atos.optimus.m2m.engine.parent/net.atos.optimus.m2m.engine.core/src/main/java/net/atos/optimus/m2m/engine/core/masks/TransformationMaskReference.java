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
	 * Mask Name
	 */
	private String name;

	/**
	 * Mask description
	 */
	private String description;

	/**
	 * Mask implementation
	 */
	private ITransformationMask implementation;

	/**
	 * Tell if the mask is editable or not
	 */
	private boolean editable;

	/**
	 * Create a new tranformation mask reference
	 * 
	 * @param name
	 *            the mask name.
	 * @param description
	 *            the mask description.
	 * @param implementation
	 *            the implementation of the mask.
	 * @param editable
	 *            tell if the mask is editable or not.
	 */
	public TransformationMaskReference(String name, String description, ITransformationMask implementation,
			boolean editable) {
		this.name = name;
		this.description = description;
		this.implementation = implementation;
		this.editable = editable;
	}

	/**
	 * @return transformation mask implementation
	 */
	public ITransformationMask getImplementation() {
		return implementation;
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

	/**
	 * @return true if the mask is editable, false otherwise.
	 */
	public boolean isEditable() {
		return this.editable;
	}

}
