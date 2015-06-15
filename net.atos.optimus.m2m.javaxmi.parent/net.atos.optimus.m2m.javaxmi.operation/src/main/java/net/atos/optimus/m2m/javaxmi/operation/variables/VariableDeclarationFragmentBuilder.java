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

import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create variable declaration fragment of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class VariableDeclarationFragmentBuilder {

	/** The build variable declaration fragment */
	private VariableDeclarationFragment buildVariableDeclarationFragment;

	/**
	 * Give a new variable declaration fragment builder
	 * 
	 * @return a new variable declaration fragment builder.
	 */
	public static VariableDeclarationFragmentBuilder builder() {
		return new VariableDeclarationFragmentBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private VariableDeclarationFragmentBuilder() {
		this.buildVariableDeclarationFragment = JavaFactory.eINSTANCE.createVariableDeclarationFragment();
	}

	/**
	 * Build a variable declaration fragment of modisco model
	 * 
	 * @return a new variable declaration fragment of modisco model.
	 */
	public VariableDeclarationFragment build() {
		return this.buildVariableDeclarationFragment;
	}

	/**
	 * Set the name of the variable declaration fragment under construction
	 * 
	 * @param name
	 *            the name of the variable declaration fragment under
	 *            construction.
	 * @return the builder.
	 */
	public VariableDeclarationFragmentBuilder setName(String name) {
		this.buildVariableDeclarationFragment.setName(name);
		return this;
	}
	
	/**
	 * Set the proxy state of the variable declaration fragment under construction
	 * 
	 * @param isProxy
	 *            the proxy state of the variable declaration fragment under
	 *            construction.
	 * @return the builder.
	 */
	public VariableDeclarationFragmentBuilder setProxy(boolean isProxy) {
		this.buildVariableDeclarationFragment.setProxy(isProxy);
		return this;
	}

}
