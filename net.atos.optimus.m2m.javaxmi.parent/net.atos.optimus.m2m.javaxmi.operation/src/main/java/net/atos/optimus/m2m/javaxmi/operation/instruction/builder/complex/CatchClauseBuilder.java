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

import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create catch clause of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class CatchClauseBuilder {

	/** The build catch clause */
	private CatchClause buildCatchClause;

	/**
	 * Give catch clause builder
	 * 
	 * @return a new catch clause builder.
	 */
	public static CatchClauseBuilder builder() {
		return new CatchClauseBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private CatchClauseBuilder() {
		this.buildCatchClause = JavaFactory.eINSTANCE.createCatchClause();
	}

	/**
	 * Build a catch clause of modisco model
	 * 
	 * @return a new catch clause of modisco model.
	 */
	public CatchClause build() {
		return this.buildCatchClause;
	}

	/**
	 * Set the body of the catch clause under construction
	 * 
	 * @param body
	 *            the body of the catch clause under construction.
	 * @return the builder.
	 */
	public CatchClauseBuilder setBody(Block body) {
		this.buildCatchClause.setBody(body);
		return this;
	}

	/**
	 * Set the exception of the catch clause under construction
	 * 
	 * @param exception
	 *            the exception of the catch clause under construction.
	 * @return the builder.
	 */
	public CatchClauseBuilder setException(SingleVariableDeclaration exception) {
		this.buildCatchClause.setException(exception);
		return this;
	}

}
