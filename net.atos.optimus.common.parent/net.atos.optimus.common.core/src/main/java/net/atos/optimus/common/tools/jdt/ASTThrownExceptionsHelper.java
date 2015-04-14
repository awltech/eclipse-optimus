package net.atos.optimus.common.tools.jdt;

import java.util.List;

import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class ASTThrownExceptionsHelper {

	private ASTThrownExceptionsHelper() {
	}
	
	private static final String DESCRIPTOR_NAME_AST8 = "thrownExceptionTypes";
	
	private static final String DESCRIPTOR_NAME_AST4 = "thrownExceptions";
	
	public static ChildListPropertyDescriptor getThrownExceptionsPropertyDescriptor(MethodDeclaration methodDeclaration) {
		List<?> propertyDescriptors = MethodDeclaration.propertyDescriptors(methodDeclaration.getAST().apiLevel());
		
		for (Object propertyDescriptor : propertyDescriptors) {
			if (propertyDescriptor instanceof ChildListPropertyDescriptor) {
				ChildListPropertyDescriptor childListPropertyDescriptor = (ChildListPropertyDescriptor) propertyDescriptor;
				if (DESCRIPTOR_NAME_AST4.equals(childListPropertyDescriptor.getId())) {
					return childListPropertyDescriptor;
				} else if (DESCRIPTOR_NAME_AST8.equals(childListPropertyDescriptor.getId())) {
					return childListPropertyDescriptor;
				}
			}
		}
		return null;
	}
	
	public static List<?> getThrownExceptions(MethodDeclaration methodDeclaration) {
		ChildListPropertyDescriptor childListPropertyDescriptor = getThrownExceptionsPropertyDescriptor(methodDeclaration);
		if (childListPropertyDescriptor != null) {
			return (List<?>) methodDeclaration.getStructuralProperty(childListPropertyDescriptor);
		}
		return null;
	}
	
}
