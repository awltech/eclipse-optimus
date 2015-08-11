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

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Label provider used in the table dedicated to selected transformation masks
 * in creation page
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */
public class SelectedTransformationMasksLabelProvider extends ColumnLabelProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof SelectedTransformationMask) {
			SelectedTransformationMask selectedTransformationMaskReference = (SelectedTransformationMask) element;
			StringBuffer s = new StringBuffer();
			s.append(selectedTransformationMaskReference.getTransformationMaskReference().getName());
			s.append(selectedTransformationMaskReference.isInclusive() ? " (inclusive)" : " (exclusive)");
			return s.toString();
		}
		return null;
	}

}
