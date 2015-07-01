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

import net.atos.optimus.m2m.javaxmi.operation.element.Element;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.StringLiteral;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * Models an instruction : wrapper of Annotation in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class JavaAnnotation extends Element<Annotation> {

	private String packageName;

	private String annotationName;

	private List<PendingAnnotationParameter> pendingAnnotationParameters;

	/**
	 * Constructor of Java annotation
	 * 
	 * @param packageName
	 *            the name of the package of the annotation.
	 * @param annotationName
	 *            the annotation name.
	 * @param annotation
	 *            the annotation.
	 */
	public JavaAnnotation(String packageName, String annotationName, Annotation annotation) {
		super(annotation);
		this.packageName = packageName;
		this.annotationName = annotationName;
		this.pendingAnnotationParameters = new LinkedList<PendingAnnotationParameter>();
	}

	/**
	 * Constructor of Java annotation
	 * 
	 * @param packageName
	 *            the name of the package of the annotation.
	 * @param annotationName
	 *            the annotation name.
	 * @param annotation
	 *            the annotation.
	 * @param pendingAnnotationParameters
	 *            the pending annotation parameters list.
	 */
	public JavaAnnotation(String packageName, String annotationName, Annotation annotation,
			List<PendingAnnotationParameter> pendingAnnotationParameters) {
		super(annotation);
		this.packageName = packageName;
		this.annotationName = annotationName;
		this.pendingAnnotationParameters = pendingAnnotationParameters;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public String getAnnotationName() {
		return this.annotationName;
	}

	public void addPendingParameters(PendingAnnotationParameter pendingAnnotationParameter) {
		this.pendingAnnotationParameters.add(pendingAnnotationParameter);
	}

	public List<PendingAnnotationParameter> getPendingParameters() {
		return this.pendingAnnotationParameters;
	}

	@Override
	public JavaAnnotation addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public JavaAnnotation addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
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
	public JavaAnnotation addAnnotationParameter(String propertyName, Object propertyValue, boolean escape) {
		if (this.getDelegate().getType() == null) {
			this.addPendingParameters(new PendingAnnotationParameter(propertyName, propertyValue, escape));
			return this;
		}

		StringLiteral propertyExpression = JavaFactory.eINSTANCE.createStringLiteral();
		if (escape) {
			propertyExpression.setEscapedValue("\"" + String.valueOf(propertyValue) + "\"");
		} else {
			propertyExpression.setEscapedValue(String.valueOf(propertyValue));
		}

		AnnotationTypeMemberDeclaration annotationTypeMemberDeclaration = AnnotationTypeMemberDeclarationHelper
				.createTypeMemberDeclaration(this.getDelegate(), propertyName);

		AnnotationMemberValuePair annotationMemberValuePair = AnnotationMemberValuePairHelper
				.createAnnotationMemberValuePair(this.getDelegate(), propertyName, propertyExpression);

		annotationMemberValuePair.setMember(annotationTypeMemberDeclaration);

		return this;
	}

}
