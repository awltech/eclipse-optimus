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
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create block of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class BlockBuilder {

	/** The build block */
	private Block buildBlock;

	/**
	 * Give a new block builder
	 * 
	 * @return a new block builder.
	 */
	public static BlockBuilder builder() {
		return new BlockBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private BlockBuilder() {
		this.buildBlock = JavaFactory.eINSTANCE.createBlock();
	}

	/**
	 * Build a block of modisco model
	 * 
	 * @return a new block of modisco model.
	 */
	public Block build() {
		return this.buildBlock;
	}

	/**
	 * Add a list of statements to the block under construction
	 * 
	 * @param statements
	 *            the list of statements to add to the block under construction.
	 * @return the builder.
	 */
	public BlockBuilder addStatements(Statement... statements) {
		EList<Statement> statementsList = this.buildBlock.getStatements();
		for (Statement statement : statements) {
			statementsList.add(statement);
		}
		return this;
	}

}
