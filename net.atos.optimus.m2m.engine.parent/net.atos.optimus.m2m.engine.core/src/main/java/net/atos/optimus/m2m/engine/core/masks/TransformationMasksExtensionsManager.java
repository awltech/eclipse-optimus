package net.atos.optimus.m2m.engine.core.masks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.transformations.ExtensionPointTransformationDataSource;

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
public enum TransformationMasksExtensionsManager {
	INSTANCE;

	/**
	 * List of registered masks, read from extension point
	 */
	private final List<TransformationMaskReference> registeredMasks;

	/**
	 * Private constructor. Responsible for the retrieval of extension points
	 * elements
	 */
	private TransformationMasksExtensionsManager() {
		ArrayList<TransformationMaskReference> foundMasks = new ArrayList<TransformationMaskReference>();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				"TransformationsMasks");

		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				if ("implementedMask".equals(configurationElement.getName())) {
					// When Implementation of Mask is provided directly
					try {
						String name = configurationElement.getAttribute("name");
						Object implementation = configurationElement.createExecutableExtension("implementation");
						if (implementation instanceof ITransformationMask && name != null) {
							foundMasks.add(new TransformationMaskReference(name, (ITransformationMask) implementation));
						}
					} catch (CoreException e) {
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
					JavaTransformationMask mask = JavaTransformationMask.allOff().withTransformationDataSource(
							ExtensionPointTransformationDataSource.instance());
					for (IConfigurationElement child : configurationElement.getChildren()) {
						if ("includes".equals(child.getName())) {
							String transformationId = child.getAttribute("transformationId");
							if (transformationId != null) {
								mask.withTransformation(transformationId);
							}
						} else if ("includesSet".equals(child.getName())) {
							String transformationSetId = child.getAttribute("transformationSetId");
							if (transformationSetId != null) {
								mask.withTransformationSet(transformationSetId);
							}
						}
					}
					foundMasks.add(new TransformationMaskReference(name, mask));
				} else if ("exclusiveMask".equals(configurationElement.getName())) {
					// When transformation set is build from extension point,
					// starting with all and excluding elements
					String name = configurationElement.getAttribute("name");
					JavaTransformationMask mask = JavaTransformationMask.allOn().withTransformationDataSource(
							ExtensionPointTransformationDataSource.instance());
					for (IConfigurationElement child : configurationElement.getChildren()) {
						if ("excludes".equals(child.getName())) {
							String transformationId = child.getAttribute("transformationId");
							if (transformationId != null) {
								mask.withoutTransformation(transformationId);
							}
						} else if ("excludesSet".equals(child.getName())) {
							String transformationSetId = child.getAttribute("transformationSetId");
							if (transformationSetId != null) {
								mask.withoutTransformationSet(transformationSetId);
							}
						}
					}
					foundMasks.add(new TransformationMaskReference(name, mask));
				}
			}
		}
		this.registeredMasks = Collections.unmodifiableList(foundMasks);
	}

	/**
	 * @return Transformation Masks registered from extension points.
	 */
	public List<TransformationMaskReference> getRegisteredMasks() {
		return registeredMasks;
	}

	/**
	 * 
	 * @param name
	 * @return Transformation Mask registered from extension points, which name
	 *         matches the proposed one
	 */
	public ITransformationMask getRegisteredMask(String name) {
		for (TransformationMaskReference reference : this.registeredMasks) {
			if (name == null ? reference.getName() == null : name.equals(reference.getName())) {
				return reference.getImplementation();
			}
		}
		return null;
	}
}
