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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeParameter;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create type parameter of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TypeParameterBuilder {

	/** The build type parameter */
	private TypeParameter buildTypeParameter;

	/**
	 * Give a type parameter builder
	 * 
	 * @return a new type parameter builder.
	 */
	public static TypeParameterBuilder builder() {
		return new TypeParameterBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private TypeParameterBuilder() {
		this.buildTypeParameter = JavaFactory.eINSTANCE.createTypeParameter();
	}

	/**
	 * Build a type parameter of modisco model
	 * 
	 * @return a new type parameter of modisco model.
	 */
	public TypeParameter build() {
		return this.buildTypeParameter;
	}

	/**
	 * Set the name of the type parameter under construction
	 * 
	 * @param typeParameterName
	 *            the name of the type parameter under construction.
	 * @return the builder.
	 */
	public TypeParameterBuilder setName(String typeParameterName) {
		this.buildTypeParameter.setName(typeParameterName);
		return this;
	}

	/**
	 * Add types list to the type parameter under construction
	 * 
	 * @param types
	 *            the types to add to the type parameter under construction.
	 * @return the builder.
	 */
	public TypeParameterBuilder addBounds(TypeAccess... types) {
		EList<TypeAccess> typesList = this.buildTypeParameter.getBounds();
		for (TypeAccess type : types) {
			typesList.add(type);
		}
		return this;
	}

}
