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

import net.atos.optimus.m2m.javaxmi.operation.instruction.AssignmentInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.ForStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.InfixOperationHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.PostfixOperationHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.InfixExpressionKind;
import org.eclipse.gmt.modisco.java.PostfixExpressionKind;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the creation of for loop
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ForLoopInstructionHelper {

	/** The build for loop instruction */
	private ForStatement buildForLoopInstruction;

	/** The for block */
	private Block forBlock;

	/** The variable name of the for loop */
	private String variableName;

	/**
	 * Launch the build of a new integer for loop instruction
	 * 
	 * @param variableName
	 *            the variable name of the integer for loop under instruction.
	 * 
	 * @return a new helper.
	 */
	public static ForLoopInstructionHelper builderIntLoop(String variableName) {
		return new ForLoopInstructionHelper(variableName);
	}

	/**
	 * Private constructor : a new integer for loop instruction
	 * 
	 * @param variableName
	 *            the variable name of the integer for loop under instruction.
	 * 
	 */
	private ForLoopInstructionHelper(String variableName) {
		this.variableName = variableName;
		this.forBlock = BlockBuilder.builder().build();
		this.buildForLoopInstruction = ForStatementBuilder
				.builder()
				.setBody(this.forBlock)
				.addInitializerExpression(
						ElementaryInstructionHelper.createVariableInstruction(variableName).getExpression()).build();
	}

	/**
	 * Give the build for loop instruction
	 * 
	 * @return the build for loop instruction.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildForLoopInstruction);
	}

	/**
	 * Initialize the variable in the integer for loop under construction
	 * 
	 * @param initialValue
	 *            the initial value of the variable of the integer for loop
	 *            under instruction.
	 * @return the helper.
	 */
	public ForLoopInstructionHelper initializeVariable(int initialValue) {
		this.buildForLoopInstruction.getInitializers().set(
				0,
				AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
						.setLeftVariableDeclarationOperand("int", this.variableName).setRightOperand(initialValue)
						.build().getExpression());
		return this;
	}

	/**
	 * Initialize the variable in the integer for loop under construction
	 * 
	 * @param fieldName
	 *            the name of the field containing the initial value of the
	 *            integer for loop under instruction.
	 * @return the helper.
	 */
	public ForLoopInstructionHelper initializeVariableWithField(String fieldName) {
		this.buildForLoopInstruction.getInitializers().set(
				0,
				AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
						.setLeftVariableDeclarationOperand("int", this.variableName).setRightFieldOperand(fieldName)
						.build().getExpression());
		return this;
	}

	/**
	 * Initialize the variable in the integer for loop under construction
	 * 
	 * @param variableName
	 *            the name of the variable containing the initial value of the
	 *            integer for loop under instruction.
	 * @return the helper.
	 */
	public ForLoopInstructionHelper initializeVariableWithVariable(String variableName) {
		this.buildForLoopInstruction.getInitializers().set(
				0,
				AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
						.setLeftVariableDeclarationOperand("int", this.variableName)
						.setRightVariableOperand(variableName).build().getExpression());
		return this;
	}

	/**
	 * Initialize the variable in the integer for loop under construction,
	 * assignment expression auto-generated
	 * 
	 * @param initializer
	 *            the instruction containing the initial value of the variable
	 *            of the for loop under instruction.
	 * @return the helper.
	 */
	public ForLoopInstructionHelper initializeVariable(IElementaryInstruction initializer) {
		this.buildForLoopInstruction.getInitializers().set(
				0,
				AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
						.setLeftVariableDeclarationOperand("int", this.variableName).setRightOperand(initializer)
						.build().getExpression());
		return this;
	}

	/**
	 * Set the constant condition instruction of the integer for loop
	 * instruction under construction
	 * 
	 * @param comparator
	 *            the comparator operator.
	 * @param condition
	 *            the constant condition instruction of the integer for loop
	 *            instruction under construction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopCondition(InfixExpressionKind comparator, int condition) {
		this.buildForLoopInstruction.setExpression(InfixOperationHelper.builder().setOperator(comparator)
				.setLeftVariableOperand(this.variableName).setRightOperand(condition).build().getExpression());
		return this;
	}

	/**
	 * Set the field condition instruction of the integer for loop instruction
	 * under construction
	 * 
	 * @param comparator
	 *            the comparator operator.
	 * @param fieldName
	 *            the name of the field containing the condition value of the
	 *            integer for loop under instruction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopFieldCondition(InfixExpressionKind comparator, String fieldName) {
		this.buildForLoopInstruction.setExpression(InfixOperationHelper.builder().setOperator(comparator)
				.setLeftVariableOperand(this.variableName).setRightFieldOperand(fieldName).build().getExpression());
		return this;
	}

	/**
	 * Set the variable condition instruction of the integer for loop
	 * instruction under construction
	 * 
	 * @param comparator
	 *            the comparator operator.
	 * @param variableName
	 *            the name of the variable containing the condition value of the
	 *            integer for loop under instruction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopVariableCondition(InfixExpressionKind comparator, String variableName) {
		this.buildForLoopInstruction.setExpression(InfixOperationHelper.builder().setOperator(comparator)
				.setLeftVariableOperand(this.variableName).setRightVariableOperand(variableName).build()
				.getExpression());
		return this;
	}

	/**
	 * Set the condition instruction of the integer for loop instruction under
	 * construction, infix operation auto generated
	 * 
	 * @param comparator
	 *            the comparator operator.
	 * @param condition
	 *            the condition instruction of the integer for loop instruction
	 *            under construction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopCondition(InfixExpressionKind comparator, IElementaryInstruction condition) {
		this.buildForLoopInstruction.setExpression(InfixOperationHelper.builder().setOperator(comparator)
				.setLeftVariableOperand(this.variableName).setRightOperand(condition).build().getExpression());
		return this;
	}

	/**
	 * Set the update instruction of the integer for loop instruction under
	 * construction
	 * 
	 * @param updateOperator
	 *            the update operator.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopUpdate(PostfixExpressionKind updateOperator) {
		this.buildForLoopInstruction.getUpdaters().add(
				0,
				PostfixOperationHelper.builder().setOperator(updateOperator).setVariableOperand(this.variableName)
						.build().getExpression());
		return this;
	}

	/**
	 * Set the constant update instruction of the integer for loop instruction
	 * under construction
	 * 
	 * @param updateOperator
	 *            the update operator.
	 * @param update
	 *            the update constant instruction of the integer for loop
	 *            instruction under construction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopUpdate(AssignmentKind updateOperator, int update) {
		this.buildForLoopInstruction.getUpdaters().add(
				0,
				AssignmentInstructionHelper.builder().setOperator(updateOperator)
						.setLeftVariableOperand(this.variableName).setRightOperand(update).build().getExpression());
		return this;
	}

	/**
	 * Set the field update instruction of the integer for loop instruction
	 * under construction
	 * 
	 * @param updateOperator
	 *            the update operator.
	 * @param fieldName
	 *            the name of the field containing the update value of the
	 *            integer for loop under instruction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopFieldUpdate(AssignmentKind updateOperator, String fieldName) {
		this.buildForLoopInstruction.getUpdaters().add(
				0,
				AssignmentInstructionHelper.builder().setOperator(updateOperator)
						.setLeftVariableOperand(this.variableName).setRightFieldOperand(fieldName).build()
						.getExpression());
		return this;
	}

	/**
	 * Set the variable update instruction of the integer for loop instruction
	 * under construction
	 * 
	 * @param updateOperator
	 *            the update operator.
	 * @param variabledName
	 *            the name of the variable containing the update value of the
	 *            integer for loop under instruction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopVariableUpdate(AssignmentKind updateOperator, String variabledName) {
		this.buildForLoopInstruction.getUpdaters().add(
				0,
				AssignmentInstructionHelper.builder().setOperator(updateOperator)
						.setLeftVariableOperand(this.variableName).setRightVariableOperand(variabledName).build()
						.getExpression());
		return this;
	}

	/**
	 * Set the update instruction of the integer for loop instruction under
	 * construction, assignment operation auto generated
	 * 
	 * @param updateOperator
	 *            the update operator.
	 * @param update
	 *            the update instruction of the integer for loop instruction
	 *            under construction.
	 * @return the helper
	 */
	public ForLoopInstructionHelper setLoopUpdate(AssignmentKind updateOperator, IElementaryInstruction update) {
		this.buildForLoopInstruction.getUpdaters().add(
				0,
				AssignmentInstructionHelper.builder().setOperator(updateOperator)
						.setLeftVariableOperand(this.variableName).setRightOperand(update).build().getExpression());
		return this;
	}

	/**
	 * Add an instructions list to the integer for loop instruction under
	 * construction
	 * 
	 * @param forInstructions
	 *            the instructions list to add to the integer for loop
	 *            instruction under construction.
	 * @return the helper.
	 */
	public ForLoopInstructionHelper addInstructions(IComplexInstruction... forInstructions) {
		EList<Statement> statementsList = this.forBlock.getStatements();
		for (IComplexInstruction thenInstruction : forInstructions) {
			statementsList.add(thenInstruction.getStatement());
		}
		return this;
	}

	/**
	 * Create a new for loop instruction
	 * 
	 * @param initializer
	 *            the initializer instruction of the created for instruction.
	 * @param condition
	 *            the condition instruction of the created for instruction.
	 * @param update
	 *            the update instruction of the created for instruction.
	 * @param forInstructions
	 *            the instructions list in the created for instruction.
	 * @return the created for loop instruction.
	 */
	public static ComplexInstruction createdForLoopInstruction(IElementaryInstruction initializer,
			IElementaryInstruction condition, IElementaryInstruction update, IComplexInstruction... forInstructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (IComplexInstruction instruction : forInstructions) {
			statements.add(instruction.getStatement());
		}
		return new ComplexInstruction(ForStatementBuilder.builder()
				.addInitializerExpression(initializer.getExpression()).setExpression(condition.getExpression())
				.addUpdaterExpression(update.getExpression()).setBody(block).build());
	}

}
