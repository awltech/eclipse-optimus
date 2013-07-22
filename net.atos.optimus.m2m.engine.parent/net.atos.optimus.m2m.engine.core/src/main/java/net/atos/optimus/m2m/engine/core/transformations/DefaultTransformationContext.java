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
package net.atos.optimus.m2m.engine.core.transformations;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.logging.EObjectLabelProvider;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * Default implementation for the Transformation Context.
 * 
 * In this implementation a Map is used
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class DefaultTransformationContext implements ITransformationContext {

	/**
	 * Session EObject
	 */
	private Map<EObject, Map<String, EObject>> session = new HashMap<EObject, Map<String, EObject>>();

	/**
	 * Roots container
	 */
	private Map<String, EObject> roots = new HashMap<String, EObject>();
	
	/**
	 * Properties Container
	 */
	private Map<String, String> properties = new HashMap<String, String>();

	/**
	 * Label Provider which purpose is to provide naming system for EObjects.
	 */
	protected LabelProvider eObjectLabelProvider = new EObjectLabelProvider();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #put(java.lang.String, java.lang.String, java.lang.Object)
	 */
	public void put(EObject source, String key, EObject value) {
		Map<String, EObject> values = this.session.get(source);
		if (values == null) {
			values = new HashMap<String, EObject>();
			session.put(source, values);
		}
		if (values.containsKey(key)) {
			OptimusM2MEngineMessages.DC01.log(eObjectLabelProvider.getText(source), key);
		}
		values.put(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #get(java.lang.String, java.lang.String)
	 */
	public EObject get(EObject source, String key) {
		Map<String, EObject> values = this.session.get(source);
		if (values != null)
			return values.get(key);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #dispose()
	 */
	public void dispose() {
		for (EObject source : session.keySet()) {
			Map<String, EObject> values = session.get(source);
			if (values != null)
				values.clear();
		}
		this.session.clear();
		this.roots.clear();
		this.properties.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #getRoots()
	 */
	public Collection<EObject> getRoots() {
		return Collections.unmodifiableCollection(this.roots.values());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #getRoot(java.lang.String)
	 */
	public EObject getRoot(String key) {
		return this.roots.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.atos.optimus.m2m.engine.core.transformations.ITransformationContext
	 * #putRoot(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public void putRoot(String key, EObject eObject) {
		if (this.roots.containsKey(key)) {
			OptimusM2MEngineMessages.DC02.log(key);
		}
		this.roots.put(key, eObject);
	}

	/*
	 * (non-Javadoc)
	 * @see net.atos.optimus.m2m.engine.core.transformations.ITransformationContext#getProperty(java.lang.String)
	 */
	public String getProperty(String key) {
		return this.properties.get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see net.atos.optimus.m2m.engine.core.transformations.ITransformationContext#putProperty(java.lang.String, java.lang.String)
	 */
	public void putProperty(String key, String property) {
		if (this.properties.containsKey(key)) {
			OptimusM2MEngineMessages.DC03.log(key);
		}
		this.properties.put(key, property);
	}

}
