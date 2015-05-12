package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import net.atos.optimus.m2m.engine.sdk.tom.properties.Activator;
import net.atos.optimus.m2m.engine.sdk.tom.properties.requestors.PublicClassRequestor;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.TypeNameRequestor;
import org.eclipselabs.resourceselector.core.processor.ResourceProcessor;
import org.eclipselabs.resourceselector.core.resources.ResourceInfo;

/**
 * Create a processor resource finding class in a project according to a
 * specific requestor
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 *
 */

public class ClassResourceProcessor extends ResourceProcessor {

	/** The java project associated to the project */
	protected IJavaProject javaProject;

	/** The top hierarchy class in the scope */
	protected Class<?> topHierarchyClass;

	/**
	 * Constructor
	 *
	 * @param project
	 *            the input project.
	 * @param pattern
	 *            pattern giving by user.
	 * @param filterClass
	 *            the top hierarchy class in the scope.
	 */
	public ClassResourceProcessor(IProject project, String pattern, Class<?> topHierarchyClass) {
		super();
		this.iProject = project;
		this.javaProject = JavaCore.create(this.iProject);
		this.pattern = pattern;
		if (topHierarchyClass == null) {
			this.topHierarchyClass = java.lang.Object.class;
		} else {
			this.topHierarchyClass = topHierarchyClass;
		}
	}

	/**
	 * Get the java project associated to the project
	 * 
	 * @return The java project associated to the project.
	 */
	public IJavaProject getJavaProject() {
		return this.javaProject;
	}

	/**
	 * Adds new result to search results list
	 *
	 * @param resourceInfo
	 *            the new resource info to add to the result list.
	 */
	public void addResult(ResourceInfo resourceInfo) {
		this.searchResults.add(resourceInfo);
	}

	@Override
	protected void process() {

		/* Scope creation */
		IType type = null;
		IJavaSearchScope javaSearchScope = null;
		try {
			type = this.getJavaProject().findType(this.topHierarchyClass.getCanonicalName());
			if (type != null) {
				javaSearchScope = HierarchyScopesCache.INSTANCE.getScope(type);
			}
		} catch (JavaModelException e) {
			Activator.getDefault().getLog()
					.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error while Creating Java Hierarchy", e));
		}
		if (type == null || javaSearchScope == null) {
			return;
		}

		/* Configuring and correcting the pattern specified by user */
		String packagePattern = null;
		String typePattern = this.pattern;
		if (!typePattern.endsWith("*")) {
			typePattern += "*";
		}
		if (typePattern.contains(ResourceProcessor.DOT)) {
			packagePattern = typePattern.substring(0, typePattern.lastIndexOf(ResourceProcessor.DOT));
			typePattern = typePattern.substring(typePattern.lastIndexOf(ResourceProcessor.DOT) + 1);
		}

		/* Launching the research process */
		TypeNameRequestor typeNameRequestor = new PublicClassRequestor(this);
		SearchEngine searchEngine = new SearchEngine();
		try {
			searchEngine.searchAllTypeNames(packagePattern == null ? null : packagePattern.toCharArray(),
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_PATTERN_MATCH, typePattern.toCharArray(),
					SearchPattern.R_EXACT_MATCH | SearchPattern.R_PATTERN_MATCH, IJavaSearchConstants.IMPLEMENTORS
							| IJavaSearchConstants.CLASS, javaSearchScope, typeNameRequestor,
					IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH, new NullProgressMonitor());
		} catch (JavaModelException e) {
			Activator
					.getDefault()
					.getLog()
					.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID,
							"Error while processing search on java project", e));
		}
	}

}
