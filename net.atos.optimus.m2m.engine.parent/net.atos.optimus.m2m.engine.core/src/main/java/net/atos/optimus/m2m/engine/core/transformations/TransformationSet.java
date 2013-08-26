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
 * Abstract Transformation Set instance
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public abstract class TransformationSet {

	/**
	 * Transformation Set privacy information
	 */
	private boolean isPrivate = false;

	/**
	 * Transformation set ID
	 */
	private String id;

	/**
	 * Transformation Set Description
	 */
	private String description;
	
	/**
	 * TransformationSetContributor
	 */
	private String contributor;

	/**
	 * 
	 * @return the process that contributed this extension point
	 */
	public String getContributor() {
		return contributor;
	}
	
	/**
	 * Sets the process that contributed this extension point
	 * @param contributor 
	 */
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	
	/**
	 * @return Transformation set ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets Transformation set ID
	 * 
	 * @param id
	 *            : ID
	 */
	void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return transformation set description field
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 *            description of transformation set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets Transformation set privacy
	 * 
	 * @param isPrivate
	 *            privacy
	 */
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	/**
	 * @return transformation set privacy
	 */
	public boolean isPrivate() {
		return isPrivate;
	}

	/**
	 * Returns whether the transformations of this TransformationSet should be
	 * enabled foe the provided EObject
	 * 
	 * @param eObject
	 *            : EObject
	 * @return
	 */
	public abstract boolean isEligible(EObject eObject);

}
