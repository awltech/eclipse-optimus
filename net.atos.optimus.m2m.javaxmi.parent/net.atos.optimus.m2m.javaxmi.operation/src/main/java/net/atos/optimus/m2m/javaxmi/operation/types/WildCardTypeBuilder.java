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
package net.atos.optimus.m2m.javaxmi.operation.types;

import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.WildCardType;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create wild card type of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class WildCardTypeBuilder {

	/** The build wild card type */
	private WildCardType buildWildCardType;

	/**
	 * Give a wild card type builder
	 * 
	 * @return a new wild card type builder.
	 */
	public static WildCardTypeBuilder builder() {
		return new WildCardTypeBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private WildCardTypeBuilder() {
		this.buildWildCardType = JavaFactory.eINSTANCE.createWildCardType();
	}

	/**
	 * Build a wild card type of modisco model
	 * 
	 * @return a new wild card type of modisco model.
	 */
	public WildCardType build() {
		return this.buildWildCardType;
	}

	/**
	 * Set the bound of the wild card type under construction
	 * 
	 * @param type
	 *            the bound of the wild card type under construction.
	 * @return the builder.
	 */
	public WildCardTypeBuilder setBound(TypeAccess type) {
		this.buildWildCardType.setBound(type);
		return this;
	}

	/**
	 * Set the upper bound state of the wild card type under construction
	 * 
	 * @param isUpperBound
	 *            the upper bound state of the wild card type under
	 *            construction.
	 * @return the builder.
	 */
	public WildCardTypeBuilder setUpperBound(boolean isUpperBound) {
		this.buildWildCardType.setUpperBound(isUpperBound);
		return this;
	}

}
