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

import java.util.Set;

import net.atos.optimus.m2m.engine.core.masks.MaskTransformationRequirementsTool;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReferenceInput;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.masks.IEditableTransformationMask;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

/**
 * Check State Listener, used to detect user actions of check boxes associated
 * to the transformation enables/disables in the mask
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationsTreeCheckListener implements ICheckStateListener {

	/* The temporary transformation mask */
	private TransformationMaskReferenceInput transformationMaskReferenceInput;

	/** The listened tree viewer */
	private CheckboxTreeViewer treeViewer;

	/**
	 * Constructor
	 * 
	 * @param treeViewer
	 *            the listened tree viewer.
	 * @param transformationMaskReferenceInput
	 *            the transformation mask reference input.
	 */
	public TransformationsTreeCheckListener(CheckboxTreeViewer treeViewer,
			TransformationMaskReferenceInput transformationMaskReferenceInput) {
		this.treeViewer = treeViewer;
		this.transformationMaskReferenceInput = transformationMaskReferenceInput;
	}

	@Override
	public void checkStateChanged(CheckStateChangedEvent event) {
		if (event.getElement() instanceof TransformationReference) {
			// Test if the current mask is editable
			if (this.transformationMaskReferenceInput.getOriginalTransformationMask() instanceof IEditableTransformationMask) {
				TransformationReference transformationReference = (TransformationReference) event.getElement();
				this.transformationMaskReferenceInput.setCheckedTransformation(transformationReference.getId(),
						event.getChecked());
				if (event.getChecked()) {
					Set<String> checkedReferences = MaskTransformationRequirementsTool.requirementsToActivate(
							this.transformationMaskReferenceInput, (TransformationReference) event.getElement());
					for (String transfoId : checkedReferences) {
						this.transformationMaskReferenceInput.setCheckedTransformation(transfoId, true);
					}
				} else {
					Set<String> uncheckedReferences = MaskTransformationRequirementsTool.requirementsToDesactivate(
							this.transformationMaskReferenceInput, (TransformationReference) event.getElement());
					for (String transfoId : uncheckedReferences) {
						this.transformationMaskReferenceInput.setCheckedTransformation(transfoId, false);
					}
				}
				this.treeViewer.refresh();
			} else {
				if (event.getSource() instanceof CheckboxTreeViewer) {
					if (event.getChecked()) {
						CheckboxTreeViewer checkBox = (CheckboxTreeViewer) event.getSource();
						checkBox.setChecked(event.getElement(), false);
					} else {
						CheckboxTreeViewer checkBox = (CheckboxTreeViewer) event.getSource();
						checkBox.setChecked(event.getElement(), true);
					}
				}
			}
		}
	}

}
