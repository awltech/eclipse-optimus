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
import net.atos.optimus.m2m.javaxmi.operation.methods.Method;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;
import net.atos.optimus.m2m.javaxmi.operation.variables.SingleVariableDeclarationBuilder;

import org.eclipse.gmt.modisco.java.InheritanceKind;
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
	 * Launch the build of a new parameter (with generated name, not final and
	 * varargs set to false)
	 * 
	 * @param parameterTypeName
	 *            the type name of the parameter under construction.
	 * @return a new helper.
	 */
	public static ParameterHelper builder(String parameterTypeName) {
		return new ParameterHelper(parameterTypeName);
	}

	/**
	 * Private constructor : a new parameter (with generated name, not final and
	 * varargs set to false)
	 * 
	 * @param parameterTypeName
	 *            the type name of the parameter under construction.
	 */
	private ParameterHelper(String parameterTypeName) {
		this.buildParameter = SingleVariableDeclarationBuilder.builder()
				.setModifier(ModifierBuilder.builder().setInheritance(InheritanceKind.NONE).build())
				.setType(TypeAccessHelper.createTypeAccess(parameterTypeName))
				.setName(NameGenerator.generateNameWithTypeName(parameterTypeName)).setVarargs(false).build();
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
	 * Set the final state of the parameter under construction
	 * 
	 * @param isFinal
	 *            the final state of the parameter under construction.
	 * @return the helper.
	 */
	public ParameterHelper setFinal(boolean isFinal) {
		this.buildParameter.getModifier().setInheritance(isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
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
	 * Set the method of the parameter under construction
	 * 
	 * @param method
	 *            the method of the parameter under construction.
	 * @return the helper.
	 */
	public ParameterHelper setMethod(Method method) {
		method.addParameters(this.build());
		return this;
	}

	/**
	 * Create a parameter
	 * 
	 * @param isFinal
	 *            the final state of the created parameter.
	 * @param parameterTypeName
	 *            the type name of the created parameter.
	 * @param parameterName
	 *            the name of the created parameter.
	 * @param varargsState
	 *            the varargs state of the created parameter.
	 * @return the created parameter accordingly to the specified parameters.
	 */
	public static Parameter createParameter(boolean isFinal, String parameterTypeName, String parameterName,
			boolean varargsState) {
		return ParameterHelper.builder(parameterTypeName).setFinal(isFinal).setName(parameterName)
				.setVarargs(varargsState).build();
	}

}
