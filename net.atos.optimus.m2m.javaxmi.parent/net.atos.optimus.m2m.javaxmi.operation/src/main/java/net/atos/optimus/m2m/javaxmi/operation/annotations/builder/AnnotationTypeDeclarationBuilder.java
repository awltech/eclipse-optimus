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

import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create annotation type declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationTypeDeclarationBuilder {

	/** The build annotation type declaration */
	private AnnotationTypeDeclaration buildAnnotationTypeDeclaration;

	/**
	 * Give an annotation type declaration builder
	 * 
	 * @return a new annotation type declaration builder.
	 */
	public static AnnotationTypeDeclarationBuilder builder() {
		return new AnnotationTypeDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private AnnotationTypeDeclarationBuilder() {
		this.buildAnnotationTypeDeclaration = JavaFactory.eINSTANCE.createAnnotationTypeDeclaration();
	}

	/**
	 * Build an annotation type declaration of modisco model
	 * 
	 * @return a new annotation type declaration of modisco model.
	 */
	public AnnotationTypeDeclaration build() {
		return this.buildAnnotationTypeDeclaration;
	}

	/**
	 * Set the name of the annotation type declaration under construction
	 * 
	 * @param name
	 *            the name of the annotation type declaration under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationTypeDeclarationBuilder setName(String name) {
		this.buildAnnotationTypeDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the package of the annotation type declaration under construction
	 * 
	 * @param javaPackage
	 *            the package of the annotation type declaration under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationTypeDeclarationBuilder setPackage(Package javaPackage) {
		this.buildAnnotationTypeDeclaration.setPackage(javaPackage);
		return this;
	}

	/**
	 * Set the proxy state of the annotation type declaration under construction
	 * 
	 * @param isProxy
	 *            the proxy state of the annotation type declaration under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationTypeDeclarationBuilder setProxy(boolean isProxy) {
		this.buildAnnotationTypeDeclaration.setProxy(isProxy);
		return this;
	}

}
