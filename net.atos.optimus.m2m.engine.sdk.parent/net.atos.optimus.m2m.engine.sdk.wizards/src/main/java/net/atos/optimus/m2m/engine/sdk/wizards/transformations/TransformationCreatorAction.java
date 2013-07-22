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
package net.atos.optimus.m2m.engine.sdk.wizards.transformations;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class TransformationCreatorAction implements IObjectActionDelegate {

	private IJavaProject project;

	@Override
	public void run(IAction action) {

		TransformationCreatorPreferences preferences = new TransformationCreatorPreferences(project.getProject());
		
		
		TransformationCreatorDialog transformationCreatorDialog = new TransformationCreatorDialog(project, preferences);
		if (transformationCreatorDialog.open()) {

			preferences.setClassName(transformationCreatorDialog.getTrn());
			preferences.setFactoryName(transformationCreatorDialog.getFactory());
			preferences.setElementName(transformationCreatorDialog.getType());
			preferences.setFragmentName(transformationCreatorDialog.getPackageFragment().getElementName());
			preferences.setTransformationSetName(transformationCreatorDialog.getTrnSet());
			preferences.setTransformationName(transformationCreatorDialog.getId());
			
			TransformationCreatorJob transformationCreatorJob = new TransformationCreatorJob();
			transformationCreatorJob.setClassName(transformationCreatorDialog.getTrn());
			transformationCreatorJob.setFactoryName(transformationCreatorDialog.getFactory());
			transformationCreatorJob.setElementName(transformationCreatorDialog.getType());
			transformationCreatorJob.setFragment(transformationCreatorDialog.getPackageFragment());
			transformationCreatorJob.setTransformationSet(transformationCreatorDialog.getTrnSet());
			transformationCreatorJob.setId(transformationCreatorDialog.getId());
			transformationCreatorJob.schedule();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.project = null;
		if (selection instanceof IStructuredSelection) {
			Object o = ((IStructuredSelection) selection).getFirstElement();
			if (o instanceof IJavaProject)
				this.project = (IJavaProject) o;
		}
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

}
