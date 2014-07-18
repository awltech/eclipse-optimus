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
package net.atos.optimus.m2m.engine.core.masks;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.transformations.ExtensionPointTransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationDataSource;
import net.atos.optimus.m2m.engine.core.transformations.TransformationReference;

/**
 * Default implementation of Transformation Mask, that proposes a fluent API to
 * help with the coding of such mask.
 * 
 * By default the Transformation Source used is the Extension Point. But this
 * can be changed, using the API within.
 * 
 * @author mvanbesien
 * @since 1.0
 * 
 */
public class JavaTransformationMask implements ITransformationMask {

	/**
	 * Defines the initial enablement condition of this transformation mask.
	 * Indeed, if the class has been initialized with allOn, this means that, by
	 * default, all the transformations that are not manually handled, should be
	 * seen as enabled, and the implementation should disable the requested
	 * ones. On the other side, with allOff, all transformations should be
	 * disabled by default, and the implementation should enable them manually.
	 */
	private boolean defaultEnabled = true;

	/**
	 * This is the counterpart of the defaultEnabled property. Indeed, with
	 * allOn, all the disabled transformations will be listed in here. On the
	 * other side, when allOff is used, all the enabled transformations should
	 * be listed in here.
	 */
	private Set<String> exceptions = new HashSet<String>();

	/**
	 * Default Transformation Data Source implementation used.
	 */
	private ITransformationDataSource transformationDataSource = ExtensionPointTransformationDataSource.instance();

	/**
	 * Creates a new Transformation mask, with the enablement default value,
	 * passed as parameter.
	 * 
	 * @param defaultEnabled
	 *            : if true, all transformation of source are enabled. The user
	 *            will have to disable some, to configure the mask. If false,
	 *            all the transformations are disabled, and the user will have
	 *            to enable them to configure the filter.
	 */
	private JavaTransformationMask(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
	}

	/**
	 * Changes the transformation Data Source to use for this mask.
	 * 
	 * @param transformationDataSource
	 *            , new implementation
	 * @return
	 */
	public JavaTransformationMask withTransformationDataSource(ITransformationDataSource transformationDataSource) {
		this.transformationDataSource = transformationDataSource;
		return this;
	}

	/**
	 * Creates a transformation mask, with all the transformations within
	 * enabled by default. Then the user has to disable some to configure the
	 * mask.
	 * 
	 * @return
	 */
	public static JavaTransformationMask allOn() {
		return new JavaTransformationMask(true);
	}

	/**
	 * Creates a transformation mask with all the transformations within
	 * disabled by default. Then the user has to enable some to configure the
	 * mask.
	 * 
	 * @return
	 */
	public static JavaTransformationMask allOff() {
		return new JavaTransformationMask(false);
	}

	/**
	 * Enables the transformations with ID provided as parameter
	 * 
	 * @param id
	 * @return
	 */
	public JavaTransformationMask withTransformation(String id) {
		this.manageTransformation(id, true);
		return this;
	}

	/**
	 * Enables all the transformations from the transformation set, which ID is
	 * provided as parameter.
	 * 
	 * @param id
	 * @return
	 */
	public JavaTransformationMask withTransformationSet(String id) {
		this.manageTransformationSet(id, true);
		return this;
	}

	/**
	 * Disables the transformations with ID provided as parameter
	 * 
	 * @param id
	 * @return
	 */
	public JavaTransformationMask withoutTransformation(String id) {
		this.manageTransformation(id, false);
		return this;
	}

	/**
	 * Disables all the transformations from the transformation set, which ID is
	 * provided as parameter.
	 * 
	 * @param id
	 * @return
	 */
	public JavaTransformationMask withoutTransformationSet(String id) {
		this.manageTransformationSet(id, false);
		return this;
	}

	/**
	 * Internal method that handles the transformations as exceptions.
	 * 
	 * @param id
	 * @param enable
	 */
	private void manageTransformation(String id, boolean enable) {
		if (id == null)
			return;

		if (enable == this.defaultEnabled)
			this.exceptions.remove(id);
		else
			this.exceptions.add(id);

	}

	/**
	 * Internal method that handles the transformations of a transformation set
	 * as exceptions.
	 * 
	 * @param id
	 * @param enable
	 */
	private void manageTransformationSet(String id, boolean enable) {

		if (id == null)
			return;

		Collection<TransformationReference> allReferences = transformationDataSource.getAll();
		for (TransformationReference reference : allReferences) {
			String transformationSetID = reference.getTransformationSet() != null ? reference.getTransformationSet()
					.getId() : null;
			if (id.equals(transformationSetID)) {
				this.manageTransformation(reference.getId(), enable);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.atos.optimus.m2m.engine.core.masks.ITransformationMask#
	 * isTransformationEnabled(java.lang.String)
	 */
	@Override
	public boolean isTransformationEnabled(String id) {
		return this.exceptions.contains(id) ? !defaultEnabled : defaultEnabled;
	}

	/**
	 * Merges two transformation masks.
	 * 
	 * be careful! the call of this method is not reflexive !!! a.merge(b) is
	 * not b.merge(a) !!!
	 * 
	 * @param transformationMask
	 * @return
	 */
	public JavaTransformationMask merge(JavaTransformationMask transformationMask) {

		if (this.transformationDataSource != transformationMask.transformationDataSource)
			return this;

		if (this.defaultEnabled == transformationMask.defaultEnabled) {
			this.exceptions.addAll(transformationMask.exceptions);
		} else {

			if (transformationMask.exceptions.size() == 0) {
				return this;
			}

			for (TransformationReference transformationReference : this.transformationDataSource.getAll()) {
				if (!transformationMask.exceptions.contains(transformationReference.getId())) {
					this.exceptions.add(transformationReference.getId());
				}
			}
		}
		return this;
	}

}
