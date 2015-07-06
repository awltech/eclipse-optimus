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
package net.atos.optimus.m2m.javaxmi.operation.packages;

import net.atos.optimus.m2m.javaxmi.operation.element.Element;

import org.eclipse.gmt.modisco.java.Package;

/**
 * Models a package : wrapper of Package in modisco model
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class JavaPackage extends Element<org.eclipse.gmt.modisco.java.Package> {

	/**
	 * Constructor of package of the modicso model
	 * 
	 * @param javaPackage
	 *            the package of the modicso model.
	 */
	public JavaPackage(org.eclipse.gmt.modisco.java.Package javaPackage) {
		super(javaPackage);
	}

	@Override
	public JavaPackage addJavadoc(String documentation, boolean addEmptyLine) {
		super.addJavadoc(documentation, addEmptyLine);
		return this;
	}

	@Override
	public JavaPackage addComment(String commentText, boolean prefixOfParent) {
		super.addComment(commentText, prefixOfParent);
		return this;
	}

	public String getName() {
		return this.getDelegate().getName();
	}

	public String getFullQualifiedName() {
		StringBuffer s = new StringBuffer();
		Package parent = this.getDelegate().getPackage();
		s.append(this.getName());
		while (parent != null) {
			s.insert(0, '.');
			s.insert(0, parent.getName());
			parent = parent.getPackage();
		}
		return s.toString();
	}
}
