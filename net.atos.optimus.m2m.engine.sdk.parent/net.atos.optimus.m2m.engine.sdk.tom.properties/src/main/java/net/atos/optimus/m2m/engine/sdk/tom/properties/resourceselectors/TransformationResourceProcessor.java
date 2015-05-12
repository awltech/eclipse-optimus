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
