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

import org.eclipse.gmt.modisco.java.AssertStatement;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.SwitchStatement;

/**
 * Models a composable instruction : wrapper of ExpressionStatement,
 * SwitchStatement or AssertStatement in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ComposableInstruction extends Instruction implements IComposable {

	/** The expression */
	private Expression expression;

	/**
	 * Constructor of composable instruction
	 * 
	 * @param statement
	 *            the expression statement.
	 */
	public ComposableInstruction(ExpressionStatement statement) {
		super(statement);
		this.expression = statement.getExpression();
	}

	/**
	 * Constructor of composable instruction
	 * 
	 * @param statement
	 *            the assert statement.
	 */
	public ComposableInstruction(AssertStatement statement) {
		super(statement);
		this.expression = statement.getExpression();
	}

	/**
	 * Constructor of composable instruction
	 * 
	 * @param statement
	 *            the switch statement.
	 */
	public ComposableInstruction(SwitchStatement statement) {
		super(statement);
		this.expression = statement.getExpression();
	}

	public Expression getExpression() {
		return this.expression;
	}

}
