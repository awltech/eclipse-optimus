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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import net.atos.optimus.m2m.javaxmi.operation.classes.Class;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.parameters.ParameterHelper;
import net.atos.optimus.m2m.javaxmi.operation.statements.ComplexStatementHelper;

import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of setter methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SetterHelper {

	public static final String SET_PREFIX = "set";

	/** The build setter method */
	private Method buildSetterMethod;

	/** The field name associated to the setter */
	private String fieldName;

	/**
	 * Launch the build of a new setter method (public, with generated name and
	 * generated parameter name)
	 * 
	 * @param javaClass
	 *            the class where is the setter method under construction.
	 * @param fieldTypeName
	 *            the name of the field type associated to the setter method
	 *            under construction.
	 * @param fieldName
	 *            the name of the field associated to the setter method under
	 *            construction.
	 * @return a new setter method helper.
	 */
	public static SetterHelper builder(Class javaClass, String fieldTypeName, String fieldName) {
		return new SetterHelper(javaClass, fieldTypeName, fieldName);
	}

	/**
	 * Private constructor : a new setter method (public, with generated name
	 * and generated parameter name)
	 * 
	 * @param javaClass
	 *            the class where is the setter method under construction.
	 * @param fieldTypeName
	 *            the name of the field type associated to the setter method
	 *            under construction.
	 * @param fieldName
	 *            the name of the field associated to the setter method under
	 *            construction.
	 */
	private SetterHelper(Class javaClass, String fieldTypeName, String fieldName) {
		String parameterName = ParameterHelper.generateParameterName(fieldTypeName);
		this.buildSetterMethod = MethodHelper.builder(javaClass, SetterHelper.generateSetterName(fieldName))
				.setVisibility(VisibilityKind.PUBLIC).addParameter(fieldTypeName, parameterName)
				.addStatements(ComplexStatementHelper.createSetFieldStatement(fieldName, parameterName)).build();
		this.fieldName = fieldName;
	}

	/**
	 * Build a setter method
	 * 
	 * @return the build setter method.
	 */
	public Method build() {
		return this.buildSetterMethod;
	}

	/**
	 * Set the name of the setter method under construction
	 * 
	 * @param setterName
	 *            the name of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setName(String setterName) {
		this.buildSetterMethod.getMethodDeclaration().setName(setterName);
		return this;
	}

	/**
	 * Set the visibility of the setter method under construction
	 * 
	 * @param visibility
	 *            the visibility of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setVisibility(VisibilityKind visibility) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(this.buildSetterMethod.getMethodDeclaration().getOriginalCompilationUnit()).build();
		this.buildSetterMethod.getMethodDeclaration().setModifier(modifier);
		return this;
	}

	/**
	 * Set the parameter name of the setter method under construction
	 * 
	 * @param parameterName
	 *            the parameter name of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setParameterName(String parameterName) {
		this.buildSetterMethod.getMethodDeclaration().setBody(null);
		MethodHelper.addMethodBody(this.buildSetterMethod,
				ComplexStatementHelper.createSetFieldStatement(this.fieldName, parameterName));
		return this;
	}

	/**
	 * Create a setter method
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param visibility
	 *            the visibility of the created setter method.
	 * @param setterName
	 *            the name of the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created setter method accordingly to the specified
	 *         parameters.
	 */
	public static Method createSetter(Class javaClass, VisibilityKind visibility, String setterName,
			String parameterName, String fieldTypeName, String fieldName) {
		return SetterHelper.builder(javaClass, fieldTypeName, fieldName).setVisibility(visibility).setName(setterName)
				.setParameterName(parameterName).build();
	}

	/**
	 * Generate the setter name associated to a field name
	 * 
	 * @param fieldName
	 *            the field name associated to the setter.
	 * @return the setter name associated to the field name.
	 */
	public static String generateSetterName(String fieldName) {
		StringBuilder s = new StringBuilder();
		s.append(SetterHelper.SET_PREFIX);
		if (!"".equals(fieldName.trim())) {
			s.append(fieldName.trim().substring(0, 1).toUpperCase());
			if (fieldName.length() > 1) {
				s.append(fieldName.substring(1));
			}
		}
		return s.toString();
	}

}
