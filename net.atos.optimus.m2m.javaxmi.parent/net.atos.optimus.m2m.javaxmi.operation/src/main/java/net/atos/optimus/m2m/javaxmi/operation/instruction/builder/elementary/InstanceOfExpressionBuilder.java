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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder.elementary;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create instanceof expression of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class InstanceOfExpressionBuilder {

	/** The build instanceof expression */
	private InstanceofExpression buildInstanceofExpression;

	/**
	 * Give an instanceof expression builder
	 * 
	 * @return a new instanceof expression builder.
	 */
	public static InstanceOfExpressionBuilder builder() {
		return new InstanceOfExpressionBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private InstanceOfExpressionBuilder() {
		this.buildInstanceofExpression = JavaFactory.eINSTANCE.createInstanceofExpression();
	}

	/**
	 * Build an instanceof expression of modisco model
	 * 
	 * @return a new instanceof expression of modisco model.
	 */
	public InstanceofExpression build() {
		return this.buildInstanceofExpression;
	}

	/**
	 * Set the left operand of the instanceof expression under construction
	 * 
	 * @param leftOperand
	 *            the left operand of the instanceof expression under
	 *            construction.
	 * @return the builder.
	 */
	public InstanceOfExpressionBuilder setLeftOperand(Expression leftOperand) {
		this.buildInstanceofExpression.setLeftOperand(leftOperand);
		return this;
	}

	/**
	 * Set the right operand of the instanceof expression under construction
	 * 
	 * @param rightOperand
	 *            the right operand of the instanceof expression under
	 *            construction.
	 * @return the builder.
	 */
	public InstanceOfExpressionBuilder setRightOperand(TypeAccess rightOperand) {
		this.buildInstanceofExpression.setRightOperand(rightOperand);
		return this;
	}

}
