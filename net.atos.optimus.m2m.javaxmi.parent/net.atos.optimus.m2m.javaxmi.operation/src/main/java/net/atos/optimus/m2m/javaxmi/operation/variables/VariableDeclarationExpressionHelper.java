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

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary.VariableDeclarationExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;

import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.VariableDeclarationExpression;

/**
 * The purpose of such class is to help with the creation of variable
 * declaration expressions
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableDeclarationExpressionHelper {

	/**
	 * Private constructor
	 * 
	 */
	private VariableDeclarationExpressionHelper() {
	}

	/**
	 * Create a variable declaration expression
	 * 
	 * @param variableTypeName
	 *            the type name of the declared variable.
	 * @param variableName
	 *            the name of the declared variable.
	 * @param inheritance
	 *            the inheritance state of the declared variable.
	 * @return the created variable declaration expression.
	 */
	public static VariableDeclarationExpression createVariableDeclarationExpression(String variableTypeName,
			String variableName, InheritanceKind inheritance) {
		return VariableDeclarationExpressionBuilder.builder()
				.setType(TypeAccessHelper.createTypeAccess(variableTypeName))
				.setModifier(ModifierBuilder.builder().setInheritance(InheritanceKind.NONE).build())
				.addFragment(VariableDeclarationFragmentBuilder.builder().setName(variableName).build()).build();
	}
}
