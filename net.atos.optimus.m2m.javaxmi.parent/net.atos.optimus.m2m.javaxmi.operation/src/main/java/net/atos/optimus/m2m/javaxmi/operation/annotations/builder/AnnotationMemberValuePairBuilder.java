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

import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create annotation member value pair of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationMemberValuePairBuilder {

	/** The build annotation member value pair */
	private AnnotationMemberValuePair buildAnnotationMemberValuePair;

	/**
	 * Give an annotation member value pair builder
	 * 
	 * @return a new annotation member value pair builder.
	 */
	public static AnnotationMemberValuePairBuilder builder() {
		return new AnnotationMemberValuePairBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private AnnotationMemberValuePairBuilder() {
		this.buildAnnotationMemberValuePair = JavaFactory.eINSTANCE.createAnnotationMemberValuePair();
	}

	/**
	 * Build an annotation member value pair of modisco model
	 * 
	 * @return a new annotation member value pair of modisco model.
	 */
	public AnnotationMemberValuePair build() {
		return this.buildAnnotationMemberValuePair;
	}

	/**
	 * Set the name of the annotation member value pair under construction
	 * 
	 * @param name
	 *            the name of the annotation member value pair under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationMemberValuePairBuilder setName(String name) {
		this.buildAnnotationMemberValuePair.setName(name);
		return this;
	}

	/**
	 * Set the member of the annotation member value pair under construction
	 * 
	 * @param typeMemberDeclaration
	 *            the type member declaration of the annotation member value
	 *            pair under construction.
	 * @return the builder.
	 */
	public AnnotationMemberValuePairBuilder setMember(AnnotationTypeMemberDeclaration typeMemberDeclaration) {
		this.buildAnnotationMemberValuePair.setMember(typeMemberDeclaration);
		return this;
	}

	/**
	 * Set an expression to the annotation member value pair under construction
	 * 
	 * @param expression
	 *            the expression of the annotation member value pair under
	 *            construction.
	 * @return the builder.
	 */
	public AnnotationMemberValuePairBuilder setValue(Expression expression) {
		this.buildAnnotationMemberValuePair.setValue(expression);
		return this;
	}

}
