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
package net.atos.optimus.m2m.javaxmi.operation.methods;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.SingleVariableDeclaration;

/**
 * Models an abstract method : method or constructor
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractMethod {

	/** The abstract method declaration */
	private AbstractMethodDeclaration abstractMethodDeclaration;

	/**
	 * Constructor of abstract method
	 * 
	 * @param abstractMethodDeclaration
	 *            the abstract method declaration.
	 */
	public AbstractMethod(AbstractMethodDeclaration abstractMethodDeclaration) {
		this.abstractMethodDeclaration = abstractMethodDeclaration;
	}

	public AbstractMethodDeclaration getAbstractMethodDeclaration() {
		return this.abstractMethodDeclaration;
	}

	public CompilationUnit getCompilationUnit() {
		return this.abstractMethodDeclaration.getOriginalCompilationUnit();
	}

	public EList<SingleVariableDeclaration> getParameters() {
		return this.abstractMethodDeclaration.getParameters();
	}

}
