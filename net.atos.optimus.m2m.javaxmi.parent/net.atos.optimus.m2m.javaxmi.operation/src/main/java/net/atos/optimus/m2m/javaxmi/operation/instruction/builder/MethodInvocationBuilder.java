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
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create method invocation of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MethodInvocationBuilder {

	/** The build method invocation */
	private MethodInvocation buildMethodInvocation;

	/**
	 * Give a new method invocation builder
	 * 
	 * @return a new method invocation builder.
	 */
	public static MethodInvocationBuilder builder() {
		return new MethodInvocationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private MethodInvocationBuilder() {
		this.buildMethodInvocation = JavaFactory.eINSTANCE.createMethodInvocation();
	}

	/**
	 * Build a method invocation of modisco model
	 * 
	 * @return a new method invocation of modisco model.
	 */
	public MethodInvocation build() {
		return this.buildMethodInvocation;
	}

	/**
	 * Set the expression of the method invocation under construction
	 * 
	 * @param expression
	 *            the expression of the method invocation under construction.
	 * @return the builder.
	 */
	public MethodInvocationBuilder setExpression(Expression expression) {
		this.buildMethodInvocation.setExpression(expression);
		return this;
	}

	/**
	 * Set the method declaration of the method invocation under construction
	 * 
	 * @param methodDeclaration
	 *            the method declaration of the method invocation under
	 *            construction.
	 * @return the builder.
	 */
	public MethodInvocationBuilder setMethod(MethodDeclaration methodDeclaration) {
		this.buildMethodInvocation.setMethod(methodDeclaration);
		return this;
	}

	/**
	 * Add an arguments list to the method invocation under construction
	 * 
	 * @param arguments
	 *            the arguments list to add to the method invocation under
	 *            construction.
	 * @return the builder.
	 */
	public MethodInvocationBuilder addArguments(Expression... arguments) {
		EList<Expression> argumentList = this.buildMethodInvocation.getArguments();
		for (Expression argument : arguments) {
			argumentList.add(argument);
		}
		return this;
	}

}
