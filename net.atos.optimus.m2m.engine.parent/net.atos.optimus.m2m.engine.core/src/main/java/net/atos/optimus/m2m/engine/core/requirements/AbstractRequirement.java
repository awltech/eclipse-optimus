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
package net.atos.optimus.m2m.engine.core.requirements;

import java.util.List;

import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.emf.ecore.EObject;

/**
 * Abstract Requirement instance. Clients may use this abstract class, if they
 * want to define a specific way to retrieve the Eobject on which the required
 * transformation has to be executed.
 * 
 * The {@link ObjectRequirement} and {@link ParentRequirement} implement this
 * class.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public abstract class AbstractRequirement {

	/**
	 * ID of the transformation behind this requirement
	 */
	private String id;

	/**
	 * Returns the ID of the transformation behind this requirement
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID of the transformation behind this requirement
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Returns the EObjects that will be the inputs of the transformation behind
	 * this requirement
	 * 
	 * @param eObject
	 *            : input of the main transformation
	 * @param context
	 *            : Transformation context
	 * @return : List of EObject that will be the inputs of the required
	 *         transformation.
	 */
	public abstract List<? extends EObject> getMatchingEObjects(EObject eObject, ITransformationContext context);

}
