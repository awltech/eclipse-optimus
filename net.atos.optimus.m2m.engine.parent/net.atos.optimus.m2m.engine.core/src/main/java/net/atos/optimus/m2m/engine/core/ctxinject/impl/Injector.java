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

import net.atos.optimus.m2m.engine.core.exceptions.FieldInjectionException;
import net.atos.optimus.m2m.engine.core.exceptions.FieldUpdateException;
import net.atos.optimus.m2m.engine.core.exceptions.NullValueException;
import net.atos.optimus.m2m.engine.core.logging.EObjectLabelProvider;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

public abstract class Injector {

	protected final EObjectLabelProvider eObjectLabelProvider = new EObjectLabelProvider();

	public abstract void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException;

	public abstract void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException;

	/**
	 * Generic implementation that sets a field value using reflection API
	 * 
	 * @param instance
	 * @param field
	 * @param value
	 * @throws FieldInjectionException
	 */
	protected static void setValue(Object instance, Field field, Object value) throws FieldInjectionException {
		if (value != null && field.getType().isAssignableFrom(value.getClass())) {
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}
			try {
				field.set(instance, value);
			} catch (IllegalArgumentException e) {
				OptimusM2MEngineMessages.CI17.log();
				throw new FieldInjectionException(field.getName(), e);
			} catch (IllegalAccessException e) {
				OptimusM2MEngineMessages.CI17.log();
				throw new FieldInjectionException(field.getName(), e);
			}
			if (!accessible) {
				field.setAccessible(false);
			}
		}
	}

	/**
	 * Generic implementation that sets a field value using reflection API
	 * 
	 * @param instance
	 * @param field
	 * @param value
	 * @throws FieldInjectionException
	 */
	protected static Object getValue(Object instance, Field field) throws FieldUpdateException {
		boolean accessible = field.isAccessible();
		if (!accessible) {
			field.setAccessible(true);
		}
		try {
			return field.get(instance);
		} catch (IllegalArgumentException e) {
			OptimusM2MEngineMessages.CI17.log();
			throw new FieldUpdateException(field.getName(), e);
		} catch (IllegalAccessException e) {
			OptimusM2MEngineMessages.CI17.log();
			throw new FieldUpdateException(field.getName(), e);
		} finally {
			if (!accessible) {
				field.setAccessible(false);
			}
		}
	}

}
