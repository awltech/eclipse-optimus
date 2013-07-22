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

import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

/**
 * Optimus adapter. This one is used to throw an IllegalStateException when the
 * user tries to remove or modify an EObject that is part of a transformation
 * process.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public class EObjectLockAdapter extends AbstractOptimusAdapter {

	/**
	 * Exception message.
	 */
	private static final String EXCEPTION_MESSAGE = "Optimus Input Object should not be altered while being transformed !";

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

			if (eReference.isDerived())
				return;

			if (eReference.isContainment() && msg.getEventType() == Notification.ADD) {
				Object newValue = msg.getNewValue();
				if (newValue instanceof EObject) {
					EObject eObject = (EObject) newValue;
					this.adaptEObject(eObject);
					OptimusM2MEngineMessages.AD05.log(this.eObjectLabelProvider.getText(msg.getNotifier()),
							this.eObjectLabelProvider.getText(eObject));
				}
			}
		}
		if (o != null) {
			OptimusM2MEngineMessages.AD03.log();
			throw new IllegalStateException(EXCEPTION_MESSAGE);
		}
	}
}
