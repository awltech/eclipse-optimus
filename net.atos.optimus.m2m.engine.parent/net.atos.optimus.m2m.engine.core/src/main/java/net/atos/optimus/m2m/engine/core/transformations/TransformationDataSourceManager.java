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

/** Manager of transformation data source contributed via extension point
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

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
								OptimusM2MEngineMessages.DS01
										.log(dataSource.getName(), dataSource.getClass().getName());
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

	/**
	 * Give the list of transformation data source of the transformation data
	 * source manager
	 * 
	 * @return the list of transformation data source of the transformation data
	 *         source manager.
	 */
	public List<TransformationDataSource> getTransformationDataSources() {
		return transformationDataSources;
	}

	/**
	 * Returns the Transformation Reference with the id provided as parameter
	 * 
	 * @param id
	 *            transformation id.
	 * @return Transformation Reference.
	 */
	public TransformationReference getById(String id) {
		TransformationReference transformationReference = null;
		for (TransformationDataSource transformationDataSource : this.getTransformationDataSources()) {
			transformationReference = transformationDataSource.getById(id);
			if (transformationReference != null) {
				return transformationReference;
			}
		}
		return transformationReference;
	}

}
