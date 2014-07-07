package net.atos.optimus.m2m.engine.core.ctxinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RootContextElement {

	String value() default ContextDefaultValues.ROOT;

	ContextElementVisibility visibility() default ContextElementVisibility.INOUT;

	boolean nullable() default true;
}
