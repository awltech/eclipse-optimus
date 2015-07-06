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

import net.atos.optimus.m2m.javaxmi.operation.accesses.ArrayAccessBuilder;

import org.eclipse.gmt.modisco.java.ArrayAccess;

/**
 * The purpose of such class is to help with the creation of array instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayInstructionHelper {

	/** The build array instruction */
	private ArrayAccess buildArrayInstruction;

	/**
	 * Launch the build of a new array instruction
	 * 
	 * @param fieldName
	 *            the name of the field containing the array of the array
	 *            instruction under construction.
	 * @return a new helper.
	 */
	public static ArrayInstructionHelper builderWithField(String fieldName) {
		return new ArrayInstructionHelper(ElementaryInstructionHelper.createFieldInstruction(fieldName));
	}

	/**
	 * Launch the build of a new array instruction
	 * 
	 * @param variableName
	 *            the name of the variable containing the array of the array
	 *            instruction under construction.
	 * @return a new helper.
	 */
	public static ArrayInstructionHelper builderWithVariable(String variableName) {
		return new ArrayInstructionHelper(ElementaryInstructionHelper.createVariableInstruction(variableName));
	}

	/**
	 * Launch the build of a new array instruction
	 * 
	 * @param array
	 *            the array of the array instruction under construction.
	 * @return a new helper.
	 */
	public static ArrayInstructionHelper builder(IElementaryInstruction array) {
		return new ArrayInstructionHelper(array);
	}

	/**
	 * Private constructor : a new array instruction
	 * 
	 * @param array
	 *            the array of the array instruction under construction.
	 */
	private ArrayInstructionHelper(IElementaryInstruction array) {
		this.buildArrayInstruction = ArrayAccessBuilder.builder().setArray(array.getExpression()).build();
	}

	/**
	 * Give the build array instruction
	 * 
	 * @return the build array instruction.
	 */
	public ElementaryInstruction build() {
		return new ElementaryInstruction(this.buildArrayInstruction);
	}

	/**
	 * Add the index of the array instruction under construction
	 * 
	 * @param indexes
	 *            the indexes of the array instruction under construction.
	 * @return the helper.
	 */
	public ArrayInstructionHelper addIndex(int... indexes) {
		for (int index : indexes) {
			this.addIndex(ElementaryInstructionHelper.createInstruction(index));
		}
		return this;
	}

	/**
	 * Add the index of the array instruction under construction
	 * 
	 * @param fieldsNames
	 *            the names of the fields containing the index of the array
	 *            instruction under construction.
	 * @return the helper.
	 */
	public ArrayInstructionHelper addFieldIndex(String... fieldsNames) {
		for (String fieldName : fieldsNames) {
			this.addIndex(ElementaryInstructionHelper.createFieldInstruction(fieldName));
		}
		return this;
	}

	/**
	 * Add the index of the array instruction under construction
	 * 
	 * @param variablesNames
	 *            the names of the variables containing the index of the array
	 *            instruction under construction.
	 * @return the helper.
	 */
	public ArrayInstructionHelper addVariableIndex(String... variablesNames) {
		for (String variableName : variablesNames) {
			this.addIndex(ElementaryInstructionHelper.createVariableInstruction(variableName));
		}
		return this;
	}

	/**
	 * Add the index of the array instruction under construction
	 * 
	 * @param indexInstruction
	 *            the index instruction of the array instruction under
	 *            construction.
	 * @return the helper.
	 */
	public ArrayInstructionHelper addIndex(IElementaryInstruction indexInstruction) {
		if (this.buildArrayInstruction.getIndex() != null) {
			this.buildArrayInstruction = ArrayAccessBuilder.builder().setArray(this.buildArrayInstruction).build();
		}
		this.buildArrayInstruction.setIndex(indexInstruction.getExpression());
		return this;
	}

}
