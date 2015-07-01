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
package net.atos.optimus.m2m.javaxmi.operation.annotations;

import java.util.LinkedList;
import java.util.List;

import net.atos.optimus.m2m.javaxmi.operation.annotations.builder.AnnotationBuilder;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * The purpose of such class is to help with the creation of annotations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationHelper {

	/** The build annotation */
	private Annotation buildAnnotation;

	private String packageName;

	private String annotationName;

	private List<PendingAnnotationParameter> pendingAnnotationParameters;

	/**
	 * Launch the build of a new annotation
	 * 
	 * @param packageName
	 *            the name of the package of the annotation under construction.
	 * @param annotationName
	 *            the name of the annotation under construction.
	 * @return a new helper.
	 */
	public static AnnotationHelper builder(String packageName, String annotationName) {
		return new AnnotationHelper(packageName, annotationName);
	}

	/**
	 * Private constructor : a new annotation
	 * 
	 * @param packageName
	 *            the name of the package of the annotation under construction.
	 * @param annotationName
	 *            the name of the annotation under construction.
	 */
	private AnnotationHelper(String packageName, String annotationName) {
		this.packageName = packageName;
		this.annotationName = annotationName;
		this.buildAnnotation = AnnotationBuilder.builder().build();
		this.pendingAnnotationParameters = new LinkedList<PendingAnnotationParameter>();
	}

	/**
	 * Give the build annotation
	 * 
	 * @return the build annotation.
	 */
	public JavaAnnotation build() {
		return new JavaAnnotation(this.packageName, this.annotationName, this.buildAnnotation,
				this.pendingAnnotationParameters);
	}

	/**
	 * Adds a new object value to an annotation property. Note that is the
	 * annotation already has a valued property with the same name, the value of
	 * the property will be updated with an array
	 * 
	 * @param propertyName
	 *            the property name.
	 * @param propertyValue
	 *            the property value.
	 * @param escape
	 *            the escape state.
	 * @return the current annotation.
	 */
	public AnnotationHelper addAnnotationParameter(String propertyName, Object propertyValue, boolean escape) {
		if (this.buildAnnotation.getType() == null) {
			this.pendingAnnotationParameters.add(new PendingAnnotationParameter(propertyName, propertyValue, escape));
			return this;
		}

		StringLiteral propertyExpression = JavaFactory.eINSTANCE.createStringLiteral();
		if (escape) {
			propertyExpression.setEscapedValue("\"" + String.valueOf(propertyValue) + "\"");
		} else {
			propertyExpression.setEscapedValue(String.valueOf(propertyValue));
		}

		AnnotationTypeMemberDeclaration annotationTypeMemberDeclaration = AnnotationTypeMemberDeclarationHelper
				.createTypeMemberDeclaration(this.buildAnnotation, propertyName);

		AnnotationMemberValuePair annotationMemberValuePair = AnnotationMemberValuePairHelper
				.createAnnotationMemberValuePair(this.buildAnnotation, propertyName, propertyExpression);

		annotationMemberValuePair.setMember(annotationTypeMemberDeclaration);

		return this;
	}

}
