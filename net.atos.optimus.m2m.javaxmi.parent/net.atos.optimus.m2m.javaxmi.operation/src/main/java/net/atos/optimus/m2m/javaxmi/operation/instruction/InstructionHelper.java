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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.comments.LineCommentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPart;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPartHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.VariableDeclarationExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * The purpose of such class is to help with the creation of instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionHelper {

	/**
	 * Create a variable declaration instruction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created declaration.
	 * @param variableName
	 *            the name of the variable in the created declaration.
	 * @return the created variable declaration instruction.
	 */
	public static Instruction createVariableDeclarationInstruction(String variableTypeName, String variableName) {
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		return new Instruction(VariableDeclarationStatementBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.addFragment(variableDeclarationFragment).build());
	}

	/**
	 * Create a variable declaration instruction with assignment part
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created declaration.
	 * @param variableName
	 *            the name of the variable in the created declaration.
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param assignmentPart
	 *            the assignment part.
	 * @return the created variable declaration instruction.
	 */
	public static Instruction createAndSetVariableDeclarationInstruction(String variableTypeName, String variableName,
			AssignmentKind assignmentOperator, InstructionPart assignmentPart) {
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		InstructionPart leftHandSide = new InstructionPart(VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.addFragment(variableDeclarationFragment).build());
		return InstructionHelper.createAssignmentInstruction(leftHandSide, assignmentOperator, assignmentPart);
	}

	/**
	 * Create an assignment instruction
	 * 
	 * @param leftHandSide
	 *            the assign instruction part.
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param rightHandSide
	 *            the instruction part with the value to assign.
	 * @return the created assignment instruction.
	 */
	public static Instruction createAssignmentInstruction(InstructionPart leftHandSide,
			AssignmentKind assignmentOperator, InstructionPart rightHandSide) {
		Expression assignmentExpression = AssignmentBuilder.builder().setLeftHandSide(leftHandSide.getExpression())
				.setOperator(assignmentOperator).setRightHandSide(rightHandSide.getExpression()).build();
		return new Instruction(ExpressionStatementBuilder.builder().setExpression(assignmentExpression).build());
	}

	/**
	 * Create an instruction to set a field
	 * 
	 * @param fieldName
	 *            the name of the field to set.
	 * @param variableName
	 *            the name of the variable containing the new value of the
	 *            field.
	 * @return the created instruction setting the specified field with the
	 *         specified variable.
	 */
	public static Instruction createSetFieldInstruction(String fieldName, String variableName) {
		InstructionPart fieldInstruction = InstructionPartHelper.createFieldInstructionPart(fieldName);
		InstructionPart variableInstruction = InstructionPartHelper.createVariableInstructionPart(variableName);
		return InstructionHelper.createAssignmentInstruction(fieldInstruction, AssignmentKind.ASSIGN,
				variableInstruction);
	}

	/**
	 * Create a class instantiation return instruction with arguments
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @param arguments
	 *            the arguments list of the class instantiation return
	 *            instruction.
	 * @return the created class instantiation return instruction returning the
	 *         instantiation of the class with the specified name and arguments.
	 */
	public static Instruction createClassInstantiationReturnInstruction(String className, InstructionPart... arguments) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createClassInstantiationInstructionPart(
				className, arguments));
	}

	/**
	 * Create a return instruction based on an instruction part
	 * 
	 * @param instructionPart
	 *            the instruction part convert to a return instruction.
	 * @return the created return instruction based on the specified instruction
	 *         part.
	 */
	public static Instruction createReturnInstruction(InstructionPart instructionPart) {
		ReturnStatement returnStatement = ReturnStatementBuilder.builder()
				.setExpression(instructionPart.getExpression()).build();
		return new Instruction(returnStatement);
	}

	/**
	 * Create an empty return instruction
	 * 
	 * @return the empty return instruction.
	 */
	public static Instruction createReturnInstruction() {
		return new Instruction(ReturnStatementBuilder.builder().build());
	}

	/**
	 * Create a null return instruction
	 * 
	 * @return the created null return instruction.
	 */
	public static Instruction createNullReturnInstruction() {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createInstructionPart());
	}

	/**
	 * Create a boolean return instruction
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean return instruction with the specified value.
	 */
	public static Instruction createReturnInstruction(boolean value) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createInstructionPart(value));
	}

	/**
	 * Create an integer return instruction
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer return instruction with the specified value.
	 */
	public static Instruction createReturnInstruction(int value) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createInstructionPart(value));
	}

	/**
	 * Create a char return instruction
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char return instruction with the specified value.
	 */
	public static Instruction createReturnInstruction(char value) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createInstructionPart(value));
	}

	/**
	 * Create a string instruction instruction
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string return instruction with the specified value.
	 */
	public static Instruction createReturnInstruction(String value) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createInstructionPart(value));
	}

	/**
	 * Create a field return instruction
	 * 
	 * @param fieldName
	 *            the name of the return field.
	 * @return the created field return instruction returning the field with the
	 *         specified name.
	 */
	public static Instruction createFieldReturnInstruction(String fieldName) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper.createFieldInstructionPart(fieldName));
	}

	/**
	 * Create a variable return instruction
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable return instruction.
	 */
	public static Instruction createVariableReturnInstruction(String variableName) {
		return InstructionHelper.createReturnInstruction(InstructionPartHelper
				.createVariableInstructionPart(variableName));
	}

	/**
	 * Add a comment to an instruction
	 * 
	 * @param instruction
	 *            the instruction which we add a comment.
	 * @param commentText
	 *            the text of the comment.
	 * @return the commented instruction.
	 */
	public static Instruction addComment(Instruction instruction, String commentText) {
		return InstructionHelper.addComment(instruction, commentText, true);
	}

	/**
	 * Add a comment to an instruction
	 * 
	 * @param instruction
	 *            the instruction which we add a comment.
	 * @param commentText
	 *            the text of the comment.
	 * @param prefixOfParent
	 *            the prefix of parent state of the comment.
	 * @return the commented instruction.
	 */
	public static Instruction addComment(Instruction instruction, String commentText, boolean prefixOfParent) {
		LineComment comment = LineCommentBuilder.builder().setContent(commentText).setPrefixOfParent(prefixOfParent)
				.build();
		instruction.getStatement().getComments().add(comment);
		return instruction;
	}

}
