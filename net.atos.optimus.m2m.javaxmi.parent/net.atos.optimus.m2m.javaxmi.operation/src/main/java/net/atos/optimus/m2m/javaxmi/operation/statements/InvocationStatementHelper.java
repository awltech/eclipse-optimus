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
package net.atos.optimus.m2m.javaxmi.operation.statements;

import net.atos.optimus.m2m.javaxmi.operation.classes.ClassInstanceCreationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableAccessHelper;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;

/**
 * The purpose of such class is to help with the creation of invocation
 * statements
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InvocationStatementHelper {

	/**
	 * Create a class instantiation statement with no argument
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @return a new class instantiation statement instantiating the class with
	 *         the specified name.
	 */
	public static ExpressionStatement createClassInstantiationStatement(String className) {
		ClassInstanceCreation classInstanceCreation = ClassInstanceCreationBuilder.builder()
				.setType(TypeAccessHelper.createClassTypeAccess(className)).build();
		return ExpressionStatementBuilder.builder().setExpression(classInstanceCreation).build();
	}

	/**
	 * Create a class instantiation statement with arguments
	 * 
	 * @param className
	 *            the name of the class to instantiate.
	 * @param arguments
	 *            the arguments list of the class instantiation statement.
	 * @return a new class instantiation statement instantiating the class with
	 *         the specified name.
	 */
	public static ExpressionStatement createClassInstantiationStatement(String className, String... arguments) {
		ClassInstanceCreation classInstanceCreation = ClassInstanceCreationBuilder.builder()
				.setType(TypeAccessHelper.createClassTypeAccess(className)).build();
		EList<Expression> argumentsList = classInstanceCreation.getArguments();
		for (String argument : arguments) {
			argumentsList.add(VariableAccessHelper.createVariableAccess(argument));
		}
		return ExpressionStatementBuilder.builder().setExpression(classInstanceCreation).build();
	}

}
