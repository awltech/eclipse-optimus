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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskDataSource;
import net.atos.optimus.m2m.engine.core.masks.TransformationMaskReference;
import net.atos.optimus.m2m.engine.masks.Activator;
import net.atos.optimus.m2m.engine.masks.JavaTransformationMask;
import net.atos.optimus.m2m.engine.masks.logging.OptimusM2MMaskMessages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * Implementation of Transformation Masks Extension Point management singleton
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
		OptimusM2MMaskMessages.TMEP01.log();

		ArrayList<TransformationMaskReference> foundMasks = new ArrayList<TransformationMaskReference>();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				ExtensionPointTransformationMaskDataSource.EXTENSION_POINT_NAME);

		for (IExtension extension : extensionPoint.getExtensions()) {
			String contributorName = extension.getContributor().getName();
			OptimusM2MMaskMessages.TMEP02.log(contributorName);

			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				if ("implementedMask".equals(configurationElement.getName())) {
					// When Implementation of Mask is provided directly
					try {
						String name = configurationElement.getAttribute("name");
						String description = configurationElement.getAttribute("description");
						Object implementation = configurationElement.createExecutableExtension("implementation");
						if (implementation instanceof ITransformationMask && name != null) {
							foundMasks.add(new TransformationMaskReference(name, description, (ITransformationMask) implementation, false));
							OptimusM2MMaskMessages.TMEP03.log(name, implementation.getClass().getName());
						} else {
							if (name == null) {
								OptimusM2MMaskMessages.TMEP04.log();
							} else {
								OptimusM2MMaskMessages.TMEP05.log(implementation.getClass());
							}
						}
					} catch (CoreException e) {
						OptimusM2MMaskMessages.TMEP06.log(e.getMessage(), contributorName);
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
					OptimusM2MMaskMessages.TMEP07.log(name);
					
					for (IConfigurationElement child : configurationElement.getChildren()) {
						if ("includes".equals(child.getName())) {
							String transformationId = child.getAttribute("transformationId");
							if (transformationId != null) {
								mask.withTransformation(transformationId);
								OptimusM2MMaskMessages.TMEP08.log(name,transformationId);
							}
						} else if ("includesSet".equals(child.getName())) {
							String transformationSetId = child.getAttribute("transformationSetId");
							if (transformationSetId != null) {
								mask.withTransformationSet(transformationSetId);
								OptimusM2MMaskMessages.TMEP09.log(name,transformationSetId);
							}
						}
					}
					foundMasks.add(new TransformationMaskReference(name, description, mask, false));
					
				} else if ("exclusiveMask".equals(configurationElement.getName())) {
					// When transformation set is build from extension point,
					// starting with all and excluding elements
					String name = configurationElement.getAttribute("name");
					String description = configurationElement.getAttribute("description");
					JavaTransformationMask mask = JavaTransformationMask.allOn();
					OptimusM2MMaskMessages.TMEP10.log(name);
					
					for (IConfigurationElement child : configurationElement.getChildren()) {
						if ("excludes".equals(child.getName())) {
							String transformationId = child.getAttribute("transformationId");
							if (transformationId != null) {
								mask.withoutTransformation(transformationId);
								OptimusM2MMaskMessages.TMEP11.log(name,transformationId);
							}
						} else if ("excludesSet".equals(child.getName())) {
							String transformationSetId = child.getAttribute("transformationSetId");
							if (transformationSetId != null) {
								mask.withoutTransformationSet(transformationSetId);
								OptimusM2MMaskMessages.TMEP12.log(name,transformationSetId);
							}
						}
					}
					foundMasks.add(new TransformationMaskReference(name, description, mask, false));
				}
			}
		}
		OptimusM2MMaskMessages.TMEP13.log();
		this.registeredMasks = Collections.unmodifiableList(foundMasks);
	}

	@Override
	public TransformationMaskReference getMaskById(String id) {
		for (TransformationMaskReference reference : this.registeredMasks) {
			if (id == null ? reference.getName() == null : id.equals(reference.getName())) {
				return reference;
			}
		}
		return null;
	}

	@Override
	public Collection<TransformationMaskReference> getAllMasks() {
		return registeredMasks;
	}

}
