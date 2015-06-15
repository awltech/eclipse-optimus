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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the creation of instructions blocks
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionBlockHelper {

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

}
