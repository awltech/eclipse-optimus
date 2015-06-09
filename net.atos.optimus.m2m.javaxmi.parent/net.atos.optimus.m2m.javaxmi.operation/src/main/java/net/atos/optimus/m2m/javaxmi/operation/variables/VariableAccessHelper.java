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
package net.atos.optimus.m2m.javaxmi.operation.variables;

import net.atos.optimus.m2m.javaxmi.operation.accesses.SingleVariableAccessBuilder;

import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.VariableDeclaration;

/**
 * The purpose of such class is to help with the manipulation of variable
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableAccessHelper {

	/**
	 * Create an access to a variable
	 * 
	 * @param variableName
	 *            the name of the variable which we want an access.
	 * @return an access to the variable with the specified name.
	 */
	public static SingleVariableAccess createVariableAccess(String variableName) {
		VariableDeclaration variableDeclaration = SingleVariableDeclarationBuilder.builder().setName(variableName)
				.build();
		return SingleVariableAccessBuilder.builder().setVariable(variableDeclaration).build();
	}

}