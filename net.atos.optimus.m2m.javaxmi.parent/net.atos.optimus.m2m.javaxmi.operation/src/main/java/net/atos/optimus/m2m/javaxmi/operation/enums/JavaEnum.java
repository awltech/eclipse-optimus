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
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;
import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models an enumeration : wrapper of EnumDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class JavaEnum extends AbstractClass<EnumDeclaration> {

	/**
	 * Constructor of enumeration
	 * 
	 * @param enumDeclaration
	 *            the enumeration declaration.
	 */
	public JavaEnum(EnumDeclaration enumDeclaration) {
		super(enumDeclaration);
	}

	@Override
	public JavaEnum addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public JavaEnum addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public JavaEnum addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

	@Override
	public JavaEnum addAnnotations(JavaAnnotation... annotations) {
		super.addAnnotations(annotations);
		return this;
	}

	/**
	 * Add an enum constant to the enumeration
	 * 
	 * @param constantName
	 *            the enum constant name to add to the enumeration.
	 * @param arguments
	 *            the arguments list associated to the enum constant to add to
	 *            the enumeration.
	 * @return the enumeration.
	 */
	public JavaEnum addEnumConstant(String constantName, IElementaryInstruction... arguments) {
		EnumConstantDeclaration enumConstantDeclaration = EnumConstantDeclarationBuilder.builder()
				.setName(constantName).build();
		EList<Expression> argumentsList = enumConstantDeclaration.getArguments();
		for (IElementaryInstruction argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		this.getDelegate().getEnumConstants().add(enumConstantDeclaration);
		return this;
	}

	/**
	 * Add interfaces list to the enumeration
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the enumeration.
	 * @return the enumeration.
	 */
	public JavaEnum addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.getDelegate().getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

	/**
	 * Add imports list to the enumeration
	 * 
	 * @param importsNames
	 *            the imports names list to add to the enumeration.
	 * @return the enumeration.
	 */
	public JavaEnum addImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.getDelegate().getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, false));
		}
		return this;
	}

	/**
	 * Add static imports list to the enumeration
	 * 
	 * @param importsNames
	 *            the static imports names list to add to the enumeration.
	 * @return the enumeration.
	 */
	public JavaEnum addStaticImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.getDelegate().getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, true));
		}
		return this;
	}

}
