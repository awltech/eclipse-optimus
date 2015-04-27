package net.atos.optimus.m2m.engine.ui.prefs.mask;

import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSourceManager;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.swt.widgets.Combo;

/**
 * Check State Provider, used to give values to the check boxes according to
 * selected transformation mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationMaskTreeCheckProvider implements ICheckStateProvider {
	
	/** The combo containing the selected transformation mask */
	protected Combo combo;
	
	/** Constructor
	 * 
	 * @param combo the combo containing the selected transformation mask.
	 */
	public TransformationMaskTreeCheckProvider(Combo combo){
		this.combo = combo;
	}

	@Override
	public boolean isChecked(Object element) {
		if (element instanceof TransformationReference) {
			TransformationMaskReference transformationMaskReference = TransformationMaskDataSourceManager.INSTANCE
					.getTransformationMaskById(combo.getText());
			TransformationReference reference = (TransformationReference) element;
			if(transformationMaskReference != null){
				return transformationMaskReference.getImplementation().isTransformationEnabled(reference.getId());
			}
		}
		return false;
	}

	@Override
	public boolean isGrayed(Object element) {
		return !(element instanceof TransformationReference);
	}

}
