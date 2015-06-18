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
package net.atos.optimus.m2m.javaxmi.operation.instruction;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.comments.LineCommentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.ReturnStatementBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary.CastExpressionBuilder;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.ComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.complex.IComplexInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstruction;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.IElementaryInstruction;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;

/**
 * Models an instruction : wrapper of ExpressionStatement in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Instruction implements IElementaryInstruction, IComplexInstruction {

	/** The statement */
	private Statement statement;

	/** The expression */
	private Expression expression;

	/**
	 * Constructor of instruction
	 * 
	 * @param statement
	 *            the expression statement.
	 */
	public Instruction(ExpressionStatement statement) {
		this.statement = statement;
		this.expression = statement.getExpression();
	}

	/**
	 * Constructor of instruction
	 * 
	 * @param statement
	 *            the expression statement.
	 */
	public Instruction(SuperConstructorInvocation statement) {
		this.statement = statement;
		this.expression = statement.getExpression();
	}

	public Statement getStatement() {
		return this.statement;
	}

	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public ComplexInstruction convertToReturnInstruction() {
		return new ComplexInstruction(ReturnStatementBuilder.builder().setExpression(this.getExpression()).build());
	}

	@Override
	public ElementaryInstruction convertToCastInstruction(String castName) {
		return new ElementaryInstruction(CastExpressionBuilder.builder().setExpression(this.getExpression())
				.setType(TypeAccessHelper.createTypeAccess(castName)).build());
	}

	/**
	 * Add a comment to the current instruction and set to true the prefix of
	 * parent state
	 * 
	 * @param commentText
	 *            the text of the comment.
	 * @return the commented instruction.
	 */
	public Instruction addComment(String commentText) {
		return this.addComment(commentText, true);
	}

	/**
	 * Add a comment to the current instruction
	 * 
	 * @param commentText
	 *            the text of the comment.
	 * @param prefixOfParent
	 *            the prefix of parent state of the comment.
	 * @return the commented instruction.
	 */
	public Instruction addComment(String commentText, boolean prefixOfParent) {
		LineComment comment = LineCommentBuilder.builder().setContent(commentText).setPrefixOfParent(prefixOfParent)
				.build();
		this.getStatement().getComments().add(comment);
		return this;
	}

}
