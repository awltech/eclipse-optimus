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
package net.atos.optimus.m2m.javaxmi.operation.comments;

import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create line comment of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class LineCommentBuilder {

	/** The build line comment */
	private LineComment buildLineComment;

	/**
	 * Give a new line comment builder
	 * 
	 * @return a new line comment builder.
	 */
	public static LineCommentBuilder builder() {
		return new LineCommentBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private LineCommentBuilder() {
		this.buildLineComment = JavaFactory.eINSTANCE.createLineComment();
	}

	/**
	 * Build a line comment of modisco model
	 * 
	 * @return a new line comment of modisco model.
	 */
	public LineComment build() {
		return this.buildLineComment;
	}

	/**
	 * Set the content of the line comment under construction
	 * 
	 * @param content
	 *            the content of the line comment under construction.
	 * @return the builder.
	 */
	public LineCommentBuilder setContent(String content) {
		this.buildLineComment.setContent(content);
		return this;
	}

	/**
	 * Set the prefix of parent state of the line comment under construction
	 * 
	 * @param isPrefixOfParent
	 *            the prefix of parent state of the line comment under
	 *            construction.
	 * @return the builder.
	 */
	public LineCommentBuilder setPrefixOfParent(boolean isPrefixOfParent) {
		this.buildLineComment.setPrefixOfParent(isPrefixOfParent);
		return this;
	}

}
