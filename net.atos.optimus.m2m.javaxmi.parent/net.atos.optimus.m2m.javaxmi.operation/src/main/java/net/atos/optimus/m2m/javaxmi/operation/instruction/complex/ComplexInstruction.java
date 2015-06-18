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
package net.atos.optimus.m2m.javaxmi.operation.instruction.complex;

import net.atos.optimus.m2m.javaxmi.operation.comments.LineCommentBuilder;

import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * Models a complex instruction : wrapper of Statement in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ComplexInstruction implements IComplexInstruction {

	/** The statement */
	private Statement statement;

	/**
	 * Constructor of complex instruction
	 * 
	 * @param statement
	 *            the statement.
	 */
	public ComplexInstruction(Statement statement) {
		this.statement = statement;
	}

	public Statement getStatement() {
		return this.statement;
	}

	/**
	 * Add a comment to the current complex instruction and set to true the prefix of
	 * parent state
	 * 
	 * @param commentText
	 *            the text of the comment.
	 * @return the commented instruction.
	 */
	public ComplexInstruction addComment(String commentText) {
		return this.addComment(commentText, true);
	}

	/**
	 * Add a comment to the current complex instruction
	 * 
	 * @param commentText
	 *            the text of the comment.
	 * @param prefixOfParent
	 *            the prefix of parent state of the comment.
	 * @return the commented instruction.
	 */
	public ComplexInstruction addComment(String commentText, boolean prefixOfParent) {
		LineComment comment = LineCommentBuilder.builder().setContent(commentText).setPrefixOfParent(prefixOfParent)
				.build();
		this.getStatement().getComments().add(comment);
		return this;
	}

}
