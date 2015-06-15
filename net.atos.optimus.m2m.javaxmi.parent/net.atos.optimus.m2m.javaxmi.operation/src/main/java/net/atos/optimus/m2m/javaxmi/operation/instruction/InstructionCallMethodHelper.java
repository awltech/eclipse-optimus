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

import net.atos.optimus.m2m.javaxmi.operation.instruction.part.InstructionPartHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.MethodDeclarationBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;

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

}
