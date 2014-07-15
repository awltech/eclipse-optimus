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
import net.atos.optimus.m2m.engine.core.ctxinject.CustomContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.IContextRetriever;
import net.atos.optimus.m2m.engine.core.exceptions.FieldInjectionException;
import net.atos.optimus.m2m.engine.core.exceptions.FieldUpdateException;
import net.atos.optimus.m2m.engine.core.exceptions.NullInstanceException;
import net.atos.optimus.m2m.engine.core.exceptions.NullValueException;
import net.atos.optimus.m2m.engine.core.logging.OptimusM2MEngineMessages;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.emf.ecore.EObject;

/**
 * Injector implementation for Custom Context Element
 * 
 * @author mvanbesien
 * 
 */
public class CustomContextElementInjector extends Injector {

	private Field field;
	private ContextElementVisibility visibility;
	private boolean nullable;
	private String mapping;
	private IContextRetriever contextRetriever;

	public CustomContextElementInjector(Field field) throws NullInstanceException {
		this.field = field;
		CustomContextElement annotation = field.getAnnotation(CustomContextElement.class);
		this.visibility = annotation.visibility();
		this.mapping = annotation.value();
		this.nullable = annotation.nullable();
		Class<? extends IContextRetriever> crClass = annotation.contextRetriever();
		if (crClass != null) {
			try {
				this.contextRetriever = crClass.newInstance();
				OptimusM2MEngineMessages.CI16.log(field.getDeclaringClass().getName(), this.field.getName(), crClass.getName().getClass());
			} catch (InstantiationException e) {
				OptimusM2MEngineMessages.CI14.log(field.getDeclaringClass().getName(), this.field.getName(), crClass.getName().getClass());
				throw new NullInstanceException(this.field.getName(), crClass, e);
			} catch (IllegalAccessException e) {
				OptimusM2MEngineMessages.CI14.log(field.getDeclaringClass().getName(), this.field.getName(), crClass.getName().getClass());
				throw new NullInstanceException(this.field.getName(), crClass, e);
			}
		} else {
			OptimusM2MEngineMessages.CI15.log(field.getDeclaringClass().getName(), this.field.getName());
		}

	}

	@Override
	public void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException {
		if (this.isInjectable() && this.contextRetriever != null) {
			EObject eObject = this.mapping != null ? context.get(
					this.contextRetriever.getFromEObject(transformation.getEObject()), this.mapping) : null;
			if (eObject == null && !this.nullable) {
				OptimusM2MEngineMessages.CI12.log(transformation.getClass().getName(), this.field.getName());
				throw new NullValueException(this.field.getName());
			} 
			setValue(transformation, this.field, eObject);
			OptimusM2MEngineMessages.CI07.log(transformation.getClass().getName(), this.field.getName(),
					eObjectLabelProvider.getText(eObject));
		} else {
			OptimusM2MEngineMessages.CI08.log(transformation.getClass().getName(), this.field.getName());
		}
	}

	@Override
	public void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException {
		if (this.isUpdatable() && this.contextRetriever != null) {
			Object object = getValue(transformation, this.field);
			if (object == null && !this.nullable) {
				OptimusM2MEngineMessages.CI13.log(transformation.getClass().getName(), this.field.getName());
				throw new NullValueException(this.field.getName());
			}
			if (object instanceof EObject) {
				EObject fromEObject = this.contextRetriever.getFromEObject(transformation.getEObject());
				if (fromEObject instanceof EObject) {
					context.put(fromEObject, this.mapping, (EObject) object);
					OptimusM2MEngineMessages.CI09.log(transformation.getClass().getName(), this.field.getName(),
							eObjectLabelProvider.getText(fromEObject));
				} else {
					OptimusM2MEngineMessages.CI11.log(transformation.getClass().getName(), this.field.getName(),
							EObject.class.getName());
				}
			}
		} else {
			OptimusM2MEngineMessages.CI10.log(transformation.getClass().getName(), this.field.getName());
		}

	}

	private boolean isInjectable() {
		return this.visibility != null ? this.visibility.isInjectable() : false;
	}

	private boolean isUpdatable() {
		return this.visibility != null ? this.visibility.isUpdatable() : false;
	}

}
