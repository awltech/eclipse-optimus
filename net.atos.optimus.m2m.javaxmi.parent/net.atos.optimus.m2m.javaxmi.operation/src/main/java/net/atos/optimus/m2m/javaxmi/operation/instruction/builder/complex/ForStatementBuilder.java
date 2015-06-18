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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.complex;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.ForStatement;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create for statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ForStatementBuilder {

	/** The build for statement */
	private ForStatement buildForStatement;

	/**
	 * Give a for statement builder
	 * 
	 * @return a new for statement builder.
	 */
	public static ForStatementBuilder builder() {
		return new ForStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ForStatementBuilder() {
		this.buildForStatement = JavaFactory.eINSTANCE.createForStatement();
	}

	/**
	 * Build a for statement of modisco model
	 * 
	 * @return a new for statement of modisco model.
	 */
	public ForStatement build() {
		return this.buildForStatement;
	}

	/**
	 * Set the body of the for statement under construction
	 * 
	 * @param body
	 *            the body of the for statement under construction.
	 * @return the builder.
	 */
	public ForStatementBuilder setBody(Statement body) {
		this.buildForStatement.setBody(body);
		return this;
	}

	/**
	 * Set the expression of the for statement under construction
	 * 
	 * @param expression
	 *            the expression of the for statement under construction.
	 * @return the builder.
	 */
	public ForStatementBuilder setExpression(Expression expression) {
		this.buildForStatement.setExpression(expression);
		return this;
	}

	/**
	 * Add initializer expressions to the for statement under construction
	 * 
	 * @param initializerExpressions
	 *            the initializer expressions list to add to the for statement
	 *            under construction.
	 * @return the builder.
	 */
	public ForStatementBuilder addInitializerExpression(Expression... initializerExpressions) {
		EList<Expression> expressionsList = this.buildForStatement.getInitializers();
		for (Expression expression : initializerExpressions) {
			expressionsList.add(expression);
		}
		return this;
	}

	/**
	 * Add updater expressions to the for statement under construction
	 * 
	 * @param updaterExpressions
	 *            the updater expressions list to add to the for statement under
	 *            construction.
	 * @return the builder.
	 */
	public ForStatementBuilder addUpdaterExpression(Expression... updaterExpressions) {
		EList<Expression> expressionsList = this.buildForStatement.getUpdaters();
		for (Expression expression : updaterExpressions) {
			expressionsList.add(expression);
		}
		return this;
	}

}
