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
package net.atos.optimus.m2m.engine.masks.extension;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;
import net.atos.optimus.m2m.engine.masks.Activator;
import net.atos.optimus.m2m.engine.masks.JavaTransformationMask;
import net.atos.optimus.m2m.engine.masks.MaskTransformationRequirementsTool;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * Implementation of Transformation Masks Extension Point management
 * 
 * @author mvanbesien
 * @since 1.1
 * 
 */
public class ExtensionPointTransformationMaskDataSource extends TransformationMaskDataSource {

	/** The description of the transformation data source */
	public static final String DESCRIPTION = "Optimus Transformation Mask Extension Point";

	/** The name of the associated extension point */
	public static final String EXTENSION_POINT_NAME = "TransformationMasks";

	/**
	 * List of registered masks, read from extension point
	 */
	private final List<TransformationMaskReference> registeredMasks;

	/**
	 * Constructor
	 */
	public ExtensionPointTransformationMaskDataSource() {
		super(ExtensionPointTransformationMaskDataSource.DESCRIPTION);
		OptimusM2MMaskMessages.ML01.log();

		Map<String, TransformationMaskReference> foundMasks = new HashMap<String, TransformationMaskReference>();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				ExtensionPointTransformationMaskDataSource.EXTENSION_POINT_NAME);

