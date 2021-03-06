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

import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;
import net.atos.optimus.m2m.javaxmi.operation.fields.Field;
import net.atos.optimus.m2m.javaxmi.operation.instructions.AssignmentInstructionHelper;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.AssignmentKind;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of setter methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class SetterHelper {

	/** Prefix of the set method name */
	public static final String SET_PREFIX = "set";

	/** The build setter method */
	private Method buildSetterMethod;

	/** The field associated to the setter */
	private Field field;

	/**
	 * Launch the build of a new setter method (public, not final, with
	 * generated name and generated parameter name)
	 * 
	 * @param abstractClass
	 *            the abstract class where is the setter method under
	 *            construction.
	 * @param field
	 *            the field associated to the setter method under construction.
	 * @return a new setter method helper.
	 */
	public static SetterHelper builder(AbstractClass<? extends AbstractTypeDeclaration> abstractClass, Field field) {
		return new SetterHelper(abstractClass, field);
	}

	/**
	 * Private constructor : a new setter method (public, not final, with
	 * generated name and generated parameter name)
	 * 
	 * @param abstractClass
	 *            the abstract class where is the setter method under
	 *            construction.
	 * @param field
	 *            the field associated to the setter method under construction.
	 */
	private SetterHelper(AbstractClass<? extends AbstractTypeDeclaration> abstractClass, Field field) {
		String parameterName = NameGenerator.generateNameWithTypeName(field.getTypeName());
		this.buildSetterMethod = MethodHelper
				.builder(abstractClass, NameGenerator.generateSetterName(field.getName()))
				.setVisibility(VisibilityKind.PUBLIC)
				.setInheritance(InheritanceKind.NONE)
				.addParameter(field.getTypeName(), parameterName)
				.addInstructions(
						AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
								.setLeftFieldOperand(field.getName()).setRightVariableOperand(parameterName).build())
				.build();
		this.field = field;
	}

	/**
	 * Build a setter method
	 * 
	 * @return the build setter method.
	 */
	public Method build() {
		return this.buildSetterMethod;
	}

	/**
	 * Set the name of the setter method under construction
	 * 
	 * @param setterName
	 *            the name of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setName(String setterName) {
		this.buildSetterMethod.getDelegate().setName(setterName);
		return this;
	}

	/**
	 * Set the visibility of the setter method under construction
	 * 
	 * @param visibility
	 *            the visibility of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setVisibility(VisibilityKind visibility) {
		this.buildSetterMethod.getDelegate().getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the final state of the setter method under construction
	 * 
	 * @param isFinal
	 *            the final state of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setFinal(boolean isFinal) {
		this.buildSetterMethod.getDelegate().getModifier()
				.setInheritance(isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Set the parameter name of the setter method under construction
	 * 
	 * @param parameterName
	 *            the parameter name of the setter method under construction.
	 * @return the helper.
	 */
	public SetterHelper setParameterName(String parameterName) {
		this.buildSetterMethod.getDelegate().getParameters().get(0).setName(parameterName);
		this.buildSetterMethod.getDelegate().setBody(null);
		this.buildSetterMethod.addInstructions(AssignmentInstructionHelper.builder().setOperator(AssignmentKind.ASSIGN)
				.setLeftFieldOperand(this.field.getName()).setRightVariableOperand(parameterName).build());
		return this;
	}

	/**
	 * Create a setter method
	 * 
	 * @param abstractClass
	 *            the abstract class where is the created setter method.
	 * @param visibility
	 *            the visibility of the created setter method.
	 * @param isFinal
	 *            the final state of the created setter method.
	 * @param field
	 *            the field associated to the created setter method.
	 * @param setterName
	 *            the name of the created setter method.
	 * @param parameterName
	 *            the variableName of the created setter method.
	 * @return the created setter method accordingly to the specified
	 *         parameters.
	 */
	public static Method createSetter(AbstractClass<? extends AbstractTypeDeclaration> abstractClass,
			VisibilityKind visibility, boolean isFinal, Field field, String setterName, String parameterName) {
		return SetterHelper.builder(abstractClass, field).setVisibility(visibility).setFinal(isFinal)
				.setName(setterName).setParameterName(parameterName).build();
	}

}
