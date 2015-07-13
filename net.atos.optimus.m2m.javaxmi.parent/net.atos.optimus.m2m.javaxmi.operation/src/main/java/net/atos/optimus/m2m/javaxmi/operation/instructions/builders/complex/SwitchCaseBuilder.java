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
package net.atos.optimus.m2m.javaxmi.operation.instructions.builders.complex;

import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SwitchCase;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create switch case of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SwitchCaseBuilder {

	/** The build switch case */
	private SwitchCase buildSwitchCase;

	/**
	 * Give a switch case builder
	 * 
	 * @return a new switch case builder.
	 */
	public static SwitchCaseBuilder builder() {
		return new SwitchCaseBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private SwitchCaseBuilder() {
		this.buildSwitchCase = JavaFactory.eINSTANCE.createSwitchCase();
	}

	/**
	 * Build a switch case of modisco model
	 * 
	 * @return a new switch case of modisco model.
	 */
	public SwitchCase build() {
		return this.buildSwitchCase;
	}

	/**
	 * Set the expression of the switch case under construction
	 * 
	 * @param expression
	 *            the expression of the switch case under construction.
	 * @return the builder.
	 */
	public SwitchCaseBuilder setExpression(Expression expression) {
		this.buildSwitchCase.setExpression(expression);
		return this;
	}

	/**
	 * Set the default state of the switch case under construction
	 * 
	 * @param isDefault
	 *            the default state of the switch case under construction.
	 * @return the builder.
	 */
	public SwitchCaseBuilder setDefault(boolean isDefault) {
		this.buildSwitchCase.setDefault(isDefault);
		return this;
	}

}
