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

import static org.eclipse.jdt.core.dom.FieldDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.FieldDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.VariableDeclarationFragment.INITIALIZER_PROPERTY;

import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLogger;
import net.atos.optimus.m2t.merger.java.core.internal.MergerLoggerMessages;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;


/**
 * Merge two FieldDeclaration instance. A FieldDeclaration is composed by a
 * javadoc, modifiers, type and initialization.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
class FieldDeclarationMerger extends BodyDeclarationMerger {

	/**
	 * Simple constructor with a logger as first parameter, An ASTRewrite
	 * instance as second one.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 * @param astr
	 *            An ASTRewrite object instance
	 */
	public FieldDeclarationMerger(ASTRewrite astr) {
		super(astr);
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
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		FieldDeclaration lastField = null;
		AbstractTypeDeclaration parentType = (AbstractTypeDeclaration) parent;

		/*
		 * Read all fields of the type declaration (used to insert this field at
		 * the right place)
		 */
		List<FieldDeclaration> existingFields = JavaCodeHelper.getTypedChildren(parentType, FieldDeclaration.class);

		// Save the last field if a field already exist
		if (existingFields.size() > 0) {
			lastField = existingFields.get(existingFields.size() - 1);
		}

		ListRewrite lw = this.astr.getListRewrite(parentType,
				JavaCodeHelper.getBodyDeclarationProperty(parentType.getClass()));

		// Insert this field...
		if (lastField == null) {
			/*
			 * ... at the beginning of the type if this is the first field
			 */
			lw.insertFirst(fragmentToInsert, null);
		} else {
			// ... after the previous field if a field already exist
			lw.insertAfter(fragmentToInsert, lastField, null);
		}
	}

	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated, Set<String> generatedAnnotations) {
		super.merge(existing, generated, generatedAnnotations);

		FieldDeclaration existingField = (FieldDeclaration) existing;
		FieldDeclaration generatedField = (FieldDeclaration) generated;

		// Merge types
		this.mergeTypes(existingField.getType(), generatedField.getType());

		// Merge initializations
		this.mergeInitialization(existingField, generatedField);

		// Rename in case of REFACTORING
		mergeFieldNames(existingField, generatedField);
	}

	private void mergeFieldNames(FieldDeclaration existingField, FieldDeclaration generatedField) {
		// Updates the name in case of refactoring:
		astr.replace((VariableDeclarationFragment) existingField.fragments().get(0),
				(VariableDeclarationFragment) generatedField.fragments().get(0), null);
	}

	/**
	 * Merge initialization from existingField and generatedField to an AST
	 * Rewrite object instance.
	 */
	private void mergeInitialization(FieldDeclaration existingField, FieldDeclaration generatedField) {
		VariableDeclarationFragment evdf = null;
		VariableDeclarationFragment gvdf = null;

		/*
		 * Initializing expression is replaced. WARNING : This code is only able
		 * to work with one field in a FieldDeclaration instance, several fields
		 * in one FieldDeclaration is not supported by this implementation.
		 */
		evdf = (VariableDeclarationFragment) existingField.fragments().get(0);
		gvdf = (VariableDeclarationFragment) generatedField.fragments().get(0);

		if (evdf.getInitializer() == null && gvdf.getInitializer() == null) {
			/*
			 * If existing initializer is null and generated initializer is
			 * null, nothing to do
			 */
			return;
		}

		if (evdf.getInitializer() != null && gvdf.getInitializer() != null) {
			/*
			 * If existing and generated initializers are not null, replace
			 * existing by generated
			 */

			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.FIELDDECL_INITIALIZER.value(evdf.getInitializer().toString(), gvdf
						.getInitializer().toString(), JavaCodeHelper.getName(existingField), JavaCodeHelper
						.getDescription(existingField)));

			this.astr.replace(evdf.getInitializer(), gvdf.getInitializer(), null);
		} else if (evdf.getInitializer() != null) {
			/*
			 * If existing initializer is not null and generated initializer is
			 * null, nothing to do
			 */
		} else {
			/*
			 * If existing initializer is null and generated initializer is not
			 * null, add generated in result
			 */

			if (MergerLogger.enabled())
				MergerLogger.log(MergerLoggerMessages.FIELDDECL_INITIALIZERADD.value(gvdf.getInitializer().toString(),
						JavaCodeHelper.getName(existingField), JavaCodeHelper.getDescription(existingField)));

			this.astr.set(evdf, INITIALIZER_PROPERTY, gvdf.getInitializer(), null);
		}
	}
}
