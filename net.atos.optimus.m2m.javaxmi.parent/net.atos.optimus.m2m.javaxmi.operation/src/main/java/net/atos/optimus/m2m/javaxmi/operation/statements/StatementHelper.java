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
package net.atos.optimus.m2m.javaxmi.operation.statements;

import net.atos.optimus.m2m.javaxmi.operation.comments.LineCommentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.fields.FieldAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.AssignmentBuilder;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableAccessHelper;

import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.LineComment;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.Statement;

/**
 * The purpose of such class is to help with the statement configuration
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class StatementHelper {

	/**
	 * Create a statement to set a field
	 * 
	 * @param fieldName
	 *            the name of the field to set.
	 * @param variableName
	 *            the name of the variable containing the new value of the
	 *            field.
	 * @return the created statement setting the specified field with the
	 *         specified variable.
	 */
	public static ExpressionStatement createSetFieldStatement(String fieldName, String variableName) {
		FieldAccess field = FieldAccessHelper.createFieldAccess(fieldName);
		SingleVariableAccess variable = VariableAccessHelper.createVariableAccess(variableName);
		Expression setExpression = AssignmentBuilder.builder().setOperator(AssignmentKind.ASSIGN)
				.setLeftHandSide(field).setRightHandSide(variable).build();
		return ExpressionStatementBuilder.builder().setExpression(setExpression).build();
	}

	/**
	 * Add a comment to a statement
	 * 
	 * @param statement
	 *            the statement which we add a comment.
	 * @param commentText
	 *            the text of the comment.
	 * @return the commented statement.
	 */
	public static Statement addComment(Statement statement, String commentText) {
		return StatementHelper.addComment(statement, commentText, true);
	}

	/**
	 * Add a comment to a statement
	 * 
	 * @param statement
	 *            the statement which we add a comment.
	 * @param commentText
	 *            the text of the comment.
	 * @param prefixOfParent
	 *            the prefix of parent state of the comment.
	 * @return the commented statement.
	 */
	public static Statement addComment(Statement statement, String commentText, boolean prefixOfParent) {
		LineComment comment = LineCommentBuilder.builder().setContent(commentText).setPrefixOfParent(prefixOfParent)
				.build();
		statement.getComments().add(comment);
		return statement;
	}

}
