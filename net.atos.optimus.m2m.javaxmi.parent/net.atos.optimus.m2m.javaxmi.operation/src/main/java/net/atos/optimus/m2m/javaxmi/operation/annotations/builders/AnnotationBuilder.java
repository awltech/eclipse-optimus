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
package net.atos.optimus.m2m.javaxmi.operation.annotations.builders;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create annotation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationBuilder {

	/** The build annotation */
	private Annotation buildAnnotation;

	/**
	 * Give an annotation builder
	 * 
	 * @return a new annotation builder.
	 */
	public static AnnotationBuilder builder() {
		return new AnnotationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private AnnotationBuilder() {
		this.buildAnnotation = JavaFactory.eINSTANCE.createAnnotation();
	}

	/**
	 * Build an annotation of modisco model
	 * 
	 * @return a new annotation of modisco model.
	 */
	public Annotation build() {
		return this.buildAnnotation;
	}

	/**
	 * Set the type of the annotation under construction
	 * 
	 * @param type
	 *            the type of the annotation under construction.
	 * @return the builder.
	 */
	public AnnotationBuilder setType(TypeAccess type) {
		this.buildAnnotation.setType(type);
		return this;
	}

	/**
	 * Set the compilation unit of the annotation under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the annotation under construction.
	 * @return the builder.
	 */
	public AnnotationBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildAnnotation.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Add annotation member value pairs list to the annotation under
	 * construction
	 * 
	 * @param annotationMemberValues
	 *            the annotation member value pairs list to add to the
	 *            annotation under construction.
	 * @return the builder.
	 */
	public AnnotationBuilder addValues(AnnotationMemberValuePair... annotationMemberValues) {
		EList<AnnotationMemberValuePair> annotationMemberValuesList = this.buildAnnotation.getValues();
		for (AnnotationMemberValuePair annotationMemberValue : annotationMemberValues) {
			annotationMemberValuesList.add(annotationMemberValue);
		}
		return this;
	}

}
