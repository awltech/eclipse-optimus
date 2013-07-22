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

import static org.eclipse.jdt.core.dom.EnumConstantDeclaration.ARGUMENTS_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumConstantDeclaration.JAVADOC_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumConstantDeclaration.MODIFIERS2_PROPERTY;
import static org.eclipse.jdt.core.dom.EnumDeclaration.ENUM_CONSTANTS_PROPERTY;

import java.util.List;
import java.util.Set;

import net.atos.optimus.common.tools.jdt.ASTHelper;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

/**
 * Merge two EnumConstantDeclaration instance.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
public class EnumConstantMerger extends BodyDeclarationMerger {

	/**
	 * Simple constructor with a logger as first parameter, an ASTRewrite object
	 * as second one.
	 * 
	 * @param log
	 *            A Merger Logger instance
	 * @param astr
	 *            An ASTRewrite instance<
	 */
	public EnumConstantMerger(ASTRewrite astr) {
		super(astr);
	}

	@Override
	protected ChildListPropertyDescriptor getModifiersPropertyDescriptor() {
		return MODIFIERS2_PROPERTY;
	}

	@Override
	protected ChildPropertyDescriptor getJavadocPropertyDescriptor() {
		return JAVADOC_PROPERTY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert) {
		super.insert(parent, fragmentToInsert);

		EnumConstantDeclaration lastConstant = null;
		EnumDeclaration parentType = (EnumDeclaration) parent;

		/*
		 * Read all constant of the type enumeration (used to insert this
		 * constant at the right place)
		 */
		List<EnumConstantDeclaration> existingConstants = parentType
				.enumConstants();

		// Save the last field if a field already exist
		if (existingConstants.size() > 0) {
			lastConstant = existingConstants.get(existingConstants.size() - 1);
		}

		ListRewrite lw = this.astr.getListRewrite(parentType,
				ENUM_CONSTANTS_PROPERTY);

		// Insert this constant...
		if (lastConstant == null) {
			/*
			 * ... at the beginning of the enumeration if this is the first
			 * constant
			 */
			lw.insertFirst(fragmentToInsert, null);
		} else {
			// ... after the previous constant if a constant already exist
			lw.insertAfter(fragmentToInsert, lastConstant, null);
		}
	}

	@Override
	public void merge(BodyDeclaration existing, BodyDeclaration generated,
			Set<String> preDefinedAnnotations) {
		super.merge(existing, generated, preDefinedAnnotations);

		// HashCode value in the existing source code annotation
		int existingHashCode = 0;

		// HashCode computed from the parameters of the constant
		int computedHashCode = 0;

		boolean shouldUpdate = true;

		try {
			existingHashCode = ASTHelper
					.getHashCodeFromGeneratedAnnotation(existing);
			computedHashCode = ASTHelper
					.enumConstantHashCode((EnumConstantDeclaration) existing);

			shouldUpdate = existingHashCode == computedHashCode;

		} catch (IllegalArgumentException iae) {
			// Occurs because existingHashCode does not exist. In this case, we
			// update
		} catch (UnsupportedOperationException uoe) {
			// Occurs because not @Generated annotation on parent element. In
			// this case, we don't update
			shouldUpdate = false;
		}

		if (shouldUpdate) {

			/*
			 * Merge enumeration constant arguments using the following
			 * mechanism : Remove all the existing ones and put all the
			 * generated ones. If the user added an argument, this one will be
			 * deleted. If he want to do this, he needs to remove the @Generated
			 * annotation.
			 */

			ListRewrite lw = this.astr.getListRewrite(existing,
					ARGUMENTS_PROPERTY);

			// Remove all existing enumeration constants arguments
			for (Object o : ((EnumConstantDeclaration) existing).arguments()) {
				lw.remove((Expression) o, null);
			}

			// Put all enumeration constants arguments from generated code
			for (Object o : ((EnumConstantDeclaration) generated).arguments()) {
				lw.insertLast((Expression) o, null);
			}
		}
	}
}
