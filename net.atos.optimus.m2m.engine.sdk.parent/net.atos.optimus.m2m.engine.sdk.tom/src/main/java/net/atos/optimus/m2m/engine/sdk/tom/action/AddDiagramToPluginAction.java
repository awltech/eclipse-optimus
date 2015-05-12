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
package net.atos.optimus.m2m.engine.sdk.tom.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;

/**
 * An action to add transformation dependency diagram contribution to the
 * plugin.xml
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AddDiagramToPluginAction implements IActionDelegate {

	/** The selected files */
	private Collection<IFile> targetFiles = new ArrayList<IFile>();

	@Override
	public void run(IAction action) {
		(new AddDiagramToPluginJob()).withTargetFiles(this.targetFiles).schedule();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		this.targetFiles.clear();
		if (selection instanceof IStructuredSelection) {
			Iterator<?> iterator = ((IStructuredSelection) selection).iterator();
			while (iterator.hasNext()) {
				Object next = iterator.next();
				if (next instanceof IFile) {
					this.targetFiles.add((IFile) next);
				}
			}
		}
		action.setEnabled(this.targetFiles.size() > 0);
	}

}
