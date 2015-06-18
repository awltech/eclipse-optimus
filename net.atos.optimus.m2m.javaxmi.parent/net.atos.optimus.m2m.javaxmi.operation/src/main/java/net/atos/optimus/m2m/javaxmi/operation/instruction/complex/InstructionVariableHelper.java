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
import net.atos.optimus.m2m.javaxmi.operation.instruction.Instruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.InstructionSetterHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.VariableDeclarationStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.VariableDeclarationExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;

/**
 * The purpose of such class is to help with the creation of variable
 * declaration instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionVariableHelper {

	/** The build variable declaration instruction */
	private VariableDeclarationStatement buildVariableDeclarationStatement;

	/**
	 * Launch the build of a new variable declaration instruction (no
	 * assignment, not final, with generated name)
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created variable
	 *            declaration instruction.
	 * @return a new helper.
	 */
	public static InstructionVariableHelper builder(String variableTypeName) {
		return new InstructionVariableHelper(variableTypeName);
	}

	/**
	 * Private constructor : a new variable declaration instruction (no
	 * assignment, not final, with generated name)
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created variable
	 *            declaration instruction.
	 */
	private InstructionVariableHelper(String variableTypeName) {
		Modifier modifier = ModifierBuilder.builder().setInheritance(InheritanceKind.NONE).build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(NameGenerator.generateNameWithTypeName(variableTypeName)).build();
		this.buildVariableDeclarationStatement = VariableDeclarationStatementBuilder.builder()
				.setType(TypeAccessHelper.createTypeAccess(variableTypeName)).setModifier(modifier)
				.addFragment(variableDeclarationFragment).build();
	}

	/**
	 * Give the build variable declaration instruction
	 * 
	 * @return the build variable declaration instruction.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildVariableDeclarationStatement);
	}

	/**
	 * Set the final state of the variable in the variable declaration
	 * instruction under construction
	 * 
	 * @param isFinal
	 *            the final state of the variable in the variable declaration
	 *            instruction under construction.
	 * @return the helper.
	 */
	public InstructionVariableHelper setFinal(boolean isFinal) {
		this.buildVariableDeclarationStatement.getModifier().setInheritance(
				isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Set the name of the variable in the variable declaration instruction
	 * under construction
	 * 
	 * @param variableName
	 *            the name of the variable in the variable declaration
	 *            instruction under construction.
	 * @return the helper.
	 */
	public InstructionVariableHelper setName(String variableName) {
		this.buildVariableDeclarationStatement.getFragments().get(0).setName(variableName);
		return this;
	}

	/**
	 * Set the assignment part of the variable declaration instruction under
	 * construction
	 * 
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param assignmentInstruction
	 *            the assignment instruction.
	 * @return the variable declaration instruction with assignment part.
	 */
	public Instruction setVariableAssignment(AssignmentKind assignmentOperator,
			IElementaryInstruction assignmentInstruction) {
		String variableName = this.buildVariableDeclarationStatement.getFragments().get(0).getName();
		String variableTypeName = this.buildVariableDeclarationStatement.getType().getType().getName();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		ElementaryInstruction leftHandSide = new ElementaryInstruction(VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createTypeAccess(variableTypeName))
				.setModifier(this.buildVariableDeclarationStatement.getModifier())
				.addFragment(variableDeclarationFragment).build());
		return InstructionSetterHelper.createAssignmentInstruction(leftHandSide, assignmentOperator,
				assignmentInstruction);
	}

	/**
	 * Create a variable declaration instruction without assignment part
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created variable
	 *            declaration instruction.
	 * @param isFinal
	 *            the final state of the variable in the created variable
	 *            declaration instruction.
	 * @param variableName
	 *            the name of the variable in the created variable declaration
	 *            instruction.
	 * @return the created variable declaration instruction with assignment
	 *         part.
	 */
	public static ComplexInstruction createVariableDeclarationInstruction(String variableTypeName, boolean isFinal,
			String variableName) {
		return InstructionVariableHelper.builder(variableTypeName).setFinal(isFinal).setName(variableName).build();
	}

	/**
	 * Create a variable declaration instruction with assignment part
	 * 
	 * @param variableTypeName
	 *            the type name of the variable in the created variable
	 *            declaration instruction.
	 * @param isFinal
	 *            the final state of the variable in the created variable
	 *            declaration instruction.
	 * @param variableName
	 *            the name of the variable in the created variable declaration
	 *            instruction.
	 * @param assignmentOperator
	 *            the assignment operator.
	 * @param assignmentInstruction
	 *            the assignment instruction.
	 * @return the created variable declaration instruction with assignment
	 *         part.
	 */
	public static Instruction createAndSetVariableDeclarationInstruction(String variableTypeName, boolean isFinal,
			String variableName, AssignmentKind assignmentOperator, IElementaryInstruction assignmentInstruction) {
		Modifier modifier = ModifierBuilder.builder()
				.setInheritance(isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE).build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		ElementaryInstruction leftHandSide = new ElementaryInstruction(VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createTypeAccess(variableTypeName)).setModifier(modifier)
				.addFragment(variableDeclarationFragment).build());
		return InstructionSetterHelper.createAssignmentInstruction(leftHandSide, assignmentOperator,
				assignmentInstruction);
	}

}
