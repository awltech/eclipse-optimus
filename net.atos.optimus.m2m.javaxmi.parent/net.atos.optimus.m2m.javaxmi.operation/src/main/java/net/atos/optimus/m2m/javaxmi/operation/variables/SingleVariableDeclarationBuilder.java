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
package net.atos.optimus.m2m.javaxmi.operation.variables;

import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create single variable declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SingleVariableDeclarationBuilder {

	/** The build single variable declaration */
	private SingleVariableDeclaration buildSingleVariableDeclaration;

	/**
	 * Give a new single variable declaration builder
	 * 
	 * @return a new single variable declaration builder.
	 */
	public static SingleVariableDeclarationBuilder builder() {
		return new SingleVariableDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private SingleVariableDeclarationBuilder() {
		this.buildSingleVariableDeclaration = JavaFactory.eINSTANCE.createSingleVariableDeclaration();
	}

	/**
	 * Build a single variable declaration of modisco model
	 * 
	 * @return a new single variable declaration of modisco model.
	 */
	public SingleVariableDeclaration build() {
		return this.buildSingleVariableDeclaration;
	}

	/**
	 * Set the name of the single variable declaration under construction
	 * 
	 * @param name
	 *            the name of the single variable declaration under
	 *            construction.
	 * @return the builder.
	 */
	public SingleVariableDeclarationBuilder setName(String name) {
		this.buildSingleVariableDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the type of the single variable declaration under construction
	 * 
	 * @param typeVariable
	 *            the type of the single variable declaration under
	 *            construction.
	 * @return the builder.
	 */
	public SingleVariableDeclarationBuilder setType(TypeAccess typeVariable) {
		this.buildSingleVariableDeclaration.setType(typeVariable);
		return this;
	}

}
