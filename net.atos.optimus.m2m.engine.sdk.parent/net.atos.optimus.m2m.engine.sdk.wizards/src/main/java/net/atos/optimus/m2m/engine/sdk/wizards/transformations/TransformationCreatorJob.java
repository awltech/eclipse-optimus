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

import java.util.Properties;

import net.atos.optimus.m2m.engine.sdk.wizards.Activator;
import net.atos.optimus.m2m.engine.sdk.wizards.Messages;
import net.atos.optimus.m2m.javaxmi.core.templating.PackageChunker;
import net.atos.optimus.m2m.javaxmi.core.templating.TemplateVariableReplacer;
import net.atos.optimus.m2t.java.core.JavaGenerator;

import org.eclipse.core.resources.IFile;
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
import org.eclipse.gmt.modisco.xml.Attribute;
import org.eclipse.gmt.modisco.xml.Element;
import org.eclipse.gmt.modisco.xml.Node;
import org.eclipse.gmt.modisco.xml.Root;
import org.eclipse.gmt.modisco.xml.emf.MoDiscoXMLFactory;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class TransformationCreatorJob extends WorkspaceJob {

	private static final String VAR_PACKAGENAME = "${packageName}";

	private static final String VAR_TRANSFO_ELT_FQN = "${transformedElementFQN}";

	private static final String VAR_TRANSFO_CLASSNAME = "${transformationClassName}";

	private static final String VAR_TRANSFO_FACTORYNAME = "${transformationFactoryName}";

	private static final String PLUGIN_FILENAME = "plugin.xml";

	private static final String KEYWORD_PLUGIN = "plugin";

	private static final String KEYWORD_EXTENSION = "extension";

	private static final String KEYWORD_POINT = "point";

	private static final String KEYWORD_ID = "id";

	private static final String EXTENSION_ID = "net.atos.optimus.m2m.engine.core.Transformations";

	private static final String KEYWORD_TRNSET = "transformationSet";

	private static final String KEYWORD_TRN = "transformation";

	private static final String KEYWORD_FACTORY = "factory";

	private static final String KEYWORD_DESC = "description";

	private static final String KEYWORD_PRIORITY = "priority";

	private static final String DEFAULT_DESCRIPTION = "";

	private static final String DEFAULT_PRIORITY = "0";

	private static final String FQN_PATTERN = "%s.%s";

	private static final String MODEL2USE_FILEPATH = "/models/transformationCreator.javaxmi";

	private IPackageFragment fragment;

	private String className;

	private String factoryName;

	private String elementType;

	private String transformationSetName;

	private String id;

	public TransformationCreatorJob() {
		super(Messages.JOBNAME.message());
		this.setPriority(BUILD);
		this.setUser(true);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {

		monitor.beginTask(Messages.JOBTASK.message(), 4);

		monitor.subTask(Messages.JOB_SUBTASK_1.message());

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

		monitor.subTask(Messages.JOB_SUBTASK_2.message());
		this.generateJava(resourceSet, properties);
		monitor.worked(1);
		monitor.subTask(Messages.JOB_SUBTASK_3.message());
		this.generateXML(resourceSet, properties);
		monitor.worked(1);

		monitor.subTask(Messages.JOB_SUBTASK_4.message());
		for (Resource r : resourceSet.getResources())
			r.unload();
		resourceSet.getResources().clear();
		monitor.worked(1);
		monitor.done();

		return Status.OK_STATUS;
	}

	private void generateXML(ResourceSet resourceSet, Properties properties) throws CoreException {
		IFile file = this.fragment.getJavaProject().getProject().getFile(PLUGIN_FILENAME);
		URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
		Root xmlRoot = null;

		if (file.exists()) {
			Resource resource = resourceSet.getResource(uri, true);
			xmlRoot = (Root) resource.getContents().get(0);
		} else {
			Resource resource = resourceSet.createResource(uri);
			xmlRoot = MoDiscoXMLFactory.eINSTANCE.createRoot();
			xmlRoot.setName(KEYWORD_PLUGIN);
			resource.getContents().add(xmlRoot);
		}

		Element extensionPoint = findElementWithAttribute(xmlRoot, KEYWORD_EXTENSION, KEYWORD_POINT, EXTENSION_ID);
		if (extensionPoint == null) {
			extensionPoint = MoDiscoXMLFactory.eINSTANCE.createElement();
			extensionPoint.setName(KEYWORD_EXTENSION);
			extensionPoint.setParent(xmlRoot);

			Attribute attribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
			attribute.setName(KEYWORD_POINT);
			attribute.setValue(EXTENSION_ID);
			attribute.setParent(extensionPoint);
		}

		Element transformationSet = findElementWithAttribute(extensionPoint, KEYWORD_TRNSET, KEYWORD_ID,
				this.transformationSetName);
		if (transformationSet == null) {
			transformationSet = MoDiscoXMLFactory.eINSTANCE.createElement();
			transformationSet.setName(KEYWORD_TRNSET);
			transformationSet.setParent(extensionPoint);

			Attribute attribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
			attribute.setName(KEYWORD_ID);
			attribute.setValue(this.transformationSetName);
			attribute.setParent(transformationSet);
		}

		Element transformation = MoDiscoXMLFactory.eINSTANCE.createElement();
		transformation.setName(KEYWORD_TRN);
		transformation.setParent(transformationSet);

		Attribute descriptionAttribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
		descriptionAttribute.setName(KEYWORD_DESC);
		descriptionAttribute.setValue(DEFAULT_DESCRIPTION);
		descriptionAttribute.setParent(transformation);

		Attribute factoryAttribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
		factoryAttribute.setName(KEYWORD_FACTORY);
		factoryAttribute.setValue(String.format(FQN_PATTERN, this.fragment.getElementName(),
				properties.get(VAR_TRANSFO_FACTORYNAME)));
		factoryAttribute.setParent(transformation);

		Attribute idAttribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
		idAttribute.setName(KEYWORD_ID);
		idAttribute.setValue(id);
		idAttribute.setParent(transformation);

		Attribute priorityAttribute = MoDiscoXMLFactory.eINSTANCE.createAttribute();
		priorityAttribute.setName(KEYWORD_PRIORITY);
		priorityAttribute.setValue(DEFAULT_PRIORITY);
		priorityAttribute.setParent(transformation);

		try {
			xmlRoot.eResource().save(null);
			file.getParent().refreshLocal(IResource.DEPTH_ONE, null);
		} catch (Exception e) {

		}
	}

	private static Element findElementWithAttribute(Element root, String elementName, String attributeName,
			String attributeValue) {
		for (Node child : root.getChildren()) {
			if (child instanceof Element) {
				Element element = (Element) child;
				if (element.getName().equals(elementName)) {
					for (Node subChild : element.getChildren()) {
						if (subChild instanceof Attribute) {
							Attribute attribute = (Attribute) subChild;
							if (attribute.getName().equals(attributeName)
									&& attribute.getValue().equals(attributeValue))
								return element;
						}
					}
				}
			}
		}
		return null;
	}

	private void generateJava(ResourceSet resourceSet, Properties properties) throws CoreException {
		JavaGenerator javaGenerator = new JavaGenerator();
		IPath outputPath = fragment.getAncestor(IJavaElement.PACKAGE_FRAGMENT_ROOT).getResource().getLocation();

		URI uri = URI.createPlatformPluginURI(Activator.PLUGIN_ID + MODEL2USE_FILEPATH, true);
		Resource resource = resourceSet.getResource(uri, true);

		EObject root = resource.getContents().get(0);
		TemplateVariableReplacer.replace(root, properties);
		PackageChunker.chunkPackages((Model) root);
		javaGenerator.generate((Model) root, outputPath);

		this.fragment.getResource().refreshLocal(IResource.DEPTH_INFINITE, null);

	}

	public TransformationCreatorJob setFragment(IPackageFragment fragment) {
		this.fragment = fragment;
		return this;
	}

	public TransformationCreatorJob setClassName(String className) {
		this.className = className;
		return this;
	}

	public TransformationCreatorJob setFactoryName(String factoryName) {
		this.factoryName = factoryName;
		return this;
	}

	public TransformationCreatorJob setElementName(String elementType) {
		this.elementType = elementType;
		return this;
	}

	public TransformationCreatorJob setTransformationSet(String trnSet) {
		this.transformationSetName = trnSet;
		return this;

	}

	public TransformationCreatorJob setId(String id) {
		this.id = id;
		return this;

	}

}
