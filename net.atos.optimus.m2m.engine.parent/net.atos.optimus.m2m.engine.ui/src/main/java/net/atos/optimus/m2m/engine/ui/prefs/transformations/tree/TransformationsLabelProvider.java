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

import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;
import net.atos.optimus.m2m.engine.ui.Activator;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * Label provider used in the tree dedicated to transformations
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationsLabelProvider extends ColumnLabelProvider {

	private static final String ICONS_TRN_GIF = "icons/trn.gif";
	private static final String ICONS_TRNSET_PUBLIC_GIF = "icons/trnset-public.gif";
	private static final String ICONS_TRNSET_PRIVATE_GIF = "icons/trnset-private.gif";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {
		if (element instanceof TransformationSet) {
			if (((TransformationSet) element).isPrivate()) {
				return Activator.getDefault().getImage(ICONS_TRNSET_PRIVATE_GIF);
			} else {
				return Activator.getDefault().getImage(ICONS_TRNSET_PUBLIC_GIF);
			}
		} else if (element instanceof TransformationReference) {
			return Activator.getDefault().getImage(ICONS_TRN_GIF);
		} else if (element instanceof TransformationDataSource) {
			return null;
		}
		return super.getImage(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object element) {
		if (element instanceof TransformationDataSource) {
			TransformationDataSource dataSource = (TransformationDataSource) element;
			return dataSource.getName();
		} else if (element instanceof TransformationSet) {
			TransformationSet transformationSet = (TransformationSet) element;
			return transformationSet.getDescription() + " (" + transformationSet.getId() + ")";
		} else if (element instanceof TransformationReference) {
			TransformationReference reference = (TransformationReference) element;
			return reference.getDescription() + " (" + reference.getId() + ")";
		}
		return null;
	};

}
