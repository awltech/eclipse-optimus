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
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create javadoc of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class JavadocBuilder {

	/** The build javadoc */
	private Javadoc buildJavadoc;

	/**
	 * Give a javadoc builder
	 * 
	 * @return a new javadoc builder.
	 */
	public static JavadocBuilder builder() {
		return new JavadocBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private JavadocBuilder() {
		this.buildJavadoc = JavaFactory.eINSTANCE.createJavadoc();
	}

	/**
	 * Build a javadoc of modisco model
	 * 
	 * @return a new javadoc of modisco model.
	 */
	public Javadoc build() {
		return this.buildJavadoc;
	}

	/**
	 * Set the prefix of parent state of the javadoc under construction
	 * 
	 * @param isPrefixOfParent
	 *            the prefix of parent state of the javadoc under construction.
	 * @return the builder.
	 */
	public JavadocBuilder setPrefixOfParent(boolean isPrefixOfParent) {
		this.buildJavadoc.setPrefixOfParent(isPrefixOfParent);
		return this;
	}

	/**
	 * Add a tags list to the javadoc under construction
	 * 
	 * @param tags
	 *            the tags list to add to the javadoc under construction.
	 * @return the builder.
	 */
	public JavadocBuilder addTags(TagElement... tags) {
		EList<TagElement> tagsList = this.buildJavadoc.getTags();
		for (TagElement tag : tags) {
			tagsList.add(tag);
		}
		return this;
	}

}
