package net.atos.optimus.m2m.engine.core.transformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.atos.optimus.m2m.engine.core.Activator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public enum TransformationDataSourceManager {
	INSTANCE, TransformationSet;

	private final List<TransformationDataSource> transformationDataSources;

	private TransformationDataSourceManager() {

		List<TransformationDataSource> tempTransformationDataSources = new ArrayList<TransformationDataSource>();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				"TransformationSources");

		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				try {
					if ("transformationDataSource".equals(configurationElement.getName())) {
						String implementation = configurationElement.getAttribute("implementation");
						if (implementation != null && implementation.length() > 0) {
							Object o = configurationElement.createExecutableExtension("implementation");
							if (o instanceof TransformationDataSource) {
								tempTransformationDataSources.add((TransformationDataSource) o);
							}
						}
					}
				} catch (CoreException ce) {
					Activator
							.getDefault()
							.getLog()
							.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
									"An error occurred when trying to load Transformation Data Source"));
				}

			}
		}
		this.transformationDataSources = Collections.unmodifiableList(tempTransformationDataSources);
	}
	
	public List<TransformationDataSource> getTransformationDataSources() {
		return transformationDataSources;
	}
}
