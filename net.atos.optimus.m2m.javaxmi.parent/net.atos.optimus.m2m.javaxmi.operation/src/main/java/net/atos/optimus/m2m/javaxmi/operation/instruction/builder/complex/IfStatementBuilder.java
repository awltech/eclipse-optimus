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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create if statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class IfStatementBuilder {

	/** The build if statement */
	private IfStatement buildIfStatement;

	/**
	 * Give an if statement builder
	 * 
	 * @return a new if statement builder.
	 */
	public static IfStatementBuilder builder() {
		return new IfStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private IfStatementBuilder() {
		this.buildIfStatement = JavaFactory.eINSTANCE.createIfStatement();
	}

	/**
	 * Build an if statement of modisco model
	 * 
	 * @return a new if statement of modisco model.
	 */
	public IfStatement build() {
		return this.buildIfStatement;
	}

	/**
	 * Set the expression of the if statement under construction
	 * 
	 * @param expression
	 *            the expression of the if statement under construction.
	 * @return the builder.
	 */
	public IfStatementBuilder setExpression(Expression expression) {
		this.buildIfStatement.setExpression(expression);
		return this;
	}

	/**
	 * Set the then statement of the if statement under construction
	 * 
	 * @param expression
	 *            the expression of the if statement under construction.
	 * @return the builder.
	 */
	public IfStatementBuilder setThenStatement(Statement statement) {
		this.buildIfStatement.setThenStatement(statement);
		return this;
	}

	/**
	 * Set the else statement of the if statement under construction
	 * 
	 * @param expression
	 *            the expression of the if statement under construction.
	 * @return the builder.
	 */
	public IfStatementBuilder setElseStatement(Statement statement) {
		this.buildIfStatement.setElseStatement(statement);
		return this;
	}

}
