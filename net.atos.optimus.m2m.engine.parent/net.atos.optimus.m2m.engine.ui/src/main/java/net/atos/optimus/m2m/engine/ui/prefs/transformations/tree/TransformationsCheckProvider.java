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
package net.atos.optimus.m2m.engine.ui.prefs.transformations.tree;

import net.atos.optimus.m2m.engine.core.masks.TemporaryTransformationMask;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

import org.eclipse.jface.viewers.ICheckStateProvider;

/**
 * Check State Provider, used to give values to the check boxes according to
 * selected transformation mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationsCheckProvider implements ICheckStateProvider {

	/** The temporary transformation mask */
	private TemporaryTransformationMask tmpTransformationMask;

	/**
	 * Constructor
	 * 
	 * @param tmpTransformationMask
	 *            the temporary transformation mask.
	 */
	public TransformationsCheckProvider(TemporaryTransformationMask tmpTransformationMask) {
		this.tmpTransformationMask = tmpTransformationMask;
	}

	@Override
	public boolean isChecked(Object element) {
		if (element instanceof TransformationReference) {
			TransformationReference reference = (TransformationReference) element;
			return tmpTransformationMask.isTransformationEnabled(reference.getId());
		}
		return false;
	}

	@Override
	public boolean isGrayed(Object element) {
		return !(element instanceof TransformationReference);
	}

}
