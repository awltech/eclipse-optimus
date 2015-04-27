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
package net.atos.optimus.m2m.engine.masks.extension;

import net.atos.optimus.m2m.engine.core.Activator;
import net.atos.optimus.m2m.engine.core.masks.ITransformationMask;
import net.atos.optimus.m2m.engine.core.masks.PreferencesTransformationMask;
import net.atos.optimus.m2m.engine.masks.JavaTransformationMask;

import org.eclipse.jface.preference.IPreferenceStore;

// Add documentation
public enum TransformationsMaskSelectionPreference {

	/**
	 * Instance
	 */
	INSTANCE;

	private static final String EXISTING_PREFIX = "existing.";

	private static final String PROPERTY_NAME = Activator.PLUGIN_ID + ".preferred";

	private static final String CUSTOM_PROPERTY = "custom";

	private static ITransformationMask ALL_TRANSFORMATIONS_MASK = JavaTransformationMask.allOn();

	private static ITransformationMask CUSTOM_TRANSFORMATIONS_MASK = PreferencesTransformationMask.INSTANCE;

	/**
	 * Internal instance of preferenceStore
	 */
	private IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();

	// TODO Add logging
	public ITransformationMask getPreferredTransformationMask() {
		String property = this.preferenceStore.getString(PROPERTY_NAME);

		if (CUSTOM_PROPERTY.equals(property)) {
			return CUSTOM_TRANSFORMATIONS_MASK;
		}

		if (property != null && property.startsWith(EXISTING_PREFIX)) {
			String maskIdentifier = property.substring(EXISTING_PREFIX.length());
			ITransformationMask registeredMask = (new ExtensionPointTransformationMaskDataSource())
					.getMaskById(maskIdentifier).getImplementation();
			if (registeredMask != null) {
				return registeredMask;
			}
		}

		return ALL_TRANSFORMATIONS_MASK;

	}

	// TODO Add logging
	public void setAllMaskAsPreferred() {
		this.preferenceStore.setValue(PROPERTY_NAME, null);
	}

	// TODO Add logging
	public void setCustomMaskAsPreferred() {
		this.preferenceStore.setValue(PROPERTY_NAME, CUSTOM_PROPERTY);
	}

	// TODO Add logging
	public void setExistingMaskAsPreferred(String maskName) {
		this.preferenceStore.setValue(PROPERTY_NAME, EXISTING_PREFIX.concat(maskName));
	}

	public boolean isAllMaskPreferred() {
		return !this.isCustomMaskPreferred() && !this.isExistingMaskPreferred();
	}

	public boolean isCustomMaskPreferred() {
		String property = this.preferenceStore.getString(PROPERTY_NAME);
		return CUSTOM_PROPERTY.equals(property);
	}

	public boolean isExistingMaskPreferred() {
		String property = this.preferenceStore.getString(PROPERTY_NAME);
		return property != null && property.startsWith(EXISTING_PREFIX);
	}

}
