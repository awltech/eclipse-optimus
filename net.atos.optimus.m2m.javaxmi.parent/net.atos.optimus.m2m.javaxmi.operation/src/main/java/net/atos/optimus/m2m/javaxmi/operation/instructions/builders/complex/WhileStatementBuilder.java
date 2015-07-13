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
package net.atos.optimus.m2m.javaxmi.operation.instructions.builders.complex;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.WhileStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create while statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class WhileStatementBuilder {

	/** The build while statement */
	private WhileStatement buildWhileStatement;

	/**
	 * Give a while statement builder
	 * 
	 * @return a new while statement builder.
	 */
	public static WhileStatementBuilder builder() {
		return new WhileStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private WhileStatementBuilder() {
		this.buildWhileStatement = JavaFactory.eINSTANCE.createWhileStatement();
	}

	/**
	 * Build a while statement of modisco model
	 * 
	 * @return a new while statement of modisco model.
	 */
	public WhileStatement build() {
		return this.buildWhileStatement;
	}

	/**
	 * Set the expression of the while statement under construction
	 * 
	 * @param expression
	 *            the expression of the while statement under construction.
	 * @return the builder.
	 */
	public WhileStatementBuilder setExpression(Expression expression) {
		this.buildWhileStatement.setExpression(expression);
		return this;
	}

	/**
	 * Set the body of the while statement under construction
	 * 
	 * @param body
	 *            the body of the while statement under construction.
	 * @return the builder.
	 */
	public WhileStatementBuilder setBody(Statement body) {
		this.buildWhileStatement.setBody(body);
		return this;
	}

}
