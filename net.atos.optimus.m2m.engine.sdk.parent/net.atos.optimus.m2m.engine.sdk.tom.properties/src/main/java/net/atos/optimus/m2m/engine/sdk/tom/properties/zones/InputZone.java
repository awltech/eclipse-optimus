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
package net.atos.optimus.m2m.engine.sdk.tom.properties.zones;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.swt.widgets.Composite;

import com.worldline.gmf.propertysections.core.AbstractZone;

/** Models an input zone with useful methods
 * 
 * @author tnachtergaele <nachtergaele.thomas@gmail.com>
 * 
 *
 */

public abstract class InputZone extends AbstractZone {
	
	/**
	 * The model feature of the model associated to the value in the input text
	 * field
	 */
	protected EStructuralFeature associatedModelFeature;
	
	/**
	 * Constructor
	 * 
	 * @param parent
	 *            parent composite.
	 * @param isGroup
	 *            true creates a Group, false creates a standard Composite.
	 * @param associatedModelFeature
	 *            the model feature of the model associated to the value in the
	 *            input text field.
	 */
	public InputZone(Composite parent, boolean isGroup, EStructuralFeature associatedModelFeature) {
		super(parent, isGroup);
		this.associatedModelFeature = associatedModelFeature;
	}
	
	/**
	 * Get the value in the input field
	 * 
	 * @return the value in the input field.
	 */
	public abstract Object getInputValue();
	
	/**
	 * The model feature of the model associated to the value in the input text
	 * field
	 * 
	 * @return the model feature of the model associated to the value in the
	 *         input text field
	 */
	public EStructuralFeature getAssociatedFeature() {
		return this.associatedModelFeature;
	}

	/**
	 * The TransactionalEditingDomain, retrieved from Opened Diagram
	 * 
	 * 
	 * @return TransactionalEditingDomain, retrieved from Opened Diagram.
	 */
	public final TransactionalEditingDomain giveEditingDomain() {
		return super.getEditingDomain();
	}

	/**
	 * The selected EObject beyond the selected EditPart in the Diagram
	 * 
	 * @return selected EObject beyond the selected EditPart in the Diagram.
	 */
	public final EObject giveEObject() {
		return super.getEObject();
	}
	
	/**
	 * Refreshes the current zone, such as the EditPart bound to this zone.
	 * 
	 */
	public void refreshZoneAndDiagram() {
		super.refreshZoneAndDiagram();
	}
	
}
