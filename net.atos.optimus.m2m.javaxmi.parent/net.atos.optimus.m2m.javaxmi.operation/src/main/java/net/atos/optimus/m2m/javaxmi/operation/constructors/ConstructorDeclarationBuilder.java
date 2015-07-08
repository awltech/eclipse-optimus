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
package net.atos.optimus.m2m.javaxmi.operation.constructors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create constructor declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ConstructorDeclarationBuilder {

	/** The build constructor declaration */
	private ConstructorDeclaration buildConstructorDeclaration;

	/**
	 * Give a new constructor declaration builder
	 * 
	 * @return a new constructor declaration builder.
	 */
	public static ConstructorDeclarationBuilder builder() {
		return new ConstructorDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ConstructorDeclarationBuilder() {
		this.buildConstructorDeclaration = JavaFactory.eINSTANCE.createConstructorDeclaration();
	}

	/**
	 * Build a constructor declaration of modisco model
	 * 
	 * @return a new constructor declaration of modisco model.
	 */
	public ConstructorDeclaration build() {
		return this.buildConstructorDeclaration;
	}

	/**
	 * Set the name of the constructor under construction
	 * 
	 * @param name
	 *            the name of the constructor under construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder setName(String name) {
		this.buildConstructorDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the compilation unit of the constructor declaration under
	 * construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the constructor declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildConstructorDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the modifier of the constructor declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the constructor declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder setModifier(Modifier modifier) {
		this.buildConstructorDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the abstract type of the constructor declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the constructor declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder setAbstractTypeDeclaration(AbstractTypeDeclaration typeDeclaration) {
		this.buildConstructorDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Set the body of the constructor declaration under construction
	 * 
	 * @param body
	 *            the body of the constructor declaration under construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder setBody(Block body) {
		this.buildConstructorDeclaration.setBody(body);
		return this;
	}

	/**
	 * Add a list of single variable declarations to the constructor declaration
	 * under construction
	 * 
	 * @param singleVariableDeclarations
	 *            the list of single variable declarations to add to the
	 *            constructor declaration under construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder addPameters(SingleVariableDeclaration... singleVariableDeclarations) {
		EList<SingleVariableDeclaration> parameters = this.buildConstructorDeclaration.getParameters();
		for (SingleVariableDeclaration singleVariableDeclaration : singleVariableDeclarations) {
			parameters.add(singleVariableDeclaration);
		}
		return this;
	}

	/**
	 * Add a list of exceptions to the constructor declaration under
	 * construction
	 * 
	 * @param exceptions
	 *            the list of exceptions to add to the constructor declaration
	 *            under construction.
	 * @return the builder.
	 */
	public ConstructorDeclarationBuilder addExceptions(TypeAccess... exceptions) {
		EList<TypeAccess> exceptionsList = this.buildConstructorDeclaration.getThrownExceptions();
		for (TypeAccess exception : exceptions) {
			exceptionsList.add(exception);
		}
		return this;
	}

}
