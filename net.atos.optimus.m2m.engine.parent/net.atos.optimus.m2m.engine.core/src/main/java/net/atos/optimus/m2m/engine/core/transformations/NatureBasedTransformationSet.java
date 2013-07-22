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
package net.atos.optimus.m2m.engine.core.transformations;

import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public abstract class NatureBasedTransformationSet extends TransformationSet {

	private Map<URI, Boolean> modelURIs = new HashMap<URI, Boolean>();

	@Override
	public boolean isEligible(EObject eObject) {
		// Get & validate the resource
		Resource resource = eObject.eResource();
		if (resource == null)
			return false;

		// Get & validate the URI
		URI uri = resource.getURI();
		if (uri == null)
			return false;

		// Check in cache
		if (this.modelURIs.containsKey(uri))
			return this.modelURIs.get(uri);

		// If not in cache, check for project, and cache the value
		IFile file = WorkspaceSynchronizer.getFile(resource);
		IProject project = file.getProject();
		boolean isEligible = false;

		try {
			isEligible = project.hasNature(getNatureName());
			this.modelURIs.put(uri, isEligible);
		} catch (CoreException e) {
			OptimusM2MEngineMessages.TS01.log(e.getClass().getName());
			Activator
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, OptimusM2MEngineMessages.TS01.message(e
							.getClass().getName()), e));
		}
		return isEligible;
	}

	protected abstract String getNatureName();
}
