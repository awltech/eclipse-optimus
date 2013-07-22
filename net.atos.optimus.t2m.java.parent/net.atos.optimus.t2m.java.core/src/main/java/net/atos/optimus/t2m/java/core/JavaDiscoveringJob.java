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
package net.atos.optimus.t2m.java.core;

import java.io.IOException;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class JavaDiscoveringJob extends WorkspaceJob {

	public JavaDiscoveringJob() {
		super("Java Discovering Job");
		this.setPriority(BUILD);
		this.setUser(true);
	}

	private IJavaProject javaProject;

	public void setJavaProject(IJavaProject javaProject) {
		this.javaProject = javaProject;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {

		Resource javaModelResource = JavaDiscovering.discoverProject(javaProject, monitor);
		
		// For the fun, we write the model on the file system.
		URI uri = URI.createPlatformResourceURI(
				this.javaProject.getProject().getFullPath()
						.append(javaProject.getElementName() + "-" + System.currentTimeMillis() + ".javaxmi")
						.toString(), true);
		javaModelResource.setURI(uri);
		try {
			javaModelResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Status.OK_STATUS;
	}

}
