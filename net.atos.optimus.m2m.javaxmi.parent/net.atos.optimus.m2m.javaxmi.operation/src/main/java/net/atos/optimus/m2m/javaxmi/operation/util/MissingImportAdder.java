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

import java.util.Iterator;

import net.atos.optimus.m2m.javaxmi.operation.imports.ImportDeclarationBuilder;

import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.UnresolvedAnnotationDeclaration;

/**
 * Util class dedicated to added missing import
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class MissingImportAdder {

	/**
	 * Adds import if missing in the body declaration for the provided type
	 * 
	 * @param bodyDeclaration
	 *            the body declaration.
	 * @param type
	 *            the type.
	 */
	public static void addMissingImport(BodyDeclaration bodyDeclaration, Type type) {
		CompilationUnit compilationUnit = ASTElementFinder.findCompilationUnit(bodyDeclaration);
		if (type instanceof UnresolvedAnnotationDeclaration || compilationUnit == null) {
			return;
		}

		boolean hasMatchingImport = false;
		Iterator<ImportDeclaration> importIterator = compilationUnit.getImports().iterator();
		while (importIterator.hasNext() && hasMatchingImport == false) {
			hasMatchingImport = importIterator.next().getImportedElement().equals(type);
		}

		if (!hasMatchingImport) {
			ImportDeclaration declaration = ImportDeclarationBuilder.builder().setImportedElement(type).build();
			compilationUnit.getImports().add(declaration);
		}
	}
}
