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

import java.util.List;

import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.statements.BlockBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.PrimitiveTypeBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.PrimitiveTypeName;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessBuilder;

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.ReturnStatement;
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

	/**
	 * Create a public void method
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param returnType
	 *            the return type the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @return the created public void method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName) {
		return MethodHelper.createMethod(javaClass, methodName, PrimitiveTypeName.VOID, VisibilityKind.PUBLIC);
	}

	/**
	 * Create a public method
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param returnType
	 *            the return type the created method.
	 * @return the created public method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName,
			PrimitiveTypeName returnType) {
		return MethodHelper.createMethod(javaClass, methodName, returnType, VisibilityKind.PUBLIC);
	}

	/**
	 * Create a void method
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @return the created void method accordingly to the specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName,
			VisibilityKind visibility) {
		return MethodHelper.createMethod(javaClass, methodName, PrimitiveTypeName.VOID, visibility);
	}

	/**
	 * Create a method
	 * 
	 * @param javaClass
	 *            the class where is the created method.
	 * @param methodName
	 *            the name of the created method.
	 * @param returnType
	 *            the return type the created method.
	 * @param visibility
	 *            the visibility the created method.
	 * @return the created method accordingly to the specified parameters.
	 */
	public static MethodDeclaration createMethod(ClassDeclaration javaClass, String methodName,
			PrimitiveTypeName returnType, VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(javaClass.getOriginalCompilationUnit()).build();
		PrimitiveType primitiveType = PrimitiveTypeBuilder.builder().setName(returnType).build();
		TypeAccess typeAccess = TypeAccessBuilder.builder().setType(primitiveType).build();
		return MethodDeclarationBuilder.builder().setModifier(modifier).setReturnType(typeAccess).setName(methodName)
				.setAbstractTypeDeclaration(javaClass).setCompilationUnit(javaClass.getOriginalCompilationUnit())
				.build();
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
