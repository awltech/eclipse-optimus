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

import net.atos.optimus.m2m.javaxmi.operation.accesses.FieldAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.accesses.ThisExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.accesses.VariableAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.ClassInstanceCreationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.InfixExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.ParenthesizedExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.PostfixExpressionBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of elementary
 * instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ElementaryInstructionHelper {

	/**
	 * Create a null constant instruction
	 * 
	 * @return a null constant instruction.
	 */
	public static ElementaryInstruction createNullInstruction() {
		return new ElementaryInstruction(JavaFactory.eINSTANCE.createNullLiteral());
	}

	/**
	 * Create a boolean constant instruction
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean constant instruction with the specified
	 *         value.
	 */
	public static ElementaryInstruction createInstruction(boolean value) {
		BooleanLiteral literal = JavaFactory.eINSTANCE.createBooleanLiteral();
		literal.setValue(value);
		return new ElementaryInstruction(literal);
	}

	/**
	 * Create an integer constant instruction
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer constant instruction with the specified
	 *         value.
	 */
	public static ElementaryInstruction createInstruction(int value) {
		NumberLiteral literal = JavaFactory.eINSTANCE.createNumberLiteral();
		literal.setTokenValue(((Integer) value).toString());
		return new ElementaryInstruction(literal);
	}

	/**
	 * Create a char constant instruction
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char constant instruction with the specified value.
	 */
	public static ElementaryInstruction createInstruction(char value) {
		CharacterLiteral literal = JavaFactory.eINSTANCE.createCharacterLiteral();
		literal.setEscapedValue("\'" + value + "\'");
		return new ElementaryInstruction(literal);
	}

	/**
	 * Create a string constant instruction
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string constant instruction with the specified value.
	 */
	public static ElementaryInstruction createInstruction(String value) {
		StringLiteral literal = JavaFactory.eINSTANCE.createStringLiteral();
		literal.setEscapedValue("\"" + value + "\"");
		return new ElementaryInstruction(literal);
	}

	/**
	 * Create a this instruction
	 * 
	 * @return the created this instruction.
	 */
	public static ElementaryInstruction createThisInstruction() {
		return new ElementaryInstruction(ThisExpressionBuilder.builder().build());
	}

	/**
	 * Create a field instruction
	 * 
	 * @param fieldName
	 *            the name of the field.
	 * @return the created field instruction.
	 */
	public static ElementaryInstruction createFieldInstruction(String fieldName) {
		return new ElementaryInstruction(FieldAccessHelper.createFieldAccess(fieldName));
	}

	/**
	 * Create a variable instruction
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable instruction.
	 */
	public static ElementaryInstruction createVariableInstruction(String variableName) {
		return new ElementaryInstruction(VariableAccessHelper.createVariableAccess(variableName));
	}

	/**
	 * Create a class instantiation instruction with arguments
	 *
	 * @param className
	 *            the name of the class to instantiate.
	 * @param arguments
	 *            the arguments list of the class instantiation elementary
	 *            instruction.
	 * @return a new class instantiation instruction instantiating the class
	 *         with the specified name.
	 */
	public static ElementaryInstruction createClassInstantiationInstruction(String className,
			IElementaryInstruction... arguments) {
		ClassInstanceCreation classInstanceCreation = ClassInstanceCreationBuilder.builder()
				.setType(TypeAccessHelper.createClassTypeAccess(className)).build();
		EList<Expression> argumentsList = classInstanceCreation.getArguments();
		for (IElementaryInstruction argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		return new ElementaryInstruction(classInstanceCreation);
	}

	/**
	 * Create an operation instruction
	 * 
	 * @param operator
	 *            the operator of the operation.
	 * @param operand
	 *            the operand of the operation.
	 * @return a new operation instruction.
	 */
	public static ElementaryInstruction createOperationInstruction(PostfixExpressionKind operator,
			IElementaryInstruction operand) {
		return new ElementaryInstruction(PostfixExpressionBuilder.builder().setOperand(operand.getExpression())
				.setOperator(operator).build());
	}

	/**
	 * Create an operation instruction
	 * 
	 * @param operator
	 *            the operator of the operation.
	 * @param leftOperand
	 *            the left operand of the operation.
	 * @param rightOperand
	 *            the right operand of the operation.
	 * @return a new operation instruction.
	 */
	public static ElementaryInstruction createOperationInstruction(InfixExpressionKind operator,
			IElementaryInstruction leftOperand, IElementaryInstruction rightOperand, boolean needParenthesis) {
		Expression operationInstruction = InfixExpressionBuilder.builder().setLeftOperand(leftOperand.getExpression())
				.setOperator(operator).setRightOperand(rightOperand.getExpression()).build();
		if (needParenthesis) {
			operationInstruction = ParenthesizedExpressionBuilder.builder().setExpression(operationInstruction).build();
		}
		return new ElementaryInstruction(operationInstruction);
	}
}
