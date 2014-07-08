package net.atos.optimus.m2m.engine.core.ctxinject.impl;

import java.lang.reflect.Field;

import net.atos.optimus.m2m.engine.core.ctxinject.ContextElementVisibility;
import net.atos.optimus.m2m.engine.core.ctxinject.ContextParameter;
import net.atos.optimus.m2m.engine.core.transformations.AbstractTransformation;
import net.atos.optimus.m2m.engine.core.transformations.ITransformationContext;

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
