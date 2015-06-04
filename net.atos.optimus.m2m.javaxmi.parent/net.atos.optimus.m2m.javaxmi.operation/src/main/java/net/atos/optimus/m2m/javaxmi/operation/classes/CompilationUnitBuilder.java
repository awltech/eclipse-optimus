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
package net.atos.optimus.m2m.javaxmi.operation.classes;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create compilation unit of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CompilationUnitBuilder {

	/** The build compilation unit */
	private CompilationUnit buildCompilationUnit;

	/**
	 * Give a new compilation unit builder
	 * 
	 * @return a new compilation unit builder.
	 */
	public static CompilationUnitBuilder builder() {
		return new CompilationUnitBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private CompilationUnitBuilder() {
		this.buildCompilationUnit = JavaFactory.eINSTANCE.createCompilationUnit();
	}

	/**
	 * Build a compilation unit of modisco model
	 * 
	 * @return a new compilation unit of modisco model.
	 */
	public CompilationUnit build() {
		return this.buildCompilationUnit;
	}

	/**
	 * Set the name of the compilation unit under construction
	 * 
	 * @param name
	 *            the name of the compilation unit under construction.
	 * @return the builder.
	 */
	public CompilationUnitBuilder setName(String name) {
		this.buildCompilationUnit.setName(name);
		return this;
	}

	/**
	 * Set the package of the compilation unit under construction
	 * 
	 * @param javaPackage
	 *            the parent package of the compilation unit under construction.
	 * @return the builder.
	 */
	public CompilationUnitBuilder setPackage(Package javaPackage) {
		this.buildCompilationUnit.setPackage(javaPackage);
		return this;
	}

	/**
	 * Add a class declaration of the compilation unit under construction
	 * 
	 * @param classDeclaration
	 *            the class declaration to add to the compilation unit under
	 *            construction
	 * @return the builder.
	 */
	public CompilationUnitBuilder addClassDeclaration(ClassDeclaration classDeclaration) {
		this.buildCompilationUnit.getTypes().add(classDeclaration);
		return this;
	}

}
