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
package net.atos.optimus.m2m.javaxmi.operation.variables;

import org.eclipse.gmt.modisco.java.Assignment;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.Expression;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create assignment of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AssignmentBuilder {

	/** The build assignment */
	private Assignment buildAssignment;

	/**
	 * Give an assignment unit builder
	 * 
	 * @return a new assignment builder.
	 */
	public static AssignmentBuilder builder() {
		return new AssignmentBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private AssignmentBuilder() {
		this.buildAssignment = JavaFactory.eINSTANCE.createAssignment();
	}

	/**
	 * Build an assignment of modisco model
	 * 
	 * @return a new assignment of modisco model.
	 */
	public Assignment build() {
		return this.buildAssignment;
	}

	/**
	 * Set the operator of the assignment under construction
	 * 
	 * @param operator
	 *            the operator of the assignment under construction.
	 * @return the builder.
	 */
	public AssignmentBuilder setOperator(AssignmentKind operator) {
		this.buildAssignment.setOperator(operator);
		return this;
	}

	/**
	 * Set the left hand side of the assignment under construction
	 * 
	 * @param expression
	 *            the left hand side of the assignment under construction.
	 * @return the builder.
	 */
	public AssignmentBuilder setLeftHandSide(Expression expression) {
		this.buildAssignment.setLeftHandSide(expression);
		return this;
	}

	/**
	 * Set the right hand side of the assignment under construction
	 * 
	 * @param expression
	 *            the right hand side of the assignment under construction.
	 * @return the builder.
	 */
	public AssignmentBuilder setRightHandSide(Expression expression) {
		this.buildAssignment.setRightHandSide(expression);
		return this;
	}

}
