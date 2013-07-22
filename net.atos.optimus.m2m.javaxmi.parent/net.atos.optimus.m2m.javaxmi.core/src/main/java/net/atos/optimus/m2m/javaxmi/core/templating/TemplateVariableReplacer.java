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
package net.atos.optimus.m2m.javaxmi.core.templating;

import java.util.Properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Remplace the awaited variables in the EStrings of the provided EObject.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class TemplateVariableReplacer {

	/**
	 * Applies a replacement of variables in EObject and children, by elements
	 * in the provided properties
	 * 
	 * @param eObject
	 * @param properties
	 */
	public static void replace(final EObject eObject, final Properties properties) {
		// Process the current object
		final EClass eClass = eObject.eClass();
		for (final EAttribute eAttribute : eClass.getEAllAttributes())
			if (eAttribute.getEAttributeType() == EcorePackage.eINSTANCE.getEString()) {
				boolean isUpdated = false;
				String value = String.valueOf(eObject.eGet(eAttribute));
				for (final Object o : properties.keySet()) {
					final String key = (String) o;
					if (value != null && value.indexOf(key) > -1) {
						value = value.replace(key, properties.getProperty(key));
						isUpdated = true;
					}
				}
				if (isUpdated)
					eObject.eSet(eAttribute, value);
			}

		// Do the same for all the children
		for (final EObject child : eObject.eContents())
			TemplateVariableReplacer.replace(child, properties);
	}
}
