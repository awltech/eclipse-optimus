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
package net.atos.optimus.m2m.javaxmi.operation.instructions;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.MethodInvocationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.SuperConstructorInvocationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.SuperMethodInvocationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary.ClassInstanceCreationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.ElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.elementary.IElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodDeclarationBuilder;

import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;

/**
 * The purpose of such class is to help with the creation of call method
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CallInstructionHelper {

	/**
	 * Private constructor
	 * 
	 */
	private CallInstructionHelper() {
	}

	/**
	 * Create a class instantiation instruction
	 *
	 * @param className
	 *            the name of the class to instantiate.
	 * @return a new class instantiation instruction.
	 */
	public static AbstractMethodInstruction createClassInstanciationInstruction(String className) {
		return new AbstractMethodInstruction(ClassInstanceCreationBuilder.builder()
				.setType(TypeAccessHelper.createClassTypeAccess(className)).build());
	}

	/**
	 * Create a method call instruction from this
	 * 
	 * @param methodName
	 *            the name of the method to call.
	 * @return the created method call instruction from this.
	 */
	public static AbstractMethodInstruction createThisMethodCallInstruction(String methodName) {
		return CallInstructionHelper.createCallMethodInstruction(ElementaryInstructionHelper.createThisInstruction(),
				methodName);
	}

	/**
	 * Create a method call instruction from field
	 * 
	 * @param fieldName
	 *            the name of the caller field.
	 * @param methodName
	 *            the name of the method to call.
	 * @return the created method call instruction from a field.
	 */
	public static AbstractMethodInstruction createFieldMethodCallInstruction(String fieldName, String methodName) {
		return CallInstructionHelper.createCallMethodInstruction(
				ElementaryInstructionHelper.createFieldInstruction(fieldName), methodName);
	}

	/**
	 * Create a method call instruction from variable
	 * 
	 * @param variableName
	 *            the name of the caller variable.
	 * @param methodName
	 *            the name of the method to call.
	 * @return the created method call instruction from a variable.
	 */
	public static AbstractMethodInstruction createVariableMethodCallInstruction(String variableName, String methodName) {
		return CallInstructionHelper.createCallMethodInstruction(
				ElementaryInstructionHelper.createVariableInstruction(variableName), methodName);
	}

	/**
	 * Create a static method call instruction from a class
	 * 
	 * @param className
	 *            the name of the caller variable.
	 * @param methodName
	 *            the name of the method to call.
	 * @return the created static call method instruction from the specified
	 *         class.
	 */
	public static AbstractMethodInstruction createStaticMethodCallInstruction(String className, String methodName) {
		return CallInstructionHelper.createCallMethodInstruction(
				new ElementaryInstruction(TypeAccessHelper.createClassTypeAccess(className)), methodName);
	}

	/**
	 * Create a method call instruction from a caller
	 * 
	 * @param caller
	 *            the caller of the method.
	 * @param methodName
	 *            the name of the method to call.
	 * @return the created method call instruction from the specified caller.
	 */
	public static AbstractMethodInstruction createCallMethodInstruction(IElementaryInstruction caller, String methodName) {
		MethodDeclaration methodDeclaration = MethodDeclarationBuilder.builder().setName(methodName).build();
		MethodInvocation methodInvocation = MethodInvocationBuilder.builder().setMethod(methodDeclaration)
				.setExpression(caller.getExpression()).build();
		return new AbstractMethodInstruction(methodInvocation);
	}

	/**
	 * Create a super method call instruction with arguments
	 * 
	 * @param superMethodName
	 *            the name of the super method to call.
	 * @return the created method call instruction with the specified name and
	 *         arguments.
	 */
	public static AbstractMethodInstruction createSuperMethodCallInstruction(String superMethodName) {
		MethodDeclaration methodDeclaration = MethodDeclarationBuilder.builder().setName(superMethodName).build();
		SuperMethodInvocation superMethodInvocation = SuperMethodInvocationBuilder.builder()
				.setMethod(methodDeclaration).build();
		return new AbstractMethodInstruction(superMethodInvocation);
	}

	/**
	 * Create a super constructor call instruction
	 * 
	 * @return the created super constructor call instruction.
	 */
	public static AbstractMethodInstruction createSuperConstructorCallInstruction() {
		return new AbstractMethodInstruction(SuperConstructorInvocationBuilder.builder().build());
	}

}
