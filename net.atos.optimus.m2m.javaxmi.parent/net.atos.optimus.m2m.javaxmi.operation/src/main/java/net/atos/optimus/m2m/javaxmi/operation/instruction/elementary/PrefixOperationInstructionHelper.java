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

import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.PrefixExpressionBuilder;

import org.eclipse.gmt.modisco.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.PrefixExpressionKind;

/**
 * The purpose of such class is to help with the creation of prefix operation
 * instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PrefixOperationInstructionHelper {

	/** The build prefix operation instruction */
	private PrefixExpression buildPrefixOperationInstruction;

	/**
	 * Launch the build of a new prefix operation instruction
	 * 
	 * @return a new helper.
	 */
	public static PrefixOperationInstructionHelper builder() {
		return new PrefixOperationInstructionHelper();
	}

	/**
	 * Private constructor : a new prefix operation instruction
	 * 
	 */
	private PrefixOperationInstructionHelper() {
		this.buildPrefixOperationInstruction = PrefixExpressionBuilder.builder().build();
	}

	/**
	 * Give the build prefix operation instruction
	 * 
	 * @return the build prefix operation instruction.
	 */
	public ElementaryInstruction build() {
		return new ElementaryInstruction(this.buildPrefixOperationInstruction);
	}

	/**
	 * Set the operator of the prefix operation instruction under construction
	 * 
	 * @param operator
	 *            the operator of the current prefix expression.
	 * @return the helper.
	 */
	public PrefixOperationInstructionHelper setOperator(PrefixExpressionKind operator) {
		this.buildPrefixOperationInstruction.setOperator(operator);
		return this;
	}

	/**
	 * Set the operand of the prefix operation instruction under construction
	 * 
	 * @param operand
	 *            the operand of the current prefix expression.
	 * @return the helper.
	 */
	public PrefixOperationInstructionHelper setOperand(boolean operand) {
		this.buildPrefixOperationInstruction.setOperand(ElementaryInstructionHelper.createInstruction(operand)
				.getExpression());
		return this;
	}

	/**
	 * Set the field operand of the prefix operation instruction under
	 * construction
	 * 
	 * @param fieldName
	 *            the name of the field operand of the current prefix
	 *            expression.
	 * @return the helper.
	 */
	public PrefixOperationInstructionHelper setFieldOperand(String fieldName) {
		this.buildPrefixOperationInstruction.setOperand(ElementaryInstructionHelper.createFieldInstruction(fieldName)
				.getExpression());
		return this;
	}

	/**
	 * Set the variable operand of the prefix operation instruction under
	 * construction
	 * 
	 * @param variableName
	 *            the name of the variable operand of the current prefix
	 *            expression.
	 * @return the helper.
	 */
	public PrefixOperationInstructionHelper setVariableOperand(String variableName) {
		this.buildPrefixOperationInstruction.setOperand(ElementaryInstructionHelper.createVariableInstruction(
				variableName).getExpression());
		return this;
	}

	/**
	 * Set the operand of the prefix operation instruction under construction
	 * 
	 * @param operand
	 *            the operand of the current prefix expression.
	 * @return the helper.
	 */
	public PrefixOperationInstructionHelper setOperand(IElementaryInstruction operand) {
		this.buildPrefixOperationInstruction.setOperand(operand.getExpression());
		return this;
	}

}
