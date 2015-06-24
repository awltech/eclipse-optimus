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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.CompilationUnitBuilder;
import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;
import net.atos.optimus.m2m.javaxmi.operation.util.ASTElementFinder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of enumerations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class EnumHelper {

	/** The build enumeration */
	private EnumDeclaration buildEnum;

	/**
	 * Launch the build of a new enumeration (public, proxy state set to false
	 * and no interface by default)
	 * 
	 * @param javaPackage
	 *            the package a the enumeration under construction.
	 * @param enumName
	 *            the name of the enumeration under construction.
	 * @return a new helper.
	 */
	public static EnumHelper builder(JavaPackage javaPackage, String enumName) {
		return new EnumHelper(javaPackage, enumName);
	}

	/**
	 * Private constructor : a new enumeration (public, proxy state set to false
	 * and no interface by default)
	 * 
	 * @param javaPackage
	 *            the package a the enumeration under construction.
	 * @param enumName
	 *            the name of the enumeration under construction.
	 */
	private EnumHelper(JavaPackage javaPackage, String enumName) {
		org.eclipse.gmt.modisco.java.Package internalPackage = javaPackage.getDelegate();
		CompilationUnit compilationUnit = CompilationUnitBuilder.builder().setName(enumName + ".java")
				.setPackage(internalPackage).build();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(compilationUnit).build();
		this.buildEnum = EnumDeclarationBuilder.builder().setName(enumName).setPackage(internalPackage).setProxy(false)
				.setModifier(modifier).setCompilationUnit(compilationUnit).build();
		compilationUnit.getTypes().add(this.buildEnum);
		ASTElementFinder.findModel(internalPackage).getCompilationUnits().add(compilationUnit);
	}

	/**
	 * Give the build enumeration
	 * 
	 * @return the build enumeration.
	 */
	public JavaEnum build() {
		return new JavaEnum(this.buildEnum);
	}

	/**
	 * Set the proxy state of the enumeration under construction
	 * 
	 * @param proxyState
	 *            the proxy state of the enumeration under construction.
	 * @return the helper.
	 */
	public EnumHelper setProxy(boolean proxyState) {
		this.buildEnum.setProxy(proxyState);
		return this;
	}

	/**
	 * Add an enum constant to the enumeration under construction
	 * 
	 * @param constantName
	 *            the enum constant name to add to the enumeration under
	 *            construction.
	 * @param arguments
	 *            the arguments list associated to the enum constant to add to
	 *            the enumeration under construction.
	 * @return the helper.
	 */
	public EnumHelper addEnumConstant(String constantName, IElementaryInstruction... arguments) {
		EnumConstantDeclaration enumConstantDeclaration = EnumConstantDeclarationBuilder.builder()
				.setName(constantName).build();
		EList<Expression> argumentsList = enumConstantDeclaration.getArguments();
		for (IElementaryInstruction argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		this.buildEnum.getEnumConstants().add(enumConstantDeclaration);
		return this;
	}

	/**
	 * Add interfaces list to the enumeration under construction
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the enumeration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumHelper addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.buildEnum.getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

	/**
	 * Add imports list to the enumeration under construction
	 * 
	 * @param importsNames
	 *            the imports names list to add to the enumeration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumHelper addImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildEnum.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, false));
		}
		return this;
	}

	/**
	 * Add static imports list to the enumeration under construction
	 * 
	 * @param importsNames
	 *            the static imports names list to add to the enumeration under
	 *            construction.
	 * @return the builder.
	 */
	public EnumHelper addStaticImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildEnum.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, true));
		}
		return this;
	}

	/**
	 * Create an enumeration in a stand alone compilation unit
	 * 
	 * @param javaPackage
	 *            the package of the created enumeration.
	 * @param enumName
	 *            the name of the created enumeration without .java extension.
	 * @param proxyState
	 *            the proxy state of of the created enumeration.
	 * @param interfacesNames
	 *            the interfaces names list to add to the created enumeration.
	 * @return the created class accordingly to the specified parameters.
	 */
	public static JavaEnum createEnum(JavaPackage javaPackage, String enumName, boolean proxyState,
			String... interfacesNames) {
		return EnumHelper.builder(javaPackage, enumName).setProxy(proxyState).addInterfaces(interfacesNames).build();
	}

}
