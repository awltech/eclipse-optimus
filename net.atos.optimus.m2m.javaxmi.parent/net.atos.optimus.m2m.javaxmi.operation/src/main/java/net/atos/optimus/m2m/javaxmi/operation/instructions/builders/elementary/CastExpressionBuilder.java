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
package net.atos.optimus.m2m.javaxmi.operation.instructions.builders.elementary;

import org.eclipse.gmt.modisco.java.CastExpression;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create cast expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CastExpressionBuilder {

	/** The build cast expression */
	private CastExpression buildCastExpression;

	/**
	 * Give a cast expression builder
	 * 
	 * @return a new cast expression builder.
	 */
	public static CastExpressionBuilder builder() {
		return new CastExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private CastExpressionBuilder() {
		this.buildCastExpression = JavaFactory.eINSTANCE.createCastExpression();
	}

	/**
	 * Build a cast expression of modisco model
	 * 
	 * @return a new cast expression of modisco model.
	 */
	public CastExpression build() {
		return this.buildCastExpression;
	}

	/**
	 * Set the expression of the cast expression under construction
	 * 
	 * @param expression
	 *            the expression of the cast expression under construction.
	 * @return the builder.
	 */
	public CastExpressionBuilder setExpression(Expression expression) {
		this.buildCastExpression.setExpression(expression);
		return this;
	}

	/**
	 * Set the type of the cast expression under construction
	 * 
	 * @param type
	 *            the type of the cast expression under construction.
	 * @return the builder.
	 */
	public CastExpressionBuilder setType(TypeAccess type) {
		this.buildCastExpression.setType(type);
		return this;
	}

}
