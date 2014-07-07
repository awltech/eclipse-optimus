package net.atos.optimus.m2m.engine.core.ctxinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Injects an context element into a field declared into an Optimus
 * transformation implementation.
 * 
 * The EObject used as key for context retrieval is the current container of the
 * EObject used as current EObject in transformation
 * 
 * @author mvanbesien
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ParentContextElement {

	/**
	 * @return the mapping part of the key to use. Default is SELF
	 */
	String value() default ContextDefaultValues.SELF;

	/**
	 * @return the Context Element visibility. Default is INOUT
	 */
	ContextElementVisibility visibility() default ContextElementVisibility.INOUT;

	/**
	 * @return If set to false, and exception will be thrown is the value to
	 *         inject is null
	 */
	boolean nullable() default true;
}
