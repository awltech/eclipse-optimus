package net.atos.optimus.m2m.engine.ui.prefs;

import net.atos.optimus.m2m.engine.core.masks.PreferencesTransformationMask;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

import org.eclipse.jface.viewers.ICheckStateProvider;

/**
 * Check State Provider, used to give initial values to the check boxes of the
 * {@link TransformationsPreferencesPage}
 * 
 * @author mvanbesien
 * 
 */
public class TransformationsTreeCheckProvider implements ICheckStateProvider {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICheckStateProvider#isGrayed(java.lang.Object)
	 */
	public boolean isGrayed(Object element) {
		return !(element instanceof TransformationReference);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ICheckStateProvider#isChecked(java.lang.Object)
	 */
	public boolean isChecked(Object element) {
		if (element instanceof TransformationReference) {
			TransformationReference reference = (TransformationReference) element;
			return PreferencesTransformationMask.getInstance().isTransformationEnabled(reference.getId());
		}
		return false;
	}

}
