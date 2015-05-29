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
package net.atos.optimus.m2m.engine.masks;

import java.util.Set;
import java.util.Map.Entry;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;

/**
 * Models an editable transformation masks reference
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public abstract class EditableTransformationMaskReference extends TransformationMaskReference {

	/**
	 * Create a new transformation mask reference
	 * 
	 * @param name
	 *            the mask name.
	 * @param description
	 *            the mask description.
	 * @param implementation
	 *            the implementation of the mask.
	 */
	public EditableTransformationMaskReference(String name, String description,
			IEditableTransformationMask implementation) {
		super(name, description, implementation);
	}
	
	/**
	 * Submit the mask modification on the implementation of the mask reeference
	 * 
	 * @param maskModifications
	 *            the mask modifications.
	 */
	public void submitMaskModification(Set<Entry<String, Boolean>> maskModifications){
		((IEditableTransformationMask) this.implementation).submitMaskModification(maskModifications);
	}

	/**
	 * Set a new name to the transformation mask reference
	 * 
	 * @param newName
	 *            the new name to the transformation mask reference.
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Set a new description to the transformation mask reference
	 * 
	 * @param newDescription
	 *            the new description to the transformation mask reference.
	 */
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}

	/**
	 * Suppress the current transformation mask reference
	 * 
	 */
	public abstract void suppressTransformationMaskReference();

}
