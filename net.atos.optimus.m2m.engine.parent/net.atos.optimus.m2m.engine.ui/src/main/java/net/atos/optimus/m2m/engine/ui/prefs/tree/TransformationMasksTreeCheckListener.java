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
package net.atos.optimus.m2m.engine.ui.prefs.tree;

import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TemporaryTransformationMask;
import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
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

public class TransformationMasksTreeCheckListener implements ICheckStateListener {

	/* The temporary transformation mask */
	private TemporaryTransformationMask tmpTransformationMask;

	/** The listened tree viewer */
	private CheckboxTreeViewer treeViewer;

	/**
	 * Constructor
	 * 
	 * @param treeViewer
	 *            the listened tree viewer.
	 * @param tmpTransformationMask
	 *            the temporary transformation mask.
	 */
	public TransformationMasksTreeCheckListener(CheckboxTreeViewer treeViewer,
			TemporaryTransformationMask tmpTransformationMask) {
		this.treeViewer = treeViewer;
		this.tmpTransformationMask = tmpTransformationMask;
	}

	@Override
	public void checkStateChanged(CheckStateChangedEvent event) {
		if (event.getElement() instanceof TransformationReference) {
			// Test if the current mask is editable
			if (this.tmpTransformationMask.getOriginalTransformationMask() instanceof IEditableTransformationMask) {
				TransformationReference transformationReference = (TransformationReference) event.getElement();
				this.tmpTransformationMask
						.setCheckedTransformation(transformationReference.getId(), event.getChecked());
				if (event.getChecked()) {
					Set<TransformationReference> checkedReferences = new HashSet<TransformationReference>();
					this.checkForRequirementsToCheck((TransformationReference) event.getElement(), checkedReferences);
				} else {
					Set<TransformationReference> uncheckedReferences = new HashSet<TransformationReference>();
					this.checkForRequirementsToUncheck((TransformationReference) event.getElement(),
							uncheckedReferences);
				}
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

	/**
	 * This method should only be called when an element is checked from the
	 * preferences view.
	 * 
	 * This method will look recursively into the transformation reference to
	 * know the other transformations required by the current reference. The
	 * required transformations will be checked too.
	 * 
	 * 
	 * @param reference
	 *            the checked transformation reference.
	 * @param checkedReferences
	 *            the reference already check.
	 */
	private void checkForRequirementsToCheck(TransformationReference reference,
			Set<TransformationReference> checkedReferences) {
		for (AbstractRequirement otherReq : reference.getRequirements()) {
			String otherReqId = otherReq.getId();
			TransformationReference otherRef = TransformationDataSourceManager.INSTANCE.getById(otherReqId);
			if (otherReqId != null && !checkedReferences.contains(otherRef)) {
				this.tmpTransformationMask.setCheckedTransformation(otherRef.getId(), true);
				this.treeViewer.refresh(otherRef);
				checkedReferences.add(otherRef);
				checkForRequirementsToCheck(otherRef, checkedReferences);
			}
		}
	}

	/**
	 * This method should only be called when an element is unchecked from the
	 * preferences view.
	 * 
	 * This method will look deeply into the referenced transformations to know
	 * whether the current reference is required by another transformation. If
	 * this is the case, the transformation requiring this one will be unchecked
	 * too.
	 * 
	 * @param reference
	 *            the unchecked transformation reference.
	 * @param uncheckedReferences
	 *            the reference already uncheck.
	 */
	private void checkForRequirementsToUncheck(TransformationReference reference,
			Set<TransformationReference> uncheckedReferences) {
		String referenceId = reference.getId();
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for (TransformationReference otherRef : transformationDataSource.getAll()) {
				for (AbstractRequirement otherReq : otherRef.getRequirements()) {
					String otherReqId = otherReq.getId();
					if (otherReqId.equals(referenceId) && !uncheckedReferences.contains(otherRef)) {
						this.tmpTransformationMask.setCheckedTransformation(otherRef.getId(), false);
						this.treeViewer.refresh(otherRef);
						uncheckedReferences.add(otherRef);
						checkForRequirementsToUncheck(otherRef, uncheckedReferences);
					}
				}
			}
		}
	}

	public void apply() {
		ITransformationMask originalTransformationMask = this.tmpTransformationMask.getOriginalTransformationMask();
		if (originalTransformationMask instanceof IEditableTransformationMask) {
			((IEditableTransformationMask) originalTransformationMask)
					.submitMaskModification(this.tmpTransformationMask.getTransformationMaskModification());
		}
	}

}
