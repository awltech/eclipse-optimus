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

import java.util.HashSet;
import java.util.Set;

import net.atos.optimus.m2m.engine.core.logging.EObjectLabelProvider;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * Abstract Adapter implementation
 * 
 * An adapter is an object that is referenced by an EObject, and that is
 * notified each time an action is performed on this object.
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public abstract class AbstractOptimusAdapter extends AdapterImpl {

	/**
	 * EObject that are being adapted by this instance
	 */
	private Set<EObject> adaptedEObjects = new HashSet<EObject>();

	/**
	 * Label Provider which purpose is to provide naming system for EObjects.
	 */
	protected LabelProvider eObjectLabelProvider = new EObjectLabelProvider();
	
	/**
	 * Method used to adapt the EObject.
	 * 
	 * The std method (eObject.eAdapters().add() is not used because this method
	 * is also used to store the eobject that is being adapted, to be used later
	 * when need to release all the objects adapted by this.
	 * 
	 * @param eObject
	 */
	public void adaptEObject(EObject eObject) {
		this.adaptedEObjects.add(eObject);
		eObject.eAdapters().add(this);
		OptimusM2MEngineMessages.AD01.log(this.getClass().getSimpleName(), eObjectLabelProvider.getText(eObject));
	}

	/**
	 * For all the EObjects that were adapted by this instance, using the
	 * {@link adaptEObject} method, this method automatically breaks the link
	 * between this instance and all the objects.
	 */
	public void release() {
		for (EObject adaptedEObject : adaptedEObjects) {
			adaptedEObject.eAdapters().remove(this);
			OptimusM2MEngineMessages.AD02.log(this.getClass().getSimpleName(), eObjectLabelProvider.getText(adaptedEObject));
		}
		this.adaptedEObjects.clear();
	}

}
