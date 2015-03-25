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
package net.atos.optimus.m2m.engine.ui.prefs;

import java.util.ArrayList;
import java.util.Collection;

import net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * Tree Contents provider for the Check box tree of the
 * {@link TransformationsPreferencesPage}
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class TransformationsTreeContentsProvider implements ITreeContentProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ITransformationDataSource) {
			Collection<TransformationReference> references = ((ITransformationDataSource) inputElement).getAll();
			Collection<TransformationSet> sets = new ArrayList<TransformationSet>();
			for (TransformationReference reference : references) {
				TransformationSet set = reference.getTransformationSet();
				if (!sets.contains(set))
					sets.add(set);
			}
			return sets.toArray();
		} else if (inputElement instanceof TransformationSet) {
			TransformationSet thisSet = (TransformationSet) inputElement;
			Collection<TransformationReference> allRefs = thisSet.getTransformationDataSource().getAll();
			Collection<TransformationReference> refs = new ArrayList<TransformationReference>();
			for (TransformationReference reference : allRefs)
				if (reference.getTransformationSet() == thisSet)
					refs.add(reference);
			return refs.toArray();
		}
		return new Object[0];
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof TransformationReference) {
			return ((TransformationReference) element).getTransformationSet();
		} else if (element instanceof TransformationSet) {
			return ((TransformationSet) element).getTransformationDataSource();
		} else if (element instanceof ITransformationDataSource) {
			return TransformationDataSourceManager.INSTANCE;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		return getElements(element).length > 0;
	}

}
