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

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationSet;
import net.atos.optimus.m2m.engine.ui.prefs.dialog.TransformationsInfoDialogMessages;

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
public class TransformationMasksTreeDoubleClickListener implements IDoubleClickListener {

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