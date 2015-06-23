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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.CompilationUnitBuilder;
import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationHelper;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.packages.JavaPackage;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeParameterHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of interfaces
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InterfaceHelper {

	/** The build interface */
	private InterfaceDeclaration buildInterface;

	/**
	 * Launch the build of a new interface (public, proxy state set to false, no
	 * interface and abstract state set to false by default)
	 * 
	 * @param javaPackage
	 *            the package a the interface under construction.
	 * @param interfaceName
	 *            the name of the interface under construction.
	 * @return a new helper.
	 */
	public static InterfaceHelper builder(JavaPackage javaPackage, String interfaceName) {
		return new InterfaceHelper(javaPackage, interfaceName);
	}

	/**
	 * Private constructor : a new interface (public, proxy state set to false,
	 * no interface and abstract state set to false by default)
	 * 
	 * @param javaPackage
	 *            the package a the interface under construction.
	 * @param interfaceName
	 *            the name of the interface under construction.
	 */
	private InterfaceHelper(JavaPackage javaPackage, String interfaceName) {
		org.eclipse.gmt.modisco.java.Package internalPackage = javaPackage.getDelegate();
		CompilationUnit compilationUnit = CompilationUnitBuilder.builder().setName(interfaceName + ".java")
				.setPackage(internalPackage).build();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(compilationUnit).build();
		this.buildInterface = InterfaceDeclarationBuilder.builder().setName(interfaceName).setPackage(internalPackage)
				.setProxy(false).setModifier(modifier).setCompilationUnit(compilationUnit).build();
		TypeParameterHelper.addTypeParametersToTypeDeclaration(this.buildInterface);
		compilationUnit.getTypes().add(this.buildInterface);
		Model model = internalPackage.getModel();
		while (model == null && internalPackage != internalPackage.getPackage()) {
			internalPackage = internalPackage.getPackage();
			model = internalPackage.getModel();
		}
		model.getCompilationUnits().add(compilationUnit);
	}

	/**
	 * Give the build interface
	 * 
	 * @return the build interface.
	 */
	public Interface build() {
		return new Interface(this.buildInterface);
	}

	/**
	 * Set the visibility of the interface under construction
	 * 
	 * @param visibility
	 *            the visibility of the interface under construction.
	 * @return the helper.
	 */
	public InterfaceHelper setVisibility(VisibilityKind visibility) {
		this.buildInterface.getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the proxy state of the interface under construction
	 * 
	 * @param proxyState
	 *            the proxy state of the interface under construction.
	 * @return the helper.
	 */
	public InterfaceHelper setProxy(boolean proxyState) {
		this.buildInterface.setProxy(proxyState);
		return this;
	}

	/**
	 * Set the abstract state of the interface under construction
	 * 
	 * @param isAbstract
	 *            the abstract state of the interface under construction.
	 * @return the helper.
	 */
	public InterfaceHelper setAbstract(boolean isAbstract) {
		this.buildInterface.getModifier().setInheritance(isAbstract ? InheritanceKind.ABSTRACT : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Add interfaces list to the interface under construction
	 * 
	 * @param interfacesNames
	 *            the interfaces names list to add to the interface under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceHelper addInterfaces(String... interfacesNames) {
		EList<TypeAccess> interfacesList = this.buildInterface.getSuperInterfaces();
		for (String javaInterface : interfacesNames) {
			interfacesList.add(TypeAccessHelper.createInterfaceTypeAccess(javaInterface));
		}
		return this;
	}

	/**
	 * Add imports list to the interface under construction
	 * 
	 * @param importsNames
	 *            the imports names list to add to the interface under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceHelper addImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildInterface.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, false));
		}
		return this;
	}

	/**
	 * Add static imports list to the interface under construction
	 * 
	 * @param importsNames
	 *            the static imports names list to add to the interface under
	 *            construction.
	 * @return the builder.
	 */
	public InterfaceHelper addStaticImports(String... importsNames) {
		EList<ImportDeclaration> importsList = this.buildInterface.getOriginalCompilationUnit().getImports();
		for (String javaImport : importsNames) {
			importsList.add(ImportDeclarationHelper.createImportDeclaration(javaImport, true));
		}
		return this;
	}

	/**
	 * Create an interface in a stand alone compilation unit
	 * 
	 * @param javaPackage
	 *            the package of the created interface.
	 * @param interfaceName
	 *            the name of the created interface without .java extension.
	 * @param visibility
	 *            the visibility of the created interface.
	 * @param isAbstract
	 *            the abstract state of the created interface.
	 * @param proxyState
	 *            the proxy state of of the created interface.
	 * @param interfacesNames
	 *            the interfaces names list to add to the created interface
	 *            declaration.
	 * @return the created interface accordingly to the specified parameters.
	 */
	public static Interface createClass(JavaPackage javaPackage, String interfaceName, VisibilityKind visibility,
			boolean isAbstract, InheritanceKind inheritance, boolean proxyState, String superClassName,
			String... interfacesNames) {
		return InterfaceHelper.builder(javaPackage, interfaceName).setVisibility(visibility).setAbstract(isAbstract)
				.setProxy(proxyState).addInterfaces(interfacesNames).build();
	}

}
