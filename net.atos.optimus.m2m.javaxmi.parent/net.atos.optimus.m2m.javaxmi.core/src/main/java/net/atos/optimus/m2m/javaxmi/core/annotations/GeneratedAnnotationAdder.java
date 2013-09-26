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
package net.atos.optimus.m2m.javaxmi.core.annotations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.gmt.modisco.java.Annotation;
import org.eclipse.gmt.modisco.java.BodyDeclaration;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class GeneratedAnnotationAdder {

	private static final String EMPTY = "";

	private static final String PATTERN = "%s,%s";

	private static final String DATE = "date";

	private static final String TO_BE_CALCULATED = "ToBeCalculated";

	private static final String COMMENTS = "comments";

	private static final String VALUE2 = "value";

	private static final String JAVAX_ANNOTATION = "javax.annotation";

	private static final String GENERATED = "Generated";

	private static final DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	public static void addGenerated(BodyDeclaration bodyDeclaration, String value, boolean withComments,
			boolean withDate) {
		Annotation addedAnnotation = JavaAnnotationHelper.addAnnotation(bodyDeclaration, JAVAX_ANNOTATION, GENERATED);
		JavaAnnotationHelper.addAnnotationParameter(addedAnnotation, VALUE2, value, true);
		if (withComments)
			JavaAnnotationHelper.addAnnotationParameter(addedAnnotation, COMMENTS, TO_BE_CALCULATED, true);
		if (withDate) {
			String formattedDate = DATEFORMAT.format(new Date());
			JavaAnnotationHelper.addAnnotationParameter(addedAnnotation, DATE, formattedDate, true);
		}
	}

	public static void addGenerated(BodyDeclaration bodyDeclaration, String value, boolean withHashCode, String uniqueId) {

		Annotation addedAnnotation = JavaAnnotationHelper.addAnnotation(bodyDeclaration, JAVAX_ANNOTATION, GENERATED);

		JavaAnnotationHelper.addAnnotationParameter(addedAnnotation, VALUE2, value, true);

		String commentsValue = String.format(PATTERN, withHashCode ? TO_BE_CALCULATED : EMPTY, uniqueId);
		JavaAnnotationHelper.addAnnotationParameter(addedAnnotation, COMMENTS, commentsValue, true);
	}

}
