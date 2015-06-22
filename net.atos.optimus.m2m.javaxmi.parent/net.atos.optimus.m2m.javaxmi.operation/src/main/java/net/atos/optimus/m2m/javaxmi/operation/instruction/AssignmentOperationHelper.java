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

import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.AssignmentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.ExpressionStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationExpressionHelper;

import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.InheritanceKind;

/**
 * The purpose of such class is to help with the creation of assignment
 * operation
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AssignmentOperationHelper {

	/** The build assignment operation */
	private Assignment buildAssignmentOperation;

	/**
	 * Launch the build of a new assignment operation
	 * 
	 * @return a new helper.
	 */
	public static AssignmentOperationHelper builder() {
		return new AssignmentOperationHelper();
	}

	/**
	 * Private constructor : a new assignment operation
	 * 
	 */
	private AssignmentOperationHelper() {
		this.buildAssignmentOperation = AssignmentBuilder.builder().build();
	}

	/**
	 * Give the build assignment operation
	 * 
	 * @return the build assignment operation.
	 */
	public Instruction build() {
		return new Instruction(ExpressionStatementBuilder.builder().setExpression(this.buildAssignmentOperation)
				.build());
	}

	/**
	 * Set the operator of the assignment operation under construction
	 * 
	 * @param operator
	 *            the operator of the current assignment operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setOperator(AssignmentKind operator) {
		this.buildAssignmentOperation.setOperator(operator);
		return this;
	}

	/**
	 * Set the left this operand of the assignment operation under construction
	 * 
	 * @return the helper.
	 */
	public AssignmentOperationHelper setLeftLeftOperand() {
		this.buildAssignmentOperation.setLeftHandSide(ElementaryInstructionHelper.createThisInstruction()
				.getExpression());
		return this;
	}

	/**
	 * Set the field left operand of the assignment operation under construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current assignment
	 *            operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setLeftFieldOperand(String fieldName) {
		this.buildAssignmentOperation.setLeftHandSide(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable left operand of the assignment operation under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setLeftVariableOperand(String variableName) {
		this.buildAssignmentOperation.setLeftHandSide(ElementaryInstructionHelper.createVariableInstruction(
				variableName).getExpression());
		return this;
	}

	/**
	 * Set the variable declaration (not final by default) left operand of the
	 * assignment operation under construction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable left operand of the current
	 *            assignment operation.
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setLeftVariableDeclarationOperand(String variableTypeName, String variableName) {
		this.buildAssignmentOperation.setLeftHandSide(VariableDeclarationExpressionHelper
				.createVariableDeclarationExpression(variableTypeName, variableName, InheritanceKind.NONE));
		return this;
	}

	/**
	 * Set the variable declaration left operand of the assignment operation
	 * under construction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable left operand of the current
	 *            assignment operation.
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment operation.
	 * @param isFinal
	 *            the final state of the variable declaration left operand of
	 *            the current assignment operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setLeftVariableDeclarationOperand(String variableTypeName, String variableName,
			boolean isFinal) {
		this.buildAssignmentOperation.setLeftHandSide(VariableDeclarationExpressionHelper
				.createVariableDeclarationExpression(variableTypeName, variableName, isFinal ? InheritanceKind.FINAL
						: InheritanceKind.NONE));
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightOperand(boolean rightOperand) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightOperand(int rightOperand) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightOperand(char rightOperand) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightOperand(String rightOperand) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the this right operand of the infix operation under construction
	 * 
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightThisOperand() {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createThisInstruction()
				.getExpression());
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
	public AssignmentOperationHelper setRightFieldOperand(String fieldName) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
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
	public AssignmentOperationHelper setRightVariableOperand(String variableName) {
		this.buildAssignmentOperation.setRightHandSide(ElementaryInstructionHelper.createVariableInstruction(
				variableName).getExpression());
		return this;
	}

	/**
	 * Set the right operand of the infix operation under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current infix operation.
	 * @return the helper.
	 */
	public AssignmentOperationHelper setRightOperand(IElementaryInstruction rightOperand) {
		this.buildAssignmentOperation.setRightHandSide(rightOperand.getExpression());
		return this;
	}

}
