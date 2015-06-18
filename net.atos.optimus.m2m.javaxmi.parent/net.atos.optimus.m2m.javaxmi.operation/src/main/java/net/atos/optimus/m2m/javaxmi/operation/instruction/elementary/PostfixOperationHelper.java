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

import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.PostfixExpressionBuilder;

import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;

/**
 * The purpose of such class is to help with the creation of postfix operation
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PostfixOperationHelper {

	/** The build postfix operation */
	private PostfixExpression buildPostfixOperation;

	/**
	 * Launch the build of a new postfix operation
	 * 
	 * @return a new helper.
	 */
	public static PostfixOperationHelper builder() {
		return new PostfixOperationHelper();
	}

	/**
	 * Private constructor : a new postfix operation
	 * 
	 */
	private PostfixOperationHelper() {
		this.buildPostfixOperation = PostfixExpressionBuilder.builder().build();
	}

	/**
	 * Give the build postfix operation
	 * 
	 * @return the build postfix operation.
	 */
	public ElementaryInstruction build() {
		return new ElementaryInstruction(this.buildPostfixOperation);
	}

	/**
	 * Set the operator of the postfix operation under construction
	 * 
	 * @param operator
	 *            the operator of the current postfix operation.
	 * @return the helper.
	 */
	public PostfixOperationHelper setOperator(PostfixExpressionKind operator) {
		this.buildPostfixOperation.setOperator(operator);
		return this;
	}

	/**
	 * Set the field operand of the postfix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current postfix
	 *            operation.
	 * @return the helper.
	 */
	public PostfixOperationHelper setFieldOperand(String fieldName) {
		this.buildPostfixOperation.setOperand(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable operand of the postfix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current postfix
	 *            operation.
	 * @return the helper.
	 */
	public PostfixOperationHelper setVariableOperand(String variableName) {
		this.buildPostfixOperation.setOperand(ElementaryInstructionHelper.createVariableInstruction(variableName)
				.getExpression());
		return this;
	}

}
