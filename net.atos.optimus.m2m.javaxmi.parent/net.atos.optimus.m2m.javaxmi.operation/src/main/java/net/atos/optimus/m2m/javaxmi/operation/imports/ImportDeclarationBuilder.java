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
package net.atos.optimus.m2m.javaxmi.operation.imports;

import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create import declaration of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ImportDeclarationBuilder {

	/** The build import declaration */
	private ImportDeclaration buildImportDeclaration;

	/**
	 * Give a new import declaration builder
	 * 
	 * @return a new import declaration builder.
	 */
	public static ImportDeclarationBuilder builder() {
		return new ImportDeclarationBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ImportDeclarationBuilder() {
		this.buildImportDeclaration = JavaFactory.eINSTANCE.createImportDeclaration();
	}

	/**
	 * Build a import declaration of modisco model
	 * 
	 * @return a new import declaration of modisco model.
	 */
	public ImportDeclaration build() {
		return this.buildImportDeclaration;
	}

	/**
	 * Set the import element of the import declaration under construction
	 * 
	 * @param namedElement
	 *            the import element of the import declaration under
	 *            construction.
	 * @return the builder.
	 */
	public ImportDeclarationBuilder setImportedElement(NamedElement namedElement) {
		this.buildImportDeclaration.setImportedElement(namedElement);
		return this;
	}

	/**
	 * Set the static state of the import declaration under construction
	 * 
	 * @param isStatic
	 *            the static state of the import declaration under construction.
	 * @return the builder.
	 */
	public ImportDeclarationBuilder setStatic(boolean isStatic) {
		this.buildImportDeclaration.setStatic(isStatic);
		return this;
	}

}
