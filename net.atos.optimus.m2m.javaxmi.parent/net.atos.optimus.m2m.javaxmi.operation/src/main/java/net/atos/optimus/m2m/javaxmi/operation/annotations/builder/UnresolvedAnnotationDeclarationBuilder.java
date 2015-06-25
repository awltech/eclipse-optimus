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
package net.atos.optimus.m2m.javaxmi.operation.annotations.builder;

import org.eclipse.gmt.modisco.java.UnresolvedAnnotationDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create an unresolved annotation declaration of modisco
 * model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class UnresolvedAnnotationDeclarationBuilder {

	/** The build unresolved annotation declaration */
	private UnresolvedAnnotationDeclaration buildUnresolvedAnnotationDeclaration;

	/**
	 * Give an unresolved annotation declaration builder
	 * 
	 * @return a new unresolved annotation declaration builder.
	 */
	public static UnresolvedAnnotationDeclarationBuilder builder() {
		return new UnresolvedAnnotationDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private UnresolvedAnnotationDeclarationBuilder() {
		this.buildUnresolvedAnnotationDeclaration = JavaFactory.eINSTANCE.createUnresolvedAnnotationDeclaration();
	}

	/**
	 * Build an unresolved annotation declaration of modisco model
	 * 
	 * @return a new unresolved annotation declaration of modisco model.
	 */
	public UnresolvedAnnotationDeclaration build() {
		return this.buildUnresolvedAnnotationDeclaration;
	}

	/**
	 * Set the name of the unresolved annotation declaration under construction
	 * 
	 * @param name
	 *            the name of the unresolved annotation declaration under
	 *            construction.
	 * @return the builder.
	 */
	public UnresolvedAnnotationDeclarationBuilder setName(String name) {
		this.buildUnresolvedAnnotationDeclaration.setName(name);
		return this;
	}

}
