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
package net.atos.optimus.m2m.javaxmi.operation.parameters;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.AbstractMethod;
import net.atos.optimus.m2m.javaxmi.operation.variables.SingleVariableDeclarationBuilder;

import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

/**
 * The purpose of such class is to help with the creation of parameters
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ParameterHelper {

	/** The build parameter */
	private SingleVariableDeclaration buildParameter;

	/**
	 * Launch the build of a new parameter (with generated name and varargs set
	 * to false)
	 * 
	 * @param method
	 *            the method associated to the parameter under construction.
	 * @param parameterTypeName
	 *            the type name of the parameter under construction.
	 * @return a new helper.
	 */
	public static ParameterHelper builder(AbstractMethod method, String parameterTypeName) {
		return new ParameterHelper(method, parameterTypeName);
	}

	/**
	 * Private constructor : a new parameter (with generated name and varargs
	 * set to false)
	 * 
	 * @param method
	 *            the method associated to the parameter under construction.
	 * @param parameterTypeName
	 *            the type name of the parameter under construction.
	 */
	private ParameterHelper(AbstractMethod method, String parameterTypeName) {
		this.buildParameter = SingleVariableDeclarationBuilder.builder()
				.setMethodDeclaration(method.getAbstractMethodDeclaration())
				.setCompilationUnit(method.getCompilationUnit())
				.setType(TypeAccessHelper.createVariableTypeAccess(parameterTypeName))
				.setName(ParameterHelper.generateParameterName(parameterTypeName)).setVarargs(false).build();
		method.getParameters().add(this.buildParameter);
	}

	/**
	 * Give the build parameter
	 * 
	 * @return the build parameter.
	 */
	public Parameter build() {
		return new Parameter(this.buildParameter);
	}

	/**
	 * Set the name of the parameter under construction
	 * 
	 * @param parameterName
	 *            the name of the parameter under construction.
	 * @return the helper.
	 */
	public ParameterHelper setName(String parameterName) {
		this.buildParameter.setName(parameterName);
		return this;
	}

	/**
	 * Set the varargs state of the parameter under construction
	 * 
	 * @param varargsState
	 *            the varargs state of the parameter under construction.
	 * @return the helper.
	 */
	public ParameterHelper setVarargs(boolean varargsState) {
		this.buildParameter.setVarargs(varargsState);
		return this;
	}

	/**
	 * Create a parameter
	 * 
	 * @param method
	 *            the method associated to the created parameter.
	 * @param parameterTypeName
	 *            the type name of the created parameter.
	 * @param parameterName
	 *            the name of the created parameter.
	 * @param varargsState
	 *            the varargs state of the created parameter.
	 * @return the created parameter accordingly to the specified parameters.
	 */
	public static Parameter createParameter(AbstractMethod method, String parameterTypeName, String parameterName,
			boolean varargsState) {
		return ParameterHelper.builder(method, parameterTypeName).setName(parameterName).setVarargs(varargsState)
				.build();
	}

	/**
	 * Generate the parameter name with the parameter type name
	 * 
	 * @param parameterTypeName
	 *            the parameter type name.
	 * @return the parameter name associated to the parameter type name.
	 */
	public static String generateParameterName(String parameterTypeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(parameterTypeName.trim())) {
			s.append(parameterTypeName.trim().substring(0, 1).toLowerCase());
			if (parameterTypeName.length() > 1) {
				s.append(parameterTypeName.substring(1));
			}
		}
		return s.toString();
	}

}
