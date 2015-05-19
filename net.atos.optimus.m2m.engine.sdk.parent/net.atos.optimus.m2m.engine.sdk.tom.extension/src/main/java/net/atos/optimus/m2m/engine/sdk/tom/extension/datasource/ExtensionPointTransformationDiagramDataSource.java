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
package net.atos.optimus.m2m.engine.sdk.tom.extension.datasource;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;
import net.atos.optimus.m2m.engine.core.transformations.DefaultTransformationSet;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationFactory;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.sdk.tom.Requirement;
import net.atos.optimus.m2m.engine.sdk.tom.Transformation;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSet;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSetExtension;
import net.atos.optimus.m2m.engine.sdk.tom.TransformationSets;
import net.atos.optimus.m2m.engine.sdk.tom.extension.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.extension.logging.DependencyDiagramMessages;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;

/**
 * Transformation Data Source based on transformation dependency diagrams
 * extension point.
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public class ExtensionPointTransformationDiagramDataSource extends TransformationDataSource {

	/** The description of the transformation data source */
	public static final String DESCRIPTION = "Optimus Transformation Dependency Diagram Extension Point Transformation Data Source";

	/** The name of the associated extension point */
	public static final String EXTENSION_POINT_NAME = "TransformationDependencyDiagrams";

	/** The name of the element in the extension */
	public static final String EXTENSION_ELEMENT = "transformationDependencyDiagram";

	/** The name of the attribute containing the .tom file */
	public static final String ATTRIBUTE_DIAGRAM = "diagram";

	/** The extension of diagram file */
	public static final String EXTENSION_DIAGRAM = ".tom";

	/**
	 * Map that holds the transformation references, sorted by id
	 */
	protected Map<String, TransformationReference> transformationReferencesMap;

	/**
	 * Map that holds the transformation sets (Optimus) by ID.
	 */
	protected Map<String, net.atos.optimus.m2m.engine.core.transformations.TransformationSet> transformationSetsMap;

	/** Map that holds the bundles sorted by associated contributor name */
	protected Map<String, Bundle> bundlesMap;

	/**
	 * Constructor
	 * 
	 */
	public ExtensionPointTransformationDiagramDataSource() {
		super(ExtensionPointTransformationDiagramDataSource.DESCRIPTION);
		this.transformationSetsMap = new HashMap<String, net.atos.optimus.m2m.engine.core.transformations.TransformationSet>();
		this.transformationReferencesMap = new HashMap<String, TransformationReference>();
		this.bundlesMap = new HashMap<String, Bundle>();
		this.loadExtensionsPoint();
	}

	@Override
	public TransformationReference getById(String id) {
		return this.transformationReferencesMap.get(id);
	}

	@Override
	public Collection<TransformationReference> getAll() {
		return Collections.unmodifiableCollection(transformationReferencesMap.values());
	}

	/**
	 * Load the extension points.
	 */
	protected void loadExtensionsPoint() {
		DependencyDiagramMessages.EP01.log();

		/* Create resource set and load transformation sets */
		ResourceSet resourceSet = this.createExtensionResourseSet();

		/* Load transformation set extensions */
		this.loadTransformationSetExtensions(resourceSet);

		/* Load transformation requirements */
		this.loadReferenceRequirements(resourceSet);

		/* Clean resource set */
		DependencyDiagramMessages.EP03.log();
		this.cleanResourceSet(resourceSet);

		DependencyDiagramMessages.EP02.log();
	}

	/**
	 * Create the resource set containing all contributed dependency diagram
	 * 
	 * @return the resource set containing all contributed dependency diagram.
	 */
	protected ResourceSet createExtensionResourseSet() {
		ResourceSet resourceSet = new ResourceSetImpl();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				ExtensionPointTransformationDiagramDataSource.EXTENSION_POINT_NAME);
		for (IExtension extension : extensionPoint.getExtensions()) {

			/* Add the associated bundle in the map */
			String contributorName = extension.getContributor().getName();
			this.bundlesMap.put(contributorName, Platform.getBundle(contributorName));

			DependencyDiagramMessages.EP04.log(contributorName);
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {

				/* Test diagram presence in the extension point */
				if (ExtensionPointTransformationDiagramDataSource.EXTENSION_ELEMENT.equals(configurationElement
						.getName())) {

					String diagramFilename = configurationElement
							.getAttribute(ExtensionPointTransformationDiagramDataSource.ATTRIBUTE_DIAGRAM);
					DependencyDiagramMessages.EP05.log(diagramFilename);
					if (diagramFilename.endsWith(ExtensionPointTransformationDiagramDataSource.EXTENSION_DIAGRAM)) {
						URI fileURI = URI.createPlatformPluginURI(contributorName + "/" + diagramFilename, true);

						/* Load Resource */
						Resource resource = resourceSet.getResource(fileURI, true);
						this.loadTransformationSet(resource, contributorName);
					} else {
						DependencyDiagramMessages.EP06.log(diagramFilename);
					}
				}
			}
		}

		return resourceSet;
	}

	/**
	 * Load a transformation set associated to a resource and put it in the
	 * associated map
	 * 
	 * @param resource
	 *            the resource.
	 * @param contributorName
	 *            the contributor name associated to the resource.
	 */
	protected void loadTransformationSet(Resource resource, String contributorName) {
		Bundle bundle = this.bundlesMap.get(contributorName);

		for (EObject root : resource.getContents()) {

			/* Instance of meta-model */
			if (root instanceof TransformationSets) {
				DependencyDiagramMessages.EP07.log();

				/* Load all transformation sets */
				for (TransformationSet transformationSet : ((TransformationSets) root).getTransformationSets()) {

					/* Optimus transformation set creation */
					net.atos.optimus.m2m.engine.core.transformations.TransformationSet optimusTransformationSet = null;
					try {
						optimusTransformationSet = (net.atos.optimus.m2m.engine.core.transformations.TransformationSet) bundle
								.loadClass(transformationSet.getImplementation()).newInstance();
					} catch (InstantiationException e) {
						DependencyDiagramMessages.EP08.log(transformationSet.getName());
						optimusTransformationSet = new DefaultTransformationSet();
					} catch (IllegalAccessException e) {
						DependencyDiagramMessages.EP08.log(transformationSet.getName());
						optimusTransformationSet = new DefaultTransformationSet();
					} catch (ClassNotFoundException e) {
						DependencyDiagramMessages.EP08.log(transformationSet.getName());
						optimusTransformationSet = new DefaultTransformationSet();
					}

					/* Fill Optimus transformation set */
					optimusTransformationSet.setContributor(contributorName);
					optimusTransformationSet.setId(transformationSet.getName());
					optimusTransformationSet.setDescription(transformationSet.getDescription());
					optimusTransformationSet.setPrivate(transformationSet.isPrivate());
					optimusTransformationSet.setTransformationDataSource(this);
					this.transformationSetsMap.put(optimusTransformationSet.getId(), optimusTransformationSet);
					DependencyDiagramMessages.EP09.log(transformationSet.getName(),
							transformationSet.getImplementation());

					/*
					 * Load all transformation reference in this transformation
					 * set
					 */
					for (Transformation transformation : transformationSet.getTransformations()) {
						this.loadTransformationReference(transformation, optimusTransformationSet.getId());
					}
				}
			}
		}
	}

	/**
	 * Load transformation set extensions
	 * 
	 * @param resourceSet
	 *            the resource set of dependency diagram.
	 */
	protected void loadTransformationSetExtensions(ResourceSet resourceSet) {
		for (Resource resource : resourceSet.getResources()) {
			for (EObject root : resource.getContents()) {

				/* Instance of meta-model */
				if (root instanceof TransformationSets) {
					for (TransformationSetExtension transformationSetExtension : ((TransformationSets) root)
							.getTransformationSetExtensions()) {
						/* Test if original transformation set exists */
						net.atos.optimus.m2m.engine.core.transformations.TransformationSet optimusTransformationSet = this.transformationSetsMap
								.get(transformationSetExtension.getTransformationSetId());
						if (optimusTransformationSet != null) {
							DependencyDiagramMessages.EP16.log(transformationSetExtension.getTransformationSetId());

							/*
							 * Load all transformation reference in this
							 * transformation set extension
							 */
							for (Transformation transformation : transformationSetExtension.getTransformations()) {
								this.loadTransformationReference(transformation, optimusTransformationSet.getId());
							}
						} else {
							DependencyDiagramMessages.EP17.log(transformationSetExtension.getTransformationSetId());
						}
					}
				}
			}
		}
	}

	/**
	 * Load a transformation
	 * 
	 * @param transformation
	 *            the transformation to load.
	 * @param transformationSetName
	 *            the transformation set name associated to the transformation.
	 */
	protected void loadTransformationReference(Transformation transformation, String transformationSetName) {
		TransformationReference transformationReference = this.transformationReferencesMap
				.get(transformation.getName());
		net.atos.optimus.m2m.engine.core.transformations.TransformationSet optimusTransformationSet = this.transformationSetsMap
				.get(transformationSetName);
		Bundle bundle = this.bundlesMap.get(optimusTransformationSet.getContributor());

		/* Test the priority of the transformation if exists already one */
		if (transformationReference == null || transformationReference.getPriority() < transformation.getPriority()) {

			/* Transformation factory creation */
			ITransformationFactory transformationFactory = null;
			try {
				transformationFactory = (ITransformationFactory) bundle.loadClass(transformation.getFactory())
						.newInstance();
			} catch (InstantiationException e) {
				handlingExceptionLoadingTransformation(e, transformation);
			} catch (IllegalAccessException e) {
				handlingExceptionLoadingTransformation(e, transformation);
			} catch (ClassNotFoundException e) {
				handlingExceptionLoadingTransformation(e, transformation);
			}

			/* Transformation reference creation */
			transformationReference = new TransformationReference(transformation.getName(), transformationFactory,
					optimusTransformationSet, transformation.getDescription(), transformation.getPriority());
			transformationReference.setContributor(optimusTransformationSet.getContributor());
			DependencyDiagramMessages.EP11.log(transformation.getName(), transformation.getFactory());
			this.transformationReferencesMap.put(transformation.getName(), transformationReference);

		}
	}

	/**
	 * Load transformation requirements
	 * 
	 * @param transformation
	 *            the transformation to load associated requirements.
	 * @param transformationSetName
	 *            the transformation set name associated to the transformation.
	 */
	protected void loadReferenceRequirements(ResourceSet resourceSet) {
		for (Resource resource : resourceSet.getResources()) {
			for (EObject root : resource.getContents()) {

				/* Instance of meta-model */
				if (root instanceof TransformationSets) {
					for (TransformationSet transformationSet : ((TransformationSets) root).getTransformationSets()) {
						for (Transformation transformation : transformationSet.getTransformations()) {
							this.loadTransformationRequirements(transformation, transformationSet.getName());
						}
					}
					for (TransformationSetExtension transformationSetExtension : ((TransformationSets) root)
							.getTransformationSetExtensions()) {
						if (this.transformationSetsMap.containsKey(transformationSetExtension.getTransformationSetId())) {
							for (Transformation transformation : transformationSetExtension.getTransformations()) {
								this.loadTransformationRequirements(transformation,
										transformationSetExtension.getTransformationSetId());
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Load requirements for a specified transformation
	 * 
	 * @param transformation
	 * @param transformationSetName
	 */
	protected void loadTransformationRequirements(Transformation transformation, String transformationSetName) {
		TransformationReference transformationReference = this.transformationReferencesMap
				.get(transformation.getName());
		net.atos.optimus.m2m.engine.core.transformations.TransformationSet optimusTransformationSet = this.transformationSetsMap
				.get(transformationSetName);
		Bundle bundle = this.bundlesMap.get(optimusTransformationSet.getContributor());

		if (transformationReference.getPriority() == transformation.getPriority()) {
			for (Requirement requirement : transformation.getRequirements()) {
				if (this.transformationReferencesMap.containsKey(requirement.getReference().getName())) {
					AbstractRequirement abstractRequirement = AbstractRequirementChainFactory.INSTANCE
							.createAbstractRequirement(transformationReference, requirement, bundle);
					if(abstractRequirement != null){
						transformationReference.addRequirement(abstractRequirement);
					}
				} else {
					DependencyDiagramMessages.EP18.log(requirement.getReference().getName());
				}
			}
		}
	}

	/**
	 * Clean a resource set
	 * 
	 * @param resourceSet
	 *            the resource set to clean.
	 */
	protected void cleanResourceSet(ResourceSet resourceSet) {

		for (Resource resource : resourceSet.getResources()) {
			resource.unload();
		}
		resourceSet.getResources().clear();
		this.bundlesMap.clear();
	}

	/**
	 * Handler when loading transformation encountered an exception
	 * 
	 * @param e
	 *            the exception.
	 * @param transformation
	 *            the transformation attended to load.
	 */
	protected void handlingExceptionLoadingTransformation(Exception e, Transformation transformation) {
		DependencyDiagramMessages.EP10.log(e.getMessage());
		Activator
				.getDefault()
				.getLog()
				.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Invalid transformation set implementation : "
						+ transformation.getFactory(), e));
	}

}
