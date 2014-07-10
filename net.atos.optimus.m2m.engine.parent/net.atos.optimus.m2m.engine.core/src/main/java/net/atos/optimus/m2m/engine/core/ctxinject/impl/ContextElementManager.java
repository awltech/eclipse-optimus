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
package net.atos.optimus.m2m.engine.core.ctxinject.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.atos.optimus.m2m.engine.core.ctxinject.ContextParameter;
import net.atos.optimus.m2m.engine.core.ctxinject.CustomContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.ObjectContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.ParentContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.RootContextElement;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

/**
 * 
 * Implementation of ContextElement injection management. This is implemented as
 * a singleton and supports caching.
 * 
 * @author mvanbesien
 * 
 */
public enum ContextElementManager {

	/**
	 * Instance
	 */
	INSTANCE;

	/**
	 * Cache implementation
	 */
	private Map<Field, Injector> injectorCache = new HashMap<Field, Injector>();

	/**
	 * true if cache is used, false otherwise
	 */
	private boolean cacheEnabled = false;

	/**
	 * Gets an injector instance. If cache is enabled, it uses it.
	 * 
	 * @param field
	 * @return
	 * @throws NullInstanceException
	 */
	private Injector getInjector(Field field) throws NullInstanceException {
		if (this.cacheEnabled) {
			Injector injector = this.injectorCache.get(field);
			if (injector == null) {
				injector = this.createInjector(field);
				this.injectorCache.put(field, injector);
			}
			return injector;
		} else {
			return this.createInjector(field);
		}
	}

	/**
	 * Creates an injector for the field. Returns null if field is not
	 * 
	 * @param field
	 * @return
	 * @throws NullInstanceException
	 */
	private Injector createInjector(Field field) throws NullInstanceException {
		if (field.getAnnotation(ContextParameter.class) != null) {
			return new ContextParameterInjector(field);
		} else if (field.getAnnotation(RootContextElement.class) != null) {
			return new RootContextElementInjector(field);
		} else if (field.getAnnotation(ObjectContextElement.class) != null) {
			return new ObjectContextElementInjector(field);
		} else if (field.getAnnotation(ParentContextElement.class) != null) {
			return new ParentContextElementInjector(field);
		} else if (field.getAnnotation(CustomContextElement.class) != null) {
			return new CustomContextElementInjector(field);
		}
		return null;
	}

	/**
	 * 
	 * Injects into annotated fields of the transformations, elements from the
	 * context.
	 * 
	 * @param transformation
	 * @param context
	 * @throws NullValueException
	 * @throws FieldInjectionException
	 * @throws NullInstanceException
	 */
	public void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException, NullInstanceException {
		for (Field field : transformation.getClass().getDeclaredFields()) {
			Injector injector = this.getInjector(field);
			if (injector != null) {
				injector.inject(transformation, context);
			}
		}
	}

	/**
	 * 
	 * updates context with valies from context-annotated fields of the
	 * transformations.
	 * 
	 * @param transformation
	 * @param context
	 * @throws NullValueException
	 * @throws FieldInjectionException
	 * @throws NullInstanceException
	 */
	public void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException, NullInstanceException {
		for (Field field : transformation.getClass().getDeclaredFields()) {
			Injector injector = this.getInjector(field);
			if (injector != null) {
				injector.update(transformation, context);
			}
		}
	}

	/**
	 * Enables the cache
	 */
	public void enableCache() {
		this.cacheEnabled = true;
	}

	/**
	 * Disables the cache
	 */
	public void disableCache() {
		this.cacheEnabled = false;
	}

	/**
	 * Empties the cache map
	 */
	public void clearCache() {
		this.injectorCache.clear();
	}
}
