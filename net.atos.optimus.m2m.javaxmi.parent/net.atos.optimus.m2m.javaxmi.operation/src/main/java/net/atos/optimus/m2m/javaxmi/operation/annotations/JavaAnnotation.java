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

import net.atos.optimus.m2m.javaxmi.operation.element.Element;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ArrayInitializerInstruction;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.CompilationUnit;
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

	/**
	 * Constructor of Java annotation
	 * 
	 * @param annotation
	 *            the annotation.
	 */
	public JavaAnnotation(Annotation annotation) {
		super(annotation);
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
			return this;
		}

		StringLiteral propertyExpression = JavaFactory.eINSTANCE.createStringLiteral();
		if (escape) {
			propertyExpression.setEscapedValue("\"" + String.valueOf(propertyValue) + "\"");
		} else {
			propertyExpression.setEscapedValue(String.valueOf(propertyValue));
		}

		AnnotationMemberValuePairHelper.createAnnotationMemberValuePair(this.getDelegate(), propertyName,
				propertyExpression);
		return this;
	}

	/**
	 * Create an annotation and add it to the current array initializer
	 * instruction
	 * 
	 * @param packageName
	 *            the name of the package of the current array initializer
	 *            instruction.
	 * @param annotationName
	 *            the annotation name.
	 * @return the created java annotation.
	 */
	public JavaAnnotation createAnnotation(String packageName, String annotationName, String propertyName) {
		Annotation annotation = this.getDelegate();
		CompilationUnit compilationUnit = this.getDelegate() == null ? null : this.getDelegate()
				.getOriginalCompilationUnit();
		AnnotationMemberValuePair pair = null;
		if (annotation.getValues().size() == 0) {
			pair = JavaFactory.eINSTANCE.createAnnotationMemberValuePair();
			if (propertyName != null) {
				AnnotationTypeMemberDeclaration annotationTypeMemberDeclaration = AnnotationTypeMemberDeclarationHelper
						.createTypeMemberDeclaration(annotation, propertyName);
				pair.setMember(annotationTypeMemberDeclaration);
			}
			annotation.getValues().add(pair);
		} else
			pair = annotation.getValues().get(0);
		ArrayInitializer initializer = null;
		if (pair.getValue() == null || !(pair.getValue() instanceof ArrayInitializer)) {
			initializer = JavaFactory.eINSTANCE.createArrayInitializer();
			pair.setValue(initializer);
			initializer.setOriginalCompilationUnit(compilationUnit);
		} else
			initializer = (ArrayInitializer) pair.getValue();
		return new ArrayInitializerInstruction(initializer).createAnnotation(packageName, annotationName);
	}

}
