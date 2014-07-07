package net.atos.optimus.m2m.engine.core.ctxinject.impl;

import java.lang.reflect.Field;

import net.atos.optimus.m2m.engine.core.ctxinject.ContextElementVisibility;
import net.atos.optimus.m2m.engine.core.ctxinject.ContextParameter;
import net.atos.optimus.m2m.engine.core.ctxinject.CustomContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.IContextRetriever;
import net.atos.optimus.m2m.engine.core.ctxinject.ObjectContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.ParentContextElement;
import net.atos.optimus.m2m.engine.core.ctxinject.RootContextElement;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

import org.eclipse.emf.ecore.EObject;

public class ContextElementManager {

	private ContextElementManager() {
	}

	public static void inject(AbstractTransformation<?> transformation, ITransformationContext context)
			throws NullValueException, NullInstanceException, FieldInjectionException {

		for (Field declaredField : transformation.getClass().getDeclaredFields()) {
			if (declaredField.getAnnotation(ContextParameter.class) != null) {
				ContextParameter annotation = declaredField.getAnnotation(ContextParameter.class);
				String value = annotation.value();
				if (value == null && !annotation.nullable())
					throw new NullValueException(declaredField.getName());
				setValue(transformation, declaredField, value);
			} else if (declaredField.getAnnotation(RootContextElement.class) != null) {
				RootContextElement annotation = declaredField.getAnnotation(RootContextElement.class);
				ContextElementVisibility visibility = annotation.visibility();
				if (visibility == ContextElementVisibility.IN || visibility == ContextElementVisibility.INOUT) {
					String value = annotation.value();
					EObject eObject = value != null ? context.getRoot(value) : null;
					if (eObject == null && !annotation.nullable())
						throw new NullValueException(declaredField.getName());
					setValue(transformation, declaredField, eObject);
				}
			} else if (declaredField.getAnnotation(ObjectContextElement.class) != null) {
				ObjectContextElement annotation = declaredField.getAnnotation(ObjectContextElement.class);
				ContextElementVisibility visibility = annotation.visibility();
				if (visibility == ContextElementVisibility.IN || visibility == ContextElementVisibility.INOUT) {
					String value = annotation.value();
					EObject eObject = value != null ? context.get(transformation.getEObject(), value) : null;
					if (eObject == null && !annotation.nullable())
						throw new NullValueException(declaredField.getName());
					setValue(transformation, declaredField, eObject);
				}
			} else if (declaredField.getAnnotation(ParentContextElement.class) != null) {
				EObject parent = transformation.getEObject().eContainer();
				if (parent != null) {
					ParentContextElement annotation = declaredField.getAnnotation(ParentContextElement.class);
					ContextElementVisibility visibility = annotation.visibility();
					if (visibility == ContextElementVisibility.IN || visibility == ContextElementVisibility.INOUT) {
						String value = annotation.value();
						EObject eObject = value != null ? context.get(parent, value) : null;
						if (eObject == null && !annotation.nullable())
							throw new NullValueException(declaredField.getName());
						setValue(transformation, declaredField, eObject);
					}
				}
			} else if (declaredField.getAnnotation(CustomContextElement.class) != null) {
				CustomContextElement annotation = declaredField.getAnnotation(CustomContextElement.class);
				Class<? extends IContextRetriever> contextRetriever = annotation.contextRetriever();
				if (contextRetriever != null) {
					IContextRetriever newInstance = null;
					try {
						newInstance = contextRetriever.newInstance();
					} catch (InstantiationException e) {
						throw new NullInstanceException(declaredField.getName(), contextRetriever, e);
					} catch (IllegalAccessException e) {
						throw new NullInstanceException(declaredField.getName(), contextRetriever, e);
					}
					if (newInstance == null) {
						throw new NullInstanceException(declaredField.getName(), contextRetriever);
					}
					ContextElementVisibility visibility = annotation.visibility();
					if (visibility == ContextElementVisibility.IN || visibility == ContextElementVisibility.INOUT) {
						String value = annotation.value();
						EObject eObject = value != null ? context.get(
								newInstance.getFromEObject(transformation.getEObject()), value) : null;
						if (eObject == null && !annotation.nullable())
							throw new NullValueException(declaredField.getName());
						setValue(transformation, declaredField, eObject);
					}
				}
			}
		}
	}

	public static void update(AbstractTransformation<?> transformation, ITransformationContext context) {
		// TODO
	}

	private static void setValue(Object instance, Field field, Object value) throws FieldInjectionException {
		if (value != null && field.getType().isAssignableFrom(value.getClass())) {
			boolean accessible = field.isAccessible();
			if (!accessible) {
				field.setAccessible(true);
			}
			try {
				field.set(instance, value);
			} catch (IllegalArgumentException e) {
				throw new FieldInjectionException(field.getName(), e);
			} catch (IllegalAccessException e) {
				throw new FieldInjectionException(field.getName(), e);
			}
			if (!accessible) {
				field.setAccessible(false);
			}
		}
	}

}
