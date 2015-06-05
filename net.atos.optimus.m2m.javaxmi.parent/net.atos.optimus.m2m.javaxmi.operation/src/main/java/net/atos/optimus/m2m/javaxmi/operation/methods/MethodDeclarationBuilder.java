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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create method declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MethodDeclarationBuilder {

	/** The build method declaration */
	private MethodDeclaration buildMethodDeclaration;

	/**
	 * Give a new method declaration builder
	 * 
	 * @return a new method declaration builder.
	 */
	public static MethodDeclarationBuilder builder() {
		return new MethodDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private MethodDeclarationBuilder() {
		this.buildMethodDeclaration = JavaFactory.eINSTANCE.createMethodDeclaration();
	}

	/**
	 * Build a method declaration of modisco model
	 * 
	 * @return a new method declaration of modisco model.
	 */
	public MethodDeclaration build() {
		return this.buildMethodDeclaration;
	}

	/**
	 * Set the name of the method declaration under construction
	 * 
	 * @param name
	 *            the name of the method declaration under construction.
	 * @return the builder.
	 */
	public MethodDeclarationBuilder setName(String name) {
		this.buildMethodDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the compilation unit of the method declaration under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the method declaration under
	 *            construction.
	 * @return the builder.
	 */
	public MethodDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildMethodDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the modifier of the method declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the method declaration under construction.
	 * @return the builder.
	 */
	public MethodDeclarationBuilder setModifier(Modifier modifier) {
		this.buildMethodDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the abstract type of the method declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the method declaration under
	 *            construction.
	 * @return the builder.
	 */
	public MethodDeclarationBuilder setAbstractTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.buildMethodDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Set the return type of the method declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the return type of the method declaration under construction.
	 * @return the builder.
	 */
	public MethodDeclarationBuilder setReturnType(TypeAccess typeAccess) {
		this.buildMethodDeclaration.setReturnType(typeAccess);
		return this;
	}

}
