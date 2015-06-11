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
package net.atos.optimus.m2m.javaxmi.operation.instruction;

import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create return statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ReturnStatementBuilder {

	/** The build return statement */
	private ReturnStatement buildReturnStatement;

	/**
	 * Give a new return statement builder
	 * 
	 * @return a new return statement builder.
	 */
	public static ReturnStatementBuilder builder() {
		return new ReturnStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ReturnStatementBuilder() {
		this.buildReturnStatement = JavaFactory.eINSTANCE.createReturnStatement();
	}

	/**
	 * Build a return statement of modisco model
	 * 
	 * @return a new return statement of modisco model.
	 */
	public ReturnStatement build() {
		return this.buildReturnStatement;
	}

	/**
	 * Set the expression of the return statement under construction
	 * 
	 * @param expression
	 *            the expression of the return statement under construction.
	 * @return the builder.
	 */
	public ReturnStatementBuilder setExpression(Expression expression) {
		this.buildReturnStatement.setExpression(expression);
		return this;
	}

	/**
	 * Add a comment to the return statement under construction
	 * 
	 * @param comment
	 *            the comment to add to the return statement under construction.
	 * @return the builder.
	 */
	public ReturnStatementBuilder addComment(Comment comment) {
		this.buildReturnStatement.getComments().add(comment);
		return this;
	}

}
