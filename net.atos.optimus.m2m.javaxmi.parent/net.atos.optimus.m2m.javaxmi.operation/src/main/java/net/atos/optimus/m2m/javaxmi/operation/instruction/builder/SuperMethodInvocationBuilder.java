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
package net.atos.optimus.m2m.javaxmi.operation.instruction.builder;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.SuperMethodInvocation;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create super method invocation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SuperMethodInvocationBuilder {

	/** The build super method invocation */
	private SuperMethodInvocation buildSuperMethodInvocation;

	/**
	 * Give a super method invocation builder
	 * 
	 * @return a new super method invocation builder.
	 */
	public static SuperMethodInvocationBuilder builder() {
		return new SuperMethodInvocationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private SuperMethodInvocationBuilder() {
		this.buildSuperMethodInvocation = JavaFactory.eINSTANCE.createSuperMethodInvocation();
	}

	/**
	 * Build a super method invocation of modisco model
	 * 
	 * @return a new super method invocation of modisco model.
	 */
	public SuperMethodInvocation build() {
		return this.buildSuperMethodInvocation;
	}

	/**
	 * Set the method declaration of the super method invocation under
	 * construction
	 * 
	 * @param methodDeclaration
	 *            the method declaration of the super method invocation under
	 *            construction.
	 * @return the builder.
	 */
	public SuperMethodInvocationBuilder setMethod(MethodDeclaration methodDeclaration) {
		this.buildSuperMethodInvocation.setMethod(methodDeclaration);
		return this;
	}

	/**
	 * Add an arguments list to the super method invocation under construction
	 * 
	 * @param arguments
	 *            the arguments list to add to the super method invocation under
	 *            construction.
	 * @return the builder.
	 */
	public SuperMethodInvocationBuilder addArguments(Expression... arguments) {
		EList<Expression> argumentList = this.buildSuperMethodInvocation.getArguments();
		for (Expression argument : arguments) {
			argumentList.add(argument);
		}
		return this;
	}

}
