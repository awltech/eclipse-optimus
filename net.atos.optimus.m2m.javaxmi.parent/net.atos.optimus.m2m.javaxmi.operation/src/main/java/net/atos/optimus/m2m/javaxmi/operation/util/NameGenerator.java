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
package net.atos.optimus.m2m.javaxmi.operation.util;

import net.atos.optimus.m2m.javaxmi.operation.methods.GetterHelper;
import net.atos.optimus.m2m.javaxmi.operation.methods.SetterHelper;

/**
 * Util class dedicated to name generator
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class NameGenerator {
	
	public static final String PARAMETRIZED_ENTRY = "<";

	public static final String PACKAGE_SEPARATOR = ".";
	
	/**
	 * Generate the getter name associated to a field name
	 * 
	 * @param fieldName
	 *            the field name associated to the getter.
	 * @return the getter name associated to the field name.
	 */
	public static String generateGetterName(String fieldName) {
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

	/**
	 * Generate a name with a type name
	 * 
	 * @param typeName
	 *            the type name.
	 * @return the name associated to the type name.
	 */
	public static String generateNameWithTypeName(String typeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(typeName.trim())) {
			int index;
			if((index = typeName.indexOf(NameGenerator.PARAMETRIZED_ENTRY)) != -1){
				typeName = typeName.substring(0,index);
			}
			if((index = typeName.lastIndexOf(NameGenerator.PACKAGE_SEPARATOR)) != -1){
				typeName = typeName.substring(index+1,typeName.length());
			}
			s.append(typeName.trim().substring(0, 1).toLowerCase());
			if (typeName.length() > 1) {
				s.append(typeName.substring(1));
			}
		}
		return s.toString();
	}

}
