package net.atos.optimus.m2m.engine.core.ctxinject;

/**
 * Defines the scope of the context injection
 * 
 * @author mvanbesien
 * 
 */
public enum ContextElementVisibility {
	/**
	 * For field injection only
	 */
	IN,

	/**
	 * for field update only
	 */
	OUT,

	/**
	 * For field injection and update
	 */
	INOUT
}
