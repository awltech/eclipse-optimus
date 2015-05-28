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
package net.atos.optimus.m2m.engine.ui.prefs.masks.list;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.EditableTransformationMaskReference;
import net.atos.optimus.m2m.engine.ui.prefs.TransformationMasksPreferencesMessages;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Label provider used in the table dedicated to transformation masks
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMasksLabelProvider extends ColumnLabelProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof TransformationMaskReference) {
			TransformationMaskReference transformationMaskReference = (TransformationMaskReference) element;
			if (transformationMaskReference instanceof EditableTransformationMaskReference) {
				return transformationMaskReference.getName();
			}
			return transformationMaskReference.getName() + " "
					+ TransformationMasksPreferencesMessages.NON_EDITABLE_MASK.message();
		}
		return null;
	}

}
