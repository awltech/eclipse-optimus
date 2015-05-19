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
package net.atos.optimus.m2m.engine.sdk.tom.properties.listeners;

import net.atos.optimus.m2m.engine.sdk.tom.properties.transformations.TransformationGeneratorDialog;
import net.atos.optimus.m2m.engine.sdk.tom.properties.transformations.TransformationGenerationJob;
import net.atos.optimus.m2m.engine.sdk.tom.properties.zones.TransformationGeneratorSelectorZone;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A listener dedicated to launch the transformation generation process
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationGeneratorListener implements SelectionListener {

	/** The listened text zone */
	protected TransformationGeneratorSelectorZone textZone;

	/**
	 * Constructor
	 * 
	 * @param textZone
	 *            the listened text zone.
	 */
	public TransformationGeneratorListener(TransformationGeneratorSelectorZone textZone) {
		this.textZone = textZone;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		IProject project = WorkspaceSynchronizer.getFile(this.textZone.giveEObject().eResource()).getProject();
		IJavaProject javaProject = JavaCore.create(project);
		Shell shell = new Shell(Display.getCurrent() != null ? Display.getCurrent() : Display.getDefault(),
				SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM | SWT.RESIZE);
		TransformationGeneratorDialog dialog = new TransformationGeneratorDialog(shell, javaProject);
		if(dialog.open() ==  Window.OK){
			TransformationGenerationJob job = new TransformationGenerationJob();
			job.setFragment(dialog.getFragment());
			job.setFactoryName(dialog.getFactory());
			job.setClassName(dialog.getTrn());
			job.setElementName(dialog.getType());
			job.schedule();
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
