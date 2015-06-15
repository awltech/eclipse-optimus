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
package net.atos.optimus.m2m.javaxmi.operation.interfaces;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create interface declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InterfaceDeclarationBuilder {

	/** The build interface declaration */
	private InterfaceDeclaration buildInterfaceDeclaration;

	/**
	 * Give a new interface declaration builder
	 * 
	 * @return a new interface declaration builder.
	 */
	public static InterfaceDeclarationBuilder builder() {
		return new InterfaceDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private InterfaceDeclarationBuilder() {
		this.buildInterfaceDeclaration = JavaFactory.eINSTANCE.createInterfaceDeclaration();
	}

	/**
	 * Build an interface declaration of modisco model
	 * 
	 * @return a new interface declaration of modisco model.
	 */
	public InterfaceDeclaration build() {
		return this.buildInterfaceDeclaration;
	}

	/**
	 * Set the name of the interface declaration under construction
	 * 
	 * @param name
	 *            the name of the interface declaration under construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setName(String name) {
		this.buildInterfaceDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the package of the interface declaration under construction
	 * 
	 * @param javaPackage
	 *            the parent package of the interface declaration under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setPackage(Package javaPackage) {
		this.buildInterfaceDeclaration.setPackage(javaPackage);
		return this;
	}

	/**
	 * Set the proxy of the interface declaration under construction
	 * 
	 * @param value
	 *            the proxy value.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setProxy(boolean value) {
		this.buildInterfaceDeclaration.setProxy(value);
		return this;
	}

	/**
	 * Set the modifier of the interface declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the interface declaration under construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setModifier(Modifier modifier) {
		this.buildInterfaceDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the compilation unit of the interface declaration under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the interface declaration under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildInterfaceDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the abstract type of the interface declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the interface declaration under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder setAbstractTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.buildInterfaceDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Add interfaces list to the interface declaration under construction
	 * 
	 * @param interfaces
	 *            the interfaces list to add to the interface declaration under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceDeclarationBuilder addInterfaces(TypeAccess... interfaces) {
		EList<TypeAccess> interfacesList = this.buildInterfaceDeclaration.getSuperInterfaces();
		for (TypeAccess javaInterface : interfaces) {
			interfacesList.add(javaInterface);
		}
		return this;
	}

}
