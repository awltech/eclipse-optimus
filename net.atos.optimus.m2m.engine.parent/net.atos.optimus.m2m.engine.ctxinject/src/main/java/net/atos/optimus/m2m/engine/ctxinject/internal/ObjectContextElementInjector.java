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
package net.atos.optimus.m2m.engine.ctxinject.internal;

import java.lang.reflect.Field;

import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;
import net.atos.optimus.m2m.engine.ctxinject.api.ContextElementVisibility;
import net.atos.optimus.m2m.engine.ctxinject.api.ObjectContextElement;
import net.atos.optimus.m2m.engine.ctxinject.exceptions.FieldInjectionException;
import net.atos.optimus.m2m.engine.ctxinject.exceptions.FieldUpdateException;
import net.atos.optimus.m2m.engine.ctxinject.exceptions.NullValueException;

import org.eclipse.emf.ecore.EObject;

/**
 * Injector implementation for Object Context Element
 * 
 * @author mvanbesien
 * 
 */
public class ObjectContextElementInjector extends Injector {

	private Field field;
	private ContextElementVisibility visibility;
	private boolean nullable;
	private String mapping;

	public ObjectContextElementInjector(Field field) {
		this.field = field;
		ObjectContextElement annotation = field.getAnnotation(ObjectContextElement.class);
		this.visibility = annotation.visibility();
		this.mapping = annotation.value();
		this.nullable = annotation.nullable();
	}

	@Override
	public void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException {
		if (this.isInjectable()) {
			EObject eObject = this.mapping != null ? context.get(transformation.getEObject(), this.mapping) : null;
			if (eObject == null && !this.nullable) {
				OptimusM2MEngineCtxInjectMessages.CI12.log(transformation.getClass().getName(), this.field.getName());
				throw new NullValueException(field.getName());
			}
			setValue(transformation, field, eObject);
			OptimusM2MEngineCtxInjectMessages.CI07.log(transformation.getClass().getName(), this.field.getName(),
					eObjectLabelProvider.getText(eObject));
		} else {
			OptimusM2MEngineCtxInjectMessages.CI08.log(transformation.getClass().getName(), this.field.getName());
		}
	}

	@Override
	public void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException {
		if (this.isUpdatable()) {
			Object object = getValue(transformation, field);
			if (object == null && !this.nullable) {
				OptimusM2MEngineCtxInjectMessages.CI13.log(transformation.getClass().getName(), this.field.getName());
				throw new NullValueException(field.getName());
			}
			if (object instanceof EObject) {
				context.put(transformation.getEObject(), this.mapping, (EObject) object);
				OptimusM2MEngineCtxInjectMessages.CI09.log(transformation.getClass().getName(), this.field.getName(),
						eObjectLabelProvider.getText(object));
			} else {
				OptimusM2MEngineCtxInjectMessages.CI11.log(transformation.getClass().getName(), this.field.getName(),
						EObject.class.getName());
			}
		} else {
			OptimusM2MEngineCtxInjectMessages.CI10.log(transformation.getClass().getName(), this.field.getName());
		}

	}

	private boolean isInjectable() {
		return this.visibility != null ? this.visibility.isInjectable() : false;
	}

	private boolean isUpdatable() {
		return this.visibility != null ? this.visibility.isUpdatable() : false;
	}

}
