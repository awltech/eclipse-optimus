package net.atos.optimus.m2m.engine.ui.prefs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.masks.PreferencesTransformationMask;
import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.ExtensionPointTransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

/**
 * Check State Listener, used to detect user actions of check boxes from the
 * {@link TransformationsPreferencesPage}
 * 
 * @author mvanbesien
 * 
 */
public class TransformationsTreeCheckListener implements ICheckStateListener {

	private CheckboxTreeViewer treeViewer;

	private Map<TransformationReference, Boolean> userActions = new HashMap<TransformationReference, Boolean>();

	public TransformationsTreeCheckListener(CheckboxTreeViewer treeViewer) {
		this.treeViewer = treeViewer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse
	 * .jface.viewers.CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		if (event.getElement() instanceof TransformationReference) {
			this.userActions.put(((TransformationReference) event.getElement()), event.getChecked());
			if (!event.getChecked()) {
				Set<TransformationReference> uncheckedReferences = new HashSet<TransformationReference>();
				this.checkForRequirementsToUncheck((TransformationReference) event.getElement(), uncheckedReferences);
			}
		}
	}

	/**
	 * This method should only be called when an element in unchecked from the
	 * preferences view.
	 * 
	 * This method will look deeply into the referenced transformations to know
	 * whether the current reference is required by another transformation. If
	 * this is the case, the transformation requiring this one will be unchecked
	 * too.
	 * 
	 * @param reference
	 * @param uncheckedReferences
	 */
	private void checkForRequirementsToUncheck(TransformationReference reference,
			Set<TransformationReference> uncheckedReferences) {
		String referenceId = reference.getId();
		for (TransformationReference otherRef : ExtensionPointTransformationDataSource.instance().getAll()) {
			for (AbstractRequirement otherReq : otherRef.getRequirements()) {
				String otherReqId = otherReq.getId();
				if (otherReqId.equals(referenceId) && !uncheckedReferences.contains(otherRef)) {
					this.userActions.put(otherRef, false);
					this.treeViewer.refresh(otherRef);
					uncheckedReferences.add(otherRef);
					checkForRequirementsToUncheck(otherRef, uncheckedReferences);
				}
			}
		}
	}

	public void apply() {
		for (TransformationReference key : this.userActions.keySet()) {
			PreferencesTransformationMask.getInstance()
					.setTransformationEnabled(key.getId(), this.userActions.get(key));
		}

	}
}
