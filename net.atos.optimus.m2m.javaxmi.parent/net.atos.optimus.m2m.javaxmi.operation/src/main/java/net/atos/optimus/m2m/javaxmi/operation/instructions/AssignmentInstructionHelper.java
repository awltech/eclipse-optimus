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
package net.atos.optimus.m2m.javaxmi.operation.instructions;

import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.AssignmentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.ExpressionStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.ArrayInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationExpressionHelper;

import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.InheritanceKind;

/**
 * The purpose of such class is to help with the creation of assignment
 * instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AssignmentInstructionHelper {

	/** The build assignment instruction */
	private Assignment buildAssignmentInstruction;

	/**
	 * Launch the build of a new assignment instruction (an equal assignment by
	 * default)
	 * 
	 * @return a new helper.
	 */
	public static AssignmentInstructionHelper builder() {
		return new AssignmentInstructionHelper();
	}

	/**
	 * Private constructor : a new assignment instruction (an equal assignment
	 * by default)
	 * 
	 */
	private AssignmentInstructionHelper() {
		this.buildAssignmentInstruction = AssignmentBuilder.builder().setOperator(AssignmentKind.ASSIGN).build();
	}

	/**
	 * Give the build assignment instruction
	 * 
	 * @return the build assignment instruction.
	 */
	public Instruction build() {
		return new Instruction(ExpressionStatementBuilder.builder().setExpression(this.buildAssignmentInstruction)
				.build());
	}

	/**
	 * Set the operator of the assignment instruction under construction
	 * 
	 * @param operator
	 *            the operator of the current assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setOperator(AssignmentKind operator) {
		this.buildAssignmentInstruction.setOperator(operator);
		return this;
	}

	/**
	 * Set the left this operand of the assignment instruction under
	 * construction
	 * 
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftThisOperand() {
		this.buildAssignmentInstruction.setLeftHandSide(ElementaryInstructionHelper.createThisInstruction()
				.getExpression());
		return this;
	}

	/**
	 * Set the field left operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current assignment
	 *            instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftFieldOperand(String fieldName) {
		this.buildAssignmentInstruction.setLeftHandSide(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the field array left operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current assignment
	 *            instruction.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftFieldArrayOperand(String fieldName, int... indexes) {
		this.buildAssignmentInstruction.setLeftHandSide(ArrayInstructionHelper.builderWithField(fieldName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the field array left operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current assignment
	 *            instruction.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildAssignmentInstruction.setLeftHandSide(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable left operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftVariableOperand(String variableName) {
		this.buildAssignmentInstruction.setLeftHandSide(ElementaryInstructionHelper.createVariableInstruction(
				variableName).getExpression());
		return this;
	}

	/**
	 * Set the variable array left operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment instruction.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftVariableArrayOperand(String variableName, int... indexes) {
		this.buildAssignmentInstruction.setLeftHandSide(ArrayInstructionHelper.builderWithVariable(variableName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the variable array left operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment instruction.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildAssignmentInstruction.setLeftHandSide(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable declaration (not final by default) left operand of the
	 * assignment instruction under construction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable left operand of the current
	 *            assignment instruction.
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftVariableDeclarationOperand(String variableTypeName, String variableName) {
		this.buildAssignmentInstruction.setLeftHandSide(VariableDeclarationExpressionHelper
				.createVariableDeclarationExpression(variableTypeName, variableName, InheritanceKind.NONE));
		return this;
	}

	/**
	 * Set the variable declaration left operand of the assignment instruction
	 * under construction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable left operand of the current
	 *            assignment instruction.
	 * @param variableName
	 *            the name of the variable left operand of the current
	 *            assignment instruction.
	 * @param isFinal
	 *            the final state of the variable declaration left operand of
	 *            the current assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftVariableDeclarationOperand(String variableTypeName, String variableName,
			boolean isFinal) {
		this.buildAssignmentInstruction.setLeftHandSide(VariableDeclarationExpressionHelper
				.createVariableDeclarationExpression(variableTypeName, variableName, isFinal ? InheritanceKind.FINAL
						: InheritanceKind.NONE));
		return this;
	}

	/**
	 * Set the field left operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field left operand of the current assignment
	 *            instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setLeftOperand(IElementaryInstruction elementary) {
		this.buildAssignmentInstruction.setLeftHandSide(elementary.getExpression());
		return this;
	}

	/**
	 * Set the boolean right operand of the assignment instruction under
	 * construction
	 * 
	 * @param rightOperand
	 *            the boolean right operand of the current assignment
	 *            instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightOperand(boolean rightOperand) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the int right operand of the assignment instruction under
	 * construction
	 * 
	 * @param rightOperand
	 *            the int right operand of the current assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightOperand(int rightOperand) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the char right operand of the assignment instruction under
	 * construction
	 * 
	 * @param rightOperand
	 *            the char right operand of the current assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightOperand(char rightOperand) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the String constant right operand of the assignment instruction under
	 * construction
	 * 
	 * @param rightOperand
	 *            the String constant right operand of the current assignment
	 *            instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightOperand(String rightOperand) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createInstruction(rightOperand)
				.getExpression());
		return this;
	}

	/**
	 * Set the this right operand of the assignment instruction under
	 * construction
	 * 
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightThisOperand() {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createThisInstruction()
				.getExpression());
		return this;
	}

	/**
	 * Set the field right operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field right operand of the current assignment
	 *            instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightFieldOperand(String fieldName) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the field array right operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field right operand of the current assignment
	 *            instruction.
	 * @param indexes
	 *            the integer indexes of the array field.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightFieldArrayOperand(String fieldName, int... indexes) {
		this.buildAssignmentInstruction.setRightHandSide(ArrayInstructionHelper.builderWithField(fieldName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the field array right operand of the assignment instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field right operand of the current assignment
	 *            instruction.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            field.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightFieldArrayOperand(String fieldName, String... variablesNames) {
		this.buildAssignmentInstruction.setRightHandSide(ArrayInstructionHelper.builderWithField(fieldName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the variable right operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current
	 *            assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightVariableOperand(String variableName) {
		this.buildAssignmentInstruction.setRightHandSide(ElementaryInstructionHelper.createVariableInstruction(
				variableName).getExpression());
		return this;
	}

	/**
	 * Set the variable array right operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current
	 *            assignment instruction.
	 * @param indexes
	 *            the integer indexes of the array variable.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightVariableArrayOperand(String variableName, int... indexes) {
		this.buildAssignmentInstruction.setRightHandSide(ArrayInstructionHelper.builderWithVariable(variableName)
				.addIndex(indexes).build().getExpression());
		return this;
	}

	/**
	 * Set the variable array right operand of the assignment instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable right operand of the current
	 *            assignment instruction.
	 * @param variablesNames
	 *            the names of the variables containing indexes of the array
	 *            variable.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightVariableArrayOperand(String variableName, String... variablesNames) {
		this.buildAssignmentInstruction.setRightHandSide(ArrayInstructionHelper.builderWithVariable(variableName)
				.addVariableIndex(variablesNames).build().getExpression());
		return this;
	}

	/**
	 * Set the right operand of the assignment instruction under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the current assignment instruction.
	 * @return the helper.
	 */
	public AssignmentInstructionHelper setRightOperand(IElementaryInstruction rightOperand) {
		this.buildAssignmentInstruction.setRightHandSide(rightOperand.getExpression());
		return this;
	}

}
