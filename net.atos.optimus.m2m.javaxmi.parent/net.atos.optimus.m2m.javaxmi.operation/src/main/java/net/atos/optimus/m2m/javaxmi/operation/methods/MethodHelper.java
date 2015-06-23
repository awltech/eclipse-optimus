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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeParameterHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MethodHelper {

	public static final String VOID_TYPE = "void";

	/** The build method */
	private MethodDeclaration buildMethod;

	/**
	 * Launch the build of a new method (void, public, not static, inheritance
	 * set to none with no parameter, no exceptions and no body by default)
	 * 
	 * @param abstractClass
	 *            the class or the interface where is the method under
	 *            construction.
	 * @param methodName
	 *            the name of the created method under construction.
	 * 
	 * @return a new method helper.
	 */
	public static MethodHelper builder(AbstractClass<?> abstractClass, String methodName) {
		return new MethodHelper(abstractClass, methodName);
	}

	/**
	 * Private constructor : a new method (void, public, not static, inheritance
	 * set to none with no parameter, no exceptions and no body by default)
	 * 
	 * @param abstractClass
	 *            the class or the interface where is the method under
	 *            construction.
	 * @param methodName
	 *            the name of the created method under construction.
	 */
	private MethodHelper(AbstractClass<?> abstractClass, String methodName) {
		AbstractTypeDeclaration abstractType = abstractClass.getDelegate();
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PUBLIC).setStatic(false)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(abstractType.getOriginalCompilationUnit())
				.build();
		this.buildMethod = MethodDeclarationBuilder.builder().setModifier(modifier)
				.setReturnType(TypeAccessHelper.createTypeAccess(MethodHelper.VOID_TYPE)).setName(methodName)
				.setAbstractTypeDeclaration(abstractType).setCompilationUnit(abstractType.getOriginalCompilationUnit())
				.build();
	}

	/**
	 * Build a method
	 * 
	 * @return the build method.
	 */
	public Method build() {
		return new Method(this.buildMethod);
	}

	/**
	 * Set the visibility of the method under construction
	 * 
	 * @param visibility
	 *            the visibility of the method under construction.
	 * @return the helper.
	 */
	public MethodHelper setVisibility(VisibilityKind visibility) {
		this.buildMethod.getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the static state of the method under construction
	 * 
	 * @param isStatic
	 *            the static state of the method under construction.
	 * @return the helper.
	 */
	public MethodHelper setStatic(boolean isStatic) {
		this.buildMethod.getModifier().setStatic(isStatic);
		return this;
	}

	/**
	 * Set the inheritance state of the method under construction
	 * 
	 * @param inheritance
	 *            the inheritance state of the method under construction.
	 * @return the helper.
	 */
	public MethodHelper setInheritance(InheritanceKind inheritance) {
		this.buildMethod.getModifier().setInheritance(inheritance);
		return this;
	}

	/**
	 * Set the return type of the method under construction
	 * 
	 * @param returnTypeName
	 *            the return type name of the method under construction.
	 * @return the helper.
	 */
	public MethodHelper setReturnType(String returnTypeName) {
		TypeParameterHelper.addReturnTypeToMethodDeclaration(this.buildMethod, returnTypeName);
		return this;
	}

	/**
	 * Add a parameter to the method under construction
	 * 
	 * @param parameterTypeName
	 *            the type name of the parameter to add to the method under
	 *            construction.
	 * @param parameterName
	 *            the name of the parameter to add to the method under
	 *            construction.
	 * @return the helper.
	 */
	public MethodHelper addParameter(String parameterTypeName, String parameterName) {
		ParameterHelper.builder(new Method(this.buildMethod), parameterTypeName).setName(parameterName).build();
		return this;
	}

	/**
	 * Add a parameters list (parameters names are generated) to the method
	 * under construction
	 * 
	 * @param parameterTypeNames
	 *            the type names list of the parameter to add to the method
	 *            under construction.
	 * @return the helper.
	 */
	public MethodHelper addParameters(String... parameterTypeNames) {
		for (String parameterTypeName : parameterTypeNames) {
			ParameterHelper.builder(new Method(this.buildMethod), parameterTypeName).build();
		}
		return this;
	}

	/**
	 * Add an exceptions list to the method under construction
	 * 
	 * @param exceptionsNames
	 *            the exceptions list to add to the method under construction.
	 * @return the helper.
	 */
	public MethodHelper addExceptions(String... exceptionsNames) {
		EList<TypeAccess> exceptionsList = this.buildMethod.getThrownExceptions();
		for (String exceptionName : exceptionsNames) {
			exceptionsList.add(TypeAccessHelper.createExceptionTypeAccess(exceptionName));
		}
		return this;
	}

	/**
	 * Add an instructions list to the method under construction
	 * 
	 * @param instructions
	 *            the instructions list to add to the method under construction.
	 * @return the helper.
	 */
	public MethodHelper addInstructions(IComplexInstruction... instructions) {
		Block block = this.buildMethod.getBody();
		if (block == null) {
			block = BlockBuilder.builder().build();
			this.buildMethod.setBody(block);
		}
		for (IComplexInstruction instruction : instructions) {
			block.getStatements().add(instruction.getStatement());
		}
		return this;
	}

	/**
	 * Create a method with exceptions but no parameter and no body
	 * 
	 * @param abstractClass
	 *            the class or the interface where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param isStatic
	 *            the static state of the created method.
	 * @param inheritance
	 *            the inheritance state of the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param exceptionsNames
	 *            the exceptions list to add to the method under construction.
	 * @return the created method with instructions and no parameter accordingly
	 *         to the specified parameters.
	 */
	public static Method createMethod(AbstractClass<?> abstractClass, VisibilityKind visibility, boolean isStatic,
			InheritanceKind inheritance, String returnTypeName, String methodName, String... exceptionsNames) {
		return MethodHelper.builder(abstractClass, methodName).setVisibility(visibility).setStatic(isStatic)
				.setInheritance(inheritance).setReturnType(returnTypeName).addExceptions(exceptionsNames).build();
	}

}
