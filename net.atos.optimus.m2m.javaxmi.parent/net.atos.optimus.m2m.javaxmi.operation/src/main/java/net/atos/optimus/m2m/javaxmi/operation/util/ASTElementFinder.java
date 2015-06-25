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
package net.atos.optimus.m2m.javaxmi.operation.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.Model;

/**
 * Util class dedicated to find some element in the AST model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ASTElementFinder {

	/**
	 * Find the model of an AST node
	 * 
	 * @param node
	 *            the node.
	 * @return the model of the specified AST node.
	 */
	public static Model findModel(ASTNode node) {
		Model model = null;
		EObject temp = node;
		while (model == null && temp != null) {
			if (temp instanceof Model) {
				model = (Model) temp;
			} else {
				temp = temp.eContainer();
			}
		}
		return model;
	}

	/**
	 * Find the compilation unit of body declaration
	 * 
	 * @param bodyDeclaration
	 *            the body declaration.
	 * @return the compilation unit of the specified body declaration.
	 */
	public static CompilationUnit findCompilationUnit(BodyDeclaration bodyDeclaration) {
		AbstractTypeDeclaration atd = null;

		if (bodyDeclaration instanceof AbstractTypeDeclaration) {
			atd = (AbstractTypeDeclaration) bodyDeclaration;
		} else if (bodyDeclaration instanceof EnumConstantDeclaration) {
			atd = ((AbstractTypeDeclaration) bodyDeclaration.eContainer());
		} else {
			atd = bodyDeclaration.getAbstractTypeDeclaration();
		}

		return atd == null ? null : atd.getOriginalCompilationUnit();
	}

}
