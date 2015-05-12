package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import org.eclipse.core.resources.IProject;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessorFactory;

/**
 * Factory of processors resources finding class in a project according to a
 * specific requestor
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class ClassResourceProcessorFactory implements ResourceProcessorFactory {

	/** The class that will be used to determine filtering in scope */
	protected Class<?> filterClass;

	/**
	 * Constructor
	 * 
	 * @param filterClass
	 *            the class that will be used to determine filtering in scope.
	 */
	public ClassResourceProcessorFactory(Class<?> filterClass) {
		this.filterClass = filterClass;
	}

	@Override
	public ResourceProcessor createResourceProcessor(IProject project, String pattern) {
		return new ClassResourceProcessor(project, pattern, this.filterClass);
	}

}
