package net.atos.optimus.m2m.engine.core.ctxinject.impl;

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
