package net.atos.optimus.m2m.engine.core.transformations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

public enum TransformationDataSourceManager {
	INSTANCE;

	private final List<TransformationDataSource> transformationDataSources;

	private TransformationDataSourceManager() {
		OptimusM2MEngineMessages.DS05.log();
		
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
								TransformationDataSource dataSource = (TransformationDataSource) o;
								tempTransformationDataSources.add(dataSource);
								OptimusM2MEngineMessages.DS01.log(dataSource.getName(), dataSource.getClass().getName());
							} else {
								OptimusM2MEngineMessages.DS02.log(o.getClass().getName());
							}
						} else {
							OptimusM2MEngineMessages.DS03.log(configurationElement.getContributor().getName());
						}
					}
				} catch (CoreException ce) {
					OptimusM2MEngineMessages.DS04.log(ce.getMessage(), configurationElement.getContributor().getName());
					Activator
							.getDefault()
							.getLog()
							.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
									"An error occurred when trying to load Transformation Data Source", ce));
				}

			}
		}
		this.transformationDataSources = Collections.unmodifiableList(tempTransformationDataSources);
		OptimusM2MEngineMessages.DS06.log();
	}
	
	public List<TransformationDataSource> getTransformationDataSources() {
		return transformationDataSources;
	}
}