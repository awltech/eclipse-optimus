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
package net.atos.optimus.m2m.javaxmi.operation.accesses;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.FieldAccess;
import org.eclipse.gmt.modisco.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create field access of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class FieldAccessBuilder {

	/** The build field access */
	private FieldAccess buildFieldAccess;

	/**
	 * Give a new this expression builder
	 * 
	 * @return a new field access builder.
	 */
	public static FieldAccessBuilder builder() {
		return new FieldAccessBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private FieldAccessBuilder() {
		this.buildFieldAccess = JavaFactory.eINSTANCE.createFieldAccess();
	}

	/**
	 * Build a field access of modisco model
	 * 
	 * @return a new field access of modisco model.
	 */
	public FieldAccess build() {
		return this.buildFieldAccess;
	}

	/**
	 * Set the single variable access of the field access under construction
	 * 
	 * @param singleVariableAccess
	 *            the single variable access of the field access under
	 *            construction.
	 * @return the builder.
	 */
	public FieldAccessBuilder setField(SingleVariableAccess singleVariableAccess) {
		this.buildFieldAccess.setField(singleVariableAccess);
		return this;
	}

	/**
	 * Set the expression of the field access under construction
	 * 
	 * @param expression
	 *            the expression of the field access under construction.
	 * @return the builder.
	 */
	public FieldAccessBuilder setExpression(Expression expression) {
		this.buildFieldAccess.setExpression(expression);
		return this;
	}

}
