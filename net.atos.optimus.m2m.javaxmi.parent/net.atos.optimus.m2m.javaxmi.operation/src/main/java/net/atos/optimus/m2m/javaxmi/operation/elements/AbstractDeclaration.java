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
package net.atos.optimus.m2m.javaxmi.operation.elements;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.annotations.JavaAnnotation;
import net.atos.optimus.m2m.javaxmi.operation.annotations.builders.AnnotationBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.MissingImportAdder;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.TypeAccess;

/**
 * Models an abstract declaration : wrapper of BodyDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractDeclaration<D extends BodyDeclaration> extends Element<D> {

	/**
	 * Constructor of abstract declaration
	 * 
	 * @param bodyDeclaration
	 *            the body declaration.
	 */
	public AbstractDeclaration(D bodyDeclaration) {
		super(bodyDeclaration);
	}

	@Override
	public AbstractDeclaration<D> addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public AbstractDeclaration<D> addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	/**
	 * Add an annotation to the current abstract declaration
	 * 
	 * @param packageName
	 *            the name of the package of the annotation.
	 * @param annotationName
	 *            the annotation name.
	 * @return the current abstract declaration.
	 */
	public AbstractDeclaration<D> addAnnotation(String packageName, String annotationName) {
		this.createAnnotation(packageName, annotationName);
		return this;
	}

	/**
	 * Create an annotation and add it to the current abstract declaration
	 * 
	 * @param packageName
	 *            the name of the package of the annotation.
	 * @param annotationName
	 *            the annotation name.
	 * @return the created java annotation.
	 */
	public JavaAnnotation createAnnotation(String packageName, String annotationName) {
		CompilationUnit compilationUnit = this.getDelegate() == null ? null : this.getDelegate()
				.getOriginalCompilationUnit();
		TypeAccess annotationType = compilationUnit != null ? TypeAccessHelper.createAnnotationTypeAccess(this,
				packageName, annotationName) : TypeAccessHelper.createOrphanAnnotationTypeAccess(this, packageName
				+ "." + annotationName);
		Annotation annotation = AnnotationBuilder.builder().setCompilationUnit(compilationUnit).setType(annotationType)
				.build();
		if (this.getDelegate() != null) {
			this.getDelegate().getAnnotations().add(annotation);
			MissingImportAdder.addMissingImport(this.getDelegate(), annotationType.getType());
		}
		return new JavaAnnotation(annotation);
	}

}
