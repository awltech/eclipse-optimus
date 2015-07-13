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
package net.atos.optimus.m2m.javaxmi.operation.instructions.complex;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.VariableDeclarationStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;

/**
 * The purpose of such class is to help with the creation of variable
 * declarations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableDeclarationHelper {

	/** The build variable declaration */
	private VariableDeclarationStatement buildVariableDeclaration;

	/**
	 * Launch the build of a new variable declaration (not final by default)
	 * 
	 * @param variableTypeName
	 *            the type name of variables in the created variable
	 *            declaration.
	 * @param variableName
	 *            the name of the first variable in the created variable
	 *            declaration.
	 * @return a new helper.
	 */
	public static VariableDeclarationHelper builder(String variableTypeName, String variableName) {
		return new VariableDeclarationHelper(variableTypeName, variableName);
	}

	/**
	 * Private constructor : a new variable declaration (not final by default)
	 * 
	 * @param variableTypeName
	 *            the type name of variables in the created variable
	 *            declaration.
	 * @param variableName
	 *            the name of the first variable in the created variable
	 *            declaration.
	 */
	private VariableDeclarationHelper(String variableTypeName, String variableName) {
		Modifier modifier = ModifierBuilder.builder().setInheritance(InheritanceKind.NONE).build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(variableName).build();
		this.buildVariableDeclaration = VariableDeclarationStatementBuilder.builder()
				.setType(TypeAccessHelper.createTypeAccess(variableTypeName)).setModifier(modifier)
				.addFragment(variableDeclarationFragment).build();
	}

	/**
	 * Give the build variable declaration
	 * 
	 * @return the build variable declaration.
	 */
	public ComplexInstruction build() {
		return new ComplexInstruction(this.buildVariableDeclaration);
	}

	/**
	 * Set the final state of the variable declaration under construction
	 * 
	 * @param isFinal
	 *            the final state of the variable declaration under
	 *            construction.
	 * @return the helper.
	 */
	public VariableDeclarationHelper setFinal(boolean isFinal) {
		this.buildVariableDeclaration.getModifier().setInheritance(
				isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Add variables to the variable declaration under construction
	 * 
	 * @param variablesNames
	 *            the variables names list to add to the variable declaration
	 *            under construction.
	 * @return the helper.
	 */
	public VariableDeclarationHelper addVariable(String... variablesNames) {
		EList<VariableDeclarationFragment> variables = this.buildVariableDeclaration.getFragments();
		for (String variableName : variablesNames) {
			variables.add(VariableDeclarationFragmentBuilder.builder().setName(variableName).build());
		}
		return this;
	}

}
