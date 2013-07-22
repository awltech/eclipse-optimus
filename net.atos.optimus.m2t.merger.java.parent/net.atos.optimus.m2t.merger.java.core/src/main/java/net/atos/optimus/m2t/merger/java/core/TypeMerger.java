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

import static org.eclipse.jdt.core.dom.TypeDeclaration.BODY_DECLARATIONS_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.SUPERCLASS_TYPE_PROPERTY;
import static org.eclipse.jdt.core.dom.TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY;

import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.Document;


/**
 * Merge two types. A type is composed by a TypeDeclartion, a set of fields,
 * methods and inner types.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
class TypeMerger extends EnumMerger {

	/**
	 * TypeMerger constructor
	 * 
	 * @param jcm
	 *            A JavaCodeMerger instance
	 * @param astr
	 *            An ASTRewrite instance
	 * @param log
	 *            A Merger Logger instance
	 */
	public TypeMerger(JavaCodeMerger jcm, ASTRewrite astr) {
		super(jcm, astr);
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getBodyDeclarationsPropertyDescriptor() {
		return BODY_DECLARATIONS_PROPERTY;
	}

	@Override
	protected ChildListPropertyDescriptor getSuperInterfacesPropertyDescriptor() {
		return SUPER_INTERFACE_TYPES_PROPERTY;
	}

	/**
	 * Return super interfaces types defines on {@code atd} Abstract
	 * TypeDeclaration.
	 * 
	 * @param atd
	 *            An Abstract Type Declaration instance
	 * @return A super interfaces types defines on {@code atd} Abstract
	 *         TypeDeclaration.
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<Type> getSuperInterfacesTypes(AbstractTypeDeclaration atd) {
		return ((TypeDeclaration) atd).superInterfaceTypes();
	}

	/**
	 * This method launch merge process on sub fragments.
	 * 
	 * @param existing
	 *            The first code fragment containing sub fragments to merge
	 * @param generated
	 *            The second code fragment containing sub fragments to merge
	 */
	@Override
	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated, Document generatedDoc) {
		AbstractTypeDeclaration existingType = (AbstractTypeDeclaration) existing;
		AbstractTypeDeclaration generatedType = (AbstractTypeDeclaration) generated;

		/*
		 * Merge fields, methods and inner types.
		 */
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, FieldDeclaration.class);
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, MethodDeclaration.class);
		mergeBodyDeclarationsChildren(existingType, generatedType, generatedDoc, AbstractTypeDeclaration.class);
	}

	/**
	 * Merge type declaration
	 */
	@Override
	protected void mergeDeclaration(AbstractTypeDeclaration existingType, AbstractTypeDeclaration generatedType,
			Set<String> generatedAnnotations) {
		super.mergeDeclaration(existingType, generatedType, generatedAnnotations);

		// Extension is merged
		mergeExtension((TypeDeclaration) existingType, (TypeDeclaration) generatedType);
	}

	/**
	 * Merge extension from existingType {@code et} and generatedType {@code gt}
	 * to an AST Rewrite object instance.
	 * 
	 * @param et
	 *            The existing type
	 * @param generatedType
	 *            The generated type
	 */
	protected void mergeExtension(TypeDeclaration et, TypeDeclaration gt) {
		if (et.getSuperclassType() != null && gt.getSuperclassType() != null) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.TYPE_REPLACESUPERCLASS.value(JavaCodeHelper.getName(et.getSuperclassType()),
						JavaCodeHelper.getName(gt.getSuperclassType()), JavaCodeHelper.getName(et),
						JavaCodeHelper.getDescription(et)));

			astr.replace(et.getSuperclassType(), gt.getSuperclassType(), null);
		} else if (et.getSuperclassType() != null) {
			/* Nothing to do */
		} else if (gt.getSuperclassType() != null) {
			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.TYPE_ADDSUPERCLASS.value(JavaCodeHelper.getName(gt.getSuperclassType()),
						JavaCodeHelper.getName(et), JavaCodeHelper.getDescription(et)));

			astr.set(et, SUPERCLASS_TYPE_PROPERTY, gt.getSuperclassType(), null);
		}
	}
}
