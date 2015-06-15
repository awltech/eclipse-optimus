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
package net.atos.optimus.m2m.javaxmi.operation.modifiers;

import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.JavaFactory;

/**
 * A builder dedicated to create modifier of modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ModifierBuilder {

	/** The build modifier */
	private Modifier buildModifier;

	/**
	 * Give a new modifier builder
	 * 
	 * @return a new modifier builder.
	 */
	public static ModifierBuilder builder() {
		return new ModifierBuilder();
	}

	/**
	 * Private constructor
	 * 
	 */
	private ModifierBuilder() {
		this.buildModifier = JavaFactory.eINSTANCE.createModifier();
	}

	/**
	 * Build a modifier of modisco model
	 * 
	 * @return a new modifier of modisco model.
	 */
	public Modifier build() {
		return this.buildModifier;
	}

	/**
	 * Set the visibility of the modifier under construction
	 * 
	 * @param visibility
	 *            the visibility of the modifier under construction.
	 * @return the builder.
	 */
	public ModifierBuilder setVisibility(VisibilityKind visibility) {
		this.buildModifier.setVisibility(visibility);
		return this;
	}

	/**
	 * Set the compilation unit of the modifier under construction
	 * 
	 * @param compilationUnit
	 *            the compilation unit of the modifier under construction.
	 * @return the builder.
	 */
	public ModifierBuilder setCompilationUnit(CompilationUnit compilationUnit) {
		this.buildModifier.setOriginalCompilationUnit(compilationUnit);
		return this;
	}

	/**
	 * Set the static state of the modifier under construction
	 * 
	 * @param isStatic
	 *            the static state of the modifier under construction.
	 * @return the builder.
	 */
	public ModifierBuilder setStatic(boolean isStatic) {
		this.buildModifier.setStatic(isStatic);
		return this;
	}

	/**
	 * Set the inheritance state of the modifier under construction
	 * 
	 * @param inheritance
	 *            the inheritance state of the modifier under construction.
	 * @return the builder.
	 */
	public ModifierBuilder setInheritance(InheritanceKind inheritance) {
		this.buildModifier.setInheritance(inheritance);
		return this;
	}

}
