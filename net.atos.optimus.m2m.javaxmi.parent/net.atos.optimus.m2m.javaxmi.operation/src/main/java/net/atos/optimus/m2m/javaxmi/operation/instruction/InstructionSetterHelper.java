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
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;

import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;

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
	 *            the assign instruction.
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param rightHandSide
	 *            the instruction with the value to assign.
	 * @return the created assignment instruction.
	 */
	public static Instruction createAssignmentInstruction(IElementaryInstruction leftHandSide,
			AssignmentKind assignmentOperator, IElementaryInstruction rightHandSide) {
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
		ElementaryInstruction fieldInstruction = ElementaryInstructionHelper.createFieldInstruction(fieldName);
		ElementaryInstruction variableInstruction = ElementaryInstructionHelper.createVariableInstruction(variableName);
		return InstructionSetterHelper.createAssignmentInstruction(fieldInstruction, AssignmentKind.ASSIGN,
				variableInstruction);
	}

}
