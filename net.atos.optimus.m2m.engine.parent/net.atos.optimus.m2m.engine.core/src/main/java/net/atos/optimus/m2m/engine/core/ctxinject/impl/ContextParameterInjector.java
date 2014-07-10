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

import net.atos.optimus.m2m.engine.core.ctxinject.ContextElementVisibility;
import net.atos.optimus.m2m.engine.core.ctxinject.ContextParameter;
import net.atos.optimus.m2m.engine.core.exceptions.FieldInjectionException;
import net.atos.optimus.m2m.engine.core.exceptions.FieldUpdateException;
import net.atos.optimus.m2m.engine.core.exceptions.NullValueException;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

/**
 * Injector implementation for Context properties
 * 
 * @author mvanbesien
 * 
 */
public class ContextParameterInjector extends Injector {

	private Field field;
	private ContextElementVisibility visibility;
	private boolean nullable;
	private String mapping;

	public ContextParameterInjector(Field field) {
		this.field = field;
		ContextParameter annotation = field.getAnnotation(ContextParameter.class);
		this.visibility = annotation.visibility();
		this.mapping = annotation.value();
		this.nullable = annotation.nullable();
	}

	@Override
	public void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException {
		if (this.isInjectable()) {
			String property = this.mapping != null ? context.getProperty(this.mapping) : null;
			if (property == null && !this.nullable) {
				throw new NullValueException(this.field.getName());
			}
			Injector.setValue(transformation, this.field, property);
		}
	}

	@Override
	public void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException {
		if (this.isUpdatable()) {
			Object object = getValue(transformation, this.field);
			if (object == null && !this.nullable) {
				throw new NullValueException(this.field.getName());
			}
			if (object instanceof String) {
				context.putProperty(this.mapping, (String) object);
			}
		}

	}

	private boolean isInjectable() {
		return this.visibility != null ? this.visibility.isInjectable() : false;
	}

	private boolean isUpdatable() {
		return this.visibility != null ? this.visibility.isUpdatable() : false;
	}

}
