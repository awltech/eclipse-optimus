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

import net.atos.optimus.m2m.javaxmi.operation.fields.FieldAccessHelper;

import org.eclipse.gmt.modisco.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.NullLiteral;
import org.eclipse.gmt.modisco.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of return statement
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ReturnStatementHelper {

	/**
	 * Create a null return statement
	 * 
	 * @return a null return statement.
	 */
	public static ReturnStatement createReturnStatement() {
		NullLiteral literal = JavaFactory.eINSTANCE.createNullLiteral();
		return ReturnStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a boolean return statement
	 * 
	 * @param value
	 *            the boolean value.
	 * @return the created boolean return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(boolean value) {
		BooleanLiteral literal = JavaFactory.eINSTANCE.createBooleanLiteral();
		literal.setValue(value);
		return ReturnStatementBuilder.builder().setExpression(literal).build();
	}

	/**
	 * Create a string return statement
	 * 
	 * @param value
	 *            the string value.
	 * @return the created string return statement with the specified value.
	 */
	public static ReturnStatement createReturnStatement(String value) {
		StringLiteral literal = JavaFactory.eINSTANCE.createStringLiteral();
		literal.setEscapedValue(value);
		return ReturnStatementBuilder.builder().setExpression(literal).build();
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
		return ReturnStatementBuilder.builder().setExpression(FieldAccessHelper.createFieldAccess(fieldName)).build();
	}

}
