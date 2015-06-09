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

import net.atos.optimus.m2m.javaxmi.operation.statements.ComplexStatementHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
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

	/**
	 * Create a public setter method with a generated name and parameter
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created public setter method with a generated name and
	 *         parameter accordingly to the specified parameters.
	 */
	public static MethodDeclaration createSetter(ClassDeclaration javaClass, String fieldTypeName, String fieldName) {
		return SetterHelper.createSetter(javaClass, VisibilityKind.PUBLIC, SetterHelper.createSetterName(fieldName),
				fieldName, fieldTypeName, fieldName);
	}

	/**
	 * Create a public setter method with a generated name
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created public setter method with a generated name
	 *         accordingly to the specified parameters.
	 */
	public static MethodDeclaration createSetter(ClassDeclaration javaClass, String parameterName,
			String fieldTypeName, String fieldName) {
		return SetterHelper.createSetter(javaClass, VisibilityKind.PUBLIC, SetterHelper.createSetterName(fieldName),
				parameterName, fieldTypeName, fieldName);
	}

	/**
	 * Create a public setter method
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @param setterName
	 *            the name of the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created public setter method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createSetter(ClassDeclaration javaClass, String setterName, String parameterName,
			String fieldTypeName, String fieldName) {
		return SetterHelper.createSetter(javaClass, VisibilityKind.PUBLIC, setterName, parameterName, fieldTypeName,
				fieldName);
	}

	/**
	 * Create a setter method with a generated name
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param visibility
	 *            the visibility of the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created setter method with a generated name accordingly to
	 *         the specified parameters.
	 */
	public static MethodDeclaration createSetter(ClassDeclaration javaClass, VisibilityKind visibility,
			String parameterName, String fieldTypeName, String fieldName) {
		return SetterHelper.createSetter(javaClass, visibility, SetterHelper.createSetterName(fieldName),
				parameterName, fieldTypeName, fieldName);
	}

	/**
	 * Create a setter method
	 * 
	 * @param javaClass
	 *            the class where is the created setter method.
	 * @param visibility
	 *            the visibility of the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @param setterName
	 *            the name of the created setter method.
	 * @param fieldTypeName
	 *            the name of the field type associated with the created setter
	 *            method.
	 * @param fieldName
	 *            the name of the field associated with the created setter
	 *            method.
	 * @return the created setter method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createSetter(ClassDeclaration javaClass, VisibilityKind visibility,
			String setterName, String parameterName, String fieldTypeName, String fieldName) {
		return MethodBuilderHelper.builder(javaClass, setterName).setVisibility(visibility)
				.addParameter(fieldTypeName, parameterName)
				.addStatements(ComplexStatementHelper.createSetFieldStatement(fieldName, parameterName)).build();
	}

	/**
	 * Create the setter name associated to a field name
	 * 
	 * @param fieldName
	 *            the field name associated to the setter name.
	 * @return the setter name associated to the field name.
	 */
	protected static String createSetterName(String fieldName) {
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
