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
package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationDataSourceManager;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

import org.eclipse.core.resources.IProject;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;

/**
 * Create a processor resource finding transformation name resource
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationResourceProcessor extends ResourceProcessor {
	
	/**
	 * Constructor
	 *
	 * @param project
	 *            the input project.
	 * @param pattern
	 *            pattern giving by user.
	 */
	public TransformationResourceProcessor(IProject project, String pattern) {
		super();
		this.iProject = project;
		this.pattern = pattern;
	}

	@Override
	protected void process() {
		/* Configuring and correcting the pattern specified by user */
		String transformationPattern = this.pattern;
		transformationPattern = transformationPattern.replace("*","(.*)");
		if (!transformationPattern.endsWith("(.*)")) {
			transformationPattern += "(.*)";
		}

		Set<String> transformationSetNames = new HashSet<String>();
		for (TransformationDataSource transformationDataSource : TransformationDataSourceManager.INSTANCE
				.getTransformationDataSources()) {
			for(TransformationReference transformationReference : transformationDataSource.getAll()){
				if (Pattern.matches(transformationPattern, transformationReference.getId())) {
					transformationSetNames.add(transformationReference.getId());
				}
			}
		}
		for(String transformationSetName : transformationSetNames){
			searchResults.add(new TransformationsResourceInfo(transformationSetName));
		}
	}

}
