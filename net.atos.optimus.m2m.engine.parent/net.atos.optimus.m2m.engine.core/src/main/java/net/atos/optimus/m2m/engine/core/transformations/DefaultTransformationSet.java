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
 * Default Transformation Set implementation. This is used when the user hasn't
 * specified any implementation for has defined transformation Set.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class DefaultTransformationSet extends TransformationSet {

	/**
	 * Default constructor, used mainly when no implementation is used
	 */
	public DefaultTransformationSet() {
	}

	/**
	 * In case of programmatic creation, here's a constructor with the
	 * possibility to set an ID.
	 * 
	 * @param id
	 */
	public DefaultTransformationSet(String id) {
		this.setId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.TransformationSet#isEligible
	 * (org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isEligible(EObject eObject) {
		return true;
	}

}
