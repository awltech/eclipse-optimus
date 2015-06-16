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
package net.atos.optimus.m2m.javaxmi.operation.instruction.block;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.CatchClause;
import org.eclipse.gmt.modisco.java.TryStatement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create try statement of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TryStatementBuilder {

	/** The build try statement */
	private TryStatement buildTryStatement;

	/**
	 * Give try statement builder
	 * 
	 * @return a new try statement builder.
	 */
	public static TryStatementBuilder builder() {
		return new TryStatementBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private TryStatementBuilder() {
		this.buildTryStatement = JavaFactory.eINSTANCE.createTryStatement();
	}

	/**
	 * Build a try statement of modisco model
	 * 
	 * @return a new try statement of modisco model.
	 */
	public TryStatement build() {
		return this.buildTryStatement;
	}

	/**
	 * Set the body of the try statement under construction
	 * 
	 * @param body
	 *            the body of the try statement under construction.
	 * @return the builder.
	 */
	public TryStatementBuilder setBody(Block body) {
		this.buildTryStatement.setBody(body);
		return this;
	}

	/**
	 * Set the finally block of the try statement under construction
	 * 
	 * @param finallyBlock
	 *            the finally block of the try statement under construction.
	 * @return the builder.
	 */
	public TryStatementBuilder setFinally(Block finallyBlock) {
		this.buildTryStatement.setFinally(finallyBlock);
		return this;
	}

	/**
	 * Add catch clauses list to the try statement under construction
	 * 
	 * @param catchClauses
	 *            the catch clauses list to add to the try statement under
	 *            construction.
	 * @return the builder.
	 */
	public TryStatementBuilder addCatchClauses(CatchClause... catchClauses) {
		EList<CatchClause> catchClausesList = this.buildTryStatement.getCatchClauses();
		for (CatchClause catchClause : catchClauses) {
			catchClausesList.add(catchClause);
		}
		return this;
	}

}
