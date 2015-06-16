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
package net.atos.optimus.m2m.javaxmi.operation.enums;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create enum constant declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class EnumConstantDeclarationBuilder {

	/** The build enum constant declaration */
	private EnumConstantDeclaration buildEnumConstantDeclaration;

	/**
	 * Give an enum constant declaration builder
	 * 
	 * @return a new enum constant declaration builder.
	 */
	public static EnumConstantDeclarationBuilder builder() {
		return new EnumConstantDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private EnumConstantDeclarationBuilder() {
		this.buildEnumConstantDeclaration = JavaFactory.eINSTANCE.createEnumConstantDeclaration();
	}

	/**
	 * Build an enum constant declaration of modisco model
	 * 
	 * @return a new enum constant declaration of modisco model.
	 */
	public EnumConstantDeclaration build() {
		return this.buildEnumConstantDeclaration;
	}

	/**
	 * Set the name of the enum constant declaration under construction
	 * 
	 * @param name
	 *            the name of the enum constant declaration under construction.
	 * @return the builder.
	 */
	public EnumConstantDeclarationBuilder setName(String name) {
		this.buildEnumConstantDeclaration.setName(name);
		return this;
	}

	/**
	 * Add an arguments list to the enum constant declaration under construction
	 * 
	 * @param arguments
	 *            the arguments list to add to the enum constant declaration
	 *            under construction.
	 * @return the builder.
	 */
	public EnumConstantDeclarationBuilder addArguments(Expression... arguments) {
		EList<Expression> argumentsList = this.buildEnumConstantDeclaration.getArguments();
		for (Expression argument : arguments) {
			argumentsList.add(argument);
		}
		return this;
	}

}
