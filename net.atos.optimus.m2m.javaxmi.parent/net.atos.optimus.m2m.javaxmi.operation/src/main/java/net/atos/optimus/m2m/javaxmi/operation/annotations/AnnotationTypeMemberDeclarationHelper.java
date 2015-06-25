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

import net.atos.optimus.m2m.javaxmi.operation.annotations.builder.AnnotationTypeMemberDeclarationBuilder;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;

/**
 * The purpose of such class is to help with the creation of annotation type
 * member declaration
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationTypeMemberDeclarationHelper {

	/**
	 * Create an annotation type member associated to an annotation and a
	 * property name
	 * 
	 * @param annotation
	 *            the annotation.
	 * @param propertyName
	 *            the property name.
	 * @return the created annotation type member associated to specified
	 *         annotation and property name.
	 */
	public static AnnotationTypeMemberDeclaration createTypeMemberDeclaration(Annotation annotation, String propertyName) {
		AnnotationTypeDeclaration annotationDeclaration = (AnnotationTypeDeclaration) annotation.getType().getType();

		if (annotationDeclaration != null) {
			for (BodyDeclaration bodyDeclaration : annotationDeclaration.getBodyDeclarations()) {
				if (bodyDeclaration instanceof AnnotationTypeMemberDeclaration) {
					AnnotationTypeMemberDeclaration atmd = (AnnotationTypeMemberDeclaration) bodyDeclaration;
					if (propertyName.equals(atmd.getName())) {
						return atmd;
					}
				}
			}
		}

		return AnnotationTypeMemberDeclarationBuilder.builder().setName(propertyName)
				.setAbstractTypeDeclaration(annotationDeclaration).build();
	}

}