		for (IExtension extension : extensionPoint.getExtensions()) {
			String contributorName = extension.getContributor().getName();
			OptimusM2MMaskMessages.ML02.log(contributorName);

			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				if ("implementedMask".equals(configurationElement.getName())) {
					// When Implementation of Mask is provided directly
					try {
						String name = configurationElement.getAttribute("name");
						String description = configurationElement.getAttribute("description");
						Object implementation = configurationElement.createExecutableExtension("implementation");
						if (implementation instanceof ITransformationMask && name != null) {
							if (foundMasks.containsKey(name)) {
								OptimusM2MMaskMessages.ML14.log(name);
							} else {
								foundMasks.put(name, new TransformationMaskReference(name, description,
										(ITransformationMask) implementation));
								OptimusM2MMaskMessages.ML03.log(name, implementation.getClass().getName());
							}
						} else {
							if (name == null) {
								OptimusM2MMaskMessages.ML04.log();
							} else {
								OptimusM2MMaskMessages.ML05.log(implementation.getClass().getName());
							}
						}
					} catch (CoreException e) {
						OptimusM2MMaskMessages.ML06.log(e.getMessage(), contributorName);
						Activator
								.getDefault()
								.getLog()
								.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
										"An error occurred when instanciating source from extension point", e));
					}

				} else if ("inclusiveMask".equals(configurationElement.getName())) {
					// When transformation set is build from extension point,
					// starting with nothing and including elements
					String name = configurationElement.getAttribute("name");
					String description = configurationElement.getAttribute("description");
					JavaTransformationMask mask = JavaTransformationMask.allOff();

					if (foundMasks.containsKey(name)) {
						OptimusM2MMaskMessages.ML14.log(name);
					} else {
						OptimusM2MMaskMessages.ML07.log(name);

						for (IConfigurationElement child : configurationElement.getChildren()) {
							if ("includes".equals(child.getName())) {
								String transformationId = child.getAttribute("transformationId");
								if (transformationId != null) {
									this.includeRequiredTransformation(name, mask, transformationId);
								}
							} else if ("includesSet".equals(child.getName())) {
								String transformationSetId = child.getAttribute("transformationSetId");
								if (transformationSetId != null) {
									this.includeRequiredTransformationSet(name, mask, transformationSetId);
								}
							}
						}
						foundMasks.put(name, new TransformationMaskReference(name, description, mask));
					}

				} else if ("exclusiveMask".equals(configurationElement.getName())) {
					// When transformation set is build from extension point,
					// starting with all and excluding elements
					String name = configurationElement.getAttribute("name");
					String description = configurationElement.getAttribute("description");
					JavaTransformationMask mask = JavaTransformationMask.allOn();

					if (foundMasks.containsKey(name)) {
						OptimusM2MMaskMessages.ML14.log(name);
					} else {
						OptimusM2MMaskMessages.ML10.log(name);

						for (IConfigurationElement child : configurationElement.getChildren()) {
							if ("excludes".equals(child.getName())) {
								String transformationId = child.getAttribute("transformationId");
								if (transformationId != null) {
									this.excludeRequiredTransformation(name, mask, transformationId);
								}
							} else if ("excludesSet".equals(child.getName())) {
								String transformationSetId = child.getAttribute("transformationSetId");
								if (transformationSetId != null) {
									this.excludeRequiredTransformationSet(name, mask, transformationSetId);
								}
							}
						}
						foundMasks.put(name, new TransformationMaskReference(name, description, mask));
					}
				}
			}
		}
		OptimusM2MMaskMessages.ML13.log();
		List<TransformationMaskReference> transformationMaskReferencesResult = new LinkedList<TransformationMaskReference>();
		for (TransformationMaskReference transformationMaskReference : foundMasks.values()) {
			transformationMaskReferencesResult.add(transformationMaskReference);
		}
		this.registeredMasks = Collections.unmodifiableList(transformationMaskReferencesResult);
	}

	/**
	 * Include the transformation set in an inclusive mask
	 * 
	 * @param maskName
	 *            the name of the inclusive mask.
	 * @param mask
	 *            the inclusive mask.
	 * @param transformationSetId
	 *            the transformation set to include.
	 */
	private void includeRequiredTransformationSet(String maskName, JavaTransformationMask mask,
			String transformationSetId) {
		OptimusM2MMaskMessages.ML09.log(maskName, transformationSetId);
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for (TransformationReference transformationReference : transformationDataSource.getAll()) {
				String setId = transformationReference.getTransformationSet() == null ? "" : transformationReference
						.getTransformationSet().getId();
				if (transformationSetId.equals(setId) && transformationReference.getId() != null) {
					this.includeRequiredTransformation(maskName, mask, transformationReference.getId());
				}
			}
		}
	}

	/**
	 * Include the required transformations of a specified transformation and
	 * the transformation specified itself in an inclusive mask
	 * 
	 * @param maskName
	 *            the name of the inclusive mask.
	 * @param mask
	 *            the inclusive mask.
	 * @param transformationId
	 *            the transformation to include.
	 */
	private void includeRequiredTransformation(String maskName, JavaTransformationMask mask, String transformationId) {
		Set<String> includedTransformations = MaskTransformationRequirementsTool.requirementsToActivate(mask,
				TransformationDataSourceManager.INSTANCE.getById(transformationId));
		includedTransformations.add(transformationId);
		for (String includedTransformation : includedTransformations) {
			mask.withTransformation(includedTransformation);
			OptimusM2MMaskMessages.ML08.log(maskName, includedTransformation);
		}
	}

	/**
	 * Exclude the transformation set in an exclusive mask
	 * 
	 * @param maskName
	 *            the name of the exclusive mask.
	 * @param mask
	 *            the exclusive mask.
	 * @param transformationSetId
	 *            the transformation set to exclude.
	 */
	private void excludeRequiredTransformationSet(String maskName, JavaTransformationMask mask,
			String transformationSetId) {
		OptimusM2MMaskMessages.ML12.log(maskName, transformationSetId);
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for (TransformationReference transformationReference : transformationDataSource.getAll()) {
				String setId = transformationReference.getTransformationSet() == null ? "" : transformationReference
						.getTransformationSet().getId();
				if (transformationSetId.equals(setId) && transformationReference.getId() != null) {
					this.excludeRequiredTransformation(maskName, mask, transformationReference.getId());
				}
			}
		}
	}

	/**
	 * Exclude the transformations required by a specified transformation and
	 * the transformation specified itself in an exclusive mask
	 * 
	 * @param maskName
	 *            the name of the exclusive mask.
	 * @param mask
	 *            the exclusive mask.
	 * @param transformationId
	 *            the transformation to exclude.
	 */
	private void excludeRequiredTransformation(String maskName, JavaTransformationMask mask, String transformationId) {
		Set<String> excludedTransformations = MaskTransformationRequirementsTool.requirementsToDesactivate(mask,
				TransformationDataSourceManager.INSTANCE.getById(transformationId));
		excludedTransformations.add(transformationId);
		for (String excludedTransformation : excludedTransformations) {
			mask.withoutTransformation(excludedTransformation);
			OptimusM2MMaskMessages.ML11.log(maskName, excludedTransformation);
		}
	}

	@Override
	public Collection<TransformationMaskReference> getAllMasks() {
		return registeredMasks;
	}

}
