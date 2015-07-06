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

import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.DoStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.WhileStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the creation of do/while
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class DoWhileInstructionHelper {

	/**
	 * Private constructor
	 * 
	 */
	private DoWhileInstructionHelper() {
	}

	/**
	 * Create a new while instruction
	 * 
	 * @param condition
	 *            the condition of the created while instruction.
	 * @param instructions
	 *            the instructions list in the created while instruction.
	 * @return the created while instruction.
	 */
	public static ComplexInstruction createWhileInstruction(IElementaryInstruction condition,
			IComplexInstruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (IComplexInstruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		return new ComplexInstruction(WhileStatementBuilder.builder().setBody(block)
				.setExpression(condition.getExpression()).build());
	}

	/**
	 * Create a new do instruction
	 * 
	 * @param condition
	 *            the condition of the created do instruction.
	 * @param instructions
	 *            the instructions list in the created do instruction.
	 * @return the created do instruction.
	 */
	public static ComplexInstruction createDoInstruction(IElementaryInstruction condition,
			IComplexInstruction... instructions) {
		Block block = BlockBuilder.builder().build();
		EList<Statement> statements = block.getStatements();
		for (IComplexInstruction instruction : instructions) {
			statements.add(instruction.getStatement());
		}
		return new ComplexInstruction(DoStatementBuilder.builder().setBody(block)
				.setExpression(condition.getExpression()).build());
	}

}
