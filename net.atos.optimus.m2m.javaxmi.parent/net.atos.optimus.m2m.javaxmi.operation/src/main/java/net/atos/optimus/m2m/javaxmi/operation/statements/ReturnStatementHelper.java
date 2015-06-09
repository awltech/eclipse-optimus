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
package net.atos.optimus.m2m.javaxmi.operation.statements;

import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.ReturnStatement;

/**
 * The purpose of such class is to help with the creation of return statement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ReturnStatementHelper {

	/**
	 * Create a return statement based on an expression statement
	 * 
	 * @param statement
	 *            the expression statement convert to a return statement.
	 * @return the created return statement based on the specified expression
	 *         statement.
	 */
	public static ReturnStatement createReturnStatement(ExpressionStatement statement) {
		return ReturnStatementBuilder.builder().setExpression(statement.getExpression()).build();
	}

	/**
	 * Create a null return statement
	 * 
	 * @return the created null return statement.
	 */
	public static ReturnStatement createReturnStatement() {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createStatement());
	}

	/**
	 * Create a boolean return statement
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(boolean value) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createStatement(value));
	}

	/**
	 * Create an integer return statement
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(int value) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createStatement(value));
	}

	/**
	 * Create a char return statement
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(char value) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createStatement(value));
	}

	/**
	 * Create a string return statement
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(String value) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createStatement(value));
	}

	/**
	 * Create a field return statement
	 * 
	 * @param fieldName
	 *            the name of the return field.
	 * @return the created field return statement returning the field with the
	 *         specified name.
	 */
	public static ReturnStatement createFieldReturnStatement(String fieldName) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createFieldStatement(fieldName));
	}

	/**
	 * Create a variable return statement
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable return statement.
	 */
	public static ReturnStatement createVariableReturnStatement(String variableName) {
		return ReturnStatementHelper.createReturnStatement(BasicStatementHelper.createVariableStatement(variableName));
	}

	/**
	 * Create a class instantiation return statement with no argument
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @return the created class instantiation return statement returning the
	 *         instantiation of the class with the specified name.
	 */
	public static ReturnStatement createClassInstantiationReturnStatement(String className) {
		return ReturnStatementHelper.createReturnStatement(InvocationStatementHelper
				.createClassInstantiationStatement(className));
	}

	/**
	 * Create a class instantiation return statement with arguments
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @param arguments
	 *            the arguments list of the class instantiation return
	 *            statement.
	 * @return the created class instantiation return statement returning the
	 *         instantiation of the class with the specified name and arguments.
	 */
	public static ReturnStatement createClassInstantiationReturnStatement(String className, String... arguments) {
		return ReturnStatementHelper.createReturnStatement(InvocationStatementHelper.createClassInstantiationStatement(
				className, arguments));
	}

}
