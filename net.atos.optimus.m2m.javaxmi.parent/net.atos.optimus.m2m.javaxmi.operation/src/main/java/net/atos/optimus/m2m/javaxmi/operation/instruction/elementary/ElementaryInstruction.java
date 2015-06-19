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
package net.atos.optimus.m2m.javaxmi.operation.instruction.elementary;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.ReturnStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.CastExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.InstanceOfExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.ParenthesizedExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.ComplexInstruction;

import org.eclipse.gmt.modisco.java.Expression;

/**
 * Models an elementary instruction : wrapper of Expression in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ElementaryInstruction implements IElementaryInstruction {

	/** The expression */
	private Expression expression;

	/**
	 * Constructor of elementary instruction
	 * 
	 * @param expression
	 *            the expression.
	 */
	public ElementaryInstruction(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public ElementaryInstruction withParentheses() {
		return new ElementaryInstruction(ParenthesizedExpressionBuilder.builder().setExpression(this.getExpression())
				.build());
	}

	@Override
	public ComplexInstruction convertToReturnInstruction() {
		return new ComplexInstruction(ReturnStatementBuilder.builder().setExpression(this.getExpression()).build());
	}

	@Override
	public ElementaryInstruction convertToCastInstruction(String castName) {
		return new ElementaryInstruction(CastExpressionBuilder.builder().setExpression(this.getExpression())
				.setType(TypeAccessHelper.createTypeAccess(castName)).build());
	}

	@Override
	public ElementaryInstruction convertToInstanceOfInstruction(String typeName) {
		return new ElementaryInstruction(InstanceOfExpressionBuilder.builder().setLeftOperand(this.getExpression())
				.setRightOperand(TypeAccessHelper.createTypeAccess(typeName)).build());
	}

}
