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

import net.atos.optimus.m2m.javaxmi.operation.classes.UnresolvedClassDeclarationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.interfaces.UnresolvedInterfaceDeclarationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.PrimitiveTypeBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.UnresolvedTypeBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.WildCardTypeBuilder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * The purpose of such class is to help with the creation of type access
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TypeAccessHelper {

	public static final String PARAMETRIZED_ENTRY = "<";

	public static final String PARAMETRIZED_SEPARATOR = ",";

	public static final String WILD_CARD = "?";

	public static final String EXTENSION = "extends";

	public static final String GENERALIZATION = "super";

	/**
	 * Create a type access associated to a variable type
	 * 
	 * @param variableTypeName
	 *            the variable type name which we want a type access.
	 * @return the created type access associated to the specified variable type
	 *         name.
	 */
	public static TypeAccess createVariableTypeAccess(String variableTypeName) {
		if (variableTypeName.contains(TypeAccessHelper.PARAMETRIZED_ENTRY)) {
			return TypeAccessHelper.createParameterizedType(
					TypeAccessHelper.createVariableTypeAccess(variableTypeName.substring(0,
							variableTypeName.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY))), variableTypeName);
		}
		return TypeAccessBuilder.builder().setType(PrimitiveTypeBuilder.builder().setName(variableTypeName).build())
				.build();
	}

	/**
	 * Create a type access associated to a class
	 * 
	 * @param className
	 *            the class name which we want a type access.
	 * @return the created type access associated to the specified class name.
	 */
	public static TypeAccess createClassTypeAccess(String className) {
		if (className.contains(TypeAccessHelper.PARAMETRIZED_ENTRY)) {
			return TypeAccessHelper.createParameterizedType(
					TypeAccessHelper.createClassTypeAccess(className.substring(0,
							className.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY))), className);
		}
		return TypeAccessBuilder.builder()
				.setType(UnresolvedClassDeclarationBuilder.builder().setName(className).build()).build();
	}

	/**
	 * Create a type access associated to an interface
	 * 
	 * @param interfaceName
	 *            the interface name which we want a type access.
	 * @return the created type access associated to the specified interface
	 *         name.
	 */
	public static TypeAccess createInterfaceTypeAccess(String interfaceName) {
		if (interfaceName.contains(TypeAccessHelper.PARAMETRIZED_ENTRY)) {
			return TypeAccessHelper.createParameterizedType(
					TypeAccessHelper.createInterfaceTypeAccess(interfaceName.substring(0,
							interfaceName.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY))), interfaceName);
		}
		return TypeAccessBuilder.builder()
				.setType(UnresolvedInterfaceDeclarationBuilder.builder().setName(interfaceName).build()).build();
	}

	/**
	 * Create a type access associated to an exception
	 * 
	 * @param exceptionName
	 *            the exception name which we want a type access.
	 * @return the created type access associated to the specified exception
	 *         name.
	 */
	public static TypeAccess createExceptionTypeAccess(String exceptionName) {
		if (exceptionName.contains(TypeAccessHelper.PARAMETRIZED_ENTRY)) {
			return TypeAccessHelper.createParameterizedType(
					TypeAccessHelper.createExceptionTypeAccess(exceptionName.substring(0,
							exceptionName.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY))), exceptionName);
		}
		return TypeAccessBuilder.builder().setType(PrimitiveTypeBuilder.builder().setName(exceptionName).build())
				.build();
	}

	/**
	 * Create a type associated to a class or an interface
	 * 
	 * @param typeName
	 *            the type name which we want a type access.
	 * @return the created type access associated to the specified type name.
	 */
	public static TypeAccess createTypeAccess(String typeName) {
		if (typeName.contains(TypeAccessHelper.PARAMETRIZED_ENTRY)) {
			return TypeAccessHelper.createParameterizedType(
					TypeAccessHelper.createTypeAccess(typeName.substring(0,
							typeName.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY))), typeName);
		}
		return TypeAccessBuilder.builder().setType(UnresolvedTypeBuilder.builder().setName(typeName).build()).build();
	}

	/**
	 * Create a parameterized type
	 * 
	 * @param mainType
	 *            the main type of the parameterized type.
	 * @param name
	 *            the name of the parameterized type.
	 * @return the created type access associated to these parameters.
	 */
	protected static TypeAccess createParameterizedType(TypeAccess mainType, String name) {
		String[] arguments = name.substring(name.indexOf(TypeAccessHelper.PARAMETRIZED_ENTRY) + 1, name.length() - 1)
				.split(TypeAccessHelper.PARAMETRIZED_SEPARATOR);
		ParameterizedType parameterizedType = ParameterizedTypeBuilder.builder().setType(mainType).build();
		EList<TypeAccess> argumentsList = parameterizedType.getTypeArguments();
		for (String argument : arguments) {
			argumentsList.add(TypeAccessHelper.createTypeParametrizedArgument(argument));
		}
		return TypeAccessBuilder.builder().setType(parameterizedType).build();
	}

	/**
	 * Create type for an argument of a parameterized type
	 * 
	 * @param argument
	 *            the argument of the parameterized type in one string.
	 * @return the created type for the specified argument of a parameterized
	 *         type
	 */
	protected static TypeAccess createTypeParametrizedArgument(String argument) {
		String[] argumentParts = argument.trim().split("\\s+");
		if (argumentParts.length >= 3) {
			if (TypeAccessHelper.WILD_CARD.equals(argumentParts[0])) {
				return TypeAccessBuilder
						.builder()
						.setType(
								WildCardTypeBuilder.builder()
										.setUpperBound(TypeAccessHelper.EXTENSION.equals(argumentParts[1]))
										.setBound(TypeAccessHelper.createClassTypeAccess(argumentParts[2])).build())
						.build();
			}
			return TypeAccessHelper.createClassTypeAccess(argumentParts[0]);
		}
		if (TypeAccessHelper.WILD_CARD.equals(argumentParts[0])) {
			TypeAccessBuilder.builder().setType(WildCardTypeBuilder.builder().build()).build();
		}
		return TypeAccessHelper.createClassTypeAccess(argumentParts[0]);
	}
}
