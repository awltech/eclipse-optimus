package net.atos.optimus.common.tools.jdt.jstcomp;

import java.util.ArrayList;
import java.util.List;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;

import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.Type;

public class ASTThrownExceptionsHelper {

	private ASTThrownExceptionsHelper() {
	}

	private static final String DESCRIPTOR_NAME_AST8 = "thrownExceptionTypes";

	private static final String DESCRIPTOR_NAME_AST4 = "thrownExceptions";

	public static ChildListPropertyDescriptor getThrownExceptionsPropertyDescriptor(MethodDeclaration node) {
		List<?> propertyDescriptors = MethodDeclaration.propertyDescriptors(node.getAST().apiLevel());
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
		ChildListPropertyDescriptor childListPropertyDescriptor = ASTThrownExceptionsHelper
				.getThrownExceptionsPropertyDescriptor(methodDeclaration);
		if (childListPropertyDescriptor != null) {
			return (List<?>) methodDeclaration.getStructuralProperty(childListPropertyDescriptor);
		}
		return null;
	}

	public static List<Name> getThrownExceptionNames(MethodDeclaration methodDeclaration) {
		List<?> thrownExceptions = getThrownExceptions(methodDeclaration);
		List<Name> thrownExceptionNames = new ArrayList<Name>();
		for (Object o : thrownExceptions) {
			if (o instanceof Type) {
				Type t = (Type) o;
				thrownExceptionNames.add(JavaCodeHelper.getName(t));
			} else if (o instanceof Name) {
				thrownExceptionNames.add((Name) o);
			}
		}
		return thrownExceptionNames;
	}
}
