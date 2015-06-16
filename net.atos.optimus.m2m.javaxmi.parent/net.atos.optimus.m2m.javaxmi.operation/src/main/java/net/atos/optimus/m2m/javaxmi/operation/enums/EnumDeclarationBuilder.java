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
package net.atos.optimus.m2m.javaxmi.operation.enums;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create enum declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class EnumDeclarationBuilder {

	/** The build enum declaration */
	private EnumDeclaration buildEnumDeclaration;

	/**
	 * Give an enum declaration builder
	 * 
	 * @return a new enum declaration builder.
	 */
	public static EnumDeclarationBuilder builder() {
		return new EnumDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private EnumDeclarationBuilder() {
		this.buildEnumDeclaration = JavaFactory.eINSTANCE.createEnumDeclaration();
	}

	/**
	 * Build an enum declaration of modisco model
	 * 
	 * @return a new enum declaration of modisco model.
	 */
	public EnumDeclaration build() {
		return this.buildEnumDeclaration;
	}

	/**
	 * Set the name of the enum declaration under construction
	 * 
	 * @param name
	 *            the name of the enum declaration under construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setName(String name) {
		this.buildEnumDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the package of the enum declaration under construction
	 * 
	 * @param javaPackage
	 *            the parent package of the enum declaration under construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setPackage(Package javaPackage) {
		this.buildEnumDeclaration.setPackage(javaPackage);
		return this;
	}

	/**
	 * Set the proxy state of the enum declaration under construction
	 * 
	 * @param isProxy
	 *            the proxy state of the enum declaration under construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setProxy(boolean isProxy) {
		this.buildEnumDeclaration.setProxy(isProxy);
		return this;
	}

	/**
	 * Set the modifier of the enum declaration under construction
	 * 
	 * @param modifier
	 *            the modifier of the enum declaration under construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setModifier(Modifier modifier) {
		this.buildEnumDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the compilation unit of the enum declaration under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the enum declaration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildEnumDeclaration.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the abstract type of the enum declaration under construction
	 * 
	 * @param typeDeclaration
	 *            the abstract type of the enum declaration under construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder setAbstractTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.buildEnumDeclaration.setAbstractTypeDeclaration(typeDeclaration);
		return this;
	}

	/**
	 * Add interfaces list to the enum declaration under construction
	 * 
	 * @param interfaces
	 *            the interfaces list to add to the enum declaration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder addInterfaces(TypeAccess... interfaces) {
		EList<TypeAccess> interfacesList = this.buildEnumDeclaration.getSuperInterfaces();
		for (TypeAccess javaInterface : interfaces) {
			interfacesList.add(javaInterface);
		}
		return this;
	}

	/**
	 * Add enum constants list to the enum declaration under construction
	 * 
	 * @param enumConstants
	 *            the enum constants list to add to the enum declaration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumDeclarationBuilder addEnumConstants(EnumConstantDeclaration... enumConstants) {
		EList<EnumConstantDeclaration> enumConstantsList = this.buildEnumDeclaration.getEnumConstants();
		for (EnumConstantDeclaration enumConstant : enumConstants) {
			enumConstantsList.add(enumConstant);
		}
		return this;
	}

}
