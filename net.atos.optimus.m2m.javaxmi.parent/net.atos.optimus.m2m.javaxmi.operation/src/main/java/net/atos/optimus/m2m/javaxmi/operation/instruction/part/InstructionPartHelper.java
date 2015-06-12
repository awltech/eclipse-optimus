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
package net.atos.optimus.m2m.javaxmi.operation.instruction.part;

import net.atos.optimus.m2m.javaxmi.operation.accesses.FieldAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.accesses.VariableAccessHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of instruction part
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionPartHelper {

	/**
	 * Create a null constant instruction part
	 * 
	 * @return a null constant instruction part.
	 */
	public static InstructionPart createInstructionPart() {
		return new InstructionPart(JavaFactory.eINSTANCE.createNullLiteral());
	}

	/**
	 * Create a boolean constant instruction part
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean constant instruction part with the specified value.
	 */
	public static InstructionPart createInstructionPart(boolean value) {
		BooleanLiteral literal = JavaFactory.eINSTANCE.createBooleanLiteral();
		literal.setValue(value);
		return new InstructionPart(literal);
	}

	/**
	 * Create an integer constant instruction part
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer constant instruction part with the specified value.
	 */
	public static InstructionPart createInstructionPart(int value) {
		NumberLiteral literal = JavaFactory.eINSTANCE.createNumberLiteral();
		literal.setTokenValue(((Integer) value).toString());
		return new InstructionPart(literal);
	}

	/**
	 * Create a char constant instruction part
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char constant instruction part with the specified value.
	 */
	public static InstructionPart createInstructionPart(char value) {
		CharacterLiteral literal = JavaFactory.eINSTANCE.createCharacterLiteral();
		literal.setEscapedValue(((Character) value).toString());
		return new InstructionPart(literal);
	}

	/**
	 * Create a string constant instruction part
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string constant instruction part with the specified value.
	 */
	public static InstructionPart createInstructionPart(String value) {
		StringLiteral literal = JavaFactory.eINSTANCE.createStringLiteral();
		literal.setEscapedValue(value);
		return new InstructionPart(literal);
	}

	/**
	 * Create a field instruction part
	 * 
	 * @param fieldName
	 *            the name of the field.
	 * @return the created field instruction part.
	 */
	public static InstructionPart createFieldInstructionPart(String fieldName) {
		return new InstructionPart(FieldAccessHelper.createFieldAccess(fieldName));
	}

	/**
	 * Create a variable instruction part
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable instruction part.
	 */
	public static InstructionPart createVariableInstructionPart(String variableName) {
		return new InstructionPart(VariableAccessHelper.createVariableAccess(variableName));
	}

	/**
	 * Create a class instantiation instruction part with arguments
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @param arguments
	 *            the arguments list of the class instantiation instruction part.
	 * @return a new class instantiation instruction part instantiating the class with
	 *         the specified name.
	 */
	public static InstructionPart createClassInstantiationInstructionPart(String className,
			InstructionPart... arguments) {
		ClassInstanceCreation classInstanceCreation = ClassInstanceCreationBuilder.builder()
				.setType(TypeAccessHelper.createClassTypeAccess(className)).build();
		EList<Expression> argumentsList = classInstanceCreation.getArguments();
		for (InstructionPart argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		return new InstructionPart(classInstanceCreation);
	}

}