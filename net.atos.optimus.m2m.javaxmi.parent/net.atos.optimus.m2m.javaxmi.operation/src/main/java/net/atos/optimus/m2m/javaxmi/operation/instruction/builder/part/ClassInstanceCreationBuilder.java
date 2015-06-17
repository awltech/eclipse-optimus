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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.part;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create class instance creation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ClassInstanceCreationBuilder {

	/** The build class instance creation */
	private ClassInstanceCreation buildClassInstanceCreation;

	/**
	 * Give a new class instance creation builder
	 * 
	 * @return a new class instance creation builder.
	 */
	public static ClassInstanceCreationBuilder builder() {
		return new ClassInstanceCreationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ClassInstanceCreationBuilder() {
		this.buildClassInstanceCreation = JavaFactory.eINSTANCE.createClassInstanceCreation();
	}

	/**
	 * Build a class instance creation of modisco model
	 * 
	 * @return a new class instance creation of modisco model.
	 */
	public ClassInstanceCreation build() {
		return this.buildClassInstanceCreation;
	}

	/**
	 * Set the type of the class instance creation under construction
	 * 
	 * @param classType
	 *            the type of the class instance creation under construction.
	 * @return the builder.
	 */
	public ClassInstanceCreationBuilder setType(TypeAccess classType) {
		this.buildClassInstanceCreation.setType(classType);
		return this;
	}

	/**
	 * Add an arguments list to the class instance creation under construction
	 * 
	 * @param arguments
	 *            the arguments list to add to the class instance creation under
	 *            construction.
	 * @return the builder.
	 */
	public ClassInstanceCreationBuilder addArguments(Expression... arguments) {
		EList<Expression> argumentList = this.buildClassInstanceCreation.getArguments();
		for (Expression argument : arguments) {
			argumentList.add(argument);
		}
		return this;
	}

}
