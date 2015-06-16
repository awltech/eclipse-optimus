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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create parameterized type of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ParameterizedTypeBuilder {

	/** The build parameterized type */
	private ParameterizedType buildParameterizedTypeAccess;

	/**
	 * Give a new parameterized type builder
	 * 
	 * @return a new parameterized type builder.
	 */
	public static ParameterizedTypeBuilder builder() {
		return new ParameterizedTypeBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ParameterizedTypeBuilder() {
		this.buildParameterizedTypeAccess = JavaFactory.eINSTANCE.createParameterizedType();
	}

	/**
	 * Build a parameterized type of modisco model
	 * 
	 * @return a new parameterized type of modisco model.
	 */
	public ParameterizedType build() {
		return this.buildParameterizedTypeAccess;
	}

	/**
	 * Set the type of the parameterized type under construction
	 * 
	 * @param type
	 *            the type of the parameterized type under construction.
	 * @return the builder.
	 */
	public ParameterizedTypeBuilder setType(TypeAccess type) {
		this.buildParameterizedTypeAccess.setType(type);
		return this;
	}

	/**
	 * Add the type arguments list to the parameterized type under construction
	 * 
	 * @param typeArguments
	 *            the type arguments list to add to the parameterized type under
	 *            construction.
	 * @return the builder.
	 */
	public ParameterizedTypeBuilder addTypeArguments(TypeAccess... typeArguments) {
		EList<TypeAccess> typeArgumentsList = this.buildParameterizedTypeAccess.getTypeArguments();
		for (TypeAccess typeArgument : typeArguments) {
			typeArgumentsList.add(typeArgument);
		}
		return this;
	}

}
