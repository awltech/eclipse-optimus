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
package net.atos.optimus.m2m.javaxmi.operation.instruction.block;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.IComposable;
import net.atos.optimus.m2m.javaxmi.operation.instruction.Instruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.VariableDeclarationExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.SingleVariableDeclarationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;

/**
 * The purpose of such class is to help with the creation of instructions blocks
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionLoopHelper {

	/**
	 * Create a new while instruction block
	 * 
	 * @param condition
	 *            the condition of the created while instruction block.
	 * @param instructions
	 *            the instructions list in the created while instruction block.
	 * @return the created while instruction block.
	 */
	public static Instruction createWhileInstruction(IComposable condition, Instruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (Instruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		return new Instruction(WhileStatementBuilder.builder().setBody(block).setExpression(condition.getExpression())
				.build());
	}

	/**
	 * Create a new do instruction block
	 * 
	 * @param condition
	 *            the condition of the created do instruction block.
	 * @param instructions
	 *            the instructions list in the created do instruction block.
	 * @return the created do instruction block.
	 */
	public static Instruction createDoInstruction(IComposable condition, Instruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (Instruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		return new Instruction(DoStatementBuilder.builder().setBody(block).setExpression(condition.getExpression())
				.build());
	}

	/**
	 * Create a new for loop instruction block
	 * 
	 * @param variableTypeName
	 *            the type name of the variable of the created for instruction
	 *            block.
	 * @param variableName
	 *            the name of the variable of the created for instruction block.
	 * @param condition
	 *            the condition instruction of the created for instruction
	 *            block.
	 * @param update
	 *            the update instruction of the created for instruction block.
	 * @param instructions
	 *            the instructions list in the created for instruction block.
	 * @return the created for loop instruction block.
	 */
	public static Instruction createdForLoopInstruction(String variableTypeName, String variableName,
			IComposable condition, IComposable update, Instruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (Instruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		VariableDeclarationExpression variableDeclarationExpression = VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.addFragment(variableDeclarationFragment).build();
		return new Instruction(ForStatementBuilder.builder().addInitializerExpression(variableDeclarationExpression)
				.setExpression(condition.getExpression()).addUpdaterExpression(update.getExpression()).setBody(block)
				.build());
	}

	/**
	 * Create a new for iterable instruction block
	 * 
	 * @param variableTypeName
	 *            the type name of the variable of the created for instruction
	 *            block.
	 * @param variableName
	 *            the name of the variable of the created for instruction block.
	 * @param iterableInstruction
	 *            the iterable instruction of the created for instruction block.
	 * @param instructions
	 *            the instructions list in the created for instruction block.
	 * @return the instructions list in the created for iterable instruction
	 *         block.
	 */
	public static Instruction createdForIterableInstruction(String variableTypeName, String variableName,
			IComposable iterableInstruction, Instruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (Instruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		SingleVariableDeclaration parameter = SingleVariableDeclarationBuilder.builder().setName(variableName)
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName)).build();
		return new Instruction(EnhancedForStatementBuilder.builder().setParameter(parameter)
				.setExpression(iterableInstruction.getExpression()).setBody(block).build());
	}

}
