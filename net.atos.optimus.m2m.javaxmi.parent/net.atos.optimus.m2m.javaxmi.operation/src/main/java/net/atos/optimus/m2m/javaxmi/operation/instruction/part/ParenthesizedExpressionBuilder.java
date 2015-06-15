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
package net.atos.optimus.m2m.javaxmi.operation.instruction.part;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ParenthesizedExpression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create parenthesized expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ParenthesizedExpressionBuilder {

	/** The build parenthesized expression */
	private ParenthesizedExpression buildParenthesizedExpression;

	/**
	 * Give an parenthesized expression builder
	 * 
	 * @return a new parenthesized expression builder.
	 */
	public static ParenthesizedExpressionBuilder builder() {
		return new ParenthesizedExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ParenthesizedExpressionBuilder() {
		this.buildParenthesizedExpression = JavaFactory.eINSTANCE.createParenthesizedExpression();
	}

	/**
	 * Build an parenthesized expression of modisco model
	 * 
	 * @return a new parenthesized expression of modisco model.
	 */
	public ParenthesizedExpression build() {
		return this.buildParenthesizedExpression;
	}

	/**
	 * Set the expression of the parenthesized expression under construction
	 * 
	 * @param expression
	 *            the expression of the parenthesized expression under
	 *            construction.
	 * @return the builder.
	 */
	public ParenthesizedExpressionBuilder setExpression(Expression expression) {
		this.buildParenthesizedExpression.setExpression(expression);
		return this;
	}

}
