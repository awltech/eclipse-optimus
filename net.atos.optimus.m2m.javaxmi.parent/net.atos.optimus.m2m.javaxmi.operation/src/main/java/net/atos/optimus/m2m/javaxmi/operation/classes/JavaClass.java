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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models a class : wrapper of ClassDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class JavaClass extends AbstractClass<ClassDeclaration> {

	/**
	 * Constructor of class
	 * 
	 * @param classDeclaration
	 *            the class declaration.
	 */
	public JavaClass(ClassDeclaration classDeclaration) {
		super(classDeclaration);
	}

	@Override
	public JavaClass addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public JavaClass addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public JavaClass addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

	/**
	 * Add interfaces list to the class
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the class.
	 * @return the class.
	 */
	public JavaClass addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.getDelegate().getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

	/**
	 * Add imports list to the class
	 * 
	 * @param importsNames
	 *            the imports names list to add to the class.
	 * @return the class.
	 */
	public JavaClass addImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.getDelegate().getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, false));
		}
		return this;
	}

	/**
	 * Add static imports list to the class
	 * 
	 * @param importsNames
	 *            the static imports names list to add to the class.
	 * @return the class.
	 */
	public JavaClass addStaticImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.getDelegate().getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, true));
		}
		return this;
	}

}
