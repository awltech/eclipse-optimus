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
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.CatchClauseBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.TryStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.SingleVariableDeclarationBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.TryStatement;

/**
 * The purpose of such class is to help with the creation of try/catch
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TryCatchInstructionHelper {

	/** The build try/catch instruction */
	private TryStatement buildTryInstruction;

	/** The try block */
	private Block tryBlock;

	/** The finally block */
	private Block finallyBlock;

	/**
	 * Launch the build of a new try/catch instruction (without catch block and
	 * finally block)
	 * 
	 * @param tryInstructions
	 *            the instructions list in the try block of the try/catch
	 *            instruction under construction.
	 * @return a new helper.
	 */
	public static TryCatchInstructionHelper builder(IComplexInstruction... tryInstructions) {
		return new TryCatchInstructionHelper(tryInstructions);
	}

	/**
	 * Private constructor : a new try/catch instruction (without catch block
	 * and finally block)
	 * 
	 * @param tryInstructions
	 *            the instructions list in the try block of the try/catch
	 *            instruction under construction.
	 */
	private TryCatchInstructionHelper(IComplexInstruction... tryInstructions) {
		this.tryBlock = BlockBuilder.builder().build();
		EList<Statement> statementsList = this.tryBlock.getStatements();
		for (IComplexInstruction tryInstruction : tryInstructions) {
			statementsList.add(tryInstruction.getStatement());
		}
		this.buildTryInstruction = TryStatementBuilder.builder().setBody(this.tryBlock).build();
	}

	/**
	 * Give the build try/catch instruction
	 * 
	 * @return the build try/catch instruction.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildTryInstruction);
	}

	/**
	 * Add an instructions list to the try block to the try/catch instruction
	 * under construction
	 * 
	 * @param tryInstructions
	 *            the instructions list to add to the try block of the try/catch
	 *            instruction under construction.
	 * @return the helper.
	 */
	public TryCatchInstructionHelper addTryBlockInstruction(IComplexInstruction... tryInstructions) {
		EList<Statement> statementsList = this.tryBlock.getStatements();
		for (IComplexInstruction tryInstruction : tryInstructions) {
			statementsList.add(tryInstruction.getStatement());
		}
		return this;
	}

	/**
	 * Add a catch catch block to the try/catch instruction under construction
	 * 
	 * @param exceptionTypeName
	 *            the type name of the exception associated to the catch block
	 *            to add to the try/catch instruction under construction.
	 * @param exceptionName
	 *            the name of the exception associated to the catch block to add
	 *            to the try/catch instruction under construction.
	 * @param catchInstructions
	 *            the instructions list of the catch block to add to the
	 *            try/catch instruction under construction.
	 * @return the helper.
	 */
	public TryCatchInstructionHelper addCatchBlock(String exceptionTypeName, String exceptionName,
			IComplexInstruction... catchInstructions) {
		Block catchBlock = BlockBuilder.builder().build();
		EList<Statement> statementsList = catchBlock.getStatements();
		for (IComplexInstruction catchInstruction : catchInstructions) {
			statementsList.add(catchInstruction.getStatement());
		}
		SingleVariableDeclaration exceptionDeclaration = SingleVariableDeclarationBuilder.builder()
				.setType(TypeAccessHelper.createExceptionTypeAccess(exceptionTypeName)).setName(exceptionName).build();
		this.buildTryInstruction.getCatchClauses().add(
				CatchClauseBuilder.builder().setException(exceptionDeclaration).setBody(catchBlock).build());
		return this;
	}

	/**
	 * Add an instructions list to the finally block to the try/catch
	 * instruction under construction
	 * 
	 * @param finallyInstructions
	 *            the instructions list to add to the finally block of the
	 *            try/catch instruction under construction.
	 * @return the helper.
	 */
	public TryCatchInstructionHelper addFinallyBlockInstruction(IComplexInstruction... finallyInstructions) {
		if (this.finallyBlock == null) {
			this.finallyBlock = BlockBuilder.builder().build();
			this.buildTryInstruction.setFinally(finallyBlock);
		}
		EList<Statement> statementsList = this.finallyBlock.getStatements();
		for (IComplexInstruction finallyInstruction : finallyInstructions) {
			statementsList.add(finallyInstruction.getStatement());
		}
		return this;
	}

}
