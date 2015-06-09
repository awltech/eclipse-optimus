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

import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.statements.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableHelper;

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the building of methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MethodBuilderHelper {

	public static final String VOID_TYPE = "void";

	/** The build method declaration */
	private MethodDeclaration buildMethodDeclaration;

	/**
	 * Give a new method builder : by default the method is void, public with no
	 * body and no parameter
	 * 
	 * @param javaClass
	 *            the class where is the method under construction.
	 * @param methodName
	 *            the name of the created method under construction.
	 * 
	 * @return a new method builder.
	 */
	public static MethodBuilderHelper builder(ClassDeclaration javaClass, String methodName) {
		return new MethodBuilderHelper(javaClass, methodName);
	}

	/**
	 * Private constructor : by default the method is void, public with no body
	 * and no parameter
	 * 
	 * @param javaClass
	 *            the class where is the method under construction.
	 * @param methodName
	 *            the name of the created method under construction.
	 */
	private MethodBuilderHelper(ClassDeclaration javaClass, String methodName) {
		this.buildMethodDeclaration = MethodBuilderHelper.createMethodBasicSignature(javaClass, VisibilityKind.PUBLIC,
				MethodBuilderHelper.VOID_TYPE, methodName);
	}

	/**
	 * Build a method
	 * 
	 * @return the build method.
	 */
	public MethodDeclaration build() {
		return this.buildMethodDeclaration;
	}

	/**
	 * Set the visibility of the method under construction
	 * 
	 * @param visibility
	 *            the visibility of the method under construction.
	 * @return the builder.
	 */
	public MethodBuilderHelper setVisibility(VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(this.buildMethodDeclaration.getOriginalCompilationUnit()).build();
		this.buildMethodDeclaration.setModifier(modifier);
		return this;
	}

	/**
	 * Set the return type of the method under construction
	 * 
	 * @param returnTypeName
	 *            the return type name of the method under construction.
	 * @return the builder.
	 */
	public MethodBuilderHelper setReturnType(String returnTypeName) {
		this.buildMethodDeclaration.setReturnType(TypeAccessHelper.createVariableTypeAccess(returnTypeName));
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
	 * @return the builder.
	 */
	public MethodBuilderHelper addParameter(String parameterTypeName, String parameterName) {
		SingleVariableDeclaration singleVariableDeclaration = VariableHelper.createVariableDeclaration(
				this.buildMethodDeclaration, parameterTypeName, parameterName);
		this.buildMethodDeclaration.getParameters().add(singleVariableDeclaration);
		return this;
	}

	/**
	 * Add a parameters list to the method under construction with generated
	 * names
	 * 
	 * @param parameterTypeNames
	 *            the type names list of the parameter to add to the method
	 *            under construction.
	 * @return the builder.
	 */
	public MethodBuilderHelper addParameters(String... parameterTypeNames) {
		for (String parameterTypeName : parameterTypeNames) {
			String parameterName = MethodBuilderHelper.createParameterName(parameterTypeName);
			SingleVariableDeclaration singleVariableDeclaration = VariableHelper.createVariableDeclaration(
					this.buildMethodDeclaration, parameterTypeName, parameterName);
			this.buildMethodDeclaration.getParameters().add(singleVariableDeclaration);
		}
		return this;
	}

	/**
	 * Add a statements list to the method under construction
	 * 
	 * @param statements
	 *            the statements list to add to the method under construction.
	 * @return the builder.
	 */
	public MethodBuilderHelper addStatements(Statement... statements) {
		Block block = this.buildMethodDeclaration.getBody();
		if (block == null) {
			block = BlockBuilder.builder().addStatements(statements).build();
			this.buildMethodDeclaration.setBody(block);
		} else {
			for (Statement statement : statements) {
				block.getStatements().add(statement);
			}
		}
		return this;
	}

	/**
	 * Create a method with no parameter and no body
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @return the created method with no parameter and no body accordingly to the specified
	 *         parameters.
	 */
	protected static MethodDeclaration createMethodBasicSignature(ClassDeclaration javaClass,
			VisibilityKind visibility, String returnTypeName, String methodName) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(javaClass.getOriginalCompilationUnit()).build();
		return MethodDeclarationBuilder.builder().setModifier(modifier)
				.setReturnType(TypeAccessHelper.createVariableTypeAccess(returnTypeName)).setName(methodName)
				.setAbstractTypeDeclaration(javaClass).setCompilationUnit(javaClass.getOriginalCompilationUnit())
				.build();
	}

	/**
	 * Add a list of statements to a method
	 * 
	 * @param methodDeclaration
	 *            the method which we add the statements list.
	 * @param statements
	 *            the added statements list of the method.
	 * @return the method with the specified body.
	 */
	public static MethodDeclaration addMethodBody(MethodDeclaration methodDeclaration, Statement... statements) {
		Block block = methodDeclaration.getBody();
		if (block == null) {
			block = BlockBuilder.builder().addStatements(statements).build();
			methodDeclaration.setBody(block);
		} else {
			for (Statement statement : statements) {
				block.getStatements().add(statement);
			}
		}
		return methodDeclaration;
	}

	/**
	 * Create the parameter name with the parameter type name
	 * 
	 * @param parameterTypeName
	 *            the parameter type name
	 * @return the parameter name associated to the parameter type name.
	 */
	protected static String createParameterName(String parameterTypeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(parameterTypeName.trim())) {
			s.append(parameterTypeName.trim().substring(0, 1).toUpperCase());
			if (parameterTypeName.length() > 1) {
				s.append(parameterTypeName.substring(1));
			}
		}
		return s.toString();
	}

}
