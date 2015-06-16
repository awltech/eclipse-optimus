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
package net.atos.optimus.m2m.javaxmi.operation.instruction;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPart;
import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPartHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodDeclarationBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;

/**
 * The purpose of such class is to help with the creation of call method
 * instructions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstructionCallMethodHelper {

	/**
	 * Create a call method instruction from this
	 * 
	 * @param methodName
	 *            the name of the method to call.
	 * @param arguments
	 *            the arguments list of the call method instruction.
	 * @return the created call method instruction from this.
	 */
	public static ComposableInstruction createThisCallMethodInstruction(String methodName, IComposable... arguments) {
		return InstructionCallMethodHelper.createCallMethodInstruction(
				InstructionPartHelper.createThisInstructionPart(), methodName, arguments);
	}

	/**
	 * Create a call method instruction from field
	 * 
	 * @param fieldName
	 *            the name of the caller field.
	 * @param methodName
	 *            the name of the method to call.
	 * @param arguments
	 *            the arguments list of the call method instruction.
	 * @return the created call method instruction from a field.
	 */
	public static ComposableInstruction createFieldCallMethodInstruction(String fieldName, String methodName,
			IComposable... arguments) {
		return InstructionCallMethodHelper.createCallMethodInstruction(
				InstructionPartHelper.createFieldInstructionPart(fieldName), methodName, arguments);
	}

	/**
	 * Create a call method instruction from variable
	 * 
	 * @param variableName
	 *            the name of the caller variable.
	 * @param methodName
	 *            the name of the method to call.
	 * @param arguments
	 *            the arguments list of the call method instruction.
	 * @return the created call method instruction from a variable.
	 */
	public static ComposableInstruction createVariableCallMethodInstruction(String variableName, String methodName,
			IComposable... arguments) {
		return InstructionCallMethodHelper.createCallMethodInstruction(
				InstructionPartHelper.createVariableInstructionPart(variableName), methodName, arguments);
	}

	/**
	 * Create a call static method instruction
	 * 
	 * @param className
	 *            the name of the caller variable.
	 * @param methodName
	 *            the name of the method to call.
	 * @param arguments
	 *            the arguments list of the call static method instruction.
	 * @return the created call static method instruction.
	 */
	public static ComposableInstruction createCallStaticMethodInstruction(String className, String methodName,
			IComposable... arguments) {
		return InstructionCallMethodHelper.createCallMethodInstruction(
				new InstructionPart(TypeAccessHelper.createClassTypeAccess(className)), methodName, arguments);
	}

	/**
	 * Create a call method instruction with arguments
	 * 
	 * @param caller
	 *            the caller of the method.
	 * @param methodName
	 *            the name of the method to call.
	 * @param arguments
	 *            the arguments list of the call method instruction.
	 * @return the created call method instruction with the specified name and
	 *         arguments.
	 */
	public static ComposableInstruction createCallMethodInstruction(IComposable caller, String methodName,
			IComposable... arguments) {
		MethodDeclaration methodDeclaration = MethodDeclarationBuilder.builder().setName(methodName).build();
		MethodInvocation methodInvocation = MethodInvocationBuilder.builder().setMethod(methodDeclaration)
				.setExpression(caller.getExpression()).build();
		EList<Expression> argumentsList = methodInvocation.getArguments();
		for (IComposable argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		return new ComposableInstruction(ExpressionStatementBuilder.builder().setExpression(methodInvocation).build());
	}

	/**
	 * Create a call super method instruction with arguments
	 * 
	 * @param superMethodName
	 *            the name of the super method to call.
	 * @param arguments
	 *            the arguments list of the call super method instruction.
	 * @return the created call method instruction with the specified name and
	 *         arguments.
	 */
	public static ComposableInstruction createCallSuperMethodInstruction(String superMethodName,
			IComposable... arguments) {
		MethodDeclaration methodDeclaration = MethodDeclarationBuilder.builder().setName(superMethodName).build();
		SuperMethodInvocation superMethodInvocation = SuperMethodInvocationBuilder.builder()
				.setMethod(methodDeclaration).build();
		EList<Expression> argumentsList = superMethodInvocation.getArguments();
		for (IComposable argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		return new ComposableInstruction(ExpressionStatementBuilder.builder().setExpression(superMethodInvocation)
				.build());
	}

	/**
	 * Create a call super constructor instruction with arguments
	 * 
	 * @param arguments
	 *            the arguments list of the call super method instruction.
	 * @return the created call super constructor instruction.
	 */
	public static Instruction createCallSuperConstructorInstruction(IComposable... arguments) {
		SuperConstructorInvocation superConstructorInvocation = SuperConstructorInvocationBuilder.builder().build();
		EList<Expression> argumentsList = superConstructorInvocation.getArguments();
		for (IComposable argument : arguments) {
			argumentsList.add(argument.getExpression());
		}
		return new Instruction(superConstructorInvocation);
	}

}