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
package net.atos.optimus.m2m.javaxmi.operation.fields;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create field declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class FieldDeclarationBuilder {

	/** The build field declaration */
	private FieldDeclaration buildFieldDeclaration;

	/**
	 * Give a new field declaration builder
	 * 
	 * @return a new field declaration builder.
	 */
	public static FieldDeclarationBuilder builder() {
		return new FieldDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private FieldDeclarationBuilder() {
		this.buildFieldDeclaration = JavaFactory.eINSTANCE.createFieldDeclaration();
	}

	/**
	 * Build a field declaration of modisco model
	 * 
	 * @return a new field declaration of modisco model.
	 */
	public FieldDeclaration build() {
		return this.buildFieldDeclaration;
	}

	/**
	 * Set the compilation unit of the field declaration under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the field declaration under
	 *            construction.
	 * @return the builder.
	 */
	public FieldDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildFieldDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the modifier of the field declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the field declaration under construction.
	 * @return the builder.
	 */
	public FieldDeclarationBuilder setModifier(Modifier modifier) {
		this.buildFieldDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the abstract type of the field declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the field declaration under construction.
	 * @return the builder.
	 */
	public FieldDeclarationBuilder setAbstractTypeDeclaration(AbstractTypeDeclaration typeDeclaration) {
		this.buildFieldDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Add a variable declaration fragment to the field declaration under
	 * construction
	 * 
	 * @param fragment
	 *            the variable declaration fragment to add to the field
	 *            declaration under construction
	 * @return the builder.
	 */
	public FieldDeclarationBuilder addFragment(VariableDeclarationFragment fragment) {
		this.buildFieldDeclaration.getFragments().add(fragment);
		return this;
	}

	/**
	 * Set the type of the field declaration under construction
	 * 
	 * @param typeAccess
	 *            the type of the field declaration under construction.
	 * @return the builder.
	 */
	public FieldDeclarationBuilder setType(TypeAccess typeAccess) {
		this.buildFieldDeclaration.setType(typeAccess);
		return this;
	}

}
