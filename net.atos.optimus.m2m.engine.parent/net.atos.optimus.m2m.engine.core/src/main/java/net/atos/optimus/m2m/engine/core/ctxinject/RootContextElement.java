package net.atos.optimus.m2m.engine.core.ctxinject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Injects a context element into a field declared into an Optimus
 * transformation implementation.
 * 
 * the injected element will be retrieved from the root map.
 * 
 * @author mvanbesien
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RootContextElement {

	/**
	 * Value of the key in root map. default is ROOT
	 * @return
	 */
	String value() default ContextDefaultValues.ROOT;

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
