package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import org.eclipse.core.resources.IProject;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

/**
 * Factory of processors resources finding transformation set name resource
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class TransformationSetResourceProcessorFactory implements ResourceProcessorFactory {

	@Override
	public ResourceProcessor createResourceProcessor(IProject project, String pattern) {
		return new TransformationSetResourceProcessor(project,pattern);
	}

}
