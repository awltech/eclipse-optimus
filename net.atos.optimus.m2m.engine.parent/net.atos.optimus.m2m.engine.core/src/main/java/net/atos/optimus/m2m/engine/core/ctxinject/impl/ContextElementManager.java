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

import net.atos.optimus.m2m.engine.core.ctxinject.ContextParameter;
import net.atos.optimus.m2m.engine.core.ctxinject.CustomContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.ObjectContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.ParentContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.RootContextElement;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

// TODO: Add cache support.
public enum ContextElementManager {
	
	INSTANCE;
	
	public void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldInjectionException, NullInstanceException {
		for (Field field : transformation.getClass().getDeclaredFields()) {
			if (field.getAnnotation(ContextParameter.class) != null) {
				new ContextParameterInjector(field).inject(transformation, context);
			} else if (field.getAnnotation(RootContextElement.class) != null) {
				new RootContextElementInjector(field).inject(transformation, context);
			} else if (field.getAnnotation(ObjectContextElement.class) != null) {
				new ObjectContextElementInjector(field).inject(transformation, context);
			} else if (field.getAnnotation(ParentContextElement.class) != null) {
				new ParentContextElementInjector(field).inject(transformation, context);
			} else if (field.getAnnotation(CustomContextElement.class) != null) {
				new CustomContextElementInjector(field).inject(transformation, context);
			}
		}
	}

	public void update(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, FieldUpdateException, NullInstanceException {
		for (Field field : transformation.getClass().getDeclaredFields()) {
			if (field.getAnnotation(ContextParameter.class) != null) {
				new ContextParameterInjector(field).update(transformation, context);
			} else if (field.getAnnotation(RootContextElement.class) != null) {
				new RootContextElementInjector(field).update(transformation, context);
			} else if (field.getAnnotation(ObjectContextElement.class) != null) {
				new ObjectContextElementInjector(field).update(transformation, context);
			} else if (field.getAnnotation(ParentContextElement.class) != null) {
				new ParentContextElementInjector(field).update(transformation, context);
			} else if (field.getAnnotation(CustomContextElement.class) != null) {
				new CustomContextElementInjector(field).update(transformation, context);
			}
		}
	}
}
