package net.atos.optimus.m2m.engine.core.ctxinject.impl;

/**
 * Thrown when the process tries to inject a null value for a field, while the
 * field's annotation specifies that the field is not nullable. This also throws
 * the same error, if the field is updatable, and the value is null when trying
 * to update.
 * 
 * @author mvanbesien
 * 
 */
public class NullValueException extends Exception {

	private String name;

	private static final String MESSAGE = "Not allowed to inject null value into field %s";

	public NullValueException(String name) {
		this.name = name;
	}

	@Override
	public String getMessage() {
		return String.format(MESSAGE, this.name);
	}

	private static final long serialVersionUID = 1L;

}
