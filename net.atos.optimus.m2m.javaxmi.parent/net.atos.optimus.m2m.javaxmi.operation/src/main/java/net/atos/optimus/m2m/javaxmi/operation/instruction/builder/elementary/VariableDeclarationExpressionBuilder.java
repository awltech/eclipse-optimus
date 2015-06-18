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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary;

import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create variable declaration expression of modisco
 * model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableDeclarationExpressionBuilder {

	/** The build variable declaration expression */
	private VariableDeclarationExpression buildVariableDeclarationExpression;

	/**
	 * Give a new variable declaration expression builder
	 * 
	 * @return a new variable declaration expression builder.
	 */
	public static VariableDeclarationExpressionBuilder builder() {
		return new VariableDeclarationExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private VariableDeclarationExpressionBuilder() {
		this.buildVariableDeclarationExpression = JavaFactory.eINSTANCE.createVariableDeclarationExpression();
	}

	/**
	 * Build a variable declaration expression of modisco model
	 * 
	 * @return a new variable declaration expression of modisco model.
	 */
	public VariableDeclarationExpression build() {
		return this.buildVariableDeclarationExpression;
	}

	/**
	 * Set the modifier of the variable declaration expression under
	 * construction
	 * 
	 * @param modifier
	 *            the modifier of the variable expression under construction.
	 * @return the builder.
	 */
	public VariableDeclarationExpressionBuilder setModifier(Modifier modifier) {
		this.buildVariableDeclarationExpression.setModifier(modifier);
		return this;
	}

	/**
	 * Set the type of the variable declaration expression under construction
	 * 
	 * @param type
	 *            the type of the variable declaration expression under
	 *            construction.
	 * @return the builder.
	 */
	public VariableDeclarationExpressionBuilder setType(TypeAccess type) {
		this.buildVariableDeclarationExpression.setType(type);
		return this;
	}

	/**
	 * Add a variable declarations list to the variable declaration expression
	 * under construction
	 * 
	 * @param variableDeclarations
	 *            the variable declarations list to add to the variable
	 *            declaration expression under construction.
	 * @return the builder.
	 */
	public VariableDeclarationExpressionBuilder addFragment(VariableDeclarationFragment... variableDeclarations) {
		for (VariableDeclarationFragment variableDeclaration : variableDeclarations) {
			this.buildVariableDeclarationExpression.getFragments().add(variableDeclaration);
		}
		return this;
	}

}
