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
package net.atos.optimus.m2m.javaxmi.operation.types;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.TypeParameter;

/**
 * The purpose of such class is to help with the creation of type parameters
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TypeParameterHelper {

	/** String constant : entry character of a parameterized type */
	public static final String PARAMETER_ENTRY = "<";

	/** String constant : exit character of a parameterized type */
	public static final String PARAMETER_EXIT = ">";

	/** String constant : separator of a parameterized type */
	public static final String PARAMETER_SEPARATOR = ",";

	/**
	 * Private constructor
	 * 
	 */
	private TypeParameterHelper() {
	}

	/**
	 * Add type parameters to a type declaration with a name including these
	 * ones and set its name properly
	 * 
	 * @param typeDeclaration
	 *            the type declaration.
	 */
	public static void addTypeParametersToTypeDeclaration(TypeDeclaration typeDeclaration) {
		String typeName = typeDeclaration.getName();
		if (typeName.contains(TypeParameterHelper.PARAMETER_ENTRY)) {
			int index = typeName.indexOf(TypeParameterHelper.PARAMETER_ENTRY);
			typeDeclaration.setName(typeName.substring(0, index).trim());
			typeDeclaration.getOriginalCompilationUnit().setName(typeDeclaration.getName() + ".java");
			String[] parametersTypes = typeName.substring(index + 1, typeName.length() - 1).split(
					TypeParameterHelper.PARAMETER_SEPARATOR);
			EList<TypeParameter> typeParametersList = typeDeclaration.getTypeParameters();
			for (String parameterTypeName : parametersTypes) {
				typeParametersList.add(TypeParameterHelper.createTypeParameter(parameterTypeName));
			}
		}
	}

	/**
	 * Add type parameters to a method declaration with a return type name
	 * including these ones and set its name properly
	 * 
	 * @param methodDeclaration
	 *            the method declaration.
	 */
	public static void addReturnTypeToMethodDeclaration(MethodDeclaration methodDeclaration, String returnTypeName) {
		returnTypeName = returnTypeName.trim();
		if (returnTypeName.contains(TypeParameterHelper.PARAMETER_ENTRY)
				&& returnTypeName.indexOf(TypeParameterHelper.PARAMETER_ENTRY) == 0) {
			int index = returnTypeName.indexOf(TypeParameterHelper.PARAMETER_EXIT);
			methodDeclaration.setReturnType(TypeAccessHelper.createTypeAccess(returnTypeName.substring(index + 1,
					returnTypeName.length()).trim()));
			String[] parametersTypes = returnTypeName.substring(1, index)
					.split(TypeParameterHelper.PARAMETER_SEPARATOR);
			EList<TypeParameter> typeParametersList = methodDeclaration.getTypeParameters();
			for (String parameterTypeName : parametersTypes) {
				typeParametersList.add(TypeParameterHelper.createTypeParameter(parameterTypeName));
			}
		} else {
			methodDeclaration.setReturnType(TypeAccessHelper.createTypeAccess(returnTypeName));
		}

	}

	/**
	 * Create type for a parameter of a type declaration
	 * 
	 * @param parameterTypeName
	 *            the parameter type name of the type declaration in one string.
	 * @return the created type for the specified argument of a parameterized
	 *         type
	 */
	protected static TypeParameter createTypeParameter(String parameterTypeName) {
		String[] parameterParts = parameterTypeName.trim().split("\\s+");
		if (parameterParts.length >= 3) {
			return TypeParameterBuilder.builder().setName(parameterParts[0].trim())
					.addBounds(TypeAccessHelper.createTypeAccess(parameterParts[2].trim())).build();
		}
		return TypeParameterBuilder.builder().setName(parameterParts[0]).build();
	}
}
