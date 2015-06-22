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
package net.atos.optimus.m2m.javaxmi.operation.types;

import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create array type of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayTypeBuilder {

	/** The build array type */
	private ArrayType buildArrayType;

	/**
	 * Give an array type builder
	 * 
	 * @return a new array type builder.
	 */
	public static ArrayTypeBuilder builder() {
		return new ArrayTypeBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ArrayTypeBuilder() {
		this.buildArrayType = JavaFactory.eINSTANCE.createArrayType();
	}

	/**
	 * Build an array type of modisco model
	 * 
	 * @return a new array type of modisco model.
	 */
	public ArrayType build() {
		return this.buildArrayType;
	}

	/**
	 * Set the dimensions of the array type under construction
	 * 
	 * @param dimensions
	 *            the dimensions of the array type under construction.
	 * @return the builder.
	 */
	public ArrayTypeBuilder setDimensions(int dimensions) {
		this.buildArrayType.setDimensions(dimensions);
		return this;
	}

	/**
	 * Set the element type of the array type under construction
	 * 
	 * @param type
	 *            the element type of the array type under construction.
	 * @return the builder.
	 */
	public ArrayTypeBuilder setElementType(TypeAccess type) {
		this.buildArrayType.setElementType(type);
		return this;
	}

}
