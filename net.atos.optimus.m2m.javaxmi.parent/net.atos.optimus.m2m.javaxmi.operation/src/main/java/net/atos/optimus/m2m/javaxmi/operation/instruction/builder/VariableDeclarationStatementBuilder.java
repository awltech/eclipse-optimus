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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder;

import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create variable declaration statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableDeclarationStatementBuilder {

	/** The build variable declaration statement */
	private VariableDeclarationStatement buildVariableDeclarationStatement;

	/**
	 * Give a new variable declaration statement builder
	 * 
	 * @return a new variable declaration statement builder.
	 */
	public static VariableDeclarationStatementBuilder builder() {
		return new VariableDeclarationStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private VariableDeclarationStatementBuilder() {
		this.buildVariableDeclarationStatement = JavaFactory.eINSTANCE.createVariableDeclarationStatement();
	}

	/**
	 * Build a variable declaration statement of modisco model
	 * 
	 * @return a new variable declaration statement of modisco model.
	 */
	public VariableDeclarationStatement build() {
		return this.buildVariableDeclarationStatement;
	}

	/**
	 * Set the type of the variable declaration statement under construction
	 * 
	 * @param type
	 *            the type of the variable statement expression under
	 *            construction.
	 * @return the builder.
	 */
	public VariableDeclarationStatementBuilder setType(TypeAccess type) {
		this.buildVariableDeclarationStatement.setType(type);
		return this;
	}

	/**
	 * Set the modifier of the variable declaration statement under construction
	 * 
	 * @param modifier
	 *            the modifier of the variable statement under construction.
	 * @return the builder.
	 */
	public VariableDeclarationStatementBuilder setModifier(Modifier modifier) {
		this.buildVariableDeclarationStatement.setModifier(modifier);
		return this;
	}

	/**
	 * Add a variable declarations list to the variable declaration statement
	 * under construction
	 * 
	 * @param variableDeclarations
	 *            the variable declarations list to add to the variable
	 *            declaration statement under construction.
	 * @return the builder.
	 */
	public VariableDeclarationStatementBuilder addFragment(VariableDeclarationFragment... variableDeclarations) {
		for (VariableDeclarationFragment variableDeclaration : variableDeclarations) {
			this.buildVariableDeclarationStatement.getFragments().add(variableDeclaration);
		}
		return this;
	}

}
