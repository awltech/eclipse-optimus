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
package net.atos.optimus.common.tools.jdt.jstcomp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import net.atos.optimus.common.tools.jdt.JavaCodeHelper;
import net.atos.optimus.common.tools.logging.OptimusLogger;

import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
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
public class ASTThrownExceptionsHelper {

	private static final String DESCRIPTOR_RETURNED_MESSAGE = "[JSTComp] Returns ThrownExceptions PropertyDescriptor named [%s].";

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
					if (OptimusLogger.logger.isLoggable(Level.FINE)) {
						OptimusLogger.logger.fine(String.format(DESCRIPTOR_RETURNED_MESSAGE, DESCRIPTOR_NAME_AST4));
					}
					return childListPropertyDescriptor;
				} else if (DESCRIPTOR_NAME_AST8.equals(childListPropertyDescriptor.getId())) {
					if (OptimusLogger.logger.isLoggable(Level.FINE)) {
						OptimusLogger.logger.fine(String.format(DESCRIPTOR_RETURNED_MESSAGE, DESCRIPTOR_NAME_AST4));
					}
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
