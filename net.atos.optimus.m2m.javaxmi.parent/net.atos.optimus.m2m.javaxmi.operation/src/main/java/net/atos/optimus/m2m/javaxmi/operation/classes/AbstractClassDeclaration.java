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

import net.atos.optimus.m2m.javaxmi.operation.elements.AbstractDeclaration;

import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;

/**
 * Models an abstract class declaration : wrapper of AbstractTypeDeclaration in modisco
 * model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AbstractClassDeclaration<C extends AbstractTypeDeclaration> extends AbstractDeclaration<C> {

	/**
	 * Constructor of abstract class declaration
	 * 
	 * @param abstractTypeDeclaration
	 *            the abstract type declaration.
	 */
	public AbstractClassDeclaration(C abstractTypeDeclaration) {
		super(abstractTypeDeclaration);
	}

	public String getName() {
		return this.getDelegate().getName();
	}

	@Override
	public AbstractClassDeclaration<C> addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public AbstractClassDeclaration<C> addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	@Override
	public AbstractClassDeclaration<C> addAnnotation(String packageName, String annotationName) {
		super.addAnnotation(packageName, annotationName);
		return this;
	}

}
