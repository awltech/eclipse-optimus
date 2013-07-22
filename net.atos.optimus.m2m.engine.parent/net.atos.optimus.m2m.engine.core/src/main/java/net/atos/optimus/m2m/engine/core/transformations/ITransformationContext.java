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

import org.eclipse.emf.ecore.EObject;

/**
 * Transformation Context.
 * 
 * The purpose of this object is to act like a session for a bunch of
 * transformations.
 * 
 * A stored object will be referenced using two identifiers : the source and the
 * key. Typically, the session is used to store the transformed EObjects. Hence,
 * the source will here stand for an identifier the input object, and the key
 * will correspond to a kind of discriminator value, in case several objects are
 * created/transformed from the source EObject
 * 
 * @author Maxence Vanbésien (mvaawl@gmail.com)
 * @since 1.0
 * 
 */
public interface ITransformationContext {

	/**
	 * Puts value into context.
	 * 
	 * @param source
	 *            : identifier for the source of the transformed object
	 * @param key
	 *            : discriminating value in case several objects are created
	 *            from transformation
	 * @param value
	 *            : value object
	 */
	public void put(EObject source, String key, EObject value);

	/**
	 * Get value from context
	 * 
	 * @param source
	 *            : identifier for the source of the transformed object
	 * @param key
	 *            : discriminating value in case several objects are created
	 *            from transformation
	 * @return: value object
	 */
	public EObject get(EObject source, String key);

	/**
	 * Returns the roots of this context
	 * 
	 * @return
	 */
	public Collection<EObject> getRoots();

	/**
	 * Get the root element referenced by the key passed as parameter
	 * 
	 * @param key
	 * @return
	 */
	public EObject getRoot(String key);

	/**
	 * Puts a new root for the provided key, in this context.
	 * 
	 * @param key
	 * @param eObject
	 */
	public void putRoot(String key, EObject eObject);

	/**
	 * Clears the context.
	 */
	public void dispose();

	/**
	 * Gets the property corresponding to proposed key
	 * 
	 * @param key
	 * @return property
	 */
	public String getProperty(String key);

	/**
	 * Puts new property with key & value in the context.
	 * 
	 * @param key
	 * @param value
	 */
	public void putProperty(String key, String value);
}
