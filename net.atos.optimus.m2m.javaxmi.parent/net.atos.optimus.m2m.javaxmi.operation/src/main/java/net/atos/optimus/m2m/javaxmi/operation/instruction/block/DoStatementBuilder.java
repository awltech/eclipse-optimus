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
package net.atos.optimus.m2m.javaxmi.operation.instruction.block;

import org.eclipse.gmt.modisco.java.DoStatement;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create do statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class DoStatementBuilder {

	/** The build do statement */
	private DoStatement buildDoStatement;

	/**
	 * Give a do statement builder
	 * 
	 * @return a new do statement builder.
	 */
	public static DoStatementBuilder builder() {
		return new DoStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private DoStatementBuilder() {
		this.buildDoStatement = JavaFactory.eINSTANCE.createDoStatement();
	}

	/**
	 * Build a do statement of modisco model
	 * 
	 * @return a new do statement of modisco model.
	 */
	public DoStatement build() {
		return this.buildDoStatement;
	}

	/**
	 * Set the expression of the do statement under construction
	 * 
	 * @param expression
	 *            the expression of the do statement under construction.
	 * @return the builder.
	 */
	public DoStatementBuilder setExpression(Expression expression) {
		this.buildDoStatement.setExpression(expression);
		return this;
	}

	/**
	 * Set the body of the do statement under construction
	 * 
	 * @param body
	 *            the body of the do statement under construction.
	 * @return the builder.
	 */
	public DoStatementBuilder setBody(Statement body) {
		this.buildDoStatement.setBody(body);
		return this;
	}

}
