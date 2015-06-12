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
import net.atos.optimus.m2m.javaxmi.operation.packages.Package;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.InheritanceKind;
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

	/** The build class */
	private ClassDeclaration buildClass;

	/**
	 * Launch the build of a new class (public, proxy state set to false and
	 * inheritance state set to none by default)
	 * 
	 * @param javaPackage
	 *            the package a the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 * @return a new helper.
	 */
	public static ClassHelper classBuilder(Package javaPackage, String className) {
		return new ClassHelper(javaPackage, className);
	}

	/**
	 * Launch the build of a new internal class (public, proxy state set to
	 * false and inheritance state set to none by default)
	 * 
	 * @param javaClass
	 *            the class containing the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 * @return a new helper.
	 */
	public static ClassHelper internalClassBuilder(Class javaClass, String className) {
		return new ClassHelper(javaClass, className);
	}

	/**
	 * Private constructor : a new class (public, proxy state set to false and
	 * inheritance state set to none by default)
	 * 
	 * @param javaPackage
	 *            the package a the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 */
	private ClassHelper(Package javaPackage, String className) {
		org.eclipse.gmt.modisco.java.Package internalPackage = javaPackage.getPackage();
		CompilationUnit compilationUnit = CompilationUnitBuilder.builder().setName(className + ".java")
				.setPackage(internalPackage).build();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(compilationUnit).build();
		this.buildClass = ClassDeclarationBuilder.builder().setName(className).setPackage(internalPackage)
				.setProxy(false).setModifier(modifier).setCompilationUnit(compilationUnit).build();
		compilationUnit.getTypes().add(this.buildClass);
		internalPackage.getModel().getCompilationUnits().add(compilationUnit);
	}

	/**
	 * Private constructor : a new internal class (public, proxy state set to
	 * false and inheritance state set to none by default)
	 * 
	 * @param javaClass
	 *            the class containing the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 */
	private ClassHelper(Class javaClass, String className) {
		ClassDeclaration classDeclaration = javaClass.getClassDeclaration();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(classDeclaration.getOriginalCompilationUnit())
				.build();
		this.buildClass = ClassDeclarationBuilder.builder().setName(className)
				.setPackage(classDeclaration.getPackage()).setProxy(false).setModifier(modifier)
				.setCompilationUnit(classDeclaration.getOriginalCompilationUnit())
				.setAbstractTypeDeclaration(classDeclaration).build();
	}

	/**
	 * Give the build class
	 * 
	 * @return the build class.
	 */
	public Class build() {
		return new Class(this.buildClass);
	}

	/**
	 * Set the visibility of the class under construction
	 * 
	 * @param visibility
	 *            the visibility of the class under construction.
	 * @return the helper.
	 */
	public ClassHelper setVisibility(VisibilityKind visibility) {
		this.buildClass.getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the proxy state of the class under construction
	 * 
	 * @param proxyState
	 *            the proxy state of the class under construction.
	 * @return the helper.
	 */
	public ClassHelper setProxy(boolean proxyState) {
		this.buildClass.setProxy(proxyState);
		return this;
	}

	/**
	 * Set the inheritance state of the class under construction
	 * 
	 * @param inheritance
	 *            the inheritance state of the class under construction.
	 * @return the helper.
	 */
	public ClassHelper setInheritance(InheritanceKind inheritance) {
		this.buildClass.getModifier().setInheritance(inheritance);
		return this;
	}

	/**
	 * Create a class in a stand alone compilation unit
	 * 
	 * @param javaPackage
	 *            the package of the created class.
	 * @param className
	 *            the name of the created class without .java extension.
	 * @param visibility
	 *            the visibility of the created class.
	 * @param inheritance
	 *            the inheritance state of the created class.
	 * @param proxyState
	 *            the proxy state of of the created class.
	 * @return the created class accordingly to the specified parameters.
	 */
	public static Class createClass(Package javaPackage, String className, VisibilityKind visibility,
			InheritanceKind inheritance, boolean proxyState) {
		return ClassHelper.classBuilder(javaPackage, className).setVisibility(visibility).setInheritance(inheritance)
				.setProxy(proxyState).build();
	}

	/**
	 * Create an internal class
	 * 
	 * @param javaClass
	 *            the class of the created internal class.
	 * @param className
	 *            the name of the created class without .java extension.
	 * @param visibility
	 *            the visibility of the created class.
	 * @param inheritance
	 *            the inheritance state of the created class.
	 * @param proxyState
	 *            the proxy state of of the created class.
	 * @return the created internal class accordingly to the specified
	 *         parameters.
	 */
	public static Class createInternalClass(Class javaClass, String className, VisibilityKind visibility,
			InheritanceKind inheritance, boolean proxyState) {
		return ClassHelper.internalClassBuilder(javaClass, className).setVisibility(visibility)
				.setInheritance(inheritance).setProxy(proxyState).build();
	}

}
