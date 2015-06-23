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
package net.atos.optimus.m2m.javaxmi.operation.javadoc;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create tag element of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TagElementBuilder {

	/** The build tag element */
	private TagElement buildTagElement;

	/**
	 * Give a tag element builder
	 * 
	 * @return a new tag element builder.
	 */
	public static TagElementBuilder builder() {
		return new TagElementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private TagElementBuilder() {
		this.buildTagElement = JavaFactory.eINSTANCE.createTagElement();
	}

	/**
	 * Build a tag element of modisco model
	 * 
	 * @return a new tag element of modisco model.
	 */
	public TagElement build() {
		return this.buildTagElement;
	}

	/**
	 * Set the name of the tag element under construction
	 * 
	 * @param tagName
	 *            the name of the tag element under construction.
	 * @return the builder.
	 */
	public TagElementBuilder setTagName(String tagName) {
		this.buildTagElement.setTagName(tagName);
		return this;
	}

	/**
	 * Add a fragments list to the tag element under construction
	 * 
	 * @param fragments
	 *            the fragments list to add to the tag element under
	 *            construction.
	 * @return the builder.
	 */
	public TagElementBuilder addFragments(ASTNode... fragments) {
		EList<ASTNode> fragmentsList = this.buildTagElement.getFragments();
		for (ASTNode fragment : fragments) {
			fragmentsList.add(fragment);
		}
		return this;
	}

}
