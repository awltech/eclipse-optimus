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
package net.atos.optimus.m2m.engine.ctxinject.api;

import org.eclipse.emf.ecore.EObject;

/**
 * Implementations of this interface are to be used in the CustomContextElement
 * annotation. They will be used to retrieve the EObject to use as the context's
 * key, from the EObject used as current in a default transformation.
 * 
 * @author mvanbesien
 * 
 */
public interface IContextRetriever {

	/**
	 * Retrieves expected eObject from eObject passed as parameter.
	 * 
	 * @param eObject
	 * @return
	 */
	EObject getFromEObject(EObject eObject);

}
