package net.atos.optimus.m2m.engine.core.ctxinject.impl;

/**
 * Thrown when an system exception is raised when trying to inject a field into
 * a transformation
 * 
 * @author mvanbesien
 * 
 */
public class FieldInjectionException extends Exception {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE = "An error occurred while injecting value into field: %s.";

	private String name;

	public FieldInjectionException(String name, Exception e) {
		super(e);
		this.name = name;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE, this.name);
	}

}
