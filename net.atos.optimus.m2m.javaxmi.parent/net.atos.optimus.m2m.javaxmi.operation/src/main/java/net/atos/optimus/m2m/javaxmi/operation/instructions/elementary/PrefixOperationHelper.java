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
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary.PrefixExpressionBuilder;

import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpressionKind;

/**
 * The purpose of such class is to help with the creation of prefix operations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PrefixOperationHelper {

	/** The build prefix operation */
	private PrefixExpression buildPrefixOperation;

	/**
	 * Launch the build of a new prefix operation
	 * 
	 * @return a new helper.
	 */
	public static PrefixOperationHelper builder() {
		return new PrefixOperationHelper();
	}

	/**
	 * Private constructor : a new prefix operation
	 * 
	 */
	private PrefixOperationHelper() {
		this.buildPrefixOperation = PrefixExpressionBuilder.builder().build();
	}

	/**
	 * Give the build prefix operation
	 * 
	 * @return the build prefix operation.
	 */
	public Instruction build() {
		return new Instruction(this.buildPrefixOperation);
	}

	/**
	 * Set the operator of the prefix operation under construction
	 * 
	 * @param operator
	 *            the operator of the current prefix operation.
	 * @return the helper.
	 */
	public PrefixOperationHelper setOperator(PrefixExpressionKind operator) {
		this.buildPrefixOperation.setOperator(operator);
		return this;
	}

	/**
	 * Set the operand of the prefix operation under construction
	 * 
	 * @param operand
	 *            the operand of the current prefix operation.
	 * @return the helper.
	 */
	public PrefixOperationHelper setOperand(boolean operand) {
		this.buildPrefixOperation.setOperand(ElementaryInstructionHelper.createInstruction(operand).getExpression());
		return this;
	}

	/**
	 * Set the field operand of the prefix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current prefix operation.
	 * @return the helper.
	 */
	public PrefixOperationHelper setFieldOperand(String fieldName) {
		this.buildPrefixOperation.setOperand(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the field array operand of the prefix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current prefix operation.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public PrefixOperationHelper setFieldArrayOperand(String fieldName, int... indexes) {
		this.buildPrefixOperation.setOperand(ArrayInstructionHelper.builderWithField(fieldName).addIndex(indexes)
				.build().getExpression());
		return this;
	}

	/**
	 * Set the field array operand of the prefix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current prefix operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public PrefixOperationHelper setFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildPrefixOperation.setOperand(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable operand of the prefix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current prefix
	 *            operation.
	 * @return the helper.
	 */
	public PrefixOperationHelper setVariableOperand(String variableName) {
		this.buildPrefixOperation.setOperand(ElementaryInstructionHelper.createVariableInstruction(variableName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable array operand of the prefix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current prefix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public PrefixOperationHelper setVariableArrayOperand(String variableName, int... indexes) {
		this.buildPrefixOperation.setOperand(ArrayInstructionHelper.builderWithVariable(variableName).addIndex(indexes)
				.build().getExpression());
		return this;
	}

	/**
	 * Set the variable array operand of the prefix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current prefix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public PrefixOperationHelper setVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildPrefixOperation.setOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the operand of the prefix operation under construction
	 * 
	 * @param operand
	 *            the operand of the current prefix operation.
	 * @return the helper.
	 */
	public PrefixOperationHelper setOperand(IElementaryInstruction operand) {
		this.buildPrefixOperation.setOperand(operand.getExpression());
		return this;
	}

}
