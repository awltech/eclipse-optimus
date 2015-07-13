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
import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpressionKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create prefix expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PrefixExpressionBuilder {

	/** The build prefix expression */
	private PrefixExpression buildPrefixExpression;

	/**
	 * Give a prefix expression builder
	 * 
	 * @return a new prefix expression builder.
	 */
	public static PrefixExpressionBuilder builder() {
		return new PrefixExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private PrefixExpressionBuilder() {
		this.buildPrefixExpression = JavaFactory.eINSTANCE.createPrefixExpression();
	}

	/**
	 * Build a prefix expression of modisco model
	 * 
	 * @return a new prefix expression of modisco model.
	 */
	public PrefixExpression build() {
		return this.buildPrefixExpression;
	}

	/**
	 * Set the operator of the prefix expression under construction
	 * 
	 * @param operator
	 *            the operator of the prefix expression under construction.
	 * @return the builder.
	 */
	public PrefixExpressionBuilder setOperator(PrefixExpressionKind operator) {
		this.buildPrefixExpression.setOperator(operator);
		return this;
	}

	/**
	 * Set the operand of the prefix expression under construction
	 * 
	 * @param operand
	 *            the operand of the prefix expression under construction.
	 * @return the builder.
	 */
	public PrefixExpressionBuilder setOperand(Expression operand) {
		this.buildPrefixExpression.setOperand(operand);
		return this;
	}

}
