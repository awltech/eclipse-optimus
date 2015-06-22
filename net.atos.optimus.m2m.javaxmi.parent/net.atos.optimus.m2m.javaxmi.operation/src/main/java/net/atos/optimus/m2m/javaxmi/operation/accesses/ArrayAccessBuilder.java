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
package net.atos.optimus.m2m.javaxmi.operation.accesses;

import org.eclipse.gmt.modisco.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create array access of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayAccessBuilder {

	/** The build array access */
	private ArrayAccess buildArrayAccess;

	/**
	 * Give an array access builder
	 * 
	 * @return a new array access builder.
	 */
	public static ArrayAccessBuilder builder() {
		return new ArrayAccessBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ArrayAccessBuilder() {
		this.buildArrayAccess = JavaFactory.eINSTANCE.createArrayAccess();
	}

	/**
	 * Build an array access of modisco model
	 * 
	 * @return a new array access of modisco model.
	 */
	public ArrayAccess build() {
		return this.buildArrayAccess;
	}

	/**
	 * Set the array of the array access under construction
	 * 
	 * @param array
	 *            the array of the array access under construction.
	 * @return the builder.
	 */
	public ArrayAccessBuilder setArray(Expression array) {
		this.buildArrayAccess.setArray(array);
		return this;
	}

	/**
	 * Set the index of the array access under construction
	 * 
	 * @param index
	 *            the index of the array access under construction.
	 * @return the builder.
	 */
	public ArrayAccessBuilder setIndex(Expression index) {
		this.buildArrayAccess.setIndex(index);
		return this;
	}

}
