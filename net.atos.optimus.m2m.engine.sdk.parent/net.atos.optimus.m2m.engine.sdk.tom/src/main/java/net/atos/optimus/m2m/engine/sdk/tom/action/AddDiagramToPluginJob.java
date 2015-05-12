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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * A job adding transformation dependency diagram contribution to the plugin.xml
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class AddDiagramToPluginJob extends WorkspaceJob {

	public static final String ADD_TO_PLUGIN_JOB_NAME = "Adding diagram to plugin.xml";

	public static final String EXTENSION_POINT_DIAGRAM = "net.atos.optimus.m2m.engine.sdk.tom.extension.TransformationDependencyDiagrams";

	public static final String CONTRIBUTION_ELEMENT = "transformationDependencyDiagram";

	/** The selected diagrams */
	private Collection<IFile> targetFiles = new ArrayList<IFile>();

	/**
	 * Constructor
	 * 
	 */
	public AddDiagramToPluginJob() {
		super(AddDiagramToPluginJob.ADD_TO_PLUGIN_JOB_NAME);
		this.setUser(true);
		this.setPriority(Job.BUILD);
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		for (IFile targetFile : this.targetFiles) {
			IProject project = targetFile.getProject();
			IFile pluginFile = project.getFile("plugin.xml");
			if (pluginFile.exists()) {
				IPath makeRelativeTo = targetFile.getLocation().makeRelativeTo(project.getLocation());
				try {
					this.addDiagramToPlugin(pluginFile.getLocation().toFile(), makeRelativeTo);
				} catch (JDOMException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * Add a contribution with the diagram to the plugin
	 * 
	 * @param pluginFile
	 *            the plugin file.
	 * @param diagramPath
	 *            the path within the project of the diagram.
	 * @throws IOException
	 * @throws JDOMExceptio
	 */
	protected void addDiagramToPlugin(File pluginFile, IPath diagramPath) throws JDOMException, IOException {
		/** Load the plugin.xml file */
		SAXBuilder sxb = new SAXBuilder();
		Document document = sxb.build(pluginFile);
		Element pluginRoot = document.getRootElement();

		/** Read each extension */
		List<?> extensions = pluginRoot.getChildren("extension");
		Iterator<?> extensionsIt = extensions.iterator();
		boolean newContribution = true;
		Element lastRelevantExtension = null;
		while (extensionsIt.hasNext() && newContribution) {
			Element extension = (Element) extensionsIt.next();
			Attribute pointAttribute = extension.getAttribute("point");

			/** Extension point related to dependency diagram contribution */
			if (pointAttribute != null
					&& AddDiagramToPluginJob.EXTENSION_POINT_DIAGRAM.equals(pointAttribute.getValue())) {
				lastRelevantExtension = extension;
				List<?> diagrams = extension.getChildren(AddDiagramToPluginJob.CONTRIBUTION_ELEMENT);
				Iterator<?> diagramsIt = diagrams.iterator();
				while (diagramsIt.hasNext() && newContribution) {
					Element diagram = (Element) diagramsIt.next();
					Attribute diagramAttribute = diagram.getAttribute("diagram");
					newContribution = diagramAttribute == null
							|| !diagramPath.toString().equals(diagramAttribute.getValue());
				}
			}
		}

		/** Add new contribution if it isn't already in the plugin.xml */
		if (newContribution) {
			Element diagram = new Element(AddDiagramToPluginJob.CONTRIBUTION_ELEMENT);
			Attribute diagramName = new Attribute("diagram", diagramPath.toString());
			diagram.setAttribute(diagramName);
			/**
			 * An extension related to dependency diagram contribution already
			 * exist
			 */
			if (lastRelevantExtension != null) {
				lastRelevantExtension.addContent(diagram);
			} else {
				Element extension = new Element("extension");
				Attribute pointAttribute = new Attribute("point", AddDiagramToPluginJob.EXTENSION_POINT_DIAGRAM);
				extension.setAttribute(pointAttribute);
				extension.addContent(diagram);
				pluginRoot.addContent(extension);
			}

			/** Rewrite the plugin.xml file */
			XMLOutputter output = new XMLOutputter(Format.getPrettyFormat());
			FileOutputStream outputStream = new FileOutputStream(pluginFile);
			output.output(document, outputStream);
			outputStream.close();
		}
	}

	public AddDiagramToPluginJob withTargetFiles(Collection<IFile> targetFiles) {
		this.targetFiles.clear();
		this.targetFiles.addAll(targetFiles);
		return this;
	}

}
