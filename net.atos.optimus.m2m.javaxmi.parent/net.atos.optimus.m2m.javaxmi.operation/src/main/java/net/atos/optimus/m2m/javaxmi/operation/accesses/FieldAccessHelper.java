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
package net.atos.optimus.m2m.javaxmi.operation.accesses;

import net.atos.optimus.m2m.javaxmi.operation.variables.VariableAccessHelper;

import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;

/**
 * The purpose of such class is to help with the creation of field accesses
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class FieldAccessHelper {

	/**
	 * Create an access to a field with proxy state set to false
	 * 
	 * @param fieldName
	 *            the field name which we want to access.
	 * @return the access to the field with the specified name.
	 */
	public static FieldAccess createFieldAccess(String fieldName) {
		return FieldAccessHelper.createFieldAccess(fieldName, false);
	}

	/**
	 * Create an access to a field
	 * 
	 * @param fieldName
	 *            the field name which we want to access.
	 * @param isProxy
	 *            the proxy state of the created field access.
	 * @return the access to the field with the specified name and specified
	 *         proxy state.
	 */
	public static FieldAccess createFieldAccess(String fieldName, boolean isProxy) {
		SingleVariableAccess singleVariableAccess = VariableAccessHelper.createVariableAccess(fieldName, isProxy);
		return FieldAccessBuilder.builder().setField(singleVariableAccess)
				.setExpression(ThisExpressionBuilder.builder().build()).build();
	}

}
