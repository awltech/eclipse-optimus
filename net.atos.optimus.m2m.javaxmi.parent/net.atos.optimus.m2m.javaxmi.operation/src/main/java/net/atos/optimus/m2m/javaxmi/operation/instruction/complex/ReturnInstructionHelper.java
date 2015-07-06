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
package net.atos.optimus.m2m.javaxmi.operation.instruction.complex;

import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;

/**
 * The purpose of such class is to help with the creation of return instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ReturnInstructionHelper {

	/**
	 * Create a null constant return instruction
	 * 
	 * @return a null constant return instruction.
	 */
	public static ComplexInstruction createNullReturnInstruction() {
		return ElementaryInstructionHelper.createNullInstruction().convertToReturnInstruction();
	}

	/**
	 * Create a boolean constant return instruction
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean constant return instruction with the
	 *         specified value.
	 */
	public static ComplexInstruction createReturnInstruction(boolean value) {
		return ElementaryInstructionHelper.createInstruction(value).convertToReturnInstruction();
	}

	/**
	 * Create an integer constant return instruction
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer constant return instruction with the
	 *         specified value.
	 */
	public static ComplexInstruction createReturnInstruction(int value) {
		return ElementaryInstructionHelper.createInstruction(value).convertToReturnInstruction();
	}

	/**
	 * Create a char constant return instruction
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char constant return instruction with the specified
	 *         value.
	 */
	public static ComplexInstruction createReturnInstruction(char value) {
		return ElementaryInstructionHelper.createInstruction(value).convertToReturnInstruction();
	}

	/**
	 * Create a string constant return instruction
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string constant return instruction with the specified
	 *         value.
	 */
	public static ComplexInstruction createReturnInstruction(String value) {
		return ElementaryInstructionHelper.createInstruction(value).convertToReturnInstruction();
	}

	/**
	 * Create a this return instruction
	 * 
	 * @return the created this return instruction.
	 */
	public static ComplexInstruction createThisReturnInstruction() {
		return ElementaryInstructionHelper.createThisInstruction().convertToReturnInstruction();
	}

	/**
	 * Create a field return instruction
	 * 
	 * @param fieldName
	 *            the name of the field.
	 * @return the created field return instruction.
	 */
	public static ComplexInstruction createFieldReturnInstruction(String fieldName) {
		return ElementaryInstructionHelper.createFieldInstruction(fieldName).convertToReturnInstruction();
	}

	/**
	 * Create a variable return instruction
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable return instruction.
	 */
	public static ComplexInstruction createVariableReturnInstruction(String variableName) {
		return ElementaryInstructionHelper.createVariableInstruction(variableName).convertToReturnInstruction();
	}

}
