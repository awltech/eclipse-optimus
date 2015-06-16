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
import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create postfix expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PostfixExpressionBuilder {

	/** The build postfix expression */
	private PostfixExpression buildPostfixExpression;

	/**
	 * Give a postfix expression builder
	 * 
	 * @return a new postfix expression builder.
	 */
	public static PostfixExpressionBuilder builder() {
		return new PostfixExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private PostfixExpressionBuilder() {
		this.buildPostfixExpression = JavaFactory.eINSTANCE.createPostfixExpression();
	}

	/**
	 * Build a postfix expression of modisco model
	 * 
	 * @return a new postfix expression of modisco model.
	 */
	public PostfixExpression build() {
		return this.buildPostfixExpression;
	}

	/**
	 * Set the operator of the postfix expression under construction
	 * 
	 * @param operator
	 *            the operator of the postfix expression under construction.
	 * @return the builder.
	 */
	public PostfixExpressionBuilder setOperator(PostfixExpressionKind operator) {
		this.buildPostfixExpression.setOperator(operator);
		return this;
	}

	/**
	 * Set the left operand of the postfix expression under construction
	 * 
	 * @param leftOperand
	 *            the left operand of the postfix expression under construction.
	 * @return the builder.
	 */
	public PostfixExpressionBuilder setOperand(Expression leftOperand) {
		this.buildPostfixExpression.setOperand(leftOperand);
		return this;
	}

}
