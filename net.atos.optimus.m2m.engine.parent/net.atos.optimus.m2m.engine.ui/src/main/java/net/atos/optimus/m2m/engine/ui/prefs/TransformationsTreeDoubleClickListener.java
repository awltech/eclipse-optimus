package net.atos.optimus.m2m.engine.ui.prefs;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;

/**
 * Double Click Listener that opens a dialog with information on the selected element.
 * @author mvanbesien
 *
 */
public class TransformationsTreeDoubleClickListener implements IDoubleClickListener {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	@Override
	public void doubleClick(DoubleClickEvent event) {
		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection) {
			Object element = ((IStructuredSelection) selection).getFirstElement();
			if (element instanceof TransformationSet) {
				this.openForTransformationSet((TransformationSet) element);
			} else if (element instanceof TransformationReference) {
				this.openForTransformation((TransformationReference) element);
			}
		}

	}

	private void openForTransformationSet(TransformationSet element) {
		StringBuilder message = new StringBuilder();
		message.append(TransformationsInfoDialogMessages.ID_LABEL.value(element.getId()));
		message.append(TransformationsInfoDialogMessages.DESCRIPTION_LABEL.value(element.getDescription()));
		message.append(TransformationsInfoDialogMessages.CONTRIBUTOR_LABEL.value(element.getContributor()));
		
		MessageDialog.openInformation(new Shell(), TransformationsInfoDialogMessages.TRNSET_LABEL.value(), message.toString());

	}

	private void openForTransformation(TransformationReference element) {
		StringBuilder message = new StringBuilder();
		message.append(TransformationsInfoDialogMessages.ID_LABEL.value(element.getId()));
		message.append(TransformationsInfoDialogMessages.DESCRIPTION_LABEL.value(element.getDescription()));
		message.append(TransformationsInfoDialogMessages.CONTRIBUTOR_LABEL.value(element.getContributor()));
		
		message.append(TransformationsInfoDialogMessages.REQUIRES_LABEL.value());
		for (AbstractRequirement requirement : element.getRequirements()) {
			message.append(TransformationsInfoDialogMessages.REQUIRES_ITEM_LABEL.value(requirement.getClass().getSimpleName(),
					requirement.getId()));
		}
		
		MessageDialog.openInformation(new Shell(), TransformationsInfoDialogMessages.TRN_LABEL.value(), message.toString());

	}
}
