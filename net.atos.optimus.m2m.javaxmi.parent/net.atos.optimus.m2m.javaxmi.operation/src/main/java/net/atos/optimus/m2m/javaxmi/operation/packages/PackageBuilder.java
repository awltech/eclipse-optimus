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
package net.atos.optimus.m2m.javaxmi.operation.packages;

import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to package of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class PackageBuilder {

	/** The build package */
	private Package buildPackage;

	public static PackageBuilder builder() {
		return new PackageBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private PackageBuilder() {
		this.buildPackage = JavaFactory.eINSTANCE.createPackage();
	}

	/**
	 * Build a package of modisco model
	 * 
	 * @return a new package of modisco model.
	 */
	public Package build() {
		return this.buildPackage;
	}

	/**
	 * Set the model of the package under construction
	 * 
	 * @param model
	 *            the model of the package under construction.
	 * @return the builder.
	 */
	public PackageBuilder setModel(Model model) {
		this.buildPackage.setModel(model);
		return this;
	}

	/**
	 * Set the parent package of the package under construction
	 * 
	 * @param parentPackage
	 *            the parent package of the package under construction.
	 * @return the builder.
	 */
	public PackageBuilder setPackage(Package parentPackage) {
		this.buildPackage.setPackage(parentPackage);
		return this;
	}

	/**
	 * Set the name of the package under construction
	 * 
	 * @param name
	 *            the name of the package under construction.
	 * @return the builder.
	 */
	public PackageBuilder setName(String name) {
		this.buildPackage.setName(name);
		return this;
	}

}
