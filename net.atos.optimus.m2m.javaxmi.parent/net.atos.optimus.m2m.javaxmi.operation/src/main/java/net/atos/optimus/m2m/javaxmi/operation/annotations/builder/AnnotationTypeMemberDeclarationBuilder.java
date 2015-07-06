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

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create annotation type member declaration of modisco
 * model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationTypeMemberDeclarationBuilder {

	/** The build annotation type member declaration */
	private AnnotationTypeMemberDeclaration buildAnnotationTypeMemberDeclaration;

	/**
	 * Give a new annotation type member declaration builder
	 * 
	 * @return a new annotation type member declaration builder.
	 */
	public static AnnotationTypeMemberDeclarationBuilder builder() {
		return new AnnotationTypeMemberDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private AnnotationTypeMemberDeclarationBuilder() {
		this.buildAnnotationTypeMemberDeclaration = JavaFactory.eINSTANCE.createAnnotationTypeMemberDeclaration();
	}

	/**
	 * Build an annotation type member declaration of modisco model
	 * 
	 * @return a new annotation type member declaration of modisco model.
	 */
	public AnnotationTypeMemberDeclaration build() {
		return this.buildAnnotationTypeMemberDeclaration;
	}

	/**
	 * Set the name of the annotation type member declaration under construction
	 * 
	 * @param name
	 *            the name of the annotation type member declaration under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationTypeMemberDeclarationBuilder setName(String name) {
		this.buildAnnotationTypeMemberDeclaration.setName(name);
		return this;
	}

	/**
	 * Set the type of the annotation type member declaration under construction
	 * 
	 * @param type
	 *            the type of the annotation type member declaration under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationTypeMemberDeclarationBuilder setType(TypeAccess type) {
		this.buildAnnotationTypeMemberDeclaration.setType(type);
		return this;
	}

	/**
	 * Set the abstract type declaration of the annotation type member
	 * declaration under construction
	 * 
	 * @param type
	 *            the abstract type declaration of the annotation type member
	 *            declaration under construction.
	 * @return the builder.
	 */
	public AnnotationTypeMemberDeclarationBuilder setAbstractTypeDeclaration(AbstractTypeDeclaration type) {
		this.buildAnnotationTypeMemberDeclaration.setAbstractTypeDeclaration(type);
		return this;
	}

	/**
	 * Set a default expression to the annotation type member declaration under
	 * construction
	 * 
	 * @param expression
	 *            the default expression of the annotation type member
	 *            declaration under construction.
	 * @return the builder.
	 */
	public AnnotationTypeMemberDeclarationBuilder setDefault(Expression expression) {
		this.buildAnnotationTypeMemberDeclaration.setDefault(expression);
		return this;
	}

	/**
	 * Add annotation member value usages list to the annotation type member
	 * declaration under construction
	 * 
	 * @param annotationMemberUsages
	 *            the annotation member value usages list to add to the
	 *            annotation type member declaration under construction.
	 * @return the builder.
	 */
	public AnnotationTypeMemberDeclarationBuilder addUsages(AnnotationMemberValuePair... annotationMemberUsages) {
		EList<AnnotationMemberValuePair> annotationMemberValuesList = this.buildAnnotationTypeMemberDeclaration
				.getUsages();
		for (AnnotationMemberValuePair annotationMemberValue : annotationMemberUsages) {
			annotationMemberValuesList.add(annotationMemberValue);
		}
		return this;
	}

}
