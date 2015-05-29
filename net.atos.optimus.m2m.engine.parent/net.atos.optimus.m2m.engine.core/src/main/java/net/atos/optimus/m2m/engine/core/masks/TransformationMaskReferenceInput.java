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
package net.atos.optimus.m2m.engine.core.masks;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A tool dedicated to cache the user input on transformation mask reference.
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMaskReferenceInput {

	/** The input mask name */
	private String inputMaskName;

	/** The input description */
	private String inputDescription;

	/** The map holding the temporary mask */
	private Map<String, Boolean> tmpTransformationMask;

	/** The original transformation mask reference */
	private TransformationMaskReference originalTransformationMaskReference;

	/**
	 * Constructor
	 * 
	 * @param transformationMask
	 *            the original transformation mask of the temporary
	 *            transformation mask.
	 */
	public TransformationMaskReferenceInput(TransformationMaskReference originalTransformationMaskReference) {
		this.inputMaskName = null;
		this.inputDescription = null;
		this.tmpTransformationMask = new HashMap<String, Boolean>();
		this.originalTransformationMaskReference = originalTransformationMaskReference;
	}

	/**
	 * Give the original transformation mask
	 * 
	 * @return the original transformation mask.
	 */
	public TransformationMaskReference getOriginalTransformationMaskReference() {
		return this.originalTransformationMaskReference;
	}

	/**
	 * Give the original transformation mask
	 * 
	 * @return the original transformation mask.
	 */
	public ITransformationMask getOriginalTransformationMask() {
		return this.originalTransformationMaskReference.getImplementation();
	}

	/**
	 * Give the current name of the transformation mask according to the input
	 * 
	 * @return the current name of the transformation mask according to the
	 *         input.
	 */
	public String getName() {
		return this.inputMaskName == null ? this.originalTransformationMaskReference.getName() : this.inputMaskName;
	}

	/**
	 * Set the name of the mask
	 * 
	 * @param inputMaskName
	 *            the new input mask name.
	 */
	public void setName(String inputMaskName) {
		this.inputMaskName = inputMaskName;
	}

	/**
	 * Give the current description of the transformation mask according to the
	 * input
	 * 
	 * @return the current description of the transformation mask according to
	 *         the input.
	 */
	public String getDescription() {
		return this.inputDescription == null ? this.originalTransformationMaskReference.getDescription()
				: this.inputDescription;
	}

	/**
	 * Set the description of the mask
	 * 
	 * @param inputDescription
	 *            the new description of the mask.
	 */
	public void setDescription(String inputDescription) {
		this.inputDescription = inputDescription;
	}

	/**
	 * Reset the temporary mask by set another original transformation mask
	 * 
	 * @param originalTransformationMask
	 *            the new original transformation mask.
	 */
	public void resetTransformationMask(TransformationMaskReference originalTransformationMaskReference) {
		this.inputMaskName = null;
		this.inputDescription = null;
		this.originalTransformationMaskReference = originalTransformationMaskReference;
		this.tmpTransformationMask.clear();
	}

	public boolean isTransformationEnabled(String id) {
		return this.tmpTransformationMask.containsKey(id) ? tmpTransformationMask.get(id) : this
				.getOriginalTransformationMask().isTransformationEnabled(id);
	}

	/**
	 * Set the checked state of a transformation for the current selected mask
	 * 
	 * @param id
	 *            the id of the setting transformation.
	 * @param state
	 *            the state of the transformation associated to the specified
	 *            id.
	 */
	public void setCheckedTransformation(String id, boolean state) {
		this.tmpTransformationMask.put(id, state);
	}

	/**
	 * Return the set of modification on the current selected mask
	 * 
	 * @return the set of modification on the current selected mask.
	 */
	public Set<Entry<String, Boolean>> getTransformationMaskModification() {
		return this.tmpTransformationMask.entrySet();
	}

}
