package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import org.eclipse.core.resources.IProject;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

/**
 * Factory of processors resources finding transformation name resource
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationResourceProcessorFactory implements ResourceProcessorFactory {

	@Override
	public ResourceProcessor createResourceProcessor(IProject project, String pattern) {
		return new TransformationResourceProcessor(project, pattern);
	}

}
