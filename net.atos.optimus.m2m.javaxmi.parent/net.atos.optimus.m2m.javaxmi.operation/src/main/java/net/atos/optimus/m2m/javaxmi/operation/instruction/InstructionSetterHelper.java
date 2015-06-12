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
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPart;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPartHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.VariableDeclarationExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * The purpose of such class is to help with the creation of setter instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionSetterHelper {

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
		return InstructionSetterHelper.createAssignmentInstruction(fieldInstruction, AssignmentKind.ASSIGN,
				variableInstruction);
	}

	/**
	 * Create a variable declaration instruction with assignment part, variable
	 * name generated
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created declaration.
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param assignmentPart
	 *            the assignment part.
	 * @return the created variable declaration instruction.
	 */
	public static Instruction createAndSetVariableDeclarationInstruction(String variableTypeName,
			AssignmentKind assignmentOperator, InstructionPart assignmentPart) {
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(NameGenerator.generateNameWithTypeName(variableTypeName)).build();
		InstructionPart leftHandSide = new InstructionPart(VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.addFragment(variableDeclarationFragment).build());
		return InstructionSetterHelper.createAssignmentInstruction(leftHandSide, assignmentOperator, assignmentPart);
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
		return InstructionSetterHelper.createAssignmentInstruction(leftHandSide, assignmentOperator, assignmentPart);
	}

}
