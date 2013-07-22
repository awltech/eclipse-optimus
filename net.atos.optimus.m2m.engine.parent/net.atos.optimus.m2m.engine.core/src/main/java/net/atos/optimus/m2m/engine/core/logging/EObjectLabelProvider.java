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
package net.atos.optimus.m2m.engine.core.logging;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * Default EObject Label Provider. Implementation formats EClass name with
 * eobject name if has one.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class EObjectLabelProvider extends LabelProvider {

	private static final String ARRAY_PATTERN = "[%s]";

	private static final String SEPARATOR = ",";

	private static final String EMPTY_STR = "";

	/**
	 * Name of the EAttribute supposed to hold object names
	 */
	private static final String EATTRIBUTE_NAME = "name";

	/**
	 * Pattern used for EObjects that have a name
	 */
	private static final String PATTERN_WITH_NAME = "<%s> %s";

	/**
	 * Pattern used of EObject that don't have a name
	 */
	private static final String PATTERN_WOUT_NAME = "<%s>";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		if (element instanceof EList<?>) {
			String value = EMPTY_STR;
			for (Iterator<?> iterator = ((EList<?>) element).iterator(); iterator.hasNext();)
				value = value + (value.length() > 0 ? SEPARATOR : EMPTY_STR) + getText(iterator.next());
			return String.format(ARRAY_PATTERN, value);
		} else if (element instanceof EObject) {
			EObject eObject = (EObject) element;
			Object name = null;
			EClass eClass = eObject.eClass();
			EStructuralFeature nameFeature = eClass.getEStructuralFeature(EATTRIBUTE_NAME);
			if (nameFeature != null)
				name = eObject.eGet(nameFeature);
			return name != null ? String.format(PATTERN_WITH_NAME, eClass.getName(), name) : String.format(
					PATTERN_WOUT_NAME, eClass.getName());
		}
		return String.valueOf(element);
	}
}
