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
package net.atos.optimus.m2m.javaxmi.operation.fields;

import net.atos.optimus.m2m.javaxmi.operation.accesses.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.classes.AbstractClass;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.util.NameGenerator;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.InheritanceKind;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.VisibilityKind;

/**
 * The purpose of such class is to help with the creation of fields
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class FieldHelper {

	/** The build field */
	private FieldDeclaration buildField;

	/**
	 * Launch the build of a new field (private, not static, nor abstract, with
	 * generated name)
	 * 
	 * @param abstractClass
	 *            the abstract class where is the field under construction.
	 * @param fieldTypeName
	 *            the type name of the field under construction.
	 * @return a new helper.
	 */
	public static FieldHelper builder(AbstractClass<? extends AbstractTypeDeclaration> abstractClass,
			String fieldTypeName) {
		return new FieldHelper(abstractClass, fieldTypeName);
	}

	/**
	 * Private constructor : a new field (private, not static, nor abstract,
	 * with generated name)
	 * 
	 * @param abstractClass
	 *            the abstract class where is the field under construction.
	 * @param fieldTypeName
	 *            the type name of the field under construction.
	 * @return a new helper.
	 */
	private FieldHelper(AbstractClass<? extends AbstractTypeDeclaration> abstractClass, String fieldTypeName) {
		AbstractTypeDeclaration internalClass = abstractClass.getDelegate();
		String fieldName = NameGenerator.generateNameWithTypeName(fieldTypeName);
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PRIVATE).setStatic(false)
				.setInheritance(InheritanceKind.NONE).setCompilationUnit(internalClass.getOriginalCompilationUnit())
				.build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(fieldName).build();
		this.buildField = FieldDeclarationBuilder.builder().setModifier(modifier)
				.setType(TypeAccessHelper.createTypeAccess(fieldTypeName)).addFragment(variableDeclarationFragment)
				.setAbstractTypeDeclaration(internalClass)
				.setCompilationUnit(internalClass.getOriginalCompilationUnit()).build();
	}

	/**
	 * Give the build field
	 * 
	 * @return the build field.
	 */
	public Field build() {
		return new Field(this.buildField);
	}

	/**
	 * Set the name of the field under construction
	 * 
	 * @param fieldName
	 *            the name of the field under construction.
	 * @return the helper.
	 */
	public FieldHelper setName(String fieldName) {
		this.buildField.getFragments().get(0).setName(fieldName);
		return this;
	}

	/**
	 * Set the visibility of the field under construction
	 * 
	 * @param visibility
	 *            the visibility of the field under construction.
	 * @return the helper.
	 */
	public FieldHelper setVisibility(VisibilityKind visibility) {
		this.buildField.getModifier().setVisibility(visibility);
		return this;
	}

	/**
	 * Set the static state of the field under construction
	 * 
	 * @param isStatic
	 *            the static state of the field under construction.
	 * @return the helper.
	 */
	public FieldHelper setStatic(boolean isStatic) {
		this.buildField.getModifier().setStatic(isStatic);
		return this;
	}

	/**
	 * Set the final state of the field under construction
	 * 
	 * @param isFinal
	 *            the final state of the field under construction.
	 * @return the helper.
	 */
	public FieldHelper setFinal(boolean isFinal) {
		this.buildField.getModifier().setInheritance(isFinal ? InheritanceKind.FINAL : InheritanceKind.NONE);
		return this;
	}

	/**
	 * Create a field
	 * 
	 * @param abstractClass
	 *            the abstract class where is the created field.
	 * @param visibility
	 *            the visibility of the created field.
	 * @param isStatic
	 *            the static state of the created field.
	 * @param isFinal
	 *            the final state of the created field.
	 * @param fieldTypeName
	 *            the type name of the created field.
	 * @param fieldName
	 *            the name of the created field.
	 * @return the created field accordingly to the specified parameters.
	 */
	public static Field createField(AbstractClass<? extends AbstractTypeDeclaration> abstractClass,
			VisibilityKind visibility, boolean isStatic, boolean isFinal, String fieldTypeName, String fieldName) {
		return FieldHelper.builder(abstractClass, fieldTypeName).setVisibility(visibility).setStatic(isStatic)
				.setFinal(isFinal).setName(fieldName).build();
	}

}
