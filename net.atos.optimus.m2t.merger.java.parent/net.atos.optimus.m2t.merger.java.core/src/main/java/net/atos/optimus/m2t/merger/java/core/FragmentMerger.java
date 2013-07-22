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

import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jface.text.Document;

/**
 * Defines common methods used to perform merge operations (insert, update and
 * delete).
 *
 * @author Architecture And Methodology
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 */
interface FragmentMerger {

	/**
	 * This method insert a new code fragment {@code fragmentToInsert} in a
	 * parent fragment {@code parent} (Exemples : insert a field in a type,
	 * insert a type in a compilation unit, ...).
	 *
	 * @param parent
	 *            The parent fragment in wich a new fragment will be inserted
	 * @param fragmentToInsert
	 *            The fragment to insert
	 */
	public void insert(ASTNode parent, BodyDeclaration fragmentToInsert);

	/**
	 * This method delete the code fragment {@code fragmentToRemove}.
	 *
	 * @param fragmentToRemove
	 *            The code fragment to remove
	 */
	public void remove(BodyDeclaration fragmentToRemove);

	/**
	 * This method merge a first code fragment {@code existing} with a second
	 * one {@code generated}.
	 *
	 * @param existing
	 *            The first code fragment to merge
	 * @param generated
	 *            The second code fragment to merge
	 * @param generatedAnnotations
	 * 			  A special annotations set
	 */
	public void merge(BodyDeclaration existing, BodyDeclaration generated,
			Set<String> generatedAnnotations);

	/**
	 * This method launch merge process on sub fragments.
	 *
	 * @param existing
	 *            The first code fragment containing sub fragments to merge
	 * @param generated
	 *            The second code fragment containing sub fragments to merge
	 * @param generatedDoc
	 * 	          The document containing the generated code
	 */
	public void mergeSubFragments(BodyDeclaration existing, BodyDeclaration generated, Document generatedDoc);
}
