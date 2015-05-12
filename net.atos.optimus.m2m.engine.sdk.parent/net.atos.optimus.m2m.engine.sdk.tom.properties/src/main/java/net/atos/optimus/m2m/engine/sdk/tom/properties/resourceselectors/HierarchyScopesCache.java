package net.atos.optimus.m2m.engine.sdk.tom.properties.resourceselectors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;

/**
 * Models a hierarchy score with a cache system
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public enum HierarchyScopesCache {

	/* The unique instance of HierarchyScopesCache class */
	INSTANCE;

	/** The cache map */
	private Map<IType, IJavaSearchScope> scopes;

	/**
	 * Constructor
	 * 
	 */
	private HierarchyScopesCache() {
		this.scopes = new HashMap<IType, IJavaSearchScope>();
	}

	/**
	 * Give a hierarchy scope associated to a specified type
	 * 
	 * @param type
	 *            the specified type.
	 * @return the hierarchy scope associated to a specified type
	 * @throws JavaModelException
	 */
	public synchronized IJavaSearchScope getScope(IType type) throws JavaModelException {
		IJavaSearchScope scope = this.scopes.get(type);
		if (scope == null) {
			scope = SearchEngine.createHierarchyScope(type);
			this.scopes.put(type, scope);
		}
		return scope;
	}

	public void clear() {
		this.scopes.clear();
	}

}
