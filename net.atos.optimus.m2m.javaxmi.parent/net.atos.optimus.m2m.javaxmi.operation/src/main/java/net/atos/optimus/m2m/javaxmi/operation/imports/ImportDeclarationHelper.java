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
package net.atos.optimus.m2m.javaxmi.operation.imports;

import net.atos.optimus.m2m.javaxmi.operation.classes.ClassDeclarationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.packages.PackageBuilder;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Package;

/**
 * The purpose of such class is to help with the creation of import declarations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ImportDeclarationHelper {

	/** String constant : ending characters of import group */
	public static final String PACKAGE_IMPORT_END = ".*";

	/**
	 * Private constructor
	 * 
	 */
	private ImportDeclarationHelper() {
	}

	/**
	 * Create a class import declaration
	 * 
	 * @param fullQualifiedClassName
	 *            the full qualified name of the class to import in the created
	 *            class import declaration.
	 * @param isStatic
	 *            the static state of the created class import declaration.
	 * @return the created class import declaration.
	 */
	protected static ImportDeclaration createClassImportDeclaration(String fullQualifiedClassName, boolean isStatic) {
		ClassDeclaration classDeclaration = ClassDeclarationBuilder.builder().setName(fullQualifiedClassName).build();
		return ImportDeclarationBuilder.builder().setImportedElement(classDeclaration).setStatic(isStatic).build();
	}

	/**
	 * Create a package import declaration
	 * 
	 * @param packageName
	 *            the full qualified name of the package to import in the
	 *            created package import declaration.
	 * @param isStatic
	 *            the static state of the created package import declaration.
	 * @return the created package import declaration.
	 */
	protected static ImportDeclaration createPackageImportDeclaration(String packageName, boolean isStatic) {
		Package javaPackage = PackageBuilder.builder().setName(packageName).build();
		return ImportDeclarationBuilder.builder().setImportedElement(javaPackage).setStatic(isStatic).build();
	}

	/**
	 * Create an import declaration
	 * 
	 * @param fullyQualifiedName
	 *            the full qualified name of the package or the class to import.
	 * @param isStatic
	 *            the static state of the created import declaration.
	 * @return the created import declaration.
	 */
	public static ImportDeclaration createImportDeclaration(String fullyQualifiedName, boolean isStatic) {
		if (fullyQualifiedName.endsWith(ImportDeclarationHelper.PACKAGE_IMPORT_END)) {
			fullyQualifiedName = fullyQualifiedName.substring(0, fullyQualifiedName.length()
					- ImportDeclarationHelper.PACKAGE_IMPORT_END.length());
			return ImportDeclarationHelper.createPackageImportDeclaration(fullyQualifiedName, isStatic);
		}
		int lastIndex = fullyQualifiedName.lastIndexOf('.');
		if (Character.isUpperCase(lastIndex == -1 ? fullyQualifiedName.charAt(0) : fullyQualifiedName
				.charAt(lastIndex + 1))) {
			return ImportDeclarationHelper.createClassImportDeclaration(fullyQualifiedName, isStatic);
		} else {
			return ImportDeclarationHelper.createPackageImportDeclaration(fullyQualifiedName, isStatic);
		}

	}

}
