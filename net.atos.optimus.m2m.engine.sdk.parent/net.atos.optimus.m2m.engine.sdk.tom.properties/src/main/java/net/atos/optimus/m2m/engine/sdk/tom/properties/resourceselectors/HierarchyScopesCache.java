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
