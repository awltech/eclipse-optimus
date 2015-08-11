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
package net.atos.optimus.m2m.engine.ui.prefs.dialog.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Table contents provider for the table associated to available transformation
 * masks in creation page
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AvailableTransformationMasksContentsProvider implements IStructuredContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			List<TransformationMaskReference> transformationMaskReferences = new ArrayList<TransformationMaskReference>();
			for (Object transformationMaskReference : ((Collection<?>) inputElement)) {
				if (transformationMaskReference instanceof TransformationMaskReference) {
					transformationMaskReferences.add((TransformationMaskReference) transformationMaskReference);
				}
			}
			return transformationMaskReferences.toArray();
		}
		return null;
	}

}
