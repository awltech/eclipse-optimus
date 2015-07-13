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
package net.atos.optimus.m2m.javaxmi.operation.arrays;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create array initializer of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayInitializerBuilder {

	/** The build array initializer */
	private ArrayInitializer buildArrayInitializer;

	/**
	 * Give an array initializer builder
	 * 
	 * @return a new array initializer builder.
	 */
	public static ArrayInitializerBuilder builder() {
		return new ArrayInitializerBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ArrayInitializerBuilder() {
		this.buildArrayInitializer = JavaFactory.eINSTANCE.createArrayInitializer();
	}

	/**
	 * Build an array initializer of modisco model
	 * 
	 * @return a new array initializer of modisco model.
	 */
	public ArrayInitializer build() {
		return this.buildArrayInitializer;
	}

	/**
	 * Add a list of expressions of the array initializer under construction
	 * 
	 * @param expressions
	 *            the list of expressions to add to the array initializer under
	 *            construction.
	 * @return the builder.
	 */
	public ArrayInitializerBuilder addExpression(Expression... expressions) {
		EList<Expression> expressionsList = this.buildArrayInitializer.getExpressions();
		for (Expression expression : expressions) {
			expressionsList.add(expression);
		}
		return this;
	}
	
}
