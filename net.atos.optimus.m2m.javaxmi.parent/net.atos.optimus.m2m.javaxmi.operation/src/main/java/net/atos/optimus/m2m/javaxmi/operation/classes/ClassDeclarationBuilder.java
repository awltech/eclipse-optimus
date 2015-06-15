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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create class declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ClassDeclarationBuilder {

	/** The build class declaration */
	private ClassDeclaration buildClassDeclaration;

	/**
	 * Give a new class declaration builder
	 * 
	 * @return a new class declaration builder.
	 */
	public static ClassDeclarationBuilder builder() {
		return new ClassDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ClassDeclarationBuilder() {
		this.buildClassDeclaration = JavaFactory.eINSTANCE.createClassDeclaration();
	}

	/**
	 * Build a class declaration of modisco model
	 * 
	 * @return a new class declaration of modisco model.
	 */
	public ClassDeclaration build() {
		return this.buildClassDeclaration;
	}

	/**
	 * Set the name of the class declaration under construction
	 * 
	 * @param name
	 *            the name of the class declaration under construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setName(String name) {
		this.buildClassDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the package of the class declaration under construction
	 * 
	 * @param javaPackage
	 *            the parent package of the class declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setPackage(Package javaPackage) {
		this.buildClassDeclaration.setPackage(javaPackage);
		return this;
	}

	/**
	 * Set the proxy state of the class declaration under construction
	 * 
	 * @param isProxy
	 *            the proxy state of the class declaration under construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setProxy(boolean isProxy) {
		this.buildClassDeclaration.setProxy(isProxy);
		return this;
	}

	/**
	 * Set the modifier of the class declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the class declaration under construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setModifier(Modifier modifier) {
		this.buildClassDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the compilation unit of the class declaration under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the class declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildClassDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the abstract type of the class declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the class declaration under construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setAbstractTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.buildClassDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Set the super type class of the class declaration under construction
	 * 
	 * @param superType
	 *            the super type class of the class declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder setSuperClass(TypeAccess superType) {
		this.buildClassDeclaration.setSuperClass(superType);
		return this;
	}

	/**
	 * Add interfaces list to the class declaration under construction
	 * 
	 * @param interfaces
	 *            the interfaces list to add to the class declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ClassDeclarationBuilder addInterfaces(TypeAccess... interfaces) {
		EList<TypeAccess> interfacesList = this.buildClassDeclaration.getSuperInterfaces();
		for (TypeAccess javaInterface : interfaces) {
			interfacesList.add(javaInterface);
		}
		return this;
	}

}
