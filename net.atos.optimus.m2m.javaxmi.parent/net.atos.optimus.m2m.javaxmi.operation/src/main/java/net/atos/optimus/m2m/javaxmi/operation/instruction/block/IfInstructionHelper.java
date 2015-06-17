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

import net.atos.optimus.m2m.javaxmi.operation.instruction.IComposable;
import net.atos.optimus.m2m.javaxmi.operation.instruction.Instruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.block.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.block.IfStatementBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.IfStatement;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the creation of if instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class IfInstructionHelper {

	/** The build if instruction */
	private IfStatement buildIfInstruction;

	/** The then block */
	private Block thenBlock;

	/** The else block */
	private Block elseBlock;

	/**
	 * Launch the build of a new if instruction (without else block)
	 * 
	 * @param condition
	 *            the condition of the if instruction under construction.
	 * @param thenInstructions
	 *            the instruction list in the then block of the if instruction
	 *            under construction.
	 * @return a new helper.
	 */
	public static IfInstructionHelper builder(IComposable condition, Instruction... thenInstructions) {
		return new IfInstructionHelper(condition, thenInstructions);
	}

	/**
	 * Private constructor : a new if instruction (without else block)
	 * 
	 * @param condition
	 *            the condition of the if instruction under construction.
	 * @param thenInstructions
	 *            the instruction list in the then block of the if instruction
	 *            under construction.
	 */
	private IfInstructionHelper(IComposable condition, Instruction... thenInstructions) {
		this.thenBlock = BlockBuilder.builder().build();
		EList<Statement> statementsList = this.thenBlock.getStatements();
		for (Instruction thenInstruction : thenInstructions) {
			statementsList.add(thenInstruction.getStatement());
		}
		this.buildIfInstruction = IfStatementBuilder.builder().setExpression(condition.getExpression())
				.setThenStatement(this.thenBlock).build();
	}

	/**
	 * Give the build if instruction
	 * 
	 * @return the build if instruction.
	 */
	public Instruction build() {
		return new Instruction(this.buildIfInstruction);
	}

	/**
	 * Add an instructions list to the then block to the if instruction under
	 * construction
	 * 
	 * @param thenInstructions
	 *            the instructions list to add to the then block of the if
	 *            instruction under construction.
	 * @return the helper.
	 */
	public IfInstructionHelper addThenBlockInstruction(Instruction... thenInstructions) {
		EList<Statement> statementsList = this.thenBlock.getStatements();
		for (Instruction thenInstruction : thenInstructions) {
			statementsList.add(thenInstruction.getStatement());
		}
		return this;
	}

	/**
	 * Add an instructions list to the else block to the if instruction under
	 * construction
	 * 
	 * @param elseInstructions
	 *            the instructions list to add to the else block of the if
	 *            instruction under construction.
	 * @return the helper.
	 */
	public IfInstructionHelper addElseBlockInstruction(Instruction... elseInstructions) {
		if (this.elseBlock == null) {
			this.elseBlock = BlockBuilder.builder().build();
			this.buildIfInstruction.setElseStatement(this.elseBlock);
		}
		EList<Statement> statementsList = this.elseBlock.getStatements();
		for (Instruction thenInstruction : elseInstructions) {
			statementsList.add(thenInstruction.getStatement());
		}
		return this;
	}

}
