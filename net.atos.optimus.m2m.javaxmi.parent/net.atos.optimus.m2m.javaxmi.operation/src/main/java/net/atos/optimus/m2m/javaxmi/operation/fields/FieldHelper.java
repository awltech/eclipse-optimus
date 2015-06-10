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

import net.atos.optimus.m2m.javaxmi.operation.classes.Class;
import net.atos.optimus.m2m.javaxmi.operation.modifiers.ModifierBuilder;
import net.atos.optimus.m2m.javaxmi.operation.types.TypeAccessHelper;
import net.atos.optimus.m2m.javaxmi.operation.variables.VariableDeclarationFragmentBuilder;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
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
	 * Launch the build of a new field (private, with generated name)
	 * 
	 * @param javaClass
	 *            the class where is the field under construction.
	 * @param fieldTypeName
	 *            the type name of the field under construction.
	 * @return a new helper.
	 */
	public static FieldHelper builder(Class javaClass, String fieldTypeName) {
		return new FieldHelper(javaClass, fieldTypeName);
	}

	/**
	 * Private constructor : a new field (private, with generated name)
	 * 
	 * @param javaClass
	 *            the class where is the field under construction.
	 * @param fieldTypeName
	 *            the type name of the field under construction.
	 * @return a new helper.
	 */
	private FieldHelper(Class javaClass, String fieldTypeName) {
		ClassDeclaration internalClass = javaClass.getClassDeclaration();
		String fieldName = FieldHelper.generateFieldName(fieldTypeName);
		Modifier modifier = ModifierBuilder.builder().setVisibility(VisibilityKind.PRIVATE)
				.setCompilationUnit(internalClass.getOriginalCompilationUnit()).build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(fieldName).build();
		this.buildField = FieldDeclarationBuilder.builder().setModifier(modifier)
				.setType(TypeAccessHelper.createVariableTypeAccess(fieldTypeName))
				.addFragment(variableDeclarationFragment).setAbstractTypeDeclaration(internalClass)
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
		this.buildField.setName(fieldName);
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
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(this.buildField.getOriginalCompilationUnit()).build();
		this.buildField.setModifier(modifier);
		return this;
	}

	/**
	 * Create a field
	 * 
	 * @param javaClass
	 *            the class where is the created field.
	 * @param visibility
	 *            the visibility of the created field.
	 * @param fieldTypeName
	 *            the type name of the created field.
	 * @param fieldName
	 *            the name of the created field.
	 * @return the created field accordingly to the specified parameters.
	 */
	public static Field createField(Class javaClass, VisibilityKind visibility, String fieldTypeName, String fieldName) {
		return FieldHelper.builder(javaClass, fieldTypeName).setVisibility(visibility).setName(fieldName).build();
	}

	/**
	 * Generate the field name with the field type name
	 * 
	 * @param fieldTypeName
	 *            the field type name.
	 * @return the field name associated to the field type name.
	 */
	public static String generateFieldName(String fieldTypeName) {
		StringBuilder s = new StringBuilder();
		if (s != null && !"".equals(fieldTypeName.trim())) {
			s.append(fieldTypeName.trim().substring(0, 1).toLowerCase());
			if (fieldTypeName.length() > 1) {
				s.append(fieldTypeName.substring(1));
			}
		}
		return s.toString();
	}

}
