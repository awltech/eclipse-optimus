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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.requirements.ObjectRequirement;
import net.atos.optimus.m2m.engine.core.requirements.ParentRequirement;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;

/**
 * Transformation Data Source implementation, based on extension points.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class ExtensionPointTransformationDataSource implements ITransformationDataSource {

	/**
	 * Singleton Holder class
	 * 
	 * @author Maxence Vanbésien (mvaawl@gmail.com)
	 * @since 1.0
	 * 
	 */
	private static class SingletonHolder {
		static ExtensionPointTransformationDataSource instance = new ExtensionPointTransformationDataSource();
	}

	/**
	 * @return Transformation Manager single instance
	 */
	public static ExtensionPointTransformationDataSource instance() {
		return SingletonHolder.instance;
	}

	/**
	 * Private Constructor
	 */
	private ExtensionPointTransformationDataSource() {
		this.transformationReferencesMap = new HashMap<String, TransformationReference>();
		this.loadExtensions();
	}

	/**
	 * Map that holds the transformation references, sorted by id
	 */
	private Map<String, TransformationReference> transformationReferencesMap;

	/**
	 * Load the extension points.
	 */
	private void loadExtensions() {
		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				"Transformations");
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				try {
					if ("transformationSet".equals(configurationElement.getName())) {
						String transformationSetId = configurationElement.getAttribute("id");
						String transformationSetPrivacy = configurationElement.getAttribute("private");
						String transformationSetImplementation = configurationElement.getAttribute("implementation");
						TransformationSet lts = null;

						if (transformationSetImplementation != null
								&& transformationSetImplementation.trim().length() > 0)
							lts = (TransformationSet) configurationElement.createExecutableExtension("implementation");

						if (lts == null)
							lts = new DefaultTransformationSet();
						lts.setId(transformationSetId);

						if (transformationSetPrivacy != null)
							lts.setPrivate(Boolean.parseBoolean(transformationSetPrivacy));

						for (IConfigurationElement child : configurationElement.getChildren("transformation"))
							this.loadTransformation(child, lts);
					}
				} catch (CoreException ce) {
					ce.printStackTrace();
				}
			}
		}
	}

	/**
	 * Loads the transformation defined by the configuration element provided as
	 * parameter
	 * 
	 * @param configurationElement
	 *            : configuration element
	 * @param lts
	 *            transformation set that contains the transformation
	 * @throws CoreException
	 */
	private void loadTransformation(IConfigurationElement configurationElement, TransformationSet lts)
			throws CoreException {
		// Load the transformation itself
		String id = configurationElement.getAttribute("id");
		String description = configurationElement.getAttribute("description");

		int priority = 0;

		try {
			priority = Integer.parseInt(configurationElement.getAttribute("priority"));
		} catch (Exception e) {
		}

		if (this.transformationReferencesMap.containsKey(id)) {
			TransformationReference reference = this.transformationReferencesMap.get(id);
			if (reference.getPriority() < priority)
				this.transformationReferencesMap.remove(id);
			else
				return;
		}

		ITransformationFactory transformationFactory = (ITransformationFactory) configurationElement
				.createExecutableExtension("factory");
		TransformationReference loopbackTransformationReference = new TransformationReference(id,
				transformationFactory, lts, description, priority);
		this.transformationReferencesMap.put(id, loopbackTransformationReference);

		// Loads the requirements of the current transformation
		for (IConfigurationElement child : configurationElement.getChildren()) {
			if ("objectRequires".equals(child.getName())) {
				String reqid = child.getAttribute("id");
				ObjectRequirement objectRequirement = new ObjectRequirement();
				objectRequirement.setId(reqid);
				loopbackTransformationReference.addRequirement(objectRequirement);
			} else if ("parentRequires".equals(child.getName())) {
				String reqid = child.getAttribute("id");
				ParentRequirement parentRequirement = new ParentRequirement();
				parentRequirement.setId(reqid);
				loopbackTransformationReference.addRequirement(parentRequirement);
			} else if ("customRequires".equals(child.getName())) {
				String reqid = child.getAttribute("id");
				AbstractRequirement requirement = (AbstractRequirement) child
						.createExecutableExtension("implementation");
				requirement.setId(reqid);
				loopbackTransformationReference.addRequirement(requirement);
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource
	 * #getById(java.lang.String)
	 */
	public TransformationReference getById(String id) {
		return this.transformationReferencesMap.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource
	 * #getAll()
	 */
	public Collection<TransformationReference> getAll() {
		return Collections.unmodifiableCollection(transformationReferencesMap.values());
	}

}
