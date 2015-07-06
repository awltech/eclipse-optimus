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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create expression statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ExpressionStatementBuilder {

	/** The build expression statement */
	private ExpressionStatement buildExpressionStatement;

	/**
	 * Give an expression statement builder
	 * 
	 * @return a new expression statement builder.
	 */
	public static ExpressionStatementBuilder builder() {
		return new ExpressionStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ExpressionStatementBuilder() {
		this.buildExpressionStatement = JavaFactory.eINSTANCE.createExpressionStatement();
	}

	/**
	 * Build an expression statement of modisco model
	 * 
	 * @return a new expression statement of modisco model.
	 */
	public ExpressionStatement build() {
		return this.buildExpressionStatement;
	}

	/**
	 * Set the expression of the expression statement under construction
	 * 
	 * @param expression
	 *            the expression of the expression statement under construction.
	 * @return the builder.
	 */
	public ExpressionStatementBuilder setExpression(Expression expression) {
		this.buildExpressionStatement.setExpression(expression);
		return this;
	}

}
