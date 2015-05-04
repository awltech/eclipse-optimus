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
 * Implementation of Transformation mask dedicated to cache the user selection.
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TemporaryTransformationMask implements ITransformationMask {

	/** The map holding the temporary mask */
	private Map<String, Boolean> tmpTransformationMask;

	/** The original transformation mask */
	private ITransformationMask originalTransformationMask;

	/**
	 * Constructor
	 * 
	 * @param transformationMask
	 *            the original transformation mask of the temporary
	 *            transformation mask.
	 */
	public TemporaryTransformationMask(ITransformationMask originalTransformationMask) {
		this.tmpTransformationMask = new HashMap<String, Boolean>();
		this.originalTransformationMask = originalTransformationMask;
	}

	/**
	 * Give the original transformation mask
	 * 
	 * @return the original transformation mask.
	 */
	public ITransformationMask getOrginalTransformationMask() {
		return this.originalTransformationMask;
	}

	/**
	 * Reset the temporary mask by set another original transformation mask
	 * 
	 * @param originalTransformationMask
	 *            the new original transformation mask.
	 */
	public void resetTransformationMask(ITransformationMask originalTransformationMask) {
		this.originalTransformationMask = originalTransformationMask;
		this.tmpTransformationMask.clear();
	}

	@Override
	public boolean isTransformationEnabled(String id) {
		return this.tmpTransformationMask.containsKey(id) ? tmpTransformationMask.get(id)
				: this.originalTransformationMask.isTransformationEnabled(id);
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
