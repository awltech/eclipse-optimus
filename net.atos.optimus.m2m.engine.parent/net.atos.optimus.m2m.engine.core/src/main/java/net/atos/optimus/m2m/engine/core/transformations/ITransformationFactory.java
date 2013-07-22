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
package net.atos.optimus.m2m.engine.core.transformations;

import org.eclipse.emf.ecore.EObject;

/**
 * Transformation Factory.
 * 
 * As there will be one transformation instance per EObject instance, we need
 * factories to create them. The factory implementations will be referenced in
 * the Extension point.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public interface ITransformationFactory {

	/**
	 * Returns whether the EObject passed as parameter is eligible from the
	 * transformations that will be created by this factory instance.
	 * 
	 * @param eObject
	 *            : input eobject
	 * @return true if transformation can be executed on EObject, false
	 *         otherwise
	 */
	public boolean isEligible(EObject eObject);

	/**
	 * Creates a new instance of Transformation, with provided ID and input
	 * EObject
	 * 
	 * @param eObject
	 *            input EObject
	 * @param id
	 *            transformation id, as defined in the extension point
	 * @return transformation instance
	 */
	public AbstractTransformation<?> create(EObject eObject, String id);

}
