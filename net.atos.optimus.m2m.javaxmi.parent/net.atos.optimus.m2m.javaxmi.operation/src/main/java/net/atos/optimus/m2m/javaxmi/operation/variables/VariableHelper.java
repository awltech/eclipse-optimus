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

import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

/**
 * The purpose of such class is to help with the creation of variables
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableHelper {

	/**
	 * Create a single variable declaration
	 * 
	 * @param typeName
	 *            the type name of the created single variable declaration.
	 * @param variableName
	 *            the name of the created single variable declaration.
	 * @return the created single variable declaration accordingly to the
	 *         specified parameters.
	 */
	public static SingleVariableDeclaration createVariableDeclaration(String typeName, String variableName) {
		return SingleVariableDeclarationBuilder.builder().setType(TypeAccessHelper.createVariableTypeAccess(typeName))
				.setName(variableName).build();
	}

	/**
	 * Create a single variable declaration with varargs state set to false
	 * 
	 * @param method
	 *            the method associated to the created single variable
	 *            declaration.
	 * @param typeName
	 *            the type name of the created single variable declaration.
	 * @param variableName
	 *            the name of the created single variable declaration.
	 * @return the created single variable declaration accordingly to the
	 *         specified parameters.
	 */
	public static SingleVariableDeclaration createVariableDeclaration(AbstractMethodDeclaration method,
			String typeName, String variableName) {
		return SingleVariableDeclarationBuilder.builder().setMethodDeclaration(method)
				.setCompilationUnit(method.getOriginalCompilationUnit())
				.setType(TypeAccessHelper.createVariableTypeAccess(typeName)).setName(variableName).setVarargs(false).build();
	}

	/**
	 * Create a single variable declaration
	 * 
	 * @param method
	 *            the method associated to the created single variable
	 *            declaration.
	 * @param typeName
	 *            the type name of the created single variable declaration.
	 * @param variableName
	 *            the name of the created single variable declaration.
	 * @param varargsState
	 *            the varargs state of the created single variable declaration.
	 * @return the created single variable declaration accordingly to the
	 *         specified parameters.
	 */
	public static SingleVariableDeclaration createVariableDeclaration(AbstractMethodDeclaration method,
			String typeName, String variableName, boolean varargsState) {
		return SingleVariableDeclarationBuilder.builder().setMethodDeclaration(method)
				.setCompilationUnit(method.getOriginalCompilationUnit())
				.setType(TypeAccessHelper.createVariableTypeAccess(typeName)).setName(variableName).setVarargs(varargsState)
				.build();
	}

}
