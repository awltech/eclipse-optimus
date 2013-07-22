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
package net.atos.optimus.m2t.merger.java.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;


/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class DefaultMergingStrategy implements MergingStrategy {

	private ASTRewrite astr = null;

	public DefaultMergingStrategy() {
	}

	public final ASTRewrite getASTRewrite() {
		return this.astr;
	}

	public final void setASTRewrite(ASTRewrite astr) {
		this.astr = astr;
	}

	public void mergeStrategy(Annotation existingAnnotation, Annotation generatedAnnotation,
			ListRewrite lw) {

		if (existingAnnotation == null && generatedAnnotation == null) {
			return;
		}

		if (existingAnnotation == null) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.BODYDECL_ANNOADDED.value(generatedAnnotation.toString(),
						JavaCodeHelper.getName(generatedAnnotation.getParent()),
						JavaCodeHelper.getDescription(generatedAnnotation.getParent())));
			/*
			 * This annotation is present in the generated code AND This
			 * annotation is not present in the existing code => Add this
			 * annotation in the merge result
			 */
			lw.insertFirst(generatedAnnotation, null);
		} else {
			if (generatedAnnotation == null) {
				/*
				 * This annotation is present into the existing fragment AND
				 * This annotation is not present into the generated fragment
				 * AND This annotation is a predefined one => This annotation
				 * must be removed - Use cases : Change association multiplicity
				 * (from OneToOne to OneToMany),...
				 */
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.BODYDECL_ANNOREMOVED.value(existingAnnotation.toString(),
							JavaCodeHelper.getName(existingAnnotation.getParent()),
							JavaCodeHelper.getDescription(existingAnnotation.getParent())));
				lw.remove(existingAnnotation, null);
			} else {
				/*
				 * This annotation is present into the existing fragment AND
				 * This annotation is present into the generated fragment =>
				 * Annotation attributes must be merged
				 */
				this.mergeAnnotationAttributes(existingAnnotation, generatedAnnotation, lw);
			}
		}
	}

	/**
	 * Annotation attributes handlings for annotation's type located in the
	 * generated annotation property file.
	 * 
	 * @param annotationInTheExistingFragment
	 * @param existingAnnotation
	 * @param lw
	 * @param generatedAnnotations
	 * @param annoName
	 */
	private void mergeAnnotationAttributes(Annotation annotationInTheExistingFragment,
			Annotation annotationInTheGeneratedFragment, ListRewrite lw) {

		if (annotationInTheExistingFragment.isMarkerAnnotation()) {
			/*
			 * The annotation used inside the existing fragment is a marker
			 * annotation (annotation without attribute)
			 */
			if (!annotationInTheGeneratedFragment.isMarkerAnnotation()) {
				/*
				 * The annotation used inside the generated fragment contains
				 * one or many attributes. The annotation used inside the
				 * generated fragment must be used in the result
				 */
				lw.replace(annotationInTheExistingFragment, annotationInTheGeneratedFragment, null);
			}
		} else {
			if (annotationInTheExistingFragment.isSingleMemberAnnotation()) {
				/*
				 * The annotation used inside the existing fragment is a single
				 * member annotation (annotation without only one attribute)
				 */
				if (annotationInTheGeneratedFragment.isSingleMemberAnnotation()) {
					/*
					 * The annotation used inside the generated fragment
					 * contains one value. Existing value must be replaced by
					 * generated value.
					 */

					this.astr.replace(annotationInTheExistingFragment, annotationInTheGeneratedFragment,
							null);
				} else {
					if (annotationInTheGeneratedFragment.isNormalAnnotation()) {
						/*
						 * The annotation used inside the generated fragment
						 * contains several attributes. Existing value must be
						 * replaced by generated value.
						 */
						lw.replace(annotationInTheExistingFragment,
								annotationInTheGeneratedFragment, null);
					}
				}
			} else {
				/*
				 * The annotation used inside the existing fragment is a normal
				 * annotation (annotation with several attributes)
				 */
				if (annotationInTheGeneratedFragment.isSingleMemberAnnotation()) {
					/*
					 * What should be done in that case ? The existing
					 * annotation has been probably enhance => Let the existing
					 * content as before.
					 */
				} else {
					if (annotationInTheGeneratedFragment.isNormalAnnotation()) {
						/*
						 * The annotation used inside the generated fragment
						 * contains several attributes. Existing and generated
						 * annotations must be merged.
						 */
						this.mergeAnnotationAttribute(
								(NormalAnnotation) annotationInTheExistingFragment,
								(NormalAnnotation) annotationInTheGeneratedFragment);
					}
				}
			}
		}
	}

	/**
	 * Merge two annotations attribute
	 */
	@SuppressWarnings("unchecked")
	private void mergeAnnotationAttribute(NormalAnnotation annotationInTheExistingFragment,
			NormalAnnotation annotationInTheGeneratedFragment) {

		List<String> existingAnnotationAttributesToRemoved = new ArrayList<String>(1);
		List<MemberValuePair> existingAnnotationAttributes = annotationInTheExistingFragment
				.values();
		List<MemberValuePair> generatedAnnotationAttributes = annotationInTheGeneratedFragment
				.values();

		List<String> existingAnnotationAttributesNames = new ArrayList<String>(1);
		Map<String, MemberValuePair> generatedAnnotationAttributesMap = new HashMap<String, MemberValuePair>(
				1);

		for (MemberValuePair mvp : existingAnnotationAttributes) {
			existingAnnotationAttributesNames.add(mvp.getName().getFullyQualifiedName());
		}

		for (MemberValuePair mvp : generatedAnnotationAttributes) {
			generatedAnnotationAttributesMap.put(mvp.getName().getFullyQualifiedName(), mvp);
		}

		for (MemberValuePair mvp : existingAnnotationAttributes) {
			if (generatedAnnotationAttributesMap.containsKey(mvp.getName().getFullyQualifiedName())) {

				/*
				 * The attribute is present in the existing fragment AND The
				 * attribute is present in the generated fragment => The
				 * attribute present in the existing fragment must be removed
				 * and the attribute present in the generated fragment must be
				 * copied in the result
				 */
				ListRewrite lw = this.astr.getListRewrite(annotationInTheExistingFragment,
						NormalAnnotation.VALUES_PROPERTY);

				lw.replace(mvp, generatedAnnotationAttributesMap.get(mvp.getName()
						.getFullyQualifiedName()), null);

				existingAnnotationAttributesToRemoved.add(mvp.getName().getFullyQualifiedName());
			}
		}

		for (MemberValuePair mvp : generatedAnnotationAttributes) {
			if (!existingAnnotationAttributesNames.contains(mvp.getName().getFullyQualifiedName())
					&& !existingAnnotationAttributesToRemoved.contains(mvp.getName()
							.getFullyQualifiedName())) {
				/*
				 * The attribute is not present in the existing fragment AND The
				 * attribute is present in the generated fragment => The
				 * attribute present in the generated fragment must be copied in
				 * the result
				 */
				ListRewrite lw = this.astr.getListRewrite(annotationInTheExistingFragment,
						NormalAnnotation.VALUES_PROPERTY);

				lw.insertFirst(mvp, null);

			} else {
				// Nothing to do
			}
		}
	}
}
