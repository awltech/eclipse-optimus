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
package net.atos.optimus.m2m.javaxmi.operation.instructions.builders;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create super constructor invocation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SuperConstructorInvocationBuilder {

	/** The build super constructor invocation */
	private SuperConstructorInvocation buildSuperConstructorInvocation;

	/**
	 * Give a super constructor invocation builder
	 * 
	 * @return a new super constructor invocation builder.
	 */
	public static SuperConstructorInvocationBuilder builder() {
		return new SuperConstructorInvocationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private SuperConstructorInvocationBuilder() {
		this.buildSuperConstructorInvocation = JavaFactory.eINSTANCE.createSuperConstructorInvocation();
	}

	/**
	 * Build a super constructor invocation of modisco model
	 * 
	 * @return a new super constructor invocation of modisco model.
	 */
	public SuperConstructorInvocation build() {
		return this.buildSuperConstructorInvocation;
	}

	/**
	 * Add an arguments list to the super constructor invocation under
	 * construction
	 * 
	 * @param arguments
	 *            the arguments list to add to the super constructor invocation
	 *            under construction.
	 * @return the builder.
	 */
	public SuperConstructorInvocationBuilder addArguments(Expression... arguments) {
		EList<Expression> argumentList = this.buildSuperConstructorInvocation.getArguments();
		for (Expression argument : arguments) {
			argumentList.add(argument);
		}
		return this;
	}

}
