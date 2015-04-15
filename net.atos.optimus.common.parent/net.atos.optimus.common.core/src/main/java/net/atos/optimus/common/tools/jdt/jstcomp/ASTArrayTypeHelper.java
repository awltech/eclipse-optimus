package net.atos.optimus.common.tools.jdt.jstcomp;

import java.util.List;

import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.Type;

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
