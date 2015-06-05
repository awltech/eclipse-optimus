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

import net.atos.optimus.m2m.javaxmi.operation.statements.ReturnStatementHelper;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of getter methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class GetterHelper {

	public static final String GET_PREFIX = "get";

	/**
	 * Create a public getter method with a generated name
	 * 
	 * @param javaClass
	 *            the class where is the created getter method.
	 * @param fieldName
	 *            the name of the field associated with the created getter
	 *            method.
	 * @return the created public getter method with a generated name
	 *         accordingly to the specified parameters.
	 */
	public static MethodDeclaration createGetter(ClassDeclaration javaClass, String fieldName) {
		return GetterHelper.createGetter(javaClass, VisibilityKind.PUBLIC, GetterHelper.createGetterName(fieldName),
				fieldName);
	}

	/**
	 * Create a public getter method
	 * 
	 * @param javaClass
	 *            the class where is the created getter method.
	 * @param getterName
	 *            the name of the created getter method.
	 * @param fieldName
	 *            the name of the field associated with the created getter
	 *            method.
	 * @return the created public getter method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createGetter(ClassDeclaration javaClass, String getterName, String fieldName) {
		return GetterHelper.createGetter(javaClass, VisibilityKind.PUBLIC, getterName, fieldName);
	}

	/**
	 * Create a getter method with a generated name
	 * 
	 * @param javaClass
	 *            the class where is the created getter method.
	 * @param visibility
	 *            the visibility of the created getter method.
	 * @param fieldName
	 *            the name of the field associated with the created getter
	 *            method.
	 * @return the created getter method with a generated name accordingly to
	 *         the specified parameters.
	 */
	public static MethodDeclaration createGetter(ClassDeclaration javaClass, VisibilityKind visibility, String fieldName) {
		return GetterHelper.createGetter(javaClass, visibility, GetterHelper.createGetterName(fieldName), fieldName);
	}

	/**
	 * Create a getter method
	 * 
	 * @param javaClass
	 *            the class where is the created getter method.
	 * @param visibility
	 *            the visibility of the created getter method.
	 * @param getterName
	 *            the name of the created getter method.
	 * @param fieldName
	 *            the name of the field associated with the created getter
	 *            method.
	 * @return the created getter method accordingly to the specified
	 *         parameters.
	 */
	public static MethodDeclaration createGetter(ClassDeclaration javaClass, VisibilityKind visibility,
			String getterName, String fieldName) {
		MethodDeclaration getterMethod = MethodHelper.createMethod(javaClass, visibility, getterName);
		MethodHelper.addMethodBody(getterMethod, ReturnStatementHelper.createFieldReturnStatement(fieldName));
		return getterMethod;
	}

	/**
	 * Create the getter name associated to a field name
	 * 
	 * @param fieldName
	 *            the field name associated to the getter name.
	 * @return the getter name associated to the field name.
	 */
	protected static String createGetterName(String fieldName) {
		StringBuilder s = new StringBuilder();
		s.append(GetterHelper.GET_PREFIX);
		if (!"".equals(fieldName.trim())) {
			s.append(fieldName.trim().substring(0, 1).toUpperCase());
			if (fieldName.length() > 1) {
				s.append(fieldName.substring(1));
			}
		}
		return s.toString();
	}

}
