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
package net.atos.optimus.m2t.java.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.atos.optimus.m2t.java.core.internal.Activator;
import net.atos.optimus.m2t.java.core.internal.DefaultJavaCodeMerger;
import net.atos.optimus.m2t.java.core.internal.XAGenerationStrategy;
import net.atos.optimus.m2t.java.core.internal.postprocessors.GeneratedPostProcessor;
import net.atos.optimus.m2t.java.core.internal.postprocessors.ImportsPostProcessor;
import net.atos.optimus.m2t.java.core.internal.postprocessors.JavaFormatterPostProcessor;
import net.atos.optimus.m2t.java.core.internal.postprocessors.MergerPostProcessor;
import net.atos.optimus.m2t.java.core.internal.strategies.XAVetoStrategy;
import net.atos.optimus.m2t.merger.java.core.JavaCodeMerger;

import org.eclipse.acceleo.engine.generation.strategy.IAcceleoGenerationStrategy;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.generation.files.GenerateJava;
import org.eclipse.gmt.modisco.java.generation.files.GenerateJavaExtended;

/**
 * M2T Generator, that overrides the MoDisco {@link GenerateJavaExtended} code
 * generator
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class JavaGenerator extends GenerateJava {

	/**
	 * Collection of post processors to apply
	 */
	private Collection<IPostProcessor> postProcessors = new ArrayList<IPostProcessor>();

	/**
	 * Veto Strategy. Initialized by default with XA Veto Strategy
	 */
	private IPostGenerationVetoStrategy vetoStrategy = new XAVetoStrategy();

	/**
	 * Creates generator with default java code merger
	 */
	public JavaGenerator() {
		super();
		this.postProcessors.addAll(JavaGenerator.getDefaultPostProcessors(new DefaultJavaCodeMerger(null)));
	}

	/**
	 * Creates generator with a java code merger with given name, but default
	 * implementation
	 * 
	 * @param generatorName
	 */
	public JavaGenerator(String generatorName) {
		super();
		this.postProcessors.addAll(JavaGenerator.getDefaultPostProcessors(new DefaultJavaCodeMerger(generatorName)));
	}

	/**
	 * Creates a Java Generator with the provided java code merger
	 * 
	 * @param javaCodeMerger
	 */
	public JavaGenerator(JavaCodeMerger javaCodeMerger) {
		super();
		this.postProcessors.addAll(JavaGenerator.getDefaultPostProcessors(javaCodeMerger));
	}

	/**
	 * Creates a Java Generator with new post processors.
	 * 
	 * No constructor allows to specify post processors AND merger, because the
	 * merger has to be used in a post processor. Hence, it is up to the caller
	 * to specify a post processor invoking its own merger !
	 * 
	 * @param postProcessors
	 */
	public JavaGenerator(List<IPostProcessor> userPostProcessors) {
		super();
		this.postProcessors.addAll(userPostProcessors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.xa.generator.m2t.core.IGenerator#generate(org.eclipse.emf.ecore
	 * .EObject, java.lang.Object, java.lang.Object[])
	 */
	public IStatus generate(Model input, IPath output, Object... arguments) {

		try {
			this.initialize(input, new File(output.toString()), Arrays.asList(arguments));
			BasicMonitor monitor2 = new BasicMonitor();
			this.doGenerate(monitor2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Status.OK_STATUS;
	}

	/**
	 * Returns the post processors
	 * 
	 * @param generatorName
	 */
	private static Collection<IPostProcessor> getDefaultPostProcessors(JavaCodeMerger javaCodeMerger) {
		Collection<IPostProcessor> postProcessors = new ArrayList<IPostProcessor>();
		postProcessors.add(new ImportsPostProcessor());
		postProcessors.add(new GeneratedPostProcessor());
		postProcessors.add(new MergerPostProcessor(javaCodeMerger));
		postProcessors.add(new JavaFormatterPostProcessor());
		return postProcessors;
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.acceleo.engine.service.AbstractAcceleoGenerator#findModuleURL
	 * (java.lang.String)
	 */

	@Override
	protected URL findModuleURL(String moduleName) {
		URL moduleURL = this.findModuleURL(moduleName, GenerateJavaExtended.class);
		if (moduleURL == null) {
			try {
				Class<?> clazz = Class.forName("net.atos.optimus.m2t.java.emtl.Activator");
				if (clazz != null)
					moduleURL = findModuleURL(moduleName, clazz);
			} catch (ClassNotFoundException e) {
				Activator
						.getDefault()
						.getLog()
						.log(new Status(
								IStatus.WARNING,
								Activator.PLUGIN_ID,
								"The EMTL template could not be resolved at all (Modisco nor Optimus). "
										+ "This will probabily make Java generation fail. "
										+ "Please contact the development team or install the Optimus EMTL Patch Module."));
			}
		}
		return moduleURL;
	}

	/**
	 * 
	 * @param moduleName
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("restriction")
	private URL findModuleURL(String moduleName, Class<?> clazz) {
		URL moduleURL = null;
		if (EMFPlugin.IS_ECLIPSE_RUNNING)
			try {
				moduleURL = org.eclipse.acceleo.common.internal.utils.workspace.AcceleoWorkspaceUtil.getResourceURL(
						clazz, moduleName);
			} catch (IOException e) {
				// Swallow this, we'll try and locate the module through the
				// class loader
			}
		if (moduleURL == null)
			moduleURL = clazz.getResource(moduleName);
		return moduleURL;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmt.modisco.java.generation.files.GenerateJava#
	 * getGenerationStrategy()
	 */
	@Override
	public IAcceleoGenerationStrategy getGenerationStrategy() {
		return new XAGenerationStrategy(this.postProcessors, this.vetoStrategy);
	}

	/**
	 * Sets the veto strategy. If null, does nothing.
	 * 
	 * @param vetoStrategy
	 */
	public void setVetoStrategy(IPostGenerationVetoStrategy vetoStrategy) {
		if (vetoStrategy != null)
			this.vetoStrategy = vetoStrategy;
	}

}
