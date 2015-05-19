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
package net.atos.optimus.m2m.engine.sdk.tom.properties.requestors;

import net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors.ClassResourceProcessor;

import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo;
import org.eclipselabs.resourceselector.processor.java.JavaTypeInfo.JavaTypeVisibility;

/**
 * A requestor dedicated to searching all public classes in a search engine
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class PublicClassRequestor extends TypeNameRequestor {

	/** The resource processor exploiting the requestor */
	protected ClassResourceProcessor resourceProcessor;

	/**
	 * Constructor
	 * 
	 * @param resourceProcessor
	 *            the resource processor exploiting the requestor.
	 */
	public PublicClassRequestor(ClassResourceProcessor resourceProcessor) {
		this.resourceProcessor = resourceProcessor;
	}

	@Override
	public void acceptType(int modifiers, char[] packageName, char[] simpleTypeName, char[][] enclosingTypeNames,
			String path) {

		/* Package and type name creation */
		String packageNameAsString = new String(packageName);
		String simpleTypeNameAsString = new String(simpleTypeName);

		/* Associated resource creation */
		JavaTypeInfo javaTypeInfo = new JavaTypeInfo(simpleTypeNameAsString, packageNameAsString, path, null, modifiers);

		/* Acceptance test */
		if (this.isValidInfo(javaTypeInfo)) {
			this.resourceProcessor.addResult(javaTypeInfo);
		}
	}

	/**
	 * Test if a resource info must be adding to the resource selector
	 * 
	 * @param javaTypeInfo
	 *            the resource info under test.
	 * 
	 * @return true if the specified resource info must be adding to the
	 *         resource selector
	 */
	protected boolean isValidInfo(JavaTypeInfo javaTypeInfo) {
		return !javaTypeInfo.isInnerElement() && javaTypeInfo.getTypeVisibility().equals(JavaTypeVisibility.PUBLIC);
	}

}
