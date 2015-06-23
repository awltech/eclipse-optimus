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
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeParameterHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
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
	 * Launch the build of a new class (public, proxy state set to false, no
	 * super class, no interface and inheritance state set to none by default)
	 * 
	 * @param javaPackage
	 *            the package a the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 * @return a new helper.
	 */
	public static ClassHelper builder(JavaPackage javaPackage, String className) {
		return new ClassHelper(javaPackage, className);
	}

	/**
	 * Launch the build of a new internal class (public, proxy state set to
	 * false, no super class, no interface and inheritance state set to none by
	 * default)
	 * 
	 * @param javaClass
	 *            the class containing the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 * @return a new helper.
	 */
	public static ClassHelper internalClassBuilder(JavaClass javaClass, String className) {
		return new ClassHelper(javaClass, className);
	}

	/**
	 * Private constructor : a new class (public, proxy state set to false, no
	 * super class, no interface and inheritance state set to none by default)
	 * 
	 * @param javaPackage
	 *            the package a the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 */
	private ClassHelper(JavaPackage javaPackage, String className) {
		org.eclipse.gmt.modisco.java.Package internalPackage = javaPackage.getDelegate();
		CompilationUnit compilationUnit = CompilationUnitBuilder.builder().setName(className + ".java")
				.setPackage(internalPackage).build();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(compilationUnit).build();
		this.buildClass = ClassDeclarationBuilder.builder().setName(className).setPackage(internalPackage)
				.setProxy(false).setModifier(modifier).setCompilationUnit(compilationUnit).build();
		compilationUnit.getTypes().add(this.buildClass);
		TypeParameterHelper.addTypeParametersToTypeDeclaration(this.buildClass);
		Model model = internalPackage.getModel();
		while (model == null && internalPackage != internalPackage.getPackage()) {
			internalPackage = internalPackage.getPackage();
			model = internalPackage.getModel();
		}
		model.getCompilationUnits().add(compilationUnit);
	}

	/**
	 * Private constructor : a new internal class (public, proxy state set to
	 * false, no super class, no interface and inheritance state set to none by
	 * default)
	 * 
	 * @param javaClass
	 *            the class containing the class under construction.
	 * @param className
	 *            the name of the class under construction.
	 */
	private ClassHelper(JavaClass javaClass, String className) {
		ClassDeclaration classDeclaration = javaClass.getDelegate();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(classDeclaration.getOriginalCompilationUnit())
				.build();
		this.buildClass = ClassDeclarationBuilder.builder().setName(className)
				.setPackage(classDeclaration.getPackage()).setProxy(false).setModifier(modifier)
				.setCompilationUnit(classDeclaration.getOriginalCompilationUnit())
				.setAbstractTypeDeclaration(classDeclaration).build();
		TypeParameterHelper.addTypeParametersToTypeDeclaration(this.buildClass);
	}

	/**
	 * Give the build class
	 * 
	 * @return the build class.
	 */
	public JavaClass build() {
		return new JavaClass(this.buildClass);
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
	 * Set the super class of the class under construction
	 * 
	 * @param superClassName
	 *            the super class name of the class under construction.
	 * @return the builder.
	 */
	public ClassHelper setSuperClass(String superClassName) {
		this.buildClass.setSuperClass(TypeAccessHelper.createClassTypeAccess(superClassName));
		return this;
	}

	/**
	 * Add interfaces list to the class under construction
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the class under
	 *            construction.
	 * @return the builder.
	 */
	public ClassHelper addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.buildClass.getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

	/**
	 * Add imports list to the class under construction
	 * 
	 * @param importsNames
	 *            the imports names list to add to the class under construction.
	 * @return the builder.
	 */
	public ClassHelper addImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildClass.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, false));
		}
		return this;
	}

	/**
	 * Add static imports list to the class under construction
	 * 
	 * @param importsNames
	 *            the static imports names list to add to the class under
	 *            construction.
	 * @return the builder.
	 */
	public ClassHelper addStaticImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildClass.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, true));
		}
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
	 * @param superClassName
	 *            the super class name of the created class.
	 * @param interfacesNames
	 *            the interfaces names list to add to the created class.
	 * @return the created class accordingly to the specified parameters.
	 */
	public static JavaClass createClass(JavaPackage javaPackage, String className, VisibilityKind visibility,
			InheritanceKind inheritance, boolean proxyState, String superClassName, String... interfacesNames) {
		return ClassHelper.builder(javaPackage, className).setVisibility(visibility).setInheritance(inheritance)
				.setProxy(proxyState).setSuperClass(superClassName).addInterfaces(interfacesNames).build();
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
	 * @param superClassName
	 *            the super class name of the created class.
	 * @param interfacesNames
	 *            the interfaces names list to add to the created class.
	 * @return the created internal class accordingly to the specified
	 *         parameters.
	 */
	public static JavaClass createInternalClass(JavaClass javaClass, String className, VisibilityKind visibility,
			InheritanceKind inheritance, boolean proxyState, String superClassName, String... interfacesNames) {
		return ClassHelper.internalClassBuilder(javaClass, className).setVisibility(visibility)
				.setInheritance(inheritance).setProxy(proxyState).setSuperClass(superClassName)
				.addInterfaces(interfacesNames).build();
	}

}
