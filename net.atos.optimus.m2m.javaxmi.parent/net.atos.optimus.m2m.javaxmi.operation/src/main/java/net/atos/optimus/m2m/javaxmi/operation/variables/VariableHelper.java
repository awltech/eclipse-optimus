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

import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessHelper;

import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

/**
 * The purpose of such class is to help with the creation of variables
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableHelper {

	/** The build variable */
	private SingleVariableDeclaration buildVariable;

	/**
	 * Launch the build of a new variable (with generated name)
	 * 
	 * @param variableTypeName
	 *            the type name of the variable under construction.
	 * @return a new helper.
	 */
	public static VariableHelper builder(String variableTypeName) {
		return new VariableHelper(variableTypeName);
	}

	/**
	 * Private constructor : a new variable (with generated name)
	 * 
	 * @param variableTypeName
	 *            the type name of the variable under construction.
	 */
	private VariableHelper(String variableTypeName) {
		this.buildVariable = SingleVariableDeclarationBuilder.builder()
				.setType(TypeAccessHelper.createVariableTypeAccess(variableTypeName))
				.setName(VariableHelper.generateVariableName(variableTypeName)).build();
	}

	/**
	 * Give the build variable
	 * 
	 * @return the build variable.
	 */
	public Variable build() {
		return new Variable(this.buildVariable);
	}

	/**
	 * Set the name of the variable under construction
	 * 
	 * @param variableName
	 *            the name of the variable under construction.
	 * @return the helper.
	 */
	public VariableHelper setName(String variableName) {
		this.buildVariable.setName(variableName);
		return this;
	}

	/**
	 * Create a single variable declaration
	 * 
	 * @param variableTypeName
	 *            the type name of the created variable.
	 * @param variableName
	 *            the name of the created variable.
	 * @return the created variable accordingly to the
	 *         specified parameters.
	 */
	public static Variable createVariable(String variableTypeName, String variableName) {
		return VariableHelper.builder(variableTypeName).setName(variableName).build();
	}

	/**
	 * Generate the variable name with the variable type name
	 * 
	 * @param variableTypeName
	 *            the variable type name
	 * @return the variable name associated to the variable type name.
	 */
	public static String generateVariableName(String variableTypeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(variableTypeName.trim())) {
			s.append(variableTypeName.trim().substring(0, 1).toLowerCase());
			if (variableTypeName.length() > 1) {
				s.append(variableTypeName.substring(1));
			}
		}
		return s.toString();
	}

}
