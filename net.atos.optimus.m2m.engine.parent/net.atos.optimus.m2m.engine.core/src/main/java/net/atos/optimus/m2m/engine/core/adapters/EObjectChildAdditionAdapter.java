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
package net.atos.optimus.m2m.engine.core.adapters;

import net.atos.optimus.m2m.engine.core.OptimusM2MEngine;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Optimus adapter. This one reacts when an EObject is added as a child of the
 * object that is being adapted by this.
 * 
 * The action of this adapter is to schedule the created child, so it will be
 * taken in consideration by the Optimus engine
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class EObjectChildAdditionAdapter extends AbstractOptimusAdapter {

	/**
	 * Optimus Engine instance
	 */
	private OptimusM2MEngine engine;

	/**
	 * Password, used to access the critical methods of the engine
	 */
	private Object password;

	/**
	 * Creates new adapter for Engine with password
	 * 
	 * @param engine
	 * @param password
	 */
	public EObjectChildAdditionAdapter(OptimusM2MEngine engine, Object password) {
		this.password = password;
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse
	 * .emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(Notification msg) {
		Object o = msg.getFeature();
		if (o instanceof EReference) {
			EReference eReference = (EReference) o;
			if (!eReference.isDerived() && eReference.isContainment() && msg.getEventType() == Notification.ADD) {
				EObject value = (EObject) msg.getNewValue();
				OptimusM2MEngineMessages.AD04.log(eObjectLabelProvider.getText(value));
				this.engine.scheduleEObject(value, this.password);
			}
		}
	}
}
