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

import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create type access of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TypeAccessBuilder {

	/** The build type access */
	private TypeAccess buildTypeAccess;

	/**
	 * Give a new type access builder
	 * 
	 * @return a new type access builder.
	 */
	public static TypeAccessBuilder builder() {
		return new TypeAccessBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private TypeAccessBuilder() {
		this.buildTypeAccess = JavaFactory.eINSTANCE.createTypeAccess();
	}

	/**
	 * Build a type access of modisco model
	 * 
	 * @return a new type access of modisco model.
	 */
	public TypeAccess build() {
		return this.buildTypeAccess;
	}

	/**
	 * Set the type of the type access under construction
	 * 
	 * @param type
	 *            the type of the type access under construction.
	 * @return the builder.
	 */
	public TypeAccessBuilder setType(Type type) {
		this.buildTypeAccess.setType(type);
		return this;
	}

}
