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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.block;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create switch statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SwitchStatementBuilder {

	/** The build switch statement */
	private SwitchStatement buildSwitchStatement;

	/**
	 * Give a switch statement builder
	 * 
	 * @return a new switch statement builder.
	 */
	public static SwitchStatementBuilder builder() {
		return new SwitchStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private SwitchStatementBuilder() {
		this.buildSwitchStatement = JavaFactory.eINSTANCE.createSwitchStatement();
	}

	/**
	 * Build a switch statement of modisco model
	 * 
	 * @return a new switch statement of modisco model.
	 */
	public SwitchStatement build() {
		return this.buildSwitchStatement;
	}

	/**
	 * Set the expression of the switch statement under construction
	 * 
	 * @param expression
	 *            the expression of the switch statement under construction.
	 * @return the builder.
	 */
	public SwitchStatementBuilder setExpression(Expression expression) {
		this.buildSwitchStatement.setExpression(expression);
		return this;
	}

	/**
	 * Add a statements list to the switch statement under construction
	 * 
	 * @param statement
	 *            the statements list to add to the switch statement under
	 *            construction.
	 * @return the builder.
	 */
	public SwitchStatementBuilder addStatement(Statement... statements) {
		EList<Statement> statementsList = this.buildSwitchStatement.getStatements();
		for(Statement statement : statements){
			statementsList.add(statement);
		}
		return this;
	}
}
