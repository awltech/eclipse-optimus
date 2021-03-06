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
package net.atos.optimus.m2m.javaxmi.operation.instructions.elementary;

import net.atos.optimus.m2m.javaxmi.operation.instructions.Instruction;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary.PostfixExpressionBuilder;

import org.eclipse.gmt.modisco.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;

/**
 * The purpose of such class is to help with the creation of postfix operations
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
	public Instruction build() {
		return new Instruction(this.buildPostfixOperation);
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
	 * Set the field array operand of the postfix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current postfix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public PostfixOperationHelper setFieldArrayOperand(String fieldName, int... indexes) {
		this.buildPostfixOperation.setOperand(ArrayInstructionHelper.builderWithField(fieldName).addIndex(indexes)
				.build().getExpression());
		return this;
	}

	/**
	 * Set the field array operand of the postfix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current postfix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public PostfixOperationHelper setFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildPostfixOperation.setOperand(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
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

	/**
	 * Set the variable array operand of the postfix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current postfix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public PostfixOperationHelper setVariableArrayOperand(String variableName, int... indexes) {
		this.buildPostfixOperation.setOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the variable array operand of the postfix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current postfix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public PostfixOperationHelper setVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildPostfixOperation.setOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the operand of the postfix operation under construction
	 * 
	 * @param operand
	 *            the operand of the current postfix operation.
	 * @return the helper.
	 */
	public PostfixOperationHelper setOperand(IElementaryInstruction operand) {
		this.buildPostfixOperation.setOperand(operand.getExpression());
		return this;
	}

}
