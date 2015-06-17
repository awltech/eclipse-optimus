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

import org.eclipse.gmt.modisco.java.UnresolvedType;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create unresolved type of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class UnresolvedTypeBuilder {

	/** The build unresolved type */
	private UnresolvedType buildUnresolvedType;

	/**
	 * Give an unresolved type builder
	 * 
	 * @return a new unresolved type builder.
	 */
	public static UnresolvedTypeBuilder builder() {
		return new UnresolvedTypeBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private UnresolvedTypeBuilder() {
		this.buildUnresolvedType = JavaFactory.eINSTANCE.createUnresolvedType();
	}

	/**
	 * Build an unresolved type of modisco model
	 * 
	 * @return a new unresolved type of modisco model.
	 */
	public UnresolvedType build() {
		return this.buildUnresolvedType;
	}

	/**
	 * Set the name of the unresolved type under construction
	 * 
	 * @param name
	 *            the name of the unresolved type under construction.
	 * @return the builder.
	 */
	public UnresolvedTypeBuilder setName(String name) {
		this.buildUnresolvedType.setName(name);
		return this;
	}

}
