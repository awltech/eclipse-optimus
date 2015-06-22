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

import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.SwitchCaseBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.SwitchStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of switch instruction
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SwitchInstructionHelper {

	/** The build switch instruction */
	private SwitchStatement buildSwitchInstruction;

	/**
	 * Launch the build of a new switch instruction (without case block)
	 * 
	 * @param testedInstruction
	 *            the tested instruction in the switch instruction under
	 *            construction.
	 * @return a new helper.
	 */
	public static SwitchInstructionHelper builder(IElementaryInstruction testedInstruction) {
		return new SwitchInstructionHelper(testedInstruction);
	}

	/**
	 * Launch the build of a new switch instruction (without case block)
	 * 
	 * @param fieldName
	 *            the name of the field under test in the switch instruction
	 *            under construction.
	 * @return a new helper.
	 */
	public static SwitchInstructionHelper builderOnField(String fieldName) {
		return new SwitchInstructionHelper(ElementaryInstructionHelper.createFieldInstruction(fieldName));
	}

	/**
	 * Launch the build of a new switch instruction (without case block)
	 * 
	 * @param variableName
	 *            the name of the variable under test in the switch instruction
	 *            under construction.
	 * @return a new helper.
	 */
	public static SwitchInstructionHelper builderOnVariable(String variableName) {
		return new SwitchInstructionHelper(ElementaryInstructionHelper.createVariableInstruction(variableName));
	}

	/**
	 * Private constructor : a new switch instruction (without case block)
	 * 
	 * @param testedInstruction
	 *            the tested instruction in the switch instruction under
	 *            construction.
	 */
	private SwitchInstructionHelper(IElementaryInstruction testedInstruction) {
		this.buildSwitchInstruction = SwitchStatementBuilder.builder().setExpression(testedInstruction.getExpression())
				.build();
	}

	/**
	 * Give the build switch instruction
	 * 
	 * @return the build switch instruction.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildSwitchInstruction);
	}

	/**
	 * Add a case null block to the switch instruction under construction
	 * 
	 * @param withBreak
	 *            tell if a break instruction finished the case null block to
	 *            add to the switch instruction under construction.
	 * @param caseInstructions
	 *            the instructions list of the case null block to add to the
	 *            switch instruction under construction.
	 * @return the helper.
	 */
	public SwitchInstructionHelper addCaseNullBlock(boolean withBreak, IComplexInstruction... caseInstructions) {
		this.addCaseBlock(ElementaryInstructionHelper.createNullInstruction(), withBreak, caseInstructions);
		return this;
	}

	/**
	 * Add a case integer block to the switch instruction under construction
	 * 
	 * @param condition
	 *            the integer condition.
	 * @param withBreak
	 *            tell if a break instruction finished the case integer block to
	 *            add to the switch instruction under construction.
	 * @param caseInstructions
	 *            the instructions list of the case integer block to add to the
	 *            switch instruction under construction.
	 * @return the helper.
	 */
	public SwitchInstructionHelper addCaseBlock(int condition, boolean withBreak,
			IComplexInstruction... caseInstructions) {
		this.addCaseBlock(ElementaryInstructionHelper.createInstruction(condition), withBreak, caseInstructions);
		return this;
	}

	/**
	 * Add a case char block to the switch instruction under construction
	 * 
	 * @param condition
	 *            the char condition.
	 * @param withBreak
	 *            tell if a break instruction finished the case char block to
	 *            add to the switch instruction under construction.
	 * @param caseInstructions
	 *            the instructions list of the case char block to add to the
	 *            switch instruction under construction.
	 * @return the helper.
	 */
	public SwitchInstructionHelper addCaseBlock(char condition, boolean withBreak,
			IComplexInstruction... caseInstructions) {
		this.addCaseBlock(ElementaryInstructionHelper.createInstruction(condition), withBreak, caseInstructions);
		return this;
	}

	/**
	 * Add a case variable block to the switch instruction under construction
	 * 
	 * @param variableName
	 *            the variable name.
	 * @param withBreak
	 *            tell if a break instruction finished the case variable block
	 *            to add to the switch instruction under construction.
	 * @param caseInstructions
	 *            the instructions list of the case variable block to add to the
	 *            switch instruction under construction.
	 * @return the helper.
	 */
	public SwitchInstructionHelper addCaseVariableBlock(String variableName, boolean withBreak,
			IComplexInstruction... caseInstructions) {
		this.addCaseBlock(ElementaryInstructionHelper.createVariableInstruction(variableName), withBreak,
				caseInstructions);
		return this;
	}

	/**
	 * Add a case block to the switch instruction under construction
	 * 
	 * @param condition
	 *            the condition of the case block to add to the switch
	 *            instruction under construction.
	 * @param withBreak
	 *            tell if a break instruction finished the case block to add to
	 *            the switch instruction under construction.
	 * @param caseInstructions
	 *            the instructions list of the case block to add to the switch
	 *            instruction under construction.
	 * @return the helper.
	 */
	public SwitchInstructionHelper addCaseBlock(IElementaryInstruction condition, boolean withBreak,
			IComplexInstruction... caseInstructions) {
		EList<Statement> statementList = this.buildSwitchInstruction.getStatements();
		statementList.add(SwitchCaseBuilder.builder().setExpression(condition.getExpression()).setDefault(false)
				.build());
		for (IComplexInstruction caseInstruction : caseInstructions) {
			statementList.add(caseInstruction.getStatement());
		}
		if (withBreak) {
			statementList.add(JavaFactory.eINSTANCE.createBreakStatement());
		}
		return this;
	}

	/**
	 * Add the final case block to the switch instruction under construction
	 * 
	 * @param finalCaseInstructions
	 *            the instructions list of the final case block to add to the
	 *            switch instruction under construction.
	 * @return the build switch instruction.
	 */
	public ComplexInstruction addFinalCaseBlock(IComplexInstruction... caseInstructions) {
		EList<Statement> statementList = this.buildSwitchInstruction.getStatements();
		statementList.add(SwitchCaseBuilder.builder().setDefault(true).build());
		for (IComplexInstruction caseInstruction : caseInstructions) {
			statementList.add(caseInstruction.getStatement());
		}
		return this.build();
	}

}
