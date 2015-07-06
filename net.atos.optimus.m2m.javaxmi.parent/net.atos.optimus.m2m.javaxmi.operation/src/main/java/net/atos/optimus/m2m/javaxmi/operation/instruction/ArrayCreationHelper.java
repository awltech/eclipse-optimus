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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessBuilder;
import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.array.ArrayCreationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.array.ArrayInitializerBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ArrayInitializerInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.types.ArrayTypeBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.ArrayType;
import org.eclipse.gmt.modisco.java.Expression;

/**
 * The purpose of such class is to help with the creation of array
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ArrayCreationHelper {

	/** The build array */
	private ArrayCreation buildArray;

	/** The type of the build array */
	private ArrayType arrayType;

	/**
	 * Launch the build of a new array
	 * 
	 * @param arrayTypeName
	 *            the type name of the array under construction.
	 * @return a new helper.
	 */
	public static ArrayCreationHelper builder(String arrayTypeName) {
		return new ArrayCreationHelper(arrayTypeName);
	}

	/**
	 * Private constructor : a new array
	 * 
	 * @param arrayTypeName
	 *            the type name of the array under construction.
	 * 
	 */
	private ArrayCreationHelper(String arrayTypeName) {
		this.arrayType = ArrayTypeBuilder.builder().setElementType(TypeAccessHelper.createTypeAccess(arrayTypeName))
				.build();
		this.buildArray = ArrayCreationBuilder.builder()
				.setType(TypeAccessBuilder.builder().setType(this.arrayType).build()).build();
	}

	/**
	 * Give the build array
	 * 
	 * @return the build array.
	 */
	public ElementaryInstruction build() {
		return new ElementaryInstruction(this.buildArray);
	}

	/**
	 * Add a dimensions list to the array under construction
	 * 
	 * @param dimensions
	 *            the dimensions list to add to the array under construction.
	 * @return the helper.
	 */
	public ArrayCreationHelper addDimensions(int... dimensions) {
		EList<Expression> expressionList;
		expressionList = this.buildArray.getDimensions();
		for (int dimension : dimensions) {
			expressionList.add(ElementaryInstructionHelper.createInstruction(dimension).getExpression());
			this.arrayType.setDimensions(this.arrayType.getDimensions() + 1);
		}
		return this;
	}

	/**
	 * Add dimensions list by specified fields containing dimensions to the
	 * array under construction
	 * 
	 * @param fieldsNames
	 *            the fields names of the fields containing dimensions to add to
	 *            the array under construction.
	 * @return the helper.
	 */
	public ArrayCreationHelper addFieldDimensions(String... fieldsNames) {
		EList<Expression> expressionList;
		expressionList = this.buildArray.getDimensions();
		for (String fieldName : fieldsNames) {
			expressionList.add(ElementaryInstructionHelper.createFieldInstruction(fieldName).getExpression());
			this.arrayType.setDimensions(this.arrayType.getDimensions() + 1);
		}
		return this;
	}

	/**
	 * Add a dimensions list by specified variables containing dimensions to the
	 * array under construction
	 * 
	 * @param variablesNames
	 *            the variables names of the variables containing dimensions to
	 *            add to the array under construction.
	 * @return the helper.
	 */
	public ArrayCreationHelper addVariableDimensions(String... variablesNames) {
		EList<Expression> expressionList;
		expressionList = this.buildArray.getDimensions();
		for (String variableName : variablesNames) {
			expressionList.add(ElementaryInstructionHelper.createVariableInstruction(variableName).getExpression());
			this.arrayType.setDimensions(this.arrayType.getDimensions() + 1);
		}
		return this;
	}

	/**
	 * Add a dimensions list by specified instructions to the array under
	 * construction
	 * 
	 * @param instructions
	 *            the list of instructions containing dimensions to add to the
	 *            array under construction.
	 * @return the helper.
	 */
	public ArrayCreationHelper addDimensions(IElementaryInstruction... instructions) {
		EList<Expression> expressionList;
		expressionList = this.buildArray.getDimensions();
		for (IElementaryInstruction instruction : instructions) {
			expressionList.add(instruction.getExpression());
			this.arrayType.setDimensions(this.arrayType.getDimensions() + 1);
		}
		return this;
	}

	/**
	 * Create an array with integer elements list
	 * 
	 * @param elements
	 *            the integer elements list.
	 * @return the created array.
	 */
	public static ArrayInitializerInstruction createArray(int... elements) {
		ArrayInitializer arrayInitializer = ArrayInitializerBuilder.builder().build();
		EList<Expression> expressionsList = arrayInitializer.getExpressions();
		for (Integer element : elements) {
			expressionsList.add(ElementaryInstructionHelper.createInstruction(element).getExpression());
		}
		return new ArrayInitializerInstruction(arrayInitializer);
	}

	/**
	 * Create an array with an elements list
	 * 
	 * @param elements
	 *            the instruction elements list.
	 * @return the created array.
	 */
	public static ArrayInitializerInstruction createArray(IElementaryInstruction... elements) {
		ArrayInitializer arrayInitializer = ArrayInitializerBuilder.builder().build();
		EList<Expression> expressionsList = arrayInitializer.getExpressions();
		for (IElementaryInstruction element : elements) {
			expressionsList.add(element.getExpression());
		}
		return new ArrayInitializerInstruction(arrayInitializer);
	}

}
