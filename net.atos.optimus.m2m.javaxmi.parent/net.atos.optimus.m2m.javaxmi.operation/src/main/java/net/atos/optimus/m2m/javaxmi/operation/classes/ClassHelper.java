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

import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of classes
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ClassHelper {

	/**
	 * Create a class in a stand alone compilation unit,set proxy to false and
	 * visibility to public
	 * 
	 * @param javaModel
	 *            the java model of the created class.
	 * @param javaPackage
	 *            the package of the created class.
	 * @param className
	 *            the name of the created class without .java extension.
	 * @return the created class accordingly to the specified parameters.
	 */
	public static ClassDeclaration createClass(Model javaModel, Package javaPackage, String className) {
		return ClassHelper.createClass(javaModel, javaPackage, className, VisibilityKind.PUBLIC, false);
	}

	/**
	 * Create a class in a stand alone compilation unit
	 * 
	 * @param javaModel
	 *            the java model of the created class.
	 * @param javaPackage
	 *            the package of the created class.
	 * @param className
	 *            the name of the created class without .java extension.
	 * @param visibility
	 *            the visibility of the created class.
	 * @param proxyState
	 *            the proxy state of of the created class.
	 * @return the created class accordingly to the specified parameters.
	 */
	public static ClassDeclaration createClass(Model javaModel, Package javaPackage, String className,
			VisibilityKind visibility, boolean proxyState) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility).build();
		CompilationUnit compilationUnit = CompilationUnitBuilder.builder().setName(className + ".java")
				.setPackage(javaPackage).build();
		ClassDeclaration classDeclaration = ClassDeclarationBuilder.builder().setName(className)
				.setPackage(javaPackage).setProxy(proxyState).setModifier(modifier).setCompilationUnit(compilationUnit)
				.build();
		compilationUnit.getTypes().add(classDeclaration);
		javaModel.getCompilationUnits().add(compilationUnit);
		return classDeclaration;
	}
}
