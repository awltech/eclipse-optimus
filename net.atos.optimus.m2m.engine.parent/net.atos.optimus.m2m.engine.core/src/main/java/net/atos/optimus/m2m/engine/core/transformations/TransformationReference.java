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

import java.util.LinkedHashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.requirements.AbstractRequirement;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Transformation Reference
 * 
 * As factories are used to create new transformations, we use a Transformation
 * Reference wrapper that contains the factory instance, and all information
 * about the transformation definition, such as the description, id,
 * transformation set, requirements...
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class TransformationReference {

	/**
	 * Id of the transformation
	 */
	private String id;

	/**
	 * Local information, to store the enablement key. The enablement key is a
	 * key used in the Eclipse preferences, to store whether a transformation is
	 * enabled or not
	 */
	private String enablementKey;

	/**
	 * Transformation factory instance
	 */
	private ITransformationFactory transformationFactory;

	/**
	 * Transformation Set instance
	 */
	private TransformationSet transformationSet;

	/**
	 * Transformation requirements
	 */
	private Set<AbstractRequirement> requirements;

	/**
	 * Description of the transformation
	 */
	private String description;

	/**
	 * boolean telling if transformations are enabled or not.
	 */
	private boolean isEnabled = true;

	private int priority;

	/**
	 * Creates a new reference to a transformation factory
	 * 
	 * @param id
	 *            : Transformation ID
	 * @param transformationFactory
	 *            : Transformation factory instance
	 * @param lts
	 *            : Transformation Set
	 * @param description
	 *            : Description
	 * @param priority 
	 */
	public TransformationReference(String id, ITransformationFactory transformationFactory, TransformationSet lts,
			String description, int priority) {
		this.id = id;
		this.transformationFactory = transformationFactory;
		this.transformationSet = lts;
		this.requirements = new LinkedHashSet<AbstractRequirement>();
		this.description = description;
		IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		this.enablementKey = Activator.PLUGIN_ID + ".disabled." + id;
		this.priority = priority;
		this.isEnabled = !preferenceStore.getBoolean(this.enablementKey);
	}

	/**
	 * @return ID of the transformation
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return Transformation Factory instance
	 */
	public ITransformationFactory getTransformationFactory() {
		return transformationFactory;
	}

	/**
	 * @return Transformation Set instance
	 */
	public TransformationSet getTransformationSet() {
		return transformationSet;
	}

	/**
	 * Adds new requirement to transformation reference
	 * 
	 * @param requirement
	 */
	public void addRequirement(AbstractRequirement requirement) {
		this.requirements.add(requirement);
	}

	/**
	 * @return the requirements of this Transformation
	 */
	public Set<AbstractRequirement> getRequirements() {
		return requirements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TransformationReference))
			return false;
		return this.getIdentifier().equals(((TransformationReference) obj).getIdentifier());
	}

	/***
	 * @return identifier of the transformation
	 */
	private String getIdentifier() {
		return id;
	}

	/**
	 * @return description of the transformation
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return whether the transformation is enabled in the Eclipse preferences
	 */
	public boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Flags the transformation as enabled or disabled, according to the
	 * provided value
	 * 
	 * @param isEnabled
	 *            : true to enable the transformation, false to disable it
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
		Activator.getDefault().getPreferenceStore().setValue(this.enablementKey, !this.isEnabled);
	}
	
	public int getPriority() {
		return priority;
	}

}
