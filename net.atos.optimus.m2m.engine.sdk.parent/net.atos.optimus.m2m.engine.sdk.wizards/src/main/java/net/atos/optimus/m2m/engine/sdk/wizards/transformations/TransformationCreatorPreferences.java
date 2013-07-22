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
package net.atos.optimus.m2m.engine.sdk.wizards.transformations;

import net.atos.optimus.m2m.engine.sdk.wizards.Activator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.osgi.service.prefs.BackingStoreException;

/**
 *  @author Maxence Vanbésien (mvaawl@gmail.com)
 *  @since 1.0
 */
public class TransformationCreatorPreferences {

	private static final String CLASSNAME = "lastClassName";

	private static final String FACTORYNAME = "lastFactoryName";

	private static final String ELEMENTNAME = "lastSelectedType";

	private static final String FRAGMENTNAME = "lastSelectedPackage";

	private static final String TRNNAME = "lastTransformationId";

	private static final String TRNSETNAME = "lastTransformationSetName";

	private static final String CLASSDEFAULT = "";

	private static final String FACTORYDEFAULT = "";

	private static final String ELEMENTDEFAULT = "org.eclipse.emf.ecore.EObject";

	private static final String FRAGMENTDEFAULT = "net.atos.acme";

	private static final String TRNDEFAULT = "net.atos.acme.MyTransformation";

	private static final String TRNSETDEFAULT = "net.atos.acme.MyTransformationSet";

	private IEclipsePreferences preferences;

	public TransformationCreatorPreferences(final IProject project) {
		final IScopeContext projectScope = new ProjectScope(project);
		this.preferences = projectScope.getNode(Activator.PLUGIN_ID);
	}

	public String getClassName() {
		return this.preferences.get(CLASSNAME, CLASSDEFAULT);
	}

	public String getFactoryName() {
		return this.preferences.get(FACTORYNAME, FACTORYDEFAULT);
	}

	public String getElementName() {
		return this.preferences.get(ELEMENTNAME, ELEMENTDEFAULT);
	}

	public String getFragmentName() {
		return this.preferences.get(FRAGMENTNAME, FRAGMENTDEFAULT);
	}

	public String getTransformationName() {
		return this.preferences.get(TRNNAME, TRNDEFAULT);
	}

	public String getTransformationSetName() {
		return this.preferences.get(TRNSETNAME, TRNSETDEFAULT);
	}

	public void setClassName(String className) {
		final String currentValue = this.getClassName();
		if (currentValue != null && currentValue.equals(className))
		return;
		this.preferences.put(CLASSNAME, className);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void setFactoryName(String factoryName) {
		final String currentValue = this.getFactoryName();
		if (currentValue != null && currentValue.equals(factoryName))
		return;
		this.preferences.put(FACTORYNAME, factoryName);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void setElementName(String elementName) {
		final String currentValue = this.getElementName();
		if (currentValue != null && currentValue.equals(elementName))
		return;
		this.preferences.put(ELEMENTNAME, elementName);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void setFragmentName(String fragmentName) {
		final String currentValue = this.getFragmentName();
		if (currentValue != null && currentValue.equals(fragmentName))
		return;
		this.preferences.put(FRAGMENTNAME, fragmentName);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	public void setTransformationName(String transformationName) {
		final String currentValue = this.getTransformationName();
		if (currentValue != null && currentValue.equals(transformationName))
		return;
		this.preferences.put(TRNNAME, transformationName);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void setTransformationSetName(String transformationSetName) {
		final String currentValue = this.getFactoryName();
		if (currentValue != null && currentValue.equals(transformationSetName))
		return;
		this.preferences.put(TRNSETNAME, transformationSetName);
		try {
			this.preferences.flush();
		} catch (final BackingStoreException e) {
			e.printStackTrace();
		}
	}
}
