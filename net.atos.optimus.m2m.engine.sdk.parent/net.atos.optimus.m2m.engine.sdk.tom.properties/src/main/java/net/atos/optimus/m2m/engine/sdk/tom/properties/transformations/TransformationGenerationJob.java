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
package net.atos.optimus.m2m.engine.sdk.tom.properties.transformations;

import java.util.Properties;

import net.atos.optimus.m2m.javaxmi.core.templating.PackageChunker;
import net.atos.optimus.m2m.javaxmi.core.templating.TemplateVariableReplacer;
import net.atos.optimus.m2t.java.core.JavaGenerator;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

/**
 * A job generating transformation
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class TransformationGenerationJob extends WorkspaceJob {

	private static final String VAR_PACKAGENAME = "${packageName}";

	private static final String VAR_TRANSFO_ELT_FQN = "${transformedElementFQN}";

	private static final String VAR_TRANSFO_CLASSNAME = "${transformationClassName}";

	private static final String VAR_TRANSFO_FACTORYNAME = "${transformationFactoryName}";

	/** The package fragment of the generated transformation */
	private IPackageFragment fragment;

	/** The class name of the generated transformation */
	private String className;

	/** The factory name of the generated transformation */
	private String factoryName;

	/** The type name of the transformed element */
	private String elementType;

	public TransformationGenerationJob() {
		super(TransformationDialogMessages.JOBNAME.message());
		this.setPriority(BUILD);
		this.setUser(true);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {

		monitor.beginTask(TransformationDialogMessages.JOBTASK.message(), 4);

		monitor.subTask(TransformationDialogMessages.JOB_SUBTASK_1.message());

		if (!fragment.exists()) {
			IPackageFragmentRoot root = (IPackageFragmentRoot) this.fragment
					.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT);
			this.fragment = root.createPackageFragment(this.fragment.getElementName(), false, monitor);
			root.getResource().refreshLocal(IResource.DEPTH_ONE, monitor);
		}

		Properties properties = new Properties();
		properties.put(VAR_PACKAGENAME, this.fragment.getElementName());
		properties.put(VAR_TRANSFO_ELT_FQN, elementType);
		properties.put(VAR_TRANSFO_CLASSNAME, className);
		properties.put(VAR_TRANSFO_FACTORYNAME, factoryName);
		ResourceSet resourceSet = new ResourceSetImpl();
		monitor.worked(1);

		monitor.subTask(TransformationDialogMessages.JOB_SUBTASK_2.message());

		this.generateJava(resourceSet, properties);
		monitor.worked(1);

		monitor.subTask(TransformationDialogMessages.JOB_SUBTASK_3.message());
		for (Resource r : resourceSet.getResources())
			r.unload();
		resourceSet.getResources().clear();
		monitor.worked(1);
		monitor.done();

		return Status.OK_STATUS;
	}

	private void generateJava(ResourceSet resourceSet, Properties properties) throws CoreException {
		JavaGenerator javaGenerator = new JavaGenerator();
		IPath outputPath = fragment.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT).getResource().getLocation();
		
		URI uri = URI.createPlatformPluginURI("net.atos.optimus.m2m.engine.sdk.wizards/models/transformationCreator.javaxmi", true);
		Resource resource = resourceSet.getResource(uri, true);

		EObject root = resource.getContents().get(0);
		TemplateVariableReplacer.replace(root, properties);
		PackageChunker.chunkPackages((Model) root);
		javaGenerator.generate((Model) root, outputPath);

		this.fragment.getResource().refreshLocal(IResource.DEPTH_INFINITE, null);
	}

	public TransformationGenerationJob setFragment(IPackageFragment fragment) {
		this.fragment = fragment;
		return this;
	}

	public TransformationGenerationJob setClassName(String className) {
		this.className = className;
		return this;
	}

	public TransformationGenerationJob setFactoryName(String factoryName) {
		this.factoryName = factoryName;
		return this;
	}

	public TransformationGenerationJob setElementName(String elementType) {
		this.elementType = elementType;
		return this;
	}

}
