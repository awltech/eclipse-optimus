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
package net.atos.optimus.m2m.javaxmi.operation.array;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create array creation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayCreationBuilder {

	/** The build array creation */
	private ArrayCreation buildArrayCreation;

	/**
	 * Give an array creation builder
	 * 
	 * @return a new array creation builder.
	 */
	public static ArrayCreationBuilder builder() {
		return new ArrayCreationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ArrayCreationBuilder() {
		this.buildArrayCreation = JavaFactory.eINSTANCE.createArrayCreation();
	}

	/**
	 * Build an array creation of modisco model
	 * 
	 * @return a new array creation of modisco model.
	 */
	public ArrayCreation build() {
		return this.buildArrayCreation;
	}

	/**
	 * Add dimensions list to the array creation under construction
	 * 
	 * @param expressions
	 *            the dimensions list to add to the array creation under
	 *            construction.
	 * @return the builder.
	 */
	public ArrayCreationBuilder addDimensions(Expression... expressions) {
		EList<Expression> expressionsList = this.buildArrayCreation.getDimensions();
		for (Expression expression : expressions) {
			expressionsList.add(expression);
		}
		return this;
	}

	/**
	 * Set the initializer of the array creation under construction
	 * 
	 * @param arrayInitializer
	 *            the initializer of the array creation under construction.
	 * @return the builder.
	 */
	public ArrayCreationBuilder setInitializer(ArrayInitializer arrayInitializer) {
		this.buildArrayCreation.setInitializer(arrayInitializer);
		return this;
	}

	/**
	 * Set the type of the array creation under construction
	 * 
	 * @param type
	 *            the type of the array creation under construction.
	 * @return the builder.
	 */
	public ArrayCreationBuilder setType(TypeAccess type) {
		this.buildArrayCreation.setType(type);
		return this;
	}

}
