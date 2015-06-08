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

import java.util.Collection;
import java.util.List;

import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.statements.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.PrimitiveTypeBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessBuilder;

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
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

	/**
	 * Create a public void method with no parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @return the created public void method with no parameter accordingly to
	 *         the specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, MethodHelper.VOID_TYPE, methodName);
	}

	/**
	 * Create a public void method with one parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameter
	 *            the parameter of the created method.
	 * @return the created public void method with one parameter accordingly to
	 *         the specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName,
			SingleVariableDeclaration parameter) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, MethodHelper.VOID_TYPE, methodName,
				parameter);
	}

	/**
	 * Create a public void method with parameters
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameters
	 *            the collection of parameters of the created method.
	 * @return the created public void method with parameters accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName,
			Collection<SingleVariableDeclaration> parameters) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, MethodHelper.VOID_TYPE, methodName,
				parameters);
	}

	/**
	 * Create a public method with no parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @return the created public method with no parameter accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String returnTypeName, String methodName) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, returnTypeName, methodName);
	}

	/**
	 * Create a public method with one parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameter
	 *            the parameter of the created method.
	 * @return the created public method with one parameter accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String returnTypeName, String methodName,
			SingleVariableDeclaration parameter) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, returnTypeName, methodName, parameter);
	}

	/**
	 * Create a public method with parameters
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameters
	 *            the collection of parameters of the created method.
	 * @return the created public method with parameters accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String returnTypeName, String methodName,
			Collection<SingleVariableDeclaration> parameters) {
		return MethodHelper.createMethod(javaClass, VisibilityKind.PUBLIC, returnTypeName, methodName, parameters);
	}

	/**
	 * Create a void method with no parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @return the created void method with no parameter accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String methodName) {
		return MethodHelper.createMethod(javaClass, visibility, MethodHelper.VOID_TYPE, methodName);
	}

	/**
	 * Create a void method with one parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameter
	 *            the parameter of the created method.
	 * @return the created void method with one parameter accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String methodName, SingleVariableDeclaration parameter) {
		return MethodHelper.createMethod(javaClass, visibility, MethodHelper.VOID_TYPE, methodName, parameter);
	}

	/**
	 * Create a void method with parameters
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameters
	 *            the collection of parameters of the created method.
	 * @return the created void method with parameters accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String methodName, Collection<SingleVariableDeclaration> parameters) {
		return MethodHelper.createMethod(javaClass, visibility, MethodHelper.VOID_TYPE, methodName, parameters);
	}

	/**
	 * Create a method with no parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @return the created method with no parameter accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String returnTypeName, String methodName) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(javaClass.getOriginalCompilationUnit()).build();
		PrimitiveType primitiveType = PrimitiveTypeBuilder.builder().setName(returnTypeName).build();
		TypeAccess typeAccess = TypeAccessBuilder.builder().setType(primitiveType).build();
		return MethodDeclarationBuilder.builder().setModifier(modifier).setReturnType(typeAccess).setName(methodName)
				.setAbstractTypeDeclaration(javaClass).setCompilationUnit(javaClass.getOriginalCompilationUnit())
				.build();
	}

	/**
	 * Create a method with one parameter
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameter
	 *            the parameter of the created method.
	 * @return the created method with one parameter accordingly to the
	 *         specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String returnTypeName, String methodName, SingleVariableDeclaration parameter) {
		MethodDeclaration methodDeclaration = MethodHelper.createMethod(javaClass, visibility, returnTypeName,
				methodName);
		methodDeclaration.getParameters().add(parameter);
		return methodDeclaration;
	}

	/**
	 * Create a method with parameters
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @param returnTypeName
	 *            the return type name of the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param parameters
	 *            the collection of parameters of the created method.
	 * @return the created method with parameters accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, VisibilityKind visibility,
			String returnTypeName, String methodName, Collection<SingleVariableDeclaration> parameters) {
		MethodDeclaration methodDeclaration = MethodHelper.createMethod(javaClass, visibility, returnTypeName,
				methodName);
		methodDeclaration.getParameters().addAll(parameters);
		return methodDeclaration;
	}

	/**
	 * Add a return statement to a method
	 * 
	 * @param methodDeclaration
	 *            the method which we add the return statement.
	 * @param returnStatement
	 *            the return statement of the method.
	 * @return the method with the specified body.
	 */
	public static MethodDeclaration addMethodBody(MethodDeclaration methodDeclaration, ReturnStatement returnStatement) {
		Block block = BlockBuilder.builder().addStatement(returnStatement).build();
		methodDeclaration.setBody(block);
		return methodDeclaration;
	}

	/**
	 * Add an expression statement to a method
	 * 
	 * @param methodDeclaration
	 *            the method which we add the expression statement.
	 * @param expressionStatement
	 *            the expression statement of the method.
	 * @return the method with the specified body.
	 */
	public static MethodDeclaration addMethodBody(MethodDeclaration methodDeclaration,
			ExpressionStatement expressionStatement) {
		Block block = BlockBuilder.builder().addStatement(expressionStatement).build();
		methodDeclaration.setBody(block);
		return methodDeclaration;
	}

	/**
	 * Add an expression statement and then a return statement to a method
	 * 
	 * @param methodDeclaration
	 *            the method which we add the expression and then the return
	 *            statement.
	 * @param expressionStatement
	 *            the expression statement of the method.
	 * @param returnStatement
	 *            the return statement of the method.
	 * @return the method with the specified body.
	 */
	public static MethodDeclaration addMethodBody(MethodDeclaration methodDeclaration,
			ExpressionStatement expressionStatement, ReturnStatement returnStatement) {
		Block block = BlockBuilder.builder().addStatement(expressionStatement).addStatement(returnStatement).build();
		methodDeclaration.setBody(block);
		return methodDeclaration;
	}

	/**
	 * Add a body to a method
	 * 
	 * @param methodDeclaration
	 *            the method which we add the body.
	 * @param statements
	 *            the statement list of the body.
	 * @return the method with the specified body.
	 */
	public static MethodDeclaration addMethodBody(MethodDeclaration methodDeclaration, List<Statement> statements) {
		Block block = BlockBuilder.builder().addStatements(statements).build();
		methodDeclaration.setBody(block);
		return methodDeclaration;
	}

}
