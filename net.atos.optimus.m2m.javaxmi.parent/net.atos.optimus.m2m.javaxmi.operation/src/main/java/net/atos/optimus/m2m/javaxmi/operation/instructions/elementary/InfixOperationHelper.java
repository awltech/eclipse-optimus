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

import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary.InfixExpressionBuilder;

import org.eclipse.gmt.modisco.java.InfixExpression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;

/**
 * The purpose of such class is to help with the creation of infix operations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InfixOperationHelper {

	/** The build infix operation */
	private InfixExpression buildInfixOperation;

	/**
	 * Launch the build of a new infix operation
	 * 
	 * @return a new helper.
	 */
	public static InfixOperationHelper builder() {
		return new InfixOperationHelper();
	}

	/**
	 * Private constructor : a new infix operation
	 * 
	 */
	private InfixOperationHelper() {
		this.buildInfixOperation = InfixExpressionBuilder.builder().build();
	}

	/**
	 * Give the build infix operation
	 * 
	 * @return the build infix operation.
	 */
	public ElementaryInstruction build() {
		return new ElementaryInstruction(this.buildInfixOperation);
	}

	/**
	 * Set the operator of the infix operation under construction
	 * 
	 * @param operator
	 *            the operator of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setOperator(InfixExpressionKind operator) {
		this.buildInfixOperation.setOperator(operator);
		return this;
	}

	/**
	 * Set the boolean left operand of the infix operation under construction
	 * 
	 * @param leftOperand
	 *            the boolean left operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftOperand(boolean leftOperand) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createInstruction(leftOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the int left operand of the infix operation under construction
	 * 
	 * @param leftOperand
	 *            the int left operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftOperand(int leftOperand) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createInstruction(leftOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the char left operand of the infix operation under construction
	 * 
	 * @param leftOperand
	 *            the char left operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftOperand(char leftOperand) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createInstruction(leftOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the String left operand of the infix operation under construction
	 * 
	 * @param leftOperand
	 *            the String left operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftOperand(String leftOperand) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createInstruction(leftOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the left this operand of the infix operation under construction
	 * 
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftThisOperand() {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createThisInstruction().getExpression());
		return this;
	}

	/**
	 * Set the field left operand of the infix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current infix
	 *            operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftFieldOperand(String fieldName) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the field array left operand of the infix operation under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current infix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftFieldArrayOperand(String fieldName, int... indexes) {
		this.buildInfixOperation.setLeftOperand(ArrayInstructionHelper.builderWithField(fieldName).addIndex(indexes)
				.build().getExpression());
		return this;
	}

	/**
	 * Set the field array left operand of the infix operation under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current infix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildInfixOperation.setLeftOperand(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable left operand of the infix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current infix
	 *            operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftVariableOperand(String variableName) {
		this.buildInfixOperation.setLeftOperand(ElementaryInstructionHelper.createVariableInstruction(variableName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable array left operand of the infix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current infix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftVariableArrayOperand(String variableName, int... indexes) {
		this.buildInfixOperation.setLeftOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the variable array left operand of the infix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current infix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildInfixOperation.setLeftOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the left operand of the infix operation under construction
	 * 
	 * @param leftOperand
	 *            the left operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setLeftOperand(IElementaryInstruction leftOperand) {
		this.buildInfixOperation.setLeftOperand(leftOperand.getExpression());
		return this;
	}

	/**
	 * Set the boolean right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the boolean right operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightOperand(boolean rightOperand) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the int right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the int right operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightOperand(int rightOperand) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the char right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the char right operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightOperand(char rightOperand) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the String right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the String right operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightOperand(String rightOperand) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the this right operand of the infix operation under construction
	 * 
	 * @return the helper.
	 */
	public InfixOperationHelper setRightThisOperand() {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createThisInstruction().getExpression());
		return this;
	}

	/**
	 * Set the field right operand of the infix operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field right operand of the current infix
	 *            operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightFieldOperand(String fieldName) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the field array right operand of the infix operation under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the right left operand of the current infix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightFieldArrayOperand(String fieldName, int... indexes) {
		this.buildInfixOperation.setRightOperand(ArrayInstructionHelper.builderWithField(fieldName).addIndex(indexes)
				.build().getExpression());
		return this;
	}

	/**
	 * Set the field array right operand of the infix operation under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the right left operand of the current infix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildInfixOperation.setRightOperand(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable right operand of the infix operation under construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current infix
	 *            operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightVariableOperand(String variableName) {
		this.buildInfixOperation.setRightOperand(ElementaryInstructionHelper.createVariableInstruction(variableName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable array right operand of the infix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current infix
	 *            operation.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightVariableArrayOperand(String variableName, int... indexes) {
		this.buildInfixOperation.setRightOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the variable array right operand of the infix operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current infix
	 *            operation.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildInfixOperation.setRightOperand(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public InfixOperationHelper setRightOperand(IElementaryInstruction rightOperand) {
		this.buildInfixOperation.setRightOperand(rightOperand.getExpression());
		return this;
	}

}
