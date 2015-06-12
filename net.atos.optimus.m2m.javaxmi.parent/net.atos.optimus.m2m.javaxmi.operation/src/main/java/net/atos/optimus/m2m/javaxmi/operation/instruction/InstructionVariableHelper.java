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
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * The purpose of such class is to help with the creation of variable
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionVariableHelper {

	/**
	 * Create a variable declaration instruction, variable name generated
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created declaration.
	 * @return the created variable declaration instruction.
	 */
	public static Instruction createVariableDeclarationInstruction(String variableTypeName) {
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(NameGenerator.generateNameWithTypeName(variableTypeName)).build();
		return new Instruction(VariableDeclarationStatementBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.addFragment(variableDeclarationFragment).build());
	}

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

}
