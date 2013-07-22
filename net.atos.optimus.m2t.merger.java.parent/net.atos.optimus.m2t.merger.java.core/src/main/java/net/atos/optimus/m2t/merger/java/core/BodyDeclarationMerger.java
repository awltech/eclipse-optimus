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
import java.util.Set;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;


/**
 * Defines common methods used to perform merge operations (insert, update and
 * delete) on body declaration objects.
 * 
 * @author Architecture And Methodology
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
abstract class BodyDeclarationMerger implements FragmentMerger {

	protected ASTRewrite astr = null;

	protected MergingStrategy ms = null;

	/**
	 * Simple constructor with a logger as first parameter, an ASTRewrite as a
	 * second one.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 * @param astr
	 *            An ASTRewrite object
	 */
	public BodyDeclarationMerger(ASTRewrite astr) {
		this.astr = astr;
		ms = new DefaultMergingStrategy();
		ms.setASTRewrite(this.astr);
	}

	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		if (MergerLogger.enabled())
			MergerLogger.log(MergerLoggerMessages.BODYDECL_INSERT.value(
					JavaCodeHelper.getName(fragmentToInsert),
					JavaCodeHelper.getName(parent),
					JavaCodeHelper.getDescription(parent)));
	}

	/**
	 * This method merge a first code fragment {@code existing} with a second
	 * one {@code generated}.
	 * 
	 * @param existing
	 *            The first code fragment to merge
	 * @param generated
	 *            The second code fragment to merge
	 */
	public void merge(BodyDeclaration existing, BodyDeclaration generated) {
		this.merge(existing, generated, null);
	}

	/**
	 * This method merge a first code fragment {@code existing} with a second
	 * one {@code generated}.
	 * 
	 * @param existing
	 *            The first code fragment to merge
	 * @param generated
	 *            The second code fragment to merge
	 * @param preDefinedAnnotations
	 *            A set containing special annotations
	 */
	public void merge(BodyDeclaration existing, BodyDeclaration generated, Set<String> preDefinedAnnotations) {
		// Modifiers are merged
		mergeModifiers(existing, generated, astr);

		// Annotations are merged
		mergeAnnotations(existing, generated, preDefinedAnnotations, astr);
		mergeUniqueId(existing, generated, astr);
	}

	/**
	 * This method delete the code fragment {@code fragmentToRemove}.
	 * 
	 * @param fragmentToRemove
	 *            The code fragment to remove
	 */
	public void remove(BodyDeclaration fragmentToRemove) {
		if (MergerLogger.enabled())
			MergerLogger.log(MergerLoggerMessages.BODYDECL_REMOVE.value(JavaCodeHelper.getName(fragmentToRemove),
					JavaCodeHelper.getDescription(fragmentToRemove)));

		/*
		 * Call simply the method remove on astr ASTRewrite instance.
		 */
		astr.remove(fragmentToRemove, null);
	}

	/**
	 * This method is used to store MergingStrategy used to customize the merge
	 * process.
	 * 
	 * @param ms
	 *            A merging strategy instance
	 */
	public void setMergingStrategy(MergingStrategy ms) {
		this.ms = ms;
		this.ms.setASTRewrite(astr);
	}

	/**
	 * Return a ChildPropertyDescriptor instance associated with Javadoc
	 * property.
	 * 
	 * @return a ChildPropertyDescriptor instance associated with Javadoc
	 *         property
	 */
	protected abstract ChildPropertyDescriptor getJavadocPropertyDescriptor();

	/**
	 * Merge modifiers from existingFragment and generatedFragment to an AST
	 * Rewrite object instance.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	@SuppressWarnings("unchecked")
	protected void mergeModifiers(BodyDeclaration existingFragment, BodyDeclaration generatedFragment, ASTRewrite astr) {
		/*
		 * For each modifier of the existing type, check if this modifier is a
		 * modelizable modifier. If true, remove this one (replaced by generated
		 * modifier during the next step). If false, nothing to do, let the
		 * modifier in place.
		 */
		List<IExtendedModifier> existingFragmentModifiers = existingFragment.modifiers();

		for (IExtendedModifier currentModifier : existingFragmentModifiers) {
			if (isAModelizableModifier(existingFragment, currentModifier)) {
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.BODYDECL_REMOVEMODIFIER.value(currentModifier.toString(),
							JavaCodeHelper.getName(existingFragment), JavaCodeHelper.getDescription(existingFragment)));

				this.astr.remove((ASTNode) currentModifier, null);
			} else {
				if (currentModifier.isModifier()) {
					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.BODYDECL_LEAVEMODIFIER.value(currentModifier.toString(),
								JavaCodeHelper.getName(existingFragment),
								JavaCodeHelper.getDescription(existingFragment)));
				}
			}
		}

		/*
		 * Get a ListRewrite object used to modify modifiers in the existing
		 * code.
		 */
		ListRewrite lw = astr.getListRewrite(existingFragment, getModifiersPropertyDescriptor());

		/*
		 * For each modifier of the generated type, insert it into the merge
		 * result.
		 */
		List<IExtendedModifier> generatedFragmentModifiers = generatedFragment.modifiers();

		for (IExtendedModifier currentModifier : generatedFragmentModifiers) {
			if (isAModelizableModifier(existingFragment, currentModifier)) {
				if (MergerLogger.enabled())
					MergerLogger.log(MergerLoggerMessages.BODYDECL_ADDMODIFIER.value(currentModifier.toString(),
							JavaCodeHelper.getName(existingFragment), JavaCodeHelper.getDescription(existingFragment)));

				lw.insertLast((ASTNode) currentModifier, null);
			} else {
				if (currentModifier.isModifier()) {
					if (MergerLogger.enabled())
						MergerLogger.log(MergerLoggerMessages.BODYDECL_MODIFIERNOTADDED.value(currentModifier.toString(),
								JavaCodeHelper.getName(existingFragment),
								JavaCodeHelper.getDescription(existingFragment)));
				}
			}
		}
	}

	/**
	 * Merge annotations from existingFragment and generatedFragment to an AST
	 * Rewrite object instance.
	 * 
	 * @param existingFragment
	 *            The existing fragment
	 * @param generatedFragment
	 *            The generated fragment
	 * @param astr
	 *            The ASTRewrite object instance containing the merge result
	 */
	@SuppressWarnings("unchecked")
	protected void mergeAnnotations(BodyDeclaration existingFragment, BodyDeclaration generatedFragment,
			Set<String> preDefinedAnnotations, ASTRewrite astr) {

		List<String> annotationsNamesInExistingFragment = new ArrayList<String>(1);
		List<String> annotationsNamesInGeneratedFragment = new ArrayList<String>(1);
		List<IExtendedModifier> existingModifiers = existingFragment.modifiers();
		List<IExtendedModifier> generatedModifiers = generatedFragment.modifiers();

		Map<String, Annotation> generatedAnnotationMap = new HashMap<String, Annotation>(3);

		ListRewrite lw = astr.getListRewrite(existingFragment, getModifiersPropertyDescriptor());

		/*
		 * Parse all annotations used on the existing fragment and save theirs
		 * names in a list
		 */
		for (IExtendedModifier iem : existingModifiers) {
			if (iem.isAnnotation()) {
				annotationsNamesInExistingFragment.add(JavaCodeHelper.getSimpleName(((Annotation) iem).getTypeName()));
			}
		}

		/*
		 * Parse all annotations used on the generated fragment and - Save
		 * theirs names in a list - Save theirs annotations instances in a map
		 * using annotations names as key
		 */
		for (IExtendedModifier iem : generatedModifiers) {
			if (iem.isAnnotation()) {
				String annotationName = JavaCodeHelper.getSimpleName(((Annotation) iem).getTypeName());
				annotationsNamesInGeneratedFragment.add(annotationName);
				generatedAnnotationMap.put(annotationName, ((Annotation) iem));
			}
		}

		// For each annotation used on the generated fragment...
		for (IExtendedModifier iem : generatedModifiers) {
			if (iem.isAnnotation()) {
				if (!annotationsNamesInExistingFragment.contains(JavaCodeHelper.getSimpleName(((Annotation) iem)
						.getTypeName()))) {
					/*
					 * This annotation is present in the generated code AND This
					 * annotation is not present in the existing code => Add
					 * this annotation in the merge result
					 */
					ms.mergeStrategy(null, ((Annotation) iem), lw);
				}
			}
		}

		// For each annotation used on the existing fragment...
		for (IExtendedModifier iem : existingModifiers) {
			if (iem.isAnnotation()) {
				String annotationName = ((Annotation) iem).getTypeName().getFullyQualifiedName();

				Annotation annotationInTheExistingFragment = (Annotation) iem;
				String annotationNameInTheExistingFragment = JavaCodeHelper
						.getSimpleName(annotationInTheExistingFragment.getTypeName());

				if (!annotationsNamesInGeneratedFragment.contains(annotationNameInTheExistingFragment)
						&& preDefinedAnnotations.contains(annotationNameInTheExistingFragment)
						&& (!annotationName.equals(JavaCodeHelper.GENERATED_CLASSNAME) && !annotationName
								.equals(JavaCodeHelper.GENERATED_SIMPLECLASSNAME))) {
					/*
					 * This annotation is present into the existing fragment AND
					 * This annotation is not present into the generated
					 * fragment AND This annotation is a predefined one => This
					 * annotation must be removed - Use cases : Change
					 * association multiplicity (from OneToOne to OneToMany),...
					 */
					ms.mergeStrategy(annotationInTheExistingFragment, null, lw);
				}

				if (annotationsNamesInGeneratedFragment.contains(annotationNameInTheExistingFragment)) {
					/*
					 * This annotation is present into the existing fragment AND
					 * This annotation is present into the generated fragment =>
					 * Annotation attributes must be merged
					 */
					Annotation annotationInTheGeneratedFragment = generatedAnnotationMap
							.get(annotationNameInTheExistingFragment);
					ms.mergeStrategy(annotationInTheExistingFragment, annotationInTheGeneratedFragment, lw);
				}
			}
		}
	}

	/**
	 * Reset the Unique ID with a "possibly new" value
	 * 
	 * Only usefull if the user has removed the element and re-created it with
	 * the same name/signature. Otherwise, a future rename/refactor would fail
	 * because UniqueIds are not synch anymore
	 * 
	 * @param existingFragment
	 * @param generatedFragment
	 * @param astr
	 */
	private void mergeUniqueId(BodyDeclaration existingFragment, BodyDeclaration generatedFragment, ASTRewrite astr) {
		// TODO
		// String uniqueId =
		// ASTHelper.getUniqueIdFromGeneratedAnnotation(generatedFragment);
		//
		// Annotation generatedAnnotation =
		// ASTHelper.getAnnotation(JavaCodeMerger.GENERATED_SIMPLECLASSNAME,
		// existingFragment);
		// if (generatedAnnotation != null &&
		// generatedAnnotation.isNormalAnnotation()) {
		// GeneratedAnnotationHelper.setGeneratedAnnotationUniqueId((NormalAnnotation)
		// generatedAnnotation);
		// astr.
		// }

	}

	/**
	 * Return a ChildListPropertyDescriptor instance associated with modifiers
	 * property.
	 * 
	 * @return a ChildListPropertyDescriptor instance associated with modifiers
	 *         property
	 */
	protected abstract ChildListPropertyDescriptor getModifiersPropertyDescriptor();

	/**
	 * Return true if {@code modifier} is a modelizable modifier. A modelizable
	 * modifier is one of abstract, static, private, protected, public, final or
	 * transient. Other modifiers are not modelizable (it's technical modifiers
	 * like synchronized,...).
	 * 
	 * @param modifier
	 *            The modifier to test
	 * @return true if {@code modifier} is a modelizable modifier, false
	 *         otherwise.
	 */
	protected boolean isAModelizableModifier(BodyDeclaration existingFragment, IExtendedModifier modifier) {
		/* An annotation modifier is not processed by this method. */
		if (modifier.isAnnotation()) {
			return false;
		}

		Modifier m = (Modifier) modifier;
		return (m.isStatic() || m.isAbstract() || m.isPrivate() || m.isProtected() || m.isPublic() || m.isTransient() || m
				.isFinal());
	}

	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated, Document generatedDoc) {
		// Nothing to do by default
	}

	/**
	 * Merge types
	 */
	protected void mergeTypes(Type existingType, Type generatedType) {
		boolean typeMustBeReplaced = true;

		if (existingType.isParameterizedType() && !generatedType.isParameterizedType()) {
			typeMustBeReplaced = (!JavaCodeHelper.areTypesEquals(((ParameterizedType) existingType).getType(),
					generatedType));
		}

		if (typeMustBeReplaced) {
			astr.replace(existingType, generatedType, null);
		}
	}
}
