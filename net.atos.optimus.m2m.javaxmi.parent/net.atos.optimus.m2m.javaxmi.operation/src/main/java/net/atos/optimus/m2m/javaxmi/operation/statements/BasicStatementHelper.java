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

import net.atos.optimus.m2m.javaxmi.operation.accesses.FieldAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableAccessHelper;

import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of basic statement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class BasicStatementHelper {

	/**
	 * Create a null constant statement
	 * 
	 * @return a null constant statement.
	 */
	public static ExpressionStatement createStatement() {
		NullLiteral literal = JavaFactory.eINSTANCE.createNullLiteral();
		return ExpressionStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a boolean constant statement
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean constant statement with the specified value.
	 */
	public static ExpressionStatement createStatement(boolean value) {
		BooleanLiteral literal = JavaFactory.eINSTANCE.createBooleanLiteral();
		literal.setValue(value);
		return ExpressionStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create an integer constant statement
	 * 
	 * @param value
	 *            the integer value.
	 * @return the created integer constant statement with the specified value.
	 */
	public static ExpressionStatement createStatement(int value) {
		NumberLiteral literal = JavaFactory.eINSTANCE.createNumberLiteral();
		literal.setTokenValue(((Integer) value).toString());
		return ExpressionStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a char constant statement
	 * 
	 * @param value
	 *            the char value.
	 * @return the created char constant statement with the specified value.
	 */
	public static ExpressionStatement createStatement(char value) {
		CharacterLiteral literal = JavaFactory.eINSTANCE.createCharacterLiteral();
		literal.setEscapedValue(((Character) value).toString());
		return ExpressionStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a string constant statement
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string constant statement with the specified value.
	 */
	public static ExpressionStatement createStatement(String value) {
		StringLiteral literal = JavaFactory.eINSTANCE.createStringLiteral();
		literal.setEscapedValue(value);
		return ExpressionStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a field statement
	 * 
	 * @param fieldName
	 *            the name of the field.
	 * @return the created field statement.
	 */
	public static ExpressionStatement createFieldStatement(String fieldName) {
		return ExpressionStatementBuilder.builder().setExpression(FieldAccessHelper.createFieldAccess(fieldName))
				.build();
	}

	/**
	 * Create a variable statement
	 * 
	 * @param variableName
	 *            the name of the variable.
	 * @return the created variable statement.
	 */
	public static ExpressionStatement createVariableStatement(String variableName) {
		return ExpressionStatementBuilder.builder()
				.setExpression(VariableAccessHelper.createVariableAccess(variableName)).build();
	}

}
