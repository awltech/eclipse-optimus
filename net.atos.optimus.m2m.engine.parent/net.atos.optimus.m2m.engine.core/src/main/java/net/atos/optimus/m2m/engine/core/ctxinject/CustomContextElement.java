package net.atos.optimus.m2m.engine.core.ctxinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Injects an context element into a field declared into an Optimus
 * transformation implementation.
 * 
 * The relation between the EObject to use as a key, from the current
 * transformation EObject, is computed by the implementation (contextRetriever)
 * to provide as a parameter.
 * 
 * @author mvanbesien
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomContextElement {

	/**
	 * @return the implementation used to retrieve, from the current EObject, the EObject to be
	 * used as a key in the context.
	 */
	Class<? extends IContextRetriever> contextRetriever();

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
