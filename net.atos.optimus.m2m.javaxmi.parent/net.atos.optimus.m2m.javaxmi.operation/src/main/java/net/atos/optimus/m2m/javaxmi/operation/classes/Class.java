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
package net.atos.optimus.m2m.javaxmi.operation.classes;

import org.eclipse.gmt.modisco.java.ClassDeclaration;

/**
 * Models a class : wrapper of ClassDeclaration in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class Class {

	/** The class declaration */
	protected ClassDeclaration classDeclaration;

	/**
	 * Constructor of class
	 * 
	 * @param classDeclaration
	 *            the class declaration.
	 */
	public Class(ClassDeclaration classDeclaration) {
		this.classDeclaration = classDeclaration;
	}

	public ClassDeclaration getClassDeclaration() {
		return this.classDeclaration;
	}
	
	public String getName() {
		return this.classDeclaration.getName();
	}

}
