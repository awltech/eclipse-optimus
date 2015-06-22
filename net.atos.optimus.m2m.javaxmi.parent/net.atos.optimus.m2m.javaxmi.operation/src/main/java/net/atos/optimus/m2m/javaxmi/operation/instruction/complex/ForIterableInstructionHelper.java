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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.EnhancedForStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.variables.SingleVariableDeclarationBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the creation of for iterable
 * instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ForIterableInstructionHelper {

	/** The build for iterable instruction */
	private EnhancedForStatement buildForIterableInstruction;

	/** The for block */
	private Block forBlock;

	/**
	 * Launch the build of a new for iterable instruction
	 * 
	 * @param variableName
	 *            the name of the variable of the for iterable instruction under
	 *            construction.
	 * @return a new helper.
	 */
	public static ForIterableInstructionHelper builder(String variableName) {
		return new ForIterableInstructionHelper(variableName);
	}

	/**
	 * Launch the build of a new for iterable instruction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable of the for iterable instruction
	 *            under construction.
	 * @param variableName
	 *            the name of the variable of the for iterable instruction under
	 *            construction.
	 * @return a new helper.
	 */
	public static ForIterableInstructionHelper builder(String variableTypeName, String variableName) {
		return new ForIterableInstructionHelper(variableTypeName, variableName);
	}

	/**
	 * Private constructor : a new for iterable instruction
	 * 
	 * @param variableName
	 *            the name of the variable of the for iterable instruction under
	 *            construction.
	 */
	private ForIterableInstructionHelper(String variableName) {
		this.forBlock = BlockBuilder.builder().build();
		this.buildForIterableInstruction = EnhancedForStatementBuilder.builder()
				.setParameter(SingleVariableDeclarationBuilder.builder().setName(variableName).build())
				.setBody(forBlock).build();
	}

	/**
	 * Private constructor : a new for iterable instruction
	 * 
	 * @param variableTypeName
	 *            the type name of the variable of the for iterable instruction
	 *            under construction.
	 * @param variableName
	 *            the name of the variable of the for iterable instruction under
	 *            construction.
	 */
	private ForIterableInstructionHelper(String variableTypeName, String variableName) {
		this.forBlock = BlockBuilder.builder().build();
		this.buildForIterableInstruction = EnhancedForStatementBuilder
				.builder()
				.setParameter(
						SingleVariableDeclarationBuilder.builder().setName(variableName)
								.setType(TypeAccessHelper.createTypeAccess(variableTypeName)).build())
				.setBody(forBlock).build();
	}

	/**
	 * Give the build for iterable instruction
	 * 
	 * @return the build for iterable instruction.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildForIterableInstruction);
	}

	/**
	 * Set the iterable instruction of the for iterable instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the iterable field of the for iterable instruction
	 *            under construction.
	 * @return the helper.
	 */
	public ForIterableInstructionHelper setIterableFieldInstruction(String fieldName) {
		this.setIterableInstruction(ElementaryInstructionHelper.createFieldInstruction(fieldName));
		return this;
	}

	/**
	 * Set the iterable instruction of the for iterable instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the iterable variable of the for iterable
	 *            instruction under construction.
	 * @return the helper.
	 */
	public ForIterableInstructionHelper setIterableVariableInstruction(String variableName) {
		this.setIterableInstruction(ElementaryInstructionHelper.createVariableInstruction(variableName));
		return this;
	}

	/**
	 * Set the iterable instruction of the for iterable instruction under
	 * construction
	 * 
	 * @param iterableInstruction
	 *            the iterable instruction of the for iterable instruction under
	 *            construction.
	 * @return the helper.
	 */
	public ForIterableInstructionHelper setIterableInstruction(IElementaryInstruction iterableInstruction) {
		this.buildForIterableInstruction.setExpression(iterableInstruction.getExpression());
		return this;
	}

	/**
	 * Add an instructions list to the for iterable instruction under
	 * construction
	 * 
	 * @param forInstructions
	 *            the instructions list to add to the for iterable instruction
	 *            under construction.
	 * @return the helper.
	 */
	public ForIterableInstructionHelper addInstructions(IComplexInstruction... forInstructions) {
		EList<Statement> statementsList = this.forBlock.getStatements();
		for (IComplexInstruction thenInstruction : forInstructions) {
			statementsList.add(thenInstruction.getStatement());
		}
		return this;
	}

}
