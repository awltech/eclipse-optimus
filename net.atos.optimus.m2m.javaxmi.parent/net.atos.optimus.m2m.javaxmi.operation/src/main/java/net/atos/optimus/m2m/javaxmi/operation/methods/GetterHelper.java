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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import net.atos.optimus.m2m.javaxmi.operation.classes.JavaClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.instruction.elementary.ElementaryInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;

import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of getter methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class GetterHelper {

	public static final String GET_PREFIX = "get";

	/** The build getter method */
	private Method buildGetterMethod;

	/**
	 * Launch the build of a new getter method (public, not final, with
	 * generated name)
	 * 
	 * @param javaClass
	 *            the class where is the getter method under construction.
	 * @param field
	 *            the field associated to the getter method under construction.
	 * @return a new getter method helper.
	 */
	public static GetterHelper builder(JavaClass javaClass, Field field) {
		return new GetterHelper(javaClass, field);
	}

	/**
	 * Private constructor : a new getter method (public, not final, with
	 * generated name)
	 * 
	 * @param javaClass
	 *            the class where is the getter method under construction.
	 * @param field
	 *            the field associated to the getter method under construction.
	 */
	private GetterHelper(JavaClass javaClass, Field field) {
		this.buildGetterMethod = MethodHelper
				.builder(javaClass, NameGenerator.generateGetterName(field.getName()))
				.setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE)
				.setReturnType(field.getTypeName())
				.addInstructions(
						ElementaryInstructionHelper.createFieldInstruction(field.getName())
								.convertToReturnInstruction()).build();
	}

	/**
	 * Build a getter method
	 * 
	 * @return the build getter method.
	 */
	public Method build() {
		return this.buildGetterMethod;
	}

	/**
	 * Set the name of the getter method under construction
	 * 
	 * @param getterName
	 *            the name of the getter method under construction.
	 * @return the helper.
	 */
	public GetterHelper setName(String getterName) {
		this.buildGetterMethod.getDelegate().setName(getterName);
		return this;
	}

	/**
	 * Set the visibility of the getter method under construction
	 * 
	 * @param visibility
	 *            the visibility of the getter method under construction.
	 * @return the helper.
	 */
	public GetterHelper setVisibility(VisibilityKind visibility) {
		this.buildGetterMethod.getDelegate().getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the final state of the getter method under construction
	 * 
	 * @param isFinal
	 *            the final state of the getter method under construction.
	 * @return the helper.
	 */
	public GetterHelper setFinal(boolean isFinal) {
		this.buildGetterMethod.getDelegate().getModifier()
				.setInheritance(isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Create a getter method
	 * 
	 * @param javaClass
	 *            the class where is the created getter method.
	 * @param visibility
	 *            the visibility of the created getter method.
	 * @param isFinal
	 *            the final state of the created getter method.
	 * @param field
	 *            the field associated with the created getter method.
	 * @param getterName
	 *            the name of the created getter method.
	 * @return the created getter method accordingly to the specified
	 *         parameters.
	 */
	public static Method createGetter(JavaClass javaClass, VisibilityKind visibility, boolean isFinal, Field field,
			String getterName) {
		return GetterHelper.builder(javaClass, field).setVisibility(visibility).setFinal(isFinal).setName(getterName)
				.build();
	}

}
