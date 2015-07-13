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
package net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create infix expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InfixExpressionBuilder {

	/** The build infix expression */
	private InfixExpression buildInfixExpression;

	/**
	 * Give an infix expression builder
	 * 
	 * @return a new infix expression builder.
	 */
	public static InfixExpressionBuilder builder() {
		return new InfixExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private InfixExpressionBuilder() {
		this.buildInfixExpression = JavaFactory.eINSTANCE.createInfixExpression();
	}

	/**
	 * Build an infix expression of modisco model
	 * 
	 * @return a new infix expression of modisco model.
	 */
	public InfixExpression build() {
		return this.buildInfixExpression;
	}

	/**
	 * Set the operator of the infix expression under construction
	 * 
	 * @param operator
	 *            the operator of the infix expression under construction.
	 * @return the builder.
	 */
	public InfixExpressionBuilder setOperator(InfixExpressionKind operator) {
		this.buildInfixExpression.setOperator(operator);
		return this;
	}

	/**
	 * Set the left operand of the infix expression under construction
	 * 
	 * @param leftOperand
	 *            the left operand of the infix expression under construction.
	 * @return the builder.
	 */
	public InfixExpressionBuilder setLeftOperand(Expression leftOperand) {
		this.buildInfixExpression.setLeftOperand(leftOperand);
		return this;
	}

	/**
	 * Set the right operand of the infix expression under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the infix expression under construction.
	 * @return the builder.
	 */
	public InfixExpressionBuilder setRightOperand(Expression rightOperand) {
		this.buildInfixExpression.setRightOperand(rightOperand);
		return this;
	}

}
