package net.atos.optimus.common.tools.jdt.jstcomp;

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
import java.util.List;

import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.Type;

/**
 * Implementation of helper that gives abstraction level over deprecated methods
 * between AST4 & AST8, to prevent AST to throw exceptions and keep compliance
 * with older eclipse versions.
 * 
 * @author mvanbesien
 * @since 1.1
 *
 */
public class ASTArrayTypeHelper {

	private ASTArrayTypeHelper() {
	}

	private static final String DESCRIPTOR_NAME_AST8 = "elementType";

	private static final String DESCRIPTOR_NAME_AST4 = "componentType";

	public static ChildPropertyDescriptor getArrayTypeElementPropertyDescriptor(ArrayType node) {
		List<?> propertyDescriptors = ArrayType.propertyDescriptors(node.getAST().apiLevel());
		for (Object propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor instanceof ChildPropertyDescriptor) {
				ChildPropertyDescriptor childListPropertyDescriptor = (ChildPropertyDescriptor) propertyDescriptor;
				if (DESCRIPTOR_NAME_AST4.equals(childListPropertyDescriptor.getId())) {
					return childListPropertyDescriptor;
				} else if (DESCRIPTOR_NAME_AST8.equals(childListPropertyDescriptor.getId())) {
					return childListPropertyDescriptor;
				}
			}
		}
		return null;
	}

	public static Type getComponentType(ArrayType arrayType) {
		ChildPropertyDescriptor cpd = ASTArrayTypeHelper.getArrayTypeElementPropertyDescriptor(arrayType);
		if (cpd != null) {
			Object structuralProperty = arrayType.getStructuralProperty(cpd);
			if (structuralProperty instanceof Type) {
				return (Type) structuralProperty;
			}
		}
		return null;
	}
}
