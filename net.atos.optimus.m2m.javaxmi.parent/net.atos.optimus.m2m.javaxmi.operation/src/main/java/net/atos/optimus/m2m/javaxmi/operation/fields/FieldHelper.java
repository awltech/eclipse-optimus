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

	/**
	 * Create a private field
	 * 
	 * @param javaClass
	 *            the class where is the created field.
	 * @param typeName
	 *            the type name of the created field.
	 * @param fieldName
	 *            the name of the created field.
	 * @return the created field accordingly to the specified parameters.
	 */
	public static FieldDeclaration createField(ClassDeclaration javaClass, String typeName, String fieldName) {
		return FieldHelper.createField(javaClass, VisibilityKind.PRIVATE, typeName, fieldName);
	}

	/**
	 * Create a field
	 * 
	 * @param javaClass
	 *            the class where is the created field.
	 * @param visibility
	 *            the visibility the created field.
	 * @param typeName
	 *            the type name of the created field.
	 * @param fieldName
	 *            the name of the created field.
	 * @return the created field accordingly to the specified parameters.
	 */
	public static FieldDeclaration createField(ClassDeclaration javaClass, VisibilityKind visibility, String typeName,
			String fieldName) {
		Modifier modifier = ModifierBuilder.builder().setVisibility(visibility)
				.setCompilationUnit(javaClass.getOriginalCompilationUnit()).build();
		VariableDeclarationFragment variableDeclarationFragment = VariableDeclarationFragmentBuilder.builder()
				.setName(fieldName).build();
		return FieldDeclarationBuilder.builder().setModifier(modifier)
				.setType(TypeAccessHelper.createVariableTypeAccess(typeName)).addFragment(variableDeclarationFragment)
				.setAbstractTypeDeclaration(javaClass).setCompilationUnit(javaClass.getOriginalCompilationUnit())
				.build();
	}

}
