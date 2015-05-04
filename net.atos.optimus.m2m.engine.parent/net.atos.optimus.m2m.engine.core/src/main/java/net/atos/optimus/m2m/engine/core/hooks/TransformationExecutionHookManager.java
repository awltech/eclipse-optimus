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
package net.atos.optimus.m2m.engine.core.hooks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

/**
 * Singleton implementation for extension point dedicated to Transformation
 * Execution Hooks.
 * 
 * @author mvanbesien
 * @since 1.1
 *
 */
public enum TransformationExecutionHookManager {
	INSTANCE;

	/**
	 * Extension point descriptor constant
	 */
	private static final String ATTRIBUTE_NAME = "implementation";

	/**
	 * Extension point descriptor constant
	 */
	private static final String CONFIGELT_NAME = "transformationExecutionHook";

	/**
	 * Extension point descriptor constant
	 */
	private static final String EXTPT_NAME = "TransformationExecutionHooks";

	/**
	 * Cached list of execution hooks
	 */
	private final List<AbstractTransformationExecutionHook> transformationExecutionHooks;

	/**
	 * Private constructor
	 */
	private TransformationExecutionHookManager() {
		OptimusM2MEngineMessages.TH01.log();
		List<AbstractTransformationExecutionHook> tempHooks = new ArrayList<AbstractTransformationExecutionHook>();

		IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(Activator.PLUGIN_ID,
				EXTPT_NAME);

		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement configurationElement : extension.getConfigurationElements()) {
				try {
					if (CONFIGELT_NAME.equals(configurationElement.getName())) {
						String implementation = configurationElement.getAttribute(ATTRIBUTE_NAME);
						if (implementation != null && implementation.length() > 0) {
							Object o = configurationElement.createExecutableExtension(ATTRIBUTE_NAME);
							if (o instanceof AbstractTransformationExecutionHook) {
								AbstractTransformationExecutionHook hook = (AbstractTransformationExecutionHook) o;
								tempHooks.add(hook);
								OptimusM2MEngineMessages.TH03.log(hook.getClass().getName(), configurationElement
										.getContributor().getName());
							} else {
								OptimusM2MEngineMessages.TH04.log(o != null ? o.getClass().getName() : "<NULL>",
										configurationElement.getContributor().getName());
							}
						} else {
							OptimusM2MEngineMessages.TH05.log(configurationElement.getContributor().getName());
						}
					}
				} catch (CoreException ce) {
					Activator
							.getDefault()
							.getLog()
							.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
									"An error occurred when trying to load Transformation Hooks", ce));
					OptimusM2MEngineMessages.TH06.log(configurationElement.getContributor().getName(), ce.getMessage());
				}
			}
		}
		this.transformationExecutionHooks = Collections.unmodifiableList(tempHooks);
		OptimusM2MEngineMessages.TH02.log(this.transformationExecutionHooks.size());
	}

	/**
	 * Processes all the internally registered hooks and runs "pre execution"
	 * processed on them for transformation and context.
	 * 
	 * @param transformation
	 * @param context
	 */
	public void beforeExecution(AbstractTransformation<?> transformation, ITransformationContext context) {
		for (AbstractTransformationExecutionHook hook : this.transformationExecutionHooks) {
			try {
				hook.beforeExecution(transformation, context);
			} catch (Exception e) {
				OptimusM2MEngineMessages.TH07.log(transformation.getClass().getName(), hook.getClass().getName(), e);
			}
		}
	}

	/**
	 * Processes all the internally registered hooks and runs "post execution"
	 * processed on them for transformation and context.
	 * 
	 * @param transformation
	 * @param context
	 */
	public void afterExecution(AbstractTransformation<?> transformation, ITransformationContext context) {
		for (AbstractTransformationExecutionHook hook : this.transformationExecutionHooks) {
			try {
				hook.afterExecution(transformation, context);
			} catch (Exception e) {
				OptimusM2MEngineMessages.TH08.log(transformation.getClass().getName(), hook.getClass().getName(), e);
			}
		}
	}
}
