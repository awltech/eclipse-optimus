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

import java.util.Iterator;

import net.atos.optimus.m2m.javaxmi.operation.annotations.builder.AnnotationMemberValuePairBuilder;
import net.atos.optimus.m2m.javaxmi.operation.array.ArrayInitializerBuilder;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.Expression;

/**
 * The purpose of such class is to help with the creation of annotation member
 * value pair declaration
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AnnotationMemberValuePairHelper {

	/**
	 * Create an annotation member value pair associated to an annotation and a
	 * property name and expression
	 * 
	 * @param annotation
	 *            the annotation.
	 * @param propertyName
	 *            the property name.
	 * @param propertyExpression
	 *            the property expression.
	 * @return the created annotation member value pair associated to specified
	 *         annotation and property name and expression.
	 */
	public static AnnotationMemberValuePair createAnnotationMemberValuePair(Annotation annotation, String propertyName,
			Expression propertyExpression) {
		AnnotationMemberValuePair mvp = null;
		Iterator<AnnotationMemberValuePair> iterator = annotation.getValues().iterator();

		while (iterator.hasNext() && mvp == null) {
			AnnotationMemberValuePair tempMvp = iterator.next();
			if (propertyName == null ? tempMvp.getName() == null : propertyName.equals(tempMvp.getName())) {
				mvp = tempMvp;
			}
		}

		if (mvp == null) {
			mvp = AnnotationMemberValuePairBuilder
					.builder()
					.setName(propertyName)
					.setValue(propertyExpression)
					.setMember(
							AnnotationTypeMemberDeclarationHelper.createTypeMemberDeclaration(annotation, propertyName))
					.build();
			annotation.getValues().add(mvp);
			return mvp;
		}

		if (mvp.getValue() != null) {
			ArrayInitializer arrayInitializer = null;
			Expression value = mvp.getValue();
			if (value instanceof ArrayInitializer) {
				arrayInitializer = (ArrayInitializer) value;
			} else {
				arrayInitializer = ArrayInitializerBuilder.builder().addExpression(value).build();
				mvp.setValue(arrayInitializer);
			}
			arrayInitializer.getExpressions().add(propertyExpression);
		} else {
			mvp.setValue(propertyExpression);
		}

		mvp.setMember(AnnotationTypeMemberDeclarationHelper.createTypeMemberDeclaration(annotation, propertyName));

		return mvp;
	}
}
